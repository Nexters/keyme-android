package com.keyme.presentation.myprofile.ui

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.keyme.domain.entity.member.Member
import com.keyme.domain.entity.response.MemberStatistics
import com.keyme.domain.entity.response.Question
import com.keyme.presentation.R
import com.keyme.presentation.designsystem.component.KeymeText
import com.keyme.presentation.designsystem.component.KeymeTextType
import com.keyme.presentation.utils.clickableRippleEffect
import kotlinx.coroutines.launch

enum class MyProfileTab(val title: String) {
    Similar("가장 비슷한"), Different("가장 차이나는")
}

private val myProfileTabs = listOf(MyProfileTab.Similar, MyProfileTab.Different)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyProfileScreen(
    myCharacter: Member,
    mySimilarStatistics: MemberStatistics,
    myDifferentStatistics: MemberStatistics,
    onQuestionClick: (Question) -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        val pagerState = rememberPagerState(initialPage = 0)
        val coroutineScope = rememberCoroutineScope()

        MyProfileTopContainer(
            myCharacter,
            pagerState.currentPage,
            myProfileTabs,
            onTabSelected = {
                coroutineScope.launch {
                    pagerState.scrollToPage(it)
                }
            },
        )

        HorizontalPager(
            pageCount = myProfileTabs.size,
            state = pagerState,
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
private fun MyProfileTitle() {
    Row(
        modifier = Modifier.padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        KeymeText(
            text = "마이",
            keymeTextType = KeymeTextType.BODY_3_SEMIBOLD,
            color = Color(0xFFF8F8F8),
        )
        Icon(
            painter = painterResource(id = R.drawable.info_circle),
            contentDescription = "",
            tint = Color.White,
        )
    }
}

@Composable
private fun MyProfileTopContainer(
    myCharacter: Member,
    selectedTabIndex: Int,
    myProfileTabs: List<MyProfileTab>,
    onTabSelected: (Int) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .zIndex(1f),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        MyProfileTitle()

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
