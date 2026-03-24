# QA Automation Framework

Framework de automação focado em qualidade de software, cobrindo testes Web, API e Performance com abordagem moderna de engenharia (SDET).

---

## Visão Geral

Este projeto demonstra a aplicação de boas práticas de automação com foco em:

- Confiabilidade dos testes
- Redução de flaky tests
- Validação de performance com métricas reais
- Integração contínua (CI/CD)

---

## Stack Tecnológica

- Java 17
- Maven
- Selenium WebDriver
- Cucumber (BDD)
- RestAssured
- JUnit
- JMeter
- GitHub Actions
- Allure Report

---

## Arquitetura

- Page Object Model (POM)
- BDD para legibilidade de cenários
- Separação entre testes funcionais e de performance
- Estrutura modular e escalável

---

## Testes Implementados

### Web

- Busca no blog
- Cenários positivos, negativos e edge cases
- Estratégia anti-flakiness via navegação direta (URL)

---

### API

- GET /breeds/list/all
- GET /breed/{breed}/images
- GET /breeds/image/random

Validações:
- Status code
- Estrutura de resposta
- Conteúdo retornado

Tratamento especial:
- Resiliência a instabilidade externa (ex: HTTP 503)

---

### Performance (API)

- Execução paralela com ThreadPool
- Coleta de métricas:
  - Min
  - Max
  - Média
  - P95

- SLA baseado em P95 (< 2000ms)

- Persistência dos resultados:
target/performance-report.txt

---

### Teste de Carga (JMeter)

- Simulação de alto volume de requisições
- Geração de relatório HTML

---

## Execução

Rodar todos os testes:

mvn clean test

Rodar API:

mvn -Dtest=DogApiTest test

Rodar Web:

mvn test -Dcucumber.filter.tags="@web"

---

## CI/CD

Pipeline automatizado com:

- Execução em push/PR
- Execução headless
- Geração de relatório Allure
- Publicação de artefatos

---

## Estratégias de Qualidade

- Separação de responsabilidades (funcional vs performance)
- Redução de flaky tests
- Tratamento de APIs instáveis
- Validação baseada em métricas reais (P95)
- Execução paralela para simular carga real

---

## Diferenciais Técnicos

- Paralelismo com coleta de métricas
- SLA baseado em percentil (P95)
- Persistência de resultados para análise
- Integração com JMeter
- Pipeline CI/CD completo

---

## Autor

Gilvando Matos  
https://github.com/Gilvando21  
https://www.linkedin.com/in/gilvando-matos-3a259821/
