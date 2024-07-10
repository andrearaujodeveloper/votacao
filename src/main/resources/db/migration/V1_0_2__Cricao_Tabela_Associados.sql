CREATE TABLE ASSOCIADOS (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    NOME VARCHAR(50) NOT NULL,
    CPF VARCHAR(50) NOT NULL,
    EMAIL VARCHAR(50) NOT NULL,
    ATIVO TINYINT(1) NOT NULL,
    CONSTRAINT CHK_ATIVO CHECK (ATIVO IN (0, 1))
);