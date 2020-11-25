# 🚚 Trackr

[![Build Status](https://dev.azure.com/estouro-de-pilha/trackr/_apis/build/status/Backend?branchName=main)](https://dev.azure.com/estouro-de-pilha/trackr/_build/latest?definitionId=3&branchName=main) ![Azure Static Web Apps CI/CD](https://github.com/Jefh-Graduacao/dev3-trackr/workflows/Azure%20Static%20Web%20Apps%20CI/CD/badge.svg)

## Ferramentas para desenvolvimento

| Aplicação | Link para download |
| :--- | :--- |
| Docker Desktop | https://www.docker.com/products/docker-desktop |
| IntelliJ IDEA | https://www.jetbrains.com/idea/ |
| JDK 14         | https://www.oracle.com/java/technologies/javase/jdk14-archive-downloads.html |
| GIT | https://git-scm.com/downloads |
| NodeJS | https://nodejs.org/en/ |
| Yarn | https://yarnpkg.com/ |

### Setup Frontend

Para desenvolvimento _frontend_ é necessário instalar o [Vue CLI](https://cli.vuejs.org/) e alguns plugins. Para isso, execute o comando abaixo:

```powershell
npm install -g @vue/cli @vue/cli-service-global

# ou

yarn global add @vue/cli @vue/cli-service-global
```

Para instalar as dependências da aplicação, navegue até a pasta `frontend` e execute:

```powershell
yarn
```

E, depois, para subir a aplicação, execute:

```powershell
yarn serve
```

A saída deve ser algo parecido com

```none
DONE Compiled successfully in 7353ms
          
No type errors found
Version: typescript 3.9.7
Time: 3179ms

  App running at:
  - Local:   http://localhost:8081/
  - Network: http://192.168.100.4:8081/

  Note that the development build is not optimized.
  To create a production build, run yarn build.
```


## Infraestrutura 

Atualmente o _backend_ usa um [banco de dados em memória embutido](https://www.h2database.com/html/main.html), portanto, o setup da infraestrutura não é necessário para desenvolvimento.

A infraestrutura para desenvolvimento _backend_ (banco de dados) pode ser gerenciada (inicializada, parada e excluída) usando o Docker. Para isto, navegue até a pasta `src/backend` e execute os comandos abaixo:

* Inicializar a infraestrutura (banco de dados e adminer)

    ```powershell
    docker-compose -f .\docker\compose-infra.yml up -d
    ``` 

* Finalizar a infraestrutura

    ```powershell
    docker-compose -f .\docker\compose-infra.yml down
    ```

* Executar a aplicação como container

    ```powershell
    docker-compose -f .\docker\compose-infra.yml -f .\docker\compose-backend.yml up -d --build
    ```
    
# Como contribuir
    A Estrutura do backend foi pensada para possibilitar facilmente a expansão de novas origem de dados, possuimos suporte para origens de dados que consumam as chaves de 'Código de rastreio' e 'Número de documento', sendo que cada um possui um coportamento próprio.
    ##Por Código: 
          O front adiciona essa nova opção como chave composta nas pesquisas e representa uma busca unitária por encomentas.O código pode ser posteriormete vinculado ao documento para que seja consultado também quando houver consultas em lote por documento.
    ##Por Documento: 
          Nessa opção serão consultados todas as origens de dado por documento para que seja montado o lote de encomendas que será inclído no dashboard.
    
    Para submeter a implementação da nova origem basta que seja enviado o Pull Request seguindo os seguintes passos:
    
    * Primeiramente pensando na nova origem de dados, precisa-se definir se ela é consumida através de um código de rastreio ou número de documento, em seguida a nova origem de dados deve ser incluida no respectivo **enum**: **TipoCrawlerPorCodigoEnum** ou **TipoCrawlerPorDocumentoEnum**;
    * Após isso, no diretório **crawlers** deve ser criado um novo **service** para a nova origem, toda implementação específica deverá ficar nesse path. O novo **service**
    criado deve implementar a interface correspondente do tipo de origem: **CrawlerCodigo** ou **CrawlerDocumento**;
    * Existe também um **service** para orquestrar as origens conforme a entrada, que chamaremos de **orquestrador**, nele deve-se implementar o mapeamento do **enum** de entrada para o **service** criado na etapa anterior, para isso será necessário adicionar o novo **service** como dependencia do **orquestrador** e então realizar o tratamento no **switch** para a nova origem de dados direcionando para o **service** específico, os **orquestradores** específicos são: **CrawlerCodigoService** ou **CrawlerDocumentoService**.

    ##Mais
    
    * Apenas serão aceitas contribuições conforme os padrões de qualidade de código, incluindo **testes unitários** e práticas de **clean code**;
    * Sobre a implementação dos crawlers, utilizamos a biblioteca Jsoup para extrair os dados, mas sinta-se a vontade para utilizar outras.
    
# Outras funcionalidades para contribuição
    * Expurgo de rastreios **por código** vinculados ao **documento**. Quando é realizado um vinculo do código de rastreio para o documento, toda vez que o documento for consultado esse rastreio vinculado **por código** será incluido na resposta, mas algum tempo após a entrega do mesmo a informação perde o valor e apenas polui o dashboard;
