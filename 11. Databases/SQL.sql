CREATE TABLE Department
(
    dept_id SERIAL PRIMARY KEY,
    name    VARCHAR(30) NOT NULL,
    phone   VARCHAR(15)
);

CREATE TABLE Employee
(
    emp_id     SERIAL PRIMARY KEY,
    last_name  VARCHAR(20) NOT NULL,
    first_name VARCHAR(20) NOT NULL,
    position   VARCHAR(20),
    dept_id    INT,
    CONSTRAINT fk_department FOREIGN KEY (dept_id)
        REFERENCES Department (dept_id) ON DELETE SET NULL
);

CREATE TABLE Task
(
    task_id     SERIAL PRIMARY KEY,
    description VARCHAR(50) NOT NULL,
    emp_id      INT,
    CONSTRAINT fk_employee FOREIGN KEY (emp_id)
        REFERENCES Employee (emp_id) ON DELETE CASCADE
);


-- Departments
INSERT INTO Department (name, phone)
VALUES ('IT', '123-456-789'),
       ('HR', '987-654-321'),
       ('Finance', '555-123-456'),
       ('Marketing', '222-333-444'),
       ('Operations', '111-222-333');

-- Employees
INSERT INTO Employee (last_name, first_name, position, dept_id)
VALUES
-- IT
('Priadchenko', 'Valerii', 'Developer', 1),
('Sokolov', 'Oleksandr', 'Tester', 1),
('Lomakin', 'Maksym', 'System Administrator', 1),
('Kuzma', 'Danil', 'DevOps Engineer', 1),

-- HR
('Regesha', 'Andrii', 'HR Manager', 2),
('Pankova', 'Dariia', 'Recruiter', 2),
('Antiuk', 'Yulia', 'HR Specialist', 2),

-- Finance
('Sviachenyi', 'Yevhen', 'Accountant', 3),
('Pikovskyi', 'Volodymyr', 'Financial Analyst', 3),
('Halahan', 'Daniil', 'Junior Accountant', 3),

-- Marketing
('Vashchenko', 'Anhelina', 'Marketing Manager', 4),
('Protsenko', 'Klymentii', 'Content Specialist', 4),
('Tkachuk', 'Daria', 'SMM Specialist', 4),

-- Operations
('Tkachenko', 'Ruslan', 'Operations Manager', 5),
('Khrebtan', 'Maksym', 'Logistics Specialist', 5),
('Shulha', 'Nikita', 'Support Engineer', 5);

-- Tasks
INSERT INTO Task (description, emp_id)
VALUES
-- IT
('Fix bugs in project', 1),
('Deploy new version', 1),
('Write test cases', 2),
('Configure CI/CD pipeline', 4),
('Maintain servers', 3),

-- HR
('Recruit new staff', 5),
('Organize corporate training', 6),
('Update HR policies', 7),

-- Finance
('Prepare quarterly report', 8),
('Analyze budget variance', 9),
('Process invoices', 10),

-- Marketing
('Create social media campaign', 12),
('Design new landing page', 11),
('Write blog post', 13),

-- Operations
('Optimize delivery routes', 14),
('Check warehouse stock', 15),
('Handle customer requests', 16);

DROP TABLE Department CASCADE;
DROP TABLE Employee CASCADE;
DROP TABLE Task CASCADE;
