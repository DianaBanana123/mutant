# Detector de Mutantes 

Detector de mutantes es un sistema que permite diferenciar entre mutantes y humanos automáticamente a partir de un ADN ingresado.

Esta aplicación reconoce la estructura del ADN correspondiente a un mutante, cada ADN procesado es registrado en base de datos. Adicionalmente provee el servicio stats que genera estadisticas de los registros procesados.

## Construido con 🛠️

Herramientras de creación del proyecto:

* [Java 11](https://docs.oracle.com/en/java/javase/11/docs/api/index.html) - Lenguaje POO usado
* [Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/) - Framework de servicios usado
* [JPA](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#reference) - Framework de persistencia usado
* [Junit](https://junit.org/junit5/) - Framework de pruebas unitarias
* [Mockito](https://site.mockito.org/) - Framework de pruebas mock
* [Maven](https://maven.apache.org/) - Manejador de dependencias
* [Postgres](https://www.postgresql.org/docs/) - Motor de base de datos
* [Jacoco](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/) - Plugin de analizador de cobertura de código
* [Google Cloud App Engine](https://cloud.google.com/appengine/docs?hl=es-419) - Alojamiento Web

## Instrucciones de ejecución API 🚀

Provee dos servicios rest que podrán ser ejecutados desde cualquier cliente rest, en este caso podrá utilizar **Postman**, clic para descargar  -> [Postman](https://www.postman.com/downloads/ "Descargar Postman")

## Comenzando  📄
Cada servicio cuenta con una funcionalidad y endpoint diferentes, desplegados en el servidor en la nube de Google.

### URL API 📌
		https://mutant-316001.rj.r.appspot.com

### Analizar ADN🔧
**Metodo**: POST
**Endpoint: **https://mutant-316001.rj.r.appspot.com
**Servicio**: /mutant

> ** Ejemplo de petición ADN - Mutante**
Respuesta : 		200 OK

	{
	“dna”:["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
	}

> **Ejemplo de petición ADN - Humano**
> Respuesta: 		403 FORBIDDEN

	{
		"dna": ["ATGCGA","CAGTAC","TTATGT","AGAAAG","TATCTA","TCACTG"]
	}


### Consultar Estadísticas de ADNs procesados🔧

**Metodo**: GET
**Endpoint: **https://mutant-316001.rj.r.appspot.com
**Servicio**: /stats

> ** Ejemplo de petición -> Sin envio de parámetros**
Respuesta : 		200 OK

	{
		"count_mutant_dna": 2,
		"count_human_dna": 3,
		"ratio": 0.6
	}


## Instalación en Máquina Local  ⚙️
En caso de requerir una instalación local de este ambiente realice los pasos descritos a continuación:
```
Clonar el proyecto en la máquina local con Git.
Importar el proyecto su IDE de desarrollo.
Realizar conexión a base de datos Postgres : Tenga en cuenta las especificaciones de conexión en el archivo application.properties . Asegurese que correspondan.
Subir el servidor: Una vez ejecute el servidor se crearán las tablas identificadas en el paquete models.
```

Tenga en cuenta, si requiere una base de datos local una vez la haya creado ajuste estos valores en el archivo application.properties


## Versionado 📌

Usamos [Git](http://semver.org/) para el versionado.

## Autores ✒️

* **Diana Paola Rojas Pinzón** - *Implementación* - [repository](https://github.com/DianaBanana123/mutant)



