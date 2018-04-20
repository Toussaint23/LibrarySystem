package model;

public class MemberAccountImpl {

	Member member;
	Account account;

	public MemberAccountImpl(Member member, Account account) {
		this.member = member;
		this.account = account;
	}

	
	public Member getMember() {
		// TODO Auto-generated method stub
		return member;
	}

	
	public Account getAccount() {
		// TODO Auto-generated method stub
		return account;
	}

}
