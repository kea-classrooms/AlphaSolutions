CREATE DATABASE IF NOT EXISTS alphasolutions DEFAULT CHARACTER SET utf8;
USE alphasolutions;
DROP TABLE IF EXISTS names, deadlines, positions, employee,  worksOn, project, tasks, clearance;


CREATE TABLE names (
	nameString VARCHAR (255),
    nameID INT PRIMARY KEY auto_increment
    );

CREATE TABLE clearance(
    clearanceID INTEGER PRIMARY KEY,
    clearanceName VARCHAR(255)
);

CREATE TABLE positions (
    posID INTEGER PRIMARY KEY,
    posName VARCHAR(255),
    clearanceLevel INTEGER,
    FOREIGN KEY (clearanceLevel) REFERENCES clearance(clearanceID)
);


CREATE TABLE employee (
    empID INTEGER AUTO_INCREMENT PRIMARY KEY,
    empName VARCHAR (255),
    position INTEGER,
    FOREIGN KEY (position) REFERENCES positions(posID)
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
    taskID INTEGER,
    PRIMARY KEY (taskID),
    FOREIGN KEY (taskID) REFERENCES tasks(taskID) ON DELETE CASCADE
);