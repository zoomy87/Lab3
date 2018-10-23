
create table USERS (
    fName varchar(50),
    lName varchar (50),
    userID varchar (50) ,
    password varchar(50),
    question varchar (200),
    answer varchar (200),
    email varchar (150),
primary key (userID),
unique(email)
);

INSERT INTO Users ( fName, lName, userID, password, question, answer, email ) 
    VALUES ('Eric', 'Zumbahlen', 'ejzumba', 'AwesomePossum', 'What''s your favorite Color', 'blue', 'ez@email.com');

INSERT INTO Users ( lName, fName, userID, password, question, answer, email ) 
    VALUES ('Kim', 'Eric', 'ehkim3', 'student', 'What''s your favorite Color', 'yellow', 'ek@email.com');

select * from USERS;

select * from users where email ='ek@email.com';