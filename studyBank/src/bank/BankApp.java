package bank;

import java.util.Scanner;

public class BankApp {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		BankService bs = new BankService();
		
		int select;
		
		// 무한 루프
		while (true) {
			if (bs.loginCheck() == null) {
				// 작업 선택 영역 (비로그인)
				do {
					System.out.println("====================================================================================================");
					System.out.println("[1.계좌생성] [2.로그인] [3.종료]");
					System.out.println("====================================================================================================");
					select = sc.nextInt();
				} while(select < 1 || select > 3); // 1 ~ 6 사이값이 아닐 경우 재입력 처리
				
				// 종료 처리
				if (select == 3) {
					System.out.println("====================================================================================================");
					System.out.println("시스템이 종료되었습니다.");
					System.out.println("====================================================================================================");
					break; // 무한 루프 벗어나기 (while문을 벗어남)
				}
				
				switch (select) {
				case 1 : bs.newAccount(); break;
				case 2 : bs.login();; break;
				}
			} else {
				System.out.println();
				System.out.println("====================================================================================================");
				System.out.println(bs.loginCheck().getName() + "님 환영합니다.");
				System.out.println("====================================================================================================");
				System.out.println();
				
				// 작업 선택 영역 (로그인)
				do {
					System.out.println("====================================================================================================");
					System.out.println("[1.입금] [2.출금] [3.송금] [4.계좌 조회] [5.계좌 삭제] [6.로그아웃] [7.종료]");
					System.out.println("====================================================================================================");
					select = sc.nextInt();
				} while(select < 1 || select > 7); // 1 ~ 6 사이값이 아닐 경우 재입력 처리
				
				// 종료 처리
				if (select == 7) {
					System.out.println("====================================================================================================");
					System.out.println("시스템이 종료되었습니다.");
					System.out.println("====================================================================================================");
					break; // 무한 루프 벗어나기 (while문을 벗어남)
				}
				
				switch (select) {
				case 1 : bs.deposit(); break;
				case 2 : bs.withdraw(); break;
				case 3 : bs.remit(); break;
				case 4 : bs.checkBalance(); break;
				case 5 : bs.delete(); break;
				case 6 : bs.logout(); break;
				}	
			}
			
		}
		
		sc.close();
	}

}
