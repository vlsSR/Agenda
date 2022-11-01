import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static File file;

    private static void makeDirs(){
        file = new File("D:\\files\\exercise1");

        if(file.mkdirs()) {
            System.out.println("Directories created properly");
        }
    }

    private static void makeFile(){
         file = new File("D:\\files\\exercise1\\agenda.txt");

        try {
            if(file.createNewFile()){
                System.out.println("File created properly");
            }
        } catch (IOException e) {
            System.err.println("Error:" +e.getMessage());
        }
    }

    private static void writeFile(String data){
        try {
            FileWriter writer = new FileWriter(file,true);
            if (file.length() == 0) {
                writer.write(data);
            }
            else{
                writer.write("\n"+data);
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Error: "+e);
        }
    }

    private static void showContacts() {

        String line;
        String mail;
        String phone;
        String name;
        int counter = 1;

        try {
            FileReader reader = new FileReader(file);
            BufferedReader buffered_reader = new BufferedReader(reader);

            while((line = buffered_reader.readLine()) != null) {
                String[] elements = line.split(":");
                name = elements[0];
                mail = elements[1];
                phone = elements[2];

                System.out.println("\nContact number: "+counter+"\nName: "+name+"\nEmail: "+mail+"\nPhone number: "+phone);
                counter++;
            }

        } catch (FileNotFoundException e) {
            System.err.println("Error, "+e.getMessage());
        } catch (IOException e) {
            System.err.println("Error, "+e.getMessage());
        }
    }

    private static String createContact(){

        String mail;
        String name;
        String phone;
        String data;
        Scanner sc = new Scanner(System.in);

        System.out.println("Name: ");
        name = sc.nextLine();
        System.out.println("Email: ");
        mail = sc.nextLine();
        System.out.println("Phone: ");
        phone = sc.nextLine();

        data = String.join(":",name,mail,phone);

        return data;
    }

    private static void menu() {
        Scanner sc = new Scanner(System.in);

        boolean exit = false;
        int exit_option;
        int option;

        while(!exit) {
            System.out.println("\nWhat do you want to do: \n" +
                    "1. Add contact\n" +
                    "2. Show all contacts");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    writeFile(createContact());
                    break;
                case 2:
                    showContacts();
                    break;
                default:
                    System.out.println("Incorrect option");
                    break;
            }
            System.out.println("\nIf you want to exit type \"1\", if you want to do something else type \"2\"");
            exit_option = sc.nextInt();
            if(exit_option == 1) {
                exit = true;
            }
        }
    }


    public static void main(String[] args) {
        makeDirs();
        makeFile();
        menu();

    }
}