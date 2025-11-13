import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Window;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Optional;
import java.awt.event.ActionEvent;

public class DBTestWindow {

	private JFrame frmDbTest;
	private JTextField existing_person_id_field;
	private JButton add_new_person_button;
	private JButton remove_person_button;
	FamilyTreeDatabase db = new FamilyTreeDatabaseSimple("family_tree.db");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DBTestWindow window = new DBTestWindow();
					window.frmDbTest.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DBTestWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDbTest = new JFrame();
		frmDbTest.setTitle("DB Test");
		frmDbTest.setBounds(100, 100, 671, 75);
		frmDbTest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDbTest.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel existing_person_id_label = new JLabel("Existing Person ID:");
		frmDbTest.getContentPane().add(existing_person_id_label);
		
		existing_person_id_field = new JTextField();
		frmDbTest.getContentPane().add(existing_person_id_field);
		existing_person_id_field.setColumns(10);
		
		JButton edit_info_button = new JButton("Edit Personal Info");
		edit_info_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Optional<Person> person = db.findById(existing_person_id_field.getText());
				System.out.println(person);
				if(person.isPresent()) {
					System.out.println(existing_person_id_field.getText());
					DataEntryWindow data_entry_window = new DataEntryWindow(person);
					data_entry_window.frmPersonalInformationEntry.setVisible(true);
				}

			}
		});
		frmDbTest.getContentPane().add(edit_info_button);
		
		remove_person_button = new JButton("Remove Person");
		frmDbTest.getContentPane().add(remove_person_button);
		
		add_new_person_button = new JButton("Add New Person");
		add_new_person_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Optional<Person> person = java.util.Optional.empty();
				DataEntryWindow data_entry_window = new DataEntryWindow(person);
				data_entry_window.frmPersonalInformationEntry.setVisible(true);
			}
		});
		frmDbTest.getContentPane().add(add_new_person_button);
	}

}
