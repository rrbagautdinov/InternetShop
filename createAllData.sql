create table shop.products
(
	id bigserial not null
		constraint products_pkey
			primary key,
	name text not null,
	price bigserial not null
);

alter table shop.products owner to postgres;

insert into shop.products (name, price)
values
('Молоко', 99),
('Сыр', 199),
('Колбаса', 299),
('Хлеб', 29),
('Йогурт', 50),
('Вафли', 79),
('Блины', 99),
('Курица', 249),
('Котлеты', 149),
('Сушки', 29);


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

insert into shop.users(login, password, name, surname, email)
values
('admin', '123', 'Роман', 'Багаутдинов', 'admin@shop.ru')