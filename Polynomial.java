package b07lab1;

public class Polynomial {
    // double x;
    double[] coefficients_arr;

    // constructor to set to 0
    public Polynomial(){
        coefficients_arr = new double[1];
        coefficients_arr[0] = 0;
    }

    // constructor that sets the coeffients
    public Polynomial(double[] coefficients){
        coefficients_arr = new double[coefficients.length];

        for(int i = 0; i < coefficients.length; i++){
            coefficients_arr[i] = coefficients[i];
        }
    }

	public Polynomial add(Polynomial coefficients){
		Polynomial result;
		int max_Length = Math.max(coefficients.coefficients_arr.length, coefficients_arr.length);
		
        if (max_Length == coefficients.coefficients_arr.length){
			result = new Polynomial(coefficients.coefficients_arr);

            for(int i = 0; i < coefficients_arr.length; i++){
				result.coefficients_arr[i] = result.coefficients_arr[i] + coefficients_arr[i];
            }
		} 
        else {
			result = new Polynomial(coefficients_arr);

            for(int i = 0; i < coefficients.coefficients_arr.length; i++){
				result.coefficients_arr[i] = result.coefficients_arr[i] + coefficients.coefficients_arr[i];
			}
		}
		return result;
	}

	public double evaluate(double num){
		double result = 0.0;

		for(int i = 0; i < coefficients_arr.length; i++){
			result = coefficients_arr[i] * Math.pow(num, i) + result;
		}
		return result;
	}

	public boolean hasRoot(double num){
		if (evaluate(num) == 0.0){
			return true;
		}
		return false;
	}
}
