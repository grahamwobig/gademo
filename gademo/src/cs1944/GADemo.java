/**
 * 
 * This class provides main body of the genetic algorithm.
 * In this file you can tune the parameters of the algorithm by changing the 
 * class fields
 * @author Graham Wobig
 * @date 11/18/20
 * 
 */

package cs1944;

import java.util.Arrays;
import java.util.Random;

public class GADemo {
	//number of elite members to advance to next generation
	private final int elite = 3;
	//probability of mutation
	private final double mutationProb = 0.1;
	//number of parents to use for crossover
	private final int numParents = 8;
	//size of the population
	private final int populationSize = 80;
	//the population
	private Individual population[];
	//which generation the algorithm is currently on
	private int currentGeneration;
	
	/**
	 * Constructor for the algorithm
	 * Initializes the population randomly across the problem space
	 */
	public GADemo() {
		currentGeneration = 0;
		population = new Individual[populationSize];
		for (int i = 0; i < populationSize; i++) {
			population[i] = new Individual(generateString());
		}
		this.fitness();
		Arrays.sort(population);
		this.printGeneration();
	}
	
	/**
     * generate a random double from -2.99999 to 2.99999 and convert to string
     */
    public static String generateString() {
                double max = 2.99999;
                double min = -2.99999;
                Random r = new Random();
                double rand = min + r.nextDouble() * (max - min);
                return Double.toString(rand);
    }
    
    /**
     * create the next generation of the population
     * the best 3 members of the current generation will be advanced to next generation
     * the top 8 members of the current generation will be used as parents for crossover
     * additionally, members have a 1 in 10 chance of mutation
     * pre: the fitness of current generation has been calculated
     */
    public void nextGeneration() {
    	Arrays.sort(population);
    	Individual parents[] = Arrays.copyOfRange(population, 0, numParents);
    	Random r = new Random();
    	for (int i = 0; i < 80; i++) {
    		int num = r.nextInt(populationSize);
    		int mutation = populationSize / ((int)(100 * mutationProb));
    		//the best members of the population should be advanced to next generation unmodified
    		if (i < elite) {
    			//do nothing for the elite
    			continue;
    		}
    		//perform crossover
    		else if (num > mutation) {
    			//randomly generate which parents to use for new individual
    			int p1 = r.nextInt(numParents - 1);
    			int p2 = r.nextInt(numParents - 1);
    			//ensure p1 and p2 aren't the same parent
    			if (p1 == p2 && p1 != numParents - 1) {
    				p2++;
    			}
    			else if (p1 == p2) {
    				p2--;
    			}
    			population[i].crossover(parents[p1], parents[p2]);
    		}
    		//perform mutation on the individual
    		else {
    			population[i].mutate();
    		}
    	}
    	currentGeneration++;
    }
    
    /**
     * calculate the fitness of current generation
     */
    public void fitness() {
    	for (int i = 0; i < populationSize; i++) {
    		population[i].calculateFitness();
    	}
    }
    
    /**
     * check if we can terminate
     * termination condition - there is an individual with fitness less than 0.001
     * will print avg fitness of population and current best estimate
     */
    public boolean testGeneration() {
    	System.out.println("\nGeneration " + currentGeneration + ":");
    	double sum = 0.0;
    	for (int i = 0; i < populationSize; i++) {
    		sum += population[i].getFitness();
    	}
    	double avgFitness = sum / populationSize;
    	System.out.println("Average Fitness of the current generation " + currentGeneration + ": " + avgFitness);
    	System.out.println("Current estimate of x: " + population[0].getXvalue());
    	if (population[0].getFitness() <= 0.001) {
    		return true;
    	}
    	return false;
    }
    
    /**
     * print out all the members of population
     * shows the value of each individual and its fitness score
     */
    public void printGeneration() {
    	Arrays.sort(population);
    	System.out.println("Printing generation " + currentGeneration + ": ");
    	for (int i = 0; i < populationSize; i++) {
    		System.out.println("Individual " + i + ": Value = " + population[i].getXvalue() + " Fitness = " + population[i].getFitness());
    	}
    }
}
