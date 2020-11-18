/**
 * 
 * The driver class for genetic algorithm demo
 * We use the genetic algorithm to maximize the function f(x) = x*e^(-x)
 * @author Graham Wobig
 * @date 11/18/20
 * 
 */

package cs1944;

public class GADemoDriver {

	public static void main(String[] args) {
		GADemo demo = new GADemo();
		while (!demo.testGeneration()) {
			demo.nextGeneration();
			demo.fitness();
		}
	}

}
