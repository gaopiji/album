package gpj.com.album;

import android.support.annotation.NonNull;

/**
 * Created by gpj on 2018/6/8.
 */

public interface Action<T> {

    /**
     * When the action responds.
     *
     * @param result the result of the action.
     */
    void onAction(@NonNull T result);

}