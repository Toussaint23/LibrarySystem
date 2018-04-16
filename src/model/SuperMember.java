package model;

public class SuperMember extends Member {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SuperMember() {
		// TODO Auto-generated constructor stub
	}

	public SuperMember(String id, String email, String firstName, int isSuperMember, String lastName) {
		super(id, email, firstName, isSuperMember, lastName);
		super.setIsSuperMember(1);
		// TODO Auto-generated constructor stub
	}

}
