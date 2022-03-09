create database wise_db;

create user 'wise_user'@'localhost'identified by 'password';

grant all privileges on wise_db.* to 'wise_user'@'localhost';

flush privileges;