/*
 * Copyright (c) 2017. Truiton (http://www.truiton.com/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 * Mohit Gupt (https://github.com/mohitgupt)
 *
 */

package com.eap.form;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ItemThreeFragment extends Fragment {
    Button btn_dimens;
    TextView textViewWidth, textViewHeight, textWidthPixels, textHeightPixels, textDensityDpi, textXdpi, textYdpi
            ,textScreenHeight, textScreenWidth, textOrientation;

    int width, height,heightPixels, widthPixels, screenHeight, screenWidth, orientation, densityDpi;
    float xdpi, ydpi;

    View view;
    public static ItemThreeFragment newInstance() {
        ItemThreeFragment fragment = new ItemThreeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_item_three, container, false);

        btn_dimens = (Button)view.findViewById(R.id.btn_dimens);
        textViewWidth = (TextView) view.findViewById(R.id.TextViewWidth);
        textViewHeight = (TextView) view.findViewById(R.id.TextViewHeight);
        textWidthPixels = (TextView) view.findViewById(R.id.TextViewWidthPixels);
        textHeightPixels = (TextView) view.findViewById(R.id.TextViewHeightPixels);
        textDensityDpi = (TextView) view.findViewById(R.id.TextViewDensityDpi);
        textXdpi = (TextView) view.findViewById(R.id.TextViewXdpi);
        textYdpi = (TextView) view.findViewById(R.id.TextViewYdpi);
        textScreenHeight = (TextView) view.findViewById(R.id.TextViewScreenHeight);
        textScreenWidth = (TextView) view.findViewById(R.id.TextViewScreenWidth);
        textOrientation = (TextView) view.findViewById(R.id.TextViewOrientation);

        btn_dimens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dimens();
                displayDimens();
            }
        });

        return view;
    }

    public void displayDimens(){
        textViewWidth.setText("Width "+String.valueOf(width));
        textViewWidth.setVisibility(View.VISIBLE);

        textViewHeight.setText("Height "+String.valueOf(height));
        textViewHeight.setVisibility(View.VISIBLE);

        textWidthPixels.setText("widthPixels "+String.valueOf(widthPixels));
        textWidthPixels.setVisibility(View.VISIBLE);

        textHeightPixels.setText("heightPixels "+String.valueOf(heightPixels));
        textHeightPixels.setVisibility(View.VISIBLE);

        textDensityDpi.setText("densityDpi "+String.valueOf(densityDpi));
        textDensityDpi.setVisibility(View.VISIBLE);

        textXdpi.setText("xdpi "+String.valueOf(xdpi));
        textXdpi.setVisibility(View.VISIBLE);

        textYdpi.setText("ydpi "+String.valueOf(ydpi));
        textYdpi.setVisibility(View.VISIBLE);

        textScreenHeight.setText("screenHeight "+String.valueOf(screenHeight));
        textScreenHeight.setVisibility(View.VISIBLE);

        textScreenWidth.setText("screenWidth "+String.valueOf(screenWidth));
        textScreenWidth.setVisibility(View.VISIBLE);

        textOrientation.setText("orientation "+String.valueOf(orientation));
        textOrientation.setVisibility(View.VISIBLE);
    }

    public void dimens(){
        Display display = getActivity().getWindowManager().getDefaultDisplay();

        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;
        Log.i("dimens", "width        = " + width);
        Log.i("dimens", "height       = " + height);

        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
         heightPixels = metrics.heightPixels;
         widthPixels = metrics.widthPixels;
         densityDpi = metrics.densityDpi;
         xdpi = metrics.xdpi;
         ydpi = metrics.ydpi;
        Log.i("dimens", "widthPixels  = " + widthPixels);
        Log.i("dimens", "heightPixels = " + heightPixels);
        Log.i("dimens", "densityDpi   = " + densityDpi);
        Log.i("dimens", "xdpi         = " + xdpi);
        Log.i("dimens", "ydpi         = " + ydpi);

         screenHeight = display.getHeight();
         screenWidth = display.getWidth();
        Log.i("dimens", "screenHeight = " + screenHeight);
        Log.i("dimens", "screenWidth  = " + screenWidth);


          orientation = getResources().getConfiguration().orientation;
        Log.i("dimens", "orientation  = " + orientation);
    }
}
