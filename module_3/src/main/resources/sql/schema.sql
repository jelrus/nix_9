drop table if exists account_operations;
drop table if exists client_accounts;
drop table if exists operations;
drop table if exists accounts;
drop table if exists clients;

create table operations
(
    id             bigint                     auto_increment primary key,
    created        datetime(6)                null,
    updated        datetime(6)                null,
    name           varchar(255)               not null,
    operation_type enum ('INCOME', 'OUTCOME') not null,
    description    varchar(255)               not null,
    sum            float(20)                 not null
);

create table accounts
(
    id             bigint       auto_increment primary key,
    created        datetime(6)  null,
    updated        datetime(6)  null,
    account_number varchar(255) not null,
    balance        float(20)    not null
);

create table clients
(
    id         bigint       auto_increment primary key,
    created    datetime(6)  null,
    updated    datetime(6)  null,
    first_name varchar(255) not null,
    last_name  varchar(255) not null,
    email      varchar(255) not null,
    phone      varchar(255) not null
);

create table account_operations
(
    account_id   bigint not null,
    operation_id bigint not null,
    constraint UK_g7bmhhecksr7bsmw3rlm107kh
        unique (operation_id),
    constraint FKmgf20t5xiq549ea3v6x392w89
        foreign key (account_id) references accounts (id) ON DELETE CASCADE,
    constraint FKmqbku81qgqysgdajnl7q2oc0q
        foreign key (operation_id) references operations (id) ON DELETE CASCADE
);

create table client_accounts
(
    client_id  bigint not null,
    account_id bigint not null,
    primary key (client_id, account_id),
    constraint UK_1u52qobmha2fsvgxuby5tsbbm
        unique (account_id),
    constraint FKa2yl4sr9rn6yiu5yndokkk0eq
        foreign key (account_id) references accounts (id) ON DELETE CASCADE,
    constraint FKk0ogdei0cx4vwmopecii240ik
        foreign key (client_id) references clients (id) ON DELETE CASCADE
);