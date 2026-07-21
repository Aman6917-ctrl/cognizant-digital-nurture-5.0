# Spring REST & Spring Core (XML)

Hands-on exercises **1–24** for Spring Boot **3.3.x**, Spring Web, Spring Core XML, REST, validation, exception handling, security, JWT, and SLF4J (Java **21**).

| Hands-On | Folder | Focus |
|----------|--------|--------|
| 1 | [Hands-On-01-Spring-Web-Project](Hands-On-01-Spring-Web-Project/) | Boot web, embedded Tomcat, `/api/health` |
| 2 | [Hands-On-02-DateFormat-Bean](Hands-On-02-DateFormat-Bean/) | `SimpleDateFormat` XML bean |
| 3 | [Hands-On-03-Logging](Hands-On-03-Logging/) | SLF4J levels & patterns |
| 4 | [Hands-On-04-Country-Bean](Hands-On-04-Country-Bean/) | XML bean, setter injection |
| 5 | [Hands-On-05-Bean-Scopes](Hands-On-05-Bean-Scopes/) | Singleton vs prototype |
| 6 | [Hands-On-06-Country-List](Hands-On-06-Country-List/) | `<list>` / `<ref>` wiring |
| 7 | [Hands-On-07-Hello-REST-Service](Hands-On-07-Hello-REST-Service/) | `GET /hello` plain text REST |
| 8 | [Hands-On-08-Country-REST-Service](Hands-On-08-Country-REST-Service/) | XML `Country` bean as JSON |
| 9 | [Hands-On-09-Get-All-Countries](Hands-On-09-Get-All-Countries/) | `GET /countries` list JSON |
| 10 | [Hands-On-10-Get-Country-By-Code](Hands-On-10-Get-Country-By-Code/) | Path lookup, 404 via `ResponseEntity` |
| 11 | [Hands-On-11-Country-Exception](Hands-On-11-Country-Exception/) | `CountryNotFoundException` + `@ResponseStatus` |
| 12 | [Hands-On-12-MockMvc-Success-Test](Hands-On-12-MockMvc-Success-Test/) | MockMvc happy-path JSON tests |
| 13 | [Hands-On-13-MockMvc-Exception-Test](Hands-On-13-MockMvc-Exception-Test/) | MockMvc 404 / error message tests |
| 14 | [Hands-On-14-Employee-REST-Service](Hands-On-14-Employee-REST-Service/) | XML `employee.xml`, `GET /employees`, DAO/Service/Controller |
| 15 | [Hands-On-15-Department-REST-Service](Hands-On-15-Department-REST-Service/) | `GET /departments`, shared XML catalog |
| 16 | [Hands-On-16-Country-POST](Hands-On-16-Country-POST/) | `POST /countries`, Jackson `@RequestBody` |
| 17 | [Hands-On-17-Country-Validation](Hands-On-17-Country-Validation/) | `@Valid`, Bean Validation, HTTP 400 |
| 18 | [Hands-On-18-Global-Exception-Handler](Hands-On-18-Global-Exception-Handler/) | `@ControllerAdvice`, structured error JSON |
| 19 | [Hands-On-19-Employee-PUT-Service](Hands-On-19-Employee-PUT-Service/) | `PUT /employees`, nested validation, `@JsonFormat` |
| 20 | [Hands-On-20-Employee-DELETE-Service](Hands-On-20-Employee-DELETE-Service/) | `DELETE /employees/{id}`, 404 when missing |
| 21 | [Hands-On-21-Spring-Security-Basic](Hands-On-21-Spring-Security-Basic/) | `SecurityFilterChain`, HTTP Basic, 401 |
| 22 | [Hands-On-22-Users-and-Roles](Hands-On-22-Users-and-Roles/) | BCrypt users, `/countries` → USER role |
| 23 | [Hands-On-23-JWT-Authentication](Hands-On-23-JWT-Authentication/) | `GET /authenticate`, HS256 JWT (jjwt) |
| 24 | [Hands-On-24-JWT-Authorization-Filter](Hands-On-24-JWT-Authorization-Filter/) | `JwtAuthorizationFilter`, Bearer protection |

Package base: `com.cognizant.springlearn`

```bash
cd 03-Products-And-Frameworks/Spring-REST
for d in Hands-On-*; do (cd "$d" && mvn -q clean compile test) || exit 1; done
```
