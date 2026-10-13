
package ca.gbc.comp3074.paycalculator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText hoursInput = findViewById(R.id.hoursInput);
        EditText rateInput = findViewById(R.id.rateInput);
        EditText taxInput = findViewById(R.id.taxInput);

        TextView resultText = findViewById(R.id.resultText);
        Button calculateButton = findViewById(R.id.calculateButton);
        Button aboutButton = findViewById(R.id.aboutButton);

        calculateButton.setOnClickListener(v -> {
            try {
                String hoursText = hoursInput.getText().toString().trim();
                String rateText = rateInput.getText().toString().trim();
                String taxText = taxInput.getText().toString().trim();

                if (hoursText.isEmpty() || rateText.isEmpty()
                        || taxText.isEmpty()) {
                    Toast.makeText(this, "Fill in all fields",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                double hours = Double.parseDouble(hoursText);
                double rate = Double.parseDouble(rateText);
                double taxRate = Double.parseDouble(taxText);

                if (hours < 0 || rate < 0
                        || taxRate < 0 || taxRate > 1) {
                    Toast.makeText(this,
                            "Enter valid values. Tax must be 0 to 1.",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                double pay;
                double overtimePay = 0;

                if (hours <= 40) {
                    pay = hours * rate;
                } else {
                    pay = 40 * rate;
                    overtimePay = (hours - 40) * rate * 1.5;
                }

                double totalPay = pay + overtimePay;
                double tax = pay * taxRate;

                resultText.setText(String.format(Locale.US,
                        "Regular Pay: $%.2f\n" +
                                "Overtime Pay: $%.2f\n" +
                                "Total Pay: $%.2f\n" +
                                "Tax: $%.2f",
                        pay, overtimePay, totalPay, tax));

            } catch (NumberFormatException e) {
                Toast.makeText(this, "Enter valid numbers",
                        Toast.LENGTH_SHORT).show();
            }
        });

        aboutButton.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this, AboutActivity.class);
            startActivity(intent);
        });
    }
}
