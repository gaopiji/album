package gpj.com.album.api;

import android.content.Context;
import android.support.annotation.Nullable;

import gpj.com.album.Action;
import gpj.com.album.api.widget.Widget;

/**
 * Created by gpj on 2018/6/8.
 */

public abstract class BasicAlbumWrapper <Returner extends BasicAlbumWrapper, Result, Cancel, Checked> {

    final Context mContext;
    Action<Result> mResult;
    Action<Cancel> mCancel;
    Widget mWidget;
    Checked mChecked;

    BasicAlbumWrapper(Context context) {
        this.mContext = context;
        mWidget = Widget.getDefaultWidget(context);
    }

    /**
     * Set the action when result.
     *
     * @param result action when producing result.
     */
    public final Returner onResult(Action<Result> result) {
        this.mResult = result;
        return (Returner) this;
    }

    /**
     * Set the action when canceling.
     *
     * @param cancel action when canceled.
     */
    public final Returner onCancel(Action<Cancel> cancel) {
        this.mCancel = cancel;
        return (Returner) this;
    }

    /**
     * Set the widget property.
     *
     * @param widget the widget.
     */
    public final Returner widget(@Nullable Widget widget) {
        this.mWidget = widget;
        return (Returner) this;
    }

    /**
     * Start up.
     */
    public abstract void start();
}