/**
 * La Võ Minh Quân - 19441111 - nhom 4
 * Mô tả: dialog danh sách các hóa đơn theo người lập
 */
package application;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import dao.ChiTietHoaDonDao;
import dao.HoaDonDao;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import helpers.MessageDialogHelpers;
import helpers.ShareData;

import java.awt.SystemColor;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JComboBox;
import java.awt.Desktop;

import org.apache.poi.ss.usermodel.Cell;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DialogDanhSachHoaDon extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtTimKiem;
	private JTable tblHoaDon, tblChiTietHoaDon;
	private DefaultTableModel dfModelHoaDon, dfModelChiTietHoaDon;
	private JButton btnQuayLai;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane;
	private JLabel lblNewLabel_3;
	private JComboBox<String> cmbTimHD;
	private JButton btnXuatHoaDon;
	private JButton btnLammoi;

	/**
	 * Create the dialog.
	 */
	public DialogDanhSachHoaDon() {

		/** set color & font **/
		Color mainColor = new Color(88, 159, 177);
		Color hoverColor = new Color(121, 178, 192);
		Font tahoma16 = new Font("Tahoma", Font.PLAIN, 16);
		Font tahoma14Bold = new Font("Tahoma", Font.BOLD, 14);
		Color whiteColor = new Color(255, 255, 255);
		Color tableHeaderColor = new Color(42, 143, 178);

		setAlwaysOnTop(true);

		setAutoRequestFocus(false);
		getContentPane().setBackground(SystemColor.window);
		setBackground(SystemColor.textHighlightText);
		setBounds(100, 100, 889, 742);
		setSize(800, 700);
		setLocationRelativeTo(null);
		contentPanel.setBackground(SystemColor.window);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		scrollPane = new JScrollPane();
		scrollPane_1 = new JScrollPane();

		tblChiTietHoaDon = new JTable();
		scrollPane_1.setViewportView(tblChiTietHoaDon);
		tblChiTietHoaDon.setFont(tahoma16);
		tblChiTietHoaDon.setRowHeight(28);
		tblChiTietHoaDon.setAutoCreateRowSorter(true);
		tblChiTietHoaDon.getTableHeader().setFont(tahoma16);
		tblChiTietHoaDon.getTableHeader().setBackground(tableHeaderColor);
		tblChiTietHoaDon.getTableHeader().setForeground(whiteColor);
		tblChiTietHoaDon.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		lblNewLabel_3 = new JLabel("Chi tiết hóa đơn");
		lblNewLabel_3.setFont(tahoma16);
		btnQuayLai = new JButton("Quay l\u1EA1i");
		btnQuayLai.setBorder(null);

		btnQuayLai.setFont(tahoma14Bold);

		btnXuatHoaDon = new JButton("Xuất hóa đơn");
		btnXuatHoaDon.setBorder(null);
		btnXuatHoaDon.setFont(tahoma14Bold);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap().addGroup(gl_contentPanel
						.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 753, Short.MAX_VALUE)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 149,
										GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPanel.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 753, Short.MAX_VALUE)))
						.addGroup(gl_contentPanel.createSequentialGroup()
								.addComponent(btnXuatHoaDon, GroupLayout.PREFERRED_SIZE, 143,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(btnQuayLai, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap()));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPanel
				.createSequentialGroup().addContainerGap()
				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE).addGap(18)
				.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnQuayLai, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnXuatHoaDon, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
				.addContainerGap()));
		contentPanel.setLayout(gl_contentPanel);

		tblHoaDon = new JTable();
		tblHoaDon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ChiTietHoaDonDao cthdDao = new ChiTietHoaDonDao();
				int row = tblHoaDon.getSelectedRow();
				dfModelChiTietHoaDon.setRowCount(0);
			
				Locale locale = new Locale("vi", "VN");
				NumberFormat format = NumberFormat.getCurrencyInstance(locale);
				
				if (row >= 0) {
					String maHD = tblHoaDon.getValueAt(row, 0).toString();
					List<ChiTietHoaDon> dsCTHD = cthdDao.getdsCTHD(maHD);
					for (ChiTietHoaDon cthd : dsCTHD) {
						dfModelChiTietHoaDon.addRow(new Object[] { cthd.getSanPham().getTenSanPham(), cthd.getSoLuong(),
								format.format(cthd.getSanPham().getDonGia()), format.format(cthd.getThanhTien()) });
					}
				}
			}
		});
		scrollPane.setViewportView(tblHoaDon);
		tblHoaDon.setFont(tahoma16);
		tblHoaDon.setRowHeight(28);
		tblHoaDon.setAutoCreateRowSorter(true);
		tblHoaDon.getTableHeader().setFont(tahoma16);
		tblHoaDon.getTableHeader().setBackground(tableHeaderColor);
		tblHoaDon.getTableHeader().setForeground(whiteColor);
		tblHoaDon.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JLabel lblNewLabel_1 = new JLabel("Qu\u1EA3n l\u00FD h\u00F3a \u0111\u01A1n");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Serif", Font.BOLD, 24));
		txtTimKiem = new JTextField();
		txtTimKiem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String searchString = txtTimKiem.getText();
				search(searchString);	
			}
		});
		
		txtTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTimKiem.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Danh s\u00E1ch h\u00F3a \u0111\u01A1n");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));

		cmbTimHD = new JComboBox<String>();
		cmbTimHD.setFocusable(false);
		cmbTimHD.setFocusTraversalKeysEnabled(false);
		
		btnLammoi = new JButton("Làm mới");
		btnLammoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtTimKiem.setText("");
				dfModelHoaDon.setRowCount(0);
				dfModelChiTietHoaDon.setRowCount(0);
				loadDataToTableHoaDon();
				tblHoaDon.setRowSorter(null);
			}
		});
		btnLammoi.setForeground(Color.WHITE);
		btnLammoi.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLammoi.setBorder(null);
		btnLammoi.setBackground(new Color(88, 159, 177));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(140)
							.addComponent(cmbTimHD, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtTimKiem, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
					.addComponent(btnLammoi, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
					.addGap(22))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 783, Short.MAX_VALUE)
						.addComponent(contentPanel, GroupLayout.DEFAULT_SIZE, 783, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1)
					.addGap(16)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtTimKiem, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmbTimHD, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLammoi, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addGap(16)
					.addComponent(lblNewLabel_2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(contentPanel, GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

		btnQuayLai.setBackground(mainColor);
		btnQuayLai.setForeground(whiteColor);
		btnQuayLai.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnQuayLai.setBackground(hoverColor);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnQuayLai.setBackground(mainColor);
			}
		});

		btnXuatHoaDon.setBackground(mainColor);
		btnXuatHoaDon.setForeground(whiteColor);
		btnXuatHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnXuatHoaDon.setBackground(hoverColor);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnXuatHoaDon.setBackground(mainColor);
			}
		});
		
		cmbTimHD.addItem("Tìm theo mã hóa đơn");
		cmbTimHD.addItem("Tìm theo tên khách hàng");
		cmbTimHD.addItem("Tìm theo ngày lập");
		cmbTimHD.addItem("Tìm theo tổng tiền");

		initTableHoaDon();
		initTableChiTietSanPham();
		loadDataToTableHoaDon();

		btnQuayLai.addActionListener(this);
		btnXuatHoaDon.addActionListener(this);
		
	}

	private void initTableHoaDon() {
		dfModelHoaDon = new DefaultTableModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		dfModelHoaDon.setColumnIdentifiers(
				new String[] { "Mã hóa đơn ", "Nhân viên lập", "Tên khách hàng", "Ngày lập hóa đơn", "Tổng tiền" });
		tblHoaDon.setModel(dfModelHoaDon);
	}

	private void initTableChiTietSanPham() {
		dfModelChiTietHoaDon = new DefaultTableModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		dfModelChiTietHoaDon.setColumnIdentifiers(new String[] { "Sản phẩm", "Số lượng", "Đơn giá", "Thành tiền" });
		tblChiTietHoaDon.setModel(dfModelChiTietHoaDon);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object o = e.getSource();
		if (o.equals(btnQuayLai)) {
			dispose();
		}
		
		
		if (o.equals(btnXuatHoaDon)) {

			try {
				int rowHD = tblHoaDon.getSelectedRow();
				if (rowHD >= 0) {
					JFileChooser jFileChooser = new JFileChooser();
					jFileChooser.showSaveDialog(this);
					File saveFile = jFileChooser.getSelectedFile();
					if (saveFile != null) {
						saveFile = new File(saveFile.toString() + ".xlsx");
						Workbook workbook = new XSSFWorkbook();
						Sheet sheet = workbook.createSheet("Hoadon");

						Row rowCol = sheet.createRow(0);

						for (int i = 0; i < tblChiTietHoaDon.getColumnCount(); ++i) {
							Cell cell = rowCol.createCell(i);
							cell.setCellValue(tblChiTietHoaDon.getColumnName(i));
						}

						int col = 3;

						for (int j = 0; j < tblChiTietHoaDon.getRowCount(); ++j) {
							col++;
							Row row1 = sheet.createRow(j + 1);
							for (int k = 0; k < tblChiTietHoaDon.getColumnCount(); ++k) {
								Cell cell = row1.createCell(k);
								if (tblChiTietHoaDon.getValueAt(j, k) != null) {
									cell.setCellValue(tblChiTietHoaDon.getValueAt(j, k).toString());
								}
							}
						}

						Row rowCol1 = sheet.createRow(col);
						for (int i = 0; i < tblHoaDon.getColumnCount(); ++i) {
							Cell cell = rowCol1.createCell(i);
							cell.setCellValue(tblHoaDon.getColumnName(i));
						}

						Row rowCol2 = sheet.createRow(col + 1);

						Cell celMaHD = rowCol2.createCell(0);
						celMaHD.setCellValue(tblHoaDon.getValueAt(rowHD, 0).toString());

						Cell celNV = rowCol2.createCell(1);
						celNV.setCellValue(tblHoaDon.getValueAt(rowHD, 1).toString());

						Cell cellTenKH = rowCol2.createCell(2);
						cellTenKH.setCellValue(tblHoaDon.getValueAt(rowHD, 2).toString());

						Cell cellNgayLap = rowCol2.createCell(3);
						cellNgayLap.setCellValue(tblHoaDon.getValueAt(rowHD, 3).toString());

						Cell cellTongTien = rowCol2.createCell(4);
						cellTongTien.setCellValue(tblHoaDon.getValueAt(rowHD, 4).toString());

						FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
						workbook.write(out);
						workbook.close();
						out.close();
						openFile(saveFile.toString());
					} else {
						MessageDialogHelpers.showErrorDialog(jFileChooser, "Lỗi", "Đã xảy ra lỗi!!");
					}
				} else {
					MessageDialogHelpers.showErrorDialog(tblHoaDon, "Cảnh báo", "Cần chọn 1 hóa đơn");
				}
			} catch (FileNotFoundException e2) {
				e2.printStackTrace();
			} catch (IOException e3) {
				e3.printStackTrace();
			}

		}

	}
	
	public void openFile(String file) {
		try {
			File path = new File(file);
			Desktop.getDesktop().open(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void loadDataToTableHoaDon() {
		try {
			HoaDonDao hoaDonDao = new HoaDonDao();
			List<HoaDon> listHoaDon = hoaDonDao.getDSHoaDon(ShareData.taiKhoanDangNhap.getNhanVien());
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Locale locale = new Locale("vi", "VN");
			NumberFormat format = NumberFormat.getCurrencyInstance(locale);
			
			for (HoaDon hoaDon : listHoaDon) {
				String ngayLap = formatter.format(hoaDon.getNgayTao());
				dfModelHoaDon.addRow(new Object[] { "HD" + hoaDon.getMaHoaDon(), hoaDon.getNhanVien().getHoTen(),
						hoaDon.getTenKhachHang(), ngayLap, format.format(hoaDon.getTongTien()) });
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void search(String str) {
		dfModelHoaDon = (DefaultTableModel) tblHoaDon.getModel();
		TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(dfModelHoaDon);
		tblHoaDon.setRowSorter(trs);
		
		if (cmbTimHD.getSelectedItem().toString().equals("Tìm theo mã hóa đơn")) {
			trs.setRowFilter(RowFilter.regexFilter(str, 0));
		}
		
		if (cmbTimHD.getSelectedItem().toString().equals("Tìm theo tên khách hàng")) {
			trs.setRowFilter(RowFilter.regexFilter(str, 2));
		}
		
		if (cmbTimHD.getSelectedItem().toString().equals("Tìm theo ngày lập")) {
			trs.setRowFilter(RowFilter.regexFilter(str, 3));
		}
		
		if (cmbTimHD.getSelectedItem().toString().equals("Tìm theo tổng tiền")) {
			trs.setRowFilter(RowFilter.regexFilter(str, 4));
		}
	}
}
