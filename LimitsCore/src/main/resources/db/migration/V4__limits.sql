create table userslimits
(
	id           bigserial      primary key,
	user_id      bigint         unique,
	limit_value  numeric(20,2)
);