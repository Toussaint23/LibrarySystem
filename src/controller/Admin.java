package controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Admin {

	public Member signIn(String user, String pwd) {

		MemberAccountImpl mai = MemberAccountFactory.signUp(user, pwd);
		Account result = null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibrarySystem");
		EntityManager em = emf.createEntityManager();

		try {
			// Retrieve entity
			Query query = em.createNamedQuery("Login.findAccount");
			query.setParameter(1, user).setParameter(2, pwd);
			result = (Account) query.getSingleResult();
			mai.getAccount().setMember(result.getMember());
			mai.getMember().setAccount(mai.getAccount());

		} catch (javax.persistence.NoResultException e) {
			result = null;
		} finally {
			em.close();
		}
		return mai.getAccount().getMember();
	}

	public MemberAccountImpl RegisterMember(String email, String firstName, int isSuperMember, String lastName,
			String user, String pwd) {
		String id = firstName.substring(0, 2) + lastName.substring(0, 2) + "-" + Objects.hash(user, pwd);
		MemberAccountImpl mai = MemberAccountFactory.createMemberAndAccount(id, email, firstName, isSuperMember,
				lastName, user, pwd);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibrarySystem");
		EntityManager em = emf.createEntityManager();

		try {
			// Persist entity
			em.getTransaction().begin();
			em.persist(mai.getMember());
			em.persist(mai.getAccount());
			em.getTransaction().commit();
		} catch (Exception e) {
			mai = null;
		} finally {
			em.close();
		}

		return mai;
	}

	public List<BookCopy> fetchAvailableBooksByName(String name) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibrarySystem");
		EntityManager em = emf.createEntityManager();
		@SuppressWarnings("unchecked")
		List<Book> books = em.createNamedQuery("Book.findBookByName").setParameter(1, name).getResultList();
		List<BookCopy> availableBooks = new ArrayList<>();

		try {
			for (Book book : books) {
				@SuppressWarnings("unchecked")
				List<BookCopy> available = em.createNamedQuery("BookCopy.fetchAvailableBooksByIdBook")
						.setParameter(1, book.getIsn()).getResultList();
				availableBooks.addAll(available);
			}
		} catch (Exception e) {
			availableBooks = null;
		} finally {
			em.close();
		}

		return availableBooks;
	}

	public boolean borrowsBookCopy(String identities, String memberId) {
		Boolean result = true;
		String[] id = identities.split(",");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibrarySystem");
		EntityManager em = emf.createEntityManager();

		try {
			List<BookCopy> b = new ArrayList<>();
			for (String identity : id) {
				b.add(em.find(BookCopy.class, identity));
			}

			Member member = em.find(Member.class, memberId);
			Calendar cal = Calendar.getInstance();
			Date borrowDate = cal.getTime();
			cal.add(Calendar.MONTH, -1);
			Date returnDate = cal.getTime();
			List<BookTransaction> transaction = MemberBookTransactionFactory.createListTransaction(borrowDate,
					returnDate, member, b);

			member.setBookTransactions(transaction);

			em.getTransaction().begin();
			for (BookTransaction bt : member.getBookTransactions()) {
				em.persist(bt);
				bt.getBookCopy().setIsAvailable(0);
			}
			em.getTransaction().commit();
		}

		catch (Exception e) {
			result = false;
		} finally {
			em.close();
		}

		return result;
	}

	public boolean returnBookCopy(String identities, String memberId) {
		Boolean result = true;
		String[] id = identities.split(",");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibrarySystem");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			for (String identity : id) {
				BookCopy bc = em.find(BookCopy.class, identity);
				bc.setIsAvailable(1);
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			result = false;
		} finally {
			em.close();
		}

		return result;
	}
	
	public Book removeBook(String id) {
		// Remove entity

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibrarySystem");
		EntityManager em = emf.createEntityManager();
		Book b = em.find(Book.class, id);

		try {
		em.getTransaction().begin();
		em.remove(b);
		em.getTransaction().commit();
		}catch(Exception e) {
			b = null;
		}
		

		return b;
	}
	
	
	
	
	
	public BookCopyImplementation createBook(String isn, String author, String bookTitle, String bookType, String edition,
			int isAvailable, int quantity) {
		
		BookCopyImplementation bookFactory = BookFactory.createBookAndBookCopy(isn, author, bookTitle, bookType,
				edition, isAvailable, quantity);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibrarySystem");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(bookFactory.getBook());
			em.persist(bookFactory.getBookCopy());
			//em.persist(bookFactory.getBookCopy().getBook().getBookCopies());
			em.getTransaction().commit();
			

			/*EntityManager em1 = emf.createEntityManager();
			for(BookCopy bc : bookFactory.getBook().getBookCopies()) {
				em1.getTransaction().begin();
				em1.persist(bookFactory.getBook());
				em1.persist(bc);
				em1.persist(bc);
				em1.getTransaction().commit();
				em1.close();
			}*/
		} catch (Exception e) {
			bookFactory = null;
		} finally {
			em.close();
		}
		return bookFactory;
	}

	public void fetchAvailableBooks() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibrarySystem");
		EntityManager em = emf.createEntityManager();
		@SuppressWarnings("unchecked")
		List<BookCopy> availableBooks = em.createNamedQuery("Book.fetchAvailableBooks").getResultList();

		for (BookCopy bc : availableBooks) {
			System.out.println(bc.getEdition());
		}
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
	 * public Member signIn(String user, String pwd) {
	 * 
	 * MemberAccountImpl mai = MemberAccountFactory.signUp(user, pwd);
	 * EntityManagerFactory emf =
	 * Persistence.createEntityManagerFactory("LibrarySystem"); EntityManager em =
	 * emf.createEntityManager(); Account result = null;
	 * 
	 * // Retrieve entity Query query = em.createNamedQuery("Login.findAccount");
	 * query.setParameter(1, user).setParameter(2, pwd);
	 * 
	 * try { result = (Account) query.getSingleResult();
	 * mai.getAccount().setMember(result.getMember());
	 * mai.getMember().setAccount(mai.getAccount());
	 * 
	 * } catch (javax.persistence.NoResultException e) { result = null; } finally {
	 * System.out.println(result.getMember().getFirstName());
	 * System.out.println(mai.getMember().getFirstName());
	 * System.out.println(mai.getAccount().getId().getPassword());
	 * System.out.println(mai.getAccount().getMember().getAccount().getId().
	 * getPassword()); em.close(); } return mai.getAccount().getMember(); }
	 */
}
