package com.unipi.smartalert.repositories;

import com.unipi.smartalert.models.ReportGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ReportGroupRepository extends JpaRepository<ReportGroup, Long> {

    @Query("SELECT rg FROM ReportGroup rg WHERE rg.status = 'ACCEPTED' AND rg.lastUpdated >= :twentyFourHoursAgo")
    List<ReportGroup> findAllAcceptedGroupsWithin24Hours(@Param("twentyFourHoursAgo") LocalDateTime twentyFourHoursAgo);

}
