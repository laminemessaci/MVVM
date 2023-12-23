package com.lamine.quiz

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.FragmentActivity
import com.lamine.quiz.databinding.ActivityMainBinding
import com.lamine.quiz.ui.theme.QuizTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : FragmentActivity()  {

    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_main)
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu_main, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            R.id.action_settings -> {
//                // Action lorsque "Settings" est sélectionné
//                true
//            }
//            R.id.action_about -> {
//                // Action lorsque "About" est sélectionné
//                true
//            }
//            else -> super.onOptionsItemSelected(item)
//        }
//    }

    override fun onStart() {
        super.onStart()
        Log.i("MainActivity", "onStart")
    }

    override fun onPause() {
        super.onPause()
        Log.i("MainActivity", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("MainActivity", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("MainActivity", "onDestroy")
    }

    override fun onResume() {
        super.onResume()
        Log.i("MainActivity", "onResume")
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    QuizTheme {
        Greeting("Android")
    }
}