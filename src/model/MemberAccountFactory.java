package model;

public class MemberAccountFactory {

	public static MemberAccountImpl createMemberAndAccount(String id, String email, String firstName,
			byte isSuperMember, String lastName, String user, String pwd) {
		Member member = new Member(id, email, firstName, isSuperMember, lastName);
		Account account = new Account(user, pwd);

		member.setAccount(account);
		account.setMember(member);

		return new MemberAccountImpl(member, account);
	}

}
