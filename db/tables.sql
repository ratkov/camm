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

create table fido_app_status (
	id NUMBER(30,0) not null primary key,
	code VARCHAR(75) null,
	name VARCHAR(75) null,
	description VARCHAR(75) null
);

create table fido_product_type (
	id NUMBER(30,0) not null primary key,
	code VARCHAR(75) null,
	name VARCHAR(75) null,
	description VARCHAR(75) null,
	status VARCHAR(75) null,
	organization_id NUMBER(30,0),
	template_id NUMBER(30,0)
);