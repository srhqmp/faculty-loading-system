-- DROP and CREATE the database
DROP DATABASE IF EXISTS dbfacultyloading;
CREATE DATABASE dbfacultyloading;
USE dbfacultyloading;

-- USERS table
CREATE TABLE tblusers (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    role TINYINT NOT NULL, -- 1 = faculty, 2 = admin
    is_archived TINYINT(1) NOT NULL DEFAULT 0
);

-- ADMINS
CREATE TABLE tbladmins (
    admin_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES tblusers(user_id) ON DELETE CASCADE
);

-- FACULTIES
CREATE TABLE tblfaculties (
    faculty_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    major VARCHAR(100),
    years_of_experience INT,
    student_feedback_score DOUBLE,
    is_available TINYINT(1),
    FOREIGN KEY (user_id) REFERENCES tblusers(user_id) ON DELETE CASCADE
);

-- SUBJECTS (only 15 subjects)
CREATE TABLE tblsubjects (
    subject_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    recommended_major VARCHAR(100),
    complexity_level TINYINT,
    is_archived TINYINT(1) NOT NULL DEFAULT 0
);

-- LOADS
CREATE TABLE tblloads (
    load_id INT AUTO_INCREMENT PRIMARY KEY,
    faculty_id INT NOT NULL,
    subject_id INT NOT NULL,
    is_archived TINYINT(1) NOT NULL DEFAULT 0,
    FOREIGN KEY (faculty_id) REFERENCES tblfaculties(faculty_id) ON DELETE CASCADE,
    FOREIGN KEY (subject_id) REFERENCES tblsubjects(subject_id) ON DELETE CASCADE
);

-- USERS (Admins + Faculties)
INSERT INTO tblusers (username, password, first_name, last_name, role) VALUES
-- Admins
('acegutierrez', '123', 'Ace', 'Gutierrez', 2),
('jamesvalmeo', '123', 'James', 'Valmeo', 2),
('ramonlacandola', '123', 'Ramon', 'Lacandola', 2),
('ruffagranale', '123', 'Ruffa', 'Granale', 2),
('sarahquimpo', '123', 'Sarah Jane', 'Quimpo', 2),
-- Faculties
('faculty1', 'facultypass1', 'Aiah', 'Bautista', 1),
('faculty2', 'facultypass2', 'Colet', 'Vergara', 1),
('faculty3', 'facultypass3', 'Maloi', 'Tugade', 1),
('faculty4', 'facultypass4', 'Gwen', 'Apuli', 1),
('faculty5', 'facultypass5', 'Stacey', 'Delos Santos', 1),
('faculty6', 'facultypass6', 'Mikha', 'Lim Jimenea', 1),
('faculty7', 'facultypass7', 'Jhoanna', 'Robles', 1),
('faculty8', 'facultypass8', 'Sheena', 'Cabral', 1),
('faculty9', 'facultypass9', 'Zild', 'Benitez', 1),
('faculty10', 'facultypass10', 'Unique', 'Salonga', 1);

-- ADMINS
INSERT INTO tbladmins (user_id) VALUES (1), (2), (3), (4), (5);

-- FACULTIES
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

-- SUBJECTS (Only 15 total)
INSERT INTO tblsubjects (name, description, recommended_major, complexity_level) VALUES
('Data Structures', 'Intro to data structures', 'Computer Science', 3),
('Calculus', 'Calculus I', 'Mathematics', 2),
('Algorithms', 'Basic algorithms', 'Computer Science', 4),
('Organic Chemistry', 'Intro to organic chem', 'Chemistry', 3),
('Modern Physics', 'Modern physics concepts', 'Physics', 4),
('Microbiology', 'Study of microbes', 'Biology', 3),
('English Grammar', 'Rules of grammar', 'English Literature', 2),
('Ethics', 'Moral philosophy', 'Philosophy', 2),
('Macroeconomics', 'Large-scale economics', 'Economics', 3),
('Political Theory', 'Political ideologies', 'Political Science', 3),
('Operating Systems', 'OS fundamentals', 'Computer Science', 4),
('Linear Algebra', 'Matrix math', 'Mathematics', 3),
('Thermodynamics', 'Energy systems', 'Physics', 3),
('Genetics', 'Genetic principles', 'Biology', 3),
('Sociological Theories', 'Sociology foundations', 'Sociology', 3);


