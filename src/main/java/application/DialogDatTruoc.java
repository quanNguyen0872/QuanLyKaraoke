package application;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class DialogDatTruoc extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private PnlDatTruoc pnlDatTruoc;
	/**
	 * Create the dialog.
	 */
	public DialogDatTruoc() {
		setBounds(100, 100, 1154, 670);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		pnlDatTruoc = new PnlDatTruoc();
		pnlDatTruoc.setBounds(0, 0, 1140, 633);
		contentPanel.add(pnlDatTruoc);
	}

}
