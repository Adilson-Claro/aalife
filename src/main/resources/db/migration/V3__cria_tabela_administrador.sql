CREATE TABLE usuario_administrador (
    id      INT         NOT NULL,
    cpf     VARCHAR(11) NOT NULL UNIQUE,
    PRIMARY KEY (id),

    CONSTRAINT fk_usuario_administrador FOREIGN KEY (id)
        REFERENCES usuario_base(id)
        ON DELETE CASCADE
);