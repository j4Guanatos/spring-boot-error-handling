# no-explicit-error-handling

Esta es una aplicación sencilla en *Spring Boot* que se encargará de manejar alumnos. 
Es un REST service que hará operaciones CRUD (Create, Retrieve, Update, Delete).
Usa una base de datos _in memory_ en MongoDB llamada **student**. 
Se usaron los siguientes módulos de Spring para crear la aplicación: Spring Boot, Spring MVC y Spring Data.
Se requiere *Maven* para generar el JAR usando el siguiente comando en la línea de comandos:
```
mvn clean package
```
y se incia usando el siguiente comando:
```
java -jar target/no-explicit-error-handling-0.0.1-SNAPSHOT.jar
```
Automáticamente iniciará un tomcat listo para recibir requests en la dirección http://localhost:8080

## Ejemplos
El formato de entrada y salida es en JSON.
Para todos los requests es requerido agregar el siguiente header:
- Accept: application/json
Y para POST y PUT es requerido además agregar el siguiente header:
- Content-Type: application/json

Aquí hay un ejemplo del JSON de entrada para la operación de POST (Create), El JSON se pone en el **Content to send/Body** del request. Les recomiendo usar un plugin/complemento para enviar el request desde su navegador favorito. En Firefox uso **HttpRequester** y en Chrome uso **Postman**.


[http://localhost:8080/student](http://localhost:8080/student)
```
{
	"id":"123",
	"firstName":"Jonathan",
	"lastName":"De la Torre",
	"birthdate":"1985-09-25T00:00:00-07:00",
	"phoneNumber":"+5213345789562",
	"email":"abc@mail.com",
	"address":{
		"street":"Diamante",
		"number":"533-A",
		"cp":"45856",
		"city":"Guadalajara",
		"state":"Jalisco",
		"country":"México"
		},
	"subjects":[1,2],
  "grade":90
}
```
 
 Ejemplo de una operación GET:
 [http://localhost:8080/student/123](http://localhost:8080/student)
 
 
 
