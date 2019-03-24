class Node {

    private double[] coordinates;
    private int nodeClass;
    private double alpha = 0.5;
    private double theta;

    Node(double[] coo){
        coordinates = coo;
    }

    Node(double[] coo, String nc){
        coordinates = coo;
        nodeClass = classStringToInt(nc);
        //System.out.println(nodeClass);
    }

    void setTheta(double theta) {
        this.theta = theta;
    }

    private int classStringToInt(String nc){
        if(nc.equals("0"))
            return 0;
        else if(nc.equals("1"))
            return 1;
        if (nc.equals("Iris-virginica"))
            return 0;
        else if (nc.equals("Iris-versicolor"))
            return 1;
        else {
            System.out.println("Wrong" + nc);
            System.err.println("Wrong input");
            return -1;
        }
    }

    double[] getCoordinates() {
        return coordinates;
    }

    int getNodeClass() {
        return nodeClass;
    }

    int tProduct(double[] xCoo){
        double res = 0;
        for (int i = 0; i < xCoo.length; i++) {
            res += xCoo[i] * coordinates[i];
            //System.out.println(res + " theta: " + theta + " coord: " + coordinates[i] + " xcoo: " + xCoo[i]);
        }
        //System.out.println();
        if (res >= theta)
            return 1;
        return 0;
    }

    void learn(double[] xCoo, int d){
        int y = tProduct(xCoo);

        if (d!=y) {
            double mod = (d - y);
            //System.out.println("mod" + mod);
            for (int i = 0; i < xCoo.length; i++)
                coordinates[i] += xCoo[i]*mod*alpha;

            theta = theta+alpha*mod*-1;
        }

    }

    void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    void displayCoo(){
        for (double i : coordinates) {
            System.out.print(i + " ");
        }
    }

}
