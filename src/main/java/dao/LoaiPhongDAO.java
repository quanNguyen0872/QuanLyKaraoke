/**
 * Tác giả: Nguyễn Hồng Quân - mssv:19445101 - Nhóm 4
 * Mô tả:  lớp dao dùng để thao tác với bảng Loai_Phong trong cơ sở dữ liệu
 * Ngày tạo: 31/10/2021
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.MSSQLConnection;
import entity.LoaiPhong;

public class LoaiPhongDAO {
	/**
	 * lấy tất cả loại phòng hiện có trong csdl
	 * 
	 * @return danh sách loại phòng
	 */
	public List<LoaiPhong> getDanhSachLoaiPhong() {
		List<LoaiPhong> listLoaiPhong = new ArrayList<LoaiPhong>();
		String sql = "SELECT * FROM Loai_Phong";
		try {
			Connection con = MSSQLConnection.getJDBCConnection();
			PreparedStatement prepareStatement = con.prepareStatement(sql);
			ResultSet rs = prepareStatement.executeQuery();

			while (rs.next()) {
				LoaiPhong loaiPhong = new LoaiPhong();
				loaiPhong.setMaLoaiPhong(rs.getString("MaLoaiPhong"));
				loaiPhong.setTenLoaiPhong(rs.getString("TenLoaiPhong"));
				loaiPhong.setDonGia(rs.getDouble("GiaPhong"));

				listLoaiPhong.add(loaiPhong);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listLoaiPhong;
	}

	/**
	 * Lấy 1 Loại Phòng theo mã
	 * 
	 * @param maLoaiPhong
	 * @return LoaiPhong
	 */
	public LoaiPhong getLoaiPhongTheoMa(String maLoaiPhong) {
		LoaiPhong loaiPhong = new LoaiPhong();
		String sql = "SELECT * FROM Loai_Phong" + " where MaLoaiPhong = ?";
		try {
			Connection con = MSSQLConnection.getJDBCConnection();
			PreparedStatement prepareStatement = con.prepareStatement(sql);
			prepareStatement.setString(1, maLoaiPhong);
			ResultSet rs = prepareStatement.executeQuery();
			while (rs.next()) {
				loaiPhong.setTenLoaiPhong(rs.getString("TenLoaiPhong"));
				loaiPhong.setDonGia(rs.getDouble("GiaPhong"));
				loaiPhong.setMaLoaiPhong(maLoaiPhong);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return loaiPhong;
	}

	/**
	 * update thông tin cho 1 Loai_Phong
	 * 
	 * @param loaiPhong Loại Phòng cần update
	 * @return
	 */
	public boolean updateLoaiPhong(LoaiPhong loaiPhong) {
		String sql = "UPDATE Loai_Phong SET TenLoaiPhong = ?, GiaPhong = ?" + " where MaLoaiPhong = ?";
		int rs = 0;
		try {
			Connection con = MSSQLConnection.getJDBCConnection();
			PreparedStatement prepareStatement = con.prepareStatement(sql);
			prepareStatement.setString(1, loaiPhong.getTenLoaiPhong());
			prepareStatement.setDouble(2, loaiPhong.getDonGia());
			prepareStatement.setString(3, loaiPhong.getMaLoaiPhong());

			rs = prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs > 0;
	}
}
