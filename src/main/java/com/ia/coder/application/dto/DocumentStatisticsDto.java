package com.ia.coder.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DocumentStatisticsDto {
    private int totalCharacters;
    private int uniqueStyles;
    private double memorySavedRatio; // Ratio of memory saved due to flyweight pattern
}