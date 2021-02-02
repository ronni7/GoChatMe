# Rest Api documentation 

Rest Api documentation available under:
- docs/Rest-api.json

After starting the app:

- https://localhost:8444/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config
  
- https://localhost:8444/v3/api-docs



# Database schema

Script generating database with schema is located under ./scripts directory.


# Project setup

1. Set up MySql server, such as Xampp and generate database schema using create.sql script
2. Adjust db configuration in resources/application.properties, at major point please specify database user and password (currently set to root)
3. Backend application is running under https protocol, configuration and sample keypair is stored under keystore resources/directory so you don't have to generate one(but you can of course!)
4. CORSFiltering and Websocket configuration is stored under ./src/main/java/gochatme/configuration, default frontend application address is set to localhost:4200; the default Angular path, you can change it before running application

