import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class SocketThread extends Thread {
    private Socket socket;
    private int nbMagique;
    private String joueurName;
    private List <Socket> sockets;

    public SocketThread(Socket socket, int nbMagique, List <Socket> sockets) {
        this.socket = socket;
        this.nbMagique = nbMagique;
        this.sockets = sockets;
    }
     private void prodcustMessage() throws IOException {
        for(Socket s:sockets){
         if(s!=socket){
             OutputStream os = s.getOutputStream();
             PrintWriter pw=new PrintWriter(new OutputStreamWriter(os),true);
             pw.println("Le joueur "+joueurName+" a trouver le nombre magique : "+nbMagique);
         }
         }
     }
    @Override
    public void run() {
        try {
            InputStream is = socket.getInputStream();
            OutputStream os=socket.getOutputStream();
            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            PrintWriter pw=new PrintWriter(new OutputStreamWriter(os),true);
            Scanner sc = new Scanner(System.in);
            int nbJoueur;
            pw.println("Bienvenue Entrez votre nom:");
            joueurName = br.readLine();
            pw.println("Entrez un nombre :");
            do{


                nbJoueur= Integer.parseInt(br.readLine());
                if(nbJoueur>nbMagique){
                    pw.println("Entrez un nombre inférieur");
                }else if(nbJoueur<nbMagique){
                    pw.println("Entrez un nombre supérieur");
                }else {
                    pw.println("Bravo  vous avez trouver le nombre magique : "+nbMagique);
                    prodcustMessage();
                }

            }while(nbJoueur!=nbMagique);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
