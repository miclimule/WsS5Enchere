create table religion(
	id serial primary key,
	nom varchar(255)
);

create table travaille(
	id serial primary key,
	nom varchar(255)
);

create table couleur(
	id serial primary key,
	nom varchar(255)
);

create table users(
	id serial primary key,
	nom varchar(255) not null,
	prenom varchar(255) not null,
	dateNaissance timestamp default now(),
	idReligion integer references religion(id),
	idtravaille integer references travaille(id),
	salaire integer default 0,
	idCouleur integer references couleur(id),
	poids integer default 0,
	bac integer default 0,
	nbrPriere integer default 0,
	description text
);

create table critere(
	id serial primary key,
	idUsers integer unique references users(id),
	debutIntervalNaissance timestamp default now(),
	finIntervalNaissance timestamp default now(),
	poidMin integer default 0,
	poidMax integer default 0,
	couleurPrefere integer default 0 references couleur(id),
	tailleMin integer default 0,
	tailleMax integer default 0,
	bacMin integer default 0,
	noteMin integer default 0
);

create or replace view historique as
select e.id as idEnchere , m.nom as nomMaterielle, e.qte , m.prixminimal , c.nom as nomClient , h.montantpayer  from enchere e
join histo_enchere h on e.id=h.idenchere
join materielle m on m.id=e.idmaterielle
join client c on c.id=m.idclient;

create or replace view count_enchere as
select idEnchere , count(idEnchere) as nbr from historique
group by idenchere; 

select  max(nbr) , min(nbr) , avg(nbr) , sqrt(VAR_SAMP(nbr))  from count_enchere 
;


BEGIN;
SELECT pg_advisory_lock(1);
PERFORM pg_sleep(30);
SELECT pg_advisory_unlock(1);
COMMIT;