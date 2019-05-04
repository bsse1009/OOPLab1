package server1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MyFileWriter {

    private String file = "person.txt";

    public MyFileWriter(String file)
    {
        this.file = file;
    }

    public void setFile(String file)
    {
        this.file = file;
    }

    public void writeToFile(String content,boolean flag)
    {
        BufferedWriter bw = null;
        FileWriter fw = null;
        try
        {
            fw = new FileWriter(file,flag);
            bw = new BufferedWriter(fw);
            bw.flush();
            bw.write(content);

            System.out.println("write done- "+ content);

            if(bw != null)
                bw.close();
            if(fw != null)
                fw.close();
        }

        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
