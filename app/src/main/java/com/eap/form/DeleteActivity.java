package com.eap.form;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.eap.database.FormDatabaseAdapter;
import com.eap.model.Model;

import java.util.ArrayList;

/**
 * Created by skikos on 1/4/2018.
 */

public class DeleteActivity extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<Model> modeList;
    FormDatabaseAdapter formDatabaseAdapter;
    Button btn_delete;
    EditText deleteText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        modeList = new ArrayList<Model>();
        btn_delete = (Button)findViewById(R.id.btn_delete);
        deleteText = (EditText)findViewById(R.id.deleteText);

        formDatabaseAdapter = new FormDatabaseAdapter(getApplicationContext());
        formDatabaseAdapter = formDatabaseAdapter.open();

        populateList();
        addHeaders();
        addData();

        findViewById(R.id.btn_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == btn_delete) {
                    deleteMitroo();
                }
            }
        });
    }

    public void deleteMitroo(){
        if(deleteText.getText()!=null){
            String entry = deleteText.getText().toString();
            formDatabaseAdapter.deleteEntry(entry);
            populateList();
            addHeaders();
            addData();

        } else {
            deleteText.setError("Please add mitroo");
            deleteText.requestFocus();
        }
    }

    private void populateList() {
        modeList = new ArrayList<Model>();
        modeList.addAll(formDatabaseAdapter.getAllByGrade());
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
        TableLayout tl = (TableLayout) findViewById(R.id.tableView);
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
        TableLayout tl = (TableLayout) findViewById(R.id.tableView);
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
