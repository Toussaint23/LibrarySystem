package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MemberBookTransactionFactory {

	public static List<BookTransaction> createListTransaction(Date borrowDate, Date returnDate, Member member, List<BookCopy> bookCopy) {
		List<BookTransaction> transaction = new ArrayList<BookTransaction>();
		
		for(BookCopy bc : bookCopy) {
			BookTransaction bt = new BookTransaction(borrowDate, returnDate, member, bc);
			transaction.add(bt);
			bc.addBookTransaction(bt);
		}
		
		return transaction;
	}
	

}
