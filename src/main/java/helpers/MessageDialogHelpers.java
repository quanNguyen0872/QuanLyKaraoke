/**
 * 
 * Tác giả: La Võ Minh Quân - MSSV:19441111 - Nhóm 4
 * Ngày tạo: 23/10/2021
 * 
 * Mô tả: Lớp MessageDialogHelpers gồm những phương thức show các thông tin (message) ở nhiều dạng cho người dùng
 * 
 */
package helpers;

import java.awt.Component;

import javax.swing.JOptionPane;

public class MessageDialogHelpers {
	
	/**
	 * show 1 message dialog thông báo cho người dùng
	 * 
	 * @param parent component nơi hiển thị thông báo
	 * @param title tiêu đề thông báo
	 * @param content nội dung thông báo
	 * 
	 */
	public static void showMessageDialog(Component parent, String title, String content) {
		JOptionPane.showMessageDialog(parent, content, title, JOptionPane.INFORMATION_MESSAGE);
	}
	
	
	/**
	 * show 1 message dialog thông báo lỗi cho người dùng
	 * 
	 * @param parent component nơi hiển thị thông báo
	 * @param title tiêu đề thông báo
	 * @param content nội dung thông báo
	 * 
	 */
	public static void showErrorDialog(Component parent, String title, String content) {
		JOptionPane.showMessageDialog(parent, content, title, JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * show 1 confirm dialog hỏi người dùng xác nhận
	 * 
	 * @param parent component nơi hiển thị thông báo
	 * @param title tiêu đề thông báo
	 * @param content nội dung thông báo
	 * 
	 */
	public static int showConfirmDialog(Component parent, String title, String content) {
		int choose = JOptionPane.showConfirmDialog(parent, content, title, JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		return choose;
	}
}
