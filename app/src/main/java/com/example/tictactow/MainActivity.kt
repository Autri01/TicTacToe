package com.example.tictactow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Button as Button
import java.lang.reflect.Array as Array1


class MainActivity : AppCompatActivity() , View.OnClickListener{
    var Player=true;
    var TURN_COUNT=0;
    var boardStatus=Array(3){IntArray(3)}
    lateinit var board: Array<Array<Button>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        board= arrayOf(
                arrayOf(button1,button2,button3),
                arrayOf(button4,button5,button6),
                arrayOf(button7,button8,button9)
        )
        for(i in board){
            for (button in i){
                button.setOnClickListener(this)
            }
        }
        initializeBoardStatus()
        resetBtn.setOnClickListener{
           TURN_COUNT=0;
            Player=true
            initializeBoardStatus()
            updateDiplay("Player X Turn")
        }
    }

    private fun initializeBoardStatus() {
        for (i in 0..2){
            for (j in 0..2){
                boardStatus[i][j]=-1
                board[i][j].isEnabled=true;
                board[i][j].text=""
            }
        }
    }

    override fun onClick(view: View) {
      when(view.id){
          R.id.button1 ->{
              updateValue(row=0,col=0,player=Player)
          }
          R.id.button2 ->{
              updateValue(row=0,col=1,player=Player)
          }
          R.id.button3 ->{
              updateValue(row=0,col=2,player=Player)
          }
          R.id.button4 ->{
              updateValue(row=1,col=0,player=Player)
          }
          R.id.button5->{
              updateValue(row=1,col=1,player=Player)
          }
          R.id.button6 ->{
              updateValue(row=1,col=2,player=Player)
          }
          R.id.button7->{
              updateValue(row=2,col=0,player=Player)
          }
          R.id.button8 ->{
              updateValue(row=2,col=1,player=Player)
          }
          R.id.button9 ->{
              updateValue(row=2,col=2,player=Player)
          }
      }
        Player=!Player
        TURN_COUNT++
        if(Player){
            updateDiplay("Player X Turn")
        }else{
            updateDiplay("Player 0 Turn")
        }
        if(TURN_COUNT==9){
            updateDiplay("Gave Draw")
        }
        checkWinner()
    }

    private fun checkWinner() {
       //Horizontal rows
        for(i in 0..2){
            if(boardStatus[i][0]==boardStatus[i][1]&&boardStatus[i][0]==boardStatus[i][2]){
                if(boardStatus[i][0]==1){
                   updateDiplay("Player X Won")
                    break
                }else if(boardStatus[i][0]==0){
                    updateDiplay("Player O Won")
                    break
                }
            }
        }
        //Vertical colms
        for(i in 0..2){
            if(boardStatus[0][i]==boardStatus[1][i]&&boardStatus[0][i]==boardStatus[2][i]){
                if(boardStatus[0][i]==1){
                    updateDiplay("Player X Won")
                    break
                }else if(boardStatus[0][i]==0){
                    updateDiplay("Player O Won")
                    break
                }
            }
        }
        //First Diagonal
        if(boardStatus[0][0]==boardStatus[1][1]&&boardStatus[0][0]==boardStatus[2][2]){
            if(boardStatus[0][0]==1){
                updateDiplay("Player X Won")
            }else if(boardStatus[0][0]==0){
                updateDiplay("Player O Won")
            }
        }
        //Second Diagonal
        if(boardStatus[0][2]==boardStatus[1][1]&&boardStatus[0][2]==boardStatus[2][0]){
            if(boardStatus[0][2]==1){
                updateDiplay("Player X Won")
            }else if(boardStatus[0][2]==0){
                updateDiplay("Player O Won")
            }
        }
    }

    private fun updateDiplay(s: String) {
            displayTv.text=s
        if(s.contains("Won")){
            disableButton()
        }

    }
    private fun disableButton(){
        for(i in board){
            for (button in i){
                button.isEnabled=false
            }
        }
    }

    private fun updateValue(row: Int, col: Int, player: Boolean) {
        val text:String=if (player)"X" else "O"
        val value:Int=if (player) 1 else 0
        board[row][col].apply {
                isEnabled=false
                setText(text)
            }
        boardStatus[row][col]=value
    }
}