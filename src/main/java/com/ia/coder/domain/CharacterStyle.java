package com.ia.coder.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Immutable flyweight object that represents character formatting properties
 */
@Getter
@AllArgsConstructor
public class CharacterStyle {
    private final String fontFamily;
    private final int fontSize;
    private final String color;
    private final boolean bold;
    private final boolean italic;
    private final boolean underline;
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        CharacterStyle that = (CharacterStyle) obj;
        
        if (fontSize != that.fontSize) return false;
        if (bold != that.bold) return false;
        if (italic != that.italic) return false;
        if (underline != that.underline) return false;
        if (!fontFamily.equals(that.fontFamily)) return false;
        return color.equals(that.color);
    }
    
    @Override
    public int hashCode() {
        int result = fontFamily.hashCode();
        result = 31 * result + fontSize;
        result = 31 * result + color.hashCode();
        result = 31 * result + (bold ? 1 : 0);
        result = 31 * result + (italic ? 1 : 0);
        result = 31 * result + (underline ? 1 : 0);
        return result;
    }
    
    @Override
    public String toString() {
        return String.format("CharacterStyle{fontFamily='%s', fontSize=%d, color='%s', bold=%s, italic=%s, underline=%s}", 
                           fontFamily, fontSize, color, bold, italic, underline);
    }
}