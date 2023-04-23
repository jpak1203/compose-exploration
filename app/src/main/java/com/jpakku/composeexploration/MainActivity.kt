package com.jpakku.composeexploration

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jpakku.composeexploration.ui.theme.ComposeExplorationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeExplorationTheme {
                Surface {
                    Conversation(SampleData.conversationSample)
                }
            }
        }
    }
}

data class Message(val author: String, val body: String)

@Composable
fun MessageCard(msg: Message) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painterResource(R.drawable.profile_picture),
            "profile picture",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.secondary, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        var isExpanded by remember { mutableStateOf(false) }

        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.background
        )

        Column {
            Text(text = msg.author)

            Spacer(modifier = Modifier.height(4.dp))

            Surface(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
                Surface(
                    shape = MaterialTheme.shapes.medium,
                    color = surfaceColor,
                    modifier = Modifier.animateContentSize().padding(1.dp),
                    shadowElevation = 1.dp, tonalElevation = 1.dp
                ) {
                    Text(
                        text = msg.body,
                        color = MaterialTheme.colorScheme.onSecondary,
                        modifier = Modifier.padding(all = 4.dp),
                        style = MaterialTheme.typography.bodyLarge,
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        }
    }

}

@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode")
@Composable
fun PreviewMessageCard() {
    ComposeExplorationTheme {
        Surface {
            MessageCard(
                msg = Message("Colleague", "Take a look at Jetpack Compose, it's great!")
            )
        }
    }
}

@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { msg ->
            MessageCard(msg)
        }
    }
}

@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode")
@Composable
fun PreviewConversation() {
    ComposeExplorationTheme {
        Surface {
            Conversation(SampleData.conversationSample)
        }
    }
}