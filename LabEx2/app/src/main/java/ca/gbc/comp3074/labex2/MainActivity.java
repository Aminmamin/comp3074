package ca.gbc.comp3074.labex2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private int count = 0;
    private int step = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView outputText = findViewById(R.id.outputText);
        Button addButton = findViewById(R.id.addButton);
        Button subtractButton = findViewById(R.id.subtractButton);
        Button resetButton = findViewById(R.id.resetButton);
        Button stepButton = findViewById(R.id.stepButton);

        addButton.setOnClickListener(v -> {
            count += step;
            outputText.setText(String.valueOf(count));
        });

        subtractButton.setOnClickListener(v -> {
            count -= step;
            outputText.setText(String.valueOf(count));
        });

        resetButton.setOnClickListener(v -> {
            count = 0;
            step = 1;
            outputText.setText(String.valueOf(count));
        });

        stepButton.setOnClickListener(v -> {
            if (step == 1) {
                step = 2;
            } else {
                step = 1;
            }
        });
    }
}