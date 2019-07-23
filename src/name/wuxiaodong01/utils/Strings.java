package name.wuxiaodong01.utils;

public class Strings {
    private Strings() {}


    public static String nullToEmpty(String string) {
        return com.google.common.base.Strings.nullToEmpty(string);
    }


    public static String emptyToNull(String string) {
        return com.google.common.base.Strings.emptyToNull(string);
    }


    public static boolean isNullOrEmpty( String string) {
        return com.google.common.base.Strings.isNullOrEmpty(string);
    }

    public static boolean isEmpty( String string) {
        return "".equals(string);
    }

    public static boolean isNull( String string) {
        return string==null;
    }

    public static String padStart(String string, int minLength, char padChar) {
       return com.google.common.base.Strings.padStart(string,minLength,padChar);
    }


    public static String padEnd(String string, int minLength, char padChar) {
        return com.google.common.base.Strings.padEnd(string,minLength,padChar);
    }


    public static String repeat(String string, int count) {
        return com.google.common.base.Strings.repeat(string,count);
    }


    public static String commonPrefix(CharSequence a, CharSequence b) {
        return com.google.common.base.Strings.commonPrefix(a,b);
    }


    public static String commonSuffix(CharSequence a, CharSequence b) {
        return com.google.common.base.Strings.commonSuffix(a,b);

    }
}
