package com.example.flywaytask.services.audit;

import java.util.List;

public interface StudentsAuditService {

    List<?> getRevisions(int id, boolean fetchChanges);
}
