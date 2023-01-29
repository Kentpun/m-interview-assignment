## MOX Interview Assignment

## Tech stack
- Java 11 JDK
- Git
- SprintBoot 3.0.1
- Maven
- Junit5 / Mockito
- H2

## Design Principle
### Directories structure
- endpoint
- entity
  - dto
  - po
- exception
- repository
- service
- utils

## APIs
- POST /account-manager/balance<br />
Description: Query the account balance and details<br />
``` curl -X POST  localhost:8080/account-manager/balance -H 'Content-Type: application/json' -d '{"ownerId": 11111111}' ```
- POST /account-manager/balance/transfer<br />
Description: Make a payment transfer transaction<br />
``` curl -X POST  localhost:8080/account-manager/balance/transfer -H 'Content-Type: application/json' -d '{"senderAccountId": 88888888, "receiverAccountId": 12345678, "amount": 1.1, "currency": "HKD", "ownerId": 11111111}' ```
