

create table if not exists "user" (
    id              identity        not null,
    first_name      varchar(255)    not null,
    last_name       varchar(255)    not null,
    email           varchar(255)    not null,
    password        varchar(255)    not null
);

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

