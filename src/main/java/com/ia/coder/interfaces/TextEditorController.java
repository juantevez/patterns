package com.ia.coder.interfaces;

import com.ia.coder.application.TextEditorServiceImpl;
import com.ia.coder.application.dto.CharacterRequestDto;
import com.ia.coder.application.dto.CharacterResponseDto;
import com.ia.coder.application.dto.DocumentStatisticsDto;
import com.ia.coder.domain.service.TextEditorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/text-editor")
@CrossOrigin(origins = "*")
public class TextEditorController {
    
    private final TextEditorService textEditorService;
    
    public TextEditorController(TextEditorService textEditorService) {
        this.textEditorService = textEditorService;
    }
    
    @PostMapping("/character")
    public ResponseEntity<String> addCharacter(@Valid @RequestBody CharacterRequestDto request) {
        char c = request.getCharacter().charAt(0); // Get the first character from the string
        
        textEditorService.addCharacter(
            c, 
            request.getPosition(), 
            request.getFontFamily(), 
            request.getFontSize(), 
            request.getColor(),
            request.getBold(), 
            request.getItalic(), 
            request.getUnderline()
        );
        
        return ResponseEntity.ok("Character added successfully");
    }
    
    @GetMapping("/characters")
    public ResponseEntity<List<CharacterResponseDto>> getAllCharacters() {
        List<CharacterResponseDto> characters = textEditorService.getAllCharacters()
            .stream()
            .map(CharacterResponseDto::fromDomain)
            .collect(Collectors.toList());
        
        return ResponseEntity.ok(characters);
    }
    
    @GetMapping("/statistics")
    public ResponseEntity<DocumentStatisticsDto> getDocumentStatistics() {
        int totalCharacters = textEditorService.getAllCharacters().size();
        int uniqueStyles = textEditorService.getStylePoolSize();
        
        // Calculate memory saved ratio (theoretical calculation)
        // If we didn't use flyweight, we'd have style objects equal to character count
        double memorySavedRatio = totalCharacters > 0 ? 
            (double)(totalCharacters - uniqueStyles) / totalCharacters : 0.0;
        
        DocumentStatisticsDto stats = new DocumentStatisticsDto(totalCharacters, uniqueStyles, memorySavedRatio);
        return ResponseEntity.ok(stats);
    }
    
    @DeleteMapping("/clear")
    public ResponseEntity<String> clearDocument() {
        textEditorService.clearDocument();
        return ResponseEntity.ok("Document cleared successfully");
    }
    
    @PostMapping("/demo") 
    public ResponseEntity<String> runFlyweightDemo() {
        // Clear any existing content
        textEditorService.clearDocument();
        
        // Add characters with repeated styles to demonstrate flyweight pattern
        textEditorService.addCharacter('H', 0, "Arial", 12, "black", true, false, false);
        textEditorService.addCharacter('e', 1, "Arial", 12, "black", true, false, false); 
        textEditorService.addCharacter('l', 2, "Arial", 12, "black", true, false, false);
        textEditorService.addCharacter('l', 3, "Arial", 12, "black", true, false, false);
        textEditorService.addCharacter('o', 4, "Arial", 12, "black", true, false, false);
        
        // Add some differently styled characters
        textEditorService.addCharacter(' ', 5, "Times New Roman", 14, "blue", false, true, true);
        textEditorService.addCharacter('W', 6, "Arial", 12, "black", true, false, false); // Same style as before
        textEditorService.addCharacter('o', 7, "Arial", 12, "black", true, false, false); // Same style as before
        textEditorService.addCharacter('r', 8, "Arial", 12, "black", true, false, false); // Same style as before
        textEditorService.addCharacter('l', 9, "Arial", 12, "black", true, false, false); // Same style as before
        textEditorService.addCharacter('d', 10, "Arial", 12, "black", true, false, false); // Same style as before
        
        // Display the document to see flyweight in action
        textEditorService.displayDocument();
        
        return ResponseEntity.ok("Flyweight demo completed. Check console for details.");
    }
}