-- drop table if exists TB_USER, TB_DELIVERY, TB_USER_INFO, TB_SUGGEST, TB_REVIEW;
create table if not exists TB_REVIEW
(
    REVIEW_ID      varchar(40) not null comment '审核信息的唯一标识',
    REVIEW_DATE    datetime    not null comment '审核信息-审核日期',
    REVIEW_IS_BY   tinyint(1)  not null comment '审核信息-审核是否已通过',
    REVIEW_MESSAGE text        null comment '审核信息-审核说明',
    USER_ID        varchar(40) not null comment '审核信息-审核用户的唯一标识',
    constraint TB_REVIEW_REVIEW_ID_uindex
        unique (REVIEW_ID)
);

alter table TB_REVIEW
    add primary key (REVIEW_ID);

create table if not exists TB_USER_INFO
(
    USER_INFO_ID              varchar(40)  not null comment '用户信息的唯一标识',
    USER_INFO_NAME            varchar(20)  not null comment '用户信息—名字',
    USER_INFO_COLLEGE         varchar(20)  not null comment '用户信息-学院',
    USER_INFO_SUBJECT         varchar(20)  not null comment '用户信息-专业',
    USER_INFO_CLASS           varchar(20)  not null comment '用户信息-班级',
    USER_INFO_STUDENT_NUMBER  varchar(20)  not null comment '用户信息-学号',
    USER_INFO_PHOTO_URL       varchar(120) not null comment '用户信息-审核图片',
    USER_INFO_THUMB_PHOTO_URL varchar(120) not null comment '用户信息-审核缩略图片',
    constraint TB_USER_INFO_USER_INFO_ID_uindex
        unique (USER_INFO_ID)
);

alter table TB_USER_INFO
    add primary key (USER_INFO_ID);

create table if not exists TB_USER
(
    USER_ID                         varchar(40) not null comment '安全用户唯一标识',
    USER_PHONE                      varchar(20) null comment '安全用户帐号（手机号）',
    USER_PASSWORD                   varchar(80) null comment '安全用户的密码',
    USER_IS_VALID                   tinyint(1)  not null comment '该安全用户帐号是否已注册',
    USER_IS_ENABLED                 tinyint(1)  not null comment '该安全用户帐号是否启用',
    USER_IS_ACCOUNT_NON_EXPIRED     tinyint(1)  not null comment '该安全用户帐号是否未过期',
    USER_IS_CREDENTIALS_NON_EXPIRED tinyint(1)  not null comment '该安全用户帐号凭证是否未过期',
    USER_IS_ACCOUNT_NON_LOCKED      tinyint(1)  not null comment '该安全用户帐号是否未锁定',
    USER_IS_BY_REVIEW               tinyint(1)  not null comment '该安全用户帐号是否已通过审核',
    USER_IS_SUBMIT_REVIEW           tinyint(1)  not null comment '该安全用户账户是否已提交用户审核',
    USER_INFO_ID                    varchar(40) null comment '该帐号对应用户信息的唯一标识',
    REVIEW_ID                       varchar(40) null comment '该帐号对应的审核信息唯一标识',
    constraint TB_USER_USER_ID_uindex
        unique (USER_ID),
    constraint TB_USER_USER_INFO_ID_uindex
        unique (USER_INFO_ID),
    constraint TB_USER_USER_PHONE_uindex
        unique (USER_PHONE),
    constraint TB_USER_TB_REVIEW_REVIEW_ID_fk
        foreign key (REVIEW_ID) references TB_REVIEW (REVIEW_ID),
    constraint TB_USER_TB_USER_INFO_USER_INFO_ID_fk
        foreign key (USER_INFO_ID) references TB_USER_INFO (USER_INFO_ID)
);

alter table TB_USER
    add primary key (USER_ID);

create table if not exists TB_DELIVERY
(
    DELIVERY_ID               varchar(40) not null comment '包裹信息-唯一标识',
    DELIVERY_NAME             varchar(20) not null comment '包裹信息-快递名称',
    DELIVERY_CODE             varchar(20) not null comment '包裹信息-快递取货号',
    DELIVERY_USER_NAME        varchar(20) not null comment '包裹信息-包裹的收货人名字',
    DELIVERY_USER_SEX         varchar(20) not null comment '包裹信息-包裹收货人性别',
    DELIVERY_ADDRESS          tinytext    not null comment '包裹信息-包裹所在地址',
    DELIVERY_GOAL_FLOOR       tinytext    not null comment '包裹信息-包裹所在地址',
    DELIVERY_GOAL_ADDRESS     tinytext    not null comment '包裹信息-包裹要送达的地址',
    DELIVERY_WEIGHT           varchar(20) not null comment '包裹信息-包裹重量',
    DELIVERY_REWARD           varchar(20) not null comment '包裹信息-包裹的赏金',
    DELIVERY_REMARK           tinytext    null comment '包裹信息-备注',
    DELIVERY_DATE             datetime    null comment '包裹信息-日期',
    DELIVERY_USER_ID          varchar(40) not null comment '包裹信息-发布者的安全用户唯一标识',
    DELIVERY_DELIVERY_USER_ID varchar(40) null comment '包裹信息-包裹工作者的安全用户唯一标识',
    DELIVERY_STATUS           tinyint(1)  not null comment '包裹信息-包裹是否被接单',
    constraint TB_DELIVERY_DELIVERY_ID_uindex
        unique (DELIVERY_ID),
    constraint TB_DELIVERY_TB_USER_USER_ID_fk
        foreign key (DELIVERY_USER_ID) references TB_USER (USER_ID),
    constraint TB_DELIVERY_TB_USER_USER_ID_fk_2
        foreign key (DELIVERY_DELIVERY_USER_ID) references TB_USER (USER_ID)
);

alter table TB_DELIVERY
    add primary key (DELIVERY_ID);

alter table TB_REVIEW
    add constraint TB_REVIEW_TB_USER_USER_ID_fk
        foreign key (USER_ID) references TB_USER (USER_ID);

create table if not exists TB_SUGGEST
(
    SUGGEST_ID      varchar(40) not null comment '建议反馈的唯一标识',
    SUGGEST_USER_ID varchar(40) null comment '建议反馈-建议者的安全用户唯一标识',
    SUGGEST_CONTENT text        not null comment '建议反馈-反馈内容',
    constraint TB_SUGGEST_SUGGEST_ID_uindex
        unique (SUGGEST_ID),
    constraint TB_SUGGEST_TB_USER_USER_ID_fk
        foreign key (SUGGEST_USER_ID) references TB_USER (USER_ID)
);

alter table TB_SUGGEST
    add primary key (SUGGEST_ID);

