package com.ia.coder.proxy.domain;

/**
 * Interfaz de dominio que define las operaciones del servicio de archivos
 */
public interface FileService {
    
    /**
     * Lee el contenido de un archivo
     * @param fileName nombre del archivo a leer
     * @return contenido del archivo
     */
    String readFile(String fileName);
    
    /**
     * Escribe contenido en un archivo
     * @param fileName nombre del archivo a escribir
     * @param content contenido a escribir
     */
    void writeFile(String fileName, String content);
    
    /**
     * Elimina un archivo
     * @param fileName nombre del archivo a eliminar
     */
    void deleteFile(String fileName);
}