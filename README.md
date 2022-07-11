# Reclutando mutantes para magneto
Patron de patrones, jefe de jefes, mutante mayor Magneto quiere reclutar la mayor cantidad de mutantes para poder luchar
contra los X-Men.


En donde recibirás como parámetro un array de Strings que representan cada fila de una tabla
de (NxN) con la secuencia del ADN. Las letras de los Strings solo pueden ser: (A,T,C,G), las
cuales representa cada base nitrogenada del ADN.

![enter image description here](https://github.com/Jonatanooki/SearchMutant/blob/main/pictures/1-planteamiento.png?raw=true)


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

![IMAGEN paso1](https://github.com/Jonatanooki/SearchMutant/blob/main/pictures/2-paso1ng.png?raw=true)

2. Luego en ***Edit Configurations***

![IMAGEN paso2](https://github.com/Jonatanooki/SearchMutant/blob/main/pictures/3-paso2ng.png?raw=true)

3. Realizamos clic en el botón **"+"** y luego en **"Application"**.

![Imagen paso 3](https://github.com/Jonatanooki/SearchMutant/blob/main/pictures/4-paso3ng.png?raw=true)

4. En la ventana de configuración colocamos el nombre de **Debug** y realizamos clic en el recuadro de **"Main Class"** al lado derecho.

![image](https://user-images.githubusercontent.com/86886404/178178555-69cd86ba-b489-4c14-b3c2-b7c96e864632.png)

5. IntelliJ nos ayuda a buscar la clase principal, la seleccionamos y damos clic en **"OK"** y guardamos los cambios, esto para habilitar la depuración.

![Imagen paso 5](https://github.com/Jonatanooki/SearchMutant/blob/main/pictures/6-paso5ng.png?raw=true)


 









