-- database 생성
DROP DATABASE IF EXISTS coco_db;
CREATE DATABASE coco_db;

USE coco_db;

-- table 생성
DROP TABLE IF EXISTS user;
CREATE TABLE user (
    user_idx int(11) unsigned NOT NULL AUTO_INCREMENT,
    id varchar(30) NOT NULL,
    pw varchar(30) NOT NULL,
    PRIMARY KEY (user_idx),
    UNIQUE (id)
);

DROP TABLE IF EXISTS profile;
CREATE TABLE profile (
    profile_idx int(11) unsigned NOT NULL AUTO_INCREMENT,
    user_id varchar(30) NOT NULL,
    name varchar(30) NOT NULL,
    gender varchar(10) NOT NULL,
    age int(11) unsigned NOT NULL,
    category1 varchar(15) NOT NULL,
    category2 varchar(15) NOT NULL,
    target_sleep int(2) unsigned DEFAULT 8,   -- 목표수면시간: 8시간
    iteration int(1) unsigned DEFAULT 0,      -- 반복: 0(안한다), 1(한다)
    frequency int(3) unsigned DEFAULT 20, -- 알람주기: 20000ms(20초)
    min_wake int(2) unsigned DEFAULT 7,       -- 최소기상: 7시
    PRIMARY KEY (profile_idx),
    FOREIGN KEY (user_id) REFERENCES user (id)
);

DROP TABLE IF EXISTS record;
CREATE TABLE record (
    record_idx int(11) unsigned NOT NULL AUTO_INCREMENT,
    user_id varchar(30) NOT NULL,
    name varchar(30) NOT NULL,
    date datetime NOT NULL,
    achieve int(1) unsigned NOT NULL,
    PRIMARY KEY (record_idx),
    FOREIGN KEY (user_id) REFERENCES user (id)
);

-- default data 추가
-- DECLARE @DefaultID varchar(30);
SET @DefaultID = 'milleneez';

INSERT INTO user (id, pw) VALUES (@DefaultID, @DefaultID);
INSERT INTO profile (user_id, name, gender, age, category1, category2) VALUES (@DefaultID, '고은서', '여', 23, '자연', '일상');
INSERT INTO profile (user_id, name, gender, age, category1, category2) VALUES (@DefaultID, '김민지', '여', 23, '교육', '일상');
INSERT INTO profile (user_id, name, gender, age, category1, category2) VALUES (@DefaultID, '문명균', '남', 26, '일상', '자연');
INSERT INTO profile (user_id, name, gender, age, category1, category2) VALUES (@DefaultID, '윤하은', '여', 23, '클래식', '일상');