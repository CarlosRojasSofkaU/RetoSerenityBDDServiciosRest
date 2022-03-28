# Reto de automatización Serenity BDD con REST Assured
# Realizado por Carlos Fernando Rojas Cortés

En este proyecto se pretende realizar unas caracteristicas para un servicio de pruebas, para practicar conocimientos como:

* Uso de metodología BDD
* Uso de lenguaje DSL y herramientas como Gherkin para su manejo
* Uso de cucumber y serenity BDD para pruebas automatizadas
* Uso de gestores de log con log4j
* Uso de Screenplay como patrón de automatizaición
* Buenas prácticas de programación en la construcción del caso de negocio

Para esto se definen las siguientes secciones:
  1. [Configuración inicial](#configuración-inicial)
  2. [Requerimientos](#requerimientos)
  3. [Plan de pruebas](#plan-de-pruebas)


## Configuración inicial

Para poder correr las pruebas automatizadas se debe ubicar en la ruta src/test/java/co/com/sofka/runner y posteriormente

en cualquiera de los paquetes que se encuentran allí (reqres y jsonplaceholder), en ellos encontrará paquetes con los nombres de los ejecutables que contienen (deletealbum, createpost, updateuser, getuser) java

que serán los que correran las pruebas automatizadas.


## Requerimientos

Para usar este proyecto es necesario tener los siguientes plugins en un IDE que maneje Java como lo puede ser IntelliJ IDEA además de contar con JDK11:

■CucumberforJava
■Gherkin
■SubstepsIntellijPlugin
■Gradle
■Maven

y dependencias como:

    implementation group: 'net.serenity-bdd',                 name: 'serenity-core',                 version: serenityVersion
    implementation group: 'net.serenity-bdd',                 name: 'serenity-junit',                version: serenityVersion
    implementation group: 'net.serenity-bdd',                 name: 'serenity-screenplay',           version: serenityVersion
    implementation group: 'net.serenity-bdd',                 name: 'serenity-screenplay-webdriver', version: serenityVersion
    implementation group: 'net.serenity-bdd', name: 'serenity-assertions',           version: serenityVersion
    implementation group: 'net.serenity-bdd', name: 'serenity-ensure',               version: serenityVersion
    implementation group: 'net.serenity-bdd', name: 'serenity-screenplay-rest', version: serenityVersion

    implementation group: 'net.serenity-bdd', name: 'serenity-cucumber5',            version: serenityCucumberVersion

    implementation group: 'log4j',                            name: 'log4j',                         version: '1.2.17'

    implementation group: 'com.fasterxml.jackson.core', name: 'jackson-databind', version: '2.11.3'
    implementation group: 'com.fasterxml.jackson.datatype', name: 'jackson-datatype-jsr310', version: '2.11.3'
    implementation 'com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.13.0'


## Plan de pruebas

### 1. **Descripción del plan de pruebas**

El proyecto de las empresas reqres y jsonplaceholder tiene como objetivo principal implementar un canal virtual (sitio web) que le permita a sus usuarios actuales o potenciales practicar el consumo de servicios y realizar peticiones HTTP como POST, CREATE, GET, DELETE, PATCH, entre otras,

Este plan de pruebas está enfocado a que el usuario pueda navegar por el sitio web y pueda simular el consumo de servicios para características como consultar por un usuario, actualizar un usuario, crear un post o borrar un album.

### 2. **Alcance de las pruebas**

El alcance de las pruebas para el primer sprint incluye pruebas para los servicios de consultar por un usuario (reqres), actualizar un usuario (reqres), crear un post (jsonplaceholder) y borrar un album (jsonplaceholder). Estos se basaron en los siguientes criterios de aceptación.

1. Servicio "GetUser (GET)"

*Verificar que cuando se consulta por un usuario con id 2 el servicio me retorne el email janet.weaver@reqres.in, el nombre Janet, el apellido Weaver y la url del avatar https://reqres.in/img/faces/2-image.jpg

2. Servicio "UpdateUser"

*Verificar que cuando quiero actualizar el usuario indicando el nombre morpheus y el trabajo zion resident, el servicio me retornará un campo update at con la fecha de actualización


3. Servicio "CreatePost"

*Verificar que cuando el usuario quiere crear un post con el titulo "foo", el cuerpo "bar" y el id de usuario 1, el servicio me retornará un objeto con los campos ingresados y un id interno equivalente a 101

4. Servicio "DeleteAlbum"

*Verificar que cuando el usuario quiere eliminar el album con el id interno 1, el servicio retornará un código de estado exitoso.

### **Fuera de alcance**

*No se verificará funcionalidades extra para el CRUD de usuarios en req res, como eliminar o crear usuarios
*No se verificarán otros módulos de la página web req res.
*No se verificarán funcionalidades extra para el CRUD de colecciones de json placeholder.

### 3. **Estrategia de las pruebas**

Las pruebas del primer sprint se realizarán de manera **automática**, estas se realizarán con ayuda de REST Assured, Serenity BDD y Cucumber. Se realizarán funcionalidades preestablecidas para los servicios de actualización y busqueda de usuarios en reqres, y la eliminación de albums y creacion de posts en json placeholder.


### Riesgos

A continuación se presenta una tabla con el análisis de riesgos para cada criterio de aceptación creados para los módulos en cuestión.

El análisis se realizó con una matriz de riesgos de 3x3, por lo tanto el menor nivel de riesgo será 1 y el mayor será 9.

| Criterio aceptación | Probabilidad | Impacto | Nivel de riesgo |
| --- | --- | --- | --- |
| CA_001 Verificar datos de la consulta de un usuario en req res | 2 | 2 | 4 |
| CA_002 Verificar campo update at para la actualización de un usuario | 3 | 2 | 6 |
| CA_003 Verificar los datos de la creación exitosa de un post | 1 | 2 | 2 |
| CA_004 Verificar el código de estado de la eliminación de un album | 1 | 3 | 3 |

### 4. Requerimientos

- Requerimos acceso al servicio de la plataforma web de la empresa.
- Documentación actualizada del proyecto.
- Comunicación constante con el equipo de trabajo.

### 5. Otros tipos de pruebas

Se recomienda al equipo realizar las siguientes pruebas:

- Pruebas de Rendimiento
- Pruebas de Seguridad

### 6. Equipo de trabajo

**Carlos Fernando Rojas Cortés** – Creador del plan de pruebas y automatizador