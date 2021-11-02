DROP TABLE IF EXISTS Employee;
CREATE TABLE Employee(
    employee_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    avatar BLOB,
    first_name VARCHAR(45) NOT NULL,
    last_name VARCHAR(45) NOT NULL,
    email VARCHAR(50) NOT NULL,
    passport_number CHAR(8)
);

insert into Employee (first_name, last_name, email, passport_number) values ('Malvina', 'Spread', 'mspread0@smugmug.com', 'B1234567');
insert into Employee (first_name, last_name, email, passport_number) values ('Polly', 'Grew', 'pgrew1@cnn.com', 'B1884568');
insert into Employee (first_name, last_name, email, passport_number) values ('Ivan', 'Keeting', 'ikeeting2@vistaprint.com', 'B1284569');
insert into Employee (first_name, last_name, email, passport_number) values ('Izzy', 'Brounsell', 'ibrounsell3@slate.com', 'B1234566');
insert into Employee (first_name, last_name, email, passport_number) values ('Llewellyn', 'Norway', 'lnorway4@washington.edu', 'B1294561');
insert into Employee (first_name, last_name, email, passport_number) values ('Felipa', 'Mohun', 'fmohun5@miitbeian.gov.cn', 'B1284560');
insert into Employee (first_name, last_name, email, passport_number) values ('Ewart', 'Oglevie', 'eoglevie6@admin.ch', 'B0234562');
insert into Employee (first_name, last_name, email, passport_number) values ('Noelani', 'Lindro', 'nlindro7@paypal.com', 'B1204563');
insert into Employee (first_name, last_name, email, passport_number) values ('Horacio', 'McKerton', 'hmckerton8@devhub.com', 'B1234564');
insert into Employee (first_name, last_name, email, passport_number) values ('Bradan', 'Eddies', 'beddies9@canalblog.com', 'B1230565');

