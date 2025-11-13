import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JDialog;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import java.awt.Font;
import javax.swing.border.EmptyBorder;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DateEditor;
import javax.swing.JTable;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.JCheckBox;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import java.awt.FlowLayout;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.Component;

import javax.swing.table.DefaultTableModel;
import javax.swing.JEditorPane;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;

public class DataEntryWindow {

	JDialog frmPersonalInformationEntry;
	private JTextField name_field;
	int current_card = 1;
	boolean editable = false;
	List<Component> editable_fields = new ArrayList<Component>();
	List<Component> dod_fields = new ArrayList<Component>();
	FamilyTreeDatabase db = new FamilyTreeDatabaseSimple("family_tree.db");
	private JTextField gn_field;
	private JTextField rel_fn_field;
	private JTextField rel_ln_field;
	private JTextField rel_id_field;
	private JTable current_relationships_table;
	/**
	 * Create the application.
	 */
	public DataEntryWindow(Optional<Person> person) {
		initialize(person);
		
	}
	/**
	 * Initialize the contents of the frame. Creates a new person if the person parameter is null.
	 */
	private void initialize(Optional<Person> person) {
		
		frmPersonalInformationEntry = new JDialog();
		frmPersonalInformationEntry.setModal(true);
		frmPersonalInformationEntry.setTitle("Personal Information Entry");
		frmPersonalInformationEntry.setBounds(100, 100, 664, 407);
		frmPersonalInformationEntry.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{400, 0};
		gridBagLayout.rowHeights = new int[]{59, -22, 65, 41, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		frmPersonalInformationEntry.getContentPane().setLayout(gridBagLayout);
		
		JPanel top_navigation_panel = new JPanel();
		top_navigation_panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		GridBagConstraints gbc_top_navigation_panel = new GridBagConstraints();
		gbc_top_navigation_panel.insets = new Insets(0, 0, 5, 0);
		gbc_top_navigation_panel.fill = GridBagConstraints.BOTH;
		gbc_top_navigation_panel.gridx = 0;
		gbc_top_navigation_panel.gridy = 0;
		frmPersonalInformationEntry.getContentPane().add(top_navigation_panel, gbc_top_navigation_panel);
		GridBagLayout gbl_top_navigation_panel = new GridBagLayout();
		gbl_top_navigation_panel.columnWidths = new int[]{451, 51, 0};
		gbl_top_navigation_panel.rowHeights = new int[]{23, 0, 0};
		gbl_top_navigation_panel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_top_navigation_panel.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		top_navigation_panel.setLayout(gbl_top_navigation_panel);
		
		JLabel top_header_label = new JLabel("Personal Information Entry");
		top_header_label.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_top_header_label = new GridBagConstraints();
		gbc_top_header_label.insets = new Insets(0, 0, 5, 5);
		gbc_top_header_label.gridx = 0;
		gbc_top_header_label.gridy = 0;
		top_navigation_panel.add(top_header_label, gbc_top_header_label);
		
		JToggleButton edit_toggle_button = new JToggleButton("Edit");
		edit_toggle_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(edit_toggle_button.isSelected()) {
					for(Component field: editable_fields) {
						field.setEnabled(true);
					}
				}
				else {
					for(Component field: editable_fields) {
						field.setEnabled(false);
					}
				}
			}
		});
		GridBagConstraints gbc_edit_toggle_button = new GridBagConstraints();
		gbc_edit_toggle_button.insets = new Insets(0, 0, 5, 0);
		gbc_edit_toggle_button.gridx = 1;
		gbc_edit_toggle_button.gridy = 0;
		top_navigation_panel.add(edit_toggle_button, gbc_edit_toggle_button);
		
		JButton save_button = new JButton("Save");
		save_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JPanel page_header_cards = new JPanel();
		GridBagConstraints gbc_page_header_cards = new GridBagConstraints();
		gbc_page_header_cards.insets = new Insets(0, 0, 0, 5);
		gbc_page_header_cards.fill = GridBagConstraints.BOTH;
		gbc_page_header_cards.gridx = 0;
		gbc_page_header_cards.gridy = 1;
		top_navigation_panel.add(page_header_cards, gbc_page_header_cards);
		page_header_cards.setLayout(new CardLayout(0, 0));
		
		JPanel basic_info_header_panel = new JPanel();
		page_header_cards.add(basic_info_header_panel, "name_531809400882500");
		
		JLabel lblNewLabel_3 = new JLabel("Basic Info");
		basic_info_header_panel.add(lblNewLabel_3);
		
		JPanel relationship_info_header_panel = new JPanel();
		page_header_cards.add(relationship_info_header_panel, "name_532139495100699");
		
		JLabel lblNewLabel_4 = new JLabel("Relationships");
		relationship_info_header_panel.add(lblNewLabel_4);
		
		JPanel medical_info_header_panel = new JPanel();
		page_header_cards.add(medical_info_header_panel, "name_617908858526600");
		
		JLabel lblNewLabel_6 = new JLabel("Medical Information");
		medical_info_header_panel.add(lblNewLabel_6);
		GridBagConstraints gbc_save_button = new GridBagConstraints();
		gbc_save_button.gridx = 1;
		gbc_save_button.gridy = 1;
		top_navigation_panel.add(save_button, gbc_save_button);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(0, 0, 0));
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 1;
		frmPersonalInformationEntry.getContentPane().add(separator, gbc_separator);
		
		JPanel entry_cards = new JPanel();
		entry_cards.setBorder(new EmptyBorder(0, 0, 0, 0));
		GridBagConstraints gbc_entry_cards = new GridBagConstraints();
		gbc_entry_cards.insets = new Insets(0, 0, 5, 0);
		gbc_entry_cards.fill = GridBagConstraints.VERTICAL;
		gbc_entry_cards.gridx = 0;
		gbc_entry_cards.gridy = 2;
		frmPersonalInformationEntry.getContentPane().add(entry_cards, gbc_entry_cards);
		entry_cards.setLayout(new CardLayout(0, 0));
		
		JPanel basic_info_panel = new JPanel();
		entry_cards.add(basic_info_panel, "name_371299842951200");
		GridBagLayout gbl_basic_info_panel = new GridBagLayout();
		gbl_basic_info_panel.columnWidths = new int[]{138, 207, 0};
		gbl_basic_info_panel.rowHeights = new int[]{0, 39, 0, 0, 0, 0};
		gbl_basic_info_panel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_basic_info_panel.rowWeights = new double[]{0.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		basic_info_panel.setLayout(gbl_basic_info_panel);
		
		JLabel name_label = new JLabel("Name:");
		GridBagConstraints gbc_name_label = new GridBagConstraints();
		gbc_name_label.fill = GridBagConstraints.HORIZONTAL;
		gbc_name_label.insets = new Insets(0, 0, 5, 5);
		gbc_name_label.gridx = 0;
		gbc_name_label.gridy = 0;
		basic_info_panel.add(name_label, gbc_name_label);
		
		name_field = new JTextField();
		name_field.setEnabled(false);
		if(person.isPresent()) {
			name_field.setText(person.get().getName());
		}
		GridBagConstraints gbc_name_field = new GridBagConstraints();
		gbc_name_field.fill = GridBagConstraints.HORIZONTAL;
		gbc_name_field.insets = new Insets(0, 0, 5, 0);
		gbc_name_field.gridx = 1;
		gbc_name_field.gridy = 0;
		basic_info_panel.add(name_field, gbc_name_field);
		editable_fields.add(name_field);
		name_field.setColumns(15);
		
		JLabel dob_label = new JLabel("Date of Birth:");
		GridBagConstraints gbc_dob_label = new GridBagConstraints();
		gbc_dob_label.fill = GridBagConstraints.HORIZONTAL;
		gbc_dob_label.insets = new Insets(0, 0, 5, 5);
		gbc_dob_label.gridx = 0;
		gbc_dob_label.gridy = 1;
		basic_info_panel.add(dob_label, gbc_dob_label);
		
		JPanel dob_selector_panel = new JPanel();
		GridBagConstraints gbc_dob_selector_panel = new GridBagConstraints();
		gbc_dob_selector_panel.anchor = GridBagConstraints.WEST;
		gbc_dob_selector_panel.insets = new Insets(0, 0, 5, 0);
		gbc_dob_selector_panel.gridx = 1;
		gbc_dob_selector_panel.gridy = 1;
		basic_info_panel.add(dob_selector_panel, gbc_dob_selector_panel);
		dob_selector_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JSpinner dob_spinner_field = new JSpinner();
		//https://stackoverflow.com/questions/22929237/convert-java-time-localdate-into-java-util-date-type
		Date dob = new Date();
		if(person.isPresent()) {
			LocalDate local_dob = person.get().getBirthDate();
			dob = Date.from(local_dob.atStartOfDay(ZoneId.systemDefault()).toInstant());
		}
		dob_spinner_field.setModel(new SpinnerDateModel(dob, null, null, Calendar.DAY_OF_YEAR));
		DateEditor de_dob_spinner_field = new JSpinner.DateEditor(dob_spinner_field, "MMMM dd, yyyy");
		dob_spinner_field.setEditor(de_dob_spinner_field);
		dob_spinner_field.setEnabled(false);
		
		dob_selector_panel.add(dob_spinner_field);
		editable_fields.add(dob_spinner_field);
		
		JLabel dod_label = new JLabel("Date of Death:");
		GridBagConstraints gbc_dod_label = new GridBagConstraints();
		gbc_dod_label.fill = GridBagConstraints.HORIZONTAL;
		gbc_dod_label.insets = new Insets(0, 0, 5, 5);
		gbc_dod_label.gridx = 0;
		gbc_dod_label.gridy = 2;
		basic_info_panel.add(dod_label, gbc_dod_label);
		
		JPanel dod_selector_panel = new JPanel();
		GridBagConstraints gbc_dod_selector_panel = new GridBagConstraints();
		gbc_dod_selector_panel.anchor = GridBagConstraints.WEST;
		gbc_dod_selector_panel.insets = new Insets(0, 0, 5, 0);
		gbc_dod_selector_panel.gridx = 1;
		gbc_dod_selector_panel.gridy = 2;
		basic_info_panel.add(dod_selector_panel, gbc_dod_selector_panel);
		
		JCheckBox dead_check = new JCheckBox("Deceased");
		dead_check.setEnabled(false);
		dead_check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dead_check.isSelected()) {
					for(Component field: dod_fields) {
						field.setEnabled(true);
					}
				}
				else {
					for(Component field: dod_fields) {
						field.setEnabled(false);
					}
				}
			}
		});
		dod_selector_panel.add(dead_check);
		editable_fields.add(dead_check);
		
		JSpinner dod_spinner_field = new JSpinner();
		Date dod = new Date();
		if(person.isPresent()) {
			LocalDate local_dod = person.get().getDeathDate();
			if(local_dod != null) {
				dod = Date.from(local_dod.atStartOfDay(ZoneId.systemDefault()).toInstant());
			}
		}
		dod_spinner_field.setEnabled(false);
		dod_spinner_field.setModel(new SpinnerDateModel(dod, null, null, Calendar.DAY_OF_YEAR));
		DateEditor de_dod_spinner_field = new JSpinner.DateEditor(dod_spinner_field, "MMMM dd, yyyy");
		dod_spinner_field.setEditor(de_dod_spinner_field);
		dod_selector_panel.add(dod_spinner_field);
		editable_fields.add(dod_spinner_field);
		dod_fields.add(dod_spinner_field);
		
		JLabel bs_label = new JLabel("Birth Sex:");
		GridBagConstraints gbc_bs_label = new GridBagConstraints();
		gbc_bs_label.anchor = GridBagConstraints.WEST;
		gbc_bs_label.insets = new Insets(0, 0, 5, 5);
		gbc_bs_label.gridx = 0;
		gbc_bs_label.gridy = 3;
		basic_info_panel.add(bs_label, gbc_bs_label);
		
		JPanel bs_radio_panel = new JPanel();
		GridBagConstraints gbc_bs_radio_panel = new GridBagConstraints();
		gbc_bs_radio_panel.anchor = GridBagConstraints.WEST;
		gbc_bs_radio_panel.insets = new Insets(0, 0, 5, 0);
		gbc_bs_radio_panel.gridx = 1;
		gbc_bs_radio_panel.gridy = 3;
		basic_info_panel.add(bs_radio_panel, gbc_bs_radio_panel);
		
		JRadioButton m_radio_field = new JRadioButton("Male");
		m_radio_field.setEnabled(false);
		bs_radio_panel.add(m_radio_field);
		editable_fields.add(m_radio_field);
		
		JRadioButton f_radio_field = new JRadioButton("Female");
		f_radio_field.setEnabled(false);
		bs_radio_panel.add(f_radio_field);
		editable_fields.add(f_radio_field);
		
		JRadioButton is_radio_field = new JRadioButton("Intersex");
		is_radio_field.setEnabled(false);
		bs_radio_panel.add(is_radio_field);
		editable_fields.add(is_radio_field);
		
		JRadioButton unk_radio_field = new JRadioButton("Unknown");
		unk_radio_field.setEnabled(false);
		bs_radio_panel.add(unk_radio_field);
		editable_fields.add(unk_radio_field);
		
		JLabel gn_label = new JLabel("Gender Notes:");
		GridBagConstraints gbc_gn_label = new GridBagConstraints();
		gbc_gn_label.anchor = GridBagConstraints.WEST;
		gbc_gn_label.insets = new Insets(0, 0, 0, 5);
		gbc_gn_label.gridx = 0;
		gbc_gn_label.gridy = 4;
		basic_info_panel.add(gn_label, gbc_gn_label);
		
		
		gn_field = new JTextField();
		gn_field.setEnabled(false);
		GridBagConstraints gbc_gn_field = new GridBagConstraints();
		gbc_gn_field.fill = GridBagConstraints.HORIZONTAL;
		gbc_gn_field.gridx = 1;
		gbc_gn_field.gridy = 4;
		basic_info_panel.add(gn_field, gbc_gn_field);
		editable_fields.add(gn_field);
		gn_field.setColumns(10);
		
		JPanel relationships_panel = new JPanel();
		entry_cards.add(relationships_panel, "name_372143316340700");
		GridBagLayout gbl_relationships_panel = new GridBagLayout();
		gbl_relationships_panel.columnWidths = new int[]{550, 0};
		gbl_relationships_panel.rowHeights = new int[]{76, 0, 0};
		gbl_relationships_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_relationships_panel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		relationships_panel.setLayout(gbl_relationships_panel);
		
		JPanel relationship_entry_panel_wrapper = new JPanel();
		GridBagConstraints gbc_relationship_entry_panel_wrapper = new GridBagConstraints();
		gbc_relationship_entry_panel_wrapper.insets = new Insets(0, 0, 5, 0);
		gbc_relationship_entry_panel_wrapper.fill = GridBagConstraints.BOTH;
		gbc_relationship_entry_panel_wrapper.gridx = 0;
		gbc_relationship_entry_panel_wrapper.gridy = 0;
		relationships_panel.add(relationship_entry_panel_wrapper, gbc_relationship_entry_panel_wrapper);
		
		JPanel relationship_entry_panel = new JPanel();
		relationship_entry_panel_wrapper.add(relationship_entry_panel);
		GridBagLayout gbl_relationship_entry_panel = new GridBagLayout();
		gbl_relationship_entry_panel.columnWidths = new int[]{115, 0, 343, 0};
		gbl_relationship_entry_panel.rowHeights = new int[]{49, 0};
		gbl_relationship_entry_panel.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_relationship_entry_panel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		relationship_entry_panel.setLayout(gbl_relationship_entry_panel);
		
		JPanel relationship_entry_fields_panel = new JPanel();
		GridBagConstraints gbc_relationship_entry_fields_panel = new GridBagConstraints();
		gbc_relationship_entry_fields_panel.insets = new Insets(0, 0, 0, 5);
		gbc_relationship_entry_fields_panel.gridx = 0;
		gbc_relationship_entry_fields_panel.gridy = 0;
		relationship_entry_panel.add(relationship_entry_fields_panel, gbc_relationship_entry_fields_panel);
		GridBagLayout gbl_relationship_entry_fields_panel = new GridBagLayout();
		gbl_relationship_entry_fields_panel.columnWidths = new int[]{0, 136, 0};
		gbl_relationship_entry_fields_panel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_relationship_entry_fields_panel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_relationship_entry_fields_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		relationship_entry_fields_panel.setLayout(gbl_relationship_entry_fields_panel);
		
		JLabel rel_fn_label = new JLabel("First Name:");
		GridBagConstraints gbc_rel_fn_label = new GridBagConstraints();
		gbc_rel_fn_label.insets = new Insets(0, 0, 5, 5);
		gbc_rel_fn_label.gridx = 0;
		gbc_rel_fn_label.gridy = 0;
		relationship_entry_fields_panel.add(rel_fn_label, gbc_rel_fn_label);
		
		rel_fn_field = new JTextField();
		rel_fn_field.setEnabled(false);
		GridBagConstraints gbc_rel_fn_field = new GridBagConstraints();
		gbc_rel_fn_field.insets = new Insets(0, 0, 5, 0);
		gbc_rel_fn_field.gridx = 1;
		gbc_rel_fn_field.gridy = 0;
		relationship_entry_fields_panel.add(rel_fn_field, gbc_rel_fn_field);
		editable_fields.add(rel_fn_field);
		rel_fn_field.setColumns(10);
		
		JLabel rel_ln_label = new JLabel("Last Name:");
		GridBagConstraints gbc_rel_ln_label = new GridBagConstraints();
		gbc_rel_ln_label.insets = new Insets(0, 0, 5, 5);
		gbc_rel_ln_label.gridx = 0;
		gbc_rel_ln_label.gridy = 1;
		relationship_entry_fields_panel.add(rel_ln_label, gbc_rel_ln_label);
		
		rel_ln_field = new JTextField();
		rel_ln_field.setEnabled(false);
		GridBagConstraints gbc_rel_ln_field = new GridBagConstraints();
		gbc_rel_ln_field.insets = new Insets(0, 0, 5, 0);
		gbc_rel_ln_field.gridx = 1;
		gbc_rel_ln_field.gridy = 1;
		relationship_entry_fields_panel.add(rel_ln_field, gbc_rel_ln_field);
		editable_fields.add(rel_ln_field);
		rel_ln_field.setColumns(10);
		
		JLabel lblRelationshipType = new JLabel("Relationship:");
		GridBagConstraints gbc_lblRelationshipType = new GridBagConstraints();
		gbc_lblRelationshipType.anchor = GridBagConstraints.EAST;
		gbc_lblRelationshipType.insets = new Insets(0, 0, 5, 5);
		gbc_lblRelationshipType.gridx = 0;
		gbc_lblRelationshipType.gridy = 2;
		relationship_entry_fields_panel.add(lblRelationshipType, gbc_lblRelationshipType);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setEnabled(false);
		comboBox.setModel(new DefaultComboBoxModel(RelationshipType.values()));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 2;
		relationship_entry_fields_panel.add(comboBox, gbc_comboBox);
		editable_fields.add(comboBox);
		
		JLabel rel_id_label = new JLabel("ID:");
		GridBagConstraints gbc_rel_id_label = new GridBagConstraints();
		gbc_rel_id_label.insets = new Insets(0, 0, 0, 5);
		gbc_rel_id_label.gridx = 0;
		gbc_rel_id_label.gridy = 3;
		relationship_entry_fields_panel.add(rel_id_label, gbc_rel_id_label);
		
		rel_id_field = new JTextField();
		rel_id_field.setEnabled(false);
		GridBagConstraints gbc_rel_id_field = new GridBagConstraints();
		gbc_rel_id_field.gridx = 1;
		gbc_rel_id_field.gridy = 3;
		relationship_entry_fields_panel.add(rel_id_field, gbc_rel_id_field);
		editable_fields.add(rel_id_field);
		rel_id_field.setColumns(10);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.fill = GridBagConstraints.BOTH;
		gbc_separator_1.insets = new Insets(0, 0, 0, 5);
		gbc_separator_1.gridx = 1;
		gbc_separator_1.gridy = 0;
		relationship_entry_panel.add(separator_1, gbc_separator_1);
		
		JPanel relationship_action_panel = new JPanel();
		GridBagConstraints gbc_relationship_action_panel = new GridBagConstraints();
		gbc_relationship_action_panel.fill = GridBagConstraints.BOTH;
		gbc_relationship_action_panel.gridx = 2;
		gbc_relationship_action_panel.gridy = 0;
		relationship_entry_panel.add(relationship_action_panel, gbc_relationship_action_panel);
		GridBagLayout gbl_relationship_action_panel = new GridBagLayout();
		gbl_relationship_action_panel.columnWidths = new int[]{193, 0};
		gbl_relationship_action_panel.rowHeights = new int[]{0, 0};
		gbl_relationship_action_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_relationship_action_panel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		relationship_action_panel.setLayout(gbl_relationship_action_panel);
		
		JPanel addrem_relationship_panel = new JPanel();
		GridBagConstraints gbc_addrem_relationship_panel = new GridBagConstraints();
		gbc_addrem_relationship_panel.gridx = 0;
		gbc_addrem_relationship_panel.gridy = 0;
		relationship_action_panel.add(addrem_relationship_panel, gbc_addrem_relationship_panel);
		GridBagLayout gbl_addrem_relationship_panel = new GridBagLayout();
		gbl_addrem_relationship_panel.columnWidths = new int[]{89, 129, 0, 0};
		gbl_addrem_relationship_panel.rowHeights = new int[]{0, 0, 0};
		gbl_addrem_relationship_panel.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_addrem_relationship_panel.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		addrem_relationship_panel.setLayout(gbl_addrem_relationship_panel);
		
		JRadioButton add_rel_radio = new JRadioButton("Add");
		add_rel_radio.setEnabled(false);
		add_rel_radio.setSelected(true);
		GridBagConstraints gbc_add_rel_radio = new GridBagConstraints();
		gbc_add_rel_radio.insets = new Insets(0, 0, 5, 5);
		gbc_add_rel_radio.gridx = 0;
		gbc_add_rel_radio.gridy = 0;
		addrem_relationship_panel.add(add_rel_radio, gbc_add_rel_radio);
		editable_fields.add(add_rel_radio);
		
		JRadioButton rem_rel_radio = new JRadioButton("Remove");
		rem_rel_radio.setEnabled(false);
		GridBagConstraints gbc_rem_rel_radio = new GridBagConstraints();
		gbc_rem_rel_radio.insets = new Insets(0, 0, 5, 5);
		gbc_rem_rel_radio.gridx = 1;
		gbc_rem_rel_radio.gridy = 0;
		addrem_relationship_panel.add(rem_rel_radio, gbc_rem_rel_radio);
		editable_fields.add(rem_rel_radio);
		
		JButton edit_rel_button = new JButton("Edit Relationship");
		edit_rel_button.setEnabled(false);
		edit_rel_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_edit_rel_button = new GridBagConstraints();
		gbc_edit_rel_button.insets = new Insets(0, 0, 0, 5);
		gbc_edit_rel_button.gridx = 0;
		gbc_edit_rel_button.gridy = 1;
		addrem_relationship_panel.add(edit_rel_button, gbc_edit_rel_button);
		editable_fields.add(edit_rel_button);
		
		JButton id_search_button = new JButton("Search for ID");
		id_search_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IDSearchWindow id_search_window = new IDSearchWindow();
				id_search_window.frmIdSearch.setVisible(true);
			}
		});
		GridBagConstraints gbc_id_search_button = new GridBagConstraints();
		gbc_id_search_button.insets = new Insets(0, 0, 0, 5);
		gbc_id_search_button.gridx = 1;
		gbc_id_search_button.gridy = 1;
		addrem_relationship_panel.add(id_search_button, gbc_id_search_button);
		
		current_relationships_table = new JTable();
		current_relationships_table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"First Name", "Last Name", "Relationship"
			}
		));
		GridBagConstraints gbc_current_relationships_table = new GridBagConstraints();
		gbc_current_relationships_table.fill = GridBagConstraints.BOTH;
		gbc_current_relationships_table.gridx = 0;
		gbc_current_relationships_table.gridy = 1;
		relationships_panel.add(current_relationships_table, gbc_current_relationships_table);
		
		JPanel medical_info_entry_panel = new JPanel();
		entry_cards.add(medical_info_entry_panel, "name_617827334194100");
		GridBagLayout gbl_medical_info_entry_panel = new GridBagLayout();
		gbl_medical_info_entry_panel.columnWidths = new int[]{107, 0};
		gbl_medical_info_entry_panel.rowHeights = new int[]{20, 0};
		gbl_medical_info_entry_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_medical_info_entry_panel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		medical_info_entry_panel.setLayout(gbl_medical_info_entry_panel);
		
		JEditorPane editorPane = new JEditorPane();
		GridBagConstraints gbc_editorPane = new GridBagConstraints();
		gbc_editorPane.fill = GridBagConstraints.BOTH;
		gbc_editorPane.gridx = 0;
		gbc_editorPane.gridy = 0;
		medical_info_entry_panel.add(editorPane, gbc_editorPane);
		
		JPanel bottom_navigation_panel = new JPanel();
		bottom_navigation_panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		GridBagConstraints gbc_bottom_navigation_panel = new GridBagConstraints();
		gbc_bottom_navigation_panel.weightx = 1.0;
		gbc_bottom_navigation_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_bottom_navigation_panel.gridx = 0;
		gbc_bottom_navigation_panel.gridy = 3;
		frmPersonalInformationEntry.getContentPane().add(bottom_navigation_panel, gbc_bottom_navigation_panel);
		GridBagLayout gbl_bottom_navigation_panel = new GridBagLayout();
		gbl_bottom_navigation_panel.columnWidths = new int[]{13, 19, 3, 0};
		gbl_bottom_navigation_panel.rowHeights = new int[]{37, 0};
		gbl_bottom_navigation_panel.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_bottom_navigation_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		bottom_navigation_panel.setLayout(gbl_bottom_navigation_panel);
		
		JProgressBar progress_bar = new JProgressBar();
		
		GridBagConstraints gbc_progress_bar = new GridBagConstraints();
		gbc_progress_bar.fill = GridBagConstraints.BOTH;
		gbc_progress_bar.weightx = 1.0;
		gbc_progress_bar.insets = new Insets(0, 0, 0, 5);
		gbc_progress_bar.gridx = 1;
		gbc_progress_bar.gridy = 0;
		bottom_navigation_panel.add(progress_bar, gbc_progress_bar);
		
		BasicArrowButton back_button = new BasicArrowButton(BasicArrowButton.WEST);
		back_button.setEnabled(false);
		back_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)(entry_cards.getLayout());
				CardLayout cl2 = (CardLayout)(page_header_cards.getLayout());
				if(current_card > 1) {
					cl.previous(entry_cards);
					cl2.previous(page_header_cards);
					current_card--;
				}
				int progress = Math.round((current_card-1) * ((float) 100/entry_cards.getComponentCount()));
				progress_bar.setValue(progress);
			}
		});
		GridBagConstraints gbc_back_button = new GridBagConstraints();
		gbc_back_button.fill = GridBagConstraints.BOTH;
		gbc_back_button.weightx = 1.0;
		gbc_back_button.insets = new Insets(0, 0, 0, 5);
		gbc_back_button.gridx = 0;
		gbc_back_button.gridy = 0;
		bottom_navigation_panel.add(back_button, gbc_back_button);
		
		BasicArrowButton forward_button = new BasicArrowButton(BasicArrowButton.EAST);
		GridBagConstraints gbc_forward_button = new GridBagConstraints();
		gbc_forward_button.fill = GridBagConstraints.BOTH;
		gbc_forward_button.weightx = 1.0;
		gbc_forward_button.gridx = 2;
		gbc_forward_button.gridy = 0;
		bottom_navigation_panel.add(forward_button, gbc_forward_button);		

		forward_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)(entry_cards.getLayout());
				CardLayout cl2 = (CardLayout)(page_header_cards.getLayout());
				if(current_card<entry_cards.getComponentCount()) {
					cl.next(entry_cards);
					cl2.next(page_header_cards);
					current_card++;
				}
				int progress = Math.round((current_card-1) * ((float) 100/entry_cards.getComponentCount()));
				progress_bar.setValue(progress);
				
			}
		});
		progress_bar.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				JProgressBar source = (JProgressBar) event.getSource();
				if(source.getValue() < source.getMaximum() && source.getValue() > 0) {
					back_button.setEnabled(true);
					forward_button.setEnabled(true);
				}
				if(source.getValue() == 0) {
					back_button.setEnabled(false);
				}
				if(source.getValue() >= (source.getMaximum()-(float) 100/entry_cards.getComponentCount())) {
					forward_button.setEnabled(false);
				}
			}
		});

	}
}
