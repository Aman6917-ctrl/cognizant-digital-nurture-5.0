package com.cognizant.ems.multidatasource.secondary.repository;

import com.cognizant.ems.multidatasource.secondary.entity.AuditEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditEventRepository extends JpaRepository<AuditEvent, Long> {
}
