package bank;

import java.util.Calendar;
import java.util.Scanner;

public class BankService extends Bank {
	private Scanner sc = new Scanner(System.in);
	private BankVO loginCheck;
	private int count;
	
	public BankService() {
		BankVO[] list = getList();
		BankVO vo = new BankVO();
		
		vo.setAccount("00000000000000");
		vo.setPassWord("0000");
		vo.setName("관리자");
		vo.setBalance(100000000);
		
		list[this.count++] = vo;
	}
	
	public void newAccount() { // 계좌 생성
		// 객체 생성
		BankVO vo = new BankVO();
		BankVO[] list = getList();
		
		// 배열의 크기가 꽉찬 경우 사이즈 증가 처리
		if (count >= getSize()) { // 배열의 크기와 같거나 큰경우 
			allocation(getSize() + 10); // 배열 증가
		}
		
		System.out.println("계좌번호를 입력해주세요.");
		vo.setAccount(sc.next());
		
		// 계좌번호 체크
		String account = vo.getAccount().replaceAll("\\-|\\.|/", ""); // 특수문자 제거
		vo.setAccount(account);
		
		// 14자리 체크
		if (vo.getAccount().length() != 14) {
			
			do {
				System.out.println("====================================================================================================");
				System.out.println("[ERROR] 계좌번호 형식에 오류가 있습니다.");
				System.out.println("====================================================================================================");
				
				System.out.println("계좌번호를 입력해주세요.");
				vo.setAccount(sc.next());
				
				String account2 = vo.getAccount().replaceAll("\\-|\\.|/", ""); // 특수문자 제거
				vo.setAccount(account2);
			} while(vo.getAccount().length() != 14);
			
		}
		
		// 중복 체크
		if (this.count >= 1) { // 계좌가 1개 이상일 경우 체크
			for (int i = 0; i < this.count; i++) {
				if (vo.getAccount().equals(list[i].getAccount())) {
					System.out.println("====================================================================================================");
					System.out.println("[계좌번호 중복] " + vo.getAccount() + "는 중복된 계좌번호 입니다.");
					System.out.println("====================================================================================================");
					newAccount();
				}
			}
		}
		
		System.out.println("비밀번호를 입력해주세요.");
		vo.setPassWord(sc.next());
		
		System.out.println("이름을 입력해주세요.");
		vo.setName(sc.next());
		
		System.out.println("생년월일을 입력해주세요.");
		vo.setBirthDay(sc.next());
		
		// 생년월일 체크
		// -, .(정규식 특수문자 : 한문자를 나타냄)을 입력받으면 ""(공백)으로 변환
		// 실제.을 나타내기위해 \\(두번 입력해야함)
		String birth = vo.getBirthDay().replaceAll("\\-|\\.|/", ""); // 특수문자 제거
		vo.setBirthDay(birth);
		
		// 생년월일이 8자리가 아닌 경우 체크
		if (vo.getBirthDay().length() != 8) {
			
			do {
				System.out.println("====================================================================================================");
				System.out.println("[ERROR] 날짜 형식에 오류가 있습니다.");
				System.out.println("====================================================================================================");
				
				// 재입력 처리
				System.out.println("생년월일을 입력해주세요.");
				vo.setBirthDay(sc.next());
				
				// 생년월일 특수문자 제거
				String birth2 = vo.getBirthDay().replaceAll("\\-|\\.|/|", ""); // 특수문자 제거
				vo.setBirthDay(birth2);
			} while(vo.getBirthDay().length() != 8);

		}
		
		try {
			System.out.println("초기 입금 금액을 입력해주세요.");
			vo.setBalance(sc.nextInt());
			
			if (vo.getBalance() < 5000) {
				do {
					System.out.println("5,000원 미만으로는 입금을 할 수 없습니다.");
					
					System.out.println("초기 입금 금액을 입력해주세요.");
					vo.setBalance(sc.nextInt());
				} while (vo.getBalance() < 5000);
				
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 생성일 추가
		Calendar now = Calendar.getInstance(); // 현재 날짜와 시간 정보를 가진 Calendar 객체를 생성 
		vo.setCreateDay(String.format("%1$tF %1$tA", now));
		
		// 생성 완료
		System.out.printf("====================================================================================================\n");
		System.out.printf("계좌번호 : %s\n", vo.getAccount());
		System.out.printf("잔액 : %,d(원)\n", vo.getBalance());
		System.out.printf("이름 : %s\n", vo.getName());
		System.out.printf("생년월일 : %s\n", vo.getBirthDay());
		System.out.printf("생성일 : %s\n", vo.getCreateDay());
		System.out.printf("====================================================================================================\n");
		
		// 배열에 값 추가
		list[this.count++] = vo;
		
		System.out.println("고객님 계좌가 생성되었습니다.");
	}
	
	public void deposit() { // 입금	
		if (loginCheck == null) {
			System.out.println("로그인이 필요한 서비스입니다.");
			return;
		}
		
		// 로그인된 계좌정보를 한 번더 체크
		BankVO vo = readAccount(loginCheck.getAccount());
		
		if (vo.getAccount().equals("00000000000000")) {
			System.out.println("====================================================================================================");
			System.out.println("[경고] 관리자 계좌는 입금이 불가능합니다.");
			System.out.println("====================================================================================================");
		}
		
		/*String account; // 계좌번호 확인용 변수
		
		System.out.println("입금하실 계좌번호를 입력해주세요.");
		account = sc.next();
		
		BankVO vo = readAccount(account);
		
		if (vo == null) {
			System.out.println("등록된 계좌번호가 없습니다.");
			return;
		}*/
		
		try {
			System.out.println("====================================================================================================");
			System.out.println("입금하실 금액을 입력해주세요.");
			System.out.println("====================================================================================================");
			
			vo.setBalance(vo.getBalance() + sc.nextInt());
			
			System.out.println("====================================================================================================");
			System.out.printf("잔액 : %,d(원)\n", vo.getBalance());
			System.out.println("====================================================================================================");
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void withdraw() { // 출금
		if (loginCheck == null) {
			System.out.println("로그인이 필요한 서비스입니다.");
			return;
		}
		
		// 로그인된 계좌정보를 한 번더 체크
		BankVO vo = readAccount(loginCheck.getAccount());
		
		if (vo.getAccount().equals("00000000000000")) {
			System.out.println("====================================================================================================");
			System.out.println("[경고] 관리자 계좌는 출금이 불가능합니다.");
			System.out.println("====================================================================================================");
		}
		/*String account; // 계좌번호 확인용 변수
		int balance = 0; // 출금 금액 변수
		
		System.out.println("출금하실 계좌번호를 입력해주세요.");
		
		account = sc.next();
		
		BankVO vo = readAccount(account);
		
		if (vo == null) {
			System.out.println("====================================================================================================");
			System.out.println("등록된 계좌번호가 없습니다.");
			System.out.println("====================================================================================================");
			return;
		}*/
		
		try {
			System.out.println("====================================================================================================");
			System.out.println("출금하실 금액을 입력해주세요.");
			System.out.println("====================================================================================================");
			
			int balance = sc.nextInt();
			
			if (vo.getBalance() < balance) {
				System.out.println("====================================================================================================");
				System.out.println("[ERROR] 잔액보다 큰 금액은 출금할 수 없습니다.");
				System.out.println("====================================================================================================");
				
				System.out.println("====================================================================================================");
				System.out.println("출금하실 금액을 입력해주세요.");
				System.out.println("====================================================================================================");
				balance = sc.nextInt();
			}
			
			vo.setBalance(vo.getBalance() - balance);
			
			System.out.printf("====================================================================================================\n");
			System.out.printf("잔액 : %,d(원)\n", vo.getBalance());
			System.out.printf("====================================================================================================\n");
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void remit() {
		if (loginCheck == null) {
			System.out.println("로그인이 필요한 서비스입니다.");
			return;
		}
		
		// 로그인된 계좌정보를 한 번더 체크
		BankVO vo = readAccount(loginCheck.getAccount());
		
		if (vo.getAccount().equals("00000000000000")) {
			System.out.println("====================================================================================================");
			System.out.println("관리자는 송금이 불가능합니다.");
			System.out.println("====================================================================================================");
			
			return;
		}
		
		System.out.println("송금할 계좌번호를 입력해주세요.");
		String account = sc.next(); // 송금할 계좌번호 변수
		
		// 송금할 계좌번호가 있는지 체크
		BankVO vo2 = readAccount(account);
		
		if (vo2 == null) {
			System.out.println("송금할 계좌번호의 정보가 없습니다.");
			return;
		}
		
		System.out.printf("====================================================================================================\n");
		System.out.printf("계좌번호 : %s\n", vo2.getAccount());
		System.out.printf("이름 : %s\n", vo2.getName());
		System.out.printf("====================================================================================================\n");
		
		System.out.println("송금할 금액을 입력해주세요.");
		int money = sc.nextInt();
		
		System.out.printf("====================================================================================================\n");
		System.out.printf("%s(님)께 %,d(원)을 송금하시겠습니까? [Y/N]\n", vo2.getName(), money);
		System.out.printf("====================================================================================================\n");
		
		char c = sc.next().charAt(0);
		
		if (c == 'N' || c != 'Y') {
			System.out.println("송금 서비스가 취소되었습니다.");
			return;
		}
		
		vo2.setBalance(vo2.getBalance() + money); // 송금할 통장에 돈 입금 처리
		
		// 송금 금액이 내 통장의 잔고보다 클 경우 송금 서비스 취소처리
		if (money > vo.getBalance()) {
			System.out.println("====================================================================================================");
			System.out.println("[ERROR]잔고보다 많은 금액을 송금할 수 없습니다.");
			System.out.println("====================================================================================================");
			
			return;
		}
		
		vo.setBalance(vo.getBalance() - money); // 송금한 금액만큼 내 통장에서 차감
		
		System.out.println("====================================================================================================");
		System.out.println(vo2.getName() + "님께 " + money + "(원) 송금 처리 완료");
		System.out.println("====================================================================================================");
	}
	
	public void checkBalance() {
		if (loginCheck == null) {
			System.out.println("로그인이 필요한 서비스입니다.");
			return;
		}
		
		// 로그인된 계좌정보를 한 번더 체크
		BankVO vo = readAccount(loginCheck.getAccount());
		
		if (vo.getAccount().equals("00000000000000")) { // 관리자로 접속 시 전체 계좌 정보 확인
			BankVO[] list = getList();
			
			System.out.println("생선된 계좌 수 : " + this.count);
			
			for (int i = 0; i < this.count; i++) {
				System.out.printf("====================================================================================================\n");
				System.out.printf("계좌번호 : %s\n", list[i].getAccount());
				System.out.printf("잔액 : %,d(원)\n", list[i].getBalance());
				System.out.printf("이름 : %s\n", list[i].getName());
				System.out.printf("생년월일 : %s\n", list[i].getBirthDay());
				System.out.printf("생성일 : %s\n", list[i].getCreateDay());
				System.out.printf("====================================================================================================\n");
			}
		} else { // 일반유저로 접속 시 자신의 계좌만 확인
			System.out.printf("====================================================================================================\n");
			System.out.printf("잔액 : %,d(원)\n", vo.getBalance());
			System.out.printf("이름 : %s\n", vo.getName());
			System.out.printf("생년월일 : %s\n", vo.getBirthDay());
			System.out.printf("생성일 : %s\n", vo.getCreateDay());
			System.out.printf("====================================================================================================\n");
		}
	}
	
	public void delete() {
		if (loginCheck == null) {
			System.out.println("로그인이 필요한 서비스입니다.");
			return;
		}
		
		// 로그인 계좌정보가 admin일 경우 삭제 처리 안함
		if (loginCheck.getAccount().equals("00000000000000" )) {
			System.out.println("====================================================================================================");
			System.out.println("[경고] 관리자 계좌는 삭제가 불가능합니다.");
			System.out.println("====================================================================================================");
			
			return;
		}
		
		/*String account;
		
		System.out.println("삭제할 계좌정보를 입력해주세요.");
		account = sc.next();
		
		BankVO vo = readAccount(account);
		
		if (vo == null) {
			System.out.println("====================================================================================================");
			System.out.println("등록된 계좌번호가 없습니다.");
			System.out.println("====================================================================================================");
			return;
		}*/
		
		// 로그인된 계좌정보를 한 번더 체크
		BankVO vo = readAccount(loginCheck.getAccount());
		
		System.out.println("비밀번호를 입력해주세요.");
		String passWord = sc.next();
		
		if (!vo.getPassWord().equals(passWord)) {
			System.out.println("비밀번호가 일치하지 않습니다.");
			return;
		}
		
		// 삭제 전 데이터 확인
		System.out.printf("====================================================================================================\n");
		System.out.printf("계좌번호 : %s\n", vo.getAccount());
		System.out.printf("잔액 : %,d(원)\n", vo.getBalance());
		System.out.printf("이름 : %s\n", vo.getName());
		System.out.printf("생년월일 : %s\n", vo.getBirthDay());
		System.out.printf("생성일 : %s\n", vo.getCreateDay());
		System.out.printf("====================================================================================================\n");
		
		System.out.println("====================================================================================================");
		System.out.println("정말로 삭제하시겠습니까? [Y/N]");
		System.out.println("====================================================================================================");
		char c = sc.next().charAt(0);
		
		if (c == 'N' || c != 'Y') {
			System.out.println("계좌정보 삭제가 취소되었습니다.");
			return;
		}
		
		// 삭제할 index위치 찾기
		int idx = -1;
		
		BankVO[] list = getList();
		for (int i = 0; i < this.count; i++) {
			if (list[i].getAccount().equals(vo.getAccount())) {
				idx = i;
				break;
			}
		}
		
		if (idx == -1) {
			System.out.println("====================================================================================================");
			System.out.println("등록된 계좌번호가 없습니다.");
			System.out.println("====================================================================================================");
			return;
		}
		
		for (int i = idx; i < count - 1; i++) {
			list[i] = list[i + 1];
		}
		
		count--;
		list[count] = null;
		
		loginCheck = null; // 탈퇴되면서 로그아웃 처리
		
		System.out.println("====================================================================================================");
		System.out.println("계좌정보를 삭제했습니다.");
		System.out.println("====================================================================================================");
	}
	
	public void login() {
		String account, passWord;
		
		System.out.println("계좌번호를 입력해주세요.");
		account = sc.next();
		
		// 계좌번호 체크
		account = account.replaceAll("\\-|\\.|/", ""); // 특수문자 제거

		// 14자리 체크
		if (account.length() != 14) {

			do {
				System.out.println("====================================================================================================");
				System.out.println("[ERROR] 계좌번호 형식에 오류가 있습니다.");
				System.out.println("====================================================================================================");

				System.out.println("계좌번호를 입력해주세요.");
				account = sc.next();

				account = account.replaceAll("\\-|\\.|/", ""); // 특수문자 제거
			} while (account.length() != 14);

		}
		
		System.out.println("비밀번호를 입력해주세요.");
		passWord = sc.next();
		
		BankVO vo = readAccount(account);
		
		if (vo != null) {
			if (vo.getPassWord().equals(passWord)) {
				loginCheck = vo;
				return;
			}
		}
		
		System.out.println("계좌번호 또는 비밀번호가 일치하지 않습니다.");
	}
	
	public void logout() {
		loginCheck = null;
		
		System.out.println("====================================================================================================");
		System.out.println("로그아웃 되었습니다.");
		System.out.println("====================================================================================================");
	}
	
	public BankVO loginCheck() {
		return loginCheck;
	}
	
	// 계좌정보를 이용한 list 주소값 확인
	private BankVO readAccount(String account) {
		BankVO vo = null;

		BankVO[] list = getList();

		for (int i = 0; i < this.count; i++) {
			if (list[i].getAccount().equals(account)) {
				vo = list[i];
				break;
			}
		}
		
		return vo;
	}
}
