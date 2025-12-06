-- TABELA BASE
CREATE TABLE usuario_base (
    id              INT NOT NULL AUTO_INCREMENT,
    email           VARCHAR(255) NOT NULL UNIQUE,
    senha           VARCHAR(255) NOT NULL,
    role            VARCHAR(50) NOT NULL,
    situacao        VARCHAR(50) NOT NULL,
    nome_exibicao   VARCHAR(255) NOT NULL,

    PRIMARY KEY (id)
);

-- TABELA PROFISSIONAL
CREATE TABLE usuario_profissional (
    id              INT NOT NULL,
    numero_conselho VARCHAR(50) NOT NULL UNIQUE,
    cpf             VARCHAR(11) NOT NULL UNIQUE,
    PRIMARY KEY (id),

    CONSTRAINT fk_usuario_profissional FOREIGN KEY (id)
        REFERENCES usuario_base(id)
        ON DELETE CASCADE
);