package Tcptest;

import java.util.Formatter;

public class MemberDTO {//정보 관리

	private String no;
	private String name;
	private String ssn;
	private String phoneNum;
	private String registdate;
	//
	
	// 생성자
	public MemberDTO(String name, String ssn, String phoneNum) {
		this.name = name;
		this.ssn = ssn;
		this.phoneNum = phoneNum;
	}//클라이언트 to DB 용 초기화 ,DAO.java

	// 생성자
	public MemberDTO(String no, String name, String ssn, String phoneNum, String registdate) {
		super();
		this.no = no;
		this.name = name;
		this.ssn = ssn;
		this.phoneNum = phoneNum;
		this.registdate = registdate;
	}//객체 member 정보 input, query 조회 시 자바에서 조회 가능하도록 초기화

	// private 으로 선언. 지정된 no, name, ssn, phoneNum, registdate에 접근 게터,세터
	//세터 = 함수에서 인스턴스 필드에 접근 및 초기화
	//게터 = 인스턴스 필드에 접근해서 사용하는 함수 초기화 변수를 return

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getRegistdate() {
		return registdate;
	}

	public void setRegistdate(String registdate) {
		this.registdate = registdate;
	}

	@Override
	public String toString() { // 포맷을 통한 데이터 출력 flag 옵션 - : 왼쪽 정렬
		Formatter fm = new Formatter();
		String meminfo = fm.format("%3s\t  %-7s\t%-14s\t%-14s\t%-14s", no, name, ssn, phoneNum, registdate).toString();
		return meminfo;
	}
}