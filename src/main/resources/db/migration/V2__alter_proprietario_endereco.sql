alter table endereco
    add estado varchar(2);

alter table endereco
    add CEP varchar(8);

alter table imovel
    add numero_matricula varchar(15);

ALTER TABLE endereco
    RENAME COLUMN municipio TO cidade;

ALTER TABLE imovel
    RENAME COLUMN valormatricula TO valor_matricula;

ALTER TABLE endereco
	DROP COLUMN bairro;