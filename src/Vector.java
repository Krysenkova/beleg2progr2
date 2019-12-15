import java.util.Arrays;

public class Vector {
    private int dimension;
    private int[] content;

    public Vector(int dimension) {
        this.dimension = dimension;
    }

    public Vector(int[] content) {
        this.content = content;
    }

    public int[] getContent() {
        return content;
    }

    @Override
    public String toString() {
        return Arrays.toString(content);
    }

    public Vector sum(Vector b) {
        int[] summand1 = this.getContent();
        int[] summand2 = b.getContent();
        int[] summe = new int[summand1.length];
        for (int i = 0; i < summand1.length; i++) {
            summe[i] = summand1[i] + summand2[i];
        }
        return new Vector(summe);
    }

    public Vector diff(Vector b) {
        int[] minuend = this.getContent();
        int[] subtrahend = b.getContent();
        int[] differenz = new int[minuend.length];
        for (int i = 0; i < minuend.length; i++) {
            differenz[i] = minuend[i] - subtrahend[i];
        }
        return new Vector(differenz);
    }

    public int skalarprodukt(Vector b) {
        int[] multiplikator1 = this.getContent();
        int[] multiplikator2 = b.getContent();
        int[] produkt = new int[multiplikator1.length];
        int skalar = 1;
        for (int i = 0; i < multiplikator1.length; i++) {
            produkt[i] = multiplikator1[i] * multiplikator2[i];
            skalar = produkt[i] + skalar;
        }
        return skalar;
    }

    public Vector mult(int z) {
        int[] multiplik = this.getContent();
        int[] mprodukt = new int[multiplik.length];
        for (int i = 0; i < multiplik.length; i++) {
            mprodukt[i] = multiplik[i] * z;
        }
        return new Vector(mprodukt);
    }

    public Vector einheits() {
        int[] inVektor = this.getContent();
        int[] einheitsvektor = new int[inVektor.length];
        int betrag = 0;
        for (int i = 0; i < inVektor.length; i++) {
            betrag = (int) Math.sqrt((inVektor[i] * inVektor[i]) + betrag);
            einheitsvektor[i] = inVektor[i] / betrag;
        }
        return new Vector(einheitsvektor);
    }

    public int betrag() {
        int[] initVektor = this.getContent();
        int betrag;
        int step = 0;
        for (int i = 0; i < initVektor.length; i++) {
            step = (initVektor[i] * initVektor[i]) + step;
        }
        betrag = (int) Math.sqrt(step);
        return betrag;
    }

    public int spatprodukt(Vector b, Vector c) {
        int[] vekt1 = this.getContent();
        int[] vekt2 = b.getContent();
        int[] vekt3 = c.getContent();



            }
