package com.ia.coder.proxy;

import com.ia.coder.proxy.interfaces.FileController;

/**
 * Clase principal para demostrar el patrón Proxy con arquitectura hexagonal
 */
public class App {
    
    public static void main(String[] args) {
        System.out.println("Demostración del patrón Proxy con Arquitectura Hexagonal");
        System.out.println("=====================================================");
        
        // Crear un controlador con rol de usuario regular
        System.out.println("\n--- Pruebas con usuario 'reader' (solo lectura) ---");
        FileController readerController = new FileController("reader");
        
        // El usuario 'reader' puede leer archivos
        try {
            readerController.write("test.txt", "Contenido de prueba");
        } catch (Exception e) {
            System.out.println("Error esperado (usuario reader no puede escribir): " + e.getMessage());
        }
        
        // Crear un archivo temporal para pruebas
        FileController adminController = new FileController("admin");
        adminController.write("test.txt", "Este es un archivo de prueba");
        
        // Ahora leer con usuario reader (debería funcionar)
        try {
            String content = readerController.read("test.txt");
            System.out.println("Contenido leído: " + content);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        // Usuario reader no puede eliminar
        try {
            readerController.remove("test.txt");
        } catch (Exception e) {
            System.out.println("Error esperado (usuario reader no puede eliminar): " + e.getMessage());
        }
        
        System.out.println("\n--- Pruebas con usuario 'editor' (lectura y escritura) ---");
        FileController editorController = new FileController("editor");
        
        // El usuario 'editor' puede leer y escribir
        try {
            editorController.write("test2.txt", "Contenido de editor");
            String content = editorController.read("test2.txt");
            System.out.println("Contenido leído por editor: " + content);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        // El usuario 'editor' no puede eliminar
        try {
            editorController.remove("test2.txt");
        } catch (Exception e) {
            System.out.println("Error esperado (usuario editor no puede eliminar): " + e.getMessage());
        }
        
        System.out.println("\n--- Pruebas con usuario 'admin' (todos los permisos) ---");
        FileController adminController2 = new FileController("admin");
        
        // El usuario 'admin' puede hacer todo
        try {
            adminController2.write("test3.txt", "Contenido de administrador");
            String content = adminController2.read("test3.txt");
            System.out.println("Contenido leído por admin: " + content);
            
            adminController2.remove("test3.txt");
            System.out.println("Archivo eliminado exitosamente por admin");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        // Limpiar archivos de prueba
        try {
            adminController.remove("test.txt");
        } catch (Exception e) {
            System.out.println("No se pudo eliminar test.txt: " + e.getMessage());
        }
        
        System.out.println("\nLa demostración ha terminado. Se puede observar cómo el patrón Proxy");
        System.out.println("controla el acceso a las operaciones basándose en el rol del usuario,");
        System.out.println("mientras que la arquitectura hexagonal mantiene las capas separadas y limpias.");
    }
}