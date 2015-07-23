package co.edu.udea.pruebassoftware.psp.programa2.controller;

import co.edu.udea.pruebassoftware.psp.programa2.entities.process.StandardCounter;
import co.edu.udea.pruebassoftware.psp.programa2.linkedlist.LinkedList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Clase que hará las veces de controlador de la interfaz gráfica de la
 * aplicación.
 *
 * @author Andersson Garc&iacute;a Sotelo
 * @since JDK 1.8
 * @version 1.0
 */
public class Program2PSPController {

    public static boolean checkFiles(File files[]) {
        if (files != null) {
            int i = 0;
            while (i < files.length) {
                File file = files[i];
                if (!file.getName().endsWith(".java")) {

                    return false;
                }
                i++;
            }

            return true;
        }

        return false;
    }

    public static LinkedList processFiles(File files[]) throws
            FileNotFoundException, IOException {
        if (files != null) {
            StandardCounter standardCounter;
            LinkedList linkedList = new LinkedList();
            int i = 0;
            while (i < files.length) {
                standardCounter = new StandardCounter();
                standardCounter.processFile(new FileReader(files[i]));
                if (standardCounter.getProgramPart() != null) {
                    linkedList.link(standardCounter.getProgramPart());
                }

                i++;
            }

            return linkedList;
        }

        return null;
    }
}
