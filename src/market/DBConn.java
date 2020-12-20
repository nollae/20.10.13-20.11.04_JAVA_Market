package market;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConn {// ������ - ������Ʈ�� �ϳ��� �������
	// Field
	private String url = "jdbc:oracle:thin:@127.0.0.1:1521";
	private String user = "scott";
	private String pass = "tiger";

	public Connection conn;
	public Statement stmt;
	public PreparedStatement pstmt;
	public ResultSet rs;

	// Constructor
	public DBConn() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection(url, user, pass);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Statement ��ü ����
	public void getStatement() {
		try {
			stmt = conn.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// PreparedStatement ��ü ����
	public void getPreparedStatement(String sql) {
		try {
			pstmt = conn.prepareStatement(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ��ü close
	public void close() {
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}// class
