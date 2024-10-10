# Proyecto de Diseño de Sistemas - Backend

Este repositorio contiene el backend del proyecto desarrollado para la materia **Diseño de Sistemas**. 

## 📦 Tecnologías utilizadas
- **Lenguaje**: Java
- **Framework**: Spring
- **Gestor de dependencias**: Maven
- **Base de datos**: MySQL

## ⚙️ Instalación

1. Clona el repositorio:
   ```bash
   git clone https://github.com/GuidoSiverio/tp-dds.git
   
2. Navega al directorio del proyecto:
   ```bash
   cd <NOMBRE_DEL_DIRECTORIO>
   
3. Compila el proyecto:
   ```bash
   mvn clean install
   
4. Configura la conexión a la base de datos en application.properties:
   ```bash
   spring.datasource.url=jdbc:mysql://localhost:3306/<NOMBRE_BASE_DE_DATOS>
   spring.datasource.username=<USUARIO>
   spring.datasource.password=<CONTRASEÑA>

5. Inicia la aplicación:
   ```bash
   mvn spring-boot:run
