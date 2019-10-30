package kotc;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class main extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main frame = new main();
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
	public main() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Lenovo\\Desktop\\icona programma.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1023, 591);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnVisualizza = new JMenu("Visualizza");
		mnVisualizza.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				visualizza visualizza = new visualizza();
				visualizza.setVisible(true);
				visualizza.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
		});
		mnVisualizza.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mnVisualizza);
		
		JMenu mnInserimento = new JMenu("Inserimento");
		mnInserimento.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				inserimento inserimento = new inserimento();
				inserimento.setVisible(true);
				inserimento.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
		});
		mnInserimento.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mnInserimento);
		
		JMenu mnModificaDati = new JMenu("Modifica dati");
		mnModificaDati.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				modifica modifica = new modifica();
				modifica.setVisible(true);
				modifica.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
		});
		
		mnModificaDati.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mnModificaDati);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblV = new JLabel("v 1.0.0");
		lblV.setBounds(953, 505, 46, 13);
		contentPane.add(lblV);
	}
}
