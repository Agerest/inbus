create table if not exists contract
(
    id           serial primary key,
    number_route varchar(11)   not null,
    description  varchar(1000) not null,
    price        int           not null,
    date         time          not null,
    number_auto  varchar(11)   not null,
    customer     varchar(1000)
);
create table if not exists users
(
    username       varchar(50)  not null primary key,
    password       varchar(200) not null,
    enabled        boolean      not null,
    booked_order   int,
    personal_order int,
    FOREIGN KEY (booked_order) REFERENCES contract (id),
    FOREIGN KEY (personal_order) REFERENCES contract (id)
);
create table if not exists authorities
(
    username  varchar(50) not null,
    authority varchar(50) not null,
    constraint fk_authorities_users foreign key (username) references users (username)
);