# Arquitetura Hexagonal

API REST para cadastro e gerenciamento de clientes, desenvolvida com Spring Boot e organizada em arquitetura hexagonal. O fluxo integra persistência em MongoDB, consulta de CEP simulada com WireMock e mensageria Kafka para validação assíncrona de CPF.

## Tecnologias e serviços

- Java 25 e Spring Boot 4.1.3
- Spring Web MVC e Bean Validation
- Spring Data MongoDB
- Apache Kafka e Spring Kafka
- Spring Cloud OpenFeign para chamadas HTTP
- WireMock para simular o serviço de consulta de CEP
- MapStruct e Lombok
- Maven
- Docker Compose para executar MongoDB e Kafka localmente (com ZooKeeper, Kafdrop e Mongo Express)

## Visão geral da arquitetura

O domínio e os casos de uso dependem de portas, não de tecnologias externas. Os adaptadores conectam essas portas aos meios de entrada e saída:

| Pacote | Responsabilidade |
|---|---|
| `application/core/domain` | Modelos de domínio `Customer` e `Address` |
| `application/core/usecase` | Casos de uso para cadastrar, consultar, atualizar e excluir clientes |
| `application/ports/in` | Contratos de entrada usados pelos adaptadores |
| `application/ports/out` | Contratos de saída para persistência, CEP e Kafka |
| `adapters/in/controller` | API REST de clientes |
| `adapters/in/consumer` | Consumo do resultado de validação de CPF pelo Kafka |
| `adapters/out` | Implementações de persistência, consulta de CEP e publicação Kafka |
| `config` | Configuração dos casos de uso e dos clientes Kafka |

### Fluxos principais

- **Cadastro:** a API consulta o endereço pelo CEP via WireMock, salva o cliente no MongoDB e publica o CPF no tópico `tp-cpf-validation`.
- **Validação de CPF:** um serviço de validação externo deve consumir `tp-cpf-validation` e publicar o resultado no tópico `tp-cpf-validated`. A aplicação consome esse resultado e atualiza o cliente no MongoDB.
- **Consulta, atualização e exclusão:** são realizadas pela API REST. A atualização também consulta o endereço pelo CEP antes de persistir.

O serviço que efetivamente valida o CPF não faz parte deste repositório; aqui estão implementados o produtor e o consumidor Kafka que integram esse fluxo.

## Pré-requisitos

- JDK 25
- Docker e Docker Compose
- WireMock disponível em `http://localhost:8088/addresses`

O cliente Feign faz `GET /{zipCode}` sobre a URL configurada em `miguel.client.address.url`. Assim, com a configuração padrão, uma consulta usa `GET http://localhost:8088/addresses/{zipCode}` e espera um JSON com `street`, `city` e `state`.

O WireMock é usado para simular o serviço de CEP, mas o servidor e seus stubs não estão versionados neste repositório. Inicie o WireMock e configure o stub correspondente antes de cadastrar ou atualizar clientes. A URL base pode ser alterada em `src/main/resources/application.yml`.

## Executar localmente

Inicie MongoDB, Kafka, ZooKeeper, Kafdrop e Mongo Express:

```bash
docker compose -f docker-local/docker-compose.yaml up -d
```

O Kafka fica disponível para a aplicação em `localhost:9092`; o Kafdrop pode ser acessado em `http://localhost:9000`. As configurações de conexão do MongoDB e do serviço de CEP ficam em `src/main/resources/application.yml`. O endereço Kafka está definido nas classes `KafkaProducerConfig` e `KafkaConsumerConfig`.

Inicie a aplicação:

```bash
# macOS/Linux
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

A API ficará disponível em `http://localhost:8081`.

## API de clientes

Base URL: `/api/v1/customers`

| Método | Rota | Descrição | Resposta |
|---|---|---|---|
| `POST` | `/api/v1/customers` | Cadastra cliente, busca endereço pelo CEP e envia CPF para validação | `200 OK` |
| `GET` | `/api/v1/customers/{id}` | Busca cliente pelo ID | `200 OK` |
| `PUT` | `/api/v1/customers/{id}` | Atualiza cliente e endereço | `204 No Content` |
| `DELETE` | `/api/v1/customers/{id}` | Remove cliente | `204 No Content` |

Cadastro e atualização recebem `name`, `cpf` e `zipCode`, todos obrigatórios:

```json
{
  "name": "Maria Silva",
  "cpf": "12345678900",
  "zipCode": "01001000"
}
```

Exemplo de consulta:

```bash
curl http://localhost:8081/api/v1/customers/ID_DO_CLIENTE
```

A resposta da consulta contém `name`, `cpf`, `address` e `isValidCpf`. O resultado consumido de `tp-cpf-validated` é uma mensagem JSON com os campos `id`, `name`, `zipCode`, `cpf` e `isValidCpf`.

## Build e testes

```bash
# macOS/Linux
./mvnw test

# Windows
mvnw.cmd test
```
