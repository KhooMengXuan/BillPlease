package sg.edu.rp.c346.id18016446.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    // Declare Variables
    EditText textAmt;
    EditText textPax;
    EditText textDiscount;
    ToggleButton toggleSvs;
    ToggleButton toggleGst;
    TextView totalAmt;
    TextView IndividualAmt;
    Button btnSplit;
    Button btnReset;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connect the variable to the respective UI element
        textAmt = findViewById(R.id.editAmount);
        textPax = findViewById(R.id.editPax);
        textDiscount = findViewById(R.id.editDiscount);
        toggleSvs = findViewById(R.id.toggleButtonSvs);
        toggleGst = findViewById(R.id.toggleButtonGst);
        totalAmt = findViewById(R.id.textTotal);
        IndividualAmt = findViewById(R.id.textIndividualAmt);
        btnSplit = findViewById(R.id.buttonSplit);
        btnReset = findViewById(R.id.buttonReset);

        btnSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textAmt.getText().toString().trim().length()!=0 && textPax.getText().toString().trim().length()!=0) {
                    double newAmt = 0.0;
                    if (!toggleSvs.isChecked() && !toggleGst.isChecked()) {
                        newAmt = Double.parseDouble(textAmt.getText().toString());
                    } else if (toggleSvs.isChecked() && !toggleGst.isChecked()) {
                        newAmt = Double.parseDouble(textAmt.getText().toString()) * 1.1;
                    } else if (!toggleSvs.isChecked() && toggleGst.isChecked()) {
                        newAmt = Double.parseDouble(textAmt.getText().toString()) * 1.07;
                    } else {
                        newAmt = Double.parseDouble(textAmt.getText().toString()) * 1.17;
                    }

                    if (textDiscount.getText().toString().trim().length() != 0) {
                        newAmt *= 1 - Double.parseDouble(textDiscount.getText().toString()) / 100;
                    }

                    totalAmt.setText("Total Bill: $" + String.format("%.2f", newAmt));
                    int numPerson = Integer.parseInt(textPax.getText().toString());
                    if (numPerson != 1) {
                        IndividualAmt.setText("Each Pays: $" + String.format("%.2f", newAmt/numPerson));
                    } else {
                        IndividualAmt.setText("Each Pays: $" + newAmt);
                    }
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textAmt.setText("");
                textPax.setText("");
                toggleSvs.setChecked(false);
                toggleGst.setChecked(false);
                textDiscount.setText("");
            }
        });

    }

}
