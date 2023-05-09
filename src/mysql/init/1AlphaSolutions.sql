CREATE database if not exists alphasolutions default character set utf8;
use alphasolutions; 
DROP table if exists names, positions, employee,  WorksOn, project, tasks;


CREATE table names (
	nameString varchar (255), 
    nameID int primary key auto_increment
    );

Create Table positions (
  posID integer Primary key,
  posName varchar(255)
);


CREATE Table employee (
 empID integer auto_increment Primary key,
  empName varchar (255),
  positionID integer,
  foreign key (positionID) REFERENCES positions(PosID)
  );

Create Table project (
  projectID integer auto_increment primary key,
  projectName varchar(255),
  managerEmployee_ID integer,
  foreign key (managerEmployee_ID) references employee(empID)
 );
 Create Table tasks (
  taskID integer primary key auto_increment,
  taskName varchar(255),
  cost Integer,
  totalEstimatedTime Integer,
  project_ID integer,
  superTask integer,
  foreign key (project_ID) references project(projectID)
);

 
Create Table worksOn (
  empID integer,
  taskID integer,
  foreign key (empID) REFERENCES employee(empID),
  foreign key(taskID) REFERENCES tasks(taskID)
);
