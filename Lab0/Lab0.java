import java.util.Scanner;

/**
 * CS2030S Lab 0: Estimating Pi with Monte Carlo
 * Semester 1, 2022/23
 *
 * <p>This program takes in two command line arguments: the 
 * number of points and a random seed.  It runs the
 * Monte Carlo simulation with the given argument and print
 * out the estimated pi value.
 *
 * @author XXX 
 */

class Lab0 {

  public static double estimatePi(int numOfPoints, int seed) {
  
	RandomPoint.setSeed(seed);
	Point centre = new Point(0.5, 0.5);
	Circle c = new Circle(centre, 0.5);
	int withinCircle = 0;
	RandomPoint r;

 	for (int i = 0; i < numOfPoints; i++) {

		RandomPoint rr = new RandomPoint(0, 1, 0, 1);

		if (c.contains(rr)) {
		
			withinCircle += 1;

		}

	}

	double piEstimate = (4.0 * withinCircle);
        piEstimate /= numOfPoints;

	return piEstimate;

	}


  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int numOfPoints = sc.nextInt();
    int seed = sc.nextInt();

    double pi = estimatePi(numOfPoints, seed);

    System.out.println(pi);
    sc.close();
  }
}
