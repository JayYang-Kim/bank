package bank2;

public interface Bank {
	public void newAccount(); // 계좌생성
	public void deposit(); // 입금
	public void withdraw(); // 출금
	public void remit(); // 송금
	public void selectAccount(); // 계좌 확인
	public void update(); // 정보수정
	public void delete(); // 계좌 삭제
	public void login(); // 로그인
	public void logout(); // 로그아웃
	
	public BankVO loginCheck();
	public String loginKey();
}
