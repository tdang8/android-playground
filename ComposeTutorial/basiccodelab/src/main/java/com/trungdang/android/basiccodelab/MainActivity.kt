@file:OptIn(ExperimentalMaterial3Api::class)

package com.trungdang.android.basiccodelab

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.overscroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.trungdang.android.basiccodelab.ui.theme.BasicCodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicCodelabTheme {
                MyApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}


@Composable
fun MyApp(
    modifier: Modifier = Modifier,
) {
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }
    Surface(modifier) {
        if (shouldShowOnboarding) {
            OnboardingScreen(onContinueClicked = {
                shouldShowOnboarding = false
            })
        } else {
            Greetings()
        }
    }
}


@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier, onContinueClicked: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to the Basics Codelab")
        Button(
            modifier = Modifier.padding(vertical = 24.dp), onClick = onContinueClicked
        ) {
            Text(text = "Continue")
        }
    }
}

@Composable
private fun Greetings(
    modifier: Modifier = Modifier, names: List<String> = List(1000) { "$it" }
) {
    LazyColumn(modifier = modifier.padding(4.dp)) {
        items(items = names) { name ->
            Greeting(name)
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }
    Card(
        modifier = modifier
            .clickable(MutableInteractionSource(), indication = null, onClick = {
                isExpanded = !isExpanded
            })
            .padding(4.dp),
        colors = CardDefaults.cardColors(
            MaterialTheme.colorScheme.primary
        )
    ) {
        Row(
            modifier
                .padding(horizontal = 24.dp, vertical = 12.dp)
                .animateContentSize()
        ) {
            Column(
                Modifier
                    .weight(1f)
            ) {
                Text("Hello ")
                Text(
                    name, style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )
                if (isExpanded) {
                    Text(text = stringResource(R.string.lorem_ipsum))
                }
            }
            IconButton(onClick = {
                isExpanded = !isExpanded
            }) {
                Icon(
                    imageVector = if (isExpanded) {
                        Icons.Filled.ExpandLess
                    } else Icons.Filled.ExpandMore,
                    contentDescription = if (isExpanded) stringResource(R.string.show_less) else stringResource(
                        R.string.show_more
                    )
                )
            }
        }
    }
//    Surface(modifier = modifier.padding(4.dp), color = MaterialTheme.colorScheme.primary) {
//
//    }
}


@Preview(showBackground = true, widthDp = 320)
@Composable
fun MyAppPreview() {
    BasicCodelabTheme {
        MyApp(modifier = Modifier.fillMaxSize())
    }
}


@Preview(
    showBackground = true, widthDp = 320, heightDp = 320
)
@Composable
fun OnboardingScreenPreview() {
    BasicCodelabTheme {
        OnboardingScreen(onContinueClicked = {

        })
    }
}

@Preview(
    showBackground = true, widthDp = 320
)
@Composable
fun GreetingsPreview() {
    BasicCodelabTheme {
        Greetings()
    }
}

@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "GreetingsPreviewDark"
)
@Composable
fun GreetingsPreviewDark() {
    BasicCodelabTheme {
        Greetings()
    }
}