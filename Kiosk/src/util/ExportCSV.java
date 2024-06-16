package util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

import dto.Menu;

public class ExportCSV {

   public void menuExport(String fileName, List<Menu> list) {
      try (FileWriter fw = new FileWriter(fileName);
           BufferedWriter br = new BufferedWriter(fw)) {

         String outputText = "";
         for (Menu t : list) {
            outputText += t.toCSV() + "\r\n"; // 표 한줄 리턴하는 함수
         }

         br.write(outputText);

      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}
