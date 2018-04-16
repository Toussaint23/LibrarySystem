package model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the members database table.
 * 
 */
@Entity
@Table(name="members")
@NamedQueries({
	@NamedQuery(name="Member.findAll", query="SELECT m FROM Member m")
})
public class Member implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String email;

	@Column(name="first_name")
	private String firstName;

	@Column(name="is_super_member")
	private int isSuperMember;

	@Column(name="last_name")
	private String lastName;

	//bi-directional many-to-one association to Account
	@OneToOne(mappedBy="member")
	private Account account;

	//bi-directional many-to-one association to BookTransaction
	@OneToMany(mappedBy="member")
	private List<BookTransaction> bookTransactions;

	Member() {
	}

	
	Member(String id, String email, String firstName, int isSuperMember, String lastName) {
		this.id = id;
		this.email = email;
		this.firstName = firstName;
		this.isSuperMember = isSuperMember;
		this.lastName = lastName;
		this.bookTransactions = new ArrayList<BookTransaction>();
	}


	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public int getIsSuperMember() {
		return this.isSuperMember;
	}

	public void setIsSuperMember(int isSuperMember) {
		this.isSuperMember = isSuperMember;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public List<BookTransaction> getBookTransactions() {
		return this.bookTransactions;
	}

	public void setBookTransactions(List<BookTransaction> bookTransactions) {
		this.bookTransactions = bookTransactions;
	}

	public BookTransaction addBookTransaction(BookTransaction bookTransaction) {
		getBookTransactions().add(bookTransaction);
		bookTransaction.setMember(this);

		return bookTransaction;
	}

	public BookTransaction removeBookTransaction(BookTransaction bookTransaction) {
		getBookTransactions().remove(bookTransaction);
		bookTransaction.setMember(null);

		return bookTransaction;
	}

}