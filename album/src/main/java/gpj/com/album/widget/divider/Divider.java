package gpj.com.album.widget.divider;

import android.support.v7.widget.RecyclerView;

/**
 * Created by gpj on 2018/6/8.
 */

public abstract class Divider extends RecyclerView.ItemDecoration {

    /**
     * Get the height of the divider.
     *
     * @return height of the divider.
     */
    public abstract int getHeight();

    /**
     * Get the width of the divider.
     *
     * @return width of the divider.
     */
    public abstract int getWidth();
}
