package postgresqltutorial;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class StaffUI extends JPanel {
	
	private JButton btnBand, btnContactPerson, btnWorker, btnSchedule;

	private JButton btnAddBand, btnRemoveBand,btnAddMember,btnRemoveMember;

	private JButton btnShowContacts;
	
	private JButton btnAddWorker, btnRemoveWorker;
	
	private JButton btnAddPlayTime, btnRemovePlayTime, btnShowWorker;

	private JPanel pnlMain, pnlOverView, pnlBand, 
				    pnlContactPerson, pnlSchedule, pnlWorker;

	private JLabel lblTitle;
	
	private InfoModifier update;
	
	public StaffUI(String pass) {

		update = new InfoModifier(pass);
		pnlOverView = showOverView();
		pnlBand = showBandUI();
		pnlContactPerson = showContactPersonUI();
		pnlSchedule = showScheduleUI();
		pnlWorker = showWorkerUI();
		
		pnlMain = this;

		pnlMain.add(pnlOverView);

	}
	
	public JButton getToOverView() {
		JButton btnBack = new JButton("Till översikt");
		btnBack.setPreferredSize(new Dimension(150,30));
		btnBack.addActionListener(new OverViewListener());
		return btnBack;
	}

	public void clearPanel(JPanel panel) {
		panel.removeAll();
		panel.repaint();
	}

	public JPanel showOverView() {
		pnlOverView = new JPanel();

		pnlOverView.setPreferredSize(new Dimension(800, 600));
		pnlOverView.setBackground(Color.LIGHT_GRAY);
		
		lblTitle = new JLabel("Menu");
		lblTitle.setFont(new Font("TimesRoman", Font.PLAIN, 45));
		lblTitle.setHorizontalAlignment(JLabel.CENTER);
		lblTitle.setPreferredSize(new Dimension(550, 80));
		pnlOverView.add(lblTitle);
		
		JPanel pnlMenu = new JPanel();
		pnlMenu.setPreferredSize(new Dimension(700, 400));
		pnlMenu.setBackground(Color.LIGHT_GRAY);

		btnBand = new JButton("Band");
		btnBand.setPreferredSize(new Dimension(330, 180));
		btnBand.addActionListener(new BandListener());
		pnlMenu.add(btnBand);
		
		btnWorker = new JButton("Arbetare");
		btnWorker.setPreferredSize(btnBand.getPreferredSize());
		btnWorker.addActionListener(new WorkerListener());
		pnlMenu.add(btnWorker);
		
		btnContactPerson = new JButton("Kontaktpersoner");
		btnContactPerson.setPreferredSize(btnBand.getPreferredSize());
		btnContactPerson.addActionListener(new ContactListener());
		pnlMenu.add(btnContactPerson);

		btnSchedule = new JButton("Schema");
		btnSchedule.setPreferredSize(btnBand.getPreferredSize());
		btnSchedule.addActionListener(new ScheduleListener());
		pnlMenu.add(btnSchedule);

		pnlOverView.add(pnlMenu);
		return pnlOverView;

	}

	public JPanel showBandUI() {
		pnlBand = new JPanel();
		pnlBand.setPreferredSize(pnlOverView.getPreferredSize());
		pnlBand.setBackground(Color.LIGHT_GRAY);
		pnlBand.add(getToOverView());

		lblTitle = new JLabel("Band");
		lblTitle.setFont(new Font("TimesRoman", Font.PLAIN, 45));
		lblTitle.setHorizontalAlignment(JLabel.CENTER);
		lblTitle.setPreferredSize(new Dimension(550, 80));
		pnlBand.add(lblTitle);
		
		JPanel pnlMenu = new JPanel();
		pnlMenu.setPreferredSize(new Dimension(600, 300));
		pnlMenu.setBackground(Color.LIGHT_GRAY);

		btnAddBand = new JButton("Lägg till band");
		btnAddBand.setPreferredSize(new Dimension(280, 120));	
		btnAddBand.addActionListener(new BandListener());
		pnlMenu.add(btnAddBand);

		btnRemoveBand = new JButton("Ta bort band");
		btnRemoveBand.setPreferredSize(btnAddBand.getPreferredSize());
		btnRemoveBand.addActionListener(new BandListener());
		pnlMenu.add(btnRemoveBand);
		
		btnAddMember = new JButton("Lägg till medlem");
		btnAddMember.setPreferredSize(new Dimension(280, 120));
		btnAddMember.addActionListener(new MemberListener());
		pnlMenu.add(btnAddMember);

		btnRemoveMember = new JButton("Ta bort medlem");
		btnRemoveMember.setPreferredSize(btnAddBand.getPreferredSize());
		btnRemoveMember.addActionListener(new MemberListener());
		pnlMenu.add(btnRemoveMember);
		pnlBand.add(pnlMenu);
		
		return pnlBand;
	}

	public JPanel showContactPersonUI() {
		pnlContactPerson = new JPanel();
		pnlContactPerson.setPreferredSize(pnlBand.getPreferredSize());
		pnlContactPerson.setBackground(Color.LIGHT_GRAY);
		
		pnlContactPerson.add(getToOverView());
		lblTitle = new JLabel("Kontaktpersoner");
		lblTitle.setFont(new Font("TimesRoman", Font.PLAIN, 45));
		lblTitle.setHorizontalAlignment(JLabel.CENTER);
		lblTitle.setPreferredSize(new Dimension(550, 80));
		pnlContactPerson.add(lblTitle);

		btnShowContacts = new JButton("Lista alla kontaktpersoner");
		btnShowContacts.setPreferredSize(new Dimension(250, 40));
		btnShowContacts.addActionListener(new ContactListener());
		pnlContactPerson.add(btnShowContacts);

	
		return pnlContactPerson;
	}
	
	public JPanel showWorkerUI () {
		pnlWorker = new JPanel();
		pnlWorker.setPreferredSize(pnlBand.getPreferredSize());
		pnlWorker.setBackground(Color.LIGHT_GRAY);

		pnlWorker.add(getToOverView());
		lblTitle = new JLabel("Arbetare");
		lblTitle.setFont(new Font("TimesRoman", Font.PLAIN, 45));
		lblTitle.setHorizontalAlignment(JLabel.CENTER);
		lblTitle.setPreferredSize(new Dimension(550, 80));
		pnlWorker.add(lblTitle);

		btnAddWorker = new JButton("Lägg till Arbetare");
		btnAddWorker.setPreferredSize(new Dimension(400, 100));
		btnAddWorker.addActionListener(new WorkerListener());
		pnlWorker.add(btnAddWorker);

		btnRemoveWorker = new JButton("Ta bort Arbetare");
		btnRemoveWorker.setPreferredSize(new Dimension(300,50));
		btnRemoveWorker.addActionListener(new WorkerListener());
		pnlWorker.add(btnRemoveWorker);
		
		btnShowWorker = new JButton("Visa arbetare");
		btnShowWorker.setPreferredSize(btnAddPlayTime.getPreferredSize());
		btnShowWorker.addActionListener(new ScheduleListener());
		pnlWorker.add(btnShowWorker);
		
		
		
		return pnlWorker;
	}

	public JPanel showScheduleUI() {
		pnlSchedule = new JPanel();
		pnlSchedule.setPreferredSize(pnlBand.getPreferredSize());
		pnlSchedule.setBackground(Color.LIGHT_GRAY);
		
		pnlSchedule.add(getToOverView());
		lblTitle = new JLabel("Spelschema");
		lblTitle.setFont(new Font("TimesRoman", Font.PLAIN, 45));
		lblTitle.setHorizontalAlignment(JLabel.CENTER);
		lblTitle.setPreferredSize(new Dimension(800, 80));
		pnlSchedule.add(lblTitle);
		
		btnAddPlayTime = new JButton("Lägg till speltid");
		btnAddPlayTime.setPreferredSize(new Dimension(300, 60));
		btnAddPlayTime.addActionListener(new ScheduleListener());
		pnlSchedule.add(btnAddPlayTime);
		
		btnRemovePlayTime = new JButton("Ta bort speltid");
		btnRemovePlayTime.setPreferredSize(btnAddPlayTime.getPreferredSize());
		btnRemovePlayTime.addActionListener(new ScheduleListener());
		pnlSchedule.add(btnRemovePlayTime);
		
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

	private class BandListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnBand) {
				clearPanel(pnlMain);
				pnlMain.add(pnlBand);
				revalidate();
			}
			
			if(e.getSource() == btnAddBand) {
				String addBand = JOptionPane.showInputDialog("Skriv in bandid,land,band namn och kontaktperson personnummer");
				update.addBand(addBand);
			}
			
			if(e.getSource() == btnRemoveBand) {
				int removeBand = Integer.parseInt(JOptionPane.showInputDialog("Ta bort band med Id: "));
				update.removeBand(removeBand);
			}

		}

	}
	
	private class MemberListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			
			if(e.getSource() == btnAddMember) {
				String arr [] = {"Inte medlem i annat band", "Är redan medlem i annat band"};
				int response = JOptionPane.showOptionDialog(null, "Välj alternativ", "Lägg till Medlem", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, arr, arr[0]);
				
				if(response == 0) {
				String addMember = JOptionPane.showInputDialog("Skriv in bandid,memberid och namnet medlemmen" );
				String parts [] = addMember.split(",");
				update.addMember(Integer.parseInt(parts[1]),parts[2]);
				update.updatePartOf(Integer.parseInt(parts[0]),Integer.parseInt(parts[1]));
				} else {
					String alreadyMember = JOptionPane.showInputDialog("Skriv in bandid,memberid: " );
					String partsOfAlready [] = alreadyMember.split(",");
					update.updatePartOf(Integer.parseInt(partsOfAlready[0]),Integer.parseInt(partsOfAlready[1]));
				}
					
			}
			
			if(e.getSource() == btnRemoveMember) {
				String res = JOptionPane.showInputDialog("Ta bort medlem med id och bandets id som han/hon tillhör: ");
				String parts[] = res.split(",");
				
				
				update.removeMember(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
				
			}
			
			
		}
		
	}
	
	private class WorkerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == btnWorker) {
				clearPanel(pnlMain);
				pnlMain.add(pnlWorker);
				revalidate();
			}
			
			if(e.getSource() == btnAddWorker) {
				String addWorker = JOptionPane.showInputDialog("Skriv in personnummer,name och adress " );
				update.addWorker(addWorker);
				
			}
			
			if(e.getSource() == btnRemoveWorker) {
				int removeWorker = Integer.parseInt(JOptionPane.showInputDialog("Ta bort arbetare med personnummer: " ));
				update.removeWorker(removeWorker);
				
			}

		}

	}

	private class ContactListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnContactPerson) {
				clearPanel(pnlMain);
				pnlMain.add(pnlContactPerson);
				revalidate();
			}
			
			if(e.getSource() == btnShowContacts) {
				ArrayList<String> addContact = update.getContactList();
				JOptionPane.showMessageDialog(null, addContact.toString());
			}
			
		}

	}
	
	private class ScheduleListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			ArrayList<String> showWork = null;
			if(e.getSource() == btnSchedule) {
				clearPanel(pnlMain);
				pnlMain.add(pnlSchedule);
				revalidate();
			}
			
			if(e.getSource() == btnAddPlayTime) {
				String addPlay = JOptionPane.showInputDialog("Lägg till bandid,tid,event, dag och säkerhetsvakt");
				update.addPlay(addPlay);
			}
			
			if(e.getSource() == btnRemovePlayTime) {
				String removePlay =JOptionPane.showInputDialog("Ta bort speltid med tid och scen: " );
				String parts[] = removePlay.split(",");
			
				update.removePlay(parts[0],parts[1]);
			}
			
			if(e.getSource() == btnShowWorker) {
				showWork = update.getWorkers();
				JOptionPane.showMessageDialog(null, showWork.toString());
			}
		}
	}
	
	public static void main(String[] args) {
		
		String pass = JOptionPane.showInputDialog("Skriv in lösenordet");
		StaffUI staff = new StaffUI(pass);
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(staff);
		f.setLocation(300, 100);
		f.setSize(800, 600);
		f.setVisible(true);
		f.setResizable(false);
	}

}
