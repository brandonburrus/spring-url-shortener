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

## Quick Reference

| Route | Description |
| --- | --- |
| `POST /api/v1/shorten` | Takes a JSON object of `{ "url": "https://example.org" }` as a body and will respond with a shortened url |
| `POST /api/v1/shorten/multiple` | Takes a JSON array of url strings and returns with each one shortened as a single response |
| `GET /api/v1/url/{urlId}` | Retrieves the corresponding URL for the given `urlId` request parameter |
| `GET /u/{urlId}` | Retrieves and directly redirects to the given URL from the `urdId` request parameter |
