create table fido_app (
	id LONG not null primary key,
	user_id LONG,
	organization_id LONG,
	created_date DATE null,
	description STRING null,
	status_id LONG,
	status_change_date DATE null,
	client_name VARCHAR(75) null,
	client_okpo VARCHAR(75) null,
	contact_phone VARCHAR(75) null,
	credit_amount INTEGER,
	comments STRING null,
	questionnaire VARCHAR(75) null
);

create table fido_app_status (
	id LONG not null primary key,
	code VARCHAR(75) null,
	name STRING null,
	description VARCHAR(75) null
);

create table fido_product_type (
	id LONG not null primary key,
	code VARCHAR(75) null,
	name STRING null,
	description VARCHAR(75) null,
	organization_id LONG,
	ddmtemplate_id LONG
);