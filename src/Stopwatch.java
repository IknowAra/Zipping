import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import org.jfree.ui.RefineryUtilities;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.ImageIcon;

import javax.swing.JTextField;

public class Stopwatch extends JFrame {
	private JPanel contentPane;
	String startT;
	String endT;
	int jip;
	Timer tm;
	Timer tm2;
	int s = 0, m = 0, h = 0;
	private JTextField textbox1;
	private JTextField textbox2;
	private JTextField textbox3;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Stopwatch frame = new Stopwatch();
					frame.setVisible(true);
				} catch (Exception e) {
						e.printStackTrace();
				}
			}  
		});
	}

//Create the frame
	public Stopwatch() {
		setResizable(false);
		setTitle("StopWatch");//���� ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,350);
		setLocationRelativeTo(null);
		contentPane = new JPanel();//�г� ����
		contentPane.setBackground(new Color(182,207,228));//���� ����
		setContentPane(contentPane);
		contentPane.setLayout(null);
  
		//���� ��ư
		JButton btnStart = new JButton("Start");//��ư ����
		btnStart.setBackground(new Color(182,207,228));//���� ����
		btnStart.setForeground(Color.white);//���ڻ� ����
		btnStart.setFont(new Font("���� ���", Font.BOLD, 28));//���� ��Ʈ �� ũ�� ����
		btnStart.setFocusPainted(false);
		btnStart.setBorderPainted(false);
		//��ư ���콺���� �̺�Ʈ
		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnStart.setForeground(new Color(126,164,199));
				btnStart.setFont(new Font("���� ���", Font.BOLD, 30));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnStart.setForeground(Color.white);
				btnStart.setFont(new Font("���� ���", Font.BOLD, 28));
			}
		});
		//��ư Ŭ�� �� �̺�Ʈ
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tm2.stop();//�ð� ���� ����
				startT = methodFiles.now();
				tm.start();//�ð��� �귯���� ��
			}
		});
		btnStart.setBounds(118, 110, 120, 45);//��ġ ���� �� ũ�� ����
		contentPane.add(btnStart);//��ư �߰�
		 
		//���� �� ���� ��ư
		JButton butReset = new JButton("Reset");//��ư ����
		butReset.setBackground(new Color(182,207,228));//���� ����
		butReset.setForeground(Color.white);//���ڻ� ����
		butReset.setFont(new Font("���� ���", Font.BOLD, 28));//���� ��Ʈ �� ũ�� ����
		butReset.setFocusPainted(false);
		butReset.setBorderPainted(false);
		//��ư ���콺���� �̺�Ʈ
		butReset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				butReset.setForeground(new Color(126,164,199));
				butReset.setFont(new Font("���� ���", Font.BOLD, 30));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				butReset.setForeground(Color.white);
				butReset.setFont(new Font("���� ���", Font.BOLD, 28));
			}
		});
		//��ư Ŭ�� �� �̺�Ʈ
		butReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tm.stop(); //�ð��� ���߰� ��
				endT = methodFiles.now();
				jip = methodFiles.Jip(s, m, h);
				
				methodFiles.WrongData(startT, endT, jip);
				startT = null;
				tm2.start(); //�ð� ����
				dispose(); //���� â �ݱ�
				main(null); //�ٽ� ����
			}
		});
		butReset.setBounds(356, 110, 120, 45);//��ġ ���� �� ũ�� ����
		contentPane.add(butReset);//��ư �߰�
		
		//��(���� ǥ��)
		JLabel label_s = new JLabel("0");
		label_s.setForeground(Color.BLACK);//���ڻ� ����
		label_s.setHorizontalAlignment(SwingConstants.CENTER);//��� ����
		label_s.setFont(new Font("���� ���", Font.BOLD, 45));//��Ʈ �� ũ�� ����
		label_s.setBounds(470, 40, 60, 50);//��ġ ���� �� ũ�� ����
		contentPane.add(label_s);//�� �߰�
		
		//��(���� ǥ��)
		JLabel label_m = new JLabel("0");
		label_m.setForeground(Color.BLACK);//���ڻ� ����
		label_m.setHorizontalAlignment(SwingConstants.CENTER);//��� ����
		label_m.setFont(new Font("���� ���", Font.BOLD, 45));//��Ʈ �� ũ�� ����
		label_m.setBounds(266, 40, 60, 50);//��ġ ���� �� ũ�� ����
		contentPane.add(label_m);//�� �߰�
  
		//�ð�(���� ǥ��)
		JLabel label_h = new JLabel("0");
		label_h.setForeground(Color.BLACK);//���ڻ� ����
		label_h.setHorizontalAlignment(SwingConstants.CENTER);//��� ����
		label_h.setFont(new Font("���� ���", Font.BOLD, 45));//��Ʈ �� ũ�� ����
		label_h.setBounds(62, 40, 60, 50);//��ġ ���� �� ũ�� ����
		contentPane.add(label_h);//�� �߰�
		
		JLabel text1 = new JLabel(":");
		text1.setHorizontalAlignment(SwingConstants.CENTER);//��� ����
		text1.setFont(new Font("���� ���", Font.BOLD, 45));//��Ʈ �� ũ�� ����
		text1.setBounds(184, 40, 20, 50);//��ġ ���� �� ũ�� ����
		contentPane.add(text1);//�� �߰�
		
		JLabel text2 = new JLabel(":");
		text2.setHorizontalAlignment(SwingConstants.CENTER);//��� ����
		text2.setFont(new Font("���� ���", Font.BOLD, 45));//��Ʈ �� ũ�� ����
		text2.setBounds(388, 40, 20, 50);//��ġ ���� �� ũ�� ����
		contentPane.add(text2);//�� �߰�
		
		//��Ʈ ��ư(���� ���� �ð�, ���� ���� �ð� ���)
		JButton btnchart1 = new JButton();
		btnchart1.setIcon(new ImageIcon("chart.png"));//�׸� ����
		btnchart1.setBounds(545, 221, 35, 35);//��ġ ���� �� ũ�� ����
		contentPane.add(btnchart1);//��ư �߰�
		//��ư Ŭ���� �̺�Ʈ
		btnchart1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//���� ���߽ð� & ���� ���߽ð� ��� ��Ʈ�� �ҷ���
		        new chart("���� ���߽ð� & ���� ���߽ð� ���");
		        chart.main(null);
			}
		});
		
		//��Ʈ ��ư(Ƚ���� ���� �ð�)
		JButton btnchart2 = new JButton();
		btnchart2.setIcon(new ImageIcon("chart2.png"));//�׸� ����
		btnchart2.setBounds(545, 268, 35, 35);//��ġ ���� �� ũ�� ����
		contentPane.add(btnchart2);//��ư �߰�
		btnchart2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Ƚ���� ���߽ð� ��Ʈ�� �ҷ���
				new chart2("Ƚ���� ���߽ð�");
				chart2.main(null);
			}
		});
		
		if(methodFiles.fileExistence("Time")==true) {
			textbox1 = new JTextField(methodFiles.timeToString(methodFiles.getTimeattention(methodFiles.getTimeArray()-1)));
			textbox2 = new JTextField(methodFiles.timeToString(methodFiles.LongGip()));
			textbox3 = new JTextField(methodFiles.TodayAver());
		}else {
			textbox1 = new JTextField("ó�� ���̳���?");
			textbox2 = new JTextField("��, ������");
			textbox3 = new JTextField("������ �ð�!");
		}
		
		textbox1.setForeground(Color.BLACK);
		textbox1.setBackground(Color.WHITE);
		textbox1.setHorizontalAlignment(SwingConstants.CENTER);
		textbox1.setFont(new Font("���� ���", Font.BOLD, 20));
		textbox1.setEnabled(false);
		textbox1.setBounds(230, 177, 180, 35);
		contentPane.add(textbox1);
		textbox1.setColumns(10);
		
		
		textbox2.setForeground(Color.BLACK);
		textbox2.setBackground(Color.WHITE);
		textbox2.setHorizontalAlignment(SwingConstants.CENTER);
		textbox2.setFont(new Font("���� ���", Font.BOLD, 20));
		textbox2.setEnabled(false);
		textbox2.setColumns(10);
		textbox2.setBounds(230, 224, 180, 35);
		contentPane.add(textbox2);
		
		
		textbox3.setForeground(Color.BLACK);
		textbox3.setBackground(Color.WHITE);
		textbox3.setHorizontalAlignment(SwingConstants.CENTER);
		textbox3.setFont(new Font("���� ���", Font.BOLD, 20));
		textbox3.setEnabled(false);
		textbox3.setColumns(10);
		textbox3.setBounds(230, 268, 180, 35);
		contentPane.add(textbox3);
		
		JLabel Label1 = new JLabel("\uC774\uC804 \uC9D1\uC911\uC2DC\uAC04");
		Label1.setHorizontalAlignment(SwingConstants.CENTER);
		Label1.setFont(new Font("���� ���", Font.BOLD, 18));
		Label1.setBounds(30, 177, 170, 35);
		contentPane.add(Label1);
				
		JLabel Label2 = new JLabel("\uCD5C\uC7A5 \uC9D1\uC911\uC2DC\uAC04(\uB204\uC801)");
		Label2.setHorizontalAlignment(SwingConstants.CENTER);
		Label2.setFont(new Font("���� ���", Font.BOLD, 18));
		Label2.setBounds(30, 224, 170, 35);
		contentPane.add(Label2);
		
		JLabel Label3 = new JLabel("\uC624\uB298 \uC9D1\uC911\uC2DC\uAC04 \uD3C9\uADE0");
		Label3.setHorizontalAlignment(SwingConstants.CENTER);
		Label3.setFont(new Font("���� ���", Font.BOLD, 18));
		Label3.setBounds(30, 268, 170, 35);
		contentPane.add(Label3);
		
		JLabel lb1 = new JLabel("Day");
		lb1.setHorizontalAlignment(SwingConstants.CENTER);
		lb1.setFont(new Font("���� ���", Font.BOLD, 18));
		lb1.setBounds(470, 221, 70, 35);
		contentPane.add(lb1);
		
		JLabel lb2 = new JLabel("Count");
		lb2.setHorizontalAlignment(SwingConstants.CENTER);
		lb2.setFont(new Font("���� ���", Font.BOLD, 18));
		lb2.setBounds(470, 268, 70, 35);
		contentPane.add(lb2);

		//�ð��� �����Ŵ
		tm2 = new Timer(0,new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				s=0;
				m=0;
				h=0;
				label_s.setText(Integer.toString(s)); //0���� �ٲ�
				label_s.setText(Integer.toString(m)); //0���� �ٲ�
				label_s.setText(Integer.toString(h)); //0���� �ٲ�
				}
			});
		
		//�ð��� ����
		tm = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				s++;//�ʸ��� 1�� ����
				if(s==60) {
					s = 0;	m++;//s(��)�� 60�� �Ǹ� s(��)�� 0���� �����ϰ� m(��)�� ������Ŵ
					if(m==60) {
						m = 0;	s = 0;	h++;//m(��)�� 60�� �Ǹ� h(�ð�)�� ������Ŵ
						label_h.setText(Integer.toString(h));//���� ����
					}label_m.setText(Integer.toString(m));//���� ����
				}label_s.setText(Integer.toString(s));//���� ����
			}
		});			
	}
}