package PBL.Client;


public class Client {
	public static void main(String[] args) {
		try {
			new Thread(new Connect()).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
