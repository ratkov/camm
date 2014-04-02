create index IX_5886D11D on fido_app (created_date, client_name, client_okpo, contact_phone, credit_amount, comments, status_id, user_id);
create index IX_3B00596C on fido_app (id, created_date, client_name, client_okpo, contact_phone, credit_amount, comments, status_id, user_id);
create index IX_631B5389 on fido_app (user_id);

create unique index IX_53319108 on fido_app_status (code);
create unique index IX_130158BB on fido_app_status (code_);

create index IX_49B4ED4E on fido_branch (branch_type);
create index IX_C7FE716D on fido_branch (branch_type, city);
create index IX_6FD43D58 on fido_branch (city);

create index IX_B3A54E19 on fido_dict (DICT_CODE);
create index IX_9E5804C7 on fido_dict (DICT_CODE, PARENT_ID);
create index IX_9477502D on fido_dict (DICT_CODE, PARENT_ID, PARENT2_ID);

create unique index IX_8C000382 on fido_product_type (code);
create unique index IX_F4013581 on fido_product_type (code_);
create unique index IX_8F4D1F96 on fido_product_type (organization_id);
create unique index IX_A415687C on fido_product_type (organization_id, status);