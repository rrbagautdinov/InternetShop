create table shop.items
(
	id bigserial not null
		constraint products_pkey
			primary key,
	name text not null,
	price bigserial not null,
	created_at text,
	updated_at text
);

alter table shop.items owner to postgres;

create table shop.users
(
	id bigserial not null
		constraint users_pk
			primary key,
	login text not null,
	password text not null,
	name text not null,
	surname text not null,
	email text not null
);

alter table shop.users owner to postgres;

create unique index users_login_uindex
	on shop.users (login);

create table shop.orders
(
	id bigserial not null
		constraint orders_pk
			primary key,
	user_id bigint not null
		constraint user_id___fk
			references shop.users,
	total_price bigint,
	payed boolean default false
);

alter table shop.orders owner to postgres;

create table shop.order_items
(
	id bigserial not null
		constraint order_items_pk
			primary key,
	item_id bigint
		constraint item_id___fk
			references shop.items
				on update cascade on delete cascade,
	quantity integer,
	price integer,
	total_item_price integer,
	order_id bigint
		constraint order_id___fk
			references shop.orders
				on update cascade on delete cascade
);

alter table shop.order_items owner to postgres;

insert into shop.users (login, password, name, surname, email)
values
('rbag', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'Роман', 'Багаутдинов', 'rbag@mail.ru'),
('urich', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'Александр', 'Урих', 'urich@mail.ru');