package com.ia.coder.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class CharacterRequestDto {
    @NotBlank(message = "Character cannot be blank")
    private String character; // Single character as string
    
    @NotNull(message = "Position is required")
    @Min(value = 0, message = "Position must be non-negative")
    private Integer position;
    
    @NotBlank(message = "Font family is required")
    private String fontFamily;
    
    @NotNull(message = "Font size is required")
    @Min(value = 1, message = "Font size must be positive")
    private Integer fontSize;
    
    @NotBlank(message = "Color is required")
    private String color;
    
    private Boolean bold = false;
    private Boolean italic = false;
    private Boolean underline = false;
}