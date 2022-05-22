/**
 * @Author zhangzhao
 * @Date 2021/6/8 13:28
 */

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @Description:
 * @Author: zhangzhao
 * @Date: 2021-06-08 13:28 
 **/
public class TimezoneTest {
    public static void main(String[] args) {

        z();
//        String[] ids = TimeZone.getAvailableIDs();
//        System.out.println(JSON.toJSONString(ids));
    }
    public static void z(){
        // + 1H
//        String timeZoneId = "Africa/Algiers";
        // +2H
//        String timeZoneId = "Europe/Dublin";
        String timeZoneId = "Atlantic/Madeira";

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone(timeZoneId));
        String format = sdf.format(new Date());
        System.out.println(format);
    }

}