package com.guru.composecookbook.ui.demoui.twitter

import androidx.compose.foundation.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.EmphasisAmbient
import androidx.compose.material.IconButton
import androidx.compose.material.ProvideEmphasis
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.guru.composecookbook.R
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.data.model.Tweet
import com.guru.composecookbook.theme.typography

@Composable
fun TwitterListItem(tweet: Tweet) {
    Row {
        AuthorImage(tweet)
        Column(modifier = Modifier.padding(8.dp).fillMaxWidth()) {
            NameAndHandle(tweet)
            Text(text = tweet.text, style = typography.body1)
            TweetImage(tweet.tweetImageId)
            TweetIconSection(tweet = tweet)
            Divider(thickness = 0.5.dp)
        }
    }
}

@Composable
private fun NameAndHandle(tweet: Tweet) {
    Row(verticalGravity = Alignment.CenterVertically) {
        //username
        Text(text = tweet.author, style = typography.h6, modifier = Modifier.padding(end = 4.dp))
        Icon(
            asset = Icons.Default.CheckCircle,
            tint = twitterColor,
            modifier = Modifier.preferredSize(18.dp).gravity(Alignment.CenterVertically)
                .padding(top = 2.dp)
        )
        //handle
        ProvideEmphasis(emphasis = EmphasisAmbient.current.medium) {
            Text(
                text = tweet.handle + " . " + tweet.time,
                modifier = Modifier.padding(start = 8.dp),
                style = typography.body1,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun AuthorImage(tweet: Tweet) {
    Image(
        asset = imageResource(id = tweet.authorImageId),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(8.dp)
            .preferredSize(50.dp)
            .clip(CircleShape)
    )
}

@Composable
private fun TweetImage(imageId: Int) {
    if (imageId != 0) {
        Image(
            asset = imageResource(id = imageId),
            modifier = Modifier.padding(top = 8.dp)
                .fillMaxWidth()
                .preferredHeight(150.dp)
                .clip(RoundedCornerShape(4.dp)),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun TweetIconSection(tweet: Tweet) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(end = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = {}) {
            Row {
                Icon(
                    asset = vectorResource(id = R.drawable.ic_speech_bubble),
                    modifier = Modifier.preferredSize(16.dp),
                    tint = Color.LightGray
                )
                Text(
                    text = tweet.commentsCount.toString(),
                    modifier = Modifier.padding(start = 8.dp),
                    color = Color.LightGray,
                    style = typography.caption
                )
            }
        }
        IconButton(onClick = {}) {
            Row {
                Icon(
                    asset = vectorResource(id = R.drawable.ic_retweet_solid),
                    modifier = Modifier.preferredSize(16.dp),
                    tint = Color.LightGray
                )
                Text(
                    text = tweet.retweetCount.toString(),
                    modifier = Modifier.padding(start = 8.dp),
                    color = Color.LightGray,
                    style = typography.caption
                )
            }
        }
        IconButton(onClick = {}) {
            Row {
                Icon(
                    asset = Icons.Default.FavoriteBorder,
                    modifier = Modifier.preferredSize(16.dp),
                    tint = Color.LightGray
                )
                Text(
                    text = tweet.likesCount.toString(),
                    modifier = Modifier.padding(start = 8.dp),
                    color = Color.LightGray,
                    style = typography.caption
                )
            }
        }
        IconButton(onClick = {}) {
            Icon(
                asset = Icons.Default.Share,
                modifier = Modifier.preferredSize(16.dp),
                tint = Color.LightGray
            )
        }
    }

}


@Preview
@Composable
fun PreviewTwitterItem() {
    val tweet = DemoDataProvider.tweet
    TwitterListItem(tweet = tweet)
}