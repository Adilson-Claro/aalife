-- CRIA TABELA USUARIO
CREATE TABLE usuario (
    id              INT NOT NULL AUTO_INCREMENT,
    email           VARCHAR(255) NOT NULL UNIQUE,
    senha           VARCHAR(255) NOT NULL,
    role            VARCHAR(50) NOT NULL,
    situacao        VARCHAR(50) NOT NULL,
    nome            VARCHAR(255) NOT NULL,
    altura          DECIMAL(5,2) DEFAULT NULL,
    peso            DECIMAL(5,2) DEFAULT NULL,
    idade           INT DEFAULT NULL,
    numero_conselho INT DEFAULT NULL UNIQUE,
    cpf             VARCHAR(11) UNIQUE,
    tipo_conselho   VARCHAR(100) DEFAULT NULL,
    tipo_usuario    VARCHAR(50) NOT NULL,

    PRIMARY KEY (id)
);