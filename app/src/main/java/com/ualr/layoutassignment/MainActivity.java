package com.ualr.layoutassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.ualr.layoutassignment.databinding.ActivityMainBinding;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    // TODO 02. Create a new method called "calculateTotal" for calculating the invoice's total amount of money

    // TODO 03. Bind the "calculateTotal" method to the button with the "CALCULATE TOTAL" label
    private ActivityMainBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        this.mBinding.btnAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                quantityAdd(v.getTag().toString());
            }

        });



//        mBinding.btnFull.setSelected(true);
    }


    public void quantityAdd(String currProduct)
    {
 //       String check=new String("check").concat(currProduct);
 //       String cost=new String("txtCost").concat(currProduct);
 //       String qty=new String("editQty").concat(currProduct);
 //       String subTotal=new String("txtSubtotal").concat(currProduct);

        CheckBox check=findViewById(getResources().getIdentifier("check"+currProduct,"id",getPackageName()));
        TextView cost=findViewById(getResources().getIdentifier("txtCost"+currProduct,"id",getPackageName()));
        EditText qty=findViewById(getResources().getIdentifier("editQty"+currProduct,"id",getPackageName()));
        TextView subtotal=findViewById(getResources().getIdentifier("txtSubtotal"+currProduct,"id",getPackageName()));

        check.setChecked(true);
        Integer newQty=Integer.parseInt(qty.getText().toString())+1;
        qty.setText(newQty.toString());
        Double newSub=newQty*Double.parseDouble(cost.getText().toString());
        subtotal.setText(newSub.toString());





        return;
    }

    public void quantitySubtract()
    {

        return;
    }

    public void applyDiscount()
    {

        return;
    }

    public void removeDiscount()
    {

        return;
    }

    public void calculateSubtotals()
    {

        return;
    }

    public void calculateTotal()
    {

        return;
    }

}