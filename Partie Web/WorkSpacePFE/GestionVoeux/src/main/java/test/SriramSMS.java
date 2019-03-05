package teste;

import java.io.*;
import java.net.*;
import java.util.Enumeration;

import javax.comm.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
public class SriramSMS {
public void sendsms()   
{  
 Enumeration portList;
 CommPortIdentifier portId;
 SerialPort serialPort;
 OutputStream outputStream;
 String messageString = "";
 String userName="";
 String phoneNumber="";
 Thread thread = null;
 
  try
  {
     userName = "periodicUpdates.blogspot.com";
     phoneNumber = "50474932";
     messageString = " Hi." +userName+"welcome to this blog" ;
 
            String line1 = "AT+CMGF=1\r\n";
     String line2 = "AT+CMGS=" + phoneNumber + "\r\n"+messageString+ "\r\n";
     String line3 = "\u001A";
 
    // Here initialize the driver class
                  String driverName = "com.sun.comm.Win32Driver";
    CommDriver commdriver = (CommDriver)Class.forName(driverName).newInstance();
    commdriver.initialize();
 
    portList = CommPortIdentifier.getPortIdentifiers();
    while (portList.hasMoreElements())
     {       
      portId = (CommPortIdentifier) portList.nextElement();
      if(portId.getPortType() == CommPortIdentifier.PORT_SERIAL)   {
      System.out.println("SMS Sending........" + portId.getName());
      if((portId.getName().equals("COM1"))) {
       try
        {
         serialPort = (SerialPort) portId.open("SimpleWriteApp",10000);
         System.out.println("sms sending port--->"+serialPort);
         outputStream = serialPort.getOutputStream();
         serialPort.setSerialPortParams(230400,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
         outputStream.write(line1.getBytes());
         outputStream.write(line2.getBytes());
         outputStream.write(line3.getBytes());
         outputStream.flush();
         thread.sleep(5000);
         serialPort.close();
        } catch (PortInUseException portUse) {
         System.out.println("Port In Use " + portUse.toString());
        } catch (UnsupportedCommOperationException unsuppComOperExcep) {
         System.out.println("UnsupportedCommOperationException occured:::"+unsuppComOperExcep.toString());
        }  catch (IOException ioExcep) {
         System.out.println("Error occured while writing to output stream IOException" + ioExcep.toString());
        } catch(Exception excep) {
         System.out.println("Error writing message  with exception while closing " +excep.toString());
        }
     }
    }
   }
  } catch(Exception excep) {
   System.out.println("EXCEPTION raised while writing message@@" +excep.toString());
  }
 }
}