import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {


    // 正则匹配url上的语言值
    private static Pattern strPattern = Pattern.compile("[a-zA-Z0-9\\-_]$");

    private static boolean hasSplit = false;
    private static List<String> strWhitelist = new ArrayList<String>();
    private static List<Pattern> regWhitelist = new ArrayList<Pattern>();
    private static List<String> whitelist = new ArrayList<String>();

    // 切分出字符串还是正则
    private static void splitStrAndReg() {
        // 每个版本不会变化，执行一次存起来就可以了
        if (hasSplit) {
            return;
        }
        for (String item : whitelist) {
//            for (String item : block) {
                Matcher matcher = strPattern.matcher(item);
                if (matcher.find()) {
                    strWhitelist.add(item);
                } else {
                    regWhitelist.add(Pattern.compile(item));
                }
//            }
        }
        hasSplit = true;
    }

    public static void main(String[] args) {
        for (int i = 0; i<100;i++) {
            Thread t = new Thread() {
                public void run(){
                    splitStrAndReg();
                }
            };
            t.start();
        }
        for (Pattern pattern : regWhitelist) {
            Matcher m = pattern.matcher("/tmp-test");
            if (m.find()) {

            }
        }
    }


}
