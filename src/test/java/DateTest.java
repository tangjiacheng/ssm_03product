import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: TJC
 * @Date: 2020/6/6 19:46
 * @description: TODO
 */
public class DateTest {
    public static void main(String[] args) throws ParseException {
        Date d = new Date();
        String s = "2020-06-06 19:48";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        System.out.println(sdf.parse(s));
    }
}
