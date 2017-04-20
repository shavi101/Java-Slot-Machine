import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class Reel extends JFrame {

	String date_time;
	String status;
	int coinsR;

	Symbol s = new Symbol();
	static boolean stop1;
	static boolean stop2;
	static boolean stop3;

	JPanel panel = new JPanel();
	static JFrame frame1 = new JFrame();

	static JButton addCoin;
	static JButton betOne = new JButton();
	static JButton betMax = new JButton();
	static JButton reset = new JButton();
	static JButton spin;
	static JButton reel_1;
	static JButton reel_2;
	static JButton reel_3;
	static JButton stat = new JButton();

	JPanel reels;
	JPanel spins;
	JPanel creditsPanel;
	JPanel betPanel;
	JPanel other;
	JPanel panel1;
	JPanel panel2;

	// Labels

	static JLabel credits;
	static JLabel betArea;
	static JLabel spinsArea;
	static JLabel info;
	static JLabel winsArea;
	static JLabel lossesArea;

	// static JLabel extra=new JLabel();

	static int coins;
	static int wins;
	static int losses;
	static int nSpins;
	static int bet;
	static int total;

	public static ArrayList<Symbol> list = new ArrayList<Symbol>();

	public Reel() {

		Collections.shuffle(list);
	}

	public Reel(String status, int coinsR, String date_time) {
		this.status = status;
		this.coinsR = coinsR;
		this.date_time = date_time;
	}

	void design() {// Method used to create the GUI

		symbol();
		coins = 10;
		bet = 0;
		wins = 0;
		losses = 0;
		frame1.setTitle("Slot Machine Application");
		frame1.setBackground(Color.gray);
		frame1.setLayout(new FlowLayout());

		frame1.setVisible(true);
		frame1.setSize(400, 200);
		frame1.setLocationRelativeTo(null);
		frame1.setDefaultCloseOperation(EXIT_ON_CLOSE);

		reels = new JPanel();// Container to hold the three reel buttons
		reels.setLayout(new GridLayout(1, 2, 3, 3));
		reels.setPreferredSize(new Dimension(1420, 500));
		reels.setBackground(Color.lightGray);

		reel_1 = new JButton(UIManager.getIcon("OptionPane.questionIcon"));
		reel_1.setPreferredSize(new Dimension(470, 450));
		reels.add(reel_1);
		reel_2 = new JButton(UIManager.getIcon("OptionPane.questionIcon"));
		reel_2.setPreferredSize(new Dimension(470, 450));
		reels.add(reel_2);
		reel_3 = new JButton(UIManager.getIcon("OptionPane.questionIcon"));
		reel_3.setPreferredSize(new Dimension(470, 450));
		reels.add(reel_3);

		frame1.add(reels);

		reel();// calling the reel() method which contains the invokation of
				// compare() method.

		spins = new JPanel();// Container to hold spin button,spins area, and
								// information
		spins.setLayout(new GridLayout(1, 3, 3, 3));
		spins.setPreferredSize(new Dimension(450, 30));
		spins.setBackground(Color.lightGray);

		spin = new JButton("Spin");
		spins.add(spin);
		spin.setEnabled(false);
		reelSpin();/*
					 * calling the reelSpin function which includes the
					 * invokation of 3 start methods of the three threads which
					 * belong to the classes Reel1,Reel2,Reel3
					 */

		spinsArea = new JLabel("You have tried " + nSpins + " spins");
		spins.add(spinsArea);

		info = new JLabel("");
		info.setBackground(Color.lightGray);
		spins.add(info);

		creditsPanel = new JPanel();// Container to hold add coin button &
									// credits area
		creditsPanel.setLayout(new GridLayout(1, 2, 3, 3));
		creditsPanel.setPreferredSize(new Dimension(450, 30));
		creditsPanel.setBackground(Color.lightGray);
		credits = new JLabel("You have " + coins + " credits left to play with");

		addCoin = new JButton("Add Coin");
		creditsPanel.add(addCoin);
		creditsPanel.add(credits);
		addCoin();// Calling the addCoin() method

		panel1 = new JPanel();// Container to hold panel spins and panel
								// creditsPanel
		panel1.setLayout(new GridLayout(2, 1, 5, 5));
		panel1.setPreferredSize(new Dimension(450, 100));
		panel1.setBackground(Color.white);
		panel1.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		panel1.add(spins);
		panel1.add(creditsPanel);
		frame1.add(panel1);// adding panel1 to the frame

		betPanel = new JPanel();// Container to hold bet one button,bet max
								// button, and betArea
		betPanel.setLayout(new GridLayout(2, 2, 3, 3));
		betPanel.setBackground(Color.lightGray);

		betOne = new JButton("Bet One");
		betMax = new JButton("Bet Max");
		betPanel.add(betOne);
		betPanel.add(betMax);
		bet();// calling bet() method
		betArea = new JLabel("You have placed a bet of " + bet + " credits");
		betPanel.add(betArea);

		reset = new JButton("Reset");
		reset.setEnabled(false);
		betPanel.add(reset);
		reset();// Calling reset method

		stat = new JButton("Statistics");

		stat();

		panel2 = new JPanel();// Container to hold panel betPanel and button
								// statistics
		panel2.setLayout(new GridLayout(1, 3, 3, 3));
		panel2.setPreferredSize(new Dimension(800, 100));
		panel2.setBackground(Color.white);
		panel2.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		panel2.add(betPanel);
		panel2.add(stat);
		frame1.add(panel2);// adding panel2 to the frame

	}

	void symbol() {/*
					 * Method which creates 6 instances of Symbol type. It sets
					 * the image and the value to the object.The objects created
					 * will be added to the array list "list"
					 */
		Symbol s1 = new Symbol();
		Symbol s2 = new Symbol();
		Symbol s3 = new Symbol();
		Symbol s4 = new Symbol();
		Symbol s5 = new Symbol();
		Symbol s6 = new Symbol();

		s1.setImage("C:\\Users\\DELL\\workspace\\SlotMachine\\src\\Images\\bell.png");
		s2.setImage("C:\\Users\\DELL\\workspace\\SlotMachine\\src\\Images\\cherry.png");
		s3.setImage("C:\\Users\\DELL\\workspace\\SlotMachine\\src\\Images\\lemon.png");
		s4.setImage("C:\\Users\\DELL\\workspace\\SlotMachine\\src\\Images\\plum.png");
		s5.setImage("C:\\Users\\DELL\\workspace\\SlotMachine\\src\\Images\\redseven.png");
		s6.setImage("C:\\Users\\DELL\\workspace\\SlotMachine\\src\\Images\\watermelon.png");

		s1.setValue(6);
		s2.setValue(2);
		s3.setValue(3);
		s4.setValue(4);
		s5.setValue(7);
		s6.setValue(5);

		list.add(s1);
		list.add(s2);
		list.add(s3);
		list.add(s4);
		list.add(s5);
		list.add(s6);

	}

	void addCoin() {// method which increments the number of credits when add
					// coin button is clicked
		addCoin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (coins >= 0)
					betOne.setEnabled(true);
				if (coins >= 2)
					betMax.setEnabled(true);

				Reel.coins++;
				credits.setText("You have " + Reel.coins + " credits left to play with");
			}
		});
	}

	void reel() {/*
					 * This method sets stop1,stop2 and stop3 as true (variables
					 * which are used as flags to identify if the reels are
					 * stopped).then the method compare() will be called
					 */
		reel_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stop1 = true;
				panel.setOpaque(false);
				s.compare();
			}
		});
		reel_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stop2 = true;
				panel.setOpaque(false);
				s.compare();
			}
		});

		reel_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stop3 = true;
				panel.setOpaque(false);
				s.compare();
			}
		});
	}

	void reelSpin() {/*
						 * Method which is used to creae the spinning effect It
						 * calls the start method on objects created by classes
						 * Reel1,Reel2, and Reel3(The 3 classes mentioned
						 * implements Runnable interface 3 threads are used
						 * here)
						 */

		spin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				info.setText(":D");
				Runnable r1 = new Reel1();
				Runnable r2 = new Reel2();
				Runnable r3 = new Reel3();

				Reel.reel_1.setEnabled(true);
				Reel.reel_2.setEnabled(true);
				Reel.reel_3.setEnabled(true);
				spin.setEnabled(false);
				betOne.setEnabled(false);
				betMax.setEnabled(false);
				reset.setEnabled(false);
				stat.setEnabled(false);
				addCoin.setEnabled(false);

				try {

					stop1 = false;
					stop2 = false;
					stop3 = false;

					new Thread(r1).start();
					new Thread(r2).start();
					new Thread(r3).start();
					Reel.nSpins++;
					spinsArea.setText("You have tried " + nSpins + " spins");
				} catch (Exception ex) {
					System.out.println(ex);
				}

				panel.setOpaque(false);
			}
		});
	}

	void bet() {/*
				 * Method used to increment the credits area and decrements bet
				 * area when user clicks on the buttons Bet One and Bet max
				 */
		betOne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset.setEnabled(true);
				if (coins <= 0) {
					JOptionPane.showMessageDialog(Reel.frame1, "Sorry!You have no credits left:( ", ":( ",
							JOptionPane.INFORMATION_MESSAGE);
					credits.setText("Sorry!You have no credits left:( ");
					betOne.setEnabled(false);
					betMax.setEnabled(false);
				} else {
					spin.setEnabled(true);
					try {
						Reel.coins--;
						Reel.bet++;
						credits.setText("You have " + coins + " credits left to play with");
						betArea.setText("You have placed a bet of " + bet + " credits");
					} catch (Exception ex) {
						System.out.println(ex);
					}
				}
				panel.setOpaque(false);
			}
		});

		betMax.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset.setEnabled(true);
				if (coins <= 2) {
					if (coins == 1 || coins == 2) {
						betMax.setEnabled(false);
					} else {
						betOne.setEnabled(false);
						betMax.setEnabled(false);
						JOptionPane.showMessageDialog(Reel.frame1, "Sorry!You have no credits left:( ", ":( ",
								JOptionPane.INFORMATION_MESSAGE);
						credits.setText("Sorry!You have no credits left:( ");
					}

				} else {
					spin.setEnabled(true);

					try {
						Reel.coins -= 3;
						Reel.bet += 3;
						credits.setText("You have " + coins + " credits left to play with");
						betArea.setText("You have placed a bet of " + bet + " credits");
					} catch (Exception ex) {
						System.out.println(ex);
					}
				}
				panel.setOpaque(false);
			}
		});
	}

	void reset() {// method which returns the amount bet to the credits area
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				spin.setEnabled(false);
				betOne.setEnabled(true);
				betMax.setEnabled(true);
				reset.setEnabled(false);
				Reel.coins += Reel.bet;
				Reel.bet = 0;
				credits.setText("You have " + Reel.coins + " credits left to play with");
				betArea.setText("You have placed a bet of " + Reel.bet + " credits");
				panel.setOpaque(false);
			}
		});
	}

	public void stat() {// method which opens the new window
		stat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (nSpins < 1) {// when no spins are done yet the following
									// message will be displayed
					JOptionPane.showMessageDialog(Reel.frame1, "No games played yet!", "Records",
							JOptionPane.INFORMATION_MESSAGE);
				}
				Statistics s = new Statistics();
				panel.setOpaque(false);
			}
		});
	}

	ArrayList<Symbol> spin() {// method which return the array list which
								// contains the 6 instances of Symbol type

		return list;

	}
}
