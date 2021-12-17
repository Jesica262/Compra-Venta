package com.example.compraventa;

import android.text.TextUtils;
import android.widget.EditText;

import java.util.regex.Pattern;

public class Validaciones {

    public Validaciones(){

    }

    //metodo para validar si es un email
    public  boolean isEmail(String cadena) {
        boolean resultado;

        String mailRegEx = "\\w{1,20}@\\w{3,20}\\.(com|org|cn|net|gov)";
        Pattern mailPattern = Pattern.compile(mailRegEx);
        if (mailPattern.matcher(cadena).matches()) {
            resultado = true;
        } else {
            resultado = false;
        }

        return resultado;
    }

    //metodo para validar si editext esta vacio
    public  boolean Vacio(EditText campo){
        String dato = campo.getText().toString().trim();
        if(TextUtils.isEmpty(dato)){

            return true;
        }
        else{
            return false;
        }


    }
    //metodo para validar cualquier string
    public  boolean validarString(String cadena) {
        boolean resultado;
        String RegEx = "[a-zA-Z0-9\\.\\_\\-\\s]{1,256}";
        Pattern stringPattern = Pattern.compile(RegEx);
        System.out.println(stringPattern);
        if (stringPattern.matcher(cadena).matches()) {
            resultado = true;

        } else {
            resultado = false;

        }

        return resultado;
    }

}
