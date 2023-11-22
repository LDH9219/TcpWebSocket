package Tcptest;

import java.io.*;
import java.net.*;
import java.util.*;

public class MemberClient extends Thread{
	public void run() {
		try {
			Socket socket = new Socket("127.0.0.1",9990);
			System.out.println("연결 요청중");
			BufferedReader br = null;
			BufferedWriter bw = null;
			
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			System.out.print(br.readLine());
			while (true) {
					System.out.println();
					System.out.println("============== 인적사항 관리 프로그램 ==============");
					System.out.println("1. 회원목록");			
					System.out.println("2. 회원등록" );
					System.out.println("3. 회원삭제");
					System.out.println("4. 회원 정보 수정");
					System.out.println("5. 종료");
					System.out.println("===============================================");
					System.out.print("메뉴를 입력하세요 : ");
					
					Scanner scan = new Scanner(System.in);
					String msg = scan.nextLine();
					
				switch(msg) {
					case "1"://멤버출력
						int i=0;
						String str,str1;
						bw.write(msg + "\n");
						bw.flush();
						str = br.readLine();
						StringTokenizer stz = new StringTokenizer(str,"."); //readLine -> 한 줄을 읽음, server -> client 한줄 x, . 을 통하여 행 구분
						System.out.println("                           Member List");
						System.out.println("==================================================================");
						System.out.println("등록번호\t  이름 \t\t생년월일\t\t연락처\t\t등록일");
						System.out.println("==================================================================");
						while(stz.hasMoreElements()) {
							str1 = stz.nextToken();
							System.out.println(str1);
							i++;
						}
						System.out.println("============================================================총 "+ i + "명=\n");
						
						break;
						
					case "2"://삽입
						bw.write(msg + "\n");
						bw.flush();
						System.out.println("회원정보를 입력해주세요.");
						System.out.print("▶이름 : ");
						msg = scan.nextLine();
						bw.write(msg + "\n");
						bw.flush();
						
						System.out.print("▶생년월일 : ");
						msg = scan.nextLine();
						bw.write(msg + "\n");
						bw.flush();
						
						System.out.print("▶연락처 : ");
						msg = scan.nextLine();
						bw.write(msg + "\n");
						bw.flush();
						
						break;
						
					case "3"://삭제
						bw.write(msg + "\n");
						bw.flush();
						
						System.out.println("삭제할 회원의 회원등록번호를 입력해주세요");
						msg = scan.nextLine();
						bw.write(msg + "\n");
						bw.flush();
						
						System.out.println("위 회원의 정보를 정말로 삭제하시겠습니까?(Y/N)");
						msg = scan.nextLine();
						bw.write(msg + "\n");
						bw.flush();
						
						break;
						
					case "4"://수정
						bw.write(msg + "\n");
						bw.flush();
						
						System.out.println("수정할 회원의 회원등록번호를 입력해주세요"); 
						System.out.print("▶");
						msg = scan.nextLine();
						bw.write(msg + "\n");
						bw.flush();
						
						System.out.println("수정작업을 계속하시겠습니까?(Y/N)");
						msg = scan.nextLine();
						bw.write(msg + "\n");
						bw.flush();
						if(msg.equalsIgnoreCase("y")){
						
						System.out.println("입력을 하시지않으면 기존의 정보가 그대로 유지됩니다.");
						System.out.print("▶수정할 이름 : ");
						msg = scan.nextLine();
						bw.write(msg + "\n");
						bw.flush();
						
						System.out.print("▶수정할 생년월일 : ");
						msg = scan.nextLine();
						bw.write(msg + "\n");
						bw.flush();
						
						System.out.print("▶수정할 전화번호 : ");
						msg = scan.nextLine();
						bw.write(msg + "\n");
						bw.flush();
						}
						
						break;
						
					case "5":
						bw.write(msg + "\n");
						bw.flush();
						System.exit(0);
						break;
						
					default:
						System.out.println("입력된 값이 잘못되었습니다. [1-5] 메뉴늘 선택해주세요!");
						break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}