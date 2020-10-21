CREATE TABLE IF NOT EXISTS RastreioDocumento(
    idRastreioDocumento NUMBER(38) NOT NULL,
    numeroDocumento     VARCHAR2(14) NOT NULL,
    codigoRastreio      VARCHAR2(50) NOT NULL,
    origem              VARCHAR2(14) NOT NULL,
    PRIMARY KEY(idRastreioDocumento)
);

--CREATE INDEX idxNumeroDocumento ON RastreioDocumento(numeroDocumento);
--COMMENT ON TABLE RastreioDocumento
--   IS 'Tabela para armazenar os códigos de rastreio vinculados ao número de documento.';