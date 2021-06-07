# TODO-LIST API – API para controle de tarefas

Criar uma RESTFUL API simples em Java que armazene e atualize tarefas (TODO-LIST API)


### REQUISITOS FUNCIONAIS

* Deve haver uma base de usuários pré-cadastrados (não precisa implementar a funcionalidade de cadastro)
* Somente usuários cadastrados nessa base podem incluir tarefas
* Cada tarefa deve possuir minimamente os dados:
>
>1. ID do usuário
>
>2. data/hora da inclusão
>
>3. resumo da tarefa
>
>4. descrição da tarefa
>
>5. status (pending ou completed)
>
>6. data/hora da alteração (quando houver mudança de status)
>
> 7. Tarefas criadas por um usuário não podem ser vistas por outros usuários
>
>8. Cada usuário deve poder listar tarefas com filtro por status
>
>9.  Se o usuário listar todas as tarefas, deve priorizar (no topo da listagem) tarefas com status "pending"
>
>9. __Plus:__ Deve haver um usuário com perfil de "super user" e somente ele poderá ver todas as tarefas de todos os usuários



### REQUISITOS NÃO FUNCIONAIS

Toda ação do usuário sobre a lista de tarefas deve ser autenticada (acesso somente com token válido)

Campos de data/hora devem ser exibidos na API no formato ISO (yyyy-MM-ddTHH:mm:ss)

__Plus:__ A senha / o secret não deve ficar exposto(a) na base de cadastro

__Plus:__ deve haver autorização para permitir somente o “super user” de ver todas as tarefas


### INFORMAÇÕES SOBRE A API

·         Deve existir uma rota de autenticação e devolver um token a expirar em 5min (POST /auth) - avaliar soluções persistência da sessão em cache

·         Deve persistir os dados uma base de sua escolha (Relacional / NoSQL) - explicar sobre a abordagem escolhida

·         Fornecer as rotas e verbos HTTP adequados para cada função / requisito funcional (ex: GET, PUT, POST, DELETE /todo)

·         Deve validar sessão (token) do usuário em todas as rotas (exceto na de autenticação)

·         Deve manter log/trace das ações executadas (pode utilizar componentes de terceiros)

·         Deve conter testes unitários (JUnit ou similar)

·         Plus: Disponibilizar uma rota para fornecer dados sobre a saúde dos seus componentes (GET /healthcheck) (pode utilizar componentes de terceiros)

·         Plus: Disponibilizar uma rota para indicadores de performance da API (ex: volume de requisições atendidas, tempo médio de serviço em milisegundos, etc) (GET /metrics) (pode utilizar componentes de terceiros)

OBS: não é necessário desenvolver o Frontend para input dos dados na API





### ENTREGA



A entrega da API deve ser disponibilizada no github.com em um repositório público. Escreva um breve arquivo README.md contendo informações da API, exemplos de uso (preferencialmente usando CURL), e como podemos rodar a API (incluindo recursos acoplados: DB, cache, etc.) via linha de comando para avaliação.





### DIFERENCIAL



Serão considerados como um diferencial na avaliação:



·         Documentação do código

·         Testes unitários com cobertura de no mínimo 80%

·         Testes de carga (JMeter ou similar)

·         Testes automatizados (linha de comando ou alguma plataforma de sua escolha)

·         Alta disponibilidade, escalabilidade horizontal, estratégia de deployment em containers ou Cloud publica
