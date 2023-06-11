package b07lab1;
import java.io.File;

public class Driver {
    public static void main(String [] args) throws Exception {
        // Polynomial p = new Polynomial(); System.out.println(p.evaluate(3));
        // double [] c1 = {6,0,0,5};
        // Polynomial p1 = new Polynomial(c1);
        // double [] c2 = {0,-2,0,0,-9};
        // Polynomial p2 = new Polynomial(c2); Polynomial s = p1.add(p2); System.out.println("s(0.1) = " + s.evaluate(0.1)); if(s.hasRoot(1))
        // System.out.println("1 is a root of s"); else
        // System.out.println("1 is not a root of s");
        
        // make first polynomial
        double [] coefs1 = {1, 3};
        int[] exps1 = {0, 1};
        Polynomial first = new Polynomial(coefs1, exps1);

        first.saveToFile("new.txt");

        // make second polynomial
        double [] coefs2 = {2 ,1};
        int[] exps2 = {2, 3};
        Polynomial second = new Polynomial(coefs2, exps2);
        
        second.saveToFile("new.txt");
        
        // chack add
        Polynomial added = first.add(second);
        added.saveToFile("new.txt");

        // check multiply
        Polynomial multiplied = first.multiply(second);
        multiplied.saveToFile("new.txt");

        // check file
        File file = new File("/Users/katherinepravdin/projects/CSCB07/b07lab1/poly.txt");
		Polynomial filePoly = new Polynomial(file);

        filePoly.saveToFile("new.txt");
    }
}