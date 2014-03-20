create table fido_app (
	id NUMBER(30,0) not null primary key,
	user_id NUMBER(30,0),
	organization_id NUMBER(30,0),
	created_date DATE null,
	description VARCHAR(75) null,
	status_id NUMBER(30,0),
	status_change_date DATE null,
	client_name VARCHAR(75) null,
	client_okpo VARCHAR(75) null,
	contact_phone VARCHAR(75) null,
	credit_amount INTEGER,
	comments VARCHAR(75) null,
	questionnaire VARCHAR(75) null
);
comment on table fido_product_type is 'Заявки на кредит';
alter table fido_app add constraint pk_fido_app primary key (id);
alter table fido_app add constraint fk_fido_organization foreign key (organization_id) references organization(id);
alter table fido_app add constraint fk_fido_app_status foreign key (status_id) references fido_app_status(id);
alter table fido_app add constraint fk_fido_app_user foreign key (user_id) references user(id);
/


create table fido_app_status (
	id NUMBER(30,0) not null primary key,
	code VARCHAR(75) null,
	name VARCHAR(75) null,
	description VARCHAR(75) null
);
comment on table fido_app_status is 'Статусы заявки';
alter table fido_app_status add constraint pk_fido_app_status primary key (id);
create unique index uq_fido_app_status on fido_app_status (code);
/

create table fido_product_type (
	id NUMBER(30,0) not null primary key,
	code VARCHAR(75) null,
	name VARCHAR(75) null,
	description VARCHAR(75) null,
	status NUMBER(1,0) null,
	organization_id NUMBER(30,0),
	template_id NUMBER(30,0)
);
comment on table fido_product_type is 'Кредитные продукты и подвязанные шаблоны';
alter table fido_product_type add constraint pk_fido_app_status primary key (id);
create unique index uq_fido_product_type on fido_product_type (organization_id, code);
alter table fido_product_type add constraint fido_prod_type_organiz foreign key (organization_id) references organization(id);
alter table fido_product_type add constraint fido_prod_type_organiz foreign key (template_id) references ddmtemplate(id);
/

create table fido_dict
(
  dict_code  varchar2(50),
  id         varchar2(50),
  parent_id  varchar2(50),
  parent2_id varchar2(50),
  name       varchar2(3000),
  sort       number(30)
);
comment on table fido_dict is 'Кэш справочников';
create index indx_fido_dict_prnt on fido_dict (dict_code, parent_id, parent2_id);
create unique index uq_fido_dict on fido_dict (dict_code, id, parent_id);

-- table for divisions/branches
create table fido_branch
(
  id 			varchar2(30),
  branch_num	varchar2(128),
  name			varchar2(1024),
  schedule		varchar2(1024),
  phones		varchar2(128),
  face_location	varchar2(1024),
  zip_code    	varchar2(128),
  country     	varchar2(128),
  region      	varchar2(128),
  district    	varchar2(128),
  citytype    	varchar2(128),
  city        	varchar2(128),
  street_type 	varchar2(128),
  street      	varchar2(128),
  house       	varchar2(128),
  block		  	varchar2(128),
  apartment   	varchar2(128),
  latitude		varchar2(30),
  longitude		varchar2(30),
  branch_type	varchar2(30) default 'DIVISION', -- 'DIVISION' - отделение ,'ATM' - банкомат
  comments 		varchar2(3000)
);

comment on table fido_branch is 'Отделения/банкоматы';
alter table fido_branch add constraint pk_fido_branch primary key (id);
alter table fido_branch add constraint ckc_fido_branch_branch_type  check (branch_type in ('DIVISION','ATM'));