/**
 * Nguyễn Hồng Quân
 * Ngày tạo: 16/11/2021
 * Giao diện dùng để đặt phòng 
 */
package application;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import dao.KhachHangDAO;
import dao.PhongDAO;
import dao.DonDatPhongDAO;
import dao.HoaDonDao;
import entity.KhachHang;
import entity.Phong;
import entity.DonDatPhong;
import entity.HoaDon;
import helpers.MessageDialogHelpers;
import helpers.ShareData;

import javax.swing.JTextField;
import javax.swing.RowFilter;

import javax.swing.JButton;
import javax.swing.ImageIcon;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.JComboBox;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PnlDatPhong extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JTextField txt_MaKH;
	private static JTextField txt_TenKH;
	private static JTextField txt_LoaiKH;
	private static JTextField txt_TimSDT;
	private JTable tbl_DonDatPhong;
	private JTable tbl_DSPhong;

	private DefaultTableModel dfDanhSachPhong;
	private JButton btn_Tim;
	private JButton btn_ThueNgay;
	private JButton btn_LamMoi;
	private Component mainFrame;
	private DefaultTableModel dfDanhSachDonDatPhong;
	private DialogChuaCoKhachHang tb;
	private JComboBox<String> cmb_LocPhongTheoTrangThai;
	private JTextField txtTimDon;
	private JComboBox<String> cmbTimDon;
	private JButton btn_DatTruoc;
	private DialogDatTruoc dialogDatTruoc;

	/**
	 * Create the panel.
	 */
	public PnlDatPhong() {

		/** set color **/
		Color whiteColor = new Color(255, 255, 255);
		Color mainColor = new Color(88, 159, 177);
		Color hoverColor = new Color(121, 178, 192);
		Color tableHeaderColor = new Color(42, 143, 178);

		Font tahoma13 = new Font("Tahoma", Font.PLAIN, 13);
		Font tahomaBold13 = new Font("Tahoma", Font.BOLD, 13);
		Font tahoma14 = new Font("Tahoma", Font.PLAIN, 14);
		Font tahoma16 = new Font("Tahoma", Font.PLAIN, 16);
		Font tahoma16Bold = new Font("Tahoma", Font.BOLD, 16);
		Font tahoma18 = new Font("Tahoma", Font.PLAIN, 18);

		JPanel pnl_DatPhong = new JPanel();
		pnl_DatPhong.setBackground(whiteColor);

		JPanel pnl_DSDonDat = new JPanel();
		pnl_DSDonDat.setBackground(whiteColor);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(pnl_DSDonDat, GroupLayout.DEFAULT_SIZE, 909, Short.MAX_VALUE)
				.addComponent(pnl_DatPhong, GroupLayout.DEFAULT_SIZE, 909, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(pnl_DatPhong, GroupLayout.PREFERRED_SIZE, 364, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(pnl_DSDonDat,
								GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		JScrollPane scrollPane_DSDonDatPhong = new JScrollPane();

		JLabel lblNewLabel_3 = new JLabel("Danh Sách Đơn Thuê Phòng");
		lblNewLabel_3.setFont(tahoma18);

		cmb_LocPhongTheoTrangThai = new JComboBox<String>();
		cmb_LocPhongTheoTrangThai.addItem("Đã Thanh Toán");
		cmb_LocPhongTheoTrangThai.addItem("Chưa Thanh Toán");
		cmb_LocPhongTheoTrangThai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DonDatPhongDAO donDatPhongDao = new DonDatPhongDAO();

				String trangThaiDon = cmb_LocPhongTheoTrangThai.getSelectedItem().toString();

				List<DonDatPhong> dsDonDatPhong = donDatPhongDao.getDanhSachDonDatPhong(trangThaiDon);

				dfDanhSachDonDatPhong.setRowCount(0);

				for (DonDatPhong ddp : dsDonDatPhong) {
					SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss - dd/MM/yyyy");
					String tgv = formatter.format(ddp.getThoiGianVao());
					dfDanhSachDonDatPhong.addRow(new Object[] { ddp.getMaDatPhong(), ddp.getKhachHang().getHoTenKH(),
							ddp.getKhachHang().getSoDienThoai(), ddp.getPhong().getTenPhong(),
							ddp.getPhong().getLoaiPhong().getTenLoaiPhong(), tgv, ddp.getTrangThaiDon() });
				}
			}
		});

		txtTimDon = new JTextField();
		txtTimDon.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String searchStr = txtTimDon.getText();
				search(searchStr);
			}
		});
		txtTimDon.setFont(tahoma14);
		txtTimDon.setColumns(10);

		JLabel lblNewLabel = new JLabel("");

		cmbTimDon = new JComboBox<String>();
		cmbTimDon.addItem("Tìm theo tên khách hàng");
		cmbTimDon.addItem("Tìm theo ngày đặt");
		cmbTimDon.addItem("Tìm theo tên phòng");
		cmbTimDon.addItem("Tìm theo số điện thoại");
		GroupLayout gl_pnl_DSDonDat = new GroupLayout(pnl_DSDonDat);
		gl_pnl_DSDonDat.setHorizontalGroup(gl_pnl_DSDonDat.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnl_DSDonDat.createSequentialGroup().addContainerGap()
						.addGroup(gl_pnl_DSDonDat.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane_DSDonDatPhong, GroupLayout.DEFAULT_SIZE, 884, Short.MAX_VALUE)
								.addGroup(gl_pnl_DSDonDat.createSequentialGroup()
										.addGroup(gl_pnl_DSDonDat.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblNewLabel)
												.addGroup(gl_pnl_DSDonDat.createSequentialGroup()
														.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 236,
																GroupLayout.PREFERRED_SIZE)
														.addGap(18)
														.addComponent(cmbTimDon, GroupLayout.PREFERRED_SIZE, 181,
																GroupLayout.PREFERRED_SIZE)
														.addGap(18)))
										.addGap(18)
										.addComponent(txtTimDon, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
										.addGap(101).addComponent(cmb_LocPhongTheoTrangThai, GroupLayout.PREFERRED_SIZE,
												131, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap()));
		gl_pnl_DSDonDat
				.setVerticalGroup(gl_pnl_DSDonDat.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnl_DSDonDat.createSequentialGroup().addContainerGap()
								.addGroup(gl_pnl_DSDonDat.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblNewLabel_3)
										.addComponent(cmb_LocPhongTheoTrangThai, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel)
										.addComponent(cmbTimDon, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(txtTimDon, GroupLayout.PREFERRED_SIZE, 24,
												GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(scrollPane_DSDonDatPhong, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
								.addContainerGap()));

		tbl_DonDatPhong = new JTable();
		tbl_DonDatPhong.setFocusable(false);
		tbl_DonDatPhong.setFocusTraversalKeysEnabled(false);
		tbl_DonDatPhong.setBorder(null);

		scrollPane_DSDonDatPhong.setViewportView(tbl_DonDatPhong);
		tbl_DonDatPhong.setFont(tahoma16);
		tbl_DonDatPhong.setRowHeight(28);
		tbl_DonDatPhong.setAutoCreateRowSorter(true);
		tbl_DonDatPhong.getTableHeader().setFont(tahoma16Bold);
		tbl_DonDatPhong.getTableHeader().setBackground(tableHeaderColor);
		tbl_DonDatPhong.getTableHeader().setForeground(whiteColor);
		pnl_DSDonDat.setLayout(gl_pnl_DSDonDat);

		JLabel lblNewLabel_1 = new JLabel("M\u00E3 Kh\u00E1ch H\u00E0ng:");
		lblNewLabel_1.setFont(tahoma14);

		btn_Tim = new JButton("");
		btn_Tim.setBorder(null);
		btn_Tim.setFocusPainted(false);
		btn_Tim.setFocusTraversalKeysEnabled(false);
		btn_Tim.setFocusable(false);
		btn_Tim.setIcon(new ImageIcon(getClass().getResource("/images/icons8-search-16.png")));

		txt_MaKH = new JTextField();
		txt_MaKH.setEditable(false);
		txt_MaKH.setFont(tahoma13);
		txt_MaKH.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("T\u00EAn Kh\u00E1ch H\u00E0ng:");
		lblNewLabel_1_1.setFont(tahoma14);

		txt_TenKH = new JTextField();
		txt_TenKH.setEditable(false);
		txt_TenKH.setFont(tahoma13);
		txt_TenKH.setColumns(10);

		JLabel lblNewLabel_1_1_1 = new JLabel("Lo\u1EA1i Kh\u00E1ch H\u00E0ng:");
		lblNewLabel_1_1_1.setFont(tahoma14);

		txt_LoaiKH = new JTextField();
		txt_LoaiKH.setEditable(false);
		txt_LoaiKH.setFont(tahoma13);
		txt_LoaiKH.setColumns(10);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("S\u1ED1 \u0110i\u1EC7n Tho\u1EA1i:");
		lblNewLabel_1_1_1_1.setFont(tahoma14);

		txt_TimSDT = new JTextField();
		txt_TimSDT.setFont(tahoma13);
		txt_TimSDT.setColumns(10);

		JLabel lblNewLabel_1_1_1_2_1_1 = new JLabel("Ch\u1ECDn Ph\u00F2ng:");
		lblNewLabel_1_1_1_2_1_1.setFont(tahoma14);

		JScrollPane scrollPane_DSPhong = new JScrollPane();

		btn_ThueNgay = new JButton("Thuê Ngay");
		btn_ThueNgay.setForeground(Color.WHITE);
		btn_ThueNgay.setBorder(null);
		btn_ThueNgay.setFocusPainted(false);
		btn_ThueNgay.setFocusTraversalKeysEnabled(false);
		btn_ThueNgay.setFocusable(false);
		btn_ThueNgay.setFont(tahomaBold13);
		btn_ThueNgay.setIcon(new ImageIcon(getClass().getResource("/images/icons8-add-20.png")));

		btn_LamMoi = new JButton("L\u00E0m M\u1EDBi");
		btn_LamMoi.setForeground(Color.WHITE);
		btn_LamMoi.setBorder(null);
		btn_LamMoi.setFocusPainted(false);
		btn_LamMoi.setFocusTraversalKeysEnabled(false);
		btn_LamMoi.setFocusable(false);
		btn_LamMoi.setFont(tahomaBold13);
		btn_LamMoi.setIcon(new ImageIcon(getClass().getResource("/images/icons8-refresh-16.png")));

		JLabel lblNewLabel_2 = new JLabel("Đặt Phòng");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		btn_DatTruoc = new JButton("Đặt Trước");
		btn_DatTruoc.setFocusable(false);
		btn_DatTruoc.setFocusTraversalKeysEnabled(false);
		btn_DatTruoc.setFocusPainted(false);
		btn_DatTruoc.setBorder(null);
		btn_DatTruoc.setBackground(mainColor);
		btn_DatTruoc.setForeground(whiteColor);
		btn_DatTruoc.setFont(tahomaBold13);
		btn_DatTruoc.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btn_DatTruoc.setBackground(hoverColor);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btn_DatTruoc.setBackground(mainColor);
			}
		});
		GroupLayout gl_pnl_DatPhong = new GroupLayout(pnl_DatPhong);
		gl_pnl_DatPhong.setHorizontalGroup(
			gl_pnl_DatPhong.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnl_DatPhong.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnl_DatPhong.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnl_DatPhong.createSequentialGroup()
							.addGroup(gl_pnl_DatPhong.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1_1)
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1_1_1_2_1_1, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_pnl_DatPhong.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnl_DatPhong.createSequentialGroup()
									.addGroup(gl_pnl_DatPhong.createParallelGroup(Alignment.LEADING)
										.addComponent(txt_TenKH, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
										.addComponent(txt_MaKH, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE))
									.addGap(18)
									.addGroup(gl_pnl_DatPhong.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lblNewLabel_1_1_1_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblNewLabel_1_1_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_pnl_DatPhong.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_pnl_DatPhong.createSequentialGroup()
											.addComponent(txt_TimSDT, GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
											.addGap(18)
											.addComponent(btn_Tim, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
										.addComponent(txt_LoaiKH, GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)))
								.addComponent(scrollPane_DSPhong, GroupLayout.DEFAULT_SIZE, 694, Short.MAX_VALUE))
							.addGap(18))
						.addGroup(gl_pnl_DatPhong.createSequentialGroup()
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_pnl_DatPhong.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_pnl_DatPhong.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btn_DatTruoc, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnl_DatPhong.createParallelGroup(Alignment.LEADING)
							.addComponent(btn_LamMoi, GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
							.addComponent(btn_ThueNgay, GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_pnl_DatPhong.setVerticalGroup(
			gl_pnl_DatPhong.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnl_DatPhong.createSequentialGroup()
					.addGroup(gl_pnl_DatPhong.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnl_DatPhong.createSequentialGroup()
							.addContainerGap(12, Short.MAX_VALUE)
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
							.addGap(13)
							.addGroup(gl_pnl_DatPhong.createParallelGroup(Alignment.LEADING)
								.addComponent(btn_Tim, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
								.addGroup(gl_pnl_DatPhong.createParallelGroup(Alignment.BASELINE)
									.addComponent(txt_MaKH, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
									.addComponent(lblNewLabel_1)
									.addComponent(lblNewLabel_1_1_1_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
									.addComponent(txt_TimSDT, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)))
							.addGap(43)
							.addGroup(gl_pnl_DatPhong.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
								.addComponent(txt_TenKH, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
								.addComponent(txt_LoaiKH, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
							.addGap(30))
						.addGroup(gl_pnl_DatPhong.createSequentialGroup()
							.addContainerGap()
							.addComponent(btn_DatTruoc, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_pnl_DatPhong.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_pnl_DatPhong.createSequentialGroup()
							.addComponent(btn_ThueNgay, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btn_LamMoi, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
							.addGap(31))
						.addGroup(gl_pnl_DatPhong.createSequentialGroup()
							.addGroup(gl_pnl_DatPhong.createParallelGroup(Alignment.BASELINE)
								.addComponent(scrollPane_DSPhong, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1_1_1_2_1_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
							.addGap(25))))
		);

		tbl_DSPhong = new JTable();
		tbl_DSPhong.setFocusTraversalKeysEnabled(false);
		tbl_DSPhong.setFocusable(false);

		scrollPane_DSPhong.setViewportView(tbl_DSPhong);
		tbl_DSPhong.setFont(tahoma16);
		tbl_DSPhong.setRowHeight(28);
		tbl_DSPhong.setAutoCreateRowSorter(true);
		tbl_DSPhong.getTableHeader().setFont(tahoma16Bold);
		tbl_DSPhong.getTableHeader().setBackground(tableHeaderColor);
		tbl_DSPhong.getTableHeader().setForeground(whiteColor);
		pnl_DatPhong.setLayout(gl_pnl_DatPhong);
		setLayout(groupLayout);

		btn_ThueNgay.setBackground(mainColor);
		btn_LamMoi.setBackground(mainColor);
		btn_Tim.setBackground(mainColor);

		btn_ThueNgay.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btn_ThueNgay.setBackground(hoverColor);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btn_ThueNgay.setBackground(mainColor);
			}
		});

		btn_LamMoi.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btn_LamMoi.setBackground(hoverColor);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btn_LamMoi.setBackground(mainColor);
			}
		});

		btn_Tim.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btn_Tim.setBackground(hoverColor);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btn_Tim.setBackground(mainColor);
			}
		});

		initTablePhong();
		initTableDonDatPhong();
		loadDataToTablePhong();
		loadDataToTableDonThuePhong();

		btn_Tim.addActionListener(this);
		btn_ThueNgay.addActionListener(this);
		btn_LamMoi.addActionListener(this);
		btn_DatTruoc.addActionListener(this);
	}

	private void initTablePhong() {
		dfDanhSachPhong = new DefaultTableModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		dfDanhSachPhong
				.setColumnIdentifiers(new String[] { "Mã phòng", "Tên phòng", "Loại phòng", "Đơn giá", "Tình Trạng" });
		tbl_DSPhong.setModel(dfDanhSachPhong);
	}

	private void initTableDonDatPhong() {
		dfDanhSachDonDatPhong = new DefaultTableModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		dfDanhSachDonDatPhong.setColumnIdentifiers(new String[] { "Mã Đặt Phòng", "Khách Hàng", "Số Điện Thoại",
				"Tên Phòng", "Loại Phòng", "Thời Gian Vào", "Trạng Thái" });
		tbl_DonDatPhong.setModel(dfDanhSachDonDatPhong);
	}

	/**
	 * tải dữ liệu từ cơ sở dữ liệu vào table
	 */
	private void loadDataToTablePhong() {
		try {
			PhongDAO phongDAO = new PhongDAO();
			List<Phong> listPhong = phongDAO.getDanhSachPhongTheoTinhTrang("Trống");
			for (Phong phong : listPhong) {
				dfDanhSachPhong.addRow(
						new Object[] { phong.getMaPhong(), phong.getTenPhong(), phong.getLoaiPhong().getTenLoaiPhong(),
								phong.getLoaiPhong().getDonGia(), phong.getTrangThai() });
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * tải dữ liệu từ cơ sở dữ liệu vào table
	 */
	private void loadDataToTableDonThuePhong() {
		try {
			DonDatPhongDAO ddpDAO = new DonDatPhongDAO();
			List<DonDatPhong> listTTDatPhong = ddpDAO.getDanhSachDonThuePhong();
			for (DonDatPhong ddp : listTTDatPhong) {
				SimpleDateFormat formatter = new SimpleDateFormat("HH:mm - dd/MM/yyyy");
				String tgv = formatter.format(ddp.getThoiGianVao());
				dfDanhSachDonDatPhong.addRow(new Object[] { ddp.getMaDatPhong(), ddp.getKhachHang().getHoTenKH(),
						ddp.getKhachHang().getSoDienThoai(), ddp.getPhong().getTenPhong(),
						ddp.getPhong().getLoaiPhong().getTenLoaiPhong(), tgv, ddp.getTrangThaiDon() });
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o.equals(btn_Tim)) {
			KhachHangDAO khachHangDAO = new KhachHangDAO();
			KhachHang kh = khachHangDAO.getKhachHangTheoSĐT(txt_TimSDT.getText());
			if (kh != null) {
				txt_MaKH.setText(kh.getMaKhachHang());
				txt_TenKH.setText(kh.getHoTenKH());
				txt_LoaiKH.setText(kh.getLoaiKhachHang());
				txt_TimSDT.setText(kh.getSoDienThoai());
			} else {
				tb = new DialogChuaCoKhachHang();
				tb.setVisible(true);
			}
		}

		if (o.equals(btn_ThueNgay)) {
			DonDatPhongDAO ddpDAO = new DonDatPhongDAO();
			DonDatPhong ddp = new DonDatPhong();
			HoaDonDao hoaDonDao = new HoaDonDao();
			KhachHangDAO khDao = new KhachHangDAO();

			ddp = createThongTinDatPhong();
			HoaDon hoaDon = new HoaDon();
			hoaDon.setNhanVien(ShareData.taiKhoanDangNhap.getNhanVien());
			hoaDon.setTenKhachHang(ddp.getKhachHang().getHoTenKH());
			hoaDonDao.addHoaDon(hoaDon);

			if (ddpDAO.addThongTinDatPhong(ddp)) {
				MessageDialogHelpers.showMessageDialog(mainFrame, "Thông báo", "Thuê Phòng thành công");
				khDao.updateSoLanDen(ddp.getKhachHang());
				dfDanhSachDonDatPhong.setRowCount(0);
				loadDataToTableDonThuePhong();
				dfDanhSachPhong.setRowCount(0);
				loadDataToTablePhong();
			}
		}

		if (o.equals(btn_LamMoi)) {
			txt_TimSDT.setText("");
			txt_MaKH.setText("");
			txt_LoaiKH.setText("");
			txt_TenKH.setText("");
			txt_TimSDT.setText("");

			dfDanhSachDonDatPhong.setRowCount(0);
			loadDataToTableDonThuePhong();;
			dfDanhSachPhong.setRowCount(0);
			loadDataToTablePhong();
		}
		
		if(o.equals(btn_DatTruoc)) {
			dialogDatTruoc = new DialogDatTruoc();
			dialogDatTruoc.setVisible(true);
			
		}

	}

	private DonDatPhong createThongTinDatPhong() {
		DonDatPhong donDatPhong = new DonDatPhong();
		KhachHangDAO khachHangDAO = new KhachHangDAO();
		PhongDAO phongDAO = new PhongDAO();

		KhachHang kh = khachHangDAO.getKhachHangTheoSĐT(txt_TimSDT.getText());
		int row = tbl_DSPhong.getSelectedRow();
		if (row >= 0) {
			Phong phong = phongDAO.getPhongTheoMa(tbl_DSPhong.getValueAt(row, 0).toString());
			phong.setTrangThai("Đang Sử Dụng");
			phongDAO.updatePhong(phong);
			donDatPhong.setPhong(phong);
			donDatPhong.setKhachHang(kh);
			donDatPhong.setThoiGianVao(new Date());
			donDatPhong.setTrangThaiDon("Chưa Thanh Toán");
		} else {
			MessageDialogHelpers.showErrorDialog(btn_ThueNgay, "Cảnh báo", "Chọn 1 phòng để thuê");
		}
		return donDatPhong;
	}

	public void search(String str) {
		dfDanhSachDonDatPhong = (DefaultTableModel) tbl_DonDatPhong.getModel();
		TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(dfDanhSachDonDatPhong);
		tbl_DonDatPhong.setRowSorter(trs);

		if (cmbTimDon.getSelectedItem().toString().equals("Tìm theo tên khách hàng")) {
			trs.setRowFilter(RowFilter.regexFilter(str, 1));
		}

		if (cmbTimDon.getSelectedItem().toString().equals("Tìm theo tên phòng")) {
			trs.setRowFilter(RowFilter.regexFilter(str, 3));
		}

		if (cmbTimDon.getSelectedItem().toString().equals("Tìm theo số điện thoại")) {
			trs.setRowFilter(RowFilter.regexFilter(str, 2));
		}

		if (cmbTimDon.getSelectedItem().toString().equals("Tìm theo ngày đặt")) {
			trs.setRowFilter(RowFilter.regexFilter(str, 5));
		}
	}
	
	public static String getMaKhachHang() {
		return txt_MaKH.getText();
	}
	
	public static String getTenKH() {
		return txt_TenKH.getText();
	}
	
	public static String getSDT() {
		return txt_TimSDT.getText();
	}
	
	public static String getLoaiKH() {
		return txt_LoaiKH.getText();
	}
}
