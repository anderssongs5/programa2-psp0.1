package co.edu.udea.pruebassoftware.psp.programa2.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Entidad donde se almacenará la información referente a una clase, por
 * ejemplo, su nombre, el listado del nombre delos métodos y el total de las
 * líneas de código correspondiente a la clase.
 *
 * @author Andersson Garc&iacute;a Sotelo
 * @since JDK 1.8
 * @version 1.0
 */
public class ProgramPart {

    private List<String> methods;
    private int linesOfCode;
    private String partName;

    public ProgramPart() {
        super();
        this.methods = new ArrayList<>();
        this.linesOfCode = 0;
    }

    public List<String> getMethods() {
        return methods;
    }

    public void setMethods(List<String> methods) {
        this.methods = methods;
    }

    public int getLinesOfCode() {
        return linesOfCode;
    }

    public void setLinesOfCode(int linesOfCode) {
        this.linesOfCode = linesOfCode;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }
}
