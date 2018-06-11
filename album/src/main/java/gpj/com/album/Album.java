package gpj.com.album;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.support.annotation.IntDef;
import android.util.Log;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import gpj.com.album.api.BasicGalleryWrapper;
import gpj.com.album.api.GalleryAlbumWrapper;
import gpj.com.album.api.GalleryWrapper;
import gpj.com.album.api.ImageMultipleWrapper;
import gpj.com.album.api.ImageSingleWrapper;
import gpj.com.album.api.choice.Choice;
import gpj.com.album.api.choice.ImageChoice;

/**
 * Created by gpj on 2018/6/8.
 */

public class Album {
    // All.
    public static final String KEY_INPUT_WIDGET = "KEY_INPUT_WIDGET";
    public static final String KEY_INPUT_CHECKED_LIST = "KEY_INPUT_CHECKED_LIST";

    // Album.
    public static final String KEY_INPUT_FUNCTION = "KEY_INPUT_FUNCTION";
    public static final int FUNCTION_CHOICE_IMAGE = 0;
    public static final int FUNCTION_CHOICE_VIDEO = 1;
    public static final int FUNCTION_CHOICE_ALBUM = 2;

    public static final int FUNCTION_CAMERA_IMAGE = 0;
    public static final int FUNCTION_CAMERA_VIDEO = 1;

    public static final String KEY_INPUT_CHOICE_MODE = "KEY_INPUT_CHOICE_MODE";
    public static final int MODE_MULTIPLE = 1;
    public static final int MODE_SINGLE = 2;
    public static final String KEY_INPUT_COLUMN_COUNT = "KEY_INPUT_COLUMN_COUNT";
    public static final String KEY_INPUT_ALLOW_CAMERA = "KEY_INPUT_ALLOW_CAMERA";
    public static final String KEY_INPUT_LIMIT_COUNT = "KEY_INPUT_LIMIT_COUNT";

    // Gallery.
    public static final String KEY_INPUT_CURRENT_POSITION = "KEY_INPUT_CURRENT_POSITION";
    public static final String KEY_INPUT_GALLERY_CHECKABLE = "KEY_INPUT_GALLERY_CHECKABLE";



    @IntDef({FUNCTION_CHOICE_IMAGE, FUNCTION_CHOICE_VIDEO, FUNCTION_CHOICE_ALBUM})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ChoiceFunction {
    }

    @IntDef({FUNCTION_CAMERA_IMAGE, FUNCTION_CAMERA_VIDEO})
    @Retention(RetentionPolicy.SOURCE)
    public @interface CameraFunction {
    }

    @IntDef({MODE_MULTIPLE, MODE_SINGLE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ChoiceMode {
    }

    private static AlbumConfig sAlbumConfig;

    /**
     * Initialize Album.
     *
     * @param albumConfig {@link AlbumConfig}.
     */
    public static void initialize(AlbumConfig albumConfig) {
        if (sAlbumConfig == null) sAlbumConfig = albumConfig;
        else Log.w("Album", new IllegalStateException("Illegal operation, only allowed to configure once."));
    }

    /**
     * Get the album configuration.
     */
    public static AlbumConfig getAlbumConfig() {
        if (sAlbumConfig == null) {
            sAlbumConfig = AlbumConfig.newBuilder(null).build();
        }
        return sAlbumConfig;
    }

    /**
     * Select images.
     */
    public static Choice<ImageMultipleWrapper, ImageSingleWrapper> image(Context context) {
        return new ImageChoice(context);
    }


    /**
     * Preview picture.
     */
    public static GalleryWrapper gallery(Context context) {
        return new GalleryWrapper(context);
    }

    /**
     * Preview Album.
     */
    public static GalleryAlbumWrapper galleryAlbum(Context context) {
        return new GalleryAlbumWrapper(context);
    }


    /**
     * Select images.
     */
    public static Choice<ImageMultipleWrapper, ImageSingleWrapper> image(Activity activity) {
        return new ImageChoice(activity);
    }



    /**
     * Preview picture.
     */
    public static BasicGalleryWrapper<GalleryWrapper, String, String, String> gallery(Activity activity) {
        return new GalleryWrapper(activity);
    }

    /**
     * Preview Album.
     */
    public static BasicGalleryWrapper<GalleryAlbumWrapper, AlbumFile, String, AlbumFile> galleryAlbum(Activity activity) {
        return new GalleryAlbumWrapper(activity);
    }


    /**
     * Select images.
     */
    public static Choice<ImageMultipleWrapper, ImageSingleWrapper> image(Fragment fragment) {
        return new ImageChoice(fragment.getActivity());
    }



    /**
     * Preview picture.
     */
    public static BasicGalleryWrapper<GalleryWrapper, String, String, String> gallery(Fragment fragment) {
        return new GalleryWrapper(fragment.getActivity());
    }

    /**
     * Preview Album.
     */
    public static BasicGalleryWrapper<GalleryAlbumWrapper, AlbumFile, String, AlbumFile> galleryAlbum(Fragment fragment) {
        return new GalleryAlbumWrapper(fragment.getActivity());
    }


    /**
     * Select images.
     */
    public static Choice<ImageMultipleWrapper, ImageSingleWrapper> image(android.support.v4.app.Fragment fragment) {
        return new ImageChoice(fragment.getContext());
    }


    /**
     * Preview picture.
     */
    public static BasicGalleryWrapper<GalleryWrapper, String, String, String> gallery(android.support.v4.app.Fragment fragment) {
        return new GalleryWrapper(fragment.getContext());
    }

    /**
     * Preview Album.
     */
    public static BasicGalleryWrapper<GalleryAlbumWrapper, AlbumFile, String, AlbumFile> galleryAlbum(android.support.v4.app.Fragment fragment) {
        return new GalleryAlbumWrapper(fragment.getContext());
    }
}