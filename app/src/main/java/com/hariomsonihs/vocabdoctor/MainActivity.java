package com.hariomsonihs.vocabdoctor;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Set up toolbar
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        // Initialize DrawerLayout and NavigationView
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        // Set up menu icon click to open drawer
        ImageView menuIcon = findViewById(R.id.menu_icon);
        menuIcon.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        // Set up navigation drawer item clicks
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                // Already on home, no action needed
            } else if (id == R.id.nav_vocabulary) {
                startActivity(new Intent(MainActivity.this, VocabularyActivity.class));
            } else if (id == R.id.nav_phrasal_verbs) {
                startActivity(new Intent(MainActivity.this, PhrasalVerbsActivity.class));
            } else if (id == R.id.nav_idioms) {
                startActivity(new Intent(MainActivity.this, IdiomsActivity.class));
            } else if (id == R.id.nav_settings) {
                // Handle Settings click (replace with SettingsActivity if exists)
                // startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        // Set up bottom navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                // Already on home
                return true;
            } else if (id == R.id.nav_vocabulary) {
                startActivity(new Intent(MainActivity.this, VocabularyActivity.class));
                return true;
            } else if (id == R.id.nav_grammar) {
                startActivity(new Intent(MainActivity.this, GrammarActivity.class));
                return true;
            }
            return false;
        });

        // Set click listeners for cards
        findViewById(R.id.btnLearnNow).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, LearnActivity.class));
        });

        CardView phrasalVerbsCard = findViewById(R.id.card_phrasal_verbs);
        phrasalVerbsCard.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, PhrasalVerbsActivity.class));
        });

        CardView idiomsCard = findViewById(R.id.card_idioms);
        idiomsCard.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, IdiomsActivity.class));
        });

        CardView verbsCard = findViewById(R.id.card_verbs);
        verbsCard.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, VerbsActivity.class));
        });

        CardView proverbsCard = findViewById(R.id.card_proverbs);
        proverbsCard.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ProverbsActivity.class));
        });

        CardView dailySentenceCard = findViewById(R.id.card_daily_sentences);
        dailySentenceCard.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, DailySentenceActivity.class));
        });

        // Handle window insets for edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            showExitConfirmationDialog();
        }
    }

    private void showExitConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.ExitDialogTheme);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_exit_confirmation, null);
        builder.setView(dialogView);

        ImageView icon = dialogView.findViewById(R.id.dialog_icon);
        TextView title = dialogView.findViewById(R.id.dialog_title);
        TextView message = dialogView.findViewById(R.id.dialog_message);
        Button positiveButton = dialogView.findViewById(R.id.positive_button);
        Button negativeButton = dialogView.findViewById(R.id.negative_button);

        title.setText(getString(R.string.exit_app_title));
        message.setText(getString(R.string.exit_app_message));
        positiveButton.setText(getString(R.string.yes));
        negativeButton.setText(getString(R.string.no));

        Animation pulse = AnimationUtils.loadAnimation(this, R.anim.pulse_animation);
        icon.startAnimation(pulse);

        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        positiveButton.setOnClickListener(v -> {
            v.startAnimation(AnimationUtils.loadAnimation(this, R.anim.button_click));
            finishAffinity();
            System.exit(0);
        });

        negativeButton.setOnClickListener(v -> {
            v.startAnimation(AnimationUtils.loadAnimation(this, R.anim.button_click));
            dialog.dismiss();
        });

        dialog.show();
    }
}