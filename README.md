### How to start
* clone this repo `git clone https://github.com/vadikpkin/task.git`
* fill `src/test/resources/test.properties` with credentials
* to run api tests execute `mvn clean test -Papi`
* to run ui tests execute `mvn clean test -Pui`
* to run all tests execute `mvn clean test`
* to get report: `allure serve`