package com.example.lab6_b_room;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText txtInputLocation;
    private Button btnSave;
    private  Button btnCancel;

    private RecyclerView recyclerView;
    private LocationAdapter adapter;
    private List<Location> locations = new ArrayList<Location>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rcy_main_location);
        txtInputLocation = findViewById(R.id.edt_main_Location);
        btnSave = findViewById(R.id.btt_main_save);
        btnCancel = findViewById(R.id.btt_main_cancel);

        LocationDatabase db = Room.databaseBuilder(getApplicationContext(), LocationDatabase.class, "locationdb")
                .allowMainThreadQueries()
                .build();
        LocationDAO dao = db.locationDAO();
        locations = dao.getAll();
        if(locations.size() == 0){
            dao.addLocation(new Location("Đà Lạt"));
            dao.addLocation(new Location("Buôn Mê Thuộc"));
            dao.addLocation(new Location("Cần Thơ"));
            dao.addLocation(new Location("Phú Quốc"));
            dao.addLocation(new Location("Lý Sơn"));
            dao.addLocation(new Location("Cần Giờ"));
            dao.addLocation(new Location("Côn Đảo"));
            dao.addLocation(new Location("Vũng Tàu"));
            locations = dao.getAll();
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = String.valueOf(txtInputLocation.getText());
                dao.addLocation(new Location(name));
                locations = dao.getAll();
                adapter = new LocationAdapter(MainActivity.this, db);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(linearLayoutManager);
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtInputLocation.setText("");
                adapter.notifyDataSetChanged();
            }
        });

        adapter = new LocationAdapter(this, db);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}