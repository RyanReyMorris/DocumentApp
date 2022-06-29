create sequence hibernate_sequence start 1 increment 1;

create table orders
(
    id            int8 not null,
    controlling   varchar(255),
    deadline      varchar(255),
    filename      varchar(255),
    performer     varchar(255),
    performing    varchar(255),
    subject       varchar(255),
    text          varchar(2048) not null,
    cooperator_id int8,
    primary key (id)
);

create table cooperator_role
(
    cooperator_id int8 not null,
    roles         varchar(255)
);
create table cooperator (
    id         int8    not null,
    active     boolean not null,
    name       varchar(255),
    password   varchar(255) not null,
    patronymic varchar(255),
    position   varchar(255),
    username   varchar(255) not null,
    primary key (id)
);

alter table if exists orders
    add constraint orders_cooperator_fk
        foreign key (cooperator_id) references cooperator;
alter table if exists cooperator_role
    add constraint cooperator_role_cooperator_fk
    foreign key (cooperator_id) references cooperator;

