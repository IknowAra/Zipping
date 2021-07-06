import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class First extends JFrame {

	private static final String MouseEvent = null;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					First frame = new First();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public First() {
		setFont(new Font("맑은 고딕", Font.BOLD, 15));
		setTitle("ZIPPING");
		setSize(600,350);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//버튼 생성
		JButton btnStopwatch = new JButton("START >");
		btnStopwatch.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		btnStopwatch.setBackground(new Color(126,164,199));
		btnStopwatch.setBounds(410, 70, 120, 35);
		contentPane.add(btnStopwatch);
		btnStopwatch.setFocusPainted(false);
		btnStopwatch.setBorderPainted(false);

		//버튼에 마우스가 올라갔을 때
		btnStopwatch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnStopwatch.setForeground(new Color(126,164,199));
				btnStopwatch.setBackground(Color.white);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnStopwatch.setForeground(Color.white);
				btnStopwatch.setBackground(new Color(126,164,199));
			}
		});
		
		//버튼 클릭 시
		btnStopwatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//스톱워치 창을 불러옴
		        new Stopwatch();
		        Stopwatch.main(null);
		        dispose();
			}
		});
		
		
		
		Image imgFromIcon = new ImageIcon("1.jpg").getImage();  //ImageIcon을 Image로 변환.
		Image changedImage = imgFromIcon.getScaledInstance(600, 350, java.awt.Image.SCALE_SMOOTH);
		
		//이미지 삽입
		JLabel image = new JLabel("");
		image.setIcon(new ImageIcon(changedImage));
		image.setHorizontalAlignment(SwingConstants.CENTER);
		image.setBounds(0, 0, 600, 350);
		contentPane.add(image);
	}
	
}