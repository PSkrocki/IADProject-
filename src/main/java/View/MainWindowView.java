package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton; 
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

import Tables.Data;

public class MainWindowView extends JFrame {

	final Data data =new Data();
	private JPanel contentPane;
	private JTable inputTable;
	private JScrollPane inputJScroll;
	private JTextField InputText;
	private JRadioButton rdbtnWczytajWejsciaZ;
	private JRadioButton rdbtnBezBiasu;
	private JTextField biasValue;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		try{
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		}
		catch(Exception e){
			e.printStackTrace();
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindowView frame = new MainWindowView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public MainWindowView() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);


		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);


		//Wejscia//

		//-tabela z wejsciami
		InputListModel inputsList = new InputListModel(data.getInputList());
		contentPane.setLayout(null);
		inputTable=new JTable(inputsList);
		inputTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


		//-jscroll z wejsciami
		inputJScroll=new JScrollPane(inputTable);
		inputJScroll.setBounds(282, 6, 178, 272);
		inputJScroll.setPreferredSize(new Dimension(120,90));		
		getContentPane().add(inputJScroll);

		final JButton btnWczytajWejscie = new JButton("Wczytaj wejscie");
		btnWczytajWejscie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (openFile()) {
					inputTable.setModel(new InputListModel(data.getInputList()));
					inputTable.repaint();
				}
			}
		});
		btnWczytajWejscie.setBounds(148, 143, 122, 28);
		contentPane.add(btnWczytajWejscie);

		final JButton btnDodajWejscie = new JButton("Dodaj wejście");

		btnDodajWejscie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
				data.addInput(Double.parseDouble(InputText.getText()));
				}
				catch(NumberFormatException exception)
				{
					System.out.println("BŁĄD PRZY OTWIERANIU PLIKU!");
					JOptionPane.showMessageDialog(contentPane, "Wpisz liczbę!",
							"złe dane", JOptionPane.ERROR_MESSAGE);
					return;
				}
				inputTable.setModel(new InputListModel(data.getInputList()));
				inputTable.repaint();
			}

			private JLabel dateText() {
				// TODO Auto-generated method stub
				return null;
			}
		});
		btnDodajWejscie.setBounds(148, 66, 122, 28);
		contentPane.add(btnDodajWejscie);

		InputText = new JTextField();
		InputText.setBounds(208, 32, 62, 28);
		contentPane.add(InputText);
		InputText.setColumns(10);

		JLabel lblWejscie = new JLabel("Wejście");
		lblWejscie.setBounds(148, 38, 55, 16);
		contentPane.add(lblWejscie);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("Wpisz wejscia ręcznie");
		rdbtnNewRadioButton.setSelected(true);
		btnDodajWejscie.setEnabled(true);	
		btnWczytajWejscie.setEnabled(false);
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				btnDodajWejscie.setEnabled(true);
				btnWczytajWejscie.setEnabled(false);
			}
		});
		rdbtnNewRadioButton.setBounds(6, 8, 158, 18);
		contentPane.add(rdbtnNewRadioButton);

		rdbtnWczytajWejsciaZ = new JRadioButton("Wczytaj wejscia z pliku");
		rdbtnWczytajWejsciaZ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDodajWejscie.setEnabled(false);	
				btnWczytajWejscie.setEnabled(true);
			}
		});
		rdbtnWczytajWejsciaZ.setBounds(6, 112, 158, 18);
		contentPane.add(rdbtnWczytajWejsciaZ);

		ButtonGroup radioButtonsGroup = new ButtonGroup();
		radioButtonsGroup.add(rdbtnNewRadioButton);
		radioButtonsGroup.add(rdbtnWczytajWejsciaZ);
		
		JRadioButton rdbtnZBiasem = new JRadioButton("Z Biasem");
		rdbtnZBiasem.setSelected(true);
		rdbtnZBiasem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				biasValue.setEnabled(true);
			}
		});
		rdbtnZBiasem.setEnabled(true);
		rdbtnZBiasem.setBounds(6, 193, 115, 18);
		contentPane.add(rdbtnZBiasem);
		
		rdbtnBezBiasu = new JRadioButton("Bez Biasu");
		rdbtnBezBiasu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				biasValue.setEnabled(false);
			}
		});
		
		rdbtnBezBiasu.setBounds(148, 193, 115, 18);
		contentPane.add(rdbtnBezBiasu);
		
		ButtonGroup radioButtonsGroup2 = new ButtonGroup();
		radioButtonsGroup2.add(rdbtnZBiasem);
		radioButtonsGroup2.add(rdbtnBezBiasu);
		
		biasValue = new JTextField();
		biasValue.setText("1");
		biasValue.setEditable(false);
		biasValue.setColumns(10);
		biasValue.setBounds(36, 223, 25, 28);
		contentPane.add(biasValue);
		
		JLabel lblBias = new JLabel("Bias:");
		lblBias.setBounds(6, 229, 55, 16);
		contentPane.add(lblBias);

	}

	private boolean openFile() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fileChooser.showDialog(this, "Wybierz plik");
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			System.out.println("Selected file: " + selectedFile.getAbsolutePath());
			String filePath = String.valueOf(fileChooser.getSelectedFile());
			return readFile(filePath);
		}
		return false;
	}

	private boolean readFile(String filePath) {
		FileReader fr = null;
		String line = "";

		// OTWIERANIE PLIKU:
		try {
			fr = new FileReader(filePath);
		} catch (FileNotFoundException e) {
			System.out.println("BŁĄD PRZY OTWIERANIU PLIKU!");
			JOptionPane.showMessageDialog(this, "Wczytanie pliku nie powiodło się! Wybierz plik txt",
					"Wczytanie pliku nie powiodło się", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		BufferedReader bfr = new BufferedReader(fr);
		// ODCZYT KOLEJNYCH LINII Z PLIKU:
		try {
			while((line = bfr.readLine()) != null){
				data.addInput(Double.parseDouble(line));
			}
		} catch (IOException e) {
			System.out.println("BŁĄD ODCZYTU Z PLIKU!");
			System.exit(2);
		}

		// ZAMYKANIE PLIKU
		try {
			fr.close();
			return true;
		} catch (IOException e) {
			System.out.println("BŁĄD PRZY ZAMYKANIU PLIKU!");
			System.exit(3);
		}
		return false;
	}
}


