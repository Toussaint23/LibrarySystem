package model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class MemberAccountFactory {

	public static MemberAccountImpl createMemberAndAccount(String id, String email, String firstName,
			int isSuperMember, String lastName, String user, String pwd) {
		Member member = new Member(id, email, firstName, isSuperMember, lastName);
		Account account = new Account(user, pwd);

		member.setAccount(account);
		account.setMember(member);
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibrarySystem");
		EntityManager em = emf.createEntityManager();

		// Persist entity
		em.getTransaction().begin();
		em.persist(member);
		em.persist(account);
		em.getTransaction().commit();
		em.close();

		return new MemberAccountImpl(member, account);
	}
	
	public static MemberAccountImpl signUp(String user, String pwd) {
		
		Member member = new Member();
		Account account = new Account(user, pwd);
		Account result;
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibrarySystem");
		EntityManager em = emf.createEntityManager();
		
		// Retrieve entity
		Query query = em.createNamedQuery("Login.findAccount");
		query.setParameter(1, user).setParameter(2, pwd);
		
		try {
			result = (Account) query.getSingleResult();
			member.setAccount(result);
			account.setMember(result.getMember());
			
		} catch (javax.persistence.NoResultException e) {
			result = null;
		}
		finally {
			em.close();
		}
		
		return (result == null) ? null : new  MemberAccountImpl(member, account);
	}

}
