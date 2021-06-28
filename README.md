# Delivery Service

Delivery service. Simple java application, using only Java SE 11.

## Demo class

by.exposit.demo.MainDemoService

Main demo class writes demonstration's result into log (appender: console and rolling file) .

## Installing / Getting started

### Using docker

1. Building the docker image from project using 'Dockerfile'
```shell
docker build -f Dockerfile -t aliaksandrrachko/delivery-app .
```
or pull from DockerHub
```shell
docker pull aliaksandrrachko/delivery-app
```

2. Running the built docker image
```shell
docker run -t --name delivery-app aliaksandrrachko/delivery-app
```

## Developing

### Modules
* delivery-main (main module running application)
* delivery-entity
* delivery-service
* delivery-api
* delivery-dao
* delivery-core 
  (The most interesting module, to implement the Dependency Injection pattern to achieve loose coupled classes. 
  This module implements IoC Container for implementing automatic dependency injection. It manages object creating,
  and it's life-time, and also injects dependencies to the class.)
* delivery-demo (contains demo classes)

### Built With
* Java » 11
* Lombok » 1.18.16
* Jackson Databind » 2.12.3
* Jackson Core » 2.12.3
* Jackson Annotations » 2.12.3
* Jackson Datatype: JSR310 » 2.12.3
* Logback Classic Module » 1.2.3
* Reflections » 0.9.12
* CGLib » 3.3.0
* Apache Commons Lang » 3.12.0

## Versioning

* 0.0.1-SNAPSHOT

## Style guide

[Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)

## Database

WARNING: The Database must be in the root directory with the .jar file.

Database with saving to file in json format.

Naming convention for jsons files:
```shell
[example:] entity_id.json
```

Database folder structure:
+ database
    + client
    + order 
    + product
    + store

## Licensing

No license