CREATE database if not exists alphasolutions default character set utf8;
use alphasolutions; 
DROP table if exists names;
CREATE table names (
	nameString varchar (255), 
    nameID int primary key auto_increment
    );
