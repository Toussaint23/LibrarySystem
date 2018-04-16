package model;

public class MemberBookCopyTransactionImpl implements MemberBookCopyTransactionInterface {

	Member member;
	BookCopy bookCopy;
	BookTransaction bookTransaction;

	public MemberBookCopyTransactionImpl(Member member, BookCopy bookCopy, BookTransaction bookTransaction) {
		super();
		this.member = member;
		this.bookCopy = bookCopy;
		this.bookTransaction = bookTransaction;
	}

	@Override
	public Member getMember() {
		// TODO Auto-generated method stub
		return member;
	}
	
	@Override
	public BookCopy getBookCopy() {
		// TODO Auto-generated method stub
		return bookCopy;
	}

	@Override
	public BookTransaction getBookTransaction() {
		// TODO Auto-generated method stub
		return bookTransaction;
	}

}
