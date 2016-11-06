package com.nnayram.uiandroid.paginatedtable;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.nnayram.uiandroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Source http://bit.ly/2eAZVS4
 * Credits to Sherwin14 and Simon Brown for Pageable class
 */
public class PaginatedTableActivity extends AppCompatActivity implements View.OnClickListener {

    private TableLayout tblMain;
    private TableRow trHeader;
    private TextView tvRowId;
    private TextView tvPageCount;

    private TableRow trRow;
    private TextView tvRow;
    private LinearLayout lytLineSeparator;

    private Pageable<Person> pageableArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pt_activity_main);

        // init components
        tblMain = (TableLayout) findViewById(R.id.tbl_main);
        trHeader = (TableRow) findViewById(R.id.tr_header);
        tvRowId = (TextView) findViewById(R.id.tr_tv_id);
        tvPageCount = (TextView) findViewById(R.id.tv_page_count);
        Button btnPrev = (Button) findViewById(R.id.btn_prev);
        Button btnNext = (Button) findViewById(R.id.btn_next);

        // init listener
        btnNext.setOnClickListener(this);
        btnPrev.setOnClickListener(this);

        // init sample values
        List<Person> sampleList = new ArrayList<>();
        for(int i=0; i<= 55; i++){
            sampleList.add(new Person(i,"Sample" + i,"G","Sample" + i));
        }

        // init Pageable, pass values from sampleList
        pageableArray = new Pageable<>(sampleList);
        pageableArray.setPageSize(10);
        pageableArray.setPage(1);
        tvPageCount.setText("Page " + pageableArray.getPage() + " of " + pageableArray.getMaxPages());
        displayPage();
    }

    public void displayPage() {
        tblMain.removeAllViews();
        tblMain.addView(trHeader);
        for (Person v : pageableArray.getListForPage()) {
            trRow = new TableRow(this);
            trRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));

            tvRow = new TextView(this);
            tvRow.setLayoutParams(tvRowId.getLayoutParams());
            tvRow.setGravity(Gravity.CENTER_HORIZONTAL);
            tvRow.setText(String.valueOf(v.get_id()));
            trRow.addView(tvRow);

            tvRow = new TextView(this);
            tvRow.setLayoutParams(tvRowId.getLayoutParams());
            tvRow.setGravity(Gravity.CENTER_HORIZONTAL);
            tvRow.setText(v.get_givenName());
            trRow.addView(tvRow);

            tvRow = new TextView(this);
            tvRow.setLayoutParams(tvRowId.getLayoutParams());
            tvRow.setGravity(Gravity.CENTER_HORIZONTAL);
            tvRow.setText(v.get_middleName());
            trRow.addView(tvRow);

            tvRow = new TextView(this);
            tvRow.setLayoutParams(tvRowId.getLayoutParams());
            tvRow.setGravity(Gravity.CENTER_HORIZONTAL);
            tvRow.setText(v.get_surName());
            trRow.addView(tvRow);

            lytLineSeparator = new LinearLayout(this);
            lytLineSeparator.setOrientation(LinearLayout.VERTICAL);
            lytLineSeparator.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 2));
            lytLineSeparator.setBackgroundColor(Color.parseColor("#5e7974"));

            tblMain.addView(trRow);
            tblMain.addView(lytLineSeparator);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next:
                pageableArray.setPage(pageableArray.getNextPage());
                trRow.removeAllViews();
                displayPage();
                tvPageCount.setText("Page " + pageableArray.getPage() + " of " + pageableArray.getMaxPages());
                break;

            case R.id.btn_prev:
                pageableArray.setPage(pageableArray.getPreviousPage());
                trRow.removeAllViews();
                displayPage();
                tvPageCount.setText("Page " + pageableArray.getPage() + " of " + pageableArray.getMaxPages());
                break;
        }
    }
}
