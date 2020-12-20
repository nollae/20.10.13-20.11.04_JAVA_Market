package market;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class MarketMgmUI extends JFrame {
	// Field
	public static MarketMgmSystem system = new MarketMgmSystem();

	public static final int REGISTER = 1;
	public static final int SEARCH = 2;
	public static final int UPDATE = 3;
	public static final int DELETE = 4;
	public static final int CHAT = 5;
	public static final int MYHOME = 6;
	public static final int REVIEW = 7;

	public static final int CONNECT = 0; // ó������ MulltiChatClient.CONNECT
	public static final int TALKING =1; // ��ȭ��
	public static final int EXIT = -1;//����
	
	ImagePanel showPane, northPane;
	JButton btnLogin, btnJoin; // �α��� ��ư, ȸ������ ��ư
	JPanel mainPane, contentsPane, menuPane;
	JButton btnReg, btnSearch, btnUpdate, btnDelete, btnChat, btnLogout, btnMyPage,btnReview;
	JLabel jl_title, jl_img;
	JTextField jtf_id; // ���̵� �Է� JTextField
	JPasswordField jtf_pass; // ��й�ȣ �Է� JPasswordField
	
	JTextArea content= new JTextArea();
	JTextField input= new JTextField();
	Socket socket;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	int now_room;

	JFrame frame = new JFrame();
	JPanel regPane = new JPanel();
	JPanel searchPane = new JPanel();
	JPanel updatePane = new JPanel();
	JPanel deletePane = new JPanel();
	JPanel chatPane = new JPanel();
	JPanel myPagePane = new JPanel();
	JPanel reviewPane = new JPanel();
	JScrollPane jScrollPane;


//	ImagePanel regsearchPane = new ImagePanel(new ImageIcon("C:\\dev\\se_workspace\\market\\images\\register_back.png").getImage()); //����

//����-C:/java_workspace/market/images/register_back.png
//�⸲ -"C:/dev/eclipse_workspace/market/images/register_back.png"
//�μ�-C:/dev/se_workspace/sist_project_1/images/register_back.png
//��ȭ - ("C:\\dev\\se_workspace\\market\\images\\register_back.png").getImage());

	JPanel joinBackPane = new JPanel();

	MemberVO vo = new MemberVO();
	public static Font font = new Font("���� ���", Font.BOLD, 12);
	private JPanel north_panel;

	// Constructor
	public MarketMgmUI() {
		super("���� �� �ٴ� : �ٲ㾲�� �ٽþ���");
		
		showMain();
//		start(); //���� �׽�Ʈ��
	}

	// Method
	public void showMain() { // 10.22 ���� ����4i
//		showPane = new ImagePanel(new ImageIcon("C:/java_workspace/market/images/login_main.png").getImage());
		showPane = new ImagePanel(new ImageIcon("C:\\dev\\se_workspace\\market\\images\\login_main.png").getImage());


		setSize(showPane.getWidth(), showPane.getHeight() + 38);

		getContentPane().add(showPane);

		JLabel Jl_id = new JLabel("\uC544\uC774\uB514");
		Jl_id.setFont(new Font("���ְ��", Font.PLAIN, 14));
		Jl_id.setHorizontalAlignment(SwingConstants.CENTER);
		Jl_id.setBounds(75, 367, 67, 34);
		showPane.add(Jl_id);

		JLabel Jl_pass = new JLabel("\uBE44\uBC00\uBC88\uD638");
		Jl_pass.setFont(new Font("���ְ��", Font.PLAIN, 14));
		Jl_pass.setBounds(75, 395, 67, 34);
		showPane.add(Jl_pass);

		jtf_id = new JTextField();
		jtf_id.setBounds(138, 376, 106, 21);
		showPane.add(jtf_id);
		jtf_id.setColumns(10);

		jtf_pass = new JPasswordField();
		jtf_pass.setBounds(138, 404, 106, 21);
		showPane.add(jtf_pass);
		jtf_pass.setColumns(10);

		btnLogin = new JButton("\uB85C\uADF8\uC778");
		btnLogin.setBackground(Color.DARK_GRAY);
		btnLogin.setForeground(new Color(153, 204, 255));
		btnLogin.setBounds(75, 447, 91, 23);
		showPane.add(btnLogin);

		btnJoin = new JButton("\uD68C\uC6D0\uAC00\uC785");
		btnJoin.setBackground(Color.DARK_GRAY);
		btnJoin.setForeground(new Color(153, 204, 255));
		btnJoin.setBounds(178, 447, 91, 23);
		showPane.add(btnJoin);
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
//		Image img = toolkit.getImage("C:/java_workspace/market/images/icon.png");
		Image img = toolkit.getImage("C:\\dev\\se_workspace\\market\\images\\icon.png");
		setIconImage(img);
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		btnLogin.addActionListener(new MarketMgmUIEvent(this));
		btnJoin.addActionListener(new MarketMgmUIEvent(this));
		jtf_pass.addActionListener(new MarketMgmUIEvent(this));
		addWindowListener(new MarketMgmUIEvent(this));

	}

	// start
	public void start() { // �ӽÿ�
		showPane.setVisible(false);
		getContentPane().setLayout(null);

		mainPane = new JPanel();
		menuPane = new JPanel();
		menuPane.setBounds(0, 130, 200, 650);
		mainPane.setBounds(200, 130, 1000, 650);

//		jl_title = new JLabel(vo.getId()+"\uB2D8 ' \uB3C4\uC2EC \uC18D \uBC14\uB2E4 '\uC5D0 \uC624\uC2E0 \uAC78 \uD658\uC601\uD569\uB2C8\uB2E4 :)");
		JLabel jl_id_title = new JLabel(vo.getId());
		jl_id_title.setHorizontalAlignment(SwingConstants.RIGHT);
		jl_id_title.setBounds(120, 32, 169, 22);
		jl_title = new JLabel("\uB2D8 ' \uB3C4\uC2EC \uC18D \uBC14\uB2E4 '\uC5D0 \uC624\uC2E0 \uAC78 \uD658\uC601\uD569\uB2C8\uB2E4 :)");
		jl_title.setBounds(290, 32, 405, 22);
		jl_img = new JLabel(new ImageIcon("images/resell.jpg"));
		jl_img.setBounds(299, 16, -1, -1);
		mainPane.setBackground(Color.WHITE);
		menuPane.setBackground(SystemColor.menu);
		regPane.setBackground(Color.WHITE);
		searchPane.setBackground(Color.WHITE);
		updatePane.setBackground(Color.WHITE);
		deletePane.setBackground(Color.WHITE);
		chatPane.setBackground(Color.WHITE);
		myPagePane.setBackground(Color.WHITE);

		btnReg = new JButton("�Խù� ���");
		btnReg.setForeground(Color.WHITE);
		btnReg.setBounds(40, 40, 120, 55);
		btnSearch = new JButton("�Խù� �˻�");
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setBounds(40, 130, 120, 55);
		btnUpdate = new JButton("�Խù� ����");
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setBounds(40, 220, 120, 55);
		btnDelete = new JButton("�Խù� ����");
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setBounds(40, 310, 120, 55);
		btnChat = new JButton("ä���ϱ�");
		btnChat.setForeground(Color.WHITE);
		btnChat.setBounds(40, 400, 120, 55);
		btnReview = new JButton("������ȸ");
		btnReview.setForeground(Color.WHITE);
		btnReview.setBounds(40, 490, 120, 55);

		btnReg.setBackground(new Color(102, 153, 204));
		btnSearch.setBackground(new Color(102, 153, 204));
		btnUpdate.setBackground(new Color(102, 153, 204));
		btnDelete.setBackground(new Color(102, 153, 204));
		btnChat.setBackground(new Color(102, 153, 204));
		btnReview.setBackground(new Color(102, 153, 204));

		jl_title.setFont(new Font("���ְ��", Font.PLAIN, 20));
		jl_id_title.setFont(new Font("���ְ��", Font.PLAIN, 20));
		jl_id_title.setForeground(Color.ORANGE);
		btnReg.setFont(new Font("���ְ��", Font.PLAIN, 17));
		btnSearch.setFont(new Font("���ְ��", Font.PLAIN, 17));
		btnUpdate.setFont(new Font("���ְ��", Font.PLAIN, 17));
		btnDelete.setFont(new Font("���ְ��", Font.PLAIN, 17));
		btnChat.setFont(new Font("���ְ��", Font.PLAIN, 17));
		btnReview.setFont(new Font("���ְ��", Font.PLAIN, 17));
		mainPane.setLayout(null);
		mainPane.add(jl_img);
		mainPane.add(jl_id_title);
		mainPane.add(jl_title);
		menuPane.setLayout(null);

		menuPane.add(btnReg);
		menuPane.add(btnSearch);
		menuPane.add(btnUpdate);
		menuPane.add(btnDelete);
		menuPane.add(btnChat);
		menuPane.add(btnReview);
//			add(menuPane, BorderLayout.WEST);
//			add(mainPane, BorderLayout.CENTER);
		getContentPane().add(menuPane);
		getContentPane().add(mainPane);
		
		JLabel lblNewLabel = new JLabel("1. �Խù� ���");
		lblNewLabel.setForeground(new Color(0, 102, 255));
		lblNewLabel.setFont(new Font("���ְ��", Font.PLAIN, 17));
		lblNewLabel.setBounds(120, 78, 109, 34);
		mainPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("�Ǹ��ϰ� ���� ��ǰ�� ����� �� �־�� ");
		lblNewLabel_1.setFont(new Font("���ְ��", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(271, 83, 290, 22);
		mainPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("��ǰ�̸�, ��ǰ����, �ŷ��� ����ó, ��ǰ����, �ŷ����, �ŷ����� �ۼ� �� ������ �� �־��");
		lblNewLabel_1_2.setFont(new Font("���ְ��", Font.PLAIN, 15));
		lblNewLabel_1_2.setBounds(271, 113, 587, 22);
		mainPane.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_2_1 = new JLabel("�������� ������ ��ǰ������ �ۼ����ּ���");
		lblNewLabel_1_2_1.setFont(new Font("���ְ��", Font.PLAIN, 15));
		lblNewLabel_1_2_1.setBounds(271, 143, 283, 22);
		mainPane.add(lblNewLabel_1_2_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("2. �Խù� �˻�");
		lblNewLabel_1_1.setForeground(new Color(0, 102, 255));
		lblNewLabel_1_1.setFont(new Font("���ְ��", Font.PLAIN, 17));
		lblNewLabel_1_1.setBounds(120, 176, 109, 34);
		mainPane.add(lblNewLabel_1_1);
		
		
		JLabel lblNewLabel_1_1_2 = new JLabel("3. �Խù� ����");
		lblNewLabel_1_1_2.setForeground(new Color(0, 102, 255));
		lblNewLabel_1_1_2.setFont(new Font("���ְ��", Font.PLAIN, 17));
		lblNewLabel_1_1_2.setBounds(120, 242, 109, 34);
		mainPane.add(lblNewLabel_1_1_2);
		
		JLabel lblNewLabel_1_1_3 = new JLabel("\uD310\uB9E4\uB97C \uC704\uD574 \uB4F1\uB85D\uD55C \uAC8C\uC2DC\uBB3C\uC744 \uC218\uC815\uD560 \uC218 \uC788\uC5B4\uC694\r\n");
		lblNewLabel_1_1_3.setFont(new Font("���ְ��", Font.PLAIN, 15));
		lblNewLabel_1_1_3.setBounds(271, 248, 337, 22);
		mainPane.add(lblNewLabel_1_1_3);
		
		JLabel lblNewLabel_1_1_2_1 = new JLabel("4. �Խù� ����");
		lblNewLabel_1_1_2_1.setForeground(new Color(0, 102, 255));
		lblNewLabel_1_1_2_1.setFont(new Font("���ְ��", Font.PLAIN, 17));
		lblNewLabel_1_1_2_1.setBounds(120, 316, 109, 34);
		mainPane.add(lblNewLabel_1_1_2_1);
		
		JLabel lblNewLabel_1_1_2_2 = new JLabel("\uD310\uB9E4\uB97C \uC704\uD574 \uB4F1\uB85D\uD55C \uAC8C\uC2DC\uBB3C\uC744 \uC0AD\uC81C\uD560 \uC218 \uC788\uC5B4\uC694");
		lblNewLabel_1_1_2_2.setFont(new Font("���ְ��", Font.PLAIN, 15));
		lblNewLabel_1_1_2_2.setBounds(271, 321, 301, 22);
		mainPane.add(lblNewLabel_1_1_2_2);
		
		JLabel lblNewLabel_1_1_2_3 = new JLabel("5. ä���ϱ�");
		lblNewLabel_1_1_2_3.setForeground(new Color(0, 102, 255));
		lblNewLabel_1_1_2_3.setFont(new Font("���ְ��", Font.PLAIN, 17));
		lblNewLabel_1_1_2_3.setBounds(120, 383, 109, 34);
		mainPane.add(lblNewLabel_1_1_2_3);
		
		JLabel lblNewLabel_1_1_2_4 = new JLabel("\uC2E4\uC2DC\uAC04 \uCC44\uD305\uC744 \uD560 \uC218 \uC788\uC5B4 \uD310\uB9E4\uC790\uC640 \uAD6C\uB9E4\uC790\uAC00 \uB300\uD654\uB97C \uB098\uB20C \uC218 \uC788\uC5B4\uC694\r\n");
		lblNewLabel_1_1_2_4.setFont(new Font("���ְ��", Font.PLAIN, 15));
		lblNewLabel_1_1_2_4.setBounds(271, 389, 566, 22);
		mainPane.add(lblNewLabel_1_1_2_4);
		
		JLabel lblNewLabel_1_1_2_5 = new JLabel("6. \uB9AC\uBDF0\uD558\uAE30");
		lblNewLabel_1_1_2_5.setForeground(new Color(0, 102, 255));
		lblNewLabel_1_1_2_5.setFont(new Font("���ְ��", Font.PLAIN, 17));
		lblNewLabel_1_1_2_5.setBounds(120, 523, 109, 34);
		mainPane.add(lblNewLabel_1_1_2_5);
		
		JLabel lblNewLabel_1_1_2_6 = new JLabel("\uD310\uB9E4\uC790\uC758 \uC544\uC774\uB514\uB97C \uAC80\uC0C9\uD558\uBA74 \uD310\uB9E4\uC790\uC5D0 \uB300\uD55C \uB9AC\uBDF0\uB97C \uC870\uD68C\uD560 \uC218 \uC788\uC5B4\uC694! ");
		lblNewLabel_1_1_2_6.setFont(new Font("���ְ��", Font.PLAIN, 15));
		lblNewLabel_1_1_2_6.setBounds(271, 523, 601, 34);
		mainPane.add(lblNewLabel_1_1_2_6);
		
		JLabel lblNewLabel_2 = new JLabel("\uAD6C\uB9E4 \uBC0F \uD310\uB9E4\uD560 \uC218 \uC788\uB294 \uBAA8\uB4E0 \uAC8C\uC2DC\uBB3C\uC744 \uAC80\uC0C9\uD560 \uC218 \uC788\uC5B4\uC694 ");
		lblNewLabel_2.setFont(new Font("���ְ��", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(271, 183, 367, 15);
		mainPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("\uCC3E\uACE0 \uC2F6\uC740 \uC0C1\uD488\uBA85\uC744 \uC785\uB825\uD574\uC11C \uCC3E\uC744 \uC218 \uC788\uC5B4\uC694");
		lblNewLabel_2_1.setFont(new Font("���ְ��", Font.PLAIN, 15));
		lblNewLabel_2_1.setBounds(271, 213, 367, 15);
		mainPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_1_1_3_1 = new JLabel("\uC218\uC815\uC744 \uD558\uACE0 \uC2F6\uC740 \uAC8C\uC2DC\uBB3C\uBC88\uD638\uB97C \uAC80\uC0C9\uD558\uC5EC \uC218\uC815\uD558\uC138\uC694");
		lblNewLabel_1_1_3_1.setFont(new Font("���ְ��", Font.PLAIN, 15));
		lblNewLabel_1_1_3_1.setBounds(271, 278, 337, 22);
		mainPane.add(lblNewLabel_1_1_3_1);
		
		JLabel lblNewLabel_1_1_2_2_1 = new JLabel("\uC0AD\uC81C\uB97C \uD558\uACE0 \uC2F6\uC740 \uAC8C\uC2DC\uBB3C\uBC88\uD638\uB97C \uAC80\uC0C9\uD558\uC5EC \uC0AD\uC81C\uD558\uC138\uC694");
		lblNewLabel_1_1_2_2_1.setFont(new Font("���ְ��", Font.PLAIN, 15));
		lblNewLabel_1_1_2_2_1.setBounds(271, 351, 441, 22);
		mainPane.add(lblNewLabel_1_1_2_2_1);
		
		JLabel lblNewLabel_1_1_2_4_1 = new JLabel("\uAD6C\uB9E4\uC790 : \uAD6C\uB9E4\uD558\uACE0 \uC2F6\uC740 \uC0C1\uD488\uC758 \uAC8C\uC2DC\uBB3C \uBC88\uD638\uB97C \uC785\uB825\uD558\uC5EC \uD310\uB9E4\uC790\uC640 \uB300\uD654\uD560 \uC218 \uC788\uC5B4\uC694\r\n");
		lblNewLabel_1_1_2_4_1.setFont(new Font("���ְ��", Font.PLAIN, 15));
		lblNewLabel_1_1_2_4_1.setBounds(271, 421, 529, 22);
		mainPane.add(lblNewLabel_1_1_2_4_1);
		
		JLabel lblNewLabel_1_1_2_4_1_1 = new JLabel("\uD310\uB9E4\uC790 : \uB4F1\uB85D\uD55C \uAC8C\uC2DC\uBB3C \uBAA9\uB85D\uC744 \uD074\uB9AD\uD558\uBA74 \uAD6C\uB9E4\uC790\uC640 \uB300\uD654\uD560 \uC218 \uC788\uC5B4\uC694\r\n");
		lblNewLabel_1_1_2_4_1_1.setFont(new Font("���ְ��", Font.PLAIN, 15));
		lblNewLabel_1_1_2_4_1_1.setBounds(271, 453, 566, 22);
		mainPane.add(lblNewLabel_1_1_2_4_1_1);
		
		JLabel lblNewLabel_1_1_2_4_1_2 = new JLabel("\u203B\uD310\uB9E4\uC790\uC640 \uAD6C\uB9E4\uC790 \uBAA8\uB450 \uB85C\uADF8\uC778 \uC0C1\uD0DC\uC77C \uB54C\uB9CC \uCC44\uD305\uC774 \uAC00\uB2A5\uD574\uC694! \uC720\uC758\uD574\uC8FC\uC138\uC694! \u203B");
		lblNewLabel_1_1_2_4_1_2.setForeground(new Color(255, 0, 51));
		lblNewLabel_1_1_2_4_1_2.setFont(new Font("���ְ��", Font.PLAIN, 15));
		lblNewLabel_1_1_2_4_1_2.setBounds(271, 485, 566, 22);
		mainPane.add(lblNewLabel_1_1_2_4_1_2);
		
		JLabel lblNewLabel_1_1_2_5_1 = new JLabel("7. \uB9C8\uC774\uD398\uC774\uC9C0");
		lblNewLabel_1_1_2_5_1.setForeground(new Color(0, 102, 255));
		lblNewLabel_1_1_2_5_1.setFont(new Font("���ְ��", Font.PLAIN, 17));
		lblNewLabel_1_1_2_5_1.setBounds(120, 567, 109, 34);
		mainPane.add(lblNewLabel_1_1_2_5_1);
		
		JLabel lblNewLabel_1_1_2_6_1 = new JLabel("\uD68C\uC6D0\uAC00\uC785 \uC2DC \uB4F1\uB85D\uD55C \uAC1C\uC778\uC815\uBCF4\uB97C \uBCC0\uACBD\uD560 \uC218 \uC788\uC5B4\uC694 \r\n \uC544\uC774\uB514\uB97C \uC778\uC99D\uD558\uBA74 \uC218\uC815\uD560 \uC218 \uC788\uC5B4\uC694!");
		lblNewLabel_1_1_2_6_1.setFont(new Font("���ְ��", Font.PLAIN, 15));
		lblNewLabel_1_1_2_6_1.setBounds(271, 573, 601, 22);
		mainPane.add(lblNewLabel_1_1_2_6_1);

		northPane = new ImagePanel(new ImageIcon("C:\\dev\\se_workspace\\market\\images\\north.png").getImage()); //��ȭ

//		north_panel = new JPanel();
//		north_panel.setBackground(new Color(153, 204, 255));
		northPane.setBounds(0, 0, 1186, 130);
		getContentPane().add(northPane);
		northPane.setLayout(null);
		
		btnLogout = new JButton("�α׾ƿ�");
		btnLogout.setForeground(Color.WHITE);
		btnLogout.setBounds(1081, 10, 93, 25);
		northPane.add(btnLogout);
		btnLogout.setBackground(new Color(102, 153, 204));
		btnLogout.setFont(new Font("���ְ��", Font.BOLD, 14));

		btnMyPage = new JButton("\uB9C8\uC774 \uD398\uC774\uC9C0");
		btnMyPage.setForeground(Color.WHITE);
		btnMyPage.setFont(new Font("���ְ��", Font.BOLD, 14));
		btnMyPage.setBackground(new Color(102, 153, 204));
		btnMyPage.setBounds(962, 10, 107, 25);
		northPane.add(btnMyPage);

		JLabel welcome_member = new JLabel(vo.getId() + "��");
		welcome_member.setFont(new Font("���ְ��", Font.PLAIN, 13));
		welcome_member.setHorizontalAlignment(SwingConstants.RIGHT);
		welcome_member.setBounds(819, 10, 131, 25);
		northPane.add(welcome_member);

		setSize(1215, 780);

		Dimension fsize = getSize();
		Dimension scsize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) (scsize.getWidth() - fsize.getWidth()) / 2;
		int height = (int) (scsize.getHeight() - fsize.getHeight()) / 2;

//		mainPane.setPreferredSize(fsize);
//		jScrollPane = new JScrollPane(mainPane,   ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
//				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//		jScrollPane.setPreferredSize(new Dimension(600, 600));
//		frame.add(comp)
//	    frame.add(jScrollPane);
//		
		setLocation(width, height);
		setVisible(true);

		MarketMgmUIEvent eventObj = new MarketMgmUIEvent(this);
		addWindowListener(eventObj);
		btnReg.addActionListener(eventObj);
		btnSearch.addActionListener(eventObj);
		btnUpdate.addActionListener(eventObj);
		btnDelete.addActionListener(eventObj);
		btnChat.addActionListener(eventObj);
		btnLogout.addActionListener(eventObj);
		btnMyPage.addActionListener(eventObj);
		btnReview.addActionListener(eventObj);
	}// start method

	
	//������ ����
	public void serverConnect() {
		try {
			socket = new Socket("localhost",5777);
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			
			//ó������ �޽��� ����
			MessageVO msgVO = new MessageVO();
			msgVO.setName(vo.id);
			msgVO.setStatus(CONNECT);
			msgVO.setRoom_num(now_room);

			oos.writeObject(msgVO);
			 
			//������ ���� ���۵Ǵ� �޽����� ��� �����ϴ� ������ ��ü ����
			ClientThread ct = new ClientThread(ois,content,input,this);
			ct.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// �޴� �̵� ����
	public void resetPane() {
		showPane.setVisible(false);
		mainPane.setVisible(false);
		regPane.setVisible(false);
		searchPane.setVisible(false);
		updatePane.setVisible(false);
		deletePane.setVisible(false);
		chatPane.setVisible(false);
		myPagePane.setVisible(false);
		reviewPane.setVisible(false);
	}

	public void switchPane(int menu) {
		resetPane();
		switch (menu) {
		case 1:
			regPane.removeAll();
			regPane.setVisible(true);
			regPane.setBounds(200, 130, 1000, 650);
			break;
		case 2:
			searchPane.removeAll();
			searchPane.setVisible(true);
			searchPane.setBounds(200, 130, 1000, 650);
			break;
		case 3:
			updatePane.removeAll();
			updatePane.setVisible(true);
			updatePane.setBounds(200, 130, 1000, 650);
			break;
		case 4:
			deletePane.removeAll();
			deletePane.setVisible(true);
			deletePane.setBounds(200, 130, 1000, 650);
			break;
		case 5:
			chatPane.removeAll();
			chatPane.setVisible(true);
			chatPane.setBounds(200, 130, 1000, 650);
			break;
		case 6:
			myPagePane.removeAll();
			myPagePane.setVisible(true);
			myPagePane.setBounds(200, 130, 1000, 650);
			break;
		case 7:
			reviewPane.removeAll();
			reviewPane.setVisible(true);
			reviewPane.setBounds(200, 130, 1000, 650);
			break;
		}
	}// switchPane method

	// �޽����� �Է¹޾� JLabel �����ϰ� ��Ʈ�� �����Ͽ� ����
	public JLabel getMsg(String msg) {
		JLabel label = new JLabel(msg);
		label.setFont(font);
		return label;
	} 
	
	
	

	// �̺�Ʈ ó�� Ŭ����
	class MarketMgmUIEvent extends WindowAdapter implements ActionListener {
		// Field
		MarketMgmUI main;
		// Constructor
		public MarketMgmUIEvent() {
		}

		public MarketMgmUIEvent(MarketMgmUI main) {
			this.main = main;
		}

		// ������ �̺�Ʈ ó��
		public void windowClosing(WindowEvent we) {
			MessageVO msgVO = new MessageVO();
			msgVO.setName(vo.id);
			msgVO.setStatus(EXIT);
			try {
				oos.writeObject(msgVO);				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			system.login_state(vo,0); //���� �� login_state 0����
			system.server_state(vo,0);//���� �� server_state 0����
			system.dao.close();
			int result = JOptionPane.showConfirmDialog(null, main.getMsg("������ ���α׷��� ���� �Ͻðڽ��ϱ�?"));
			if (result == 0) {
				System.exit(0);
			}
		}

		public boolean login() {
			vo.setId(jtf_id.getText());
			vo.setPass(jtf_pass.getText());

			boolean result = system.loginCheck(vo.getId(), vo.getPass());

			if (result) {
				JOptionPane.showMessageDialog(null, "�α��ο� �����ϼ̽��ϴ�.");
			} else if (jtf_id.getText().equals("") || jtf_pass.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "���̵� �Ǵ� ��й�ȣ�� ���� �Է����ּ���.");
			} else {
				JOptionPane.showMessageDialog(null, "���̵�/��й�ȣ�� Ʋ�Ƚ��ϴ�. �ٽ��ѹ� Ȯ�����ּ���");
				jtf_id.setText(""); jtf_pass.setText("");
			}

			return result;
		}

		// �׼� �̺�Ʈ ó��
		public void actionPerformed(ActionEvent ae) {
			Object obj = ae.getSource();
			MarketChat mc=new MarketChat(main);

			if (btnLogin == obj || jtf_pass == obj) {
				if (login()) {
					system.login_state(vo,1);//�α��� �� login_state�� 1�� ����
					main.serverConnect();
					system.server_state(vo,1);
					if(system.SellCkeck(vo)){// �Ǹ� �Խù��� ������ true ������ false
						//now_room defualt ����
						ArrayList<String> list=main.system.chat_list(vo.id);
						now_room=Integer.parseInt(list.get(0));
						//server�� �����ϱ�
						//������ ���� �� server_state 1�� ����
						
					}
					start();
				}

			} else if (btnJoin == obj) {
				new MarketMgmJoin(main).join();
			} else if (btnReg == obj) {
				new MarketRegister(main).register();
			} else if (btnSearch == obj) {
				new MarketSearch(main, system.dao).search("show_all");	
			} else if (btnDelete == obj) {
				new MarketDelete(main).delete();
			} else if (btnUpdate == obj) {
				new MarketUpdate(main, main.system.dao).update();
			} else if (btnChat == obj) {
				new MarketChat(main).chat();
			} else if (btnReview == obj) {
				new MarketReview(main, system.dao).review();
			} else if (btnLogout == obj) {
				int result = JOptionPane.showConfirmDialog(null, main.getMsg("������ �α׾ƿ� �Ͻðڽ��ϱ�?"));
				if (result == 0) {
					system.login_state(vo,0); //�α׾ƿ� �� login_state 0����
					system.server_state(vo,0);//�α׾ƿ� �� server_state 0����
					MessageVO msgVO = new MessageVO();
					msgVO.setName(vo.id);
					msgVO.setStatus(EXIT);
					try {
						oos.writeObject(msgVO);				
					} catch (Exception e) {
						e.printStackTrace();
					}
					mainPane.setVisible(false);
					menuPane.setVisible(false);
					northPane.setVisible(false);
					resetPane();
					showMain();
				}
			} else if (btnMyPage == obj) {
				new MarketMyPage(main).myPage();
			}
		}
	}// event class
}

class ImagePanel extends JPanel {
	private Image img;

	public ImagePanel(Image img) {
		this.img = img;
		setSize(new Dimension(img.getWidth(null), img.getHeight(null)));
		setPreferredSize(new Dimension(img.getWidth(null), img.getHeight(null)));
		setLayout(null);
	}

	public int getWidth() {
		return img.getWidth(null);
	}

	public int getHeight() {
		return img.getHeight(null);
	}

	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}

}