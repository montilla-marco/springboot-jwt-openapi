# springboot-jwt-openapi
Muestra de un simple picoservicio para la API REST de creacion de usuarios.

## Creacion del contrato del API
Antes de empezar a codear lo recomendable es, reunir las personas involucradas en el desarrollo, tanto del frontend
como backend y en tarea conjunta elaborar el contrato de comportamiento del API, luego cada parte puede empezar a
trabajar por su lado, confiando en el mismo, culaquier modificacion debe ser comunicada, asi se logra una separación 
de intereses lo que permite trabajar de forma paralela y dividida.

``` yaml
openapi: 3.0.0
info:
  title: OpenAPI document for Simple User Registry Microservice.
  description: REST API for managing web users, authorization and authentication using JWT.
  contact:
    name: API Support
    url: https://marcomarco.blog
    email: marcomarco@marcomarco.blog
  version: 0.0.1
servers:
  - url: http://127.0.0.1
    description: The development API server
    variables:
      port:
        enum:
          - '8092'
        default: '8092'
      basePath:
        default: /api/v1
paths:
  /api/v1/users:
    post:
      operationId: createUser
      summary: Create a User
      security:
        - BearerAuth: [write]
      description: Lets create an user post a entry in users registry
      requestBody:
        required: true
        content:
          application/json:    
              schema:
                $ref: "#/components/schemas/User"

      responses:
        '201':
          description: OK user created
          content:
            application/json:    
              schema:
                $ref: "#/components/schemas/UserOut"

        default:
          description: Unexpected Error
          content:
            application/json:
              schema:
                $ref: "#/components/schemas/Error"
          
components:
  securitySchemes:
    BearerAuth:
      type: http
      scheme: bearer
  schemas:
    User:
      type: object
      required:
        - email
        - password
        - phones
      properties:
        name:
          type: string
          minLength: 36
          maxLength: 200
        email:
          type: string
          format:
            email
          pattern: '^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$'
        password:
          type: string
          format:
            password
        
        phones:
          type: array
          items:
            type: object
            properties:
              number:
                type: integer
                minimum: 6
                maximum: 9
              citycode:
                type: integer
                minimum: 1
                maximum: 3
              contrycode:
                type: integer
                minimum: 1
                maximum: 3
    UserOut:
      type: object
      properties:
        id:
          type: string
          minLength: 36
          maxLength: 36
          format:
            uuid
        created:
          type: string
          format:
            date-time
        modified:
          type: string
          format:
            date-time
        last_login:
          type: string
          format:
            date-time
        token:
          type: string
        isactive:
          type: boolean

      allOf:     # Combines the User and the output model
        - $ref: '#/components/schemas/User'

    Error:
      type: object
      required:
        - code
        - message
      properties:
        http-code:
          type: integer
          minimum: 3
          maximum: 3
        message:
          type: string
        stack-trace:
          type: string
          

```
Haga el contrato en la siguiente direccion: https://editor-next.swagger.io/

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.2.0/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.2.0/gradle-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.2.0/reference/htmlsingle/index.html#web)
* [Spring Security](https://docs.spring.io/spring-boot/docs/3.2.0/reference/htmlsingle/index.html#web.security)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.2.0/reference/htmlsingle/index.html#data.sql.jpa-and-spring-data)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/3.2.0/reference/htmlsingle/index.html#using.devtools)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)