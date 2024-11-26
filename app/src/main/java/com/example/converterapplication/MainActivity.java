package com.example.converterapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText input;
    Spinner list1, list2;
    TextView output;
    Button convertBtn;
    String fromUnit, toUnit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        input = findViewById(R.id.input);
        list1 = findViewById(R.id.list1);
        list2 = findViewById(R.id.list2);
        output = findViewById(R.id.output);
        convertBtn = findViewById(R.id.convertBtn);

        showDropDown(list1);
        showDropDown(list2);

        convertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieve input value and perform conversion
                String inputText = input.getText().toString();

                if (inputText.isEmpty()) {
                    output.setText("Please enter a value to convert.");
                    return;
                }

                double inputValue = Double.parseDouble(inputText);
                double result = convert(inputValue, fromUnit, toUnit);

                // Display result with 2 decimal places
                output.setText(String.format("%.5f", result));
            }
        });
    }

    public void showDropDown(Spinner spinner) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(MainActivity.this, R.array.dropdown_item, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spinner == list1) {
                    fromUnit = adapterView.getItemAtPosition(i).toString();
                } else if (spinner == list2) {
                    toUnit = adapterView.getItemAtPosition(i).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    // Conversion logic
    public double convert(double value, String fromUnit, String toUnit) {
        // Conversion between the unit
        if (fromUnit.equals("Metre") && toUnit.equals("Millimetre")) {
            return value * 1000;
        } else if (fromUnit.equals("Millimetre") && toUnit.equals("Metre")) {
            return value / 1000;
        } else if (fromUnit.equals("Metre") && toUnit.equals("Mile")) {
            return value * 0.000621371;
        } else if (fromUnit.equals("Mile") && toUnit.equals("Metre")) {
            return value / 0.000621371;
        } else if (fromUnit.equals("Metre") && toUnit.equals("Foot")) {
            return value * 3.28084;
        } else if (fromUnit.equals("Foot") && toUnit.equals("Metre")) {
            return value / 3.28084;
        } else if (fromUnit.equals("Millimetre") && toUnit.equals("Mile")) {
            return value * 6.2137e-7;
        } else if (fromUnit.equals("Mile") && toUnit.equals("Millimetre")) {
            return value / 6.2137e-7;
        } else if (fromUnit.equals("Millimetre") && toUnit.equals("Foot")) {
            return value * 0.00328084;
        } else if (fromUnit.equals("Foot") && toUnit.equals("Millimetre")) {
            return value / 0.00328084;
        } else if (fromUnit.equals("Mile") && toUnit.equals("Foot")) {
            return value * 5280;
        } else if (fromUnit.equals("Foot") && toUnit.equals("Mile")) {
            return value / 5280;
        } else {
            return value;
        }
    }
}
