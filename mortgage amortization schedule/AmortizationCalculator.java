import java.util.Scanner;


public class AmortizationCalculator {
    private static Scanner scanner = new Scanner(System.in);
    private static double monthlyRate;
    public static void main(String[] args){
        System.out.println("Please enter your total loan amount:");
        double principal = scanner.nextDouble();
        System.out.println("Please enter your total APR interest rate (format is 00.00):");
        double interest = scanner.nextDouble();
        System.out.println("Please enter in your total number of payments:");
        int numberofPayments = scanner.nextInt();

        monthlyRate = (double) interest / 1200;
        double amortization = calculate(principal, numberofPayments);
        System.out.println("=====================================");
        System.out.printf("Your periodic payment amount is %.4f\n", amortization);
        System.out.println("=====================================");
        
        printTable(principal, numberofPayments);



    }

    private static double calculate(double principal, int numberofPayments){
        double amortization = 0.00;
        if (monthlyRate > 0){
            amortization = (principal * monthlyRate * Math.pow(1 + monthlyRate, (double)numberofPayments)) /
                        (Math.pow(1 + monthlyRate, (double)numberofPayments) - 1);
        } else {
            amortization = principal / numberofPayments;
        }
        return amortization;
    }

    public static void printTable(double principal, int numberofPayments){
        //Begin printing table
        Object [] headers = {"Payment #", "Amount Due", "Interest Due", "Principal Due", "Amount Left"};
        System.out.println("\n==========================Your Mortgage Payoff table=========================");
        System.out.println("=========================------------------------------======================");
        System.out.format("%5s %12s %18s %18s %16s \n", headers);
        System.out.println("=============================================================================");

        //Establish values for the variables that we will use in the calculations
        double monthlyPayment = calculate(principal, numberofPayments);
        double interestPayment = 0.0;
        double principalDue = 0.0;
        double principalLeft = principal;
        boolean loanPaid = false;

        for(int i = 0; i < numberofPayments; i++){
            interestPayment = monthlyRate * principalLeft;
            principalDue = monthlyPayment - interestPayment;
            principalLeft = Math.abs(principalLeft - principalDue);

            Object[] row =  {i+1, monthlyPayment, interestPayment, principalDue, principalLeft};
            System.out.format("%5d%17.4f%19.4f%19.4f%17.4f\n", row);
            

        }


    }
}