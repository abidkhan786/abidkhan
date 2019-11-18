insert into auth_user(user_id,  login_name, password, first_name, last_name, is_active) values (1, 'admin', 'password', 'Abid', 'Khan', 1);
insert into auth_user(user_id,  login_name, password, first_name, last_name, is_active) values (2, 'user', 'good', 'John', 'Smith', 1);
insert into auth_role(role_id, role_name) values (1, 'HumanResource');
insert into auth_role(role_id, role_name) values (2, 'Development');
insert into user_role(role_id, user_id) values (1, 1);
insert into user_role(role_id, user_id) values (2, 2);
--insert into employee(employee_id, first_name, middle_init, last_name, status, birth_date, joined_on) values (1, 'Abid', 'S', 'Khan', 'ACTIVE', '2003-04-23 01:01:01', '2012-04-23 01:01:01');
--insert into employee(employee_id, first_name, middle_init, last_name, status, birth_date, joined_on) values (2, 'John', 'K', 'Smith', 'ACTIVE', '2002-04-23 10:10:10', '2015-04-23 11:11:11');
