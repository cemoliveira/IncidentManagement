CREATE TABLE alunos(
        id BIGINT NOT NULL AUTO_INCREMENT,
        url_imagem VARCHAR(255),
        nome VARCHAR(100) NOT NULL,
        data_nascimento DATE NOT NULL,

        CONSTRAINT pk_alunos PRIMARY KEY(id)
);