package com.example.ex102;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * The type Main activity.
 *
 * @author Keren Weintraub <kv5171@bs.amalnet.k12.il>
 * @version 1
 * @since  9/12/2021  short description:  This activity let the user try 3 Alert Dialog's situations.
 */
public class MainActivity extends AppCompatActivity {
    final String[] colors = {"Red", "Green", "Blue"};
    int[] color;
    AlertDialog.Builder adb;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.layout);
    }

    /**
     * Change the background to one of the RGB colors: red, green, blue
     *
     * @param view the view
     */
    public void changeBackground(View view) {
        color = new int[]{0, 0, 0};

        adb = new AlertDialog.Builder(this);
        adb.setCancelable(false);
        adb.setTitle("chose color");
        adb.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                color[which] = 255;
                layout.setBackgroundColor(Color.rgb(color[0], color[1], color[2]));
            }
        });

        adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog ad = adb.create();
        ad.show();
    }

    /**
     * Show the user a large selection of colors, their combination, apply the Layout background
     *
     * @param view the view
     */
    public void multiColorChange(View view) {
        color = new int[]{0, 0, 0};

        adb = new AlertDialog.Builder(this);
        adb.setCancelable(false);
        adb.setTitle("chose color/s");
        adb.setMultiChoiceItems(colors, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked)
                    color[which] = 255;
                else if (color[which] == 255)
                    color[which] = 0;
            }
        });

        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                layout.setBackgroundColor(Color.rgb(color[0], color[1], color[2]));
            }
        });

        adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog ad = adb.create();
        ad.show();
    }

    /**
     * Reset layout background to white.
     *
     * @param view the view
     */
    public void restBackground(View view) {
        layout.setBackgroundColor(0);
    }

    /**
     * Get an input from the user and show it with a Toast
     *
     * @param view the view
     */
    public void inputDialog(View view) {
        adb = new AlertDialog.Builder(this);
        adb.setCancelable(false);
        adb.setTitle("Try input");
        final EditText input = new EditText(this);
        input.setHint("Write text here");
        adb.setView(input);

        adb.setPositiveButton("Apply", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String inputString = input.getText().toString();
                Toast.makeText(MainActivity.this, inputString, Toast.LENGTH_SHORT).show();
            }
        });

        adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog ad = adb.create();
        ad.show();
    }

    /**
     * Create the options menu
     *
     * @param menu the menu
     * @return ture if success
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * go to credits activity if it was clicked at the menu
     *
     * @param item the item in menu that was clicked
     * @return true - if it success
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String title = (String) item.getTitle();

        if (title.equals("Credits"))
        {
            Intent si = new Intent(this, CreditsActivity.class);
            startActivity(si);
        }

        return true;
    }
}