/**
 * 
 * Tác giả: Đoàn Thị Mỹ Linh - MSSV:19442391 - Nhóm 4
 * Ngày tạo: 30/10/2021
 * Cập nhật: 11/10/2021
 * Mô tả: Giao diện quản lý sản phẩm,loại sản phẩm bao gồm các chức năng xem, sửa, xóa, tìm kiếm và danh sách các sản phẩm, loại sản phẩm
 * 
 * Quân đã sửa
 */
package application;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import dao.LoaiSanPhamDAO;
import dao.SanPhamDAO;
import entity.LoaiSanPham;
import entity.SanPham;
import helpers.DataValidator;
import helpers.MessageDialogHelpers;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PnlQuanLySanPham extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtMaSP;
	private JTextField txtTenSP;
	private JTextField txtDonGia;
	private JTextField txtMaLoaiSP;
	private JTextField txtTenLoaiSP;
	private JButton btnThemLoaiSP, btnCapNhatLoaiSP, btnXoaLoaiSP;
	private JButton btnXoaSP, btnCapNhatSP, btnThem, btnLamMoi;
	private JTable tblSanPham, tblLoaiSanPham;
	private JComboBox<String> cmbTim;
	private DefaultTableModel modelSanPham;
	private DefaultTableModel modelLoaiSP;
	private MainFrame mainFrame;
	private JComboBox<String> cmbLoaiSanPham;
	private JTextField txtTim;

	/**
	 * Create the panel.
	 */
	public PnlQuanLySanPham() {
		Color whiteColor = new Color(255, 255, 255);
		Color mainColor = new Color(88, 159, 177);
		Color seperatorColor = new Color(204, 204, 204);
		Color blackColor = new Color(51, 51, 51);
		Color hovertextColor = new Color(250, 130, 49);
		Color hoverColor = new Color(121, 178, 192);
		Color tableHeaderColor = new Color(42, 143, 178);

		Font tahoma16 = new Font("Tahoma", Font.PLAIN, 16);
		Font tahoma16Bold = new Font("Tahoma", Font.BOLD, 16);
		Font tahoma14 = new Font("Tahoma", Font.PLAIN, 14);
		Font tahoma14Bold = new Font("Tahoma", Font.BOLD, 14);
		Font tahoma20Bold = new Font("Tahoma", Font.BOLD, 20);

		JPanel panel = new JPanel();
		panel.setBackground(whiteColor);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(whiteColor);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(whiteColor);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 488, GroupLayout.PREFERRED_SIZE))
				.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 939, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE).addContainerGap()));

		JScrollPane scrollPane = new JScrollPane();

		cmbTim = new JComboBox<String>();
		cmbTim.addItem("Tìm theo tên sản phẩm");
		cmbTim.setFont(tahoma14);
		cmbTim.setBorder(null);

		JLabel lblNewLabel_1_3 = new JLabel("Chọn sản phẩm:");
		lblNewLabel_1_3.setFont(tahoma16);

		JLabel lblNewLabel_1_4 = new JLabel("Danh sách sản phẩm:");
		lblNewLabel_1_4.setFont(tahoma16);

		// btn làm mới text field sản phẩm
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

		btnLamMoi.setIcon(new ImageIcon(getClass().getResource("/images/icons8-refresh-16.png")));
		btnLamMoi.setForeground(whiteColor);
		btnLamMoi.setFont(tahoma14Bold);
		btnLamMoi.setFocusable(false);
		btnLamMoi.setBackground(mainColor);
		btnLamMoi.addActionListener(this);
		
		txtTim = new JTextField();
		txtTim.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				search(txtTim.getText());
			}
		});
		txtTim.setColumns(10);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 895, Short.MAX_VALUE)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(lblNewLabel_1_3, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(cmbTim, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtTim, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 324, Short.MAX_VALUE)
							.addComponent(btnLamMoi, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNewLabel_1_4, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_3, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmbTim, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLamMoi, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtTim, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel_1_4, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
					.addContainerGap())
		);

		tblSanPham = new JTable();
		tblSanPham.setFont(tahoma16);
		tblSanPham.setRowHeight(28);
		tblSanPham.setAutoCreateRowSorter(true);
		tblSanPham.getTableHeader().setFont(tahoma16Bold);
		tblSanPham.getTableHeader().setBackground(tableHeaderColor);
		tblSanPham.getTableHeader().setForeground(whiteColor);
		scrollPane.setViewportView(tblSanPham);
		initTableSanPham();
		panel_2.setLayout(gl_panel_2);

		/**
		 * load dữ liệu từ table sản phẩm lên jtext
		 */
		tblSanPham.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int row = tblSanPham.getSelectedRow();
					if (row >= 0) {
						String ma = (String) tblSanPham.getValueAt(row, 0);
						SanPhamDAO sanPhamDAO = new SanPhamDAO();
						SanPham sanPham = sanPhamDAO.getSanPhamTheoMa(ma);
						if (sanPham != null) {
							txtMaSP.setText(ma);
							txtTenSP.setText(sanPham.getTenSanPham());
							double donGia = sanPham.getDonGia();
							txtDonGia.setText(Double.toString(donGia));
							cmbLoaiSanPham.setSelectedItem(sanPham.getLoaiSanPham().getTenLoaiSP());
						}
					}

				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});

		JLabel lblNewLabel_1_2 = new JLabel("Mã loại sản phẩm:");
		lblNewLabel_1_2.setFont(tahoma16);

		txtMaLoaiSP = new JTextField();
		txtMaLoaiSP.setFont(tahoma14);
		txtMaLoaiSP.setEditable(false);
		txtMaLoaiSP.setColumns(10);

		JLabel lblNewLabel_1_1_3 = new JLabel("Tên loại sản phẩm:");
		lblNewLabel_1_1_3.setFont(tahoma16);

		txtTenLoaiSP = new JTextField();
		txtTenLoaiSP.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtTenLoaiSP.setBorder(BorderFactory.createLineBorder(hoverColor));
				txtTenLoaiSP.setForeground(hovertextColor);
			}

			@Override
			public void focusLost(FocusEvent e) {
				txtTenLoaiSP.setBorder(BorderFactory.createLineBorder(seperatorColor));
				txtTenLoaiSP.setForeground(blackColor);
			}
		});
		txtTenLoaiSP.setFont(tahoma14);
		txtTenLoaiSP.setColumns(10);

		JScrollPane scrollPane_1 = new JScrollPane();

		// btn thêm loại sản phẩm
		btnThemLoaiSP = new JButton("Thêm");
		btnThemLoaiSP.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnThemLoaiSP.setBackground(hoverColor);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnThemLoaiSP.setBackground(mainColor);
			}
		});
		btnThemLoaiSP.setBorder(null);

		btnThemLoaiSP.setIcon(new ImageIcon(getClass().getResource("/images/icons8-add-20.png")));
		btnThemLoaiSP.setForeground(whiteColor);
		btnThemLoaiSP.setFont(tahoma14Bold);
		btnThemLoaiSP.setFocusable(false);
		btnThemLoaiSP.setBackground(mainColor);

		// btn cập nhật loại sản phẩm
		btnCapNhatLoaiSP = new JButton("Cập nhật");
		btnCapNhatLoaiSP.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnCapNhatLoaiSP.setBackground(hoverColor);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnCapNhatLoaiSP.setBackground(mainColor);
			}
		});
		btnCapNhatLoaiSP.setBorder(null);

		btnCapNhatLoaiSP.setIcon(new ImageIcon(getClass().getResource("/images/icons8-pencil-16.png")));
		btnCapNhatLoaiSP.setForeground(whiteColor);
		btnCapNhatLoaiSP.setFont(tahoma14Bold);
		btnCapNhatLoaiSP.setFocusable(false);
		btnCapNhatLoaiSP.setBackground(mainColor);

		// btn xóa loại phòng
		btnXoaLoaiSP = new JButton("Xóa");
		btnXoaLoaiSP.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnXoaLoaiSP.setBackground(hoverColor);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnXoaLoaiSP.setBackground(mainColor);
			}
		});
		btnXoaLoaiSP.setBorder(null);

		btnXoaLoaiSP.setIcon(new ImageIcon(getClass().getResource("/images/icons8-remove-24.png")));
		btnXoaLoaiSP.setForeground(whiteColor);
		btnXoaLoaiSP.setFont(tahoma14Bold);
		btnXoaLoaiSP.setFocusable(false);
		btnXoaLoaiSP.setBackground(mainColor);

		JLabel lblThngTinLoi = new JLabel("Thông tin loại sản phẩm ");
		lblThngTinLoi.setFont(tahoma20Bold);

		JLabel lblNewLabel_1_1_3_1 = new JLabel("Danh sách loại sản phẩm");
		lblNewLabel_1_1_3_1.setFont(tahoma16);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_panel_1.createSequentialGroup()
								.addComponent(lblNewLabel_1_1_3_1)
								.addContainerGap(301, Short.MAX_VALUE))
							.addGroup(gl_panel_1.createSequentialGroup()
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_panel_1.createSequentialGroup()
										.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
											.addComponent(lblNewLabel_1_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(lblNewLabel_1_1_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGap(53)
										.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
											.addComponent(txtTenLoaiSP, GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
											.addComponent(txtMaLoaiSP, GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)))
									.addGroup(gl_panel_1.createSequentialGroup()
										.addComponent(lblThngTinLoi, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED))
									.addGroup(gl_panel_1.createSequentialGroup()
										.addComponent(btnThemLoaiSP, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnCapNhatLoaiSP, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnXoaLoaiSP, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)))
								.addGap(26)))))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblThngTinLoi, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(31)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtMaLoaiSP, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_2, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtTenLoaiSP, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_1_3, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel_1_1_3_1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnThemLoaiSP, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnXoaLoaiSP, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCapNhatLoaiSP, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);

		tblLoaiSanPham = new JTable();
		tblLoaiSanPham.setFont(tahoma16);
		tblLoaiSanPham.setRowHeight(28);
		tblLoaiSanPham.setAutoCreateRowSorter(true);
		tblLoaiSanPham.getTableHeader().setFont(tahoma16Bold);
		tblLoaiSanPham.getTableHeader().setBackground(tableHeaderColor);
		tblLoaiSanPham.getTableHeader().setForeground(whiteColor);
		initTableLoaiSP();
		scrollPane_1.setViewportView(tblLoaiSanPham);

		/**
		 * load dữ liệu từ table loại sản phẩm lên jtext
		 */
		tblLoaiSanPham.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int row = tblLoaiSanPham.getSelectedRow();
					txtMaLoaiSP.setText(tblLoaiSanPham.getValueAt(row, 0).toString());
					txtTenLoaiSP.setText(tblLoaiSanPham.getValueAt(row, 1).toString());

				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		panel_1.setLayout(gl_panel_1);

		JLabel lblNewLabel = new JLabel("Thông tin sản phẩm");
		lblNewLabel.setFont(tahoma20Bold);

		JLabel lblNewLabel_1 = new JLabel("Mã sản phẩm:");
		lblNewLabel_1.setFont(tahoma16);

		JLabel lblNewLabel_1_1 = new JLabel("Tên sản phẩm:");
		lblNewLabel_1_1.setFont(tahoma16);

		JLabel lblNewLabel_1_1_1 = new JLabel("Loại sản phẩm:");
		lblNewLabel_1_1_1.setFont(tahoma16);

		JLabel lblNewLabel_1_1_2 = new JLabel("Đơn giá:");
		lblNewLabel_1_1_2.setFont(tahoma16);

		txtMaSP = new JTextField();
		txtMaSP.setFont(tahoma16);
		txtMaSP.setEditable(false);
		txtMaSP.setColumns(10);

		// btn xóa sản phẩm
		btnXoaSP = new JButton("Xóa");
		btnXoaSP.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnXoaSP.setBackground(hoverColor);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnXoaSP.setBackground(mainColor);
			}
		});
		btnXoaSP.setBorder(null);

		btnXoaSP.setIcon(new ImageIcon(getClass().getResource("/images/icons8-remove-24.png")));
		btnXoaSP.setForeground(whiteColor);
		btnXoaSP.setFont(tahoma14Bold);
		btnXoaSP.setFocusable(false);
		btnXoaSP.setBackground(mainColor);

		txtTenSP = new JTextField();
		txtTenSP.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtTenSP.setBorder(BorderFactory.createLineBorder(hoverColor));
				txtTenSP.setForeground(hovertextColor);
			}

			@Override
			public void focusLost(FocusEvent e) {
				txtTenSP.setBorder(BorderFactory.createLineBorder(seperatorColor));
				txtTenSP.setForeground(blackColor);
			}
		});
		txtTenSP.setFont(tahoma16);
		txtTenSP.setColumns(10);

		txtDonGia = new JTextField();
		txtDonGia.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtDonGia.setBorder(BorderFactory.createLineBorder(hoverColor));
				txtDonGia.setForeground(hovertextColor);
			}

			@Override
			public void focusLost(FocusEvent e) {
				txtDonGia.setBorder(BorderFactory.createLineBorder(seperatorColor));
				txtDonGia.setForeground(blackColor);
			}
		});
		txtDonGia.setFont(tahoma16);
		txtDonGia.setColumns(10);

		// btn cập nhật sản phẩm
		btnCapNhatSP = new JButton("Cập nhật");
		btnCapNhatSP.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnCapNhatSP.setBackground(hoverColor);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnCapNhatSP.setBackground(mainColor);
			}
		});
		btnCapNhatSP.setBorder(null);

		btnCapNhatSP.setIcon(new ImageIcon(getClass().getResource("/images/icons8-pencil-16.png")));
		btnCapNhatSP.setForeground(whiteColor);
		btnCapNhatSP.setFont(tahoma14Bold);
		btnCapNhatSP.setFocusable(false);
		btnCapNhatSP.setBackground(mainColor);

		// btn Thêm sản phẩm
		btnThem = new JButton("Thêm");
		btnThem.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnThem.setBackground(hoverColor);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnThem.setBackground(mainColor);
			}
		});
		btnThem.setBorder(null);

		btnThem.setIcon(new ImageIcon(getClass().getResource("/images/icons8-add-20.png")));
		btnThem.setForeground(whiteColor);
		btnThem.setFont(tahoma14Bold);
		btnThem.setFocusable(false);
		btnThem.setBackground(mainColor);

		cmbLoaiSanPham = new JComboBox<String>();
		cmbLoaiSanPham.setFont(tahoma16);
		cmbLoaiSanPham.setBackground(whiteColor);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.TRAILING).addGroup(gl_panel
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_panel.createSequentialGroup()
												.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
														.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 121,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(lblNewLabel_1_1_2)
														.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE,
																121, GroupLayout.PREFERRED_SIZE)
														.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 121,
																GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
														.addComponent(cmbLoaiSanPham, 0, 241, Short.MAX_VALUE)
														.addComponent(txtDonGia, GroupLayout.DEFAULT_SIZE, 241,
																Short.MAX_VALUE)
														.addComponent(txtTenSP, GroupLayout.DEFAULT_SIZE, 241,
																Short.MAX_VALUE)
														.addComponent(txtMaSP, Alignment.TRAILING,
																GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)))
										.addGroup(gl_panel.createSequentialGroup()
												.addComponent(btnThem, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(btnCapNhatSP, GroupLayout.DEFAULT_SIZE, 117,
														Short.MAX_VALUE)
												.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnXoaSP,
														GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)))
								.addGap(41))
						.addGroup(gl_panel.createSequentialGroup()
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(173, Short.MAX_VALUE)))));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addContainerGap()
				.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE).addGap(35)
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel.createSequentialGroup()
								.addComponent(txtMaSP, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(txtTenSP, GroupLayout.PREFERRED_SIZE, 24,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 19,
												GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(cmbLoaiSanPham, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 19,
												GroupLayout.PREFERRED_SIZE))
								.addGap(14)
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE, false)
										.addComponent(txtDonGia, GroupLayout.PREFERRED_SIZE, 24,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_1_1_2, GroupLayout.PREFERRED_SIZE, 19,
												GroupLayout.PREFERRED_SIZE)))
						.addComponent(lblNewLabel_1))
				.addGap(65)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnThem, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnXoaSP, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCapNhatSP, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
				.addContainerGap()));
		panel.setLayout(gl_panel);

		setLayout(groupLayout);
		btnThem.addActionListener(this);
		btnXoaSP.addActionListener(this);
		btnCapNhatSP.addActionListener(this);

		// LOẠI SẢN PHẨM
		btnThemLoaiSP.addActionListener(this);
		btnXoaLoaiSP.addActionListener(this);
		btnCapNhatLoaiSP.addActionListener(this);

		// LOAD DATA
		loadDataToTblLoaiSanPham();
		loadDataToTblSanPham();
		loadDataToCmbLoaiSanPham();

	}

	/* =================Tạo df table model================ */
	/**
	 * Tạo default table model cho sản phẩm add các row cho tblLoaiSanPham
	 */
	private void initTableSanPham() {
		modelSanPham = new DefaultTableModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		modelSanPham.setColumnIdentifiers(new String[] { "Mã sản phẩm", "Tên sản phẩm", "Loại sản phẩm", "Đơn giá" });
		tblSanPham.setModel(modelSanPham);
	}

	/**
	 * Tạo default table model cho loại sản phẩm , add các row cho tblLoaiSanPham
	 */
	private void initTableLoaiSP() {
		modelLoaiSP = new DefaultTableModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		modelLoaiSP.setColumnIdentifiers(new String[] { "Mã loại sản phẩm", "Tên loại sản phẩm" });
		tblLoaiSanPham.setModel(modelLoaiSP);
	}
	/* =================================== */

	/* ==================Tải dữ liệu vào 2 table=============== */
	private void loadDataToTblLoaiSanPham() {
		LoaiSanPhamDAO ds = new LoaiSanPhamDAO();
		List<LoaiSanPham> list = ds.getDanhSachLoaiSanPham();
		for (LoaiSanPham lsp : list) {
			modelLoaiSP.addRow(new Object[] { lsp.getMaLoaiSP(), lsp.getTenLoaiSP() });
		}
	}

	private void loadDataToTblSanPham() {
		SanPhamDAO ds = new SanPhamDAO();
		List<SanPham> list = ds.getDanhSachSanPham();

		for (SanPham sanPham : list) {
			modelSanPham.addRow(new Object[] { sanPham.getMaSanPham(), sanPham.getTenSanPham(),
					sanPham.getLoaiSanPham().getTenLoaiSP(), sanPham.getDonGia() });
		}
		tblSanPham.setModel(modelSanPham);
	}
	/* =================================== */

	/* ==================Tải dữ liệu vào combobox================== */
	

	private void loadDataToCmbLoaiSanPham() {
		LoaiSanPhamDAO loaiSanPhamDAO = new LoaiSanPhamDAO();
		List<LoaiSanPham> lsp = loaiSanPhamDAO.getDanhSachLoaiSanPham();
		for (LoaiSanPham loaiSanPham : lsp) {
			cmbLoaiSanPham.addItem(loaiSanPham.getTenLoaiSP());
		}
	}
	/* =================================== */

	/* ================Xóa rỗng textfields================= */
	private void xoaRongTextfieldLoaiSP() {
		txtMaLoaiSP.setText("");
		txtTenLoaiSP.setText("");
		txtMaLoaiSP.requestFocus();
	}

	private void xoaRongTextfieldsSanPham() {
		txtMaSP.setText("");
		txtTenSP.setText("");
		txtDonGia.setText("");
		cmbLoaiSanPham.setSelectedItem(0);
		tblSanPham.setRowSorter(null);
		txtMaSP.requestFocus();
	}
	/* =================================== */

	/* ==================Tạo đối tượng sản phẩm và loại sản phẩm================= */
	private LoaiSanPham createLoaiSanPham() {
		LoaiSanPham loaiSanPham = new LoaiSanPham();
		loaiSanPham.setTenLoaiSP(txtTenLoaiSP.getText());
		return loaiSanPham;
	}

	private SanPham createSanPham() {
		SanPham sp = new SanPham();
		LoaiSanPham loaiSanPham = new LoaiSanPham();
		LoaiSanPhamDAO loaiSanPhamDAO = new LoaiSanPhamDAO();

		String tenLoaiSP = (String) cmbLoaiSanPham.getSelectedItem();
		String maLoaiSP = loaiSanPhamDAO.getMaTheoTenLoai(tenLoaiSP);
		loaiSanPham.setMaLoaiSP(maLoaiSP);
		loaiSanPham.setTenLoaiSP(tenLoaiSP);

		sp.setTenSanPham(txtTenSP.getText());
		sp.setDonGia(Double.parseDouble(txtDonGia.getText()));
		sp.setLoaiSanPham(loaiSanPham);

		return sp;
	}
	/* =================================== */

	/**
	 * kiểm tra biểu thức chính quy
	 * 
	 * @param sb
	 */
	private void dataValidateLoaiSP(StringBuilder sb) {
		DataValidator.validateEmpty(txtTenLoaiSP, sb, "Tên loại sản phẩm không được để trống");
		DataValidator.validateVietnameseCharacters(txtTenLoaiSP, sb,
				"Tên loại sản phẩm sai.Không được nhập số và kí tự đặt biệt");
	}

	private void dataValidateSP(StringBuilder sb) {
		DataValidator.validateEmpty(txtDonGia, sb, "Đơn giá không được để trống");
		DataValidator.validateEmpty(txtTenSP, sb, "Tên sản phẩm không được để trống");
		DataValidator.validateDonGia(txtDonGia, sb, "Đơn giá chỉ được nhập số");
		DataValidator.validateVietnameseCharactersAndNumber(txtTenSP, sb, "Tên sản phẩm sai.Không có kí tự đặt biệt");
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object o = e.getSource();

		// SẢN PHẨM
		// THÊM SẢN PHẨM
		if (o.equals(btnThem)) {

			SanPham sanPham = createSanPham();
			SanPhamDAO sanPhamDAO = new SanPhamDAO();

			StringBuilder sb = new StringBuilder();
			dataValidateSP(sb);

			if (sb.length() > 0) {
				MessageDialogHelpers.showErrorDialog(mainFrame, "Lỗi", sb.toString());
				return;
			}

			if (sanPhamDAO.checkExist(txtTenSP.getText())) {
				MessageDialogHelpers.showErrorDialog(mainFrame, "Cảnh báo", "Sản phẩm đã tồn tại, tên sản phẩm trùng");
				return;
			} else {
				if (sanPhamDAO.addSanPham(sanPham)) {
					MessageDialogHelpers.showMessageDialog(mainFrame, "Thông báo", "Bạn đã thêm thành công");

					modelSanPham.setRowCount(0);
					loadDataToTblSanPham();
					xoaRongTextfieldsSanPham();
				}

			}

		}

		// CẬP NHẬT SẢN PHẨM
		if (o.equals(btnCapNhatSP)) {

			int row = tblSanPham.getSelectedRow();

			SanPham sp = createSanPham();
			sp.setMaSanPham(txtMaSP.getText());
			SanPhamDAO sanPhamDAO = new SanPhamDAO();

			if (row >= 0) {
				StringBuilder sb = new StringBuilder();
				dataValidateSP(sb);
				if (sb.length() > 0) {
					MessageDialogHelpers.showErrorDialog(mainFrame, "Lỗi", sb.toString());
					return;
				}
				if (sanPhamDAO.checkExist(txtTenSP.getText())) {
					MessageDialogHelpers.showErrorDialog(mainFrame, "Cảnh báo", "Tên sản phẩm trùng");
					return;
				} else {
					if (MessageDialogHelpers.showConfirmDialog(mainFrame, "Lỗi",
							"Bạn có chắc muốn cập nhật") == JOptionPane.NO_OPTION) {
						return;
					}
					try {

						if (sanPhamDAO.updateSanPham(sp)) {
							MessageDialogHelpers.showMessageDialog(mainFrame, "Thông báo",
									"Bạn đã cập nhật thành công");
							modelSanPham.setRowCount(0);
							loadDataToTblSanPham();

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
			xoaRongTextfieldsSanPham();

		}

		// XÓA SẢN PHẨM
		if (o.equals(btnXoaSP)) {

			int row = tblSanPham.getSelectedRow();
			SanPhamDAO spDAO = new SanPhamDAO();

			if (row >= 0) {
				String maSP = (String) tblSanPham.getValueAt(row, 0);
				String stt = maSP.replaceAll("SP", "");
				if (MessageDialogHelpers.showConfirmDialog(mainFrame, "Cảnh báo",
						"Bạn có chắc muốn xóa tài khoản này") == JOptionPane.WARNING_MESSAGE) {
					return;
				}

				try {

					if (spDAO.deleteSanPham(stt)) {
						MessageDialogHelpers.showMessageDialog(mainFrame, "Thông báo", "Xóa thành công");
						modelSanPham.setRowCount(0);
						loadDataToTblSanPham();
						cmbTim.removeAllItems();

					} else {
						MessageDialogHelpers.showErrorDialog(mainFrame, "Lỗi", "Xóa không thành công");
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			} else {
				MessageDialogHelpers.showErrorDialog(mainFrame, "Lỗi", "Phải chọn 1 dòng trong bảng");
			}

		}

		// LÀM RỖNG TEXTFIELD SẢN PHẨM
		if (o.equals(btnLamMoi)) {
			xoaRongTextfieldsSanPham();
			modelSanPham.setRowCount(0);
			loadDataToTblSanPham();

			xoaRongTextfieldLoaiSP();
			modelLoaiSP.setRowCount(0);
			loadDataToTblLoaiSanPham();
			
			txtTim.setText("");
		}

		// LOẠI SẢN PHẨM
		// THÊM LOẠI SẢN PHẨM
		if (o.equals(btnThemLoaiSP)) {

			LoaiSanPham lsp = createLoaiSanPham();
			LoaiSanPhamDAO loaiSPDAO = new LoaiSanPhamDAO();

			StringBuilder sb = new StringBuilder();
			dataValidateLoaiSP(sb);
			if (sb.length() > 0) {
				MessageDialogHelpers.showErrorDialog(mainFrame, "Lỗi", sb.toString());
				return;
			}
			if (loaiSPDAO.checkExist(txtTenLoaiSP.getText())) {
				MessageDialogHelpers.showErrorDialog(mainFrame, "Cảnh báo",
						"Loại sản phẩm đã tồn tại, tên loại sản phẩm trùng");
				return;
			} else {
				if (loaiSPDAO.addLoaiSanPham(lsp)) {
					MessageDialogHelpers.showMessageDialog(mainFrame, "Thông báo", "Bạn đã thêm thành công");
					modelLoaiSP.setRowCount(0);

					loadDataToTblLoaiSanPham();
					cmbLoaiSanPham.removeAllItems();
					loadDataToCmbLoaiSanPham();
					xoaRongTextfieldLoaiSP();
				}

			}
		}

		// CẬP NHẬT LOẠI SẢN PHẨM
		if (o.equals(btnCapNhatLoaiSP)) {

			int row = tblLoaiSanPham.getSelectedRow();

			LoaiSanPham lsp = createLoaiSanPham();
			lsp.setMaLoaiSP(txtMaLoaiSP.getText());
			LoaiSanPhamDAO loaiSanPhamDAO = new LoaiSanPhamDAO();

			if (row >= 0) {
				StringBuilder sb = new StringBuilder();
				dataValidateLoaiSP(sb);
				if (sb.length() > 0) {
					MessageDialogHelpers.showErrorDialog(mainFrame, "Lỗi", sb.toString());
					return;
				}
				if (loaiSanPhamDAO.checkExist(txtTenLoaiSP.getText())) {
					MessageDialogHelpers.showErrorDialog(mainFrame, "Cảnh báo", "Tên loại sản phẩm trùng");
					return;
				} else {
					if (MessageDialogHelpers.showConfirmDialog(mainFrame, "Lỗi",
							"Bạn có chắc muốn cập nhật") == JOptionPane.NO_OPTION) {
						return;
					}
					try {

						if (loaiSanPhamDAO.updateLoaiSanPham(lsp)) {
							MessageDialogHelpers.showMessageDialog(mainFrame, "Thông báo",
									"Bạn đã cập nhật thành công");
							modelLoaiSP.setRowCount(0);
							loadDataToTblLoaiSanPham();

							cmbLoaiSanPham.removeAllItems();
							loadDataToCmbLoaiSanPham();
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
			xoaRongTextfieldLoaiSP();

		}

		// XÓA LOẠI SẢN PHẨM
		if (o.equals(btnXoaLoaiSP)) {

			int row = tblLoaiSanPham.getSelectedRow();
			LoaiSanPhamDAO lspDAO = new LoaiSanPhamDAO();

			if (row >= 0) {
				String maLoaiSP = (String) tblLoaiSanPham.getValueAt(row, 0);
				String stt = maLoaiSP.replaceAll("LSP", "");
				if (MessageDialogHelpers.showConfirmDialog(mainFrame, "Cảnh báo",
						"Bạn có chắc muốn xóa sản phẩm này") == JOptionPane.NO_OPTION) {
					return;
				}

				try {

					if (lspDAO.deleteLoaiSanPham(stt)) {
						MessageDialogHelpers.showMessageDialog(mainFrame, "Thông báo", "Xóa thành công");
						modelLoaiSP.setRowCount(0);
						loadDataToTblLoaiSanPham();
						cmbLoaiSanPham.removeAllItems();
						loadDataToCmbLoaiSanPham();

					} else {
						MessageDialogHelpers.showErrorDialog(mainFrame, "Lỗi", "Xóa không thành công");
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			} else {
				MessageDialogHelpers.showErrorDialog(mainFrame, "Lỗi", "Phải chọn 1 dòng trong bảng");
			}

		}

	}
	
	public void search(String str) {
		modelSanPham = (DefaultTableModel) tblSanPham.getModel();
		TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(modelSanPham);
		tblSanPham.setRowSorter(trs);

		if (cmbTim.getSelectedItem().toString().equals("Tìm theo tên sản phẩm")) {
			trs.setRowFilter(RowFilter.regexFilter(str, 1));
		}
	}

}
