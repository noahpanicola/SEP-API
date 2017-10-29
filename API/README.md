## About

This is just a small project to help me learn Docker and Java (Spring Boot) concepts.

## Requirements

- Maven (3.5.0)
- Docker (>= v17.0)

## Running

1. Navigate to the project root in Terminal
2. Run `mvn install`
3. Run `docker build -t fun_container .` to create the Docker image
4. Run `docker run -p 8080:8080 fun_container` to run the container using the previously built image
5. You should now be able to make requests to 'localhost:8080'

## Disclaimer

The skeleton for this project was taken from [this project](https://github.com/damithme/spring-boot-REST). I'm
just using this to learn. No commercial gain here or nothin...
