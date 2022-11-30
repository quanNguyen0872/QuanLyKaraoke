/**
 * Tác giả: La Võ Minh Quân - 19441111
 * Mô tả: dialog thêm khách hàng khi chưa có số điện thoại
 */
package application;

import javax.swing.JButton;
import javax.swing.JDialog;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;

import dao.KhachHangDAO;
import entity.KhachHang;
import helpers.DataValidator;
import helpers.MessageDialogHelpers;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogThemKhachHang extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField txtTenKH;
	private JTextField txtSDT;
	private JButton btnThem;
	private JButton btnDong;

	/**
	 * Create the dialog.
	 */
	public DialogThemKhachHang() {
		
		Font tahoma16 = new Font("Tahoma", Font.PLAIN, 16);
		Color whiteColor = new Color(255, 255, 255);
		Color mainColor = new Color(88, 159, 177);
		Color hoverColor = new Color(121, 178, 192);
		
		setSize(620, 300);
		setLocationRelativeTo(null);

		JLabel lblNewLabel_1_1 = new JLabel("Tên khách hàng:");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setFont(tahoma16);

		JLabel lblNewLabel_1_1_1 = new JLabel("Số điện thoại:");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1.setFont(tahoma16);

		JLabel lblNewLabel = new JLabel("THÔNG TIN KHÁCH HÀNG");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));

		txtTenKH = new JTextField();
		txtTenKH.setFont(tahoma16);
		txtTenKH.setColumns(10);

		txtSDT = new JTextField();
		txtSDT.setFont(tahoma16);
		txtSDT.setColumns(10);

		btnThem = new JButton("Thêm khách hàng");
		btnThem.setFocusPainted(false);
		btnThem.setFocusTraversalKeysEnabled(false);
		btnThem.setFocusable(false);
		btnThem.setBorder(null);
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				KhachHang khachHang = createKhachHang();
				KhachHangDAO khachHangDAO = new KhachHangDAO();

				StringBuilder sb = new StringBuilder();
				dataValidateKhachHang(sb);

				if (sb.length() > 0) {
					MessageDialogHelpers.showErrorDialog(txtSDT, "Lỗi", sb.toString());
					return;
				}

				if (khachHangDAO.checkExist(txtSDT.getText())) {
					MessageDialogHelpers.showErrorDialog(txtSDT, "Cảnh báo",
							"Khách hàng đã tồn tại, số điện thoại trùng");
					return;
				} else {
					if (khachHangDAO.addKhachHang(khachHang)) {
						MessageDialogHelpers.showMessageDialog(txtSDT, "Thông báo", "Khách hàng đã thêm thành công");
					} else {
						MessageDialogHelpers.showErrorDialog(txtSDT, "Lỗi", "Thêm không thành công");
					}
				}
			}
		});
		btnThem.setFont(tahoma16);

		btnDong = new JButton("Đóng");
		btnDong.setFocusPainted(false);
		btnDong.setFocusTraversalKeysEnabled(false);
		btnDong.setFocusable(false);
		btnDong.setBorder(null);
		btnDong.setFont(tahoma16);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 604, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnThem, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
							.addGap(29)
							.addComponent(btnDong, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(txtSDT, GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
								.addComponent(txtTenKH, GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE))
							.addGap(18))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addGap(51)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtTenKH, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(23)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtSDT, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(57)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnThem, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
						.addComponent(btnDong, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
					.addGap(20))
		);
		getContentPane().setLayout(groupLayout);
		
		btnDong.setBackground(mainColor);
		btnDong.setForeground(whiteColor);
		btnDong.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnDong.setBackground(hoverColor);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnDong.setBackground(mainColor);
			}
		});
		
		btnThem.setBackground(mainColor);
		btnThem.setForeground(whiteColor);
		btnThem.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnThem.setBackground(hoverColor);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnThem.setBackground(mainColor);
			}
		});
	}

	// tạo mới khách hàng
	private KhachHang createKhachHang() {
		KhachHang khachHang = new KhachHang();
		khachHang.setHoTenKH(txtTenKH.getText());
		khachHang.setSoDienThoai(txtSDT.getText());
		return khachHang;
	}

	/**
	 * kiểm tra biểu thức chính quy
	 * 
	 * @param sb
	 */
	private void dataValidateKhachHang(StringBuilder sb) {
		DataValidator.validateEmpty(txtSDT, sb, "Số điện thoại không được để trống");
		DataValidator.validateEmpty(txtTenKH, sb, "Tên khách hàng không được để trống");
		DataValidator.validateVietnameseCharacters(txtTenKH, sb,
				"Tên khách hàng sai.Không được nhập số và kí tự đặt biệt");
		DataValidator.validateSoDT(txtSDT, sb,
				"Số điện thoại sai định dạng, phải có từ 9-10 chữ số, không có kí tự. Ví dụ:0788775877");
		DataValidator.validateSoDT(txtSDT, sb, "Số điện thoại đã tồn tại");
	}
}
