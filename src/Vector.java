import java.util.Arrays;

public class Vector {
    private int dimension;
    private float[] content;

    public Vector(float[] content) {
        this.content = content;
    }

    public float[] getContent() {
        return content;
    }

    @Override
    public String toString() {
        return Arrays.toString(content);
    }

    public Vector sum(Vector b) {
        float[] summand1 = this.getContent();
        float[] summand2 = b.getContent();
        float[] summe = new float[summand1.length];
        for (int i = 0; i < summand1.length; i++) {
            summe[i] = summand1[i] + summand2[i];
        }
        return new Vector(summe);
    }

    public Vector diff(Vector b) {
        float[] minuend = this.getContent();
        float[] subtrahend = b.getContent();
        float[] differenz = new float[minuend.length];
        for (int i = 0; i < minuend.length; i++) {
            differenz[i] = minuend[i] - subtrahend[i];
        }
        return new Vector(differenz);
    }

    public float skalarprodukt(Vector b) {
        float[] multiplikator1 = this.getContent();
        float[] multiplikator2 = b.getContent();
        float[] produkt = new float[multiplikator1.length];
        float skalar = 0;
        for (int i = 0; i < multiplikator1.length; i++) {
            produkt[i] = multiplikator1[i] * multiplikator2[i];
            skalar = produkt[i] + skalar;
        }
        return skalar;
    }

    public Vector mult(int z) {
        float[] multiplik = this.getContent();
        float[] mprodukt = new float[multiplik.length];
        for (int i = 0; i < multiplik.length; i++) {
            mprodukt[i] = multiplik[i] * z;
        }
        return new Vector(mprodukt);
    }

    public Vector einheits() {
        float[] inVektor = this.getContent();
        float[] einheitsvektor = new float[inVektor.length];
        int betrag = 0;
        for (int i = 0; i < inVektor.length; i++) {
            einheitsvektor[i] = inVektor[i] / betrag();
        }
        return new Vector(einheitsvektor);
    }

    public float betrag() {
        float[] initVektor = this.getContent();
        int betrag;
        float step = 0;
        for (int i = 0; i < initVektor.length; i++) {
            step = (initVektor[i] * initVektor[i]) + step;
        }
        betrag = (int) Math.sqrt(step);
        return betrag;
    }

    public Vector kreuzprodukt(Vector b) {
        float[] vekt1 = this.getContent();
        float[] vekt2 = b.getContent();
        float[] ergebnis;
        ergebnis = new float[]{vekt1[1] * vekt2[2] - vekt1[2] * vekt2[1], vekt1[2] * vekt2[0] - vekt1[0] * vekt2[2], vekt1[0] * vekt2[1] - vekt1[1] * vekt2[0]};

        return new Vector(ergebnis);
    }

    public float spatprodukt(Vector b, Vector c) {
        float[] erste = this.getContent();
        float[] zweite = b.getContent();
        float[] dritte = c.getContent();
        float det = ((erste[0] * zweite[1] * dritte[2]) + (erste[2] * zweite[0] * dritte[1]) + (erste[1] * zweite[2] * dritte[0])) - ((erste[2] * zweite[1] * dritte[0]) + (erste[0] * zweite[2] * dritte[1]) + (erste[1] * zweite[0] * dritte[2]));
        return det;
    }
}
