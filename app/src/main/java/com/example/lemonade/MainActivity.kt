package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonadeApp(modifier: Modifier = Modifier) {
    LemonadeTheme {
        Scaffold(topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFFF9E44C),
                    titleContentColor = Color.Black,
                ),
                title = {
                    Text(
                        text = stringResource(R.string.app_name),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Bold
                    )
                },
            )
        }) { padding ->
            LemonadeScreen(Modifier.padding(padding))
        }
    }
}

@Composable
fun LemonadeScreen(modifier: Modifier = Modifier) {
    var randomTap by remember { mutableIntStateOf(Random.nextInt(2, 5)) }
    var countTap by rememberSaveable { mutableIntStateOf(0) }
    var step by rememberSaveable { mutableIntStateOf(0) }

    val currentImageAndDescription = when (step) {
        1 -> intArrayOf(
            R.drawable.lemon_squeeze,
            R.string.lemon,
            R.string.string_description_2
        )

        2 -> intArrayOf(
            R.drawable.lemon_drink,
            R.string.glass_of_lemonade,
            R.string.string_description_3
        )

        3 -> intArrayOf(
            R.drawable.lemon_restart,
            R.string.empty_glass,
            R.string.string_description_4
        )

        else -> {
            step = 0
            intArrayOf(
                R.drawable.lemon_tree,
                R.string.lemon_tree,
                R.string.string_description_1
            )
        }

    }

    Surface(modifier = modifier, color = MaterialTheme.colorScheme.background) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .size(250.dp)
                    .clip(shape = RoundedCornerShape(32.dp))
                    .background(color = Color(0xFFC3ECD2))
                    .clickable {
                        if (step == 1) {
                            if (countTap < randomTap) {
                                countTap++
                            }
                            if(countTap == randomTap){
                                step++
                                countTap = 0
                                randomTap = Random.nextInt(2, 5)
                            }
                        } else {
                            step++
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = currentImageAndDescription[0]),
                    contentDescription = stringResource(id = currentImageAndDescription[1])
                )
            }
            Spacer(modifier = Modifier.size(32.dp))
            Text(text = stringResource(currentImageAndDescription[2]))
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun LemonadeScreenPreview() {
    LemonadeScreen()
}


@Preview(showBackground = true)
@Composable
fun LemonadeAppPreview() {
    LemonadeTheme {
        LemonadeApp()
    }
}