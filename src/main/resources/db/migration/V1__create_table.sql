-- Tabela: proprietario
CREATE TABLE IF NOT EXISTS proprietario (
                                            idproprietario integer PRIMARY KEY,
                                            nome character varying(45) NOT NULL,
    sobrenome character varying(45) NOT NULL,
    pessoa_fj character(1) NOT NULL,
    cnpj_cpf character varying(14) NOT NULL,
    email character varying(45),
    telefone character varying(16)
    );

-- Tabela: grupo
CREATE TABLE IF NOT EXISTS grupo (
                                     idgrupo integer PRIMARY KEY,
                                     nome character varying(45) NOT NULL,
    codigo character varying(45) NOT NULL UNIQUE,
    senha character varying(45) NOT NULL,
    imagem_base64 oid
    );

-- Tabela: endereco
CREATE TABLE IF NOT EXISTS endereco (
                                        idendereco bigint PRIMARY KEY,
                                        bairro character varying(45) NOT NULL,
    municipio character varying(45) NOT NULL,
    numero integer NOT NULL,
    rua character varying(45) NOT NULL,
    loteamento character varying(45) NOT NULL
    );

-- Tabela: imovel
CREATE TABLE IF NOT EXISTS imovel (
                                      idimovel bigint PRIMARY KEY,
                                      nome character varying(45) NOT NULL,
    data_cadastro date NOT NULL,
    area double precision NOT NULL,
    preco double precision NOT NULL,
    idproprietario bigint NOT NULL,
    idgrupo bigint NOT NULL,
    idendereco bigint NOT NULL,
    imagem_base64 oid,
    valormatricula double precision,
    FOREIGN KEY (idproprietario) REFERENCES proprietario(idproprietario),
    FOREIGN KEY (idgrupo) REFERENCES grupo(idgrupo),
    FOREIGN KEY (idendereco) REFERENCES endereco(idendereco)
    );

-- Tabela: gestor
CREATE TABLE IF NOT EXISTS gestor (
                                      idgestor integer PRIMARY KEY,
                                      nome character varying(45) NOT NULL,
    sobrenome character varying(45) NOT NULL,
    email character varying(100),
    telefone character varying(16),
    cnpj_cpf character varying(14) NOT NULL,
    senha character varying(45) NOT NULL,
    pessoa_fj character(1) NOT NULL
    );

-- Tabela: gestor_x_grupo (tabela de associação)
CREATE TABLE IF NOT EXISTS gestor_x_grupo (
                                              idgestor integer NOT NULL,
                                              idgrupo integer NOT NULL,
                                              PRIMARY KEY (idgestor, idgrupo),
    FOREIGN KEY (idgestor) REFERENCES gestor(idgestor),
    FOREIGN KEY (idgrupo) REFERENCES grupo(idgrupo)
    );

-- Tabela: avaliacao
CREATE TABLE IF NOT EXISTS avaliacao (
                                         idavaliacao integer PRIMARY KEY,
                                         valor double precision NOT NULL,
                                         cnpjavaliador character varying(14) NOT NULL,
    idimovel integer NOT NULL,
    data date NOT NULL,
    razaosocialavaliador character varying(130),
    pdf_base64 oid,
    FOREIGN KEY (idimovel) REFERENCES imovel(idimovel)
    );

-- Tabela: despesa
CREATE TABLE IF NOT EXISTS despesa (
                                       iddespesa bigint PRIMARY KEY,
                                       manutencao double precision,
                                       despesaavulsa double precision,
                                       data date,
                                       idimovel bigint NOT NULL,
                                       agua double precision,
                                       luz double precision,
                                       FOREIGN KEY (idimovel) REFERENCES imovel(idimovel)
    );

-- Tabela: receita
CREATE TABLE IF NOT EXISTS receita (
                                       idreceita bigint PRIMARY KEY,
                                       aluguel double precision,
                                       receitaavulsa double precision,
                                       data date,
                                       idimovel bigint NOT NULL,
                                       FOREIGN KEY (idimovel) REFERENCES imovel(idimovel)
    );

-- Tabela: iptu
CREATE TABLE IF NOT EXISTS iptu (
                                    idiptu integer PRIMARY KEY,
                                    valor double precision,
                                    data date,
                                    idimovel bigint NOT NULL,
                                    FOREIGN KEY (idimovel) REFERENCES imovel(idimovel)
    );

-- Tabela: ir
CREATE TABLE IF NOT EXISTS ir (
                                  idir integer PRIMARY KEY,
                                  ir double precision,
                                  data date,
                                  idimovel integer NOT NULL,
                                  FOREIGN KEY (idimovel) REFERENCES imovel(idimovel)
    );

-- Tabela: incc
CREATE TABLE IF NOT EXISTS incc (
                                    idIncc integer PRIMARY KEY,
                                    porcentagem double precision,
                                    data date
);