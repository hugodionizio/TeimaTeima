-- Geração de Modelo físico
-- Sql ANSI 2003 - brModelo.

					CREATE TABLE VENDAS( " +
                    "id_vendedor integer not null GENERATED ALWAYS AS " +
                    "IDENTITY (START WITH 1, INCREMENT BY 1) " +
                    "CONSTRAINT PK_VENDAS PRIMARY KEY, " +
                    "nomeVendedor varchar(50) not null, " +
                    "idade integer not null" +
                    ")
                    
					CREATE TABLE ACERTOS( " +
                    "id_acerto integer not null GENERATED ALWAYS AS " +
                    "IDENTITY (START WITH 1, INCREMENT BY 1) " +
                    "CONSTRAINT PK_ACERTOS PRIMARY KEY, " +
                    "tipoAcerto varchar(20) not null" +
                    "CONSTRAINT FK_NOMES FOREIGN KEY (nomes) " +
                    "REFERENCES NOMES (id_concorrente) " +
                    
                    CREATE TABLE NOMES( " +
                    "id_concorrente integer not null GENERATED ALWAYS AS " +
                    "IDENTITY (START WITH 1, INCREMENT BY 1) " +
                    "CONSTRAINT PK_NOMES PRIMARY KEY, " +
                    "nomeConcorrente varchar(50) not null, " +
                    "idade integer not null" +
                    
                    CREATE TABLE PALPITES( " +
                    "id_palpite integer not null GENERATED ALWAYS AS " +
                    "IDENTITY (START WITH 1, INCREMENT BY 1) " +
                    "CONSTRAINT PK_PALPITES PRIMARY KEY, " +
                    "palpite integer[5] not null, " +
                    "CONSTRAINT FK_NOMES FOREIGN KEY (nomes) " +
                    "REFERENCES NOMES (id_concorrente)
                    
                    
                    CREATE TABLE SORTEIOS( " +
                    "id_sorteio integer not null GENERATED ALWAYS AS " +
                    "IDENTITY (START WITH 1, INCREMENT BY 1) " +
                    "CONSTRAINT PK_SORTEIOS PRIMARY KEY, " +
                    "data date," + 
                    "dezena integer[5] not null " +