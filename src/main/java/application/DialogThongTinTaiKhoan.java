/**
 * Tác giả : La Võ Minh Quân
 * Ngày tạo: 30/10/2021
 * Mô tả: xem thông tin chi tiết của tài khoản đó
 */
package application;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;

import helpers.ShareData;
import javax.swing.JSeparator;

public class DialogThongTinTaiKhoan extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblTenDangNhap;
	private JLabel lblVaiTro;
	private JLabel lblGioiTinh;
	private JLabel lblTenNV;
	private JLabel lblMaNV;

	/**
	 * Create the dialog.
	 */
	public DialogThongTinTaiKhoan() {
		setBounds(100, 100, 508, 325);
		setLocationRelativeTo(null);
		setResizable(false);
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new MatteBorder(1, 1, 2, 2, (Color) new Color(0, 0, 0)));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("T\u00EAn \u0111\u0103ng nh\u1EADp:");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Serif", Font.PLAIN, 17));
		lblNewLabel.setBounds(20, 60, 107, 33);
		contentPanel.add(lblNewLabel);

		JLabel lblVaiTr = new JLabel("Vai tr\u00F2:");
		lblVaiTr.setHorizontalAlignment(SwingConstants.LEFT);
		lblVaiTr.setFont(new Font("Serif", Font.PLAIN, 17));
		lblVaiTr.setBounds(299, 60, 60, 33);
		contentPanel.add(lblVaiTr);
		{
			lblTenDangNhap = new JLabel("");
			lblTenDangNhap.setFont(new Font("Serif", Font.PLAIN, 17));
			lblTenDangNhap.setBounds(137, 60, 129, 33);
			contentPanel.add(lblTenDangNhap);
		}
		{
			lblVaiTro = new JLabel("");
			lblVaiTro.setFont(new Font("Serif", Font.PLAIN, 17));
			lblVaiTro.setBounds(369, 60, 129, 33);
			contentPanel.add(lblVaiTro);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Thông tin tài khoản");
			lblNewLabel_1.setFont(new Font("Serif", Font.BOLD, 28));
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1.setBounds(10, 0, 488, 33);
			contentPanel.add(lblNewLabel_1);
		}

		JButton btnDong = new JButton("Đóng");
		btnDong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnDong.setFocusable(false);
		btnDong.setForeground(new Color(255, 255, 255));
		btnDong.setBackground(new Color(88, 177, 159));
		btnDong.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnDong.setIcon(null);
		btnDong.setBounds(411, 277, 87, 29);
		contentPanel.add(btnDong);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(0, 0, 0));
		separator.setBounds(10, 264, 488, 2);
		contentPanel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(new Color(0, 0, 0));
		separator_1.setBounds(10, 31, 488, 2);
		contentPanel.add(separator_1);
		
		JLabel lblMNhnVin = new JLabel("Mã nhân viên:");
		lblMNhnVin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMNhnVin.setForeground(Color.BLACK);
		lblMNhnVin.setFont(new Font("Serif", Font.PLAIN, 17));
		lblMNhnVin.setBounds(20, 104, 107, 33);
		contentPanel.add(lblMNhnVin);
		
		lblTenNV = new JLabel((String) null);
		lblTenNV.setFont(new Font("Serif", Font.PLAIN, 16));
		lblTenNV.setBounds(137, 152, 129, 29);
		contentPanel.add(lblTenNV);
		
		lblMaNV = new JLabel((String) null);
		lblMaNV.setFont(new Font("Serif", Font.PLAIN, 16));
		lblMaNV.setBounds(137, 104, 129, 33);
		contentPanel.add(lblMaNV);
		
		JLabel lblTnNhnVin = new JLabel("Tên nhân viên:");
		lblTnNhnVin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTnNhnVin.setFont(new Font("Serif", Font.PLAIN, 17));
		lblTnNhnVin.setBounds(20, 148, 107, 33);
		contentPanel.add(lblTnNhnVin);
		
		JLabel lblVaiTro_2 = new JLabel((String) null);
		lblVaiTro_2.setFont(new Font("Serif", Font.PLAIN, 16));
		lblVaiTro_2.setBounds(137, 114, 129, 23);
		contentPanel.add(lblVaiTro_2);
		
		JLabel lblMaNhanVien_1 = new JLabel((String) null);
		lblMaNhanVien_1.setFont(new Font("Serif", Font.PLAIN, 16));
		lblMaNhanVien_1.setBounds(137, 104, 129, 29);
		contentPanel.add(lblMaNhanVien_1);
		
		JLabel lblGioi = new JLabel("Giới tính:");
		lblGioi.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGioi.setFont(new Font("Serif", Font.PLAIN, 17));
		lblGioi.setBounds(20, 192, 107, 33);
		contentPanel.add(lblGioi);
		
		lblGioiTinh = new JLabel((String) null);
		lblGioiTinh.setFont(new Font("Serif", Font.PLAIN, 16));
		lblGioiTinh.setBounds(137, 192, 129, 29);
		contentPanel.add(lblGioiTinh);

		processLoginSuccessfull();
	}
	
	private void processLoginSuccessfull() {
		lblTenDangNhap.setText(ShareData.taiKhoanDangNhap.getTenDangNhap());
		lblVaiTro.setText(ShareData.taiKhoanDangNhap.getVaiTro());
		lblMaNV.setText(ShareData.taiKhoanDangNhap.getNhanVien().getMaNhanVien());
		lblTenNV.setText(ShareData.taiKhoanDangNhap.getNhanVien().getHoTen());
		lblGioiTinh.setText(ShareData.taiKhoanDangNhap.getNhanVien().getGioiTinh() == 1 ? "Nam" : "Nữ");
	
	}
}
