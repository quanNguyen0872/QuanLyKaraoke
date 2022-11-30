package start;

import javax.swing.JDialog;

import application.DialogDangNhap;

public class Main {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogDangNhap dialog = new DialogDangNhap();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
