# LinksCutter 
> _Web application created to cut your long links_




## Following technologies being used in project:
#### Server side:
* JDK 1.8
* Eclipse Mars.2 Release(4.5.2)
* PostreSQL 9.4
* Tomcat v8.0
* Apache Maven 3.2.5
* Spring 4.2.5.RELEASE
* Spring Security 4.0.4.RELEASE
* JPA 2.1
* Hibernate 4.3.8.Final
* Jackson 2.5.3
* JUnit 4.12
* Spring Test 4.2.5.RELEASE
* Mockito 1.10.19
* Log4j 2.1

#### Client side:
* HTML5
* CSS
* Bootstrap 3.3.4
* AngularJs 1.4.4


## Project Structure
####Project consists of 4 modules:
* Module - datamodel

![datamodel](http://s019.radikal.ru/i609/1604/a2/691dd5cfc124.png)

* Module - dataacces

![dataacces](http://s014.radikal.ru/i328/1604/d0/267dc55dd5e1.png)

* Module - service

![service](http://s014.radikal.ru/i327/1604/de/d98c66a6e7b0.png)

* Module - webapp

![webapp1](http://s019.radikal.ru/i601/1604/85/3d1faa87369a.png)
![webapp2](http://s019.radikal.ru/i629/1604/24/74e667329d2e.png)

## Database

* Simple Database shema

![DatabaseShema](http://s018.radikal.ru/i512/1604/e8/5c2c0fbc86f3.png)

#### Database relationship:
* _OneToMany_. **user_account** to **link**
* _OneToOne_. **link** to **link_details**
* _ManyToMany_. **link_details** to **tag**


## REST Api

* All requests must begin with the prefix "http: // localhost: 8080 / webapp"
* All requests and responses contain data in JSON format

|URL|Method|Description|
|---|-----------|------|
|/login|POST|User authorization|
|/logout|GET|User logout|
|/registration|POST|User registration|
|/links|POST|Create new link|
|/links/{id}|DELETE|Delete link|
|/links|GET|Get All user links|
|/links|PUT|Update link|
|/link/{id}|GET|Get link by Id|
|/tag/{tagId}|GET|Get links by tag|
|/search|POST|Search link|
