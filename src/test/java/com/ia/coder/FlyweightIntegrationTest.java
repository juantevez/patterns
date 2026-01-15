package com.ia.coder;

import com.ia.coder.domain.CharacterStyleFactory;
import com.ia.coder.domain.service.TextEditorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class FlyweightIntegrationTest {

    @Autowired
    private TextEditorService textEditorService;

    @Test
    void testFlyweightPatternEfficiency() {
        // Clear any existing content
        textEditorService.clearDocument();
        
        // Add multiple characters with the same style
        for (int i = 0; i < 10; i++) {
            textEditorService.addCharacter((char) ('A' + i), i, "Arial", 12, "black", true, false, false);
        }
        
        // Add some characters with different styles
        textEditorService.addCharacter('X', 10, "Times New Roman", 14, "red", false, true, false);
        textEditorService.addCharacter('Y', 11, "Times New Roman", 14, "red", false, true, false);
        
        // Even though we have 12 characters, we should only have 2 unique styles
        assertEquals(12, textEditorService.getAllCharacters().size());
        assertEquals(2, textEditorService.getStylePoolSize());
        
        // The factory should have reused the same style objects
        assertEquals(2, CharacterStyleFactory.getPoolSize());
    }

    @Test
    void testDifferentStylesCreateNewObjects() {
        // Clear any existing content
        textEditorService.clearDocument();
        
        // Add characters with different styles
        textEditorService.addCharacter('A', 0, "Arial", 12, "black", true, false, false);
        textEditorService.addCharacter('B', 1, "Times New Roman", 14, "red", false, true, false);
        textEditorService.addCharacter('C', 2, "Arial", 12, "black", true, false, false); // Same as A
        textEditorService.addCharacter('D', 3, "Arial", 16, "black", true, false, false); // Different font size
        textEditorService.addCharacter('E', 4, "Arial", 12, "black", true, false, false); // Same as A
        
        // We should have 5 characters but only 3 unique styles
        assertEquals(5, textEditorService.getAllCharacters().size());
        assertEquals(3, textEditorService.getStylePoolSize());
    }
}