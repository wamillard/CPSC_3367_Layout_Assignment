package com.ualr.layoutassignment;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;

import com.google.android.material.button.MaterialButton;
import com.ualr.layoutassignment.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // TODO 02. Create a new method called "calculateTotal" for calculating the invoice's total amount of money

    // TODO 03. Bind the "calculateTotal" method to the button with the "CALCULATE TOTAL" label
    private ActivityMainBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());


        this.mBinding.layoutDiscount.setStartIconOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Integer newDiscount= Integer.parseInt(mBinding.editDiscount.getText().toString().substring(0,mBinding.editDiscount.getText().length()-1))-1;
                mBinding.editDiscount.setText(newDiscount.toString()+"%");
                calculateTotal(Double.parseDouble(newDiscount.toString()));
            }
        });

        this.mBinding.layoutDiscount.setEndIconOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Integer newDiscount= Integer.parseInt(mBinding.editDiscount.getText().toString().substring(0,mBinding.editDiscount.getText().length()-1))+1;
                mBinding.editDiscount.setText(newDiscount.toString()+"%");
                calculateTotal(Double.parseDouble(newDiscount.toString()));
            }
        });

        this.mBinding.btnFull.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Double newDiscount= 0.0;
                calculateTotal(newDiscount);
            }
        });

        this.mBinding.btnDiscount.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Double newDiscount= Double.parseDouble(mBinding.editDiscount.getText().toString().substring(0,mBinding.editDiscount.getText().length()-1));
                calculateTotal(newDiscount);
            }
        });

        this.mBinding.btnCalculate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Double newDiscount= Double.parseDouble(mBinding.editDiscount.getText().toString().substring(0,mBinding.editDiscount.getText().length()-1));
                calculateTotal(newDiscount);
            }
        });


        this.mBinding.btnFull.setChecked(true);

        List<Product> products=getList();

        for (Integer i=0; i<products.size(); i++)
        {
            CheckBox check = findViewById(getResources().getIdentifier("check" + i, "id", getPackageName()));
            TextView cost = findViewById(getResources().getIdentifier("txtCost" + i, "id", getPackageName()));
            MaterialButton sub = findViewById(getResources().getIdentifier("btnSubtract" + i, "id", getPackageName()));
            MaterialButton add = findViewById(getResources().getIdentifier("btnAdd" + i, "id", getPackageName()));
            EditText qty = findViewById(getResources().getIdentifier("editQty" + i, "id", getPackageName()));

            check.setText(products.get(i).getName());
            cost.setText(String.format("%.2f",products.get(i).getCost()));
            cost.setHint(products.get(i).getCost().toString());

            sub.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    quantityChange('s', v.getTag().toString());
                }

            });

            add.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    quantityChange('a', v.getTag().toString());
                }

            });

            qty.setOnFocusChangeListener(new View.OnFocusChangeListener()
            {
                @Override
                public void onFocusChange(View v, boolean hasFocus)
                {
                    if (!hasFocus)
                    {
                        quantityChange('n',v.getTag().toString());
                    }
                }
            });


        }

        return;


    
    }



    public List<Product> getList()
    {
        List<Product> newList= new ArrayList<>();
        String nameList[]= this.getResources().getStringArray(R.array.product_names);
        String costList[]=this.getResources().getStringArray(R.array.product_price);

        for(Integer i=0; i<nameList.length;i++)
        {
            Product newItem=new Product();

            newItem.setName(nameList[i]);
            newItem.setCost(Double.parseDouble(costList[i]));
            newList.add(newItem);
        }

        return newList;
    }

    public void quantityChange(Character op,String currProduct)
    {
        EditText qty=findViewById(getResources().getIdentifier("editQty"+currProduct,"id",getPackageName()));
        Integer newQty=Integer.parseInt(qty.getText().toString());
        CheckBox check=findViewById(getResources().getIdentifier("check"+currProduct,"id",getPackageName()));
        TextView subtotal=findViewById(getResources().getIdentifier("txtSubtotal"+currProduct,"id",getPackageName()));
        TextView cost=findViewById(getResources().getIdentifier("txtCost"+currProduct,"id",getPackageName()));

        if (op=='a')  //a is add | s is subtract
        {
            newQty=newQty+1;

        } else if(op=='s')
        {
            newQty=newQty-1;

        }

        if (newQty<=0)
        {
            newQty=0;
            check.setChecked(false);
        } else
        {
            check.setChecked(true);
        }
        qty.setText(newQty.toString());
        Double newSub=Double.parseDouble(cost.getText().toString())*newQty;
        subtotal.setText(String.format("%.2f",newSub));

    }

    public void calculateTotal( Double percentDiscount)
    {

        Boolean apply=this.mBinding.btnDiscount.isChecked();

        Double costModifier=1-(percentDiscount/100);

        Double newTotal=0.0;

        for (Integer i=0;i<this.mBinding.layoutProductList.getChildCount();i++)
        {
            EditText qty=findViewById(getResources().getIdentifier("editQty"+i,"id",getPackageName()));
            Double newQty=Double.parseDouble(qty.getText().toString());
            TextView cost=findViewById(getResources().getIdentifier("txtCost"+i,"id",getPackageName()));
            TextView subtotal=findViewById(getResources().getIdentifier("txtSubtotal"+i,"id",getPackageName()));
            Double newSub=Double.parseDouble(cost.getHint().toString());

            if (apply)
            {
                newSub=newSub*costModifier;
            }


            cost.setText(String.format("%.2f",newSub));
            newSub=newSub*newQty;

            subtotal.setText(String.format("%.2f",newSub));

            newTotal+=newSub;
        }

        this.mBinding.txtTotal.setText(String.format("%.2f",newTotal));

        return;
    }

}