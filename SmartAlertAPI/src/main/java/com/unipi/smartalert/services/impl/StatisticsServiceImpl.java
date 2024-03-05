package com.unipi.smartalert.services.impl;

import com.unipi.smartalert.dtos.IncidentCategoryDTO;
import com.unipi.smartalert.exceptions.LanguageNotSupportedException;
import com.unipi.smartalert.models.ReportGroup;
import com.unipi.smartalert.services.IncidentCategoryService;
import com.unipi.smartalert.services.ReportGroupService;
import com.unipi.smartalert.services.StatisticsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private ReportGroupService reportGroupService;
    private IncidentCategoryService categoryService;

    @Override
    public Map<String, Double> getStatistics(int month, int year, String languageCode) {
        List<ReportGroup> groupList = reportGroupService.findAcceptedGroupsByMonthAndYear(month, year);
        List<IncidentCategoryDTO> categories = categoryService.findByLanguage(languageCode);

        Map<String, Double> statistics = new HashMap<>();

        categories.forEach(category -> statistics.put(category.getName(), 0.0));

        groupList.forEach(group -> {

            String category = group.getCategory().getNames().stream()
                    .filter(categoryName -> categoryName.getLanguage().equals(languageCode))
                    .findFirst()
                    .orElseThrow(() -> new LanguageNotSupportedException(String.format("Language %s is not supported", languageCode)))
                    .getName();

            statistics.put(category, statistics.getOrDefault(category, 0.0) + 1);
        });

        int total = groupList.size();

        statistics.forEach((category, value) -> {
            statistics.put(category, value / total);
        });

        return statistics;
    }

}
