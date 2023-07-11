# AV2-4MOD
Projeto de controle de Estadias na linguagem Java utilizado para a avaliação de Modelagem

Código para criação da tabela do código:
-- Table: public.estadia

-- DROP TABLE public.estadia;

CREATE TABLE public.estadia
(
  id integer NOT NULL DEFAULT nextval('estadia_id_seq'::regclass),
  nome character varying(30),
  data date,
  quarto integer,
  CONSTRAINT id PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.estadia
  OWNER TO postgres;
