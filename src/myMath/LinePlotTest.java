package myMath;

import java.awt.Color;
import javax.swing.JFrame;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.plots.points.PointRenderer;
import de.erichseifert.gral.ui.InteractivePanel;
import myMath.Polynom;
import myMath.Polynom_able;

public class LinePlotTest extends JFrame {
//
    public LinePlotTest() {
    	
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);

        DataTable data = new DataTable(Double.class, Double.class);
        DataTable datakitzon = new DataTable(Double.class, Double.class);
        Polynom p2 = new Polynom("0.2x^4-1.5x^3+3.0x^2-x-5");
        Polynom_able divPoly=p2.derivative();
        System.out.println("the area is " + p2.areaUnder(-2, 6));
        double x1;
        double x2;
        double y;
        for (double x = -2.0; x <= 6.0; x=x+0.25) {
        	y=p2.f(x);
        	data.add(x, y);
        	x1=x;
        	x2=x+0.2;
        		if(divPoly.f(x1)*divPoly.f(x2)<0)
        		{
        			double x3 = divPoly.root(x1 , x2 , 0.00000000001);
        			datakitzon.add(x3, p2.f(x3));
        			System.out.println("the point is: ("+x3+","+ p2.f(x3)+")");
        		}          
        }
        
        XYPlot plot = new XYPlot(data);
        getContentPane().add(new InteractivePanel(plot));
        LineRenderer lines = new DefaultLineRenderer2D();
        plot.setLineRenderers(data, lines);
        Color color = new Color(0.0f, 0.0f, 0.0f);
        plot.add(datakitzon);
        plot.getPointRenderers(data).get(0).setColor(color.GREEN);
        plot.getPointRenderers(datakitzon).get(0).setColor(color.RED);
        plot.getLineRenderers(data).get(0).setColor(color.BLACK);
    }

    public static void main(String[] args) {
        LinePlotTest frame = new LinePlotTest();
        frame.setVisible(true);
    }
}
