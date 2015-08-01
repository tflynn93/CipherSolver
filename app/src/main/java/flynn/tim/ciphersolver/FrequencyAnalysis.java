package flynn.tim.ciphersolver;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Tim
 */
public class FrequencyAnalysis {

    public void FrequencyAnalysis()
    {

    }

    public HashMap analyze(String fa)
    {
        HashMap<Character,Integer> map = new HashMap<Character,Integer>();

        for(int i = 0; i < fa.length(); i++){
            char c = fa.charAt(i);
            Integer val = map.get(new Character(c));
            if(val != null){
                map.put(c, new Integer(val + 1));
            }
            else{
                map.put(c,1);
            }
        }

        /*
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.print(pair.getKey() + " = " + pair.getValue() + "\t");
            for(int i = 0; i < Integer.parseInt(pair.getValue().toString()); i++)
            {
                System.out.print("*");
            }
            System.out.println("\n");
            it.remove(); // avoids a ConcurrentModificationException
        }
        */
        return map;
    }
}
