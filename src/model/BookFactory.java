package model;

import java.util.ArrayList;
import java.util.List;

public class BookFactory {
	public static BookCopyImplementation createBookAndBookCopy(String isn, String author, String bookTitle,
			String bookType, String edition, int isAvailable, int qty) {
		
		Book book = new Book(isn, author, bookTitle, bookType);
		BookCopy bookCopy = new BookCopy(edition, isAvailable);
		bookCopy.setBook(book);
		
		List<BookCopy> bclist = new ArrayList<BookCopy>();
		for(int i = 1; i < qty; i++) bclist.add(bookCopy);
		
		book.setBookCopies(bclist);
		
		bookCopy.setBook(book);


		return new BookCopyImplementation(book, bookCopy);
	}
}
