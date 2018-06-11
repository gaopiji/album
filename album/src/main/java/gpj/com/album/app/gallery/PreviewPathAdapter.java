/*
 * Copyright 2017 Yan Zhenjie.
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
package gpj.com.album.app.gallery;

import android.content.Context;
import android.widget.ImageView;

import java.util.List;

import gpj.com.album.Album;

/**
 * <p>Preview local image.</p>
 * Created by yanzhenjie on 17-3-29.
 */
public class PreviewPathAdapter extends PreviewAdapter<String> {

    public PreviewPathAdapter(Context context, List<String> previewList) {
        super(context, previewList);
    }

    @Override
    protected void loadPreview(ImageView imageView, String item, int position) {
        Album.getAlbumConfig().getAlbumLoader().load(imageView, item);
    }
}