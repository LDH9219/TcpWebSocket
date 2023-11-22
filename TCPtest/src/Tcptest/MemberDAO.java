package Tcptest;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class MemberDAO { //�����ͺ��̽� ���� ��ü ���� ������ ����
	
	private static Connection conn;
	
	private PreparedStatement pstmt; //Statement Ŭ������ �޸� �Ű������� �� �������� ��� ����
	private ResultSet rs; //������ ���� �����
	
    private void getConnection() throws ClassNotFoundException, SQLException{
            //��������
            String url = "jdbc:mariadb://127.0.0.1:3306/TB_Member02";
            String user = "root";
            String pw = "8327";
           
            //JDBC����̹� �ε�
            Class.forName("org.mariadb.jdbc.Driver");
           
            //mariadb(DBMS)�� �����Ͽ� Connection ��ü ���.
            conn = DriverManager.getConnection(url,user,pw);
                               
        
    }
   
    	
	/* ȸ�� ����ϱ�*/
	public boolean insertMember(MemberDTO dto){ //�Ҹ���(����,����,����) - �Լ��� ��ȯ���� ������ ���� ���� Ȯ�� / MemberDto dto ��� �ν��Ͻ��� �޾� ���� �Է�		
		
		boolean result = false;				
		try {
			getConnection();
			
			String sql = "INSERT INTO TB_member02(m_name, m_ssn, m_phoneNum) VALUES (?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);//sql �� ���� ��ü
			
			pstmt.setString(1,dto.getName()); //�Ű����� -> ������ ���ڿ�
			pstmt.setString(2,dto.getSsn());
			pstmt.setString(3,dto.getPhoneNum());
			
			int r = pstmt.executeUpdate(); //r = ����� ���ڵ� ����
			
			if(r>0) result = true;
			
		} catch (Exception e) {
			System.out.println("���ܹ߻�:insertMember()=> "+e.getMessage()); //����ó��
		}finally{	
			dbClose();
		}
		return result;
	}		
	
	/*ȸ����ȣ�� �ش��ϴ� ȸ������ ����*/
	public MemberDTO getMember(String no){
		MemberDTO dto =null;
		try {
			getConnection();
			
			String sql = "SELECT m_no, m_name, m_ssn, m_phoneNum, m_registdate FROM TB_member02 WHERE m_no = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);
			ResultSet r = pstmt.executeQuery();//excuteQuery -> ��ü�� ��ȯ
			
			if(r.next()) {
				String m_no = r.getString("m_no");//�Ű����� ���� ��-> ��=> �ڹ� ���ڿ� ��ȯ
				String m_name = r.getString("m_name");
				String m_ssn = r.getString("m_ssn");
				String m_phoneNum = r.getString("m_phoneNum");
				String m_registdate = r.getDate("m_registdate").toString();
				dto = new MemberDTO(m_no, m_name, m_ssn, m_phoneNum, m_registdate);
			}
		} catch (Exception e) {
			System.out.println("���ܹ߻�:deleteMember()=> "+e.getMessage());
		}finally{			
			dbClose();
		}		
		return dto;
	}

	/* ����� ȸ�� ��� ����*/
	public List<MemberDTO> getMemberList(){
		List<MemberDTO> list = new ArrayList<MemberDTO>();//������ ��������Ʈ
		
		try {
			getConnection();
			
			String sql = "SELECT m_no, m_name, m_ssn, m_phoneNum, m_registdate FROM TB_member02 ORDER BY m_no";

			PreparedStatement pstmt = conn.prepareStatement(sql);			
			ResultSet r = pstmt.executeQuery();//excuteQuery -> ��ü�� ��ȯ
			
			while(r.next()) { //null ���� ���� ������ while �� �ݺ� = ȸ����� ���
				String m_no = r.getString("m_no");
				String m_name = r.getString("m_name");
				String m_ssn = r.getString("m_ssn");
				String m_phoneNum = r.getString("m_phoneNum");
				String m_registdate = r.getDate("m_registdate").toString();
				list.add(new MemberDTO(m_no, m_name, m_ssn, m_phoneNum, m_registdate));
			}
			
		} catch (Exception e) {
			System.out.println("���ܹ߻�:getMemberList()=> "+e.getMessage());
		}finally{			
			dbClose();
		}	
		return list;
	}
	
	/*ȸ�� ����*/
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
			System.out.println("���ܹ߻�:updateMember()=> "+e.getMessage());
		}finally{			
			dbClose();
		}		
		return result;
	}
	
	
	/*ȸ�� ����*/
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
			System.out.println("���ܹ߻�:deleteMember()=> "+e.getMessage());
		}finally{			
			dbClose();
		}		
		return result;
	}//deleteMember()--------------
	
	
	/**DB���� ����(�ݱ�)*/
    public void dbClose(){       
     
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println("����:ResultSet��ü close():" + e.getMessage());
			}
		}
         
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				System.out.println("����:PreparedStatement��ü close():" + e.getMessage());
			}
		} 
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("����:Connection��ü close():" + e.getMessage());
			}
		}    
		conn = null;        
    }//dbClose()---------
}