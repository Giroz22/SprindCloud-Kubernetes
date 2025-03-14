# SpringCloud-Kubernetes

# Docker

## Comandos más comunes de Docker

### 🔹 Gestión de Contenedores

| Comando                               | Descripción                                                |
| ------------------------------------- | ---------------------------------------------------------- |
| `docker ps`                           | Muestra los contenedores en ejecución.                     |
| `docker ps -a`                        | Muestra todos los contenedores, incluidos los detenidos.   |
| `docker run <imagen>`                 | Crea y ejecuta un nuevo contenedor a partir de una imagen. |
| `docker run -d <imagen>`              | Ejecuta un contenedor en segundo plano (modo _detached_).  |
| `docker run --name <nombre> <imagen>` | Ejecuta un contenedor con un nombre específico.            |
| `docker start <contenedor>`           | Inicia un contenedor detenido.                             |
| `docker stop <contenedor>`            | Detiene un contenedor en ejecución.                        |
| `docker restart <contenedor>`         | Reinicia un contenedor.                                    |
| `docker rm <contenedor>`              | Elimina un contenedor detenido.                            |
| `docker logs <contenedor>`            | Muestra los logs de un contenedor.                         |
| `docker exec -it <contenedor> bash`   | Accede a la terminal de un contenedor en ejecución.        |
| `docker inspect <contenedor>`         | Muestra información detallada de un contenedor.            |
| `docker top <contenedor>`             | Muestra los procesos en ejecución dentro de un contenedor. |
| `docker kill <contenedor>`            | Finaliza un contenedor de inmediato.                       |
| `docker container prune`              | Elimina todos los contenedores no utilizados.              |

### 🔹 Gestión de Imágenes

| Comando                                 | Descripción                                    |
| --------------------------------------- | ---------------------------------------------- |
| `docker images`                         | Lista las imágenes disponibles en el sistema.  |
| `docker pull <imagen>`                  | Descarga una imagen desde Docker Hub.          |
| `docker rmi <imagen>`                   | Elimina una imagen del sistema.                |
| `docker build -t <nombre>:<tag> .`      | Construye una imagen desde un `Dockerfile`.    |
| `docker tag <imagen> <nuevo_nombre>`    | Renombra una imagen.                           |
| `docker history <imagen>`               | Muestra el historial de cambios de una imagen. |
| `docker save -o <archivo>.tar <imagen>` | Guarda una imagen en un archivo tar.           |
| `docker load -i <archivo>.tar`          | Carga una imagen desde un archivo tar.         |
| `docker image prune`                    | Elimina todos las imagenes no utilizados.      |

### 🔹 Gestión de Volúmenes

| Comando                          | Descripción                                |
| -------------------------------- | ------------------------------------------ |
| `docker volume ls`               | Lista los volúmenes de Docker.             |
| `docker volume create <nombre>`  | Crea un volumen.                           |
| `docker volume rm <nombre>`      | Elimina un volumen.                        |
| `docker volume inspect <nombre>` | Muestra información de un volumen.         |
| `docker volume prune`            | Elimina todos los volúmenes no utilizados. |

### 🔹 Gestión de Redes

| Comando                                        | Descripción                            |
| ---------------------------------------------- | -------------------------------------- |
| `docker network ls`                            | Lista las redes disponibles.           |
| `docker network create <nombre>`               | Crea una nueva red.                    |
| `docker network inspect <nombre>`              | Muestra información de una red.        |
| `docker network connect <red> <contenedor>`    | Conecta un contenedor a una red.       |
| `docker network disconnect <red> <contenedor>` | Desconecta un contenedor de una red.   |
| `docker network prune`                         | Elimina todas las redes no utilizadas. |

### 🔹 Gestión de Docker Compose

| Comando                  | Descripción                                                       |
| ------------------------ | ----------------------------------------------------------------- |
| `docker-compose up`      | Inicia los servicios definidos en `docker-compose.yml`.           |
| `docker-compose up -d`   | Inicia los servicios en segundo plano.                            |
| `docker-compose down`    | Detiene y elimina los contenedores de `docker-compose.yml`.       |
| `docker-compose ps`      | Muestra los contenedores gestionados por Docker Compose.          |
| `docker-compose logs`    | Muestra los logs de los servicios gestionados por Docker Compose. |
| `docker-compose restart` | Reinicia los servicios gestionados por Docker Compose.            |

### 🔹 Otras Utilidades

| Comando                                               | Descripción                                                    |
| ----------------------------------------------------- | -------------------------------------------------------------- |
| `docker system prune`                                 | Elimina contenedores, imágenes y volúmenes no utilizados.      |
| `docker info`                                         | Muestra información sobre la instalación de Docker.            |
| `docker stats`                                        | Muestra el uso de recursos en tiempo real de los contenedores. |
| `docker version`                                      | Muestra la versión de Docker instalada.                        |
| `docker search <imagen>`                              | Busca imágenes en Docker Hub.                                  |
| `docker cp <contenedor>:<ruta_origen> <ruta_destino>` | Copia archivos desde/hacia un contenedor.                      |
| `docker diff <contenedor>`                            | Muestra cambios en el sistema de archivos de un contenedor.    |

## DockerFile

# Instrucciones de Dockerfile

| Instrucción   | Descripción                                                                                     | Ejemplo                                                   |
| ------------- | ----------------------------------------------------------------------------------------------- | --------------------------------------------------------- |
| `FROM`        | Define la imagen base para el contenedor.                                                       | `FROM ubuntu:latest`                                      |
| `LABEL`       | Agrega metadatos a la imagen.                                                                   | `LABEL maintainer="tu@email.com"`                         |
| `ENV`         | Define variables de entorno.                                                                    | `ENV APP_ENV=production`                                  |
| `WORKDIR`     | Establece el directorio de trabajo dentro del contenedor.                                       | `WORKDIR /app`                                            |
| `COPY`        | Copia archivos o directorios al contenedor.                                                     | `COPY . /app`                                             |
| `ADD`         | Similar a `COPY`, pero permite descargar archivos desde una URL y extraer archivos comprimidos. |                                                           |
| `RUN`         | Ejecuta comandos en la imagen durante la construcción.                                          | `RUN apt-get update && apt-get install -y curl`           |
| `CMD`         | Define el comando por defecto al ejecutar el contenedor.                                        | `CMD ["node", "server.js"]`                               |
| `ENTRYPOINT`  | Similar a `CMD`, pero no se puede sobrescribir fácilmente.                                      | `ENTRYPOINT ["python", "app.py"]`                         |
| `EXPOSE`      | Expone un puerto para comunicación.                                                             | `EXPOSE 8080`                                             |
| `VOLUME`      | Define un volumen para persistencia de datos.                                                   | `VOLUME /data`                                            |
| `ARG`         | Define variables de entorno solo disponibles en tiempo de construcción.                         | `ARG VERSION=1.0`                                         |
| `HEALTHCHECK` | Define un chequeo de salud para el contenedor.                                                  | `HEALTHCHECK --interval=30s CMD curl -f http://localhost` |
| `USER`        | Cambia el usuario con el que se ejecutan los procesos.                                          | `USER appuser`                                            |
| `ONBUILD`     | Especifica instrucciones que se ejecutan cuando una imagen base se usa en otro Dockerfile.      |                                                           |
| `SHELL`       | Cambia el shell predeterminado para ejecutar comandos en `RUN`.                                 | `SHELL ["/bin/bash", "-c"]`                               |

## Dudas

### 🛠️ Conectando una Base de Datos en la Máquina Local desde un Contenedor Docker en Ubuntu

En Ubuntu, si se desea conectar a una base de datos (BD) que se ejecuta en la máquina local desde un contenedor Docker, existen dos opciones principales:

#### 1️⃣ Usando `host.docker.internal`

##### Configuración:

- En `application.properties`:
  ```properties
  spring.datasource.url=jdbc:mysql://host.docker.internal:3306/msvc_users
  ```
- Ejecutar el contenedor Docker:
  ```sh
  docker run --add-host=host.docker.internal:host-gateway -p 8001:8001 <ID_CONTENEDOR>
  ```

#### 2️⃣ Usando la IP de la máquina local

##### Configuración:

- Obtener la IP de la máquina local:
  ```sh
  hostname -I | awk '{print $1}'
  ```
- En `application.properties`:
  ```properties
  spring.datasource.url=jdbc:mysql://<IP_LOCAL>:3306/msvc_users
  ```
- Ejecutar el contenedor Docker:
  ```sh
  docker run -p 8001:8001 <ID_CONTENEDOR>
  ```

#### 🛠️ Recomendaciones

- Asegurar que MySQL esté configurado para escuchar en todas las interfaces (`bind-address=0.0.0.0` en `my.cnf`).
- Verificar que el usuario tenga acceso desde fuentes externas (`%:root`).
