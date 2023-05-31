# Challenge ONE | Back End | Foro Alura

<p align="center" >
     <img width="200" heigth="200" src="https://user-images.githubusercontent.com/91544872/209678377-70b50b21-33de-424c-bed8-6a71ef3406ff.png">
</p>

El Proyecto consiste en una APIRest para un Foro, en el cual un usuario creara un topico referente a un curso en el cual presenta alguna duda, y otro usuario podra responder al topico intentando darle solucion. 

## FUNCIONAMIENTO:

- ### Debera hacer uso de herramientas que nos permitan realizar pruebas en la API como por ejemplo:

  - [Postman](https://www.postman.com/)
  - [Insomnia](https://insomnia.rest/)

Para los ejemplos se utilizo <em>Insomnia</em> pero cualquiera de estas herramientas funcionan de manera parecida y simulan un cliente HTTP que nos da la posibilidad de testear ‘HTTP requests’ a través de una interfaz gráfica de usuario, por medio de la cual obtendremos diferentes tipos de respuesta dependiendo del Metodo Http del cual hagamos uso.

Esta Api se desarrollo con autenticacion JWT por lo que primero debera crear un usuario y hacer un login para obtener el token y tener la autorizacion que le permita acceder a los distintos EndPoints, solo esta permitido ingresar sin autorizacion a las siguientes rutas:

## Registro de nuevo Usuario y Login:

- Registrar un un nuevo usuario: POST - "/usuarios/registro" - Datos de Registro ("nombre","email","contrasena")
<p align="center" >
     <img width="600" heigth="250" src="https://github.com/ymos1223/alura_challenge_API_Rest/assets/100883836/bd625bef-65f7-43eb-8afd-1e86a79fa384.png">
</p></br>

- Hacer un login de usuario: POST - "/login" - Datos del Login ("email","contrasena")
- El Token expira una vez transcurridas 2 horas desde el mometo de ser generado</br>
<p align="center" >
     <img width="600" heigth="250" src="https://github.com/ymos1223/alura_challenge_API_Rest/assets/100883836/4f27cd9c-c869-4c9a-834d-36fc159af929.png">
</p>

## Enviar Token como header:
En la pestaña Auth, seleccionar la opcion <em>Bearer Token</em>

<p align="center" >
     <img width="600" heigth="400" src="https://github.com/ymos1223/alura_challenge_API_Rest/assets/100883836/46ce97c7-5284-4428-84a2-e5df71a8922a.png">
</p></br>

Luego debemos copiar en <em>Token</em> el valor del token obtenido sin incluir las comillas, una vez hecho esto la API valida que el Token sea valido y obtenemos la authorizacion para la solicitud.

<p align="center" >
     <img width="600" heigth="400" src="https://github.com/ymos1223/alura_challenge_API_Rest/assets/100883836/ee2dddc9-4e78-4e30-b592-6ab061165448.png">
</p>
---

## Una vez obtenga el token podra obtener autorizacion para las siguientes rutas o endpoints:

## USUARIOS:
- Listar Usuarios: GET - "/usuarios"
- Eliminar Usuario: DELETE - "/usuarios/{idUsuario}"
- Actualizar un usuario: PUT - "/usuarios/{idUsuario}" - Datos que se pueden actualizar("nombre","email") 
- Buscar usuario por Id: GET - "/usuarios/{idUsuario}"</br>

<p align="center" >
     <img width="600" heigth="250" src="https://github.com/ymos1223/alura_challenge_API_Rest/assets/100883836/273da212-7fe1-47d1-896c-e6cb3f3e4d1c.png">
</p>
---

## TOPICOS:
- Registrar un Topico: POST - "/topicos" - Datos de Registro de topico ("id_autor","id_curso","titulo","mensaje")
- Listar Topicos: GET - "/topicos"
- Eliminar Topico: DELETE - "/topicos/{idTopico}"
- Actualizar Topico: PUT - "/topicos/{idTopico}" - Datos para actualizar un topico ("titulo","mensaje","status","id_curso")
- Buscar Topico por Id: GET - "/usuarios/{idUsuario}"</br>

<p align="center" >
     <img width="600" heigth="400" src="https://github.com/ymos1223/alura_challenge_API_Rest/assets/100883836/8fc33360-fec3-4bbd-b88c-9d7591327fde.png">
</p>

---

## CURSOS:
- Registrar un Curso: POST - "/cursos" - Datos de Registro de curso ("nombre","categoria")
- Listar Cursos: GET - "/cursos"
- Eliminar Curso: DELETE - "/cursos/{idCurso}"
- Actualizar Curso: PUT - "/cursos/{idCurso}" - Datos para actualizar un Curso ("nombre","categoria")
- Buscar Curso por Id: GET - "/Cursos/{idCurso}"</br>

<p align="center" >
     <img width="600" heigth="400" src="https://github.com/ymos1223/alura_challenge_API_Rest/assets/100883836/b1467afc-53ca-46c3-a24c-72f1c49a15b0.png">
</p>

---

## RESPUESTAS:
- Registrar una respuesta: POST - "/respuestas/{idTopico}" - Datos de Registro de respuesta ("mensaje","id_autor")
- Listar Topico con una respuesta: GET - "/respuestas/{idTopico}"
- Eliminar Respuesta: DELETE - "/respuestas/{idTopico}/{idRespuesta}"
- Actualizar una respuesta: PUT - "/respuestas/{idtopico}/{idRespuesta}" - Datos para actualizar una respuesta ("mensaje","solucion")
- Listar topico con respuesta especifica: GET - "/respuestas/{idTopico}/{idRespuesta}"</br>

<p align="center" >
     <img width="600" heigth="400" src="https://github.com/ymos1223/alura_challenge_API_Rest/assets/100883836/a8f1db29-6272-4449-b299-c5649c68a329.png">
</p>

---
     

- ### Tecnologías utilizadas:

  - [IntelliJ Idea](https://www.jetbrains.com/idea/)
  - [MySql](https://www.mysql.com/)
  - [Java](https://www.java.com/en/)

  - [Spring Security](https://start.spring.io/)
  - [Token JWT](https://jwt.io/)

  ## ⬇️ Download

  ### ¿Cómo descargar?

  #### 🔹 Fork

  1. Haga el **Fork** del proyecto. En la parte superior derecha, al hacer clic en el icono, creará un repositorio del proyecto en su cuenta personal de GitHub.

<p align="center" >
     <img width="600" heigth="600" src="https://user-images.githubusercontent.com/101413385/169404781-7df6355b-3a15-472a-8d8e-fdb84d91a7bd.png">
</p>

  2. Después de tener el repositorio "forkado" para su cuenta, verifica si la url de la página es la del repositorio de su cuenta.

 <p align="center" >
     <img width="600" heigth="600" src="https://user-images.githubusercontent.com/78982435/209683304-04e0d114-8834-4449-b82b-29a38f057f2d.png">
     
</p>

  3. Haga clic en la opción **Code**. Presenta tres formas para instalar el repositorio en su máquina, y destacamos dos:

     <p align="center" >
     <img width="600" heigth="600" src="https://user-images.githubusercontent.com/78982435/209683480-72fab313-ecbc-4de7-8f75-2d6b5013ea49.png">
     </p></br>

#### 🔹 Clonar o descargar el ZIP

1 - Para clonar, simplemente copia el <em>url</em> resaltado en la imagen y ubicado justo debajo del HTTPS, crea una carpeta en tu computadora, abre el <em>cmd</em> o el <em>git bash</em> dentro de esa carpeta y luego ingresa el comando <strong>git clone</strong> y con el botón derecho del mouse dentro del terminal haz click en la opcion <strong>Paste</strong> para pegar el <em>url</em> y presiona <em>Enter</em>. 

<p align="center" >
     <img width="600" heigth="600" src="https://user-images.githubusercontent.com/78982435/209683774-85c78b5e-605f-4643-818f-0bb2eddca175.png">
</p>

2 - La segunda opción es descargar el código en un paquete <strong>"zipado"</strong> y extraer la carpeta a tu computadora.
</br></br>

## 📝 IntelliJ Idea

### ¿Cómo importar mi proyecto a IntelliJ Idea?

1 - Una vez dentro del IDE en el lado superior izquierdo, haz clic en <em>File</em>, elige la opción <em>Open</em>.

<p align="center" >
     <img width="400" heigth="400" src="https://github.com/ymos1223/alura_challenge_API_Rest/assets/100883836/f34c3f77-fcb5-4f48-9280-00920dfdaedc.png">
</p>


Luego ubica el directorio del proyecto "clonado" o "extraído" en tu computadora. Haz click en <em>Ok</em> para completar la importación.

### Configurar MySql en el proyecto

Debera tener instalado MySQL y configurar el archivo Application.Properties que esta en la carpeta resources del proyecto y agregar sus propios datos de conexion de usuario y contraseña. Deberas crear una base de datos con el nombre que desees por ejemplo "foro_alura" y debe ser el mismo que colocas en el spring.datasource.url. 

spring.datasource.url=jdbc:mysql://localhost:/{El nombre de base de datos que creaste}?serverTimezone=America/Bogota&useSSL=false&allowPublicKeyRetrieval=true
spring.datasource.username= {Tu Usuario}
spring.datasource.password= {Tu contraseña}

<p align="center" >
     <img width="600" heigth="600" src="https://github.com/ymos1223/alura_challenge_API_Rest/assets/100883836/88f0cbb8-d314-41a9-8eb9-14244dba85ee.png">
</p>

💙 Alura Latam

[![img](https://camo.githubusercontent.com/c00f87aeebbec37f3ee0857cc4c20b21fefde8a96caf4744383ebfe44a47fe3f/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f2d4c696e6b6564496e2d2532333030373742353f7374796c653d666f722d7468652d6261646765266c6f676f3d6c696e6b6564696e266c6f676f436f6c6f723d7768697465)](https://www.linkedin.com/company/alura-latam/mycompany/)

🧡 Oracle

[![img](https://camo.githubusercontent.com/c00f87aeebbec37f3ee0857cc4c20b21fefde8a96caf4744383ebfe44a47fe3f/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f2d4c696e6b6564496e2d2532333030373742353f7374796c653d666f722d7468652d6261646765266c6f676f3d6c696e6b6564696e266c6f676f436f6c6f723d7768697465)](https://www.linkedin.com/company/oracle/)


