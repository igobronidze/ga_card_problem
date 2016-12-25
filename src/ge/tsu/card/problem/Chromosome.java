package ge.tsu.card.problem;

import ge.tsu.card.problem.data.ProblemData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Chromosome {

    private List<Boolean> genes;      // გენები (0 ან 1)

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

    /**
     * მეთოდი შემთხვევითად ქმნის ქრომოსომას.
     *
     * @return შემთქვევითად შექმნილი ქრომოსომა
     */
    public static Chromosome getRandomChromosome() {
        Random r = new Random();
        Chromosome c = new Chromosome(ProblemData.NUMBER_OF_CARDS);
        for (int i = 0; i < ProblemData.NUMBER_OF_CARDS; i++) {
            c.getGenes().set(i, r.nextBoolean());
        }
        return c;
    }

    /**
     * ფიტნეს ფუნქცია - ითვლება არჩეული გენების შესაბამისი ელემენტების
     * ჯამი, შესბამისი ელემენტების ნამრავლი და ბრუნდება ჯამი
     * მოდულების მიღებული ჯამებისა და ამოცანის პირობის
     *
     * @return ფიტნეს ფუნქციის მნიშვნელობა
     */
    public int fitness() {
        int sum = 0;
        int product = 1;
        for (int i = 0; i < ProblemData.NUMBER_OF_CARDS; i++) {
            sum += ProblemData.CARDS[i] * (genes.get(i) ? 1 : 0);
            product *= ProblemData.CARDS[i] * (genes.get(i) ? 1 : 0);
        }
        return Math.abs(product - ProblemData.PRODUCT) + Math.abs(sum - ProblemData.SUM);
    }
}
