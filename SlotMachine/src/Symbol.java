import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class Symbol extends JFrame {
	String date_time;
	String status;
	int coinsR;
	private String image;
	private int value;

	public void setImage(String i) {
		this.image = i;
	}

	public void setValue(int v) {
		this.value = v;
	}

	public String getImage() {
		return this.image;

	}

	public int getValue() {
		return this.value;

	}

	public void compare() {// Method which compares the symbols
		/*
		 * getting the value of the symbols according to the variables declared
		 * in classes Reel1,Reel2 and Reel3
		 */
		int com1 = ((Reel.list).get(Reel1.i1)).getValue();
		int com2 = ((Reel.list).get(Reel2.i2)).getValue();
		int com3 = ((Reel.list).get(Reel3.i3)).getValue();

		if (Reel.stop1 && Reel.stop2 && Reel.stop3 && Reel.nSpins > 0) {// checking
																		// if
																		// all 3
																		// reels
																		// are
																		// stopped.

			Reel.betOne.setEnabled(true);
			Reel.betMax.setEnabled(true);
			Reel.reset.setEnabled(true);
			Reel.stat.setEnabled(true);
			Reel.addCoin.setEnabled(true);
			Reel.reel_1.setEnabled(false);
			Reel.reel_2.setEnabled(false);
			Reel.reel_3.setEnabled(false);

			if (com1 == com2 && com1 == com3) {
				Reel.reset.setEnabled(false);
				JOptionPane.showMessageDialog(Reel.frame1, "You win!You netted " + Reel.bet * com1 + " Coins",
						"Game Result", JOptionPane.INFORMATION_MESSAGE);
				(Reel.info).setText("You won " + Reel.bet * com1 + " Coins");
				Reel.coins += Reel.bet * com1;// Adding credits netted to the
												// current credit value
				coinsR = Reel.bet * com1;
				Reel.total += coinsR;
				status = "Win";
				Reel.bet = 0;
				Reel.wins++;
				(Reel.credits).setText("You have " + Reel.coins + " credits left to play with");
			} else if (com1 == com2 || com1 == com3 || com2 == com3) {
				JOptionPane.showMessageDialog(Reel.frame1, "You win!Free spin recieved!", "Game Result",
						JOptionPane.INFORMATION_MESSAGE);
				(Reel.info).setText("You Won a free spin!");
				Reel.spin.setEnabled(true);
				status = "Win";
				Reel.wins++;
				coinsR = 0;
				(Reel.credits).setText("You have " + Reel.coins + " credits left to play with");
			} else {
				Reel.reset.setEnabled(false);
				JOptionPane.showMessageDialog(Reel.frame1, "You Lose!Try again!", "Game Result",
						JOptionPane.INFORMATION_MESSAGE);
				(Reel.info).setText("You Lost");
				status = "Loss";
				(Reel.credits).setText("You have " + Reel.coins + " credits left to play with");
				Reel.total -= Reel.bet;
				coinsR = 0 - Reel.bet;
				Reel.bet = 0;
				Reel.losses++;

			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy HH:mm:ss");
			Date date = new Date();
			date_time = dateFormat.format(date);
			table();
			Reel.betArea.setText("You have placed a bet of " + Reel.bet + " credits");
			double avg = (double) Reel.total / (double) Reel.nSpins;// calculating
																	// the
																	// average
																	// number of
																	// credits
																	// netted
																	// per game

			if (avg > 0) {
				Statistics.result.setText("You have been on average winning more credits than losing ");
				Statistics.avg.setText("Number of Wins: " + Reel.wins + "| Number of Losses: " + Reel.losses
						+ "| Average credits netted: " + avg);
			} else {
				Statistics.result.setText("You have been on average losing more credits than winning ");
				Statistics.avg.setText("Number of Wins: " + Reel.wins + "| Number of Losses: " + Reel.losses
						+ "| Average credits netted: " + avg);
			}
		}

	}

	public void table() {

		Reel stat = new Reel(status, coinsR, date_time);//Creating an object with status,number of credits netted and the current date time
		(Statistics.gameList).add(stat);//the object will be added to a list in the class statistics so that it can be used when adding data to the table "records"
	}
}
