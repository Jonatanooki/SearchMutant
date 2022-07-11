# Reclutando mutantes para magneto
Patron de patrones, jefe de jefes, mutante mayor Magneto quiere reclutar la mayor cantidad de mutantes para poder luchar
contra los X-Men.


En donde recibirás como parámetro un array de Strings que representan cada fila de una tabla
de (NxN) con la secuencia del ADN. Las letras de los Strings solo pueden ser: (A,T,C,G), las
cuales representa cada base nitrogenada del ADN.

<p align="center">
  <img alt="Cadena ADN" src="https://github.com/Jonatanooki/SearchMutant/blob/main/pictures/1-planteamiento.png?raw=true">

</p>

Sabrás si un humano es mutante, **si encuentras más de una secuencia de cuatro letras
iguales**, de forma oblicua, horizontal o vertical.

Ejemplo (Caso mutante):
```
String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
```
En este caso el llamado a la función isMutant(dna) devuelve **“true”**.
Desarrolla el algoritmo de la manera más eficiente posible.

## Desafíos:
### Nivel 1:
Programa (en cualquier lenguaje de programación) que cumpla con el método pedido por
Magneto.
### Nivel 2:
Crear una API REST, hostear esa API en un cloud computing libre (Google App Engine,
Amazon AWS, etc), crear el servicio “/mutant/” en donde se pueda detectar si un humano es
mutante enviando la secuencia de ADN mediante un HTTP POST con un Json el cual tenga el
siguiente formato:

POST → /mutant/
```
{
  “dna”:["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
}
```


En caso de verificar un mutante, debería devolver un HTTP 200-OK, en caso contrario un
403-Forbidden

##Nivel 3:
Anexar una base de datos, la cual guarde los ADN’s verificados con la API.
Solo 1 registro por ADN.

Exponer un servicio extra “/stats” que devuelva un Json con las estadísticas de las
verificaciones de ADN: 

GET → /stats
```
{
    “count_mutant_dna”:40,
    “count_human_dna”:100:
    “ratio”:0.4
}
```

Tener en cuenta que la API puede recibir fluctuaciones agresivas de tráfico (Entre 100 y 1
millón de peticiones por segundo).
**Test-Automáticos, Code coverage > 80%.**


## Probando la solución de este repositorio

El proyecto consta de 4 carpetas y 5 archivos los cuales conforman todo el servicio.

Una vez se clona o descarga el proyecto, se deben seguir los pasos a continuación con el IDE **IntelliJ IDEA**.

1. Ir a la parte superior derecha en ***Select run/Debug Configutation*** y realizar clic.

<p align="center">
<img alt="Cadena ADN" src="https://github.com/Jonatanooki/SearchMutant/blob/main/pictures/2-paso1ng.png?raw=true">
</p>

2. Luego en ***Edit Configurations***

<p align="center">
<img alt="Cadena ADN" src="https://github.com/Jonatanooki/SearchMutant/blob/main/pictures/3-paso2ng.png?raw=true">
</p>

3. Realizamos clic en el botón **"+"** y luego en **"Application"**.

<p align="center">
<img alt="Cadena ADN" src="https://github.com/Jonatanooki/SearchMutant/blob/main/pictures/4-paso3ng.png?raw=true">
</p>

4. En la ventana de configuración colocamos el nombre de **Debug** y realizamos clic en el recuadro de **"Main Class"** al lado derecho.

<p align="center">
<img alt="Cadena ADN" src="https://user-images.githubusercontent.com/86886404/178178555-69cd86ba-b489-4c14-b3c2-b7c96e864632.png">
</p>

5. IntelliJ nos ayuda a buscar la clase principal, la seleccionamos y damos clic en **"OK"** y guardamos los cambios, esto para habilitar la depuración.

<p align="center">
<img alt="Cadena ADN" src="https://github.com/Jonatanooki/SearchMutant/blob/main/pictures/6-paso5ng.png?raw=true">
</p>

6. Se debe de instalar [MySql](https://www.mysql.com/downloads/) seguir los pasos de instalación, crear un usuario y contraseña, seguidamente crear una base de datos llamada **"db_mutants"** con el siguiente query:

```
CREATE DATABASE `db_mutants` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
```

7. en la ruta: ~\SearchMutant\src\main\resources\application.properties cambiar los datos de la conexión creada anteriormente de MySQL:

```
management.endpoint.shutdown.enabled=true
management.endpoints.web.exposure.include=health,info,shutdown
spring.datasource.url=jdbc:mysql://localhost:3306/db_mutants?serverTimezone=America/Bogota
spring.datasource.username=root
spring.datasource.password=123456
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5InnoDBDialect
logging.level.org.hibernate=debug

```

***Debido a que usamos "Spring Boot" la tabla donde se guardan las cadenas de ADN se borra (si existe) y crea automaticamente al ejecutar el servicio, como se puede observar en la configuración previa:"spring.jpa.hibernate.ddl-auto=create-drop".***


***Ya con los pasos previos realizados ya podemos ejecutar el codigo.***

8. Cuando la aplicación se encuentra en ejecución en [http://localhost:8080/](http://localhost:8080/) contiene los siguientes ***endpoints***:

- Validadción y guardado de los datos. *Es importante ejecutar este endpoint primero para ir guardando las cadenas de ADN para ejecutar posteriormente las estadisticas.*
  - Método HTTP: ***POST***
  - Url: [http://localhost:8080/api/mutant](http://localhost:8080/api/mutant)
  - Controlador: [MutantController](https://github.com/Jonatanooki/SearchMutant/blob/main/src/main/java/com/mutant/search_mutants/Controller/MutantController.java)
  - Método: *create*
  - Codigos de respuesta: 200(OK), 403(FORBIDDEN)

  [200 OK](https://developer.mozilla.org/es/docs/Web/HTTP/Status/200)

  ```true```            

  [403 FORBIDDEN](https://developer.mozilla.org/es/docs/Web/HTTP/Status/403)

  ```false```            

  [500 Internal Server Error](https://developer.mozilla.org/es/docs/Web/HTTP/Status/500): Ocurrió un error en el cargue o validacion de ADN. Se puede deber a conexión con la base de datos u otros procesos que no fueron controlados.
 
- Consulta de las estadisticas.
        
  - Método HTTP: ***GET***
  - Url: http://localhost:8080/api/stats
  - Controlador: [MutantController](https://github.com/Jonatanooki/SearchMutant/blob/main/src/main/java/com/mutant/search_mutants/Controller/MutantController.java)
  - Método: *data*
  - Codigos de respuesta: 202(ACCEPTED)

  [202 ACCEPTED](https://developer.mozilla.org/es/docs/Web/HTTP/Status/202)

  ```
  {
      “count_mutant_dna”:40,
      “count_human_dna”:100,
      “ratio”:0.4
  }
  ```

## Como se realizó 🚀

### Hablando de código... 💻

- **✍️ Lenguaje/tecnología...** todo esto fue desarrollado en **[JAVA 11](https://www.java.com/es/)** por medio del IDE **IntelliJ IDEA** usando [JAVA Spring Boot](https://spring.io/projects/spring-boot) y conectandonos la la base de datos por medio de [JPARepository](https://docs.spring.io/spring-data/jpa/docs/current/api/org/springframework/data/jpa/repository/JpaRepository.html).


Se analizó el algoritmo del recorrido de las celdas y para las verificaciones horizontales y verticales son recorridos sencillos por lo tanto no hay compledidad en el caso pero para las diagonales y diagonales inversas se realizó el siguiente analisis:

<p align="center">
<img alt="Cadena ADN" src="https://github.com/Jonatanooki/SearchMutant/blob/main/pictures/ECXEL1.png?raw=true">
</p>

Ya que el analisis es muy complejo para solo recorrer las diagonales y las diagonales inversas que tengan una longitud de 4 se decidió recorrer todas las diagonales de la matriz, y para la matriz inversa se decidió darle la vuelta para usar el metodo normal de verificar diagonales.


### En cuanto a la base de datos como se mencionó anteriormente el servicio crea automaticamente la tabla de la siguiente manera:

- Tabla ***tbl_mutants***:

    Los campos son:
    
    - **id_mutant (BIGINT)**: Identificación del mutante guardado.
    - **dna(VARCHAR(8000))**: Corresponde a la cadena de ADN consultada.
    - **is_mutant (BIT(1))**: Casilla donde se indica si la cadena de ADN correspondiente es mutante o muggle.

<p align="center">
    <img alt="Table tbl_mutants" src="https://github.com/Jonatanooki/SearchMutant/blob/main/pictures/DB2.png?raw=true">
</p>

- Los datos almacenados se verían de la siguiente manera: 

<p align="center">
    <img alt="Data" src="https://user-images.githubusercontent.com/86886404/178216946-eaeef916-7cef-4c94-9c05-c572fd53e35e.png">
</p>

**Consideración**: Esta tabla no contiene más información debido al planteamiento del problema.











