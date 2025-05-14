package com.hariomsonihs.vocabdoctor;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

public class VocabularyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary);

        // Find views
        findViewById(R.id.cardFlowers).setOnClickListener(v -> startWebViewActivity("flowers.html"));
        findViewById(R.id.cardAnimals).setOnClickListener(v -> startWebViewActivity("animals.html"));
        findViewById(R.id.cardInsects).setOnClickListener(v -> startWebViewActivity("insects.html"));
        findViewById(R.id.cardBodyParts).setOnClickListener(v -> startWebViewActivity("bodyparts.html"));
        findViewById(R.id.cardFruits).setOnClickListener(v -> startWebViewActivity("fruits.html"));
    }

    private void startWebViewActivity(String fileName) {
        Intent intent = new Intent(this, WebViewActivity.class);
        intent.putExtra("fileName", fileName);
        startActivity(intent);
    }
}