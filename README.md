# Tweets Mood Analyzer

### Overview:

- O projeto foi feito usando a linguagem kotlin, Clean Architecture com MVVM e modularização.
- Utilizei a biblioteca Koin para injeção de dependência;
- Para chamadas assíncronas utilizei o Kotlin Coroutines, abstraido tanto para os use cases como para as viewmodels.
- Foram aplicados testes unitários nos use cases e nas viewmodels, e um helper que verifica a pontuação para parsear em emoji. Testes de UI não foram aplicados pois ainda é algo que estou trabalhando :pray:

### Como buildar:

- Para buildar o projeto, é necessário inserir o tokens/chaves das APIs do Twitter e do Google Natural Language no arquivo `gradle.properties`, dentro das respectivas variáveis. 
- Obs: Caso após inserir as chaves, se ainda assim houver algum erro de autenticação, sugir deletar a pasta build do módulo data, e rodar novamente.

