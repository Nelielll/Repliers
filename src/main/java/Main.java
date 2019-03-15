import model.Office;

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

            Office[] offices = new Office[nrCustomerHeadquarters];

            char[][] map = new char[height][width];
            int i = 0;
            // Reading Customer HeadQuarters
            while (sc.hasNextLine() && i < nrCustomerHeadquarters) {
                line = sc.nextLine();
                numbers = line.split(" ");

                offices[i] = new Office(Integer.parseInt(numbers[0]),
                        Integer.parseInt(numbers[1]),
                        Integer.parseInt(numbers[2]));
                i++;
            }

            i = 0;
            // Reading Reply Offices
            while (sc.hasNextLine() && i < height) {
                line = sc.nextLine();

                char[] chars = line.toCharArray();

                for (int j = 0; j < chars.length; j++)
                    map[i][j] = chars[j];

                i++;
            }

            System.out.println(width + " " + height + " " + nrCustomerHeadquarters + " " + maxReplyOffices);

            for (int s = 0; s < nrCustomerHeadquarters; s++)
                System.out.println(offices[s].x + " " + offices[s].y + " " + offices[s].rewards);

            for (int k = 0; k < height; k++) {
                for (int p = 0; p < width; p++)
                    System.out.print(map[k][p]);
                System.out.println();
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
