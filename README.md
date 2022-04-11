# teste dev junior

Processo de seleção para desenvovedor java junior

### 📋 Pré-requisitos

1 -  download do postgre.

2 - download do java.

3 - add suas variaveis globais no SO

4 - download do IDE

5 - download do postman


### 🔧 Instalação

1 - criar um banco de dados com o nome teste_dev_junior rodando na porta 5432

2 - faça o Download deste dump https://drive.google.com/file/d/11ppT-TQPSS5CPLfonyp0yl-zehK_cp71/view

3 - adapte o seguinte codigo para a sua maquina local e rode o dump "psql --port 5432 --username postgres nome_do_banco < C:/Users/maico/Downloads/dump.sql"

4 - com o projeto ja aberto abrir src/main/resources/application.properties e altere datasource.usename e datasource.password para o usuario e password do seu banco de dados

5 - Rode a aplicação na classeTesteDevJuniorApplication.java

6 - use o postman para fazer para fazer o os teste no webservice

## 🛠️ Construído com

* [Spring](https://spring.io/) - O framework web usado
* [Maven](https://maven.apache.org/) - Gerente de Dependência
