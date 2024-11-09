
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

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/newsalesapi
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
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
- **URL:** `POST /usuarios`

**Exemplo de Requisição:**

```json
{
  "name": "João Silva",
  "email": "joao.silva@example.com"
}
```

#### Obter Usuário por ID
- **URL:** `GET /usuarios/{id}`

#### Listar Todos os Usuários
- **URL:** `GET /usuarios`

#### Atualizar Usuário
- **URL:** `PUT /usuarios/{id}`

**Exemplo de Requisição:**

```json
{
  "name": "João da Silva",
  "email": "joao.dasilva@example.com"
}
```

#### Deletar Usuário
- **URL:** `DELETE /usuarios/{id}`

---

### 2. Itens (Item)

#### Criar Item
- **URL:** `POST /itens`

**Exemplo de Requisição:**

```json
{
  "name": "Laptop"
}
```

#### Obter Item por ID
- **URL:** `GET /itens/{id}`

#### Listar Todos os Itens
- **URL:** `GET /itens`

#### Atualizar Item
- **URL:** `PUT /itens/{id}`

**Exemplo de Requisição:**

```json
{
  "name": "Gaming Laptop"
}
```

#### Deletar Item
- **URL:** `DELETE /itens/{id}`

---

### 3. Movimentos de Estoque (StockMovement)

#### Adicionar Movimento de Estoque
- **URL:** `POST /itens/estoque/adicionar`

**Parâmetros:**
- `itemId` (Long) - ID do Item
- `quantidade` (int) - Quantidade a ser adicionada

**Exemplo de Requisição:**

```sh
POST /itens/estoque/adicionar?itemId=1&quantidade=50
```

---

### 4. Pedidos (Order)

#### Criar Pedido
- **URL:** `POST /pedidos`

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
- **URL:** `GET /pedidos/{id}`

#### Listar Todos os Pedidos
- **URL:** `GET /pedidos`

#### Atualizar Pedido
- **URL:** `PUT /pedidos/{id}`

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
- **URL:** `DELETE /pedidos/{id}`

---

## Informações Adicionais

- A data de criação do pedido é automaticamente definida para a data/hora do sistema.
- O status do pedido é atualizado para "COMPLETO" quando o estoque é suficiente, caso contrário, fica como "PENDENTE".
- Os logs são gravados no arquivo `app.log` na pasta `logs`.
