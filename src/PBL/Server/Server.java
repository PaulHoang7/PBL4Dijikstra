package PBL.Server;


public class Server {
	public static void main(String[] args) {
		try {
			new Thread(new ServerFrame()).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}