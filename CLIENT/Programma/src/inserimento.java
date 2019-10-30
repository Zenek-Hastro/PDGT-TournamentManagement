package kotc;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Base64;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.List;
import javax.swing.JTree;
import java.awt.Font;
import javax.swing.JRadioButton;
import java.awt.TextField;
import javax.swing.JTextField;
import javax.swing.DropMode;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import javax.swing.JProgressBar;
import javax.swing.ImageIcon;
import java.awt.Button;

public class inserimento extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtCognome;
	private JTextField txtDataNascitaggmmaaaa;
	private JTextField txtLuogoNascita;
	private JTextField txtLuogoResidenza;
	private JTextField txtMail;
	private JTextField txtCellulare;
	private JTextField txtSquadra;
	private JTextField textField;
	
	int elem_counter=0;
	private JTextField txtID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					inserimento frame = new inserimento();
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
	public inserimento() {
		setResizable(false);
		setTitle("Inserimento");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Lenovo\\Desktop\\icona programma.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 994, 581);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane txtAttenzione = new JTextPane();
		txtAttenzione.setEditable(false);
		txtAttenzione.setVisible(false);
		txtAttenzione.setBackground(SystemColor.info);
		txtAttenzione.setText("Attenzione. Per usufruire di questa funzione \u00E8 necesasrio essere autenticati con i privilegi di sviluppatore.");
		txtAttenzione.setBounds(709, 315, 259, 54);
		contentPane.add(txtAttenzione);
		
		List list_1 = new List();
		list_1.setVisible(false);
		list_1.setBounds(528, 381, 440, 132);
		contentPane.add(list_1);
		
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setVisible(false);
		progressBar.setBounds(528, 519, 396, 14);
		contentPane.add(progressBar);
		
		JTextPane txtpnAttenzioneInserendoUn = new JTextPane();
		txtpnAttenzioneInserendoUn.setVisible(false);
		txtpnAttenzioneInserendoUn.setBackground(SystemColor.info);
		txtpnAttenzioneInserendoUn.setEditable(false);
		txtpnAttenzioneInserendoUn.setText("Attenzione. Inserire un atleta con un ID non seguente a quelli gi\u00E0 presenti nel database, produrr\u00E0 errori");
		txtpnAttenzioneInserendoUn.setBounds(709, 315, 259, 54);
		contentPane.add(txtpnAttenzioneInserendoUn);
		
		JButton btnInserisci = new JButton("Inserisci");
		JRadioButton rdbtnInserimentoPerId = new JRadioButton("Inserimento per ID");
		rdbtnInserimentoPerId.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtID.setEnabled(false);
				btnInserisci.setVisible(true);
				if (kotc1.getAuth().compareTo("admin:kotc2020") != 0)
				{
					txtAttenzione.setVisible(true);
					btnInserisci.setEnabled(false);
					txtID.setEnabled(false);
				}
				else
				{
					txtID.setEditable(true);
					txtID.setEnabled(true);
					txtpnAttenzioneInserendoUn.setEnabled(true);
					txtpnAttenzioneInserendoUn.setVisible(true);
					
					progressBar.setVisible(true);
					list_1.setVisible(true);
					String url = "https://tournament-manage.herokuapp.com/tournament/players";
					try {
						list_1.clear();
						progressBar.setValue(0);
						try
						{
							String result = sendGet(url);
							progressBar.setValue(40);
							JSONArray mainobj = new JSONArray(result);
							progressBar.setValue(50);
		
							for (var i = 0; i < mainobj.length(); i++) {
								JSONObject obj = mainobj.getJSONObject(i);
								String stringa = i + ",  " + obj.get("Nome").toString() + ",  " + obj.get("Cognome").toString()
										+ ",  " + obj.get("Data di nascita").toString() + ",  "
										+ obj.get("Luogo di nascita").toString() + ",  " + obj.get("Codice Fiscale").toString()
										+ ",  " + obj.get("Luogo residenza").toString() + ",  " + obj.get("Mail").toString()
										+ ",  " + obj.get("Cellulare").toString() + ",  " + obj.get("Squadra").toString()
										+ '\n';
								progressBar.setValue(50 + (50 / mainobj.length() + (50 - mainobj.length())));
								list_1.add(stringa);
							}
						}
						catch(Exception e2) {
							list_1.add("Database vuoto");
							progressBar.setValue(100);
						}

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						list_1.add(e1.toString());
					}
				
				
				}
			}
		});
		rdbtnInserimentoPerId.setBounds(528, 257, 171, 24);
		contentPane.add(rdbtnInserimentoPerId);
		
	
		
		JRadioButton rdbtnInserimentoSingolo = new JRadioButton("Inserimento singolo");
		rdbtnInserimentoSingolo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnInserisci.setVisible(true);
				btnInserisci.setEnabled(true);
				txtAttenzione.setVisible(false);
				txtID.setEditable(false);;

		
			}
		});
		rdbtnInserimentoSingolo.setBounds(10, 259, 179, 21);
		contentPane.add(rdbtnInserimentoSingolo);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnInserimentoSingolo);
		group.add(rdbtnInserimentoPerId);
		
		

		List list = new List();
		list.setForeground(Color.BLACK);
		list.setBackground(Color.WHITE);
		list.setBounds(10, 10, 960, 183);
		contentPane.add(list);
		String url = "https://tournament-manage.herokuapp.com/tournament/players/";
		try {
			String result = sendGet(url);
			JSONArray mainobj = new JSONArray(result);
			String[] stringa = new String[mainobj.length()];
			System.out.println(mainobj);
			elem_counter = mainobj.length();
			for (var i = 0; i < mainobj.length(); i++) {
				JSONObject obj = mainobj.getJSONObject(i);
				stringa[i] = obj.get("Squadra").toString() + ",  "
								+obj.get("Nome").toString() + ",  " 
								+ obj.get("Cognome").toString();
			}
			Arrays.parallelSort(stringa);
			for(var k = 0; k< mainobj.length(); k++)
			{
				list.add(stringa[k]);
			}
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			System.out.print(e1);
			list.add("Database vuoto");
			elem_counter = 0; 
		}
		
		

		
		JLabel lblV = new JLabel("v 1.0.0");
		lblV.setBounds(932, 528, 46, 13);
		contentPane.add(lblV);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 13));
		lblNewLabel_2.setBounds(12, 513, 306, 25);
		contentPane.add(lblNewLabel_2);
		
		
		
		JLabel lblNewLabel = new JLabel("Inserimento Atleti");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 209, 411, 25);
		contentPane.add(lblNewLabel);
		
		btnInserisci.setVisible(false);
		btnInserisci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cf = null; 
				boolean esit = isValid(txtMail.getText());
				try {
					try
					{
						String res = sendGet(url);
						JSONArray mainobj = new JSONArray(res);
						elem_counter = mainobj.length();
					}
					catch(Exception e2)
					{
						elem_counter = 0; 
					}
					
					var result = sendGet("http://webservices.dotnethell.it/codicefiscale.asmx/CalcolaCodiceFiscale?Nome="+txtNome.getText().replace(" ", "%20")+
										"&Cognome="+txtCognome.getText().replace(" ","%20")+
										 "&ComuneNascita="+txtLuogoNascita.getText().replace(" ", "%20")+
										 "&DataNascita="+txtDataNascitaggmmaaaa.getText()+"&sesso=M");
					cf = result.substring(101,117);
					textField.setText(cf);		
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						textField.setText(e1.toString());
						return; 
					}
				String url1;
				System.out.print(Integer.toString(elem_counter));
				if (kotc1.getAuth().equals("admin:kotc2020")&& rdbtnInserimentoPerId.isSelected()==true)
					url1 = "https://tournament-manage.herokuapp.com/tournament/players?name="+txtNome.getText().replace(" ", "%20")+
					 "&surname="+txtCognome.getText().replace(" ", "%20")+"&data="+txtDataNascitaggmmaaaa.getText()+
					 "&luogo="+txtLuogoNascita.getText().replace(" ", "%20")+"&cf="+textField.getText()+
					 "&residenza="+txtLuogoResidenza.getText().replace(" ", "%20")+
					 "&cell="+txtCellulare.getText()+"&mail="+txtMail.getText()+"&teamname="+
					 txtSquadra.getText().replace(" ", "%20")+"&len="+txtID.getText();
				else
				url1= "https://tournament-manage.herokuapp.com/tournament/players?name="+txtNome.getText().replace(" ", "%20")+
							 "&surname="+txtCognome.getText().replace(" ", "%20")+"&data="+txtDataNascitaggmmaaaa.getText()+
							 "&luogo="+txtLuogoNascita.getText().replace(" ", "%20")+"&cf="+textField.getText()+
							 "&residenza="+txtLuogoResidenza.getText().replace(" ", "%20")+
							 "&cell="+txtCellulare.getText()+"&mail="+txtMail.getText()+"&teamname="+
							 txtSquadra.getText().replace(" ", "%20")+"&len="+Integer.toString(elem_counter);
				
				int response;
				try {
					response = sendPost(url1);
					if (response == 403)
						textField.setText("Atleta con questo codice fiscale già inserito. Riprovare");
					else if (response == 406)
						textField.setText("Formato data non supportato. Inserire GG/MM/AAAA");
					else
						textField.setText(cf+" - Inserimento avvenuto");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
			}
		});
		btnInserisci.setBounds(349, 487, 99, 26);
		contentPane.add(btnInserisci);
		
		
		
		
		txtID = new JTextField();
		txtID.setEnabled(false);
		txtID.setBounds(528, 349, 150, 20);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(528, 330, 150, 16);
		contentPane.add(lblId);
		

		
		
		txtNome = new JTextField();
		
		txtNome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtNome.setText("");
			}
		});
		txtNome.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		txtNome.setBounds(10, 349, 150, 19);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		txtCognome = new JTextField();
		txtCognome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtCognome.setText("");
			}
		});
		txtCognome.setColumns(10);
		txtCognome.setBounds(168, 349, 150, 19);
		contentPane.add(txtCognome);
		
		txtDataNascitaggmmaaaa = new JTextField();
		txtDataNascitaggmmaaaa.setFont(new Font("Dialog", Font.PLAIN, 10));
		txtDataNascitaggmmaaaa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtDataNascitaggmmaaaa.setText("");
			}
		});
		txtDataNascitaggmmaaaa.setColumns(10);
		txtDataNascitaggmmaaaa.setBounds(329, 349, 150, 19);
		contentPane.add(txtDataNascitaggmmaaaa);
		
		txtLuogoNascita = new JTextField();
		txtLuogoNascita.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtLuogoNascita.setText("");
			}
		});
		txtLuogoNascita.setColumns(10);
		txtLuogoNascita.setBounds(10, 395, 150, 19);
		contentPane.add(txtLuogoNascita);
		
		txtLuogoResidenza = new JTextField();
		txtLuogoResidenza.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtLuogoResidenza.setText("");
			}
		});
		txtLuogoResidenza.setColumns(10);
		txtLuogoResidenza.setBounds(168, 395, 150, 19);
		contentPane.add(txtLuogoResidenza);
		
		txtMail = new JTextField();
		txtMail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtMail.setText("");
			}
		});
		txtMail.setColumns(10);
		txtMail.setBounds(10, 445, 150, 19);
		contentPane.add(txtMail);
		
		txtCellulare = new JTextField();
		txtCellulare.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtCellulare.setText("");
			}
		});
		txtCellulare.setColumns(10);
		txtCellulare.setBounds(329, 395, 150, 19);
		contentPane.add(txtCellulare);
		
		txtSquadra = new JTextField();
		txtSquadra.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtSquadra.setText("");
			}
		});
		txtSquadra.setColumns(10);
		txtSquadra.setBounds(168, 445, 311, 19);
		contentPane.add(txtSquadra);	
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 330, 150, 16);
		contentPane.add(lblNome);
		
		JLabel lblCognome = new JLabel("Cognome");
		lblCognome.setBounds(168, 330, 150, 16);
		contentPane.add(lblCognome);
		
		JLabel lblDataDiNascita = new JLabel("Data di Nascita (GG/MM/AAAA)");
		lblDataDiNascita.setBounds(313, 330, 196, 16);
		contentPane.add(lblDataDiNascita);
		
		JLabel lblLuogoNascita = new JLabel("Luogo Nascita");
		lblLuogoNascita.setBounds(10, 380, 150, 16);
		contentPane.add(lblLuogoNascita);
		
		JLabel lblLuogoResidenza = new JLabel("Luogo Residenza");
		lblLuogoResidenza.setBounds(168, 380, 150, 16);
		contentPane.add(lblLuogoResidenza);
		
		JLabel lblCellulare = new JLabel("Cellulare");
		lblCellulare.setBounds(329, 380, 150, 16);
		contentPane.add(lblCellulare);
		
		JLabel lblMail = new JLabel("Mail");
		lblMail.setBounds(10, 426, 150, 16);
		contentPane.add(lblMail);
		
		JLabel lblSquadra = new JLabel("Squadra");
		lblSquadra.setBounds(168, 426, 311, 16);
		contentPane.add(lblSquadra);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(10, 490, 308, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Codice Fiscale");
		lblNewLabel_1.setBounds(10, 472, 150, 16);
		contentPane.add(lblNewLabel_1);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(507, 207, 13, 327);
		contentPane.add(separator);
		
		JLabel lblInserimentoPerId = new JLabel("Inserimento atleti per ID");
		lblInserimentoPerId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblInserimentoPerId.setBounds(525, 209, 432, 25);
		contentPane.add(lblInserimentoPerId);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list.removeAll();
				String url = "https://tournament-manage.herokuapp.com/tournament/players/";
				try {
					String result = sendGet(url);
					JSONArray mainobj = new JSONArray(result);
					String[] stringa = new String[mainobj.length()];
					elem_counter = mainobj.length();
					for (var i = 0; i < mainobj.length(); i++) {
						JSONObject obj = mainobj.getJSONObject(i);
						stringa[i] = obj.get("Squadra").toString() + ",  "
										+obj.get("Nome").toString() + ",  " 
										+ obj.get("Cognome").toString();
					}
					Arrays.parallelSort(stringa);
					for(var k = 0; k< mainobj.length(); k++)
					{
						list.add(stringa[k]);
					}
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.print(e1);
					list.add("Database vuoto");
					elem_counter = 0; 
				}
				
				try {
					list_1.clear();
					progressBar.setValue(0);
					try
					{
						String result = sendGet(url);
						progressBar.setValue(40);
						JSONArray mainobj = new JSONArray(result);
						progressBar.setValue(50);
	
						for (var i = 0; i < mainobj.length(); i++) {
							JSONObject obj = mainobj.getJSONObject(i);
							String stringa = i + ",  " + obj.get("Nome").toString() + ",  " + obj.get("Cognome").toString()
									+ ",  " + obj.get("Data di nascita").toString() + ",  "
									+ obj.get("Luogo di nascita").toString() + ",  " + obj.get("Codice Fiscale").toString()
									+ ",  " + obj.get("Luogo residenza").toString() + ",  " + obj.get("Mail").toString()
									+ ",  " + obj.get("Cellulare").toString() + ",  " + obj.get("Squadra").toString()
									+ '\n';
							progressBar.setValue(50 + (50 / mainobj.length() + (50 - mainobj.length())));
							list_1.add(stringa);
						}
					}
					catch(Exception e2) {
						list_1.add("Database vuoto");
						progressBar.setValue(100);
					}

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					list_1.add(e1.toString());
				}
			
			
				
			
			
			}
			
		});
		btnRefresh.setBounds(883, 196, 85, 26);
		contentPane.add(btnRefresh);
		
	
	}
	
	
	
	public static String sendGet(String url) throws Exception {
		HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();

		// optional default is GET
		httpClient.setRequestMethod("GET");

		int responseCode = httpClient.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
		String jsonString;

		try (BufferedReader in = new BufferedReader(new InputStreamReader(httpClient.getInputStream()))) {

			StringBuilder response = new StringBuilder();
			String line;

			while ((line = in.readLine()) != null) {
				response.append(line);
			}

			jsonString = response.toString();
			return jsonString;

		}
		

	}
	public static int sendPost(String url)throws Exception
	{
		HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();

		// optional default is GET
		httpClient.setRequestMethod("POST");
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
	
	static boolean isValid(String email) {
		   String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		   return email.matches(regex);
		}
}
