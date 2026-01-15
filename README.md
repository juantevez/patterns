# Flyweight Pattern Implementation with Hexagonal Architecture

This project demonstrates the **Flyweight Design Pattern** implemented using **Java 17**, **Spring Boot**, and **Hexagonal Architecture**. The example simulates a text editor where character formatting is optimized using the Flyweight pattern.

## 🎯 Real-World Use Case

The Flyweight pattern is implemented in a text editor scenario where:

- Each character in a document has formatting properties (font, size, color, etc.)
- Instead of storing formatting data with each character (which would be memory-intensive),
- Common formatting styles are shared among multiple characters
- This dramatically reduces memory consumption when handling large documents

## 🏗️ Architecture Overview

```
┌─────────────────┐    ┌──────────────────┐    ┌──────────────────┐
│   Interfaces    │    │  Application     │    │      Domain      │
│                 │    │                  │    │                  │
│ REST Controller │───▶│ Service Impl     │───▶│ Service Interface│
│                 │    │                  │    │                  │
└─────────────────┘    └──────────────────┘    └──────────────────┘
                              │
                       ┌──────────────────┐
                       │ Infrastructure   │
                       │                  │
                       │ Data Adapters,   │
                       │ External APIs    │
                       └──────────────────┘
```

## 🧱 Project Structure

```
src/
├── main/
│   ├── java/com/ia/coder/
│   │   ├── domain/                 # Domain entities and flyweight factory
│   │   │   ├── Character.java      # Character entity with shared style
│   │   │   ├── CharacterStyle.java # Flyweight object (immutable)
│   │   │   └── CharacterStyleFactory.java # Flyweight factory
│   │   ├── domain/service/         # Domain service interfaces
│   │   │   └── TextEditorService.java
│   │   ├── application/            # Application services
│   │   │   ├── TextEditorServiceImpl.java
│   │   │   └── dto/                # Data Transfer Objects
│   │   │       ├── CharacterRequestDto.java
│   │   │       ├── CharacterResponseDto.java
│   │   │       └── DocumentStatisticsDto.java
│   │   ├── interfaces/             # API endpoints
│   │   │   └── TextEditorController.java
│   │   └── FlyweightApplication.java
│   └── resources/
└── test/
    └── java/com/ia/coder/
        └── FlyweightIntegrationTest.java
```

## 🛠️ Pattern Implementation Details

### Flyweight Components:

1. **CharacterStyle** (Flyweight Object): Immutable object containing intrinsic state (formatting properties)
2. **Character** (Context): Contains extrinsic state (position) and holds reference to shared style
3. **CharacterStyleFactory** (Flyweight Factory): Manages the pool of shared objects

### Memory Optimization:

- Without Flyweight: N characters × M formatting properties = N×M space
- With Flyweight: N characters + K unique styles = N + K×M space (where K << N)

## 🚀 Getting Started

### Prerequisites

- Java 17
- Maven 3.6+

### Build and Run

```bash
# Clone the repository
git clone <repository-url>
cd <repository-name>

# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

The application will be available at `http://localhost:8080`

### API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/text-editor/character` | Add a character with formatting |
| GET | `/api/text-editor/characters` | Get all characters in the document |
| GET | `/api/text-editor/statistics` | Get document statistics and memory savings |
| DELETE | `/api/text-editor/clear` | Clear the entire document |
| POST | `/api/text-editor/demo` | Run a Flyweight pattern demonstration |

### Example Usage

#### Add Characters
```bash
curl -X POST http://localhost:8080/api/text-editor/character \
  -H "Content-Type: application/json" \
  -d '{
    "character": "H",
    "position": 0,
    "fontFamily": "Arial",
    "fontSize": 12,
    "color": "black",
    "bold": true,
    "italic": false,
    "underline": false
  }'
```

#### Run Demo
```bash
curl -X POST http://localhost:8080/api/text-editor/demo
```

This endpoint demonstrates the Flyweight pattern by adding multiple characters with repeated styles and showing how the factory reuses existing style objects.

#### Get Statistics
```bash
curl -X GET http://localhost:8080/api/text-editor/statistics
```

Returns information about total characters, unique styles, and theoretical memory savings.

## 🔍 Pattern Benefits

1. **Memory Efficiency**: Significantly reduces memory usage by sharing common objects
2. **Performance**: Faster object creation since we reuse existing instances
3. **Scalability**: Enables handling of large documents without memory issues
4. **Maintainability**: Centralized style management through the factory

## 🧪 Testing

Run the tests with:
```bash
mvn test
```

The integration tests verify that the Flyweight pattern works correctly by checking that identical styles are properly shared among characters.

## 📋 Key Concepts Demonstrated

- **Intrinsic State**: Character formatting properties (shared)
- **Extrinsic State**: Character position (unique per character)
- **Object Pool**: Factory managing shared style objects
- **Immutability**: Flyweight objects are immutable to ensure safe sharing
- **Hash-based Lookup**: Efficient retrieval of existing objects using hash maps

## 🏆 Conclusion

This implementation showcases how the Flyweight pattern can optimize resource usage in applications that handle large numbers of similar objects. The hexagonal architecture ensures clean separation of concerns while maintaining the benefits of the design pattern.

The text editor example demonstrates a practical use case where memory optimization is crucial for performance with large documents.