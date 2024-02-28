package com.unipi.smartalert.repositories;

import com.unipi.smartalert.models.IncidentReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncidentReportRepository extends JpaRepository<IncidentReport, Long> {
}
