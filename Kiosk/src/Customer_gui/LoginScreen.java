package Customer_gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen {
    public LoginScreen() {
        JFrame f = new JFrame();
        f.setSize(460,370);
        f.setTitle("관리자 LOGIN");
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(null);


        BackgroundPanel bg = new BackgroundPanel();
        bg.setLayout(null);
        f.setContentPane(bg);


        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBounds(35, 35, 405, 285);
        panel.setLayout(null);
        panel.setOpaque(false);

        JPanel subpanel = new JPanel();
        subpanel.setLayout(new BorderLayout());
        subpanel.setBounds(55, 120, 270, 140);
        subpanel.setOpaque(false);
        subpanel.setLayout(null);

        JLabel imgPan = new JLabel();
        ImageIcon icon = new ImageIcon("nunsongworld.png");
        Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        imgPan.setIcon(new ImageIcon(img));
        imgPan.setBounds(150, 20, 100,100);
        //imgPan.setHorizontalAlignment(SwingConstants.CENTER);


        JTextField id = new JTextField(10);
        JLabel idlbl = new JLabel("ID");
        JPanel idP = new JPanel();
        idP.add(idlbl);
        idP.add(id);
        idP.setOpaque(false);
        idP.setBounds(0,30,165,50);

        JPasswordField pw = new JPasswordField(10);
        JLabel pwlbl = new JLabel("PW");
        JPanel pwP = new JPanel();
        pwP.add(pwlbl);
        pwP.add(pw);
        pwP.setOpaque(false);
        pwP.setBounds(0,70,165,50);

        JButton LgnBtn = new JButton("LOGIN");
        LgnBtn.setFont(new Font("굴림", Font.BOLD, 16));
        LgnBtn.setBounds(180, 35, 87, 67);

        JLabel sublgnPan = new JLabel();
        sublgnPan.setLayout(null);
        sublgnPan.add(idP);
        sublgnPan.add(pwP);
        sublgnPan.setBounds(0, 0, 240, 280);
        sublgnPan.setOpaque(false);

        JPanel lgnPan = new JPanel();
        lgnPan.setLayout(null);
        lgnPan.setOpaque(false);
        lgnPan.setBounds(0, 0, 170,145);
        lgnPan.add(sublgnPan);

        panel.add(imgPan);
        subpanel.add(lgnPan);
        subpanel.add(LgnBtn);
        panel.add(subpanel);



        LgnBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String username = id.getText();
                String password = new String(pw.getPassword());
                String account = username + "," + password;
                JOptionPane.showMessageDialog(null, "로그인이 완료되었습니다.");

                // manager 창 클래스 호출
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        try {
                            manager_gui.ManagerMain frame = new manager_gui.ManagerMain("CSV/friday.csv");
                            frame.setVisible(true);
                            f.dispose();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });


        f.add(panel);
        f.setVisible(true);

    }
}
