import controller.Admin;
import gui.*;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainTest extends Application {

	public static void main(String[] args) throws Exception {
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Toto");
		Stage stage = new Stage();
		//new SignIn().start(stage); good
		new AppMain(null).start(stage);
		
	}
	
	
	// TODO Auto-generated method stub
			//Admin admin = new Admin();
			//admin.RegisterMember("st-0098", "jean@mu.edu", "Joseph", 1, "Jean", "jojo", "roro");
			//admin.RegisterMember("st-0090", "dipo@mu.edu", "Isola",  0, "Dipo", "popo", "toto");
			//admin.createBook("7832", "Wole Soyinka", "Ibadan", "MBA", "first", 1);
			//admin.createBook("7836", "Soyin", "Pok", "CS", "second", 1);
			//admin.createBook("7835", "Soyino", "Pok", "CS", "third", 1);
			//admin.borrowsBookCopy(new String[]{"8","9","10"},"st-0090");
			//admin.returnBookCopy(new String[]{"8","9"},"st-0090");
			//admin.removeBook("7832");
			
			
			/*admin.RegisterMember("st-0098", "jean@mu.edu", "Joseph", 1, "Jean", "jojo", "roro");
			admin.RegisterMember("st-0080", "chris@mu.edu", "Chrisner", 0, "Charles", "coco", "roro");*/
			//admin.RegisterMember("st-0078", "math@mu.edu", "Joseph", 1, "Mathieu", "momo", "poto");
			//admin.signIn("momo", "poto");

			//admin.createBook("7836", "Soyin", "Pok", "CS", "second", 1);

			//admin.signIn("momo", "pot");
			//admin.fetchAvailableBooks();
		//	admin.createBook("7832", "Wole Soyinka", "Ibadan", "MBA", "first", 1);
		//	admin.removeBook("7831");
		//	admin.fetchAvailableBooksByName("Ibadan");
		//	admin.returnBookCopy(new String[]{"4","5","7"},"st-0078");

}
