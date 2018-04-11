package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the book_transactions database table.
 * 
 */
@Entity
@Table(name="book_transactions")
@NamedQuery(name="BookTransaction.findAll", query="SELECT b FROM BookTransaction b")
public class BookTransaction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="borrow_date")
	private Date borrowDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="return_date")
	private Date returnDate;

	//bi-directional many-to-one association to Member
	@ManyToOne
	private Member member;

	//bi-directional many-to-one association to BookCopy
	@ManyToOne
	@JoinColumn(name="book_copy_id")
	private BookCopy bookCopy;

	public BookTransaction() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getBorrowDate() {
		return this.borrowDate;
	}

	public void setBorrowDate(Date borrowDate) {
		this.borrowDate = borrowDate;
	}

	public Date getReturnDate() {
		return this.returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public BookCopy getBookCopy() {
		return this.bookCopy;
	}

	public void setBookCopy(BookCopy bookCopy) {
		this.bookCopy = bookCopy;
	}

}