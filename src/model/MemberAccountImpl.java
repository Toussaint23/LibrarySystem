package model;

public class MemberAccountImpl implements MemberAccountInterface{
	
	Member member;
	Account account;

	public MemberAccountImpl(Member member, Account account) {
		this.member = member;
		this.account = account;
	}

	@Override
	public Member getMember() {
		// TODO Auto-generated method stub
		return member;
	}

	@Override
	public Account getAccount() {
		// TODO Auto-generated method stub
		return account;
	}

}
