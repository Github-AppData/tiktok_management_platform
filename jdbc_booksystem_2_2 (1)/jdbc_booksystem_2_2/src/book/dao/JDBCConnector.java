package book.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCConnector {
	// 포트번호의 역할 : ex) 유한대 - (교학과 21, 행정과 20, ...)
	// 대표전화 역할이 URL, 과 전화번호가 포트번호
	
	/// 기본 설정 - base configuration
	private static final String DRIVER_PATH = "com.mysql.cj.jdbc.Driver"; // 메모리에 로딩할 수 있다.
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/db2022_2_2?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8"; // http 같은 프로토콜이다. - 절대 바꾸면 안된다.
	private static final String ID = "root";
	private static final String PWD = "1234";
	private static Connection con;
	
	public static Connection getCon() {
		try {
			Class.forName(DRIVER_PATH);
			System.out.println("정상적으로 JDBC Driver가 Road 되었습니다.");
			con = DriverManager.getConnection(URL, ID, PWD); // 예외 처리를 해야 사용할 수 있다.
			System.out.println("연결이 잘 되었습니다.");
		} catch (ClassNotFoundException e) { 
			e.printStackTrace();
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		return con;
	}
	
	public static void resultSetTest() {
		try {
			Statement stmt = con.createStatement();
			String sql = "select * from book";
			ResultSet rs = stmt.executeQuery(sql);  // sql 명령어의 종류마다 메소드가 다르다.
			while (rs.next()) {
				System.out.print(rs.getString("name") + "\t");
				System.out.print(rs.getString("author") + "\t");
				System.out.println(rs.getInt("price"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		getCon();
		resultSetTest();
	}

}
