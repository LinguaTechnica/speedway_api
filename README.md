# Dockerizing a Spring Boot App

This is a project to demonstrate for students how to configure Docker and use Docker Compose to build and run a Spring Boot application. It comes in 3 environments: latest (prod), dev, and test.

### Usage

You can run it directly from Docker Hub by using `docker run lotech/speedway_api:latest`.

You can also pull it to local to build and run it. But you must first edit the environment variables found in `application.properties` to run it:

- DB_USER
- DB_PASSWORD
- DB_HOST
- DB_PORT
- DB_NAME

## How It Works

This app has 3 configurations:

* `Dockerfile`: The image configuration
* `application.properties`: Spring profiles for the app config (test, prod and dev).
* `docker-compose.yaml`: The stack configuration which includes MySQL and the Spring service.

All environment variables for the Spring application are stored in the environment. Those variables are then used for all other configurations in the app.

Docker has it's own environments for each container and those variables are defined in `docker-compose.yaml`. By configuring the properties files, you can streamline the build and running of the app for any environment.

Try building and running the app to get a closer look at how they work.

``` 
docker build -t speedway_api:dev
docker run speedway_api:dev

# or the other builds
docker build -t speedway_api:test
docker build -t speedway_api:latest
```

All of these are local builds and exist only on your machine. When running the version from the repository of origin, the tags are lotech/speedway_api:dev and so forth.

## Development

Google maintains a Docker Gradle project that makes builds fast and much more reliable. It's called Jib and you can [find out more about it here](https://spring.io/guides/gs/spring-boot-docker/#scratch).

Using the Jib package is FAR faster and easier than doing it from the local gradle and it's highly recommended. This application uses it already.

### Build Reports

Docker/gradle also writes reports when a docker build is run. They're HTML documents that can be found in `build/reports/tests/test/index.html` and contain info about the failures and successes of the run.

However, the reports don't update locally when building Docker unfortunately.

### Common commands:

```bash
# docker-compose exec service_name command
docker-compose exec mysqldb bash

# Build docker image with gradle
./gradlew jibDockerBuild --image=repo_name/image_name
```

### Docker Compose

Docker compose will build and launch services for you. Configure them with `docker-compose.yaml`.  Any env variables that are on the container can be used and/or overwritten using the yaml file.

[Environment variables](https://docs.docker.com/compose/environment-variables/#pass-environment-variables-to-containers) can be used in docker-compose.yaml.

- `up`: Builds, (re)creates, starts, and attaches to containers for a service.
- `down`: Stops containers and removes containers, networks, volumes, and images created by `up`

The compose configuration is used each time the command is run, so some changes can be made to it without rebuilding the image.

#### Data

If data is persisted via volumes, then database changes to the compose file will not take effect when `compose up`. The volumes have to be removed:

`docker-compose down -v`

## Troubleshooting

1. Must rebuild before running compose if there have been changes to the app or config

There's no consistently documented way to use `docker build` on a gradle project that doesn't have user permissions issues when connecting with the database.