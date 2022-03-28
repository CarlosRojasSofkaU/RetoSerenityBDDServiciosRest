# language: es

Característica: Creacion de posts
  Como usuario
  Necesito postear en la plataforma
  Para que los otros usuarios sepan lo que estoy opinando.

  Escenario: Creacion de un post exitosa
    Dado que el usuario esta en el recurso web indicando el titulo "foo", el cuerpo "bar" y el userId 1
    Cuando el usuario desea crear un post
    Entonces el usuario observara los datos de su post como id usuario 1, el titulo "foo", el cuerpo "bar" y el id interno 101