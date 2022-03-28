# language: es

Característica: Actualizacion de usuarios
  Como usuario
  Necesito actualizar los datos de un usuario
  Para que la plataforma cuente con informacion verdadera.

  Escenario: Actualizacion de un usuario exitosa
    Dado que el usuario esta en el recurso web de actualizacion de datos indicando el nombre "morpheus" y el trabajo "zion resident"
    Cuando el usuario desea actualizar la informacion de una cuenta
    Entonces el usuario observara la fecha en la que la cuenta fue actualizada