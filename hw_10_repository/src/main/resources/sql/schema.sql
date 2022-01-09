drop table if exists property;
drop table if exists houses;
drop table if exists owners;

create table houses(
  id bigint auto_increment primary key,
  created datetime(6) null,
  updated datetime(6) null,
  image varchar(255) not null,
  country varchar(255) not null,
  city varchar(255) not null,
  street varchar(255) not null,
  building_number varchar(255) not null,
  status enum('SALE', 'RENT'),
  cost float not null
);

create table owners(
  id bigint auto_increment primary key,
  created datetime(6) null,
  updated datetime(6) null,
  first_name varchar(255) not null,
  last_name varchar(255) not null,
  email varchar(255) not null,
  phone varchar(255) not null
);

create table property
(
    house_id bigint not null,
    owner_id bigint not null,
    primary key (house_id, owner_id),
    constraint FKb5w0brbi9dl946yj1hdg5pc8t
        foreign key (owner_id) references owners (id),
    constraint FKqqo6aubpuhtvxvyugr6xu3ofw
        foreign key (house_id) references houses (id)
);