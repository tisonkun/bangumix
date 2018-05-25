drop view      if exists bangumix_anime_rank;
drop view      if exists bangumix_anime_tag_count;
drop view      if exists bangumix_anime_full_info;
drop table     if exists bangumix_user;
drop table     if exists bangumix_anime;
drop table     if exists bangumix_anime_comment;
drop table     if exists bangumix_anime_point;
drop table     if exists bangumix_anime_tag;


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
  comment_timestamp timestamp not null,
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

create view bangumix_anime_tag_count as
  select anime_name, tag_content, count(tag_content) as times
  from bangumix_anime_tag
  group by anime_name, tag_content
  order by times desc
;

create view bangumix_anime_full_info as
  select a.anime_name, a.director_name, a.synopsis, r.rank, group_concat(tag_content separator '/') as tags
  from bangumix_anime as a, bangumix_anime_rank as r, bangumix_anime_tag_count as t
  where a.anime_name = r.anime_name and a.anime_name = t.anime_name
  group by a.anime_name, a.director_name, a.synopsis, r.rank
  order by r.rank
;


drop procedure if exists bangumix_recommend;
delimiter //
    create procedure bangumix_recommend(
        username varchar (255)
    )
    begin
        select anime_name
        from (select s.anime_name, min(s.tag) as prec
              from (select anime_name, tag
                    from (select distinct anime_name, 0 as tag
                          from
                            (bangumix_anime_tag as a)
                            natural join
                            (select distinct tag_content
                            from bangumix_anime_tag as b
                            where b.username = username) as x) as y

                            union

                            (select distinct anime_name, 1 as tag
                             from bangumix_anime)) as s
                    group by s.anime_name
                    order by prec
                    limit 10) as t;

    end; //
delimiter ;


insert into bangumix_user (username, password) values ('Tison', '123456');
insert into bangumix_user (username, password) values ('Real', '123456');
insert into bangumix_user (username, password) values ('Think', '123456');
insert into bangumix_anime (anime_name, director_name, synopsis) values ('Megalo Box', '森山洋', '一首复古的热血拳击赞歌');
insert into bangumix_anime (anime_name, director_name, synopsis) values ('Unnatraul', '野木亚纪子', '非自然死亡原因研究所');
insert into bangumix_anime_comment (anime_name, username, comment_content, comment_timestamp) values ('Megalo Box', 'Tison', 'Good', null);
insert into bangumix_anime_point (anime_name, username, point) values ('Megalo Box', 'Tison', 4);
insert into bangumix_anime_point (anime_name, username, point) values ('Megalo Box', 'Real', 5);
insert into bangumix_anime_tag (anime_name, username, tag_content) values ('Megalo Box', 'Tison', '复古');
insert into bangumix_anime_tag (anime_name, username, tag_content) values ('Megalo Box', 'Tison', '四月新番');
insert into bangumix_anime_tag (anime_name, username, tag_content) values ('Megalo Box', 'Real', '四月新番');
insert into bangumix_anime_tag (anime_name, username, tag_content) values ('Megalo Box', 'Think', '四月新番');
insert into bangumix_anime_tag (anime_name, username, tag_content) values ('Megalo Box', 'Tison', 'Good');
insert into bangumix_anime_tag (anime_name, username, tag_content) values ('Unnatraul', '野木亚纪子', 'Good');