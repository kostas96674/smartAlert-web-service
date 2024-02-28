package com.unipi.smartalert.services;

import com.unipi.smartalert.dtos.IncidentCategoryDTO;
import com.unipi.smartalert.models.IncidentCategory;

import java.util.List;

public interface IncidentCategoryService {

    List<IncidentCategoryDTO> findByLanguage(String language);

    IncidentCategory findById(long id);

}
