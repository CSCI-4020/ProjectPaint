package edu.apsu.csci.projectpaint;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

public class PaintActivity extends Activity implements RadioGroup.OnCheckedChangeListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);

        Intent intent = getIntent();
        String fileName;
        fileName = intent.getStringExtra(MainActivity.FILENAME_KEY);
        PaintView paintView = (PaintView) findViewById(R.id.imageButton);

        //paintView.readData(fileName);


        RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(this);

        findViewById(R.id.save_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(PaintActivity.this);
                builder.setTitle("Enter a Project name");

                final EditText editText = new EditText(PaintActivity.this);
                editText.setInputType(InputType.TYPE_TEXT_VARIATION_SHORT_MESSAGE);
                builder.setView(editText);

                builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        PaintView paintView = (PaintView) findViewById(R.id.imageButton);
                        String filename = editText.getText().toString();

                        paintView.save(filename);
                    }
                });

                builder.show();


            }
        });

        findViewById(R.id.undo_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PaintView paintView = (PaintView) findViewById(R.id.imageButton);



                paintView.undoArray();


            }
        });






//Comment
//Buttons that lets the user select the color of the new line
        findViewById(R.id.color_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PaintActivity.this);
                builder.setTitle("Colors");
                builder.setPositiveButton("Black", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        PaintView.setCurrentColor("Black");

                    }
                });

                builder.setNegativeButton("Blue", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PaintView.setCurrentColor("Blue");
                    }
                });
                builder.setNeutralButton("Red", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PaintView.setCurrentColor("Red");
                    }
                });


                builder.show();

            }
        });

        findViewById(R.id.thick_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PaintActivity.this);
                builder.setTitle("Thickness");
                builder.setPositiveButton("10pt", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PaintView.setCurrentThickness(10);


                    }
                });

                builder.setNegativeButton("20pt", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PaintView.setCurrentThickness(20);


                    }
                });
                builder.setNeutralButton("30pt", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PaintView.setCurrentThickness(30);


                    }
                });





                builder.show();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_paint, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if(checkedId == R.id.line_toggleButton){
            PaintView.shapeSelection(1);
        }else if(checkedId == R.id.circle_toggleButton){
            PaintView.shapeSelection(2);
        }else if(checkedId == R.id.square_toggleButton) {
            PaintView.shapeSelection(3);
        }



    }
}
