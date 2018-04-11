package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the accounts database table.
 * 
 */
@Entity
@Table(name="accounts")
@NamedQuery(name="Account.findAll", query="SELECT a FROM Account a")
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AccountPK id;

	//bi-directional one-to-one association to Member
	@OneToOne
	@JoinColumn(name="memberId")
	private Member member;

	public Account() {
	}

	public Account(String user, String password) {
		this.id = new AccountPK();
		this.id.setUsername(user);
		this.id.setPassword(password);
	}



	public AccountPK getId() {
		return this.id;
	}

	public void setId(AccountPK id) {
		this.id = id;
	}

	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

}