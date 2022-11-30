/**
 * 
 * Tác giả: La Võ Minh Quân - MSSV:19441111 - Nhóm 4
 * Ngày tạo: 21/10/2021
 * 
 * Mô tả: Giao diện mô tả thông tin ứng dụng , nhóm phát triển, hướng dẫn và liên hệ
 */
package application;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

public class DialogGioiThieu extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public DialogGioiThieu() {
		
		/**set color and font**/
		Font tahoma20 = new Font("Tahoma", Font.PLAIN, 20);
		Font tahoma16 = new Font("Tahoma", Font.PLAIN, 16);
		Font tahoma18 = new Font("Tahoma", Font.PLAIN, 18);
		Font tahoma12 = new Font("Tahoma", Font.PLAIN, 12);
		
		Color mainColor = new Color(88, 177, 159);
		Color whiteColor = new Color(255, 255, 255);
		
		setBounds(100, 100, 699, 554);
		setLocationRelativeTo(null);
		setTitle("Thông tin ứng dụng");
		ImageIcon img = new ImageIcon(getClass().getResource("/images/info.png"));
		setIconImage(img.getImage());
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(whiteColor);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblNewLabel = new JLabel("Ph\u1EA7n m\u1EC1m do nh\u00F3m 4 th\u1EF1c hi\u1EC7n");
		lblNewLabel.setFont(tahoma20);
		JLabel lblThnhVin = new JLabel("Th\u00E0nh vi\u00EAn:");
		lblThnhVin.setFont(tahoma18);
		JLabel lbllaVMinh = new JLabel("-La V\u00F5 Minh Qu\u00E2n(leader)-19441111");
		lbllaVMinh.setFont(tahoma16);
		JLabel lblnguynHngQunnotetaker = new JLabel("-Nguy\u1EC5n H\u1ED3ng Qu\u00E2n(Notetaker)-19445101");
		lblnguynHngQunnotetaker.setFont(tahoma16);
		JLabel lbltngGiaBotimekeeper = new JLabel("-T\u0103ng Gia B\u1EA3o(Timekeeper)-19452981");
		lbltngGiaBotimekeeper.setFont(tahoma16);
		JLabel lblonThM = new JLabel("-Đoàn Thị Mỹ Linh(Reporter)-19442391");
		lblonThM.setFont(tahoma16);
		JLabel lblGioVinHng = new JLabel("Gi\u00E1o vi\u00EAn h\u01B0\u1EDBng d\u1EABn : Tr\u1EA7n Th\u1ECB Anh Thi");
		lblGioVinHng.setFont(tahoma16);
		JLabel lblnuXyRa = new JLabel("*N\u1EBFu x\u1EA3y ra l\u1ED7i trong qu\u00E1 tr\u00ECnh s\u1EED d\u1EE5ng vui l\u00F2ng li\u00EAn h\u1EC7 \u0111\u1EBFn hotline 123456789");
		lblnuXyRa.setFont(tahoma16);
		lblnuXyRa.setForeground(new Color(255, 0, 51));
		
		JLabel lblNewLabel_1 = new JLabel("T\u1ED5ng quan v\u1EC1 \u1EE9ng d\u1EE5ng:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JPanel panel = new JPanel();
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblThnhVin, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
								.addComponent(lbllaVMinh, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblonThM, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lbltngGiaBotimekeeper, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblnguynHngQunnotetaker, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblGioVinHng, GroupLayout.PREFERRED_SIZE, 319, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblnuXyRa, GroupLayout.PREFERRED_SIZE, 653, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(18)
							.addComponent(lblThnhVin)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lbllaVMinh)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblnguynHngQunnotetaker)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lbltngGiaBotimekeeper)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblonThM)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblGioVinHng)
							.addGap(18)
							.addComponent(lblNewLabel_1))
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 171, Short.MAX_VALUE)
					.addComponent(lblnuXyRa, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(getClass().getResource("/images/kara.png")));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(10, 11, 205, 197);
		panel.add(lblNewLabel_2);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnClose = new JButton("\u0110\u00F3ng");
				btnClose.setForeground(whiteColor);
				btnClose.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnClose.setBackground(mainColor);
				btnClose.setFocusable(false);
				btnClose.setFont(tahoma12);
				btnClose.setActionCommand("Cancel");
				buttonPane.add(btnClose);
			}
		}
	}
}
