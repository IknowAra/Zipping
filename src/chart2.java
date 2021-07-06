import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;


import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

public class chart2 extends JFrame {

	private static final long serialVersionUID = 1L;

	public chart2(String title) {
		super(title);

		// 차트 그림 작성
		CategoryPlot plot = new CategoryPlot();

		// 꺽은선 그래프(일일 집중시간 평균)
		CategoryItemRenderer lineRenderer = new LineAndShapeRenderer();
		plot.setDataset(0, createDataset());
		plot.setRenderer(0, lineRenderer);

		// 가로축, 세로축 설정
		plot.setDomainAxis(new CategoryAxis("횟수"));
		plot.setRangeAxis(new NumberAxis("시간(초)"));

		JFreeChart chart = new JFreeChart(plot);
		// chart.setTitle("횟수별 집중시간");
		
		ChartPanel panel = new ChartPanel(chart);
		setContentPane(panel);
	}

	private DefaultCategoryDataset createDataset() {// line data
		if(methodFiles.fileExistence("Time")==false) {
			JOptionPane.showMessageDialog(null, "아직 기록된 데이터가 없습니다");
			return null;
		}
		

		String time1 = "횟수별 집중시간";
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		for (int i = 0; i < methodFiles.getTimeArray(); i++) {
			dataset.addValue(methodFiles.getTimeattention(i), time1, i+1+"");
		}
		
		

		return dataset;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			chart2 example = new chart2("횟수별 집중시간");
			example.setSize(800, 600);
			example.setLocationRelativeTo(null);
			example.setVisible(true);
		});
	}
}