import com.sun.org.apache.regexp.internal.RE;

import java.io.*;
import java.util.*;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        String[] path;

        int width = 0, height = 0, nrCustomerHeadquarters = 0, maxReplyOffices = 0;

        File file = new File("a.txt");
        String line;
        Office[] offices = null;
        char[][] terrain = null;
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

            offices = new Office[nrCustomerHeadquarters];

            terrain = new char[height][width];
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
                    terrain[i][j] = chars[j];

                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        MyMap map = new MyMap(terrain);

        List<OfficeConn> officeConns = new ArrayList<>();

        for (int c = 0; c < nrCustomerHeadquarters; c++)
            for (int j = c + 1; j < nrCustomerHeadquarters; j++) {
                Info info = map.calcPath(new Pair(offices[c]), new Pair(offices[j]));
                if (info.cost != Integer.MAX_VALUE)
                    officeConns.add(new OfficeConn(offices[c], offices[j], info));
            }

        officeConns.sort(Comparator.comparingInt(officeConn -> officeConn.info.cost));
        HashMap<Office, Pair> reachedOffices = new HashMap<>();

        HashMap<Pair, HashSet<String>> result = new HashMap<>();

        for (OfficeConn conn : officeConns) {
            if (reachedOffices.get(conn.p1) != null && reachedOffices.get(conn.p2) == null) {
                Pair replyOffice = reachedOffices.get(conn.p1);
                Info info = map.calcPath(replyOffice, new Pair(conn.p1));
                result.get(replyOffice).add(info.path);
                continue;
            }
            if (reachedOffices.get(conn.p2) != null && reachedOffices.get(conn.p1) == null) {
                Pair replyOffice = reachedOffices.get(conn.p2);
                Info info = map.calcPath(replyOffice, new Pair(conn.p2));
                result.get(replyOffice).add(info.path);
                continue;
            }

            if (maxReplyOffices <= 0)
                break;
            maxReplyOffices--;

            char direction = conn.info.path.toCharArray()[0];

            Pair p = map.move(new Pair(conn.p1.x, conn.p1.y), direction);

            if (result.get(p) == null) {
                HashSet<String> hashSet = new HashSet<>();
                hashSet.add(conn.info.path.substring(1));
                result.put(p, hashSet);
            } else {
                result.get(p).add(conn.info.path.substring(1));
            }
            String dir = "";

            switch (direction) {
                case 'U':
                    dir = "D";
                    break;
                case 'D':
                    dir = "U";
                    break;
                case 'R':
                    dir = "L";
                    break;
                case 'L':
                    dir = "R";
                    break;
            }
            result.get(p).add(dir);

            reachedOffices.put(conn.p1, p);
            reachedOffices.put(conn.p2, p);

        }

        for (Map.Entry<Pair, HashSet<String>> entry : result.entrySet()) {
            Pair p = entry.getKey();
            for (String s : entry.getValue())
                System.out.println(p.x + " " + p.y + " " + s);
        }
    }


}

class OfficeConn {
    public Office p1;
    public Office p2;
    public Info info;

    public OfficeConn(Office p1, Office p2, Info info) {
        this.p1 = p1;
        this.p2 = p2;
        this.info = info;
    }

}


