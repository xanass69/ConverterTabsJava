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

public class DistanceFragment extends Fragment {

    private RadioGroup rgDist;
    private RadioButton rbKmToMiles;
    private RadioButton rbMilesToKm;
    private EditText etDistInput;
    private Button btnConvertDist;
    private TextView tvDistResult;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_distance, container, false);

        // Initialisation des composants
        rgDist = view.findViewById(R.id.rgDist);
        rbKmToMiles = view.findViewById(R.id.rbKmToMiles);
        rbMilesToKm = view.findViewById(R.id.rbMilesToKm);
        etDistInput = view.findViewById(R.id.etDistInput);
        btnConvertDist = view.findViewById(R.id.btnConvertDist);
        tvDistResult = view.findViewById(R.id.tvDistResult);

        // Gestionnaire d'événement pour le bouton de conversion
        btnConvertDist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertirDistance();
            }
        });

        return view;
    }

    private void convertirDistance() {
        String inputValue = etDistInput.getText().toString().trim();

        // Vérification que l'utilisateur a entré une valeur
        if (TextUtils.isEmpty(inputValue)) {
            Toast.makeText(getContext(), "Veuillez entrer une valeur", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double valeur = Double.parseDouble(inputValue);
            double resultat;

            // Conversion selon l'option sélectionnée
            if (rbKmToMiles.isChecked()) {
                // Kilomètres vers Miles
                resultat = valeur * 0.621371;
                tvDistResult.setText(String.format("Résultat : %.2f miles", resultat));
            } else {
                // Miles vers Kilomètres
                resultat = valeur / 0.621371;
                tvDistResult.setText(String.format("Résultat : %.2f km", resultat));
            }
        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), "Veuillez entrer un nombre valide", Toast.LENGTH_SHORT).show();
        }
    }
}