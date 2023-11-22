package Tcptest;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class MemberDAO { //데이터베이스 접근 객체 내부 쿼리문 실행
	
	private static Connection conn;
	
	private PreparedStatement pstmt; //Statement 클래스와 달리 매개변수를 값 대입으로 사용 가능
	private ResultSet rs; //쿼리문 실행 결과값
	
    private void getConnection() throws ClassNotFoundException, SQLException{
            //접속정보
            String url = "jdbc:mariadb://127.0.0.1:3306/TB_Member02";
            String user = "root";
            String pw = "8327";
           
            //JDBC드라이버 로드
            Class.forName("org.mariadb.jdbc.Driver");
           
            //mariadb(DBMS)에 연결하여 Connection 객체 얻기.
            conn = DriverManager.getConnection(url,user,pw);
                               
        
    }
   
    	
	/* 회원 등록하기*/
	public boolean insertMember(MemberDTO dto){ //불리언(삽입,삭제,수정) - 함수의 반환으로 쿼리의 실행 여부 확인 / MemberDto dto 멤버 인스턴스를 받아 값을 입력		
		
		boolean result = false;				
		try {
			getConnection();
			
			String sql = "INSERT INTO TB_member02(m_name, m_ssn, m_phoneNum) VALUES (?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);//sql 문 전송 객체
			
			pstmt.setString(1,dto.getName()); //매개변수 -> 지정된 문자열
			pstmt.setString(2,dto.getSsn());
			pstmt.setString(3,dto.getPhoneNum());
			
			int r = pstmt.executeUpdate(); //r = 실행된 레코드 갯수
			
			if(r>0) result = true;
			
		} catch (Exception e) {
			System.out.println("예외발생:insertMember()=> "+e.getMessage()); //예외처리
		}finally{	
			dbClose();
		}
		return result;
	}		
	
	/*회원번호에 해당하는 회원정보 보기*/
	public MemberDTO getMember(String no){
		MemberDTO dto =null;
		try {
			getConnection();
			
			String sql = "SELECT m_no, m_name, m_ssn, m_phoneNum, m_registdate FROM TB_member02 WHERE m_no = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);
			ResultSet r = pstmt.executeQuery();//excuteQuery -> 객체값 반환
			
			if(r.next()) {
				String m_no = r.getString("m_no");//매개변수 지정 ㅇ-> 값=> 자바 문자열 반환
				String m_name = r.getString("m_name");
				String m_ssn = r.getString("m_ssn");
				String m_phoneNum = r.getString("m_phoneNum");
				String m_registdate = r.getDate("m_registdate").toString();
				dto = new MemberDTO(m_no, m_name, m_ssn, m_phoneNum, m_registdate);
			}
		} catch (Exception e) {
			System.out.println("예외발생:deleteMember()=> "+e.getMessage());
		}finally{			
			dbClose();
		}		
		return dto;
	}

	/* 저장된 회원 목록 보기*/
	public List<MemberDTO> getMemberList(){
		List<MemberDTO> list = new ArrayList<MemberDTO>();//가변적 선형리스트
		
		try {
			getConnection();
			
			String sql = "SELECT m_no, m_name, m_ssn, m_phoneNum, m_registdate FROM TB_member02 ORDER BY m_no";

			PreparedStatement pstmt = conn.prepareStatement(sql);			
			ResultSet r = pstmt.executeQuery();//excuteQuery -> 객체값 반환
			
			while(r.next()) { //null 값이 나올 때까지 while 문 반복 = 회원목록 출력
				String m_no = r.getString("m_no");
				String m_name = r.getString("m_name");
				String m_ssn = r.getString("m_ssn");
				String m_phoneNum = r.getString("m_phoneNum");
				String m_registdate = r.getDate("m_registdate").toString();
				list.add(new MemberDTO(m_no, m_name, m_ssn, m_phoneNum, m_registdate));
			}
			
		} catch (Exception e) {
			System.out.println("예외발생:getMemberList()=> "+e.getMessage());
		}finally{			
			dbClose();
		}	
		return list;
	}
	
	/*회원 수정*/
	public boolean updateMember(MemberDTO dto){
		
		boolean result = false;				
		try {
			getConnection();
			
			String sql = "UPDATE TB_member02 SET m_name=?, m_ssn=?, m_phoneNum=? WHERE m_no=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,dto.getName());
			pstmt.setString(2,dto.getSsn());
			pstmt.setString(3,dto.getPhoneNum());
			pstmt.setString(4,dto.getNo());			
			
			int r = pstmt.executeUpdate(); 
			
			if(r>0) result = true;
			
		} catch (Exception e) {
			System.out.println("예외발생:updateMember()=> "+e.getMessage());
		}finally{			
			dbClose();
		}		
		return result;
	}
	
	
	/*회원 삭제*/
	public boolean deleteMember(String id){			
		boolean result = false;				
		try {
			getConnection();
			
			String sql = "DELETE FROM TB_member02 WHERE m_no = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			int r = pstmt.executeUpdate();
			
			if(r>0) result = true;
			
		} catch (Exception e) {
			System.out.println("예외발생:deleteMember()=> "+e.getMessage());
		}finally{			
			dbClose();
		}		
		return result;
	}//deleteMember()--------------
	
	
	/**DB연결 해제(닫기)*/
    public void dbClose(){       
     
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println("예외:ResultSet객체 close():" + e.getMessage());
			}
		}
         
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				System.out.println("예외:PreparedStatement객체 close():" + e.getMessage());
			}
		} 
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("예외:Connection객체 close():" + e.getMessage());
			}
		}    
		conn = null;        
    }//dbClose()---------
}