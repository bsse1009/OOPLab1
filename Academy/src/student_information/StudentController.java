package student_information;


import java.io.*;

public class StudentController {

    public boolean login(String registrationNumber, String password)
    {
        try{
            FileInputStream fin = new FileInputStream("E:\\Study Material\\Lab Exam\\Academy\\src\\resources\\" + registrationNumber);
            ObjectInputStream objectInputStream = new ObjectInputStream(fin);

            Student student = (Student) objectInputStream.readObject();
            if(student.getPassword().equals(password))
                return true;
            else
                return false;

        }catch(Exception e)
        {
            return false;
        }
    }


        /*

NIMS University
1-2-1, Sengen, Tsukuba, Ibaraki 305-0047, JAPAN
CERTIFICATE OF ENROLLMENT
Date Issued ：January 15,20XX
Issue Number：XXXXXXXX
Name in Full ：John DOE
Date of Birth ：May XX, 19XX
Date of Admission ：September XX, 20XX
Department/Major：Materials Science and Engineering
Course ：Master Course
Date of Expected Graduation：June XX, 20XX
This is to certify that the above mentioned has been a full-time student_information of the
Dept. of Materials Science and Engineering, NIMS University since September
XX, 20XX.
Signature President’s Name, Professor
President
NIMS University

    */

    public String getCertificate(String registrationNumber) throws IOException, ClassNotFoundException {

        FileInputStream fileInputStream = new FileInputStream("E:\\Study Material\\Lab Exam\\Academy\\src\\resources\\" + registrationNumber);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        Student student = (Student) objectInputStream.readObject();

        String result = "University of Dhaka" + "\n" +
                "Dhaka, Bangladesh" + "\n" + "\n" + "\n" +
                "Name : " + student.getName() + "\n" +
                "Roll : " + student.getRoll() + "\n" +
                "Class : " + student.getCls() + "\n" +
                "Registration Number : " + student.getRegistrationNumber() + "\n" + "\n" + "\n" +
                "This is to certify that the above mentioned has been a full-time student_information of the\n" +
                "Tangail cadet school, Tangail";

        return result;
    }


    public boolean addStudent(Student student) throws IOException {

        if(student.getName().isEmpty() || student.getRoll().isEmpty() || student.getCls().isEmpty()  || student.getRegistrationNumber().isEmpty() || student.getPassword().isEmpty())
            return false;

        File folder = new File("src/resources/");
        File[] listOfFiles = folder.listFiles();

        for(File f: listOfFiles)
        {
            if(f.getName().equals(student.getRegistrationNumber()))
                return false;
        }

        FileOutputStream fout = new FileOutputStream("src/resources/" + student.getRegistrationNumber());
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fout);

        objectOutputStream.writeObject(student);

        fout.close();
        objectOutputStream.close();

        return true;
    }

    public boolean removeStudent(String registrationNumber) throws IOException {
        if(registrationNumber.isEmpty())
            return false;
        System.out.println("work");
        File file = new File("src/resources/" + registrationNumber);

        file.delete();
        System.out.println(file.getName() + " " + file.exists());

        if(file.exists())
            return false;
        else
            return true;
    }
}
