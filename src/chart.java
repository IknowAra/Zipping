import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

public class chart extends JFrame {

	private static final long serialVersionUID = 1L;

	public chart(String title) {
		super(title);

		// 차트 그림 작성
		CategoryPlot plot = new CategoryPlot();

		// 꺽은선 그래프(일일 집중시간 평균)
		CategoryItemRenderer lineRenderer = new LineAndShapeRenderer();
		plot.setDataset(0, createDataset());
		plot.setRenderer(0, lineRenderer);

		// 막대 그래프(일일 집중시간)
		CategoryItemRenderer baRenderer = new BarRenderer();
		plot.setDataset(1, createDataset2());
		plot.setRenderer(1, baRenderer);
		baRenderer.setSeriesOutlineStroke(0, null);
		((BarRenderer) baRenderer).setShadowVisible(false);

		// Set Axis
		plot.setDomainAxis(new CategoryAxis("날짜"));
		plot.setRangeAxis(new NumberAxis("시간(초)"));
		
		
		JFreeChart chart = new JFreeChart(plot);
		// chart.setTitle("일일 집중시간 & 일일 집중시간 평균");
		
		
		ChartPanel panel = new ChartPanel(chart);
		setContentPane(panel);
	}

	private DefaultCategoryDataset createDataset() {// line data

		if (methodFiles.fileExistence("Time") == false) {
			JOptionPane.showMessageDialog(null, "아직 기록된 데이터가 없습니다");
			return null;
		}
		String time1 = "일일 집중시간 평균";
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for (int i = 0; i < methodFiles.getDateArray(); i++) {
			dataset.addValue(methodFiles.getDateAver(i), time1, methodFiles.getDateDay(i));
		}

		return dataset;
	}

	private DefaultCategoryDataset createDataset2() {// bar data

		if (methodFiles.fileExistence("Time") == false) {
			JOptionPane.showMessageDialog(null, "아직 기록된 데이터가 없습니다");
			return null;
		}

		String time2 = "일일 집중시간";
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		for (int i = 0; i < methodFiles.getDateArray(); i++) {
			dataset.addValue(methodFiles.getDateJip(i), time2, methodFiles.getDateDay(i));
		}

		return dataset;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			chart example = new chart("일일 집중시간 & 일일 집중시간 평균");
			example.setSize(800, 600);
			example.setLocationRelativeTo(null);
			example.setVisible(true);
		});
	}
}