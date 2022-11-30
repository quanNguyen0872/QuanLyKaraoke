/**
 * 
 * Tác giả: La Võ Minh Quân - MSSV:19441111 - Nhóm 4
 * Ngày tạo: 24/10/2021
 * Cập nhật: 30/10/2021
 * Mô tả: Giao diện quản lý nhân viên, bao gồm các chức năng xem, sửa, xóa, tìm kiếm nhân viên và danh sách nhân viên
 * 
 */

package application;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import dao.NhanVienDAO;
import entity.NhanVien;
import helpers.DataValidator;
import helpers.ImageHelpers;
import helpers.MessageDialogHelpers;

import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PnlQuanLyNhanVien extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtMaNhanVien;
	private JTextField txtHoTen;
	private JTextField txtEmail;
	private JTextField txtSoDT;
	private JTextField txtNamSinh;
	private JRadioButton radMale;
	private JTextArea txaDiaChi;
	private JTable tblDanhSachNhanVien;
	private DefaultTableModel dfModel;
	private JRadioButton radFemale;
	private JButton btnThemNV;
	private JButton btnCapNhat;
	private JButton btnLamMoi;
	private JButton btnMoHinh;
	private JLabel lblImage;
	private ButtonGroup rdGoup;

	private MainFrame mainFrame;
	private byte[] personalImage;
	private JComboBox<String> cmbTim;
	private JTextField txtTim;

	/**
	 * Create the panel.
	 */
	public PnlQuanLyNhanVien() {

		/** set font and color **/
		Color whiteColor = new Color(255, 255, 255);
		Color mainColor = new Color(88, 159, 177);
		Color hoverColor = new Color(121, 178, 192);
		Color seperatorColor = new Color(204, 204, 204);
		Color hovertextColor = new Color(250, 130, 49);
		Color blackColor = new Color(51, 51, 51);
		Color tableHeaderColor = new Color(42, 143, 178);

		Font tahoma14 = new Font("Tahoma", Font.BOLD, 14);
		Font tahoma16 = new Font("Tahoma", Font.PLAIN, 16);
		Font tahoma16Bold = new Font("Tahoma", Font.BOLD, 16);
		Font tahoma18 = new Font("Tahoma", Font.BOLD, 18);

		JPanel panel = new JPanel();
		panel.setBackground(whiteColor);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.text);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.text);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(whiteColor);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
				.createSequentialGroup().addComponent(panel, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 363, Short.MAX_VALUE)))
				.addComponent(panel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 818, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(panel_3,
										GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)));

		JLabel lblTmKim = new JLabel("Tìm kiếm");
		lblTmKim.setForeground(new Color(0, 102, 102));
		lblTmKim.setFont(tahoma18);

		cmbTim = new JComboBox<String>();
		cmbTim.addItem("Tìm theo mã nhân viên");
		cmbTim.addItem("Tìm theo tên nhân viên");
		cmbTim.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		txtTim = new JTextField();
		txtTim.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String keyword =  txtTim.getText();
				search(keyword);
			}
		});
		txtTim.setColumns(10);
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTmKim, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
						.addGroup(Alignment.TRAILING, gl_panel_3.createSequentialGroup()
							.addComponent(cmbTim, 0, 172, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtTim, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTmKim, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(cmbTim, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtTim, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(132, Short.MAX_VALUE))
		);
		panel_3.setLayout(gl_panel_3);

		btnThemNV = new JButton("Thêm");
		btnThemNV.setFocusTraversalKeysEnabled(false);
		btnThemNV.setFocusPainted(false);
		btnThemNV.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnThemNV.setBackground(hoverColor);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnThemNV.setBackground(mainColor);
			}
		});
		btnThemNV.setBorder(null);
		btnThemNV.setForeground(whiteColor);
		btnThemNV.setIcon(new ImageIcon(getClass().getResource("/images/icons8-add-20.png")));
		btnThemNV.setBackground(mainColor);
		btnThemNV.setFont(tahoma14);
		btnThemNV.setFocusable(false);

		btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.setFocusTraversalKeysEnabled(false);
		btnCapNhat.setFocusPainted(false);
		btnCapNhat.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnCapNhat.setBackground(hoverColor);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnCapNhat.setBackground(mainColor);
			}
		});
		btnCapNhat.setBorder(null);
		btnCapNhat.setForeground(whiteColor);
		btnCapNhat.setIcon(new ImageIcon(getClass().getResource("/images/icons8-pencil-16.png")));
		btnCapNhat.setFont(tahoma14);
		btnCapNhat.setBackground(mainColor);
		btnCapNhat.setFocusable(false);

		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setFocusTraversalKeysEnabled(false);
		btnLamMoi.setFocusPainted(false);
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
		btnLamMoi.setBackground(mainColor);
		btnLamMoi.setFont(tahoma14);
		btnLamMoi.setFocusable(false);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(whiteColor);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup().addContainerGap()
						.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE).addGap(29)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(btnCapNhat, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
								.addComponent(btnLamMoi, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
								.addComponent(btnThemNV, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
						.addGap(21)));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup().addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup().addContainerGap().addComponent(panel_4,
								GroupLayout.PREFERRED_SIZE, 175, Short.MAX_VALUE))
						.addGroup(gl_panel_1.createSequentialGroup().addGap(61)
								.addComponent(btnThemNV, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(btnCapNhat, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE).addGap(18)
								.addComponent(btnLamMoi, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)))
						.addGap(25)));

		lblImage = new JLabel("");
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblImage.setIcon(new ImageIcon(getClass().getResource("/images/icons8-user-96.png")));
		lblImage.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblImage.setBackground(SystemColor.menu);

		btnMoHinh = new JButton("Mở hình");
		btnMoHinh.setForeground(Color.BLACK);
		btnMoHinh.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnMoHinh.setFocusable(false);
		btnMoHinh.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnMoHinh.setBackground(whiteColor);

		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4
				.setHorizontalGroup(
						gl_panel_4.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_4.createSequentialGroup().addGap(51)
										.addComponent(btnMoHinh, GroupLayout.PREFERRED_SIZE, 73,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap())
								.addGroup(Alignment.TRAILING, gl_panel_4.createSequentialGroup()
										.addContainerGap(22, Short.MAX_VALUE).addComponent(lblImage,
												GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
										.addGap(23)));
		gl_panel_4.setVerticalGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup().addContainerGap()
						.addComponent(lblImage, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(btnMoHinh, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panel_4.setLayout(gl_panel_4);
		panel_1.setLayout(gl_panel_1);

		JScrollPane scrollPane = new JScrollPane();

		JLabel lblNewLabel_2 = new JLabel("Danh sách nhân viên");
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setFont(tahoma18);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2
				.setHorizontalGroup(
						gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_2.createSequentialGroup().addContainerGap()
										.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
												.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
														767, Short.MAX_VALUE)
												.addComponent(lblNewLabel_2))
										.addContainerGap()));
		gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup().addComponent(lblNewLabel_2)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE).addContainerGap()));

		radMale = new JRadioButton("Nam");
		radMale.setFont(tahoma16);
		radMale.setSelected(true);
		radMale.setBackground(whiteColor);

		radFemale = new JRadioButton("N\u1EEF");
		radFemale.setFont(tahoma16);
		radFemale.setBackground(SystemColor.text);

		rdGoup = new ButtonGroup();
		rdGoup.add(radMale);
		rdGoup.add(radFemale);

		/** create table danh sách nhân viên **/

		tblDanhSachNhanVien = new JTable();
		initTable();
		tblDanhSachNhanVien.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int row = tblDanhSachNhanVien.getSelectedRow();

					if (row >= 0) {
						String id = (String) tblDanhSachNhanVien.getValueAt(row, 0);
						NhanVienDAO nvDAO = new NhanVienDAO();
						NhanVien nv = nvDAO.getNhanVienTheoMa(id);

						if (nv != null) {
							txtMaNhanVien.setText(id);
							txtHoTen.setText(nv.getHoTen());
							txtEmail.setText(nv.getEmail());
							txtNamSinh.setText(nv.getNamSinh());
							txtSoDT.setText(nv.getSoDT());
							txaDiaChi.setText(nv.getDiaChi());

							boolean isMale = nv.getGioiTinh() == 1 ? true : false;
							if (isMale) {
								radMale.setSelected(true);
								radFemale.setSelected(false);
							} else {
								radFemale.setSelected(true);
								radMale.setSelected(false);
							}

							if (nv.getHinh() != null) {
								Image img = ImageHelpers.createImageFromByteArray(nv.getHinh(), "jpg");
								lblImage.setIcon(new ImageIcon(img));
								personalImage = nv.getHinh();
							} else {
								personalImage = nv.getHinh();

								lblImage.setIcon(new ImageIcon(getClass().getResource("/images/icons8-user-96.png")));
							}
						}
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		tblDanhSachNhanVien.getTableHeader().setFont(tahoma16Bold);
		tblDanhSachNhanVien.getTableHeader().setBackground(tableHeaderColor);
		tblDanhSachNhanVien.getTableHeader().setForeground(whiteColor);
		tblDanhSachNhanVien.setFont(tahoma16);
		tblDanhSachNhanVien.setRowHeight(28);
		tblDanhSachNhanVien.setAutoCreateRowSorter(true);

		// chỉ cho khách hàng được chọn 1 dòng ở 1 thời điểm
		tblDanhSachNhanVien.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tblDanhSachNhanVien);

		loadDataToTable();
		/** ================================ **/

		panel_2.setLayout(gl_panel_2);
		JLabel lblNewLabel = new JLabel("TH\u00D4NG TIN NH\u00C2N VI\u00CAN");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(tahoma18);

		JSeparator separator = new JSeparator();

		JLabel lblNewLabel_1 = new JLabel("M\u00E3 nh\u00E2n vi\u00EAn:");
		lblNewLabel_1.setFont(tahoma16);

		JLabel lblNewLabel_1_1 = new JLabel("H\u1ECD t\u00EAn:");
		lblNewLabel_1_1.setFont(tahoma16);

		JLabel lblNewLabel_1_1_1 = new JLabel("Email:");
		lblNewLabel_1_1_1.setFont(tahoma16);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("S\u1ED1 \u0111i\u1EC7n tho\u1EA1i:");
		lblNewLabel_1_1_1_1.setFont(tahoma16);

		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("N\u0103m sinh:");
		lblNewLabel_1_1_1_1_1.setFont(tahoma16);

		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Gi\u1EDBi t\u00EDnh:");
		lblNewLabel_1_1_1_1_1_1.setFont(tahoma16);

		JLabel lblNewLabel_1_1_1_1_1_1_1 = new JLabel("\u0110\u1ECBa ch\u1EC9:");
		lblNewLabel_1_1_1_1_1_1_1.setFont(tahoma16);

		txtHoTen = new JTextField();
		txtHoTen.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtHoTen.setBorder(BorderFactory.createLineBorder(hoverColor));
				txtHoTen.setForeground(hovertextColor);
			}

			@Override
			public void focusLost(FocusEvent e) {
				txtHoTen.setBorder(BorderFactory.createLineBorder(seperatorColor));
				txtHoTen.setForeground(blackColor);
			}
		});
		txtHoTen.setFont(tahoma16);
		txtHoTen.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtEmail.setBorder(BorderFactory.createLineBorder(hoverColor));
				txtEmail.setForeground(hovertextColor);
			}

			@Override
			public void focusLost(FocusEvent e) {
				txtEmail.setBorder(BorderFactory.createLineBorder(seperatorColor));
				txtEmail.setForeground(blackColor);
			}
		});
		txtEmail.setFont(tahoma16);
		txtEmail.setColumns(10);

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

		txtNamSinh = new JTextField();
		txtNamSinh.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtNamSinh.setBorder(BorderFactory.createLineBorder(hoverColor));
				txtNamSinh.setForeground(hovertextColor);
			}

			@Override
			public void focusLost(FocusEvent e) {
				txtNamSinh.setBorder(BorderFactory.createLineBorder(seperatorColor));
				txtNamSinh.setForeground(blackColor);
			}
		});
		txtNamSinh.setFont(tahoma16);
		txtNamSinh.setColumns(10);

		txaDiaChi = new JTextArea();
		txaDiaChi.setBorder(new LineBorder(Color.LIGHT_GRAY));
		txaDiaChi.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txaDiaChi.setBorder(BorderFactory.createLineBorder(hoverColor));
				txaDiaChi.setForeground(hovertextColor);
			}

			@Override
			public void focusLost(FocusEvent e) {
				txaDiaChi.setBorder(BorderFactory.createLineBorder(seperatorColor));
				txaDiaChi.setForeground(blackColor);
			}
		});
		txaDiaChi.setFont(tahoma16);
		txaDiaChi.setBackground(Color.WHITE);

		txtMaNhanVien = new JTextField();
		txtMaNhanVien.setEditable(false);
		txtMaNhanVien.setFont(tahoma16);
		txtMaNhanVien.setColumns(10);

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, 434, Short.MAX_VALUE)
						.addComponent(lblNewLabel)
						.addGroup(gl_panel.createSequentialGroup().addGroup(gl_panel
								.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblNewLabel_1_1_1_1, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
								.addComponent(lblNewLabel_1_1_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(lblNewLabel_1_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(lblNewLabel_1_1_1_1_1_1_1, Alignment.LEADING,
												GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblNewLabel_1_1_1_1_1_1, Alignment.LEADING,
												GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblNewLabel_1_1_1_1_1, Alignment.LEADING,
												GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)))
								.addGap(10)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(txaDiaChi, GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
										.addGroup(gl_panel.createSequentialGroup().addComponent(radMale).addGap(54)
												.addComponent(radFemale, GroupLayout.PREFERRED_SIZE, 47,
														GroupLayout.PREFERRED_SIZE))
										.addComponent(txtNamSinh, GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
										.addComponent(txtSoDT, GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
										.addComponent(txtEmail, GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
										.addComponent(txtHoTen, GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
										.addComponent(txtMaNhanVien, GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE))))
				.addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addContainerGap().addComponent(lblNewLabel).addGap(12)
				.addComponent(
						separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGap(12)
				.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING).addComponent(lblNewLabel_1)
						.addComponent(txtMaNhanVien, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtHoTen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(
						gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 19,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(
						gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1_1_1_1, GroupLayout.PREFERRED_SIZE, 19,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(txtSoDT, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(
						gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1_1_1_1_1, GroupLayout.PREFERRED_SIZE, 19,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(txtNamSinh, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1_1_1_1_1, GroupLayout.PREFERRED_SIZE, 19,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(radFemale).addComponent(radMale))
				.addGap(18)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1_1_1_1_1_1, GroupLayout.PREFERRED_SIZE, 19,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(txaDiaChi, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);
		setLayout(groupLayout);

		btnThemNV.addActionListener(this);
		btnCapNhat.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnMoHinh.addActionListener(this);
	}

	private void lamMoiText() {
		txtMaNhanVien.setText("");
		txtHoTen.setText("");
		txtEmail.setText("");
		txaDiaChi.setText("");
		txtSoDT.setText("");
		txtNamSinh.setText("");
		txtTim.setText("");
		personalImage = null;
		// set hình hảnh mặc định khi người dùng k chọn ảnh nào
		lblImage.setIcon(new ImageIcon(getClass().getResource("/images/icons8-user-96.png")));
		txtHoTen.requestFocus();
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

		dfModel.setColumnIdentifiers(new String[] { "Mã nhân viên", "Họ tên", "Email", "Số điện thoại", "Năm sinh",
				"Giới tính", "Địa chỉ" });
		tblDanhSachNhanVien.setModel(dfModel);
	}

	/**
	 * tải dữ liệu từ cơ sở dữ liệu vào table
	 */
	private void loadDataToTable() {
		try {
			NhanVienDAO nvDAO = new NhanVienDAO();
			List<NhanVien> listNV = nvDAO.getDanhSachNhanVien();
			for (NhanVien nhanVien : listNV) {
				dfModel.addRow(new Object[] { nhanVien.getMaNhanVien(), nhanVien.getHoTen(), nhanVien.getEmail(),
						nhanVien.getSoDT(), nhanVien.getNamSinh(), nhanVien.getGioiTinh() == 1 ? "Nam" : "Nữ",
						nhanVien.getDiaChi() });
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * kiểm tra biểu thức chính quy
	 * 
	 * @param sb
	 */
	private void dataValidate(StringBuilder sb) {
		DataValidator.validateEmpty(txtNamSinh, sb, "Năm sinh không được để trống");
		DataValidator.validateEmpty(txtSoDT, sb, "Số điện thoại không được để trống");
		DataValidator.validateEmpty(txtEmail, sb, "Email không được để trống");
		DataValidator.validateEmpty(txtHoTen, sb, "Tên nhân viên không được để trống");

		DataValidator.validateNamSinh(txtNamSinh, sb, "Có 4 chữ số , không có kí tự và kí tự đặt biệt");
		DataValidator.validateSoDT(txtSoDT, sb,
				"Số điện thoại sai định dạng, phải có từ 9-10 chữ số, không có kí tự. Ví dụ:0788775877");
		DataValidator.validateEmail(txtEmail, sb, "Email sai định dạng. Ví dụ: minhquan@gmail.com");
		DataValidator.validateVietnameseCharacters(txtHoTen, sb,
				"Họ tên không được có kí tự đặt biệt. Ví dụ:Lê Hoàng Long");
	}

	private NhanVien createNhanVien() {
		NhanVien nv = new NhanVien();

		nv.setHoTen(txtHoTen.getText());
		nv.setGioiTinh(radMale.isSelected() ? 1 : 0);
		nv.setSoDT(txtSoDT.getText());
		nv.setEmail(txtEmail.getText());
		nv.setDiaChi(txaDiaChi.getText());
		nv.setNamSinh(txtNamSinh.getText());
		nv.setHinh(personalImage);

		return nv;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		// THÊM NHÂN VIÊN
		if (o.equals(btnThemNV)) {
			NhanVien nv = createNhanVien();
			NhanVienDAO nhanVienDAO = new NhanVienDAO();

			StringBuilder sb = new StringBuilder();
			dataValidate(sb);

			if (sb.length() > 0) {
				MessageDialogHelpers.showErrorDialog(mainFrame, "Lỗi", sb.toString());
				return;
			}

			if (nhanVienDAO.checkExist(txtEmail.getText().trim(), txtSoDT.getText().trim())) {
				MessageDialogHelpers.showErrorDialog(mainFrame, "Lỗi", "Trùng số điện thoại hoặc email");
				return;
			} else {
				if (nhanVienDAO.addNhanVien(nv)) {
					MessageDialogHelpers.showMessageDialog(mainFrame, "Thông báo", "Nhân viên đã thêm thành công");
					dfModel.setRowCount(0);
					loadDataToTable();
					cmbTim.removeAllItems();
				} else {
					MessageDialogHelpers.showErrorDialog(mainFrame, "Lỗi", "Thêm không thành công");
				}
			}
			lamMoiText();
		}

		// CẬP NHẬT NHÂN VIÊN
		if (o.equals(btnCapNhat)) {
			NhanVienDAO nhanVienDAO = new NhanVienDAO();
			int row = tblDanhSachNhanVien.getSelectedRow();

			if (row >= 0) {
				StringBuilder sb = new StringBuilder();
				dataValidate(sb);

				if (sb.length() > 0) {
					MessageDialogHelpers.showErrorDialog(mainFrame, "Lỗi", sb.toString());
					return;
				}

				int isCapNhat = MessageDialogHelpers.showConfirmDialog(mainFrame, "Cảnh báo",
						"Bạn có chắc muốn cập nhật cho nhân viên này");
				if (isCapNhat == JOptionPane.NO_OPTION) {
					return;
				} else if (isCapNhat == JOptionPane.CLOSED_OPTION) {
					return;
				}

				try {
					NhanVien nv = createNhanVien();
					nv.setMaNhanVien(txtMaNhanVien.getText());

					if (nhanVienDAO.updateNhanVien(nv)) {
						MessageDialogHelpers.showMessageDialog(mainFrame, "Thông báo",
								"Nhân viên đã cập nhật thành công");
						dfModel.setRowCount(0);
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
			lamMoiText();
		}

		// MỞ HÌNH
		if (o.equals(btnMoHinh)) {
			JFileChooser chooser = new JFileChooser();
			chooser.setFileFilter(new FileFilter() {

				@Override
				public String getDescription() {
					return "Image file ( .jpg)";
				}

				@Override
				public boolean accept(File f) {
					if (f.isDirectory()) {
						return true;
					} else {
						return f.getName().toLowerCase().endsWith(".jpg");
					}
				}
			});

			if (chooser.showOpenDialog(mainFrame) == JFileChooser.CANCEL_OPTION) {
				return;
			}

			File file = chooser.getSelectedFile();
			try {
				ImageIcon icon = new ImageIcon(file.getPath());
				Image img = ImageHelpers.resize(icon.getImage(), 140, 160);
				ImageIcon resizeIcon = new ImageIcon(img);
				lblImage.setIcon(resizeIcon);
				personalImage = ImageHelpers.toByteArray(img, "jpg");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		// LÀM MỚI
		if (o.equals(btnLamMoi)) {
			lamMoiText();
			dfModel.setRowCount(0);
			loadDataToTable();
			tblDanhSachNhanVien.setRowSorter(null);
		}
	}
	
	
	public void search(String str) {
		dfModel = (DefaultTableModel) tblDanhSachNhanVien.getModel();
		TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(dfModel);
		tblDanhSachNhanVien.setRowSorter(trs);

		if (cmbTim.getSelectedItem().toString().equals("Tìm theo mã nhân viên")) {
			trs.setRowFilter(RowFilter.regexFilter(str, 0));
		}

		if (cmbTim.getSelectedItem().toString().equals("Tìm theo tên nhân viên")) {
			trs.setRowFilter(RowFilter.regexFilter(str, 1));
		}
	}
}
