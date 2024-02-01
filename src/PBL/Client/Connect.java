package PBL.Client;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.SystemColor;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Connect extends JFrame implements Runnable {

	private JPanel contentPane;
	private JFrame frmShort;
	private JTextField textField_DuongDi;
	private JTextField textField_KhoangCach;

	String file = "";
	DataInputStream din;
	DataOutputStream dos;

	String ipServer;
	boolean check = false;
	private final static String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	int port = 9999;
	Socket socket;

	/**
	 * Launch the application.
	 */
	public Connect() {
		try {
			initComponent();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void run() {
		try
		{

		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 * @return 
	 * @throws IOException 
	 */

	
	private void initComponent() throws IOException {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("IP Server: ");
		JLabel lblNewLabel_1 = new JLabel("Tên máy ");
		JLabel lblNewLabel_1_1 = new JLabel("IP Address ");
		JLabel lblNewLabel_1_3 = new JLabel("Trạng thái ");
		JTextArea textArea_IPServer = new JTextArea();
		JTextArea textArea_NameCom = new JTextArea();
		JTextArea textArea_IPAddress = new JTextArea();
		JTextArea textArea_Status = new JTextArea();
		JButton btnQuetIP = new JButton("Quét IP");
		JButton btnExit = new JButton("Thoát");
		JButton btnConnect = new JButton("Kết nối ");
		
		
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setBounds(40, 28, 78, 27);
		contentPane.add(lblNewLabel);
		

		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_1.setBounds(40, 81, 78, 27);
		contentPane.add(lblNewLabel_1);
		

		lblNewLabel_1_1.setForeground(Color.BLACK);
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_1_1.setBounds(40, 131, 78, 27);
		contentPane.add(lblNewLabel_1_1);
		

		lblNewLabel_1_3.setForeground(Color.BLACK);
		lblNewLabel_1_3.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_1_3.setBounds(40, 181, 78, 27);
		contentPane.add(lblNewLabel_1_3);
		

		textArea_IPServer.setBounds(161, 30, 121, 25);
		contentPane.add(textArea_IPServer);
	

		textArea_NameCom.setText(InetAddress.getLocalHost().getHostName());
		textArea_NameCom.setForeground(Color.red);
		textArea_NameCom.setBounds(161, 82, 121, 25);
		contentPane.add(textArea_NameCom);
		

		textArea_IPAddress.setText(InetAddress.getLocalHost().getHostAddress());
		textArea_IPAddress.setForeground(Color.red);
		textArea_IPAddress.setBounds(161, 132, 121, 25);
		contentPane.add(textArea_IPAddress);
		

		textArea_Status.setText("Đang chờ kết nối tới server");
		textArea_Status.setForeground(Color.red);
		textArea_Status.setBounds(161, 182, 227, 25);
		contentPane.add(textArea_Status);

		
		btnQuetIP.setForeground(Color.BLACK);
		btnQuetIP.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnQuetIP.setBackground(SystemColor.scrollbar);
		btnQuetIP.setBounds(108, 230, 85, 27);
		contentPane.add(btnQuetIP);
		

		btnExit.setForeground(Color.BLACK);
		btnExit.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnExit.setBackground(SystemColor.scrollbar);
		btnExit.setBounds(216, 230, 85, 27);
		contentPane.add(btnExit);
		

		btnConnect.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnConnect.setForeground(SystemColor.windowText);
		btnConnect.setBackground(SystemColor.scrollbar);
		btnConnect.setBounds(303, 29, 85, 27);
		contentPane.add(btnConnect);
		
		
		btnExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		
		btnQuetIP.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if(check)
						{
						try {
							initializeClient();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						}
						else {
							JOptionPane.showMessageDialog(getContentPane(), "Kết nối server thất bại");
						} 
						
					}
				});
		
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ipServer = textArea_IPServer.getText().toString();
					if(ipServer == null || ipServer.isEmpty()) {
						JOptionPane.showMessageDialog(getContentPane(), "Nhập địa chỉ IP Server");
					}
					else {
						socket = new Socket(ipServer, 9999);
						textArea_Status.setText("Đã kết nối server thành công");
						textArea_Status.setForeground(Color.red);	
						check = true;

					}}
					catch (Exception ex) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, "Không thể kết nối với server!");
						textArea_Status.setText("Đang chờ kết nối tới server");
					}

			}		});
		setVisible(true);
		
	}

	private void initializeClient() throws UnknownHostException, IOException {
		
		frmShort = new JFrame();
		JPanel panel = new JPanel();
		textField_DuongDi = new JTextField();
		textField_KhoangCach = new JTextField();
		JPanel panel_4 = new JPanel();
		JLabel lblNewLabel_4 = new JLabel("Điều khiển");
		JButton btnKetThuc = new JButton("Kết thúc");
		JPanel panel_1 = new JPanel();
		JPanel panel_2 = new JPanel();
		JTextArea textArea_Nguon = new JTextArea();
		JTextArea textArea_Dich = new JTextArea();
		JLabel lblNewLabel_1 = new JLabel("Chọn trạm trên mạng");
		JLabel lblNewLabel_2 = new JLabel("Nguồn");
		JLabel lblNewLabel_3 = new JLabel("Đích");
		JLabel lblNewLabel_5 = new JLabel("Chọn file ma trận trọng số");
		JTextField textField_Graph = new JTextField();
		JLabel lblNewLabel_6 = new JLabel("Chọn file ma trận đỉnh");
		JTextField textField_Point = new JTextField();
		JButton btnNewButton = new JButton("Chọn");
		JButton btnBrowseGraph = new JButton("Browse");
		JButton btnBrowsePoint = new JButton("Browse");
		JButton btnOK = new JButton("OK");
		
		frmShort.setTitle("Short Path Routing");
		frmShort.setBounds(100, 100, 1016, 527);
		frmShort.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmShort.getContentPane().setLayout(null);
		
		
		panel.setBounds(369, 20, 610, 460);
		frmShort.getContentPane().add(panel);
		panel.setLayout(null);
		
		
		textField_DuongDi.setBounds(110, 261, 365, 37);
		panel.add(textField_DuongDi);
		textField_DuongDi.setColumns(10);
		
		
		textField_KhoangCach.setColumns(10);
		textField_KhoangCach.setBounds(110, 308, 365, 37);
		panel.add(textField_KhoangCach);
		
		
		panel_4.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_4.setBounds(200, 355, 176, 95);
		panel.add(panel_4);
		panel_4.setLayout(null);
		
		
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(10, 0, 80, 21);
		panel_4.add(lblNewLabel_4);
		
		
		btnKetThuc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		
		btnKetThuc.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnKetThuc.setBounds(46, 42, 87, 43);
		panel_4.add(btnKetThuc);
		
		
		panel_1.setBounds(34, 20, 400, 460);
		frmShort.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		
		
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_2.setBounds(10, 250, 304, 155);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		
		textArea_Nguon.setFont(new Font("Arial", Font.BOLD, 20));
		textArea_Nguon.setBounds(35, 53, 75, 34);
		panel_2.add(textArea_Nguon);
		
		
		textArea_Dich.setFont(new Font("Arial", Font.BOLD, 20));
		textArea_Dich.setBounds(190, 53, 75, 34);
		panel_2.add(textArea_Dich);
		
		
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(10, 0, 118, 21);
		panel_2.add(lblNewLabel_1);
		
		
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_2.setBounds(46, 20, 64, 34);
		panel_2.add(lblNewLabel_2);
		
		
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_3.setBounds(201, 20, 64, 34);
		panel_2.add(lblNewLabel_3);
		
		
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_5.setBounds(10, 10, 304, 155);
		panel_1.add(lblNewLabel_5);
		
		
		textField_Graph.setFont(new Font("Arial",Font.PLAIN, 12));
		textField_Graph.setBounds(10, 100, 220, 25);
		panel_1.add(textField_Graph);
		
		
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_6.setBounds(10, 70, 304, 155);
		panel_1.add(lblNewLabel_6);
		
		
		textField_Point.setFont(new Font("Arial", Font.PLAIN, 12));
		textField_Point.setBounds(10, 160, 220, 25);
		panel_1.add(textField_Point);
		
		
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnNewButton.setBounds(101, 102, 87, 43);
		panel_2.add(btnNewButton);

		
		
		btnBrowseGraph.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
		        final JFileChooser fc = new JFileChooser();
		        fc.showOpenDialog(frmShort);
		        try {
		            if (fc.getSelectedFile() != null) {
		                textField_Graph.setText(fc.getSelectedFile().getPath());
		            }
		        } catch (Exception e1) {
		            e1.printStackTrace();
		        }
				
			}
		});
		btnBrowseGraph.setBounds(230, 100, 80, 25);
		panel_1.add(btnBrowseGraph);
		
		
		btnBrowsePoint.setBounds(230, 160, 80, 25);
		panel_1.add(btnBrowsePoint);
		
		btnBrowsePoint.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
		        final JFileChooser fc = new JFileChooser();
		        fc.showOpenDialog(frmShort);
		        try {
		            if (fc.getSelectedFile() != null) {
		                textField_Point.setText(fc.getSelectedFile().getPath());
		            }
		        } catch (Exception e1) {
		            e1.printStackTrace();
		        }
				
			}
		});
		
		
		btnOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(textField_Graph.getText().toString()==null || textField_Graph.getText().toString().isEmpty() || textField_Point.getText().toString() ==null || textField_Point.getText().toString().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Vui lòng chọn file ma trận trọng số và đỉnh");
				}
				else
				{
					DrawGraph g = new DrawGraph(textField_Graph.getText().toString(),textField_Point.getText().toString());
					JPanel hinh = new JPanel();
					hinh.setBounds(0, 0, 610, 240);
					g.setBounds(32, 32, 492, 220);
				    hinh.add(g);
				    panel.add(g);
				}
			}
		});
		btnOK.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnOK.setBounds(110, 195, 87, 43);
		panel_1.add(btnOK);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {
					
					
					@Override
					public void run() {
						try {
							din = new DataInputStream(socket.getInputStream());
							dos = new DataOutputStream(socket.getOutputStream());
							int mtr[][] = new ReadFile().loadData(textField_Graph.getText().toString());
							int maxMtr = mtr.length;
							boolean checkG = false;
							boolean checkP = false;
							
							while(true) {
							String nguon = textArea_Nguon.getText();
							String dich = textArea_Dich.getText();
							
							for(int i=0; i< maxMtr; i++) 
							{

								if(Character.toString(ALPHABET.charAt(i)).equals(nguon.toString()) )
								{
									checkG = true;
								}
							}
							for(int i=0; i< maxMtr; i++) 
							{

								if(Character.toString(ALPHABET.charAt(i)).equals(dich.toString()) )
								{
									checkP = true;
								}
							}
							if(checkG == false || checkP == false)
							{
								JOptionPane.showMessageDialog(null, "Lỗi");
								break;
							}
							else
							{
						// xử lí
								int matrixG[][] = new ReadFile().loadData(textField_Graph.getText().toString());
								int matrixP[][] = new ReadFile().loadData(textField_Point.getText().toString());
								String output = String.valueOf(matrixG.length);
								String outG = "";
								String outP = "";
								for(int i=0; i < matrixG.length; i++)
								{
									{
										for(int j=0; j< matrixG.length; j++)
										{
											outG += String.valueOf(matrixG[i][j]);
										}
										if(i != matrixG.length-1) outG += "@";
									}
								}
								output += "@" + matrixG.length + "@" + outG + "  ";
								for(int i=0; i < matrixP.length; i++) 
								{
									{
										for(int j=0; j < 2; j++) 
										{
											outP += String.valueOf(matrixP[i][j]);
										}
										if(i != matrixP.length-1) outP += "@";
									}
								}
								output += matrixP.length + "@" + "2" + "@" + outP;
								output += "  " + nguon + dich;
								dos.writeUTF(output);
							//
								String ketqua = din.readUTF();
								String[] result = ketqua.split("  ");
								textField_DuongDi.setText("Đường đi: " + result[0]);
								textField_KhoangCach.setText("Khoảng cách: " + result[1]);	
							}
						} }
						catch (IOException e1) {
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null, "Lỗi");
						}
						}

						
				}).start();
						
					}	
			}
		);
		
		frmShort.setVisible(true);
		
	
	}
	
	}

