package com.unipi.smartalert.repositories;

import com.unipi.smartalert.dtos.IncidentCategoryDTO;
import com.unipi.smartalert.models.IncidentCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IncidentCategoryRepository extends JpaRepository<IncidentCategory, Long> {

    @Query("SELECT new com.unipi.smartalert.dtos.IncidentCategoryDTO(ic.id, icn.name) " +
            "FROM IncidentCategory ic JOIN ic.names icn WHERE icn.language = :language")
    List<IncidentCategoryDTO> findByLanguage(@Param("language") String language);

}
