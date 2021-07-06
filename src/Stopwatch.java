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
		setTitle("StopWatch");//제목 지정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,350);
		setLocationRelativeTo(null);
		contentPane = new JPanel();//패널 생성
		contentPane.setBackground(new Color(182,207,228));//배경색 지정
		setContentPane(contentPane);
		contentPane.setLayout(null);
  
		//시작 버튼
		JButton btnStart = new JButton("Start");//버튼 생성
		btnStart.setBackground(new Color(182,207,228));//배경색 지정
		btnStart.setForeground(Color.white);//글자색 지정
		btnStart.setFont(new Font("맑은 고딕", Font.BOLD, 28));//글자 폰트 및 크기 지정
		btnStart.setFocusPainted(false);
		btnStart.setBorderPainted(false);
		//버튼 마우스오버 이벤트
		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnStart.setForeground(new Color(126,164,199));
				btnStart.setFont(new Font("맑은 고딕", Font.BOLD, 30));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnStart.setForeground(Color.white);
				btnStart.setFont(new Font("맑은 고딕", Font.BOLD, 28));
			}
		});
		//버튼 클릭 시 이벤트
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tm2.stop();//시간 동결 중지
				startT = methodFiles.now();
				tm.start();//시간이 흘러가게 함
			}
		});
		btnStart.setBounds(118, 110, 120, 45);//위치 지정 및 크기 지정
		contentPane.add(btnStart);//버튼 추가
		 
		//리셋 및 정지 버튼
		JButton butReset = new JButton("Reset");//버튼 생성
		butReset.setBackground(new Color(182,207,228));//배경색 지정
		butReset.setForeground(Color.white);//글자색 지정
		butReset.setFont(new Font("맑은 고딕", Font.BOLD, 28));//글자 폰트 및 크기 지정
		butReset.setFocusPainted(false);
		butReset.setBorderPainted(false);
		//버튼 마우스오버 이벤트
		butReset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				butReset.setForeground(new Color(126,164,199));
				butReset.setFont(new Font("맑은 고딕", Font.BOLD, 30));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				butReset.setForeground(Color.white);
				butReset.setFont(new Font("맑은 고딕", Font.BOLD, 28));
			}
		});
		//버튼 클릭 시 이벤트
		butReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tm.stop(); //시간이 멈추게 함
				endT = methodFiles.now();
				jip = methodFiles.Jip(s, m, h);
				
				methodFiles.WrongData(startT, endT, jip);
				startT = null;
				tm2.start(); //시간 동결
				dispose(); //원래 창 닫기
				main(null); //다시 실행
			}
		});
		butReset.setBounds(356, 110, 120, 45);//위치 지정 및 크기 지정
		contentPane.add(butReset);//버튼 추가
		
		//초(글자 표시)
		JLabel label_s = new JLabel("0");
		label_s.setForeground(Color.BLACK);//글자색 지정
		label_s.setHorizontalAlignment(SwingConstants.CENTER);//가운데 정렬
		label_s.setFont(new Font("맑은 고딕", Font.BOLD, 45));//폰트 및 크기 지정
		label_s.setBounds(470, 40, 60, 50);//위치 지정 및 크기 지정
		contentPane.add(label_s);//라벨 추가
		
		//분(글자 표시)
		JLabel label_m = new JLabel("0");
		label_m.setForeground(Color.BLACK);//글자색 지정
		label_m.setHorizontalAlignment(SwingConstants.CENTER);//가운데 정렬
		label_m.setFont(new Font("맑은 고딕", Font.BOLD, 45));//폰트 및 크기 지정
		label_m.setBounds(266, 40, 60, 50);//위치 지정 및 크기 지정
		contentPane.add(label_m);//라벨 추가
  
		//시간(글자 표시)
		JLabel label_h = new JLabel("0");
		label_h.setForeground(Color.BLACK);//글자색 지정
		label_h.setHorizontalAlignment(SwingConstants.CENTER);//가운데 정렬
		label_h.setFont(new Font("맑은 고딕", Font.BOLD, 45));//폰트 및 크기 지정
		label_h.setBounds(62, 40, 60, 50);//위치 지정 및 크기 지정
		contentPane.add(label_h);//라벨 추가
		
		JLabel text1 = new JLabel(":");
		text1.setHorizontalAlignment(SwingConstants.CENTER);//가운데 정렬
		text1.setFont(new Font("맑은 고딕", Font.BOLD, 45));//폰트 및 크기 지정
		text1.setBounds(184, 40, 20, 50);//위치 지정 및 크기 지정
		contentPane.add(text1);//라벨 추가
		
		JLabel text2 = new JLabel(":");
		text2.setHorizontalAlignment(SwingConstants.CENTER);//가운데 정렬
		text2.setFont(new Font("맑은 고딕", Font.BOLD, 45));//폰트 및 크기 지정
		text2.setBounds(388, 40, 20, 50);//위치 지정 및 크기 지정
		contentPane.add(text2);//라벨 추가
		
		//차트 버튼(일일 공부 시간, 일일 집중 시간 평균)
		JButton btnchart1 = new JButton();
		btnchart1.setIcon(new ImageIcon("chart.png"));//그림 지정
		btnchart1.setBounds(545, 221, 35, 35);//위치 지정 및 크기 지정
		contentPane.add(btnchart1);//버튼 추가
		//버튼 클릭시 이벤트
		btnchart1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//일일 집중시간 & 일일 집중시간 평균 차트를 불러옴
		        new chart("일일 집중시간 & 일일 집중시간 평균");
		        chart.main(null);
			}
		});
		
		//차트 버튼(횟수별 집중 시간)
		JButton btnchart2 = new JButton();
		btnchart2.setIcon(new ImageIcon("chart2.png"));//그림 지정
		btnchart2.setBounds(545, 268, 35, 35);//위치 지정 및 크기 지정
		contentPane.add(btnchart2);//버튼 추가
		btnchart2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//횟수별 집중시간 차트를 불러옴
				new chart2("횟수별 집중시간");
				chart2.main(null);
			}
		});
		
		if(methodFiles.fileExistence("Time")==true) {
			textbox1 = new JTextField(methodFiles.timeToString(methodFiles.getTimeattention(methodFiles.getTimeArray()-1)));
			textbox2 = new JTextField(methodFiles.timeToString(methodFiles.LongGip()));
			textbox3 = new JTextField(methodFiles.TodayAver());
		}else {
			textbox1 = new JTextField("처음 오셨나요?");
			textbox2 = new JTextField("자, 지금은");
			textbox3 = new JTextField("집중할 시간!");
		}
		
		textbox1.setForeground(Color.BLACK);
		textbox1.setBackground(Color.WHITE);
		textbox1.setHorizontalAlignment(SwingConstants.CENTER);
		textbox1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		textbox1.setEnabled(false);
		textbox1.setBounds(230, 177, 180, 35);
		contentPane.add(textbox1);
		textbox1.setColumns(10);
		
		
		textbox2.setForeground(Color.BLACK);
		textbox2.setBackground(Color.WHITE);
		textbox2.setHorizontalAlignment(SwingConstants.CENTER);
		textbox2.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		textbox2.setEnabled(false);
		textbox2.setColumns(10);
		textbox2.setBounds(230, 224, 180, 35);
		contentPane.add(textbox2);
		
		
		textbox3.setForeground(Color.BLACK);
		textbox3.setBackground(Color.WHITE);
		textbox3.setHorizontalAlignment(SwingConstants.CENTER);
		textbox3.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		textbox3.setEnabled(false);
		textbox3.setColumns(10);
		textbox3.setBounds(230, 268, 180, 35);
		contentPane.add(textbox3);
		
		JLabel Label1 = new JLabel("\uC774\uC804 \uC9D1\uC911\uC2DC\uAC04");
		Label1.setHorizontalAlignment(SwingConstants.CENTER);
		Label1.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		Label1.setBounds(30, 177, 170, 35);
		contentPane.add(Label1);
				
		JLabel Label2 = new JLabel("\uCD5C\uC7A5 \uC9D1\uC911\uC2DC\uAC04(\uB204\uC801)");
		Label2.setHorizontalAlignment(SwingConstants.CENTER);
		Label2.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		Label2.setBounds(30, 224, 170, 35);
		contentPane.add(Label2);
		
		JLabel Label3 = new JLabel("\uC624\uB298 \uC9D1\uC911\uC2DC\uAC04 \uD3C9\uADE0");
		Label3.setHorizontalAlignment(SwingConstants.CENTER);
		Label3.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		Label3.setBounds(30, 268, 170, 35);
		contentPane.add(Label3);
		
		JLabel lb1 = new JLabel("Day");
		lb1.setHorizontalAlignment(SwingConstants.CENTER);
		lb1.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		lb1.setBounds(470, 221, 70, 35);
		contentPane.add(lb1);
		
		JLabel lb2 = new JLabel("Count");
		lb2.setHorizontalAlignment(SwingConstants.CENTER);
		lb2.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		lb2.setBounds(470, 268, 70, 35);
		contentPane.add(lb2);

		//시간을 동결시킴
		tm2 = new Timer(0,new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				s=0;
				m=0;
				h=0;
				label_s.setText(Integer.toString(s)); //0으로 바꿈
				label_s.setText(Integer.toString(m)); //0으로 바꿈
				label_s.setText(Integer.toString(h)); //0으로 바꿈
				}
			});
		
		//시간을 구함
		tm = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				s++;//초마다 1씩 증가
				if(s==60) {
					s = 0;	m++;//s(초)가 60이 되면 s(초)을 0으로 설정하고 m(분)을 증가시킴
					if(m==60) {
						m = 0;	s = 0;	h++;//m(분)이 60이 되면 h(시간)을 증가시킴
						label_h.setText(Integer.toString(h));//값을 넣음
					}label_m.setText(Integer.toString(m));//값을 넣음
				}label_s.setText(Integer.toString(s));//값을 넣음
			}
		});			
	}
}