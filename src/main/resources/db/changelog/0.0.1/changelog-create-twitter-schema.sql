--liquibase formatted sql

--changeset SergeyLabuzof:create-twitter-
--comment create new schema

create schema twitter;
--rollback drop schema twitter;