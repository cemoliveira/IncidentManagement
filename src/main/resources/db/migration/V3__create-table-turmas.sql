CREATE TABLE turmas(
        id BIGINT NOT NULL AUTO_INCREMENT,
        nome VARCHAR(100) NOT NULL,
        turno VARCHAR(10) NOT NULL,
        ano INT NOT NULL,
        semestre VARCHAR(10) NOT NULL,

        CONSTRAINT pk_turmas PRIMARY KEY(id)
);