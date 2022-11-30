/**
 * 
 * Tác giả: La Võ Minh Quân - MSSV:19441111 - Nhóm 4
 * Ngày tạo: 23/10/2021
 * 
 * Mô tả: Lớp thực thể tài khoản chứa dữ liệu để đăng nhập vào ứng dụng
 * dữ liệu gồm : tenDangNhap (tên đăng nhập), matKhau(mật khẩu đăng nhập), vaiTro(vai trò)
 */

package entity;

import java.util.Objects;

public class TaiKhoan {
	private String tenDangNhap;
	private String matKhau;
	private String vaiTro;
	private NhanVien nhanVien;
	private String cauHoi;
	private String traLoi;

	public TaiKhoan() {
		super();
	}

	public TaiKhoan(String tenDangNhap, String matKhau, String vaiTro, NhanVien nhanVien, String cauHoi,
			String traLoi) {
		super();
		this.tenDangNhap = tenDangNhap;
		this.matKhau = matKhau;
		this.vaiTro = vaiTro;
		this.nhanVien = nhanVien;
		this.cauHoi = cauHoi;
		this.traLoi = traLoi;
	}

	public String getTenDangNhap() {
		return tenDangNhap;
	}

	public void setTenDangNhap(String tenDangNhap) {
		this.tenDangNhap = tenDangNhap;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public String getVaiTro() {
		return vaiTro;
	}

	public void setVaiTro(String vaiTro) {
		this.vaiTro = vaiTro;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public String getCauHoi() {
		return cauHoi;
	}

	public void setCauHoi(String cauHoi) {
		this.cauHoi = cauHoi;
	}

	public String getTraLoi() {
		return traLoi;
	}

	public void setTraLoi(String traLoi) {
		this.traLoi = traLoi;
	}

	@Override
	public String toString() {
		return "TaiKhoan [tenDangNhap=" + tenDangNhap + ", matKhau=" + matKhau + ", vaiTro=" + vaiTro + ", nhanVien="
				+ nhanVien + ", cauHoi=" + cauHoi + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(tenDangNhap);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaiKhoan other = (TaiKhoan) obj;
		return Objects.equals(tenDangNhap, other.tenDangNhap);
	}

}
