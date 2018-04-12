package controller;

import javax.persistence.Query;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.BookCopy;
import model.BookCopyImplementation;
import model.BookFactory;
import model.MemberAccountFactory;
import model.MemberAccountImpl;

public class Admin {

	public void RegisterMember(String id, String email, String firstName, int isSuperMember, String lastName,
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
	
	public void fetchAvailableBooks() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibrarySystem");
		EntityManager em = emf.createEntityManager();
		@SuppressWarnings("unchecked")
		List<BookCopy> availableBooks = em.createNamedQuery("Member.fetchAvailableBooks").getResultList();
		
		for (BookCopy bc : availableBooks) {
			System.out.println(bc.getEdition());
		}
	}

	public boolean signIn(String user, String pwd) {

		MemberAccountImpl mai = MemberAccountFactory.signUp(user, pwd);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibrarySystem");
		EntityManager em = emf.createEntityManager();

		// Retrieve entity
		Query query = em.createNamedQuery("Login.findAccount");
		query.setParameter(1, user).setParameter(2, pwd);
		Object acct = query.getSingleResult();
		System.out.println(acct);
		// System.out.println(mai.getAccount().getId());

		return false;
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
