CREATE TABLE PAUTAS (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    TITULO VARCHAR(50) NOT NULL,
    DESCRICAO VARCHAR(500) NOT NULL,
    DATA_CRIACAO TIMESTAMP NOT NULL,
    DATA_ABERTURA TIMESTAMP,
    DATA_FECHAMENTO TIMESTAMP,
    ABERTA_VOTACAO TINYINT(1) NOT NULL,
    CONSTRAINT CHK_ABERTA_VOTACAO CHECK (ABERTA_VOTACAO IN (0, 1))
);