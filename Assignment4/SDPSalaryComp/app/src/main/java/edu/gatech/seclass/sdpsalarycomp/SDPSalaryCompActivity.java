package edu.gatech.seclass.sdpsalarycomp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class SDPSalaryCompActivity extends AppCompatActivity {

    private Spinner currentCity;
    private Spinner newCity;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> cities;
    private EditText baseSalary;
    private TextView targetSalary;
    private ArrayList<Integer> prices;
    private int currentIndex;
    private int targetIndex;
    private int target;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sdpsalary_comp);

        Spinner currentCity = (Spinner) findViewById(R.id.currentCity);
        Spinner newCity = (Spinner) findViewById(R.id.newCity);
        baseSalary = (EditText) findViewById(R.id.baseSalary);
        targetSalary = (TextView) findViewById(R.id.targetSalary);

        cities = new ArrayList<String>();
        cities.add("Atlanta, GA");
        cities.add("Austin, TX");
        cities.add("Boston, MA");
        cities.add("Honolulu, HI");
        cities.add("Las Vegas, NV");
        cities.add("Mountain View, CA");
        cities.add("New York City, NY");
        cities.add("San Francisco, CA");
        cities.add("Seattle, WA");
        cities.add("Springfield, MO");
        cities.add("Tampa, FL");
        cities.add("Washington D.C.");

        prices = new ArrayList<Integer>(Arrays.asList(160, 152, 197, 201, 153, 244, 232, 241, 198, 114, 139, 217));

        adapter = new ArrayAdapter(SDPSalaryCompActivity.this, android.R.layout.simple_spinner_item, cities);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        currentCity.setAdapter(adapter);
        newCity.setAdapter(adapter);
        listener();}

        public void listener() {

            //listen the change of  Current City
            currentCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    convertSalary( );                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            newCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    convertSalary( );                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            // for baseSalary, add textchangedListener
            baseSalary.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after){
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count){
            }

            @Override
            public void afterTextChanged(Editable s){
                convertSalary( );
            }
            });
        }

    public void convertSalary( ) {
        String baseSalaryStr = baseSalary.getText().toString().trim();
        Double tempSalary = Double.parseDouble(baseSalaryStr);
        int temp = tempSalary.intValue();
        if ((TextUtils.isEmpty(baseSalaryStr)) || (baseSalaryStr.contains("-"))||baseSalaryStr.contains(".")) {

            baseSalary.setError("Invalid salary");
            targetSalary.setText("");
        } else {
            currentIndex = Arrays.asList(cities).indexOf(currentCity);
            targetIndex = Arrays.asList(cities).indexOf(newCity);
            target = temp * (prices.get(targetIndex) / prices.get(currentIndex));
            targetSalary.setText(String.valueOf(Math.round(target)));
        }
    }
}
















