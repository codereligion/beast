# Contributing

## Issues
We welcome any feature requests or bug reports. Keep in mind to be as detailed as you can in the description, or even better if you can provide a failing unit test.

## Pull Requests

### Setup
To develop this project you should have the following setup:
* Java 1.6 or higher
* Maven 3.2.2 or higher

### Build
We use Jenkins CI to run [our builds](http://jenkins.codereligion.com/view/codereligion-beast/job/codereligion-beast-master-build-flow/).

#### Java version compliance
The byte code is and should be compliant to all Java versions starting and including 1.6.

#### Continuous integration and local testing
CI compiles the code and runs the tests for Oracle JDK 6u29. It will fail on any compiler warnings. In order to debug compiler warnings and errors more efficiently we recommend to install
the above mentioned JDK versions. We also recommend to use [jEnv](http://www.jenv.be/) by Gildas Cuisinier to switch between them easily.

Run local tests with: ```mvn clean test```, or by running unit tests from your favorite IDE.

### Coding guidelines
We roughly follow the [java coding guidelines](http://www.oracle.com/technetwork/java/codeconv-138413.html), though they are quite obsolete on some topics. We follow more strictly the warnings generated by our [sonar](http://sonar.codereligion.com). If you want to check your pull request for compliance with our sonar quality profile simply install the sonar plugin for you favourite IDE and run local analysis against our server:

URL: http://sonar.codereligion.com <br>
User: contributor <br>
PW: contributor 

### Testing guidelines
We use hamcrest matchers in combination with JUnit to write expressive and compact tests.