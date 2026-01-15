package com.ia.coder.domain.service;

import com.ia.coder.domain.Character;

import java.util.List;

/**
 * Domain service interface for text editor functionality.
 * This represents the business logic layer in hexagonal architecture.
 */
public interface TextEditorService {
    /**
     * Adds a character with specific formatting to the document
     */
    void addCharacter(char c, int position, String fontFamily, int fontSize, String color, 
                     boolean bold, boolean italic, boolean underline);
    
    /**
     * Gets all characters in the document
     */
    List<Character> getAllCharacters();
    
    /**
     * Gets the size of the style pool (for demonstration of Flyweight pattern efficiency)
     */
    int getStylePoolSize();
    
    /**
     * Displays all characters in the document
     */
    void displayDocument();
    
    /**
     * Clears the entire document
     */
    void clearDocument();
}