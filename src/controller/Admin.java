package controller;

import model.MemberAccountFactory;
import model.MemberAccountImpl;

public class Admin {
	public MemberAccountImpl RegisterMember(String id, String email, String firstName, int isSuperMember, String lastName,
			String user, String pwd) {

		MemberAccountImpl mai = MemberAccountFactory.createMemberAndAccount(id, email, firstName, isSuperMember,
				lastName, user, pwd);
		
		return mai;
	}

	public MemberAccountImpl signIn(String user, String pwd) {

		MemberAccountImpl mai = MemberAccountFactory.signUp(user, pwd);

		return mai;
	}

	
	
	
	
	
	
	/*
	 * public static void main(String[] args) { // TODO Auto-generated method stub
	 * /* Create EntityManagerFactory EntityManagerFactory emf =
	 * Persistence.createEntityManagerFactory("Essaie");
	 * 
	 * Create and populate Entity Paysan paysan = new Paysan(); paysan.setNom(1);
	 * paysan.setPrenom(2); paysan.setAge(3); paysan.setSexe(4);
	 * 
	 * Create EntityManager EntityManager em = emf.createEntityManager();
	 * 
	 * Persist entity // em.getTransaction().begin(); // em.persist(paysan); //
	 * em.getTransaction().commit();
	 * 
	 * Retrieve entity // paysan = em.find(Paysan.class, 2); //
	 * System.out.println(paysan);
	 * 
	 * Retrieve all entities // @SuppressWarnings("unchecked") // List<Paysan>
	 * paysans = em.createNamedQuery("Paysan.findAll").getResultList(); //
	 * System.out.println(paysans);
	 * 
	 * Update entity em.getTransaction().begin(); // paysan = em.find(Paysan.class,
	 * 2); // paysan.setNom(22); List<Paysan> paysans =
	 * em.createNamedQuery("Paysan.findAll").getResultList(); for (Paysan p :
	 * paysans) { p.setNom(22); } em.createNamedQuery("Paysan.updateAll");
	 * System.out.println("Paysan after updation :- " + paysan);
	 * em.getTransaction().commit();
	 * 
	 * 
	 * Remove entity em.getTransaction().begin(); em.remove(paysan);
	 * em.getTransaction().commit();
	 * 
	 * Check whether enittiy is removed or not paysan = em.find(Paysan.class, 1);
	 * System.out.println("Paysan after removal :- " + paysan);
	 * 
	 * 
	 * }
	 */
}
