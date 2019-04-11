#!/usr/bin/env bash

APP=inventory
VERSION=dev
S2I_IMAGE=java:8

. ../env.sh

DEV_PROJECT=quay-dev

oc login https://${IP}:8443 -u $USER

oc project ${DEV_PROJECT}

oc delete all -l app=${APP} -n ${DEV_PROJECT}
oc delete pvc -l app=${APP} -n ${DEV_PROJECT}
oc delete sa ${APP}-sa
oc delete is,bc,dc,svc,route,sa ${APP} -n ${DEV_PROJECT}
oc delete template ${APP}-dev-dc ${APP}-dev-template -n ${DEV_PROJECT}
oc delete configmap ${APP}-config -n ${DEV_PROJECT}

echo Setting up ${APP} for ${DEV_PROJECT}
oc new-app -f spring-boot-dev-quay-template.yaml \
    -p PROJECT=${DEV_PROJECT} \
    -p APPLICATION_NAME=${APP} \
    -p BASE_IMAGE_NAMESPACE="openshift" \
    -p BASE_IMAGE=${S2I_IMAGE} \
    -n ${DEV_PROJECT}

