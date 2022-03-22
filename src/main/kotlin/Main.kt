// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.material.MaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
@Preview
fun App() {
    MaterialTheme {
        var judge1Score by remember { mutableStateOf(100) }
        var judge2Score by remember { mutableStateOf(100) }
        var judge3Score by remember { mutableStateOf(100) }
        val average by remember { derivedStateOf { (judge1Score + judge2Score + judge3Score) / 3 } }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                JudgeScoreInput {
                    judge1Score = it
                }
                JudgeScoreInput {
                    judge2Score = it
                }
                JudgeScoreInput {
                    judge3Score = it
                }
            }
            Text(
                text = average.toString(),
            )
        }
    }
}

@Composable
fun JudgeScoreInput(onScoreUpdate: (Int) -> Unit) {
    var judgeScore by remember { mutableStateOf(100) }
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = judgeScore.toString(),
            color = Color.Blue,
            fontSize = 24.sp,
        )
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    judgeScore--
                    onScoreUpdate(judgeScore)
                }
            ) {
                Text("-1")
            }
            Button(
                onClick = {
                    judgeScore -= 5
                    onScoreUpdate(judgeScore)
                }
            ) {
                Text("-5")
            }
            Button(
                onClick = {
                    judgeScore -= 10
                    onScoreUpdate(judgeScore)
                }
            ) {
                Text("-10")
            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Drift Scores") {
        App()
    }
}
