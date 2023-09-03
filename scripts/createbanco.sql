CREATE TABLE IF NOT exists tb_usuario(
id SERIAL PRIMARY KEY,
username VARCHAR(255) NOT NULL,
senha VARCHAR(255) NOT NULL);


CREATE table IF NOT exists  tb_pessoa(
        id 		SERIAL       PRIMARY KEY,
        cpf	 	 VARCHAR(14)  NOT NULL,
   	nome	 	 VARCHAR(255) NOT NULL,
   	telefone 	 VARCHAR(30) ,
   	email 	         VARCHAR(30)  NOT NULL,
   	senha 	         VARCHAR(255) NOT NULL,
   	dataNascimento    DATE       NOT NULL,
   	sexo 		  VARCHAR(9)  NOT NULL,
   	parentesco        VARCHAR(30)  NOT null,
   	idUsuario         BIGINT       NOT null,
   	FOREIGN KEY (idUsuario) REFERENCES tb_usuario (id) ON DELETE CASCADE
);


CREATE TABLE IF NOT exists  tb_endereco
(
    id 		 	 SERIAL       PRIMARY KEY,
    pais	     VARCHAR(255)  NOT NULL,
   	estado	 	 CHAR(2)       NOT NULL,
   	municipio 	 VARCHAR(255),
   	bairro 	     VARCHAR(255)  NOT NULL,
   	cep 	     VARCHAR(30)   NOT NULL,
   	complemento   VARCHAR(30),
	rua	     VARCHAR(255)  NOT NULL
);




CREATE TABLE IF NOT exists  tb_pessoa_endereco
(
    idPessoa     SERIAL NOT NULL,
	idEndereco   SERIAL NOT NULL,
	PRIMARY KEY (idPessoa, idEndereco),
	FOREIGN KEY (idPessoa) REFERENCES tb_pessoa (id) ON DELETE CASCADE,
	FOREIGN KEY (idEndereco) REFERENCES tb_endereco (id) ON DELETE CASCADE
 
);


CREATE TABLE IF NOT exists  tb_eletrodomestico
(
    	id 	 	         SERIAL       PRIMARY KEY,
    	nome	 	         VARCHAR(255) NOT NULL,
    	modelo	 	         VARCHAR(255) NOT NULL,
   	potencia 	 	INTEGER ,
   	serialNumber 	     	VARCHAR(30)  NOT NULL unique,
   	idEndereco   		 SERIAL 	  NOT null ,
    FOREIGN KEY (idEndereco) REFERENCES tb_endereco (id) ON DELETE CASCADE
);



	