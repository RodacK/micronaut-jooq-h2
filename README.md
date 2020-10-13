## Feature http-client documentation

- [Micronaut Micronaut HTTP Client documentation](https://docs.micronaut.io/latest/guide/index.html#httpClient)
- Se usa eleste plugin para la generacion de clases (https://github.com/etiennestuder/gradle-jooq-plugin)

Para generar fuentes

./gradlew generateJooq

para eliminar fuentes generadas

./gradlew cleanGenerateJooq

------------Copia de archivos base de la BD---------------

Estos archivos se necesitan para levantar el server de H2
1- Copiar el contenido de basebd.rar en el home de su usuario

2- navegar en su consola hasta la ubicacion del archivo h2-1.4.200.jar

3- Escribir el siguiente comando en la consola 
$ java -cp h2-1.4.200.jar org.h2.tools.Server -web -webAllowOthers -tcp -tcpAllowOthers -baseDir ~/tmp/h2dbs

4- La consola mostrara algo parecido a esto: TCP server running at tcp://172.25.80.1:9092 (others can connect), tenga presente eso para un paso mas adelante

Lo anterior permite levantar el server de BD en modo tcp para multiples conexiones (aunque solo se tiene creado un usuario por el momento)

-------------Configurar el driver de H2 en Netbeans---------------------------

Necesitamos agregar driver h2 para que lo use Hibernate

5- services->database->new Connection->new Driver->add->buscar y agregar el archivo h2-1.4.200.jar

6- los siguientes son los datos a configurar:
user Name: rodack
Password: rodack
jdbc URL: jdbc:h2:tcp://172.25.80.1:9092/rodack;schema=transmi (aqui debe reemplazar por lo que muestra la consola en el paso 4)

6.1- da click en next para seleccionar el esquema:
Select schema: PUBLIC

7- buscar el archivo hibernate.cgf.xml y reeemplazar la propiedad hibernate.connection.url por el jdbc URL del paso 6

8- Correr la aplicacion y si al hacer build, tiene algun error como este: Malformed argument has embedded quote, seguir esta solucion
https://stackoverflow.com/a/59199958/8782229 

9- si por alguna cosa genera el error: org.h2.jdbc.JdbcSQLNonTransientConnectionException: La base de datos puede que ya esté siendo utilizada.
 La solucion es reiniciar el servidor de BD
 
 -------------Configurar el datasource de H2 en IntelliJ---------------------------
 
 5- A la derecha hay una opcion llamada Database, dar click alli
    despues boton + >  Data source > H2
 
 6- Despues en URL colocar:
    jdbc:h2:tcp://172.25.80.1:9092/rodack;schema=transmi (aqui debe reemplazar por lo que muestra la consola en el paso 4)
 
 7- Si quiere leda click en Test para probar la conexion y despues en apply    
     
----------------Extra y solo para ver definicion de la tabla y hacer consultas directamente---------------------------------------

1. Para correr un cliente H2 en local escribir en la terminal
$java -jar h2-1.4.200.jar -baseDir C:\Users\miUser\tmp\h2dbs (tener en cuenta mi home y la ubicacion de lo que se extrajo del rar)

2. Usar diferentes querys que estan en el archivo h2.sql
