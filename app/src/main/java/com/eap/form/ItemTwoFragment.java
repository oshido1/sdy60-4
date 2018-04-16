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

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;

public class ItemTwoFragment extends Fragment implements PopupMenu.OnMenuItemClickListener{
    Button btn_displayClock, btn_browseInternet;

    View view;

    public static ItemTwoFragment newInstance() {
        ItemTwoFragment fragment = new ItemTwoFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_item_two, container, false);
        btn_displayClock = (Button) view.findViewById(R.id.btn_displayClock);
        btn_browseInternet = (Button)view.findViewById(R.id.btn_browseInternet);

        btn_displayClock.setOnLongClickListener(new View.OnLongClickListener() {
            /** Instantiating PopupMenu class */
                @Override
            public boolean onLongClick(View v) {
                    PopupMenu popupMenu = new PopupMenu(getActivity(), view);
                    popupMenu.setOnMenuItemClickListener(ItemTwoFragment.this);
                    popupMenu.inflate(R.menu.fragment2popup);
                    popupMenu.show();
                    return true;
            }
        });

        btn_browseInternet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.eap.gr/el/"));
                startActivity(browserIntent);
            }
        });

        return view;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.digital_clock:
                Intent intent = new Intent(getActivity(), DigitalClockActivity.class);
                startActivity(intent);
                return true;
            case R.id.analog_clock:
                Intent intent2 = new Intent(getActivity(), AnalogClockActivity.class);
                startActivity(intent2);
                return true;
            default:
                return false;
        }
    }
}
