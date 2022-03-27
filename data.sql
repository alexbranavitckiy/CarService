BEGIN;
DROP TABLE if exists clients,qualifications, car_clients,label,employers,master_receivers,masters,orders,outfits,role_user,state_car_breakdown,state_orders,state_outfits,type_car_breakdowns CASCADE;

create table if not exists qualifications(
id UUID primary key,
name varchar(50));

create table if not exists employers(
id UUID primary key ,
name varchar(50),
education varchar(50),
qualification_id UUID,
password varchar(50) not null unique,
login varchar(50) not null ,
home_address varchar(50),
phone varchar(50) not null,
mail varchar(50),
descriptions varchar(50) not null,
foreign key (qualification_id) references qualifications(id)
);


create table if not exists masters(
id UUID primary key ,
id_outfits UUID,
foreign key (id) references employers(id) ON UPDATE CASCADE ON DELETE CASCADE
);

create table if not exists master_receivers(
id UUID primary key,
foreign key (id) references employers(id) ON UPDATE CASCADE ON DELETE CASCADE
);

create table if not exists role_user(
id UUID primary key,
name varchar(50));

create table if not exists clients(
id UUID primary key,
password varchar(50) not null unique,
login varchar(50) not null,
first_name varchar(50),
role_user UUID not null,
last_name varchar(50),
email varchar(25),
phone varchar(50),
descriptions varchar(50) not null,
constraint foreign_key_role_user foreign key (role_user) references role_user(id)
);

create table if not exists state_orders(
id UUID primary key,
name varchar(50));

create table if not exists orders(
id UUID primary key,
descriptions varchar(50) not null,
id_clients UUID not null,
id_masters UUID,
created_date timestamp,
id_outfits UUID unique,
updated_date timestamp,
id_state_order UUID not null,
price_sum real not null,
constraint foreign_key_master_orders foreign key (id_clients) references clients(id),
constraint foreign_key_id_orders foreign key (id_masters) references master_receivers(id),
constraint foreign_state_order foreign key (id_state_order) references state_orders(id)
);

create table if not exists label(
call_date timestamp,
name varchar(50),
id_clients UUID,
id_orders UUID,
primary key (id_clients,id_orders),
foreign key (id_clients) references clients(id),
foreign key (id_orders) references orders(id)
);

create table if not exists state_outfits(
id UUID primary key,
name varchar(50));

create table if not exists outfits(
id UUID primary key,
name_outfits varchar(45),
descriptions varchar(100) ,
start_date timestamp,
end_date timestamp,
price_sum real,
state_outfits UUID ,
constraint foreign_key_masters_outfits foreign key (id) references masters(id_outfits),
constraint foreign_key_state_outfits foreign key (state_outfits) references state_outfits(id),
constraint foreign_key_orders_outfits foreign key (id) references orders(id_outfits)
);

create table if not exists type_car_breakdowns(
id UUID primary key,
name varchar(50) not null,
locationd varchar(50));

create table if not exists state_car_breakdown(
id UUID primary key,
name varchar(50));

create table if not exists car_clients(
id UUID primary key,
id_clients UUID unique,
id_breakdown UUID,
summary varchar(250),
ear date,
run varchar(250),
metadata_car varchar(250),
id_state_car_breakdown UUID,
descriptions varchar(250) not null,
constraint foreign_key_state_car_breakdown foreign key (id_state_car_breakdown) references state_car_breakdown(id),
constraint foreign_key_clients foreign key (id_clients) references clients(id),
constraint foreign_key_type_car_breakdown foreign key (id_breakdown) references type_car_breakdowns(id));

INSERT INTO public.role_user  (id,name)
VALUES
('c1a4d3ea-a5e5-3205-beb2-894e21c4a2bf', 'REGISTERED'),
('c65b6b5a-311f-369f-9936-5e0b45ca6907', 'UNREGISTERED');

INSERT INTO public.qualifications(id, name)
VALUES ('cda01a34-4119-3e5e-9ab9-60b341f233fb', 'DISC_EDITING'),
('efa1d473-42dc-3d5c-af48-448b55c705cc', 'ELECTRICIAN');

INSERT INTO public.employers(
id, name, education, qualification_id, password, login, home_address, phone, mail, descriptions)
VALUES ('cda01a34-4119-3e5e-9ab9-60b341f233fb', 'test', 'test', 'cda01a34-4119-3e5e-9ab9-60b341f233fb',
 'test1', 'test1','test', 'test', 'test', 'test'),
('cda01a34-4119-3e5e-9ab9-60b341f234fb', 'test', 'test', 'cda01a34-4119-3e5e-9ab9-60b341f233fb', 'test',
 'test2','test2', 'test', 'test', 'test');

INSERT INTO public.master_receivers(id)
VALUES ('cda01a34-4119-3e5e-9ab9-60b341f234fb');

INSERT INTO public.masters(id)
VALUES ('cda01a34-4119-3e5e-9ab9-60b341f233fb');

INSERT INTO public.clients(
	id, password, login, first_name, role_user, last_name, email, phone, descriptions)
	VALUES ('8ef08b2c-7ffa-4373-ad3a-72ae64fd4935', 'clients', 'clients', 'clients',
	'c65b6b5a-311f-369f-9936-5e0b45ca6907',
	 'clients', 'clients', 'clients', 'clients');

INSERT INTO public.state_car_breakdown(
	id, name)
	VALUES ('acfd38b7-5861-37a2-b04d-ce420b134a80', 'CORRECTED'),
	('b3c1b7df-bc67-3b62-adb7-f44946c07784', 'NOT_FIXED');

COMMIT;