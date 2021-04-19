# Test task

Service enables manage customers and them products. 

## Requirements
* Java 11
* Gradle
* Keycloak
* Postgres
* Docker (app uses testcontainers)
* docker-compose for fast local run

## Api 

It has following rest api:
```
[GET/POST]             /api/v1/customers
[GET/PUT/DELETE]       /api/v1/customers/{customerId}
[GET/POST]             /api/v1/customers/{customerId}/products
[GET/POST/PUT/DELETE]  /api/v1/products/{productId}
```
Example of usage api you can find in `api-example.http`

## Auth
All methods require authentication via JWT. Application uses keycloak as SSO.

Application has three roles: 
* ADMIN - can get, list, create, update and delete any resources
* EDITOR - can get, list, create, update and delete any resources
* VIEWER - can get and list any resources

### Generating access token with keycloak
To get jwt token use command like this
```
curl http://<KEYCLOAK_URL>/auth/realms/<REALM>/protocol/openid-connect/token -d 'client_id=<APP>&username=<USER>&password=<PASSWORD>&grant_type=password' --silent | jq '.access_token'
```

## Build
```
./gradlew clean build
```
## Local run
```
docker-compose up -d
```
Application will listen 8080 and 18080 ports. 

Go to keycloak admin (http://localhost:8081 admin/password) and add users with roles EDITOR or VIEWER.

Then issue access token via keycloak:
```
curl http://localhost:8081/auth/realms/app/protocol/openid-connect/token -d 'client_id=app&username=<USER>&password=<PASSWORD>&grant_type=password' --silent | jq '.access_token'
```

Check app api:
```
curl http://localhost:8080/api/v1/customers -H 'Authorization: Bearer <JWT_TOKEN>'
```

