ALTER TABLE avaliacao
    RENAME COLUMN cnpjavaliador TO cnpj;

ALTER TABLE avaliacao
    RENAME COLUMN data TO data_avaliacao;

ALTER TABLE avaliacao
    RENAME COLUMN valor TO valor_avaliacao;

ALTER TABLE avaliacao
    RENAME COLUMN razaosocialavaliador TO razao_social;

ALTER TABLE avaliacao
    RENAME COLUMN pdf_base64 TO doc_avaliacao;

ALTER TABLE avaliacao
ALTER COLUMN doc_avaliacao SET DATA TYPE text;

ALTER TABLE avaliacao
    ALTER COLUMN valor_avaliacao SET DATA TYPE double precision;