# PruebaTecnicaJavaDevsu
Prueba Técnica para optar al puesto de Programador Senior Java en la empresa Devsu
## Arquitectura Aplicada : Microservicios
  Descripción : se han desarrolllado 2 microservicios. El primero para la gestión de Personas- Clientes y el segundo para la gestión de Cuentas-Movimientos
  #### Microservicio de Persona-Cliente devsu-clientes 
  #### Microservicio Cuentas-Movimientos devsu-movimientos
## Preparación del Ambiente
  - Compilar ambos proyectos para la generación de jars antes de ejecutarlos teniendo el cuidado de dejar libres los puertos 8081 y 8082.
  - mvn clean compile package : Ejecutar dentro de las carpetas de cada proyecto.
  - Los proyectos ya se encuentran configurados para que el jar sea generado en las carpetas de configuración de Docker.

  - la orquestación de los contenedores se realiza utilizando Docker compose, el docker-compose.yml considera el uso de las siguientes herramientas creando un contenedor para cada una de ellas:

-- rabbitmq
-- MySQL 
-- Php MyAdmin
-- devsu-clientes ejecutándose en el puerto 8081
-- devsu-movimientos ejecutándose en el puerto 8082

para levantar la aplicación ejecutar el comando : 
Docker compose up -d
## Buenas Practicas de Programación
  -Utilización de clase abstracta para la implementación y reutilización de código.
  -Uso de patron de diseño Builder para construcción de objetos paso a paso.  
## Manejo de Excepciones
  Se realiza en la capa de servicios a traves de la implementación de la clase Exceptions y RestExceptionHandler

## Pruebas.
  - Pruebas Unitarias: Se han desarrollado pruebas unitarias para la gestión del cliente con el framework Mockito.
  - Pruebas de Integración : desarrolladas con Karate se realizo el uso de un wiremock para facilitar las pruebas 
## Consideraciones a tomar en cuenta
El reporte considera periodos de fecha para dias anteriores a la fecha actual
