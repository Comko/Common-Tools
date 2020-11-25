package sample.utils;

/**
 * @author zhangpenghao
 * @ClassName : JsonViewUtils
 * @description: json view
 * @date 2020-11-20 16:49
 */
public class JsonViewUtils {
    private static String SPACE = "    ";

    public static String formatJson(String json)
    {
        StringBuffer result = new StringBuffer();

        int length = json.length();
        int number = 0;
        char key = 0;

        for (int i = 0; i < length; i++)
        {
            key = json.charAt(i);
            if((key == '[') || (key == '{') )
            {
                if((i - 1 > 0) && (json.charAt(i - 1) == ':'))
                {
                    result.append('\n');
                    result.append(indent(number));
                }
                result.append(key);
                result.append('\n');
                number++;
                result.append(indent(number));
                continue;
            }
            if((key == ']') || (key == '}') )
            {
                result.append('\n');
                number--;
                result.append(indent(number));
                result.append(key);
                if(((i + 1) < length) && (json.charAt(i + 1) != ','))
                {
                    result.append('\n');
                }
                continue;
            }
            if((key == ','))
            {
                result.append(key);
                result.append('\n');
                result.append(indent(number));
                continue;
            }
            result.append(key);
        }

        return result.toString();
    }

    private static String indent(int number)
    {
        StringBuffer result = new StringBuffer();
        for(int i = 0; i < number; i++)
        {
            result.append(SPACE);
        }
        return result.toString();
    }
}
