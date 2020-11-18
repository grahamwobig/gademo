/**
 * 
 * Testing function to check the individual is behaving as expected
 * @author Graham Wobig
 * @date 11/18/20
 * 
 */

package cs1944;

public class TestIndividual {
	//dummy constructor, do nothing
	public TestIndividual() {
		return;
	}
	
	public void IndvTest() {
		//test the actual one
		Individual actual = new Individual("1.00000");
		actual.calculateFitness();
		System.out.println("Actual: " + actual.getFitness());
		//test a close one
		Individual close = new Individual("1.10000");
		close.calculateFitness();
		System.out.println("Close: " + close.getFitness());
		//test a far one
		Individual far = new Individual("2.50000");
		far.calculateFitness();
		System.out.println("Far: " + far.getFitness());
		//test a negative one
		Individual neg = new Individual("-1.30000");
		neg.calculateFitness();
		System.out.println("Negative: " + neg.getFitness());
		System.out.println("\nTESTING Mutation\n");
		Individual mutate1 = new Individual("1.23456");
		Individual mutate2 = new Individual("2.14506");
		Individual mutate3 = new Individual("-1.22344");
		for (int i = 0; i < 10; i++) {
			System.out.println("Values before mutation " + i + ":");
			System.out.println("Individual 1: " + mutate1.getXvalue());
			System.out.println("Individual 2: " + mutate2.getXvalue());
			System.out.println("Individual 3: " + mutate3.getXvalue());
			mutate1.mutate();
			mutate2.mutate();
			mutate3.mutate();
			System.out.println("Values after mutation " + i + ":");
			System.out.println("Individual 1: " + mutate1.getXvalue());
			System.out.println("Individual 2: " + mutate2.getXvalue());
			System.out.println("Individual 3: " + mutate3.getXvalue());
		}
		System.out.println("\nTesting Crossover\n");
		Individual cross1 = new Individual("0.00000");
		Individual cross2 = new Individual("0.00000");
		Individual cross3 = new Individual("0.00000");
		Individual cross4 = new Individual("0.00000");
		Individual p1 = new Individual("1.23456");
		Individual p2 = new Individual("0.67891");
		Individual p3 = new Individual("-1.23456");
		Individual p4 = new Individual("-2.67891");
		for (int i = 0; i < 10; i++) {
			System.out.println("Values before crossover" + i + ":");
			System.out.println("Individual 1: " + cross1.getXvalue());
			System.out.println("Individual 2: " + cross2.getXvalue());
			System.out.println("Individual 3: " + cross3.getXvalue());
			System.out.println("Individual 4: " + cross4.getXvalue());
			cross1.crossover(p1, p2);
			cross2.crossover(p3, p4);
			cross3.crossover(p2, p3);
			cross4.crossover(p4, p1);
			System.out.println("Values after crossover " + i + ":");
			System.out.println("Individual 1: " + cross1.getXvalue());
			System.out.println("Individual 2: " + cross2.getXvalue());
			System.out.println("Individual 3: " + cross3.getXvalue());
			System.out.println("Individual 4: " + cross4.getXvalue());
		}
	}
}
