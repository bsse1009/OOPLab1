package server1;

import java.io.BufferedReader;
import java.io.FileReader;

public class Replace {

    private String file ="academic.txt";

    public Replace(String file)
    {
        this.file = file;
    }

    public void replace(String temp, String academic)
    {
        BufferedReader br = null;
        FileReader fr = null;
        try
        {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String str,oldContent="";

            while((str = br.readLine()) != null )
            {
                oldContent += str + "\n";
            }

            if(br != null)
                br.close();

            if(fr != null)
                br.close();

            String newText = oldContent.replaceAll(temp, academic);
            MyFileWriter mfw =new MyFileWriter(file);
            mfw.writeToFile(newText,false);
        }

        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
