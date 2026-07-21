# Spring REST & Spring Core (XML)

Hands-on exercises **1–6** for Spring Boot **3.3.x**, Spring Web, Spring Core XML, and SLF4J (Java **21**).

| Hands-On | Folder | Focus |
|----------|--------|--------|
| 1 | [Hands-On-01-Spring-Web-Project](Hands-On-01-Spring-Web-Project/) | Boot web, embedded Tomcat, `/api/health` |
| 2 | [Hands-On-02-DateFormat-Bean](Hands-On-02-DateFormat-Bean/) | `SimpleDateFormat` XML bean |
| 3 | [Hands-On-03-Logging](Hands-On-03-Logging/) | SLF4J levels & patterns |
| 4 | [Hands-On-04-Country-Bean](Hands-On-04-Country-Bean/) | XML bean, setter injection |
| 5 | [Hands-On-05-Bean-Scopes](Hands-On-05-Bean-Scopes/) | Singleton vs prototype |
| 6 | [Hands-On-06-Country-List](Hands-On-06-Country-List/) | `<list>` / `<ref>` wiring |

Package base: `com.cognizant.springlearn`

```bash
cd 03-Products-And-Frameworks/Spring-REST
for d in Hands-On-*; do (cd "$d" && mvn -q clean compile test) || exit 1; done
```
