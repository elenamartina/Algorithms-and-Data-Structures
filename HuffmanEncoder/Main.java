import java.io.*;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws Exception {

        // new Scanner for reading and storing the user input in the variables fileName1 and fileName2;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the input file name: ");
        String fileName1 = input.next().trim();

        System.out.println("Enter the output file name: ");
        String fileName2 = input.next().trim();

        Scanner s = null;
        PrintStream print;

        // try and catch to deal with Exceptions if the file doesn't exist in the path given by the user;
        try {

            // create two file objects, one for the input, one for the output
            File file1 = new File (fileName1);
            File file2 = new File (fileName2);

            s = new Scanner(new BufferedReader(new FileReader(file1)));
            print = new PrintStream(file2);

            // length of the input file
            int length = (int)file1.length();




            Compressor compressor = new Compressor();

            String outputString = compressor.compress("");




            print.close();
        }

        catch (FileNotFoundException e) {
            System.out.println(e);
        }

        finally {
            if (s != null) {
                s.close();
            }
        }
    }

}
