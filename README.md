# Cake Manager

This repo contains my solution to the [Waracle Cake Manage technical test](https://github.com/Waracle/cake-manager).
It features a REST API built with [Spring Boot](https://spring.io/projects/spring-boot), which reads from/writes to an in-memory H2 database using a [Spring Data JPA repository](https://spring.io/projects/spring-data-jpa).
The back end is built with [Gradle](https://gradle.org/), and Java 15, and is packaged with [Docker](https://www.docker.com/).
Also included is a front end client application, written in [Typescript](https://www.typescriptlang.org/) and [React](https://reactjs.org/), built with [Yarn](https://yarnpkg.com/), and packaged with Docker.

## Getting started

The easiest way to get up and running is by running the [docker-compose](./docker-compose.yml) from the root of this repository:

```
docker-compose up
```

This will build and start both the API service and the client app on ports 8080 and 3000 respectively.

Alternatively you can build and run each application individually. For the back end, run the following from `./cake-api`:

```
./gradlew build bootRun
```

*Note: the back end requires Java 15 to run.*

Once the back end is running, set the root API path as an environment variable `REACT_APP_API_ROOT_URL=http://localhost:8080/` and then run the following from `./cake-ui`:

```
yarn install && yarn build && yarn start
```

*Note: the front end requires Node and yarn to be installed globally prior to use.*

## Notes

- This repo uses github actions for CI, building both the BE and FE on push to master (or on creating a PR onto master). See workflow definitions [here](./.github/workflows)
- Tests for the API are located [here](./cake-api/src/test/java/cake/api/controller/CakeControllerTest.java)
- There's a known issue with using JUnit 5 with spring-boot-starter-test, which is resolved by [excluding JUnit dependencies from that library](./cake-api/build.gradle#L23)
- I've [left CORS open to all origins](./cake-api/src/main/java/cake/api/CakeApiApplication.java#L23) for now to make it easier to use a rest client such as Postman to inspect the API. In a real application, this would likely be need to be restricted.
- By default, H2 doesn't require a password - this has been overridden [here](./cake-api/src/main/resources/application.properties#L4)

## Further Developments

If time/money were not a factor I would have liked to make the following additional changes:

- Require users to authenticate prior to creating/updating/deleting data
- Migrate from in-memory DB to real DB
- Push built docker images to AWS ECR repo on passing build
- Use Terraform to create AWS infrastructure to enable CD on merge to master
- Create end to end UI tests, run as part of CD pipeline
