package PBL.Server;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class ServerFrame extends JFrame implements Runnable {

	Socket socket = null;

	private final static String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	private JPanel contentPane;
	private JTextArea txt_Content;

	
	/**
	 * Launch the application.
	 */
	public ServerFrame()
	{
		initcomponent();
	}
	
	public void run() {
		try {
			ServerSocket serversocket = new ServerSocket(9999);
			while(true)
			{
				Socket socket = serversocket.accept();
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
	            DataInputStream din = new DataInputStream(socket.getInputStream());
	        	String str = din.readUTF();
	        	String output;
				output = Handling(str);
	        	dos.writeUTF(output);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void initcomponent() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		setBounds(100, 100, 450, 305);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Server", SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel.setBounds(10, 9, 416, 58);
		contentPane.add(lblNewLabel);
		
		JButton btnListen = new JButton("Start");
		btnListen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {	
					@Override
					public void run() {
						try {
								InetAddress myid = InetAddress.getLocalHost();
								txt_Content.setText(txt_Content.getText() + "Địa chỉ ip máy bạn là : " + myid + "\n" + "Listening...");
								
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "Đã khởi động Server");
							e1.printStackTrace();
						}
						
					}
				}).start();
				
			}		
		});
		btnListen.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		btnListen.setBounds(175, 75, 78, 30);
		contentPane.add(btnListen);
		
		txt_Content = new JTextArea();
		txt_Content.setBounds(10, 134, 402, 124);
		contentPane.add(txt_Content);
		setVisible(true);
	}
	public static String Handling(String str) throws IOException 
	{
		 String matrix[] = str.split("  ");
         String mGraph[] =  matrix[0].split("@");
 		String mPoint[] = matrix[1].split("@");
 		char[] tokens = matrix[2].toCharArray();
 		String outputG = mGraph[0] + "\n";
 		String outputP = mPoint[0] + "\n";
 		int colG = Integer.parseInt(mGraph[0]);
 		int rowG = Integer.parseInt(mGraph[1]);
 		for(int i=0; i < colG; i++ )
 		{
 			for(int j=0; j < rowG; j++)
 			{
 				if(j !=(rowG -1))
 					outputG = outputG + mGraph[i+2].charAt(j) + " ";
 				else
 					outputG = outputG + mGraph[i+2].charAt(j);
 			}
 			if( i != (colG -1))
 				outputG = outputG + "\n";
 		}
 		ReadFile r = new ReadFile();
 		r.writeDataG(outputG);
 		int colP = Integer.parseInt(mPoint[0]);
 		int rowP = Integer.parseInt(mPoint[1]);
 		for(int i=0; i < colP; i++ )
 		{
 			for(int j=0; j < rowP; j++)
 			{
 				if(j !=(rowP -1))
 					outputP = outputP + mPoint[i+2].charAt(j) + " ";
 				else
 					outputP = outputP + mPoint[i+2].charAt(j);
 			}
 			if( i != (colP -1))
 				outputP = outputP + "\n";
 		}
 		r.writeDataP(outputP);
 		Dijkstra dij = new Dijkstra();
 		String output = dij.dijkstra(tokens[0], tokens[1], ALPHABET.charAt(colP-1));
 		return output;
	}
}



