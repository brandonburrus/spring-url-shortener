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

## URL Shortening Algorithm

This URL shortener algorithm will average 6 characters, maxing at 9 (depending on the lifetime of the applications database).

It does this by first splitting the protocol and host name from the actual path/route name and query parameters. These two components are stored separately in the database using a hexacondrabimal id instead a normal decimal id (essentially an identifier that uses 0-9, all uppercase, and all lowercase characters meanings 62 character possibilities). These two identifiers are joined by a `.` to create a complete url id.

By doing this, it ensures that the first part of the url id (the protocol + host name) will be very unlikely to be more than 4 characters as the number of unique domains in the database would need to reach 14,776,336 before it because 5 characters (62^4=14,776,336). It also means that the second part of the url id (path/route name + query parameters) would need to reach 238,328 before it became 4 characters (62^3=238,328).

An average of 3 (4 once it passes 238,328 host names) characters for the host name and an average of 2 characters for the route/path name means 6-7 character average of the url id (also counting the `.`). This maxes at 9 (possbily 10 in the future) as there are currently only ~13 million registered domains world wide, adding 1 for the `.` and 4 at most for the route/path name (not accounting for the possibility of a double registering for both the HTTP and HTTPS protocol which would count for two separate entries in the host name database table)

The algorithm for converting a number to a hexacondrabimal can be found in the [IdShortener](./src/main/java/com/brandonburrus/urlshortener/util/IdShortener.java) class.
