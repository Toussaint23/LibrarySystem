package model;

public class MemberAccountFactory {

	public static MemberAccountImpl createMemberAndAccount(String id, String email, String firstName, int isSuperMember,
			String lastName, String user, String pwd) {
		Member member = new Member(id, email, firstName, isSuperMember, lastName);
		
		/*if (isSuperMember == 0)
			member = new SimpleMember(id, email, firstName, isSuperMember, lastName);
		else
			member = new SuperMember(id, email, firstName, isSuperMember, lastName);*/

		Account account = new Account(user, pwd);

		member.setAccount(account);
		account.setMember(member);

		return new MemberAccountImpl(member, account);
	}

	public static MemberAccountImpl signUp(String user, String pwd) {

		Member member = new Member();
		Account account = new Account(user, pwd);

		return new MemberAccountImpl(member, account);
	}

}
