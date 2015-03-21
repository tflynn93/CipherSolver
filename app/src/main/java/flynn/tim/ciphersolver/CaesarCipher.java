package flynn.tim.ciphersolver;

import java.io.IOException;

/**
 *@author Timothy Flynn
 *Code modified from source provided at: http://rosettacode.org/wiki/Caesar_cipher#Java
 */

public class CaesarCipher {

    public static void main(String[] args) throws IOException {


        //Declare and initialize String givenString with the appropriate value (from assignment)
        String givenString = "ZSAD LG LZW DAGF DGQSD SFV LJMW ZSAD SDES ESLWJ OALZ QGMJ OZALW SFV TDMW";
        //Print out original ciphertext
        System.out.println("Ciphertext: \t" + givenString +"\n");
        //Declare String result for use later
        String result;

        //Loop through all 26 combinations of the Caesar Cipher
        for(int i = 1; i <= 26; i++)
        {
            //Store the value of the encode with the given offset in result
            result = encode(givenString, i);
            //Display result with some formatting
            System.out.println("Offset " + i + ":\t" + result);
            //Loop again with new offset
        }
    }

    //Method for encoding Caesar Cipher
    //Takes two arguments, String enc to be encoded and int offset for number of characters to offset
    public static String encode(String enc, int offset) {
        //Take the offset mod 26 then add 26
        offset = offset % 26 + 26;
        //Declare a StringBuilder to access individual parts of the String
        StringBuilder encoded = new StringBuilder();
        //Iterate through all characters in String enc
        for (char i : enc.toCharArray()) {
            //Nested ifs to shift individual elements of the character array
            if (Character.isLetter(i)) {
                //Check for upper case/lower case
                if (Character.isUpperCase(i)) {
                    //Append character + offset (taking into account wraparound)
                    encoded.append((char) ('A' + (i - 'A' + offset) % 26 ));
                }
                //Append character + offset (taking into account wraparound)
                else {
                    encoded.append((char) ('a' + (i - 'a' + offset) % 26 ));
                }
            } else {
                //Append characcter to StringBuilder object
                encoded.append(i);
            }
        }
        //Return encoded string
        return encoded.toString();
    }
}

