# Spring Boot 2, Oauth2, JWT, MongoDB starter pack - Authorization Server, Resource Server, Client authentication

## Includes

- **Eureka Server** - used for service discovery
- **Authorization Server** - used for authentication and token generation with JWT
- **Resource Server** - minimal APIs to demonstrate resource access by roles
- **Client Server** - showing how to request a token and use it 

## Helpful information

### Generate your own jwt key store and RSA public key - to be able to use OAUTH with JWT

 #### Generate JWT key store
 - Run the command
 
       keytool -genkeypair -alias jwt -keyalg RSA -dname "CN=jwt, L=Berlin, S=Berlin, C=DE" -keypass secret -keystore jwt.jks -storepass secret
       
 - Add the result jwt.jks file to your AUthorization Server resource package
 
 #### Generate public key
 - Run the command
 
       keytool -list -rfc --keystore jwt.jks | openssl x509 -inform pem -pubkey
       
 - Copy the result into a file called public.cert and add it to any of your Resource Servers into the resource package
 
 