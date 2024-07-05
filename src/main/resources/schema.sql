

create table if not exists "user" (
    id              identity        not null,
    first_name      varchar(255)    not null,
    last_name       varchar(255)    not null,
    email           varchar(255)    not null,
    password        varchar(255)    not null
);

<<<<<<< HEAD
ALTER TABLE "user" ADD CONSTRAINT uq_email UNIQUE (email);

CREATE TABLE IF NOT EXISTS token(
    id              IDENTITY        NOT NULL,
    refresh_token   VARCHAR(255)    NOT NULL,
    expired_at      DATETIME        NOT NULL,
    issued_at       DATETIME        NOT NULL,
    "user"          BIGINT          NOT NULL,
    CONSTRAINT fk_token_user FOREIGN KEY ("user") REFERENCES "user" (id)
);



CREATE TABLE IF NOT EXISTS password_recovery (
    id          IDENTITY        NOT NULL,
    token       VARCHAR(255)    NOT NULL,
    "user"      BIGINT          NOT NULL,
    CONSTRAINT fk_password_recovery_user FOREIGN KEY ("user") REFERENCES "user" (id)
);


ALTER TABLE "user" ADD COLUMN tfa_secret VARCHAR(255) DEFAULT '';

=======
alter table if exists user
     add contraint if not exists uq_email unique (email);

create table if not exists token(
    id          identity        not null,
    refresh_token varchar(255)  not null,
    expired_at  datetime        not null,
    issued_at   datetime        not null,
    user        bigint          not null,
    constraint fk_token_user foreign key (user) references user (id)
);

create table if not exists password_recovery (
    id          identity        not null,
    token       varchar(255)    not null,
    user        bigint          not null,
    constraint fk_password_recovery_user foreign key (user) reference user (id)
);

alter table if exists user
    add column if not exists tfa_secret varchar (255) default '';
>>>>>>> 9b7b3ad516e5445ec9243828988cd6bf31a67a4f
