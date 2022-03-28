# language: es

Característica: Eliminacion de albums
  Como usuario
  Necesito eliminar un album de la plataforma
  Para borrar informacion que no es deseada en la plataforma.

  Escenario: Eliminacion de un album exitosa
    Dado que el usuario esta en el recurso web
    Cuando el usuario desea eliminar un album con el id interno 1
    Entonces el usuario obtendra un codigo de estado exitoso