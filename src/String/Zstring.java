package String;
import java.util.List;
import java.util.ArrayList;

/**
 * 6.Z字形变换
 */
public class Zstring {
    public String convert(String s, int numRows) {
        if(numRows < 2)
            return s;

        List<StringBuilder> rows = new ArrayList<StringBuilder>();
        for(int i=0 ; i<Math.min(numRows,s.length()) ; i++){
            rows.add(new StringBuilder());
        }

        int curRow = 0 ;
        boolean goingDown = false;
        for(char c : s.toCharArray()){
            rows.get(curRow).append(c);
            if(curRow == 0 || curRow == numRows-1)
                goingDown = !goingDown;
            curRow += goingDown?1:-1;
        }

        StringBuilder ret = new StringBuilder();
        for(StringBuilder row : rows)
            ret.append(row);

        return ret.toString();
    }
}
