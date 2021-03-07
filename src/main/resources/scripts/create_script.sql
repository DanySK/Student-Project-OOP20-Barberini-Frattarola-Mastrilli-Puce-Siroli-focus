create table if not exists FREQUENCY
(
    PK_FREQUENCY INT         not null
        primary key,
    NAME         VARCHAR(45) not null
);

create table if not exists TODO_ACTION
(
    PK_TODO_ACTION INT auto_increment
        primary key,
    DESCRIPTION    VARCHAR(150) not null
);

create table if not exists RELATIONSHIP
(
    PK_RELATIONSHIP INT auto_increment
        primary key,
    NAME            VARCHAR(45) not null
);

create table if not exists FIDELITY_CARD
(
    PK_FIDELITY_CARD INT auto_increment
        primary key,
    NAME             VARCHAR(45) not null,
    CODE             VARCHAR(45) not null
);

create table if not exists DAILY_MOOD
(
    PK_DAILY_MOOD INT auto_increment
        primary key,
    VALUE         INT  not null,
    DATE          DATE not null
);

create table if not exists COLOR
(
    PK_COLOR INT auto_increment
        primary key,
    VALUE    CHAR(6) not null
);

create table if not exists ACTION_TYPE
(
    PK_ACTION_TYPE INT         not null
        primary key,
    VALUE          VARCHAR(45) not null
);

create table if not exists QUICK_ACTION
(
    PK_QUICK_ACTION INT auto_increment
        primary key,
    NAME            VARCHAR(45) not null,
    FK_ACTION_TYPE  INT         not null,
    constraint FK_1
        foreign key (FK_ACTION_TYPE) references ACTION_TYPE (PK_ACTION_TYPE)
);

create table if not exists PERSON
(
    PK_PERSON       INT auto_increment
        primary key,
    NAME            VARCHAR(50) not null,
    FK_RELATIONSHIP INT         not null,
    constraint FK_2
        foreign key (FK_RELATIONSHIP) references RELATIONSHIP (PK_RELATIONSHIP)
);

create table if not exists EVENT
(
    PK_EVENT     INT auto_increment
        primary key,
    NAME         VARCHAR(45) not null,
    STARTDATE    DATETIME    not null,
    ENDDATE      DATETIME    not null,
    FK_FREQUENCY INT         not null,
    constraint FK_3
        foreign key (FK_FREQUENCY) references FREQUENCY (PK_FREQUENCY)
);

create table if not exists CATEGORY
(
    PK_CATEGORY INT auto_increment
        primary key,
    NAME        VARCHAR(45) not null,
    FK_COLOR    INT,
    constraint FK_4
        foreign key (FK_COLOR) references COLOR (PK_COLOR)
            on update cascade on delete set null
);

create table if not exists ACCOUNT
(
    PK_ACCOUT    INT auto_increment
        primary key,
    NAME         VARCHAR(45) not null,
    FK_COLOR     INT,
    START_AMOUNT INT         not null,
    constraint FK_6
        foreign key (FK_COLOR) references COLOR (PK_COLOR)
            on update cascade on delete set null
);

create table if not exists TRANSACTION
(
    PK_TRANSACTION      INT auto_increment
        primary key,
    DESCRIPTION         VARCHAR(100),
    DATE                DATE    not null,
    FK_CATEGORY         INT     not null,
    FK_ACCOUT           INT     not null,
    FK_TRANSACTION_TYPE INT     not null,
    IS_LAST             TINYINT not null,
    constraint FK_7
        foreign key (FK_CATEGORY) references CATEGORY (PK_CATEGORY),
    constraint FK_8
        foreign key (FK_ACCOUT) references ACCOUNT (PK_ACCOUT)
            on delete cascade,
    constraint FK_9
        foreign key (FK_TRANSACTION_TYPE) references FREQUENCY (PK_FREQUENCY)
);

create table if not exists QUICK_TRANSACTION
(
    PK_QUICK_TRANSACTION INT auto_increment
        primary key,
    PRICE                INT         not null,
    NAME                 VARCHAR(45) not null,
    FK_CATEGORY          INT         not null,
    FK_ACCOUT            INT         not null,
    constraint FK_10
        foreign key (FK_CATEGORY) references CATEGORY (PK_CATEGORY),
    constraint FK_11
        foreign key (FK_ACCOUT) references ACCOUNT (PK_ACCOUT)
            on delete cascade
);

create table if not exists GROUP_TRANSACTION
(
    PK_EXPENSE  INT auto_increment
        primary key,
    PRICE       INT not null,
    FK_PERSON   INT not null,
    FK_ACCOUT   INT not null,
    FK_CATEGORY INT not null,
    constraint FK_12
        foreign key (FK_PERSON) references PERSON (PK_PERSON),
    constraint FK_13
        foreign key (FK_ACCOUT) references ACCOUNT (PK_ACCOUT)
            on delete cascade,
    constraint FK_14
        foreign key (FK_CATEGORY) references CATEGORY (PK_CATEGORY)
);

create table if not exists EVENT_PERSONS
(
    PK_EVENT_PERSONS INT auto_increment
        primary key,
    FK_EVENT         INT not null,
    FK_PERSON        INT not null,
    constraint FK_15
        foreign key (FK_EVENT) references EVENT (PK_EVENT)
            on delete cascade,
    constraint FK_16
        foreign key (FK_PERSON) references PERSON (PK_PERSON)
);

create table if not exists GROUP_TRANSACTION_PERSONS
(
    PK_GROUP_TRANSACTION_PERSONS INT auto_increment
        primary key,
    FK_GROUP_TRANSACTION         INT not null,
    FK_PERSON                    INT not null,
    constraint FK_17
        foreign key (FK_GROUP_TRANSACTION) references GROUP_TRANSACTION (PK_EXPENSE)
            on delete cascade,
    constraint FK_18
        foreign key (FK_PERSON) references PERSON (PK_PERSON)
);


