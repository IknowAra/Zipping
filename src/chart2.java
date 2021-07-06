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

		// ��Ʈ �׸� �ۼ�
		CategoryPlot plot = new CategoryPlot();

		// ������ �׷���(���� ���߽ð� ���)
		CategoryItemRenderer lineRenderer = new LineAndShapeRenderer();
		plot.setDataset(0, createDataset());
		plot.setRenderer(0, lineRenderer);

		// ������, ������ ����
		plot.setDomainAxis(new CategoryAxis("Ƚ��"));
		plot.setRangeAxis(new NumberAxis("�ð�(��)"));

		JFreeChart chart = new JFreeChart(plot);
		// chart.setTitle("Ƚ���� ���߽ð�");
		
		ChartPanel panel = new ChartPanel(chart);
		setContentPane(panel);
	}

	private DefaultCategoryDataset createDataset() {// line data
		if(methodFiles.fileExistence("Time")==false) {
			JOptionPane.showMessageDialog(null, "���� ��ϵ� �����Ͱ� �����ϴ�");
			return null;
		}
		

		String time1 = "Ƚ���� ���߽ð�";
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		for (int i = 0; i < methodFiles.getTimeArray(); i++) {
			dataset.addValue(methodFiles.getTimeattention(i), time1, i+1+"");
		}
		
		

		return dataset;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			chart2 example = new chart2("Ƚ���� ���߽ð�");
			example.setSize(800, 600);
			example.setLocationRelativeTo(null);
			example.setVisible(true);
		});
	}
}