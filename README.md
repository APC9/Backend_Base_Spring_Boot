# Backend Basico Spring Boot

## Aplicacion Guia para crear RestFull API 

- ### 1. Crear la Carpeta modelo 
   Esta clase User representa la entidad de usuario que se almacenará en una base de datos. Está anotada para ser gestionada por JPA, tiene validaciones de campos y utiliza Lombok para reducir el código repetitivo.

  -  Crear la clase modelo
  
- ### 2. Crear la Carpeta response 
   Estas clases están diseñadas para manejar respuestas relacionadas con usuarios (User). UserResponse se centra en la lista de usuarios, mientras que UserResponseRest extiende la clase ResponseRest (que maneja metadatos) y agrega la capacidad de incluir una instancia de UserResponse.

   Utiles en el contexto de una aplicación que maneja respuestas relacionadas con usuarios, proporcionando un lugar centralizado para gestionar información sobre usuarios y metadatos asociados a esas respuestas.

  -  Crear la clase ResponseRest
  -  Crear la clase UserResponse
  -  Crear la clase UserResponseRest

- ### 3. Crear la Carpeta Dao
   Esta interfaz, obtienes automáticamente métodos como save, findById, findAll, delete, entre otros, sin necesidad de implementarlos tú mismo.

  -  Crear la Inteface IUserDao

- ### 4. Crear la Carpeta Services
   <span style="color:green"> __IUserService__ </span> proporciona una interfaz para operaciones comunes, mientras que <span style="color:green"> __UserServiceImpl__ </span>implementa esas operaciones.

  -  Crear la Inteface IUserService
  -  Crear la clase UserServiceImpl
      -  getUsers
      -  getUSerById
      -  getUserByName
      -  createUser
      -  updateUser
      -  deleteUSerById
