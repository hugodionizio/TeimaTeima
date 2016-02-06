-- Geração de Modelo físico
-- Sql ANSI 2003 - brModelo.

CREATE TABLE Loteria (
id_loteria int PRIMARY KEY,
dezenaConcurso int
);

CREATE TABLE Sorteios (
data date,
id_sorteio int PRIMARY KEY,
sorteio int[5],
id_loteria,
FOREIGN KEY(id_loteria) REFERENCES Loteria (id_loteria)
);

CREATE TABLE Vendas (
id_vendedor int PRIMARY KEY,
nomeVendedor varchar(50),
idade integer
);

CREATE TABLE Nomes (
id_concorrente int PRIMARY KEY,
email varchar(50),
endereco varchar(50),
fone varchar(15),
idade int,
nomeConcorrente varchar(50),
referencia varchar(20)
);

CREATE TABLE Acertos (
tipo varchar(10),
id_acerto int PRIMARY KEY,
numeroAcertos int,
id_concorrente int,
id_sorteio int,
FOREIGN KEY(id_concorrente) REFERENCES Nomes (id_concorrente),
FOREIGN KEY(id_sorteio) REFERENCES Sorteios (id_sorteio)
);

CREATE TABLE Palpites (
id_palpite int PRIMARY KEY,
palpite int[5],
id_vendedor int,
id_concorrente int,
FOREIGN KEY(id_vendedor) REFERENCES Vendas (id_vendedor)/*falha: chave estrangeira*/
);

