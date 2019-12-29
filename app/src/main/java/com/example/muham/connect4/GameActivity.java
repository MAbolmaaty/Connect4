package com.example.muham.connect4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.support.v7.widget.GridLayout;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.muham.connect4.settings.SettingsActivity;

public class GameActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    private GridLayout mGridLayout;

    // True : red , False : black
    private Boolean playerTurn = true;

    private int connect = 1 ;

    private TextView winnerTextView;

    private Button playAgainButton;

    private boolean gameActive = true;

    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mMediaPlayer = MediaPlayer.create(this, R.raw.short_triumphal_fanfare);

        mMediaPlayer.start();

        mMediaPlayer = MediaPlayer.create(this, R.raw.tiny_button_push);

        mGridLayout = (GridLayout) findViewById(R.id.grid_layout);

        winnerTextView = (TextView) findViewById(R.id.winner_text_view);

        winnerTextView.setVisibility(View.INVISIBLE);

        playAgainButton = (Button) findViewById(R.id.play_again_button);

        final int count = mGridLayout.getChildCount();

        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAgainButton.setVisibility(View.INVISIBLE);
                winnerTextView.setVisibility(View.INVISIBLE);
                gameActive = true;
                connect = 1;
                for(int i = 0 ; i < count ; i++){
                    ImageView child = (ImageView) mGridLayout.getChildAt(i);
                    child.setTag("Empty");
                    child.setImageDrawable(null);
                }
            }
        });

        childrenClickListener();

        setupSharedPreferences();
    }

    private void setupSharedPreferences(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        loadBoardColorFromPreferences(sharedPreferences);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);

    }

    private void loadBoardColorFromPreferences(SharedPreferences sharedPreferences){
        String boardColor = sharedPreferences.getString(getString(R.string.pref_board_color_key),
                getString(R.string.pref_color_blue_value));

        if(boardColor.equals("blue")){
            mGridLayout.setBackgroundResource(R.drawable.board);
        }
        else if(boardColor.equals("brown")){
            mGridLayout.setBackgroundResource(R.drawable.board2);
        }
        else if(boardColor.equals("purple")){
            mGridLayout.setBackgroundResource(R.drawable.board3);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings){
            Intent startSettingsActivity = new Intent(this, SettingsActivity.class);
            startActivity(startSettingsActivity);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void childrenClickListener (){
        int count = mGridLayout.getChildCount();
        for(int i = 0 ; i < count ; i++){
            final ImageView container = (ImageView) mGridLayout.getChildAt(i);
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mMediaPlayer.isPlaying()){
                        mMediaPlayer.seekTo(0);
                    }
                    mMediaPlayer.start();
                    int currentChild = mGridLayout.indexOfChild(container);
                    int column = getColumn(currentChild);
                    switch (column){
                        case 0:
                            for (int j = 5 ; j >= 0  ; j-- ){
                                ImageView prevContainer = (ImageView) mGridLayout.getChildAt(j);
                                if(prevContainer.getDrawable() == null){
                                    dropDisc(prevContainer);
                                    break;
                                }
                            }
                            break;

                        case 1:
                            for (int j = 11 ; j >= 6  ; j-- ){
                                ImageView prevContainer = (ImageView) mGridLayout.getChildAt(j);
                                if(prevContainer.getDrawable() == null){
                                    dropDisc(prevContainer);
                                    break;
                                }
                            }
                            break;

                        case 2:
                            for (int j = 17 ; j >= 12  ; j-- ){
                                ImageView prevContainer = (ImageView) mGridLayout.getChildAt(j);
                                if(prevContainer.getDrawable() == null){
                                    dropDisc(prevContainer);
                                    break;
                                }
                            }
                            break;

                        case 3:
                            for (int j = 23 ; j >= 18  ; j-- ){
                                ImageView prevContainer = (ImageView) mGridLayout.getChildAt(j);
                                if(prevContainer.getDrawable() == null){
                                    dropDisc(prevContainer);
                                    break;
                                }
                            }
                            break;

                        case 4:
                            for (int j = 29 ; j >= 24  ; j-- ){
                                ImageView prevContainer = (ImageView) mGridLayout.getChildAt(j);
                                if(prevContainer.getDrawable() == null){
                                    dropDisc(prevContainer);
                                    break;
                                }
                            }
                            break;

                        case 5:
                            for (int j = 35 ; j >= 30  ; j-- ){
                                ImageView prevContainer = (ImageView) mGridLayout.getChildAt(j);
                                if(prevContainer.getDrawable() == null){
                                    dropDisc(prevContainer);
                                    break;
                                }
                            }
                            break;

                        case 6:
                            for (int j = 41 ; j >= 36  ; j-- ){
                                ImageView prevContainer = (ImageView) mGridLayout.getChildAt(j);
                                if(prevContainer.getDrawable() == null){
                                    dropDisc(prevContainer);
                                    break;
                                }
                            }
                            break;
                    }
                }
            });
        }
    }

    private int getColumn(int childIndex){
        if(childIndex >= 0 && childIndex <= 5){
            return 0;
        }
        else if(childIndex >= 6 && childIndex <= 11){
            return 1;
        }
        else if(childIndex >= 12 && childIndex <= 17){
            return 2;
        }
        else if(childIndex >= 18 && childIndex <= 23){
            return 3;
        }
        else if(childIndex >= 24 && childIndex <= 29){
            return 4;
        }
        else if(childIndex >= 30 && childIndex <= 35){
            return 5;
        }
        else if(childIndex >= 36 && childIndex <= 41){
            return 6;
        }
        else {
            return -1;
        }
    }

    private boolean inLastRow(int childIndex){

        int [] sideRowChildsIndexes = {5,11,17,23,29,35,41};

        for(int sideRowChildIndex : sideRowChildsIndexes ){
            if(sideRowChildIndex == childIndex)
                return true;
        }
        return false;
    }

    private boolean inFirstRow(int childIndex){

        int [] firstRowChildsIndexes = {0,6,12,18,24,30,36};

        for(int firstRowChildIndex : firstRowChildsIndexes ){
            if(firstRowChildIndex == childIndex)
                return true;
        }
        return false;
    }

    private void dropDisc(ImageView child){
        if(!gameActive)
            return;

        child.setTranslationY(-1500);

        if(playerTurn){
            child.setImageResource(R.drawable.red_disc);
            child.setTag("RED");
            playerTurn = false;
        }
        else{
            child.setImageResource(R.drawable.black_disc);
            child.setTag("BLACK");
            playerTurn = true;
        }
        child.animate().translationYBy(1500).rotation(3600).setDuration(300);

        connect = 1;
        checkDownDirection(child);
        checkForAWinner(connect, child);

        connect = 1;
        checkRightDirection(child);
        checkLeftDirection(child);
        checkForAWinner(connect, child);

        connect = 1;
        checkUpLeftDiagonalDirection(child);
        checkDownRightDiagonalDirection(child);
        checkForAWinner(connect, child);

        connect = 1;
        checkUpRightDiagonalDirection(child);
        checkDownLeftDiagonalDirection(child);
        checkForAWinner(connect, child);

        if(inFirstRow(mGridLayout.indexOfChild(child))){
            if(isBoardFull() && gameActive) {
                playAgainButton.setVisibility(View.VISIBLE);
                gameActive = false;
                winnerTextView.setText("No Winner");
                winnerTextView.setTextColor(Color.WHITE);
                winnerTextView.setVisibility(View.VISIBLE);
                mMediaPlayer = MediaPlayer.create(this, R.raw.sad_trombone);

                mMediaPlayer.start();

                mMediaPlayer = MediaPlayer.create(this, R.raw.tiny_button_push);
            }
        }

    }

    private void checkForAWinner(int connectNumber, ImageView child){
        if (connectNumber >= 4){
            gameActive = false;
            String winner = child.getTag().toString();
            winnerTextView.setText("The Winner is " + winner);
            if(winner.equals("RED"))
                winnerTextView.setTextColor(Color.RED);
            else if(winner.equals("BLACK"))
                winnerTextView.setTextColor(Color.BLACK);

            winnerTextView.setRotation(-3600);
            winnerTextView.setTranslationY(-1500);
            winnerTextView.setVisibility(View.VISIBLE);
            winnerTextView.animate().translationYBy(1500).rotation(3600).setDuration(500);

            playAgainButton.setVisibility(View.VISIBLE);

            mMediaPlayer = MediaPlayer.create(this, R.raw.crowd_applause);

            mMediaPlayer.start();

            mMediaPlayer = MediaPlayer.create(this, R.raw.tiny_button_push);

        }
    }

    private boolean isBoardFull(){
        int [] firstRowChildsIndexes = {0,6,12,18,24,30,36};
        for (int firstRowchildIndex : firstRowChildsIndexes){
            ImageView child = (ImageView) mGridLayout.getChildAt(firstRowchildIndex);
            if(child.getDrawable() == null)
                return false;
        }
        return true;
    }

    private void checkDownDirection(ImageView child){
        int childIndex = mGridLayout.indexOfChild(child);
        if(!inLastRow(childIndex)){
            ImageView nextChild = (ImageView) mGridLayout.getChildAt(childIndex+1);
            if(nextChild.getTag().equals(child.getTag())){
                connect++;
                checkDownDirection(nextChild);
            }
        }
    }

    private void checkRightDirection(ImageView child){
        int childIndex = mGridLayout.indexOfChild(child);
        if(!(getColumn(childIndex) == 6)){
            ImageView nextChild = (ImageView) mGridLayout.getChildAt(childIndex+6);
            if(nextChild.getTag().equals(child.getTag())){
                connect++;
                checkRightDirection(nextChild);
            }
        }
    }

    private void checkLeftDirection(ImageView child){
        int childIndex = mGridLayout.indexOfChild(child);
        if(!(getColumn(childIndex) == 0)){
            ImageView nextChild = (ImageView) mGridLayout.getChildAt(childIndex-6);
            if(nextChild.getTag().equals(child.getTag())){
                connect++;
                checkLeftDirection(nextChild);
            }
        }
    }

    private void checkUpLeftDiagonalDirection(ImageView child){
        int childIndex = mGridLayout.indexOfChild(child);
        if(!(getColumn(childIndex) == 0) && !inFirstRow(childIndex)){
            ImageView nextChild = (ImageView) mGridLayout.getChildAt(childIndex-7);
            if(nextChild.getTag().equals(child.getTag())){
                connect++;
                checkUpLeftDiagonalDirection(nextChild);
            }
        }
    }

    private void checkDownRightDiagonalDirection(ImageView child){
        int childIndex = mGridLayout.indexOfChild(child);
        if(!(getColumn(childIndex) == 6) && !inLastRow(childIndex)){
            ImageView nextChild = (ImageView) mGridLayout.getChildAt(childIndex+7);
            if(nextChild.getTag().equals(child.getTag())){
                connect++;
                checkDownRightDiagonalDirection(nextChild);
            }
        }
    }

    private void checkUpRightDiagonalDirection(ImageView child){
        int childIndex = mGridLayout.indexOfChild(child);
        if(!(getColumn(childIndex) == 6) && !inFirstRow(childIndex)){
            ImageView nextChild = (ImageView) mGridLayout.getChildAt(childIndex+5);
            if(nextChild.getTag().equals(child.getTag())){
                connect++;
                checkUpRightDiagonalDirection(nextChild);
            }
        }
    }

    private void checkDownLeftDiagonalDirection(ImageView child){
        int childIndex = mGridLayout.indexOfChild(child);
        if(!(getColumn(childIndex) == 0) && !inLastRow(childIndex)){
            ImageView nextChild = (ImageView) mGridLayout.getChildAt(childIndex-5);
            if(nextChild.getTag().equals(child.getTag())){
                connect++;
                checkDownLeftDiagonalDirection(nextChild);
            }
        }
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if(key.equals(getString(R.string.pref_board_color_key))){
            loadBoardColorFromPreferences(sharedPreferences);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this)
                .unregisterOnSharedPreferenceChangeListener(this);
    }
}
