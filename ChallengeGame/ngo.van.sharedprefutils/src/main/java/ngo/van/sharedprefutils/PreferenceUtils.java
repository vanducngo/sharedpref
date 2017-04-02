package ngo.van.sharedprefutils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceUtils {

    private static final String PREF_NAME = "PREF";

    public static void saveString(Context context, String key, String value) {
        if (context == null)
            return;

        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, /* MODE_PRIVATE */
                0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getString(Context context, String key, String defValue) {
        if (context == null)
            return defValue;
        SharedPreferences settings = context.getSharedPreferences(PREF_NAME, /* MODE_PRIVATE */
                0);
        return settings.getString(key, defValue);
    }

    public static void saveLong(Context context, String key, long value) {
        if (context == null)
            return;

        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, /* MODE_PRIVATE */
                0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public static long getLong(Context context, String key, long defValue) {
        if (context == null)
            return defValue;
        SharedPreferences settings = context.getSharedPreferences(PREF_NAME, /* MODE_PRIVATE */
                0);
        return settings.getLong(key, defValue);
    }

    public static void saveInt(Context context, String key, int value) {
        if (context == null)
            return;

        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, /* MODE_PRIVATE */
                0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static int getInt(Context context, String key, int defValue) {
        if (context == null)
            return defValue;
        SharedPreferences settings = context.getSharedPreferences(PREF_NAME, /* MODE_PRIVATE */
                0);
        return settings.getInt(key, defValue);
    }

    public static void saveBoolean(Context context, String key, boolean value) {
        if (context == null)
            return;

        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, /* MODE_PRIVATE */
                0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static boolean getBoolean(Context context, String key,
                                     boolean defValue) {
        if (context == null)
            return defValue;
        SharedPreferences settings = context.getSharedPreferences(PREF_NAME, /* MODE_PRIVATE */
                0);
        return settings.getBoolean(key, defValue);
    }

    public static void remove(Context context, String key) {
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, /* MODE_PRIVATE */
                0);
        SharedPreferences.Editor editor = pref.edit();
        editor.remove(key);
        editor.apply();
    }

}