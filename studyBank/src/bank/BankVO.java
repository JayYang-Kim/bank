package bank;

public class BankVO {
	private String account; // 계좌번호
	private String passWord; // 비밀번호
	private String name; // 이름
	private String birthDay; // 생년월일
	private int balance; // 잔액
	private String createDay; // 생성일
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public String getCreateDay() {
		return createDay;
	}
	public void setCreateDay(String createDay) {
		this.createDay = createDay;
	}
}
