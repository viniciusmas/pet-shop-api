# 🐾 Pet Shop API - Backend

Backend da aplicação **Pet Shop**, responsável por gerenciar clientes, pets e autenticação via **Keycloak**, expondo uma API REST segura com **Spring Boot**.

---

## 🚀 Tecnologias Utilizadas

* **Java 17**
* **Spring Boot 3**
* **Spring Security (OAuth2 Resource Server)**
* **Keycloak** (Autenticação e Autorização)
* **JWT**
* **Docker & Docker Compose**
* **Banco H2**
* **Maven**

---

## 📂 Estrutura do Projeto

```
backend/
├── src/main/java/br/edu/infnet/petshopapi
│   ├── config        # Configurações (Security, Keycloak, etc)
│   ├── controller    # Controllers REST
│   ├── service       # Regras de negócio
│   ├── repository    # Repositórios JPA
│   ├── model         # Entidades
├── src/main/resources
│   ├── application.properties
│   └── data
└── Dockerfile
```

---

## 🔐 Autenticação e Segurança

A aplicação utiliza **Keycloak** como servidor de identidade.

* Autenticação via **OAuth2 / OpenID Connect**
* Tokens no formato **JWT**
* Validação automática do token pelo Spring Security

### Issuer configurado

```
http://localhost:8080/realms/pet-shop
```

> ⚠️ Importante: o `issuer-uri` do backend deve ser **idêntico** ao `iss` presente no token JWT.

---

## ⚙️ Configuração do application.yml

```yaml
spring.application.name=pet-shop-api

# H2 Database config
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.url=jdbc:h2:~/databasePetShop
spring.datasource.username=sa
spring.datasource.password=

# JPA / Hibernate
spring.jpa.database-plataform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=create
spring.jpa.show-sql=true

# Console H2 (acesso em http://localhost:8080/h2-console)
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

#Keylock
spring.security.oauth2.resourceserver.jwt.issuer-uri=http://localhost:8080/realms/pet-shop

# API viaCep
api.viacep.url=https://viacep.com.br/ws

# API Google Calendar
google.auth.client.id=823830676883-epfpt9cvrfuei5n26rqugurprel1h1pg.apps.googleusercontent.com
google.auth.client.secret=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
google.auth.refresh.token=1//0h8sCv5pxrx9BCgYIARAAGBESNwF-L9Ir3GypZYcEDLzlNjZSf4DDJQNMcv-LIHuJbd9FHtGkqTBn5EEJBJRzpTFbN9MxjaB2060

server.port=8081
```

---

## 🐳 Executando com Docker

### Subir toda a stack

```bash
docker-compose up -d --build
```

Serviços disponíveis:

| Serviço  | URL                                            |
| -------- | ---------------------------------------------- |
| Backend  | [http://localhost:8081](http://localhost:8081) |
| Keycloak | [http://localhost:8080](http://localhost:8080) |
| Frontend | [http://localhost:5173](http://localhost:5173) |

---

## 📌 Endpoints Principais

### Clientes

* `GET /api/clientes`
* `POST /api/clientes`
* `GET /api/clientes/{id}`

> 🔒 Todos os endpoints exigem **Bearer Token** válido.

### Exemplo de requisição

```
GET /api/clientes
Authorization: Bearer <TOKEN>
```

---

## 🧪 Testando a API

Você pode testar usando:

* **Postman**

Exemplo:

```bash
curl -X GET http://localhost:8081/api/clientes \
  -H "Authorization: Bearer SEU_TOKEN"
```

---

## 🧠 Observações Importantes

* O backend **não gera tokens** — apenas valida
* O frontend deve obter o token diretamente do Keycloak
* Nginx atua como proxy para `/api`

---

## 👨‍💻 Autor

Vinicius Souza