CREATE TABLE revision_info
(
    revision_id   INT NOT NULL AUTO_INCREMENT,
    rev_timestamp BIGINT NOT NULL,
    PRIMARY KEY (revision_id)
);

CREATE TABLE students_audition
(
    revision_id   INT      NOT NULL,
    id_student    INT      NOT NULL,
    revision_type SMALLINT NOT NULL,
    first_name    VARCHAR(50) NULL,
    last_name     VARCHAR(50) NULL,
    birth_date    DATE NULL,
    PRIMARY KEY (revision_id, id_student),
    CONSTRAINT fk_students_revinfo_rev_id
        FOREIGN KEY (revision_id)
            REFERENCES revision_info (revision_id)
);

CREATE TABLE exam_marks_audition
(
    revision_id   INT      NOT NULL,
    id            INT      NOT NULL,
    revision_type SMALLINT NOT NULL,
    id_student    INT NULL,
    id_subject    INT NULL,
    mark          INT NULL,
    PRIMARY KEY (revision_id, id),
    CONSTRAINT fk_exam_marks_revinfo_rev_id
        FOREIGN KEY (revision_id)
            REFERENCES revision_info (revision_id)
);
