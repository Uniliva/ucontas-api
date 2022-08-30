# Ucontas
Projeto simples para demostrar a construção de rest api, testes com banco em memória e consumo de api de consulta cep.

[![Build Status](https://travis-ci.com/Uniliva/Ucontas-api.svg?branch=master)](https://travis-ci.com/github/Uniliva/Ucontas-api)
[![codecov](https://codecov.io/gh/Uniliva/Ucontas-api/branch/master/graph/badge.svg)](https://codecov.io/gh/Uniliva/Ucontas-api)


---

## Acessando o projeto 

### Heroku

Para facilitar o acesso, esse projeto foi ajustado, através da branch _heroku-version_, para ser executado no heroku:

- ucontas-front: https://ucontas-front.herokuapp.com/
- swagger ucontas-api: https://ucontas-api.herokuapp.com/swagger-ui.html

---

### Localmente

- Passo 1: Base de dados

Para executar o projeto localmente e necessário ter o banco postgres


**Obs:** Caso queira utilizar o banco em outro endereço/porta, altere os dados em _application.properties_.

- Passo 2: Configurando banco

SIM eu devia ter usado o **flyway** para versionar o banco, mas não usei :(, por isso será necessário conectar no banco e executar os scripts que estão dentro da pasta _scripts_.  :( DESCULPE por isso ....

- Passo 3: Baixe o projeto

- Passo 4: Execute

Acesse a pasta e execute:

```
mvn spring-boot:run 
```

- Passo 5:  Acesse

```
#swagger
http://localhost:9090/swagger-ui.html
```
---

### Com docker 

Você também, pode usar essa aplicação através do docker, para isso acesse : https://github.com/Uniliva/ucontas-installer e siga as intruções para instalação.

---
---

##  Tecnologias empregadas

- Java 8+
- Rest
- Spring Boot
- JUnit
- Oracle 12g
- Docker
- Swagger
- Api de consulta cep, para demostração de serviços rest - https://viacep.com.br/ 
- CodeCov para analise de cobertura de teste - https://codecov.io/gh/Uniliva/Ucontas-api
- Travis CI pra build e geração de image docker
    - https://travis-ci.com/github/Uniliva/Ucontas-api
    - https://hub.docker.com/repository/docker/uniliva/ucontas-api


