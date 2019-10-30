package kotc;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.Toolkit;
import java.util.Arrays;
import java.util.Base64;
import java.awt.List;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;

public class modifica extends JFrame {

	int elem_counter = 0;
	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtCognome;
	private JTextField txtData;
	private JTextField txtLuogo;
	private JTextField txtResidenza;
	private JTextField txtMail;
	private JTextField txtCellulare;
	private JTextField txtSquadra;
	private JTextField txid;
	private JButton btnInserisci;
	private JLabel lblV;
	private JSeparator separator;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					modifica frame = new modifica();
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
	public modifica() {
		setResizable(false);
		setTitle("Modifica");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Lenovo\\Desktop\\icona programma.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 826, 483);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txid = new JTextField();
		txid.setEditable(false);
		txid.setBounds(189, 182, 96, 19);
		contentPane.add(txid);
		txid.setColumns(10);
		
		List list = new List();
		list.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String string = list.getSelectedItem();
				String [] split = string.split("\\, ");
				
				txtNome.setText(split[1].substring(1));
				txtCognome.setText(split[2].substring(1));
				txtData.setText(split[3].substring(1));
				txtLuogo.setText(split[4].substring(1));
				txtResidenza.setText(split[6].substring(1));
				txtMail.setText(split[7].substring(1));
				txtCellulare.setText(split[8].substring(1));
				txtSquadra.setText(split[9].substring(1));
				txid.setText(split[0]);
				
				
			}
		});
		
		String url = "https://tournament-manage.herokuapp.com/tournament/players";
		try {
			list.clear();
		
			try
			{
				String result = inserimento.sendGet(url);
				JSONArray mainobj = new JSONArray(result);

				for (var i = 0; i < mainobj.length(); i++) {
					JSONObject obj = mainobj.getJSONObject(i);
					String stringa = i + ",  " + obj.get("Nome").toString() + ",  " + obj.get("Cognome").toString()
							+ ",  " + obj.get("Data di nascita").toString() + ",  "
							+ obj.get("Luogo di nascita").toString() + ",  " + obj.get("Codice Fiscale").toString()
							+ ",  " + obj.get("Luogo residenza").toString() + ",  " + obj.get("Mail").toString()
							+ ",  " + obj.get("Cellulare").toString() + ",  " + obj.get("Squadra").toString()
							+ '\n';
					list.add(stringa);
				}
			}
			catch(Exception e2) {
				list.add("Database vuoto");
			}

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			list.add(e1.toString());
		}
		list.setBounds(10, 10, 792, 124);
		contentPane.add(list);
		
		JCheckBox chckbxNome = new JCheckBox("Nome");
		chckbxNome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(txtNome.isEditable()==false)
					txtNome.setEditable(true);
				else
					txtNome.setEditable(false);
			}
		});
		chckbxNome.setBounds(6, 210, 177, 21);
		contentPane.add(chckbxNome);
		
		JCheckBox chckbxCognome = new JCheckBox("Cognome");
		chckbxCognome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(txtCognome.isEditable()==false)
					txtCognome.setEditable(true);
				else
					txtCognome.setEditable(false);
			}
		});
		chckbxCognome.setBounds(6, 233, 177, 21);
		contentPane.add(chckbxCognome);
		
		JCheckBox chckbxDataNascita = new JCheckBox("Data Nascita");
		chckbxDataNascita.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(txtData.isEditable()==false)
					txtData.setEditable(true);
				else
					txtData.setEditable(false);
			}
		});
		chckbxDataNascita.setBounds(6, 256, 177, 21);
		contentPane.add(chckbxDataNascita);
		
		JCheckBox chckbxLuogoNascita = new JCheckBox("Luogo nascita");
		chckbxLuogoNascita.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(txtLuogo.isEditable()==false)
					txtLuogo.setEditable(true);
				else
					txtLuogo.setEditable(false);
			}
		});
		chckbxLuogoNascita.setBounds(6, 279, 177, 21);
		contentPane.add(chckbxLuogoNascita);
		
		JCheckBox chckbxLuogoResidenza = new JCheckBox("Luogo Residenza");
		chckbxLuogoResidenza.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(txtResidenza.isEditable()==false)
					txtResidenza.setEditable(true);
				else
					txtResidenza.setEditable(false);
			}
		});
		chckbxLuogoResidenza.setBounds(6, 302, 177, 21);
		contentPane.add(chckbxLuogoResidenza);
		
		JCheckBox chckbxMail = new JCheckBox("Mail");
		chckbxMail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(txtMail.isEditable()==false)
					txtMail.setEditable(true);
				else
					txtMail.setEditable(false);
			}
		});
		chckbxMail.setBounds(6, 325, 177, 21);
		contentPane.add(chckbxMail);
		
		JCheckBox chckbxCellulare = new JCheckBox("Cellulare");
		chckbxCellulare.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(txtCellulare.isEditable()==false)
					txtCellulare.setEditable(true);
				else
					txtCellulare.setEditable(false);
			}
		});
		chckbxCellulare.setBounds(6, 348, 177, 21);
		contentPane.add(chckbxCellulare);
		
		txtNome = new JTextField();
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 10));
		txtNome.setEditable(false);
		txtNome.setBounds(189, 211, 187, 19);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		txtCognome = new JTextField();
		txtCognome.setFont(new Font("Tahoma", Font.PLAIN, 10));
		txtCognome.setEditable(false);
		txtCognome.setBounds(189, 234, 187, 19);
		contentPane.add(txtCognome);
		txtCognome.setColumns(10);
		
		txtData = new JTextField();
		txtData.setFont(new Font("Tahoma", Font.PLAIN, 10));
		txtData.setEditable(false);
		txtData.setBounds(189, 257, 187, 19);
		contentPane.add(txtData);
		txtData.setColumns(10);
		
		txtLuogo = new JTextField();
		txtLuogo.setFont(new Font("Tahoma", Font.PLAIN, 10));
		txtLuogo.setEditable(false);
		txtLuogo.setBounds(189, 280, 187, 19);
		contentPane.add(txtLuogo);
		txtLuogo.setColumns(10);
		
		txtResidenza = new JTextField();
		txtResidenza.setFont(new Font("Tahoma", Font.PLAIN, 10));
		txtResidenza.setEditable(false);
		txtResidenza.setBounds(189, 303, 187, 19);
		contentPane.add(txtResidenza);
		txtResidenza.setColumns(10);
		
		txtMail = new JTextField();
		txtMail.setFont(new Font("Tahoma", Font.PLAIN, 10));
		txtMail.setEditable(false);
		txtMail.setBounds(189, 326, 187, 19);
		contentPane.add(txtMail);
		txtMail.setColumns(10);
		
		txtCellulare = new JTextField();
		txtCellulare.setFont(new Font("Tahoma", Font.PLAIN, 10));
		txtCellulare.setEditable(false);
		txtCellulare.setBounds(189, 349, 187, 19);
		contentPane.add(txtCellulare);
		txtCellulare.setColumns(10);
		
		JCheckBox chckbxSquadra = new JCheckBox("Squadra");
		chckbxSquadra.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(txtSquadra.isEditable()==false)
					txtSquadra.setEditable(true);
				else
					txtSquadra.setEditable(false);
			}
		});
		chckbxSquadra.setBounds(6, 371, 177, 21);
		contentPane.add(chckbxSquadra);
		
		txtSquadra = new JTextField();
		txtSquadra.setFont(new Font("Tahoma", Font.PLAIN, 10));
		txtSquadra.setEditable(false);
		txtSquadra.setBounds(189, 372, 187, 19);
		contentPane.add(txtSquadra);
		txtSquadra.setColumns(10);
		

		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblId.setBounds(25, 182, 158, 19);
		contentPane.add(lblId);
		
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(443, 280, 359, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JRadioButton rdbtnModificaeliminaPerSquadra = new JRadioButton("Modifica/Elimina per Squadra Iscritto");
		JRadioButton rdbtnModificaeliminaPerCognome = new JRadioButton("Modifica/Elimina per Cognome Iscritto");
		rdbtnModificaeliminaPerSquadra.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rdbtnModificaeliminaPerCognome.setSelected(false);
				textField.setEditable(true);
				textField.setEditable(true);
				txtNome.setText("");
				txtCognome.setText("");
				txtData.setText("");
				txtLuogo.setText("");
				txtResidenza.setText("");
				txtMail.setText("");
				txtCellulare.setText("");
				txtSquadra.setText("");
				txid.setText("");
			}
		});
		rdbtnModificaeliminaPerSquadra.setBounds(443, 233, 359, 21);
		contentPane.add(rdbtnModificaeliminaPerSquadra);
		
		
		rdbtnModificaeliminaPerCognome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rdbtnModificaeliminaPerSquadra.setSelected(false);
				textField.setEditable(true);
				txtNome.setText("");
				txtCognome.setText("");
				txtData.setText("");
				txtLuogo.setText("");
				txtResidenza.setText("");
				txtMail.setText("");
				txtCellulare.setText("");
				txtSquadra.setText("");
				txid.setText("");
			}
		});
		rdbtnModificaeliminaPerCognome.setBounds(443, 210, 359, 21);
		contentPane.add(rdbtnModificaeliminaPerCognome);
		
	
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnModificaeliminaPerSquadra);
		group.add(rdbtnModificaeliminaPerCognome);
		
		
		btnInserisci = new JButton("Inserisci");
		btnInserisci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String result;
				String cf ;	
				String url = "https://tournament-manage.herokuapp.com/tournament/players?name="+txtNome.getText().replace(" ", "%20")+
						 "&surname="+txtCognome.getText().replace(" ", "%20")+"&data="+txtData.getText()+
						 "&luogo="+txtLuogo.getText().replace(" ", "%20")+"&cf="+""+
						 "&residenza="+txtResidenza.getText().replace(" ", "%20")+
						 "&cell="+txtCellulare.getText()+"&mail="+txtMail.getText()+"&teamname="+
						 txtSquadra.getText().replace(" ", "%20")+"&len="+txid.getText();
				
				try {
					int res = inserimento.sendPost(url);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					result = inserimento.sendGet("http://webservices.dotnethell.it/codicefiscale.asmx/CalcolaCodiceFiscale?Nome="+txtNome.getText().replace(" ", "%20")+
							"&Cognome="+txtCognome.getText().replace(" ","%20")+
							 "&ComuneNascita="+txtLuogo.getText().replace(" ", "%20")+
							 "&DataNascita="+txtData.getText()+"&sesso=M");
					 cf = result.substring(101,117);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					cf = "";
				}
				String url1 = "https://tournament-manage.herokuapp.com/tournament/players?name="+txtNome.getText().replace(" ", "%20")+
						 "&surname="+txtCognome.getText().replace(" ", "%20")+"&data="+txtData.getText()+
						 "&luogo="+txtLuogo.getText().replace(" ", "%20")+"&cf="+cf+
						 "&residenza="+txtResidenza.getText().replace(" ", "%20")+
						 "&cell="+txtCellulare.getText()+"&mail="+txtMail.getText()+"&teamname="+
						 txtSquadra.getText().replace(" ", "%20")+"&len="+txid.getText();
				try {
					int res = inserimento.sendPost(url1);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnInserisci.setBounds(189, 413, 85, 21);
		contentPane.add(btnInserisci);
		
		lblV = new JLabel("v 1.0.0");
		lblV.setBounds(766, 433, 36, 13);
		contentPane.add(lblV);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String url = "https://tournament-manage.herokuapp.com/tournament/players";
				try {
					list.clear();
				
					try
					{
						String result = inserimento.sendGet(url);
						JSONArray mainobj = new JSONArray(result);

						for (var i = 0; i < mainobj.length(); i++) {
							JSONObject obj = mainobj.getJSONObject(i);
							String stringa = i + ",  " + obj.get("Nome").toString() + ",  " + obj.get("Cognome").toString()
									+ ",  " + obj.get("Data di nascita").toString() + ",  "
									+ obj.get("Luogo di nascita").toString() + ",  " + obj.get("Codice Fiscale").toString()
									+ ",  " + obj.get("Luogo residenza").toString() + ",  " + obj.get("Mail").toString()
									+ ",  " + obj.get("Cellulare").toString() + ",  " + obj.get("Squadra").toString()
									+ '\n';
							list.add(stringa);
						}
					}
					catch(Exception e2) {
						list.add("Database vuoto");
					}

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					list.add(e1.toString());
				}
			
				
			}
		});
		btnRefresh.setBounds(717, 140, 85, 21);
		contentPane.add(btnRefresh);
		
		separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(421, 156, 36, 278);
		contentPane.add(separator);
		
		JButton btnElimina = new JButton("Elimina");
		btnElimina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String url = "https://tournament-manage.herokuapp.com/tournament/players?id="+txid.getText();
				int res;
				try {
					res = sendDelete(url);
					System.out.println(res);
					list.removeAll();
					list.add("Rimozione avvenuta");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					list.add(e1.toString());
					e1.printStackTrace();
				}
				
			}
		});
		btnElimina.setBounds(284, 413, 85, 21);
		contentPane.add(btnElimina);
		
		JButton btnTrova = new JButton("Trova");
		btnTrova.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnModificaeliminaPerSquadra.isSelected() == true)
				{
					String url = "https://tournament-manage.herokuapp.com/tournament/players/";
				
					list.removeAll();
					try {
						String result = inserimento.sendGet(url);
						JSONArray mainobj = new JSONArray(result);
						for (var i = 0; i < mainobj.length(); i++) {
							JSONObject obj = mainobj.getJSONObject(i);
							if(textField.getText().toUpperCase().equals(obj.get("Squadra").toString()))
							{
								String stringa = i+",  "+obj.get("Nome").toString() + ",  " + obj.get("Cognome").toString() + ",  "
										+ obj.get("Data di nascita").toString() + ",  " + obj.get("Luogo di nascita").toString()
										+ ",  " + obj.get("Codice Fiscale").toString() + ",  "
										+ obj.get("Luogo residenza").toString() + ",  " + obj.get("Mail").toString() + ",  "
										+ obj.get("Cellulare").toString() + ",  " + obj.get("Squadra").toString() + '\n';
								list.add(stringa);
							}
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						list.add("Nessuna corrispondenza trovata");
						list.add(e1.toString());
						
					}
					
				}
				else if (rdbtnModificaeliminaPerCognome.isSelected()==true)
				{
					String url = "https://tournament-manage.herokuapp.com/tournament/players/";
					
					list.removeAll();
					try {
						String result = inserimento.sendGet(url);
						JSONArray mainobj = new JSONArray(result);
						for (var i = 0; i < mainobj.length(); i++) {
							JSONObject obj = mainobj.getJSONObject(i);
							if(textField.getText().toUpperCase().equals(obj.get("Cognome").toString()))
							{
								String stringa = i+",  "+obj.get("Nome").toString() + ",  " + obj.get("Cognome").toString() + ",  "
										+ obj.get("Data di nascita").toString() + ",  " + obj.get("Luogo di nascita").toString()
										+ ",  " + obj.get("Codice Fiscale").toString() + ",  "
										+ obj.get("Luogo residenza").toString() + ",  " + obj.get("Mail").toString() + ",  "
										+ obj.get("Cellulare").toString() + ",  " + obj.get("Squadra").toString() + '\n';
								list.add(stringa);
							}
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						list.add("Nessuna corrispondenza trovata");
						list.add(e1.toString());
					}
				}
					
			}
		});
		btnTrova.setBounds(443, 325, 85, 21);
		contentPane.add(btnTrova);

		

	}
		

		
		

		
		
	
	
	
	private int sendDelete(String url) throws Exception
	{
		HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();

		// optional default is GET
		httpClient.setRequestMethod("DELETE");
		httpClient.addRequestProperty("Content-Type","Application/json");
		String auth = kotc1.getAuth();
		String encodedString = Base64.getEncoder().encodeToString(auth.getBytes());
		httpClient.setRequestProperty("Authorization","BASIC "+encodedString);
		httpClient.setDoOutput(true);
		httpClient.connect();
		int res = httpClient.getResponseCode();
		System.out.println(res);
		return res;
	}
	}

