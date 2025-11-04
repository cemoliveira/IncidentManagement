CREATE TABLE usuarios(
        id BIGINT NOT NULL AUTO_INCREMENT,
        nome VARCHAR(100) NOT NULL,
        login VARCHAR(100) NOT NULL,
        senha VARCHAR(255) NOT NULL,
        perfil VARCHAR(20) NOT NULL,

        CONSTRAINT pk_usuarios PRIMARY KEY(id),
        CONSTRAINT uk_usuarios_login UNIQUE(login)
);

INSERT INTO usuarios (id, nome, login, senha, perfil)
VALUES (
        1,
        'administrador',
        'admin',
        '$2a$10$EMtF5r3SmAvZ0hgP82jsM.febY/jphFgxhHM86v0Ip/ZNbX29RB.W',
        'ADMINISTRATIVO'
);