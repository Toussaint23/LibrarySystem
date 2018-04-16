package gui;

public class MyData {
		private String identity;
		private String isn;
		private String author;
		private String title;
		private String edition;
		private String type;
		
		public MyData(String id, String isn, String author, String title, String edition, String type) {
			this.identity = id;
			this.isn = isn;
			this.author = author;
			this.title = title;
			this.edition = edition;
			this.type = type;
		}

		public String getIdentity() {
			return identity;
		}

		public void setIdentity(String identity) {
			this.identity = identity;
		}

		public String getIsn() {
			return isn;
		}

		public void setIsn(String isn) {
			this.isn = isn;
		}

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getEdition() {
			return edition;
		}

		public void setEdition(String edition) {
			this.edition = edition;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}
	
}
