import java.util.Random;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class Reel2 extends Reel implements Runnable {
	static int i2;

	public Reel2() {

	}

	public void run() {
		while (!stop2) {/*
						 * This loop will continue until the flag remains as
						 * false(Until user clicks on the button reel_2)
						 */
			Random rand = new Random();

			i2 = rand.nextInt(6);// a random number between 0 and 6 will be
									// assigned to the variable i2

			reel_2.setIcon(new ImageIcon(
					(list.get(i2)).getImage()));/*
												 * Image taken from the array
												 * will be set on the button
												 */
			try {
				Thread.sleep(100);// a new symbol will be shown in every 100
									// milliseconds
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

}
