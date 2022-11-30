package application;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import dao.TaiKhoanDAO;
import helpers.DataValidator;
import helpers.MessageDialogHelpers;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogQuenMatKhau extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtTenDN;
	private JTextField txtCauHoi;
	private JTextField txtCauTraLoi;
	private JPasswordField txtMatKhauMoi;
	private JButton btnLuu;
	private JButton btnTroLai;
	private JButton btnTim;
	private JButton btnLamMoi;

	/**
	 * Create the dialog.
	 */
	public DialogQuenMatKhau() {

		/** set font & color **/
		Color whiteColor = new Color(255, 255, 255);
		Color mainColor = new Color(88, 159, 177);
		Color hoverColor = new Color(121, 178, 192);
		
		Font tahoma14 = new Font("Tahoma", Font.PLAIN, 14);
		Font tahoma16 = new Font("Tahoma", Font.PLAIN, 16);
		Font tahoma20 = new Font("Tahoma", Font.BOLD, 20);

		setBounds(100, 100, 829, 475);
		setTitle("Quên mật khẩu");
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Qu\u00EAn m\u1EADt kh\u1EA9u");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(tahoma20);
			lblNewLabel.setBounds(0, 0, 813, 43);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("T\u00EAn \u0111\u0103ng nh\u1EADp:");
			lblNewLabel_1.setFont(tahoma16);
			lblNewLabel_1.setBounds(104, 91, 139, 27);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("C\u00E2u h\u1ECFi b\u1EA3o m\u1EADt:");
			lblNewLabel_1.setFont(tahoma16);
			lblNewLabel_1.setBounds(104, 145, 139, 27);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("C\u00E2u tr\u1EA3 l\u1EDDi:");
			lblNewLabel_1.setFont(tahoma16);
			lblNewLabel_1.setBounds(104, 204, 139, 27);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("M\u1EADt kh\u1EA9u m\u1EDBi:");
			lblNewLabel_1.setFont(tahoma16);
			lblNewLabel_1.setBounds(104, 270, 139, 27);
			contentPanel.add(lblNewLabel_1);
		}

		txtTenDN = new JTextField();
		txtTenDN.setFont(tahoma16);
		txtTenDN.setBounds(268, 91, 393, 25);
		contentPanel.add(txtTenDN);
		txtTenDN.setColumns(10);

		txtCauHoi = new JTextField();
		txtCauHoi.setEditable(false);
		txtCauHoi.setFocusTraversalKeysEnabled(false);
		txtCauHoi.setFocusable(false);
		txtCauHoi.setFont(tahoma16);
		txtCauHoi.setColumns(10);
		txtCauHoi.setBounds(268, 150, 393, 25);
		contentPanel.add(txtCauHoi);

		txtCauTraLoi = new JTextField();
		txtCauTraLoi.setEditable(false);
		txtCauTraLoi.setFont(tahoma16);
		txtCauTraLoi.setColumns(10);
		txtCauTraLoi.setBounds(268, 209, 393, 25);
		contentPanel.add(txtCauTraLoi);

		txtMatKhauMoi = new JPasswordField();
		txtMatKhauMoi.setEditable(false);
		txtMatKhauMoi.setFont(tahoma16);
		txtMatKhauMoi.setColumns(10);
		txtMatKhauMoi.setBounds(268, 270, 393, 25);
		contentPanel.add(txtMatKhauMoi);

		btnLuu = new JButton("L\u01B0u");
		btnLuu.setFocusable(false);
		btnLuu.setFocusTraversalKeysEnabled(false);
		btnLuu.setFocusPainted(false);
		btnLuu.setBorder(null);
		btnLuu.setRequestFocusEnabled(false);
		btnLuu.setFont(tahoma14);
		btnLuu.setBounds(268, 332, 105, 27);
		contentPanel.add(btnLuu);

		btnTroLai = new JButton("Tr\u1EDF l\u1EA1i");
		btnTroLai.setFocusable(false);
		btnTroLai.setFocusTraversalKeysEnabled(false);
		btnTroLai.setFocusPainted(false);
		btnTroLai.setBorder(null);
		btnTroLai.setRequestFocusEnabled(false);
		btnTroLai.setFont(tahoma14);
		btnTroLai.setBounds(556, 332, 105, 27);
		contentPanel.add(btnTroLai);

		btnTim = new JButton("T\u00ECm");
		btnTim.setFocusable(false);
		btnTim.setFocusTraversalKeysEnabled(false);
		btnTim.setFocusPainted(false);
		btnTim.setBorder(null);
		btnTim.setRequestFocusEnabled(false);
		btnTim.setFont(tahoma14);
		btnTim.setBounds(698, 91, 105, 27);
		contentPanel.add(btnTim);

		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setFocusable(false);
		btnLamMoi.setFocusTraversalKeysEnabled(false);
		btnLamMoi.setFocusPainted(false);
		btnLamMoi.setBorder(null);
		btnLamMoi.setRequestFocusEnabled(false);
		btnLamMoi.setFont(tahoma14);
		btnLamMoi.setBounds(698, 149, 105, 27);
		contentPanel.add(btnLamMoi);
		
		/**/
		btnLamMoi.setBackground(mainColor);
		btnLamMoi.setForeground(whiteColor);
		btnLamMoi.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnLamMoi.setBackground(hoverColor);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnLamMoi.setBackground(mainColor);
			}
		});
		
		btnLuu.setBackground(mainColor);
		btnLuu.setForeground(whiteColor);
		btnLuu.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnLuu.setBackground(hoverColor);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnLuu.setBackground(mainColor);
			}
		});
		
		btnTim.setBackground(mainColor);
		btnTim.setForeground(whiteColor);
		btnTim.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnTim.setBackground(hoverColor);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnTim.setBackground(mainColor);
			}
		});
		
		btnTroLai.setBackground(mainColor);
		btnTroLai.setForeground(whiteColor);
		btnTroLai.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnTroLai.setBackground(hoverColor);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnTroLai.setBackground(mainColor);
			}
		});
		/**/

		btnTim.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnLuu.addActionListener(this);
		btnTroLai.addActionListener(this);
	}

	public void lamRongText() {
		txtTenDN.setText("");
		txtCauHoi.setText("");
		txtCauTraLoi.setText("");
		txtMatKhauMoi.setText("");
		txtTenDN.requestFocus();
	}

	public void validateTim(StringBuilder sb) {
		DataValidator.validateEmpty(txtTenDN, sb, "Tên đăng nhập không được rỗng");
	}

	public void validateAll(StringBuilder sb) {
		DataValidator.validateMatKhau(txtMatKhauMoi, sb,
				"Mật khẩu phải đúng định dạng: phải có 1 chữ hoa, 1 chữ thường, 1 kí tự đặc biệt, tối thiểu 8 kí tự");
		DataValidator.validateEmpty(txtMatKhauMoi, sb, "Mật khẩu mới không được rỗng");
		DataValidator.validateEmpty(txtCauHoi, sb, "Câu hỏi không được rỗng");
		DataValidator.validateEmpty(txtCauTraLoi, sb, "Câu trả lời không được rỗng");
		DataValidator.validateEmpty(txtTenDN, sb, "Tên đăng nhập không được rỗng");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		// tìm câu hỏi theo tên đăng nhập
		if (o.equals(btnTim)) {
			String tenDN = txtTenDN.getText();
			TaiKhoanDAO tkDao = new TaiKhoanDAO();
			String cauHoi = tkDao.getCauHoiTheoTen(tenDN);

			// validate
			StringBuilder sb = new StringBuilder();
			validateTim(sb);
			if (sb.length() > 0) {
				MessageDialogHelpers.showMessageDialog(txtMatKhauMoi, "Nhắc nhở", sb.toString());
				return;
			}

			if (cauHoi != null) {
				txtCauHoi.setText(cauHoi);
				txtCauTraLoi.setEditable(true);
				txtMatKhauMoi.setEditable(true);
			} else {
				MessageDialogHelpers.showErrorDialog(txtMatKhauMoi, "Lỗi", "Không tìm thấy tên đăng nhập này");
			}
		}

		// Lưu mật khẩu mới
		if (o.equals(btnLuu)) {
			String tenDN = txtTenDN.getText();
			String matKhauMoi = txtMatKhauMoi.getText();
			String cauTraLoi = txtCauTraLoi.getText();

			TaiKhoanDAO tkDao = new TaiKhoanDAO();

			// validate
			StringBuilder sb = new StringBuilder();
			validateAll(sb);
			if (sb.length() > 0) {
				MessageDialogHelpers.showMessageDialog(txtMatKhauMoi, "Nhắc nhở", sb.toString());
				return;
			}

			if (tkDao.updateMatKhauTheoTenVaTraLoi(tenDN, cauTraLoi, matKhauMoi)) {
				MessageDialogHelpers.showMessageDialog(txtMatKhauMoi, "Thông báo",
						"Mật khẩu mới đã cập nhật thành công");
				dispose();
			} else {
				MessageDialogHelpers.showErrorDialog(txtMatKhauMoi, "Lỗi", "Cập nhật không thành công");
			}
		}

		// trở về
		if (o.equals(btnTroLai)) {
			dispose();
		}

		// làm mới
		if (o.equals(btnLamMoi)) {
			lamRongText();
			txtCauTraLoi.setEditable(false);
			txtMatKhauMoi.setEditable(false);
		}
	}
}
