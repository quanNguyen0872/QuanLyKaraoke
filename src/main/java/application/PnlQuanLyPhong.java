/**
 * Tác giả: Nguyễn Hồng Quân - mssv:19445101 - Nhóm 4
 * Mô tả: Giao Diện Chức Năng Quản lý Phòng.
 * Ngày tạo: 27/10/2021
 */
package application;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.DebugGraphics;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import dao.LoaiPhongDAO;
import dao.PhongDAO;
import entity.LoaiPhong;
import entity.Phong;
import helpers.MessageDialogHelpers;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ImageIcon;

public class PnlQuanLyPhong extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txt_TenLoaiPhong;
	private JTextField txt_DonGia;
	private JTextField txt_MaPhong;
	private JTextField txt_TenPhong;
	private JTextField txt_TrangThai;
	private JComboBox<String> cmb_TimKiemPhong;
	private DefaultTableModel model;
	private JComboBox<String> cmb_MaLoaiPhong;
	private JTable tbl_DanhSachPhong;
	private JButton btn_ThemPhong;
	private MainFrame mainFrame;

	/**
	 * Create the panel.
	 */
	public PnlQuanLyPhong() {

		/** Font and color **/
		Color whiteColor = new Color(255, 255, 255);
		Color blackColor = new Color(51, 51, 51);
		Color hoverColor = new Color(121, 178, 192);
		Color hovertextColor = new Color(250, 130, 49);
		Color mainColor = new Color(88, 159, 177);
		Color seperatorColor = new Color(204, 204, 204);
		Color tableHeaderColor = new Color(42, 143, 178);

		Font tahoma16 = new Font("Tahoma", Font.PLAIN, 16);
		Font tahoma16Bold = new Font("Tahoma", Font.BOLD, 16);
		Font tahoma14 = new Font("Tahoma", Font.PLAIN, 14);
		Font tahoma20 = new Font("Tahoma", Font.PLAIN, 20);

		JPanel pnl_LoaiPhong = new JPanel();
		pnl_LoaiPhong.setBackground(whiteColor);

		JPanel pnl_PhongHat = new JPanel();
		pnl_PhongHat.setBackground(whiteColor);

		JPanel pnl_ChucNang_QLP = new JPanel();
		pnl_ChucNang_QLP.setBackground(whiteColor);

		JPanel pnl_DSPhong = new JPanel();
		pnl_DSPhong.setBackground(whiteColor);

		tbl_DanhSachPhong = new JTable();
		tbl_DanhSachPhong.setFocusable(false);
		tbl_DanhSachPhong.setFocusTraversalKeysEnabled(false);
		initTable();

		tbl_DanhSachPhong.getTableHeader().setBackground(tableHeaderColor);
		tbl_DanhSachPhong.getTableHeader().setForeground(whiteColor);
		tbl_DanhSachPhong.getTableHeader().setFont(tahoma16Bold);
		tbl_DanhSachPhong.setFont(tahoma16);
		tbl_DanhSachPhong.setRowHeight(28);
		tbl_DanhSachPhong.setAutoCreateRowSorter(true);
		tbl_DanhSachPhong.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		/**
		 * load dữ liệu từ table lên jtext
		 */
		tbl_DanhSachPhong.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int row = tbl_DanhSachPhong.getSelectedRow();

					if (row >= 0) {
						String id = (String) tbl_DanhSachPhong.getValueAt(row, 0);
						PhongDAO phongDAO = new PhongDAO();
						Phong phong = phongDAO.getPhongTheoMa(id);

						if (phong != null) {
							cmb_MaLoaiPhong.setSelectedItem(phong.getLoaiPhong().getMaLoaiPhong());
							txt_MaPhong.setText(phong.getMaPhong());
							txt_TenPhong.setText(phong.getTenPhong());
							txt_TrangThai.setText(phong.getTrangThai());
						}
					}

				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});

		JScrollPane scr_DanhSachPhong = new JScrollPane(tbl_DanhSachPhong);
		scr_DanhSachPhong.setBackground(whiteColor);

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(pnl_LoaiPhong, GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(pnl_PhongHat, GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE).addGap(8))
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(pnl_ChucNang_QLP, GroupLayout.DEFAULT_SIZE, 995, Short.MAX_VALUE)
						.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(pnl_DSPhong, GroupLayout.DEFAULT_SIZE, 995, Short.MAX_VALUE).addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(pnl_PhongHat, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(pnl_LoaiPhong, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(pnl_ChucNang_QLP, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(pnl_DSPhong, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)));
		pnl_DSPhong.setLayout(null);

		JLabel lbl_DSPhongHat = new JLabel("Danh Sách Phòng Hát");
		lbl_DSPhongHat.setFont(tahoma20);
		GroupLayout gl_pnl_DSPhong = new GroupLayout(pnl_DSPhong);
		gl_pnl_DSPhong.setHorizontalGroup(gl_pnl_DSPhong.createParallelGroup(Alignment.LEADING).addGroup(gl_pnl_DSPhong
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_pnl_DSPhong.createParallelGroup(Alignment.LEADING)
						.addComponent(lbl_DSPhongHat, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE)
						.addComponent(scr_DanhSachPhong, GroupLayout.DEFAULT_SIZE, 928, Short.MAX_VALUE))
				.addContainerGap()));
		gl_pnl_DSPhong.setVerticalGroup(gl_pnl_DSPhong.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnl_DSPhong.createSequentialGroup().addGap(8)
						.addComponent(lbl_DSPhongHat, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scr_DanhSachPhong, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
						.addContainerGap()));
		pnl_DSPhong.setLayout(gl_pnl_DSPhong);

		btn_ThemPhong = new JButton("Thêm Phòng");
		btn_ThemPhong.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btn_ThemPhong.setBackground(hoverColor);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btn_ThemPhong.setBackground(mainColor);
			}
		});
		btn_ThemPhong.setFocusable(false);
		btn_ThemPhong.setFocusTraversalKeysEnabled(false);
		btn_ThemPhong.setIcon(new ImageIcon(getClass().getResource("/images/icons8-add-20.png")));
		btn_ThemPhong.setForeground(whiteColor);
		btn_ThemPhong.setFont(tahoma16Bold);
		btn_ThemPhong.setFocusPainted(false);
		btn_ThemPhong.setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);
		btn_ThemPhong.setBorder(null);
		btn_ThemPhong.setBackground(mainColor);

		JButton btn_XoaPhong = new JButton("Xóa Phòng");
		btn_XoaPhong.setFocusable(false);
		btn_XoaPhong.setFocusTraversalKeysEnabled(false);
		btn_XoaPhong.setIcon(new ImageIcon(getClass().getResource("/images/icons8-remove-24.png")));
		btn_XoaPhong.setForeground(whiteColor);
		btn_XoaPhong.setFont(tahoma16Bold);
		btn_XoaPhong.setFocusPainted(false);
		btn_XoaPhong.setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);
		btn_XoaPhong.setBorder(null);
		btn_XoaPhong.setBackground(mainColor);

		JButton btn_CapNhat = new JButton("Cập Nhật");
		btn_CapNhat.setFocusable(false);
		btn_CapNhat.setFocusTraversalKeysEnabled(false);
		btn_CapNhat.setIcon(new ImageIcon(getClass().getResource("/images/icons8-pencil-16.png")));
		btn_CapNhat.setForeground(whiteColor);
		btn_CapNhat.setFont(tahoma16Bold);
		btn_CapNhat.setFocusPainted(false);
		btn_CapNhat.setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);
		btn_CapNhat.setBorder(null);
		btn_CapNhat.setBackground(mainColor);

		cmb_TimKiemPhong = new JComboBox<String>();
		cmb_TimKiemPhong.setFont(tahoma16);
		cmb_TimKiemPhong.addItem("Trống");
		cmb_TimKiemPhong.addItem("Đã Đặt");
		cmb_TimKiemPhong.addItem("Đang Thuê");
		GroupLayout gl_pnl_ChucNang_QLP = new GroupLayout(pnl_ChucNang_QLP);
		gl_pnl_ChucNang_QLP.setHorizontalGroup(gl_pnl_ChucNang_QLP.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnl_ChucNang_QLP.createSequentialGroup().addContainerGap()
						.addComponent(btn_ThemPhong, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE).addGap(18)
						.addComponent(btn_XoaPhong, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE).addGap(18)
						.addComponent(btn_CapNhat, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE).addGap(320)
						.addComponent(cmb_TimKiemPhong, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));

		gl_pnl_ChucNang_QLP.setVerticalGroup(gl_pnl_ChucNang_QLP.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnl_ChucNang_QLP.createSequentialGroup().addContainerGap(11, Short.MAX_VALUE)
						.addGroup(gl_pnl_ChucNang_QLP.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnl_ChucNang_QLP.createParallelGroup(Alignment.BASELINE)
										.addComponent(btn_CapNhat, GroupLayout.PREFERRED_SIZE, 31,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(cmb_TimKiemPhong, GroupLayout.PREFERRED_SIZE, 29,
												GroupLayout.PREFERRED_SIZE))
								.addComponent(btn_XoaPhong, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
								.addComponent(btn_ThemPhong, GroupLayout.PREFERRED_SIZE, 31,
										GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));
		pnl_ChucNang_QLP.setLayout(gl_pnl_ChucNang_QLP);

		JLabel lbl_PhongHat = new JLabel("Phòng Hát");
		lbl_PhongHat.setFont(tahoma20);

		JSeparator spr_PhongHat = new JSeparator();
		spr_PhongHat.setForeground(blackColor);

		JLabel lbl_MaPhong = new JLabel("Mã Phòng:");
		lbl_MaPhong.setFont(tahoma16);

		JLabel lbl_TenPhong = new JLabel("Tên Phòng:");
		lbl_TenPhong.setFont(tahoma16);

		JLabel lbl_TrangThaiPhong = new JLabel("Trạng Thái:");
		lbl_TrangThaiPhong.setFont(tahoma16);

		txt_MaPhong = new JTextField();
		txt_MaPhong.setFont(tahoma16);
		txt_MaPhong.setColumns(10);
		txt_MaPhong.setEditable(false);

		txt_TenPhong = new JTextField();
		txt_TenPhong.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txt_TenPhong.setBorder(BorderFactory.createLineBorder(hoverColor));
				txt_TenPhong.setForeground(hovertextColor);
			}

			@Override
			public void focusLost(FocusEvent e) {
				txt_TenPhong.setBorder(BorderFactory.createLineBorder(seperatorColor));
				txt_TenPhong.setForeground(blackColor);
			}
		});
		txt_TenPhong.setFont(tahoma16);
		txt_TenPhong.setColumns(10);

		txt_TrangThai = new JTextField();
		txt_TrangThai.setFont(tahoma16);
		txt_TrangThai.setColumns(10);
		txt_TrangThai.setText("Trống");
		txt_TrangThai.setEditable(false);
		GroupLayout gl_pnl_PhongHat = new GroupLayout(pnl_PhongHat);
		gl_pnl_PhongHat.setHorizontalGroup(gl_pnl_PhongHat.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnl_PhongHat.createSequentialGroup().addContainerGap().addGroup(gl_pnl_PhongHat
						.createParallelGroup(Alignment.LEADING)
						.addComponent(spr_PhongHat, GroupLayout.PREFERRED_SIZE, 442, Short.MAX_VALUE)
						.addComponent(lbl_PhongHat, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_pnl_PhongHat.createSequentialGroup()
								.addComponent(lbl_MaPhong, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(txt_MaPhong, GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE))
						.addGroup(gl_pnl_PhongHat.createSequentialGroup()
								.addGroup(gl_pnl_PhongHat.createParallelGroup(Alignment.LEADING)
										.addComponent(lbl_TenPhong, GroupLayout.PREFERRED_SIZE, 119,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lbl_TrangThaiPhong, GroupLayout.PREFERRED_SIZE, 119,
												GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_pnl_PhongHat.createParallelGroup(Alignment.LEADING)
										.addComponent(txt_TrangThai, GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
										.addComponent(txt_TenPhong, GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE))))
						.addContainerGap()));
		gl_pnl_PhongHat.setVerticalGroup(gl_pnl_PhongHat.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnl_PhongHat.createSequentialGroup().addContainerGap()
						.addComponent(lbl_PhongHat, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(spr_PhongHat, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_pnl_PhongHat.createParallelGroup(Alignment.BASELINE)
								.addComponent(lbl_MaPhong, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addComponent(txt_MaPhong, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_pnl_PhongHat.createParallelGroup(Alignment.BASELINE)
								.addComponent(txt_TenPhong, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lbl_TenPhong, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_pnl_PhongHat.createParallelGroup(Alignment.BASELINE)
								.addComponent(txt_TrangThai, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lbl_TrangThaiPhong, GroupLayout.PREFERRED_SIZE, 20,
										GroupLayout.PREFERRED_SIZE))
						.addContainerGap(72, Short.MAX_VALUE)));
		pnl_PhongHat.setLayout(gl_pnl_PhongHat);

		JLabel lbl_LoaiPhong = new JLabel("Loại Phòng");
		lbl_LoaiPhong.setFont(tahoma20);

		JSeparator spr_LoaiPhong = new JSeparator();
		spr_LoaiPhong.setForeground(blackColor);

		JLabel lbl_MaLoaiPhong = new JLabel("Mã Loại Phòng:");
		lbl_MaLoaiPhong.setFont(tahoma16);

		cmb_MaLoaiPhong = new JComboBox<String>();
		cmb_MaLoaiPhong.setFont(tahoma16);

		JLabel lbl_TenLoaiPhong = new JLabel("Tên Loại Phòng:");
		lbl_TenLoaiPhong.setFont(tahoma16);

		txt_TenLoaiPhong = new JTextField();
		txt_TenLoaiPhong.setFont(tahoma16);
		txt_TenLoaiPhong.setColumns(10);
		txt_TenLoaiPhong.setEditable(false);

		JLabel lbl_DonGiaPhong = new JLabel("Đơn Giá:");
		lbl_DonGiaPhong.setFont(tahoma16);

		txt_DonGia = new JTextField();
		txt_DonGia.setFont(tahoma16);
		txt_DonGia.setColumns(10);
		txt_DonGia.setEditable(false);
		GroupLayout gl_pnl_LoaiPhong = new GroupLayout(pnl_LoaiPhong);
		gl_pnl_LoaiPhong.setHorizontalGroup(gl_pnl_LoaiPhong.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnl_LoaiPhong.createSequentialGroup().addContainerGap().addGroup(gl_pnl_LoaiPhong
						.createParallelGroup(Alignment.LEADING)
						.addComponent(spr_LoaiPhong, GroupLayout.PREFERRED_SIZE, 458, Short.MAX_VALUE)
						.addComponent(lbl_LoaiPhong, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_pnl_LoaiPhong.createSequentialGroup()
								.addComponent(lbl_DonGiaPhong, GroupLayout.PREFERRED_SIZE, 119,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(txt_DonGia, GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE))
						.addGroup(gl_pnl_LoaiPhong.createSequentialGroup()
								.addGroup(gl_pnl_LoaiPhong.createParallelGroup(Alignment.LEADING)
										.addComponent(lbl_TenLoaiPhong, GroupLayout.PREFERRED_SIZE, 119,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lbl_MaLoaiPhong, GroupLayout.PREFERRED_SIZE, 119,
												GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(gl_pnl_LoaiPhong.createParallelGroup(Alignment.LEADING)
										.addComponent(cmb_MaLoaiPhong, 0, 329, Short.MAX_VALUE).addComponent(
												txt_TenLoaiPhong, GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE))))
						.addContainerGap()));
		gl_pnl_LoaiPhong.setVerticalGroup(gl_pnl_LoaiPhong.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnl_LoaiPhong.createSequentialGroup().addContainerGap()
						.addComponent(lbl_LoaiPhong, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(spr_LoaiPhong, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_pnl_LoaiPhong.createParallelGroup(Alignment.BASELINE).addComponent(lbl_MaLoaiPhong)
								.addComponent(cmb_MaLoaiPhong, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_pnl_LoaiPhong.createParallelGroup(Alignment.LEADING)
								.addComponent(lbl_TenLoaiPhong, GroupLayout.PREFERRED_SIZE, 20,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(txt_TenLoaiPhong, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_pnl_LoaiPhong.createParallelGroup(Alignment.BASELINE)
								.addComponent(txt_DonGia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lbl_DonGiaPhong, GroupLayout.PREFERRED_SIZE, 20,
										GroupLayout.PREFERRED_SIZE))
						.addContainerGap(70, Short.MAX_VALUE)));
		pnl_LoaiPhong.setLayout(gl_pnl_LoaiPhong);
		setLayout(groupLayout);

		cmb_MaLoaiPhong.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				LoaiPhongDAO loaiPhongDAO = new LoaiPhongDAO();
				LoaiPhong loaiPhong = loaiPhongDAO.getLoaiPhongTheoMa(cmb_MaLoaiPhong.getSelectedItem().toString());
				txt_TenLoaiPhong.setText(loaiPhong.getTenLoaiPhong());
				txt_DonGia.setText(String.valueOf(loaiPhong.getDonGia()));

			}
		});

		btn_ThemPhong.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Phong phong = createPhong();
				PhongDAO phongDAO = new PhongDAO();
				if (phongDAO.addPhong(phong)) {
					MessageDialogHelpers.showMessageDialog(mainFrame, "Thông báo", "Phòng đã thêm thành công");
					model.setRowCount(0);
					loadDataToTable();
					lamMoiText();
				}

			}
		});

		/** btn cập nhật Phòng sự kiện **/

		btn_CapNhat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tbl_DanhSachPhong.getSelectedRow();
				if (row >= 0) {
					StringBuilder sb = new StringBuilder();
					if (sb.length() > 0) {
						MessageDialogHelpers.showErrorDialog(mainFrame, "Lỗi", sb.toString());
						return;
					}

					int isCapNhat = MessageDialogHelpers.showConfirmDialog(mainFrame, "Cảnh báo",
							"Bạn có chắc muốn cập nhật cho phòng này");
					if (isCapNhat == JOptionPane.NO_OPTION) {
						return;
					} else if (isCapNhat == JOptionPane.CLOSED_OPTION) {
						return;
					}

					try {
						Phong phong = createPhong();

						phong.setMaPhong(txt_MaPhong.getText());

						LoaiPhong loaiPhong = new LoaiPhong();
						loaiPhong.setMaLoaiPhong(cmb_MaLoaiPhong.getSelectedItem().toString());
						loaiPhong.setTenLoaiPhong(txt_TenLoaiPhong.getText());
						loaiPhong.setDonGia(Double.parseDouble(txt_DonGia.getText()));

						LoaiPhongDAO loaiPhongDAO = new LoaiPhongDAO();
						PhongDAO phongDAO = new PhongDAO();

						if (phongDAO.updatePhong(phong)) {
							MessageDialogHelpers.showMessageDialog(mainFrame, "Thông báo",
									"Phòng đã cập nhật thành công");
							model.setRowCount(0);
							loadDataToTable();
						} else {
							MessageDialogHelpers.showErrorDialog(mainFrame, "Lỗi", "Cập nhật không thành công");
						}

						if (loaiPhongDAO.updateLoaiPhong(loaiPhong)) {
							MessageDialogHelpers.showMessageDialog(mainFrame, "Thông báo",
									"Loại Phòng đã cập nhật thành công");
							model.setRowCount(0);
							loadDataToTable();
						} else {
							MessageDialogHelpers.showErrorDialog(mainFrame, "Lỗi", "Cập nhật không thành công");
						}

					} catch (Exception e2) {
						e2.printStackTrace();
					}
				} else {
					MessageDialogHelpers.showErrorDialog(mainFrame, "Lỗi", "Phải chọn một dòng trong bảng");
				}
				// ========================================//

				lamMoiText();
			}

		});

		btn_CapNhat.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btn_CapNhat.setBackground(hoverColor);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btn_CapNhat.setBackground(mainColor);
			}
		});

		btn_XoaPhong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String maPhong = null;
				PhongDAO phongDAO = new PhongDAO();
				int row = tbl_DanhSachPhong.getSelectedRow();

				if (row >= 0) {
					maPhong = (String) tbl_DanhSachPhong.getValueAt(row, 0);

					int isXoa = MessageDialogHelpers.showConfirmDialog(mainFrame, "Cảnh báo",
							"Bạn có chắc muốn xóa phòng này");
					if (isXoa == JOptionPane.NO_OPTION) {
						return;
					} else if (isXoa == JOptionPane.CLOSED_OPTION) {
						return;
					}

					try {
						if (phongDAO.deletePhong(maPhong)) {
							MessageDialogHelpers.showMessageDialog(mainFrame, "Thông báo", "Xóa thành công");
							model.setRowCount(0);
							loadDataToTable();
						} else {
							MessageDialogHelpers.showErrorDialog(mainFrame, "Lỗi", "Xóa không thành công");
						}
					} catch (Exception e2) {
						e2.printStackTrace();
					}

				} else {
					MessageDialogHelpers.showErrorDialog(mainFrame, "Cảnh báo", "Cần chọn 1 dòng cần xóa");
				}

			}
		});

		btn_XoaPhong.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btn_XoaPhong.setBackground(hoverColor);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btn_XoaPhong.setBackground(mainColor);
			}
		});

		loadDataToComboMaLoaiPhong();
		loadDataToTable();
	}

	private void initTable() {
		Object[] columns = { "Mã Phòng", "Tên Phòng", "Loại Phòng", "Đơn Giá", "Trạng Thái Phòng" };
		model = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {

				return false;
			}
		};
		model.setColumnIdentifiers(columns);
		tbl_DanhSachPhong.setModel(model);
	}

	/**
	 * tải dữ liệu từ cơ sở dữ liệu vào combobox mã nhân viên
	 */
	private void loadDataToComboMaLoaiPhong() {
		LoaiPhongDAO loaiPhongDAO = new LoaiPhongDAO();
		List<LoaiPhong> listLP = loaiPhongDAO.getDanhSachLoaiPhong();
		for (LoaiPhong lp : listLP) {
			cmb_MaLoaiPhong.addItem(lp.getMaLoaiPhong());
		}
	}

	public void loadDataToTable() {
		try {
			PhongDAO phongDAO = new PhongDAO();
			List<Phong> listPhong = phongDAO.getDanhSachPhong();
			for (Phong phong : listPhong) {
				model.addRow(
						new Object[] { phong.getMaPhong(), phong.getTenPhong(), phong.getLoaiPhong().getTenLoaiPhong(),
								phong.getLoaiPhong().getDonGia(), phong.getTrangThai() });
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Phong createPhong() {
		Phong phong = new Phong();
		LoaiPhongDAO loaiPhongDAO = new LoaiPhongDAO();
		phong.setTenPhong(txt_TenPhong.getText());
		LoaiPhong loaiPhong = loaiPhongDAO.getLoaiPhongTheoMa(cmb_MaLoaiPhong.getSelectedItem().toString());
		phong.setLoaiPhong(loaiPhong);
		phong.setTrangThai(txt_TrangThai.getText());

		return phong;
	}

	private void lamMoiText() {
		cmb_MaLoaiPhong.setSelectedIndex(0);
		txt_MaPhong.setText("");
		txt_TrangThai.setText("Trống");
		txt_TenPhong.setText("");
	}
}
