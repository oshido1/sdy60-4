package com.eap.form;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.eap.database.FormDatabaseAdapter;
import com.eap.model.Form;


public class ItemOneFragment extends Fragment implements PopupMenu.OnMenuItemClickListener,
                                                        AdapterView.OnItemSelectedListener{


    public static final String mypreference = "myLocation";
    public static final String LAT = "Lat";
    public static final String LNG = "Lng";
    public static final String COUNTRY = "Cnt";
    int MYCODE=1000;

    SharedPreferences sharedpreferences;
    FormDatabaseAdapter formDatabaseAdapter;

    EditText  name, mitroo, epidosi,  editTextCountry, allo,editTextMtk ;
    Button button , button1 ,button2;
    Spinner spinner, spinner2, spinnerSxoli1, spinnerUni, spinnerRes, spinnerOrg;
    TextView labelSpinner1, labelSpinner2, tmima, onoma_forea;
    View mView;
    RelativeLayout rl, rl1;

    Form form;

    public static ItemOneFragment newInstance() {
        ItemOneFragment fragment = new ItemOneFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         mView  =inflater.inflate(R.layout.fragment_item_one, container, false);

         name = (EditText) mView.findViewById(R.id.editText1);
         mitroo= (EditText) mView.findViewById(R.id.editText2);
         tmima= (TextView) mView.findViewById(R.id.labelSxoli);
         epidosi = (EditText) mView.findViewById(R.id.editText4);
         allo= (EditText) mView.findViewById(R.id.editText5);
         onoma_forea= (TextView) mView.findViewById(R.id.editText7);
         editTextMtk = (EditText) mView.findViewById(R.id.editTextMtk);

         button = (Button)mView.findViewById(R.id.button);
         button1 = (Button)mView.findViewById(R.id.btn_metakinisi);
         button2 = (Button)mView.findViewById(R.id.btn_foreas);
         spinner = (Spinner)mView.findViewById(R.id.editText6);
         spinner2 = (Spinner)mView.findViewById(R.id.editText9);
        // spinnerSxoli = (Spinner)mView.findViewById(R.id.spinnerSxoli);
         spinnerSxoli1 = (Spinner)mView.findViewById(R.id.spinnerSxoli1);
         labelSpinner1 = (TextView) mView.findViewById(R.id.labelSpinner1);
         labelSpinner2 = (TextView) mView.findViewById(R.id.labelSpinner2);
         editTextCountry = (EditText) mView.findViewById(R.id.editTextCountry);

         spinnerUni = (Spinner) mView.findViewById(R.id.spinnerUni);
         spinnerOrg = (Spinner) mView.findViewById(R.id.spinnerOrg);
         spinnerRes = (Spinner) mView.findViewById(R.id.spinnerRes);

        rl = (RelativeLayout) mView.findViewById(R.id.relLayout1);

         spinner2.setOnItemSelectedListener(this);
         spinner.setOnItemSelectedListener(this);
       //   spinnerSxoli.setOnItemSelectedListener(this);
         spinnerSxoli1.setOnItemSelectedListener(this);

        spinnerUni.setOnItemSelectedListener(this);
        spinnerRes.setOnItemSelectedListener(this);
        spinnerOrg.setOnItemSelectedListener(this);

         form = new Form();

        // create the instance of Databse
         formDatabaseAdapter = new FormDatabaseAdapter(getActivity().getApplicationContext());
         formDatabaseAdapter = formDatabaseAdapter.open();
         ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),R.array.spinner1, R.layout.spinner_item);
         spinner.setAdapter(adapter);

         ArrayAdapter adapter2 = ArrayAdapter.createFromResource(getActivity(),R.array.spinner2, R.layout.spinner_item);
         spinner2.setAdapter(adapter2);

        ArrayAdapter adapterUni = ArrayAdapter.createFromResource(getActivity(),R.array.spinnerUni, R.layout.spinner_item);
        spinnerUni.setAdapter(adapterUni);

        ArrayAdapter adapterOrg = ArrayAdapter.createFromResource(getActivity(),R.array.spinnerOrg, R.layout.spinner_item);
        spinnerOrg.setAdapter(adapterOrg);

        ArrayAdapter adapterRes = ArrayAdapter.createFromResource(getActivity(),R.array.spinnerRes, R.layout.spinner_item);
        spinnerRes.setAdapter(adapterRes);

        ArrayAdapter adapter4 = ArrayAdapter.createFromResource(getActivity(),R.array.spinner4, R.layout.spinner_item);
        spinnerSxoli1.setAdapter(adapter4);

        mView.findViewById(R.id.btn_erasmus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(getActivity(), view);
                popupMenu.setOnMenuItemClickListener(ItemOneFragment.this);
                popupMenu.inflate(R.menu.popup_menu);
                popupMenu.show();
            }
        });

        mView.findViewById(R.id.btn_metakinisi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(getActivity(), view);
                popupMenu.setOnMenuItemClickListener(ItemOneFragment.this);
                popupMenu.inflate(R.menu.popup_foreas);
                popupMenu.show();
            }
        });
        layout(0);


        mView.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == button) {
                    submitForm();
                }
            }
        });

        mView.findViewById(R.id.btn_foreas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == button2) {
                    createMap();
                }
            }
        });
        sharedpreferences = getActivity().getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        return mView;
    }

    /**
     * Start new Activity in ordr to show the map
     */
    private void createMap(){
        Intent intent = new Intent(getActivity(), MapActivity.class);
        startActivityForResult(intent, MYCODE);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Result OK.d.
        String country="";
        if (requestCode == MYCODE) {
            // do something good
            Log.d("onActivityResult",":");
            if (sharedpreferences.contains(COUNTRY)) {
                country = sharedpreferences.getString(COUNTRY, "empty");
            }
            editTextCountry.setText(country);
            editTextCountry.setVisibility(View.VISIBLE);
        }
    }
    private void submitForm() {
        int value = valid();
        if(value == 0){
            readForm();
            formDatabaseAdapter.insertEntry(form);
            Log.d("submitForm","form:"+form.toString());
        }
    }

    private void readForm(){
        Double lat = 0.0, lng = 0.0;
        String country="";


        if (sharedpreferences.contains(LAT)) {
            Log.d("submitForm", "1 LAT"+sharedpreferences.getFloat(LAT, 0));
            lat = Double.valueOf(sharedpreferences.getFloat(LAT, 0));
        }
        if (sharedpreferences.contains(LNG)) {
            Log.d("submitForm", "1 LNG"+sharedpreferences.getFloat(LNG, 0));
            lng = Double.valueOf(sharedpreferences.getFloat(LNG, 0));
        }

        if (sharedpreferences.contains(COUNTRY)) {
            country = sharedpreferences.getString(COUNTRY, "empty");
        }

        form.setName(name.getText().toString());
        form.setMitroo(Integer.parseInt((mitroo.getText().toString().isEmpty()?"0":mitroo.getText().toString())));
        form.setEpidosi(Double.valueOf(epidosi.getText().toString()));
        form.setCountry_lat(lat);
        form.setCountry_lng(lng);
        form.setCountry(country);
        form.setTmima(spinnerSxoli1.getSelectedItem().toString());
        form.setForeas_ipodoxis(spinner.getSelectedItem().toString());
        form.setMetakinisi(spinner2.getSelectedItem().toString());

        if(form.getKat_metakinisis() == null){
            form.setKat_metakinisis(allo.getText().toString());
        }
    }

    private int valid(){
        if(epidosi.getText().toString()!=null){
            String name = epidosi.getText().toString();
            if (name.matches("[0-9]+([,.][0-9]{1,2})?")) {
                double num = Double.valueOf(name);
                if (num>4 && num<9){
                    Log.d("num", "1 num"+num);
                } else {
                    epidosi.setError(" Not matched");
                    epidosi.requestFocus();
                    return 1;
                }
            } else {
                epidosi.setError(" Not matched");
                epidosi.requestFocus();
                return 1;
            }
        }
        if(mitroo.getText().toString()==null || mitroo.getText().toString().isEmpty()){
            mitroo.setError(" Not matched");
            mitroo.requestFocus();
            return 1;
        }
        if(name.getText().toString()==null|| name.getText().toString().isEmpty()){
            name.setError(" Not matched");
            name.requestFocus();
            return 1;
        }
        return 0;
    }


    public void layout(int k){
        if (k==0){
            rl.setVisibility(View.INVISIBLE);

        }
        if (k==1){
            rl.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        String spoudes ="";
        String praktiki = "";
        switch (item.getItemId()) {
            case R.id.item_stoixeia:
                String stoixeia = getActivity().getString(R.string.text_stoixeia);
                layout(1);
                return true;
            case R.id.item_aitisi:
                String aitisi = getActivity().getString(R.string.text_aitisi);
                layout(0);
                Intent intent = new Intent(getActivity(), FormActivity.class);
                startActivity(intent);
                allo.setVisibility(View.GONE);
                editTextCountry.setVisibility(View.GONE);
                editTextMtk.setVisibility(View.GONE);
                return true;
            case R.id.item_diagrafi:
                String diagrafi = getActivity().getString(R.string.text_diagrafi);
                Intent intentDl = new Intent(getActivity(), DeleteActivity.class);
                startActivity(intentDl);
                layout(0);
                allo.setVisibility(View.GONE);
                editTextCountry.setVisibility(View.GONE);
                editTextMtk.setVisibility(View.GONE);
                return true;
            case R.id.item_spoudes:
                return true;
            case R.id.item_praktiki:
                return true;
            case R.id.item_allo:
                allo.setVisibility(View.VISIBLE);
                editTextMtk.setVisibility(View.GONE);
                return true;
            case R.id.item_mathimata:
                spoudes = getActivity().getString(R.string.add_mathimata);
                editTextMtk.setVisibility(View.VISIBLE);
                allo.setVisibility(View.GONE);
                editTextMtk.setText(spoudes);
                form.setKat_metakinisis(spoudes);
                return true;
            case R.id.item_ptixiaki:
                spoudes = getActivity().getString(R.string.add_ptixiaki);
                form.setKat_metakinisis(spoudes);
                editTextMtk.setVisibility(View.VISIBLE);
                allo.setVisibility(View.GONE);
                editTextMtk.setText(spoudes);
                return true;
            case R.id.item_praktikiAskisi:
                praktiki = getActivity().getString(R.string.add_praktiki_askisi);
                form.setKat_metakinisis(praktiki);
                editTextMtk.setVisibility(View.VISIBLE);
                allo.setVisibility(View.GONE);
                editTextMtk.setText(praktiki);
                return true;
            case R.id.item_after_placement:
                praktiki = getActivity().getString(R.string.add_after_placement);
                form.setKat_metakinisis(praktiki);
                editTextMtk.setVisibility(View.VISIBLE);
                allo.setVisibility(View.GONE);
                editTextMtk.setText(praktiki);
                return true;
        }

        return false;
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
         // On selecting a spinner item
        if(parent.getItemAtPosition(pos).toString().matches("Πανεπιστήμιο")){
            spinnerUni.setVisibility(View.VISIBLE);
            spinnerRes.setVisibility(View.GONE);
            spinnerOrg.setVisibility(View.GONE);

        } else if (parent.getItemAtPosition(pos).toString().matches("Ερευνητικό Κέντρο")){
            spinnerRes.setVisibility(View.VISIBLE);
            spinnerOrg.setVisibility(View.GONE);
            spinnerUni.setVisibility(View.GONE);
        } else if (parent.getItemAtPosition(pos).toString().matches("Επιχείρηση")){
            spinnerOrg.setVisibility(View.VISIBLE);
            spinnerUni.setVisibility(View.GONE);
            spinnerRes.setVisibility(View.GONE);
        }
        if(View.VISIBLE == spinnerOrg.getVisibility()){
            Log.d("onItemSelected","spinnerOrg:"+spinnerOrg.getSelectedItem().toString());
            form.setForeas(spinnerOrg.getSelectedItem().toString());
        }
        if(View.VISIBLE == spinnerUni.getVisibility()){
            Log.d("onItemSelected","spinnerUni:"+spinnerUni.getSelectedItem().toString());
            form.setForeas(spinnerUni.getSelectedItem().toString());
        }
        if(View.VISIBLE == spinnerRes.getVisibility()){
            Log.d("onItemSelected","spinnerRes:"+spinnerRes.getSelectedItem().toString());
            form.setForeas(spinnerRes.getSelectedItem().toString());
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
