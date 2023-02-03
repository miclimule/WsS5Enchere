
CREATE TABLE category (
	id serial4 NOT NULL,
	"types" varchar(255) NULL,
	CONSTRAINT category_pkey PRIMARY KEY (id)
);

CREATE TABLE client (
	id serial4 NOT NULL,
	nom varchar(255) NOT NULL,
	mdp varchar(255) NOT NULL,
	CONSTRAINT client_pkey PRIMARY KEY (id)
);

CREATE TABLE admins (
	id serial4 NOT NULL,
	nom varchar(255) NOT NULL,
	mdp varchar(255) NOT NULL,
	CONSTRAINT admins_pkey PRIMARY KEY (id)
);

CREATE TABLE solde_client (
	montant int4 NULL,
	isvalidate int4 NULL DEFAULT 0,
	idclient int4 NULL,
	id serial4 NOT NULL,
	CONSTRAINT solde_client_pkey PRIMARY KEY (id),
	CONSTRAINT solde_client_idclient_fkey FOREIGN KEY (idclient) REFERENCES client(id)
);

CREATE TABLE "token" (
	value text NULL,
	dateajout timestamp NULL DEFAULT now(),
	idclient int4 NULL,
	CONSTRAINT token_value_key UNIQUE (value),
	CONSTRAINT token_idclient_fkey FOREIGN KEY (idclient) REFERENCES client(id)
);

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

CREATE TABLE img_materille (
	idmaterielle int4 NULL,
	"ref" text NULL,
	CONSTRAINT img_materille_idmaterielle_fkey FOREIGN KEY (idmaterielle) REFERENCES materielle(id)
);

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

CREATE TABLE histo_enchere (
	idenchere int4 NULL,
	idgagnant int4 NULL,
	montantpayer float8 NULL,
	dateajout timestamp NULL DEFAULT now(),
	CONSTRAINT histo_enchere_idenchere_fkey FOREIGN KEY (idenchere) REFERENCES enchere(id),
	CONSTRAINT histo_enchere_idgagnant_fkey FOREIGN KEY (idgagnant) REFERENCES client(id)
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

CREATE OR REPLACE VIEW public.count_enchere
AS SELECT historique.idenchere,
    count(historique.idenchere) AS nbr
   FROM historique
  GROUP BY historique.idenchere;
