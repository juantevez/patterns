package com.ia.coder.proxy;

import com.ia.coder.proxy.interfaces.FileController;

/**
 * Clase de prueba adicional para verificar el patrón Proxy
 */
public class TestProxy {
    
    public static void main(String[] args) {
        System.out.println("Prueba adicional del patrón Proxy:");
        
        // Probar diferentes roles
        String[] roles = {"reader", "editor", "admin"};
        
        for (String role : roles) {
            System.out.println("\n--- Prueba con rol: " + role + " ---");
            FileController controller = new FileController(role);
            
            // Intentar leer (todos los roles deberían poder)
            System.out.println("Intentando leer archivo...");
            try {
                controller.read("nonexistent.txt");
            } catch (Exception e) {
                System.out.println("Error (esperado): " + e.getMessage());
            }
            
            // Intentar escribir (solo admin y editor deberían poder)
            System.out.println("Intentando escribir archivo...");
            try {
                controller.write("test_" + role + ".txt", "Contenido para " + role);
                System.out.println("Escritura exitosa para rol: " + role);
            } catch (Exception e) {
                System.out.println("Escritura fallida para rol: " + role + " - " + e.getMessage());
            }
            
            // Intentar eliminar (solo admin debería poder)
            System.out.println("Intentando eliminar archivo...");
            try {
                controller.remove("test_" + role + ".txt");
                System.out.println("Eliminación exitosa para rol: " + role);
            } catch (Exception e) {
                System.out.println("Eliminación fallida para rol: " + role + " - " + e.getMessage());
            }
        }
    }
}