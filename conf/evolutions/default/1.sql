# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table instance (
  id                        bigint not null,
  seed                      bigint,
  game_name                 varchar(255),
  constraint pk_instance primary key (id))
;

create table linked_account (
  id                        bigint not null,
  user_id                   bigint,
  provider_user_id          varchar(255),
  provider_key              varchar(255),
  constraint pk_linked_account primary key (id))
;

create table users (
  id                        bigint not null,
  email                     varchar(255),
  name                      varchar(255),
  active                    boolean,
  email_validated           boolean,
  constraint pk_users primary key (id))
;

create sequence instance_seq;

create sequence linked_account_seq;

create sequence users_seq;

alter table linked_account add constraint fk_linked_account_user_1 foreign key (user_id) references users (id);
create index ix_linked_account_user_1 on linked_account (user_id);



# --- !Downs

drop table if exists instance cascade;

drop table if exists linked_account cascade;

drop table if exists users cascade;

drop sequence if exists instance_seq;

drop sequence if exists linked_account_seq;

drop sequence if exists users_seq;

