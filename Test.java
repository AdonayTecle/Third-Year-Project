import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

public class Test {

    public static void main(String avg[]) throws IOException
    {
    	double[] inputs  = {12 , 4};
    	double[] weights = {0.5,-1};

    	double sum = 0;
    	for (int i = 0; i < inputs.length; i++) {
    	  sum += inputs[i]*weights[i];
    	}
    	
    	double output = activate(sum);
    	System.out.println(output);

    }

    
    public static double activate(double sum) {
    if (sum > 0) return 1;
    else return -1;

  }
//    public Test() throws IOException
//    {
//        BufferedImage img=ImageIO.read(new File("img/2_of_clubs.png"));
//        ImageIcon icon=new ImageIcon(img);
//        JFrame frame=new JFrame();
//        frame.setLayout(new FlowLayout());
//        frame.setSize(200,300);
//        JLabel lbl=new JLabel();
//        lbl.setIcon(icon);
//        frame.add(lbl);
//        frame.setVisible(true);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    }
}