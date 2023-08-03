import java.io.*;
import java.net.*;
import java.util.*;
public class TCPEchoClient{
private static InetAddress host;
private static final int PORT=1234;
public static void main(String[]args){
try{
host=InetAddress.getLocalHost();
}
catch(UnknownHostException unEx){
System.out.println("Host ID not found!");
System.exit(1);
}
accessServer();
}
private static void accessServer(){
Socket link = null;
try{
link=new Socket(host, PORT); //Step1
Scanner input=new Scanner(link.getInputStream()); //Step2
PrintWriter output=new PrintWriter(link.getOutputStream(),true); //Step2
//set up a stream for keyboard entry
Scanner userEntry = new Scanner(System.in);
String message, response;
do{
System.out.println("Enter a message:");
message=userEntry.nextLine();
output.println(message); //Step3
response = input.nextLine(); //Step3
System.out.println("\nSERVER>" + response);
}while(!message.equals("***CLOSE***"));
}
catch(IOException ioEx){
ioEx.printStackTrace();
}
finally{
try{
    if (link != null){
System.out.println("\n Closing connection..*"); //Step4
link.close();
}
}
catch(IOException ioEx){
System.out.println("Unable to disconnect!");
System.exit(1);
}
}
}
}