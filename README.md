# RentaVehiculos
Descripción del Proyecto
Este proyecto es una aplicación web desarrollada en Java con el framework Spring Boot. La aplicación está diseñada para gestionar un sistema de alquiler de vehículos, permitiendo a los usuarios registrarse, alquilar vehículos, administrar medios de pago y realizar diversas operaciones relacionadas con el alquiler de vehículos.

Características Principales
Registro de Usuarios: Los usuarios pueden registrarse en la plataforma proporcionando información básica y creando una cuenta segura.

Autenticación y Autorización: La aplicación utiliza Spring Security para la autenticación y autorización de usuarios, garantizando la seguridad de las cuentas y datos personales.

Gestión de Renta de Vehículos: Los usuarios pueden alquilar vehículos disponibles, establecer el estado de las rentas y finalizar los procesos de alquiler.

Administración de Medios de Pago: Los usuarios pueden agregar y gestionar múltiples medios de pago asociados a sus cuentas para realizar transacciones seguras.

Seguridad de Contraseñas: Se utiliza el algoritmo bcrypt para encriptar las contraseñas de los usuarios, proporcionando un almacenamiento seguro y confiable.

Tecnologías Utilizadas
Java: Lenguaje de programación principal del proyecto.

Spring Boot: Framework de desarrollo de aplicaciones Java que simplifica la creación de aplicaciones robustas y seguras.

Spring Security: Para utilizar el algoritmo de encriptación.

Hibernate: Framework de mapeo objeto-relacional (ORM) para gestionar la interacción con la base de datos.

Base de Datos: Se utiliza una base de datos relacional para almacenar información de usuarios, rentas y medios de pago.

Requisitos del Sistema
Java 11: Es necesario tener instalado Java 11 o una versión superior para ejecutar la aplicación.

Gestor de Base de Datos: Se requiere un gestor de base de datos relacional compatible con Hibernate, como MySQL o PostgreSQL.

Configuración y Ejecución
Clona este repositorio en tu máquina local.
Configura la base de datos y las credenciales de conexión en el archivo application.properties.
Ejecuta la aplicación utilizando tu IDE preferido o mediante la línea de comandos con el comando ./mvnw spring-boot:run.
Accede a la aplicación a través del navegador web en http:
¡Disfruta explorando nuestra aplicación de alquiler de vehículos!

