package gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import controller.Admin;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;

public class AppMain extends Application {
	private Parent root;
	private Scene scene;
	private Member currentMember;

	public AppMain(Member currentMember) {
		this.currentMember = currentMember;
	}

	@Override
	public void start(Stage stage) throws Exception {
		root = FXMLLoader.load(getClass().getResource("resources/xml/AppMain.fxml"));
		scene = new Scene(root, 750, 650);
		stage.setScene(scene);
		stage.setTitle("Library Systems");
		stage.setResizable(false);

		Button btnExit = (Button) scene.lookup("#btn_exit");
		Button btnSearch = (Button) scene.lookup("#btn_search");
		Button btnBorrow = (Button) scene.lookup("#btn_borrow");
		Button btnReturn = (Button) scene.lookup("#btn_return");
		Button btnadd = (Button) scene.lookup("#btn_add");
		Button btnRemove = (Button) scene.lookup("#btn_remove");
		Button btnRegister = (Button) scene.lookup("#btn_register");
		
		if(currentMember.getIsSuperMember() != 1) {
			btnRemove.setVisible(false);
			btnRegister.setVisible(false);
			btnadd.setVisible(false);
		}
		

		btnExit.setOnAction(evt -> {
			signOut();
		});

		btnSearch.setOnAction(evt -> {
			searchBookByName();
		});

		btnBorrow.setOnAction(evt -> {
			borrowsBook();
		});

		btnReturn.setOnAction(evt -> {
			returnBook();
		});
		
		btnRemove.setOnAction(evt -> {
			removeBook();
		});
		
		btnadd.setOnAction(evt -> {
			try {
				addBook();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		stage.show();
	}

	public static void Main(String[] args) {
		launch(args);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addBook() throws IOException {
		FXMLLoader fXMLLoader = new FXMLLoader();
		fXMLLoader.setLocation(getClass().getResource("resources/xml/AddBook.fxml"));
		Stage stage = new Stage();
		Scene scene = new Scene(fXMLLoader.load());
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("Add new book");
		ComboBox cb = (ComboBox) scene.lookup("#type");
		ComboBox quantity = (ComboBox) scene.lookup("#quantity");
		TextField txtIsn = (TextField) scene.lookup("#isn");
		TextField txtTitle = (TextField) scene.lookup("#title");
		TextField txtAuthor = (TextField) scene.lookup("#author");
		TextField txtEdition = (TextField) scene.lookup("#edition");

		Button cancel = (Button) scene.lookup("#btn_cancel");
		Button add = (Button) scene.lookup("#btn_add");
		cb.getItems().addAll("MBA", "CS");
		for (int i = 1; i <= 100; i++)
			quantity.getItems().addAll(i);
		stage.show();

		Label error = (Label) scene.lookup("#error_label");

		cancel.setOnAction(evt -> {
			txtIsn.setText("");
			txtTitle.setText("");
			txtAuthor.setText("");
			txtEdition.setText("");

		});

		add.setOnAction(evt -> {
			String isn = txtIsn.getText().trim();
			String title = txtTitle.getText().trim();
			String author = txtAuthor.getText().trim();
			String edition = txtEdition.getText().trim();
			String type = cb.getSelectionModel().getSelectedItem().toString().trim();
			

			if (!isn.isEmpty() && !title.isEmpty() && !author.isEmpty() && !edition.isEmpty() && !type.isEmpty()) {
				try {
					Admin admin = new Admin();
					int qty = Integer.parseInt(quantity.getSelectionModel().getSelectedItem().toString().trim());
					
					BookCopyImplementation mai = admin.createBook(isn, author, title, type, edition, 1,qty);

					if (mai == null) {
						error.setText("Operation failed! Try again");
						error.setVisible(true);
					} else {
						error.setText("Success! The book is added");
						error.setTextFill(Color.GREEN);
						error.setVisible(true);
					}
				} catch (Exception e) {
					error.setText("Precise the quantity of copy");
					error.setTextFill(Color.BLACK);
					error.setVisible(true);
				}

			} else {
				error.setText("All fields are required");
				error.setTextFill(Color.BLACK);
				error.setVisible(true);
			}
		});

	}

	public void returnBook() {

		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Return books");
		dialog.setHeaderText(null);
		dialog.setContentText("Please enter list id of bookd (Exmp : 112,111,...)");

		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			Admin admin = new Admin();
			boolean results = admin.returnBookCopy(result.get(), SignIn.currentMember.getId());
			if (results) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information");
				alert.setHeaderText(null);
				alert.setContentText("Books returned");
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information");
				alert.setHeaderText(null);
				alert.setContentText("Operation failed");
				alert.showAndWait();
			}
		}

	}
	
	
	
	public void removeBook() {

		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Remove books");
		dialog.setHeaderText(null);
		dialog.setContentText("Enter ISN of book");

		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			Admin admin = new Admin();
			Book results = admin.removeBook(result.get());
			if (results != null) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information");
				alert.setHeaderText(null);
				alert.setContentText("Books removed");
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information");
				alert.setHeaderText(null);
				alert.setContentText("Operation failed! You cannot remove this book");
				alert.showAndWait();
			}
		}

	}
	
	
	
	
	

	public void borrowsBook() {

		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Borrow books");
		dialog.setHeaderText(null);
		dialog.setContentText("Please enter list id of bookd (Exmp : 112,111,...)");

		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			Admin admin = new Admin();
			boolean results = admin.borrowsBookCopy(result.get(), SignIn.currentMember.getId());
			if (results) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information");
				alert.setHeaderText(null);
				alert.setContentText("Books borrowed");
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information");
				alert.setHeaderText(null);
				alert.setContentText("Operation failed");
				alert.showAndWait();
			}
		}

	}

	public void signOut() {
		try {
			Stage stage = new Stage();
			new SignIn().start(stage);
			this.root.getScene().getWindow().hide();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void searchBookByName() {
		try {
			String bookname = ((TextField) scene.lookup("#bookname")).getText().trim();
			TableView table = (TableView) scene.lookup("#book_table");
			table.getItems().setAll();

			if (!bookname.isEmpty()) {
				Admin admin = new Admin();

				List<BookCopy> mai = admin.fetchAvailableBooksByName(bookname);
				List<Book> b = new ArrayList<>();
				for (BookCopy bc : mai) {
					b.add(bc.getBook());
				}

				List<MyData> data = new ArrayList<MyData>();

				for (BookCopy bc : mai) {
					data.add(new MyData(bc.getId(), bc.getBook().getIsn(), bc.getBook().getAuthor(),
							bc.getBook().getBookTitle(), bc.getEdition(), bc.getBook().getBookType()));
				}

				if (mai == null || mai.size() == 0) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information");
					alert.setHeaderText(null);
					alert.setContentText("Book not found");
					alert.showAndWait();
				} else {

					table.getColumns().setAll();
					TableColumn[] tc = new TableColumn[] { new TableColumn("Book ID"), new TableColumn("Title"),
							new TableColumn("Type"), new TableColumn("ISN"), new TableColumn("Author"),
							new TableColumn("Edition") };
					table.getColumns().setAll(tc[0], tc[1], tc[2], tc[3], tc[4], tc[5]);

					ObservableList<MyData> csvDatas = FXCollections.observableArrayList(data);
					tc[0].setCellValueFactory(new PropertyValueFactory<MyData, String>("identity"));
					tc[1].setCellValueFactory(new PropertyValueFactory<BookCopy, String>("title"));
					tc[2].setCellValueFactory(new PropertyValueFactory<MyData, String>("type"));
					tc[3].setCellValueFactory(new PropertyValueFactory<BookCopy, String>("isn"));
					tc[4].setCellValueFactory(new PropertyValueFactory<MyData, String>("author"));
					tc[5].setCellValueFactory(new PropertyValueFactory<BookCopy, String>("edition"));

					table.setItems(csvDatas);

				}

			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information");
				alert.setHeaderText(null);
				alert.setContentText("You must type the name of your book");
				alert.showAndWait();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
