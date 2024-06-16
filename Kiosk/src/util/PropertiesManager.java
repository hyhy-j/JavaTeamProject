package util;

import javax.swing.*;
import java.io.BufferedReader;


import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.annotation.ElementType;
import java.util.Properties;

/*public class PropertiesManager {
   
   public ElementType[] propertiesImport(String fileName) throws Exception {

      try (FileInputStream fis = new FileInputStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader((fis),"UTF-8"));)
      {            
         Properties props = new Properties();
         props.load(br);
         
         JLabel kioskName = new JLabel("눈송월드 놀이기구 예약");
         String kioskNo = props.getProperty("kioskNo");
         String serverUrl = props.getProperty("serverUrl");
         String serverPort = props.getProperty("serverPort");
         
         ElementType[] outputProps = new ElementType[][4];
         
         outputProps[0] = kioskName;
         outputProps[1] = kioskNo;
         outputProps[2] = serverUrl;
         outputProps[3] = serverPort;
         return outputProps;
         
      }   catch(Exception e)    {throw e;}      
   }
}
*/

public class PropertiesManager {

   public String[] propertiesImport(String fileName) throws Exception {

      try (FileInputStream fis = new FileInputStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader((fis),"UTF-8"));)
      {
         Properties props = new Properties();
         props.load(br);

         String kioskName = " 눈송월드 놀이기구 예약";
         String kioskNo = props.getProperty("kioskNo");
         String serverUrl = props.getProperty("serverUrl");
         String serverPort = props.getProperty("serverPort");

         String[] outputProps = new String[4];

         outputProps[0] = kioskName;
         outputProps[1] = kioskNo;
         outputProps[2] = serverUrl;
         outputProps[3] = serverPort;
         return outputProps;

      }   catch(Exception e)    {throw e;}
   }
}
