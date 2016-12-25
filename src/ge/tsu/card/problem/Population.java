package ge.tsu.card.problem;

import ge.tsu.card.problem.data.ProblemData;

import java.util.ArrayList;
import java.util.List;

public class Population {

    private List<Chromosome> chromosomes;       // პოპულაციაში არსებული ქრომოსომები

    public Population() {
        chromosomes = new ArrayList<>();
    }

    public Population(List<Chromosome> chromosomes) {
        this.chromosomes = chromosomes;
    }

    public List<Chromosome> getChromosomes() {
        return chromosomes;
    }

    public void setChromosomes(List<Chromosome> chromosomes) {
        this.chromosomes = chromosomes;
    }

    /**
     * საწყისი, შემთხვევითი პოპულაციის მიღება.
     *
     * @return საწყისი პოპულაცია
     */
    public static Population initPopulation() {
        Population p = new Population();
        for (int i = 0; i < ProblemData.NUMBER_OF_CARDS; i++) {
            p.getChromosomes().add(Chromosome.getRandomChromosome());
        }
        return p;
    }

}
