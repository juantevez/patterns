package com.ia.coder.proxy.infrastructure;

import com.ia.coder.proxy.domain.FileService;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Implementación concreta del servicio de archivos
 * Esta clase representa el objeto real en el patrón Proxy
 */
public class FileServiceImpl implements FileService {

    @Override
    public String readFile(String fileName) {
        System.out.println("Leyendo archivo: " + fileName);
        try {
            return Files.readString(Paths.get(fileName));
        } catch (IOException e) {
            throw new RuntimeException("Error leyendo archivo: " + fileName, e);
        }
    }

    @Override
    public void writeFile(String fileName, String content) {
        System.out.println("Escribiendo archivo: " + fileName);
        try {
            Files.writeString(Paths.get(fileName), content);
        } catch (IOException e) {
            throw new RuntimeException("Error escribiendo archivo: " + fileName, e);
        }
    }

    @Override
    public void deleteFile(String fileName) {
        System.out.println("Eliminando archivo: " + fileName);
        try {
            Files.deleteIfExists(Paths.get(fileName));
        } catch (IOException e) {
            throw new RuntimeException("Error eliminando archivo: " + fileName, e);
        }
    }
}