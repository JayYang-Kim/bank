package bank;

public class Bank {
	private BankVO[] list;
	
	// 생성자 추가
	public Bank() {
		allocation(10); // 초기 배열 값 
	}
	
	protected void allocation(int capacity) {
		// 생성자에서 인자를 호출한 값 만큼 배열 생성
		BankVO[] temp = new BankVO[capacity];
		
		// BankVO[] list null이 아니고, 값이 있다면 배열 복사처리
		if (list != null && list.length > 0) {
			System.arraycopy(list, 0, temp, 0, list.length);
		}
		
		list = temp;
	}
	
	// list에 들어가 있는 값 개수 반환
	protected int getSize() {
		if (list == null) {
			return 0;
		}
		
		return list.length;
	}
	
	// BankVO[]의 주소값 반환
	protected BankVO[] getList() {
		return list;
	}
}
