import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;

public class RSA1 {
    private static SecureRandom random = new SecureRandom();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Generate 9-digit primes
        BigInteger p = generateNineDigitPrime();
        BigInteger q = generateNineDigitPrime();
        System.out.println("Selected primes: " + p + " " + q);

        BigInteger n = p.multiply(q); // n = p * q
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE)); // phi = (p-1)*(q-1)
        System.out.println("phi value: " + phi);

        // Generate a random public exponent e
        BigInteger e = generateRandomPublicExponent(phi);
        System.out.println("Selected public exponent e: " + e);

        // Calculate private exponent
        BigInteger d = modInverse(e, phi);

        // Flush the newline left by nextBigInteger()
        scanner.nextLine();

        // Prompt user for the message input
        System.out.print("Enter a message (text): ");
        String message = scanner.nextLine().trim();

        // Check if message is not empty
        if (message.isEmpty()) {
            System.out.println("Message cannot be empty.");
            return;
        }

        // Encrypt each character of the message
        System.out.println("Encrypted message:");
        StringBuilder encryptedMessage = new StringBuilder();
        for (char ch : message.toCharArray()) {
            BigInteger encryptedChar = modPow(BigInteger.valueOf(ch), e, n);
            encryptedMessage.append(encryptedChar).append(" ");
        }
        System.out.println(encryptedMessage.toString());

        // Decrypt each character of the encrypted message
        System.out.println("Decrypted message:");
        String[] encryptedChars = encryptedMessage.toString().trim().split(" ");
        StringBuilder decryptedMessage = new StringBuilder();
        for (String encChar : encryptedChars) {
            BigInteger encryptedChar = new BigInteger(encChar);
            char decryptedChar = (char) modPow(encryptedChar, d, n).intValue();
            decryptedMessage.append(decryptedChar);
        }
        System.out.println(decryptedMessage.toString());

        scanner.close();
    }

    // Generate a random 9-digit prime number
    private static BigInteger generateNineDigitPrime() {
        BigInteger prime;
        do {
            // Generate a random 9-digit number
            prime = new BigInteger(9 * 3, random); // 9 * 3 bits is a safe estimate for 9-digit number
            prime = prime.nextProbablePrime();
        } while (prime.toString().length() != 9); // Ensure it has exactly 9 digits
        return prime;
    }

    // Generate a random public exponent e that is coprime with phi
    private static BigInteger generateRandomPublicExponent(BigInteger phi) {
        BigInteger e;
        do {
            // Generate a random number
            e = new BigInteger(phi.bitLength(), random);
            // Ensure e is within a reasonable range and is not too small
            if (e.compareTo(BigInteger.TWO) <= 0) {
                e = BigInteger.TWO;
            }
        } while (!e.gcd(phi).equals(BigInteger.ONE)); // Check if e and phi are coprime
        return e;
    }

    // Modular exponentiation (base^exponent % modulus) using BigInteger
    public static BigInteger modPow(BigInteger base, BigInteger exponent, BigInteger modulus) {
        return base.modPow(exponent, modulus);
    }

    // Extended Euclidean Algorithm to find modular inverse of e mod phi using BigInteger
    public static BigInteger modInverse(BigInteger e, BigInteger phi) {
        return e.modInverse(phi);
    }
}
