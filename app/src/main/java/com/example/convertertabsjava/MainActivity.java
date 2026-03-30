package com.example.convertertabsjava;  // Changé de converttabsjava à convertertabsjava

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisation des composants
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        // Configuration de l'adaptateur
        adapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(adapter);

        // Liaison du TabLayout avec le ViewPager2
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    if (position == 0) {
                        tab.setText("Température");
                    } else {
                        tab.setText("Distance");
                    }
                }
        ).attach();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Ajout du menu Quitter
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_quit) {
            showQuitConfirmationDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        showQuitConfirmationDialog();
    }

    private void showQuitConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Quitter")
                .setMessage("Voulez-vous vraiment quitter l'application ?")
                .setPositiveButton("Oui", (dialog, which) -> finish())
                .setNegativeButton("Non", null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}