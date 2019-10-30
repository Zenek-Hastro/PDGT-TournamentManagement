package kotc;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.KeyEvent;

public class kotc1 {
	static String user = null;
	static String pwd = null;


	private JFrame frmLogin;
	private JPasswordField pwdPassword;
	private JTextField txtUsername;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					kotc1 window = new kotc1();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public kotc1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frmLogin = new JFrame();
		frmLogin.setResizable(false);
		frmLogin.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Lenovo\\Desktop\\icona programma.png"));
		frmLogin.setTitle("LOGIN");
		frmLogin.setBounds(100, 100, 610, 300);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);

		JLabel lblGestioneTorneo = new JLabel("GESTIONE TORNEO - Effettua il login per iniziare");
		lblGestioneTorneo.setHorizontalAlignment(SwingConstants.CENTER);
		lblGestioneTorneo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGestioneTorneo.setBounds(24, 10, 544, 35);
		frmLogin.getContentPane().add(lblGestioneTorneo);

		txtUsername = new JTextField();
		txtUsername.setName("");
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtUsername.setHorizontalAlignment(SwingConstants.LEFT);
		txtUsername.setBounds(199, 86, 218, 21);
		frmLogin.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);

		pwdPassword = new JPasswordField();
		pwdPassword.setDragEnabled(true);
		pwdPassword.setBounds(199, 127, 218, 21);
		frmLogin.getContentPane().add(pwdPassword);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUsername.setBounds(126, 86, 63, 21);
		frmLogin.getContentPane().add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPassword.setBounds(126, 126, 63, 21);
		frmLogin.getContentPane().add(lblPassword);

		JLabel lblEx = new JLabel("");
		lblEx.setVisible(false);
		lblEx.setBounds(316, 185, 218, 17);
		frmLogin.getContentPane().add(lblEx);

		JButton btnLogin = new JButton("Login");
		btnLogin.setMnemonic(KeyEvent.VK_ENTER);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String url = "https://tournament-manage.herokuapp.com/login";
				String result;
				try {
					result = attemptAuth(url);
					System.out.println(user);
					System.out.println(result);
					lblEx.setVisible(true);
					lblEx.setText("Login effettuato");

					main main = new main();
					main.setVisible(true);
					frmLogin.setVisible(false);

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println(e1);
					lblEx.setVisible(true);
					lblEx.setVisible(true);
					lblEx.setText("Credenziali errate. Riprovare");

				}

			}
		});
		btnLogin.setBounds(199, 181, 85, 21);
		frmLogin.getContentPane().add(btnLogin);

		JLabel lblV = new JLabel("v 1.0.0");
		lblV.setBounds(550, 250, 46, 13);
		frmLogin.getContentPane().add(lblV);

	}

	private String attemptAuth(String url) throws Exception {
		HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();

		httpClient.setRequestMethod("GET");
		user = txtUsername.getText();
		pwd = pwdPassword.getText();
		String input = txtUsername.getText() + ':' + pwdPassword.getText();
		String encodedString = Base64.getEncoder().encodeToString(input.getBytes());
		httpClient.addRequestProperty("Authorization", "BASIC " + encodedString);
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
	
	public static String getAuth() {
		String authstring = user+":"+pwd;
		return authstring;
	}
}
