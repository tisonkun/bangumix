SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

drop view      if exists bangumix_anime_rank;
drop view      if exists bangumix_anime_tag_count;
drop view      if exists bangumix_anime_full_info;
drop table     if exists bangumix_user;
drop table     if exists bangumix_anime;
drop table     if exists bangumix_anime_comment;
drop table     if exists bangumix_anime_point;
drop table     if exists bangumix_anime_tag;


create table bangumix_user(
  username varchar (255) not null,
  password varchar (255) not null,
  primary key (username)
);

create table bangumix_anime(
  anime_name varchar (255) not null,
  director_name varchar (255) not null,
  synopsis text not null,
  primary key (anime_name)
);

create table bangumix_anime_comment(
  comment_id int AUTO_INCREMENT,
  anime_name varchar (255) not null,
  username varchar (255) not null,
  comment_content text not null,
  comment_timestamp timestamp not null,
  primary key (comment_id),
  FOREIGN KEY (username)
  REFERENCES bangumix_user(username)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  FOREIGN KEY (anime_name)
  REFERENCES bangumix_anime(anime_name)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

create table bangumix_anime_point(
  anime_name varchar (255) not null,
  username varchar (255) not null,
  point int not null,
  primary key (anime_name, username),
    FOREIGN KEY (username)
    REFERENCES bangumix_user(username)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY (anime_name)
    REFERENCES bangumix_anime(anime_name)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

create table bangumix_anime_tag(
  anime_name varchar (255) not null,
  username varchar (255) not null,
  tag_content varchar (255) not null,
  primary key (anime_name, username, tag_content),
    FOREIGN KEY (username)
    REFERENCES bangumix_user(username)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY (anime_name)
    REFERENCES bangumix_anime(anime_name)
    ON DELETE CASCADE
    ON UPDATE CASCADE
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
    select x.anime_name, x.director_name, x.synopsis, x.rank, group_concat(tag_content separator '/') as tags
    from (
    select a.anime_name, a.director_name, a.synopsis, b.rank
    from (bangumix_anime as a
          left join bangumix_anime_rank as b on a.anime_name = b.anime_name)) as x
          left join bangumix_anime_tag_count as t on x.anime_name = t.anime_name
    group by x.anime_name, x.director_name, x.synopsis, x.rank
    order by x.rank desc
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
                    limit 5) as t;

    end; //
delimiter ;

drop trigger if exists insert_bangumix_anime_point;
delimiter //
    create trigger insert_bangumix_anime_point
    before
    insert on bangumix_anime_point
    for each row begin
        if new.point > 5 or new.point < 1
        then
             CALL `'Cannot add or update row: point such be an integer in [1, 5]'`;
        end if;
    end; //
delimiter ;


