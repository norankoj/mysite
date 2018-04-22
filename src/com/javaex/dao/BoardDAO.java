package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.javaex.vo.BoardVO;
import com.javaex.vo.GuestbookVO;
import com.javaex.vo.UserVO;

public class BoardDAO {
	
	public void insert(BoardVO vo) {

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
			String sql = "INSERT INTO board VALUES(seq_user_no.NEXTVAL,?,?,0,?,SYSDATE)";
			pstmt = con.prepareStatement(sql);

			// 바인딩
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getUser_no());
			
			

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
	
public ArrayList<BoardVO> Select(){
		
		Connection con =null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		
		BoardVO vo = null;
		ArrayList<BoardVO> list= new ArrayList<BoardVO>();
		
		
		try {
			//드라이버 로딩
			String driver = "oracle.jdbc.OracleDriver";
			Class.forName(driver);
			
			//연결
			String url ="jdbc:oracle:thin:@localhost:1521:xe";
			con = DriverManager.getConnection(url,"webdb","webdb");
			
			
			/*//쿼리전송
			String sql = " SELECT no,title,content,hit,user_no,reg_date "
			            +" from board "
					    +" order by no desc ";*/
			
			String sql =" Select b.no, b.title, b.hit, b.reg_date, b.user_no, u.name "
					   +" from BOARD b,USERS u "
					   +" where B.USER_NO = U.NO "
			           +" order by no desc "; 
			pstmt= con.prepareStatement(sql);
			rs =pstmt.executeQuery();
			
			//select
			while(rs.next()) {
				vo = new BoardVO();
				vo.setNo(rs.getInt("no"));
				vo.setTitle(rs.getString("title"));
				//vo.setContent(rs.getString("content"));
				vo.setHit(rs.getInt("hit"));
				vo.setUser_no(rs.getInt("user_no"));
				vo.setReg_date(rs.getString("reg_date"));
				vo.setName(rs.getString("name"));
				
				list.add(vo);
				
			}			
			
				
		} catch (ClassNotFoundException e) {
			System.out.println("error page"+e);

		} catch (SQLException e) {
			System.out.println("error"+e);
		}
		

		finally {
			
			try {
				
				if(rs!=null) {
				rs.close();
				}
				if(pstmt!=null) {
					pstmt.close();
				}
				if(con!=null) {
					con.close();
				}
			} catch (SQLException e) {
				System.out.println("error"+e);
			}
			
		}
			
		return list;
	}

public BoardVO getcontent(int no){
	
	Connection con =null;
	PreparedStatement pstmt = null;
	ResultSet rs =null;
	
	BoardVO vo = null;
	
	try {
		//드라이버 로딩
		String driver = "oracle.jdbc.OracleDriver";
		Class.forName(driver);
		
		//연결
		String url ="jdbc:oracle:thin:@localhost:1521:xe";
		con = DriverManager.getConnection(url,"webdb","webdb");
		
		
		//쿼리전송
		String sql = " SELECT no,title,content,user_no,hit "
		            +" from board where no= ?";
		
		pstmt= con.prepareStatement(sql);
		pstmt.setInt(1, no);
		rs =pstmt.executeQuery();
		
		//select
		while(rs.next()) {
			vo = new BoardVO();
			vo.setNo(rs.getInt("no"));
			vo.setTitle(rs.getString("title"));
			vo.setContent(rs.getString("content"));
			vo.setUser_no(rs.getInt("user_no"));
			vo.setHit(rs.getInt("hit"));
		}			
		
			
	} catch (ClassNotFoundException e) {
		System.out.println("error page"+e);

	} catch (SQLException e) {
		System.out.println("error"+e);
	}
	

	finally {
		
		try {
			
			if(rs!=null) {
			rs.close();
			}
			if(pstmt!=null) {
				pstmt.close();
			}
			if(con!=null) {
				con.close();
			}
		} catch (SQLException e) {
			System.out.println("error"+e);
		}
		
	}
		
	return vo;
}


public void update(BoardVO vo) {

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
		String sql = "UPDATE board SET title=?,content=? WHERE no=?";
		pstmt = con.prepareStatement(sql);

		// 바인딩
		pstmt.setString(1, vo.getTitle());
		pstmt.setString(2, vo.getContent());
		pstmt.setInt(3, vo.getNo());

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


public void updateHit(BoardVO vo) {

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
		String sql = "UPDATE board SET hit=? WHERE no=?";
		pstmt = con.prepareStatement(sql);

		// 바인딩
		pstmt.setInt(1, vo.getHit());
		pstmt.setInt(2, vo.getNo());
		

		// 실행
		count = pstmt.executeUpdate();

		if (count > 0) {
			System.out.println("count" + "조회수");
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
