use alphasolutions;
INSERT INTO project(projectName) VALUES ('Alpha solutions project');
INSERT INTO project(projectName) VALUES ('Consultation project');
INSERT INTO names (nameString) VALUE('Carl Harlang');
INSERT INTO names (nameString) VALUE('Sadek Alsukafi');
INSERT INTO names(nameString) VALUE('Tore Simonsen');
INSERT INTO names(nameString) VALUE('Simone Gottbrecht');
INSERT INTO role(roleID,role) VALUE (1,'ROLE_ADMIN');
INSERT INTO role(roleID,role) VALUE (2,'ROLE_USER');
INSERT INTO positions(posID,posName,roleID) VALUE (1,'manager', 1);
INSERT INTO positions(posID,posName,roleID) VALUE (2,'developer', 2);
INSERT INTO employee(username, password, posID) VALUE ('Sadek', '$2a$12$QEKHuq8gVsL.CBwyLWTH7OHHanP20jGg4A4YyPwdBASlP0p5oQGA6', 1);
INSERT INTO employee(username, password, posID) VALUE ('Simone', '$2a$12$.i08bgTl/69YdNwagxT2wexnmIyWzvTugxoR5RSrntGY2yN.z3Wae', 1);
INSERT INTO employee(username, password, posID) VALUE ('Tore', '$2a$12$.iO6cJHlA9GwdFbs9I42k.LvpO6tFyyezBiQy64FJEoFD2Jo0qZI6', 2);
INSERT INTO employee(username, password, posID) VALUE ('Carl', '$2a$12$fje0hn1lEOBvv1AXOO/myO0MP/0gEPCtqAPSq9YEhcDX0ek72oyHS', 2);
INSERT INTO tasks(taskName, taskDescription, cost, totalEstimatedTime, project_ID) VALUE ('oprette tasks','Oprette tasks så det kan sees i webappen', 100, 200, 1);
INSERT INTO tasks(taskName, taskDescription, cost, totalEstimatedTime, project_ID) VALUE ('Lav scrum board','Lav et scrum board så vi kan se progress', 100, 200, 1);
INSERT INTO tasks(taskName, taskDescription, cost, totalEstimatedTime, project_ID) VALUE ('Lav scrum board','Lav et scrum board så vi kan se progress', 150, 15, 2);
INSERT INTO tasks(taskName, taskDescription , cost, totalEstimatedTime, project_ID) VALUE ('oprette Subtasks', 'Oprette SUBtasks så det kan sees i webappen',50, 100, 2);
INSERT INTO tasks(taskName, taskDescription , cost, totalEstimatedTime, project_ID, superTask) VALUE ('Deadlines', 'Tjek at man kan lave deadlines',1, 60, 1, 1);
INSERT INTO tasks(taskName, taskDescription , cost, totalEstimatedTime, project_ID, superTask) VALUE ('Lav kaffe', '100% den vigtigeste opgave',69, 420, 2, 4);
INSERT INTO tasks(taskName, taskDescription , cost, totalEstimatedTime, project_ID, superTask) VALUE ('Husk at holde pause', 'Optimer arbejdstid ved at holde pause <3',1200000, 56532, 2, 3);
INSERT INTO worksOn(empID,taskID) VALUE (2, 1);
INSERT INTO worksOn(empID,taskID) VALUE (1, 2);
INSERT INTO deadlines (deadline_time, taskID) VALUES ('2023-05-12 12:00:00', 1);
INSERT INTO deadlines (deadline_time, taskID) VALUES ('2023-10-07 11:48:24', 2);
INSERT INTO deadlines (deadline_time, taskID) VALUES ('2023-05-31 12:00:00', 3);
INSERT INTO deadlines (deadline_time, taskID) VALUES ('2021-05-08 10:53:00', 4);
INSERT INTO deadlines (deadline_time, taskID) VALUES ('2022-05-12 10:55:00', 5);
COMMIT;