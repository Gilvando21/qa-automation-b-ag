# QA Automation Framework (Web + API)

Framework de automação desenvolvido com foco em boas práticas de engenharia de qualidade, cobrindo testes Web e API, com estrutura escalável e preparada para CI/CD.

---

## Tecnologias utilizadas

- Java 17
- Maven
- Selenium WebDriver
- Cucumber (BDD)
- RestAssured
- JUnit
- GitHub Actions (CI/CD)

---

## Estrutura do projeto

```
src
 └── test
     ├── java
     │   ├── pages        # Page Object Model (POM)
     │   ├── steps        # Steps Cucumber
     │   ├── runners      # Runner dos testes
     │   ├── hooks        # Setup e teardown
     │   ├── api          # Testes de API
     └── resources
         └── features     # Cenários BDD
```

---

## Testes implementados

### Web
- Busca de artigos no Blog do Agi
- Validação de resultados

 Estratégia aplicada:
- Evitei dependência da UI instável (lupa)
- Utilizei busca via URL (`?s=`) para garantir estabilidade e reduzir flakiness

---

### API (Dog API)
- Listagem de raças
- Busca de imagens por raça
- Validação de status code e estrutura da resposta

---

##  Como executar

### Executar todos os testes
```bash
mvn clean test
```

---

### Executar apenas API
```bash
mvn -Dtest=DogApiTest test
```

---

### Executar apenas Web
```bash
mvn test -Dcucumber.filter.tags="@web"
```

---

### Executar em modo headless (pipeline)
```bash
mvn clean test -Dheadless=true
```

---

## CI/CD

O projeto está preparado para execução em pipeline (GitHub Actions), permitindo rodar testes automaticamente a cada push ou pull request.

---

## Diferenciais

- Page Object Model (POM)
- Separação de responsabilidades (Web e API)
- Testes resilientes (redução de flakiness)
- Execução integrada via Maven
- Estrutura escalável
- Estratégia de estabilização (uso de URL ao invés de UI dinâmica)

---

## Autor

Gilvando Matos  
 https://github.com/Gilvando21  
 https://www.linkedin.com/in/gilvando-matos-3a259821/
