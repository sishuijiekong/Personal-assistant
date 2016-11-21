package util;

import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;

/**
 * Created by 张显林 on 2016/11/11.
 * 屏幕亮度工具类
 */
public class BrightnessUtils {


    /**
     * 判断屏幕亮度调整模式，自动：mode=1；手动：mode=0
     * @param context
     */
    public static void setScrennManualMode(Context context) {
        ContentResolver contentResolver =context.getContentResolver();
        try {
            int mode = Settings.System.getInt(contentResolver,
                    Settings.System.SCREEN_BRIGHTNESS_MODE);
            if (mode == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC) {
                Settings.System.putInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS_MODE,
                        Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
            }
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断屏幕亮度调整模式，自动：mode=1；手动：mode=0
     * @param context
     */
    public static void setScrennManualMode2(Context context) {
        ContentResolver contentResolver =context.getContentResolver();
        try {
            int mode = Settings.System.getInt(contentResolver,
                    Settings.System.SCREEN_BRIGHTNESS_MODE);
            if (mode == Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL) {
                Settings.System.putInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS_MODE,
                        Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);
            }
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置屏幕亮度
     * @param context
     * @return
     */
    public static int getScreenBrightness(Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        int defVal = 25;
        return Settings.System.getInt(contentResolver,
                Settings.System.SCREEN_BRIGHTNESS, defVal);
    }
}
