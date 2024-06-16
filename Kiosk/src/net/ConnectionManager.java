package net;

import java.util.ArrayList;
import java.util.List;
import dto.Menu;
import dto.Purchase;
import util.ExportCSV;
import util.ImportCSV;

public class ConnectionManager {
    private List<Menu> menuList = new ArrayList<>();
    private String filePath;

    public ConnectionManager(String filePath) {
        this.filePath = filePath;
        // 초기 데이터 로드 (지정된 CSV 파일에서 읽기)
        try {
            menuList = new ImportCSV().menuImport(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    synchronized public List<Menu> getMenuList() {
        return menuList;
    }

    synchronized public List<Menu> setMenuList(List<Menu> list) {
        menuList = list;
        new ExportCSV().menuExport(filePath, menuList);
        return menuList;
    }

    public boolean purchaseOneMenu(int menuIndex) {
        int currentQuantity = menuList.get(menuIndex).getQuantity();
        menuList.get(menuIndex).setQuantity(currentQuantity - 1);
        new ExportCSV().menuExport(filePath, menuList);
        return true;
    }

    public boolean purchaseSomeMenu(int menuIndex, int quantity) {
        int currentQuantity = menuList.get(menuIndex).getQuantity();
        menuList.get(menuIndex).setQuantity(currentQuantity - quantity);
        new ExportCSV().menuExport(filePath, menuList);
        return true;
    }

    public boolean purchaseMultipleMenu(List<Purchase> purchaseList) {
        for (Purchase t : purchaseList) {
            purchaseSomeMenu(t.getMenuIndex(), t.getQuantity());
        }
        return true;
    }

    public boolean toggleMenuSaleStatus(String menuName) {
        for (Menu menu : menuList) {
            if (menu.getMenuName().equals(menuName)) {
                menu.setOnSale(!menu.isOnSale());
                new ExportCSV().menuExport(filePath, menuList);
                return true;
            }
        }
        return false;
    }
}
