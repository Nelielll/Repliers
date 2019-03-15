import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class MyMap {

    char[][] map;
    int width;
    int height;

    HashMap<Character, Pair> directions = new HashMap<Character, Pair>();
    HashMap<Character, Integer> cost = new HashMap<Character, Integer>();

    Integer minCost;
    String minPath;

    public Info calcPath(Pair current, Pair destination) {
        minPath = "";
        minCost = Integer.MAX_VALUE;

        calculatePath(current, destination, "", 0, new HashSet<>());
        return new Info(minPath, minCost);
    }

    private void calculatePath(Pair current, Pair destination, String path, Integer currentCost, HashSet<Pair> visited) {
        if (currentCost > minCost)
            return;

        if (current.x < 0 || current.x >= height || current.y < 0 || current.y >= width)
            return;

        if (current.x == destination.x && current.y == destination.y) {
            if (currentCost < minCost) {
                minCost = currentCost;
                minPath = path;
            }
            return;
        }

        visited.add(current);

//        visited.forEach(System.out::print);

        System.out.println("Current " + current.x + " " + current.y);

        Pair newPair = move(current, 'U');

        int cost = getCost(newPair);

        char last = 'A';
        if (path.length() != 0)
            last = path.toCharArray()[path.length() - 1];

        if (cost != Integer.MAX_VALUE && last != 'D' && !visited.contains(newPair)) {
            calculatePath(newPair, destination, path + 'U', currentCost + cost, visited);
        }

        newPair = move(current, 'D');

        cost = getCost(newPair);

        if (cost != Integer.MAX_VALUE && last != 'U' && !visited.contains(newPair)) {
            calculatePath(newPair, destination, path + 'D', currentCost + cost, visited);
        }

        newPair = move(current, 'L');

        cost = getCost(newPair);

        if (cost != Integer.MAX_VALUE && last != 'R' && !visited.contains(newPair)) {
            calculatePath(newPair, destination, path + 'L', currentCost + cost, visited);
        }

        newPair = move(current, 'R');

        cost = getCost(newPair);

        if (cost != Integer.MAX_VALUE && last != 'L' && !visited.contains(newPair)) {
            calculatePath(newPair, destination, path + 'R', currentCost + cost, visited);
        }
    }

    public Integer getCost(Pair p) {
        if (p.x < 0 || p.x >= height || p.y < 0 || p.y >= width)
            return Integer.MAX_VALUE;
        return cost.get(map[p.x][p.y]);
    }

    public MyMap(char[][] map) {
        this.map = map;
        directions.put('U', new Pair(-1, 0));
        directions.put('R', new Pair(0, 1));
        directions.put('D', new Pair(1, 0));
        directions.put('L', new Pair(0, -1));

        cost.put('#', Integer.MAX_VALUE);
        cost.put('~', 800);
        cost.put('*', 200);
        cost.put('+', 150);
        cost.put('X', 120);
        cost.put('_', 100);
        cost.put('H', 70);
        cost.put('T', 50);

        height = map.length;
        width = map[0].length;

    }

    public Pair move(Pair pairInitial, Character direction) {
        Pair p = directions.get(direction);
        return new Pair(pairInitial.x + p.x, pairInitial.y + p.y);
    }
}

class Pair {
    public int x;
    public int y;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return x == pair.x &&
                y == pair.y;
    }

    @Override
    public int hashCode() {

        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Pair(" +
                "x=" + x +
                ", y=" + y +
                ')';
    }

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Pair(Office office) {
        this.x = office.x;
        this.y = office.y;
    }

}

class Info {
    public String path;
    public int cost;

    public Info(String path, int cost) {
        this.path = path;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Info{" +
                "path='" + path + '\'' +
                ", cost=" + cost +
                '}';
    }
}




