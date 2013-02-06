integration-test-demo
====

Demonstrates how to run regular unit tests and integration tests side by side in a single project.
Uses a combination of the surefire and failsafe plugins with jetty as a integration test servlet container.

Unit tests

    mvn test

Integration tests

    mvn verify

Running the server

    mvn jetty:run
    open http://localhost:8080/integration-test-demo/app?base=2
