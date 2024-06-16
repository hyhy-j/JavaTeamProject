package Customer_gui;

import java.util.List;
import java.util.Scanner;

import dto.Menu;
import net.ConnectionManager;
import dto.Purchase;

public class CustomerMain {

    public static void main(String[] args) {
        String filePath = "CSV/friday.csv"; // 실제 파일 경로로 변경해야 함
        
        try (Scanner scanner = new Scanner(System.in)) {
            ConnectionManager connectionManager = new ConnectionManager(filePath);
            boolean isRunning = true;

            while (isRunning) {
                // 메뉴 표시
                System.out.println("==== 고객 메뉴 구매 ====");
                List<Menu> menuList = connectionManager.getMenuList();
                
                // 메뉴 출력
                int index = 1;
                for (Menu menu : menuList) {
                    System.out.println(index++ + ". " + menu.getMenuName() + " - " + menu.getPrice() + "원");
                }

                // 사용자 선택 처리
                System.out.print("구매할 메뉴 번호를 입력하세요 (나가기: 0): ");
                int choice = Integer.parseInt(scanner.nextLine());
                
                if (choice == 0) {
                    System.out.println("고객 프로그램을 종료합니다.");
                    isRunning = false;
                    continue;
                }

                // 선택한 메뉴 구매 처리
                try {
                    boolean purchaseResult = connectionManager.purchaseOneMenu(choice - 1);
                    if (purchaseResult) {
                        System.out.println("티켓 구매가 완료되었습니다.");
                    } else {
                        System.out.println("티켓구매에 실패했습니다. 다시 시도해주세요.");
                    }
                } catch (Exception e) {
                    System.out.println("오류가 발생하여 티켓 구매에 실패했습니다.");
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            System.out.println("고객 프로그램 실행 중 오류가 발생했습니다.");
            e.printStackTrace();
        }
    }
}
