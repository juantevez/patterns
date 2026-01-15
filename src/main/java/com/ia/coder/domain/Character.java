package com.ia.coder.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Represents a character with its position and style.
 * The style is shared among many characters using the flyweight pattern.
 */
@Getter
@AllArgsConstructor
public class Character {
    private final char character;
    private final int position;
    private final CharacterStyle style;
    
    public void display() {
        System.out.printf("Character '%c' at position %d with style: %s%n", 
                         character, position, style);
    }
    
    @Override
    public String toString() {
        return String.format("Character{character=%c, position=%d, style=%s}", 
                           character, position, style);
    }
}