package b07lab1;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
// import java.util.Arrays;
import java.util.Scanner;

public class Polynomial {
    double[] coefficients;
	int[] exponents;

    // constructor to set to 0
    public Polynomial(){
        this.coefficients = new double[0];
        this.exponents = new int[0];
    }

    // constructor that sets the coeffients
    public Polynomial(double[] newCoefficients, int[] newExponents){
		this.coefficients = newCoefficients;
		this.exponents = newExponents;
    }

	// constructor for files
	public Polynomial(File inputFile) throws FileNotFoundException {
		// get input
		Scanner input = new Scanner(inputFile);
		String polynomial = input.nextLine();
		input.close();

		// split up by possible operations
		String splitPolynomial[] = polynomial.split("[-\\+]");

		// make coeffcients and exponents arrays
		coefficients = new double[splitPolynomial.length];
		exponents = new int[splitPolynomial.length];

		// put in the proper values
		int counter = 0;
		String nums[];
		for(int i = 0; i < splitPolynomial.length; i++) {
			// split again by x ot separate coefiecent and exponent
			nums = splitPolynomial[i].split("x");

			// add first num into the coefficents, second into the exponents
			coefficients[counter] = Double.parseDouble(nums[0]);
			if(nums.length >= 2){
				exponents[counter] = Integer.parseInt(nums[1]);
			}
			else{
				exponents[counter] = Integer.parseInt("0");
			}
			counter++;
		}
	}

	// adds the polynomial the the method is called on to the polymonal in the brackets
	public Polynomial add(Polynomial second){
		// get the number of expoents in the final poly
		// first getting longest exponent in both arrays
		int biggestExp = 0;
		for(int i = 0; i < second.exponents.length; i++){
			if(biggestExp < second.exponents[i]){
				biggestExp = second.exponents[i];
			}
		}
		for(int i = 0; i < this.exponents.length; i++){
			if(biggestExp < this.exponents[i]){
				biggestExp = this.exponents[i];
			}
		}

		// arrays for coef of final result with 0's
		double[] curCoefs = new double[biggestExp + 1];
        int[] curExps = new int[biggestExp + 1];
		
		// insert the coefficients into the final array, adding both in the process
		for(int i = 0; i < this.exponents.length; i ++){
        	curCoefs[this.exponents[i]] = curCoefs[this.exponents[i]] + this.coefficients[i]; 
        }
        for(int i = 0; i < second.exponents.length; i ++){
        	curCoefs[second.exponents[i]] = curCoefs[second.exponents[i]] + second.coefficients[i]; 
        } 

		// find all the 0's in the new coef array after addition is done and remove them from the length
		int numZeros = 0;
		for(int i = 0; i < curCoefs.length; i++){
			if(curCoefs[i] == 0.0 || curCoefs[i] == 0){
				numZeros++;
			}
		}
		int finalLength = curCoefs.length - numZeros;

		// put in values for the exponents (those with zero coefs still included so we can compare later)
		for(int i = 0; i < biggestExp + 1; i ++){
        	curExps[i] = i;
        } 

		// array for final results
		double[] finalCoefs = new double[finalLength];
        int[] finalExps = new int[finalLength];

		// remove 0-coefs from final results
		int counter = 0;
		for(int i = 0; i < curCoefs.length; i++){
			if(curCoefs[i] != 0.0 && curCoefs[i] != 0){
				finalCoefs[counter] = curCoefs[i];
				finalExps[counter] = curExps[i];
				counter++;
			}
		}

		// System.out.println(Arrays.toString(finalCoefs));
		// System.out.println(Arrays.toString(finalExps));

		// yayyyyy we're done! :)
		Polynomial result = new Polynomial(finalCoefs, finalExps);
		return result;
	}

	// multiplies the polynomial the the method is called on to the polymonal in the brackets
	public Polynomial multiply(Polynomial second){
		// get the biggest exp
		int biggestExp = this.exponents[this.exponents.length - 1] + second.exponents[second.exponents.length - 1];

		// arrays for coef of final result without taking 0's into account
		double[] curCoefs = new double[biggestExp + 1];
        int[] curExps = new int[biggestExp + 1];

		// put in values for the exponents (those with zero coefs still included so we can compare later)
		for(int i = 0; i < biggestExp + 1; i ++){
        	curExps[i] = i;
        } 

		// multiply the polynomials and place in respective slot based on exponent
		int curExp = 0;
		for(int i = 0; i < this.exponents.length; i++){
			for(int j = 0; j < second.exponents.length; j++){
				curExp = this.exponents[i] + second.exponents[j];
				curCoefs[curExp] = curCoefs[curExp] + (this.coefficients[i] * second.coefficients[j]);
			}
		}

		// find all the 0's in the new coef array after multiplication is done and remove them from the length
		int numZeros = 0;
		for(int i = 0; i < curCoefs.length; i++){
			if(curCoefs[i] == 0.0 || curCoefs[i] == 0){
				numZeros++;
			}
		}
		int finalLength = curCoefs.length - numZeros;

		// array for final results
		double[] finalCoefs = new double[finalLength];
        int[] finalExps = new int[finalLength];

		// remove 0-coefs from final results
		int counter = 0;
		for(int i = 0; i < curCoefs.length; i++){
			if(curCoefs[i] != 0.0 && curCoefs[i] != 0){
				finalCoefs[counter] = curCoefs[i];
				finalExps[counter] = curExps[i];
				counter++;
			}
		}

		// System.out.println(Arrays.toString(finalCoefs));
		// System.out.println(Arrays.toString(finalExps));

		// yayyyyy we're done! :)
		Polynomial result = new Polynomial(finalCoefs, finalExps);
		return result;
	}

	public double evaluate(double num){
		double result = 0.0;

		for(int i = 0; i < coefficients.length; i++){
			result = coefficients[i] * Math.pow(num, i) + result;
		}
		return result;
	}

	public boolean hasRoot(double num){
		if (evaluate(num) == 0.0){
			return true;
		}
		return false;
	}

	// add all the data form the polynmial it is called with into a file
	public void saveToFile(String fileName) throws IOException {
		fileName = "/Users/katherinepravdin/projects/CSCB07/b07lab1/" + fileName;
		
		String polynomial = "";
		for(int i = 0; i < coefficients.length; i++) {
			if(i != 0 && coefficients[i] >= 0){
				polynomial = polynomial + "+";
			}
			polynomial = polynomial + coefficients[i];

			if(exponents[i] != 0){
				polynomial = polynomial + "x" + exponents[i];
			}
		}
		
		// System.out.println("polynomial is " + polynomial);
		FileWriter p = new FileWriter(fileName);
		p.write(polynomial);
		p.flush();
		p.close();
	}
	
}
