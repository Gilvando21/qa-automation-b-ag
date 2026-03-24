# QA Automation Framework (Web + API + Performance)

Framework de automação desenvolvido com foco em boas práticas de engenharia de qualidade, cobrindo testes Web, API e Performance, com estrutura escalável e preparada para CI/CD.

---

## Tecnologias utilizadas

- Java 17
- Maven
- Selenium WebDriver
- Cucumber (BDD)
- RestAssured
- JUnit
- JMeter (Performance)
- Allure Report
- GitHub Actions (CI/CD)

---

## Estrutura do projeto

src
 └── test
     ├── java
     │   ├── pages
     │   ├── steps
     │   ├── runners
     │   ├── hooks
     │   ├── api
     └── resources
         └── features

performance
 └── blazedemo.jmx

---

## Testes implementados

Web (BDD + Selenium)
- Busca de artigos no Blog do Agi
- Cenários positivos, negativos e edge cases

API (RestAssured)
- Listagem de raças
- Busca de imagens por raça
- Validação de status e tempo de resposta

Performance (JMeter)
- 50 usuários simultâneos
- Ramp-up de 10 segundos
- Duração de 60 segundos
- SLA < 2000ms

---

## Como executar

mvn clean test

API:
mvn -Dtest=DogApiTest test

Web:
mvn test -Dcucumber.filter.tags="@web"

Headless:
mvn clean test -Dheadless=true

---

## Relatórios

Allure:
mvn allure:serve

Performance:
jmeter -n -t performance/blazedemo.jmx -l resultado.jtl
jmeter -g resultado.jtl -o relatorio-performance

---

## CI/CD

Executado via GitHub Actions com geração de artefatos.

---

## Diferenciais

- POM
- BDD
- Web + API + Performance
- Anti-flakiness
- Execução CLI
- CI/CD
- Evidências (Allure + HTML)

---

## Autor

Gilvando Matos  
https://github.com/Gilvando21  
https://www.linkedin.com/in/gilvando-matos-3a259821/
