use alphasolutions;
insert into names (nameString) value("Carl Harlang");
insert into names (nameString) value("Sadek Alsukafi");
insert into names(nameString) value("Tore Simonsen");
insert into names(nameString) value("Simone Gottbrecht");
insert into positions(posID,posName) value (1,"manager");
insert into positions(posID,posName) value (2, "developer");
insert into employee(empName) value("Sadek");
insert into employee(empName) value("Simone");
insert into project(projectName, managerEmployee_ID) value ("projectAlpha", 2);
insert into project(projectName, managerEmployee_ID) value ("projectAlphaa2", 2);
insert into tasks(taskName, cost, totalEstimatedTime, project_ID) value ("oprette tasks", 100, 200, 1);
insert into tasks(taskName, cost, totalEstimatedTime, project_ID) value ("oprette Subtasks", 50, 100, 2);
insert into worksOn(empID,taskID) value (2, 1);
insert into worksOn(empID,taskID) value (1, 2);
commit; 