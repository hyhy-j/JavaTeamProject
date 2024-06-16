package customer_gui;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Customer_gui.StartFrame;
import machine.VirtualCashModule;
import machine.VirtualTicketPrinter;
import net.ConnectionManager;
import util.PropertiesManager;
import dto.Menu;
import dto.Purchase;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class KioskMain extends JFrame {

    static final long serialVersionUID = 1L;

    private ConnectionManager cm;

    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new KioskMain("CSV/friday.csv");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     * @throws Exception
     */
    public KioskMain(String filePath) {
        cm = new ConnectionManager(filePath);

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBackground(Color.BLUE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(176, 196, 222));
        panel.setBounds(12, 91, 560, 499);

        JScrollPane scrollPanel = new JScrollPane(panel); //
        scrollPanel.setPreferredSize(new Dimension(560, 499)); //
        scrollPanel.setBounds(12, 91, 560, 499); //
        scrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); //

        contentPane.add(scrollPanel);
        try {
            List<Menu> menuList = cm.getMenuList();
            long visibleItemCount = menuList.stream().filter(Menu::isOnSale).count();
            if (visibleItemCount <= 9) {
                panel.setLayout(new GridLayout(3, 3, 12, 12));
            } else {
                panel.setLayout(new GridLayout(3, 3, 12, 12));
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblSum = new JLabel(" ");
        lblSum.setHorizontalAlignment(SwingConstants.RIGHT);
        lblSum.setForeground(Color.WHITE);
        lblSum.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        lblSum.setBounds(649, 279, 138, 40);
        contentPane.add(lblSum);

        String[] props = null;
        PropertiesManager pm = new PropertiesManager();
        try {
            props = pm.propertiesImport("kiosk.properties");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        JLabel lblNewLabel = new JLabel();
        Image title = new ImageIcon("002.png").getImage().getScaledInstance(410, 55, Image.SCALE_SMOOTH);
        lblNewLabel.setIcon(new ImageIcon(title));
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setFont(new Font("굴림", Font.BOLD, 33));
        lblNewLabel.setBounds(96, 10, 542, 71);
        contentPane.add(lblNewLabel);

        JButton btnPayment = new JButton("결 제");
        btnPayment.setForeground(new Color(10, 10, 10));
        btnPayment.setFont(new Font("굴림", Font.PLAIN, 20));
        btnPayment.setBounds(582, 520, 100, 70);
        contentPane.add(btnPayment);

        JButton btnPayCancel = new JButton("취 소");
        btnPayCancel.setForeground(new Color(10, 10, 10));
        btnPayCancel.setFont(new Font("굴림", Font.PLAIN, 20));
        btnPayCancel.setBounds(687, 520, 100, 70);
        contentPane.add(btnPayCancel);

        String[] tableHeaderTitles = { "어트랙션", "수량", "금액" };
        DefaultTableModel model = new DefaultTableModel(tableHeaderTitles, 0);
        JTable table = new JTable(model);
        table.getColumnModel().getColumn(0).setPreferredWidth(200);

        JPanel tablePanel = new JPanel();
        tablePanel.setBounds(584, 91, 204, 178);
        contentPane.add(tablePanel);
        tablePanel.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        tablePanel.add(scrollPane);

        tablePanel.add(table.getTableHeader(), BorderLayout.NORTH);
        scrollPane.setViewportView(table);

        table.setFont(new Font("맑은 고딕", Font.PLAIN, 14));

        JLabel lblNewLabel_1 = new JLabel("");
        Image img = new ImageIcon("songE.png").getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        lblNewLabel_1.setIcon(new ImageIcon(img));
        lblNewLabel_1.setBounds(15, 10, 70, 70);
        lblNewLabel_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new StartFrame();
                dispose();
            }
        });
        contentPane.add(lblNewLabel_1);

        List<Menu> list = null;
        try {
            list = cm.getMenuList();
        } catch (Exception e1) {
            JOptionPane.showMessageDialog(null, "어트랙션을 불러오지 못했습니다.");
            e1.printStackTrace();
        }

        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).isOnSale()) {
                continue; // 판매 중이 아닌 항목은 건너뛰기
            }

            String tagStart = "<html><center><font size=5>";
            String tagMid = list.get(i).getMenuName() + "</font><br>" + "잔여석: " + list.get(i).getQuantity() + "석";

            if (list.get(i).getQuantity() <= 0) {
                tagMid += " <font color=red>(품절)</font>";
                String tagEnd = "</center></html>";
                JButton tempButton = new JButton(tagStart + tagMid + tagEnd);
                tempButton.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
                panel.add(tempButton);
                continue;
            }

            String tagMidEnd = "<br><img src=\"file:///Users/kimminkyung/Documents/MyProjects00/Kiosk/snowSmall.png\"/>";
            String tagEnd = "</center></html>";

            JButton tempButton = new JButton(tagStart + tagMid + tagMidEnd + tagEnd);
            tempButton.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
            panel.add(tempButton);
            final int CURRENT_INDEX = i;
            tempButton.addActionListener(al -> {
                try {
                    List<Menu> listToCheck = cm.getMenuList();

                    Object[] objArr = new Object[4];
                    objArr[0] = listToCheck.get(CURRENT_INDEX).getMenuName();
                    objArr[1] = 1;
                    objArr[2] = listToCheck.get(CURRENT_INDEX).getPrice();

                    boolean isFind = false;
                    if (table.getRowCount() == 0) {
                        model.addRow(objArr);
                    } else {
                        for (int index = 0; index < table.getRowCount(); index++) {
                            if (table.getValueAt(index, 0).toString().equals(listToCheck.get(CURRENT_INDEX).getMenuName())) {
                                isFind = true;
                                int value = (Integer) table.getValueAt(index, 1);
                                if (value >= 4) {
                                    JOptionPane.showMessageDialog(null, "5장 이상 구입하실 수 없습니다.");
                                    break;
                                } else if (value >= listToCheck.get(CURRENT_INDEX).getQuantity()) {
                                    JOptionPane.showMessageDialog(null, "여석이 없습니다.");
                                    break;
                                }
                                table.setValueAt(value + 1, index, 1);
                                break;
                            }
                        }
                        if (!isFind) model.addRow(objArr);
                    }

                    int sum = 0;
                    for (int index = 0; index < table.getRowCount(); index++) {
                        sum += (Integer) table.getValueAt(index, 1) * (Integer) table.getValueAt(index, 2);
                    }

                    lblSum.setText("￦" + new DecimalFormat().format(sum));

                    if (listToCheck.get(CURRENT_INDEX).getQuantity() == 0) {
                        setVisible(false);
                        new KioskMain(filePath);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        for (int i = list.size(); i < 9; i++) {
            panel.add(new JLabel(""));
        }

        JLabel lblNewLabel_2 = new JLabel("합계");
        lblNewLabel_2.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        lblNewLabel_2.setForeground(Color.WHITE);
        lblNewLabel_2.setBounds(584, 279, 70, 40);
        contentPane.add(lblNewLabel_2);

        JLabel lblKioskno = new JLabel("kioskNo: " + props[1]);
        lblKioskno.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == 'q') {
                    System.out.println("@종료 요청됨");
                    System.exit(0);
                }
            }
        });
        lblKioskno.setForeground(Color.LIGHT_GRAY);
        lblKioskno.setBounds(726, 10, 70, 23);
        contentPane.add(lblKioskno);

        JLabel banner = new JLabel("");
        banner.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("@배너 클릭됨");
                lblKioskno.requestFocus();
            }
        });
        banner.setBounds(584, 326, 204, 170);
        contentPane.add(banner);
        banner.setIcon(new ImageIcon("banner.png"));

        JLabel bg = new JLabel();
        bg.setIcon(new ImageIcon("background.png"));
        bg.setBounds(0, 0, 800, 600);
        contentPane.add(bg);

        btnPayCancel.addActionListener(ev -> {
            setVisible(false);
            new KioskMain(filePath);
        });

        final String[] PROPS = props;
        btnPayment.addActionListener(ev -> {
            List<Purchase> outputPurchaseList = new ArrayList<>();
            VirtualCashModule vc = new VirtualCashModule(
                    Integer.parseInt(lblSum.getText().replace("￦", "").replace(",", "")));
            if (!vc.isPurchaseSuccess()) {
                setVisible(false);
                new KioskMain(filePath);
            } else {
                try {
                    List<Menu> listToCheck = cm.getMenuList();
                    String category = "";
                    String menuName = "";
                    int price = 0;

                    for (int i = 0; i < table.getRowCount(); i++) {
                        int menuIndex = -1;
                        for (int j = 0; j < listToCheck.size(); j++) {
                            if (table.getValueAt(i, 0).equals(listToCheck.get(j).getMenuName())) {
                                menuIndex = j;
                                category = listToCheck.get(j).getCategory();
                                menuName = listToCheck.get(j).getMenuName();
                                price = listToCheck.get(j).getPrice();
                            }
                        }
                        outputPurchaseList.add(new Purchase(menuIndex, (Integer) table.getValueAt(i, 1)));
                        for (int k = 0; k < (Integer) table.getValueAt(i, 1); k++) {
                            new VirtualTicketPrinter(category, menuName, price, PROPS[0], Integer.parseInt(PROPS[1]));
                        }
                    }

                    boolean purchaseResult = cm.purchaseMultipleMenu(outputPurchaseList);
                    System.out.println("구매결과: " + purchaseResult);

                    setVisible(false);
                    new KioskMain(filePath);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        setUndecorated(true);
        setVisible(true);
    }
}
