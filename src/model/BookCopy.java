package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the book_copies database table.
 * 
 */
@Entity
@Table(name="book_copies")
@NamedQueries({
	@NamedQuery(name="BookCopy.findAll", query="SELECT b FROM BookCopy b"),
	@NamedQuery(name="BookCopy.fetchAvailableBooksByIdBook", query="SELECT bc FROM BookCopy bc WHERE bc.book.isn = ?1 AND bc.isAvailable = 1"),
	@NamedQuery(name="BookCopy.fetchAvailableBooks", query="SELECT bc FROM BookCopy bc WHERE bc.isAvailable = 1")
})
public class BookCopy implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String edition;

	private int isAvailable;

	//bi-directional many-to-one association to Book
	@ManyToOne
	@JoinColumn(name="book_id")
	private Book book;

	//bi-directional many-to-one association to BookTransaction
	@OneToMany(mappedBy="bookCopy")
	private List<BookTransaction> bookTransactions;

	public BookCopy() {
	}
	
	public BookCopy(String edition, int isAvailable) {
		this.edition = edition;
		this.isAvailable = isAvailable;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEdition() {
		return this.edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public int getIsAvailable() {
		return this.isAvailable;
	}

	public void setIsAvailable(int isAvailable) {
		this.isAvailable = isAvailable;
	}

	public Book getBook() {
		return this.book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public List<BookTransaction> getBookTransactions() {
		return this.bookTransactions;
	}

	public void setBookTransactions(List<BookTransaction> bookTransactions) {
		this.bookTransactions = bookTransactions;
	}

	public BookTransaction addBookTransaction(BookTransaction bookTransaction) {
		getBookTransactions().add(bookTransaction);
		bookTransaction.setBookCopy(this);

		return bookTransaction;
	}

	public BookTransaction removeBookTransaction(BookTransaction bookTransaction) {
		getBookTransactions().remove(bookTransaction);
		bookTransaction.setBookCopy(null);

		return bookTransaction;
	}

}