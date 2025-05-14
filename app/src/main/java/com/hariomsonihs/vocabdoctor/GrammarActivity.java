package com.hariomsonihs.vocabdoctor;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class GrammarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar);

        // Find views and set click listeners with animations
        setCardClickListener(R.id.cardNoun, "noun.html");
        setCardClickListener(R.id.cardPronoun, "pronoun.html");
        setCardClickListener(R.id.cardArticle, "article.html");
        setCardClickListener(R.id.cardVerb, "verb.html");
        setCardClickListener(R.id.cardModal, "modals.html");
        setCardClickListener(R.id.cardAdjective, "adjective.html");
        setCardClickListener(R.id.cardAdverb, "adverb.html");
        setCardClickListener(R.id.cardPreposition, "preposition.html");
        setCardClickListener(R.id.cardConjunction, "conjunction.html");
        setCardClickListener(R.id.cardInterjection, "interjection.html");
        setCardClickListener(R.id.cardEmphatic, "emphatic.html");
        setCardClickListener(R.id.cardTimeAndTense, "time_and_tense.html");
        setCardClickListener(R.id.cardVoice, "voice.html");
        setCardClickListener(R.id.cardDegreeOfComparison, "degree_of_comparison.html");
        setCardClickListener(R.id.cardSentence, "sentence.html");
        setCardClickListener(R.id.cardQuestionTags, "question_tags.html");
        setCardClickListener(R.id.cardDirectAndIndirectSpeech, "direct_and_indirect_speech.html");
        setCardClickListener(R.id.cardTransformation, "transformation.html");
        setCardClickListener(R.id.cardSynthesis, "synthesis.html");
        setCardClickListener(R.id.cardRemovalOfToo, "removal_of_too.html");
        setCardClickListener(R.id.cardPunctuation, "punctuation.html");
        setCardClickListener(R.id.cardLettersAndApplication, "letters_and_application.html");
        setCardClickListener(R.id.cardNotices, "notices.html");
    }

    private void setCardClickListener(int cardId, String fileName) {
        View card = findViewById(cardId);
        card.setOnClickListener(v -> {
            // Add click animation
            Animation bounce = AnimationUtils.loadAnimation(this, R.anim.bounce);
            card.startAnimation(bounce);

            // Start activity after animation
            bounce.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {}

                @Override
                public void onAnimationEnd(Animation animation) {
                    startWebViewActivity(fileName);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {}
            });
        });
    }

    private void startWebViewActivity(String fileName) {
        Intent intent = new Intent(this, WebViewActivity.class);
        intent.putExtra("fileName", fileName);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
    }
}