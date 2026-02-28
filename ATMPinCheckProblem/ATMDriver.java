import java.util.Scanner;

class AttemptsExceededException extends RuntimeException {
    public AttemptsExceededException() {
        System.out.println("PIN Attempts exceeded.");
    }
}

class AccountManager {

    static int wrongCounts;

    private String name;
    private Long accno;
    private int pin;
    private double balance;

    AccountManager(String name, Long accno) {
        this.name = name;
        this.accno = accno;
        int randomPin = (int) (Math.random() * 10000);
        System.out.println("Random Pin generated:- " + randomPin);
        this.pin = randomPin;
        this.balance = 100.0d;
        wrongCounts = 0;
    }

    public double getBalance() {
        Scanner pinInput = new Scanner(System.in);
        System.out.println("Enter pin:- ");
        int userInput = pinInput.nextInt();
        pinInput.nextLine();
        if (userInput != pin) {
            ++wrongCounts;
            try {
                System.out.println("Wrong PIN.");
                if (wrongCounts == 3)
                    throw new AttemptsExceededException();
            } catch (AttemptsExceededException e) {
                // System.out.println(e.getMessage());
                System.exit(0);
            }
            return -1.0d;
        }
        return balance;
    }

    public void deposit() {
    }// Unimplemented method to deposit money

    public void withdraw() {
    }// Unimplemented method to withdraw amount

}

public class ATMDriver {
    public static void main(String[] args) {
        AccountManager accountManager = new AccountManager("Aditya Mondal", 112345698390L);
        while (true) {
            double balance = accountManager.getBalance();
            if (balance != -1.0d) {
                System.out.println("Balance:- Rs." + balance);
                break;
            }
        }
    }
}