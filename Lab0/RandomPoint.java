import java.util.Random;
import java.lang.*;

// TODO
class RandomPoint extends Point {

	private static Random rng = new Random(1);

	public RandomPoint(double minX, double maxX, double minY, double maxY) {

		super((RandomPoint.rng.nextDouble() * (maxX - minX) + minX), (RandomPoint.rng.nextDouble() * (maxY - minY) + minY));

	}

	public static void setSeed(int seed) {

		RandomPoint.rng.setSeed(seed);

	}

}
