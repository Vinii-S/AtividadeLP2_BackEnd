SCRIPT CIRAÇÃO BANCO:
create database if not exists projeto_demo;
use projeto_demo;

create table if not exists usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    login varchar(100) not null unique,
    senha varchar(100) not null,
    create_at timestamp DEFAULT CURRENT_TIMESTAMP,
    last_login timestamp null
);

CREATE TABLE if not exists livro (
    id_livro INT AUTO_INCREMENT PRIMARY KEY,  -- Identificador único do livro
    titulo VARCHAR(255) NOT NULL,       -- Título do livro
    autor VARCHAR(255) NOT NULL,        -- Autor do livro
    editora VARCHAR(255),               -- Editora do livro
    ano_publicacao YEAR,                -- Ano de publicação
    id_genero int,          -- Gênero do livro (ficção, fantasia, etc.)
    isbn VARCHAR(20),                   -- ISBN do livro (pode ser um valor único)
    num_paginas INT,                    -- Número de páginas
    sinopse TEXT,                       -- Sinopse do livro
    idioma VARCHAR(50),                 -- Idioma do livro
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- Data de cadastro do livro
    preco DECIMAL(10, 2),
    constraint fk_id_genero foreign key (id_genero)
    references genero(id_genero)
);
create table if not exists genero(
	id_genero int auto_increment primary key not null,
    nome_genero varchar(50),
    status bit
);
CREATE TABLE genero ( id_genero INT AUTO_INCREMENT PRIMARY KEY, nome_genero VARCHAR(45) NOT NULL, status BIT DEFAULT 1 );

CREATE TABLE livro ( id_livro INT AUTO_INCREMENT PRIMARY KEY, titulo VARCHAR(255) NOT NULL, autor VARCHAR(255) NOT NULL, editora VARCHAR(255) NOT NULL, ano_publicacao YEAR NOT NULL,
 isbn VARCHAR(20) UNIQUE NOT NULL, num_paginas INT(11) NOT NULL, sinopse TEXT, idioma VARCHAR(50) NOT NULL, data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 preco DECIMAL(10,2) NOT NULL, id_genero INT,
constraint fk_id_genero foreign key (id_genero)
    references genero(id_genero) ON DELETE SET NULL ON UPDATE CASCADE );

INSERT INTO `projeto_demo`.`usuario`(`login`,`senha`,`last_login`)
VALUES("jooj","555","2024-11-25 10:22:29");
INSERT INTO `projeto_demo`.`usuario`(`login`,`senha`,`last_login`)
VALUES("luiza","123123","2024-11-26 14:33:09");
INSERT INTO `projeto_demo`.`usuario`(`login`,`senha`,`last_login`)
VALUES("ronaldo","aaaa111","2023-12-12 06:55:26");
INSERT INTO `projeto_demo`.`usuario`(`login`,`senha`)
VALUES("vinicinho","55555");