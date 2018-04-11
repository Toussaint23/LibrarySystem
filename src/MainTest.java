import controller.Admin;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Admin admin = new Admin();
		admin.EnregistrerMember("st-0090", "dipo@mu.edu", "Isola", (byte) 0, "Dipo", "popo", "toto");
		admin.EnregistrerMember("st-0098", "jean@mu.edu", "Joseph", (byte) 1, "Jean", "jojo", "roro");
	}

}
