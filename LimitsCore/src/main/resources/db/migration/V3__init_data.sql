insert into users (username) values
	('Преображенский Филипп Филлипович'),
	('Борменталь Иван Арнольдович'),
	('Иванова Дарья Петровна'),
	('Бунина Зинаида Прокопьевна');

insert into pruduct_types (product_type_name) values
	('Банковский счет для физ. лиц'),
	('Дебетовая карта для физ. лиц');

insert into products (account, amount, product_type_id, user_id) values
    ('40817810600010000001', 100500.00, (Select id from pruduct_types where product_type_name = 'Банковский счет для физ. лиц' ), (Select id from users where username = 'Преображенский Филипп Филлипович')),
    ('40817810600010000002', 100.00, (Select id from pruduct_types where product_type_name = 'Банковский счет для физ. лиц' ), (Select id from users where username = 'Преображенский Филипп Филлипович')),
    ('40817810600010000003', 100500.00, (Select id from pruduct_types where product_type_name = 'Дебетовая карта для физ. лиц' ), (Select id from users where username = 'Преображенский Филипп Филлипович')),
    ('40817810600010000004', 100.00, (Select id from pruduct_types where product_type_name = 'Дебетовая карта для физ. лиц' ), (Select id from users where username = 'Преображенский Филипп Филлипович')),

    ('40817810600010000005', 100400.00, (Select id from pruduct_types where product_type_name = 'Банковский счет для физ. лиц' ), (Select id from users where username = 'Борменталь Иван Арнольдович')),
    ('40817810600010000006', 70.00, (Select id from pruduct_types where product_type_name = 'Банковский счет для физ. лиц' ), (Select id from users where username = 'Борменталь Иван Арнольдович')),
    ('40817810600010000007', 100400.00, (Select id from pruduct_types where product_type_name = 'Дебетовая карта для физ. лиц' ), (Select id from users where username = 'Борменталь Иван Арнольдович')),
    ('40817810600010000008', 70.00, (Select id from pruduct_types where product_type_name = 'Дебетовая карта для физ. лиц' ), (Select id from users where username = 'Борменталь Иван Арнольдович')),

    ('40817810600010000009', 100200.00, (Select id from pruduct_types where product_type_name = 'Банковский счет для физ. лиц' ), (Select id from users where username = 'Иванова Дарья Петровна')),
    ('40817810600010000010', 50.00, (Select id from pruduct_types where product_type_name = 'Банковский счет для физ. лиц' ), (Select id from users where username = 'Иванова Дарья Петровна')),
    ('40817810600010000011', 100200.00, (Select id from pruduct_types where product_type_name = 'Дебетовая карта для физ. лиц' ), (Select id from users where username = 'Иванова Дарья Петровна')),
    ('40817810600010000012', 50.00, (Select id from pruduct_types where product_type_name = 'Дебетовая карта для физ. лиц' ), (Select id from users where username = 'Иванова Дарья Петровна')),

    ('40817810600010000013', 100000.00, (Select id from pruduct_types where product_type_name = 'Банковский счет для физ. лиц' ), (Select id from users where username = 'Бунина Зинаида Прокопьевна')),
    ('40817810600010000014', 25.00, (Select id from pruduct_types where product_type_name = 'Банковский счет для физ. лиц' ), (Select id from users where username = 'Бунина Зинаида Прокопьевна')),
    ('40817810600010000015', 100000.00, (Select id from pruduct_types where product_type_name = 'Дебетовая карта для физ. лиц' ), (Select id from users where username = 'Бунина Зинаида Прокопьевна')),
    ('40817810600010000016', 25.00, (Select id from pruduct_types where product_type_name = 'Дебетовая карта для физ. лиц' ), (Select id from users where username = 'Бунина Зинаида Прокопьевна'));


