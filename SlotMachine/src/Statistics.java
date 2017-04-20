
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class Statistics extends JFrame {
	JFrame frame2 = new JFrame();
	static ArrayList<Reel> gameList = new ArrayList<Reel>();
	static JButton saveStat;
	static JLabel avg = new JLabel("");
	static JLabel result = new JLabel("");
	JTable records;
	JPanel panel = new JPanel();
	JPanel wPanel = new JPanel();

	public void saveData() throws FileNotFoundException {// Method which write
															// statistics to the
															// file
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy HH-mm-ss");
		Date date = new Date();
		PrintWriter text = new PrintWriter(dateFormat.format(date) + "");
		for (int i = 0; i < gameList.size(); i++) {
			text.println(
					(gameList.get(i)).status + "\t" + (gameList.get(i)).coinsR + "\t" + (gameList.get(i)).date_time);

		}

		JOptionPane.showMessageDialog(frame2, "Saved Successfully!", "Save", JOptionPane.INFORMATION_MESSAGE);
		text.println(avg.getText());
		text.println(result.getText());
		text.close();

	}

	public Statistics() {

		frame2.setTitle("Statistics");
		frame2.setLayout(new FlowLayout());
		frame2.setVisible(true);
		frame2.setSize(400, 200);
		frame2.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		saveStat = new JButton("Save Statistics");

		saveStat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					saveData();
				} catch (FileNotFoundException e1) {
					JOptionPane.showMessageDialog(frame2, "Saved Successfully!", "Save",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		records = new JTable();
		JScrollPane js = new JScrollPane(records);
		frame2.add(js);
		if (Reel.nSpins < 1) {
			saveStat.setEnabled(false);

		}
		

		wPanel.setLayout(new GridLayout(2, 1, 3, 3));
		wPanel.setPreferredSize(new Dimension(400, 400));
		wPanel.setBackground(Color.lightGray);
		wPanel.add(js);
		wPanel.add(panel);
		wPanel.add(saveStat);
		
		panel.setLayout(new GridLayout(2, 1, 3, 3));
		panel.setPreferredSize(new Dimension(400, 50));
		panel.setBackground(Color.white);
		frame2.add(saveStat);
		panel.add(avg);
		panel.add(result);
		frame2.add(wPanel);
		// How data gets added to the table is shown below

		ArrayList<Object[]> list = new ArrayList<Object[]>();

		for (int i = 0; i < gameList.size(); i++) {
			list.add(new Object[] { gameList.get(i).status, gameList.get(i).coinsR, gameList.get(i).date_time });

		}
		records.setModel(new DefaultTableModel(list.toArray(new Object[][] {}),
				new String[] { "Status", "Coins Netted", "Date and Time" }));

	}
}
