package com.bignerdranch.android.geoquiz

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bignerdranch.android.geoquiz.ui.theme.GeoQuizTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

                    val trueButton: Button = findViewById(R.id.true_button)
                    val falseButton: Button = findViewById(R.id.false_button)

                    trueButton.setOnClickListener { _: View ->
                        Toast.makeText(
                            this,
                            R.string.correct_toast,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    falseButton.setOnClickListener { _: View ->
                        Toast.makeText(
                            this,
                            R.string.incorrect_toast,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
            text = "There goes $name!",
            modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GeoQuizTheme {
        Greeting("Walter Bicklein")
    }
}