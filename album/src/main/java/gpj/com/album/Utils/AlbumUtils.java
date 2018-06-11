package gpj.com.album.Utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.webkit.MimeTypeMap;

import java.io.File;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import gpj.com.album.widget.divider.Api20ItemDivider;
import gpj.com.album.widget.divider.Api21ItemDivider;
import gpj.com.album.widget.divider.Divider;

/**
 * Created by gpj on 2018/6/8.
 */

public class AlbumUtils {

    private static final String CACHE_DIRECTORY = "AlbumCache";

    /**
     * Get a writable root directory.
     *
     * @param context context.
     * @return {@link File}.
     */
    @NonNull
    public static File getAlbumRootPath(Context context) {
        if (sdCardIsAvailable()) {
            return new File(Environment.getExternalStorageDirectory(), CACHE_DIRECTORY);
        } else {
            return new File(context.getFilesDir(), CACHE_DIRECTORY);
        }
    }

    /**
     * SD card is available.
     *
     * @return true when available, other wise is false.
     */
    public static boolean sdCardIsAvailable() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return Environment.getExternalStorageDirectory().canWrite();
        } else
            return false;
    }


    /**
     * Generate a random jpg file path.
     *
     * @return file path.
     */
    @NonNull
    public static String randomJPGPath() {
        File bucket = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        return randomJPGPath(bucket);
    }

    /**
     * Generates a random jpg file path in the specified directory.
     *
     * @param bucket specify the directory.
     * @return file path.
     */
    @NonNull
    public static String randomJPGPath(File bucket) {
        return randomMediaPath(bucket, ".jpg");
    }


    /**
     * Generates a random file path using the specified suffix name in the specified directory.
     *
     * @param bucket    specify the directory.
     * @param extension extension.
     * @return file path.
     */
    @NonNull
    private static String randomMediaPath(File bucket, String extension) {
        if (bucket.exists() && bucket.isFile()) bucket.delete();
        if (!bucket.exists()) bucket.mkdirs();
        String outFilePath = AlbumUtils.getNowDateTime("yyyyMMdd_HHmmssSSS") + "_" + getMD5ForString(UUID.randomUUID().toString()) + extension;
        File file = new File(bucket, outFilePath);
        return file.getAbsolutePath();
    }

    /**
     * Format the current time in the specified format.
     *
     * @return the time string.
     */
    @NonNull
    public static String getNowDateTime(@NonNull String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.ENGLISH);
        Date curDate = new Date(System.currentTimeMillis());
        return formatter.format(curDate);
    }

    /**
     * Get the mime type of the file in the url.
     *
     * @param url file url.
     * @return mime type.
     */
    public static String getMimeType(String url) {
        String extension = getExtension(url);
        if (!MimeTypeMap.getSingleton().hasExtension(extension)) return "";

        String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        return TextUtils.isEmpty(mimeType) ? "" : mimeType;
    }

    /**
     * Get the file extension in url.
     *
     * @param url file url.
     * @return extension.
     */
    public static String getExtension(String url) {
        url = TextUtils.isEmpty(url) ? "" : url.toLowerCase();
        String extension = MimeTypeMap.getFileExtensionFromUrl(url);
        return TextUtils.isEmpty(extension) ? "" : extension;
    }

    /**
     * Specifies a tint for {@code drawable}.
     *
     * @param drawable drawable target, mutate.
     * @param color    color.
     */
    public static void setDrawableTint(@NonNull Drawable drawable, @ColorInt int color) {
        DrawableCompat.setTint(DrawableCompat.wrap(drawable.mutate()), color);
    }

    /**
     * Specifies a tint for {@code drawable}.
     *
     * @param drawable drawable target, mutate.
     * @param color    color.
     * @return convert drawable.
     */
    @NonNull
    public static Drawable getTintDrawable(@NonNull Drawable drawable, @ColorInt int color) {
        drawable = DrawableCompat.wrap(drawable.mutate());
        DrawableCompat.setTint(drawable, color);
        return drawable;
    }

    /**
     * {@link ColorStateList}.
     *
     * @param normal    normal color.
     * @param highLight highLight color.
     * @return {@link ColorStateList}.
     */
    public static ColorStateList getColorStateList(@ColorInt int normal, @ColorInt int highLight) {
        int[][] states = new int[6][];
        states[0] = new int[]{android.R.attr.state_checked};
        states[1] = new int[]{android.R.attr.state_pressed};
        states[2] = new int[]{android.R.attr.state_selected};
        states[3] = new int[]{};
        states[4] = new int[]{};
        states[5] = new int[]{};
        int[] colors = new int[]{highLight, highLight, highLight, normal, normal, normal};
        return new ColorStateList(states, colors);
    }

    /**
     * Change part of the color of CharSequence.
     *
     * @param content content text.
     * @param start   start index.
     * @param end     end index.
     * @param color   color.
     * @return {@code SpannableString}.
     */
    @NonNull
    public static SpannableString getColorText(@NonNull CharSequence content, int start, int end, @ColorInt int color) {
        SpannableString stringSpan = new SpannableString(content);
        stringSpan.setSpan(new ForegroundColorSpan(color), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return stringSpan;
    }

    /**
     * Return a color-int from alpha, red, green, blue components.
     *
     * @param color color.
     * @param alpha alpha, alpha component [0..255] of the color.
     */
    @ColorInt
    public static int getAlphaColor(@ColorInt int color, @IntRange(from = 0, to = 255) int alpha) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha, red, green, blue);
    }

    /**
     * Generate divider.
     *
     * @param color color.
     * @return {@link Divider}.
     */
    public static Divider getDivider(@ColorInt int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return new Api21ItemDivider(color);
        }
        return new Api20ItemDivider(color);
    }

    /**
     * Time conversion.
     *
     * @param duration ms.
     * @return such as: {@code 00:00:00}, {@code 00:00}.
     */
    @NonNull
    public static String convertDuration(@IntRange(from = 1) long duration) {
        duration /= 1000;
        int hour = (int) (duration / 3600);
        int minute = (int) ((duration - hour * 3600) / 60);
        int second = (int) (duration - hour * 3600 - minute * 60);

        String hourValue = "";
        String minuteValue;
        String secondValue;
        if (hour > 0) {
            if (hour >= 10) {
                hourValue = Integer.toString(hour);
            } else {
                hourValue = "0" + hour;
            }
            hourValue += ":";
        }
        if (minute > 0) {
            if (minute >= 10) {
                minuteValue = Integer.toString(minute);
            } else {
                minuteValue = "0" + minute;
            }
        } else {
            minuteValue = "00";
        }
        minuteValue += ":";
        if (second > 0) {
            if (second >= 10) {
                secondValue = Integer.toString(second);
            } else {
                secondValue = "0" + second;
            }
        } else {
            secondValue = "00";
        }
        return hourValue + minuteValue + secondValue;
    }

    /**
     * Get the MD5 value of string.
     *
     * @param content the target string.
     * @return the MD5 value.
     */
    public static String getMD5ForString(String content) {
        StringBuilder md5Buffer = new StringBuilder();
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] tempBytes = digest.digest(content.getBytes());
            int digital;
            for (int i = 0; i < tempBytes.length; i++) {
                digital = tempBytes[i];
                if (digital < 0) {
                    digital += 256;
                }
                if (digital < 16) {
                    md5Buffer.append("0");
                }
                md5Buffer.append(Integer.toHexString(digital));
            }
        } catch (Exception ignored) {
            return Integer.toString(content.hashCode());
        }
        return md5Buffer.toString();
    }
}
