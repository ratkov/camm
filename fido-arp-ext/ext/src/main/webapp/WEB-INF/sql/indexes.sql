create index IX_631B5389 on fido_app (user_id);

create unique index IX_53319108 on fido_app_status (code);
create unique index IX_130158BB on fido_app_status (code_);

create unique index IX_8C000382 on fido_product_type (code);
create unique index IX_F4013581 on fido_product_type (code_);
create unique index IX_8F4D1F96 on fido_product_type (organization_id);