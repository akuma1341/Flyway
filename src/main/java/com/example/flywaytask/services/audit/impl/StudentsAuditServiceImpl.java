package com.example.flywaytask.services.audit.impl;

import com.example.flywaytask.entities.Student;
import com.example.flywaytask.services.audit.StudentsAuditService;
import lombok.RequiredArgsConstructor;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentsAuditServiceImpl implements StudentsAuditService {

    private final AuditReader auditReader;

    @Override
    public List<?> getRevisions(int id, boolean fetchChanges) {
        AuditQuery auditQuery;

        if (fetchChanges) {
            auditQuery = auditReader.createQuery()
                    .forRevisionsOfEntityWithChanges(Student.class, true);
        } else {
            auditQuery = auditReader.createQuery()
                    .forRevisionsOfEntity(Student.class, true);
        }
        auditQuery.add(AuditEntity.id().eq(id));
        return auditQuery.getResultList();
    }
}
