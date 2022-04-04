CREATE TABLE students
(
    id_student INT         NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(50) NULL,
    last_name  VARCHAR(50) NULL,
    birth_date DATE        NULL,
    PRIMARY KEY (id_student)
);
CREATE UNIQUE INDEX id_student_idx ON students (id_student);

CREATE TABLE subjects
(
    id_subject INT         NOT NULL AUTO_INCREMENT,
    name       VARCHAR(45) NULL,
    PRIMARY KEY (id_subject)
);
CREATE UNIQUE INDEX id_subject_idx ON subjects (id_subject);

CREATE TABLE exam_marks
(
    id         INT NOT NULL AUTO_INCREMENT,
    id_student INT NULL,
    id_subject INT NULL,
    mark       INT NULL,
    PRIMARY KEY (id),
    CONSTRAINT id_student
        FOREIGN KEY (id_student)
            REFERENCES students (id_student)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT id_subject
        FOREIGN KEY (id_subject)
            REFERENCES subjects (id_subject)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);
CREATE INDEX id_student_index ON exam_marks (id_student);
CREATE INDEX id_subject_index ON exam_marks (id_subject);
