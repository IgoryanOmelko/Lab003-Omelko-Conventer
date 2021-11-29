package com.example.lab_002convertomelko;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Spinner spnto, spnfrom;
    TextView tvto, tvfrom,tvr;
    EditText etfrom;
    ArrayAdapter <Unit> adpW, adpL, adpV;
    RadioButton rbW,rbL,rbV;
    double value;
    Toast toast;
    Unit uf,ut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spnto=findViewById(R.id.spinnerTo);spnfrom=findViewById(R.id.spinnerFrom);
        tvto=findViewById(R.id.textViewTo);tvfrom=findViewById(R.id.textViewFrom);tvr=findViewById(R.id.textViewRes);
        etfrom=findViewById(R.id.editTextFrom);
        adpW = new <Unit> ArrayAdapter(this, android.R.layout.simple_list_item_1);
        adpL = new <Unit> ArrayAdapter(this, android.R.layout.simple_list_item_1);
        adpV = new <Unit> ArrayAdapter(this, android.R.layout.simple_list_item_1);
        adpV.add(new Unit("m3",1));adpV.add(new Unit("l",0.001));adpV.add(new Unit("ml",0.000001));
        adpL.add(new Unit("m",1));adpL.add(new Unit("cm",0.01));adpL.add(new Unit("mm",0.001));adpL.add(new Unit("km",1000));
        adpW.add(new Unit("kg",1));adpW.add(new Unit("g",0.001));adpW.add(new Unit("mg",0.000001));adpW.add(new Unit("t",1000));
        spnfrom.setAdapter(adpL); spnto.setAdapter(adpL);
        //rbW.setChecked(true);
    }
    public void Convert(View v)//created by IgoryanOmelko
    {
        if (!InputCheckDouble(etfrom.getText().toString())) {
            toast= Toast.makeText(getApplicationContext(), getResources().getString(R.string.text_error), Toast.LENGTH_SHORT);
            toast.show();
            tvr.setText("");
            return;
        }
        else {
            value=Double.parseDouble(etfrom.getText().toString());
            if (value<0) {
                toast= Toast.makeText(getApplicationContext(), getResources().getString(R.string.text_error), Toast.LENGTH_SHORT);
                toast.show();
                tvr.setText("");
                return;
            }
            else {
                uf=(Unit) spnfrom.getSelectedItem();
                ut=(Unit) spnto.getSelectedItem();
                if (uf.name==ut.name) {
                    tvr.setText(String.valueOf(value));
                }
                else {
                    tvr.setText(String.valueOf(value*uf.k/ut.k));
                }
            }
        }
    }
    public void RbChange(View rb)
    {
        switch (rb.getId()) {
            case R.id.radioButtonWeight:spnfrom.setAdapter(adpW); spnto.setAdapter(adpW);break;
            case R.id.radioButtonLength:spnfrom.setAdapter(adpL); spnto.setAdapter(adpL);break;
            case R.id.radioButtonVolume:spnfrom.setAdapter(adpV); spnto.setAdapter(adpV);break;
        }
    }
    public boolean InputCheckDouble(String value)
    {
        try {
            Double.parseDouble(value);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }
}