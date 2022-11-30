/**
 * 
 * Tác giả: La Võ Minh Quân - MSSV:19441111 - Nhóm 4
 * Ngày tạo: 23/10/2021
 * 
 * Mô tả: Lớp MSSQLConnection giúp kết nối với cơ sở dữ liệu Microsoft SQL Server 
 * 
 */
package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MSSQLConnection {
	public static Connection getJDBCConnection() {
		final String user = "sa";
		final String password = "123456";
		final String url = "jdbc:sqlserver://localhost:1433;databasename=TheKaraoke;user="+ user + ";password="+password;
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			return DriverManager.getConnection(url);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
