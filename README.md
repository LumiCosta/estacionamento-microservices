# 🚗 Estacionamento Microservices

Sistema de microsserviços desenvolvido com **Spring Boot 3**, **Spring Cloud**, **Eureka**, **API Gateway**, **H2** e **RabbitMQ**, seguindo a arquitetura solicitada para gerenciamento de vagas, reservas, pagamentos e ocupação em tempo real.

---

# 🧱 Arquitetura

* **Discovery Server (Eureka)** → registro e descoberta dos serviços
* **API Gateway** → ponto único de entrada, roteamento, rate limiting e circuit breaker
* **Vagas Service** → inventário físico das vagas e eventos de sensores
* **Reservas Service** → agenda/reserva de vagas, consultando o serviço de vagas
* **Pagamentos Service** → processamento de pagamento com retry
* **Ocupação Service** → métricas de lotação em tempo real
* **RabbitMQ** → broker de mensagens para eventos IoT
* **DLQ** → filas de erro para mensagens que falharam

## 🔁 Fluxos

```text
Cliente/Admin
   ↓ HTTP
API Gateway
   ↓
Vagas / Reservas / Pagamentos / Ocupação
```

```text
Sensor IoT
   ↓ evento sensor.vaga
RabbitMQ Exchange parking.events
   ├── sensor.vagas        → Vagas Service
   └── ocupacao.metricas   → Ocupação Service
```

---

# ⚙️ Tecnologias utilizadas

* Java 17
* Spring Boot 3.x
* Spring Cloud
* Eureka Server
* Spring Cloud Gateway
* OpenFeign
* Spring Data JPA
* H2 Database
* RabbitMQ / Spring AMQP
* Resilience4j Retry e Circuit Breaker
* Maven multi-module
* Actuator

---

# 📁 Estrutura do projeto

```text
estacionamento-microservices/
│
├── discovery-server/
├── api-gateway/
├── vagas-service/
├── reservas-service/
├── pagamentos-service/
├── ocupacao-service/
├── sensor-simulator/
├── docker-compose.yml
└── pom.xml (parent)
```

---

# 🚀 Como executar

## 1. Subir o RabbitMQ

```bash
docker compose up -d
```

Painel RabbitMQ:

```text
http://localhost:15672
usuário: guest
senha: guest
```

## 2. Rodar os serviços nesta ordem

1. `DiscoveryServerApplication` → `http://localhost:8761`
2. `VagasServiceApplication` → porta `8100`
3. `ReservasServiceApplication` → porta `8200`
4. `PagamentosServiceApplication` → porta `8300`
5. `OcupacaoServiceApplication` → porta `8400`
6. `ApiGatewayApplication` → porta `8080`

---

# 🧪 Testes rápidos

## Verificar Gateway

```text
http://localhost:8080/actuator/health
```

## Listar vagas pelo Gateway

```text
http://localhost:8080/api/vagas
```

## Listar vagas disponíveis

```text
http://localhost:8080/api/vagas/disponiveis
```

## Criar reserva

```bash
curl -X POST http://localhost:8080/api/reservas   -H "Content-Type: application/json"   -d '{"vagaId":1,"cliente":"Paloma"}'
```

## Criar pagamento

```bash
curl -X POST http://localhost:8080/api/pagamentos   -H "Content-Type: application/json"   -d '{"reservaId":1,"valor":25.50}'
```

## Consultar ocupação

```text
http://localhost:8080/api/ocupacao/resumo
```

## Simular sensor IoT

```bash
cd sensor-simulator
pip install pika
python publish-sensor-event.py 1 true
python publish-sensor-event.py 1 false
```

---

# 💾 Bancos H2

* Vagas: `http://localhost:8100/h2-console`
* Reservas: `http://localhost:8200/h2-console`
* Pagamentos: `http://localhost:8300/h2-console`
* Ocupação: `http://localhost:8400/h2-console`

JDBC URL por serviço:

```text
jdbc:h2:mem:vagasdb
jdbc:h2:mem:reservasdb
jdbc:h2:mem:pagamentosdb
jdbc:h2:mem:ocupacaodb
```

---

# 📌 Observação

Este projeto é uma base didática pronta para evoluir. Para produção, adicione autenticação JWT, Redis real no rate limiter, Dockerfile por serviço, banco PostgreSQL por serviço, Prometheus/Grafana e tracing com OpenTelemetry.
