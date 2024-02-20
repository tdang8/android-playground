package com.trungdang.android.basicstatecodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.trungdang.android.basicstatecodelab.ui.theme.BasicStateCodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicStateCodelabTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    WellnessScreen()
                }
            }
        }
    }
}



@Composable
fun WaterCounter(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        var counter by rememberSaveable {
            mutableIntStateOf(0)
        }
        if (counter > 0) {

            var showTask by rememberSaveable {
                mutableStateOf(true)
            }
            if (showTask) {

            }

            Text(
                text = "You've had $counter glasses.",
                modifier = modifier.padding(16.dp)
            )
        }

        Row(

        ) {
            Button(
                modifier = Modifier.padding(top = 8.dp),
                onClick = {
                    counter++
                },
                enabled = counter < 10
            ) {
                Text(text = "Add one")
            }

            Button(
                modifier = Modifier.padding(top = 8.dp, start = 8.dp),
                onClick = {
                    counter = 0
                },
                enabled = counter > 0
            ) {
                Text(text = "Clear water count")
            }

        }
    }

}

@Composable
fun StatelessCounter(modifier: Modifier = Modifier, counter : Int, onIncrement: () -> Unit) {
    Column(modifier = modifier.padding(16.dp)) {
        if (counter > 0) {
            Text(
                text = "You've had $counter glasses.",
                modifier = modifier.padding(16.dp)
            )
        }
        Row(

        ) {
            Button(
                modifier = Modifier.padding(top = 8.dp),
                onClick = {
                    onIncrement()
                },
                enabled = counter < 10
            ) {
                Text(text = "Add one")
            }

//            Button(
//                modifier = Modifier.padding(top = 8.dp, start = 8.dp),
//                onClick = {
//                    counter = 0
//                },
//                enabled = counter > 0
//            ) {
//                Text(text = "Clear water count")
//            }

        }
    }
}

@Composable
fun StatefulCounter(modifier: Modifier = Modifier) {
    var count by rememberSaveable {
        mutableIntStateOf(0)
    }
    StatelessCounter(modifier = modifier, counter = count) {
        count++
    }
}

@Preview(
    showBackground = true,
    widthDp = 320,
    heightDp = 480
)
@Composable
fun StatefulCounterPreview() {
    BasicStateCodelabTheme {
        StatefulCounter()
    }
}