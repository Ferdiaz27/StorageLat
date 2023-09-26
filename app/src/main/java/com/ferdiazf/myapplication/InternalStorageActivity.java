package com.ferdiazf.myapplication;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class InternalStorageActivity extends AppCompatActivity {

    private EditText editText;
    private Button createButton, readButton, updateButton, deleteButton;
    private String fileName = "internal_data.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_storage);

        editText = findViewById(R.id.editText);
        createButton = findViewById(R.id.createButton);
        readButton = findViewById(R.id.readButton);
        updateButton = findViewById(R.id.updateButton);
        deleteButton = findViewById(R.id.deleteButton);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createFile();
            }
        });

        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readFile();
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateFile();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteFile();
            }
        });
    }

    private void createFile() {
        String data = editText.getText().toString();
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(data.getBytes());
            outputStream.close();
            showToast("File berhasil dibuat.");
        } catch (Exception e) {
            e.printStackTrace();
            showToast("Gagal membuat file.");
        }
    }

    private void readFile() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(openFileInput(fileName)));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            reader.close();
            showToast("Isi file:\n" + stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
            showToast("Gagal membaca file.");
        }
    }

    private void updateFile() {
        String data = editText.getText().toString();
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(data.getBytes());
            outputStream.close();
            showToast("File berhasil diperbarui.");
        } catch (Exception e) {
            e.printStackTrace();
            showToast("Gagal memperbarui file.");
        }
    }

    private void deleteFile() {
        File file = new File(getFilesDir(), fileName);
        if (file.delete()) {
            showToast("File berhasil dihapus.");
        } else {
            showToast("Gagal menghapus file.");
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
