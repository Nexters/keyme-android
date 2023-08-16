package com.keyme.presentation.dailykeymetest

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.keyme.domain.entity.member.Member
import com.keyme.domain.entity.response.Category
import com.keyme.domain.entity.response.Test
import com.keyme.domain.entity.response.TestStatistic
import com.keyme.presentation.designsystem.component.KeymeText
import com.keyme.presentation.designsystem.component.KeymeTextType
import com.keyme.presentation.designsystem.theme.keyme_black
import com.keyme.presentation.utils.ColorUtil
import com.keyme.presentation.utils.clickableRippleEffect
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

@Composable
fun DailyKeymeTestScreen(
    myCharacter: Member,
    dailyKeymeTest: Test,
    dailyKeymeTestStatistic: TestStatistic? = null,
    onDailyKeymeTestClick: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (dailyKeymeTestStatistic != null) {
            DailyKeymeTestStatisticScreen(myCharacter, dailyKeymeTestStatistic)
        } else {
            DailKeymeTestScreen(myCharacter, dailyKeymeTest, onDailyKeymeTestClick)
        }
    }
}

@Composable
fun BoxScope.DailKeymeTestScreen(
    myCharacter: Member,
    dailyKeymeTest: Test,
    onDailyKeymeTestClick: () -> Unit,
) {
    WelComeTextTitle(modifier = Modifier.zIndex(1f), myCharacter = myCharacter)

    DailyKeymeTestCircle(
        modifier = Modifier.align(Alignment.Center),
        categories = dailyKeymeTest.questions.map { it.category },
        onClick = onDailyKeymeTestClick,
    )
}

@Composable
private fun WelComeTextTitle(modifier: Modifier = Modifier, myCharacter: Member) {
    KeymeText(
        modifier = modifier
            .padding(vertical = 75.dp, horizontal = 16.dp)
            .fillMaxWidth(),
        text = "환영해요 ${myCharacter.nickname}님! 이제 문제를 풀어볼까요?",
        keymeTextType = KeymeTextType.HEADING_1,
        color = Color(0xFFF8F8F8),
    )
}

@Composable
private fun DailyKeymeTestCircle(
    modifier: Modifier = Modifier,
    categories: List<Category>,
    onClick: () -> Unit,
) {
    if (categories.isEmpty()) return

    var expand by remember { mutableStateOf(false) }

    var selectedIndex by remember { mutableStateOf(0) }
    var selectedCategory by remember { mutableStateOf(categories[selectedIndex++]) }

    LaunchedEffect(key1 = selectedCategory, key2 = selectedIndex) {
        expand = true
        delay(800L)
        expand = false
    }

    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(key1 = Unit) {
        snapshotFlow { expand }
            .filter { !it }
            .collectLatest { isAnimEnd ->
                coroutineScope.launch {
                    delay(800L)
                    selectedIndex = if (selectedIndex > categories.lastIndex) 0 else selectedIndex
                    selectedCategory = categories[selectedIndex++]
                }
            }
    }

    DailyKeymeTestCircleImpl(modifier, expand = expand, category = selectedCategory, onClick = onClick)
}

private val initialSize = 0.dp
private val expandedSize = 90.dp

@Composable
private fun DailyKeymeTestCircleImpl(
    modifier: Modifier = Modifier,
    expand: Boolean,
    category: Category,
    onClick: () -> Unit,
) {
    val size by animateDpAsState(
        targetValue = if (expand) expandedSize else initialSize,
        animationSpec = spring(
            stiffness = 100f,
        ),
        label = "",
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(90.dp - size / 2)
            .aspectRatio(1f)
            .border(width = 1.dp, color = Color(0x4DFFFFFF), shape = CircleShape)
            .clip(CircleShape)
            .padding(1.dp)
            .background(color = Color(0x26FFFFFF), shape = CircleShape)
            .onGloballyPositioned {
                it.size
            }
            .clickableRippleEffect { onClick() },
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .background(color = ColorUtil.hexStringToColor(category.color), shape = CircleShape)
                .size(size),
        )

        AnimatedVisibility(
            visible = expand,
            enter = slideIn(
                animationSpec = spring(
                    stiffness = 100f,
                ),
                initialOffset = { IntOffset(200, 200) },
            ) + fadeIn(),
            exit = slideOut(
                animationSpec = spring(
                    stiffness = 100f,
                ),
                targetOffset = { IntOffset(200, 200) },
            ) + fadeOut(),
        ) {
            AsyncImage(
                modifier = Modifier.size(24.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(category.iconUrl)
                    .build(),
                contentDescription = "",
                contentScale = ContentScale.Crop,
            )
        }
    }
}

@Preview
@Composable
fun DailyKeymeTestCirclePreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = keyme_black),
        contentAlignment = Alignment.Center,
    ) {
        DailyKeymeTestCircle(categories = categories, onClick = { })
    }
}

val categories = listOf(
    Category(
        color = "FFB2F5",
        iconUrl = "https://keyme-ec2-access-s3.s3.ap-northeast-2.amazonaws.com/test_star.png",
        name = "",
    ),
    Category(
        color = "E5D85C",
        iconUrl = "https://keyme-ec2-access-s3.s3.ap-northeast-2.amazonaws.com/test_star.png",
        name = "",
    ),
    Category(
        color = "993800",
        iconUrl = "https://keyme-ec2-access-s3.s3.ap-northeast-2.amazonaws.com/test_star.png",
        name = "",
    ),
    Category(
        color = "6B66FF",
        iconUrl = "https://keyme-ec2-access-s3.s3.ap-northeast-2.amazonaws.com/test_star.png",
        name = "",
    ),
)
