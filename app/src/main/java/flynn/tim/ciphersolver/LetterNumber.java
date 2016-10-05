package flynn.tim.ciphersolver;

/**
 * Created by Tim on 4/5/2016.
 */
public class LetterNumber {

    public void LetterNumber()
    {

    }

    public String encrypt(String s)
    {
        String result = " ";
        String intString = "";

        char[] ch  = s.trim().toUpperCase().toCharArray();
        for(char c : ch)
        {
            int temp = (int)c;
            if(temp<=122 & temp>=97)
                result = result + Integer.toString(temp-96) + " ";
                //System.out.print(temp-96);
        }
        return result;
    }
}
