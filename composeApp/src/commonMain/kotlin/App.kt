import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import data.MongoDB
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.core.context.startKoin
import org.koin.dsl.module
import presentation.screen.home.HomeScreen
import presentation.screen.home.HomeViewModel
import presentation.screen.task.TaskViewModel


val primaryColor = Color(color = 0xFF4c662b)
val onPrimaryColor = Color(color = 0xFFffffff)
val primaryContainerColor = Color(color = 0xFFcdeda3)
val onPrimaryContainerColor = Color(color = 0xFF102000)

val darkPrimaryColor = Color(color = 0xFF4c662b)
val darkOnPrimaryColor = Color(color = 0xFFffffff)
val darkPrimaryContainerColor = Color(color = 0xFFcdeda3)
val darkOnPrimaryContainerColor = Color(color = 0xFF102000)

@Composable
@Preview
fun App() {
    initializeKoin()

    val lightColors = lightColorScheme(
        primary = primaryColor,
        onPrimary = onPrimaryColor,
        primaryContainer = primaryContainerColor,
        onPrimaryContainer = onPrimaryContainerColor,
    )
    val darkColors = darkColorScheme(
        primary = darkPrimaryColor,
        onPrimary = darkOnPrimaryColor,
        primaryContainer = darkPrimaryContainerColor,
        onPrimaryContainer = darkOnPrimaryContainerColor,
    )

    val colors by mutableStateOf(
        if (isSystemInDarkTheme()) darkColors else lightColors
    )

    MaterialTheme(colorScheme = colors) {
        Navigator(HomeScreen()) {
            SlideTransition(it)
        }
    }
}

val mongodbModule = module {
    single { MongoDB() }
    factory { HomeViewModel(get()) }
    factory { TaskViewModel(get()) }
}

fun initializeKoin() {
    startKoin { modules(mongodbModule) }
}