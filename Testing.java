package calc;

import com.sun.javafx.css.parser.Token;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.System.out;
import static java.lang.System.setOut;

public class Testing {
    public static void main(String[] args) {
        String beforeTokenize = "(100*10)/50+3";
        String digits = "1234567890";
        List<String> afterTokenize = new ArrayList<String>();
        StringBuilder temp = new StringBuilder();

        for (int i = 0; i<beforeTokenize.length(); i++) {
            char ch = beforeTokenize.charAt(i);
            out.println(afterTokenize);
            if (Character.isWhitespace(ch)) {
                continue;
            } if (digits.contains(String.valueOf(ch))) {
                temp.append(ch);
            } if (digits.contains(String.valueOf(ch)) && i == beforeTokenize.length()-1) {
                afterTokenize.add(String.valueOf(temp));
                continue;
            } if (!digits.contains(String.valueOf(ch)) && !(temp.length() == 0)){
                afterTokenize.add(String.valueOf(temp));
                temp.setLength(0);
            } if (!digits.contains(String.valueOf(ch))) {
                afterTokenize.add(String.valueOf(ch));
            }
        }
        out.println(afterTokenize);



       /* for (char ch : beforeTokenize.toCharArray()) {
            i++;
            if (Character.isWhitespace(ch)) {
                continue;
            } if (digits.contains(String.valueOf(ch))) {
                temp.append(ch);
            } if (i == beforeTokenize.length() && !digits.contains(String.valueOf(ch))) {
                afterTokenize.add(String.valueOf(ch));
            } else if (i == beforeTokenize.length()) {
                afterTokenize.add(String.valueOf(temp));
            } if (!digits.contains(String.valueOf(ch))) {
                afterTokenize.add(String.valueOf(temp));
                afterTokenize.add(String.valueOf(ch));
                temp.setLength(0);
            }
        }
        out.println(afterTokenize);*/
    }
}
