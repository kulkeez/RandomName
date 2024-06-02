CREATE TABLE IF NOT EXISTS EMPLOYEE
(
	ID int NOT NULL PRIMARY KEY,
	NAME varchar(255),
	AGE int,
	GENDER varchar(10),
	JOININGDATE date,
	RETIRINGDATE date,
	DEPT varchar(25),
	BIRTHDATE date
);

INSERT INTO EMPLOYEE VALUES (1, 'James Gosling', 45, 'Male', '1980-10-08', null, 'IT', '1960-10-08');

INSERT INTO EMPLOYEE VALUES (2, 'Donald Knuth', 45, 'Male', '1990-10-24', null, 'HR', '1970-10-08');

INSERT INTO EMPLOYEE VALUES (3, 'Linus Torvalds', 50, 'Male', '1973-10-11', null, 'IT', '1955-10-08');

INSERT INTO EMPLOYEE VALUES (4, 'Dennis Ritchie', 75, 'Male', '2022-10-08', null, 'IT', '1947-10-08');