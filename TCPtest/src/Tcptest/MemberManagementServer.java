package Tcptest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MemberManagementServer{
	
	public static void main(String args[]) {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd | HH:mm:ss | ");
		Date time = new Date();
		String n_time = ft.format(time);
		
		ServerSocket serverSocket = null;
		Socket socket = null;
		BufferedReader br = null;
		BufferedWriter bw = null;	
		
		try {
			serverSocket = new ServerSocket(9990);
			socket = serverSocket.accept(); //접속 승인
			MemberProc mm = new MemberProc(); //MemberProc객체 생성
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			System.out.println();
			System.out.println("============== 인적사항 관리 프로그램 ==============");
			System.out.println("\t\t서버-클라이언트 연결됨\n\t\t 서버 가동 중");
			System.out.println("==============================================");
			bw.write("연결됨" + "\n");
			bw.flush();
			
			while(true) {
				String msg = br.readLine(); 
				String no, name, ssn, phoneNum, input;
				switch(msg) {
				
					case "1": // 멤버 출력
						System.out.print(n_time);
						System.out.println("회원출력 요청");
						bw.write(mm.showMemberList() + "\n");//회원목록	
						bw.flush();
						break;
						
					case "2" ://삽입
						System.out.print(n_time);
						System.out.println("신규 회원 등록 요청");
						name = br.readLine();
						ssn = br.readLine();
						phoneNum = br.readLine();
						System.out.println(mm.insertMember(name, ssn, phoneNum)); //회원 등록
						break;
						
					case "3"://삭제
						System.out.print(n_time);
						System.out.println("회원 삭제 요청");
						no = br.readLine();
						input = br.readLine();
						mm.deleteMember(no,input); //회원 삭제			
						break;
						
					case "4"://수정
						System.out.print(n_time);
						System.out.println("회원 정보 수정 요청");
						no = br.readLine();
						input = br.readLine();
						if(input.equalsIgnoreCase("y")) {
						name = br.readLine();
						ssn = br.readLine();
						phoneNum = br.readLine();
						System.out.println(mm.updateMember(no,input,name,ssn,phoneNum));
						}//회원 수정
						break;
						
					case "5":
						System.out.println("프로그램을 종료합니다.");
						System.exit(0);
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}