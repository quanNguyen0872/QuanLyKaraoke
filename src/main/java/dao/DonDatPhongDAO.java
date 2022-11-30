/**
 * Tác giả: Nguyễn Hồng Quân - mssv:19445101 - Nhóm 4
 * Mô tả: lớp dao dùng để thao tác với bảng Thong_Tin_Dat_Phong trong cơ sở dữ liệu
 * Ngày tạo: 02/11/2021
 */
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;

import connectDB.MSSQLConnection;
import entity.KhachHang;
import entity.Phong;
import entity.DonDatPhong;

public class DonDatPhongDAO {

	public boolean addThongTinDatPhong(DonDatPhong donDatPhong) {
		String sql = "INSERT INTO [dbo].[Don_Dat_Phong] ([ThoiGianVao], [MaKhachHang], [MaPhong], [TrangThaiDon])"
				+ " VALUES(?,?,?,?)";
		int rs = 0;
		try {
			Connection con = MSSQLConnection.getJDBCConnection();
			PreparedStatement prepareStatement = con.prepareStatement(sql);

			LocalDateTime ldt = LocalDateTime.of(1900 + donDatPhong.getThoiGianVao().getYear(),
					1 + donDatPhong.getThoiGianVao().getMonth(), donDatPhong.getThoiGianVao().getDate(),
					donDatPhong.getThoiGianVao().getHours(), donDatPhong.getThoiGianVao().getMinutes(),
					donDatPhong.getThoiGianVao().getSeconds());
			prepareStatement.setTimestamp(1, Timestamp.valueOf(ldt));
//			prepareStatement.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
			String mkh = donDatPhong.getKhachHang().getMaKhachHang().replaceAll("KH", "");
			prepareStatement.setInt(2, Integer.parseInt(mkh));
			String mp = donDatPhong.getPhong().getMaPhong().replaceAll("MP", "");
			prepareStatement.setInt(3, Integer.parseInt(mp));
			prepareStatement.setString(4, donDatPhong.getTrangThaiDon());

			rs = prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs > 0;
	}

	/**
	 * lấy tất cả Phòng hiện có trong csdl
	 * 
	 * @return danh sách Phòng
	 */
	public List<DonDatPhong> getDanhSachDonDatPhong() {
		KhachHangDAO khachHangDAO = new KhachHangDAO();
		PhongDAO phongDAO = new PhongDAO();
		List<DonDatPhong> listdonDatPhong = new ArrayList<DonDatPhong>();

		String sql = "SELECT * FROM Don_Dat_Phong";
		try {
			Connection con = MSSQLConnection.getJDBCConnection();
			PreparedStatement prepareStatement = con.prepareStatement(sql);
			ResultSet rs = prepareStatement.executeQuery();

			while (rs.next()) {
				DonDatPhong ttdp = new DonDatPhong();
				Phong phong = new Phong();
				KhachHang khachHang = new KhachHang();

				int maTTDP = rs.getInt(1);
				String maTTDatPhong = "DP" + maTTDP;

				Timestamp thoiGianVao = rs.getTimestamp(2);

				khachHang = khachHangDAO.getKhachHangTheoMa(rs.getString(3));
				phong = phongDAO.getPhongTheoMa(rs.getString(4));
				String trangThaiPhong = rs.getString(5);

				ttdp.setMaDatPhong(maTTDatPhong);
				ttdp.setThoiGianVao(thoiGianVao);
				ttdp.setKhachHang(khachHang);
				ttdp.setPhong(phong);
				ttdp.setTrangThaiDon(trangThaiPhong);

				listdonDatPhong.add(ttdp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listdonDatPhong;
	}

	public boolean updateTrangThaiDonDat_Huy(String maDonDat) {
		String sql = "Update Don_Dat_Phong\r\n" + "SET TrangThaiDon = N'Hủy Đơn'\r\n" + "Where MaDatPhong = ?";
		int rs = 0;

		Connection con = MSSQLConnection.getJDBCConnection();
		PreparedStatement prepareStatement = null;

		try {
			prepareStatement = con.prepareStatement(sql);

			String stt = maDonDat.replaceAll("DP", "");

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

	public boolean updateTrangThaiDonDat_DaThanhToan(String maDonDat) {
		String sql = "Update Don_Dat_Phong\r\n" + "SET TrangThaiDon = N'Đã Thanh Toán'\r\n" + "Where MaDatPhong = ?";
		int rs = 0;

		Connection con = MSSQLConnection.getJDBCConnection();
		PreparedStatement prepareStatement = null;

		try {
			prepareStatement = con.prepareStatement(sql);

			String stt = maDonDat.replaceAll("DP", "");

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

	public boolean updateTrangThaiDonDat_ChoThanhToan(String maDatPhong) {
		String sql = "Update Don_Dat_Phong\r\n"
				+ "SET TrangThaiDon = N'Chưa Thanh Toán', ThoiGianVao = ?\r\n"
				+ "where MaDatPhong = ?";
		int rs = 0;

		Connection con = MSSQLConnection.getJDBCConnection();
		PreparedStatement prepareStatement = null;

		try {
			prepareStatement = con.prepareStatement(sql);

			String stt = maDatPhong.replaceAll("DP", "");
			prepareStatement.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
			prepareStatement.setInt(2, Integer.parseInt(stt));

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

	public DonDatPhong getDonDatPhongTheoMa(String maDP) {
		KhachHangDAO khachHangDAO = new KhachHangDAO();
		PhongDAO phongDAO = new PhongDAO();
		DonDatPhong ddp = new DonDatPhong();
		String sql = "select * from Don_Dat_Phong where MaDatPhong = ?";
		try {
			Connection con = MSSQLConnection.getJDBCConnection();
			PreparedStatement prepareStatement = con.prepareStatement(sql);
			String stt = maDP.replaceAll("DP", "");
			prepareStatement.setInt(1, Integer.parseInt(stt));
			ResultSet rs = prepareStatement.executeQuery();
			while (rs.next()) {
				ddp = new DonDatPhong();
				Phong phong = new Phong();
				KhachHang khachHang = new KhachHang();

				int maTTDP = rs.getInt(1);
				String maTTDatPhong = "DP" + maTTDP;

				Timestamp thoiGianVao = rs.getTimestamp(2);

				khachHang = khachHangDAO.getKhachHangTheoMa(rs.getString(3));
				phong = phongDAO.getPhongTheoMa(rs.getString(4));
				String trangThaiPhong = rs.getString(5);

				ddp.setMaDatPhong(maTTDatPhong);
				ddp.setThoiGianVao(thoiGianVao);
				ddp.setKhachHang(khachHang);
				ddp.setPhong(phong);
				ddp.setTrangThaiDon(trangThaiPhong);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ddp;
	}
	
	/**
	 * lấy danh sách đơn đặt phòng theo trạng thái đơn
	 * @param tinhTrang
	 * @return danh sách phòng
	 */
	public List<DonDatPhong> getDanhSachDonDatPhong(String trangThaiDon) {
		KhachHangDAO khachHangDAO = new KhachHangDAO();
		PhongDAO phongDAO = new PhongDAO();
		List<DonDatPhong> dsDonDatPhong = new ArrayList<DonDatPhong>();

		String sql = "SELECT * FROM Don_Dat_Phong WHERE TrangThaiDon = ?";

		Connection con = MSSQLConnection.getJDBCConnection();
		PreparedStatement prepareStatement = null;

		try {
			prepareStatement = con.prepareStatement(sql);
			prepareStatement.setString(1, trangThaiDon);
			ResultSet rs = prepareStatement.executeQuery();

			while (rs.next()) {
				
				DonDatPhong ddp = new DonDatPhong();
				Phong phong = new Phong();
				KhachHang khachHang = new KhachHang();

				int maTTDP = rs.getInt(1);
				String maTTDatPhong = "DP" + maTTDP;
				
				Timestamp thoiGianVao = rs.getTimestamp(2);

				khachHang = khachHangDAO.getKhachHangTheoMa(rs.getString(3));
				phong = phongDAO.getPhongTheoMa(rs.getString(4));
				String trangThaiPhong = rs.getString(5);

				ddp.setMaDatPhong(maTTDatPhong);
				ddp.setThoiGianVao(thoiGianVao);
				ddp.setKhachHang(khachHang);
				ddp.setPhong(phong);
				ddp.setTrangThaiDon(trangThaiPhong);

				dsDonDatPhong.add(ddp);
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

		return dsDonDatPhong;
	}
	
	public List<DonDatPhong> getDanhSachDonDatTruoc() {
		KhachHangDAO khachHangDAO = new KhachHangDAO();
		PhongDAO phongDAO = new PhongDAO();
		List<DonDatPhong> dsDonDatPhong = new ArrayList<DonDatPhong>();

		String sql = "SELECT * FROM Don_Dat_Phong WHERE TrangThaiDon = N'Đặt Trước' or TrangThaiDon = N'Hủy Đơn'";

		Connection con = MSSQLConnection.getJDBCConnection();
		PreparedStatement prepareStatement = null;

		try {
			prepareStatement = con.prepareStatement(sql);
			ResultSet rs = prepareStatement.executeQuery();

			while (rs.next()) {
				
				DonDatPhong ddp = new DonDatPhong();
				Phong phong = new Phong();
				KhachHang khachHang = new KhachHang();

				int maTTDP = rs.getInt(1);
				String maTTDatPhong = "DP" + maTTDP;
				
				Timestamp thoiGianVao = rs.getTimestamp(2);

				khachHang = khachHangDAO.getKhachHangTheoMa(rs.getString(3));
				phong = phongDAO.getPhongTheoMa(rs.getString(4));
				String trangThaiPhong = rs.getString(5);

				ddp.setMaDatPhong(maTTDatPhong);
				ddp.setThoiGianVao(thoiGianVao);
				ddp.setKhachHang(khachHang);
				ddp.setPhong(phong);
				ddp.setTrangThaiDon(trangThaiPhong);

				dsDonDatPhong.add(ddp);
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

		return dsDonDatPhong;
	}
	
	public List<DonDatPhong> getDanhSachDonThuePhong() {
		KhachHangDAO khachHangDAO = new KhachHangDAO();
		PhongDAO phongDAO = new PhongDAO();
		List<DonDatPhong> dsDonDatPhong = new ArrayList<DonDatPhong>();

		String sql = "SELECT * FROM Don_Dat_Phong WHERE TrangThaiDon != N'Đặt Trước'";

		Connection con = MSSQLConnection.getJDBCConnection();
		PreparedStatement prepareStatement = null;

		try {
			prepareStatement = con.prepareStatement(sql);
			ResultSet rs = prepareStatement.executeQuery();

			while (rs.next()) {
				
				DonDatPhong ddp = new DonDatPhong();
				Phong phong = new Phong();
				KhachHang khachHang = new KhachHang();

				int maTTDP = rs.getInt(1);
				String maTTDatPhong = "DP" + maTTDP;
				
				Timestamp thoiGianVao = rs.getTimestamp(2);

				khachHang = khachHangDAO.getKhachHangTheoMa(rs.getString(3));
				phong = phongDAO.getPhongTheoMa(rs.getString(4));
				String trangThaiPhong = rs.getString(5);

				ddp.setMaDatPhong(maTTDatPhong);
				ddp.setThoiGianVao(thoiGianVao);
				ddp.setKhachHang(khachHang);
				ddp.setPhong(phong);
				ddp.setTrangThaiDon(trangThaiPhong);

				dsDonDatPhong.add(ddp);
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

		return dsDonDatPhong;
	}
}
