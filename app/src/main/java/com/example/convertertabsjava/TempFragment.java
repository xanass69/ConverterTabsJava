package com.example.convertertabsjava;  // Changé de converttabsjava à convertertabsjava

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TempFragment extends Fragment {

    private RadioGroup rgTemp;
    private RadioButton rbCtoF;
    private RadioButton rbFtoC;
    private EditText etTempInput;
    private Button btnConvertTemp;
    private TextView tvTempResult;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_temp, container, false);

        // Initialisation des composants
        rgTemp = view.findViewById(R.id.rgTemp);
        rbCtoF = view.findViewById(R.id.rbCtoF);
        rbFtoC = view.findViewById(R.id.rbFtoC);
        etTempInput = view.findViewById(R.id.etTempInput);
        btnConvertTemp = view.findViewById(R.id.btnConvertTemp);
        tvTempResult = view.findViewById(R.id.tvTempResult);

        // Gestionnaire d'événement pour le bouton de conversion
        btnConvertTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertirTemperature();
            }
        });

        return view;
    }

    private void convertirTemperature() {
        String inputValue = etTempInput.getText().toString().trim();

        // Vérification que l'utilisateur a entré une valeur
        if (TextUtils.isEmpty(inputValue)) {
            Toast.makeText(getContext(), "Veuillez entrer une valeur", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double valeur = Double.parseDouble(inputValue);
            double resultat;

            // Conversion selon l'option sélectionnée
            if (rbCtoF.isChecked()) {
                // Celsius vers Fahrenheit
                resultat = (valeur * 9/5) + 32;
                tvTempResult.setText(String.format("Résultat : %.2f °F", resultat));
            } else {
                // Fahrenheit vers Celsius
                resultat = (valeur - 32) * 5/9;
                tvTempResult.setText(String.format("Résultat : %.2f °C", resultat));
            }
        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), "Veuillez entrer un nombre valide", Toast.LENGTH_SHORT).show();
        }
    }
}