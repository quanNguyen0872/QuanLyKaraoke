/**
 * 
 * Tác giả: La Võ Minh Quân - MSSV:19441111 Đoàn Thị Mỹ Linh -MSSV: 19442391 chỉnh sửa - Nhóm 4 
 * Ngày tạo: 21/10/2021
 * 
 * Mô tả: Giao diện hướng dẫn các phím tắt trong ứng dụng
 */

package application;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

public class DialogPhimTat extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogPhimTat dialog = new DialogPhimTat();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogPhimTat() {
		setResizable(false);
		setBackground(Color.WHITE);
		setBounds(100, 100, 497, 526);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		Font serif18 = new Font("Serif", Font.PLAIN, 18);
		
		JLabel lblNewLabel = new JLabel("Danh sách phím tắt");
		lblNewLabel.setFont(new Font("Serif", Font.BOLD, 23));
		
		JLabel lblNewLabel_1 = new JLabel("Đặt phòng");
		lblNewLabel_1.setFont(serif18);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Thanh toán");
		lblNewLabel_1_1_1.setFont(serif18);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Nhận phòng");
		lblNewLabel_1_1_1_1.setFont(serif18);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Tiền khách trả");
		lblNewLabel_1_1_2.setFont(serif18);
		
		JLabel lblNewLabel_1_1_2_1 = new JLabel("Thống kê doanh thu");
		lblNewLabel_1_1_2_1.setFont(serif18);
		
		JLabel lblNewLabel_1_1_2_1_1 = new JLabel("Làm mới");
		lblNewLabel_1_1_2_1_1.setFont(serif18);
		
		JLabel lblNewLabel_1_1_2_1_1_1_1 = new JLabel("Đổi mật khẩu");
		lblNewLabel_1_1_2_1_1_1_1.setFont(serif18);
		
		JLabel lblNewLabel_1_1_2_1_1_1_1_1 = new JLabel("Giới thiệu phần mềm");
		lblNewLabel_1_1_2_1_1_1_1_1.setFont(serif18);
		
		JLabel lblNewLabel_1_1_2_1_1_1_1_1_1 = new JLabel("Danh sách phím tắt");
		lblNewLabel_1_1_2_1_1_1_1_1_1.setFont(serif18);
		
		JLabel lblNewLabel_1_1_2_1_1_1_1_2 = new JLabel("Xem tài khoản");
		lblNewLabel_1_1_2_1_1_1_1_2.setFont(serif18);
		
		JLabel lblNewLabel_2 = new JLabel("1: ctrl + D");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setFont(serif18);
		lblNewLabel_2.setBackground(Color.BLACK);
		
		JLabel lblNewLabel_2_1 = new JLabel("2: ctrl + N");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1.setFont(serif18);
		lblNewLabel_2_1.setBackground(Color.BLACK);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("3: ctrl + C");
		lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1_1.setFont(serif18);
		lblNewLabel_2_1_1.setBackground(Color.BLACK);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("4: ctrl + M");
		lblNewLabel_2_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1_2.setFont(serif18);
		lblNewLabel_2_1_2.setBackground(Color.BLACK);
		
		JLabel lblNewLabel_2_1_3 = new JLabel("5: ctrl + T");
		lblNewLabel_2_1_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1_3.setFont(serif18);
		lblNewLabel_2_1_3.setBackground(Color.BLACK);
		
		JLabel lblNewLabel_2_1_4 = new JLabel("6: ctrl + R");
		lblNewLabel_2_1_4.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1_4.setFont(serif18);
		lblNewLabel_2_1_4.setBackground(Color.BLACK);
		
		JLabel lblNewLabel_2_1_5 = new JLabel("7: ctrl + P");
		lblNewLabel_2_1_5.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1_5.setFont(serif18);
		lblNewLabel_2_1_5.setBackground(Color.BLACK);
		
		JLabel lblNewLabel_2_1_6 = new JLabel("8: ctrl + A");
		lblNewLabel_2_1_6.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1_6.setFont(serif18);
		lblNewLabel_2_1_6.setBackground(Color.BLACK);
		
		JLabel lblNewLabel_2_1_7 = new JLabel("9: ctrl + I");
		lblNewLabel_2_1_7.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1_7.setFont(serif18);
		lblNewLabel_2_1_7.setBackground(Color.BLACK);
		
		JLabel lblNewLabel_2_1_8 = new JLabel("10: Alt + Shift + P");
		lblNewLabel_2_1_8.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1_8.setFont(serif18);
		lblNewLabel_2_1_8.setBackground(Color.BLACK);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(64)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(30)
									.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED, 56, GroupLayout.PREFERRED_SIZE)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblNewLabel_2_1_2)
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
											.addComponent(lblNewLabel_2_1)
											.addComponent(lblNewLabel_2_1_1))
										.addComponent(lblNewLabel_2_1_3)
										.addComponent(lblNewLabel_2_1_4)
										.addComponent(lblNewLabel_2_1_5)
										.addComponent(lblNewLabel_2_1_6)
										.addComponent(lblNewLabel_2_1_7)))))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_2_1_8)))
					.addGap(33)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1_1_1_1, GroupLayout.PREFERRED_SIZE, 232, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_1_2, GroupLayout.PREFERRED_SIZE, 232, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 232, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_1_2_1, GroupLayout.PREFERRED_SIZE, 232, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_1_2_1_1_1_1_2, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_1_2_1_1, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_1_2_1_1_1_1, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_1_2_1_1_1_1_1_1, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_1_2_1_1_1_1_1, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
					.addGap(19))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap(126, Short.MAX_VALUE)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)
					.addGap(124))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(26)
					.addComponent(lblNewLabel)
					.addGap(31)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1_1_1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1_2, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2_1_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(9)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2_1_2, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(9)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1_2_1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2_1_3, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(9)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1_2_1_1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2_1_4, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1_2_1_1_1_1_2, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2_1_5, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(9)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1_2_1_1_1_1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2_1_6, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2_1_7, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_1_2_1_1_1_1_1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1_1_2_1_1_1_1_1_1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2_1_8, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(42, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
	}
}
