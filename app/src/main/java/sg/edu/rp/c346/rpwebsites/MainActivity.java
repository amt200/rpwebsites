package sg.edu.rp.c346.rpwebsites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
Spinner spnCategory, spnSubCategory;
Button btnGo;
String[] arrCategoryNames;
String[] arrSubCateNames;
ArrayList<String> alCategoryNames;
ArrayList<String> subCategoryNames;
ArrayAdapter<String> aaCategoryNames;
ArrayAdapter<String> aaSubCateNames;

    @Override
    protected void onPause() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        SharedPreferences.Editor editPref = preferences.edit();
        editPref.putInt("categoryIndex",spnCategory.getSelectedItemPosition());
        editPref.putInt("subCategoryIndex", spnSubCategory.getSelectedItemPosition());
        super.onPause();
    }

    @Override
    protected void onResume() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        int cateIndex = preferences.getInt("categoryIndex",0);
        int subCateIndex = preferences.getInt("subCategoryIndex",0);
        spnCategory.setSelection(cateIndex);
        spnSubCategory.setSelection(subCateIndex);
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spnCategory = findViewById(R.id.spinnerCate);
        spnSubCategory = findViewById(R.id.spinnerSubCate);
        btnGo = findViewById(R.id.buttonGo);
        alCategoryNames = new ArrayList<String>();
        subCategoryNames = new ArrayList<String>();

        arrCategoryNames = getResources().getStringArray(R.array.category);
        arrSubCateNames = getResources().getStringArray(R.array.subCategoryRP);

        alCategoryNames.addAll(Arrays.asList(arrCategoryNames));
        subCategoryNames.addAll(Arrays.asList(arrSubCateNames));

        aaSubCateNames = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, subCategoryNames);
        aaCategoryNames = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, alCategoryNames);

        spnCategory.setAdapter(aaCategoryNames);
        spnSubCategory.setAdapter(aaSubCateNames);

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "";
                Intent it = new Intent(MainActivity.this, WebDisplay.class);
                if(spnCategory.getSelectedItemPosition() == 0) {
                    if (spnSubCategory.getSelectedItemPosition() == 0) {
                        url = "https://www.rp.edu.sg/";
                    }
                    else {
                        url = "https://www.rp.edu.sg/student-life";
                    }
                }
                else{
                    if(spnSubCategory.getSelectedItemPosition() == 0){
                        url = "https://www.rp.edu.sg/soi/full-time-diplomas/details/r47";
                    }
                    else {
                        url = "https://www.rp.edu.sg/soi/full-time-diplomas/details/r12";
                    }
                }
                it.putExtra("urlName",url);
                startActivity(it);
            }
        });
        spnCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        subCategoryNames.clear();
                        arrSubCateNames = getResources().getStringArray(R.array.subCategoryRP);
                        subCategoryNames.addAll(Arrays.asList(arrSubCateNames));
                        aaSubCateNames.notifyDataSetChanged();
                        break;
                    case 1:
                        subCategoryNames.clear();
                        arrSubCateNames = getResources().getStringArray(R.array.subCategorySOI);
                        subCategoryNames.addAll(Arrays.asList(arrSubCateNames));
                        aaSubCateNames.notifyDataSetChanged();
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
