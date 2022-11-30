/**
 * La Võ Minh Quân
 */
package entity;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class HoaDon {
	private String maHoaDon;
	private NhanVien nhanVien;
	private String tenKhachHang;
	private Date ngayTao;
	private double tongTien;

	public HoaDon() {
		super();
	}

	public HoaDon(String maHoaDon, NhanVien nhanVien, String tenKhachHang, Date ngayTao) {
		super();
		this.maHoaDon = maHoaDon;
		this.nhanVien = nhanVien;
		this.tenKhachHang = tenKhachHang;
		this.ngayTao = ngayTao;
	}

	public String getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public String getTenKhachHang() {
		return tenKhachHang;
	}

	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
	}

	public Date getNgayTao() {
		return ngayTao;
	}

	public void setNgayTao(Date ngayTao) {
		this.ngayTao = ngayTao;
	}


	public double getTongTien() {
		return tongTien;
	}

	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}

	@Override
	public String toString() {
		return "HoaDon [maHoaDon=" + maHoaDon + ", nhanVien=" + nhanVien + ", tenKhachHang=" + tenKhachHang
				+ ", ngayTao=" + ngayTao + ", tongTien=" + tongTien;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maHoaDon);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HoaDon other = (HoaDon) obj;
		return Objects.equals(maHoaDon, other.maHoaDon);
	}

}
