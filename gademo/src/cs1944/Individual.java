/**
 * 
 * An individual in the population for genetic algorithm
 * Provides functionality like calculating fitness, mutation and crossover
 * @author Graham Wobig
 * @date 11/18/20
 * 
 */

package cs1944;

import java.util.Random;

public class Individual implements Comparable<Individual> {
	/**
	 * use a string to represent the current x-value of an individual
	 * This string will have the form x.xxxxx
	 * Doing this to simplify crossover between two individuals
	 */
	private String Xvalue;
	//fitness value of this individual
	private double fitness;
	
	/**
	 * constructor for an individual
	 */
	public Individual(String initialValue) {
		double value = Double.parseDouble(initialValue);
		if (value >= 0) { 
			this.Xvalue = initialValue.substring(0, 7);
		}
		else {
			this.Xvalue = initialValue.substring(0, 8);
		}
	}
	
	/**
	 * calculate the fitness of an individual
	 * will update the fitness field
	 * fitness function for the demo code is relative error
	 */
	public void calculateFitness() {
		double numericalValue = Double.parseDouble(Xvalue);
		double expectedValue = 1.0;
		double error = (numericalValue - expectedValue) / expectedValue;
		double adjustedError = error * 100;
		this.fitness = Math.abs(adjustedError);
	}

	/**
	 * Return the value of the xvalue of the individual 
	 */
	public String getXvalue() {
		return this.Xvalue;
	}

	/**
	 * Return the fitness of an individual
	 */
	public double getFitness() {
		return this.fitness;
	}
	
	/**
	 * perform mutation on this individual
	 * Will mutate at a random point in the chromosome string
	 */
	public void mutate() {
		Random r = new Random();
		int max1 = 6;
		int max2 = 10;
		int max3 = 2;
		int min = -2;
		int mutateLocation = r.nextInt(max1);
		int mutateValue = r.nextInt(max2);
		String newValue;
		double currValue = Double.parseDouble(Xvalue);
		if (currValue > 0) {
			if (mutateLocation == 0) {
				mutateValue = r.nextInt(max3 - min) + min;
				newValue = Integer.toString(mutateValue) + Xvalue.substring(1);
			}
			else if (mutateLocation == 1) {
				newValue = Xvalue.substring(0, 2) + Integer.toString(mutateValue) + Xvalue.substring(3);
			}
			else if (mutateLocation == 5) {
				newValue = Xvalue.substring(0, 6) + Integer.toString(mutateValue);
			}
			else {
				newValue = Xvalue.substring(0, mutateLocation + 1) + Integer.toString(mutateValue) + Xvalue.substring(mutateLocation + 2);
			}
		}
		else {
			if (mutateLocation == 0) {
				mutateValue = r.nextInt(max3 - min) + min;
				newValue = Integer.toString(mutateValue) + Xvalue.substring(2);
			}
			else if (mutateLocation == 1) {
				newValue = Xvalue.substring(0, 3) + Integer.toString(mutateValue) + Xvalue.substring(4);
			}
			else if (mutateLocation == 5) {
				newValue = Xvalue.substring(0, 7) + Integer.toString(mutateValue);
			}
			else {
				newValue = Xvalue.substring(0, mutateLocation + 2) + Integer.toString(mutateValue) + Xvalue.substring(mutateLocation + 3);
			}
		}
		Xvalue = newValue;
	}
	
	/**
	 * This function allows two parents to "breed" an offspring for
	 * next generation of the population
	 * 
	 * @param parent1 first parent for crossover
	 * @param parent2 second parent for crossover
	 */
	public void crossover(Individual parent1, Individual parent2) {
		Double p1value = Double.parseDouble(parent1.getXvalue());
		Double p2value = Double.parseDouble(parent2.getXvalue());
		Random r = new Random();
		int crossPt;
		String newValue;
		//case where both p1 and p2 have form x.xxxxx
		if (p1value > 0 && p2value > 0) {
			crossPt = r.nextInt(6);
			newValue = parent1.getXvalue().substring(0, crossPt) + parent2.getXvalue().substring(crossPt);
		}
		//case where both p1 and p2 have form -x.xxxxx
		else if (p1value < 0 && p2value < 0) {
			crossPt = r.nextInt(7);
			newValue = parent1.getXvalue().substring(0, crossPt) + parent2.getXvalue().substring(crossPt);
		}
		//case where p1 has form -x.xxxxx and p2 has form x.xxxxx
		else if (p1value < 0) {
			crossPt = r.nextInt(7);
			if (crossPt == 7) {
				newValue = parent1.getXvalue();
			}
			else if (crossPt == 0) {
				newValue = parent1.getXvalue().substring(0, 2) + parent2.getXvalue().substring(1);
			}
			else {
				newValue = parent1.getXvalue().substring(0, crossPt) + parent2.getXvalue().substring(crossPt - 1);
			}
		}
		//case where p1 has form x.xxxxx and p2 has form -x.xxxxx
		else {
			crossPt = r.nextInt(6);
			newValue = parent1.getXvalue().substring(0, crossPt) + parent2.getXvalue().substring(crossPt + 1);
		}
		Xvalue = newValue;
	}
	
	/**
	 * comparator for sorting individuals by fitness
	 */
	@Override
	public int compareTo(Individual i) {
		double compare = this.fitness - i.getFitness();
		if (compare < 0.0) {
			return -1;
		}
		else if (compare > 0.0) {
			return 1;
		}
		else {
			return 0;
		}
	}
}
