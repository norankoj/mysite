package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.javaex.vo.UserVO;

public class UserDAO {

	public void insert(UserVO vo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		int count;
		try {

			// 드라이버 로딩
			String driver = "oracle.jdbc.OracleDriver";
			Class.forName(driver);

			// 접속
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			con = DriverManager.getConnection(url, "webdb", "webdb");

			// 쿼리 전송
			String sql = "INSERT INTO users VALUES(seq_user_no.NEXTVAL,?,?,?,?)";
			pstmt = con.prepareStatement(sql);

			// 바인딩
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getGender());

			// 실행
			count = pstmt.executeUpdate();

			if (count > 0) {
				System.out.println("count" + "삽입완료");
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {

				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				System.out.println("error" + e);
			}

		}

	}

	public UserVO getUser(String email, String password) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		UserVO uservo = null;

		try {
			// 드라이버 로딩
			String driver = "oracle.jdbc.OracleDriver";
			Class.forName(driver);

			// 연결
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			con = DriverManager.getConnection(url, "webdb", "webdb");

			// 쿼리전송
			String sql = " SELECT no,name " + " from users " + " where email=? and password=?";
			pstmt = con.prepareStatement(sql);
			// 물음표에 대한 값들
			pstmt.setString(1, email);
			pstmt.setString(2, password);

			// 실행
			rs = pstmt.executeQuery();

			// select결과담기
			while (rs.next()) {
				uservo = new UserVO();// 객체생성
				uservo.setNo(rs.getInt("no"));
				uservo.setName(rs.getString("name"));

			}

		} catch (ClassNotFoundException e) {
			System.out.println("error page" + e);

		} catch (SQLException e) {
			System.out.println("error" + e);
		} finally {

			try {

				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				System.out.println("error" + e);
			}

		}

		return uservo;
	}

	public UserVO Select(int no) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		UserVO evo = null;

		try {
			// 드라이버 로딩
			String driver = "oracle.jdbc.OracleDriver";
			Class.forName(driver);

			// 연결
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			con = DriverManager.getConnection(url, "webdb", "webdb");

			// 쿼리전송
			String sql = " SELECT name,email,password,gender from users where no=? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			// 실행
			rs = pstmt.executeQuery();

			// select결과담기
			while (rs.next()) {
				evo = new UserVO();
				evo.setName(rs.getString("name"));
				evo.setEmail(rs.getString("email"));
				evo.setPassword(rs.getString("password"));
				evo.setGender(rs.getString("gender"));

			}

		} catch (ClassNotFoundException e) {
			System.out.println("error page" + e);

		} catch (SQLException e) {
			System.out.println("error" + e);
		}

		finally {

			try {

				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				System.out.println("error" + e);
			}

		}

		return evo;
	}

	public void update(UserVO vo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		int count;
		try {

			// 드라이버 로딩
			String driver = "oracle.jdbc.OracleDriver";
			Class.forName(driver);

			// 접속
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			con = DriverManager.getConnection(url, "webdb", "webdb");

			// 쿼리 전송
			String sql = "UPDATE users SET NAME=?,PASSWORD=?,GENDER=? WHERE EMAIL=?";
			pstmt = con.prepareStatement(sql);

			// 바인딩
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getGender());
			pstmt.setString(4, vo.getEmail());

			// 실행
			count = pstmt.executeUpdate();

			if (count > 0) {
				System.out.println("count" + "업뎃완료");
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {

				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				System.out.println("error" + e);
			}

		}

	}

}
