CREATE database if not exists alphaSolutions default character set utf8;
use alphaSolutions; 
DROP table if exists names;
CREATE table names (
	nameString varchar (255), 
    nameID int primary key auto_increment
    );
