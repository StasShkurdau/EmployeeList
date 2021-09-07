create sequence hibernate_sequence start  1 increment 1;

create table employee_category(
    id bigint not null
        constraint employee_category_pkey
            primary key,
    name varchar(255)
);

alter table employee_category
    owner to postgres;

create table employee(
    id bigint not null
        constraint employee_pkey
            primary key,
    name        varchar(255),
    category_id bigint
        constraint fk7gmue8agdjh42rp5fwnw3g9tn
            references employee_category
);

alter table employee
    owner to postgres;

