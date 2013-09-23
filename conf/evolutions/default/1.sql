# --- !Ups

create table command (
  id                        bigint not null,
  table_id                  bigint not null,
  command                   varchar(255),
  constraint pk_command primary key (id))
;

create table table (
  id                        bigint not null,
  name                      varchar(255),
  seed                      integer,
  game                      varchar(255),
  constraint pk_table primary key (id))
;

create sequence command_seq;

create sequence table_seq;

alter table command add constraint fk_command_table_1 foreign key (table_id) references table (id) on delete restrict on update restrict;
create index ix_command_table_1 on command (table_id);

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists command;

drop table if exists table;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists command_seq;

drop sequence if exists table_seq;

