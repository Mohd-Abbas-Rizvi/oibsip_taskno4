package com.example.quizapp

import android.graphics.Color
import android.graphics.Color.GREEN
import android.graphics.Color.RED
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.quizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val qrr = arrayOf(
        "Which of these parts of Computer would interpret a program’s instructions to initiate the control operations?",
        "Which of these computer languages do we use in artificial intelligence?",
        "A list of various coded instructions is known as",
        "Which of the following is the first callback method that is invoked by the system during an activity life-cycle in Android?",
        "What does API stand for?",
        // Additional questions
        "What is the purpose of the CPU in a computer?",
        "Which programming language is known for its simplicity and readability?",
        "What is the primary function of an operating system?",
        "What is the significance of a firewall in computer security?",
        "What is the difference between RAM and ROM?"
    )

    private val opt = arrayOf(
        arrayOf("Logic Unit", "Storage Unit", "Input Unit", "Control Unit"),
        arrayOf("C", "Fortran", "Prolog", "Cobol"),
        arrayOf("Flowchart", "Utility Program", "Algorithm", "Computer Program"),
        arrayOf("onClick() method", "onCreate() method", "onStart() method", "onRestart() method"),
        arrayOf(
            "Application Programming Interface",
            "Android Programming Interface",
            "Android Page Interface",
            "Application Page Interface"
        ),
        // Additional options
        arrayOf("Processing graphics", "Storing data", "Performing calculations", "Managing instructions"),
        arrayOf("Java", "Python", "C++", "JavaScript"),
        arrayOf("Running applications", "Managing hardware resources", "Providing internet connectivity", "Creating user interfaces"),
        arrayOf("Preventing unauthorized access", "Increasing internet speed", "Detecting viruses", "Enhancing screen resolution"),
        arrayOf("Volatile memory", "Non-volatile memory", "Temporary storage", "Permanent storage")
    )

    private val ans = arrayOf(3, 2, 3, 1, 0, 2, 1, 1, 0, 0)

    private var currquesind = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayQuestion()

        binding.btn1.setOnClickListener {
            checkAnswer(0)
        }
        binding.btn2.setOnClickListener {
            checkAnswer(1)
        }
        binding.btn3.setOnClickListener {
            checkAnswer(2)
        }
        binding.btn4.setOnClickListener {
            checkAnswer(3)
        }
        binding.btn5.setOnClickListener { restartQuiz() }
    }

    private fun correctButtonColor(buttonIndex: Int) {
        when (buttonIndex) {
            0 -> binding.btn1.setBackgroundColor(GREEN)
            1 -> binding.btn2.setBackgroundColor(GREEN)
            2 -> binding.btn3.setBackgroundColor(GREEN)
            3 -> binding.btn4.setBackgroundColor(GREEN)
        }
    }

    private fun wrongButtonColor(buttonIndex: Int) {
        when (buttonIndex) {
            0 -> binding.btn1.setBackgroundColor(RED)
            1 -> binding.btn2.setBackgroundColor(RED)
            2 -> binding.btn3.setBackgroundColor(RED)
            3 -> binding.btn4.setBackgroundColor(RED)
        }
    }

    private fun resetButtonColor() {
        binding.btn1.setBackgroundColor(Color.rgb(50, 59, 96))
        binding.btn2.setBackgroundColor(Color.rgb(50, 59, 96))
        binding.btn3.setBackgroundColor(Color.rgb(50, 59, 96))
        binding.btn4.setBackgroundColor(Color.rgb(50, 59, 96))
    }
//    private fun showResult(){
//        Toast.makeText(this,"Your Score:$score out of ${qrr.size}",Toast.LENGTH_LONG).show()
//        binding.btn5.isEnabled=true
//    }//using toast

    private fun showResult() {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Quiz Result")
        alertDialogBuilder.setMessage("Your Score: $score out of ${qrr.size}")
        alertDialogBuilder.setPositiveButton("OK") { _, _ ->
            // Handle OK button click if needed
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()

        // Enable the reset button after showing the dialog
        binding.btn5.isEnabled = true
    } //using dialogbox

    private fun displayQuestion() {
        binding.quest.text = qrr[currquesind]
        binding.btn1.text = opt[currquesind][0]
        binding.btn2.text = opt[currquesind][1]
        binding.btn3.text = opt[currquesind][2]
        binding.btn4.text = opt[currquesind][3]
        resetButtonColor()

        binding.btn5.isEnabled = false
    }

    private fun checkAnswer(selectedAnswerIndex: Int) {
        val correctAnswerIndex = ans[currquesind]
        if (correctAnswerIndex == selectedAnswerIndex) {
            score++
            correctButtonColor(selectedAnswerIndex)
        } else {
            wrongButtonColor(selectedAnswerIndex)
            correctButtonColor(correctAnswerIndex)
        }
        if (currquesind < qrr.size - 1) {
            currquesind++
            binding.quest.postDelayed({ displayQuestion() }, 500)
        } else {
            showResult()
        }
    }

    private fun restartQuiz() {
        currquesind = 0
        score = 0
        displayQuestion()
        binding.btn5.isEnabled = false
    }
}