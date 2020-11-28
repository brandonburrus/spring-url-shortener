# spring-url-shortener
Spring app for simple url shortening

## Getting Started

Pre-requisites:
1. JDK >=8
2. MySQL >=5
3. Maven >=3

Instructions:
1. Clone the repo.
2. Create a local MySQL database named `urlshortener`.
3. Update the `spring.datasource` in `src/main/resources/application.properties` with your local db username + password.
(optional) 3b. Also configure the spring.jpa.hibernate.ddl-auto to "update" instead of "create-drop".
4. Run `mvn clean install`.
5. Run `mvn spring-boot:run` to start the application.
