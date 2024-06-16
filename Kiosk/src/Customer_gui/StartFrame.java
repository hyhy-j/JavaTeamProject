package Customer_gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartFrame {
    public StartFrame() {
        JFrame f = new JFrame();
        f.setTitle("StartFrame");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocation(460,210);
        f.setLayout(null);

        // 배경 패널 생성 및 설정
        BackgroundPanel bg = new BackgroundPanel();
        bg.setLayout(null);
        f.setContentPane(bg);


        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBounds(170, 115, 500, 350);
        panel.setOpaque(false);

        JPanel subPan = new JPanel();
        subPan.setLayout(new BorderLayout());
        subPan.setBounds(190, 120, 500, 350);
        subPan.setOpaque(false);


        JButton admin = new JButton("관리자 페이지");
        admin.setForeground(Color.BLUE);
        admin.setBounds(10, 10, 120, 30);


        JLabel imageLabel = new JLabel();
        ImageIcon icon = new ImageIcon("nunsongworld.png");
        Image img = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(img));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);


        JLabel imageLabel2 = new JLabel();
        ImageIcon title = new ImageIcon("Title.png");
        Image tit = title.getImage().getScaledInstance(400, 50, Image.SCALE_SMOOTH);
        imageLabel2.setIcon(new ImageIcon(tit));
        imageLabel2.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel strt = new JPanel();
        strt.setOpaque(false);
        JButton strtbtn = new JButton("예약하기");
        strtbtn.setFont(new Font("굴림", Font.BOLD, 35));
        strtbtn.setForeground(new Color(123,123,250));
        strt.add(strtbtn);


        subPan.add(imageLabel2, BorderLayout.NORTH);
        subPan.add(imageLabel, BorderLayout.CENTER);
        panel.add(subPan, BorderLayout.CENTER);
        panel.add(strt, BorderLayout.SOUTH);
        bg.add(admin);
        bg.add(panel);


        strtbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // KioskMain 클래스 호출
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        try {
                            new customer_gui.KioskMain("CSV/friday.csv");
                            f.dispose();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        admin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // KioskMain 클래스 호출
                new LoginScreen();
                f.dispose();
            }
        });

        f.setVisible(true);
        f.setSize(800, 600);

    }
}

// 배경 패널 클래스
class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel() {
        try {
            // 이미지 로드
            backgroundImage = new ImageIcon("background.png").getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 이미지 그리기
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

