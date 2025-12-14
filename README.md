# 🚪 Spring Cloud Gateway MVC (Spring Boot 3)

Este projeto demonstra a implementação de um **API Gateway** utilizando **Spring Cloud Gateway MVC** com **Spring Boot 3** e **Java 21+**.

O Gateway atua como **ponto único de entrada** da aplicação, recebendo requisições HTTP e **roteando** para diferentes microsserviços internos, sem que o cliente tenha contato direto com eles.

---

## 🧠 Conceito

Em uma arquitetura de microsserviços:

* O **cliente (browser / frontend)** acessa apenas o **Gateway**
* O **Gateway** decide para qual microsserviço a requisição será encaminhada
* Os **microsserviços ficam isolados**, acessíveis apenas internamente

```
Cliente ──▶ Gateway ──▶ Microsserviços
```

---

## 🏗️ Arquitetura do Projeto

| Componente | Porta | Descrição                           |
| ---------- | ----- | ----------------------------------- |
| Gateway    | 8082  | Ponto único de entrada              |
| API 1      | 8080  | Microsserviço principal (`/api/**`) |
| API 2      | 8083  | Segundo microsserviço (`/apt/**`)   |

> ⚠️ Em produção, apenas o **Gateway** ficaria exposto. As portas dos microsserviços seriam privadas.

---

## 🛠️ Tecnologias Utilizadas

* Java 21
* Spring Boot 3.5.8
* Spring Cloud Gateway MVC
* Spring Web MVC
* Tomcat Embedded
* Maven

---

## 📦 Dependências Principais

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-gateway-mvc</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

---

## ⚙️ Configuração do Gateway

Classe responsável por definir as rotas do Gateway:

```java
@Configuration
public class GatewayConfig {

    @Bean
    public RouterFunction<ServerResponse> gatewayRoutes() {

        return GatewayRouterFunctions.route("gateway-routes")

            .route(
                path("/api/**"),
                http(URI.create("http://localhost:8080"))
            )

            .route(
                path("/apt/**"),
                http(URI.create("http://localhost:8083"))
            )

            .build();
    }
}
```

---

## 🌍 Fluxo da Requisição (Passo a Passo)

1. O usuário acessa no navegador:

   ```
   http://localhost:8082/api/hello
   ```

2. A requisição chega no **Gateway** (porta 8082)

3. O Gateway identifica o path `/api/**`

4. A requisição é encaminhada para:

   ```
   http://localhost:8080/api/hello
   ```

5. O microsserviço processa a requisição

6. A resposta retorna para o Gateway

7. O Gateway devolve a resposta ao cliente

➡️ O cliente **nunca acessa diretamente** a porta 8080.

---

## ▶️ Como Executar o Projeto

### 1️⃣ Suba os microsserviços

* API 1 → porta **8080**
* API 2 → porta **8083**

### 2️⃣ Suba o Gateway

```bash
mvn spring-boot:run
```

Gateway disponível em:

```
http://localhost:8082
```

---

## 🧪 Exemplos de Teste

### Acessando a API 1 pelo Gateway

```
http://localhost:8082/api/hello
```

### Acessando a API 2 pelo Gateway

```
http://localhost:8082/apt/test
```

---

## 🔐 Benefícios do Uso do Gateway

* Centralização de acesso
* Maior segurança
* Isolamento dos microsserviços
* Facilidade para adicionar:

  * autenticação
  * filtros
  * rate limit
  * logs

---

## 🚀 Próximos Passos

* Adicionar **Filters** (logging, auth)
* Integrar com **Eureka Server**
* Usar **Docker / Docker Compose**
* Configurar **HTTPS e domínio**

---

## 👨‍💻 Autor

**João Fabrício Moya Alves**
Desenvolvedor de Software

---

📌 *Projeto educacional para estudo de arquitetura de microsserviços com Spring Cloud Gateway MVC.*
