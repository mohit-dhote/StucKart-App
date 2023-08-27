package com.example.stuckartapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.ramotion.paperonboarding.PaperOnboardingFragment;
import com.ramotion.paperonboarding.PaperOnboardingPage;
import com.ramotion.paperonboarding.listeners.PaperOnboardingOnChangeListener;

import java.util.ArrayList;

public class IntroActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private static final String PREFS_NAME = "MyPrefs";
    private static final String PREF_ONBOARDING_COMPLETED = "onboarding_completed";
    private Button skipButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean onboardingCompleted = preferences.getBoolean(PREF_ONBOARDING_COMPLETED, false);

        if (!onboardingCompleted) {
            setContentView(R.layout.activity_intro);

            skipButton = findViewById(R.id.skipButton);
            skipButton.setVisibility(View.VISIBLE);
            skipButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    redirectToMainActivity();
                }
            });

            fragmentManager = getSupportFragmentManager();
            final PaperOnboardingFragment paperOnboardingFragment = PaperOnboardingFragment.newInstance(getDataforOnboarding());
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.frame_layout, paperOnboardingFragment);
            fragmentTransaction.commit();

            // Change skip button background color dynamically
            paperOnboardingFragment.setOnChangeListener(new PaperOnboardingOnChangeListener() {
                @Override
                public void onPageChanged(int oldIndex, int newIndex) {
                    skipButton.setBackgroundColor(getDataforOnboarding().get(newIndex).getBgColor());
                }
            });
        } else {
            redirectToMainActivity();
        }
    }

    private void redirectToMainActivity() {
        Intent intent = new Intent(IntroActivity.this, MainActivity.class);
        startActivity(intent);
        finish(); // Close this activity so the user can't go back to onboarding
    }

    private ArrayList<PaperOnboardingPage> getDataforOnboarding() {
        PaperOnboardingPage source1 = new PaperOnboardingPage("Easy Buying & Selling", "Buy & Sell Your Items Just On Single Click", Color.parseColor("#ffb174"),R.drawable.img1, R.drawable.whitedot);
        PaperOnboardingPage source2 = new PaperOnboardingPage("Trusted Sellers", "Sellers are the Students Like & Products Will Be Exchanege By Meeting", Color.parseColor("#22eaaa"),R.drawable.img2, R.drawable.whitedot);
        PaperOnboardingPage source3 = new PaperOnboardingPage("Desired Community", "Find Your Housing Roomates & Ride Sharing From Our Community", Color.parseColor("#ee5a5a"),R.drawable.img3, R.drawable.whitedot);

        ArrayList<PaperOnboardingPage> elements = new ArrayList<>();
        elements.add(source1);
        elements.add(source2);
        elements.add(source3);

        // Store the background colors
        int[] backgroundColors = {Color.parseColor("#ffb174"), Color.parseColor("#22eaaa"), Color.parseColor("#ee5a5a")};
        for (int i = 0; i < elements.size(); i++) {
            elements.get(i).setBgColor(backgroundColors[i]);
        }

        return elements;
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(PREF_ONBOARDING_COMPLETED, true);
        editor.apply();
    }
}
