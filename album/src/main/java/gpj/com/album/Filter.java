package gpj.com.album;

/**
 * Created by gpj on 2018/6/8.
 */

public interface Filter<T> {

    /**
     * Filter the file.
     *
     * @param attributes attributes of file.
     * @return filter returns true, otherwise false.
     */
    boolean filter(T attributes);

}