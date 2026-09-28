# TEK Microservices

Open this folder's `pom.xml` in IntelliJ (File → Open → select this folder,
or select `pom.xml` and choose "Open as Project"). IntelliJ will detect it
as a Maven multi-module project and import all four modules together.

## Modules

| Module               | Port | Role                                                   |
|----------------------|------|---------------------------------------------------------|
| TEK_Service_Registry  | 8761 | Eureka server — service discovery                       |
| TEK_UserService       | 8334 | Owns `users` table (own DB). Registers as `TEK-UserService` |
| TEK_OrderService      | 8333 | Owns `orders` table (own DB); calls User Service via Feign. Registers as `TEK-OrderService` |
| TEK_API_Gateway       | 8335 | Single entry point, routes to both services via Eureka  |

> **Note on naming:** service names use hyphens (`TEK-UserService`, not
> `TEK_UserService`). Spring Cloud OpenFeign validates `@FeignClient`
> names as legal hostnames, and hostnames can't contain underscores —
> using an underscore causes `IllegalStateException: Service id not
> legal hostname` at startup. Keep this hyphenated form if you rename
> anything.

## Run order

Start in this order (each needs the previous one available):

1. **TEK_Service_Registry** — `TekServiceRegistryApplication`
2. **TEK_UserService** — `TekUserServiceApplication`
3. **TEK_OrderService** — `TekOrderServiceApplication`
4. **TEK_API_Gateway** — `TekApiGatewayApplication`

In IntelliJ, right-click each module's `*Application.java` main class and
choose "Run", in the order above (or create 4 Run/Debug configurations and
launch them in order).

## How the services connect

- Eureka (Service Registry) is the directory both User Service and Order
  Service register with, and that the Gateway and Order Service use to
  find them.
- **API Gateway** routes:
  - `/api/v1/users/**` → `TEK-UserService` (`lb://TEK-USERSERVICE`)
  - `/api/v1/orders/**` → `TEK-OrderService` (`lb://TEK-ORDERSERVICE`)
- **Order Service → User Service**: `OrderServiceImpl` calls a Feign
  client (`UserServiceClient`, `@FeignClient(name = "TEK-UserService")`)
  to fetch/validate the user for an order, even though the two services
  use separate databases. This call is direct service-to-service via
  Eureka and doesn't go through the Gateway.

## Try it

```
POST http://localhost:8335/api/v1/users
{ "name": "Jane Doe", "email": "jane@example.com" }

POST http://localhost:8335/api/v1/orders
{ "userId": 1, "product": "Laptop", "amount": 1200.0 }

GET http://localhost:8335/api/v1/orders/1
```

The order response embeds the user fetched live from User Service.
