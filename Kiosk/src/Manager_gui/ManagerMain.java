package manager_gui;

import customer_gui.KioskMain;
import Customer_gui.StartFrame;
import dto.Menu;
import net.ConnectionManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ManagerMain extends JFrame {

    private static final long serialVersionUID = 1L;

    private ConnectionManager cm;
    private JPanel contentPane;
    private List<Menu> menuList;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ManagerMain frame = new ManagerMain("CSV/friday.csv");
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public ManagerMain(String filePath) throws Exception {
        // ConnectionManager 초기화
        cm = new ConnectionManager(filePath);
        menuList = cm.getMenuList();

        setTitle("관리자 페이지 Administer Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(200, 200, 250));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(780, 100));
        contentPane.add(panel, BorderLayout.NORTH);
        JLabel imgLabel = new JLabel("");
        Image img = new ImageIcon("songE.png").getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        imgLabel.setIcon(new ImageIcon(img));
        imgLabel.setBounds(15, 10, 70, 70);
        imgLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // StartMain 클래스 호출
                new StartFrame();
                dispose();
            }
        });
        panel.setBackground(new Color(200,200,250)); ////////////////////////////
        panel.add(imgLabel);

        JLabel lblNewLabel = new JLabel("구역 선택");
        lblNewLabel.setPreferredSize(new Dimension(100, 50));
        lblNewLabel.setFont(new Font("굴림", Font.BOLD, 21));
        panel.add(lblNewLabel);

        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setPreferredSize(new Dimension(140, 30));
        comboBox.setFont(new Font("맑은 고딕", Font.BOLD, 18));

        List<String> category = new ArrayList<>();
        for (int i = 0; i < menuList.size(); i++) {
            if (i == 0) {
                category.add(menuList.get(i).getCategory());
            } else if (!menuList.get(i).getCategory().equals(menuList.get(i - 1).getCategory())) {
                category.add(menuList.get(i).getCategory());
            }
        }
        for (String t : category) {
            comboBox.addItem(t);
        }
        panel.add(comboBox);

        JLabel lblCurrentTime = new JLabel("");
        lblCurrentTime.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
        panel.add(lblCurrentTime);

        JPanel panel_2 = new JPanel();
        contentPane.add(panel_2, BorderLayout.SOUTH);
        panel_2.setLayout(new BorderLayout(0, 0));
        String help = "<html>위의 [파트 선택]에서 해당 놀이기구가 위치한 위치를 선택해주세요. 놀이기구 상황에 따라 수량이 초마다 갱신됩니다.<br>" +
                " 놀이기구 여석의 변동이 필요한 경우 [-], [+]을 클릭하시면 여석 수가 1개씩 변경됩니다. <br>기상상태 및 기타 상황으로 어트랙션 운영을 중단할 경우" +
                " [놀이기구 운휴 설정]을 클릭해주세요.</html>";
        JLabel lblNewLabel_1 = new JLabel(help);
        //lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 12));
        lblNewLabel_1.setForeground(new Color(50, 50, 50));
        panel_2.setBackground(new Color(200, 200, 250));
        lblNewLabel_1.setPreferredSize(new Dimension(750, 60));
        panel_2.add(lblNewLabel_1, BorderLayout.CENTER);

        JButton btnApplyChanges = new JButton("반영");
        btnApplyChanges.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        btnApplyChanges.setForeground(new Color(111, 21, 51));
        btnApplyChanges.addActionListener(ev -> {
            try {
                cm.setMenuList(menuList);
                refreshKioskMain();
                JOptionPane.showMessageDialog(null, "변경 사항이 반영되었습니다.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        panel_2.add(btnApplyChanges, BorderLayout.EAST);

        JPanel panel_1 = new JPanel();
        panel_1.setLayout(new GridLayout(0, 1, 10, 10));
        panel_1.setBackground(new Color(200 , 200, 250));
        contentPane.add(panel_1, BorderLayout.CENTER);

        // 메뉴 목록 업데이트 스레드
        new Thread(() -> {
            while (true) {
                try {
                    List<Menu> checkList = cm.getMenuList();
                    panel_1.removeAll();

                    String selected = (String) comboBox.getSelectedItem();
                    for (Menu t : checkList) {
                        if (selected.equals(t.getCategory())) {
                            JPanel linePanel = new JPanel();
                            linePanel.setLayout(new GridBagLayout());
                            JLabel menuName = new JLabel(t.getMenuName());
                            menuName.setPreferredSize(new Dimension(180, 30));
                            menuName.setFont(new Font("맑은 고딕", Font.BOLD, 20));

                            if (!t.isOnSale()) {
                                menuName.setForeground(new Color(90, 90,90)); // 판매 중지 시 빨간색으로 표시
                            }

                            JLabel quantity = new JLabel(" 여석 : " + t.getQuantity());
                            quantity.setPreferredSize(new Dimension(180, 30));
                            quantity.setFont(new Font("맑은 고딕", Font.BOLD, 20));
                            if (t.getQuantity() <= 10) {
                                quantity.setForeground(new Color(111, 21, 51));
                            }

                            JButton jbMinus = new JButton("-");
                            jbMinus.setFont(new Font("맑은 고딕", Font.BOLD, 20));
                            jbMinus.addActionListener(ev -> {
                                t.setQuantity(t.getQuantity() - 1);
                                updateMenuList(t);
                            });

                            JButton jbPlus = new JButton("+");
                            jbPlus.setFont(new Font("맑은 고딕", Font.BOLD, 20));
                            jbPlus.addActionListener(ev -> {
                                t.setQuantity(t.getQuantity() + 1);
                                updateMenuList(t);
                            });

                            JButton jbCancel = new JButton("놀이기구 운휴설정");
                            jbCancel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
                            jbCancel.addActionListener(ev -> {
                                int result = JOptionPane.showConfirmDialog(null, "해당 놀이기구의 판매 상태를 변경하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
                                if (result == JOptionPane.YES_OPTION) {
                                    cm.toggleMenuSaleStatus(t.getMenuName());
                                    JOptionPane.showMessageDialog(null, "놀이기구의 판매 상태가 변경되었습니다.");
                                    refreshKioskMain();
                                }
                            });

                            GridBagConstraints gbc = new GridBagConstraints();
                            gbc.gridx = 0;
                            gbc.gridy = 0;
                            gbc.anchor = GridBagConstraints.WEST;
                            gbc.insets.left = 10;
                            gbc.insets.top = 10;
                            linePanel.add(menuName, gbc);

                            gbc.gridx = 1;
                            linePanel.add(quantity, gbc);

                            gbc.gridx = 2;
                            linePanel.add(jbMinus, gbc);

                            gbc.gridx = 3;
                            linePanel.add(jbPlus, gbc);

                            gbc.gridx = 4;
                            linePanel.add(jbCancel, gbc);

                            linePanel.setBackground(new Color(111, 111, 211));
                            panel_1.add(linePanel);
                        }
                    }

                    panel_1.revalidate();
                    panel_1.repaint();
                    contentPane.revalidate();
                    contentPane.repaint();

                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // 현재 시간 업데이트 스레드
        new Thread(() -> {
            while (true) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String currentTime = sdf.format(new Date());
                lblCurrentTime.setText("현재 시각: " + currentTime);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void updateMenuList(Menu updatedMenu) {
        for (int i = 0; i < menuList.size(); i++) {
            if (menuList.get(i).getMenuName().equals(updatedMenu.getMenuName())) {
                menuList.set(i, updatedMenu);
                break;
            }
        }
    }

    private void refreshKioskMain() {
        for (Frame frame : Frame.getFrames()) {
            if (frame instanceof KioskMain) {
                frame.dispose();
            }
        }
        EventQueue.invokeLater(() -> {
            try {
                new KioskMain("CSV/friday.csv");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
