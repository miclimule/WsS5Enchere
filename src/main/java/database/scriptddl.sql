ALTER TABLE public.client ADD nom varchar(255) NOT NULL;

-- Drop table

-- DROP TABLE histo_enchere;

CREATE TABLE histo_enchere (
	idenchere int4 NULL,
	idgagnant int4 NULL,
	montantpayer float8 NULL,
	dateajout timestamp NULL DEFAULT now(),
	CONSTRAINT histo_enchere_idenchere_fkey FOREIGN KEY (idenchere) REFERENCES enchere(id),
	CONSTRAINT histo_enchere_idgagnant_fkey FOREIGN KEY (idgagnant) REFERENCES client(id)
);

ALTER TABLE public.enchere ADD description text NULL;

ALTER TABLE public.materielle ADD prixminimal int4 NULL;

ALTER TABLE public.materielle ADD idclient int4 NULL;

ALTER TABLE public.materielle ADD datedebut timestamp NULL DEFAULT now();

-- Drop table

-- DROP TABLE img_materille;

CREATE TABLE img_materille (
	idmaterielle int4 NULL,
	"ref" varchar(255) NULL,
	CONSTRAINT img_materille_idmaterielle_fkey FOREIGN KEY (idmaterielle) REFERENCES materielle(id)
);

ALTER TABLE public.historique ADD nomclient varchar(255) NULL;

ALTER TABLE public.materielle ADD id serial4 NOT NULL;

ALTER TABLE public.enchere ADD idmaterielle int4 NULL;

-- Drop table

-- DROP TABLE category;

CREATE TABLE category (
	id serial4 NOT NULL,
	"types" varchar(255) NULL,
	CONSTRAINT category_pkey PRIMARY KEY (id)
);

-- Drop table

-- DROP TABLE client;

CREATE TABLE client (
	id serial4 NOT NULL,
	nom varchar(255) NOT NULL,
	mdp varchar(255) NOT NULL,
	CONSTRAINT client_pkey PRIMARY KEY (id)
);

-- Drop table

-- DROP TABLE admins;

CREATE TABLE admins (
	id serial4 NOT NULL,
	nom varchar(255) NOT NULL,
	mdp varchar(255) NOT NULL,
	CONSTRAINT admins_pkey PRIMARY KEY (id)
);

ALTER TABLE public.count_enchere ADD idenchere int4 NULL;

ALTER TABLE public.histo_enchere ADD idgagnant int4 NULL;

CREATE OR REPLACE VIEW public.count_enchere
AS SELECT historique.idenchere,
    count(historique.idenchere) AS nbr
   FROM historique
  GROUP BY historique.idenchere;

ALTER TABLE public.solde_client ADD idclient int4 NULL;

ALTER TABLE public.solde_client ADD montant int4 NULL;

ALTER TABLE public.enchere ADD dureeenchere int4 NULL DEFAULT 0;

ALTER TABLE public.materielle ADD duree int4 NULL;

ALTER TABLE public."token" ADD value text NULL;

ALTER TABLE public.solde_client ADD isvalidate int4 NULL DEFAULT 0;

ALTER TABLE public.enchere ADD commision int4 NULL DEFAULT 0;

ALTER TABLE public.historique ADD prixminimal int4 NULL;

ALTER TABLE public.category ADD "types" varchar(255) NULL;

-- Drop table

-- DROP TABLE solde_client;

CREATE TABLE solde_client (
	montant int4 NULL,
	isvalidate int4 NULL DEFAULT 0,
	idclient int4 NULL,
	id serial4 NOT NULL,
	CONSTRAINT solde_client_pkey PRIMARY KEY (id),
	CONSTRAINT solde_client_idclient_fkey FOREIGN KEY (idclient) REFERENCES client(id)
);

ALTER TABLE public.enchere ADD qte int4 NULL DEFAULT 0;

-- Drop table

-- DROP TABLE "token";

CREATE TABLE "token" (
	value text NULL,
	dateajout timestamp NULL DEFAULT now(),
	idclient int4 NULL,
	CONSTRAINT token_value_key UNIQUE (value),
	CONSTRAINT token_idclient_fkey FOREIGN KEY (idclient) REFERENCES client(id)
);

CREATE OR REPLACE VIEW public.historique
AS SELECT e.id AS idenchere,
    m.nom AS nommaterielle,
    e.qte,
    m.prixminimal,
    c.nom AS nomclient,
    h.montantpayer
   FROM enchere e
     JOIN histo_enchere h ON e.id = h.idenchere
     JOIN materielle m ON m.id = e.idmaterielle
     JOIN client c ON c.id = m.idclient;

ALTER TABLE public.count_enchere ADD nbr int8 NULL;

ALTER TABLE public.admins ADD nom varchar(255) NOT NULL;

ALTER TABLE public.enchere ADD datedepartenchere timestamp NULL DEFAULT now();

ALTER TABLE public.client ADD mdp varchar(255) NOT NULL;

ALTER TABLE public.img_materille ADD "ref" varchar(255) NULL;

-- Drop table

-- DROP TABLE materielle;

CREATE TABLE materielle (
	nom varchar(255) NULL,
	prixminimal int4 NULL,
	datedebut timestamp NULL DEFAULT now(),
	duree int4 NULL,
	idcategory int4 NULL,
	id serial4 NOT NULL,
	idclient int4 NULL,
	CONSTRAINT materielle_pkey PRIMARY KEY (id),
	CONSTRAINT materielle_idcategory_fkey FOREIGN KEY (idcategory) REFERENCES category(id),
	CONSTRAINT materielle_idclient_fkey FOREIGN KEY (idclient) REFERENCES client(id)
);

ALTER TABLE public.histo_enchere ADD montantpayer float8 NULL;

ALTER TABLE public.historique ADD idenchere int4 NULL;

ALTER TABLE public.materielle ADD nom varchar(255) NULL;

ALTER TABLE public."token" ADD idclient int4 NULL;

ALTER TABLE public."token" ADD dateajout timestamp NULL DEFAULT now();

-- Drop table

-- DROP TABLE enchere;

CREATE TABLE enchere (
	id serial4 NOT NULL,
	description text NULL,
	datedepartenchere timestamp NULL DEFAULT now(),
	dureeenchere int4 NULL DEFAULT 0,
	idmaterielle int4 NULL,
	isfinish int4 NULL DEFAULT 0,
	qte int4 NULL DEFAULT 0,
	commision int4 NULL DEFAULT 0,
	CONSTRAINT enchere_pkey PRIMARY KEY (id),
	CONSTRAINT enchere_idmaterielle_fkey FOREIGN KEY (idmaterielle) REFERENCES materielle(id)
);

ALTER TABLE public.solde_client ADD id serial4 NOT NULL;

ALTER TABLE public.enchere ADD id serial4 NOT NULL;

ALTER TABLE public.admins ADD id serial4 NOT NULL;

ALTER TABLE public.client ADD id serial4 NOT NULL;

ALTER TABLE public.historique ADD montantpayer float8 NULL;

ALTER TABLE public.category ADD id serial4 NOT NULL;

ALTER TABLE public.img_materille ADD idmaterielle int4 NULL;

ALTER TABLE public.materielle ADD idcategory int4 NULL;

ALTER TABLE public.histo_enchere ADD dateajout timestamp NULL DEFAULT now();

ALTER TABLE public.historique ADD nommaterielle varchar(255) NULL;

ALTER TABLE public.enchere ADD isfinish int4 NULL DEFAULT 0;

ALTER TABLE public.admins ADD mdp varchar(255) NOT NULL;

ALTER TABLE public.histo_enchere ADD idenchere int4 NULL;

ALTER TABLE public.historique ADD qte int4 NULL;
