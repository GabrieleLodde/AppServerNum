package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class App 
{
    public static void main( String[] args )
    {
        try{
            int contatore = 0;
            
            //Genero il numero casuale 
            int generato = (int) (Math.random()*100 + 1);

            System.out.println(generato);
            //Avvio il server 
            System.out.println("Server avviato...");
            ServerSocket server = new ServerSocket(3000);

            //Attendo il client
            Socket s = server.accept();
            System.out.println("Un client si è connesso!");

            //Creo i "tubi"
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            
            //Attendo il numero inviato dal client
            String numeroRicevuto = in.readLine();

            //Converto in stringa il numero ricevuto
            Integer number = Integer.parseInt(numeroRicevuto);
            
            do {    
                contatore++;
                if(number == generato){
                    out.writeBytes("3" + "\n");
                    out.writeBytes(String.valueOf(contatore));
                }
                else if(number < generato){
                    out.writeBytes("1" + "\n");
                }
                else if(number > generato){
                    out.writeBytes("2" + "\n");
                }
            } while (number != generato);

            //Chiudo il server
            server.close();
            
            //Chiudo il socket
            s.close();

        } catch( Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server");
            System.exit(1);
        }
    }
}