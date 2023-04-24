# Desafio API Grupo 5
Para este desafio desenvolvemos diversos End Points para um sistema de atendimento veterinário. esse sistema considera somente o atendimento de cachorros. O sistema foi criado de modo a fornecer End Points para: 

- CRUD completo para clientes
- CRUD completo para médicos veterinários 
- CRUD completo para o cachorro (Obs. Cada animal esta associado a um cliente) 
- Registro de dados do atendimento: veterinário que está atendendo, tutor, animal em atendimento, data, hora, dados do animal no dia, diagnóstico, comentários. 
- Autenticação do usuário (Ha tres categorias de autenticacoes sendo elas cliente, admin e veterinario). 
- Banco de dados e populado no momento em que a aplicacao e iniciada.

O projeto foi criado no VS Code/Spring Tool Suit junto com MySQL. Tanto o usuario como a senha do banco e "root". Favor, configurar com seu banco antes de rodar o projeto. 
O email de administrador e "admin@gft.com" e a senha e "gft@1234". As senhas dos outros usuarios e "123456". Podera consultar no banco o email para cada um deles para fazer o log in. As senhas no banco de dados foram encriptografadas. 
Fizemos tambem os testes de rotas e TDD. 
Para fazer as buscas do getall, favor, apagar o campo string no json, e alterar o size para 10.

## IMPLEMENTACAO SWAGGER

Para acessar a documentação via swagger, basta rodar o projeto e acessar:

**http://localhost:8080/swagger-ui.html**

## IMPLEMENTACAO HATEOAS

Nas solicitacoes de informacoes de animais, inserimos um link via HATEOAS para apresentacao de mais informacoes. 

## IMPLEMENTACAO SPRING SECURITY

Todos os usuarios estao cadastrados dentro uma role, onde criamos um limite do que cada um pode fazer dentro do projeto. O unico usuario com acesso a todos os itens da aplicacao e o administrador. 

## EXCEEDS IMPLEMENTADOS 

1. Fizemos o cadastro  do  animal de tal forma que e possivel recuperar dados da raça buscando na API fornecida pelo link no final da descrição da atividade. 

2. O cliente não consegue acessar a API diretamente, ele acessa via End Point, e seu End Poit acessa a API e esta passa os dados para o cliente. 

3. O histórico dos atendimentos pode ser acessado tanto pelo cliente quanto veterinário. O cliente tem acesso somente aos dados das consultas de seus animais. Os veterinários por sua vez, podem ver o histórico de qualquer consulta e animal. 

4. Implementacao do Swagger

5. Test Driven Development - Criados mais de 40 testes.  


## DIAGRAMA UML QUE USAMOS EM NOSSO BANCO DE DADOS

![Captura_de_tela_2022-07-28_132207](/uploads/e2c4c994b5b01c66f3e4cd3eb8d682ff/Captura_de_tela_2022-07-28_132207.png)




