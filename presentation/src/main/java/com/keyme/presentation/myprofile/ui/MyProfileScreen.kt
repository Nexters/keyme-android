package com.keyme.presentation.myprofile.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.keyme.domain.entity.member.Member
import com.keyme.domain.entity.response.MemberStatistics
import com.keyme.domain.entity.response.QuestionStatistic
import com.keyme.presentation.R
import com.keyme.presentation.designsystem.component.KeymeText
import com.keyme.presentation.designsystem.component.KeymeTextType
import com.keyme.presentation.designsystem.component.KeymeToolTip
import com.keyme.presentation.myprofile.MyProfileUiState
import com.keyme.presentation.utils.clickableRippleEffect
import kotlinx.coroutines.launch

enum class MyProfileTab(val title: String) {
    Similar("가장 비슷한"), Different("가장 차이나는")
}

private val myProfileTabs = listOf(MyProfileTab.Similar, MyProfileTab.Different)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyProfileScreen(
    myProfileUiState: MyProfileUiState,
    myCharacter: Member,
    mySimilarStatistics: MemberStatistics,
    myDifferentStatistics: MemberStatistics,
    onInfoClick: () -> Unit,
    onToolTipCloseClick: () -> Unit,
    onQuestionClick: (QuestionStatistic) -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        val pagerState = rememberPagerState(initialPage = 0)
        val coroutineScope = rememberCoroutineScope()

        MyProfileTopContainer(
            modifier = Modifier
                .fillMaxWidth()
                .zIndex(1f),
            myProfileUiState,
            myCharacter,
            pagerState.currentPage,
            myProfileTabs,
            onInfoClick = onInfoClick,
            onToolTipCloseClick = onToolTipCloseClick,
            onTabSelected = {
                coroutineScope.launch {
                    pagerState.scrollToPage(it)
                }
            },
        )

        HorizontalPager(
            pageCount = myProfileTabs.size,
            state = pagerState,
            userScrollEnabled = false,
        ) {
            when (myProfileTabs[it]) {
                MyProfileTab.Similar -> KeymeMemberStatisticsScreen(
                    memberStatistics = mySimilarStatistics,
                    onQuestionClick = { question -> onQuestionClick(question) },
                )

                MyProfileTab.Different -> KeymeMemberStatisticsScreen(
                    memberStatistics = myDifferentStatistics,
                    onQuestionClick = { question -> onQuestionClick(question) },
                )
            }
        }
    }
}

@Composable
private fun MyProfileTitle(modifier: Modifier = Modifier, onInfoClick: () -> Unit) {
    Row(
        modifier = modifier.padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        KeymeText(
            text = "마이",
            keymeTextType = KeymeTextType.BODY_3_SEMIBOLD,
            color = Color(0xFFF8F8F8),
        )
        Icon(
            modifier = Modifier.clickableRippleEffect(bounded = false) { onInfoClick() },
            painter = painterResource(id = R.drawable.info_circle),
            contentDescription = "",
            tint = Color.White,
        )
    }
}

@Composable
private fun MyProfileTopContainer(
    modifier: Modifier = Modifier,
    myProfileUiState: MyProfileUiState,
    myCharacter: Member,
    selectedTabIndex: Int,
    myProfileTabs: List<MyProfileTab>,
    onInfoClick: () -> Unit,
    onToolTipCloseClick: () -> Unit,
    onTabSelected: (Int) -> Unit,
) {
    var toolTipPosition by remember { mutableStateOf(Rect.Zero) }

    Box(modifier) {
        AnimatedVisibility(
            modifier = Modifier
                .width(250.dp)
                .offset(
                    with(LocalDensity.current) { toolTipPosition.bottomLeft.x.toDp() / 4 },
                    with(LocalDensity.current) { toolTipPosition.bottomLeft.y.toDp() - 5.dp },
                )
                .zIndex(1f),
            visible = myProfileUiState.showToolTip,
        ) {
            KeymeToolTip(text = "원의 크기가 클수록 해당 성격이 자신을 나타내는 지표가 됩니다.", onCloseClick = onToolTipCloseClick)
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            MyProfileTitle(
                modifier = Modifier.onGloballyPositioned { toolTipPosition = it.boundsInParent() },
                onInfoClick = onInfoClick,
            )

            Spacer(modifier = Modifier.height(10.dp))

            MyProfileTabRow(
                selectedTabIndex = selectedTabIndex,
                tabs = myProfileTabs,
                onTabSelected = onTabSelected,
            )

            Spacer(modifier = Modifier.height(18.dp))

            KeymeText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp),
                text = "친구들이 생각하는\n${myCharacter.nickname}님의 성격은?",
                keymeTextType = KeymeTextType.HEADING_1,
                color = Color(0xFFFFFFFF),
            )
        }

    }
}

@Composable
private fun MyProfileTabRow(
    selectedTabIndex: Int,
    tabs: List<MyProfileTab>,
    onTabSelected: (Int) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 16.dp)
            .border(width = 1.dp, color = Color(0xFF3C3C3C), shape = RoundedCornerShape(size = 16.dp))
            .background(color = Color(0x80363636), shape = RoundedCornerShape(size = 16.dp)),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        tabs.forEachIndexed { index, tab ->
            MyProfileTabItem(
                modifier = Modifier.weight(1f),
                text = tab.title,
                isSelected = selectedTabIndex == index,
                onClick = {
                    onTabSelected(index)
                },
            )
        }
    }
}

@Composable
private fun MyProfileTabItem(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .then(
                if (isSelected) {
                    Modifier
                        .padding(4.dp)
                        .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 16.dp))
                } else {
                    Modifier
                },
            )
            .clipToBounds()
            .clickableRippleEffect(bounded = false) { onClick() },
        contentAlignment = Alignment.Center,
    ) {
        KeymeText(
            text = text,
            keymeTextType = KeymeTextType.BODY_3_SEMIBOLD,
            color = if (isSelected) Color(0xFF171717) else Color.White,
        )
    }
}
