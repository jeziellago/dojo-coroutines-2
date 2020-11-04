# Dojo Coroutines - II
### Desafio
O desafio desse dojo será consultar a api do The Movie DB para listar filmes em cartaz e filmes populares.

Para cada filme precisamos exibir:
- Título
- Gênero
- Avaliação
- Imagem

A api possui paginação, então ao fim do scroll horizontal nós devemos buscar a próxima página.

Os dados podem estar no cache, então antes de fazer uma request devemos consultá-lo.

Novos dados também devem popular o cache.

### Regras gerais
- Respeitar o ciclo de vida do android
- Ser resiliente em caso de falha
- Não utilizar as extensions `lifecycleScope` e `viewModelScope`
