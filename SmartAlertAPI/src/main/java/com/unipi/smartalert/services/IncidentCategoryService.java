package com.unipi.smartalert.services;

import com.unipi.smartalert.dtos.IncidentCategoryDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IncidentCategoryService {

    List<IncidentCategoryDTO> findByLanguage(String language);

}
