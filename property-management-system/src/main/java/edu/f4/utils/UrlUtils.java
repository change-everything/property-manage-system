package edu.f4.utils;

/**
 * @author PeiYP
 * @since 2022年06月10日 15:45
 */
public class UrlUtils {

    public static String subUrl(String str) {
        //String url = "filter invocation [GET /owner]";

        return str.substring(str.indexOf("[") + 1, str.indexOf("/"));
    }

    public static String splitUrl(String str) {
        // emp/1/3
        String[] split = str.split("/");
        if (split.length <= 2) {
            split = str.split("\\?");
            return split[0];
        }
        return "/" + split[1];
    }


}
