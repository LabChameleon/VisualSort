import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Julian on 04.02.2015.
 */
public class ControllUI
{
    JFrame mainUI = new JFrame();
    JPanel mainUIPanel = new JPanel(null);

    startAnimation animation = new startAnimation();

    JLabel arrayLengthLabel = new JLabel();
    JTextField arrayLength = new JTextField(6);

    JLabel delayTimeLabel = new JLabel();
    JTextField delayTime = new JTextField(6);

    JButton startSorting = new JButton("Visualisieren!");

    String[] sorts = {"Insertionsort", "Bubblesort", "Selectionsort", "Quicksort" };
    JList selectSort = new JList(sorts);

    public ControllUI()
    {
        mainUI.setResizable(false);
        mainUI.setTitle("VisualSort");
        mainUI.addWindowListener(new TestWindowListener());
        init();

        mainUI.setLocationRelativeTo(null);
        mainUI.pack();
        mainUI.setVisible(true);
    }

    private void init()
    {
        mainUIPanel.setPreferredSize(new Dimension(470,82));

        arrayLengthLabel.setText("Anzahl der Blöcke: ");
        arrayLengthLabel.setBounds(5,21,200,10);
        mainUIPanel.add(arrayLengthLabel);

        arrayLength.setText("500");
        arrayLength.addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent e)
            {
                char c = e.getKeyChar();
                if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE) || arrayLength.getText().length() > 3)
                {
                    e.consume();
                }
            }
        });

        arrayLength.setBounds(160, 17, 35, 20);
        mainUIPanel.add(arrayLength);

        delayTimeLabel.setText("Verzögerungszeit (ms): ");
        delayTimeLabel.setBounds(5,45,200,15);
        mainUIPanel.add(delayTimeLabel);

        delayTime.setText("20");
        delayTime.addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent e)
            {
                char c = e.getKeyChar();
                if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE) || delayTime.getText().length() > 2)
                {
                    e.consume();
                }
            }
        });

        delayTime.setBounds(160, 43, 35, 20);
        mainUIPanel.add(delayTime);

        selectSort.setSelectedIndex(0);
        selectSort.setBounds(220,5,100,75);
        mainUIPanel.add(selectSort);

        startSorting.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(arrayLength.getText().isEmpty() == false) {
                    startAnimation tempAnimation = new startAnimation();
                    tempAnimation.execute();
                }
                else
                {
                    JOptionPane.showMessageDialog(mainUI,
                            "Anzahl der Zufalszahlen darf nicht unbestimmt sein!",
                            "Error",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        startSorting.setBounds(340, 25, 120, 30);
        mainUIPanel.add(startSorting);

        mainUI.add(mainUIPanel);
    }

    class TestWindowListener extends WindowAdapter
    {
        public void windowClosing(WindowEvent e)
        {
            e.getWindow().dispose();
            System.exit(0);
        }
    }

    class startAnimation extends SwingWorker
    {
        protected Integer doInBackground() throws Exception
        {
            startSorting.setEnabled(false);
            new SortAlgorithms(new AnimationFrame(Integer.parseInt(arrayLength.getText()), 600, this, startSorting), Integer.parseInt(arrayLength.getText()), selectSort.getSelectedValue().toString(), Integer.parseInt(delayTime.getText()));

            return 0;
        }
    }
}
