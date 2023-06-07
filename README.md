# Controle de fluxo diario API
Este projeto é destinado a plataforma de controle de estoque, utilizando a versão 17 do
 Java.


## Começando

Para executar o projeto, será necessário instalar os seguintes programas:

- [JDK 17: Necessário para executar o projeto Java](https://www.oracle.com/java/technologies/downloads/#java17)
- [Maven 3++: Necessário para realizar o build do projeto Java](https://maven.apache.org/download.cgi)
- [Git: Necessário para clonar o projeto](https://git-scm.com/)


## Desenvolvimento

Para iniciar o desenvolvimento, é necessário clonar o projeto [controle-estoque](https://github.com/rafaelvulner/controle-estoque.git) do Github em um diretório de sua preferência:

- `controle-estoque`: git clone https://github.com/rafaelvulner/controle-estoque.git


## Construção (Build)

Para construir o projeto com o Maven, executar os comando abaixo:

- `mvn clean install` executar esse comando para baixar as dependências

O comando irá baixar todas as dependências do projeto e criar um diretório target com os artefatos construídos, que incluem o arquivo jar do projeto. Além disso, serão executados os testes unitários, e se algum falhar, o Maven exibirá essa informação no console.


## Commits

Faça comite nas sua branch de desenvolvimento, faça um `merge-request` e solicite ao seu DEV-LEAD ou um dev com mais experiência para revisar seu código e aprovar o merge.


## Tecnologias utilizadas

 - [Spring-boot](https://docs.spring.io/spring-boot/docs/current/maven-plugin/usage.html)
 - [Maven](https://maven.apache.org/index.html)
 - [Java 17](https://www.oracle.com/java/technologies/downloads/#java17)
 - [Lombok](https://projectlombok.org/)

## DB's

A aplicação necessita de bancos de dados relacionais (H2 em caso de uma execução local/desenvolvimento):

 - [H2] (http://www.h2database.com/html/download.html)

## Boas práticas

- Faça testes unitários de todas suas chamadas e regras de NEGOCIOS, tente manter uma cobertura minimia de 80%
 
- Sempre certifique-se de não ter quebrado nenhum outro código.

- Faça build do projeto e execute-o antes de fazer commit das suas alterações.

- Utilizar o Retryable para a retentativa dentro do código, de acordo com as exceções mapeadas dentro dos clients/services; 

## Melhorias

- Quebrar a API em microserviços
- Desacoplar modulos integrados
- Convenções e padrões de projetos 
- Estrutura de arquivos e pastas
- Centralizar arquivos de configurações



## Rodando os testes 

Para rodar os testes, realize o seguinte comando

```bash
# Rodar todos os testes 
$ mvn test

# Rodar penas uma classe
$ mvn -Dtest=TestApp1 test
```


## Rodando local

Clonar o projeto no diretorio de sua preferencia

```bash
  git clone https://github.com/rafaelvulner/controle-estoque.git
```

Ir para o diretorio do projeto

```bash
  cd controle-estoque
```

Instalar dependencias

```bash
  mvn clean install
```

Rodar a aplicação

```bash
  mvn spring-boot:run
```

## Testando endpoints no Postman

- Dentro do Postman, clicar em import
- Upload Files
- Selecionar a Collection [Fazer download](https://raw.githubusercontent.com/rafaelvulner/controle-estoque/master/Controle%20estoque%20Inmetrics.postman_collection.json)
- Import

Pronto, já vai estar tudo organizado para realizar os testes locais.

