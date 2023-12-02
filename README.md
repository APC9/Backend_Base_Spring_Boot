# Backend Basico Spring Boot

## Aplicacion Guia para crear RestFull API 

- ### Crear la Carpeta modelo 
   Esta clase User representa la entidad de usuario que se almacenará en una base de datos. Está anotada para ser gestionada por JPA, tiene validaciones de campos y utiliza Lombok para reducir el código repetitivo.

  -  Crear la clase modelo
  
- ### Crear la Carpeta response 
   Estas clases están diseñadas para manejar respuestas relacionadas con usuarios (User). UserResponse se centra en la lista de usuarios, mientras que UserResponseRest extiende la clase ResponseRest (que maneja metadatos) y agrega la capacidad de incluir una instancia de UserResponse.

   Utiles en el contexto de una aplicación que maneja respuestas relacionadas con usuarios, proporcionando un lugar centralizado para gestionar información sobre usuarios y metadatos asociados a esas respuestas.

  -  Crear la clase ResponseRest
  -  Crear la clase UserResponse
  -  Crear la clase UserResponseRest
