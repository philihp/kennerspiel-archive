# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table instance (
  id                        bigint not null,
  seed                      bigint,
  game_name                 varchar(255),
  phase                     varchar(1),
  constraint ck_instance_phase check (phase in ('N','A','F')),
  constraint pk_instance primary key (id))
;

create table linked_account (
  id                        bigint not null,
  user_id                   bigint,
  provider_user_id          varchar(255),
  provider_key              varchar(255),
  constraint pk_linked_account primary key (id))
;

create table state (
  id                        bigint not null,
  rank                      float,
  token                     varchar(255),
  instance_id               bigint,
  constraint pk_state primary key (id))
;

create table users (
  id                        bigint not null,
  email                     varchar(255),
  name                      varchar(255),
  active                    boolean,
  email_validated           boolean,
  constraint pk_users primary key (id))
;


create table instance_users (
  instance_id                    bigint not null,
  users_id                       bigint not null,
  constraint pk_instance_users primary key (instance_id, users_id))
;
create sequence instance_seq;

create sequence linked_account_seq;

create sequence state_seq;

create sequence users_seq;

alter table linked_account add constraint fk_linked_account_user_1 foreign key (user_id) references users (id);
create index ix_linked_account_user_1 on linked_account (user_id);
alter table state add constraint fk_state_instance_2 foreign key (instance_id) references instance (id);
create index ix_state_instance_2 on state (instance_id);



alter table instance_users add constraint fk_instance_users_instance_01 foreign key (instance_id) references instance (id);

alter table instance_users add constraint fk_instance_users_users_02 foreign key (users_id) references users (id);

# --- !Downs

drop table if exists instance cascade;

drop table if exists instance_users cascade;

drop table if exists linked_account cascade;

drop table if exists state cascade;

drop table if exists users cascade;

drop sequence if exists instance_seq;

drop sequence if exists linked_account_seq;

drop sequence if exists state_seq;

drop sequence if exists users_seq;

