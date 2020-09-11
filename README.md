# Schedule API
Projeto de agendamento para envio de comunicação. Desenvolvido na Linguagem Java.

## License
[Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0)


## Tecnologias 

- [Spring Boot](https://spring.io/projects/spring-boot) - Framework de Desenvolvimento para a Linguagem Java.

- [Lombok](https://projectlombok.org/) - Biblioteca Java focada em produtividade e redução de código boilerplate que, por meio de anotações adicionadas ao nosso código, ensinamos o compilador (maven ou gradle) durante o processo de compilação a criar código Java.

- [JUnit5](https://junit.org/junit5/) - Framework facilita a criação e manutenção do código para a automação de testes com apresentação dos resultados.

- [Mockito](https://site.mockito.org/) - Estrutura de teste de código aberto para Java liberada sob a licença MIT. A estrutura permite a criação de objetos duplos de teste em testes de unidade automatizados com o objetivo de desenvolvimento orientado a teste ou desenvolvimento orientado a comportamento.

- [PostgreSQL](https://www.postgresql.org/download/) - Banco de dados.

- [Hibernate](https://hibernate.org/) - Framework para persistência de dados. (ORM)

- [JPA](https://hibernate.org/orm/) - Especificação do Java que dita como os Frameworks ORM devem ser implementados.

- [Docker](https://www.docker.com/) - Plataforma open source que facilita a criação e administração de ambientes isolados. Ele possibilita o empacotamento de uma aplicação ou ambiente dentro de um container, se tornando portátil para qualquer outro host que contenha o Docker instalado.

- [Swagger](https://swagger.io/) - Essencialmente uma linguagem de descrição de interface para descrever APIs RESTful expressas usando JSON.


## Para compilar e executar o projeto você precisa ter instalado

 - [Maven](https://maven.apache.org/) - Ferramenta de automação de compilação utilizada primariamente em projetos Java.
 - [Docker](https://docs.docker.com/get-docker/) - Execução de aplicativos de containers.
 
## Instalação

 - Clone o projeto: `$git clone https://github.com/muriloalvesdev/luizalabs-desafio.git`
 - Após concluir o Download do projeto, acesse o diretório do mesmo: `$cd luizalabs-desafio/`
 `OBS: Sobre a instalação existem duas formas, abaixo está o passo a passo, sobre como realizar este procedimento. Mas caso você queira instalar rapidamente o      projeto, basta executar o script contido na raiz do projeto chamado da seguinte maneira $./docker-run.sh`
 
### Instando dependências e executando os testes com Maven:

- Para instalar as dependências e executar os testes, utilize o comando: `$mvn clean package`

### Gerando uma versão de PROD:
- Para gerar uma versão de `prod` do projeto utilize o comando: `$mvn clean install -DskipTests -Pprod`

## Docker - Construindo a aplicação
- Execute o comando `$docker build -t muriloalvesdev/schedule .` para construir a aplicação.
- E por fim, execute `$docker-compose up` para executar a aplicação.
`Agora você terá a aplicação rodando perfeitamente`

`OBS: O projeto é executado na porta 8080 e o banco de dados é executado na porta 5432, verifique se essas portas estão disponíveis caso ocorra algum erro. Tenha certeza de que você está com o Docker e o Maven instalados para que você não tenha problemas ao compilar, testar e executar a aplicação.`

### Ainda com Docker - Deixei a imagem disponível para Download no [DockerHub](https://hub.docker.com/u/muriloalvesdev)
Caso você queira apenas baixar a imagem e executar a aplicação, basta seguir os passos abaixo:
`OBS: Você precisa ter o PostgreSQL pré instalado com um database chamado "schedule_labs" .`

- Baixe a imagem Docker, utilizando o comando: `$docker pull muriloalvesdev/schedule`
- Execute a imagem Docker, utilizando o comando: `$docker run <id_imagem>`

`Agora você terá a aplicação rodando perfeitamente.`


## As APIs

Você pode visualizar as APIs utilizando o Swagger através desta URL: http://localhost:8080/swagger-ui.html#/

### Descrição das APIs
- Enviar uma solicitação de agendamento - Utilizando o verbo POST - http://localhost:8080/api/schedule/ - Com o body abaixo:

`{
	"send_date": "2020-11-01 23:59:59",
	"recipient": {
		"recipient": "murilo.alves@gmail.com"
	},
	"message": "Mensagem de teste",
	"type": "EMAIL"
}`
`OBS: os TYPES disponíveis são SMS, EMAIL, PUSH e WHATSAPP`

Existem validações, ou seja, se o campo `recipient` for um e-mail independente do que for informado no campo `type` a aplicação vai salvar o `type` como `EMAIL` e caso o `type` diferente de um `EMAIL` ou `PHONE CELULAR` retornamos uma exceção informando que o `type` é inválido.

Exemplo de uma resposta após salvar uma solicitação de agendamento:
`Header - Location: http://localhost:8080/api/schedule/PENDING
Content-type: application/json
Date: Fri, 11 Sep 2020 10:52:12 GMT`

E no body: `"23ba1ab7-6384-48c6-b6e4-4947ce5f749b"`

- Buscar solicitações de agendamento por status - Utilizando o verbo GET - http://localhost:8080/api/schedule/{status} - Status disponíveis para consulta são `PENDING, DELETED, SENT` . A busca por status está paginada com o [Pageable do próprio Spring](https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/domain/Pageable.html).

- Deletar uma solicitação de agendamento por `UUID` - Utilizando o verbo DELETE - http://localhost:8080/api/schedule/{uuid} - O `UUID` é devolvido após realizar a solicitação de agendamento.

 
## Heroku
Caso você queira somente realizar requisições na aplicação, o projeto também está no Heroku.
`A URL Base é: http://luizalabs-desafio.herokuapp.com/ `
- Utilize a URL BASE e você só precisa passar os parâmetros ou o body para enviar uma solicitação de agendamento.
