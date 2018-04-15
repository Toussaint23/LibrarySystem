import controller.Admin;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Admin admin = new Admin();
		/*admin.RegisterMember("st-0090", "dipo@mu.edu", "Isola",  0, "Dipo", "popo", "toto");
		admin.RegisterMember("st-0098", "jean@mu.edu", "Joseph", 1, "Jean", "jojo", "roro");
		admin.RegisterMember("st-0080", "chris@mu.edu", "Chrisner", 0, "Charles", "coco", "roro");*/
		//admin.RegisterMember("st-0078", "math@mu.edu", "Joseph", 1, "Mathieu", "momo", "poto");
		//admin.signIn("momo", "poto");

		admin.createBook("7831", "Wole Soyinka", "Ibadan", "MBA", "first", 1);
		
	}

}
