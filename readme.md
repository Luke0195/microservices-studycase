# 🍽️ Restaurante - Sistema de Gerenciamento de Pedidos

Este projeto é um estudo prático de uma arquitetura baseada em **microserviços** utilizando **Spring Boot** e **RabbitMQ** como broker de mensagens. Ele simula o fluxo de pedidos em um restaurante, com serviços responsáveis por diferentes partes do processo.

## 📌 Objetivo

Aplicar os conceitos de microserviços, mensageria assíncrona e comunicação entre sistemas desacoplados por meio do RabbitMQ, promovendo escalabilidade e independência entre os serviços.

---

## ⚙️ Arquitetura da Solução


### 🔁 Fluxo de execução

1. O cliente faz um pedido via frontend.
2. O **Order Service** registra o pedido e envia para a fila `order-create`.
3. O **Kitchen Service** consome a mensagem, prepara o pedido e envia para a fila `order-done`.
4. O **Notification Service** consome da fila `order-done` e notifica o cliente (via WebSocket ou log).

---

## 🧩 Microserviços

- **order-service**: Responsável por registrar e enviar pedidos para processamento.
- **kitchen-service**: Simula a cozinha que prepara os pedidos.
- **notification-service**: Responsável por notificar o cliente quando o pedido estiver pronto.

---

## 🛠️ Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Web
- Spring AMQP
- RabbitMQ 3.12 (Docker)
- H2 Database (Dev) / PostgreSQL (Homologação)
- Docker
- Lombok
- WebSocket (para notificações futuras)
- OpenAPI (opcional para documentação)

---

## 🐳 RabbitMQ com Docker

```bash
docker run -d \
  --name rabbitmq \
  -p 5672:5672 \
  -p 15672:15672 \
  rabbitmq:3.12-management

## 📄 Endpoints (exemplo - order-service)
POST /api/orders – Criar um novo pedido

GET /api/orders – Listar todos os pedidos

## 📝 Melhorias Futuras
Autenticação via JWT

Documentação OpenAPI

Integração com WebSocket ou push notifications

Deploy com Docker Compose ou Kubernetes