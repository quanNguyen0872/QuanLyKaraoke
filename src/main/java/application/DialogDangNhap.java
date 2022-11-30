/**
 * 
 * Tác giả: La Võ Minh Quân - MSSV:19441111 - Nhóm 4
 * Ngày tạo: 21/10/2021
 * 
 * Mô tả: Giao diện đăng nhập vào phần mềm quản lý karaoke
 */

package application;

import java.awt.BorderLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import java.awt.Font;
import javax.swing.JLabel;

import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;

import dao.TaiKhoanDAO;
import entity.TaiKhoan;
import helpers.DataValidator;
import helpers.MessageDialogHelpers;
import helpers.ShareData;

import javax.swing.SwingConstants;
import java.awt.event.KeyEvent;

public class DialogDangNhap extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JButton btnDangNhap;
	private JButton btnThoat;

	private TaiKhoanDAO taiKhoanDAO;
	private DialogQuenMatKhau quenMatKhauDialog;


	/**
	 * Create the dialog.
	 */
	public DialogDangNhap() {

		/** set color & font **/
		Color mainColor = new Color(88,159,177);
		Color hoverColor = new Color(121,178,192);
		Color seperatorColor = new Color(204, 204, 204);
		Color whiteColor = new Color(255, 255, 255);
		Color blackColor = new Color(51, 51, 51);
		Color hovertextColor = new Color(250, 130, 49);
		Color borderColor = new Color(153, 153, 153);
		
		Font tahoma18 = new Font("Tahoma", Font.BOLD, 18);
		Font tahoma16 = new Font("Tahoma", Font.PLAIN, 16);
		Font tahoma14 = new Font("Tahoma", Font.BOLD, 14);
		


		setSize(650, 340);
		setLocationRelativeTo(null);
		setResizable(false);
		setUndecorated(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(borderColor));
		panel.setBackground(mainColor);
		panel.setBounds(0, 0, 325, 360);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/images/kara.png")));
		lblNewLabel.setBounds(63, 62, 193, 233);
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(borderColor, 1, true));
		panel_1.setBackground(whiteColor);
		panel_1.setBounds(324, 0, 326, 360);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		panel_1.setFocusable(true);

		txtUsername = new JTextField();
		txtUsername.setFocusTraversalPolicyProvider(true);
		txtUsername.setFont(tahoma16);
		txtUsername.setForeground(blackColor);
		txtUsername.setBorder(new LineBorder(borderColor));
		txtUsername.setBounds(62, 121, 230, 27);
		panel_1.add(txtUsername);
		txtUsername.setColumns(10);
		txtUsername.setText("quanla2001");

		txtPassword = new JPasswordField();
		txtPassword.setFont(tahoma16);
		txtPassword.setForeground(blackColor);
		txtPassword.setBorder(new LineBorder(borderColor));
		txtPassword.setColumns(10);
		txtPassword.setBounds(62, 197, 230, 27);
		panel_1.add(txtPassword);
		txtPassword.setText("Quan@1234");

		// tab xuống
		txtUsername.setNextFocusableComponent(txtPassword);

		JSeparator separator = new JSeparator();
		separator.setForeground(seperatorColor);
		separator.setBounds(62, 159, 230, 2);
		panel_1.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(seperatorColor);
		separator_1.setBounds(62, 235, 230, 2);
		panel_1.add(separator_1);

		JLabel lblLogin = new JLabel("ĐĂNG NHẬP");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setForeground(blackColor);
		lblLogin.setFont(tahoma18);
		lblLogin.setBounds(107, 42, 126, 27);
		panel_1.add(lblLogin);

		// btn đăng nhập
		btnDangNhap = new JButton("Đăng nhập");
		btnDangNhap.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnDangNhap.setBackground(hoverColor);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnDangNhap.setBackground(mainColor);
		    }
		});
		btnDangNhap.setMnemonic(KeyEvent.VK_ENTER);
		btnDangNhap.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDangNhap.setFont(tahoma14);
		btnDangNhap.setForeground(whiteColor);
		btnDangNhap.setFocusable(false);
		btnDangNhap.setBorder(null);
		btnDangNhap.setBackground(mainColor);
		btnDangNhap.setBounds(62, 248, 105, 35);
		panel_1.add(btnDangNhap);

		// btn thoát
		btnThoat = new JButton("Thoát");
		btnThoat.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnThoat.setBackground(hoverColor);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnThoat.setBackground(mainColor);
		    }
		});
		btnThoat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnThoat.setForeground(whiteColor);
		btnThoat.setFont(tahoma14);
		btnThoat.setFocusable(false);
		btnThoat.setBorder(null);
		btnThoat.setBackground(mainColor);
		btnThoat.setBounds(187, 248, 105, 35);
		panel_1.add(btnThoat);

		JLabel lblExit = new JLabel("");
		lblExit.setHorizontalAlignment(SwingConstants.CENTER);
		lblExit.setIcon(new ImageIcon(getClass().getResource("/images/x-mark-16.png")));
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblExit.setForeground(new Color(255, 102, 102));
		lblExit.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblExit.setBounds(302, 0, 24, 21);
		panel_1.add(lblExit);

		JLabel lblNewLabel_1 = new JLabel("Tên đăng nhập:");
		lblNewLabel_1.setFont(tahoma16);
		lblNewLabel_1.setBounds(62, 89, 129, 21);
		panel_1.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Mật khẩu:");
		lblNewLabel_1_1.setFont(tahoma16);
		lblNewLabel_1_1.setBounds(62, 165, 129, 21);
		panel_1.add(lblNewLabel_1_1);

		JLabel lblQuenMatKhau = new JLabel("Quên mật khẩu?");
		lblQuenMatKhau.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				quenMatKhauDialog = new DialogQuenMatKhau();
				quenMatKhauDialog.setVisible(true);
			}
		});
		
		lblQuenMatKhau.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				lblQuenMatKhau.setBackground(hoverColor);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	lblQuenMatKhau.setBackground(mainColor);
		    }
		});
		
		
		lblQuenMatKhau.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblQuenMatKhau.setForeground(mainColor);
		lblQuenMatKhau.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuenMatKhau.setFont(tahoma16);
		lblQuenMatKhau.setBounds(107, 304, 126, 21);
		panel_1.add(lblQuenMatKhau);
		
		txtUsername.addFocusListener(new FocusAdapter() {
			 @Override
			    public void focusGained(FocusEvent e) {
			        txtUsername.setBorder(BorderFactory.createLineBorder(hoverColor));
			        txtUsername.setForeground(hovertextColor);
			    }

			    @Override
			    public void focusLost(FocusEvent e) {
			    	txtUsername.setBorder(BorderFactory.createLineBorder(seperatorColor));
			    	txtUsername.setForeground(blackColor);
			    }
        });
		
		txtPassword.addFocusListener(new FocusAdapter() {
			 @Override
			    public void focusGained(FocusEvent e) {
			        txtPassword.setBorder(BorderFactory.createLineBorder(hoverColor));
			        txtPassword.setForeground(hovertextColor);
			    }

			    @Override
			    public void focusLost(FocusEvent e) {
			        txtPassword.setBorder(BorderFactory.createLineBorder(seperatorColor));
			        txtPassword.setForeground(blackColor);
			    }
        });
		

		btnThoat.addActionListener(this);
		btnDangNhap.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o.equals(btnThoat)) {
			System.exit(0);
		}

		if (o.equals(btnDangNhap)) {
			StringBuilder sb = new StringBuilder();
			DataValidator.validateEmpty(txtPassword, sb, "Mật khẩu không được để trống!");
			DataValidator.validateEmpty(txtUsername, sb, "Tên đăng nhập không được để trống!");
			if (sb.length() > 0) {
				MessageDialogHelpers.showErrorDialog(btnDangNhap, "Lỗi", sb.toString());
				return;
			}

			taiKhoanDAO = new TaiKhoanDAO();
			TaiKhoan taiKhoan = taiKhoanDAO.checkLogin(txtUsername.getText(), txtPassword.getText());
			if (taiKhoan != null) {
				ShareData.taiKhoanDangNhap = taiKhoan;
				dispose();
				MainFrame mainFrame = new MainFrame();
				mainFrame.setVisible(true);
			} else {
				MessageDialogHelpers.showErrorDialog(btnDangNhap, "Lỗi", "Sai tên đăng nhập hoặc mật khẩu");
				txtUsername.requestFocus();
				txtUsername.selectAll();
			}
		}

	}
}
