package kotc;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.List;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.json.JSONArray;
import org.json.JSONObject;

public class visualizza extends JFrame {

	private JPanel contentPane;
	private JTextField txtCognome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					visualizza frame = new visualizza();
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
	public visualizza() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Lenovo\\Desktop\\icona programma.png"));

		getContentPane().setLayout(null);
		setTitle("Visualizza");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 917, 508);
		contentPane = new JPanel();
		contentPane.setName("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		List list_1 = new List();
		list_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		list_1.setBounds(25, 20, 856, 230);
		contentPane.add(list_1);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(25, 256, 856, 11);
		contentPane.add(progressBar);

		JButton btnVisualizzaIscritti = new JButton("Visualizza Iscritti");
		btnVisualizzaIscritti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		});
		btnVisualizzaIscritti.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnVisualizzaIscritti.setBounds(25, 298, 145, 45);
		contentPane.add(btnVisualizzaIscritti);

		JLabel lblV = new JLabel("v 1.0.0");
		lblV.setBounds(857, 458, 46, 13);
		contentPane.add(lblV);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(328, 298, 9, 163);
		contentPane.add(separator_1);

		JLabel lblVisualizzaAnagraficaPer = new JLabel("Visualizza Anagrafica per:");
		lblVisualizzaAnagraficaPer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblVisualizzaAnagraficaPer.setBounds(347, 298, 285, 27);
		contentPane.add(lblVisualizzaAnagraficaPer);

		txtCognome = new JTextField();
		txtCognome.setName("Cognome");
		txtCognome.setVisible(false);
		txtCognome.setBounds(575, 350, 194, 27);
		contentPane.add(txtCognome);
		txtCognome.setColumns(10);

		JLabel lblCognome = new JLabel("Cognome");
		lblCognome.setVisible(false);
		lblCognome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCognome.setBounds(802, 349, 79, 27);
		contentPane.add(lblCognome);

		JButton btnTrova = new JButton("Trova");
		txtCognome.setText("");
		btnTrova.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String url = "https://tournament-manage.herokuapp.com/tournament/players/searchbyname?surname="
						+ txtCognome.getText().replace(" ","%20");
				progressBar.setValue(0);
				list_1.removeAll();
				try {
					String result = sendGet(url); 
					JSONArray mainobj = new JSONArray(result);
					for (var i = 0; i < mainobj.length(); i++) {
						JSONObject obj = mainobj.getJSONObject(i);
						String stringa = obj.get("Nome").toString() + ",  " + obj.get("Cognome").toString() + ",  "
								+ obj.get("Data di nascita").toString() + ",  " + obj.get("Luogo di nascita").toString()
								+ ",  " + obj.get("Codice Fiscale").toString() + ",  "
								+ obj.get("Luogo residenza").toString() + ",  " + obj.get("Mail").toString() + ",  "
								+ obj.get("Cellulare").toString() + ",  " + obj.get("Squadra").toString() + '\n';
						progressBar.setValue(50 + (50 / mainobj.length() + (50 - mainobj.length())));
						list_1.add(stringa);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					list_1.add("Nessuna corrispondenza trovata");
					progressBar.setValue(100);
				}
			}
		});
		btnTrova.setVisible(false);
		btnTrova.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnTrova.setBounds(575, 399, 85, 21);
		contentPane.add(btnTrova);
		
		JButton btnTrova_1 = new JButton("Trova");
		txtCognome.setText("");
		btnTrova_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String url = "https://tournament-manage.herokuapp.com/tournament/players/searchbyteam?teamname="
						+ txtCognome.getText().replace(" ","%20");
				progressBar.setValue(0);
				list_1.removeAll();
				try {
					String result = sendGet(url);
					JSONArray mainobj = new JSONArray(result);
					for (var i = 0; i < mainobj.length(); i++) {
						JSONObject obj = mainobj.getJSONObject(i);
						String stringa = obj.get("Nome").toString() + ",  " + obj.get("Cognome").toString() + ",  "
								+ obj.get("Data di nascita").toString() + ",  " + obj.get("Luogo di nascita").toString()
								+ ",  " + obj.get("Codice Fiscale").toString() + ",  "
								+ obj.get("Luogo residenza").toString() + ",  " + obj.get("Mail").toString() + ",  "
								+ obj.get("Cellulare").toString() + ",  " + obj.get("Squadra").toString() + '\n';
						progressBar.setValue(50 + (50 / mainobj.length() + (50 - mainobj.length())));
						list_1.add(stringa);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					list_1.add("Nessuna corrispondenza trovata");
					progressBar.setValue(100);
				}
				
			}
		});
		btnTrova_1.setVisible(false);
		btnTrova_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnTrova_1.setBounds(575, 400, 85, 21);
		contentPane.add(btnTrova_1);

		JRadioButton rdbtnIdIscritto = new JRadioButton("Nome Squadra");
		JRadioButton rdbtnNomeECognome = new JRadioButton("Cognome Iscritto");

		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnIdIscritto);
		group.add(rdbtnNomeECognome);

		rdbtnIdIscritto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtCognome.setVisible(true);
				lblCognome.setVisible(true);
				lblCognome.setText("Nome Squadra");
				btnTrova.setVisible(false);
				btnTrova_1.setVisible(true);
				
				
			}
		});
		rdbtnIdIscritto.setBounds(343, 384, 226, 21);
		contentPane.add(rdbtnIdIscritto);

		rdbtnNomeECognome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtCognome.setVisible(true);
				lblCognome.setVisible(true);
				lblCognome.setText("Cognome");
				btnTrova.setVisible(true);
			}

		});
		rdbtnNomeECognome.setBounds(343, 353, 226, 21);
		contentPane.add(rdbtnNomeECognome);
		
		

	}

	private String sendGet(String url) throws Exception {
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
}
