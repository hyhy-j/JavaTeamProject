package util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import dto.Menu;

public class ImportCSV {

    public List<Menu> menuImport(String fileName) throws Exception {
        try (FileInputStream fis = new FileInputStream(fileName);
             BufferedReader br = new BufferedReader(new InputStreamReader((fis),"UTF8"))) {

            String line;
            List<Menu> list = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String category = data[0];
                String menuName = data[1];
                int price = Integer.parseInt(data[2]);
                int quantity = Integer.parseInt(data[3]);
                boolean isOnSale = Boolean.parseBoolean(data[4]);

                list.add(new Menu(category, menuName, price, quantity, isOnSale));
            }

            return list;
        } catch (Exception e) {
            throw e;
        }
    }
}
