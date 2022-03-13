
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class main {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Stack<Character> pilha = new Stack<>();
        Scanner scanner = new Scanner(new File("prog.txt"));
        String textToFile = "";

        while (scanner.hasNextLine()) {
            Boolean validExpression = true;
            String line = scanner.nextLine();

            for (Character caractere : line.toCharArray()) {
                if ("{[(<".indexOf(caractere) >= 0) {
                    pilha.add(caractere);
                } else if (pilha.isEmpty()) {
                    validExpression = false;
                } else {
                    switch (caractere) {
                        case '}':
                            if (pilha.peek() == '{') {
                                pilha.pop();
                            } else {
                                validExpression = false;
                            }
                            break;
                        case ']':
                            if (pilha.peek() == '[') {
                                pilha.pop();
                            } else {
                                validExpression = false;
                            }
                            break;
                        case ')':
                            if (pilha.peek() == '(') {
                                pilha.pop();
                            } else {
                                validExpression = false;
                            }
                            break;
                        case '>':
                            if (pilha.peek() == '<') {
                                pilha.pop();
                            } else {
                                validExpression = false;
                            }
                            break;
                    }
                }

                if (!validExpression) {
                    System.out.println(line + " - Inválido");
                    textToFile += line + " - Inválido\n";
                    break;
                }
            }

            if (validExpression) {
                if (pilha.isEmpty()) {
                    System.out.println(line + " - OK");
                    textToFile += line + " - OK\n";
                }
            }

            pilha.clear();
        }
        
        FileWriter fw = new FileWriter("prog-check.txt");
        fw.write(textToFile);
        fw.close();
    }
}

