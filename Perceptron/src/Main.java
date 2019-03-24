import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static int dimentions;
    public static void main(String[] args) throws IOException {
        File train = new File("C:\\Users\\Андрей\\eclipse-workspace\\Perceptron\\src\\example1\\train.txt");
        File test = new File("C:\\Users\\Андрей\\eclipse-workspace\\Perceptron\\src\\example1\\test.txt");
        List<Node> trainingData = new ArrayList<>();
        List<Node> testData = new ArrayList<>();

        Scanner sc = new Scanner(System.in);

        readAndSave(train,trainingData);
        readAndSave(test,testData);

        Random rand = new Random();

        double[] doubles = new double[dimentions];
        for (int i = 0; i < dimentions; i++) {
            doubles[i] = rand.nextDouble();
            System.out.println(doubles[i]);
        }
        Node node = new Node(doubles);
        System.out.println("Write alpha: ");
        double a = sc.nextDouble();
        node.setAlpha(a);
        System.out.println("Write theta: ");
        node.setTheta(sc.nextDouble());

        System.out.println("How many times iterate training file");
        int iter = sc.nextInt();
        for (int j = 0; j < iter; j++) {
            for (int i = 0; i < trainingData.size(); i++) {
                Node xTraining = trainingData.get(i);
                node.learn(xTraining.getCoordinates(), xTraining.getNodeClass());
            }
        }

        testing(testData,node);

        System.out.println("Do you want to write manual vector?");
        Scanner scanner = new Scanner(System.in);
        String ansv = scanner.nextLine();
        if (ansv.equals("yes")) {
            System.out.println("write your vector:");

            double[] user = new double[dimentions];

            for (int i = 0; i < dimentions; i++) {
                user[i] = sc.nextDouble();
            }
            System.out.println("Vector is predicted to belong to " + node.tProduct(user) + " class");
        }

    }
    private static void testing(List<Node> test, Node node1){
        int trueCount = 0, falseCount = 0, positiveTrueCount = 0, negativeTrueCount = 0, positiveFalseCount = 0, negativeFalseCount = 0;

        for (int i = 0; i < test.size(); i++) {
            Node xTest = test.get(i);
            int tprodtest = node1.tProduct(xTest.getCoordinates());
            System.out.println("Names: " + tprodtest);
            if (tprodtest == xTest.getNodeClass()) {
                trueCount++;//System.out.println("true");
                if (xTest.getNodeClass()==1)
                    positiveTrueCount++;
                else
                    negativeTrueCount++;
            }
            else {
                falseCount++;//System.out.println("false");
                if (xTest.getNodeClass()==1)
                    positiveFalseCount++;
                else
                    negativeFalseCount++;
            }
        }
        System.out.println("True: " + trueCount + " False: " + falseCount + " Positive true: " + positiveTrueCount + " Negative true: " + negativeTrueCount + " Positive false " + positiveFalseCount + " Negative false " + negativeFalseCount);
        System.out.println("P: " + (double)positiveTrueCount/(positiveTrueCount+negativeFalseCount));
        System.out.println("A: " + (double)trueCount/(positiveTrueCount+negativeTrueCount+positiveFalseCount+negativeFalseCount));
    }
    private static void readAndSave(File file, List<Node> list) throws IOException {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(file)));
        String line;
        while ((line = br.readLine()) != null){
            String[] tStr = line.split(",");
            dimentions = tStr.length-1;
            String name = tStr[dimentions];
            double[] input = new double[dimentions];
            for (int i = 0; i < dimentions; i++)
                input[i] = Double.parseDouble(tStr[i]);
            Node node = new Node(input,name);
            list.add(node);
        }
    }
}
