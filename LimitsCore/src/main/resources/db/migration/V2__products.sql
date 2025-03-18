create table pruduct_types
(
	id           		bigserial    primary key,
	product_type_name	varchar(100) unique
);

create table products
(
	id 			 	bigserial    	primary key,
	account		 	varchar(20)  	unique,
	amount       	numeric(20,2),
	product_type_id bigint       	references pruduct_types(id),
	user_id 		bigint       	references users(id)
);