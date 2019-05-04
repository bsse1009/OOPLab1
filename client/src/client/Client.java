package client;

import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {

        System.out.println("client is trying to connect");

        Socket socket = new Socket("localhost",4569);

        DataInputStream din = new DataInputStream(socket.getInputStream());
        DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str1="",str2=" "; // str2 - reply from server, str1 - client message

        while(true)
        {
            str2 = din.readUTF();
            System.out.println("s : " + str2);

            str1 = br .readLine();
            dout.writeUTF(str1);

            if(str1.equals("1")) // login
            {
                str2 = din.readUTF();  // reg number
                System.out.println("s : " + str2);

                str1 = br .readLine(); //reg number
                dout.writeUTF(str1);

                str2 = din.readUTF(); // valid or not
                System.out.println("s : " + str2);

                if(str2.contains("invalid"))
                {
                    continue;
                }

                str2 = din.readUTF();  //account number
                System.out.println("s : " + str2);

                str1 = br .readLine(); //acc number
                dout.writeUTF(str1);

                str2 = din.readUTF();  //password
                System.out.println("s : " + str2);

                str1 = br .readLine();  // password
                dout.writeUTF(str1);

                while(true) {
                    str2 = din.readUTF();   //login sucess or not
                    System.out.println("s : " + str2);

                    if (str2.contains("failed")) {
                        break;
                    }

                    str1 = br.readLine();  // take choice
                    dout.writeUTF(str1);

                    if (str1.equals(("1"))) // get certificate
                    {
                        str2 = din.readUTF(); //print balance
                        System.out.println("s : " + str2);

                        str2 = din.readUTF(); //cut info
                        System.out.println("s : " + str2);

                        if (!str2.contains("enough")) {
                            str2 = din.readUTF(); //certificate
                            System.out.println("s : " + str2);
                        }


                    } else if (str1.equals("2"))  //academic info
                    {
                        for (int i = 0; i < 8; i++) {
                            str2 = din.readUTF(); //certificate
                            System.out.println("s : " + str2);
                        }
                    } else if (str1.equals("3") || str2.equals("4")) {
                        str2 = din.readUTF(); //amount:
                        System.out.println("s : " + str2);

                        str1 = br.readLine();  // give amount
                        dout.writeUTF(str1);

                        str2 = din.readUTF(); //done or not
                        System.out.println("s : " + str2);
                    } else if (str1.equals("5")) {
                        str2 = din.readUTF(); //balance
                        System.out.println("s : " + str2);
                    } else {
                        break;
                    }
                }

            }

            else if(str1.equals("2")) //signup
            {
                for(int i=0;i<3;i++)
                {
                    str2 = din.readUTF();   //account info
                    System.out.println("s : " + str2);

                    str1 = br .readLine(); //account info
                    dout.writeUTF(str1);
                }

                str2 = din.readUTF();   //success
                System.out.println("s : " + str2);

                str2 = din.readUTF();   //acc number
                System.out.println("s : " + str2);
            }

            else if (str1.equals("3"))
            {
                System.out.println("closing client");
                break;
            }
        }
    }
}
