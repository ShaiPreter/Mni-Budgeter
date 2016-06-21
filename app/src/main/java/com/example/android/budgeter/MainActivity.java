package com.example.android.budgeter;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int Total =  0;
    int TotalExpenses = 0;
    int TotalIncome = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button IncomeAdder = (Button) findViewById(R.id.AddIncome);
        Button ExpenseAdder = (Button) findViewById(R.id.AddExpense);
        Button ClearAll = (Button) findViewById(R.id.ClearAll);
        IncomeAdder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddIncome();
            }
        });
        ExpenseAdder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddExpense();
            }
        });
        ClearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearAll();
            }
        });
    }



    public boolean isEmpty(EditText e){
       return e.getText().toString().trim().length() == 0;
    }
    public void AddIncome(){
        LinearLayout Income = (LinearLayout) findViewById(R.id.IncomeLayout);

        EditText IncomeActivity = (EditText) findViewById(R.id.IncomeActivity);
        EditText IncomeAmount = (EditText) findViewById(R.id.IncomeAmount);
        final TextView TotalAmount = (TextView) findViewById(R.id.Sum);
        final TextView TotalIncomeView = (TextView) findViewById(R.id.TotalIncome);



        if (!isEmpty(IncomeActivity) && !isEmpty(IncomeAmount)) {
            String incomeActivity = IncomeActivity.getText().toString();
            String incomeAmounttext = IncomeAmount.getText().toString();
            Integer incomeAmount = Integer.parseInt(incomeAmounttext);
            Total += incomeAmount;
            String totalText = String.valueOf(Total);
            TotalAmount.setText("$" + totalText);
            TotalIncome += incomeAmount;
            String TotalIncomeText = String.valueOf(TotalIncome);
            TotalIncomeView.setText("Total Income: $" + TotalIncomeText);
            final TextView incomeInfo = new TextView(this);
            incomeInfo.setText(incomeActivity + ": $" + incomeAmount);
            incomeInfo.setLayoutParams(new ActionBar.LayoutParams(
                    ActionBar.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            Income.addView(incomeInfo);
            incomeInfo.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                                                  String text = incomeInfo.getText().toString();
                                                  int pos = text.indexOf("$");
                                                  String amount = text.substring(pos + 1);
                                                  int num = Integer.parseInt(amount);
                                                  Total -= num;
                                                  String totalamount = String.valueOf(Total);
                                                  TotalAmount.setText("$" + totalamount);
                                                  TotalIncome -= num;
                                                  String totalincome = String.valueOf(TotalIncome);
                                                  TotalIncomeView.setText("Total Income: $" + totalincome);
                                                  ((ViewGroup) incomeInfo.getParent()).removeView(incomeInfo);
                                              }
                                          }
            );
            IncomeActivity.getText().clear();
            IncomeAmount.getText().clear();
        }
        else {
            Toast.makeText(this,"You did not enter an activity and/or an amount", Toast.LENGTH_SHORT).show();
        }
    }

    public void AddExpense() {

        LinearLayout Expenses = (LinearLayout) findViewById(R.id.ExpensesLayout);

        EditText ExpenseActivity = (EditText) findViewById(R.id.ExpenseActivity);
        EditText ExpenseAmount = (EditText) findViewById(R.id.ExpenseAmount);
        final TextView TotalAmount = (TextView) findViewById(R.id.Sum);
        final TextView TotalExpensesView = (TextView) findViewById(R.id.TotalExpenses);


        if (!isEmpty(ExpenseActivity) && !isEmpty(ExpenseAmount)) {
            String expenseActivity = ExpenseActivity.getText().toString();
            String expenseAmounttext = ExpenseAmount.getText().toString();
            Integer expenseAmount = Integer.parseInt(expenseAmounttext);
            Total -= expenseAmount;
            TotalAmount.setText("$" + String.valueOf(Total));
            TotalExpenses += expenseAmount;
            TotalExpensesView.setText("Total Expenses: $" + String.valueOf(TotalExpenses));
            final TextView expenseInfo = new TextView(this);
            expenseInfo.setText(expenseActivity + ": $" + expenseAmount);
            expenseInfo.setLayoutParams(new ActionBar.LayoutParams(
                    ActionBar.LayoutParams.WRAP_CONTENT,
                    ActionBar.LayoutParams.WRAP_CONTENT
            ));
            Expenses.addView(expenseInfo);
            expenseInfo.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
                                                   String text = expenseInfo.getText().toString();
                                                   int pos = text.indexOf("$");
                                                   String amount = text.substring(pos + 1);
                                                   int num = Integer.parseInt(amount);
                                                   Total += num;
                                                   String totalamount = String.valueOf(Total);
                                                   TotalAmount.setText("$" + totalamount);
                                                   TotalExpenses -= num;
                                                   TotalExpensesView.setText("Total Expenses: $" + String.valueOf(TotalExpenses));
                                                   ((ViewGroup) expenseInfo.getParent()).removeView(expenseInfo);
                                               }
                                           }
            );
            ExpenseActivity.getText().clear();
            ExpenseAmount.getText().clear();
        } else {
            Toast.makeText(this, "You did not enter an activity and/or an amount", Toast.LENGTH_SHORT).show();
        }
    }
        public void ClearAll(){
            Intent intent = getIntent();
            finish();
            startActivity(intent);


    }
    }


