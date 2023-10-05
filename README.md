# spring-starter-oidc-resourceserver

OIDC resource server written in Kotlin, powered by Spring Boot. This project is part of a
bundle of services that I'm developing for learning purposes only. Once
finished, this repository could be a starting point for a Spring Boot OIDC resource server.

### Technology stack
- docker
- kotlin
- spring boot

## Disclaimer

Files included in this repository are meant to be used in local
environment only. Please ensure all passwords or keys included here are not
used in production.

### List of files that include keys/passwords/secrets
- ./local/compose.yml

## Requirements

- Docker image for auth server [see](https://github.com/emidiocruciani/django-starter-oidc-authserver/)

## Installation

```shell
# ensure required docker images were built (see previous paragraph)

# install and run services
. ./local/scripts/install
```

## Related projects

- Auth server [django-starter-oidc-authserver](https://github.com/emidiocruciani/django-starter-oidc-authserver/)
- Frontend app [angular-starter-oidc-app](https://github.com/emidiocruciani/angular-starter-oidc-app/)
