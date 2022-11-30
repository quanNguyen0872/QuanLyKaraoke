/**
 * 
 * Tác giả: La Võ Minh Quân - MSSV:19441111 - Nhóm 4
 * Ngày tạo: 21/10/2021
 * Cập nhật lần 1: 29/10/2021
 * Cập nhật lần 2: 30/10/2021
 * 
 * Mô tả: Giao diện chính của ứng dụng, nơi chọn và hiển thị các chức năng xử lý của phần mềm quản lý
 */

package application;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JDialog;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

import helpers.MessageDialogHelpers;
import helpers.ShareData;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.Cursor;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import javax.swing.SwingConstants;

public class MainFrame extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnQLNhanVien;
	private JButton btnQLTaiKhoan;
	private JButton btnQLThongKe;
	private JButton btnQLSanPham;
	private JButton btnQLPhong;
	private JButton btnDatPhong;
	private JButton btnThoat;
	private JMenu mnTaiKhoan;
	private JPanel panelManageMenu;
	private JButton btnQuanLyKhachHang;
	private JLabel lblTen;
	private JLabel lblChucVu;
	private JButton btnThanhToan;

	private DialogGioiThieu gioiThieuDialog;
	private PnlQuanLyNhanVien quanLyNhanVienPanel;
	private DialogDoiMatKhau doiMatKhauDialog;
	private PnlQuanLyTaiKhoan quanLyTaiKhoanPanel;
	private DialogThongTinTaiKhoan thongTinTaiKhoan;
	private PnlQuanLyPhong quanLyPhongPanel;
	private PnlQuanLySanPham quanLySanPhamPanel;
	private PnlQuanLyKhachHang quanLyKhachHangPanel;
	private PnlDatPhong panelDatPhong;
	private PnlThanhToan thanhToanPanel;
	public static JLayeredPane layeredPane;
	private DialogPhimTat phimTatDialog;
	private PnlManHinhMoDau manHinhMoDau;
	private PnlThongKe thongKePanel;
	

	/**
	 * Create the frame.
	 */
	public MainFrame() {

		/** set color and font **/
		Color whiteColor = new Color(255, 255, 255);
		Color mainColor = new Color(121,178,192);
		Color hoverColor = new Color(189,195,199);

		Font tahoma14 = new Font("Tahoma", Font.PLAIN, 14);
		Font serif12 = new Font("Serif", Font.PLAIN, 12);
		Font serif14 = new Font("Serif", Font.PLAIN, 14);

		/** set thuộc tính main frame **/
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 990, 631);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setTitle("Phần mềm quản lý quán karaoke");
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);

		/** set icon main frame **/
//		ImageIcon img = new ImageIcon(getClass().getResource(""));
//		setIconImage(img.getImage());

		/** set background menu quản lý **/
		panelManageMenu = new JPanel();
		panelManageMenu.setBackground(mainColor);

		JPanel panel_menu = new JPanel();
		layeredPane = new JLayeredPane();

		manHinhMoDau = new PnlManHinhMoDau();
		switchPanel(manHinhMoDau);

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panelManageMenu, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(layeredPane, GroupLayout.DEFAULT_SIZE, 812, Short.MAX_VALUE)
						.addComponent(panel_menu, GroupLayout.DEFAULT_SIZE, 812, Short.MAX_VALUE)))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel_menu, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(layeredPane, GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE))
				.addComponent(panelManageMenu, GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
		);

		layeredPane.setLayout(new CardLayout(0, 0));
		panel_menu.setLayout(new BorderLayout(0, 0));

		JMenuBar mainMenuBar = new JMenuBar();
		mainMenuBar.setBackground(whiteColor);
		panel_menu.add(mainMenuBar, BorderLayout.NORTH);

		mnTaiKhoan = new JMenu("T\u00E0i kho\u1EA3n");
		mnTaiKhoan.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		mnTaiKhoan.setIcon(new ImageIcon(getClass().getResource("/images/user(2).png")));
		mnTaiKhoan.setFont(serif14);
		mnTaiKhoan.setBackground(whiteColor);
		mainMenuBar.add(mnTaiKhoan);

		JMenuItem mnXemTaiKhoan = new JMenuItem("Xem t\u00E0i kho\u1EA3n");
		mnXemTaiKhoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thongTinTaiKhoan = new DialogThongTinTaiKhoan();
				thongTinTaiKhoan.setVisible(true);
			}
		});
		mnXemTaiKhoan.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
		mnXemTaiKhoan.setIcon(new ImageIcon(getClass().getResource("/images/eye.png")));
		mnXemTaiKhoan.setFont(serif12);
		mnTaiKhoan.add(mnXemTaiKhoan);

		JMenuItem mnDoiMatKhau = new JMenuItem("\u0110\u1ED5i m\u1EADt kh\u1EA9u");
		mnDoiMatKhau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doiMatKhauDialog = new DialogDoiMatKhau();
				doiMatKhauDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				doiMatKhauDialog.setVisible(true);
			}
		});
		mnDoiMatKhau.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		mnDoiMatKhau.setIcon(new ImageIcon(getClass().getResource("/images/changePassword.png")));
		mnDoiMatKhau.setFont(serif12);
		mnTaiKhoan.add(mnDoiMatKhau);

		JMenu mnTroGiup = new JMenu("Tr\u1EE3 gi\u00FAp");
		mnTroGiup.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		mnTroGiup.setIcon(new ImageIcon(getClass().getResource("/images/question.png")));
		mnTroGiup.setFont(serif14);
		mnTroGiup.setBackground(whiteColor);
		mainMenuBar.add(mnTroGiup);

		JMenuItem mnPhimTat = new JMenuItem("Ph\u00EDm t\u1EAFt");
		mnPhimTat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				phimTatDialog = new DialogPhimTat();
				phimTatDialog.setVisible(true);
			}
		});
		mnPhimTat.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.ALT_MASK | InputEvent.SHIFT_MASK));
		mnPhimTat.setIcon(new ImageIcon(getClass().getResource("/images/key.png")));
		mnPhimTat.setFont(serif12);
		mnTroGiup.add(mnPhimTat);

		JMenuItem mnGioiThieu = new JMenuItem("Giới thiệu");
		mnGioiThieu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gioiThieuDialog = new DialogGioiThieu();
				gioiThieuDialog.setVisible(true);
			}
		});
		mnGioiThieu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
		mnGioiThieu.setIcon(new ImageIcon(getClass().getResource("/images/info.png")));
		mnGioiThieu.setFont(serif12);
		mnTroGiup.add(mnGioiThieu);

		JLabel lblTitle = new JLabel("KARAOKE");
		lblTitle.setIcon(new ImageIcon(getClass().getResource("/images/music-2-24.png")));
		lblTitle.setFont(new Font("Serif", Font.ITALIC, 21));

		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		separator.setForeground(new Color(51, 51, 51));

		/** btn quản lý nhân viên **/
		btnQLNhanVien = new JButton("Qu\u1EA3n l\u00FD nh\u00E2n vi\u00EAn");
		btnQLNhanVien.setHorizontalAlignment(SwingConstants.LEADING);
		btnQLNhanVien.setBorder(new EmptyBorder(0, 10, 0, 0));
		btnQLNhanVien.setBorderPainted(false);
		btnQLNhanVien.setFocusPainted(false);
		btnQLNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnQLNhanVien.setBackground(hoverColor);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnQLNhanVien.setBackground(whiteColor);
		    }
		});
		btnQLNhanVien.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnQLNhanVien.setIcon(new ImageIcon(getClass().getResource("/images/employees.png")));
		btnQLNhanVien.setFocusable(false);
		btnQLNhanVien.setFont(tahoma14);
		btnQLNhanVien.setBackground(whiteColor);

		/** btn quản lý tài khoản **/
		btnQLTaiKhoan = new JButton("Qu\u1EA3n l\u00FD t\u00E0i kho\u1EA3n");
		btnQLTaiKhoan.setHorizontalAlignment(SwingConstants.LEADING);
		btnQLTaiKhoan.setBorder(new EmptyBorder(0, 10, 0, 0));
		btnQLTaiKhoan.setBorderPainted(false);
		btnQLTaiKhoan.setFocusPainted(false);
		btnQLTaiKhoan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnQLTaiKhoan.setIcon(new ImageIcon(getClass().getResource("/images/account.png")));
		btnQLTaiKhoan.setFont(tahoma14);
		btnQLTaiKhoan.setFocusable(false);
		btnQLTaiKhoan.setBackground(whiteColor);
		btnQLTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnQLTaiKhoan.setBackground(hoverColor);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnQLTaiKhoan.setBackground(whiteColor);
		    }
		});

		/** btn thống kê **/
		btnQLThongKe = new JButton("Qu\u1EA3n l\u00FD th\u1ED1ng k\u00EA");
		btnQLThongKe.setHorizontalAlignment(SwingConstants.LEADING);
		btnQLThongKe.setBorder(new EmptyBorder(0, 10, 0, 0));
		btnQLThongKe.setBorderPainted(false);
		btnQLThongKe.setFocusPainted(false);
		btnQLThongKe.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnQLThongKe.setIcon(new ImageIcon(getClass().getResource("/images/thongke.png")));
		btnQLThongKe.setFont(tahoma14);
		btnQLThongKe.setFocusable(false);
		btnQLThongKe.setBackground(whiteColor);
		btnQLThongKe.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnQLThongKe.setBackground(hoverColor);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnQLThongKe.setBackground(whiteColor);
		    }
		});

		/** btn quản lý sản phẩm **/
		btnQLSanPham = new JButton("Qu\u1EA3n l\u00FD s\u1EA3n ph\u1EA9m");
		btnQLSanPham.setHorizontalAlignment(SwingConstants.LEADING);
		btnQLSanPham.setBorder(new EmptyBorder(0, 10, 0, 0));
		btnQLSanPham.setBorderPainted(false);
		btnQLSanPham.setFocusPainted(false);
		btnQLSanPham.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnQLSanPham.setIcon(new ImageIcon(getClass().getResource("/images/product.png")));
		btnQLSanPham.setFont(tahoma14);
		btnQLSanPham.setFocusable(false);
		btnQLSanPham.setBackground(whiteColor);
		btnQLSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnQLSanPham.setBackground(hoverColor);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnQLSanPham.setBackground(whiteColor);
		    }
		});

		/** btn quản lý phòng **/
		btnQLPhong = new JButton("Quản lý phòng");
		btnQLPhong.setHorizontalAlignment(SwingConstants.LEADING);
		btnQLPhong.setBorder(new EmptyBorder(0, 10, 0, 0));
		btnQLPhong.setBorderPainted(false);
		btnQLPhong.setFocusPainted(false);
		btnQLPhong.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnQLPhong.setIcon(new ImageIcon(getClass().getResource("/images/door.png")));
		btnQLPhong.setFont(tahoma14);
		btnQLPhong.setFocusable(false);
		btnQLPhong.setBackground(whiteColor);
		btnQLPhong.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnQLPhong.setBackground(hoverColor);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnQLPhong.setBackground(whiteColor);
		    }
		});

		/** btn đặt đồ ăn **/
		btnDatPhong = new JButton("Đặt phòng");
		btnDatPhong.setHorizontalAlignment(SwingConstants.LEADING);
		btnDatPhong.setBorder(new EmptyBorder(0, 10, 0, 0));
		btnDatPhong.setBorderPainted(false);
		btnDatPhong.setFocusPainted(false);
		btnDatPhong.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDatPhong.setIcon(new ImageIcon(getClass().getResource("/images/order.png")));
		btnDatPhong.setFont(tahoma14);
		btnDatPhong.setFocusable(false);
		btnDatPhong.setBackground(whiteColor);
		btnDatPhong.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnDatPhong.setBackground(hoverColor);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnDatPhong.setBackground(whiteColor);
		    }
		});

		/** btn thoát **/
		btnThoat = new JButton("Đăng xuất");
		btnThoat.setBorder(null);
		btnThoat.setFont(tahoma14);
		btnThoat.setFocusable(false);
		btnThoat.setForeground(whiteColor);
		btnThoat.setBackground(new Color(255,99,71));
		btnThoat.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnThoat.setBackground(new Color(255,114,89));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnThoat.setBackground(new Color(255,99,71));
		    }
		});

		btnQuanLyKhachHang = new JButton("Quản lý khách hàng");
		btnQuanLyKhachHang.setHorizontalAlignment(SwingConstants.LEADING);
		btnQuanLyKhachHang.setBorder(new EmptyBorder(0, 10, 0, 0));
		btnQuanLyKhachHang.setBorderPainted(false);
		btnQuanLyKhachHang.setFocusPainted(false);
		btnQuanLyKhachHang.setIcon(new ImageIcon(getClass().getResource("/images/customer.png")));
		btnQuanLyKhachHang.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnQuanLyKhachHang.setFont(tahoma14);
		btnQuanLyKhachHang.setFocusable(false);
		btnQuanLyKhachHang.setBackground(whiteColor);
		btnQuanLyKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnQuanLyKhachHang.setBackground(hoverColor);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnQuanLyKhachHang.setBackground(whiteColor);
		    }
		});

		btnThanhToan = new JButton("Thanh Toán");
		btnThanhToan.setHorizontalAlignment(SwingConstants.LEADING);
		btnThanhToan.setBorder(new EmptyBorder(0, 10, 0, 0));
		btnThanhToan.setIcon(new ImageIcon(getClass().getResource("/images/pay.png")));
		btnThanhToan.setBorderPainted(false);
		btnThanhToan.setFocusPainted(false);
		btnThanhToan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnThanhToan.setFont(tahoma14);
		btnThanhToan.setFocusable(false);
		btnThanhToan.setBackground(whiteColor);
		btnThanhToan.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnThanhToan.setBackground(hoverColor);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnThanhToan.setBackground(whiteColor);
		    }
		});
		
		lblTen = new JLabel("New label");
		lblTen.setFont(tahoma14);
		lblTen.setForeground(Color.red);
		
		lblChucVu = new JLabel("New label");
		lblChucVu.setFont(tahoma14);
		lblChucVu.setForeground(Color.red);

		GroupLayout gl_panelManageMenu = new GroupLayout(panelManageMenu);
		gl_panelManageMenu.setHorizontalGroup(
			gl_panelManageMenu.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelManageMenu.createSequentialGroup()
					.addGap(57)
					.addComponent(lblTitle, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(61))
				.addGroup(gl_panelManageMenu.createSequentialGroup()
					.addContainerGap(87, Short.MAX_VALUE)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
					.addGap(81))
				.addGroup(gl_panelManageMenu.createSequentialGroup()
					.addGap(70)
					.addComponent(btnThoat, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(73, Short.MAX_VALUE))
				.addGroup(gl_panelManageMenu.createSequentialGroup()
					.addGap(27)
					.addGroup(gl_panelManageMenu.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panelManageMenu.createSequentialGroup()
							.addGroup(gl_panelManageMenu.createParallelGroup(Alignment.LEADING)
								.addComponent(btnThanhToan, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnDatPhong, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnQLPhong, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnQuanLyKhachHang, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnQLSanPham, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnQLThongKe, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnQLTaiKhoan, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnQLNhanVien, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE))
							.addGap(28))
						.addGroup(gl_panelManageMenu.createSequentialGroup()
							.addComponent(lblTen, GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
							.addContainerGap())))
				.addGroup(gl_panelManageMenu.createSequentialGroup()
					.addGap(27)
					.addComponent(lblChucVu, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(28, Short.MAX_VALUE))
		);
		gl_panelManageMenu.setVerticalGroup(
			gl_panelManageMenu.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelManageMenu.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTitle)
					.addGap(18)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(33)
					.addComponent(btnQLNhanVien, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnQLTaiKhoan, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnQLThongKe, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnQLSanPham, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnQuanLyKhachHang, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnQLPhong, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnDatPhong, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnThanhToan, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblTen, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblChucVu)
					.addGap(18, 18, Short.MAX_VALUE)
					.addComponent(btnThoat, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panelManageMenu.setLayout(gl_panelManageMenu);
		contentPane.setLayout(gl_contentPane);

		btnThoat.addActionListener(this);
		btnDatPhong.addActionListener(this);
		btnQLNhanVien.addActionListener(this);
		btnQLPhong.addActionListener(this);
		btnQLSanPham.addActionListener(this);
		btnQLTaiKhoan.addActionListener(this);
		btnQLThongKe.addActionListener(this);
		btnQuanLyKhachHang.addActionListener(this);
		btnThanhToan.addActionListener(this);

		processLoginSuccessfull();
	}

	private void processLoginSuccessfull() {
		mnTaiKhoan.setText(ShareData.taiKhoanDangNhap.getTenDangNhap());
		
		lblTen.setText("Tên: "+ShareData.taiKhoanDangNhap.getNhanVien().getHoTen());
		lblChucVu.setText("Vai trò: "+ShareData.taiKhoanDangNhap.getVaiTro());

		if (ShareData.taiKhoanDangNhap.getVaiTro().equals("Nhân viên lễ tân")) {		
			btnQLNhanVien.setVisible(false);
			btnQLTaiKhoan.setVisible(false);
			btnQLThongKe.setVisible(false);
		}
	}

	public void switchPanel(JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o.equals(btnQLNhanVien)) {
			quanLyNhanVienPanel = new PnlQuanLyNhanVien();
			switchPanel(quanLyNhanVienPanel);
		}

		if (o.equals(btnQLPhong)) {
			quanLyPhongPanel = new PnlQuanLyPhong();
			switchPanel(quanLyPhongPanel);
		}

		if (o.equals(btnQLSanPham)) {
			quanLySanPhamPanel = new PnlQuanLySanPham();
			switchPanel(quanLySanPhamPanel);
		}

		if (o.equals(btnQLTaiKhoan)) {
			quanLyTaiKhoanPanel = new PnlQuanLyTaiKhoan();
			switchPanel(quanLyTaiKhoanPanel);
		}

		if (o.equals(btnQLThongKe)) {
			thongKePanel = new PnlThongKe();
			switchPanel(thongKePanel);
		}

		if (o.equals(btnQuanLyKhachHang)) {
			quanLyKhachHangPanel = new PnlQuanLyKhachHang();
			switchPanel(quanLyKhachHangPanel);
		}

		if (o.equals(btnDatPhong)) {
			panelDatPhong = new PnlDatPhong();
			switchPanel(panelDatPhong);
		}

		if (o.equals(btnThanhToan)) {
			thanhToanPanel = new PnlThanhToan();
			switchPanel(thanhToanPanel);
		}

		if (o.equals(btnThoat)) {
			if (MessageDialogHelpers.showConfirmDialog(layeredPane, "Cảnh báo",
					"Bạn có chắc muốn đăng xuất") == JOptionPane.NO_OPTION) {
				return;
			}
			dispose();
			DialogDangNhap loginForm = new DialogDangNhap();
			loginForm.setVisible(true);
		}

	}
}
