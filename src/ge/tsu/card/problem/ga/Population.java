package ge.tsu.card.problem.ga;

import ge.tsu.card.problem.data.AlgorithmData;

import java.util.ArrayList;
import java.util.List;

public class Population {

    private List<Chromosome> chromosomes;

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

    public static Population initPopulation() {
        Population p = new Population();
        for (int i = 0; i < AlgorithmData.NUMBER_OF_CHROMOSOMES; i++) {
            p.getChromosomes().add(Chromosome.getRandomChromosome());
        }
        return p;
    }

}
