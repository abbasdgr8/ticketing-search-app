# ticketing-search-app
Implementation of a CLI application to search ticketing data and return results 
in a human readable format.

## Pre-Requisites
- JDK ==> 14
- Gradle ==> 6+
- Kotlin ==> 1.3+

## Install
```bash
# Clone the repo
$ git clone git@github.com:abbasdgr8/ticketing-search-app.git

# Change into the directory
$ cd ticketing-search-app

# Initiate the gradle build
$ ./gradlew clean build
```

## Run tests
```bash
# Runs all Unit Tests in the project
$ ./gradlew clean test

# Runs all Unit Tests in a specific test class
$ ./gradlew clean test --tests fully.qualified.ClassName
```

## Notes
### Tech Debt / TODOs
- [ ] Add a plugin that calculates Test Coverage during build

### Assumptions
- [ ] It is acceptable to use [given input data](https://github.com/abbasdgr8/ticketing-search-app/tree/develop/src/test/resources/json) for arriving at the basic search functionality.