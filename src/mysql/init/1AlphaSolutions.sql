CREATE DATABASE IF NOT EXISTS alphasolutions DEFAULT CHARACTER SET utf8;
USE alphasolutions;
DROP TABLE IF EXISTS deadlines, positions, employee,  worksOn, project, tasks, role;


CREATE TABLE role(
    roleID INTEGER PRIMARY KEY,
    role VARCHAR(255)
);

CREATE TABLE positions (
    posID INTEGER PRIMARY KEY,
    posName VARCHAR(255),
    roleID INTEGER,
    FOREIGN KEY (roleID) REFERENCES role(roleID)
);


CREATE TABLE employee (
    empID INTEGER AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR (255),
    password VARCHAR (255),
    enabled BIT DEFAULT 1,
    posID INTEGER,
    FOREIGN KEY (posID) REFERENCES positions(posID)
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