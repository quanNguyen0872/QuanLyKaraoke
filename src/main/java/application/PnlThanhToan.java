/**
 * 
 * Tác giả: La Võ Minh Quân - MSSV:19441111 - Nhóm 4
 * Ngày tạo: 17/11/2021
 * 
 * Mô tả: Giao diện dùng để thanh toán cho khách hàng
 */
package application;

import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import connectDB.MSSQLConnection;
import dao.ChiTietHoaDonDao;
import dao.DonDatPhongDAO;
import dao.HoaDonDao;
import dao.LoaiSanPhamDAO;
import dao.PhongDAO;
import dao.SanPhamDAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.LoaiSanPham;
import entity.Phong;
import entity.SanPham;
import helpers.DataValidator;
import helpers.MessageDialogHelpers;
import helpers.ShareData;

import javax.swing.border.EtchedBorder;
import javax.swing.JComboBox;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PnlThanhToan extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtTimPhong;
	private JTable tblPhongDangSuDung;
	private JTable tblDichVu;
	private JTextField txtSoLuong;
	private JTextField txtNhanVienLap;
	private JButton btnXemDanhSachHD;
	private JButton btnThemThucPham;
	private JButton btnXoa;
	private JButton btnLamMoi;
	private JButton btnThanhToan;
	private JComboBox<String> cmbLoaiThucPham;
	private JComboBox<String> cmbTenThucPham;
	private DefaultTableModel dfModelPhongSuDung;
	private DefaultTableModel dfModelDichVu;
	private JLabel lblPhong;
	private JLabel lblDonGiaPhong;
	private JLabel lblKhachHang;
	private JButton btnCapNhat;
	private JLabel lblTienDichVu;
	private JComboBox<String> cmbTimKiem;

	private DialogDanhSachHoaDon danhSachHoaDon;
	private JLabel lblTongTienHoaDon;
	private JLabel lblNewLabel_1;
	private JTextField txtTienKhachDua;
	private JLabel lblTienThua;

	/**
	 * Create the panel.
	 */
	public PnlThanhToan() {

		/** set font and color **/
		Color mainColor = new Color(88, 159, 177);
		Color hoverColor = new Color(121, 178, 192);
		Color seperatorColor = new Color(204, 204, 204);
		Color whiteColor = new Color(255, 255, 255);
		Color blackColor = new Color(51, 51, 51);
		Color hovertextColor = new Color(250, 130, 49);
		Color tableHeaderColor = new Color(42, 143, 178);

		Font tahoma18Bold = new Font("Tahoma", Font.BOLD, 18);
		Font tahoma16Bold = new Font("Tahoma", Font.BOLD, 16);
		Font tahoma15Bold = new Font("Tahoma", Font.BOLD, 15);
		Font tahoma13Bold = new Font("Tahoma", Font.BOLD, 13);
		Font tahoma14Bold = new Font("Tahoma", Font.BOLD, 14);
		Font tahoma16 = new Font("Tahoma", Font.PLAIN, 16);
		Font tahoma13 = new Font("Tahoma", Font.PLAIN, 13);

		setBackground(whiteColor);

		JLabel lblNewLabel = new JLabel("Thanh to\u00E1n");
		lblNewLabel.setFont(tahoma18Bold);

		txtTimPhong = new JTextField();
		txtTimPhong.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String searchStr = txtTimPhong.getText();
				search(searchStr);
			}
		});
		txtTimPhong.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtTimPhong.setBorder(BorderFactory.createLineBorder(hoverColor));
				txtTimPhong.setForeground(hovertextColor);
			}

			@Override
			public void focusLost(FocusEvent e) {
				txtTimPhong.setBorder(BorderFactory.createLineBorder(seperatorColor));
				txtTimPhong.setForeground(blackColor);
			}
		});
		txtTimPhong.setFont(tahoma16);
		txtTimPhong.setColumns(10);

		btnXemDanhSachHD = new JButton("Xem danh s\u00E1ch h\u00F3a \u0111\u01A1n");
		btnXemDanhSachHD.setBorder(null);
		btnXemDanhSachHD.setFocusTraversalKeysEnabled(false);
		btnXemDanhSachHD.setFocusPainted(false);
		btnXemDanhSachHD.setFocusable(false);
		btnXemDanhSachHD.setFont(tahoma13Bold);

		JLabel lblNewLabel_2 = new JLabel("Ph\u00F2ng \u0111ang s\u1EED d\u1EE5ng");
		lblNewLabel_2.setFont(tahoma14Bold);

		JScrollPane scrollPane_phongSuDung = new JScrollPane();

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"D\u1ECBch v\u1EE5", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));

		JScrollPane scrollPane_dichvu = new JScrollPane();

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Thanh to\u00E1n", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));

		lblPhong = new JLabel("Ph\u00F2ng:");
		lblPhong.setFont(tahoma13);

		lblKhachHang = new JLabel("Kh\u00E1ch h\u00E0ng:");
		lblKhachHang.setFont(tahoma13);

		lblDonGiaPhong = new JLabel("\u0110\u01A1n gi\u00E1 ph\u00F2ng:");
		lblDonGiaPhong.setFont(tahoma13);

		JLabel lblNewLabel_3 = new JLabel("T\u1ED5ng ti\u1EC1n d\u1ECBch v\u1EE5:");
		lblNewLabel_3.setFont(tahoma14Bold);

		lblTienDichVu = new JLabel("");
		lblTienDichVu.setFont(tahoma16);

		cmbTimKiem = new JComboBox<String>();
		cmbTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout
				.setHorizontalGroup(
						groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addContainerGap()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup()
														.addComponent(scrollPane_phongSuDung, GroupLayout.DEFAULT_SIZE,
																890, Short.MAX_VALUE)
														.addContainerGap())
												.addGroup(groupLayout
														.createSequentialGroup().addGroup(groupLayout
																.createParallelGroup(Alignment.LEADING)
																.addGroup(
																		groupLayout.createSequentialGroup()
																				.addComponent(lblNewLabel).addGap(102)
																				.addComponent(cmbTimKiem,
																						GroupLayout.PREFERRED_SIZE, 151,
																						GroupLayout.PREFERRED_SIZE)
																				.addPreferredGap(
																						ComponentPlacement.RELATED)
																				.addComponent(txtTimPhong,
																						GroupLayout.PREFERRED_SIZE, 195,
																						GroupLayout.PREFERRED_SIZE)
																				.addPreferredGap(
																						ComponentPlacement.RELATED, 115,
																						Short.MAX_VALUE)
																				.addComponent(
																						btnXemDanhSachHD,
																						GroupLayout.PREFERRED_SIZE, 219,
																						GroupLayout.PREFERRED_SIZE))
																.addGroup(groupLayout.createSequentialGroup()
																		.addComponent(panel, GroupLayout.PREFERRED_SIZE,
																				295, GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(ComponentPlacement.UNRELATED)
																		.addGroup(groupLayout
																				.createParallelGroup(Alignment.LEADING)
																				.addGroup(groupLayout
																						.createSequentialGroup()
																						.addComponent(scrollPane_dichvu,
																								GroupLayout.DEFAULT_SIZE,
																								274, Short.MAX_VALUE)
																						.addPreferredGap(
																								ComponentPlacement.RELATED)
																						.addComponent(
																								panel_1,
																								GroupLayout.PREFERRED_SIZE,
																								307,
																								GroupLayout.PREFERRED_SIZE))
																				.addGroup(groupLayout
																						.createSequentialGroup()
																						.addComponent(lblPhong,
																								GroupLayout.PREFERRED_SIZE,
																								99,
																								GroupLayout.PREFERRED_SIZE)
																						.addPreferredGap(
																								ComponentPlacement.RELATED)
																						.addComponent(lblDonGiaPhong,
																								GroupLayout.PREFERRED_SIZE,
																								155,
																								GroupLayout.PREFERRED_SIZE)
																						.addGap(32)
																						.addComponent(lblKhachHang,
																								GroupLayout.PREFERRED_SIZE,
																								157,
																								GroupLayout.PREFERRED_SIZE))
																				.addGroup(groupLayout
																						.createSequentialGroup()
																						.addComponent(lblNewLabel_3,
																								GroupLayout.PREFERRED_SIZE,
																								133,
																								GroupLayout.PREFERRED_SIZE)
																						.addPreferredGap(
																								ComponentPlacement.UNRELATED)
																						.addComponent(lblTienDichVu,
																								GroupLayout.PREFERRED_SIZE,
																								126,
																								GroupLayout.PREFERRED_SIZE)))))
														.addGap(8))
												.addGroup(
														groupLayout.createSequentialGroup().addComponent(lblNewLabel_2)
																.addContainerGap(755, Short.MAX_VALUE)))));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(cmbTimKiem, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel)
										.addComponent(txtTimPhong, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addGap(26).addComponent(lblNewLabel_2))
						.addComponent(btnXemDanhSachHD, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(scrollPane_phongSuDung, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
				.addGap(34)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblPhong)
						.addComponent(lblKhachHang)
						.addComponent(lblDonGiaPhong, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup().addGap(1).addComponent(panel,
								GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(scrollPane_dichvu, GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTienDichVu, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		JLabel lblNewLabel_5 = new JLabel("Nh\u00E2n vi\u00EAn l\u1EADp h\u00F3a \u0111\u01A1n:");
		lblNewLabel_5.setFont(tahoma13Bold);

		txtNhanVienLap = new JTextField();
		txtNhanVienLap.setFont(tahoma13);
		txtNhanVienLap.setEditable(false);
		txtNhanVienLap.setColumns(10);

		btnThanhToan = new JButton("THANH TO\u00C1N");
		btnThanhToan.setBorder(null);
		btnThanhToan.setFocusPainted(false);
		btnThanhToan.setFocusTraversalKeysEnabled(false);
		btnThanhToan.setFocusable(false);
		btnThanhToan.setFont(tahoma15Bold);

		lblTongTienHoaDon = new JLabel("Tổng tiền:");
		lblTongTienHoaDon.setForeground(Color.RED);
		lblTongTienHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 16));

		lblNewLabel_1 = new JLabel("Tiền khách đưa:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));

		txtTienKhachDua = new JTextField();
		txtTienKhachDua.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				int row = tblPhongDangSuDung.getSelectedRow();
				double tongTien = tinhTongTien(row);
				double tienKhachDua = Double.parseDouble(txtTienKhachDua.getText());
				double tienThua = tienKhachDua - tongTien;
				
				Locale locale = new Locale("vi", "VN");
				NumberFormat format = NumberFormat.getCurrencyInstance(locale);
				
				lblTienThua.setText(format.format(tienThua));
			}
		});
		txtTienKhachDua.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtTienKhachDua.setColumns(10);

		JLabel lblT = new JLabel("Tiền thừa:");
		lblT.setFont(new Font("Tahoma", Font.PLAIN, 14));

		lblTienThua = new JLabel("");
		lblTienThua.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup().addComponent(lblNewLabel_5)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(txtNhanVienLap, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))
								.addComponent(lblTongTienHoaDon))
						.addContainerGap())
				.addGroup(gl_panel_1.createSequentialGroup().addGap(75)
						.addComponent(btnThanhToan, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE).addGap(63))
				.addGroup(gl_panel_1.createSequentialGroup().addContainerGap().addComponent(lblNewLabel_1)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(txtTienKhachDua, GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
						.addContainerGap())
				.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup().addContainerGap()
						.addComponent(lblT, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblTienThua, GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE).addContainerGap()));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_1
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_5).addComponent(
						txtNhanVienLap, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addGap(18).addComponent(lblTongTienHoaDon).addGap(18)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_1)
						.addComponent(txtTienKhachDua, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblT, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTienThua, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
				.addComponent(btnThanhToan, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE).addGap(42)));
		panel_1.setLayout(gl_panel_1);

		JLabel lblNewLabel_4 = new JLabel("Lo\u1EA1i th\u1EF1c ph\u1EA9m:");
		lblNewLabel_4.setFont(tahoma13Bold);

		cmbLoaiThucPham = new JComboBox<String>();
		cmbLoaiThucPham.setFont(tahoma13);

		JLabel lblNewLabel_4_1 = new JLabel("T\u00EAn th\u1EF1c ph\u1EA9m:");
		lblNewLabel_4_1.setFont(tahoma13Bold);

		cmbTenThucPham = new JComboBox<String>();
		cmbTenThucPham.setFont(tahoma13);

		JLabel lblNewLabel_4_1_1 = new JLabel("S\u1ED1 l\u01B0\u1EE3ng:");
		lblNewLabel_4_1_1.setFont(tahoma13Bold);

		txtSoLuong = new JTextField();
		txtSoLuong.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtSoLuong.setBorder(BorderFactory.createLineBorder(hoverColor));
				txtSoLuong.setForeground(hovertextColor);
			}

			@Override
			public void focusLost(FocusEvent e) {
				txtSoLuong.setBorder(BorderFactory.createLineBorder(seperatorColor));
				txtSoLuong.setForeground(blackColor);
			}
		});
		txtSoLuong.setFont(tahoma13);
		txtSoLuong.setColumns(10);

		btnThemThucPham = new JButton("Th\u00EAm th\u1EF1c ph\u1EA9m");
		btnThemThucPham.setBorder(null);
		btnThemThucPham.setFocusTraversalKeysEnabled(false);
		btnThemThucPham.setFocusPainted(false);
		btnThemThucPham.setFocusable(false);
		btnThemThucPham.setIcon(new ImageIcon(getClass().getResource("/images/icons8-add-20.png")));
		btnThemThucPham.setFont(tahoma13Bold);

		btnXoa = new JButton("X\u00F3a");
		btnXoa.setBorder(null);
		btnXoa.setFocusTraversalKeysEnabled(false);
		btnXoa.setFocusPainted(false);
		btnXoa.setIcon(new ImageIcon(getClass().getResource("/images/icons8-remove-24.png")));
		btnXoa.setFont(tahoma13Bold);
		btnXoa.setFocusable(false);

		btnLamMoi = new JButton("L\u00E0m m\u1EDBi");
		btnLamMoi.setBorder(null);
		btnLamMoi.setFocusPainted(false);
		btnLamMoi.setFocusTraversalKeysEnabled(false);
		btnLamMoi.setIcon(new ImageIcon(getClass().getResource("/images/icons8-refresh-16.png")));
		btnLamMoi.setFont(tahoma13Bold);
		btnLamMoi.setFocusable(false);

		btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.setIcon(new ImageIcon(getClass().getResource("/images/icons8-pencil-16.png")));
		btnCapNhat.setBorder(null);
		btnCapNhat.setFocusPainted(false);
		btnCapNhat.setFocusTraversalKeysEnabled(false);
		btnCapNhat.setFont(tahoma13Bold);
		btnCapNhat.setFocusable(false);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup().addContainerGap().addComponent(btnLamMoi,
										GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
												.addComponent(lblNewLabel_4_1, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(lblNewLabel_4, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addComponent(cmbLoaiThucPham, 0, 166, Short.MAX_VALUE)
												.addComponent(cmbTenThucPham, 0, 166, Short.MAX_VALUE)))
								.addGroup(gl_panel.createSequentialGroup()
										.addComponent(lblNewLabel_4_1_1, GroupLayout.PREFERRED_SIZE, 104,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(txtSoLuong, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup().addContainerGap()
										.addComponent(btnXoa, GroupLayout.PREFERRED_SIZE, 131,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnCapNhat,
												GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup().addContainerGap()
										.addComponent(btnThemThucPham, GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)))
						.addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_4).addComponent(
						cmbLoaiThucPham, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cmbTenThucPham, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_4_1))
				.addGap(18)
				.addGroup(
						gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_4_1_1, GroupLayout.PREFERRED_SIZE, 16,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(txtSoLuong, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
				.addGap(23).addComponent(btnThemThucPham, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCapNhat, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnXoa, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(btnLamMoi, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE).addGap(21)));
		panel.setLayout(gl_panel);

		tblDichVu = new JTable();
		tblDichVu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// set 2 cái combo nó tắt
				int row = tblDichVu.getSelectedRow();
				if (row >= 0) {
					SanPhamDAO sanPhamDao = new SanPhamDAO();
					String tenSanPham = tblDichVu.getValueAt(row, 0).toString();
					SanPham sanPham = sanPhamDao.getSanPhamTheoTen(tenSanPham);

					cmbLoaiThucPham.setSelectedItem(sanPham.getLoaiSanPham().getTenLoaiSP());
					cmbTenThucPham.setSelectedItem(sanPham.getTenSanPham());
					txtSoLuong.setText(tblDichVu.getValueAt(row, 1).toString());
				}
			}
		});
		tblDichVu.setFont(tahoma13);
		tblDichVu.setRowHeight(28);
		tblDichVu.setAutoCreateRowSorter(true);
		tblDichVu.getTableHeader().setFont(tahoma14Bold);
		tblDichVu.getTableHeader().setBackground(tableHeaderColor);
		tblDichVu.getTableHeader().setForeground(whiteColor);
		// chỉ cho khách hàng được chọn 1 dòng ở 1 thời điểm
		tblDichVu.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_dichvu.setViewportView(tblDichVu);

		tblPhongDangSuDung = new JTable();
		tblPhongDangSuDung.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tblPhongDangSuDung.getSelectedRow();
				if (row >= 0) {
					dfModelDichVu.setRowCount(0);

					lblPhong.setText((String) tblPhongDangSuDung.getValueAt(row, 2));
					lblDonGiaPhong.setText("Đơn giá phòng: " + tblPhongDangSuDung.getValueAt(row, 4));
					lblKhachHang.setText((String) tblPhongDangSuDung.getValueAt(row, 6));

					loadDataCTHD();

					hienThiTongTien(row);
				}
			}
		});
		tblPhongDangSuDung.setFont(tahoma16);
		tblPhongDangSuDung.setRowHeight(28);
		tblPhongDangSuDung.setAutoCreateRowSorter(true);
		tblPhongDangSuDung.getTableHeader().setFont(tahoma16Bold);
		tblPhongDangSuDung.getTableHeader().setBackground(tableHeaderColor);
		tblPhongDangSuDung.getTableHeader().setForeground(whiteColor);
		// chỉ cho khách hàng được chọn 1 dòng ở 1 thời điểm
		tblPhongDangSuDung.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_phongSuDung.setViewportView(tblPhongDangSuDung);

		btnCapNhat.setBackground(mainColor);
		btnCapNhat.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnCapNhat.setBackground(hoverColor);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnCapNhat.setBackground(mainColor);
			}
		});
		btnCapNhat.setForeground(whiteColor);

		btnLamMoi.setBackground(mainColor);
		btnLamMoi.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnLamMoi.setBackground(hoverColor);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnLamMoi.setBackground(mainColor);
			}
		});
		btnLamMoi.setForeground(whiteColor);

		btnThanhToan.setBackground(mainColor);
		btnThanhToan.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnThanhToan.setBackground(hoverColor);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnThanhToan.setBackground(mainColor);
			}
		});
		btnThanhToan.setForeground(whiteColor);

		btnThemThucPham.setBackground(mainColor);
		btnThemThucPham.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnThemThucPham.setBackground(hoverColor);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnThemThucPham.setBackground(mainColor);
			}
		});
		btnThemThucPham.setForeground(whiteColor);

		btnXemDanhSachHD.setBackground(mainColor);
		btnXemDanhSachHD.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnXemDanhSachHD.setBackground(hoverColor);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnXemDanhSachHD.setBackground(mainColor);
			}
		});
		btnXemDanhSachHD.setForeground(whiteColor);

		btnXoa.setBackground(mainColor);
		btnXoa.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnXoa.setBackground(hoverColor);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnXoa.setBackground(mainColor);
			}
		});
		btnXoa.setForeground(whiteColor);

		setLayout(groupLayout);
		lblTienThua.setForeground(Color.RED);

		txtNhanVienLap.setText(ShareData.taiKhoanDangNhap.getNhanVien().getHoTen());

		cmbTimKiem.addItem("Tìm theo mã phòng");
		cmbTimKiem.addItem("Tìm theo tên phòng");
		cmbTimKiem.addItem("Tìm theo loại phòng");
		cmbTimKiem.addItem("Tìm theo đơn giá");
		cmbTimKiem.addItem("Tìm theo thời gian vào");
		cmbTimKiem.addItem("Tìm theo khách hàng");

		initTablePhongSuDung();
		initTableDichVu();
		loadTablePhongDangSuDungTheoTrangThai("Chưa Thanh Toán");
		loadDataToCmbLoaiSanPham();
		loadDataToCmbTenSanPham();

		cmbLoaiThucPham.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnCapNhat.addActionListener(this);
		btnThemThucPham.addActionListener(this);
		btnXemDanhSachHD.addActionListener(this);
		btnXoa.addActionListener(this);
		btnThanhToan.addActionListener(this);
	}

	private void initTablePhongSuDung() {
		dfModelPhongSuDung = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		dfModelPhongSuDung.setColumnIdentifiers(new String[] { "Mã Đơn Đặt", "Mã phòng", "Tên phòng", "Loại phòng",
				"Đơn giá", "Thời gian vào", "Khách hàng" });
		tblPhongDangSuDung.setModel(dfModelPhongSuDung);
	}

	private void initTableDichVu() {
		dfModelDichVu = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		dfModelDichVu.setColumnIdentifiers(new String[] { "Sản phẩm", "Số lượng", "Đơn giá", "Thành tiền" });
		tblDichVu.setModel(dfModelDichVu);
	}
	
	/**
	 * lay du lieu tu co so du lieu dua vao table phong dang su dung dua theo trang thai cua don dat phong
	 */
	private void loadTablePhongDangSuDungTheoTrangThai(String trangThaiDon) {
		String sql = "SELECT Don_Dat_Phong.MaDatPhong,Don_Dat_Phong.MaPhong, Phong.TenPhong, Loai_Phong.TenLoaiPhong, Loai_Phong.GiaPhong, Don_Dat_Phong.ThoiGianVao, Khach_Hang.TenKhachHang\r\n"
				+ "FROM     Don_Dat_Phong INNER JOIN\r\n"
				+ "                  Khach_Hang ON Don_Dat_Phong.MaKhachHang = Khach_Hang.MaKhachHang INNER JOIN\r\n"
				+ "                  Phong ON Don_Dat_Phong.MaPhong = Phong.MaPhong INNER JOIN\r\n"
				+ "                  Loai_Phong ON Phong.MaLoaiPhong = Loai_Phong.MaLoaiPhong\r\n"
				+ "Where TrangThaiDon = ?";
		Connection con = MSSQLConnection.getJDBCConnection();
		PreparedStatement prepareStatement = null;

		try {
			prepareStatement = con.prepareStatement(sql);
			prepareStatement.setString(1, trangThaiDon);
			ResultSet rs = prepareStatement.executeQuery();
			while (rs.next()) {
				Date thoiGianVao = rs.getTimestamp("ThoiGianVao");
				dfModelPhongSuDung.addRow(new Object[] { "DP" + rs.getString("MaDatPhong"),
						"MP" + rs.getString("MaPhong"), rs.getString("TenPhong"), rs.getString("TenLoaiPhong"),
						rs.getDouble("GiaPhong"), thoiGianVao, rs.getString("TenKhachHang") });
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	/**
	 * lay du lieu tu co so du lieu dua vao combobox loai san pham
	 */
	private void loadDataToCmbLoaiSanPham() {
		LoaiSanPhamDAO loaiSanPhamDao = new LoaiSanPhamDAO();
		List<LoaiSanPham> dsLoaiSanPham = loaiSanPhamDao.getDanhSachLoaiSanPham();
		for (LoaiSanPham loaiSanPham : dsLoaiSanPham) {
			cmbLoaiThucPham.addItem(loaiSanPham.getTenLoaiSP());
		}
	}

	private void loadDataToCmbTenSanPham() {
		SanPhamDAO sanPhamDAO = new SanPhamDAO();
		List<SanPham> sp = sanPhamDAO.getDanhSachSanPham();
		for (SanPham sanPham : sp) {
			cmbTenThucPham.addItem(sanPham.getTenSanPham());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(cmbLoaiThucPham)) {
			SanPhamDAO sanPhamDao = new SanPhamDAO();
			List<String> dsTenSanPham = sanPhamDao.getSanPhamTheoLoai((String) cmbLoaiThucPham.getSelectedItem());
			cmbTenThucPham.removeAllItems();
			for (String tenSanPham : dsTenSanPham) {
				cmbTenThucPham.addItem(tenSanPham);
			}
		}

		if (o.equals(btnLamMoi)) {
			lamMoiText();

			cmbTenThucPham.removeAllItems();
			loadDataToCmbTenSanPham();

			dfModelDichVu.setRowCount(0);
			dfModelPhongSuDung.setRowCount(0);
			loadTablePhongDangSuDungTheoTrangThai("Chưa thanh toán");

			tblPhongDangSuDung.setRowSorter(null);
		}

		if (o.equals(btnThemThucPham)) {
			// còn kiểm tra dữ liệu đầu vào và thêm không trùng sản phẩm
			int row = tblPhongDangSuDung.getSelectedRow();

			if (row >= 0) {
				HoaDonDao hoaDonDao = new HoaDonDao();
				ChiTietHoaDon cthd = new ChiTietHoaDon();
				PhongDAO phongDao = new PhongDAO();
				ChiTietHoaDonDao cthdDao = new ChiTietHoaDonDao();

				StringBuilder sb = new StringBuilder();
				dataValidateThemTP(sb);

				if (sb.length() > 0) {
					MessageDialogHelpers.showErrorDialog(tblPhongDangSuDung, "Lỗi", sb.toString());
				}

				String maHoaDon = hoaDonDao.getMaHoaDon(lblKhachHang.getText());
				HoaDon hoaDon = hoaDonDao.getHoaDonTheoMa(maHoaDon);

				cthd.setHoaDon(hoaDon);
				cthd.setPhong(phongDao.getPhongTheoMa(tblPhongDangSuDung.getValueAt(row, 1).toString()));
				SanPham sanPham = createSanPham();
				cthd.setSanPham(sanPham);
				cthd.setSoLuong(Integer.parseInt(txtSoLuong.getText()));

				if (cthdDao.checkExist(maHoaDon, sanPham.getTenSanPham())) {
					MessageDialogHelpers.showErrorDialog(tblDichVu, "Cảnh báo", "Sản phẩm đã tồn tại");
					return;
				} else {
					if (cthdDao.addChiTietHoaDon(cthd)) {
						MessageDialogHelpers.showMessageDialog(tblDichVu, "Thông báo", "Thêm thành công");
						dfModelDichVu.setRowCount(0);
						loadDataCTHD();
						txtSoLuong.setText("");

						hienThiTongTien(row);
					} else {
						MessageDialogHelpers.showErrorDialog(tblDichVu, "Lỗi", "Thêm không thành công");
					}
				}
			} else {
				MessageDialogHelpers.showErrorDialog(tblPhongDangSuDung, "Lỗi", "Phải chọn 1 phòng trước khi thêm");
			}

		}

		if (o.equals(btnCapNhat)) {
			// biểu thức chính quy cho số lượng
			int row = tblDichVu.getSelectedRow();

			if (row >= 0) {
				ChiTietHoaDonDao chiTietHoaDonDao = new ChiTietHoaDonDao();
				HoaDonDao hoaDonDao = new HoaDonDao();

				StringBuilder sb = new StringBuilder();
				dataValidateThemTP(sb);

				if (sb.length() > 0) {
					MessageDialogHelpers.showErrorDialog(tblPhongDangSuDung, "Lỗi", sb.toString());
				}

				String maHoaDon = hoaDonDao.getMaHoaDon(lblKhachHang.getText());
				String tenSanPham = tblDichVu.getValueAt(row, 0).toString();

				if (chiTietHoaDonDao.updateSoLuongCTHD(maHoaDon, Integer.parseInt(txtSoLuong.getText()), tenSanPham)) {
					MessageDialogHelpers.showMessageDialog(tblDichVu, "Thông báo", "Cập nhật thành công");

					dfModelDichVu.setRowCount(0);
					loadDataCTHD();
					txtSoLuong.setText("");

					int rowPhong = tblPhongDangSuDung.getSelectedRow();
					hienThiTongTien(rowPhong);
				} else {
					MessageDialogHelpers.showErrorDialog(tblDichVu, "Lỗi", "Cập nhật không thành công");
				}

			} else {
				MessageDialogHelpers.showErrorDialog(tblDichVu, "Lỗi", "Phải chọn 1 sản phẩm trước khi cập nhật");
			}
		}

		if (o.equals(btnXoa)) {
			// biểu thức chính quy cho số lượng
			int row = tblDichVu.getSelectedRow();

			if (row >= 0) {
				ChiTietHoaDonDao chiTietHoaDonDao = new ChiTietHoaDonDao();
				HoaDonDao hoaDonDao = new HoaDonDao();

				String maHoaDon = hoaDonDao.getMaHoaDon(lblKhachHang.getText());
				String tenSanPham = tblDichVu.getValueAt(row, 0).toString();

				if (chiTietHoaDonDao.deleteCTHD(maHoaDon, tenSanPham)) {
					MessageDialogHelpers.showMessageDialog(tblDichVu, "Thông báo", "Xóa thành công");

					dfModelDichVu.setRowCount(0);
					tblPhongDangSuDung.setRowSorter(null);
					loadDataCTHD();

					int rowPhong = tblPhongDangSuDung.getSelectedRow();
					hienThiTongTien(rowPhong);
				} else {
					MessageDialogHelpers.showErrorDialog(tblDichVu, "Lỗi", "Xóa không thành công");
				}

			} else {
				MessageDialogHelpers.showErrorDialog(tblDichVu, "Lỗi", "Phải chọn 1 sản phẩm trước khi xóa");
			}
		}

		if (o.equals(btnThanhToan)) {
			DonDatPhongDAO donDatPhongDAO = new DonDatPhongDAO();
			PhongDAO phongDAO = new PhongDAO();
			HoaDonDao hoaDonDao = new HoaDonDao();
			int row = tblPhongDangSuDung.getSelectedRow();

			if (row >= 0) {
				// lay du lieu de tinh
				Date thoiGianVao = (Date) tblPhongDangSuDung.getValueAt(row, 5);
				int gioVao = thoiGianVao.getHours();
				int phutVao = thoiGianVao.getMinutes();
				int gioRa = java.time.LocalTime.now().getHour();
				int phutRa = java.time.LocalTime.now().getMinute();
				double donGia = (double) tblPhongDangSuDung.getValueAt(row, 4);
				double tienDichVu = 0;
				if (lblTienDichVu.getText().equals("")) {
					tienDichVu = 0;
				} else {
					tienDichVu = Double.parseDouble(lblTienDichVu.getText().replaceAll(" VND", ""));
				}

				// tinh theo cong thuc
				int tongGio = gioRa - gioVao;
				int tongPhut = phutRa - phutVao;
				int thoiGianSuDung = tongGio * 60 + tongPhut;
				double tienSuDungPhong = thoiGianSuDung * (donGia / 60);
				double tongTien = tienSuDungPhong + tienDichVu;

				// format tien te va ngay thang
				Locale locale = new Locale("vi", "VN");
				NumberFormat format = NumberFormat.getCurrencyInstance(locale);
				String pattern = "dd-MM-yyyy hh:mm a";
				SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
				
				//confirm
				int isThanhToan = MessageDialogHelpers.showConfirmDialog(tblPhongDangSuDung, "Cảnh báo",
						"Bạn có chắc muốn thanh toán cho phòng này");
				if (isThanhToan == JOptionPane.NO_OPTION) {
					return;
				} else if (isThanhToan == JOptionPane.CLOSED_OPTION) {
					return;
				}

				// hien thi dialog khi thanh toan
				DialogThanhToanThanhCong thanhToanThanhCong = new DialogThanhToanThanhCong();
				thanhToanThanhCong.setVisible(true);

				String maHoaDon = hoaDonDao.getMaHoaDon(lblKhachHang.getText());

				// dua du lieu vao dialog
				String thoiGianRa = dateFormat.format(new Date());
				String thoiGianVaoFormated = dateFormat.format(thoiGianVao);
				thanhToanThanhCong.putTextNow(lblKhachHang.getText(), maHoaDon, thoiGianRa, thoiGianVaoFormated,
						thoiGianSuDung + " phút", format.format(tienDichVu), format.format(tongTien),
						format.format(donGia), format.format(tienSuDungPhong));

				thanhToanThanhCong.loadDataCTHD(lblKhachHang.getText());

				// update trang thai don va tinh trang phong
				// update tong tien vao hoa don
				donDatPhongDAO.updateTrangThaiDonDat_DaThanhToan(tblPhongDangSuDung.getValueAt(row, 0).toString());
				Phong p = phongDAO.getPhongTheoMa(tblPhongDangSuDung.getValueAt(row, 1).toString());
				p.setTrangThai("Trống");
				phongDAO.updatePhong(p);
				hoaDonDao.updateTongTienHoaDon(tongTien, maHoaDon);

			} else {
				MessageDialogHelpers.showErrorDialog(tblPhongDangSuDung, "Cảnh báo", "Chưa chọn phòng để thanh toán");
			}
			// load lai du lieu table
			dfModelPhongSuDung.setRowCount(0);
			loadTablePhongDangSuDungTheoTrangThai("Chờ Thanh Toán");
			dfModelDichVu.setRowCount(0);
		}

		if (o.equals(btnXemDanhSachHD)) {
			danhSachHoaDon = new DialogDanhSachHoaDon();
			danhSachHoaDon.setVisible(true);
		}

	}

	private void lamMoiText() {
		txtSoLuong.setText("");
		lblDonGiaPhong.setText("Đơn giá phòng:");
		lblKhachHang.setText("Khách hàng:");
		lblPhong.setText("Phòng:");
		lblTienDichVu.setText("");
		lblTongTienHoaDon.setText("");
	}

	private SanPham createSanPham() {
		String tenSanPham = cmbTenThucPham.getSelectedItem().toString();
		SanPhamDAO sanPhamDao = new SanPhamDAO();

		SanPham sanPham = sanPhamDao.getSanPhamTheoTen(tenSanPham);
		return sanPham;
	}
	
	/**
	 * lay du lieu tu co so du lieu dua vao table chi tiet hoa don
	 */
	private void loadDataCTHD() {
		HoaDonDao hoaDonDao = new HoaDonDao();
		ChiTietHoaDonDao cthdDao = new ChiTietHoaDonDao();
		double tongTien = 0;

		String maHoaDon = hoaDonDao.getMaHoaDon(lblKhachHang.getText());

		List<ChiTietHoaDon> dsCTHD = cthdDao.getdsCTHD(maHoaDon);
		for (ChiTietHoaDon cthd : dsCTHD) {
			dfModelDichVu.addRow(new Object[] { cthd.getSanPham().getTenSanPham(), cthd.getSoLuong(),
					cthd.getSanPham().getDonGia(), cthd.getThanhTien() });
			tongTien += cthd.getThanhTien();
			lblTienDichVu.setText(tongTien + " VND");
		}
	}

	private void dataValidateThemTP(StringBuilder sb) {
		DataValidator.validateEmpty(txtSoLuong, sb, "Phải nhập số lượng trước");
		DataValidator.validateDonGia(txtSoLuong, sb, "Không được nhập kí tự, số lượng phải lớn hơn hoặc bằng 0");
	}
	
	/**
	 * tinh tong tien ban duoc
	 * @param row
	 * @return tongTien
	 */
	private double tinhTongTien(int row) {
		// lay du lieu de tinh
		Date thoiGianVao = (Date) tblPhongDangSuDung.getValueAt(row, 5);
		int gioVao = thoiGianVao.getHours();
		int phutVao = thoiGianVao.getMinutes();
		int gioRa = java.time.LocalTime.now().getHour();
		int phutRa = java.time.LocalTime.now().getMinute();
		double donGia = (double) tblPhongDangSuDung.getValueAt(row, 4);
		double tienDichVu = 0;
		if (lblTienDichVu.getText().equals("")) {
			tienDichVu = 0;
		} else {
			tienDichVu = Double.parseDouble(lblTienDichVu.getText().replaceAll(" VND", ""));
		}

		// tinh theo cong thuc
		int tongGio = gioRa - gioVao;
		int tongPhut = phutRa - phutVao;
		int thoiGianSuDung = tongGio * 60 + tongPhut;
		double tienSuDungPhong = thoiGianSuDung * (donGia / 60);
		double tongTien = tienSuDungPhong + tienDichVu;
		return tongTien;
	}
	
	/**
	 * show tong tien ban duoc
	 * @param row
	 */
	private void hienThiTongTien(int row) {
		Locale locale = new Locale("vi", "VN");
		NumberFormat format = NumberFormat.getCurrencyInstance(locale);
		double tongTien = tinhTongTien(row);
		lblTongTienHoaDon.setText("Tổng tiền: " + String.valueOf(format.format(tongTien)));
	}
	
	/**
	 * Tim kiem theo keyword
	 * Tim kiem dua vao bieu thuc chinh quy
	 * @param str
	 */
	public void search(String str) {
		dfModelPhongSuDung = (DefaultTableModel) tblPhongDangSuDung.getModel();
		TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(dfModelPhongSuDung);
		tblPhongDangSuDung.setRowSorter(trs);

		if (cmbTimKiem.getSelectedItem().toString().equals("Tìm theo mã phòng")) {
			trs.setRowFilter(RowFilter.regexFilter(str, 1));
		}

		if (cmbTimKiem.getSelectedItem().toString().equals("Tìm theo tên phòng")) {
			trs.setRowFilter(RowFilter.regexFilter(str, 2));
		}

		if (cmbTimKiem.getSelectedItem().toString().equals("Tìm theo loại phòng")) {
			trs.setRowFilter(RowFilter.regexFilter(str, 3));
		}

		if (cmbTimKiem.getSelectedItem().toString().equals("Tìm theo đơn giá")) {
			trs.setRowFilter(RowFilter.regexFilter(str, 4));
		}

		if (cmbTimKiem.getSelectedItem().toString().equals("Tìm theo thời gian vào")) {
			trs.setRowFilter(RowFilter.regexFilter(str, 5));
		}

		if (cmbTimKiem.getSelectedItem().toString().equals("Tìm theo khách hàng")) {
			trs.setRowFilter(RowFilter.regexFilter(str, 6));
		}
	}
}
