
create table USERS (
    fName varchar(50),
    lName varchar (50),
    userID varchar (50) ,
    password varchar(50),
    question varchar (50),
    answer varchar (50),
    email varchar (50)
);

INSERT INTO Users ( fName, lName, userID, password, question, answer, email ) 
    VALUES ('Eric', 'Zumbahlen', 'ejzumba', 'AwesomePossum', 'What''s your favorite Color', 'blue', 'ez@email.com');

INSERT INTO Users ( lName, fName, userID, password, question, answer, email ) 
    VALUES ('Kim', 'Eric', 'ehkim3', 'student', 'What''s your favorite Color', 'yellow', 'ek@email.com');

select * from USERS;

select * from users where email ='ek@email.com';