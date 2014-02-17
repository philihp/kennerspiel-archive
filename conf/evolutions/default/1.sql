# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table command (
  id                        integer auto_increment not null,
  instance_id               integer,
  command                   varchar(255),
  constraint pk_command primary key (id))
;

create table instance (
  id                        integer auto_increment not null,
  name                      varchar(255),
  seed                      bigint,
  game                      varchar(255),
  constraint pk_instance primary key (id))
;

create table user (
  email                     varchar(255) not null,
  name                      varchar(255),
  password                  varchar(255),
  constraint pk_user primary key (email))
;


create table instance_user (
  instance_id                    integer not null,
  user_email                     varchar(255) not null,
  constraint pk_instance_user primary key (instance_id, user_email))
;
alter table command add constraint fk_command_instance_1 foreign key (instance_id) references instance (id) on delete restrict on update restrict;
create index ix_command_instance_1 on command (instance_id);



alter table instance_user add constraint fk_instance_user_instance_01 foreign key (instance_id) references instance (id) on delete restrict on update restrict;

alter table instance_user add constraint fk_instance_user_user_02 foreign key (user_email) references user (email) on delete restrict on update restrict;

# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table command;

drop table instance;

drop table instance_user;

drop table user;

SET FOREIGN_KEY_CHECKS=1;

