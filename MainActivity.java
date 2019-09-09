package com.sai.hiddenpasswords;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;

/*
App Developed by Sai Kiran Maddela
*/

public class MainActivity extends AppCompatActivity {

    private String input;
    private EditText in;
    private TextView out;
    private Button encrypt;
    private Button decrypt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = "";
        in = findViewById(R.id.inputText);
        out = findViewById(R.id.outputText);
        encrypt = findViewById(R.id.encryptB);
        decrypt = findViewById(R.id.decryptB);
        encrypt.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                /*
                * Very simple Hash Function, can replace more complex as needed
                */
                String orig = ""+in.getText();
                int[] bits = new int[orig.length()];
                for (int i = 0;  i<orig.length(); i++)
                {
                    bits[i] = (int)(orig.charAt(i));
                }
                for (int i = 0; i < bits.length; i++)
                {
                    bits[i]*=orig.length();
                }

                String bitString = "";
                for (int i = 0; i<bits.length; i++)
                {
                    bitString +=Integer.toBinaryString(bits[i]) + " ";
                }

                out.setText(bitString);
            }
        });

        decrypt.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                /*
                 * Very simple Re-hash Function, can replace more complex as needed
                 */
                String orig = ""+in.getText();
                String[] str = orig.split(" ");
                int[] bits = new int[str.length];

                for (int i = 0; i < bits.length; i++)
                {
                    bits[i] = Integer.parseInt(str[i], 2);
                    bits[i]/=str.length;
                }


                String de = "";
                for (int j =0; j<bits.length;  j++)
                {
                    de += "" + (char) (bits[j]);
                }
                out.setText(de);
            }
        });
    }
}
