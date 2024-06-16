package machine;

import java.awt.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class VirtualTicketPrinter extends JDialog {
   
   static final long serialVersionUID = 1L;

   private final JPanel contentPanel = new JPanel();

   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      try {
         VirtualTicketPrinter vt = new VirtualTicketPrinter("어트랙션 위치", "어트랙션 이름", 10000, "눈송월드", 1);
         
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   /**
    * Create the dialog.
    */
   public VirtualTicketPrinter(String category, String menuName, int price, String kioskName, int kioskNo) {
      setType(Type.UTILITY);
      setTitle("Ticket");
      setFont(new Font("맑은 고딕", Font.PLAIN, 12));
      setBounds(100, 100, 216, 340);
      getContentPane().setLayout(new BorderLayout());
      contentPanel.setBackground(new Color(155, 156, 205));
      contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
      getContentPane().add(contentPanel, BorderLayout.CENTER);
      contentPanel.setLayout(null);
      {
         JLabel lblCategory = new JLabel(category);
         lblCategory.setHorizontalAlignment(SwingConstants.CENTER);
         lblCategory.setBounds(13, 17, 176, 30);
         lblCategory.setFont(new Font("맑은 고딕", Font.PLAIN, 17));
         contentPanel.add(lblCategory);
      }
      {
         JLabel lblmenuName = new JLabel(menuName);
         lblmenuName.setHorizontalAlignment(SwingConstants.CENTER);
         lblmenuName.setBounds(13, 43, 176, 34);
         lblmenuName.setFont(new Font("맑은 고딕", Font.BOLD, 25));
         contentPanel.add(lblmenuName);
      }
      {
         JLabel lblMent1 = new JLabel("==================");
         lblMent1.setHorizontalAlignment(SwingConstants.CENTER);
         lblMent1.setBounds(13, 70, 190, 20);
         lblMent1.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
         contentPanel.add(lblMent1);
      }
      {
         JLabel lblMent1 = new JLabel("Enjoy  Your  Ride  in");
         lblMent1.setHorizontalAlignment(SwingConstants.CENTER);
         lblMent1.setBounds(13, 85, 176, 20);
         lblMent1.setFont(new Font("맑은 고딕", Font.ITALIC, 13));
         contentPanel.add(lblMent1);
      }
      {
         JLabel lblMent2 = new JLabel("NoonSongWorld");
         lblMent2.setHorizontalAlignment(SwingConstants.CENTER);
         lblMent2.setBounds(13, 105, 176, 20);
         lblMent2.setFont(new Font("맑은 고딕", Font.ITALIC, 13));
         contentPanel.add(lblMent2);
      }
      {
         JLabel sImg = new JLabel();
         sImg.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
         sImg.setHorizontalAlignment(SwingConstants.CENTER);
         Image snow = new ImageIcon("snow.png").getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
         sImg.setIcon(new ImageIcon(snow));
         sImg.setBounds(86, 122, 30, 80);
         contentPanel.add(sImg);
      }
      {
         long time = System.currentTimeMillis(); 
         SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         String timeStr = dayTime.format(new Date(time));
         
         JLabel lblPrintedDate = new JLabel(timeStr);
         lblPrintedDate.setHorizontalAlignment(SwingConstants.CENTER);
         lblPrintedDate.setBounds(13, 128, 176, 21);
         lblPrintedDate.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
         contentPanel.add(lblPrintedDate);
      }
      {
         JLabel lblKioskName = new JLabel("❄\uFE0E‧˚₊✧*｡*̥❄\uFE0E‧˚₊✧*｡*̥❄\uFE0E‧˚₊✧*｡*̥❄\uFE0E");
         lblKioskName.setHorizontalAlignment(SwingConstants.RIGHT);
         lblKioskName.setBounds(13, 176, 170, 17);
         lblKioskName.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
         contentPanel.add(lblKioskName);
      }
      {
         JLabel barcodeImg = new JLabel();
         barcodeImg.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
         barcodeImg.setHorizontalAlignment(SwingConstants.CENTER);
         Image barcode = new ImageIcon("barcoded.png").getImage().getScaledInstance(150, 60, Image.SCALE_SMOOTH);
         barcodeImg.setIcon(new ImageIcon(barcode));
         barcodeImg.setBounds(13, 160, 176, 140);
         contentPanel.add(barcodeImg);
      }
      {
         JLabel barcodeImg = new JLabel();
         barcodeImg.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
         barcodeImg.setHorizontalAlignment(SwingConstants.CENTER);
         Image barcode = new ImageIcon("songE.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
         barcodeImg.setIcon(new ImageIcon(barcode));
         barcodeImg.setBounds(80, 265, 50, 60);
         contentPanel.add(barcodeImg);
      }

      setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
      setVisible(true);
   }

}