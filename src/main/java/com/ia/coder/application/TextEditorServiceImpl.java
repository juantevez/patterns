package com.ia.coder.application;

import com.ia.coder.domain.Character;
import com.ia.coder.domain.CharacterStyle;
import com.ia.coder.domain.CharacterStyleFactory;
import com.ia.coder.domain.service.TextEditorService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Application service implementation that uses the Flyweight pattern.
 * This is the adapter between the domain and infrastructure layers in hexagonal architecture.
 */
@Service
public class TextEditorServiceImpl implements TextEditorService {
    
    private final List<Character> characters = new ArrayList<>();
    
    @Override
    public void addCharacter(char c, int position, String fontFamily, int fontSize, String color,
                           boolean bold, boolean italic, boolean underline) {
        // Using the Flyweight factory to get or create a CharacterStyle
        // This demonstrates the Flyweight pattern - sharing styles among many characters
        CharacterStyle style = CharacterStyleFactory.getCharacterStyle(
            fontFamily, fontSize, color, bold, italic, underline
        );
        
        Character character = new Character(c, position, style);
        characters.add(character);
    }
    
    @Override
    public List<Character> getAllCharacters() {
        return new ArrayList<>(characters);
    }
    
    @Override
    public int getStylePoolSize() {
        return CharacterStyleFactory.getPoolSize();
    }
    
    @Override
    public void displayDocument() {
        System.out.println("=== Document Content ===");
        for (Character character : characters) {
            character.display();
        }
        System.out.printf("Total characters: %d, Unique styles: %d%n", 
                         characters.size(), CharacterStyleFactory.getPoolSize());
    }
    
    @Override
    public void clearDocument() {
        characters.clear();
        CharacterStyleFactory.clearPool();
    }
}