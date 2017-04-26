-- begin DBCER_REMINDER
create table DBCER_REMINDER (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    USER_ID varchar(36),
    REMIND_AT timestamp,
    DONE boolean,
    CAPTION varchar(255) not null,
    ENTITY_ID varchar(36) not null,
    ENTITY_CLASS varchar(255) not null,
    --
    primary key (ID)
)^
-- end DBCER_REMINDER
-- begin DBCER_CUSTOMER
create table DBCER_CUSTOMER (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255),
    --
    primary key (ID)
)^
-- end DBCER_CUSTOMER
