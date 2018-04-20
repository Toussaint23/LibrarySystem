package model;

public class BookCopyImplementation {
	Book book;
	BookCopy bookCopy;
	
	public BookCopyImplementation(Book book, BookCopy bookCopy) {
		this.book = book;
		this.bookCopy = bookCopy;
	}

	public Book getBook() {
		return book;
	}

	public BookCopy getBookCopy() {
		return bookCopy;
	}

}
