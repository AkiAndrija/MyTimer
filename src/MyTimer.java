import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MyTimer extends JFrame {
    private static JPanel mainPanel;
    private Color selectedColor;

    static Timer t;

    static Label label;

    static JButton button;
    private static int miliS;
    private static int countdown = 10;


    public static void main(String[] args) {


        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

    public static void createAndShowGUI() {

        MyTimer frame = new MyTimer();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setTitle("Color");
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        label = new Label("10");
        frame.getContentPane().add(label);


        int cod = JOptionPane.showOptionDialog(null, "Choose option", "Option dialog", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Settings", "Close"}, null);


        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));


        JRadioButton onTimeRadioButton = new JRadioButton("On Time");
        JTextField onTimeTextField = new JTextField("HH:mm:ss");
        JRadioButton countdownRadioButton = new JRadioButton("Countdown (sec):");
        JTextField countdownTextField = new JTextField("0");

        JButton chooseColorButton = new JButton("Choose Color");
        JLabel colorLabel = new JLabel("rgb format");
        Color myRGBColor = new Color(200, 72, 55);
        colorLabel.setForeground(frame.selectedColor);

        JLabel speedLabel = new JLabel("speed");
        JComboBox<Integer> speedMenu = new JComboBox<>(new Integer[]{1000, 2000, 3000, 4000, 5000});
        speedMenu.setSelectedItem(miliS);

        JButton startCountDownButton = new JButton("Start Countdown");
        JButton stopButon = new JButton("Stop");


        chooseColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color selectedColor = JColorChooser.showDialog(frame, "Choose a Color", Color.WHITE);
                if (selectedColor != null) {


                    JFrame colorFrame = new JFrame("Color Window");
                    colorFrame.getContentPane().setBackground(selectedColor);
                    colorFrame.setSize(200, 200);
                    colorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


                    Timer t = new Timer(miliS, new ActionListener() {
                        private boolean changeColor = true;

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (changeColor) {
                                colorFrame.getContentPane().setBackground(Color.WHITE);
                            } else {
                                colorFrame.getContentPane().setBackground(selectedColor);
                            }
                            changeColor = !changeColor;
                        }
                    });
                    t.start();

                    colorFrame.setVisible(true);
                }
            }
        });

        startCountDownButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        countdown--;
                        label.setText("" + countdown);
                        if (countdown == 0) {
                            t.stop();
                        }
                    }
                });
                t.start();
            }
        });


        speedMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                miliS = (int) speedMenu.getSelectedItem();
            }
        });


        JPanel jPanel1 = new JPanel();
        jPanel1.add(onTimeRadioButton);
        jPanel1.add(onTimeTextField);
        jPanel1.add(countdownRadioButton);
        jPanel1.add(countdownTextField);

        JPanel jPanel2 = new JPanel();
        jPanel2.add(chooseColorButton);
        jPanel2.add(speedLabel);
        jPanel2.add(speedMenu);
        jPanel2.add(colorLabel);
        jPanel2.setLayout(new FlowLayout());

        JPanel jPanel3 = new JPanel();
        jPanel3.add(startCountDownButton);
        jPanel3.add(stopButon);


        jPanel.add(jPanel1);
        jPanel.add(jPanel2);
        jPanel.add(jPanel3);
        frame.add(jPanel);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        //frame.pack();
    }


}
