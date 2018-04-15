package controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.*;

public class Admin {
	public MemberAccountImpl RegisterMember(String id, String email, String firstName, int isSuperMember, String lastName,
			String user, String pwd) {

		MemberAccountImpl mai = MemberAccountFactory.createMemberAndAccount(id, email, firstName, isSuperMember,
				lastName, user, pwd);
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibrarySystem");
		EntityManager em = emf.createEntityManager();

		// Persist entity
		em.getTransaction().begin();
		em.persist(mai.getMember());
		em.persist(mai.getAccount());
		em.getTransaction().commit();
		em.close();
		
		return mai;
	}

	public Member signIn(String user, String pwd) {

		MemberAccountImpl mai = MemberAccountFactory.signUp(user, pwd);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibrarySystem");
		EntityManager em = emf.createEntityManager();
		Account result = null;
		
		// Retrieve entity
		Query query = em.createNamedQuery("Login.findAccount");
		query.setParameter(1, user).setParameter(2, pwd);
		
		try {
			result = (Account) query.getSingleResult();
			mai.getAccount().setMember(result.getMember());
			mai.getMember().setAccount(mai.getAccount());
			
		} catch (javax.persistence.NoResultException e) {
			result = null;
		}
		finally {
			em.close();
		}
		return mai.getAccount().getMember();
	}

	
	
	public void createBook(String isn, String author, String bookTitle,
			String bookType, String edition, int isAvailable) {
		BookCopyImplementation bookFactory = BookFactory.createBookAndBookCopy(isn, author, bookTitle, bookType, edition, isAvailable);
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibrarySystem");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(bookFactory.getBook());
		em.persist(bookFactory.getBookCopy());
		em.getTransaction().commit();
		em.close();
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
	 * 
	 * 
	 * 	public Member signIn(String user, String pwd) {

		MemberAccountImpl mai = MemberAccountFactory.signUp(user, pwd);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibrarySystem");
		EntityManager em = emf.createEntityManager();
		Account result = null;
		
		// Retrieve entity
		Query query = em.createNamedQuery("Login.findAccount");
		query.setParameter(1, user).setParameter(2, pwd);
		
		try {
			result = (Account) query.getSingleResult();
			mai.getAccount().setMember(result.getMember());
			mai.getMember().setAccount(mai.getAccount());
			
		} catch (javax.persistence.NoResultException e) {
			result = null;
		}
		finally {
			System.out.println(result.getMember().getFirstName());
			System.out.println(mai.getMember().getFirstName());
			System.out.println(mai.getAccount().getId().getPassword());
			System.out.println(mai.getAccount().getMember().getAccount().getId().getPassword());
			em.close();
		}
		return mai.getAccount().getMember();
	}
	 */
}
