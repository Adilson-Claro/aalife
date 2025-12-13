-- CRIA TABELA ADMINISTRADOR
CREATE TABLE administrador (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    cpf         VARCHAR(11) NOT NULL UNIQUE,
    telefone    VARCHAR(20) NOT NULL UNIQUE,
    usuario_id  INT         NOT NULL UNIQUE,
    nome        VARCHAR(150)NOT NULL,

    CONSTRAINT fk_administrador_usuario
        FOREIGN KEY (usuario_id)
        REFERENCES usuario (id)
        ON DELETE CASCADE
);