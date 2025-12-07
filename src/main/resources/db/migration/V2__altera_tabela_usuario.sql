ALTER TABLE usuario_base
    ADD altura DECIMAL(5,2) DEFAULT NULL,
    ADD peso   DECIMAL(5,2) DEFAULT NULL,
    ADD idade  INT DEFAULT NULL;

ALTER TABLE usuario_profissional
    ADD tipo_conselho  VARCHAR(50) NOT NULL;
