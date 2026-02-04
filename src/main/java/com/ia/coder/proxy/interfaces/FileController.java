package com.ia.coder.proxy.interfaces;

import com.ia.coder.proxy.application.FileApplicationService;

/**
 * Controlador que expone las operaciones de archivos al exterior
 * Representa la interfaz de entrada/salida en la arquitectura hexagonal
 */
public class FileController {
    
    private final FileApplicationService fileApplicationService;
    
    public FileController(String userRole) {
        this.fileApplicationService = new FileApplicationService(userRole);
    }
    
    /**
     * Lee el contenido de un archivo
     * @param fileName nombre del archivo a leer
     * @return contenido del archivo
     */
    public String read(String fileName) {
        System.out.println("Controlador: Recibiendo solicitud para leer archivo: " + fileName);
        return fileApplicationService.readFile(fileName);
    }
    
    /**
     * Escribe contenido en un archivo
     * @param fileName nombre del archivo a escribir
     * @param content contenido a escribir
     */
    public void write(String fileName, String content) {
        System.out.println("Controlador: Recibiendo solicitud para escribir en archivo: " + fileName);
        fileApplicationService.writeFile(fileName, content);
    }
    
    /**
     * Elimina un archivo
     * @param fileName nombre del archivo a eliminar
     */
    public void remove(String fileName) {
        System.out.println("Controlador: Recibiendo solicitud para eliminar archivo: " + fileName);
        fileApplicationService.deleteFile(fileName);
    }
}