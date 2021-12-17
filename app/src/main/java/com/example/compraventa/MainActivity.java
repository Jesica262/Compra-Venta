package com.example.compraventa;

import static android.util.Log.*;

import static java.lang.Integer.parseInt;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Spinner spinner;
    private Switch switchP;
    private SeekBar seekBarPorcentaje;
    private CheckBox checkBoxretiro;
    private TextView varPorcentaje;
    private CheckBox checkBoxAceptar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinnerCategoria();
        switchPorcentaje();
        mostrarDireccionRetiro();
        bottonPublicar();
    }


    public void spinnerCategoria(){
        String[] opciones = {"SELECCIONE CATEGORIA","INDUMENTARIA", "ELECTRONICA", "ENTRETENIMIENTO", "JARDIN", "VEHICULOS", "JUGUETES"};
        spinner = (Spinner) findViewById(R.id.spinnerCategoria);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter (this,
                android.R.layout.simple_spinner_item,
                opciones) ;
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    public  void switchPorcentaje() {


        TextView ceroPorcentaje;
        TextView variablePorcentaje;

        switchP = (Switch) findViewById(R.id.switchDescuento);
        switchP .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                Switch switchP;
                TextView ceroPorcentaje;
                TextView variablePorcentaje;
                seekBarPorcentaje = (SeekBar) findViewById(R.id.seekBarPorcentaje);
                ceroPorcentaje= (TextView)  findViewById(R.id.textCero);
                variablePorcentaje = (TextView)  findViewById(R.id.textPor);
                if (isChecked) {
                    ceroPorcentaje.setVisibility(View.VISIBLE);
                    seekBarPorcentaje.setVisibility(View.VISIBLE);
                    variablePorcentaje.setVisibility(View.VISIBLE);
                    mostrarProgreso();
                }
                if (!isChecked) {

                    ceroPorcentaje.setVisibility(View.GONE);
                    seekBarPorcentaje.setVisibility(View.GONE);
                    variablePorcentaje.setVisibility(View.GONE);
                }

            }
        });
    }
    public void mostrarProgreso(){

        SeekBar seekBarPorcentaje;

        varPorcentaje = (TextView)findViewById(R.id.textPor);
        // SeekBar
        seekBarPorcentaje = (SeekBar) findViewById(R.id.seekBarPorcentaje);
        // Valor Inicial
        seekBarPorcentaje.setProgress(0);
        // Valot Final
        seekBarPorcentaje.setMax(100);
        seekBarPorcentaje.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    //hace un llamado a la perilla cuando se arrastra
                    @Override
                    public void onProgressChanged(SeekBar seekBar,
                                                  int progress, boolean fromUser) {
                        varPorcentaje.setText(String.valueOf(progress));
                    }
                    //hace un llamado  cuando se toca la perilla
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }
                    //hace un llamado  cuando se detiene la perilla
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                });


    }
    public void mostrarDireccionRetiro(){

        TextView textViewDireccion;
        EditText editTextDireccionRetiro;

        checkBoxretiro= (CheckBox) findViewById(R.id.checkBoxRetiro);
        textViewDireccion=(TextView) findViewById(R.id.textDrireccionRetiro);
        editTextDireccionRetiro=(EditText) findViewById(R.id.editTextDireccionRetiro);


        checkBoxretiro.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    textViewDireccion.setVisibility(View.VISIBLE);
                    editTextDireccionRetiro.setVisibility(View.VISIBLE);
                }

                if(!b){
                    textViewDireccion.setVisibility(View.GONE);
                    editTextDireccionRetiro.setVisibility(View.GONE);

                }

            }
        });




    }

    public String seleccionarCategoria() {

        Spinner categoria = (Spinner) findViewById(R.id.spinnerCategoria);

        final String[] selec = new String[1];
        selec[0] =categoria.getSelectedItem().toString();

        return selec[0];

    }


    public void bottonPublicar() {

        Button publicar = (Button) findViewById(R.id.buttonPublicar);
        EditText  titulo = (EditText) findViewById(R.id.editTextTitulo);
        EditText precio= (EditText) findViewById(R.id.editTextPrecio);
        EditText retiro=(EditText) findViewById(R.id.editTextDireccionRetiro);
        EditText email=(EditText) findViewById(R.id.editTextTextEmail);
        checkBoxAceptar=(CheckBox) findViewById(R.id.checkAceptarTerminos);

        final String[] textoPrecio = new String[1];
        final Integer[] numeroPrecio = new Integer[1];
        final String [] textoPorcentaje= new String[1];
        final Integer [] numeroPorcentaje =new Integer[1];
        final String[] categoria = new String[1];


        Validaciones validaciones= new Validaciones();

        publicar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                categoria[0] =seleccionarCategoria();
                boolean[] permisoPublicar = {true};

                if(validaciones.Vacio(titulo)){
                    //  titulo.setError("Campo Requerido");
                    // titulo.requestFocus();
                    permisoPublicar[0] =false;
                    Toast.makeText(getApplicationContext(), "Debe ingresar un título", Toast.LENGTH_LONG).show();
                }
                else{
                    if(!validaciones.validarString(titulo.getText().toString()))

                    {   //  titulo.setError("Título no valido");
                        //   titulo.requestFocus();
                        permisoPublicar[0] =false;
                        Toast.makeText(getApplicationContext(), "Título no valido ", Toast.LENGTH_LONG).show();}

                }

                if(validaciones.Vacio(email)) {
                    Toast.makeText(getApplicationContext(), "Debe ingresar un correo ", Toast.LENGTH_LONG).show();
                }
                else{
                    if (!validaciones.isEmail(email.getText().toString())){
                        permisoPublicar[0] =false;
                        Toast.makeText(getApplicationContext(), "Correo no valido", Toast.LENGTH_LONG).show();}


                }

                if(validaciones.Vacio(precio)){
                    // precio.setError("Campo Requerido");
                    //precio.requestFocus();
                    permisoPublicar[0] =false;
                    Toast.makeText(getApplicationContext(), "Debe ingresar un valor al precio", Toast.LENGTH_LONG).show();
                }
                else {
                    textoPrecio[0] = precio.getText().toString();
                    numeroPrecio[0] = parseInt(textoPrecio[0]);

                    if(numeroPrecio[0].equals(0)){
                        permisoPublicar[0] =false;
                        Toast.makeText(getApplicationContext(), "El precio debe ser mayor 0", Toast.LENGTH_LONG).show();
                    }

                }
                if(categoria[0].equals("SELECCIONE CATEGORIA")){
                    permisoPublicar[0] =false;
                    Toast.makeText(getApplicationContext(), "Debe seleccionar una categoria", Toast.LENGTH_LONG).show();

                }
                if(checkBoxretiro.isChecked()){
                    if(validaciones.Vacio(retiro)){
                        //retiro.setError("Campo Requerido");
                        //retiro.requestFocus();
                        permisoPublicar[0] =false;
                        Toast.makeText(getApplicationContext(), "Debe ingresar direccion de retiro ", Toast.LENGTH_LONG).show();
                    }
                    else{
                        if(!validaciones.validarString(retiro.getText().toString())){

                            //  titulo.setError("Título no valido");
                            //   titulo.requestFocus();
                            permisoPublicar[0] =false;
                            Toast.makeText(getApplicationContext(), "Direccion no valida ", Toast.LENGTH_LONG).show();}
                    }

                }
                if(switchP.isChecked()){
                    textoPorcentaje[0]=varPorcentaje.getText().toString();
                    numeroPorcentaje[0]= parseInt(textoPorcentaje[0]);
                    if(numeroPorcentaje[0].equals(0)){
                        permisoPublicar[0] =false;
                        Toast.makeText(getApplicationContext(), " Por favor seleccione un porcentaje mayor a 0 o quite la opcion de ofrecer descuento de envio ", Toast.LENGTH_LONG).show();

                    }
                }

                if(!checkBoxAceptar.isChecked()){
                    permisoPublicar[0] =false;
                    Toast.makeText(getApplicationContext(), "Debe aceptar los terminos para publicar los datos ", Toast.LENGTH_LONG).show();

                }

                if( permisoPublicar[0] ){
                    permisoPublicar[0] =false;
                    Toast.makeText(getApplicationContext(), "Los datos fueron cargados correctamente ", Toast.LENGTH_LONG).show();
                }

            }




        });


    }}