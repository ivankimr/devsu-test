********************************************************************************
Microservicios Devsu – Customers & Accounts
********************************************************************************

REQUISITOS:
    • Java 21
    • Maven 3.9+
    • Docker y Docker Compose
    • Puertos disponibles: 
        - RabbitMQ: 5672 (AMQP) / 15672 (Management UI)
        - PostgreSQL: 5432
        - accounts-service: 5001
        - customers-service: 5002

Repositorio GitHub (Descargar como Zip):
	https://github.com/ivankimr/devsu-test

Comando para levantar los contenedores:
    En devsu-test ejecutar: docker-compose up --build

Comando para ejecutar pruebas automáticas:
    En devsu-test\customers-service ejecutar: mvn test

********************************************************************************
VISIÓN GENERAL DE LA SOLUCIÓN
********************************************************************************

Proyecto de microservicios Customer Service y Accounts Service, implementados con
Spring Boot 3.5.4 y Java 21, conectados a PostgreSQL y comunicándose mediante
RabbitMQ. La arquitectura es hexagonal / en capas, separando dominio, aplicación y
adaptadores/infraestructura. Cada servicio se ejecuta en contenedores Docker y se
puede levantar usando Docker Compose.

TECNOLOGÍAS PRINCIPALES:
    • Backend: Spring Boot 3.5.4, Java 21
    • Persistencia: PostgreSQL 17, JPA/Hibernate
    • Mensajería: RabbitMQ 3.13 (AMQP + Management UI)
    • Documentación / API: springdoc-openapi (Swagger UI)
    • Pruebas: Mockito + JUnit 5 (unitarias), Karate (integración / contract tests)
    • Contenerización: Docker, Docker Compose 3.9

ARQUITECTURA:
    • Capa API: REST controllers con endpoints CRUD, mapeo DTO ↔ dominio
    • Capa Aplicación: casos de uso (UseCase) que orquestan lógica, validaciones y transacciones
    • Capa Dominio: modelos (Customer, Person, Account, AccountTransaction) y repositorios de puerto
    • Infraestructura / Adaptadores: repositorios Spring Data JPA, entidades JPA y adaptadores de persistencia
    • Mensajería: CustomerEventsPublisher propaga eventos de creación, actualización y eliminación de clientes vía RabbitMQ

********************************************************************************
DOCKER COMPOSE
********************************************************************************

Servicios levantados:
    • rabbit: RabbitMQ (5672 AMQP / 15672 Management UI)
    • db: PostgreSQL, con scripts de inicialización en ./db-init
    • customers-service: expone puerto 5002, conecta a db y rabbit
    • accounts-service: expone puerto 5001, conecta a db y rabbit

********************************************************************************
ENDPOINTS
********************************************************************************

Customer Service (/clientes):
    • GET /clientes → lista paginada de clientes
    • POST /clientes → crea cliente (CustomerRequest)
    • GET /clientes/{id} → obtiene cliente por ID
    • PUT /clientes/{id} → actualiza cliente
    • DELETE /clientes/{id} → elimina cliente

Accounts Service:

Cuentas (/cuentas):
    • GET /cuentas → lista de cuentas paginada
    • POST /cuentas → crea cuenta (AccountRequest)
    • GET /cuentas/{id} → obtiene cuenta por ID
    • PUT /cuentas/{id} → actualiza cuenta

Movimientos (/movimientos):
    • GET /movimientos/{id} → obtiene transacción por ID
    • GET /movimientos/cuenta/{accountId} → lista transacciones de una cuenta
    • POST /movimientos → crea transacción (TransactionCreateRequest)
    • PUT /movimientos/{id} → actualiza transacción

Reportes (/reportes):
    • GET /reportes?customerId=&from=&to= → lista movimientos de un cliente por rango de fechas

********************************************************************************
FLUJO DE COMUNICACIÓN
********************************************************************************

    1. Clientes y cuentas se crean mediante REST.
    2. Eventos de clientes (CustomerInsUpdEvent / CustomerDeletedEvent) se envían a RabbitMQ.
    3. Microservicios pueden consumir los eventos para actualizar datos o generar notificaciones.
    4. Pruebas unitarias se realizan con Mockito/JUnit, y pruebas de integración/contract con Karate.

********************************************************************************
