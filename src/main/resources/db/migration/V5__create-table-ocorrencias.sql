CREATE TABLE ocorrencias(
        id BIGINT NOT NULL AUTO_INCREMENT,
        id_turma BIGINT,
        id_aluno BIGINT,
        data_registro DATETIME NOT NULL,
        categoria VARCHAR(20) NOT NULL,
        tipo VARCHAR(100) NOT NULL,
        descricao VARCHAR(400) NOT NULL,
        status VARCHAR(100) NOT NULL,
        data_atualizacao DATETIME,

        CONSTRAINT pk_ocorrencias PRIMARY KEY(id),
        CONSTRAINT fk_ocorrencias_id_turma FOREIGN KEY(id_turma) REFERENCES turmas(id),
        CONSTRAINT fk_ocorrencias_id_aluno FOREIGN KEY(id_aluno) REFERENCES alunos(id)
);