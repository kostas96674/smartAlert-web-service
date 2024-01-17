-- insert roles into table roles
insert into roles (name)
values ('sender'),
       ('receiver'),
       ('employee');

-- insert users into table users
-- username: user1, password: user1
-- username: employee1, password: employee1
insert into users (username, password, email, first_name, last_name, phone)
values ('user1', '$2a$10$lL2PCPm.C10mxbUfop6P5ujdyhiKCggb.5raH.iBshMwXrsNKnaay', 'user1@email.com', 'user', 'one',
        '1111111111'),
       ('employee1', '$2a$10$z3WUsz7SGCzzIKRpoiDnmeNZzW9dy3/xKTvdkzJU3l7HX5SdPLtOW', 'employee1@email.com', 'employee',
        'one', '2222222222');

-- insert users roles into table users_roles
insert into users_roles (user_id, role_id)
values (1, 1),
       (1, 2),
       (2, 3);

-- insert incidents into table incidents
insert into incidents (name)
values ('flood'),
       ('fire'),
       ('earthquake'),
       ('tsunami'),
       ('hurricane'),
       ('tornado'),
       ('volcano.eruption'),
       ('avalanche'),
       ('storm');

-- insert incident report into table incident_reports
insert into incident_reports (incident_id, user_id, description, longitude, latitude)
values (4, 1, 'TSUNAMI IN PIRAEUS', 37.937154337142026, 23.648438716131498);