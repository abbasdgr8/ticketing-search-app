# ticketing-search-app

[![Build Status](https://travis-ci.com/abbasdgr8/ticketing-search-app.svg?token=sVudbeziXANtkqKLaFRM&branch=master)](https://travis-ci.com/abbasdgr8/ticketing-search-app)
[![GitHub license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](https://www.apache.org/licenses/LICENSE-2.0)


Implementation of a (Kotlin) CLI application to search ticketing data and return results 
in a human readable format.

In order to standardise feature development and ensure separation of concerns, this search tool 
has been developed as a Model-View-Controller application.

## Pre-Requisites
- git on bash
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

If you'd like to run and modify tests independently in IntelliJ, you need to install the [Spek Framework Plugin](https://plugins.jetbrains.com/plugin/10915-spek-framework)

## Usage
```bash
# Runs the main class (App.kt)
$ ./gradlew run --console=plain
```

## Notes

This application was developed and tested on a MacOS Catalina. Therefore, there might possibly 
be behavioural differences on how the CLI app runs on a different OS

### Tech Debt / TODOs
- [ ] Add build and code coverage badges to repo
- [ ] Add a plugin that calculates Test Coverage during build
- [ ] Clean up and beautify CLI views that get displayed to the end user. Perhaps evaluate CLI libraries like Clikt
- [ ] Add more tests for Controllers

### Assumptions
- [ ] It is acceptable to use [given input data](https://github.com/abbasdgr8/ticketing-search-app/tree/master/src/test/resources/json) for arriving at the basic search functionality.
- [ ] It is possible that some of the given input data could be invalid and so there need to be cases to handle that scenario
- [ ] All input data would be of size that could be easily loaded into the JVM memory; thereby eliminating the need of data persistence.