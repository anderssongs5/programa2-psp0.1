package co.edu.udea.pruebassoftware.psp.programa2.linkedlist;

import co.edu.udea.pruebassoftware.psp.programa2.entities.ProgramPart;

/**
 * Clase nodo donde se guardará la información referente a una parte del
 * programa, que para este caso será una clase JAVA.
 *
 * @author Andersson Garc&iacute;a Sotelo
 * @since JDK 1.8
 * @version 1.0
 */
public class Node {

    private Node next;
    private ProgramPart programClass;

    public Node() {
        super();
    }

    public Node(ProgramPart programClass) {
        this.programClass = programClass;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public ProgramPart getProgramClass() {
        return programClass;
    }

    public void setProgramClass(ProgramPart programClass) {
        this.programClass = programClass;
    }
}
