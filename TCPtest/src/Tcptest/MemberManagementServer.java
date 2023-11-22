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
			socket = serverSocket.accept(); //���� ����
			MemberProc mm = new MemberProc(); //MemberProc��ü ����
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			System.out.println();
			System.out.println("============== �������� ���� ���α׷� ==============");
			System.out.println("\t\t����-Ŭ���̾�Ʈ �����\n\t\t ���� ���� ��");
			System.out.println("==============================================");
			bw.write("�����" + "\n");
			bw.flush();
			
			while(true) {
				String msg = br.readLine(); 
				String no, name, ssn, phoneNum, input;
				switch(msg) {
				
					case "1": // ��� ���
						System.out.print(n_time);
						System.out.println("ȸ����� ��û");
						bw.write(mm.showMemberList() + "\n");//ȸ�����	
						bw.flush();
						break;
						
					case "2" ://����
						System.out.print(n_time);
						System.out.println("�ű� ȸ�� ��� ��û");
						name = br.readLine();
						ssn = br.readLine();
						phoneNum = br.readLine();
						System.out.println(mm.insertMember(name, ssn, phoneNum)); //ȸ�� ���
						break;
						
					case "3"://����
						System.out.print(n_time);
						System.out.println("ȸ�� ���� ��û");
						no = br.readLine();
						input = br.readLine();
						mm.deleteMember(no,input); //ȸ�� ����			
						break;
						
					case "4"://����
						System.out.print(n_time);
						System.out.println("ȸ�� ���� ���� ��û");
						no = br.readLine();
						input = br.readLine();
						if(input.equalsIgnoreCase("y")) {
						name = br.readLine();
						ssn = br.readLine();
						phoneNum = br.readLine();
						System.out.println(mm.updateMember(no,input,name,ssn,phoneNum));
						}//ȸ�� ����
						break;
						
					case "5":
						System.out.println("���α׷��� �����մϴ�.");
						System.exit(0);
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}