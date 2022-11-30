/**
 * La Võ Minh Quân - 19441111
 * màn hình mở đầu khi bắt đầu ứng dụng
 */
package application;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.Cursor;

public class PnlManHinhMoDau extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PnlManHinhMoDau() {
		
		Font tahoma16 = new Font("Tahoma", Font.PLAIN, 16);
		
		JLabel lblNewLabel = new JLabel("Nhấn vào ");
		lblNewLabel.setIcon(null);
		lblNewLabel.setFont(tahoma16);
		
		JLabel lblXemThng = new JLabel("để xem thông tin tài khoản hoặc ctrl + p");
		lblXemThng.setIcon(new ImageIcon(getClass().getResource("/images/eye.png")));
		lblXemThng.setFont(tahoma16);
		
		JLabel lblNewLabel_1 = new JLabel("Nhấn vào ");
		lblNewLabel_1.setFont(tahoma16);
		
		JLabel lbliMt = new JLabel("để đổi mật khẩu hoặc ctrl + a");
		lbliMt.setIcon(new ImageIcon(getClass().getResource("/images/changePassword.png")));
		lbliMt.setFont(tahoma16);
		
		JLabel lblNewLabel_2 = new JLabel("Phần mềm quản lý karaoke");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nhấn vào ");
		lblNewLabel_1_1.setFont(tahoma16);
		
		JLabel lblXemThng_1 = new JLabel("để xem thông tin về các phím tắt hoặc alt + shift + p");
		lblXemThng_1.setIcon(new ImageIcon(getClass().getResource("/images/key.png")));
		lblXemThng_1.setFont(tahoma16);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Nhấn vào ");
		lblNewLabel_1_1_1.setFont(tahoma16);
		
		JLabel lblXemThng_1_1 = new JLabel("để xem thông tin chi tiết của ứng dụng và chúng tôi hoặc ctrl + i");
		lblXemThng_1_1.setIcon(new ImageIcon(getClass().getResource("/images/info.png")));
		lblXemThng_1_1.setFont(tahoma16);
		
		JLabel lblDuongDan = new JLabel("https://mhxx307.github.io/tai_lieu_huong_dan_karaoke/");
		lblDuongDan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblDuongDan.setForeground(new Color(3, 138, 255));
		lblDuongDan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Desktop desktop = Desktop.getDesktop();
				String address = "https://mhxx307.github.io/tai_lieu_huong_dan_karaoke/";
				try {
					desktop.browse(new URI(address));
				} catch (IOException | URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
		});
		lblDuongDan.setFont(tahoma16);
		
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Vào trang");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("để xem hướng dẫn chi tiết");
		lblNewLabel_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(239)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lbliMt, GroupLayout.PREFERRED_SIZE, 307, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblXemThng))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblXemThng_1, GroupLayout.PREFERRED_SIZE, 427, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblXemThng_1_1, GroupLayout.PREFERRED_SIZE, 495, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_1_1_1_1, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblDuongDan, GroupLayout.PREFERRED_SIZE, 419, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblNewLabel_1_1_1_1_1, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 84, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 886, Short.MAX_VALUE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_2)
					.addGap(73)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(lblXemThng, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbliMt, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblXemThng_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblXemThng_1_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1_1_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDuongDan, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(lblNewLabel_1_1_1_1_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(147, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		
	}
}
