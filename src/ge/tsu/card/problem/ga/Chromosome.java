package ge.tsu.card.problem.ga;

import ge.tsu.card.problem.data.ProblemData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Chromosome {

    private List<Boolean> genes;

    public Chromosome() {
        genes = new ArrayList<>();
    }

    public Chromosome(int size) {
        this();
        for (int i = 0; i < size; i++) {
            genes.add(Boolean.FALSE);
        }
    }

    public Chromosome(ArrayList<Boolean> genes) {
        this.genes = genes;
    }

    public List<Boolean> getGenes() {
        return genes;
    }

    public void setGenes(List<Boolean> genes) {
        this.genes = genes;
    }

    public static Chromosome getRandomChromosome() {
        Random r = new Random();
        Chromosome c = new Chromosome(ProblemData.NUMBER_OF_CARDS);
        for (int i = 0; i < ProblemData.NUMBER_OF_CARDS; i++) {
            c.getGenes().set(i, r.nextBoolean());
        }
        return c;
    }

    public int fitness() {
        return getDifferenceBetweenProduct() + getDifferenceBetweenSum();
    }

    public int getDifferenceBetweenSum() {
        return Math.abs(getSum() - ProblemData.SUM);
    }

    public int getDifferenceBetweenProduct() {
        return Math.abs(getProduct() - ProblemData.PRODUCT);
    }

    public int getSum() {
        int sum = 0;
        for (int i = 0; i < ProblemData.NUMBER_OF_CARDS; i++) {
            sum += ProblemData.CARDS[i] * (genes.get(i) ? 1 : 0);
        }
        return sum;
    }

    public int getProduct() {
        int product = 1;
        for (int i = 0; i < ProblemData.NUMBER_OF_CARDS; i++) {
            if (!genes.get(i)) {
                product *= ProblemData.CARDS[i];
            }
        }
        return product;
    }

    public String toString() {
        String info = "------------------------საუკეთესო ინდივიდი------------------------" + System.lineSeparator();
        info += "ჯამისთვის არჩეულები - ";
        for (int  i = 0; i < ProblemData.NUMBER_OF_CARDS; i++) {
            if (genes.get(i)) {
                info += ProblemData.CARDS[i] + ", ";
            }
        }
        info += System.lineSeparator();
        info += "სასურველი ჯამი - " + ProblemData.SUM + "     ";
        info += "მიღებული ჯამი - " + getSum() + "     ";
        info += "სხვაობა - " + getDifferenceBetweenSum() + System.lineSeparator();
        info += "ნამრავლისთვის არჩეულები - ";
        for (int  i = 0; i < ProblemData.NUMBER_OF_CARDS; i++) {
            if (!genes.get(i)) {
                info += ProblemData.CARDS[i] + ", ";
            }
        }
        info += "სასურველი ნამრავლი - " + ProblemData.PRODUCT + "     ";
        info += "მიღებული ნამრავლი - " + getProduct() + "     ";
        info += "სხვაობა - " + getDifferenceBetweenProduct() + System.lineSeparator();
        info += "მთლიანი სხვაობა - " + fitness();
        return info;
    }
}
