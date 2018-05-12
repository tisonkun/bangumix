drop view     if exists bangumix_anime_rank;
drop table    if exists bangumix_user;
drop table    if exists bangumix_anime;
drop table    if exists bangumix_anime_comment;
drop table    if exists bangumix_anime_point;
drop table    if exists bangumix_anime_tag;


create table bangumix_user(
  username varchar (255),
  password varchar (255),
  primary key (username)
);

create table bangumix_anime(
  anime_name varchar (255),
  director_name varchar (255),
  synopsis text,
  primary key (anime_name)
);

create table bangumix_anime_comment(
  comment_id int AUTO_INCREMENT,
  anime_name varchar (255),
  username varchar (255),
  comment_content text,
  primary key (comment_id)
);

create table bangumix_anime_point(
  anime_name varchar (255),
  username varchar (255),
  point int,
  primary key (anime_name, username)
);

create table bangumix_anime_tag(
  anime_name varchar (255),
  username varchar (255),
  tag_content varchar (255),
  primary key (anime_name, username, tag_content)
);

create view bangumix_anime_rank as
  select anime_name, avg(point) as rank
  from bangumix_anime_point
  group by anime_name
  order by rank desc
;

insert into bangumix_user (username, password) values ('Tison', '123456');
insert into bangumix_user (username, password) values ('Real', '123456');
insert into bangumix_anime (anime_name, director_name, synopsis) values ('Megalo Box', '森山洋', '一首复古的热血拳击赞歌');
insert into bangumix_anime_comment (anime_name, username, comment_content) values ('Megalo Box', 'Tison', 'Good');
insert into bangumix_anime_point (anime_name, username, point) values ('Megalo Box', 'Tison', 10);
insert into bangumix_anime_point (anime_name, username, point) values ('Megalo Box', 'Real', 5);