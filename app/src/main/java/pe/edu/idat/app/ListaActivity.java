package pe.edu.idat.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import pe.edu.idat.app.databinding.ActivityListaBinding;
import pe.edu.idat.app.databinding.ActivityRegistroBinding;

public class ListaActivity extends AppCompatActivity {

    private ActivityListaBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ArrayList<String> listaPersonas = (ArrayList<String>)
                getIntent().getSerializableExtra("Listapersonas");
        ArrayAdapter arrayAdapter = new ArrayAdapter(
                this, android.R.layout.simple_list_item_1,
                listaPersonas
        );
        binding.lwpersonas.setAdapter(arrayAdapter);
    }
}

