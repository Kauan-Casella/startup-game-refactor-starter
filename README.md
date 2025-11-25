# 🚀 Startup Game – Projeto de Refatoração (POO Avançado)

Este repositório contém a implementação completa do **Startup Game**, refatorado seguindo o enunciado do projeto de POO Avançado.
O jogo simula a evolução de uma startup ao longo de várias rodadas, aplicando decisões estratégicas, atualizando valores e persistindo os resultados em **banco de dados H2**.

Este projeto utiliza:
- **Padrão Strategy** para decisões
- **Domain Objects (VO)**: `Dinheiro`, `Humor`, `Percentual`
- **Persistência com H2 (arquivo .mv.db)**
- **Repositórios DAO**
- **Console App**
- **Arquitetura limpa por camadas**
- **Configuração parametrizada via game.properties**


## 📁 Estrutura do Projeto

src/
config/Config.java
engine/GameEngine.java
engine/ScoreService.java
actions/
DecisaoStrategy.java
DecisaoFactory.java
[estratégias/*.java]
model/
Startup.java
Deltas.java
vo/
Dinheiro.java
Humor.java
Percentual.java
persistence/
DataSourceProvider.java
StartupRepository.java
RodadaRepository.java
DecisaoAplicadaRepository.java
ui/
ConsoleApp.java
Main.java

resources/
game.properties
schema.sql

lib/
h2-2.2.224.jar

out/ → arquivos compilados
data/ → banco de dados H2 (criado em runtime)


## 🔧 Configuração do Banco H2

O projeto usa um banco **H2 no modo arquivo**, salvo em:

data/game.mv.db

A URL de acesso é:

jdbc:h2:file:./data/game;AUTO_SERVER=TRUE


### ▶ Como abrir o banco no H2 Console

Rode o console:

```bash
java -jar lib/h2-2.2.224.jar

Acesse:
http://localhost:8082

Preencha:
JDBC URL

jdbc:h2:file:./data/game;AUTO_SERVER=TRUE
User: sa

Password: (vazio)

Copie o conteúdo de resources/schema.sql no editor SQL

Clique em Run para criar as tabelas:

startup

rodada

decisao_aplicada


 macOS-
javac -cp "lib/h2-2.2.224.jar" -d out $(find src -name "*.java")


Como Executar o Jogo
macOS-
java -cp ".:out:resources:lib/h2-2.2.224.jar" Main


Como Jogar

Escolha Criar novo jogo

Digite o nome da sua startup

A cada rodada deve-se escolher decisões (Marketing, Equipe, Produto, Investidores, Cortar Custos)

Cada decisão aplica um conjunto de deltas

Depois que os deltas são aplicados o Caixa, moral, reputação e receita são atualizados

O estado é gravado no banco H2

Ao final, o jogo mostra o Ranking de Startups com base no Score final


O cálculo está implementado em ScoreService.java e considera:

Caixa final

Receita base

Moral

Reputação

Multiplicadores por performance


Repositórios Implementados

StartupRepository

RodadaRepository

DecisaoAplicadaRepository
Todos utilizando JDBC puro + PreparedStatement.

Cada rodada e cada decisão da rodada são persistidas no banco.


Arquivo game.properties
total.rodadas=8
max.decisoes.por.rodada=3