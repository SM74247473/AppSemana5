package pe.edu.idat.app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pe.edu.idat.app.databinding.ActivityRegistroBinding;

public class RegistroActivity extends AppCompatActivity
 implements AdapterView.OnItemSelectedListener, View.OnClickListener{



    private ActivityRegistroBinding binding;
    private String estadocivil = "";

    private List<String> preferencias = new ArrayList<>();
    private List<String> personas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ArrayAdapter<CharSequence> adapterSpiner =
                ArrayAdapter.createFromResource(
                        this,
                        R.array.estado_civil,
                        android.R.layout.simple_spinner_item
                );

        binding.spestadocivil.setAdapter(adapterSpiner);
        binding.cbdeporte.setOnClickListener(this);
        binding.cbarte.setOnClickListener(this);
        binding.cbotros.setOnClickListener(this);
        binding.btnregistrar.setOnClickListener(this);
        binding.spestadocivil.setOnItemSelectedListener(this);
        binding.btnlistapersona.setOnClickListener(this);
    }

    private String obtenerGenero(){
        String genero = "";
        if (binding.rggenero.getCheckedRadioButtonId() == R.id.rbmasculino){
            genero = binding.rbmasculino.getText().toString();
        }else {
            genero = binding.rbfemenino.getText().toString();
        }
        return genero;
    }

    private void agregarQuitarPreferencias (View view,String preferencia){
        boolean checked = ((CheckBox)view).isChecked();
        if (checked)
            preferencias.add(preferencia);
        else preferencias.remove(preferencia);
    }

    private Boolean validarNombreApellido(){
        boolean respuesta = true;
        if (binding.etnombre.getText().toString().trim().isEmpty()){
            binding.etnombre.setFocusableInTouchMode(true);
            binding.etnombre.requestFocus();
            respuesta = false;
        } else if (binding.etapellido.getText().toString().trim().isEmpty()){
            binding.etapellido.setFocusableInTouchMode(true);
            binding.etapellido.requestFocus();
            respuesta = false;
        }
        return respuesta;
    }

    private Boolean validarGenero(){
        boolean respuesta = true;
        if (binding.rggenero.getCheckedRadioButtonId() == -1){
            respuesta = false;
        }
        return respuesta;
    }

    private Boolean validarPreferencias(){
        boolean respuesta = false;
        if (binding.cbdeporte.isChecked() || binding.cbarte.isChecked()
        || binding.cbotros.isChecked()){
            respuesta = true;
        }
        return respuesta;
    }

    private Boolean validarEstadoCivil(){
        boolean respuesta= true;
        if (estadocivil.equals("")){
            respuesta = false;
        }
        return respuesta;
    }

    private Boolean validarFormulario(){
    boolean respuesta = false;
    String mensaje = "";
    if (!validarNombreApellido()){
        mensaje = "Ingrese su nombre y apellido";
    } else if (!validarGenero()) {
        mensaje = "Seleccione su Genero";
    } else if (!validarPreferencias()) {
        mensaje = "Seleccione al menos una preferencia";
    } else if (!validarEstadoCivil()) {
        mensaje= "Seleccione un estado civil";
    }else
        respuesta = true;
    if (!respuesta)
        Toast.makeText(this, mensaje,
                Toast.LENGTH_SHORT).show();
    return respuesta;

    }

    private void registrarPersonas(){
        if (validarFormulario()){
            StringBuilder infoPersona = new StringBuilder();
            infoPersona.append(binding.etnombre.getText().toString()+ "-");
            infoPersona.append(binding.etapellido.getText().toString()+ "-");
            infoPersona.append(obtenerGenero()+"-");
            infoPersona.append(preferencias.toString());
            infoPersona.append(estadocivil+"-");
            infoPersona.append(binding.swnotificacion.isChecked());
            personas.add(infoPersona.toString());
            Toast.makeText(this,"Persona Registrada", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        if (position == 0){
            estadocivil = "";
        }else
            estadocivil = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.cbdeporte){
            agregarQuitarPreferencias(view, "Deporte");
        } else if (view.getId() == R.id.cbarte){
            agregarQuitarPreferencias(view, "Arte y Creatividad");

        } else if (view.getId() == R.id.cbotros) {
            agregarQuitarPreferencias(view, "Otras Preferencias");

        } else if (view.getId() == R.id.btnregistrar) {
            registrarPersonas();
            setearControles();
        } else if (view.getId()== R.id.btnlistapersona) {
            Intent intentLista = new Intent(getApplicationContext(),
                    ListaActivity.class);
            intentLista.putExtra("Listapersonas",
                    (ArrayList<String>) personas);
            startActivity(intentLista);
        }
    }
    private void setearControles(){
        binding.etnombre.setText("");
        binding.etapellido.setText("");
        binding.rggenero.clearCheck();
        binding.cbdeporte.setChecked(false);
        binding.cbotros.setChecked(false);
        binding.cbarte.setChecked(false);
        binding.spestadocivil.setSelection(0);
        binding.swnotificacion.setChecked(false);
        preferencias.clear();
        binding.etnombre.setFocusableInTouchMode(true);
        binding.etnombre.requestFocus();
    }
}




