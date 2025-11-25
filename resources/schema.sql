-- Tabela principal da Startup
CREATE TABLE IF NOT EXISTS startup (
    id IDENTITY PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    caixa DOUBLE NOT NULL,
    receita_base DOUBLE NOT NULL,
    reputacao INT NOT NULL,
    moral INT NOT NULL,
    score_final DOUBLE NOT NULL,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Cada linha representa uma rodada jogada pela startup
CREATE TABLE IF NOT EXISTS rodada (
    id IDENTITY PRIMARY KEY,
    startup_id BIGINT NOT NULL,
    numero_da_rodada INT NOT NULL,
    caixa DOUBLE NOT NULL,
    receita_base DOUBLE NOT NULL,
    reputacao INT NOT NULL,
    moral INT NOT NULL,
    FOREIGN KEY (startup_id) REFERENCES startup(id)
);

-- Cada linha representa UMA decisão tomada em UMA rodada
CREATE TABLE IF NOT EXISTS decisao_aplicada (
    id IDENTITY PRIMARY KEY,
    startup_id BIGINT NOT NULL,
    rodada_id BIGINT NOT NULL,
    nome_decisao VARCHAR(100) NOT NULL,
    caixa_delta DOUBLE NOT NULL,
    reputacao_delta INT NOT NULL,
    moral_delta INT NOT NULL,
    receita_bonus_delta DOUBLE NOT NULL,

    FOREIGN KEY (startup_id) REFERENCES startup(id),
    FOREIGN KEY (rodada_id) REFERENCES rodada(id)
);
