package com.example.flywaytask.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "subjects")
@Audited
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_subject")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "created_on", nullable = false, updatable = false)
    @CreatedDate
    private long createdDate;

    @Column(name = "updated_on")
    @LastModifiedDate
    private long modifiedDate;

    @OneToMany(mappedBy = "subject")
    @NotAudited
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<ExamMark> examMarks = new HashSet<>();
}
