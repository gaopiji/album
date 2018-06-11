package gpj.com.album;

import android.util.Log;
import android.widget.ImageView;

/**
 * Created by gpj on 2018/6/8.
 */

public interface AlbumLoader {
    AlbumLoader DEFAULT = new AlbumLoader() {
        @Override
        public void load(ImageView imageView, AlbumFile albumFile) {
            Log.d("jpg",albumFile.getPath());
        }

        @Override
        public void load(ImageView imageView, String url) {
        }
    };

    /**
     * Load a preview of the album file.
     *
     * @param imageView {@link ImageView}.
     * @param albumFile the media object may be a picture or video.
     */
    void load(ImageView imageView, AlbumFile albumFile);

    /**
     * Load thumbnails of pictures or videos, either local file or remote file.
     *
     * @param imageView {@link ImageView}.
     * @param url       The url of the file, local path or remote path.
     */
    void load(ImageView imageView, String url);

}