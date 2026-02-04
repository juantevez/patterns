package com.ia.coder.proxy.infrastructure;

import com.ia.coder.proxy.domain.FileService;

/**
 * Implementación del patrón Proxy para el servicio de archivos
 * Proporciona control adicional sobre el acceso al servicio real
 */
public class FileServiceProxy implements FileService {
    
    private final FileService fileService;
    private final String userRole;
    
    public FileServiceProxy(FileService fileService, String userRole) {
        this.fileService = fileService;
        this.userRole = userRole;
    }

    @Override
    public String readFile(String fileName) {
        // Verificar permisos antes de leer
        if (!hasReadPermission(fileName)) {
            throw new SecurityException("Usuario sin permiso para leer el archivo: " + fileName);
        }
        
        System.out.println("Proxy: Registrando intento de lectura para el archivo: " + fileName);
        
        // Llamar al servicio real
        String content = fileService.readFile(fileName);
        
        // Registrar operación después de completarse
        logOperation("READ", fileName);
        
        return content;
    }

    @Override
    public void writeFile(String fileName, String content) {
        // Verificar permisos antes de escribir
        if (!hasWritePermission(fileName)) {
            throw new SecurityException("Usuario sin permiso para escribir en el archivo: " + fileName);
        }
        
        System.out.println("Proxy: Registrando intento de escritura para el archivo: " + fileName);
        
        // Llamar al servicio real
        fileService.writeFile(fileName, content);
        
        // Registrar operación después de completarse
        logOperation("WRITE", fileName);
    }

    @Override
    public void deleteFile(String fileName) {
        // Verificar permisos antes de eliminar
        if (!hasDeletePermission(fileName)) {
            throw new SecurityException("Usuario sin permiso para eliminar el archivo: " + fileName);
        }
        
        System.out.println("Proxy: Registrando intento de eliminación para el archivo: " + fileName);
        
        // Llamar al servicio real
        fileService.deleteFile(fileName);
        
        // Registrar operación después de completarse
        logOperation("DELETE", fileName);
    }
    
    /**
     * Verifica si el usuario tiene permiso para leer archivos
     * @param fileName nombre del archivo
     * @return true si tiene permiso, false en caso contrario
     */
    private boolean hasReadPermission(String fileName) {
        // En este ejemplo simple, todos los roles pueden leer
        return true;
    }
    
    /**
     * Verifica si el usuario tiene permiso para escribir archivos
     * @param fileName nombre del archivo
     * @return true si tiene permiso, false en caso contrario
     */
    private boolean hasWritePermission(String fileName) {
        // Solo usuarios con rol "admin" o "editor" pueden escribir
        return "admin".equalsIgnoreCase(userRole) || "editor".equalsIgnoreCase(userRole);
    }
    
    /**
     * Verifica si el usuario tiene permiso para eliminar archivos
     * @param fileName nombre del archivo
     * @return true si tiene permiso, false en caso contrario
     */
    private boolean hasDeletePermission(String fileName) {
        // Solo usuarios con rol "admin" pueden eliminar
        return "admin".equalsIgnoreCase(userRole);
    }
    
    /**
     * Registra las operaciones realizadas
     * @param operation tipo de operación
     * @param fileName nombre del archivo
     */
    private void logOperation(String operation, String fileName) {
        System.out.println("Proxy: Operación " + operation + " registrada para el archivo: " + fileName + 
                          " por usuario con rol: " + userRole);
    }
}