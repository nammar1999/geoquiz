package com.bignerdranch.android.geoquiz

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.bignerdranch.android.geoquiz.databinding.ActivityMainBinding

private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding
    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_ocean, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )

    private var currentIndex = 0
    private var isQuestionAnswered = false
    private var correctAnswersCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Toast.makeText(this, R.string.welcome, Toast.LENGTH_SHORT).show()

        binding.nextButton.setOnClickListener {
            if (currentIndex == questionBank.size - 1) {
                displayScore()
            } else {
                currentIndex = (currentIndex + 1) % questionBank.size
                isQuestionAnswered = false
                updateQuestion()
            }
        }

        binding.previousButton.setOnClickListener {
            currentIndex = (currentIndex - 1 + questionBank.size) % questionBank.size
            isQuestionAnswered = false
            updateQuestion()
        }

        updateQuestion()
    }

    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        binding.questionTextView.setText(questionTextResId)
        binding.trueButton.isEnabled = !isQuestionAnswered
        binding.falseButton.isEnabled = !isQuestionAnswered

        binding.trueButton.setOnClickListener { view: View ->
            checkAnswer(true)
        }

        binding.falseButton.setOnClickListener { view: View ->
            checkAnswer(false)
        }
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer

        if (!isQuestionAnswered) {
            if (userAnswer == correctAnswer) {
                correctAnswersCount++
            }
            isQuestionAnswered = true
            binding.trueButton.isEnabled = false
            binding.falseButton.isEnabled = false

            if (currentIndex == questionBank.size - 1) {
                displayScore()
            }
        }
    }

    private fun displayScore() {
        val scorePercentage = (correctAnswersCount.toDouble() / questionBank.size) * 100
        Toast.makeText(this, "Quiz completed! Score: $scorePercentage%", Toast.LENGTH_SHORT).show()
    }
}