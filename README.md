# atm-springboot-java-11
Projeto de um Caixa Eletrônico | ATM Project


Desenvolver uma aplicação que simule a entrega de notas quando um cliente efetuar um saque em um caixa eletrônico. Os requisitos básicos são os seguintes:

- Entregar o menor número de notas;
- É possível sacar o valor solicitado com as notas disponíveis;
- Saldo do cliente será cadastrado; 
- Quantidade de notas infinita. Notas disponíveis de R$ 100,00; R$ 50,00; R$ 20,00 e R$ 10,00.
- O Cliente não poderá entrar no negativo.
- Criar no mínimo 3 endpoints: para efetuar saque, cadastrar cliente e outro pra retornar saldo.
- Arquitetura fica a seu critério.

Exemplos:

Valor do Saque: R$ 30,00 – Resultado Esperado: Entregar 1 nota de R$20,00 e 1 nota de R$ 10,00.

Valor do Saque: R$ 80,00 – Resultado Esperado: Entregar 1 nota de R$50,00 1 nota de R$ 20,00 e 1 nota de R$ 10,00

Observações:

Cadastro , Edição e Exclusão de Clientes ( saldo de cada cliente será cadastrado junto ).

## Tecnologias:

### Back-end

- Java 8 ou superior
- Maven para criação do projeto
- Spring-boot
- Spring-data-jpa
- Lombok
- Banco de dados MySQL.
- Liquibase
- JUnit 5 com Mockito para testes unitários


### Executando a aplicação
- Para executar o projeto é necessário ter o banco de dados MySql instalado no host. Caso você tenha o docker
instalado em sua máquina, informar configuração.
