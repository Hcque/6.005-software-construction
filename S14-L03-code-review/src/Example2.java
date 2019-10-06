
public class Example2 {

    public static boolean leap(int y) {
        String tmp = String.valueOf(y);
        if (tmp.charAt(2) == '1' || tmp.charAt(2) == '3' || tmp.charAt(2) == 5 || tmp.charAt(2) == '7' || tmp.charAt(2) == '9') {
            if (tmp.charAt(3)=='2'||tmp.charAt(3)=='6') return true;
            else
                return false;
        }else{
            if (tmp.charAt(2) == '0' && tmp.charAt(3) == '0') {
                return false;
            }
            if (tmp.charAt(3)=='0'||tmp.charAt(3)=='4'||tmp.charAt(3)=='8')return true;
        }
        return false;
    }

}
