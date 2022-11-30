/**
 * La Võ Minh Quân - 19441111 - Nhom 4
 * Mô tả: thống kê doanh thu bán được trong ngày, thống kê số lượng sản phẩm bán được bằng biểu đồ
 */
package application;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.util.Date;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.CardLayout;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import com.toedter.calendar.JDateChooser;

import dao.ChiTietHoaDonDao;
import dao.HoaDonDao;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class PnlThongKe extends JPanel {

	private static final long serialVersionUID = 1L;
	private HoaDonDao hoaDonDao;
	private ChiTietHoaDonDao cthdDao;
	private JDateChooser dateChooser;
	private JButton btnThongKe;
	private JDateChooser dateChooser_to;

	/**
	 * Create the panel.
	 */
	public PnlThongKe() {

		hoaDonDao = new HoaDonDao();
		cthdDao = new ChiTietHoaDonDao();

		JPanel pnl_Top = new JPanel();

		JPanel pnl_Bottom = new JPanel();

		JPanel pnl_Center = new JPanel();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(pnl_Top, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
				.addComponent(pnl_Bottom, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
				.addComponent(pnl_Center, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(Alignment.LEADING,
				groupLayout.createSequentialGroup()
						.addComponent(pnl_Top, GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(pnl_Center, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(pnl_Bottom, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)));
		setLayout(groupLayout);

		dateChooser = new JDateChooser();
		dateChooser.setPreferredSize(new Dimension(200, 30));

		btnThongKe = new JButton("Thống kê");
		btnThongKe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date from = dateChooser.getDate();
				Date to = dateChooser_to.getDate();
				setDateToChart3(pnl_Top, from, to);
			}
		});

		JLabel lblNewLabel = new JLabel("Từ ngày");
		
		dateChooser_to = new JDateChooser();
		
		JLabel lblnNgy = new JLabel("Đến ngày");
		GroupLayout gl_pnl_Center = new GroupLayout(pnl_Center);
		gl_pnl_Center.setHorizontalGroup(
			gl_pnl_Center.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnl_Center.createSequentialGroup()
					.addGap(6)
					.addComponent(lblNewLabel)
					.addGap(16)
					.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblnNgy)
					.addGap(18)
					.addComponent(dateChooser_to, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnThongKe)
					.addContainerGap(268, Short.MAX_VALUE))
		);
		gl_pnl_Center.setVerticalGroup(
			gl_pnl_Center.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_pnl_Center.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnl_Center.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnThongKe)
						.addComponent(dateChooser_to, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblnNgy, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(dateChooser, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
						.addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
					.addContainerGap())
		);
		pnl_Center.setLayout(gl_pnl_Center);

		setDateToChart1(pnl_Top);
		setDateToChart2(pnl_Bottom);
	}

	/**
	 * set so do mac dinh
	 * @param panelItem
	 */
	private void setDateToChart1(JPanel panelItem) {
		List<HoaDon> listHoaDon = hoaDonDao.getDSHoaDon();
		if (listHoaDon != null) {
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			
			for (HoaDon hd : listHoaDon) {
				dataset.addValue(hd.getTongTien(), "Doanh thu", hd.getNgayTao());
			}

			JFreeChart charts = ChartFactory.createBarChart("THỐNG KÊ DOANH THU THEO NGÀY", "Thời gian", "Doanh thu",
					dataset);
			ChartPanel chartPanel = new ChartPanel(charts);
			chartPanel.setPreferredSize(new Dimension(panelItem.getWidth(), 300));

			panelItem.removeAll();
			panelItem.setLayout(new CardLayout());
			panelItem.add(chartPanel);
			panelItem.validate();
			panelItem.repaint();
		}
	}
	
	/**
	 * set so do doanh thu ban duoc khi chon 1 khoang thoi gian
	 * @param panelItem
	 */
	private void setDateToChart3(JPanel panelItem, Date from, Date to) {
		List<HoaDon> listHoaDon = hoaDonDao.getDSHoaDonTheoNgay(from, to);
		if (listHoaDon != null) {
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			for (HoaDon hd : listHoaDon) {
				dataset.addValue(hd.getTongTien(), "Doanh thu", hd.getNgayTao());
			}
			JFreeChart charts = ChartFactory.createBarChart("THỐNG KÊ DOANH THU THEO NGÀY",
					"Thời gian", "Doanh thu", dataset);
			ChartPanel chartPanel = new ChartPanel(charts);
			chartPanel.setPreferredSize(new Dimension(panelItem.getWidth(), 300));

			panelItem.removeAll();
			panelItem.setLayout(new CardLayout());
			panelItem.add(chartPanel);
			panelItem.validate();
			panelItem.repaint();
		}
	}

	/**
	 * set so do so luong san pham ban duoc 
	 * @param panelItem
	 */
	private void setDateToChart2(JPanel panelItem) {
		List<ChiTietHoaDon> listCTHD = cthdDao.getdsCTHD();
		if (listCTHD != null) {
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			for (ChiTietHoaDon cthd : listCTHD) {
				dataset.addValue(cthd.getSoLuong(), "Số lượng", cthd.getSanPham().getTenSanPham());
			}

			JFreeChart charts = ChartFactory.createBarChart("THỐNG KÊ TẤT CẢ SỐ SẢN PHẨM BÁN ĐƯỢC", "Sản phẩm", "Số lượng",
					dataset);
			ChartPanel chartPanel = new ChartPanel(charts);
			chartPanel.setPreferredSize(new Dimension(panelItem.getWidth(), 300));

			panelItem.removeAll();
			panelItem.setLayout(new CardLayout());
			panelItem.add(chartPanel);
			panelItem.validate();
			panelItem.repaint();
		}
	}
}
