#!/usr/bin/env bash

APP=web
S2I_IMAGE=openshift/nginx:1.10

. ../../../env.sh

oc login https://${IP}:8443 -u $USER

oc project ${DEV_PROJECT}

oc delete all -l app=${APP} -n ${DEV_PROJECT}
oc delete pvc -l app=${APP} -n ${DEV_PROJECT}
oc delete is,bc,dc,svc,route ${APP} -n ${DEV_PROJECT}
oc delete template ${APP}-dev-dc -n ${DEV_PROJECT}
oc delete configmap ${APP}-config -n ${DEV_PROJECT}

echo Setting up ${APP} for ${DEV_PROJECT}
oc new-build --binary=true --strategy=source --labels=app=${APP} --name=${APP} --image-stream=${S2I_IMAGE} -n ${DEV_PROJECT}
oc new-app -f ./${APP}-dev-dc.yaml --allow-missing-imagestream-tags=true -n ${DEV_PROJECT}
oc expose dc ${APP} --port 8080 -n ${DEV_PROJECT}
oc expose svc ${APP} -l app=${APP} -n ${DEV_PROJECT}

