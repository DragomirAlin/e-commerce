# Security

I'm going to use a third party solution to manage the security.
As a third party solution I will use Keycloak.

## Keycloak

Keycloak is an open source identity and access management solution.

## Spring Security

I'm using Keycloak adapters(`keycloak-spring-boot-starter`) for my application but in production I would use OAuth2 and OpenID(`spring-boot-starter-oauth2-resource-server`) libraries by spring, because
Keycloak adapters have been deprecated and won't be maintained anymore in the future. [Spring Security and Boot deprecation #10187](https://github.com/keycloak/keycloak/discussions/10187)

## Spring Security Test
I'm trying some nice tools for testing the security of my application.
