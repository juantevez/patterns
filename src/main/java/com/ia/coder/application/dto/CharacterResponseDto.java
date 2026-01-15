package com.ia.coder.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CharacterResponseDto {
    private char character;
    private int position;
    private String fontFamily;
    private int fontSize;
    private String color;
    private boolean bold;
    private boolean italic;
    private boolean underline;
    
    public static CharacterResponseDto fromDomain(com.ia.coder.domain.Character character) {
        var style = character.getStyle();
        return new CharacterResponseDto(
            character.getCharacter(),
            character.getPosition(),
            style.getFontFamily(),
            style.getFontSize(),
            style.getColor(),
            style.isBold(),
            style.isItalic(),
            style.isUnderline()
        );
    }
}