import java.math.BigDecimal;
import java.math.BigInteger;

public class RSAProgram {

    int product1;
    int product2;
    int eValue;
    int dValue;
    double encryptedMessage;

    /**
     * @param num1 First Prime Number - Input from user
     * @param num2 Second Prime Number - Input from user
     */
    protected void executeProgram(int num1, int num2) {
        product1 = (num1-1) * (num2-1);
        product2 = num1 * num2;
        int e = 0;
        for (e=2; e<product1; e++) {
            if (findValueOfE(e, product1) == 1 ){
                break;
            }
        }
        int d = findValueOfD(e, product1+1);
        System.out.println("\nCalculating RSA values...\n");
        System.out.println("Public RSA key is ("+e+", "+product2+")");
        System.out.println("Private RSA key is ("+d+", "+product2+")\n");
        eValue = e;
        dValue = d;
    }

    /**
     * @param e - The value of E in RSA Algorithm
     * @param product - Product of (p-1) (q-1) represented in RSA Algorithm
     */
    protected int findValueOfE(int e, int product) {
        if (e == 0)
            return product;
        return findValueOfE(product % e, e);
    }

    /**
     * @param e - The value of E in RSA Algorithm
     * @param product - Product of (p-1) (q-1) represented in RSA Algorithm
     */
    protected int findValueOfD(int e, int product) {
        int result = 0, multiplier = 1, d = 0;
        while (result == 0) {
            result = (product * multiplier++) % e;
            d = (product * multiplier++) / e;
        }
        return d;
    }


    /**
     * @param value The plain text to encrypt
     */
    protected void encryptData(int value) {
        encryptedMessage = Math.pow(value, eValue) % product2;
        System.out.println("\nEncrypting m...\nThe ciphertext c is "+String.format("%.0f", encryptedMessage));
    }

    protected void decryptData() {
        /*
         “BigInteger (Java Platform SE 7 ),” Oracle.com, Jun. 24, 2020.
         https://docs.oracle.com/javase/7/docs/api/java/math/BigInteger.html (accessed Apr. 03, 2022).
         */

        BigInteger nValue = BigInteger.valueOf(product2);
        BigInteger cValue = BigDecimal.valueOf(encryptedMessage).toBigInteger();

        // To perform mod operation on a Big Integer, I am making use of mod()
        BigInteger plainText = (cValue.pow(dValue)).mod(nValue);
        System.out.println("\nDecrypting c...\nThe plaintext m is "+plainText);
    }
}
