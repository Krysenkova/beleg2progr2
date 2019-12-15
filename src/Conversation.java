import java.util.Random;
import java.util.Scanner;

public class Conversation {
    public static final int MIN_VECTOR_BOUND = -10;
    public static final int MAX_VECTOR_BOUND = 10;

    Scanner in = new Scanner(System.in);
    int vectorsNumber;
    boolean isRandom;
    int[] dimensions;
    Vector[] vectors;

    public void startdialogwithUser() {
        System.out.println("Vektor Rechner");
        howManyVectorsQuestion();
        askIsRandom();
        askVectorsDimension();
        if (isRandom == true) createVectorsWithRandomNumbers();
        else createVectorsWithUserInput();

        System.out.println("Ihre Vektoren:");
        for (int i = 0; i < vectors.length; i++) {
            System.out.println("Vector " + (i + 1) + ": " + vectors[i]);
        }
        hauptmenu();
    }

    public void howManyVectorsQuestion() {
        System.out.println("Mit wie viele Vektoren möchten Sie arbeiten? (min.3)");
        int howManyVectors = in.nextInt();
        this.vectorsNumber = howManyVectors;
        if (howManyVectors < 3) {
            System.out.println("Die Anzahl von Vektoren soll mindestens 3 sein");
            howManyVectorsQuestion();

        }

    }

    public void askIsRandom() {
        System.out.println("Möchten Sie mit Zufallvektoren arbeiten? j/n");
        String answer = in.next();
        if (answer.equals("j")) {
            this.isRandom = true;
        } else {
            this.isRandom = false;
        }
    }

    public void askVectorsDimension() {
        dimensions = new int[vectorsNumber];
        for (int i = 0; i < vectorsNumber; i++) {
            int dimension = askDimension(i);
            while (dimension > 6) {
                System.out.println("Die Dimension soll maximal 6 sein");
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
            int[] content = new int[currentDimension];
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
            int[] content = new int[currentDimension];
            for (int j = 0; j < currentDimension; j++) {
                content[j] = getRandomNumberInRange(MIN_VECTOR_BOUND, MAX_VECTOR_BOUND);
            }
            Vector vector = new Vector(content);
            vectors[i] = vector;
        }
    }

    public void hauptmenu() {
        System.out.println("---------------HAUPTMENU----------------");
        char wahl = '0';
        while (wahl != 'e') {
        System.out.println("--> Bitte wählen Sie eine Aktion aus <--");
        System.out.println(" 1 - Matematische Operationen mit Vektoren durchführen ");
        System.out.println(" 2 - Vektoren ändern");
        System.out.println(" e - Programm beenden");
        System.out.println("Ihre Eingabe: ");
        wahl = in.next().charAt(0);}
        switch (wahl) {
            case '1':
                menuOperationen();
                break;
            case '2':
                menuChanges();
                break;
            case 'e':
            default:
                System.out.println("Falsche Eingabe - bitte wiederholen");
        }
    }

    public void menuOperationen() {
        System.out.println("-----------MENU: OPERATIONEN------------");
        char aktion = '0';
        while (aktion != 'e') {
            System.out.println();
            System.out.println("--> Bitte wählen Sie eine Aktion aus <--");
            System.out.println(" a - Eingegebene Vektoren zeigen");
            System.out.println(" s - Summe berechnen");
            System.out.println(" d - Differenz berechnen");
            System.out.println(" p - Skalarprodukt berechnen");
            System.out.println(" m - Ein Vektor mit einer Zahl multipliezieren");
            System.out.println(" i - Einheitsvektor berechnen");
            System.out.println(" l - Länge des Vektors berechnen");
            System.out.println(" r - Spatprodukt berechnen");
            System.out.println(" z - zurück zum Hauptmenu");
            System.out.println(" e - Programm beenden");
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
                case 'r':
                    spatDialog();
                case 'z':
                    hauptmenu();
                    break;
                case 'e':
                    break;
                default:
                    System.out.println("Falsche Eingabe - bitte wiederholen");
            }
        }
    }

    public void menuChanges() {
        System.out.println("-----------ÄNDERUNGEN VORNEHMEN-----------");
        char choice = '0';
        while (choice != 'e') {
        System.out.println("--> Bitte wählen Sie eine Aktion aus <--");
        System.out.println(" 1 - Vektor ändern ");
        System.out.println(" 2 - Mit neuen Vektoren starten");
        System.out.println(" z - zurück zum Hauptmenu");
        System.out.println(" e - Programm beenden");
        System.out.println("Ihre Eingabe: ");
        choice = in.next().charAt(0);}
        switch (choice) {
            case '1':
            case '2':
            case 'z':
                hauptmenu();
                break;
            case 'e':
            default:
                System.out.println("Falsche Eingabe - bitte wiederholen");
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
        System.out.println("Bitte beachten dass die Vektoren sollen gleiche Dimensionen haben");
        int s1 = in.nextInt();
        System.out.println("Wählen Sie Vektor - Summand 2 (bsp. 2)");
        System.out.println("Bitte beachten dass die Vektoren sollen gleiche Dimensionen haben");
        int s2 = in.nextInt();
        Vector a = vectors[s1 - 1];
        Vector b = vectors[s2 - 1];
        Vector c = a.sum(b);
        System.out.printf("Summe von Vektor %d und Vektor %d ist:%n", s1, s2);
        System.out.println(a + " + " + b + " = " + c);


    }

    public void differenzDialog() {
        System.out.println("Wählen Sie Vektor - Minuend (bsp. 1)");
        System.out.println("Bitte beachten dass die Vektoren sollen gleiche Dimensionen haben");
        int m1 = in.nextInt();
        System.out.println("Wählen Sie Vektor - Subtrahend (bsp. 2)");
        System.out.println("Bitte beachten dass die Vektoren sollen gleiche Dimensionen haben");
        int st2 = in.nextInt();
        Vector a = vectors[m1 - 1];
        Vector b = vectors[st2 - 1];
        Vector c = a.diff(b);

        System.out.printf("Differenz von Vektor %d und Vektor %d ist:%n", m1, st2);
        System.out.println(a + " - " + b + " = " + c);
    }

    public void skalarproduktDialog() {
        System.out.println("Wählen Sie Vektor - Multiplikator 1 (bsp. 1)");
        System.out.println("Bitte beachten dass die Vektoren sollen gleiche Dimensionen haben");
        int mp1 = in.nextInt();
        System.out.println("Wählen Sie Vektor - Multipliktor 2 (bsp. 2)");
        System.out.println("Bitte beachten dass die Vektoren sollen gleiche Dimensionen haben");
        int mp2 = in.nextInt();
        Vector a = vectors[mp1 - 1];
        Vector b = vectors[mp2 - 1];
        int c = a.skalarprodukt(b);

        System.out.printf("Skalarprodukt von Vektor %d und Vektor %d ist:%n", mp1, mp2);
        System.out.println(a + " * " + b + " = " + c);
    }

    public void multiplikationDialog() {
        System.out.println("Wählen Sie Vektor für Multiplikation (bsp. 1)");
        int mp = in.nextInt();
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
        Vector a = vectors[ein - 1];
        Vector b = a.einheits();
        System.out.printf("Einheitsvektor von Vektor %d ist:", ein);
        System.out.println(b);
    }

    public void betragDialog() {
        System.out.println("Wählen Sie Vektor für Berechnen (bsp. 1)");
        int be = in.nextInt();
        Vector a = vectors[be - 1];
        int b = a.betrag();
        System.out.printf("Länge des Vektors %d ist: ", be);
        System.out.println(b);
    }

    public void spatDialog() {
        System.out.println("Wählen Sie den ersten Vektor aus");
        int v1 = in.nextInt();
        Vector a = vectors[v1 - 1];
        System.out.println("Wählen Sie den zweiten Vektor aus");
        int v2 = in.nextInt();
        Vector b = vectors[v2 - 1];
        System.out.println("Wählen Sie den dritten Vektor aus");
        int v3 = in.nextInt();
        Vector c = vectors[v3 - 1];
        int det =
    }

    private static int getRandomNumberInRange(int min, int max) {
        Random r = new Random();
        return r.ints(min, (max + 1)).findFirst().getAsInt();

    }

}




