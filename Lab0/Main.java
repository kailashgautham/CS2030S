import java.lang.*;

class Main {

	public static void main(String [] args) {

		RandomPoint r = new RandomPoint(1,2,3,4);
		System.out.println(" " + r.getX() + " " +  r.getY());
		RandomPoint.setSeed(5);
		System.out.println(RandomPoint.getSeed());

	}

}
