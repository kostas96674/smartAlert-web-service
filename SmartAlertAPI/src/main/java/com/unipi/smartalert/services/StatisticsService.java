package com.unipi.smartalert.services;

import java.util.Map;

public interface StatisticsService {

    Map<String, Double> getStatistics(int month, int year, String languageCode);

}
