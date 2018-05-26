delete from bangumix_anime_comment;
delete from bangumix_anime_point;
delete from bangumix_anime_tag;
delete from bangumix_user;
delete from bangumix_anime;

-- 123456 : e10adc3949ba59abbe56e057f20f883e

insert into bangumix_user (username, password) values ('Tison', 'e10adc3949ba59abbe56e057f20f883e');
insert into bangumix_user (username, password) values ('Real',  'e10adc3949ba59abbe56e057f20f883e');
insert into bangumix_user (username, password) values ('Blaze', 'e10adc3949ba59abbe56e057f20f883e');
insert into bangumix_user (username, password) values ('Mill',  'e10adc3949ba59abbe56e057f20f883e');

insert into bangumix_anime (anime_name, director_name, synopsis) values ('Megalo Box', '森山洋', '一首复古的热血拳击赞歌。');
insert into bangumix_anime (anime_name, director_name, synopsis) values ('JOJO的奇妙冒险: 不灭钻石', '三池崇史', '杜王町上有关“替身使者”们的全新故事。');
insert into bangumix_anime (anime_name, director_name, synopsis) values ('秒速5厘米', '新海诚', '动画以一个少年为故事轴心而展开连续3个独立故事的动画短篇，时代背景是从1990年代至现代的日本，通过少年的人生展现东京以及其他地区的变迁。');
insert into bangumix_anime (anime_name, director_name, synopsis) values ('你的名字。', '新海诚', '男女高中生在梦中相遇，并寻找彼此的故事。');
insert into bangumix_anime (anime_name, director_name, synopsis) values ('CODE GEASS 反叛的鲁路修', '谷口悟朗', '作品描述被神圣不列颠帝国侵略而亡国的日本——11区中，在生死边缘获得神秘魔女c.c.赋予的Geass力量，立志要粉碎帝国的黑色王子鲁路修·兰佩路基，与坚持公理的白色骑士枢木朱雀所掀起的巨大变化。');
insert into bangumix_anime (anime_name, director_name, synopsis) values ('罪恶王冠', '荒木哲郎', '该动画讲述了2029年，日本因突然爆发的”Apocalypse Virus（天启病毒）“的蔓延而陷入了混乱之中的故事。');
insert into bangumix_anime (anime_name, director_name, synopsis) values ('Fate/stay night [Unlimited Blade Works]', '三浦贵博', '圣杯是传说中可实现持有者一切愿望的宝物。而为了得到圣杯的仪式就被称为圣杯战争。 
　　参加圣杯战争的7名由圣杯选出的魔术师被称为Master，与7名被称为Servant的使魔订定契约。他们是由圣杯选择的七位英灵，被分为七个职阶，以使魔的身份被召唤出来。能获得圣杯的只有一组，这7组人马各自为了成为最后的那一组而互相残杀。');
insert into bangumix_anime (anime_name, director_name, synopsis) values ('Fate/kaleid liner 魔法少女☆伊莉雅', '大沼心', '故事主要讲述了女主角伊莉雅是就读穗群原学园的普通女生，在某天遇到了自称人工天然精灵的魔法露比万花筒之杖，并强制地被缔结契约，成为了魔法少女伊莉雅。而已是红宝石之星持有人的她，还变成了万花筒之杖原持有者的魔术师远坂凛的奴隶，在其的命令之下，被迫帮忙回收沉睡于冬木市的某危险的卡片……');
insert into bangumix_anime (anime_name, director_name, synopsis) values ('刀剑神域', '伊藤智彦', '作品以 2022 年为舞台，大厂牌电子机械制造商“ARGUS”开发出－“NERvGear”－能连结虚拟世界的机器。完全的虚拟实境终于能够实现。主角桐人使用 NERvGear 游玩 VR MMORPG《Sword Art Online》的玩家，幸运地参与过封测并买下正式版的桐人，和正式营运就马上“完全潜行”享受着正式版的 SAO 世界。
就在游戏四小时多后，桐人发现到“登出”指令竟然消失。认为只是系统暂时出错的桐人和开始陷入混乱的所有玩家们一起被传送到开始地点广场，并传来游戏设计者的死亡游戏说明：不能登出是游戏的正常现象，只有打倒位于“艾恩葛朗特”顶楼的头目，达成“完全攻略”才是离开这个世界唯一的方法……');

insert into bangumix_anime_comment (anime_name, username, comment_content, comment_timestamp) values ('Megalo Box', 'Tison', 'Good', null);
insert into bangumix_anime_comment (anime_name, username, comment_content, comment_timestamp) values ('罪恶王冠', 'Mill', '大声告诉我你是属于谁的！', null);
insert into bangumix_anime_comment (anime_name, username, comment_content, comment_timestamp) values ('罪恶王冠', 'Blaze', '大大大大大河内一楼还未嗑药时的作品', null);
insert into bangumix_anime_comment (anime_name, username, comment_content, comment_timestamp) values ('CODE GEASS 反叛的鲁路修', 'Tison', 'All Hail Lelouch!', null);
insert into bangumix_anime_comment (anime_name, username, comment_content, comment_timestamp) values ('Fate/kaleid liner 魔法少女☆伊莉雅', 'Tison', '美游真的是太萌啦（', null);
insert into bangumix_anime_comment (anime_name, username, comment_content, comment_timestamp) values ('Fate/kaleid liner 魔法少女☆伊莉雅', 'Real', '伊莉雅伊莉雅upup', null);
insert into bangumix_anime_comment (anime_name, username, comment_content, comment_timestamp) values ('Fate/kaleid liner 魔法少女☆伊莉雅', 'Real', '美游也很努力啦', null);

insert into bangumix_anime_point (anime_name, username, point) values ('Fate/kaleid liner 魔法少女☆伊莉雅', 'Tison', 5);
insert into bangumix_anime_point (anime_name, username, point) values ('Fate/kaleid liner 魔法少女☆伊莉雅', 'Real', 5);
insert into bangumix_anime_point (anime_name, username, point) values ('Fate/stay night [Unlimited Blade Works]', 'Mill', 4);
insert into bangumix_anime_point (anime_name, username, point) values ('Fate/stay night [Unlimited Blade Works]', 'Blaze', 3);
insert into bangumix_anime_point (anime_name, username, point) values ('JOJO的奇妙冒险: 不灭钻石', 'Real', 3);
insert into bangumix_anime_point (anime_name, username, point) values ('CODE GEASS 反叛的鲁路修', 'Mill', 5);

insert into bangumix_anime_tag (anime_name, username, tag_content) values ('Megalo Box', 'Tison', '复古');
insert into bangumix_anime_tag (anime_name, username, tag_content) values ('Megalo Box', 'Tison', '热血');
insert into bangumix_anime_tag (anime_name, username, tag_content) values ('Fate/kaleid liner 魔法少女☆伊莉雅', 'Real', '萌！无敌！');
insert into bangumix_anime_tag (anime_name, username, tag_content) values ('Fate/kaleid liner 魔法少女☆伊莉雅', 'Tison', '萌！无敌！');
insert into bangumix_anime_tag (anime_name, username, tag_content) values ('Fate/kaleid liner 魔法少女☆伊莉雅', 'Tison', 'Fate');
insert into bangumix_anime_tag (anime_name, username, tag_content) values ('JOJO的奇妙冒险: 不灭钻石', 'Real', '复古');
insert into bangumix_anime_tag (anime_name, username, tag_content) values ('Fate/stay night [Unlimited Blade Works]', 'Mill', 'Fate');
insert into bangumix_anime_tag (anime_name, username, tag_content) values ('CODE GEASS 反叛的鲁路修', 'Mill', '热血');
insert into bangumix_anime_tag (anime_name, username, tag_content) values ('CODE GEASS 反叛的鲁路修', 'Mill', '妹控傲天');
insert into bangumix_anime_tag (anime_name, username, tag_content) values ('刀剑神域', 'Blaze', '松冈祯丞');
insert into bangumix_anime_tag (anime_name, username, tag_content) values ('罪恶王冠', 'Blaze', '最二网管');
insert into bangumix_anime_tag (anime_name, username, tag_content) values ('秒速5厘米', 'Mill', '胃药');
insert into bangumix_anime_tag (anime_name, username, tag_content) values ('你的名字。', 'Real', '真实秒五');
