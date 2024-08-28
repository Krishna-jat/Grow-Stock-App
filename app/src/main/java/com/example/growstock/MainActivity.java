package com.example.growstock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
AutoCompleteTextView autoCompleteTextView_enter_days;
TextInputEditText edittextinputlayout_enter_value;
TextInputLayout textinputlayout_enter_value;
MaterialButton btn_submit,btn_evaluate;
MaterialTextView txt_from,txt_to,txt_net_profit;
    ArrayList<String> days;
    ArrayList<String> profit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();


        autoCompleteTextView_enter_days.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String value= profit.get(i+1);
                if(value!=null)
                {
                    textinputlayout_enter_value.getEditText().setText(value);
                }
                else
                {
                    textinputlayout_enter_value.getEditText().setText("0");
                }
            }
        });


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=textinputlayout_enter_value.getEditText().getText().toString();
                String index=autoCompleteTextView_enter_days.getText().toString().trim();


                if((!value.equals(""))&&(!index.equals(""))&&(!(Integer.parseInt(value)<0)))
                {
                    int ind=Integer.parseInt(index);
                    profit.set(ind,value);
                    Toast.makeText(MainActivity.this, "data submitted", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Enter correct data", Toast.LENGTH_SHORT).show();
                }

            }
        });


        btn_evaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Integer> profit_diff=new ArrayList<>();
                int i=0;

                while(profit.get(i).equals("0"))
                {
                    i++;
                }

                for(;i<profit.size()-1;i++)
                {
                    if(profit.get(i+1).equals("0"))
                    {
                        profit_diff.add(0);
                        profit.set(i+1,profit.get(i));
                    }
                    else
                    {
                        int diff=(Integer.parseInt(profit.get(i+1)))-(Integer.parseInt(profit.get(i)));
                        profit_diff.add(diff);

                    }
                }

                //write algo of subsequence
                findMaximumSubarray(profit_diff);


            }
        });


    }

    public  void findMaximumSubarray(ArrayList<Integer> profit_dif) {
        int maxSum = Integer.MIN_VALUE; // Initialize maxSum to a very small value.
        int currentSum = 0;
        int start = 0;
        int currentStart = 0;
        int end = -1;

        for (int i = 0; i < profit_dif.size(); i++) {
            currentSum += profit_dif.get(i);

            if (currentSum > maxSum) {
                maxSum = currentSum;
                start = currentStart;
                end = i;
            }

            if (currentSum < 0) {
                currentSum = 0;
                currentStart = i + 1;
            }
        }
        for(int i=0;i<profit_dif.size();i++)
        {
            Log.d("array values", "findMaximumSubarray: i= "+i+" value = "+profit_dif.get(i));
        }
        Toast.makeText(this, ""+start+" "+end, Toast.LENGTH_SHORT).show();

        txt_from.setText(""+(start+2));
        txt_to.setText(""+(end+2));
        txt_net_profit.setText(""+maxSum);


    }

    private void initialize() {
        autoCompleteTextView_enter_days=findViewById(R.id.main_autocompletetextview_enter_days);
        edittextinputlayout_enter_value=findViewById(R.id.main_edittextinputlayout_enter_value);
        textinputlayout_enter_value=findViewById(R.id.main_textinputlayout_enter_value);
        txt_from=findViewById(R.id.main_textview_interval_from);
        txt_to=findViewById(R.id.main_textview_interval_to);
        txt_net_profit=findViewById(R.id.main_textview_net_profit);
        btn_evaluate=findViewById(R.id.main_btn_evaluate);
        btn_submit=findViewById(R.id.main_btn_submit);
        days=new ArrayList<>();
        days.add("1");days.add("2");days.add("3");days.add("4");days.add("5");days.add("6");days.add("7");days.add("8");days.add("9");days.add("10");
        days.add("11");days.add("12");days.add("13");days.add("14");days.add("15");days.add("16");days.add("17");days.add("18");days.add("19");days.add("20");
        days.add("21");days.add("22");days.add("23");days.add("24");days.add("25");days.add("26");days.add("27");days.add("28");days.add("29");days.add("30");
        days.add("31");

        ArrayAdapter adapter=new ArrayAdapter(MainActivity.this,R.layout.days_spinner_layout,days);

        autoCompleteTextView_enter_days.setAdapter(adapter);

        profit=new ArrayList<>(32);
        for(int i=0;i<32;i++)
        {
            profit.add(i,"0");
        }
    }
}