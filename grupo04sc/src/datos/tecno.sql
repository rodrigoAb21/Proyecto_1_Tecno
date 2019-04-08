create table usuario (
	id serial primary key,
	nombre varchar(255) not null,
	apellido varchar(255) not null,
	ci varchar(255) not null,
	email varchar(255) unique not null,
	password varchar(255) not null,
	visible boolean not null default true
);

create table unidad_medida (
	id serial primary key,
	nombre varchar(255) not null,
	visible boolean not null default true
);

create table movimiento_suministro (
	id serial primary key,
	fecha date not null,
	tipo varchar(255) not null,
	dpto varchar(255),
        encargado varchar(255),
        observacion varchar(255),
	estado varchar(255) not null default 'Realizado'
);

create table movimiento_activo (
	id serial primary key,
	fecha date not null,
	visible boolean not null default true
);

create table categoria (
	id serial primary key,
	nombre varchar(255) not null,
	codigo varchar(255) not null,
	categoria_sup int,
	visible boolean not null default true
);

alter table categoria add constraint fk_categoria foreign key (categoria_sup) references categoria(id);

create table producto (
	id serial primary key,
	nombre varchar(255) not null,
	codigo varchar(255) not null,
	descripcion varchar(255),
	categoria_id int not null,
	visible boolean not null default true,
	foreign key (categoria_id) references categoria(id) on update cascade on delete cascade
);

create table suministro (
	id serial primary key,
	stock_minimo int not null,
	stock_maximo int not null,
	stock int not null,
	producto_id int not null,
	unidad_medida_id int not null,
	visible boolean not null default true,
	foreign key (producto_id) references producto(id) on delete cascade on update cascade,
	foreign key (unidad_medida_id) references unidad_medida(id) on delete cascade on update cascade
);

create table detalle_suministrar(
  id serial primary key,
  cantidad int not null,
  suministro_id int not null,
  movimiento_suministro_id int not null,
  foreign key (suministro_id) references suministro(id) on delete cascade on update cascade,
  foreign key (movimiento_suministro_id) references movimiento_suministro(id) on delete cascade on update cascade
);

create table activo_fijo (
	id serial primary key,
	estado varchar(255) not null,
	disponible boolean not null,
	codigo varchar(255) not null,
	producto_id int not null,
	visible boolean not null default true,
	foreign key (producto_id) references producto(id) on delete cascade on update cascade
);

create table revaluo (
	id serial primary key,
	detalle varchar(255) not null,
	fecha date not null,
	tipo varchar(255) not null,
	activo_fijo_id int not null,
	visible boolean not null default true,
	foreign key (activo_fijo_id) references activo_fijo(id) on delete cascade on update cascade
);


















