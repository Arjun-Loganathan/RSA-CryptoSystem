import java.util.Scanner;

public class RSAEmulator {

    /**
     * @param args String argument passes to main method
     */
    public static void main(String[] args) {
        startProgram();
    }

    /**
     * startProgram() calls RSAProgram.java to simulate
     * RSA Algorithm
     */
    private static void startProgram() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter two prime numbers, p and q: ");
        int firstNumber = scanner.nextInt();
        int secondNumber = scanner.nextInt();
        RSAProgram rsaProgram = new RSAProgram();
        rsaProgram.executeProgram(firstNumber, secondNumber);
        System.out.print("Enter the plaintext message m (an integer): ");
        int inputPlainText = scanner.nextInt();
        rsaProgram.encryptData(inputPlainText);
        rsaProgram.decryptData();
    }
}
