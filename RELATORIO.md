# 📘 RELATÓRIO – Startup Game (Refatoração – POO Avançado)

Este documento descreve a implementação do projeto **Startup Game**, desenvolvido como atividade de refatoração para a disciplina de **POO Avançado**.  
O projeto segue a risca os requisitos do enunciado, aplicando boas práticas de programação, arquitetura por camadas, padrões de projeto e persistência com banco de dados H2.

---

# 1. Arquitetura Geral da Aplicação

A aplicação segue uma arquitetura por camadas:

- **UI (interface)** → interação via console  
  - `ConsoleApp.java`
  - `Main.java`

- **Engine (lógica de jogo)**  
  - `GameEngine.java`
  - `ScoreService.java`

- **Actions (padrão Strategy)**  
  Cada decisão é uma Strategy independente:
  - Marketing
  - Equipe
  - Produto
  - Investidores
  - Cortar Custos

- **Model (entidades e regras de domínio)**  
  - `Startup.java`
  - `Deltas.java`
  - VO (`Dinheiro`, `Humor`, `Percentual`)  
    *Value Objects imutáveis com validações internas.*

- **Persistence (DAO / Repositórios)**  
  - `DataSourceProvider.java`
  - `StartupRepository.java`
  - `RodadaRepository.java`
  - `DecisaoAplicadaRepository.java`

- **Config**  
  - `Config.java` (lê game.properties)

---

# 🧠 2. Itens Obrigatórios Implementados

| Requisito | Implementado? | Local |
|----------|--------------|-------|
| Padrão Strategy para decisões  - pasta `actions/` |
| Camada Engine controlando rodadas e decisões |  | `GameEngine.java` |
| VO imutáveis com validação (`Dinheiro`, `Humor`) |  | `model/vo` |
| Arquivo de propriedades |  | `resources/game.properties` |
| Banco de dados H2 - `lib/h2-2.2.224.jar` + `data/game.mv.db` |
| Uso de JDBC e DAO - pasta `persistence/` |
| Script SQL com criação de tabelas - `resources/schema.sql` |
| Persistência de Startup, Rodada e Decisão - `StartupRepository`, `RodadaRepository`, `DecisaoAplicadaRepository` |
| Ranking final baseado no Score - `StartupRepository.listarRanking()` |
| Controle de versão com Git - GitHub |
| README.md completo - raiz do projeto |
| RELATORIO.md - este documento |

---

---

# 🏗️ 4. Estrutura do Banco (H2)

A aplicação utiliza um banco H2 em modo **arquivo**, sempre salvo em:

