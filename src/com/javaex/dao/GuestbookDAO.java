package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.javaex.vo.GuestbookVO;

public class GuestbookDAO {
	
	
	public void insert(GuestbookVO vo) {
		
	 Connection con=null;
	 PreparedStatement pstmt=null;
	 
	 int count;
	 try {
		 
		//드라이버 로딩
		String driver="oracle.jdbc.OracleDriver";
		Class.forName(driver);
		
		//접속
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		con = DriverManager.getConnection(url,"webdb","webdb");
		
		//쿼리 전송
		String sql ="insert into GUESTBOOK VALUES(SEQ_NO.NEXTVAL,?,?,?,SYSDATE)";
		pstmt = con.prepareStatement(sql);
		
		//insert 
		pstmt.setString(1, vo.getName());
		pstmt.setString(2, vo.getPw());
		pstmt.setString(3, vo.getContent());
		
		//결과
		count = pstmt.executeUpdate();
		
		if(count>0) {
			System.out.println("삽입완료");
		}
		
		
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 finally {
			
			try {
				
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
		
	}
	
public ArrayList<GuestbookVO> Select(){
		
		Connection con =null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		
		GuestbookVO vo = null;
		ArrayList<GuestbookVO> list= new ArrayList<GuestbookVO>();
		
		
		try {
			//드라이버 로딩
			String driver = "oracle.jdbc.OracleDriver";
			Class.forName(driver);
			
			//연결
			String url ="jdbc:oracle:thin:@localhost:1521:xe";
			con = DriverManager.getConnection(url,"webdb","webdb");
			
			
			//쿼리전송
			String sql = " SELECT no,name,pw,content,reg_date "
			            +" from guestbook "
					    +" order by no desc ";
			pstmt= con.prepareStatement(sql);
			rs =pstmt.executeQuery();
			
			//select
			while(rs.next()) {
				vo = new GuestbookVO();
				vo.setNo(rs.getInt("no"));
				vo.setName(rs.getString("name"));
				vo.setPw(rs.getString("pw"));
				vo.setContent(rs.getString("content"));
				vo.setReg_date(rs.getString("reg_date"));
				
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

public void delete(GuestbookVO vo) {
	
	 Connection con=null;
	 PreparedStatement pstmt=null;
	 
	 int count;
	 try {
		 
		//����̹� �ε� 
		String driver="oracle.jdbc.OracleDriver";
		Class.forName(driver);
		
		//����
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		con = DriverManager.getConnection(url,"webdb","webdb");
		
		//���� ����
		String sql ="DELETE from GUESTBOOK where no=? and pw=?";
		pstmt = con.prepareStatement(sql);
		
		//delete 
		//숫자는 물음표 순서입니다. 
		pstmt.setInt(1, vo.getNo());
		pstmt.setString(2, vo.getPw());
		
		//실행
		count = pstmt.executeUpdate();
		
		if(count>0) {
			System.out.println("삭제완료");
		}
		
		
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 finally {
			
			try {
				
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
		
	}
	 
		
		
	}


