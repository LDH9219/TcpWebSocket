package Tcptest;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class MemberProc {
	String str = "";
	MemberDAO dao; //dao = MemberDAO.java
	
	public MemberProc() {
		dao = new MemberDAO();

	}

	/* ȸ�� ���ó�� */
	public String insertMember(String name, String ssn, String phoneNum) {
		MemberDTO dto = new MemberDTO(name, ssn, phoneNum);
		boolean r = dao.insertMember(dto); // �Է¹��� ������ �߰�

		if (r) {
			str = "\n ȸ������� ���������� �Ϸ�Ǿ����ϴ�.";
		} else {
			str = "\n ȸ������� ���������� �̷����� �ʾҽ��ϴ�.";
		}
		return str;
	} //

	
	/* ����� ȸ�� ��� ���� */
	public String showMemberList() {
		List<MemberDTO> list = dao.getMemberList();

		for (MemberDTO dto : list) { //list ��ü->dto til list null
			str += dto + "."; //���մ��Կ�����
		}

		return str;
	}

	
	/* ȸ�� ���� */
	public String updateMember(String no, String input, String name, String ssn, String phoneNum) {
		MemberDTO dto = dao.getMember(no);
		if (dto != null) {
			if (input.equalsIgnoreCase("y")) {
				if (name.trim().equals(""))
					name = dto.getName();
				if (ssn.trim().equals(""))
					ssn = dto.getSsn();
				if (phoneNum.trim().equals(""))
					phoneNum = dto.getPhoneNum();
				dto = new MemberDTO(no, name, ssn, phoneNum, dto.getRegistdate());
				boolean r = dao.updateMember(dto);
				if (r) {

					str = "\n ȸ���� ������ ������ ���� �����Ǿ����ϴ�.";
				} else {
					str = "\n ȸ���� ������ ���������� ���� ���� �ʾҽ��ϴ�.";
				}
			} else {
				str = "\n ���� �۾��� ����Ͽ����ϴ�.";
			}
		} else {
			str = "\n �Է��Ͻ� ȸ����Ϲ�ȣ�� �ش��ϴ� ȸ���� �������� �ʽ��ϴ�.";
		}
		return str;
	}

	/* ȸ�� ���� */
	public String deleteMember(String no, String input) {
		MemberDTO dto = dao.getMember(no);
		if (dto != null) {

			if (input.equalsIgnoreCase("y")) { // ��ҹ��� ���� ���� y �Է¹��� �� ����
				boolean r = dao.deleteMember(no);

				if (r) {
					str = no + "\n ȸ���� ������ ���������� �����Ǿ����ϴ�.";
				} else {
					str = "\n ȸ���� ������ ���������� ���� ���� �ʾҽ��ϴ�.";
				}
			} else {
				str = "\n ���� �۾��� ����Ͽ����ϴ�.";
			}
		} else {
			str = "\n �Է��Ͻ� ȸ����Ϲ�ȣ�� �ش��ϴ� ȸ���� �������� �ʽ��ϴ�.";
		}
		return str;
	}

	/* �����Է½� ���Է�, �׽�Ʈ �޼ҵ�... */
	public String reInput(Scanner scn) {
		while (true) {
			str = scn.nextLine();
			if (!(str == null || str.trim().equals(""))) {
				break;
			} else {
				System.out.println("\n ������ �Է��ϽǼ������ϴ�. �ùٸ����� �Է����ּ���!");
				System.out.print("��");
			}
		}
		return str;
	}
}