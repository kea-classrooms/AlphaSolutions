CREATE DATABASE IF NOT EXISTS alphasolutions DEFAULT CHARACTER SET utf8;
USE alphasolutions;
DROP TABLE IF EXISTS names, deadlines, positions, employee,  WorksOn, project, tasks;


CREATE TABLE names (
	nameString VARCHAR (255),
    nameID INT PRIMARY KEY auto_increment
    );

CREATE TABLE positions (
    posID INTEGER PRIMARY KEY,
    posName VARCHAR(255)
);


CREATE TABLE employee (
    empID INTEGER AUTO_INCREMENT PRIMARY KEY,
    empName VARCHAR (255)
  );

CREATE TABLE project (
    projectID INTEGER AUTO_INCREMENT PRIMARY KEY,
    projectName VARCHAR(255),
    managerEmployee_ID INTEGER,
    FOREIGN KEY (managerEmployee_ID) REFERENCES employee(empID)
 );

 CREATE TABLE tasks (
    taskID INTEGER PRIMARY KEY auto_increment,
    taskName VARCHAR(255),
    taskDescription VARCHAR(255),
    cost INTEGER,
    totalEstimatedTime INTEGER,
    project_ID INTEGER NOT NULL,
    superTask INTEGER,
    FOREIGN KEY (project_ID) REFERENCES project(projectID)
);

 
CREATE TABLE worksOn (
    empID INTEGER,
    taskID INTEGER,
    FOREIGN KEY (empID) REFERENCES employee(empID),
    FOREIGN KEY (taskID) REFERENCES tasks(taskID) ON DELETE CASCADE
);
CREATE TABLE deadlines (
    deadline_time DATETIME,
    taskID INTEGER UNIQUE,
    PRIMARY KEY (deadline_time, taskID),
    FOREIGN KEY (taskID) REFERENCES tasks(taskID) ON DELETE CASCADE
);