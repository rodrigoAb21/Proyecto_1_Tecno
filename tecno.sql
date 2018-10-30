create table usuario (
	id serial primary key,
	nombre varchar(50) not null,
	apellido varchar(50) not null,
	ci varchar(20) not null,
	email varchar(50) not null,
	password varchar(255) not null,
	visible boolean not null default true
);

create table unidad_medida (
	id serial primary key,
	nombre varchar(50) not null,
	visible boolean not null default true
);

create table movimiento_suministro (
	id serial primary key,
	fecha date not null,
	tipo varchar(50) not null,
	visible boolean not null default true
);

create table categoria (
	id serial primary key,
	nombre varchar(50) not null,
	codigo varchar(50) not null,
	categoria_sup int
	visible boolean not null default true,
);

alter table categoria add constraint fk_categoria foreign key (categoria_sup) references categoria(id);

