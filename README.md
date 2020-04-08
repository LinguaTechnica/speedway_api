# Dockerizing a Spring Boot App

To use, first edit the environment variables found in `application.properties` to match your environment:

- DB_USER
- DB_PASSWORD
- DB_HOST
- DB_PORT
- DB_NAME


``` 
docker run lotech/speedway_api:dev
```
Instructions: 
- https://spring.io/guides/gs/spring-boot-docker/#scratch

Using the Jib package is FAR faster and easier than doing it from the local gradle.

### Build Reports

Docker/gradle also writes reports when a docker build is run. They're HTML documents that can be found in `build/reports/tests/test/index.html` and contain info about the failures and successes of the run.

### Common commands:

```bash
# docker-compose exec service_name command
docker-compose exec mysqldb bash

# Build docker image with gradle
./gradlew jibDockerBuild --image=repo_name/image_name
```

## Docker Compose

Docker compose will build and launch services for you. Configure them with `docker-compose.yaml`.  Any env variables that are on the container can be used and/or overwritten using the yaml file.

[Environment variables](https://docs.docker.com/compose/environment-variables/#pass-environment-variables-to-containers) can be used in docker-compose.yaml.

- `up`: Builds, (re)creates, starts, and attaches to containers for a service.
- `down`: Stops containers and removes containers, networks, volumes, and images created by `up`

The compose configuration is used each time the command is run, so some changes can be made to it without rebuilding the image.

### Data

If data is persisted via volumes, then database changes to the compose file will not take effect when `compose up`. The volumes have to be removed:

`docker-compose down -v`

## Troubleshooting

1. Must rebuild before running compose if there have been changes to the app or config

There's no consistently documented way to use `docker build` on a gradle project that doesn't have user permissions issues when connecting with the database.