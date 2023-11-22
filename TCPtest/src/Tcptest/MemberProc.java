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

	/* 회원 등록처리 */
	public String insertMember(String name, String ssn, String phoneNum) {
		MemberDTO dto = new MemberDTO(name, ssn, phoneNum);
		boolean r = dao.insertMember(dto); // 입력받은 데이터 추가

		if (r) {
			str = "\n 회원등록이 정상적으로 완료되었습니다.";
		} else {
			str = "\n 회원등록이 정상적으로 이루지지 않았습니다.";
		}
		return str;
	} //

	
	/* 저장된 회원 목록 보기 */
	public String showMemberList() {
		List<MemberDTO> list = dao.getMemberList();

		for (MemberDTO dto : list) { //list 객체->dto til list null
			str += dto + "."; //복합대입연산자
		}

		return str;
	}

	
	/* 회원 수정 */
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

					str = "\n 회원의 정보가 다음과 같이 수정되었습니다.";
				} else {
					str = "\n 회원의 정보가 정상적으로 수정 되지 않았습니다.";
				}
			} else {
				str = "\n 수정 작업을 취소하였습니다.";
			}
		} else {
			str = "\n 입력하신 회원등록번호에 해당하는 회원이 존재하지 않습니다.";
		}
		return str;
	}

	/* 회원 삭제 */
	public String deleteMember(String no, String input) {
		MemberDTO dto = dao.getMember(no);
		if (dto != null) {

			if (input.equalsIgnoreCase("y")) { // 대소문자 구분 없이 y 입력받을 시 진입
				boolean r = dao.deleteMember(no);

				if (r) {
					str = no + "\n 회원의 정보가 정상적으로 삭제되었습니다.";
				} else {
					str = "\n 회원의 정보가 정상적으로 삭제 되지 않았습니다.";
				}
			} else {
				str = "\n 삭제 작업을 취소하였습니다.";
			}
		} else {
			str = "\n 입력하신 회원등록번호에 해당하는 회원이 존재하지 않습니다.";
		}
		return str;
	}

	/* 공백입력시 재입력, 테스트 메소드... */
	public String reInput(Scanner scn) {
		while (true) {
			str = scn.nextLine();
			if (!(str == null || str.trim().equals(""))) {
				break;
			} else {
				System.out.println("\n 공백은 입력하실수없습니다. 올바른값을 입력해주세요!");
				System.out.print("▶");
			}
		}
		return str;
	}
}