create table mm_user(
n_userid int primary key not null,
s_nickname varchar(32) not null,
n_sex int,
s_phone varchar(16) not null,
s_password varchar(16) not null,
d_regtime timestamp default current_timestamp not null,
d_lastlogintime timestamp not null,
s_iconurl varchar(128)
); 

create table mm_userdetail(
n_userid int primary key not null,
s_email varchar(64),
s_province varchar(8),
s_city varchar(8),
s_discrict varchar(8),
s_constellation int,
s_weibo varchar(64),
s_wechat varchar(64),
d_updatetime default current_timestamp not null
); 

create table mm_pet(
n_petid int primary key not null,
n_masterid int not null,
s_petname varchar(32) not null,
n_petsex int,
s_petcategory varchar(16) not null,
s_pettype varchar(64)
); 