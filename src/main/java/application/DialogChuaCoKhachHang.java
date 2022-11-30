/**
 * Tác giả: La Võ Minh Quân - 19441111
 * Mô tả: Thông báo với người dùng là số điện thoại này vẫn chưa có tồn tại hỏi xem có nne6
 * thêm khách hàng mới hay không
 */
package application;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogChuaCoKhachHang extends JDialog {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private DialogThemKhachHang themKhachHang;

	

	/**
	 * Create the dialog.
	 */
	public DialogChuaCoKhachHang() {
		setTitle("Th\u00F4ng B\u00E1o");
		setBounds(100, 100, 420, 150);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel lblNewLabel = new JLabel("Kh\u00F4ng T\u00ECm Th\u1EA5y S\u1ED1 \u0110i\u1EC7n Tho\u1EA1i N\u00E0y!!!");
			lblNewLabel.setFont(new Font("Serif", Font.PLAIN, 20));
			contentPanel.add(lblNewLabel);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btn_OpenQLKH = new JButton("Th\u00EAm Kh\u00E1ch H\u00E0ng");
				btn_OpenQLKH.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
						
						themKhachHang = new DialogThemKhachHang();
						themKhachHang.setVisible(true);
						
						
					}
				});
				btn_OpenQLKH.setActionCommand("OK");
				buttonPane.add(btn_OpenQLKH);
				getRootPane().setDefaultButton(btn_OpenQLKH);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
