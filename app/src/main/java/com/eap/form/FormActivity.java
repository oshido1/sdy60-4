package com.eap.form;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.eap.database.FormDatabaseAdapter;
import com.eap.model.Model;

import java.util.ArrayList;

public class FormActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,View.OnClickListener{
    private ArrayList<Model> modeList;
    private int position = 0;
    FormDatabaseAdapter formDatabaseAdapter;
    Spinner search_spinner;
    Button btn_search;
    EditText searchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_activity);

        modeList = new ArrayList<Model>();
        btn_search = (Button)findViewById(R.id.btn_search);
        searchText = (EditText) findViewById(R.id.searchText);

        search_spinner = (Spinner) findViewById(R.id.search_spinner);
        search_spinner.setOnItemSelectedListener(this);

        ArrayAdapter adapterAr = ArrayAdapter.createFromResource(this,R.array.spinner3, R.layout.spinner_item);
        search_spinner.setAdapter(adapterAr);

        formDatabaseAdapter = new FormDatabaseAdapter(getApplicationContext());
        formDatabaseAdapter = formDatabaseAdapter.open();


        findViewById(R.id.btn_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == btn_search) {
                    searchButton();
                }
            }
        });
    }
    private void searchButton(){
        modeList.clear();
        if(searchText.getText().toString()!=null){
            String txt = searchText.getText().toString();
            Log.d("searchButton","txt:"+txt +" position:"+position);
            switch (position) {
                case 0:
                    populateList();
                    break;
                case 1:
                    populateListByName(txt);
                    break;
                case 2:
                    populateListByMoveCategory(txt);
                    break;
                case 3:
                    populateListByForea(txt);
                    break;
                case 4:
                    populateListByOldMove(txt);
                    break;
                case 5:
                    break;
            }
            addHeaders();
            addData();
        } else {
            searchText.setError(" Please type something");
            searchText.requestFocus();
        }
    }


    private void populateList() {
        modeList = new ArrayList<Model>();
        modeList.addAll(formDatabaseAdapter.getAllByGrade());
    }

    private void populateListByName(String name) {
        modeList = new ArrayList<Model>();
        modeList.addAll(formDatabaseAdapter.getByName(name));
    }

    private void populateListByMoveCategory(String category) {
        modeList = new ArrayList<Model>();
        modeList.addAll(formDatabaseAdapter.getByCategory(category));
    }

    private void populateListByForea(String foreas) {
        modeList = new ArrayList<Model>();
        modeList.addAll(formDatabaseAdapter.getByForea(foreas));
    }

    private void populateListByOldMove(String metakinisi) {
        modeList = new ArrayList<Model>();
        modeList.addAll(formDatabaseAdapter.getByOldMove(metakinisi));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
      //  getMenuInflater().inflate(R.menu.menu_form, menu);
        return true;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        position = pos;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private TextView getTextView(int id, String title, int color, int typeface, int bgColor) {
        TextView tv = new TextView(this);
        tv.setId(id);
        tv.setText(title.toUpperCase());
        tv.setTextColor(color);
        tv.setPadding(40, 40, 40, 40);
        tv.setTypeface(Typeface.DEFAULT, typeface);
        tv.setBackgroundColor(bgColor);
        tv.setLayoutParams(getLayoutParams());
        tv.setOnClickListener(this);
        return tv;
    }
    @NonNull
    private TableRow.LayoutParams getLayoutParams() {
        TableRow.LayoutParams params = new TableRow.LayoutParams(
                TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.FILL_PARENT);
        params.setMargins(2, 0, 0, 2);
        return params;
    }

    @NonNull
    private TableLayout.LayoutParams getTblLayoutParams() {
        return new TableLayout.LayoutParams(
                TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.FILL_PARENT);
    }

    /**
     * This function add the headers to the table
     **/
    public void addHeaders() {
        TableLayout tl = (TableLayout) findViewById(R.id.table);
        TableRow tr = new TableRow(this);
        tr.setLayoutParams(getLayoutParams());
        tr.addView(getTextView(0, getResources().getString(R.string.Add_name), Color.WHITE, Typeface.BOLD, Color.BLUE));
        tr.addView(getTextView(0, getResources().getString(R.string.add_mitroo), Color.WHITE, Typeface.BOLD, Color.BLUE));
        tr.addView(getTextView(0, getResources().getString(R.string.add_tmima), Color.WHITE, Typeface.BOLD, Color.BLUE));
        tr.addView(getTextView(0, getResources().getString(R.string.add_epidosi), Color.WHITE, Typeface.BOLD, Color.BLUE));
        tr.addView(getTextView(0, getResources().getString(R.string.add_metakinisi), Color.WHITE, Typeface.BOLD, Color.BLUE));
        tr.addView(getTextView(0, getResources().getString(R.string.add_forea), Color.WHITE, Typeface.BOLD, Color.BLUE));
        tr.addView(getTextView(0, getResources().getString(R.string.add_onoma_forea), Color.WHITE, Typeface.BOLD, Color.BLUE));
        tr.addView(getTextView(0, getResources().getString(R.string.add_xora_forea), Color.WHITE, Typeface.BOLD, Color.BLUE));
        tr.addView(getTextView(0, getResources().getString(R.string.add_metakinisi_parelthon), Color.WHITE, Typeface.BOLD, Color.BLUE));
        tl.addView(tr, getTblLayoutParams());
    }

    /**
     * This function add the data to the table
     **/
    public void addData() {
        int numEntries = modeList.size();
        TableLayout tl = (TableLayout) findViewById(R.id.table);
        tl.removeAllViews();
        addHeaders();
        Log.d("addData", "entries:"+modeList.toString());
        for (int i = 0; i < numEntries; i++) {
            TableRow tr = new TableRow(this);
            tr.setLayoutParams(getLayoutParams());
            tr.addView(getTextView(i + 1, modeList.get(i).getName(), Color.BLUE, Typeface.NORMAL, ContextCompat.getColor(this, R.color.yellowlight)));
            tr.addView(getTextView(i + numEntries, modeList.get(i).getMitroo(), Color.BLUE, Typeface.NORMAL, ContextCompat.getColor(this, R.color.yellowlight)));
            tr.addView(getTextView(i + numEntries, modeList.get(i).getTmima(), Color.BLUE, Typeface.NORMAL, ContextCompat.getColor(this, R.color.yellowlight)));
            tr.addView(getTextView(i + numEntries, modeList.get(i).getEpidosi(), Color.BLUE, Typeface.NORMAL, ContextCompat.getColor(this, R.color.yellowlight)));
            tr.addView(getTextView(i + numEntries, modeList.get(i).getMetakinisi(), Color.BLUE, Typeface.NORMAL, ContextCompat.getColor(this, R.color.yellowlight)));
            tr.addView(getTextView(i + numEntries, modeList.get(i).getForeas(), Color.BLUE, Typeface.NORMAL, ContextCompat.getColor(this, R.color.yellowlight)));
            tr.addView(getTextView(i + numEntries, modeList.get(i).getForeas_ipodoxis(), Color.BLUE, Typeface.NORMAL, ContextCompat.getColor(this, R.color.yellowlight)));
            tr.addView(getTextView(i + numEntries, modeList.get(i).getCountry(), Color.BLUE, Typeface.NORMAL, ContextCompat.getColor(this, R.color.yellowlight)));
            tr.addView(getTextView(i + numEntries, modeList.get(i).getKat_metakinisis(), Color.BLUE, Typeface.NORMAL, ContextCompat.getColor(this, R.color.yellowlight)));
            tl.addView(tr, getTblLayoutParams());
        }
    }

    @Override
    public void onClick(View v) {

    }
}
