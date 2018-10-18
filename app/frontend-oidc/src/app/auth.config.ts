import { AuthConfig } from 'angular-oauth2-oidc';

export const authConfig: AuthConfig = {

// Url of the Identity Provider
issuer: 'https://secure-sso.apps.ocp.datr.eu/auth/realms/amazin',

// URL of the SPA to redirect the user to after login
redirectUri: window.location.origin + '/#',

// The SPA's id. The SPA is registerd with this id at the auth-server
clientId: 'amazin-app-view',

// set the scope for the permissions the client should request
// The first three are defined by OIDC. The 4th is a usecase-specific one
scope: 'openid',
}
