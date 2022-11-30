/**
 * 
 * Tác giả: La Võ Minh Quân - MSSV:19441111 - Nhóm 4
 * Ngày tạo: 23/10/2021
 * 
 * Mô tả: Lớp taiKhoanDAO có chức năng là thao tác trực tiếp với cơ sở dữ liệu lấy thông tin tài khoản từ cơ sở dữ liệu
 * 
 */

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.MSSQLConnection;
import entity.NhanVien;
import entity.TaiKhoan;

public class TaiKhoanDAO {

	/**
	 * Phương thức kiểm tra login, nếu tên đăng nhập và mật khẩu đúng thì trả về
	 * entity TaiKhoan
	 * 
	 * @param tenDangNhap tên đăng nhập của tài khoản
	 * @param matKhau     mật khẩu đăng nhập của tài khoản
	 * 
	 * @return TaiKhoan
	 */
	public TaiKhoan checkLogin(String tenDangNhap, String matKhau) {
		TaiKhoan taiKhoan = new TaiKhoan();
		String sql = "SELECT * FROM TaiKhoan" + " WHERE TenDangNhap=? and MatKhau=?";

		Connection con = MSSQLConnection.getJDBCConnection();
		PreparedStatement prepareStatement = null;

		try {
			prepareStatement = con.prepareStatement(sql);

			prepareStatement.setString(1, tenDangNhap);
			prepareStatement.setString(2, matKhau);
			ResultSet rs = prepareStatement.executeQuery();
			if (rs.next()) {
				taiKhoan.setTenDangNhap(tenDangNhap);
				taiKhoan.setVaiTro(rs.getString("VaiTro"));

				int maNV = rs.getInt("MaNhanVien");
				String maNhanVien = "NV" + maNV;

				NhanVienDAO nvDAO = new NhanVienDAO();
				NhanVien nhanVien = nvDAO.getNhanVienTheoMa(maNhanVien);

				taiKhoan.setNhanVien(nhanVien);
				return taiKhoan;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				prepareStatement.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * Lấy danh sách tài khoản từ database
	 * 
	 * @return
	 */
	public List<TaiKhoan> getDanhSachTaiKhoan() {
		List<TaiKhoan> listTaiKhoan = new ArrayList<TaiKhoan>();
		String sql = "SELECT * FROM TaiKhoan";

		Connection con = MSSQLConnection.getJDBCConnection();
		PreparedStatement prepareStatement = null;

		try {
			prepareStatement = con.prepareStatement(sql);

			ResultSet rs = prepareStatement.executeQuery();

			while (rs.next()) {
				TaiKhoan tk = new TaiKhoan();

				tk.setTenDangNhap(rs.getString("TenDangNhap"));
				tk.setVaiTro(rs.getString("VaiTro"));

				int maNhanVien = rs.getInt("MaNhanVien");
				NhanVienDAO nvDAO = new NhanVienDAO();
				tk.setNhanVien(nvDAO.getNhanVienTheoMa("NV" + maNhanVien));
				listTaiKhoan.add(tk);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				prepareStatement.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listTaiKhoan;
	}

	/**
	 * Thêm 1 tài khoản
	 * 
	 * @param tk
	 * @return true nếu thêm thành công . false nếu thêm thất bại
	 */
	public boolean addTaiKhoan(TaiKhoan tk) {
		String sql = "INSERT INTO [dbo].[TaiKhoan] ([TenDangNhap], [MatKhau], [VaiTro], [MaNhanVien], [CauHoi], [TraLoi])"
				+ " VALUES(?,?,?,?,?,?)";
		int rs = 0;

		Connection con = MSSQLConnection.getJDBCConnection();
		PreparedStatement prepareStatement = null;

		try {

			prepareStatement = con.prepareStatement(sql);

			prepareStatement.setString(1, tk.getTenDangNhap());
			prepareStatement.setString(2, tk.getMatKhau());
			prepareStatement.setString(3, tk.getVaiTro());

			String maNV = tk.getNhanVien().getMaNhanVien();
			String maNhanVien = maNV.replaceAll("NV", "");
			prepareStatement.setInt(4, Integer.parseInt(maNhanVien));

			prepareStatement.setString(5, tk.getCauHoi());
			prepareStatement.setString(6, tk.getTraLoi());

			rs = prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				prepareStatement.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rs > 0;
	}

	/**
	 * cập nhật thông tin cho 1 tài khoản
	 * 
	 * @param nv nhân viên cần cập nhật
	 * @return
	 */
	public boolean updateMatKhau(TaiKhoan tk) {
		String sql = "UPDATE TaiKhoan SET MatKhau = ?" + " where TenDangNhap = ?";
		int rs = 0;

		Connection con = MSSQLConnection.getJDBCConnection();
		PreparedStatement prepareStatement = null;

		try {
			prepareStatement = con.prepareStatement(sql);

			prepareStatement.setString(1, tk.getMatKhau());
			prepareStatement.setString(2, tk.getTenDangNhap());
			rs = prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				prepareStatement.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rs > 0;
	}

	/**
	 * xóa thông tin của 1 tài khoản
	 * 
	 * @param tên đăng nhập cần xóa
	 * @return
	 */
	public boolean deleteTaiKhoan(String tenDN) {
		String sql = "DELETE FROM TaiKhoan" + " where TenDangNhap = ?";
		int rs = 0;

		Connection con = MSSQLConnection.getJDBCConnection();
		PreparedStatement prepareStatement = null;

		try {
			prepareStatement = con.prepareStatement(sql);

			prepareStatement.setString(1, tenDN);
			rs = prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				prepareStatement.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rs > 0;
	}

	/**
	 * lấy 1 tài khoản bằng tên tài khoản
	 * 
	 * @param tên đăng nhập
	 * @return
	 */
	public TaiKhoan getTaiKhoanTheoTen(String tenDN) {
		TaiKhoan tk = new TaiKhoan();
		String sql = "SELECT * FROM TaiKhoan" + " where TenDangNhap = ?";

		Connection con = MSSQLConnection.getJDBCConnection();
		PreparedStatement prepareStatement = null;

		try {
			prepareStatement = con.prepareStatement(sql);

			prepareStatement.setString(1, tenDN);
			ResultSet rs = prepareStatement.executeQuery();
			while (rs.next()) {
				tk.setTenDangNhap(tenDN);
				tk.setVaiTro(rs.getString("VaiTro"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				prepareStatement.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return tk;
	}

	/**
	 * lấy câu hỏi bảo mật theo tên tài khoản
	 * 
	 * @param tên đăng nhập
	 * @return
	 */
	public String getCauHoiTheoTen(String tenDN) {
		String cauHoi = null;
		String sql = "SELECT CauHoi FROM TaiKhoan" + " where TenDangNhap = ?";

		Connection con = MSSQLConnection.getJDBCConnection();
		PreparedStatement prepareStatement = null;

		try {
			prepareStatement = con.prepareStatement(sql);

			prepareStatement.setString(1, tenDN);
			ResultSet rs = prepareStatement.executeQuery();
			while (rs.next()) {
				cauHoi = rs.getString("CauHoi");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				prepareStatement.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cauHoi;
	}

	/**
	 * cập nhật mật khẩu cho tài khoản bị quên mật khẩu qua câu trả lời
	 * 
	 * @param tên đăng nhập cần xóa
	 * @return
	 */
	public boolean updateMatKhauTheoTenVaTraLoi(String tenDN, String cauTraLoi, String matKhauMoi) {
		String sql = "UPDATE TaiKhoan SET MatKhau = ?" + " WHERE TenDangNhap = ? AND TraLoi = ?";
		int rs = 0;

		Connection con = MSSQLConnection.getJDBCConnection();
		PreparedStatement prepareStatement = null;

		try {
			prepareStatement = con.prepareStatement(sql);

			prepareStatement.setString(1, matKhauMoi);
			prepareStatement.setString(2, tenDN);
			prepareStatement.setString(3, cauTraLoi);

			rs = prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				prepareStatement.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rs > 0;
	}

	public boolean checkExist(int maNV) {
		boolean check = false;
		String sql = "select * from TaiKhoan where MaNhanVien = ?";

		Connection con = MSSQLConnection.getJDBCConnection();
		PreparedStatement prepareStatement = null;

		try {
			prepareStatement = con.prepareStatement(sql);

			prepareStatement.setInt(1, maNV);
			ResultSet rs = prepareStatement.executeQuery();
			check = rs.next();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				prepareStatement.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return check;
	}
}
