insert into user (name) values ('Trang');
insert into user (name) values ('David');
insert into user (name) values ('May');

insert into post (title, user_id) values ('Top five hardest languages',1);
insert into post (title, user_id) values ('Top 10 beautiful countries',1);
insert into post (title, user_id) values ('Top 5 famous actors',2);
insert into post (title, user_id) values ('Top 5 programming languages',3);
insert into post (title, user_id) values ('How to learn Spring boot',3);
insert into post (title, user_id) values ('What is a spring bean',3);

insert into comment (review, post_id, user_id) values ('Robin Williams is the best actor',3,1);
insert into comment (review, post_id, user_id) values ('I love Java',4,1);
insert into comment (review, post_id, user_id) values ('I like Morgan Freeman',3,1);
insert into comment (review, post_id, user_id) values ('Japanese',1,2);
insert into comment (review, post_id, user_id) values ('Viet Nam',2,3);
insert into comment (review, post_id, user_id) values ('New Zealand',2,3);
insert into comment (review, post_id, user_id) values ('Italy',2,2);
insert into comment (review, post_id, user_id) values ('Great, thanks',6,1);

