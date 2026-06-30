package kh.edu.rupp.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kh.edu.rupp.myapplication.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Scaffold(
        bottomBar = { CineBottomNavigation() },
        containerColor = CineBackground
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            item { TopBar() }
            item { SearchBar() }
            item { FeaturedMovie() }
            item { SectionHeader(title = "Continue Watching") }
            item { ContinueWatchingList() }
            item { SectionHeader(title = "Trending Now") }
            item { TrendingNowList() }
            item { Spacer(modifier = Modifier.height(32.dp)) }
        }
    }
}

@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "CINE",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            )
            Text(
                text = "VERSE",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = CinePrimary
                )
            )
        }
        Row {
            Icon(
                imageVector = Icons.Default.Cast,
                contentDescription = "Cast",
                tint = Color.White,
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Box {
                Icon(
                    imageVector = Icons.Outlined.Notifications,
                    contentDescription = "Notifications",
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(CinePrimary, CircleShape)
                        .align(Alignment.TopEnd)
                        .offset(x = (-2).dp, y = 2.dp)
                )
            }
        }
    }
}

@Composable
fun SearchBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(CineSearchBackground)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Search",
            tint = CineTextSecondary
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Search movies, shows...",
            color = CineTextSecondary,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            painter = painterResource(id = R.drawable.ic_tune),
            contentDescription = "Filter",
            tint = CineTextSecondary
        )
    }
}

@Composable
fun FeaturedMovie() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(500.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(CineSurface)
    ) {
        Image(
            painter = painterResource(id = R.drawable.dunetwo),
            contentDescription = "Dune Part Two",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        // Dark Gradient Overlay to make text readable
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.95f)),
                        startY = 400f
                    )
                )
        )
        // Content
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(20.dp)
        ) {
            Text(
                text = "DUNE",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White,
                    letterSpacing = 2.sp
                )
            )
            Text(
                text = "PART TWO",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Normal,
                    color = Color.White,
                    letterSpacing = 6.sp
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Star, contentDescription = null, tint = Color(0xFFFFAD0E), modifier = Modifier.size(16.dp))
                Text(" 8.6  •  2024  •  2h 46m  •  PG-13", color = CineTextSecondary, fontSize = 12.sp)
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Paul Atreides unites with Chani and the Fremen while seeking revenge against the conspirators who destroyed his family.",
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 14.sp,
                lineHeight = 20.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = { },
                    modifier = Modifier.weight(1f).height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = CinePrimary),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(Icons.Default.PlayArrow, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Play Now", fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.width(12.dp))
                Button(
                    onClick = { },
                    modifier = Modifier.weight(1f).height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White.copy(alpha = 0.1f)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("My List", fontWeight = FontWeight.Bold)
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(5) { index ->
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .size(if (index == 0) 16.dp else 8.dp, 3.dp)
                            .clip(CircleShape)
                            .background(if (index == 0) CinePrimary else Color.White.copy(alpha = 0.3f))
                    )
                }
            }
        }
    }
}

@Composable
fun SectionHeader(title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title, style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold))
        Text(text = "See All", color = CinePrimary, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun ContinueWatchingList() {
    val movies = listOf(
        MovieItem("Oppenheimer", "2h 20m left", 0.7f, R.drawable.oppenheimer),
        MovieItem("The Batman", "1h 05m left", 0.4f, R.drawable.thebatman),
        MovieItem("Avatar 2", "15m left", 0.9f, R.drawable.avatartwo)
    )
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(movies) { movie ->
            Column(modifier = Modifier.width(160.dp)) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(CineSurface)
                ) {
                    Image(
                        painter = painterResource(id = movie.imageRes),
                        contentDescription = movie.title,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    // Play icon overlay
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(Color.Black.copy(alpha = 0.5f))
                            .align(Alignment.Center)
                    ) {
                        Icon(
                            Icons.Default.PlayArrow,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = movie.title, fontWeight = FontWeight.Bold, color = Color.White)
                Text(text = movie.subtitle, color = CineTextSecondary, fontSize = 12.sp)
                Spacer(modifier = Modifier.height(8.dp))
                LinearProgressIndicator(
                    progress = { movie.progress },
                    modifier = Modifier.fillMaxWidth().height(3.dp).clip(CircleShape),
                    color = CinePrimary,
                    trackColor = Color.DarkGray,
                )
            }
        }
    }
}

@Composable
fun TrendingNowList() {
    val movies = listOf(
        TrendingMovie("Spider-Man", 8.7, R.drawable.spiderman_across_the_spiderverse),
        TrendingMovie("John Wick", 8.1, R.drawable.johnwickfour),
        TrendingMovie("Interstellar", 8.6, R.drawable.interstellar),
        TrendingMovie("The Batman", 8.4, R.drawable.thebatman)
    )
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(movies) { movie ->
            Column(modifier = Modifier.width(130.dp)) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(190.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(CineSurface)
                ) {
                    Image(
                        painter = painterResource(id = movie.imageRes),
                        contentDescription = movie.title,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Star, contentDescription = null, tint = Color(0xFFFFAD0E), modifier = Modifier.size(12.dp))
                    Text(" ${movie.rating}", color = CineTextSecondary, fontSize = 12.sp)
                }
            }
        }
    }
}

@Composable
fun CineBottomNavigation() {
    NavigationBar(
        containerColor = CineBackground,
        contentColor = CineTextSecondary,
        tonalElevation = 0.dp
    ) {
        val items = listOf(
            Triple("Home", Icons.Default.Home, true),
            Triple("Browse", Icons.Default.GridView, false),
            Triple("Watchlist", Icons.Default.BookmarkBorder, false),
            Triple("Profile", Icons.Default.PersonOutline, false)
        )
        items.forEach { (label, icon, selected) ->
            NavigationBarItem(
                selected = selected,
                onClick = { },
                icon = { Icon(icon, contentDescription = label) },
                label = { Text(label) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = CinePrimary,
                    selectedTextColor = CinePrimary,
                    unselectedIconColor = CineTextSecondary,
                    unselectedTextColor = CineTextSecondary,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}

data class MovieItem(val title: String, val subtitle: String, val progress: Float, val imageRes: Int)
data class TrendingMovie(val title: String, val rating: Double, val imageRes: Int)
