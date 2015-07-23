package co.edu.udea.pruebassoftware.psp.programa2.entities.process;

import co.edu.udea.pruebassoftware.psp.programa2.entities.ProgramPart;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
// 5

/**
 * Clase que se encargará de validar el estándar de codificación y contará las
 * líneas de código según el estándar de conteo.
 *
 * @author Andersson Garc&iacute;a Sotelo
 * @since JDK 1.8
 * @version 1.0
 */
public class StandardCounter {

    private ProgramPart programPart;
    private boolean gotClass = false;

    private static final String CLASS_DEFINITION = " class ";
    private static final String BLANK_SPACE = " ";
    private static final String LEFT_BRACE = "{";
    private static final String RIGHT_BRACE = "}";
    private static final String SEMICOLON = ";";
    private static final String PACKAGE_DEFINITION = "package ";
    private static final String IMPORT_DEFINITION = "import ";
    // 15
    private static final String PUBLIC_MODIFIER = "public ";
    private static final String PRIVATE_MODIFIER = "private ";
    private static final String PROTECTED_MODIFIER = "protected ";
    private static final String ABSTRACT_MODIFIER = "abstract ";
    private static final String EXTENDS_DEFINITION = " extends ";
    private static final String IMPLEMENTS_DEFINITION = " implements ";
    private static final String THROWS_DEFINITION = " throws ";
    private static final String LEFT_PARENTHESIS = "(";
    private static final String RIGHT_PARENTHESIS = ")";
    private static final String PARENTHESIS_BRACE = ") {";
    // 25
    private static final String ASTERISK = "*";
    private static final String BLOCK_COMMENT_BEGINNING = "/**";
    private static final String BLOCK_COMMENT_ENDING = "*/";
    private static final String COMMENT = "/*";
    private static final String DOUBLE_SLASH = "// ";
    private static final String DOT = ".";
    private static final String WHILE_DEFINITION = "while";
    private static final String ELSE_DEFINITION = " else ";
    private static final String ELSE_IF_DEFINITION = " else if ";
    private static final String CATCH_DEFINITION = " catch ";
    private static final String ANNOTATION_DEFINTION = "@";
    private static final String ENUM_DEFINITION = " enum ";
    private static final String INTERFACE_DEFINITION = " interface ";
    // 38

    public StandardCounter() {
        super();
        programPart = new ProgramPart();
    }

    public ProgramPart getProgramPart() {
        return programPart;
    }

    public void setProgramPart(ProgramPart programPart) {
        this.programPart = programPart;
    }
    // 44

    public void processFile(FileReader fileReader) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            if (line.trim().isEmpty() || (line.trim().indexOf(RIGHT_BRACE) == 0
                    && !(line.trim().contains(WHILE_DEFINITION) || line.trim().
                    contains(ELSE_DEFINITION) || line.trim().contains(
                            ELSE_IF_DEFINITION) || line.trim().contains(
                            CATCH_DEFINITION))) || line.trim().indexOf(
                            ASTERISK) == 0 || line.trim().indexOf(
                            BLOCK_COMMENT_BEGINNING) == 0 || line.trim().
                    indexOf(BLOCK_COMMENT_ENDING) == 0 || line.trim().
                    indexOf(DOUBLE_SLASH) == 0 || line.trim().
                    indexOf(COMMENT) == 0) {

                continue;
            }

            if (!this.gotClass && (isPackageOrImport(line) || isClassEnumOrInterface(line))) {
                this.programPart.setLinesOfCode(this.programPart.
                        getLinesOfCode() + 1);

                continue;
            }

            String missingText = "";
            missingText += " " + line.trim();
            if (!(line.trim().contains(LEFT_BRACE) || line.trim().indexOf(
                    SEMICOLON, line.trim().length() - 1)
                    == line.trim().length() - 1 || line.trim().indexOf(
                            ANNOTATION_DEFINTION) == 0)) {
                String temp;
                do {
                    temp = bufferedReader.readLine();
                    if (temp != null) {
                        temp = temp.trim();
                    }

                    if (!String.valueOf(missingText.charAt(missingText.length()
                            - 1)).equals(DOT)) {
                        temp = " " + temp;
                    }
                    missingText += temp;
                } while ((temp.trim().indexOf(LEFT_BRACE) != temp.trim().
                        length() - 1) && (temp.trim().indexOf(SEMICOLON)
                        != temp.trim().length() - 1));
            }

            String methodName;
            if ((methodName = isMethod(missingText)) != null) {
                if (!methodName.equals(this.getProgramPart().getPartName())) {
                    this.programPart.getMethods().add(methodName);
                }
            }
            this.programPart.setLinesOfCode(this.programPart.
                    getLinesOfCode() + 1);
        }
    }
    // 71

    public boolean isPackageOrImport(String lineCode) {
        String text = lineCode.trim();

        return (text.contains(IMPORT_DEFINITION)
                || text.contains(PACKAGE_DEFINITION))
                && text.indexOf(SEMICOLON) == (text.length() - 1);
    }

    public boolean isClassEnumOrInterface(String lineCode) {
        String text = lineCode.trim();
        if ((text.contains(CLASS_DEFINITION) || text.contains(ENUM_DEFINITION)
                || text.contains(INTERFACE_DEFINITION)) && text.indexOf(LEFT_BRACE)
                == (text.length() - 1)) {
            int partNamePosition = 0;
            if (text.contains(CLASS_DEFINITION)) {
                partNamePosition = text.indexOf(CLASS_DEFINITION)
                        + CLASS_DEFINITION.length();
            } else if (text.contains(ENUM_DEFINITION)) {
                partNamePosition = text.indexOf(ENUM_DEFINITION)
                        + ENUM_DEFINITION.length();
            } else {
                partNamePosition = text.indexOf(INTERFACE_DEFINITION)
                        + INTERFACE_DEFINITION.length();
            }

            text = text.substring(partNamePosition);
            partNamePosition = text.indexOf(BLANK_SPACE);
            String partName = text.substring(0, partNamePosition);

            this.programPart.setPartName(partName.trim());
            this.gotClass = true;

            return true;
        }

        return false;
    }

    public String isMethod(String lineCode) {
        String methodName = null;
        if (lineCode.contains(ABSTRACT_MODIFIER) && lineCode.indexOf(SEMICOLON)
                == lineCode.length() - 1) {
            int leftParenthesisPosition = lineCode.indexOf(LEFT_PARENTHESIS);
            if (leftParenthesisPosition >= 0) {
                String methodDefinition = lineCode.substring(0, leftParenthesisPosition);
                int position = methodDefinition.length() - 1;
                while (!String.valueOf(methodDefinition.charAt(position)).
                        equals(" ")) {
                    position--;
                }

                methodName = methodDefinition.substring(position).trim();

                return methodName;
            }
        }

        if ((lineCode.contains(PUBLIC_MODIFIER) || lineCode.
                contains(PRIVATE_MODIFIER) || lineCode.
                contains(PROTECTED_MODIFIER)) && (lineCode.
                contains(PARENTHESIS_BRACE) || (lineCode.trim().
                contains(RIGHT_PARENTHESIS) && lineCode.trim().
                indexOf(LEFT_BRACE) == lineCode.trim().length() - 1
                && (lineCode.trim().contains(EXTENDS_DEFINITION)
                || lineCode.trim().contains(IMPLEMENTS_DEFINITION)
                || lineCode.trim().contains(THROWS_DEFINITION))))
                && !lineCode.contains(SEMICOLON)) {
            String methodDefinition = lineCode.substring(0, lineCode.
                    indexOf(LEFT_PARENTHESIS));
            int position = methodDefinition.length() - 1;
            while (!String.valueOf(methodDefinition.charAt(position)).
                    equals(" ")) {
                position--;
            }

            methodName = methodDefinition.substring(position).trim();

            return methodName;
        }

        if ((lineCode.contains(PUBLIC_MODIFIER) || lineCode.
                contains(PRIVATE_MODIFIER) || lineCode.
                contains(PROTECTED_MODIFIER)) && (lineCode.contains(
                        LEFT_PARENTHESIS) && lineCode.contains(
                        RIGHT_PARENTHESIS)) && lineCode.indexOf(SEMICOLON)
                == lineCode.length() - 1) {
            String methodDefinition = lineCode.substring(0, lineCode.
                    indexOf(LEFT_PARENTHESIS));
            int position = methodDefinition.length() - 1;
            while (!String.valueOf(methodDefinition.charAt(position)).
                    equals(" ")) {
                position--;
            }

            methodName = methodDefinition.substring(position).trim();
        }

        return methodName;
    }
    // 116
}
