package postgresqltutorial;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VisitorUI extends JPanel {

	private ArrayList<String> bands;
	private ArrayList<String> schedule = new ArrayList<>();
	private ArrayList<String> members = new ArrayList<>();
	private ArrayList<JButton> btnBandList = new ArrayList<>();

	private JButton btnSchedule, btnBand, btnThursday, btnFriday, btnSaturday;

	private JPanel pnlOverView, pnlMain, pnlBandUI, pnlBands, pnlSchedule, pnlThursday, pnlFriday, pnlSaturday;

	private JLabel lblTitle;

	private RetrieveInfo retrieveInfo;

	public VisitorUI(String password) {

		retrieveInfo = new RetrieveInfo(password);
		pnlOverView = showOverView();
		pnlBands = showBand();
		pnlSchedule = showSchedule();

		pnlMain = this;
		pnlMain.add(pnlOverView);

	}

	public JButton getToOverView() {
		JButton btnBack = new JButton("Till översikt");
		btnBack.setPreferredSize(new Dimension(150, 20));
		btnBack.addActionListener(new OverViewListener());
		return btnBack;
	}

	public void clearPanel(JPanel panel) {
		panel.removeAll();
		panel.repaint();
	}

	public void clearSchedule() {
		pnlSchedule.remove(pnlFriday);
		pnlSchedule.remove(pnlThursday);
		pnlSchedule.remove(pnlSaturday);
	}

	public JPanel showOverView() {
		pnlOverView = new JPanel();
		pnlOverView.setPreferredSize(new Dimension(800, 600));
		pnlOverView.setBackground(Color.LIGHT_GRAY);
		
		lblTitle = new JLabel("Meny");
		lblTitle.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 35));
		lblTitle.setHorizontalAlignment(JLabel.CENTER);
		lblTitle.setPreferredSize(new Dimension(550, 80));
		pnlOverView.add(lblTitle);

		btnSchedule = new JButton("Schema");
		btnSchedule.setPreferredSize(new Dimension(500, 200));
		btnSchedule.addActionListener(new ScheduleListener());
		pnlOverView.add(btnSchedule);

		btnBand = new JButton("Band");
		btnBand.setPreferredSize(btnSchedule.getPreferredSize());
		btnBand.addActionListener(new BandListener());
		pnlOverView.add(btnBand);

		return pnlOverView;
	}

	public JPanel showBand() {

		pnlBandUI = new JPanel();
		pnlBandUI.setPreferredSize(new Dimension(800, 600));
		pnlBandUI.setBackground(Color.LIGHT_GRAY);

		pnlBandUI.add(getToOverView());

		pnlBands = new JPanel();
		pnlBands.setBackground(Color.GRAY);
		pnlBands.setLayout(new GridLayout(0, 5));

		lblTitle = new JLabel("Band som har spelningar");
		lblTitle.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 35));
		lblTitle.setHorizontalAlignment(JLabel.CENTER);
		lblTitle.setPreferredSize(new Dimension(550, 80));
		pnlBandUI.add(lblTitle);
		
		int vert = ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER;
		int horiz = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;
		JScrollPane jsp = new JScrollPane(pnlBands, vert, horiz);
		jsp.setPreferredSize(new Dimension(500, 300));
		jsp.setBackground(Color.WHITE);
		
		
		pnlBandUI.add(jsp);

		bands = retrieveInfo.getBands();
		
		for (int i = 0; i < bands.size(); i++) {
			String parts[] = bands.get(i).split(",");
			btnBandList.add(new JButton(parts[1] + ", " + parts[2]));
			btnBandList.get(i).addActionListener(new ListenerMember());
			pnlBands.add(btnBandList.get(i));
		}
		
		return pnlBandUI;

	}

	public JPanel showSchedule() {
		JPanel pnlSchedule = new JPanel();
		pnlSchedule.setPreferredSize(new Dimension(800, 600));
		pnlSchedule.setBackground(Color.LIGHT_GRAY);

		pnlSchedule.add(getToOverView());

		lblTitle = new JLabel("Spelscheman:");
		lblTitle.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
		lblTitle.setHorizontalAlignment(JLabel.CENTER);
		lblTitle.setPreferredSize(new Dimension(450, 80));

		pnlSchedule.add(lblTitle);

		Dimension dim = new Dimension(200, 200);

		btnThursday = new JButton("Torsdag");
		btnThursday.setPreferredSize(dim);
		btnThursday.setBackground(Color.GRAY);
		btnThursday.addActionListener(new ScheduleListener());
		pnlSchedule.add(btnThursday);

		btnFriday = new JButton("Fredag");
		btnFriday.setPreferredSize(dim);
		btnFriday.setBackground(Color.GRAY);
		btnFriday.addActionListener(new ScheduleListener());
		pnlSchedule.add(btnFriday);

		btnSaturday = new JButton("Lördag");
		btnSaturday.setPreferredSize(dim);
		btnSaturday.setBackground(Color.GRAY);
		btnSaturday.addActionListener(new ScheduleListener());
		pnlSchedule.add(btnSaturday);

		return pnlSchedule;
	}

	private class OverViewListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			clearPanel(pnlMain);
			pnlMain.add(pnlOverView);
			pnlMain.revalidate();
		}
	}

	private class ScheduleListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnSchedule) {
				clearPanel(pnlMain);
				pnlMain.add(pnlSchedule);
				revalidate();
			}
			if (e.getSource() == btnThursday) {
				
				ArrayList<String> arrThursday = retrieveInfo.getSchedule(btnThursday.getText().toString());
				
				
				JOptionPane.showMessageDialog(null, arrThursday.toString());
				
			}
			if (e.getSource() == btnFriday) {

				ArrayList<String> arrFriday = retrieveInfo.getSchedule(btnFriday.getText().toString());
				
				
				JOptionPane.showMessageDialog(null, arrFriday.toString());

			}
			if (e.getSource() == btnSaturday) {

				ArrayList<String> arrSaturday = retrieveInfo.getSchedule(btnSaturday.getText().toString());
				
				
				JOptionPane.showMessageDialog(null, arrSaturday.toString());	

			}
		}
	}

	private class BandListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == btnBand) {
				clearPanel(pnlMain);
				pnlMain.add(pnlBands);
				pnlBands.revalidate();
			}

		}
	}

	private class ListenerMember implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			ArrayList<String> groupMembers = null;
			String res = "";

			for (int i = 0; i < btnBandList.size(); i++) {
				
				if (btnBandList.get(i) == e.getSource()) {
					String parts[] = bands.get(i).split(",");
					String partButtonList [] = btnBandList.get(i).getText().split(",");
					
					
					if (partButtonList[0].equals(parts[1])) {
						groupMembers = retrieveInfo.getMembers(Integer.parseInt(parts[0]));
						
						
						for(String s:groupMembers) {
							res += s + "\n";
						}
						
					}
				}
			}

			JOptionPane.showMessageDialog(null, "Members: \n" + res);

		}

	}

	public static void main(String[] args) {
		String pass = "Microsoft98";
		VisitorUI visitorUI = new VisitorUI(pass);
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(visitorUI);
		f.setLocation(300, 100);
		f.setSize(800, 600);
		f.setVisible(true);
		f.setResizable(false);
	}
}
