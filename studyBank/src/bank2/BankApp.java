package bank2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BankApp {
	
	private static void menu() {
		System.out.println("====================================================================================================");
		System.out.println("[1.계좌생성] [2.로그인] [3.종료]");
		System.out.println("====================================================================================================");
	}
	
	private static void menuAdmin() {
		System.out.println("====================================================================================================");
		System.out.println("[1.입금] [2.출금] [3.송금] [4.계좌 조회] [5.계좌 삭제] [6.로그아웃] [7.종료]");
		System.out.println("====================================================================================================");
	}
	
	private static void menuExit() {
		System.out.println("====================================================================================================");
		System.out.println("시스템이 종료되었습니다.");
		System.out.println("====================================================================================================");
	}
	
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Bank bs = new BankService(); 
		
		int select;
		
		// 무한 루프
		while (true) {
			// 작업 선택 영역 (비로그인)
			if (bs.loginCheck() == null) {
				try {
					do {
						menu();
						select = Integer.parseInt(br.readLine());
					} while(select < 1 || select > 3); // 1 ~ 6 사이값이 아닐 경우 재입력 처리
					
					// 종료 처리
					if (select == 3) {
						menuExit();
						break; // 무한 루프 벗어나기 (while문을 벗어남)
					}
					
					switch (select) {
					case 1 : bs.newAccount(); break;
					case 2 : bs.login(); break;
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			// 작업 선택 영역 (로그인)
			} else {
				System.out.println();
				System.out.println("====================================================================================================");
				System.out.println(bs.loginCheck().getName() + "님 환영합니다.");
				
				// 작업 선택 영역 (로그인)
				try {
					do {
						menuAdmin();
						select = Integer.parseInt(br.readLine());
					} while(select < 1 || select > 7); // 1 ~ 6 사이값이 아닐 경우 재입력 처리
					
					// 종료 처리
					if (select == 7) {
						menuExit();
						break; // 무한 루프 벗어나기 (while문을 벗어남)
					}
					
					switch (select) {
					case 1 : bs.deposit(); break;
					case 2 : bs.withdraw(); break;
					case 3 : bs.remit(); break;
					case 4 : bs.selectAccount(); break;
					case 5 : bs.delete(); break;
					case 6 : bs.logout(); break;
					}	
				} catch (IOException e) {
					e.printStackTrace();
				}
			} // else
		} // while
	} // main()

} // class BankApp
