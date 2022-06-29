insert into cooperator (id, username, password, active)
values (2, 'admin', '1234', true);

insert into cooperator_role (cooperator_id, roles)
values (2, 'ADMIN');