create table if not exists FIDELITY_CARD
(
    ID   INT auto_increment
        primary key,
    NAME VARCHAR(45) not null,
    CODE VARCHAR(45) not null,
    TYPE INT
);

create table if not exists RELATIONSHIP
(
    ID   INT auto_increment
        primary key,
    NAME VARCHAR(45) not null
);

create table if not exists COLOR
(
    ID    INT auto_increment
        primary key,
    VALUE CHAR(6) not null
);

create table if not exists QUICK_ACTION
(
    ID          INT auto_increment
        primary key,
    NAME        VARCHAR(45) not null,
    ACTION_TYPE INT         not null
);

create table if not exists DAILY_MOOD
(
    ID    INT auto_increment
        primary key,
    VALUE INT         not null,
    DATE  VARCHAR(45) not null
);

create table if not exists PERSON
(
    ID              INT auto_increment
        primary key,
    NAME            VARCHAR(50) not null,
    ID_RELATIONSHIP INT         not null,
    constraint FK_2
        foreign key (ID_RELATIONSHIP) references RELATIONSHIP (ID)
);

create table if not exists GROUP_TRANSACTION
(
    ID          INT auto_increment
        primary key,
    DESCRIPTION VARCHAR(100),
    ID_PERSON   INT not null,
    AMOUNT      INT not null,
    DATE        VARCHAR(45),

    constraint FK_12
        foreign key (ID_PERSON) references PERSON (ID)
);

create table if not exists TODO_ACTION
(
    ID          INT auto_increment
        primary key,
    DESCRIPTION VARCHAR(150) not null,
    DONE        TINYINT
);

create table if not exists EVENT
(
    ID        INT auto_increment
        primary key,
    NAME      VARCHAR(45) not null,
    STARTDATE VARCHAR(45) not null,
    ENDDATE   VARCHAR(45) not null,
    FREQUENCY INT
);

create table if not exists CATEGORY
(
    ID       INT auto_increment
        primary key,
    NAME     VARCHAR(45) not null,
    ID_COLOR INT,
    constraint FK_4
        foreign key (ID_COLOR) references COLOR (ID)
            on update cascade on delete set null
);

create table if not exists ACCOUNT
(
    ID           INT auto_increment
        primary key,
    NAME         VARCHAR(45) not null,
    ID_COLOR     INT,
    START_AMOUNT INT         not null,
    constraint FK_6
        foreign key (ID_COLOR) references COLOR (ID)
            on update cascade on delete set null
);

create table if not exists TRANSACTION
(
    ID          INT auto_increment
        primary key,
    DESCRIPTION VARCHAR(100),
    ID_CATEGORY INT     not null,
    DATE        DATE    not null,
    ID_ACCOUT   INT     not null,
    AMOUNT      INT,
    TYPE        INT     not null,
    IS_LAST     TINYINT not null,

    constraint FK_7
        foreign key (ID_CATEGORY) references CATEGORY (ID),
    constraint FK_8
        foreign key (ID_ACCOUT) references ACCOUNT (ID)
            on delete cascade
);

create table if not exists QUICK_TRANSACTION
(
    ID          INT auto_increment
        primary key,
    PRICE       INT         not null,
    NAME        VARCHAR(45) not null,
    FK_CATEGORY INT         not null,
    FK_ACCOUT   INT         not null,
    DESCRIPTION VARCHAR(150),
    constraint FK_10
        foreign key (FK_CATEGORY) references CATEGORY (ID),
    constraint FK_11
        foreign key (FK_ACCOUT) references ACCOUNT (ID)
            on delete cascade
);

create table if not exists EVENT_PERSON
(
    ID        INT auto_increment
        primary key,
    ID_EVENT  INT not null,
    ID_PERSON INT not null,
    constraint FK_15
        foreign key (ID_EVENT) references EVENT (ID)
            on delete cascade,
    constraint FK_16
        foreign key (ID_PERSON) references PERSON (ID)
);

create table if not exists GROUP_TRANSACTION_PERSONS
(
    ID                   INT auto_increment
        primary key,
    ID_GROUP_TRANSACTION INT not null,
    ID_PERSON            INT not null,
    constraint FK_17
        foreign key (ID_GROUP_TRANSACTION) references GROUP_TRANSACTION (ID)
            on delete cascade,
    constraint FK_18
        foreign key (ID_PERSON) references PERSON (ID)
);

