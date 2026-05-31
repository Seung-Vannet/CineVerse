package kh.edu.rupp.myapplication

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
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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
            item { Spacer(modifier = Modifier.height(16.dp)) }
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
            imageVector = Icons.Default.Tune,
            contentDescription = "Filter",
            tint = CineTextSecondary
        )
    }
}

@Composable
fun FeaturedMovie() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(CineSurface)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .background(Color.DarkGray) // Placeholder for image
        )
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "DUNE PART TWO",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Star, contentDescription = null, tint = Color.Yellow, modifier = Modifier.size(16.dp))
                Text(" 8.6  •  2024  •  2h 46m  •  PG-13", color = CineTextSecondary, fontSize = 12.sp)
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Paul Atreides unites with Chani and the Fremen while seeking revenge against the conspirators who destroyed his family.",
                color = CineTextSecondary,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = { },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = CinePrimary),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(Icons.Default.PlayArrow, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Play Now")
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(
                    onClick = { },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = CineSearchBackground),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("My List")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(5) { index ->
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .size(if (index == 0) 12.dp else 8.dp, 4.dp)
                            .clip(CircleShape)
                            .background(if (index == 0) CinePrimary else Color.Gray)
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
        MovieItem("Oppenheimer", "2h 20m left", 0.7f),
        MovieItem("The Batman", "1h 05m left", 0.4f),
        MovieItem("Avatar 2", "15m left", 0.9f)
    )
    LazyRow(contentPadding = PaddingValues(horizontal = 16.dp), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        items(movies) { movie ->
            Column(modifier = Modifier.width(150.dp)) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.DarkGray),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.PlayCircle, contentDescription = null, modifier = Modifier.size(48.dp), tint = Color.White)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = movie.title, fontWeight = FontWeight.Bold)
                Text(text = movie.subtitle, color = CineTextSecondary, fontSize = 12.sp)
                Spacer(modifier = Modifier.height(8.dp))
                LinearProgressIndicator(
                    progress = { movie.progress },
                    modifier = Modifier.fillMaxWidth().height(4.dp).clip(CircleShape),
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
        TrendingMovie("Spider-Man", 8.7),
        TrendingMovie("John Wick", 8.1),
        TrendingMovie("Interstellar", 8.6),
        TrendingMovie("The Dark Knight", 9.0)
    )
    LazyRow(contentPadding = PaddingValues(horizontal = 16.dp), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        items(movies) { movie ->
            Column(modifier = Modifier.width(120.dp)) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.DarkGray)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Star, contentDescription = null, tint = Color.Yellow, modifier = Modifier.size(12.dp))
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
        contentColor = CineTextSecondary
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

data class MovieItem(val title: String, val subtitle: String, val progress: Float)
data class TrendingMovie(val title: String, val rating: Double)
