package com.vidlearn.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.StarHalf
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.PsychologyAlt
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vidlearn.data.Course
import com.vidlearn.data.Screen

val PrimaryGreen = Color(0xFF0B6B3A)
val SecondaryGreen = Color(0xFF1F8F55)
val DarkCharcoal = Color(0xFF111114)
val LightBorder = Color(0xFFD1D2E0)
val MutedText = Color(0xFF6A6F73)
val StarYellow = Color(0xFFE59819)

@Composable
fun VidlearnLogo(
    modifier: Modifier = Modifier,
    light: Boolean = false,
    onClick: (() -> Unit)? = null
) {
    val annotatedString = buildAnnotatedString {
        withStyle(SpanStyle(color = if (light) Color.White else PrimaryGreen, fontWeight = FontWeight.ExtraBold, fontSize = 22.sp)) {
            append("Vid")
        }
        withStyle(SpanStyle(color = if (light) Color.White else DarkCharcoal, fontWeight = FontWeight.ExtraBold, fontSize = 22.sp)) {
            append("learn")
        }
        withStyle(SpanStyle(color = SecondaryGreen, fontWeight = FontWeight.ExtraBold, fontSize = 22.sp)) {
            append(".")
        }
    }

    Box(
        modifier = modifier.then(
            if (onClick != null) Modifier.clickable { onClick() } else Modifier
        )
    ) {
        Text(text = annotatedString)
    }
}

@Composable
fun StarRatingBar(rating: Double) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        val rounded = kotlin.math.round(rating * 2) / 2.0
        for (i in 1..5) {
            val icon = when {
                i <= rounded -> Icons.Filled.Star
                i - 0.5 == rounded -> Icons.AutoMirrored.Filled.StarHalf
                else -> Icons.Filled.StarOutline
            }
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = StarYellow,
                modifier = Modifier.size(14.dp)
            )
        }
    }
}

@Composable
fun CategoryIcon(iconName: String) {
    val icon = when (iconName) {
        "layout" -> Icons.Filled.Book
        "code" -> Icons.Filled.Code
        "dollar-sign" -> Icons.Filled.MonetizationOn
        "film" -> Icons.Filled.Movie
        "cpu" -> Icons.Filled.Psychology
        "mic" -> Icons.Filled.Mic
        else -> Icons.Filled.Book
    }
    Icon(
        imageVector = icon,
        contentDescription = null,
        tint = Color.White,
        modifier = Modifier.size(24.dp)
    )
}

@Composable
fun CourseThumbnail(
    course: Course,
    modifier: Modifier = Modifier
) {
    val bgHex = try {
        Color(android.graphics.Color.parseColor(course.color))
    } catch (e: Exception) {
        PrimaryGreen
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(16f / 9f)
            .background(bgHex)
            .border(1.dp, LightBorder),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(100.dp)
                .background(Color.White.copy(alpha = 0.12f), CircleShape)
        )
        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .size(80.dp)
                .background(Color.White.copy(alpha = 0.15f), CircleShape)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(8.dp)
                .background(Color(0xCC101A16), RoundedCornerShape(12.dp))
                .padding(horizontal = 10.dp, vertical = 5.dp)
        ) {
            CategoryIcon(iconName = course.icon)
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = course.category,
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun CourseCard(
    course: Course,
    isEnrolled: Boolean,
    completedCount: Int,
    isFavorite: Boolean,
    onCourseClick: () -> Unit,
    modifier: Modifier = Modifier,
    onFavoriteToggle: (() -> Unit)? = null
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onCourseClick() }
            .padding(bottom = 12.dp)
    ) {
        Box {
            CourseThumbnail(course = course)
            if (onFavoriteToggle != null) {
                IconButton(
                    onClick = onFavoriteToggle,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(4.dp)
                        .background(Color.White.copy(alpha = 0.85f), CircleShape)
                        .size(32.dp)
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (isFavorite) Color.Red else DarkCharcoal,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }

        Text(
            text = course.title,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            lineHeight = 19.sp,
            modifier = Modifier.padding(top = 8.dp)
        )

        Text(
            text = course.instructor,
            color = MutedText,
            fontSize = 12.sp,
            modifier = Modifier.padding(top = 2.dp)
        )

        if (isEnrolled) {
            val total = course.lessons.size
            val progressFraction = if (total > 0) completedCount.toFloat() / total.toFloat() else 0f
            Spacer(modifier = Modifier.height(6.dp))
            LinearProgressIndicator(
                progress = { progressFraction },
                color = SecondaryGreen,
                trackColor = LightBorder,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
            )
            Text(
                text = "$completedCount of $total complete",
                color = MutedText,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        } else {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                Text(
                    text = course.rating.toString(),
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFB4690E),
                    fontSize = 13.sp
                )
                Spacer(modifier = Modifier.width(4.dp))
                StarRatingBar(rating = course.rating)
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "(${course.ratingsCount})",
                    color = MutedText,
                    fontSize = 12.sp
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 4.dp)
            ) {
                Text(
                    text = course.price,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = course.level,
                    color = MutedText,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 4.dp)
            ) {
                if (course.isBestseller) {
                    Text(
                        text = "Bestseller",
                        color = Color(0xFF0A5A38),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .background(Color(0xFFC6F0DD), RoundedCornerShape(2.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                }
                if (course.isHotAndNew) {
                    Text(
                        text = "Hot & New",
                        color = Color(0xFF8A1C1C),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .background(Color(0xFFFDE4E4), RoundedCornerShape(2.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                }
                if (course.isHighestRated) {
                    Text(
                        text = "Highest Rated",
                        color = Color(0xFF8F5300),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .background(Color(0xFFFDF3E3), RoundedCornerShape(2.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun TopBar(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onLogoClick: () -> Unit,
    onFavoritesClick: () -> Unit,
    onUserClick: () -> Unit,
    userName: String? = null
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .border(1.dp, LightBorder)
            .padding(horizontal = 14.dp, vertical = 8.dp)
    ) {
        VidlearnLogo(onClick = onLogoClick)
        Spacer(modifier = Modifier.width(12.dp))

        OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchQueryChange,
            placeholder = { Text("Search courses...", fontSize = 13.sp) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search",
                    tint = DarkCharcoal,
                    modifier = Modifier.size(18.dp)
                )
            },
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = PrimaryGreen,
                unfocusedBorderColor = LightBorder,
                focusedContainerColor = Color(0xFFF7F9FA),
                unfocusedContainerColor = Color(0xFFF7F9FA)
            ),
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier
                .weight(1f)
                .height(44.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        IconButton(onClick = onFavoritesClick) {
            Icon(
                imageVector = Icons.Filled.FavoriteBorder,
                contentDescription = "Saved courses",
                tint = DarkCharcoal
            )
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(34.dp)
                .clip(CircleShape)
                .background(DarkCharcoal)
                .clickable { onUserClick() }
        ) {
            if (!userName.isNullOrEmpty()) {
                Text(
                    text = userName.first().uppercase(),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            } else {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Account",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    currentScreen: Screen,
    onNavigate: (Screen) -> Unit
) {
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 8.dp
    ) {
        NavigationBarItem(
            selected = currentScreen == Screen.HOME,
            onClick = { onNavigate(Screen.HOME) },
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            label = { Text("Home", fontSize = 11.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = PrimaryGreen,
                selectedTextColor = PrimaryGreen,
                unselectedIconColor = MutedText,
                unselectedTextColor = MutedText,
                indicatorColor = Color(0xFFE7F4EC)
            )
        )
        NavigationBarItem(
            selected = currentScreen == Screen.MY_LEARNING,
            onClick = { onNavigate(Screen.MY_LEARNING) },
            icon = { Icon(Icons.Filled.PlayCircle, contentDescription = "My learning") },
            label = { Text("My learning", fontSize = 11.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = PrimaryGreen,
                selectedTextColor = PrimaryGreen,
                unselectedIconColor = MutedText,
                unselectedTextColor = MutedText,
                indicatorColor = Color(0xFFE7F4EC)
            )
        )
        NavigationBarItem(
            selected = currentScreen == Screen.ROLEPLAY,
            onClick = { onNavigate(Screen.ROLEPLAY) },
            icon = { Icon(Icons.Filled.PsychologyAlt, contentDescription = "Practice") },
            label = { Text("Practice", fontSize = 11.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = PrimaryGreen,
                selectedTextColor = PrimaryGreen,
                unselectedIconColor = MutedText,
                unselectedTextColor = MutedText,
                indicatorColor = Color(0xFFE7F4EC)
            )
        )
        NavigationBarItem(
            selected = currentScreen == Screen.CREATE_COURSE,
            onClick = { onNavigate(Screen.CREATE_COURSE) },
            icon = { Icon(Icons.Filled.Edit, contentDescription = "Teach") },
            label = { Text("Teach", fontSize = 11.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = PrimaryGreen,
                selectedTextColor = PrimaryGreen,
                unselectedIconColor = MutedText,
                unselectedTextColor = MutedText,
                indicatorColor = Color(0xFFE7F4EC)
            )
        )
        NavigationBarItem(
            selected = currentScreen == Screen.LOGIN,
            onClick = { onNavigate(Screen.LOGIN) },
            icon = { Icon(Icons.Filled.Person, contentDescription = "Account") },
            label = { Text("Account", fontSize = 11.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = PrimaryGreen,
                selectedTextColor = PrimaryGreen,
                unselectedIconColor = MutedText,
                unselectedTextColor = MutedText,
                indicatorColor = Color(0xFFE7F4EC)
            )
        )
    }
}
