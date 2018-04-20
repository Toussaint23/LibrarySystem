package model;

public class MemberBookCopyTransactionImpl {

	Member member;
	BookCopy bookCopy;
	BookTransaction bookTransaction;

	public MemberBookCopyTransactionImpl(Member member, BookCopy bookCopy, BookTransaction bookTransaction) {
		super();
		this.member = member;
		this.bookCopy = bookCopy;
		this.bookTransaction = bookTransaction;
	}

	public Member getMember() {
		// TODO Auto-generated method stub
		return member;
	}

	public BookCopy getBookCopy() {
		// TODO Auto-generated method stub
		return bookCopy;
	}

	public BookTransaction getBookTransaction() {
		// TODO Auto-generated method stub
		return bookTransaction;
	}

}
