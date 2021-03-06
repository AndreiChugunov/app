package mishas.clientofapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;

import mishas.clientofapp.R;
import mishas.clientofapp.logic.Administrator;
import mishas.clientofapp.logic.ProductType;


public class FoodActivity extends AppCompatActivity {

    TextView tv;
    ListView list;
    CustomList adapter;
    String[] currentOrder = {"0", "0", "0", "0", "0", "0", "0", "0"};
    ProductType[] type =
            {ProductType.HOT_DOG, ProductType.HOT_CORN, ProductType.HAMBURGER, ProductType.CHIPS,
                    ProductType.TEA, ProductType.COFFEE, ProductType.WATER, ProductType.JUICE};
    String[] web = {
            "Хот-дог - 100\u20BD",
            "Кукуруза - 50\u20BD",
            "Гамбургер - 150\u20BD",
            "Чипсы - 100\u20BD"
    };
    Integer[] imageId = {
            R.mipmap.hotdog,
            R.mipmap.hotcorn,
            R.mipmap.burger,
            R.mipmap.chips,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        this.setTitle("Еда");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#168de2")));
        tv = (TextView) findViewById(R.id.text_rubles);
        switch (getIntent().getStringExtra("from")) {
            case "liquid":
                for(int i = 0; i < currentOrder.length; i++)
                    currentOrder[i] = getIntent().getStringArrayExtra("order")[i];
                tv.setText(getIntent().getStringExtra("money"));
                break;
            case "main":
                break;
        }

        adapter = new CustomList(this, web, imageId, tv,  Arrays.copyOf(currentOrder, 4));
        list = (ListView)findViewById(R.id.list_view);
        list.setAdapter(adapter);
        ImageView iv = (ImageView) findViewById(R.id.cart_image);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FoodActivity.this, BagActivity.class));
                for (int i = 0; i < adapter.getCurrentOrder().length; i++) {
                    currentOrder[i] = adapter.getCurrentOrder()[i];
                }
                Administrator.products.clear();
                for (int i = 0; i < currentOrder.length; i++)
                    if (!currentOrder[i].equals("0"))
                        Administrator.products.put(type[i], Integer.parseInt(currentOrder[i]));
                startActivity(new Intent(FoodActivity.this, BagActivity.class));
            }
        });
        ImageView next = (ImageView) findViewById(R.id.next_image);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FoodActivity.this, LiquidActivity.class);
                intent.putExtra("money", tv.getText().toString());
                for (int i = 0; i < adapter.getCurrentOrder().length; i++) {
                    currentOrder[i] = adapter.getCurrentOrder()[i];
                }
                intent.putExtra("order", currentOrder);
                startActivity(intent);
            }
        });
    }
}
