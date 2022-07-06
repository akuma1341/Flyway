CREATE TABLE subjects_audition
(
    revision_id   INT      NOT NULL,
    id_subject    INT      NOT NULL,
    revision_type SMALLINT NOT NULL,
    name          VARCHAR(50) NULL,
    created_on    DATETIME NULL,
    updated_on    DATETIME NULL,
    PRIMARY KEY (revision_id, id_subject),
    CONSTRAINT fk_subjects_revinfo_rev_id
        FOREIGN KEY (revision_id)
            REFERENCES revision_info (revision_id)
);