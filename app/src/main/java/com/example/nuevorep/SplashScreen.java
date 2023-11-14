package com.example.nuevorep;

import android.content.Intent;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.nuevorep.activity.MainActivity;

public class SplashScreen extends AppCompatActivity {

    private static final int REQEST_CODE_PERMISSION =123 ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        //En esta actividad pediremos permiso de almacenamiento interno de lectura y escritura
        //Tambien se agrega el permiso en el manifests
        permission();



    }

    private void nextActivity() {
        startActivity(new Intent(SplashScreen.this, MainActivity.class));
        finish();
    }

    private void permission(){
        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(SplashScreen.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQEST_CODE_PERMISSION);
        }else {
            nextActivity();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQEST_CODE_PERMISSION){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(SplashScreen.this, "Permiso Concedido", Toast.LENGTH_SHORT).show();
                nextActivity();
            }else {
                Toast.makeText(SplashScreen.this, "Denegado", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(SplashScreen.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQEST_CODE_PERMISSION);

            }
        }
    }
}
