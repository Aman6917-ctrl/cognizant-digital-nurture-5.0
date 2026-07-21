package com.cognizant.ems.multidatasource.web;

import com.cognizant.ems.multidatasource.secondary.entity.AuditEvent;
import com.cognizant.ems.multidatasource.secondary.repository.AuditEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/audit-events")
@RequiredArgsConstructor
public class AuditEventController {

    private final AuditEventRepository auditEventRepository;

    @GetMapping
    public List<AuditEvent> list() {
        return auditEventRepository.findAll();
    }
}
