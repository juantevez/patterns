package com.ia.coder.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Flyweight factory that manages CharacterStyle objects.
 * It ensures that identical character styles are shared among multiple characters.
 */
public class CharacterStyleFactory {
    private static final Map<CharacterStyleKey, CharacterStyle> stylePool = new HashMap<>();
    
    /**
     * Creates or retrieves an existing CharacterStyle based on the given parameters.
     * This is the core of the Flyweight pattern - sharing objects with identical intrinsic state.
     */
    public static CharacterStyle getCharacterStyle(String fontFamily, int fontSize, String color, 
                                                  boolean bold, boolean italic, boolean underline) {
        CharacterStyleKey key = new CharacterStyleKey(fontFamily, fontSize, color, bold, italic, underline);
        
        CharacterStyle style = stylePool.get(key);
        if (style == null) {
            style = new CharacterStyle(fontFamily, fontSize, color, bold, italic, underline);
            stylePool.put(key, style);
            System.out.println("Created new CharacterStyle: " + style);
        } else {
            System.out.println("Reused existing CharacterStyle: " + style);
        }
        
        return style;
    }
    
    /**
     * Returns the number of unique character styles in the pool.
     */
    public static int getPoolSize() {
        return stylePool.size();
    }
    
    /**
     * Clears the style pool (for testing purposes).
     */
    public static void clearPool() {
        stylePool.clear();
    }
    
    /**
     * Private static class to represent the key for the style pool map.
     * This ensures proper equality comparison for CharacterStyle objects.
     */
    private static class CharacterStyleKey {
        private final String fontFamily;
        private final int fontSize;
        private final String color;
        private final boolean bold;
        private final boolean italic;
        private final boolean underline;
        
        public CharacterStyleKey(String fontFamily, int fontSize, String color, 
                                boolean bold, boolean italic, boolean underline) {
            this.fontFamily = fontFamily;
            this.fontSize = fontSize;
            this.color = color;
            this.bold = bold;
            this.italic = italic;
            this.underline = underline;
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            
            CharacterStyleKey that = (CharacterStyleKey) obj;
            
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
    }
}