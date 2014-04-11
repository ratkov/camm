create table fido_app (
	id LONG not null primary key,
	user_id LONG,
	organization_id LONG,
	created_date DATE null,
	description STRING null,
	status_id LONG,
	product_type_id LONG,
	status_change_date DATE null,
	client_name VARCHAR(75) null,
	client_okpo VARCHAR(75) null,
	contact_phone VARCHAR(75) null,
	credit_amount DOUBLE,
	comments STRING null,
	questionnaire VARCHAR(75) null
);

create table fido_app_status (
	id LONG not null primary key,
	code VARCHAR(75) null,
	name STRING null,
	description STRING null
);

create table fido_branch (
	id VARCHAR(75) not null primary key,
	branch_num VARCHAR(75) null,
	branch_num_order INTEGER,
	name STRING null,
	schedule STRING null,
	phones STRING null,
	face_location STRING null,
	zip_code STRING null,
	country STRING null,
	region STRING null,
	district STRING null,
	citytype STRING null,
	city STRING null,
	street_type STRING null,
	street STRING null,
	house STRING null,
	block STRING null,
	apartment STRING null,
	latitude VARCHAR(75) null,
	longitude VARCHAR(75) null,
	branch_type VARCHAR(75) null,
	comments STRING null
);

create table fido_dict (
	ID VARCHAR(75) not null primary key,
	DICT_CODE VARCHAR(75) null,
	PARENT_ID VARCHAR(75) null,
	PARENT2_ID VARCHAR(75) null,
	name STRING null,
	sort LONG
);

create table fido_product_type (
	id LONG not null primary key,
	code VARCHAR(75) null,
	name STRING null,
	description STRING null,
	status BOOLEAN,
	organization_id LONG,
	template_id LONG
);