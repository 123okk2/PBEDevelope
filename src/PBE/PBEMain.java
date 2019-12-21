package PBE;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.DropMode;
import javax.swing.JTextArea;

public class PBEMain extends JFrame {

	private JPanel contentPane;
	private JTextField inputField;
	private JButton ency;
	private JButton decy;
	private JTextField keyField;
	private JTextArea outputField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PBEMain frame = new PBEMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PBEMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 273, 321);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getInputField());
		contentPane.add(getEncy());
		contentPane.add(getDecy());
		contentPane.add(getKeyField());
		contentPane.add(getOutputField());
		
		ency.addActionListener(new ActionListener() {
			//암호화
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String password = inputField.getText();
				char[] passArr = password.toCharArray();
				String text = keyField.getText();
				PBEClass pbe = new PBEClass(passArr, text);
				outputField.setText(pbe.EnCrypt());
			}
			
		});
		
		decy.addActionListener(new ActionListener() {
			//복호화
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String password = inputField.getText();
				char[] passArr = password.toCharArray();
				String text = keyField.getText();
				PBEClass pbe = new PBEClass(passArr, text);
				outputField.setText(pbe.DeCript());
			}
			
		});
	}
	private JTextField getInputField() {
		if (inputField == null) {
			inputField = new JTextField();
			inputField.setToolTipText("\uD0A4 \uC785\uB825");
			inputField.setBounds(12, 10, 124, 38);
			inputField.setColumns(10);
		}
		return inputField;
	}
	private JButton getEncy() {
		if (ency == null) {
			ency = new JButton("\uC554\uD638\uD654");
			ency.setBounds(148, 9, 97, 38);
		}
		return ency;
	}
	private JButton getDecy() {
		if (decy == null) {
			decy = new JButton("\uBCF5\uD638\uD654");
			decy.setBounds(148, 51, 97, 38);
		}
		return decy;
	}
	private JTextField getKeyField() {
		if (keyField == null) {
			keyField = new JTextField();
			keyField.setToolTipText("\uBB38\uC7A5 \uC785\uB825");
			keyField.setColumns(10);
			keyField.setBounds(12, 51, 124, 38);
		}
		return keyField;
	}
	private JTextArea getOutputField() {
		if (outputField == null) {
			outputField = new JTextArea();
			outputField.setEditable(false);
			outputField.setText("\uACB0\uACFC\uAC12\uC774 \uCD9C\uB825\uB429\uB2C8\uB2E4");
			outputField.setLineWrap(true);
			outputField.setBounds(12, 99, 233, 173);
		}
		return outputField;
	}
}
