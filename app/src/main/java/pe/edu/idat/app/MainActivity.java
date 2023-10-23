package pe.edu.idat.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import pe.edu.idat.app.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener{

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnirlistado.setOnClickListener(this);
        binding.btnirregistro.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnirlistado){
            startActivity(new Intent(MainActivity.this,
                    ListaActivity.class));
        } else if (view.getId() == R.id.btnirregistro){
            startActivity(new Intent(MainActivity.this,
                    RegistroActivity.class));
        }
    }
}
