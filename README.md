
# New Sales API

Esta é uma API simples para gerenciamento de pedidos, onde os usuários podem criar e gerenciar pedidos, adicionar itens e atualizar o estoque. A API foi desenvolvida com Java 8, Spring Boot, Spring JPA, PostgreSQL e Log4j2.

## Como Executar o Projeto

### Pré-requisitos
- Java 8
- Maven
- PostgreSQL

### Configuração do Banco de Dados

Crie um banco de dados no PostgreSQL com o nome `newsalesapi`.

### Configurações do `application.properties`

Este projeto ignora o arquivo real `src/main/resources/application.properties` no Git para evitar expor credenciais.

1. Copie o arquivo de exemplo e ajuste conforme seu ambiente:

    ```sh
    cp src/main/resources/application.properties.example src/main/resources/application.properties
    ```

2. Edite `src/main/resources/application.properties` preenchendo usuário, senha e demais variáveis.

O arquivo de exemplo contém chaves como:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/newsalesapi
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

1. Clone o repositório:

    ```sh
    git clone https://github.com/arceger/new_api_java_postgres.git
    cd newsalesapi
    ```

2. Compile e instale as dependências:

    ```sh
    mvn clean install
    ```

3. Execute a aplicação:

    ```sh
    mvn spring-boot:run
    ```

## Endpoints da API

### 1. Usuários (User)

#### Criar Usuário
- **URL:** `POST http://localhost:8080/usuarios`

**Exemplo de Requisição:**

```json
{
  "name": "João Silva",
  "email": "joao.silva@example.com"
}
```

#### Obter Usuário por ID
- **URL:** `GET http://localhost:8080/usuarios/{id}`

#### Listar Todos os Usuários
- **URL:** `GET http://localhost:8080/usuarios`

#### Atualizar Usuário
- **URL:** `PUT http://localhost:8080/usuarios/{id}`

**Exemplo de Requisição:**

```json
{
  "name": "João da Silva",
  "email": "joao.dasilva@example.com"
}
```

#### Deletar Usuário
- **URL:** `DELETE http://localhost:8080/usuarios/{id}`

---

### 2. Itens (Item)

#### Criar Item
- **URL:** `POST http://localhost:8080/itens`

**Exemplo de Requisição:**

```json
{
  "name": "Laptop"
}
```

#### Obter Item por ID
- **URL:** `GET http://localhost:8080/itens/{id}`

#### Listar Todos os Itens
- **URL:** `GET http://localhost:8080/itens`

#### Atualizar Item
- **URL:** `PUT http://localhost:8080/itens/{id}`

**Exemplo de Requisição:**

```json
{
  "name": "Gaming Laptop"
}
```

#### Deletar Item
- **URL:** `DELETE http://localhost:8080/itens/{id}`

---

### 3. Movimentos de Estoque (StockMovement)

#### Adicionar Movimento de Estoque
- **URL:** `POST http://localhost:8080/itens/estoque/adicionar`

**Parâmetros:**
- `itemId` (Long) - ID do Item
- `quantidade` (int) - Quantidade a ser adicionada

**Exemplo de Requisição:**

```sh
POST http://localhost:8080/itens/estoque/adicionar?itemId=1&quantidade=50
```

---

### 4. Pedidos (Order)

#### Criar Pedido
- **URL:** `POST http://localhost:8080/pedidos`

**Exemplo de Requisição:**

```json
{
  "item": {
    "id": 1
  },
  "quantidade": 10,
  "usuario": {
    "id": 1
  }
}
```

#### Obter Pedido por ID
- **URL:** `GET http://localhost:8080/pedidos/{id}`

#### Listar Todos os Pedidos
- **URL:** `GET http://localhost:8080/pedidos`

#### Atualizar Pedido
- **URL:** `PUT http://localhost:8080/pedidos/{id}`

**Exemplo de Requisição:**

```json
{
  "item": {
    "id": 1
  },
  "quantidade": 15,
  "usuario": {
    "id": 1
  }
}
```

#### Deletar Pedido
- **URL:** `DELETE http://localhost:8080/pedidos/{id}`

---

## Informações Adicionais

- A data de criação do pedido é automaticamente definida para a data/hora do sistema.
- O status do pedido é atualizado para "COMPLETO" quando o estoque é suficiente, caso contrário, fica como "PENDENTE".
- Os logs são gravados no arquivo `app.log` na pasta `logs`.
