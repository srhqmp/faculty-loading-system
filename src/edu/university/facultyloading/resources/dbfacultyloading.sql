-- DROP and CREATE the database
DROP DATABASE IF EXISTS dbfacultyloading;
CREATE DATABASE dbfacultyloading;
USE dbfacultyloading;

-- USERS table (base for Admin and Faculty)
CREATE TABLE tblusers (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    role TINYINT NOT NULL, -- 1 = faculty, 2 = admin
    is_archived TINYINT(1) NOT NULL DEFAULT 0
);

-- ADMINS table (inherits from User)
CREATE TABLE tbladmins (
    admin_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES tblusers(user_id) ON DELETE CASCADE
);

-- FACULTIES table (inherits from User)
CREATE TABLE tblfaculties (
    faculty_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    major VARCHAR(100),
    years_of_experience INT,
    student_feedback_score DOUBLE,
    is_available TINYINT(1), -- 0 = no, 1 = yes
    FOREIGN KEY (user_id) REFERENCES tblusers(user_id) ON DELETE CASCADE
);

-- SUBJECTS table
CREATE TABLE tblsubjects (
    subject_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    recommended_major VARCHAR(100),
    complexity_level TINYINT,
    is_archived TINYINT(1) NOT NULL DEFAULT 0
);

-- LOADS table (assign subjects to faculty)
CREATE TABLE tblloads (
    load_id INT AUTO_INCREMENT PRIMARY KEY,
    faculty_id INT NOT NULL,
    subject_id INT NOT NULL,
    is_archived TINYINT(1) NOT NULL DEFAULT 0,
    FOREIGN KEY (faculty_id) REFERENCES tblfaculties(faculty_id) ON DELETE CASCADE,
    FOREIGN KEY (subject_id) REFERENCES tblsubjects(subject_id) ON DELETE CASCADE
);

-- SEED DATA

-- Users (5 Admins, 10 Faculty)
INSERT INTO tblusers (username, password, first_name, last_name, role) VALUES
('admin1', 'adminpass1', 'Alice', 'Admin', 2),
('admin2', 'adminpass2', 'Bob', 'Admin', 2),
('admin3', 'adminpass3', 'Clara', 'Admin', 2),
('admin4', 'adminpass4', 'David', 'Admin', 2),
('admin5', 'adminpass5', 'Eva', 'Admin', 2),

('acegutierrez', '123', 'Ace', 'Gutierrez', 2),
('jamesvalmeo', '123', 'James', 'Valmeo', 2),
('ramonlacandola', '123', 'Ramon', 'Lacandola', 2),
('ruffagranale', '123', 'Ruffa', 'Granale', 2),
('sarahquimpo', '123', 'Sarah Jane', 'Quimpo', 2),

('faculty1', 'facultypass1', 'Charlie', 'Clark', 1),
('faculty2', 'facultypass2', 'Dana', 'Doe', 1),
('faculty3', 'facultypass3', 'Ethan', 'Edwards', 1),
('faculty4', 'facultypass4', 'Fiona', 'Foster', 1),
('faculty5', 'facultypass5', 'George', 'Green', 1),
('faculty6', 'facultypass6', 'Hannah', 'Hill', 1),
('faculty7', 'facultypass7', 'Ian', 'Irwin', 1),
('faculty8', 'facultypass8', 'Julia', 'Jones', 1),
('faculty9', 'facultypass9', 'Kevin', 'King', 1),
('faculty10', 'facultypass10', 'Laura', 'Lane', 1);

-- Admins
INSERT INTO tbladmins (user_id) VALUES 
(1), (2), (3), (4), (5), 
(6), (7), (8), (9), (10);

-- Faculties
INSERT INTO tblfaculties (user_id, major, years_of_experience, student_feedback_score, is_available) VALUES
(6, 'Computer Science', 5, 4.5, 1),
(7, 'Mathematics', 3, 4.2, 1),
(8, 'Physics', 4, 4.0, 1),
(9, 'Chemistry', 2, 3.8, 1),
(10, 'Biology', 6, 4.6, 1),
(11, 'English Literature', 7, 4.1, 0),
(12, 'Philosophy', 8, 4.3, 1),
(13, 'Sociology', 3, 3.9, 1),
(14, 'Economics', 5, 4.4, 0),
(15, 'Political Science', 4, 4.2, 1);

-- Subjects
INSERT INTO tblsubjects (name, description, recommended_major, complexity_level, is_archived) VALUES
('Data Structures', 'Introduction to data structures', 'Computer Science', 3, 0),
('Calculus', 'Fundamentals of Calculus', 'Mathematics', 2, 0),
('Algorithms', 'Study of algorithms', 'Computer Science', 4, 0),
('Organic Chemistry', 'Basics of organic chemistry', 'Chemistry', 3, 0),
('Modern Physics', 'Concepts in modern physics', 'Physics', 4, 0),
('Microbiology', 'Study of microorganisms', 'Biology', 3, 0),
('English Grammar', 'Rules of English grammar', 'English Literature', 2, 0),
('Ethics', 'Introduction to ethics', 'Philosophy', 2, 0),
('Macroeconomics', 'Economics on a large scale', 'Economics', 3, 0),
('Political Theory', 'Foundations of political science', 'Political Science', 3, 0);

-- Load Assignments
INSERT INTO tblloads (faculty_id, subject_id, is_archived) VALUES
(1, 1, 0), (1, 3, 0), (1, 2, 0), (1, 4, 0),
(2, 2, 0), (2, 5, 0), (2, 6, 0),
(3, 5, 0), (3, 7, 0), (3, 8, 0), (3, 3, 0),
(4, 4, 0), (4, 9, 0), (4, 10, 0),
(5, 6, 0), (5, 1, 0), (5, 2, 0), (5, 8, 0), (5, 7, 0),
(6, 7, 0), (6, 9, 0), (6, 3, 0),
(7, 8, 0), (7, 10, 0), (7, 1, 0), (7, 2, 0),
(8, 9, 0), (8, 4, 0), (8, 5, 0), (8, 3, 0),
(9, 10, 0), (9, 6, 0), (9, 7, 0),
(10, 1, 0), (10, 2, 0), (10, 3, 0), (10, 4, 0), (10, 5, 0);
