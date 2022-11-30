/**
 * 
 * Tác giả: La Võ Minh Quân - MSSV:19441111 - Nhóm 4
 * Ngày tạo: 25/10/2021
 * 
 * Mô tả: Giao diện quản lý tài khoản của nhân viên, hiển thị danh sách tài khoản , thêm sửa xóa tài khoản của nhân viên
 * 
 */
package application;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import dao.NhanVienDAO;
import dao.TaiKhoanDAO;
import entity.NhanVien;
import entity.TaiKhoan;
import helpers.DataValidator;
import helpers.MessageDialogHelpers;
import helpers.ShareData;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PnlQuanLyTaiKhoan extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tblDSTaiKhoan;
	private DefaultTableModel dfModel;
	private JTextField txtTenDN;
	private JPasswordField txtMatKhau;
	private JComboBox<String> cmbVaiTro;
	private JComboBox<String> cmbTim;
	private JButton btnThemTK;
	private JButton btnXoaTK;

	private MainFrame mainFrame;
	private JButton btnLamMoi;
	private JComboBox<String> cmbMaNhanVien;
	private JComboBox<String> cmbListCauHoi;
	private JTextArea txaCauTraLoi;
	private JTextField txtTim;

	/**
	 * Create the panel.
	 */
	public PnlQuanLyTaiKhoan() {

		/** set color and font **/
		Color whiteColor = new Color(255, 255, 255);
		Color mainColor = new Color(88, 159, 177);
		Color hoverColor = new Color(121, 178, 192);
		Color seperatorColor = new Color(204, 204, 204);
		Color hovertextColor = new Color(250, 130, 49);
		Color blackColor = new Color(51, 51, 51);
		Color tableHeaderColor = new Color(42, 143, 178);

		Font tahoma14Bold = new Font("Tahoma", Font.BOLD, 14);
		Font tahoma16Bold = new Font("Tahoma", Font.BOLD, 16);
		Font tahoma16 = new Font("Tahoma", Font.PLAIN, 16);
		Font tahoma18 = new Font("Tahoma", Font.PLAIN, 18);
		Font tahoma18Bold = new Font("Tahoma", Font.BOLD, 18);

		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(whiteColor);

		JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(whiteColor);

		JPanel rightPanel = new JPanel();
		rightPanel.setBackground(whiteColor);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(leftPanel, GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(rightPanel, GroupLayout.PREFERRED_SIZE, 401, GroupLayout.PREFERRED_SIZE))
				.addComponent(bottomPanel, GroupLayout.DEFAULT_SIZE, 994, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(rightPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(leftPanel, GroupLayout.PREFERRED_SIZE, 453, Short.MAX_VALUE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(bottomPanel, GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)));
		rightPanel.setLayout(null);

		JLabel lblNewLabel_2_3 = new JLabel("Chọn tên đăng nhập:");
		lblNewLabel_2_3.setFont(tahoma16);
		lblNewLabel_2_3.setBounds(10, 104, 177, 26);
		rightPanel.add(lblNewLabel_2_3);

		cmbTim = new JComboBox<String>();
		cmbTim.addItem("Tìm theo tên tài khoản");
		cmbTim.setFont(tahoma16);
		cmbTim.setBounds(10, 141, 177, 26);
		rightPanel.add(cmbTim);

		JLabel lblNewLabel_1_1 = new JLabel("TÌM KIẾM TÀI KHOẢN");
		lblNewLabel_1_1.setFont(tahoma18Bold);
		lblNewLabel_1_1.setBounds(10, 11, 222, 22);
		rightPanel.add(lblNewLabel_1_1);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 44, 373, 2);
		rightPanel.add(separator_1);
		
		txtTim = new JTextField();
		txtTim.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				search(txtTim.getText());
			}
		});
		txtTim.setBounds(192, 141, 163, 26);
		rightPanel.add(txtTim);
		txtTim.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("TH\u00D4NG TIN T\u00C0I KHO\u1EA2N");
		lblNewLabel_1.setFont(tahoma18Bold);

		JSeparator separator = new JSeparator();

		JLabel lblNewLabel_2 = new JLabel("T\u00EAn \u0111\u0103ng nh\u1EADp:");
		lblNewLabel_2.setFont(tahoma16);

		JLabel lblNewLabel_2_1 = new JLabel("M\u1EADt kh\u1EA9u:");
		lblNewLabel_2_1.setFont(tahoma16);

		JLabel lblNewLabel_2_2 = new JLabel("Vai tr\u00F2:");
		lblNewLabel_2_2.setFont(tahoma16);

		btnThemTK = new JButton("Th\u00EAm");
		btnThemTK.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnThemTK.setBackground(hoverColor);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnThemTK.setBackground(mainColor);
			}
		});
		btnThemTK.setBorder(null);
		btnThemTK.setFocusable(false);
		btnThemTK.setBackground(mainColor);
		btnThemTK.setForeground(whiteColor);
		btnThemTK.setIcon(new ImageIcon(getClass().getResource("/images/icons8-add-20.png")));
		btnThemTK.setFont(tahoma14Bold);

		btnXoaTK = new JButton("X\u00F3a");
		btnXoaTK.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnXoaTK.setBackground(hoverColor);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnXoaTK.setBackground(mainColor);
			}
		});
		btnXoaTK.setBorder(null);
		btnXoaTK.setFocusable(false);
		btnXoaTK.setBackground(mainColor);
		btnXoaTK.setForeground(whiteColor);
		btnXoaTK.setIcon(new ImageIcon(getClass().getResource("/images/icons8-remove-24.png")));
		btnXoaTK.setFont(tahoma14Bold);

		txtTenDN = new JTextField();
		txtTenDN.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtTenDN.setBorder(BorderFactory.createLineBorder(hoverColor));
				txtTenDN.setForeground(hovertextColor);
			}

			@Override
			public void focusLost(FocusEvent e) {
				txtTenDN.setBorder(BorderFactory.createLineBorder(seperatorColor));
				txtTenDN.setForeground(blackColor);
			}
		});
		txtTenDN.setFont(tahoma16);
		txtTenDN.setColumns(10);

		txtMatKhau = new JPasswordField();
		txtMatKhau.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtMatKhau.setBorder(BorderFactory.createLineBorder(hoverColor));
				txtMatKhau.setForeground(hovertextColor);
			}

			@Override
			public void focusLost(FocusEvent e) {
				txtMatKhau.setBorder(BorderFactory.createLineBorder(seperatorColor));
				txtMatKhau.setForeground(blackColor);
			}
		});
		txtMatKhau.setFont(tahoma16);
		txtMatKhau.setColumns(10);

		cmbVaiTro = new JComboBox<String>();
		cmbVaiTro.setFont(tahoma16);
		cmbVaiTro.addItem("Quản lý");
		cmbVaiTro.addItem("Nhân viên lễ tân");

		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnLamMoi.setBackground(hoverColor);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnLamMoi.setBackground(mainColor);
			}
		});
		btnLamMoi.setBorder(null);
		btnLamMoi.setFocusable(false);
		btnLamMoi.setIcon(new ImageIcon(getClass().getResource("/images/icons8-refresh-16.png")));
		btnLamMoi.setForeground(whiteColor);
		btnLamMoi.setFont(tahoma14Bold);
		btnLamMoi.setBackground(mainColor);

		JLabel lblNewLabel_2_2_1 = new JLabel("Mã nhân viên:");
		lblNewLabel_2_2_1.setFont(tahoma16);

		cmbMaNhanVien = new JComboBox<String>();
		cmbMaNhanVien.setFont(tahoma16);

		JLabel lblNewLabel_2_2_1_1 = new JLabel("Câu hỏi:");
		lblNewLabel_2_2_1_1.setFont(tahoma16);

		cmbListCauHoi = new JComboBox<String>();
		cmbListCauHoi.setFont(tahoma16);

		cmbListCauHoi.addItem("Màu yêu thích của bạn là màu gì?");
		cmbListCauHoi.addItem("Bạn có thú cưng không?");
		cmbListCauHoi.addItem("Bạn gái của bạn tên gì?");
		cmbListCauHoi.addItem("Ba mẹ bạn sống ở đâu?");
		cmbListCauHoi.addItem("Thời gian rảnh bạn hay làm gì?");

		JLabel lblNewLabel_2_2_1_1_1 = new JLabel("Trả lời:");
		lblNewLabel_2_2_1_1_1.setFont(tahoma16);

		txaCauTraLoi = new JTextArea();
		txaCauTraLoi.setBorder(new LineBorder(Color.LIGHT_GRAY));
		txaCauTraLoi.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txaCauTraLoi.setBorder(BorderFactory.createLineBorder(hoverColor));
				txaCauTraLoi.setForeground(hovertextColor);
			}

			@Override
			public void focusLost(FocusEvent e) {
				txaCauTraLoi.setBorder(BorderFactory.createLineBorder(seperatorColor));
				txaCauTraLoi.setForeground(blackColor);
			}
		});
		txaCauTraLoi.setFont(tahoma16);
		txaCauTraLoi.setBackground(Color.WHITE);

		GroupLayout gl_leftPanel = new GroupLayout(leftPanel);
		gl_leftPanel.setHorizontalGroup(gl_leftPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_leftPanel
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_leftPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, 567, Short.MAX_VALUE)
						.addComponent(lblNewLabel_1)
						.addGroup(gl_leftPanel.createSequentialGroup()
								.addComponent(btnThemTK, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(btnXoaTK, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 273, Short.MAX_VALUE)
								.addComponent(btnLamMoi, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_leftPanel.createSequentialGroup()
								.addGroup(gl_leftPanel.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lblNewLabel_2_2_1_1_1, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblNewLabel_2_2_1_1, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblNewLabel_2_2, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblNewLabel_2_1, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
										.addComponent(lblNewLabel_2_2_1, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGap(36)
								.addGroup(gl_leftPanel.createParallelGroup(Alignment.TRAILING)
										.addComponent(txtTenDN, GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
										.addComponent(txtMatKhau, GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
										.addComponent(cmbListCauHoi, 0, 436, Short.MAX_VALUE)
										.addComponent(cmbMaNhanVien, 0, 436, Short.MAX_VALUE)
										.addComponent(cmbVaiTro, 0, 436, Short.MAX_VALUE).addComponent(txaCauTraLoi,
												Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE))))
				.addContainerGap()));
		gl_leftPanel.setVerticalGroup(gl_leftPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_leftPanel.createSequentialGroup().addContainerGap().addComponent(lblNewLabel_1)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE).addGap(18)
						.addGroup(gl_leftPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtTenDN, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_leftPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_2_1, GroupLayout.PREFERRED_SIZE, 26,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(txtMatKhau, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_leftPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_2_2, GroupLayout.PREFERRED_SIZE, 26,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(cmbVaiTro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_leftPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(cmbMaNhanVien, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_2_2_1, GroupLayout.PREFERRED_SIZE, 26,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_leftPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_2_2_1_1, GroupLayout.PREFERRED_SIZE, 26,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(cmbListCauHoi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_leftPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_2_2_1_1_1, GroupLayout.PREFERRED_SIZE, 26,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(txaCauTraLoi, GroupLayout.PREFERRED_SIZE, 113,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_leftPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnThemTK, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnXoaTK, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnLamMoi, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
						.addGap(24)));
		leftPanel.setLayout(gl_leftPanel);

		JLabel lblNewLabel = new JLabel("Danh s\u00E1ch t\u00E0i kho\u1EA3n:");
		lblNewLabel.setFont(tahoma18);

		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_bottomPanel = new GroupLayout(bottomPanel);
		gl_bottomPanel
				.setHorizontalGroup(
						gl_bottomPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(
										gl_bottomPanel.createSequentialGroup().addContainerGap()
												.addGroup(gl_bottomPanel.createParallelGroup(Alignment.LEADING)
														.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 713,
																Short.MAX_VALUE)
														.addComponent(lblNewLabel))
												.addContainerGap()));
		gl_bottomPanel.setVerticalGroup(gl_bottomPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_bottomPanel.createSequentialGroup().addContainerGap().addComponent(lblNewLabel)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE).addContainerGap()));

		/** Table danh sách tài khoản **/
		tblDSTaiKhoan = new JTable();
		tblDSTaiKhoan.setFocusable(false);
		tblDSTaiKhoan.setFocusTraversalKeysEnabled(false);
		tblDSTaiKhoan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int row = tblDSTaiKhoan.getSelectedRow();

					if (row >= 0) {
						String tenDN = (String) tblDSTaiKhoan.getValueAt(row, 0);
						String vaiTro = (String) tblDSTaiKhoan.getValueAt(row, 1);
						String maNhanVien = (String) tblDSTaiKhoan.getValueAt(row, 2);

						txtTenDN.setText(tenDN);
						cmbVaiTro.setSelectedItem(vaiTro);
						cmbMaNhanVien.setSelectedItem(maNhanVien);
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		tblDSTaiKhoan.getTableHeader().setBackground(tableHeaderColor);
		tblDSTaiKhoan.getTableHeader().setForeground(whiteColor);
		tblDSTaiKhoan.getTableHeader().setFont(tahoma16Bold);
		tblDSTaiKhoan.setFont(tahoma16);
		tblDSTaiKhoan.setRowHeight(28);
		tblDSTaiKhoan.setAutoCreateRowSorter(true);

		initTable();

		loadDataToTable();

		// chỉ cho khách hàng được chọn 1 dòng ở 1 thời điểm
		tblDSTaiKhoan.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		/* ================================= */

		scrollPane.setViewportView(tblDSTaiKhoan);
		bottomPanel.setLayout(gl_bottomPanel);

		setLayout(groupLayout);

		btnThemTK.addActionListener(this);
		btnXoaTK.addActionListener(this);
		btnLamMoi.addActionListener(this);
	}

	/**
	 * Tạo default table model add các row title cho table
	 */
	private void initTable() {
		dfModel = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		dfModel.setColumnIdentifiers(new String[] { "Tên đăng nhập", "Vai trò", "Mã nhân viên" });
		tblDSTaiKhoan.setModel(dfModel);
		loadDataToComboMaNV();
	}

	/**
	 * tải dữ liệu từ cơ sở dữ liệu vào table
	 */
	private void loadDataToTable() {
		try {
			TaiKhoanDAO tkDAO = new TaiKhoanDAO();
			List<TaiKhoan> listTK = tkDAO.getDanhSachTaiKhoan();
			for (TaiKhoan tk : listTK) {
				dfModel.addRow(new Object[] { tk.getTenDangNhap(), tk.getVaiTro(), tk.getNhanVien().getMaNhanVien() });
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * tải dữ liệu từ cơ sở dữ liệu vào combobox mã nhân viên
	 */
	private void loadDataToComboMaNV() {
		NhanVienDAO nvDAO = new NhanVienDAO();
		List<NhanVien> listNV = nvDAO.getDanhSachNhanVien();
		for (NhanVien nhanVien : listNV) {
			cmbMaNhanVien.addItem(nhanVien.getMaNhanVien());
		}
	}

	private void dataValidate(StringBuilder sb) {
		DataValidator.validateEmpty(txtMatKhau, sb, "Mật khẩu không được để trống");
		DataValidator.validateEmpty(txtTenDN, sb, "Tên đăng nhập không được để trống");
		DataValidator.validateTenDN(txtTenDN, sb, "Tên đăng nhập không được có khoảng trắng, không có kí tự đặt biệt");
		DataValidator.validateMatKhau(txtMatKhau, sb,
				"Mật khẩu phải có ít nhất 1 chữ thường, 1 chữ hoa, 1 kí tự đặt biệt, không có khoảng trắng và tối thiểu 8 kí tự");
	}

	/**
	 * Tạo 1 tài khoản
	 * 
	 * @return TaiKhoan
	 */
	private TaiKhoan createTaiKhoan() {
		TaiKhoan tk = new TaiKhoan();
		NhanVienDAO nvDAO = new NhanVienDAO();

		tk.setTenDangNhap(txtTenDN.getText());
		tk.setMatKhau(txtMatKhau.getText());
		tk.setVaiTro((String) cmbVaiTro.getSelectedItem());

		String maNhanVien = (String) cmbMaNhanVien.getSelectedItem();
		NhanVien nhanVien = nvDAO.getNhanVienTheoMa(maNhanVien);
		tk.setNhanVien(nhanVien);

		tk.setCauHoi((String) cmbListCauHoi.getSelectedItem());
		tk.setTraLoi(txaCauTraLoi.getText());

		return tk;
	}

	private void lamMoiText() {
		txtTenDN.setText("");
		txtMatKhau.setText("");
		cmbVaiTro.setSelectedIndex(0);
		cmbListCauHoi.setSelectedIndex(0);
		txaCauTraLoi.setText("");
		txtTim.setText("");
		txtTenDN.requestFocus();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		// THEM TAI KHOAN
		if (o.equals(btnThemTK)) {
			TaiKhoan tk = createTaiKhoan();
			TaiKhoanDAO tkDAO = new TaiKhoanDAO();

			StringBuilder sb = new StringBuilder();
			dataValidate(sb);

			if (sb.length() > 0) {
				MessageDialogHelpers.showErrorDialog(mainFrame, "Lỗi", sb.toString());
				return;
			}

			String maNV = (String) cmbMaNhanVien.getSelectedItem();
			int maNVInt = Integer.parseInt(maNV.replaceAll("NV", ""));

			if (tkDAO.checkExist(maNVInt)) {
				MessageDialogHelpers.showErrorDialog(mainFrame, "Cảnh báo",
						"Tên đặng nhập đã tồn tại hoặc mã nhân viên trùng");
				return;
			} else {
				if (tkDAO.addTaiKhoan(tk)) {
					MessageDialogHelpers.showMessageDialog(mainFrame, "Thông báo", "Tài khoản đã thêm thành công");
					dfModel.setRowCount(0);
					loadDataToTable();
					cmbTim.addItem(tk.getTenDangNhap());
					lamMoiText();
				}
			}
		}

		// XOA TAI KHOAN
		if (o.equals(btnXoaTK)) {
			TaiKhoanDAO tkDAO = new TaiKhoanDAO();
			String tenDN = null;

			int row = tblDSTaiKhoan.getSelectedRow();

			if (row >= 0) {
				tenDN = (String) tblDSTaiKhoan.getValueAt(row, 0);

				if (!tenDN.equals(ShareData.taiKhoanDangNhap.getTenDangNhap())) {
					int isXoa = MessageDialogHelpers.showConfirmDialog(mainFrame, "Cảnh báo",
							"Bạn có chắc muốn xóa tài khoản này");
					if (isXoa == JOptionPane.NO_OPTION) {
						return;
					} else if (isXoa == JOptionPane.CLOSED_OPTION) {
						return;
					}

					try {
						if (tkDAO.deleteTaiKhoan(tenDN)) {
							MessageDialogHelpers.showMessageDialog(mainFrame, "Thông báo", "Xóa thành công");
							dfModel.setRowCount(0);
							loadDataToTable();
							cmbTim.removeItem(tenDN);
						} else {
							MessageDialogHelpers.showErrorDialog(mainFrame, "Lỗi", "Xóa không thành công");
						}
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				} else {
					MessageDialogHelpers.showErrorDialog(mainFrame, "Cảnh báo",
							"Không được xóa tài khoản đang sử dụng");
				}
			} else {
				MessageDialogHelpers.showErrorDialog(mainFrame, "Cảnh báo", "Cần chọn 1 dòng trong bảng");
			}
		}

		// LAM MOI
		if (o.equals(btnLamMoi)) {
			lamMoiText();
			dfModel.setRowCount(0);
			loadDataToTable();
			tblDSTaiKhoan.setRowSorter(null);
		}
	}
	
	public void search(String str) {
		dfModel = (DefaultTableModel) tblDSTaiKhoan.getModel();
		TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(dfModel);
		tblDSTaiKhoan.setRowSorter(trs);

		if (cmbTim.getSelectedItem().toString().equals("Tìm theo tên tài khoản")) {
			trs.setRowFilter(RowFilter.regexFilter(str, 0));
		}

		
	}
}
