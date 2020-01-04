import java.sql.SQLOutput;
import java.util.Random;
import java.util.Scanner;

public class Conversation {
    public static final int MIN_VECTOR_BOUND = -50;
    public static final int MAX_VECTOR_BOUND = 50;

    Scanner in = new Scanner(System.in);
    int vectorsNumber;
    boolean isRandom;
    int[] dimensions;
    Vector[] vectors;

    public void startDialogWithUser() {
        System.out.println("--------------------VEKTOR RECHNER--------------------");
        howManyVectorsQuestion();
        askIsRandom();
        askVectorsDimension();
        if (isRandom == true) createVectorsWithRandomNumbers();
        else createVectorsWithUserInput();
        System.out.println(" ");
        ausgabe();
        System.out.println();
        menuOperationen();
    }

    private void howManyVectorsQuestion() {
        System.out.println("Mit wie viele Vektoren möchten Sie arbeiten? (min.3)");
        int howManyVectors = in.nextInt();
        this.vectorsNumber = howManyVectors;
        if (howManyVectors < 3 || howManyVectors < 0) {
            System.out.println("----Die Anzahl von Vektoren soll mindestens 3 sein----");
            howManyVectorsQuestion();
        }
    }

    private void askIsRandom() {
        System.out.println("Möchten Sie mit Zufallvektoren arbeiten? j/n");
        String answer = in.next();
        if (answer.equals("j")) {
            this.isRandom = true;
        } else if (answer.equals("n")) {
            this.isRandom = false;
        } else {
            System.out.println("Falsche Eingabe - bitte wiederholen!");
            askIsRandom();
        }
    }

    private void askVectorsDimension() {
        dimensions = new int[vectorsNumber];
        for (int i = 0; i < vectorsNumber; i++) {
            int dimension = askDimension(i);
            while (dimension > 6 || dimension < 0) {
                System.out.println("----Die Dimension soll positive und maximal 6 sein----");
                dimension = askDimension(i);
            }
            dimensions[i] = dimension;
        }
    }

    private int askDimension(int i) {
        System.out.printf("Schreiben Sie bitte die Dimension für Vektor %d (max.6) %n", i + 1);
        return in.nextInt();
    }

    private void createVectorsWithUserInput() {
        vectors = new Vector[vectorsNumber];
        for (int i = 0; i < dimensions.length; i++) {
            int currentDimension = dimensions[i];
            float[] content = new float[currentDimension];
            for (int j = 0; j < currentDimension; j++) {
                System.out.printf("Geben Sie Zahl %d für Vektor %d%n", j + 1, i + 1);
                content[j] = in.nextInt();
            }
            Vector vector = new Vector(content);
            vectors[i] = vector;
        }
    }

    private void createVectorsWithRandomNumbers() {
        vectors = new Vector[vectorsNumber];
        for (int i = 0; i < dimensions.length; i++) {
            int currentDimension = dimensions[i];
            float[] content = new float[currentDimension];
            for (int j = 0; j < currentDimension; j++) {
                content[j] = getRandomNumberInRange(MIN_VECTOR_BOUND, MAX_VECTOR_BOUND);
            }
            Vector vector = new Vector(content);
            vectors[i] = vector;
        }
    }

    public void menuOperationen() {
        System.out.println("--------------------------MENU: OPERATIONEN MIT VEKTOREN--------------------------");
        char aktion = '0';
        while (aktion != 'e') {
            System.out.println(" ");
            System.out.println("-----------------------> BITTE WÄHLEN SIE EINE AKTION AUS <-----------------------");
            System.out.println(" a - Eingegebene Vektoren zeigen                   i - Einheitsvektor berechnen");
            System.out.println(" s - Summe berechnen                               l - Länge des Vektors berechnen");
            System.out.println(" d - Differenz berechnen                           v - Vektorprodukt berechnen");
            System.out.println(" p - Skalarprodukt berechnen                       r - Spatprodukt berechnen");
            System.out.println(" m - Ein Vektor mit einer Zahl multipliezieren     e - Programm beenden");
            System.out.println("----------------------------------------------------------------------------------");
            System.out.println("Ihre Eingabe: ");
            aktion = in.next().charAt(0);
            switch (aktion) {
                case 'a':
                    ausgabe();
                    break;
                case 's':
                    summeDialog();
                    break;
                case 'd':
                    differenzDialog();
                    break;
                case 'p':
                    skalarproduktDialog();
                    break;
                case 'm':
                    multiplikationDialog();
                    break;
                case 'i':
                    einheitsVektorDialog();
                    break;
                case 'l':
                    betragDialog();
                    break;
                case 'v':
                    kreuzDialog();
                    break;
                case 'r':
                    spatDialog();
                    break;
                case 'e':
                    break;
                default:
                    System.out.println("------Falsche Eingabe - bitte wiederholen------");
            }
        }
    }

    public void ausgabe() {
        System.out.println("Ihre Vektoren:");
        for (int i = 0; i < vectors.length; i++) {
            System.out.println("Vector " + (i + 1) + ": " + vectors[i]);
        }
    }

    public void summeDialog() {
        System.out.println("Wählen Sie Vektor - Summand 1 (bsp. 1)");
        System.out.println("Bitte beachten Sie dass die Vektoren sollen gleiche Dimensionen haben");
        int s1 = in.nextInt();
        while (s1 > vectorsNumber) {
            System.out.println("-----Falsche Eingabe - bitte wählen Summand 1-----");
            s1 = in.nextInt();
        }
        System.out.println("Wählen Sie Vektor - Summand 2 (bsp. 2)");
        System.out.println("Bitte beachten Sie dass die Vektoren sollen gleiche Dimensionen haben");
        int s2 = in.nextInt();
        while (s2 > vectorsNumber) {
            System.out.println("-----Falsche Eingabe - bitte wählen Summand 2-----");
            s2 = in.nextInt();
        }
        Vector a = vectors[s1 - 1];
        Vector b = vectors[s2 - 1];
        if (dimensions[s1 - 1] != dimensions[s2 - 1]) {
            System.out.println("Die Vektoren sollen gleiche Dimension haben - wählen noch einmal");
            summeDialog();
        }
        Vector c = a.sum(b);
        System.out.printf("Summe von Vektor %d und Vektor %d ist:%n", s1, s2);
        System.out.println(a + " + " + b + " = " + c);
    }

    public void differenzDialog() {
        System.out.println("Wählen Sie Vektor - Minuend (bsp. 1)");
        System.out.println("Bitte beachten dass die Vektoren sollen gleiche Dimensionen haben");
        int m1 = in.nextInt();
        while (m1 > vectorsNumber) {
            System.out.println("-----Falsche Eingabe - bitte wählen Minuend-----");
            m1 = in.nextInt();
        }
        System.out.println("Wählen Sie Vektor - Subtrahend (bsp. 2)");
        System.out.println("Bitte beachten dass die Vektoren sollen gleiche Dimensionen haben");
        int st2 = in.nextInt();
        while (st2 > vectorsNumber) {
            System.out.println("-----Falsche Eingabe - bitte wählen Subtrahend-----");
            st2 = in.nextInt();
        }
        Vector a = vectors[m1 - 1];
        Vector b = vectors[st2 - 1];
        if (dimensions[m1 - 1] != dimensions[st2 - 1]) {
            System.out.println("---Dimensionen sind nicht gleich - wählen noch einmal---");
            differenzDialog();
        }
        Vector c = a.diff(b);

        System.out.printf("Differenz von Vektor %d und Vektor %d ist:%n", m1, st2);
        System.out.println(a + " - " + b + " = " + c);
    }

    public void skalarproduktDialog() {
        System.out.println("Wählen Sie Vektor - Multiplikator 1 (bsp. 1)");
        System.out.println("Bitte beachten dass die Vektoren sollen gleiche Dimensionen haben");
        int mp1 = in.nextInt();
        while (mp1 > vectorsNumber) {
            System.out.println("-----Falsche Eingabe - bitte wählen Multiplikator 1-----");
            mp1 = in.nextInt();
        }
        System.out.println("Wählen Sie Vektor - Multipliktor 2 (bsp. 2)");
        System.out.println("Bitte beachten dass die Vektoren sollen gleiche Dimensionen haben");
        int mp2 = in.nextInt();
        while (mp2 > vectorsNumber) {
            System.out.println("-----Falsche Eingabe - bitte wählen Multiplikator 2-----");
            mp2 = in.nextInt();
        }
        Vector a = vectors[mp1 - 1];
        Vector b = vectors[mp2 - 1];
        if (dimensions[mp1 - 1] != dimensions[mp2 - 1]) {
            System.out.println("---Dimensionen sind nicht gleich - wählen noch einmal---");
            skalarproduktDialog();
        }
        float c = a.skalarprodukt(b);

        System.out.printf("Skalarprodukt von Vektor %d und Vektor %d ist:%n", mp1, mp2);
        System.out.println(a + " * " + b + " = " + c);
    }

    public void multiplikationDialog() {
        System.out.println("Wählen Sie Vektor für Multiplikation (bsp. 1)");
        int mp = in.nextInt();
        while (mp > vectorsNumber) {
            System.out.println("----Falsche Eingabe - bitte wählen ein neues Vektor----");
            mp = in.nextInt();
        }
        System.out.println("Geben Sie die Zahl für Multiplikation ein");
        int z = in.nextInt();
        Vector a = vectors[mp - 1];
        Vector b = a.mult(z);
        System.out.printf("Produkt von Vektor %d und %d ist: %n", mp, z);
        System.out.println(a + " * " + z + " = " + b);
    }

    public void einheitsVektorDialog() {
        System.out.println("Wählen Sie Vektor für Berechnen (bsp. 1)");
        int ein = in.nextInt();
        while (ein > vectorsNumber) {
            System.out.println("----Falsche Eingabe - bitte wählen ein neues Vektor----");
            ein = in.nextInt();
        }
        Vector a = vectors[ein - 1];
        Vector b = a.einheits();
        System.out.printf("Einheitsvektor von Vektor %d ist:", ein);
        System.out.println(b);
    }

    public void betragDialog() {
        System.out.println("Wählen Sie Vektor für Berechnen (bsp. 1)");
        int be = in.nextInt();
        while (be > vectorsNumber) {
            System.out.println("----Falsche Eingabe - bitte wählen ein neues Vektor----");
            be = in.nextInt();
        }
        Vector a = vectors[be - 1];
        float b = a.betrag();
        System.out.printf("Länge des Vektors %d ist: ", be);
        System.out.println(b);
    }

    public void kreuzDialog() {
        System.out.println("Wählen Sie den ersten Vektor aus");
        System.out.println("Bitte beachten Sie dass die Vektoren sollen Dimension 3 haben");
        int v1 = in.nextInt();
        while (v1 > vectorsNumber) {
            System.out.println("----Falsche Eingabe - bitte wählen ein neues Vektor----");
            v1 = in.nextInt();
        }
        System.out.println("Wählen Sie den zweiten Vektor aus");
        System.out.println("Bitte beachten Sie dass die Vektoren sollen Dimension 3 haben");
        int v2 = in.nextInt();
        while (v2 > vectorsNumber) {
            System.out.println("----Falsche Eingabe - bitte wählen ein neues Vektor----");
            v2 = in.nextInt();
        }
        Vector a = vectors[v1 - 1];
        Vector b = vectors[v2 - 1];
        if (dimensions[v1 - 1] != 3 && dimensions[v2 - 1] != 3) {
            System.out.println("---Dimensionen sind ungleich 3 - wählen noch einmal---");
            kreuzDialog();
        }
        Vector c = a.kreuzprodukt(b);
        System.out.printf("Vektorprodukt von Vektor %d und Vektor %d ist: ", v1, v2);
        System.out.println(c);
    }

    public void spatDialog() {
        System.out.println("Wählen Sie den ersten Vektor aus");
        System.out.println("Bitte beachten Sie dass die Vektoren sollen Dimension 3 haben");
        int wahl1 = in.nextInt();
        while (wahl1 > vectorsNumber) {
            System.out.println("----Falsche Eingabe - bitte wählen ein neues Vektor----");
            wahl1 = in.nextInt();
        }
        System.out.println("Wählen Sie den zweiten Vektor aus");
        System.out.println("Bitte beachten Sie dass die Vektoren sollen Dimensionen 3 haben");
        int wahl2 = in.nextInt();
        while (wahl2 > vectorsNumber) {
            System.out.println("----Falsche Eingabe - bitte wählen ein neues Vektor----");
            wahl2 = in.nextInt();
        }
        System.out.println("Wählen Sie den dritten Vektor aus");
        System.out.println("Bitte beachten Sie dass die Vektoren sollen Dimensionen 3 haben");
        int wahl3 = in.nextInt();
        while (wahl3 > vectorsNumber) {
            System.out.println("----Falsche Eingabe - bitte wählen ein neues Vektor----");
            wahl3 = in.nextInt();
        }
        Vector a = vectors[wahl1 - 1];
        Vector b = vectors[wahl2 - 1];
        Vector c = vectors[wahl3 - 1];
        if (dimensions[wahl1 - 1] != 3 && dimensions[wahl2 - 1] != 3 && dimensions[wahl3 - 1] != 3) {
            System.out.println("---Dimensionen sind ungleich 3 - wählen noch einmal---");
            spatDialog();
        }
        float det = a.spatprodukt(b, c);
        System.out.printf("Spatprodukt von Vektor %d, Vektor %d und Vektor %d ist: ", wahl1, wahl2, wahl3);
        System.out.println(det);
    }

    private static int getRandomNumberInRange(int min, int max) {
        Random r = new Random();
        return r.ints(min, (max + 1)).findFirst().getAsInt();

    }
}