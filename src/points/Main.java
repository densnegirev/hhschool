package points;

import java.io.*;
import java.util.*;

public class Main {

    public static double distance(Point p1, Point p2) {
        double xdist = p2.getX() - p1.getX();
        double ydist = p2.getY() - p1.getY();
        return Math.hypot(xdist, ydist);
    }

    public static double getMinimum(List<Point> points) {
        int size = points.size();
        if (size < 2) {
            return 0;
        }

        double dist = distance(points.get(0), points.get(1));
        if (size > 2) {
            for (int i = 0; i < size - 1; i++) {
                Point point = points.get(i);
                for (int j = i + 1; j < size; j++) {
                    double distance = distance(point, points.get(j));
                    if (distance < dist) {
                        dist = distance;
                    }
                }
            }
        }
        return dist;
    }

    public static void orderByX(List<Point> points) {
        Collections.sort(points, new Comparator<Point>() {
                    public int compare(Point point1, Point point2) {
                        return (int)(point1.getX() - point2.getX());
                    }
                }
        );
    }

    public static void orderByY(List<Point> points) {
        Collections.sort(points, new Comparator<Point>() {
                    public int compare(Point point1, Point point2) {
                        return (int)(point1.getY() - point2.getY());
                    }
                }
        );
    }

    public static double findMinimumDistance(List<Point> points) {
        List<Point> orderedByX = new ArrayList<>(points);
        orderByX(orderedByX);
        List<Point> orderedByY = new ArrayList<>(points);
        orderByY(orderedByY);
        return findMinimumDistance(orderedByX, orderedByY);
    }

    private static double findMinimumDistance(List<Point> orderedByX, List<Point> orderedByY) {
        int size = orderedByX.size();
        if (size <= 3) {
            return getMinimum(orderedByX);
        }

        int middle = size >>> 1;
        List<Point> left = orderedByX.subList(0, middle);
        List<Point> right = orderedByX.subList(middle, size);

        List<Point> tempList = new ArrayList<>(left);
        orderByY(tempList);
        double closestDistance = findMinimumDistance(left, tempList);

        tempList.clear();
        tempList.addAll(right);
        orderByY(tempList);
        double closestRight = findMinimumDistance(right, tempList);

        if (closestRight < closestDistance) {
            closestDistance = closestRight;
        }

        tempList.clear();

        double center = right.get(0).getX();

        for (Point point : orderedByY) {
            if (Math.abs(center - point.getX()) < closestDistance) {
                tempList.add(point);
            }
        }

        for (int i = 0; i < tempList.size() - 1; i++) {
            Point point = tempList.get(i);
            for (int j = i + 1; j < tempList.size(); j++) {
                Point point2 = tempList.get(j);
                if ((point2.getY() - point.getY()) >= closestDistance) {
                    break;
                }

                double distance = distance(point, point2);
                if (distance < closestDistance) {
                    closestDistance = distance;
                }
            }
        }
        return closestDistance;
    }

    public static void main(String[] args) throws IOException {
        String inputFileName;
        if (args.length == 0) {
            inputFileName = "input.txt";
        } else {
            inputFileName = args[0];
        }

        List<Point> points = new ArrayList<>();
        FileInputStream inputFileStream = new FileInputStream(inputFileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputFileStream));

        while (reader.ready()){
            String[] line = reader.readLine().split(" ");
            int x = Integer.parseInt(line[0]);
            int y = Integer.parseInt(line[1]);
            points.add(new Point(x, y));
        }

        double distance  = findMinimumDistance(points);
        System.out.println(distance);
    }
}
