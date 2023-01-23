DROP TABLE IF EXISTS action;
DROP TABLE IF EXISTS account;


CREATE TABLE IF NOT EXISTS account
(
    id bigint NOT NULL,
    balance numeric(19,2) NOT NULL,
    num_account VARCHAR(100) NOT NULL UNIQUE,
    CONSTRAINT account_pkey PRIMARY KEY (id)
) ;


CREATE TABLE IF NOT EXISTS action
(
    id_action bigint NOT NULL,
    amount numeric(19,2) NOT NULL,
    balance numeric(19,2) NOT NULL,
    date_effect timestamp without time zone NOT NULL,
    operation character varying(128) COLLATE pg_catalog."default" NOT NULL,
    account_id bigint,
    CONSTRAINT action_pkey PRIMARY KEY (id_action),
    CONSTRAINT fkej1q9wxsh4xrj5j23cwykmr75 FOREIGN KEY (account_id)
        REFERENCES public.account (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
