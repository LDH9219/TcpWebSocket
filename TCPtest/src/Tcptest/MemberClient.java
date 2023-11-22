package Tcptest;

import java.io.*;
import java.net.*;
import java.util.*;

public class MemberClient extends Thread{
	public void run() {
		try {
			Socket socket = new Socket("127.0.0.1",9990);
			System.out.println("���� ��û��");
			BufferedReader br = null;
			BufferedWriter bw = null;
			
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			System.out.print(br.readLine());
			while (true) {
					System.out.println();
					System.out.println("============== �������� ���� ���α׷� ==============");
					System.out.println("1. ȸ�����");			
					System.out.println("2. ȸ�����" );
					System.out.println("3. ȸ������");
					System.out.println("4. ȸ�� ���� ����");
					System.out.println("5. ����");
					System.out.println("===============================================");
					System.out.print("�޴��� �Է��ϼ��� : ");
					
					Scanner scan = new Scanner(System.in);
					String msg = scan.nextLine();
					
				switch(msg) {
					case "1"://������
						int i=0;
						String str,str1;
						bw.write(msg + "\n");
						bw.flush();
						str = br.readLine();
						StringTokenizer stz = new StringTokenizer(str,"."); //readLine -> �� ���� ����, server -> client ���� x, . �� ���Ͽ� �� ����
						System.out.println("                           Member List");
						System.out.println("==================================================================");
						System.out.println("��Ϲ�ȣ\t  �̸� \t\t�������\t\t����ó\t\t�����");
						System.out.println("==================================================================");
						while(stz.hasMoreElements()) {
							str1 = stz.nextToken();
							System.out.println(str1);
							i++;
						}
						System.out.println("============================================================�� "+ i + "��=\n");
						
						break;
						
					case "2"://����
						bw.write(msg + "\n");
						bw.flush();
						System.out.println("ȸ�������� �Է����ּ���.");
						System.out.print("���̸� : ");
						msg = scan.nextLine();
						bw.write(msg + "\n");
						bw.flush();
						
						System.out.print("��������� : ");
						msg = scan.nextLine();
						bw.write(msg + "\n");
						bw.flush();
						
						System.out.print("������ó : ");
						msg = scan.nextLine();
						bw.write(msg + "\n");
						bw.flush();
						
						break;
						
					case "3"://����
						bw.write(msg + "\n");
						bw.flush();
						
						System.out.println("������ ȸ���� ȸ����Ϲ�ȣ�� �Է����ּ���");
						msg = scan.nextLine();
						bw.write(msg + "\n");
						bw.flush();
						
						System.out.println("�� ȸ���� ������ ������ �����Ͻðڽ��ϱ�?(Y/N)");
						msg = scan.nextLine();
						bw.write(msg + "\n");
						bw.flush();
						
						break;
						
					case "4"://����
						bw.write(msg + "\n");
						bw.flush();
						
						System.out.println("������ ȸ���� ȸ����Ϲ�ȣ�� �Է����ּ���"); 
						System.out.print("��");
						msg = scan.nextLine();
						bw.write(msg + "\n");
						bw.flush();
						
						System.out.println("�����۾��� ����Ͻðڽ��ϱ�?(Y/N)");
						msg = scan.nextLine();
						bw.write(msg + "\n");
						bw.flush();
						if(msg.equalsIgnoreCase("y")){
						
						System.out.println("�Է��� �Ͻ��������� ������ ������ �״�� �����˴ϴ�.");
						System.out.print("�������� �̸� : ");
						msg = scan.nextLine();
						bw.write(msg + "\n");
						bw.flush();
						
						System.out.print("�������� ������� : ");
						msg = scan.nextLine();
						bw.write(msg + "\n");
						bw.flush();
						
						System.out.print("�������� ��ȭ��ȣ : ");
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
						System.out.println("�Էµ� ���� �߸��Ǿ����ϴ�. [1-5] �޴��� �������ּ���!");
						break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}