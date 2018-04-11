package model;

public class BookCopyImplementation implements BookCopyInterface {
	Book book;
	BookCopy bookCopy;
	
	public BookCopyImplementation(Book book, BookCopy bookCopy) {
		this.book = book;
		this.bookCopy = bookCopy;
	}

	@Override
	public Book getBook() {
		return book;
	}

	@Override
	public BookCopy getBookCopy() {
		return bookCopy;
	}

}
