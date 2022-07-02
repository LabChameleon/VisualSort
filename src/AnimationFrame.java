import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Julian on 04.02.2015.
 */
public class AnimationFrame extends JFrame
{
    AnimationPanel animationPanel = new AnimationPanel();
    JButton startButton;
    SwingWorker runnable;

    public AnimationFrame(int width, int height, SwingWorker runnable, JButton startButton)
    {
        this.runnable = runnable;
        this.startButton = startButton;

        if(width > 800)
        {
            this.setSize(width, height);
        }
        else
        {
            width = 800;
            this.setSize(width, height);
        }

        this.setLocationRelativeTo(null);
        this.addWindowListener(new TestWindowListener());
        this.setBackground(Color.BLACK);
        this.add(animationPanel);
        this.setVisible(true);
    }

    public void update(int a[], int comparisons, int permutations)
    {
        animationPanel.updateArray(a, comparisons, permutations);
        this.repaint();
    }

    class TestWindowListener extends WindowAdapter
    {
        public void windowClosing(WindowEvent e)
        {
            startButton.setEnabled(true);
            e.getWindow().dispose();
        }
    }

    class AnimationPanel extends JPanel
    {
        int a[];
        int comparisons;
        int permutations;

        public AnimationPanel()
        {
            this.setSize(800,800);
            this.setDoubleBuffered(true);
            this.setVisible(true);
        }

        public void updateArray(int a[], int comparisons, int permutations)
        {
            this.a = a;
            this.comparisons = comparisons;
            this.permutations = permutations;
        }

        public void paintComponent(Graphics g)
        {
            g.setColor(Color.GREEN);

            for(int i = 0; i < a.length; i++)
            {
                g.setColor(Color.GREEN);

                int posx = (int)(((double)(getWidth()) * ((double)i / (double)a.length)) + 0.999);
                int posy = (getHeight());
                int rectsizex = (int)(((double)(getWidth()) / (double)a.length) + 0.999);
                int rectsizey = (int)(((double)getHeight() - 50) * ((a[i] + 1) / (double)a.length));
                g.fillRect(posx, posy - rectsizey, rectsizex, rectsizey);
            }

            g.setColor(Color.WHITE);
            Font font = new Font("Serif", Font.PLAIN, 22);
            g.setFont(font);
            g.drawString("Vergleiche: " + Integer.toString(comparisons), 10, 25);
            g.drawString("Vertauschungen: " + Integer.toString(permutations), 10, 50);
        }
    }
}
