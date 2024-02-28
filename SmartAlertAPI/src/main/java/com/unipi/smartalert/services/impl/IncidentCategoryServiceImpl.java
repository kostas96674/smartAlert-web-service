package com.unipi.smartalert.services.impl;

import com.unipi.smartalert.dtos.IncidentCategoryDTO;
import com.unipi.smartalert.exceptions.LanguageNotSupportedException;
import com.unipi.smartalert.models.IncidentCategory;
import com.unipi.smartalert.repositories.IncidentCategoryRepository;
import com.unipi.smartalert.services.IncidentCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class IncidentCategoryServiceImpl implements IncidentCategoryService {

    private IncidentCategoryRepository repository;

    @Override
    public List<IncidentCategoryDTO> findByLanguage(String language) {
        var incidentCategoryDTOList = repository.findByLanguage(language);
        if (incidentCategoryDTOList.isEmpty())
            throw new LanguageNotSupportedException("Language '" + language + "' is not supported");
        return incidentCategoryDTOList;
    }

    @Override
    public IncidentCategory findById(long id) {
        // Todo: Throw RecourseNotFoundException
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Incident category with id " + id + " not found"));
    }

}
