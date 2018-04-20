package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the books database table.
 * 
 */
@Entity
@Table(name="books")
@NamedQueries({
	@NamedQuery(name="Book.findAll", query="SELECT b FROM Book b"),
	@NamedQuery(name="Book.findBookByName", query="SELECT b FROM Book b WHERE b.bookTitle = ?1"),
})

public class Book implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String isn;

	private String author;

	@Column(name="book_title")
	private String bookTitle;

	@Column(name="book_type")
	private String bookType;

	//bi-directional many-to-one association to BookCopy
	@OneToMany(mappedBy="book", cascade = CascadeType.PERSIST)
	private List<BookCopy> bookCopies;

	Book() {
	}
	
	Book(String isn, String author, String bookTitle, String bookType) {
		this.isn = isn;
		this.author = author;
		this.bookTitle = bookTitle;
		this.bookType = bookType;
	}

	public String getIsn() {
		return this.isn;
	}

	public void setIsn(String isn) {
		this.isn = isn;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getBookTitle() {
		return this.bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getBookType() {
		return this.bookType;
	}

	public void setBookType(String bookType) {
		this.bookType = bookType;
	}

	public List<BookCopy> getBookCopies() {
		return this.bookCopies;
	}

	public void setBookCopies(List<BookCopy> bookCopies) {
		this.bookCopies = bookCopies;
	}

	public BookCopy addBookCopy(BookCopy bookCopy) {
		getBookCopies().add(bookCopy);
		bookCopy.setBook(this);

		return bookCopy;
	}

	public BookCopy removeBookCopy(BookCopy bookCopy) {
		getBookCopies().remove(bookCopy);
		bookCopy.setBook(null);

		return bookCopy;
	}

}