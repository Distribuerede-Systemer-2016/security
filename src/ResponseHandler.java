import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ResponseHandler implements Runnable {

  private Socket remoteSocket;

  public ResponseHandler(Socket remoteSocket) {
    this.remoteSocket = remoteSocket;
  }

  public void run() {

    try {
      System.out.println("Connection!");
      System.out.println("Thread count: " + Thread.activeCount());

      //Read from input stream (from client)
      BufferedReader inFromClient = new BufferedReader(new InputStreamReader(this.remoteSocket.getInputStream()));

      //Print request headers from client
      String str = ".";
      while (!str.equals("")) {
        str = inFromClient.readLine();
        //System.out.println(str);
      }


      //Create output stream (to client)
      PrintWriter outToClient = new PrintWriter(remoteSocket.getOutputStream());


      //Write response headers
      outToClient.println("HTTP/1.0 200 OK");
      outToClient.println("Content-Type: application/json");
      outToClient.println("Server: Hackerbot");
      outToClient.println("");


      outToClient.println("{\"hello\":\"world\"}");

      //Flush'n'close
      outToClient.flush();

      outToClient.close();
      this.remoteSocket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
