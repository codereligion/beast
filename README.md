*work in progress*

# BEAST [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.codereligion/codereligion-beast/badge.svg?style=plastic)](https://maven-badges.herokuapp.com/maven-central/com.codereligion/codereligion-beast) [![Build Status](https://ssl.webpack.de/secure-jenkins.codereligion.com/buildStatus/icon?job=codereligion-beast-master-build-flow)](http://jenkins.codereligion.com/view/codereligion-beast/job/codereligion-beast-master-build-flow/) [![SonarQube Coverage](https://img.shields.io/sonar/http/sonar.codereligion.com/com.codereligion:codereligion-beast/coverage.svg?style=plastic)](http://sonar.codereligion.com/dashboard/index/365)

The name of the project comes from BEAn teSTing. It provides facilities to test basic bean functionalities like the toString, equals and hashCode implementation.

For more details have a look at the [wiki](https://github.com/codereligion/beast/wiki).

## Build

### Setup
* Java 1.6 or higher

### Continuous integration and local testing
[![Build Status](https://api.travis-ci.org/codereligion/beast.png?branch=master)](https://travis-ci.org/codereligion/beast)

We use Travis CI to run our builds. The build compiles the code and runs the tests for OpenJDK 6, OpenJDK 7 and Oracle JDK 7.
It will fail on any compiler warnings. In order to debug compiler warnings and errors more efficiently we recommend to install
the above mentioned JDK versions. We also recommend to use [jEnv](http://www.jenv.be/) by Gildas Cuisinier to switch between them easily.

When you run local tests with: ```mvn clean test``` Maven will automatically try to compile the code and run the tests with 
the Java version which is currently set on your system.
