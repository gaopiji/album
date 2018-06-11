/*
 * Copyright 2018 Yan Zhenjie.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package gpj.com.album;

import android.content.Context;

/**
 * <p>Any action takes place.</p>
 * Created by YanZhenjie on 2018/4/17.
 */
public interface ItemAction<T> {
    /**
     * When the action responds.
     *
     * @param context context.
     * @param item    item.
     */
    void onAction(Context context, T item);
}
