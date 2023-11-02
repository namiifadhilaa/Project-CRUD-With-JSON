package com.example.json;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast; // Import untuk Toast
import org.json.JSONException;
import org.json.JSONObject;
import android.text.TextUtils; // Import untuk TextUtils

public class MainActivity extends AppCompatActivity {

    String JSON_STRING = "{\"pegawai\":{\"nama\":\"Najmi Fadhila Atsari\",\"Gaji\":10000000}}";
    String nama, gaji;
    TextView namaPegawai, gajiPegawai;
    EditText editNama, editGaji;
    Button updateButton, createButton, deleteButton; // Menambahkan tombol "Delete"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        namaPegawai = findViewById(R.id.nama);
        gajiPegawai = findViewById(R.id.gaji);
        editNama = findViewById(R.id.editNama);
        editGaji = findViewById(R.id.editGaji);
        updateButton = findViewById(R.id.updateButton);
        createButton = findViewById(R.id.createButton);
        deleteButton = findViewById(R.id.deleteButton); // Menginisialisasi tombol "Delete"

        try {
            JSONObject obj = new JSONObject(JSON_STRING);
            JSONObject pegawai = obj.getJSONObject("pegawai");
            nama = pegawai.getString("nama");
            gaji = String.valueOf(pegawai.getInt("Gaji"));
            namaPegawai.setText("Nama: " + nama);
            gajiPegawai.setText("Gaji: " + gaji);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        updateButton.setOnClickListener(view -> {
            // Mengambil data yang dimasukkan oleh pengguna
            String newNama = editNama.getText().toString();
            String gajiInput = editGaji.getText().toString();

            if (TextUtils.isDigitsOnly(gajiInput)) {
                int newGaji = Integer.parseInt(gajiInput);

                // Mengupdate data JSON
                try {
                    JSONObject obj = new JSONObject(JSON_STRING);
                    JSONObject pegawai = obj.getJSONObject("pegawai");
                    pegawai.put("nama", newNama);
                    pegawai.put("Gaji", newGaji);

                    // Menampilkan data yang diperbarui
                    nama = newNama;
                    gaji = String.valueOf(newGaji);
                    namaPegawai.setText("Nama: " + nama);
                    gajiPegawai.setText("Gaji: " + gaji);

                    JSON_STRING = obj.toString();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                // Pesan kesalahan jika string gaji tidak valid
                Toast.makeText(this, "Gaji harus berupa angka.", Toast.LENGTH_SHORT).show();
            }
        });


        createButton.setOnClickListener(view -> {
            // Mengambil data yang dimasukkan oleh pengguna untuk data baru
            String newNama = editNama.getText().toString();
            int newGaji = Integer.parseInt(editGaji.getText().toString());

            // Membuat data JSON baru
            try {
                JSONObject obj = new JSONObject();
                JSONObject pegawai = new JSONObject();
                pegawai.put("nama", newNama);
                pegawai.put("Gaji", newGaji);
                obj.put("pegawai", pegawai);

                // Menampilkan data baru
                nama = newNama;
                gaji = String.valueOf(newGaji);
                namaPegawai.setText("Nama: " + nama);
                gajiPegawai.setText("Gaji: " + gaji);

                JSON_STRING = obj.toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });

        deleteButton.setOnClickListener(view -> {
            // Menghapus data JSON dengan membuat objek JSON kosong
            try {
                JSONObject obj = new JSONObject("{}");

                JSON_STRING = obj.toString();

                // Menampilkan data yang diperbarui
                nama = "";
                gaji = "";
                namaPegawai.setText("Nama: ");
                gajiPegawai.setText("Gaji: ");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });

    }
}
