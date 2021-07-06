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

		// ��Ʈ �׸� �ۼ�
		CategoryPlot plot = new CategoryPlot();

		// ������ �׷���(���� ���߽ð� ���)
		CategoryItemRenderer lineRenderer = new LineAndShapeRenderer();
		plot.setDataset(0, createDataset());
		plot.setRenderer(0, lineRenderer);

		// ���� �׷���(���� ���߽ð�)
		CategoryItemRenderer baRenderer = new BarRenderer();
		plot.setDataset(1, createDataset2());
		plot.setRenderer(1, baRenderer);
		baRenderer.setSeriesOutlineStroke(0, null);
		((BarRenderer) baRenderer).setShadowVisible(false);

		// Set Axis
		plot.setDomainAxis(new CategoryAxis("��¥"));
		plot.setRangeAxis(new NumberAxis("�ð�(��)"));
		
		
		JFreeChart chart = new JFreeChart(plot);
		// chart.setTitle("���� ���߽ð� & ���� ���߽ð� ���");
		
		
		ChartPanel panel = new ChartPanel(chart);
		setContentPane(panel);
	}

	private DefaultCategoryDataset createDataset() {// line data

		if (methodFiles.fileExistence("Time") == false) {
			JOptionPane.showMessageDialog(null, "���� ��ϵ� �����Ͱ� �����ϴ�");
			return null;
		}
		String time1 = "���� ���߽ð� ���";
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for (int i = 0; i < methodFiles.getDateArray(); i++) {
			dataset.addValue(methodFiles.getDateAver(i), time1, methodFiles.getDateDay(i));
		}

		return dataset;
	}

	private DefaultCategoryDataset createDataset2() {// bar data

		if (methodFiles.fileExistence("Time") == false) {
			JOptionPane.showMessageDialog(null, "���� ��ϵ� �����Ͱ� �����ϴ�");
			return null;
		}

		String time2 = "���� ���߽ð�";
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		for (int i = 0; i < methodFiles.getDateArray(); i++) {
			dataset.addValue(methodFiles.getDateJip(i), time2, methodFiles.getDateDay(i));
		}

		return dataset;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			chart example = new chart("���� ���߽ð� & ���� ���߽ð� ���");
			example.setSize(800, 600);
			example.setLocationRelativeTo(null);
			example.setVisible(true);
		});
	}
}