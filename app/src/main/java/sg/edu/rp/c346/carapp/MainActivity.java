package sg.edu.rp.c346.carapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnRetrieve;
    TextView tvResults;
    EditText etBrand, etLitre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.buttonInsert);
        btnRetrieve = findViewById(R.id.buttonRetrieve);
        tvResults = findViewById(R.id.textViewResult);
        etBrand = findViewById(R.id.editTextBrand);
        etLitre = findViewById(R.id.editTextLitre);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String brand = etBrand.getText().toString();
                double litre = Double.parseDouble(etLitre.getText().toString());
                DBHelper db = new DBHelper(MainActivity.this);
                db.insertCar(brand, litre);
                db.close();
            }
        });

        btnRetrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);
                ArrayList<Car> data = db.getCar();
                db.close();
                String txt = "";
                for (int i = 0; i < data.size(); i++) {
                    txt += "Car " + i + ": Brand - " + data.get(i).getBrand() + ", Litre - " + data.get(i).getLitre() + "\n" ;
                }
                tvResults.setText(txt);
            }
        });
    }
}
