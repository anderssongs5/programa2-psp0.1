package co.edu.udea.pruebassoftware.psp.programa2.entities.process;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class StandardCounterTest {

    @Test()
    public void isClassTest1() {
        StandardCounter standardCounter = new StandardCounter();
        String classDefinition = "public class Test {";
        boolean isClass = standardCounter.isClassEnumOrInterface(classDefinition);

        assertTrue(standardCounter.getProgramPart().getPartName().
                equals("Test") && isClass);
    }

    @Test()
    public void isClassTest2() {
        StandardCounter standardCounter = new StandardCounter();
        String classDefinition = "public void test (";
        boolean isClass = standardCounter.isClassEnumOrInterface(classDefinition);

        assertTrue(standardCounter.getProgramPart().getPartName() == null
                && !isClass);
    }

    @Test()
    public void isClassTest3() {
        StandardCounter standardCounter = new StandardCounter();
        String classDefinition = "public class Test (";
        boolean isClass = standardCounter.isClassEnumOrInterface(classDefinition);

        assertTrue(standardCounter.getProgramPart().getPartName() == null
                && !isClass);
    }

    @Test()
    public void isClassTest4() {
        StandardCounter standardCounter = new StandardCounter();
        String classDefinition = "public class Test implements Sarializable, "
                + "IEntityContext {";
        boolean isClass = standardCounter.isClassEnumOrInterface(classDefinition);

        assertTrue(standardCounter.getProgramPart().getPartName().
                equals("Test") && isClass);
    }

    @Test()
    public void isClassTest5() {
        StandardCounter standardCounter = new StandardCounter();
        String classDefinition = "public abstract class Test {";
        boolean isClass = standardCounter.isClassEnumOrInterface(classDefinition);

        assertTrue(standardCounter.getProgramPart().getPartName().
                equals("Test") && isClass);
    }

    @Test()
    public void isClassTest6() {
        StandardCounter standardCounter = new StandardCounter();
        String classDefinition = "public class Test extends JFrame {";
        boolean isClass = standardCounter.isClassEnumOrInterface(classDefinition);

        assertTrue(standardCounter.getProgramPart().getPartName().
                equals("Test") && isClass);
    }

    @Test()
    public void isPackageOrImportTest1() {
        StandardCounter standardCounter = new StandardCounter();
        String line = "import java.io.IOException;";

        assertTrue(standardCounter.isPackageOrImport(line));
    }

    @Test()
    public void isPackageOrImportTest2() {
        StandardCounter standardCounter = new StandardCounter();
        String line = "package co.edu.udea.pruebassoftware.psp.programa2."
                + "entities.process;";

        assertTrue(standardCounter.isPackageOrImport(line));
    }

    @Test()
    public void getMethodNameTest1() {
        StandardCounter standardCounter = new StandardCounter();
        String lineCode = "public static void main(String args []) {";

        assertTrue(standardCounter.isMethod(lineCode).equals("main"));
    }

    @Test()
    public void getMethodNameTest2() {
        StandardCounter standardCounter = new StandardCounter();
        String lineCode = "private void checkFile(FileReader fileReader) {";

        assertTrue(standardCounter.isMethod(lineCode).equals("checkFile"));
    }

    @Test()
    public void getMethodNameTest3() {
        StandardCounter standardCounter = new StandardCounter();
        String lineCode = "private void checkFile(FileReader fileReader, String"
                + " text) {";

        assertTrue(standardCounter.isMethod(lineCode).equals("checkFile"));
    }

    @Test()
    public void getMethodNameTest4() {
        StandardCounter standardCounter = new StandardCounter();
        String lineCode = "abstract void draw();";

        assertTrue(standardCounter.isMethod(lineCode).equals("draw"));
    }

    @Test()
    public void processFileTest1() throws IOException {
        String filePath = "C:\\Users\\Andersson\\Documents\\NetBeansProjects\\"
                + "programa2-psp0.1\\src\\co\\edu\\udea\\pruebassoftware\\psp\\"
                + "programa2\\linkedlist\\Node.java";
        StandardCounter standardCounter = new StandardCounter();
        File file = new File(filePath);
        standardCounter.processFile(new FileReader(file));

        assertTrue(standardCounter.getProgramPart().getLinesOfCode() == 17);
    }

    @Test()
    public void processFileTest2() throws IOException {
        String filePath = "C:\\Users\\Andersson\\Documents\\NetBeansProjects\\"
                + "programa2-psp0.1\\src\\co\\edu\\udea\\pruebassoftware\\psp\\"
                + "programa2\\entities\\process\\StandardCounter.java";
        StandardCounter standardCounter = new StandardCounter();
        File file = new File(filePath);
        standardCounter.processFile(new FileReader(file));

        assertTrue(standardCounter.getProgramPart().getLinesOfCode() == 116);
    }

    @Test()
    public void processFileTest3() throws IOException {
        String filePath = "C:\\Users\\Andersson\\Documents\\NetBeansProjects\\"
                + "programa2-psp0.1\\src\\co\\edu\\udea\\pruebassoftware\\psp\\"
                + "programa2\\linkedlist\\LinkedList.java";
        StandardCounter standardCounter = new StandardCounter();
        File file = new File(filePath);
        standardCounter.processFile(new FileReader(file));

        assertTrue(standardCounter.getProgramPart().getLinesOfCode() == 43);
    }

    @Test()
    public void processFileTest4() throws IOException {
        String filePath = "C:\\Users\\Andersson\\Dropbox\\Universidad\\Materias"
                + "\\Pruebas de Software\\Trabajos\\PSP - Programas\\Programa 1 "
                + "- PSP 0\\Solución\\programa1-psp0\\src\\co\\edu\\udea\\"
                + "pruebassoftware\\psp\\programa1\\main\\Program1PSPGUI.java";
        StandardCounter standardCounter = new StandardCounter();
        File file = new File(filePath);
        standardCounter.processFile(new FileReader(file));

        assertTrue(standardCounter.getProgramPart().getLinesOfCode() == 117);
    }

    @Test()
    public void processFileTest5() throws IOException {
        String filePath = "C:\\Users\\Andersson\\Dropbox\\Universidad\\Materias"
                + "\\Pruebas de Software\\Trabajos\\PSP - Programas\\Programa 1"
                + " - PSP 0\\Solución\\programa1-psp0\\src\\co\\edu\\udea\\"
                + "pruebassoftware\\psp\\programa1\\util\\constants\\"
                + "Constants.java";
        StandardCounter standardCounter = new StandardCounter();
        File file = new File(filePath);
        standardCounter.processFile(new FileReader(file));

        assertTrue(standardCounter.getProgramPart().getLinesOfCode() == 3);
    }

    @Test()
    public void processFileTest6() throws IOException {
        String filePath = "C:\\Users\\Andersson\\Dropbox\\Universidad\\Materias"
                + "\\Pruebas de Software\\Trabajos\\PSP - Programas\\Programa 1"
                + " - PSP 0\\Solución\\programa1-psp0\\src\\co\\edu\\udea\\"
                + "pruebassoftware\\psp\\programa1\\util\\enums\\"
                + "MessageEnums.java";
        StandardCounter standardCounter = new StandardCounter();
        File file = new File(filePath);
        standardCounter.processFile(new FileReader(file));

        assertTrue(standardCounter.getProgramPart().getLinesOfCode() == 16);
    }
}
