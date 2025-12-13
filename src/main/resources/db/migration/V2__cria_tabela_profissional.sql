-- CRIA TABELA PROFISSIONAL
CREATE TABLE profissional (
    id              INT AUTO_INCREMENT PRIMARY KEY,
    nome            VARCHAR(150) NOT NULL,
    numero_conselho VARCHAR(50)  NOT NULL UNIQUE,
    cpf             VARCHAR(11)  UNIQUE,
    cnpj            VARCHAR(14)  UNIQUE,
    tipo_conselho   VARCHAR(30)  NOT NULL,
    telefone        VARCHAR(20)  NOT NULL UNIQUE,
    usuario_id      INT          NULL,

    CONSTRAINT fk_profissional_usuario
            FOREIGN KEY (usuario_id)
            REFERENCES usuario (id)
            ON DELETE SET NULL
);