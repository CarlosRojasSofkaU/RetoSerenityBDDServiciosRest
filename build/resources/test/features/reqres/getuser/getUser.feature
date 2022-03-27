# language: es

Característica: Busqueda de un usuario
  Como usuario
  Necesito consultar por una cuenta de usuario en la plataforma
  Para observar sus datos personales.

  Escenario: Busqueda de un usuario exitoso
    Dado que el usuario esta en el recurso web indicando el id 2
    Cuando el usuario desea buscar una cuenta de usuario en la plataforma
    Entonces visualizara el email "janet.weaver@reqres.in", el primer nombre "Janet", el apellido "Weaver" y el avatar "https://reqres.in/img/faces/2-image.jpg"