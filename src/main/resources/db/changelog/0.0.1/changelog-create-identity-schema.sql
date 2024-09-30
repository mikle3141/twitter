--liquibase formatted sql

--changeset SergeyLabuzov:create-identity-schema
--comment create new schema

create schema identity;
--rollback drop schema identity;

--changeset SergeyLabuzov:create-identity-user-accounts-table
--comment create identity.user_accounts
create table identity.user_accounts
(
    id       serial primary key,
    username varchar(32) unique not null,
    password varchar(128) not null
)
--rollback drop table identity.user_accounts

--changeset SergeyLabuzov:create-identity-user-roles-table
--comment create identity.user_roles
create table identity.user_roles
(
    id        serial primary key,
    authority varchar(32) unique not null
)
--rollback drop table identity.user_roles

--changeset SergeyLabuzov:create-identity-user-accounts-roles-table
--comment create identity.user_accounts-roles
create table identity.user_accounts_roles
(
    user_account_id integer not null,
    user_role_id    integer not null
)
--rollback drop table identity.user_accounts-roles

--changeset SergeyLabuzov:add-identity-user-accounts-roles-table-constraints
--comment add constraints to identity.user_accounts-roles
alter table identity.user_accounts_roles
  add constraint user_accounts_roles__user_roles__fk
    foreign key (user_role_id) references identity.user_roles (id);

alter table identity.user_accounts_roles
  add constraint user_accounts_roles__user_accounts__fk
    foreign key (user_account_id) references identity.user_accounts (id);

alter table identity.user_accounts_roles
  add constraint user_accounts_roles_unique
    unique (user_account_id, user_role_id);
--rollback alter table identity.user_accounts_roles drop constraint user_accounts_roles__user_roles__fk;
--rollback alter table identity.user_accounts_roles drop constraint user_accounts_roles__user_accounts__fk;
--rollback alter table identity.user_accounts_roles drop constraint user_accounts_roles_unique;

--changeset SergeyLabuzov:add-data-to-user_roles-table
--comment add data to user_roles table
insert into identity.user_roles(authority)
values ('ROLE_USER'),
       ('ROLE_ADMIN');
--rollback truncate table identity.user_roles;

