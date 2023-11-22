package Tcptest;

import java.util.Formatter;

public class MemberDTO {//���� ����

	private String no;
	private String name;
	private String ssn;
	private String phoneNum;
	private String registdate;
	//
	
	// ������
	public MemberDTO(String name, String ssn, String phoneNum) {
		this.name = name;
		this.ssn = ssn;
		this.phoneNum = phoneNum;
	}//Ŭ���̾�Ʈ to DB �� �ʱ�ȭ ,DAO.java

	// ������
	public MemberDTO(String no, String name, String ssn, String phoneNum, String registdate) {
		super();
		this.no = no;
		this.name = name;
		this.ssn = ssn;
		this.phoneNum = phoneNum;
		this.registdate = registdate;
	}//��ü member ���� input, query ��ȸ �� �ڹٿ��� ��ȸ �����ϵ��� �ʱ�ȭ

	// private ���� ����. ������ no, name, ssn, phoneNum, registdate�� ���� ����,����
	//���� = �Լ����� �ν��Ͻ� �ʵ忡 ���� �� �ʱ�ȭ
	//���� = �ν��Ͻ� �ʵ忡 �����ؼ� ����ϴ� �Լ� �ʱ�ȭ ������ return

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
	public String toString() { // ������ ���� ������ ��� flag �ɼ� - : ���� ����
		Formatter fm = new Formatter();
		String meminfo = fm.format("%3s\t  %-7s\t%-14s\t%-14s\t%-14s", no, name, ssn, phoneNum, registdate).toString();
		return meminfo;
	}
}