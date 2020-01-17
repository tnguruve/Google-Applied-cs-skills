package com.example.scarnedice;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import java.util.Timer;
import java.util.TimerTask;
import android.graphics.drawable.Drawable;

public class MainActivity extends AppCompatActivity
{
    //private static final String TAG = "TEST_TAG";
    //Timer myTimer;

    public int userOverallScore ;
    public int userTurnScore ;
    public int computerOverallScore;
    public int computerTurnScore;
    public Button rollButton, holdButton,reset;
    TextView label;
    ImageView imageView;

     String userScoreLabel = "Your score : ";
     String compScoreLabel = " Computer score : ";
     String userTurnScoreLabel = " Your turn score : ";
     String compTurnScoreLabel = "\ncomputer turn score : ";
    String labelText = userScoreLabel + userOverallScore + compScoreLabel + computerOverallScore;
    int[] drawables = {R.drawable.dice1, R.drawable.dice2, R.drawable.dice3, R.drawable.dice4, R.drawable.dice5, R.drawable.dice6};

    Drawable a = getResources().getDrawable(R.drawable.dice1);








    Drawable b = getResources().getDrawable(R.drawable.dice2);
    Drawable c = getResources().getDrawable(R.drawable.dice3);
    Drawable d = getResources().getDrawable(R.drawable.dice4);
    Drawable e = getResources().getDrawable(R.drawable.dice5);
    Drawable f = getResources().getDrawable(R.drawable.dice6);



    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rollButton = (Button) findViewById(R.id.button );
        holdButton = (Button) findViewById(R.id.button2 );
        label = (TextView) findViewById(R.id.label);
        reset = (Button) findViewById(R.id.button3 );
        imageView =(ImageView) findViewById(R.id.imageView);
        //label.setText(Html.fromHtml(labelText));
   }

    private int rollDice()
    {
        Random random = new Random();
        int randomNumber = random.nextInt(6);
        return randomNumber;

    }
    public void rollButtonClick(View view)
    {
        int score=0;

        switch (rollDice()) {
            case 1:
                imageView.setImageDrawable(a);
                break;
            case 2:
                imageView.setImageDrawable(b);
                score++;
                break;
            case 3:
                imageView.setImageDrawable(c);
                score++;
                break;
            case 4:
                imageView.setImageDrawable(d);
                score++;
                break;
            case 5:
                imageView.setImageDrawable(e);
                score++;
                break;
            case 6:
                imageView.setImageDrawable(f);
                score++;
                break;

        }

    }
























    public void computerTurn() {
       public void run() {





                //disable the buttons while computer is playing


                runOnUiThread(new Runnable() {


                    @Override


                    public void run() {


                        enableButtons(false);


                    }


                });







                //roll dice for comp


                int computerRolledNumber = rollDice();







                //update the image on the ui thread


                final int finalComputerRolledNumber = computerRolledNumber;


                runOnUiThread(new Runnable() {


                    @Override


                    public void run() {


                        imageView.setImageResource(drawables[finalComputerRolledNumber]);


                    }


                });







                computerRolledNumber++;







                //if computer looses, set turnSCore to 0 and enable buttons for user's turn


                if (computerRolledNumber == 1) {


                    computerTurnScore = 0;


                    labelText = userScoreLabel + userOverallScore + compScoreLabel + computerOverallScore + userTurnScoreLabel + userTurnScore +


                            "\n computer rolled a one and lost it's chance";












                    //enable buttons, on ui thread


                    runOnUiThread(new Runnable() {


                        @Override


                        public void run() {


                            enableButtons(true);


                        }


                    });







                    //update the label


                    runOnUiThread(new Runnable() {


                        @Override


                        public void run() {


                            label.setText(Html.fromHtml(labelText));


                        }


                    });







                    //cancel the timer, this is exiting out of function


                    myTimer.cancel();







                }







                //if not 1, add the score to turn score


                else {


                    computerTurnScore += computerRolledNumber;


                    labelText = userScoreLabel + userOverallScore + compScoreLabel + computerOverallScore + userTurnScoreLabel + userTurnScore


                            + "\nComputer rolled a " + computerRolledNumber


                            + compTurnScoreLabel + computerTurnScore;







                    //update the label


                    runOnUiThread(new Runnable() {


                        @Override


                        public void run() {


                            label.setText(Html.fromHtml(labelText));


                        }


                    });







                    //if the turn score is greater than 20 stop rolling and hold(update the comp score and cancel timer)


                    if (computerTurnScore > 20) {







                        runOnUiThread(new Runnable() {


                            @Override


                            public void run() {


                                enableButtons(true);


                            }


                        });







                        computerOverallScore += computerTurnScore;


                        computerTurnScore = 0;


                        labelText = userScoreLabel + userOverallScore + compScoreLabel + computerOverallScore + "\nComputer holds";







                        runOnUiThread(new Runnable() {


                            @Override


                            public void run() {


                                label.setText(Html.fromHtml(labelText));


                            }


                        });












                        myTimer.cancel();












                    }







                }







            }







        }, 0, 2000);












    }

















   /*private void computerTurn() {






       Log.d(TAG, "computerTurn called ");






       //disable all the buttons first


       enableButtons(false);






       //infinite loop until computer loses turn


       while (true) {


           //roll dice by comp


           int computerRolledNumber = rollDice();


           imageView.setImageResource(drawables[computerRolledNumber]);


           computerRolledNumber++;






           Log.d(TAG, "computerTurn: " + computerRolledNumber);






           //if comp rolled 1, make the turnScore 0, update the labels and enable the buttons


           if (computerRolledNumber == 1) {


               computerTurnScore = 0;


               labelText = userScoreLabel + userOverallScore + compScoreLabel + computerOverallScore + userTurnScoreLabel + userTurnScore


                       + "\n computer rolled a one and lost it's chance";


               enableButtons(true);


               label.setText(Html.fromHtml(labelText));


               return;


           }






           //else add the score to turnScore and update the label


           else {


               computerTurnScore += computerRolledNumber;


               labelText = userScoreLabel + userOverallScore + compScoreLabel + computerOverallScore + userTurnScoreLabel + userTurnScore


                       + "\nComputer rolled a " + computerRolledNumber;


               label.setText(Html.fromHtml(labelText));


           }






           //hold strategy for comp...if turnScore is > 20 then hold and save the turnScore and exit from this function, also enable the buttons


           if (computerTurnScore > 20) {


               computerOverallScore += computerTurnScore;


               computerTurnScore = 0;


               labelText = userScoreLabel + userOverallScore + compScoreLabel + computerOverallScore + "\n" +


                       "Computer holds";






               label.setText(Html.fromHtml(labelText));






               enableButtons(true);






               return;


           }


       }






   }*/







    public void holdButtonClick(View view) {







        userOverallScore += userTurnScore;


        userTurnScore = 0;







        labelText = userScoreLabel + userOverallScore + compScoreLabel + computerOverallScore + userTurnScoreLabel + userTurnScore;


        label.setText(Html.fromHtml(labelText));












        computerTurn();


    }







    public void resetButtonClick(View view) {







        userOverallScore = 0;


        userTurnScore = 0;


        computerOverallScore = 0;


        computerTurnScore = 0;







        labelText = userScoreLabel + userOverallScore + compScoreLabel + computerOverallScore;


        label.setText(Html.fromHtml(labelText));







        enableButtons(true);







    }








    private void enableButtons(boolean isEnabled) {


        rollButton.setEnabled(isEnabled);


        holdButton.setEnabled(isEnabled);


    }


}

