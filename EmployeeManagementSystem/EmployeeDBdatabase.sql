CREATE DATABASE Employee_Management_System;
USE Employee_Management_System;

-- 1. Department Table
CREATE TABLE Department (
    Dep_ID INT PRIMARY KEY AUTO_INCREMENT,
    Dep_Name VARCHAR(50) NOT NULL
);

-- 2. User Table (Using backticks for reserved word 'User')

-- 3. Payroll Table
CREATE TABLE Payroll (
    P_Id INT PRIMARY KEY AUTO_INCREMENT,
    Amount DECIMAL(18, 2) NOT NULL
);

-- 4. Employee Table 
CREATE TABLE Employee (
    Emp_ID VARCHAR(10) PRIMARY KEY NOT NULL, -- Format like 'EMP001'
    Emp_Name VARCHAR(50) NOT NULL,
    Emp_Email VARCHAR(50) NOT NULL,
    `Password` VARCHAR(10) NOT NULL,
    Role VARCHAR(20) NOT NULL,
    Dep_ID INT NOT NULL,
    P_Id INT NOT NULL,
    -- Foreign Keys for Data Integrity (LO7: Design Patterns/Standards)
    CONSTRAINT fk_department FOREIGN KEY (Dep_ID) REFERENCES Department(Dep_ID) ON DELETE CASCADE,
    CONSTRAINT fk_payroll FOREIGN KEY (P_Id) REFERENCES Payroll(P_Id) ON DELETE CASCADE
);

INSERT INTO Department (Dep_Name) VALUES 
('Human Resources'),
('Information Technology'),
('Finance'),
('Marketing');



-- P_Id will be 1, 2, 3, 4 automatically
INSERT INTO Payroll (Amount) VALUES 
(45000.00),  -- Junior Level
(75000.00),  -- Senior Level
(120000.00), -- Management Level
(250000.00); -- Executive Level

-- Linking existing Users to Employee Profiles
-- Format: (Emp_ID, Name, Email, Dep_ID, User_ID, P_Id)

INSERT INTO Employee (Emp_ID, Emp_Name, Emp_Email,`Password`,Role, Dep_ID,  P_Id) VALUES 
('EMP001', 'John Doe', 'jdoe@staffsync.com', 'john12','Employee',1, 1),      
('EMP002', 'Alice Smith', 'alice.s@staffsync.com','alice12','Manager',2, 4), 
('EMP003', 'Kamal Perera', 'kamal.p@staffsync.com','kamal12','Employee',1, 2 ); 


CREATE TABLE Admin (
    Admin_ID VARCHAR(10) PRIMARY KEY NOT NULL, -- Format like 'A001'
    Admin_Name VARCHAR(50) NOT NULL, 
   `Password` VARCHAR(10) NOT NULL,
    Role VARCHAR(20) NOT NULL
    );
    
    ALTER TABLE Admin
ADD Admin_Email VARCHAR(255);

    
    INSERT INTO Admin (Admin_ID, Admin_Name,`Password`,Role,Admin_Email) VALUES 
('A001', 'Admin', 'Admin12','Admin','admin12@gmail.com');

SELECT * FROM employee_management_system.employee;

SELECT * FROM employee_management_system.admin;

SELECT * FROM Admin;
SELECT Emp_Name AS Name, Emp_Email AS Email, Password, Role FROM Employee
UNION
SELECT Admin_Name AS Name, Admin_Email AS Email, Password, 'Admin' AS Role FROM Admin;
