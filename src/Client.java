import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost",9090);
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        PrintWriter pr = new PrintWriter(new OutputStreamWriter(os), true);
        Scanner sc = new Scanner(System.in);
        String msg;
        do{
            System.out.print("Moi :");
            msg = sc.nextLine();
            pr.println(msg);
            msg= br.readLine();
            System.out.println("Lui :"+msg);
        }while(!msg.equals("bye"));




    }
}
