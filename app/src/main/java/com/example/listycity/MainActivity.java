package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    Button addButton;
    Button deleteButton;
    EditText cityInput;
    int selectedIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);
        addButton = findViewById(R.id.add_button);
        deleteButton = findViewById(R.id.delete_button);
        cityInput = findViewById(R.id.city_input);

        String []cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Dehli"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, dataList);
        cityList.setAdapter(cityAdapter);

        // Tap to SELECT city
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedIndex = position;
            cityList.setItemChecked(position, true);
        });

        // ADD city
        addButton.setOnClickListener(v -> {
            String city = cityInput.getText().toString().trim();
            if (!city.isEmpty()) {
                dataList.add(city);
                cityAdapter.notifyDataSetChanged();
                cityInput.setText("");
            }
        });

        // DELETE selected city
        deleteButton.setOnClickListener(v -> {
            if (selectedIndex != -1) {
                dataList.remove(selectedIndex);
                cityAdapter.notifyDataSetChanged();
                cityList.clearChoices();
                selectedIndex = -1;
            }
        });
    }
}