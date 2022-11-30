/**
 * Tác giả: Đoàn Thị Mỹ Linh - mssv:19442391 - Nhóm 4
 * Tăng Gia Bảo
 * 
 * Ngày tạo:10/11/2021
 * Mô tả: Giao diện quản lý khách hàng gồm các chức năng xem, sửa, xóa,danh sách các khách hàng và chức năng đặt phòng
 * 
 * 
 */
package application;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import dao.KhachHangDAO;
import entity.KhachHang;
import helpers.DataValidator;
import helpers.MessageDialogHelpers;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PnlQuanLyKhachHang extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tblKhachHang;
	private JTextField txtMaKhachHang;
	private JTextField txtTenKhachHang;
	private JTextField txtSoDT;
	private JTextField txtSoLanDen;
	private JTextField txtLoaiKhachHang;
	private JButton btnThemKhachHang;
	private JButton btnSuaKhachHang;
	private DefaultTableModel model;
	private Component mainFrame;
	private JTextField txtTim;
	private JComboBox<String> cmbTim;
	private JButton btnLammoi;

	/**
	 * Create the panel.
	 */
	public PnlQuanLyKhachHang() {

		Color whiteColor = new Color(255, 255, 255);
		Color mainColor = new Color(88, 159, 177);
		Color blackColor = new Color(51, 51, 51);
		Color hovertextColor = new Color(250, 130, 49);
		Color hoverColor = new Color(121, 178, 192);
		Color seperatorColor = new Color(204, 204, 204);
		Color tableHeaderColor = new Color(42, 143, 178);

		Font tahoma16 = new Font("Tahoma", Font.PLAIN, 16);
		Font tahoma16Bold = new Font("Tahoma", Font.BOLD, 16);
		Font tahoma18 = new Font("Tahoma", Font.PLAIN, 18);

		JPanel panel = new JPanel();
		panel.setBackground(whiteColor);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(whiteColor);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 692, Short.MAX_VALUE)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 692, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 334, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE).addContainerGap()));

		JLabel lblThngTinKhch = new JLabel("Th\u00F4ng tin kh\u00E1ch h\u00E0ng");
		lblThngTinKhch.setFont(tahoma18);

		JLabel lblNewLabel_1 = new JLabel("M\u00E3 kh\u00E1ch h\u00E0ng:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(tahoma16);

		JLabel lblNewLabel_1_1 = new JLabel("T\u00EAn kh\u00E1ch h\u00E0ng:");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setFont(tahoma16);

		JLabel lblNewLabel_1_1_1 = new JLabel("S\u1ED1 \u0111i\u1EC7n tho\u1EA1i:");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1.setFont(tahoma16);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("S\u1ED1 l\u1EA7n \u0111\u1EBFn:");
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1_1.setFont(tahoma16);

		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Lo\u1EA1i kh\u00E1ch h\u00E0ng:");
		lblNewLabel_1_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1_1_1.setFont(tahoma16);

		txtMaKhachHang = new JTextField();
		txtMaKhachHang.setEditable(false);
		txtMaKhachHang.setFont(tahoma16);
		txtMaKhachHang.setColumns(10);

		// btn thêm khách hàng

		btnThemKhachHang = new JButton("Thêm");
		btnThemKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnThemKhachHang.setBackground(hoverColor);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnThemKhachHang.setBackground(mainColor);
			}
		});
		btnThemKhachHang.setFocusable(false);
		btnThemKhachHang.setFocusTraversalKeysEnabled(false);
		btnThemKhachHang.setFocusPainted(false);
		btnThemKhachHang.setBorder(null);
		btnThemKhachHang.setRequestFocusEnabled(false);
		btnThemKhachHang.setForeground(whiteColor);
		btnThemKhachHang.setIcon(new ImageIcon(getClass().getResource("/images/icons8-add-20.png")));
		btnThemKhachHang.setFont(tahoma16);
		btnThemKhachHang.setBackground(mainColor);

		txtTenKhachHang = new JTextField();
		txtTenKhachHang.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtTenKhachHang.setBorder(BorderFactory.createLineBorder(hoverColor));
				txtTenKhachHang.setForeground(hovertextColor);
			}

			@Override
			public void focusLost(FocusEvent e) {
				txtTenKhachHang.setBorder(BorderFactory.createLineBorder(seperatorColor));
				txtTenKhachHang.setForeground(blackColor);
			}
		});
		txtTenKhachHang.setFont(tahoma16);
		txtTenKhachHang.setColumns(10);

		// btn sửa khách hàng
		btnSuaKhachHang = new JButton("Sửa");
		btnSuaKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnSuaKhachHang.setBackground(hoverColor);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnSuaKhachHang.setBackground(mainColor);
			}
		});
		btnSuaKhachHang.setFocusable(false);
		btnSuaKhachHang.setFocusTraversalKeysEnabled(false);
		btnSuaKhachHang.setFocusPainted(false);

		btnSuaKhachHang.setBorder(null);
		btnSuaKhachHang.setRequestFocusEnabled(false);
		btnSuaKhachHang.setForeground(whiteColor);
		btnSuaKhachHang.setIcon(new ImageIcon(getClass().getResource("/images/icons8-pencil-16.png")));
		btnSuaKhachHang.setFont(tahoma16);
		btnSuaKhachHang.setBackground(mainColor);

		txtSoDT = new JTextField();
		txtSoDT.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtSoDT.setBorder(BorderFactory.createLineBorder(hoverColor));
				txtSoDT.setForeground(hovertextColor);
			}

			@Override
			public void focusLost(FocusEvent e) {
				txtSoDT.setBorder(BorderFactory.createLineBorder(seperatorColor));
				txtSoDT.setForeground(blackColor);
			}
		});
		txtSoDT.setFont(tahoma16);
		txtSoDT.setColumns(10);

		txtSoLanDen = new JTextField();
		txtSoLanDen.setEditable(false);
		txtSoLanDen.setFont(tahoma16);
		txtSoLanDen.setColumns(10);

		txtLoaiKhachHang = new JTextField();
		txtLoaiKhachHang.setFont(tahoma16);
		txtLoaiKhachHang.setEditable(false);
		txtLoaiKhachHang.setColumns(10);
		
		btnLammoi = new JButton("Làm mới");
		btnLammoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaRongTextfieldKH();
				model.setRowCount(0);
				loadDataToTable();
			}
		});
		btnLammoi.setRequestFocusEnabled(false);
		btnLammoi.setForeground(Color.WHITE);
		btnLammoi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLammoi.setFocusable(false);
		btnLammoi.setFocusTraversalKeysEnabled(false);
		btnLammoi.setFocusPainted(false);
		btnLammoi.setBorder(null);
		btnLammoi.setBackground(new Color(88, 159, 177));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblThngTinKhch, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtSoDT, GroupLayout.DEFAULT_SIZE, 740, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_1_1_1_1, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtSoLanDen, GroupLayout.DEFAULT_SIZE, 740, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_1_1_1_1_1, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtLoaiKhachHang, GroupLayout.DEFAULT_SIZE, 740, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(lblNewLabel_1_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblNewLabel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(10)
									.addComponent(btnThemKhachHang, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(btnSuaKhachHang, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 558, Short.MAX_VALUE)
									.addComponent(btnLammoi, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))
								.addComponent(txtMaKhachHang, GroupLayout.DEFAULT_SIZE, 740, Short.MAX_VALUE)
								.addComponent(txtTenKhachHang, GroupLayout.DEFAULT_SIZE, 740, Short.MAX_VALUE))))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblThngTinKhch, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(txtMaKhachHang, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtTenKhachHang, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtSoDT, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1_1_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtSoLanDen, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1_1_1_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtLoaiKhachHang, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnSuaKhachHang, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnThemKhachHang, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnLammoi, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);

		JScrollPane scrollPane = new JScrollPane();

		JLabel lblNewLabel = new JLabel("Danh sách khách hàng");
		lblNewLabel.setFont(tahoma18);
		
		cmbTim = new JComboBox<String>();
		cmbTim.addItem("Tìm theo tên khách hàng");
		cmbTim.addItem("Tìm theo số điện thoại");
		
		txtTim = new JTextField();
		txtTim.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				search(txtTim.getText());
			}
		});
		txtTim.setColumns(10);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 885, Short.MAX_VALUE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(249)
							.addComponent(cmbTim, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtTim, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(cmbTim, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtTim, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
					.addContainerGap())
		);

		// create table khách hàng
		tblKhachHang = new JTable();
		Object[] columns = { "Mã Khách Hàng", "Tên Khách Hàng", "Số Điện Thoại", "Số Lần Đến", "Loại Khách Hàng" };
		model = new DefaultTableModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {

				return false;
			}
		};
		model.setColumnIdentifiers(columns);
		tblKhachHang.setModel(model);
		scrollPane.setViewportView(tblKhachHang);
		tblKhachHang.setFont(tahoma16);
		tblKhachHang.setRowHeight(28);
		tblKhachHang.setAutoCreateRowSorter(true);
		tblKhachHang.getTableHeader().setFont(tahoma16Bold);
		tblKhachHang.getTableHeader().setBackground(tableHeaderColor);
		tblKhachHang.getTableHeader().setForeground(whiteColor);
		panel_1.setLayout(gl_panel_1);
		setLayout(groupLayout);

		/**
		 * load dữ liệu từ table lên jtext
		 */
		tblKhachHang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int row = tblKhachHang.getSelectedRow();

					if (row >= 0) {
						String id = (String) tblKhachHang.getValueAt(row, 0);
						KhachHangDAO khachHangDAO = new KhachHangDAO();
						KhachHang khachHang = khachHangDAO.getKhachHangTheoMa(id);

						if (khachHang != null) {
							txtMaKhachHang.setText(khachHang.getMaKhachHang());
							txtTenKhachHang.setText(khachHang.getHoTenKH());
							txtSoDT.setText(khachHang.getSoDienThoai());
							txtLoaiKhachHang.setText(khachHang.getLoaiKhachHang());
							int soLanDen = khachHang.getSoLanDen();
							txtSoLanDen.setText(Integer.toString(soLanDen));

						}
					}

				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		loadDataToTable();

		btnThemKhachHang.addActionListener(this);
		btnSuaKhachHang.addActionListener(this);
	}

	// Xóa rỗng textfield Khách hàng
	private void xoaRongTextfieldKH() {
		txtMaKhachHang.setText("");
		txtTenKhachHang.setText("");
		txtSoDT.setText("");
		txtSoLanDen.setText("");
		txtTim.setText("");
		tblKhachHang.setRowSorter(null);
		txtMaKhachHang.requestFocus();
		txtLoaiKhachHang.setText("");
	}

	// load data
	public void loadDataToTable() {
		try {
			KhachHangDAO khachHangDAO = new KhachHangDAO();
			List<KhachHang> listKH = khachHangDAO.getDanhSachKhachHang();
			
			for (KhachHang khachHang : listKH) {
				if (khachHang.getSoLanDen() > 2) {
					khachHang.setLoaiKhachHang("Khách hàng thân thiết");
					khachHangDAO.updateLoaiKhachHang(khachHang);
				}
				model.addRow(new Object[] { khachHang.getMaKhachHang(), khachHang.getHoTenKH(),
						khachHang.getSoDienThoai(), khachHang.getSoLanDen(), khachHang.getLoaiKhachHang() });
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// tạo mới khách hàng
	private KhachHang createKhachHang() {
		KhachHang khachHang = new KhachHang();
		khachHang.setHoTenKH(txtTenKhachHang.getText());
		khachHang.setSoDienThoai(txtSoDT.getText());
		return khachHang;
	}

	/**
	 * kiểm tra biểu thức chính quy
	 * 
	 * @param sb
	 */
	private void dataValidateKhachHang(StringBuilder sb) {
		DataValidator.validateEmpty(txtSoDT, sb, "Số điện thoại không được để trống");
		DataValidator.validateEmpty(txtTenKhachHang, sb, "Tên khách hàng không được để trống");
		DataValidator.validateVietnameseCharacters(txtTenKhachHang, sb,
				"Tên khách hàng sai.Không được nhập số và kí tự đặt biệt");
		DataValidator.validateSoDT(txtSoDT, sb,
				"Số điện thoại sai định dạng, phải có từ 9-10 chữ số, không có kí tự. Ví dụ:0788775877");
		DataValidator.validateSoDT(txtSoDT, sb, "Số điện thoại đã tồn tại");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		// THÊM KHÁCH HÀNG
		if (o.equals(btnThemKhachHang)) {
			KhachHang khachHang = createKhachHang();
			KhachHangDAO khachHangDAO = new KhachHangDAO();

			StringBuilder sb = new StringBuilder();
			dataValidateKhachHang(sb);

			if (sb.length() > 0) {
				MessageDialogHelpers.showErrorDialog(mainFrame, "Lỗi", sb.toString());
				return;
			}

			if (khachHangDAO.checkExist(txtSoDT.getText())) {
				MessageDialogHelpers.showErrorDialog(mainFrame, "Cảnh báo",
						"Khách hàng đã tồn tại, số điện thoại trùng");
				return;
			} else {
				if (khachHangDAO.addKhachHang(khachHang)) {
					MessageDialogHelpers.showMessageDialog(mainFrame, "Thông báo", "Khách hàng đã thêm thành công");
					model.setRowCount(0);
					loadDataToTable();
					xoaRongTextfieldKH();
				} else {
					MessageDialogHelpers.showErrorDialog(mainFrame, "Lỗi", "Thêm không thành công");
				}
			}
		}

		// CẬP NHẬT THÔNG TIN KHÁCH HÀNG
		if (o.equals(btnSuaKhachHang)) {
			int row = tblKhachHang.getSelectedRow();
			KhachHang khachHang = createKhachHang();
			khachHang.setMaKhachHang(txtMaKhachHang.getText());
			KhachHangDAO khachHangDAO = new KhachHangDAO();
			if (row >= 0) {
				StringBuilder sb = new StringBuilder();
				dataValidateKhachHang(sb);
				if (sb.length() > 0) {
					MessageDialogHelpers.showErrorDialog(mainFrame, "Lỗi", sb.toString());
					return;
				}
				if (khachHangDAO.checkExist(txtSoDT.getText())) {
					MessageDialogHelpers.showErrorDialog(mainFrame, "Cảnh báo", "Số điện thoại trùng");
					return;
				} else {
					if (MessageDialogHelpers.showConfirmDialog(mainFrame, "Lỗi",
							"Bạn có chắc muốn cập nhật") == JOptionPane.NO_OPTION) {
						return;
					}
					try {

						if (khachHangDAO.updateKhachHang(khachHang)) {
							MessageDialogHelpers.showMessageDialog(mainFrame, "Thông báo",
									"Bạn đã cập nhật thành công");
							model.setRowCount(0);
							loadDataToTable();

						} else {
							MessageDialogHelpers.showErrorDialog(mainFrame, "Lỗi", "Cập nhật không thành công");
						}
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}

			} else {
				MessageDialogHelpers.showErrorDialog(mainFrame, "Lỗi", "Phải chọn một dòng trong bảng");
			}
			xoaRongTextfieldKH();

		}

	}
	
	public void search(String str) {
		model = (DefaultTableModel) tblKhachHang.getModel();
		TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
		tblKhachHang.setRowSorter(trs);

		if (cmbTim.getSelectedItem().toString().equals("Tìm theo tên khách hàng")) {
			trs.setRowFilter(RowFilter.regexFilter(str, 1));
		}

		if (cmbTim.getSelectedItem().toString().equals("Tìm theo số điện thoại")) {
			trs.setRowFilter(RowFilter.regexFilter(str, 2));
		}
	}
}
