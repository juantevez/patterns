package com.ia.coder.proxy.application;

import com.ia.coder.proxy.domain.FileService;
import com.ia.coder.proxy.infrastructure.FileServiceProxy;
import com.ia.coder.proxy.infrastructure.FileServiceImpl;

/**
 * Servicio de aplicación que utiliza el patrón Proxy para controlar el acceso
 * a las operaciones de archivos según el rol del usuario
 */
public class FileApplicationService {
    
    private final FileService fileService;
    
    public FileApplicationService(String userRole) {
        // Crear el servicio real
        FileService realService = new FileServiceImpl();
        
        // Envolverlo con el proxy para agregar control de seguridad
        this.fileService = new FileServiceProxy(realService, userRole);
    }
    
    /**
     * Lee el contenido de un archivo
     * @param fileName nombre del archivo a leer
     * @return contenido del archivo
     */
    public String readFile(String fileName) {
        return fileService.readFile(fileName);
    }
    
    /**
     * Escribe contenido en un archivo
     * @param fileName nombre del archivo a escribir
     * @param content contenido a escribir
     */
    public void writeFile(String fileName, String content) {
        fileService.writeFile(fileName, content);
    }
    
    /**
     * Elimina un archivo
     * @param fileName nombre del archivo a eliminar
     */
    public void deleteFile(String fileName) {
        fileService.deleteFile(fileName);
    }
}