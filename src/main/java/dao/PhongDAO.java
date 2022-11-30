/**
 * Tác giả: Nguyễn Hồng Quân - mssv:19445101 - Nhóm 4
 * Mô tả: lớp dao dùng để thao tác với bảng Phong trong cơ sở dữ liệu
 * Ngày tạo: 31/10/2021
 */
package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import connectDB.MSSQLConnection;
import entity.LoaiPhong;
import entity.Phong;

public class PhongDAO {
	/**
	 * lấy tất cả Phòng hiện có trong csdl
	 * 
	 * @return danh sách Phòng
	 */
	public List<Phong> getDanhSachPhong() {
		LoaiPhongDAO loaiPhongDAO = new LoaiPhongDAO();
		List<Phong> listPhong = new ArrayList<Phong>();
		String sql = "SELECT * FROM Phong";

		Connection con = MSSQLConnection.getJDBCConnection();
		PreparedStatement prepareStatement = null;

		try {
			prepareStatement = con.prepareStatement(sql);
			ResultSet rs = prepareStatement.executeQuery();

			while (rs.next()) {
				Phong phong = new Phong();
				LoaiPhong loaiPhong = new LoaiPhong();

				int maP = rs.getInt(1);
				String maPhong = "MP" + maP;
				phong.setMaPhong(maPhong);

				phong.setTenPhong(rs.getString(2));
				loaiPhong = loaiPhongDAO.getLoaiPhongTheoMa(rs.getString(3));

				phong.setLoaiPhong(loaiPhong);
				phong.setTrangThai(rs.getString(4));

				listPhong.add(phong);
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

		return listPhong;
	}

	/**
	 * Hàm dùng để insert 1 Phòng vào cơ sở dữ liệu
	 * 
	 * @param p tham số truy�?n vào là Phong
	 * @return trả v�? true nếu insert thành công trả v�? false nếu insert thất bại
	 * @throws IOException
	 */
	public boolean addPhong(Phong p) {
		String sql = "INSERT INTO [dbo].[Phong] ([TenPhong], [MaLoaiPhong], [TinhTrang])" + " VALUES(?,?,?)";
		int rs = 0;

		Connection con = MSSQLConnection.getJDBCConnection();
		PreparedStatement prepareStatement = null;

		try {
			prepareStatement = con.prepareStatement(sql);

			prepareStatement.setString(1, p.getTenPhong());
			prepareStatement.setString(2, p.getLoaiPhong().getMaLoaiPhong());
			prepareStatement.setString(3, p.getTrangThai());

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
	 * Lấy 1 Phòng theo mã
	 * 
	 * @param maPhong
	 * @return Phong
	 */
	public Phong getPhongTheoMa(String maPhong) {
		Phong phong = new Phong();
		LoaiPhongDAO loaiPhongDAO = new LoaiPhongDAO();
		String sql = "SELECT * FROM Phong" + " where MaPhong = ?";

		Connection con = MSSQLConnection.getJDBCConnection();
		PreparedStatement prepareStatement = null;

		try {
			prepareStatement = con.prepareStatement(sql);

			String stt = maPhong.replaceAll("MP", "");
			prepareStatement.setInt(1, Integer.parseInt(stt));

			ResultSet rs = prepareStatement.executeQuery();
			while (rs.next()) {
				phong.setMaPhong(maPhong);
				phong.setTenPhong(rs.getString("TenPhong"));

				LoaiPhong loaiPhong = loaiPhongDAO.getLoaiPhongTheoMa(rs.getString("MaLoaiPhong"));
				phong.setLoaiPhong(loaiPhong);
				phong.setTrangThai(rs.getString("TinhTrang"));
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

		return phong;
	}

	/**
	 * update thông tin cho 1 Phong
	 * 
	 * @param phong Phòng cần update
	 * @return
	 */
	public boolean updatePhong(Phong phong) {
		String sql = "UPDATE Phong SET TenPhong = ?, TinhTrang = ?" + " where MaPhong = ?";
		int rs = 0;

		Connection con = MSSQLConnection.getJDBCConnection();
		PreparedStatement prepareStatement = null;

		try {
			prepareStatement = con.prepareStatement(sql);
			prepareStatement.setString(1, phong.getTenPhong());
			prepareStatement.setString(2, phong.getTrangThai());

			String maPhong = phong.getMaPhong();

			String stt = maPhong.replaceAll("MP", "");

			prepareStatement.setInt(3, Integer.parseInt(stt));

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
	 * xóa thông tin của 1 Phòng
	 * 
	 * @param maPhong
	 * @return boolean
	 */
	public boolean deletePhong(String maPhong) {
		String sql = "DELETE FROM Phong" + " where MaPhong = ?";
		int rs = 0;

		Connection con = MSSQLConnection.getJDBCConnection();
		PreparedStatement prepareStatement = null;

		try {
			prepareStatement = con.prepareStatement(sql);
			String stt = maPhong.replaceAll("MP", "");

			prepareStatement.setInt(1, Integer.parseInt(stt));

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
	 * lấy tất cả Phòng trống trong csdl
	 * 
	 * Quân sửa: lấy tất cả phòng theo tình trạng
	 * 
	 * @return danh sách Phòng trống
	 */
	public List<Phong> getDanhSachPhongTheoTinhTrang(String tinhTrang) {
		LoaiPhongDAO loaiPhongDAO = new LoaiPhongDAO();
		List<Phong> listPhongTrong = new ArrayList<Phong>();

		String sql = "SELECT * FROM Phong WHERE TinhTrang = ?";

		Connection con = MSSQLConnection.getJDBCConnection();
		PreparedStatement prepareStatement = null;

		try {
			prepareStatement = con.prepareStatement(sql);
			prepareStatement.setString(1, tinhTrang);
			ResultSet rs = prepareStatement.executeQuery();

			while (rs.next()) {
				Phong phong = new Phong();
				LoaiPhong loaiPhong = new LoaiPhong();

				int maP = rs.getInt(1);
				String maPhong = "MP" + maP;
				phong.setMaPhong(maPhong);

				phong.setTenPhong(rs.getString(2));

				loaiPhong = loaiPhongDAO.getLoaiPhongTheoMa(rs.getString(3));
				phong.setLoaiPhong(loaiPhong);
				phong.setTrangThai(rs.getString(4));

				listPhongTrong.add(phong);
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

		return listPhongTrong;
	}
	
	public List<Phong> getDanhSachPhongTheoNgayDatTruoc(Date ngayDatTruoc) {
		LoaiPhongDAO loaiPhongDAO = new LoaiPhongDAO();
		List<Phong> listPhongDatTruoc = new ArrayList<Phong>();

		String sql = "SELECT Phong.MaPhong, Phong.TenPhong, Phong.MaLoaiPhong, Phong.TinhTrang\r\n"
				+ "FROM     Don_Dat_Phong INNER JOIN\r\n"
				+ "                  Phong ON Don_Dat_Phong.MaPhong = Phong.MaPhong\r\n"
				+ "where 	Day(ThoiGianVao) = ? and MONTH(ThoiGianVao) = ? and YEAR(ThoiGianVao) = ? and TrangThaiDon = N'Đặt Trước'";

		Connection con = MSSQLConnection.getJDBCConnection();
		PreparedStatement prepareStatement = null;

		try {
			prepareStatement = con.prepareStatement(sql);
			prepareStatement.setInt(1, ngayDatTruoc.getDate());
			prepareStatement.setInt(2, 1 + ngayDatTruoc.getMonth());
			prepareStatement.setInt(3, 1900 + ngayDatTruoc.getYear());
			ResultSet rs = prepareStatement.executeQuery();

			while (rs.next()) {
				Phong phong = new Phong();
				LoaiPhong loaiPhong = new LoaiPhong();

				int maP = rs.getInt(1);
				String maPhong = "MP" + maP;
				phong.setMaPhong(maPhong);

				phong.setTenPhong(rs.getString(2));

				loaiPhong = loaiPhongDAO.getLoaiPhongTheoMa(rs.getString(3));
				phong.setLoaiPhong(loaiPhong);
				phong.setTrangThai(rs.getString(4));

				listPhongDatTruoc.add(phong);
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

		return listPhongDatTruoc;
	}
}
