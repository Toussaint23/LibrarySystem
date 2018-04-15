package model;

import java.util.ArrayList;

public class BookFactory {
	public static BookCopyImplementation createBookAndBookCopy(String isn, String author, String bookTitle,
			String bookType, String edition, int isAvailable) {
		Book book = new Book(isn, author, bookTitle, bookType);
		if (book.getBookCopies() == null) {
			book.setBookCopies(new ArrayList<BookCopy>());
		}
		BookCopy bookCopy = book.addBookCopy(new BookCopy(edition, isAvailable));
		
		return new BookCopyImplementation(book, bookCopy);
	}
	
	public static void addBookCopy(String edition, int isAvailable) {
		
	}
}
