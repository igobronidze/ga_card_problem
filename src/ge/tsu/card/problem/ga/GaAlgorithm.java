package ge.tsu.card.problem.ga;

import ge.tsu.card.problem.data.AlgorithmData;
import ge.tsu.card.problem.data.ProblemData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GaAlgorithm {

    private static long generation = 0;

    private static Population population;
    private static List<Integer> fitnessList;

    public static void solve() throws InterruptedException {
        population = Population.initPopulation();
        while (true) {
            generation++;
            fitnessList = new ArrayList<>();
            for (Chromosome c : population.getChromosomes()) {
                fitnessList.add(c.fitness());
            }
            Chromosome parent1 = new Chromosome();
            Chromosome parent2 = new Chromosome();
            RouletteWheelSelection(parent1, parent2);
            crossoverAndReplace(parent1, parent2);
            mutation();
            Thread.sleep(AlgorithmData.TIME_BETWEEN_ITERATIONS);
            System.out.println("თაობა - " + generation + "     " +
                    "სხვაობა ჯამში - " + getBestAnswer().getDifferenceBetweenSum() + "     " +
                    "სხვაობა ნამრავლში - " + getBestAnswer().getDifferenceBetweenProduct() + "     " +
                    "საუკეთესოს ფიტნესი - " + getBestAnswer().fitness() + "     " +
                    "დამთხვევა პოპულაციაში - " + 100 * percentageOfSames(fitnessList) + "%");
            if (percentageOfSames(fitnessList) >= AlgorithmData.GOAL_CONDITION) {
                System.out.println(getBestAnswer());
                break;
            }
        }
    }

    private static void RouletteWheelSelection(Chromosome parent1, Chromosome parent2) {
        int fSum = 0;
        for (int x : fitnessList) {
            fSum += x;
        }
        Random r = new Random();
        int s = r.nextInt(fSum);
        int i = 0;
        while (true) {
            s = s - fitnessList.get(i);
            if (s < 0) {
                break;
            }
            i++;
        }
        int j = 0;
        s = r.nextInt(fSum);
        while (true) {
            s = s - fitnessList.get(j);
            if (s < 0) {
                break;
            }
            j++;
        }
        parent1.setGenes(population.getChromosomes().get(i).getGenes());
        parent2.setGenes(population.getChromosomes().get(j).getGenes());
    }

    private static void crossoverAndReplace(Chromosome parent1, Chromosome parent2) {
        Random r = new Random();
        int x = r.nextInt(ProblemData.NUMBER_OF_CARDS);
        Chromosome child1 = new Chromosome(ProblemData.NUMBER_OF_CARDS);
        Chromosome child2 = new Chromosome(ProblemData.NUMBER_OF_CARDS);
        for (int k = 0; k < x; k++) {
            child1.getGenes().set(k, parent1.getGenes().get(k));
        }
        for (int k = x; k < ProblemData.NUMBER_OF_CARDS; k++) {
            child1.getGenes().set(k, parent2.getGenes().get(k));
        }
        for (int k = 0; k < x; k++) {
            child2.getGenes().set(k, parent2.getGenes().get(k));
        }
        for (int k = x; k < ProblemData.NUMBER_OF_CARDS; k++) {
            child2.getGenes().set(k, parent2.getGenes().get(k));
        }
        replaceChromosomes(child1, child2);
    }

    private static void replaceChromosomes(Chromosome child1, Chromosome child2) {
        int m1 = 0;
        for (int i = 0; i<fitnessList.size(); i++) {
            if (fitnessList.get(i) < fitnessList.get(m1)) {
                m1 = i;
            }
        }
        int m2 = 0;
        for (int i = 0; i<fitnessList.size(); i++) {
            if (m1 != i && fitnessList.get(i) < fitnessList.get(m2)) {
                m2 = i;
            }
        }
        population.getChromosomes().set(m1, child1);
        population.getChromosomes().set(m2, child2);
    }

    private static void mutation() {
        Random random = new Random();
        double d = random.nextDouble();
        if (d > AlgorithmData.MUTATION_PROBABILITY) {
            return;
        }
        int index = random.nextInt(population.getChromosomes().size());
        Chromosome chromosome = population.getChromosomes().get(index);
        for (int i = 0; i < chromosome.getGenes().size(); i++) {
            chromosome.getGenes().set(i, !chromosome.getGenes().get(i));
        }
    }

    private static Chromosome getBestAnswer() {
        List<Chromosome> chromosomes = population.getChromosomes();
        Chromosome best = chromosomes.get(0);
        for (Chromosome c : chromosomes) {
            if (c.fitness() < best.fitness()) {
                best = c;
            }
        }
        return best;
    }

    private static Double percentageOfSames(List<Integer> arr) {
        int max = arr.get(0);
        for (int a : arr) {
            if (a > max) {
                max = a;
            }
        }
        int x = 0;
        for (int a : arr) {
            if (max == a) {
                x++;
            }
        }
        return (double) x / arr.size();
    }
}
