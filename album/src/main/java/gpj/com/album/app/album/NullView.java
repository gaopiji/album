/*
 * Copyright 2018 Yan Zhenjie
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
package gpj.com.album.app.album;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import gpj.com.album.R;
import gpj.com.album.Utils.AlbumUtils;
import gpj.com.album.Utils.SystemBar;
import gpj.com.album.api.widget.Widget;
import gpj.com.album.app.Contract;


/**
 * Created by YanZhenjie on 2018/4/7.
 */
class NullView extends Contract.NullView implements View.OnClickListener {

    private Activity mActivity;

    private Toolbar mToolbar;
    private TextView mTvMessage;

    public NullView(Activity activity, Contract.NullPresenter presenter) {
        super(activity, presenter);
        this.mActivity = activity;
        this.mToolbar = activity.findViewById(R.id.toolbar);
        this.mTvMessage = activity.findViewById(R.id.tv_message);
    }

    @Override
    public void setupViews(Widget widget) {
        mToolbar.setBackgroundColor(widget.getToolBarColor());

        int statusBarColor = widget.getStatusBarColor();
        Drawable navigationIcon = getDrawable(R.drawable.album_ic_back_white);
        if (widget.getUiStyle() == Widget.STYLE_LIGHT) {
            if (SystemBar.setStatusBarDarkFont(mActivity, true)) {
                SystemBar.setStatusBarColor(mActivity, statusBarColor);
            } else {
                SystemBar.setStatusBarColor(mActivity, getColor(R.color.albumColorPrimaryBlack));
            }

            AlbumUtils.setDrawableTint(navigationIcon, getColor(R.color.albumIconDark));
            setHomeAsUpIndicator(navigationIcon);
        } else {
            SystemBar.setStatusBarColor(mActivity, statusBarColor);
            setHomeAsUpIndicator(navigationIcon);
        }
        SystemBar.setNavigationBarColor(mActivity, widget.getNavigationBarColor());

        Widget.ButtonStyle buttonStyle = widget.getButtonStyle();
        ColorStateList buttonSelector = buttonStyle.getButtonSelector();

    }

    @Override
    public void setMessage(int message) {
        mTvMessage.setText(message);
    }

    @Override
    public void setMakeImageDisplay(boolean display) {

    }

    @Override
    public void setMakeVideoDisplay(boolean display) {
    }

    @Override
    public void onClick(View v) {
    }
}