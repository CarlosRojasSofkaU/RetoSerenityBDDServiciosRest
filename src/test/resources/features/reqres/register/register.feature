# language: es

Característica: Registro en la pagina web
  Como usuario
  Necesito registrarme en la pagina web
  Para tener una cuenta en la plataforma.

  Escenario: Registro exitoso
    Dado que el usuario esta en el recurso web indicando el email "eve.holt@reqres.in" y la contrasena "pistol"
    Cuando el usuario desea registrarse en la plataforma
    Entonces el usuario obtendra un codigo de respuesta exitoso y un token de registro