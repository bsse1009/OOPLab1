package server1;

import java.io.IOException;

public class Certificate {

    private String file = "person.txt";
    private String certificate = "";

    public void writeCertificate(String st) throws IOException {

        String cfile = "certificate-" + st + ".txt";
        SearchFile search_a = new SearchFile("academic.txt");
        SearchFile search_p = new SearchFile("person.txt");
        String str_a = search_a.SearchInFile(st);
        String str_p = search_p.SearchInFile(st);
        System.out.println(str_a);
        System.out.println(str_p);
        printAcademicInfo(cfile, str_a, str_p);
    }

    public void printAcademicInfo(String cfile, String academic, String person) throws IOException {

        MyFileWriter file = new MyFileWriter(cfile);
        String [] words_a = academic.split(",");
        String[] words_p = person.split(",");

        certificate += "                  Institute of Information Technology\n";
        certificate += "                         University of Dhaka\n\n";
        certificate += "This is to certify that ";
        certificate += words_p[0]+", son/daughter of "+words_p[1]+" and "+words_p[2]+"\n";
        certificate += "registration number: "+ words_a[0]+"\n";
        certificate += "session: "+ words_a[1]+"\n";
        certificate += "semester: "+ words_a[2]+"\n";
        certificate += "year: "+ words_a[3]+"\n";
        certificate += "class-roll: "+ words_a[5]+"\n";
        certificate += "was a regular student of software engineering , IIT, University of Dhaka. He/she obtained CGPA  ";
        certificate +=  words_a[4]+ " .\n";
        certificate += "As far I know, he/she did not take part in any activity subversive of the state or of discipline.\n\n";
        certificate += "I wish him/her every success in life.\n\n\n";
        certificate += "Dr. Md. Shariful Islam\n";
        certificate += "       (Director)\n";
        certificate += "Institute of Information Technology\n";
        certificate += "       University of Dhaka\n";

        file.writeToFile(certificate, false);
    }
}
