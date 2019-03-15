import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int width, height, nrCustomerHeadquarters = 0, maxReplyOffices = 0;

        File file = new File("a.txt");
        String line;
        try {
            Scanner sc = new Scanner(file);

            line = sc.nextLine();
            String[] numbers = line.split(" ");

            //Reading first 4 numbers
            width = Integer.parseInt(numbers[0]);
            height = Integer.parseInt(numbers[1]);
            nrCustomerHeadquarters = Integer.parseInt(numbers[2]);
            maxReplyOffices = Integer.parseInt(numbers[3]);

            int[] x = new int[nrCustomerHeadquarters];
            int[] y = new int[nrCustomerHeadquarters];
            int[] rewards = new int[nrCustomerHeadquarters];


            int i = 0;
            // Reading Customer HeadQuarters
            while (sc.hasNextLine() && i < nrCustomerHeadquarters) {
                line = sc.nextLine();
                numbers = line.split(" ");
                x[i] = Integer.parseInt(numbers[0]);
                y[i] = Integer.parseInt(numbers[1]);
                rewards[i] = Integer.parseInt(numbers[2]);
            }
            // Reading Reply Offices
            while (sc.hasNextLine() && i < nrCustomerHeadquarters) {
                line = sc.nextLine();
                numbers = line.split(" ");
                x[i] = Integer.parseInt(numbers[0]);
                y[i] = Integer.parseInt(numbers[1]);
                rewards[i] = Integer.parseInt(numbers[2]);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
