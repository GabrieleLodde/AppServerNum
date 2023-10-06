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
            
            //Avvio il server 
            System.out.println("Server avviato...");
            ServerSocket server = new ServerSocket(3000);

            //Attendo il client
            Socket s = server.accept();
            System.out.println("Un client si è connesso!");

            //Creo i "tubi"
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            
            //Chiedo all'utente un range di numeri da generare
            String numeroRange = in.readLine();
            int maxRange = 0;
            int generato = 0;

            //Controlli risposte client
            if(Integer.parseInt(numeroRange) == 1){    
                generato = (int) (Math.random()*100 + 1);
                maxRange = 100;
            }
            else if(Integer.parseInt(numeroRange) == 2){
                generato = (int) (Math.random()*300 + 1);
                maxRange = 300;
            }
            else if(Integer.parseInt(numeroRange) == 3){
                generato = (int) (Math.random()*500 + 1);
                maxRange = 500;
            }
            System.out.println("Numero generato " + generato);
            
            //Creo la variabile temporanea del numero ricevuto
            int numero;
            
            //Creo il contatore di tentativi
            int contatore = 0;

            do { 
                //Attendo il numero inviato dal client
                String numeroRicevuto = in.readLine();

                //Converto in stringa il numero ricevuto
                numero = Integer.parseInt(numeroRicevuto);
                
                //Incremento il numero di tentativi
                contatore++;
   
                //Controllo se il client ha indovinato
                if(numero > maxRange || numero < 0){
                    out.writeBytes("4" + "\n");
                }
                else if(numero < generato){
                    out.writeBytes("1" + "\n");
                }
                else if(numero > generato){
                    out.writeBytes("2" + "\n");
                }
            } while (numero != generato);
            
            //Il client ha indovinato
            out.writeBytes("3" + "\n");
            out.writeBytes(String.valueOf(contatore));
            
            System.out.println("Il numero è stato indovinato!");
            System.out.println("------------");
            System.out.println("Chiusura del server");

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