package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {

    int quantity=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText t=(EditText)findViewById(R.id.name);
        String subject="JustJava order for "+t.getText().toString();
        String priceMessage="Name: "+t.getText().toString()+"\nAdd Whipped Cream? "+addWC()+"\nAdd Chocolate? "+addC()+"\nQuantity: "+quantity+"\nTotal: $"+ getPrice(quantity)+"\nThankyou! :D" ;
       // displayMessage(priceMessage);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */

    private int getPrice(int qty){
        int price = 5;
        if(addWC()){
            price +=1;
        }
        if(addC()){
            price +=2;
        }
        return qty*price;
    }


    public void increment(View view){

        quantity++;
        if(quantity>100){
            quantity--;
            Toast.makeText(MainActivity.this, "You cannot order more than 100 cups",Toast.LENGTH_SHORT).show();
        }
        display(quantity);
    }

    public void decrement(View view){

        quantity--;
        if(quantity<1){
            quantity++;
            Toast.makeText(MainActivity.this, "You cannot order less than 1 cup",Toast.LENGTH_SHORT).show();
        }
        display(quantity);
    }

    /**
     * This method displays the given text on the screen.
     */


    private boolean addWC(){
        CheckBox a= (CheckBox)findViewById(R.id.notify_me_checkbox1);
        return a.isChecked();
    }

    private boolean addC(){
        CheckBox a= (CheckBox)findViewById(R.id.notify_me_checkbox2);
        return a.isChecked();
    }
}