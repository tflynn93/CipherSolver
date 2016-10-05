package flynn.tim.ciphersolver;

public class Atbash
{
    public static void main(String[] args) {
    }

    public static String decrypt(String message)
    {
        StringBuilder decoded = new StringBuilder();
        for (char c : message.toCharArray()) {
            if (Character.isLetter(c)) {
                int newChar = ('Z' - c) + 'A';
                decoded.append((char) newChar);
            } else {
                decoded.append(c);
            }
        }
        return decoded.toString();
    }
}