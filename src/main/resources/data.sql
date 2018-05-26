-- 123456 : e10adc3949ba59abbe56e057f20f883e

insert into bangumix_user (username, password) values ('Tison', 'e10adc3949ba59abbe56e057f20f883e');
insert into bangumix_user (username, password) values ('Real', 'e10adc3949ba59abbe56e057f20f883e');
insert into bangumix_user (username, password) values ('Think', 'e10adc3949ba59abbe56e057f20f883e');
insert into bangumix_anime (anime_name, director_name, synopsis) values ('Megalo Box', '森山洋', '一首复古的热血拳击赞歌');
insert into bangumix_anime (anime_name, director_name, synopsis) values ('Unnatural', '野木亚纪子', '非自然死亡原因研究所');
insert into bangumix_anime_comment (anime_name, username, comment_content, comment_timestamp) values ('Megalo Box', 'Tison', 'Good', null);
insert into bangumix_anime_point (anime_name, username, point) values ('Megalo Box', 'Tison', 4);
insert into bangumix_anime_point (anime_name, username, point) values ('Megalo Box', 'Real', 5);
insert into bangumix_anime_tag (anime_name, username, tag_content) values ('Megalo Box', 'Tison', '复古');
insert into bangumix_anime_tag (anime_name, username, tag_content) values ('Megalo Box', 'Tison', '四月新番');
insert into bangumix_anime_tag (anime_name, username, tag_content) values ('Megalo Box', 'Real', '四月新番');
insert into bangumix_anime_tag (anime_name, username, tag_content) values ('Megalo Box', 'Think', '四月新番');
insert into bangumix_anime_tag (anime_name, username, tag_content) values ('Megalo Box', 'Tison', 'Good');