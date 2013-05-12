# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table table (
  id                        bigint not null,
  name                      varchar(255),
  seed                      integer,
  constraint pk_table primary key (id))
;

create sequence table_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists table;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists table_seq;

