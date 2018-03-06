USE contacts;
Create Table contacts
(
    contactID INT NOT NULL auto_increment primary key,
    firstName VARCHAR(30),
    lastName VARCHAR(30),
    phoneNumber VARCHAR(12),
    address VARCHAR(50),
    birthDay DATE,
    imageFile VARCHAR(100)
);

select * from contacts;

insert into contacts (firstName, lastName, phoneNumber,address, birthday, imageFile)
 Values
       ('Harmandeep' , 'Singh' , '705-970-7777' , '198 livingstone st e' ,'1998-05-22' , 'some files' );
