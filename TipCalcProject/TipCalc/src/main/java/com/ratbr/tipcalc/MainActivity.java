package com.ratbr.tipcalc;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Currency;
import java.util.Locale;

public class MainActivity extends Activity {
    private static DecimalFormat DECIMAL_FORMAT;
    static {
        DECIMAL_FORMAT = new DecimalFormat();
        DECIMAL_FORMAT.setCurrency(Currency.getInstance(Locale.US));
        DECIMAL_FORMAT.setMaximumFractionDigits(2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //attach action listeners for the buttons.
        Button tenPct = (Button) findViewById(R.id.btnTenPercent);
        tenPct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText et = (EditText) findViewById(R.id.etCheckAmt);

                String checkAmtStr = et.getText().toString();
                if (checkAmtStr == null || checkAmtStr.isEmpty()) {
                    return;
                }

                Float checkAmt = Float.parseFloat(checkAmtStr);
                Float tipAmt = computeTipAmount(checkAmt, 0.1f);
                TextView tvTipAmt = (TextView) findViewById(R.id.tvTipAmt);
                tvTipAmt.setText(DECIMAL_FORMAT.format(tipAmt.doubleValue()));
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public float computeTipAmount(float checkAmt, float percent) {
        return checkAmt * percent;
    }
    
}
