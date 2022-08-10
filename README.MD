# ⚜️ Projeto Logistic API

> 🔰 Aplicação criada com Spring Framework para estudo de criação de REST API com JAVA, para cadastro e criação de uma logistica de entrega

## 💻 Tecnologias usadas
- ☕️ [Linguagem de Programação Java](https://docs.oracle.com/en/java/)
- 🍃 [Spring Framework](http://spring.io/)
- 🐬 [Mysql Databse](https://dev.mysql.com/doc/refman/8.0/en/)
- 🪶 [Flyway](https://flywaydb.org/)
- 📝 [ModelMapper](http://modelmapper.org/)

### 📌 Implementações
- **Rota de Clientes**
  - 📇 Criação
  - 🔍 Listagem de todos os clientes
  - 🔎 Listagem de clientes especificos
  - 📝 Atualizaçõa de dados de clientes 
  - 🗑 Exclusão de Cliente

- **Rota de Clientes**
  > Endpoint de Entregas usando uma representação do modelo de dados, não a classe de entrega (do Domain Model), para garantir melhor segurança na entrega dos dados
  - 📇 Solicitação de nova entrega (validando dados e ignorando dados não preenchidos pelo cliente)
  - 🔍 Consultando todas entrgas
  - 🔎 Consultando entregas especificas
