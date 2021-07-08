CREATE TABLE editora(
	codigo INT PRIMARY KEY,
	nome VARCHAR(255)
);

CREATE TABLE livro(
	codigo INT PRIMARY KEY,
	obra VARCHAR(255) NOT NULL,
	edicao SMALLINT,
	ano DATE,
	numPaginas SMALLINT,
	idioma VARCHAR(255),
	pais VARCHAR(255),
	isbn VARCHAR(30),
	preco NUMERIC(6,2),
	editora_codigo INT,
	FOREIGN KEY (editora_codigo) REFERENCES editora (codigo)
);

CREATE TABLE genero(
	codigo INT PRIMARY KEY,
	codigo_livro INT,
	nome VARCHAR(255),
	FOREIGN KEY (codigo_livro) REFERENCES livro (codigo)
);

CREATE TABLE autor(
	codigo INT PRIMARY KEY,
	codigo_livro INT,
	nome VARCHAR(255),
	FOREIGN KEY (codigo_livro) REFERENCES livro (codigo)
);

CREATE TABLE estoque(
	codigo INT PRIMARY KEY,
	quantidade INT,
	codigo_livro INT,
	FOREIGN KEY (codigo_livro) REFERENCES livro (codigo)
);

INSERT INTO editora VALUES(0,'Rocco');
INSERT INTO editora VALUES(1,'LTC');

INSERT INTO livro VALUES(0,'Harry Potter: e a pedra filosofal',1,'19/08/2017',208,'BR','Reino Unido','978-8532530783',25.90,0);
INSERT INTO livro VALUES(1,'Uml - Guia do Usuario',1,'23/01/2006',552,'BR','EUA','9788535217841',205.18,1);

INSERT INTO genero VALUES(0,0,'Aventura');
INSERT INTO genero VALUES(1,1,'Computacao');
INSERT INTO genero VALUES(2,0,'Drama');
INSERT INTO genero VALUES(3,1,'Programacao');

INSERT INTO autor VALUES(0,0,'JK. Rowling');
INSERT INTO autor VALUES(1,1,'Grady Booch');
INSERT INTO autor VALUES(2,1,'James Rumbaugh');
INSERT INTO autor VALUES(3,1,'Ivar Jacobson');

INSERT INTO estoque VALUES(0,8,0);
INSERT INTO estoque VALUES(1,3,1);