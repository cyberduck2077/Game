package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public Button buttonMix;
    public Button btn1;
    public Button btn2;
    public Button btn3;
    public Button btn4;
    public Button btn5;
    public Button btn6;
    public Button btn7;
    public Button btn8;
    public Button btn9;
    public TextView ScoreValue;
    public Button [] buttonArray = new Button[9];
    public int [] numArray = new int[9];
    public int emptyBlock = 8;
    int score = 0;

    public TextView txtValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (Button)findViewById(R.id.button1);
        btn2 = (Button)findViewById(R.id.button2);
        btn3 = (Button)findViewById(R.id.button3);
        btn4 = (Button)findViewById(R.id.button4);
        btn5 = (Button)findViewById(R.id.button5);
        btn6 = (Button)findViewById(R.id.button6);
        btn7 = (Button)findViewById(R.id.button7);
        btn8 = (Button)findViewById(R.id.button8);
        btn9 = (Button)findViewById(R.id.button9);
        ScoreValue = (TextView)findViewById(R.id.textViewScore);
        fillArrayOfButtons();
        mix();

    }

    public void mixNumbers(){

        ArrayList<Integer> list = new ArrayList<Integer>();
        int r= -1;
        list.add(r);
        for(int i=0;i<numArray.length;i++){
            if(i!=emptyBlock) {//поскольку emptyBlock: 0-8
                while(list.contains(r)==true){//проверка на повторяющиеся значения
                    r = rnd(1,9);
                }
                list.add(r);
                buttonArray[i].setText(String.valueOf(r));

            }

        }
        list.clear();
    }

    public void fillArrayOfButtons(){
        buttonArray[0]=btn1;
        buttonArray[1]=btn2;
        buttonArray[2]=btn3;
        buttonArray[3]=btn4;
        buttonArray[4]=btn5;
        buttonArray[5]=btn6;
        buttonArray[6]=btn7;
        buttonArray[7]=btn8;
        buttonArray[8]=btn9;
    }

    public void mix(){


        emptyBlock = rnd(buttonArray.length-1);//рандомное пустое место
        mixNumbers();


        buttonArray[emptyBlock].setBackgroundColor(getResources().getColor(R.color.activeBlock));//устанавливаем цвет пустой кнопки
        buttonArray[emptyBlock].setText(getResources().getText(R.string.empty));//устанавливаем текст пустой кнопки

        //устанавливаем цвет и номера оставшихся кнопок (номера должны быть уникальными!)
        for(int i=0;i<buttonArray.length;i++){
            if(i!=emptyBlock){
                buttonArray[i].setBackgroundColor(getResources().getColor(R.color.nonActiveBlock));

            }
            else
                buttonArray[i].setBackgroundColor(getResources().getColor(R.color.activeBlock));
        }
    }

    public static int rnd(int max)//генерация случайных чисел включая max
    {
        return (int) (Math.random() * ++max);
    }

    public static int rnd(int min, int max){//при вызове добавляем +1
        Random r = new Random();
        int lowerBound = min;
        int upperBound = max;
        int result = r.nextInt(upperBound-lowerBound) + lowerBound;
        return result;
    }


    public void onClickMix(View view) {
        mix();
        score = 0;
        ScoreValue.setText("score: 0");
        //txtValue.setText("");
    }

    int idInArray;

    public void onClickBlock(View view) {

        idInArray = findClickBlock(view.getId());

        if(emptyBlock==(idInArray+1)&&idInArray!=5&&idInArray!=2){
            String text = (String)buttonArray[idInArray].getText();
            drawAllBlock();
            buttonArray[idInArray].setText(getResources().getText(R.string.empty));
            buttonArray[idInArray].setBackgroundColor(getResources().getColor(R.color.activeBlock));
            buttonArray[idInArray+1].setText(text);
            buttonArray[idInArray+1].setBackground(getResources().getDrawable(R.drawable.my_buutom_style));
            emptyBlock=idInArray;
            ScoreValue.setText("score: "+ ++score);
        }
        if(emptyBlock==(idInArray-1)&&idInArray!=6&&idInArray!=3){
            drawAllBlock();
            String text = (String)buttonArray[idInArray].getText();
            buttonArray[idInArray].setText(getResources().getText(R.string.empty));
            buttonArray[idInArray].setBackgroundColor(getResources().getColor(R.color.activeBlock));
            buttonArray[idInArray-1].setText(text);
            buttonArray[idInArray-1].setBackground(getResources().getDrawable(R.drawable.my_buutom_style));
            emptyBlock=idInArray;
            ScoreValue.setText("score: "+ ++score);
        }
        if(emptyBlock==(idInArray+3)){
            drawAllBlock();
            String text = (String)buttonArray[idInArray].getText();
            buttonArray[idInArray].setText(getResources().getText(R.string.empty));
            buttonArray[idInArray].setBackgroundColor(getResources().getColor(R.color.activeBlock));
            buttonArray[idInArray+3].setText(text);
            buttonArray[idInArray+3].setBackground(getResources().getDrawable(R.drawable.my_buutom_style));
            emptyBlock=idInArray;
            ScoreValue.setText("score: "+ ++score);
        }
        if(emptyBlock==(idInArray-3)){
            drawAllBlock();
            String text = (String)buttonArray[idInArray].getText();
            buttonArray[idInArray].setText(getResources().getText(R.string.empty));
            buttonArray[idInArray].setBackgroundColor(getResources().getColor(R.color.activeBlock));
            buttonArray[idInArray-3].setText(text);
            buttonArray[idInArray-3].setBackground(getResources().getDrawable(R.drawable.my_buutom_style));
            emptyBlock=idInArray;
            ScoreValue.setText("score: "+ ++score);
        }
        return;

    }
    public void drawAllBlock(){
        for(int i=0;i<buttonArray.length;i++){
            buttonArray[i].setBackgroundColor(getResources().getColor(R.color.nonActiveBlock));
        }
    }

//    public void moveBlocks(int idBlockEmpty, int idBlockFull){
//
//    }

    public int findClickBlock(int id){//ищем блок, на который нажал пользователь, в массиве

        int result=-1;
        for(int i =0;i<buttonArray.length;i++){
            if(buttonArray[i].getId()==id){
                result = i;
                break;
            }
        }
        return result;
    }


}