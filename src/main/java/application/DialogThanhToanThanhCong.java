/**
 * La Võ Minh Quân
 * 19441111
 * Mô tả: xuất ra thông tin khi thanh toán thành công
 */
package application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.ChiTietHoaDonDao;
import dao.HoaDonDao;
import entity.ChiTietHoaDon;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class DialogThanhToanThanhCong extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblTenKH;
	private JLabel lblMaHD;
	private JLabel lblTGVao;
	private JLabel lblTGRa;
	private JLabel lblThoiGianSuDung;
	private JLabel lblDonGia;
	private JLabel lblTienPhongTheoThoiGian;
	private JLabel lblTongDichVu;
	private JLabel lblTongCong;
	private JButton btnXemHoaDon;
	private JButton btnDong;
	
	private DialogDanhSachHoaDon danhSachHoaDon;
	private JTable tblDichVu;
	private static DefaultTableModel dfModelDichVu;

	/**
	 * Create the dialog.
	 */
	public DialogThanhToanThanhCong() {
		
		/*set color and font*/
		Font tahoma20 = new Font("Tahoma", Font.BOLD, 20);
		Font tahoma13Bold = new Font("Tahoma", Font.BOLD, 13);
		Font tahoma14 = new Font("Tahoma", Font.PLAIN, 14);
		Font tahoma18Bold = new Font("Tahoma", Font.BOLD, 18);
		
		Color mainColor = new Color(88,159,177);
		Color hoverColor = new Color(121,178,192);

		setResizable(false);
		setBounds(100, 100, 823, 447);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Thanh Toán Thành Công");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(tahoma20);
		lblNewLabel.setBounds(0, 0, 798, 48);
		contentPanel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Thời gian vào:");
		lblNewLabel_1.setFont(tahoma13Bold);
		lblNewLabel_1.setBounds(10, 127, 130, 23);
		contentPanel.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Thời gian ra:");
		lblNewLabel_1_1.setFont(tahoma13Bold);
		lblNewLabel_1_1.setBounds(10, 161, 130, 23);
		contentPanel.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Thời gian sử dụng:");
		lblNewLabel_1_2.setFont(tahoma13Bold);
		lblNewLabel_1_2.setBounds(10, 195, 130, 23);
		contentPanel.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_2_1 = new JLabel("Tổng tiền dịch vụ:");
		lblNewLabel_1_2_1.setFont(tahoma13Bold);
		lblNewLabel_1_2_1.setBounds(10, 297, 130, 23);
		contentPanel.add(lblNewLabel_1_2_1);

		JLabel lblNewLabel_1_2_1_1 = new JLabel("Đơn giá phòng:");
		lblNewLabel_1_2_1_1.setFont(tahoma13Bold);
		lblNewLabel_1_2_1_1.setBounds(10, 229, 130, 23);
		contentPanel.add(lblNewLabel_1_2_1_1);

		JLabel lblNewLabel_1_2_1_1_1 = new JLabel("Tiền phòng theo thời gian:");
		lblNewLabel_1_2_1_1_1.setFont(tahoma13Bold);
		lblNewLabel_1_2_1_1_1.setBounds(10, 263, 175, 23);
		contentPanel.add(lblNewLabel_1_2_1_1_1);

		JLabel lblNewLabel_1_2_1_2 = new JLabel("TỔNG CỘNG:");
		lblNewLabel_1_2_1_2.setForeground(Color.RED);
		lblNewLabel_1_2_1_2.setFont(tahoma18Bold);
		lblNewLabel_1_2_1_2.setBounds(10, 331, 130, 23);
		contentPanel.add(lblNewLabel_1_2_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("Đã thanh toán thành công cho khách hàng:");
		lblNewLabel_1_3.setFont(tahoma13Bold);
		lblNewLabel_1_3.setBounds(10, 59, 283, 23);
		contentPanel.add(lblNewLabel_1_3);

		JLabel lblNewLabel_1_4 = new JLabel("Mã hóa đơn của khách hàng này là:");
		lblNewLabel_1_4.setFont(tahoma13Bold);
		lblNewLabel_1_4.setBounds(10, 93, 233, 23);
		contentPanel.add(lblNewLabel_1_4);

		btnDong = new JButton("Đóng");
		btnDong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		btnDong.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnDong.setBackground(hoverColor);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnDong.setBackground(mainColor);
		    }
		});
		
		btnDong.setRequestFocusEnabled(false);
		btnDong.setFont(tahoma13Bold);
		btnDong.setBounds(396, 365, 89, 32);
		contentPanel.add(btnDong);

		btnXemHoaDon = new JButton("Xem hóa đơn");
		btnXemHoaDon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				danhSachHoaDon = new DialogDanhSachHoaDon();
				danhSachHoaDon.setVisible(true);
			}
		});
		
		btnXemHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnXemHoaDon.setBackground(hoverColor);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnXemHoaDon.setBackground(mainColor);
		    }
		});
		
		btnXemHoaDon.setRequestFocusEnabled(false);
		btnXemHoaDon.setFont(tahoma13Bold);
		btnXemHoaDon.setBounds(215, 365, 158, 32);
		contentPanel.add(btnXemHoaDon);

		lblTenKH = new JLabel("");
		lblTenKH.setFont(tahoma14);
		lblTenKH.setBounds(301, 59, 158, 23);
		contentPanel.add(lblTenKH);

		lblMaHD = new JLabel("");
		lblMaHD.setFont(tahoma14);
		lblMaHD.setBounds(253, 93, 206, 23);
		contentPanel.add(lblMaHD);

		lblTGVao = new JLabel("");
		lblTGVao.setFont(tahoma14);
		lblTGVao.setBounds(135, 127, 158, 23);
		contentPanel.add(lblTGVao);

		lblTGRa = new JLabel("");
		lblTGRa.setFont(tahoma14);
		lblTGRa.setBounds(135, 161, 158, 23);
		contentPanel.add(lblTGRa);

		lblThoiGianSuDung = new JLabel("");
		lblThoiGianSuDung.setFont(tahoma14);
		lblThoiGianSuDung.setBounds(135, 195, 158, 23);
		contentPanel.add(lblThoiGianSuDung);

		lblDonGia = new JLabel("");
		lblDonGia.setFont(tahoma14);
		lblDonGia.setBounds(135, 229, 158, 23);
		contentPanel.add(lblDonGia);

		lblTienPhongTheoThoiGian = new JLabel("");
		lblTienPhongTheoThoiGian.setFont(tahoma14);
		lblTienPhongTheoThoiGian.setBounds(184, 263, 158, 23);
		contentPanel.add(lblTienPhongTheoThoiGian);

		lblTongDichVu = new JLabel("");
		lblTongDichVu.setFont(tahoma14);
		lblTongDichVu.setBounds(135, 297, 158, 23);
		contentPanel.add(lblTongDichVu);

		lblTongCong = new JLabel("");
		lblTongCong.setForeground(Color.RED);
		lblTongCong.setFont(tahoma18Bold);
		lblTongCong.setBounds(150, 331, 309, 23);
		contentPanel.add(lblTongCong);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(469, 130, 329, 224);
		contentPanel.add(scrollPane);
		
		tblDichVu = new JTable();
		tblDichVu.setFont(tahoma14);
		tblDichVu.setRowHeight(28);
		tblDichVu.setAutoCreateRowSorter(true);
		tblDichVu.getTableHeader().setFont(tahoma14);
		tblDichVu.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tblDichVu);
		
		btnDong.setBackground(mainColor);
		btnXemHoaDon.setBackground(mainColor);
		
		
		JLabel lblNewLabel_2 = new JLabel("Danh sách dịch vụ");
		lblNewLabel_2.setBounds(469, 102, 103, 14);
		contentPanel.add(lblNewLabel_2);
		
		initTableDichVu();
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

	public void putTextNow(String tenKhachHang, String maHoaDon, String thoiGianRa, String thoiGianVao,
			String thoiGianSuDung, String tongDichVu, String tongCong, String donGiaPhong, String tienPhong) {
		lblTenKH.setText(tenKhachHang);
		lblMaHD.setText(maHoaDon);
		lblTGRa.setText(thoiGianRa);
		lblTGVao.setText(thoiGianVao);
		lblThoiGianSuDung.setText(thoiGianSuDung);
		lblTongDichVu.setText(tongDichVu);
		lblTongCong.setText(tongCong);
		lblDonGia.setText(donGiaPhong);
		lblTienPhongTheoThoiGian.setText(tienPhong);
	}
	
	public static void loadDataCTHD(String tenKH) {
		HoaDonDao hoaDonDao = new HoaDonDao();
		ChiTietHoaDonDao cthdDao = new ChiTietHoaDonDao();

		String maHoaDon = hoaDonDao.getMaHoaDon(tenKH);

		List<ChiTietHoaDon> dsCTHD = cthdDao.getdsCTHD(maHoaDon);
		for (ChiTietHoaDon cthd : dsCTHD) {
			dfModelDichVu.addRow(new Object[] { cthd.getSanPham().getTenSanPham(), cthd.getSoLuong(),
					cthd.getSanPham().getDonGia(), cthd.getThanhTien() });
		}
	}
}
