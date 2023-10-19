package com.example;

import java.net.Socket;
import java.net.ServerSocket;

public class App 
{
    public static void main( String[] args )
    {
        try{
            //Avvio il server 
            System.out.println("Server avviato...");
            ServerSocket server = new ServerSocket(3000);

            while(true){
       
                //Attendo il client
                Socket s = server.accept();
                
                //Dichiaro un nuovo thread client
                ServerThread thread = new ServerThread(s);
                thread.start();
                System.out.println("" + s.getPort() + " si è connesso!");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server");
        }
    }
}