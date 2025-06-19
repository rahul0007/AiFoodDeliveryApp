package org.example.project.ui.theme
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import org.aifooddelivery.app.theme.backgroundDark
import org.aifooddelivery.app.theme.backgroundDarkHighContrast
import org.aifooddelivery.app.theme.backgroundDarkMediumContrast
import org.aifooddelivery.app.theme.backgroundLight
import org.aifooddelivery.app.theme.backgroundLightHighContrast
import org.aifooddelivery.app.theme.backgroundLightMediumContrast
import org.aifooddelivery.app.theme.errorContainerDark
import org.aifooddelivery.app.theme.errorContainerDarkHighContrast
import org.aifooddelivery.app.theme.errorContainerDarkMediumContrast
import org.aifooddelivery.app.theme.errorContainerLight
import org.aifooddelivery.app.theme.errorContainerLightHighContrast
import org.aifooddelivery.app.theme.errorContainerLightMediumContrast
import org.aifooddelivery.app.theme.errorDark
import org.aifooddelivery.app.theme.errorDarkHighContrast
import org.aifooddelivery.app.theme.errorDarkMediumContrast
import org.aifooddelivery.app.theme.errorLight
import org.aifooddelivery.app.theme.errorLightHighContrast
import org.aifooddelivery.app.theme.errorLightMediumContrast
import org.aifooddelivery.app.theme.inverseOnSurfaceDark
import org.aifooddelivery.app.theme.inverseOnSurfaceDarkHighContrast
import org.aifooddelivery.app.theme.inverseOnSurfaceDarkMediumContrast
import org.aifooddelivery.app.theme.inverseOnSurfaceLight
import org.aifooddelivery.app.theme.inverseOnSurfaceLightHighContrast
import org.aifooddelivery.app.theme.inverseOnSurfaceLightMediumContrast
import org.aifooddelivery.app.theme.inversePrimaryDark
import org.aifooddelivery.app.theme.inversePrimaryDarkHighContrast
import org.aifooddelivery.app.theme.inversePrimaryDarkMediumContrast
import org.aifooddelivery.app.theme.inversePrimaryLight
import org.aifooddelivery.app.theme.inversePrimaryLightHighContrast
import org.aifooddelivery.app.theme.inversePrimaryLightMediumContrast
import org.aifooddelivery.app.theme.inverseSurfaceDark
import org.aifooddelivery.app.theme.inverseSurfaceDarkHighContrast
import org.aifooddelivery.app.theme.inverseSurfaceDarkMediumContrast
import org.aifooddelivery.app.theme.inverseSurfaceLight
import org.aifooddelivery.app.theme.inverseSurfaceLightHighContrast
import org.aifooddelivery.app.theme.inverseSurfaceLightMediumContrast
import org.aifooddelivery.app.theme.onBackgroundDark
import org.aifooddelivery.app.theme.onBackgroundDarkHighContrast
import org.aifooddelivery.app.theme.onBackgroundDarkMediumContrast
import org.aifooddelivery.app.theme.onBackgroundLight
import org.aifooddelivery.app.theme.onBackgroundLightHighContrast
import org.aifooddelivery.app.theme.onBackgroundLightMediumContrast
import org.aifooddelivery.app.theme.onErrorContainerDark
import org.aifooddelivery.app.theme.onErrorContainerDarkHighContrast
import org.aifooddelivery.app.theme.onErrorContainerDarkMediumContrast
import org.aifooddelivery.app.theme.onErrorContainerLight
import org.aifooddelivery.app.theme.onErrorContainerLightHighContrast
import org.aifooddelivery.app.theme.onErrorContainerLightMediumContrast
import org.aifooddelivery.app.theme.onErrorDark
import org.aifooddelivery.app.theme.onErrorDarkHighContrast
import org.aifooddelivery.app.theme.onErrorDarkMediumContrast
import org.aifooddelivery.app.theme.onErrorLight
import org.aifooddelivery.app.theme.onErrorLightHighContrast
import org.aifooddelivery.app.theme.onErrorLightMediumContrast
import org.aifooddelivery.app.theme.onPrimaryContainerDark
import org.aifooddelivery.app.theme.onPrimaryContainerDarkHighContrast
import org.aifooddelivery.app.theme.onPrimaryContainerDarkMediumContrast
import org.aifooddelivery.app.theme.onPrimaryContainerLight
import org.aifooddelivery.app.theme.onPrimaryContainerLightHighContrast
import org.aifooddelivery.app.theme.onPrimaryContainerLightMediumContrast
import org.aifooddelivery.app.theme.onPrimaryDark
import org.aifooddelivery.app.theme.onPrimaryDarkHighContrast
import org.aifooddelivery.app.theme.onPrimaryDarkMediumContrast
import org.aifooddelivery.app.theme.onPrimaryLight
import org.aifooddelivery.app.theme.onPrimaryLightHighContrast
import org.aifooddelivery.app.theme.onPrimaryLightMediumContrast
import org.aifooddelivery.app.theme.onSecondaryContainerDark
import org.aifooddelivery.app.theme.onSecondaryContainerDarkHighContrast
import org.aifooddelivery.app.theme.onSecondaryContainerDarkMediumContrast
import org.aifooddelivery.app.theme.onSecondaryContainerLight
import org.aifooddelivery.app.theme.onSecondaryContainerLightHighContrast
import org.aifooddelivery.app.theme.onSecondaryContainerLightMediumContrast
import org.aifooddelivery.app.theme.onSecondaryDark
import org.aifooddelivery.app.theme.onSecondaryDarkHighContrast
import org.aifooddelivery.app.theme.onSecondaryDarkMediumContrast
import org.aifooddelivery.app.theme.onSecondaryLight
import org.aifooddelivery.app.theme.onSecondaryLightHighContrast
import org.aifooddelivery.app.theme.onSecondaryLightMediumContrast
import org.aifooddelivery.app.theme.onSurfaceDark
import org.aifooddelivery.app.theme.onSurfaceDarkHighContrast
import org.aifooddelivery.app.theme.onSurfaceDarkMediumContrast
import org.aifooddelivery.app.theme.onSurfaceLight
import org.aifooddelivery.app.theme.onSurfaceLightHighContrast
import org.aifooddelivery.app.theme.onSurfaceLightMediumContrast
import org.aifooddelivery.app.theme.onSurfaceVariantDark
import org.aifooddelivery.app.theme.onSurfaceVariantDarkHighContrast
import org.aifooddelivery.app.theme.onSurfaceVariantDarkMediumContrast
import org.aifooddelivery.app.theme.onSurfaceVariantLight
import org.aifooddelivery.app.theme.onSurfaceVariantLightHighContrast
import org.aifooddelivery.app.theme.onSurfaceVariantLightMediumContrast
import org.aifooddelivery.app.theme.onTertiaryContainerDark
import org.aifooddelivery.app.theme.onTertiaryContainerDarkHighContrast
import org.aifooddelivery.app.theme.onTertiaryContainerDarkMediumContrast
import org.aifooddelivery.app.theme.onTertiaryContainerLight
import org.aifooddelivery.app.theme.onTertiaryContainerLightHighContrast
import org.aifooddelivery.app.theme.onTertiaryContainerLightMediumContrast
import org.aifooddelivery.app.theme.onTertiaryDark
import org.aifooddelivery.app.theme.onTertiaryDarkHighContrast
import org.aifooddelivery.app.theme.onTertiaryDarkMediumContrast
import org.aifooddelivery.app.theme.onTertiaryLight
import org.aifooddelivery.app.theme.onTertiaryLightHighContrast
import org.aifooddelivery.app.theme.onTertiaryLightMediumContrast
import org.aifooddelivery.app.theme.outlineDark
import org.aifooddelivery.app.theme.outlineDarkHighContrast
import org.aifooddelivery.app.theme.outlineDarkMediumContrast
import org.aifooddelivery.app.theme.outlineLight
import org.aifooddelivery.app.theme.outlineLightHighContrast
import org.aifooddelivery.app.theme.outlineLightMediumContrast
import org.aifooddelivery.app.theme.outlineVariantDark
import org.aifooddelivery.app.theme.outlineVariantDarkHighContrast
import org.aifooddelivery.app.theme.outlineVariantDarkMediumContrast
import org.aifooddelivery.app.theme.outlineVariantLight
import org.aifooddelivery.app.theme.outlineVariantLightHighContrast
import org.aifooddelivery.app.theme.outlineVariantLightMediumContrast
import org.aifooddelivery.app.theme.primaryContainerDark
import org.aifooddelivery.app.theme.primaryContainerDarkHighContrast
import org.aifooddelivery.app.theme.primaryContainerDarkMediumContrast
import org.aifooddelivery.app.theme.primaryContainerLight
import org.aifooddelivery.app.theme.primaryContainerLightHighContrast
import org.aifooddelivery.app.theme.primaryContainerLightMediumContrast
import org.aifooddelivery.app.theme.primaryDark
import org.aifooddelivery.app.theme.primaryDarkHighContrast
import org.aifooddelivery.app.theme.primaryDarkMediumContrast
import org.aifooddelivery.app.theme.primaryLight
import org.aifooddelivery.app.theme.primaryLightHighContrast
import org.aifooddelivery.app.theme.primaryLightMediumContrast
import org.aifooddelivery.app.theme.scrimDark
import org.aifooddelivery.app.theme.scrimDarkHighContrast
import org.aifooddelivery.app.theme.scrimDarkMediumContrast
import org.aifooddelivery.app.theme.scrimLight
import org.aifooddelivery.app.theme.scrimLightHighContrast
import org.aifooddelivery.app.theme.scrimLightMediumContrast
import org.aifooddelivery.app.theme.secondaryContainerDark
import org.aifooddelivery.app.theme.secondaryContainerDarkHighContrast
import org.aifooddelivery.app.theme.secondaryContainerDarkMediumContrast
import org.aifooddelivery.app.theme.secondaryContainerLight
import org.aifooddelivery.app.theme.secondaryContainerLightHighContrast
import org.aifooddelivery.app.theme.secondaryContainerLightMediumContrast
import org.aifooddelivery.app.theme.secondaryDark
import org.aifooddelivery.app.theme.secondaryDarkHighContrast
import org.aifooddelivery.app.theme.secondaryDarkMediumContrast
import org.aifooddelivery.app.theme.secondaryLight
import org.aifooddelivery.app.theme.secondaryLightHighContrast
import org.aifooddelivery.app.theme.secondaryLightMediumContrast
import org.aifooddelivery.app.theme.surfaceBrightDark
import org.aifooddelivery.app.theme.surfaceBrightDarkHighContrast
import org.aifooddelivery.app.theme.surfaceBrightDarkMediumContrast
import org.aifooddelivery.app.theme.surfaceBrightLight
import org.aifooddelivery.app.theme.surfaceBrightLightHighContrast
import org.aifooddelivery.app.theme.surfaceBrightLightMediumContrast
import org.aifooddelivery.app.theme.surfaceContainerDark
import org.aifooddelivery.app.theme.surfaceContainerDarkHighContrast
import org.aifooddelivery.app.theme.surfaceContainerDarkMediumContrast
import org.aifooddelivery.app.theme.surfaceContainerHighDark
import org.aifooddelivery.app.theme.surfaceContainerHighDarkHighContrast
import org.aifooddelivery.app.theme.surfaceContainerHighDarkMediumContrast
import org.aifooddelivery.app.theme.surfaceContainerHighLight
import org.aifooddelivery.app.theme.surfaceContainerHighLightHighContrast
import org.aifooddelivery.app.theme.surfaceContainerHighLightMediumContrast
import org.aifooddelivery.app.theme.surfaceContainerHighestDark
import org.aifooddelivery.app.theme.surfaceContainerHighestDarkHighContrast
import org.aifooddelivery.app.theme.surfaceContainerHighestDarkMediumContrast
import org.aifooddelivery.app.theme.surfaceContainerHighestLight
import org.aifooddelivery.app.theme.surfaceContainerHighestLightHighContrast
import org.aifooddelivery.app.theme.surfaceContainerHighestLightMediumContrast
import org.aifooddelivery.app.theme.surfaceContainerLight
import org.aifooddelivery.app.theme.surfaceContainerLightHighContrast
import org.aifooddelivery.app.theme.surfaceContainerLightMediumContrast
import org.aifooddelivery.app.theme.surfaceContainerLowDark
import org.aifooddelivery.app.theme.surfaceContainerLowDarkHighContrast
import org.aifooddelivery.app.theme.surfaceContainerLowDarkMediumContrast
import org.aifooddelivery.app.theme.surfaceContainerLowLight
import org.aifooddelivery.app.theme.surfaceContainerLowLightHighContrast
import org.aifooddelivery.app.theme.surfaceContainerLowLightMediumContrast
import org.aifooddelivery.app.theme.surfaceContainerLowestDark
import org.aifooddelivery.app.theme.surfaceContainerLowestDarkHighContrast
import org.aifooddelivery.app.theme.surfaceContainerLowestDarkMediumContrast
import org.aifooddelivery.app.theme.surfaceContainerLowestLight
import org.aifooddelivery.app.theme.surfaceContainerLowestLightHighContrast
import org.aifooddelivery.app.theme.surfaceContainerLowestLightMediumContrast
import org.aifooddelivery.app.theme.surfaceDark
import org.aifooddelivery.app.theme.surfaceDarkHighContrast
import org.aifooddelivery.app.theme.surfaceDarkMediumContrast
import org.aifooddelivery.app.theme.surfaceDimDark
import org.aifooddelivery.app.theme.surfaceDimDarkHighContrast
import org.aifooddelivery.app.theme.surfaceDimDarkMediumContrast
import org.aifooddelivery.app.theme.surfaceDimLight
import org.aifooddelivery.app.theme.surfaceDimLightHighContrast
import org.aifooddelivery.app.theme.surfaceDimLightMediumContrast
import org.aifooddelivery.app.theme.surfaceLight
import org.aifooddelivery.app.theme.surfaceLightHighContrast
import org.aifooddelivery.app.theme.surfaceLightMediumContrast
import org.aifooddelivery.app.theme.surfaceVariantDark
import org.aifooddelivery.app.theme.surfaceVariantDarkHighContrast
import org.aifooddelivery.app.theme.surfaceVariantDarkMediumContrast
import org.aifooddelivery.app.theme.surfaceVariantLight
import org.aifooddelivery.app.theme.surfaceVariantLightHighContrast
import org.aifooddelivery.app.theme.surfaceVariantLightMediumContrast
import org.aifooddelivery.app.theme.tertiaryContainerDark
import org.aifooddelivery.app.theme.tertiaryContainerDarkHighContrast
import org.aifooddelivery.app.theme.tertiaryContainerDarkMediumContrast
import org.aifooddelivery.app.theme.tertiaryContainerLight
import org.aifooddelivery.app.theme.tertiaryContainerLightHighContrast
import org.aifooddelivery.app.theme.tertiaryContainerLightMediumContrast
import org.aifooddelivery.app.theme.tertiaryDark
import org.aifooddelivery.app.theme.tertiaryDarkHighContrast
import org.aifooddelivery.app.theme.tertiaryDarkMediumContrast
import org.aifooddelivery.app.theme.tertiaryLight
import org.aifooddelivery.app.theme.tertiaryLightHighContrast
import org.aifooddelivery.app.theme.tertiaryLightMediumContrast

 val lightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
)

 val darkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
)

 val mediumContrastLightColorScheme = lightColorScheme(
    primary = primaryLightMediumContrast,
    onPrimary = onPrimaryLightMediumContrast,
    primaryContainer = primaryContainerLightMediumContrast,
    onPrimaryContainer = onPrimaryContainerLightMediumContrast,
    secondary = secondaryLightMediumContrast,
    onSecondary = onSecondaryLightMediumContrast,
    secondaryContainer = secondaryContainerLightMediumContrast,
    onSecondaryContainer = onSecondaryContainerLightMediumContrast,
    tertiary = tertiaryLightMediumContrast,
    onTertiary = onTertiaryLightMediumContrast,
    tertiaryContainer = tertiaryContainerLightMediumContrast,
    onTertiaryContainer = onTertiaryContainerLightMediumContrast,
    error = errorLightMediumContrast,
    onError = onErrorLightMediumContrast,
    errorContainer = errorContainerLightMediumContrast,
    onErrorContainer = onErrorContainerLightMediumContrast,
    background = backgroundLightMediumContrast,
    onBackground = onBackgroundLightMediumContrast,
    surface = surfaceLightMediumContrast,
    onSurface = onSurfaceLightMediumContrast,
    surfaceVariant = surfaceVariantLightMediumContrast,
    onSurfaceVariant = onSurfaceVariantLightMediumContrast,
    outline = outlineLightMediumContrast,
    outlineVariant = outlineVariantLightMediumContrast,
    scrim = scrimLightMediumContrast,
    inverseSurface = inverseSurfaceLightMediumContrast,
    inverseOnSurface = inverseOnSurfaceLightMediumContrast,
    inversePrimary = inversePrimaryLightMediumContrast,
    surfaceDim = surfaceDimLightMediumContrast,
    surfaceBright = surfaceBrightLightMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestLightMediumContrast,
    surfaceContainerLow = surfaceContainerLowLightMediumContrast,
    surfaceContainer = surfaceContainerLightMediumContrast,
    surfaceContainerHigh = surfaceContainerHighLightMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestLightMediumContrast,
)

 val highContrastLightColorScheme = lightColorScheme(
    primary = primaryLightHighContrast,
    onPrimary = onPrimaryLightHighContrast,
    primaryContainer = primaryContainerLightHighContrast,
    onPrimaryContainer = onPrimaryContainerLightHighContrast,
    secondary = secondaryLightHighContrast,
    onSecondary = onSecondaryLightHighContrast,
    secondaryContainer = secondaryContainerLightHighContrast,
    onSecondaryContainer = onSecondaryContainerLightHighContrast,
    tertiary = tertiaryLightHighContrast,
    onTertiary = onTertiaryLightHighContrast,
    tertiaryContainer = tertiaryContainerLightHighContrast,
    onTertiaryContainer = onTertiaryContainerLightHighContrast,
    error = errorLightHighContrast,
    onError = onErrorLightHighContrast,
    errorContainer = errorContainerLightHighContrast,
    onErrorContainer = onErrorContainerLightHighContrast,
    background = backgroundLightHighContrast,
    onBackground = onBackgroundLightHighContrast,
    surface = surfaceLightHighContrast,
    onSurface = onSurfaceLightHighContrast,
    surfaceVariant = surfaceVariantLightHighContrast,
    onSurfaceVariant = onSurfaceVariantLightHighContrast,
    outline = outlineLightHighContrast,
    outlineVariant = outlineVariantLightHighContrast,
    scrim = scrimLightHighContrast,
    inverseSurface = inverseSurfaceLightHighContrast,
    inverseOnSurface = inverseOnSurfaceLightHighContrast,
    inversePrimary = inversePrimaryLightHighContrast,
    surfaceDim = surfaceDimLightHighContrast,
    surfaceBright = surfaceBrightLightHighContrast,
    surfaceContainerLowest = surfaceContainerLowestLightHighContrast,
    surfaceContainerLow = surfaceContainerLowLightHighContrast,
    surfaceContainer = surfaceContainerLightHighContrast,
    surfaceContainerHigh = surfaceContainerHighLightHighContrast,
    surfaceContainerHighest = surfaceContainerHighestLightHighContrast,
)

 val mediumContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkMediumContrast,
    onPrimary = onPrimaryDarkMediumContrast,
    primaryContainer = primaryContainerDarkMediumContrast,
    onPrimaryContainer = onPrimaryContainerDarkMediumContrast,
    secondary = secondaryDarkMediumContrast,
    onSecondary = onSecondaryDarkMediumContrast,
    secondaryContainer = secondaryContainerDarkMediumContrast,
    onSecondaryContainer = onSecondaryContainerDarkMediumContrast,
    tertiary = tertiaryDarkMediumContrast,
    onTertiary = onTertiaryDarkMediumContrast,
    tertiaryContainer = tertiaryContainerDarkMediumContrast,
    onTertiaryContainer = onTertiaryContainerDarkMediumContrast,
    error = errorDarkMediumContrast,
    onError = onErrorDarkMediumContrast,
    errorContainer = errorContainerDarkMediumContrast,
    onErrorContainer = onErrorContainerDarkMediumContrast,
    background = backgroundDarkMediumContrast,
    onBackground = onBackgroundDarkMediumContrast,
    surface = surfaceDarkMediumContrast,
    onSurface = onSurfaceDarkMediumContrast,
    surfaceVariant = surfaceVariantDarkMediumContrast,
    onSurfaceVariant = onSurfaceVariantDarkMediumContrast,
    outline = outlineDarkMediumContrast,
    outlineVariant = outlineVariantDarkMediumContrast,
    scrim = scrimDarkMediumContrast,
    inverseSurface = inverseSurfaceDarkMediumContrast,
    inverseOnSurface = inverseOnSurfaceDarkMediumContrast,
    inversePrimary = inversePrimaryDarkMediumContrast,
    surfaceDim = surfaceDimDarkMediumContrast,
    surfaceBright = surfaceBrightDarkMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkMediumContrast,
    surfaceContainerLow = surfaceContainerLowDarkMediumContrast,
    surfaceContainer = surfaceContainerDarkMediumContrast,
    surfaceContainerHigh = surfaceContainerHighDarkMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkMediumContrast,
)

 val highContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkHighContrast,
    onPrimary = onPrimaryDarkHighContrast,
    primaryContainer = primaryContainerDarkHighContrast,
    onPrimaryContainer = onPrimaryContainerDarkHighContrast,
    secondary = secondaryDarkHighContrast,
    onSecondary = onSecondaryDarkHighContrast,
    secondaryContainer = secondaryContainerDarkHighContrast,
    onSecondaryContainer = onSecondaryContainerDarkHighContrast,
    tertiary = tertiaryDarkHighContrast,
    onTertiary = onTertiaryDarkHighContrast,
    tertiaryContainer = tertiaryContainerDarkHighContrast,
    onTertiaryContainer = onTertiaryContainerDarkHighContrast,
    error = errorDarkHighContrast,
    onError = onErrorDarkHighContrast,
    errorContainer = errorContainerDarkHighContrast,
    onErrorContainer = onErrorContainerDarkHighContrast,
    background = backgroundDarkHighContrast,
    onBackground = onBackgroundDarkHighContrast,
    surface = surfaceDarkHighContrast,
    onSurface = onSurfaceDarkHighContrast,
    surfaceVariant = surfaceVariantDarkHighContrast,
    onSurfaceVariant = onSurfaceVariantDarkHighContrast,
    outline = outlineDarkHighContrast,
    outlineVariant = outlineVariantDarkHighContrast,
    scrim = scrimDarkHighContrast,
    inverseSurface = inverseSurfaceDarkHighContrast,
    inverseOnSurface = inverseOnSurfaceDarkHighContrast,
    inversePrimary = inversePrimaryDarkHighContrast,
    surfaceDim = surfaceDimDarkHighContrast,
    surfaceBright = surfaceBrightDarkHighContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkHighContrast,
    surfaceContainerLow = surfaceContainerLowDarkHighContrast,
    surfaceContainer = surfaceContainerDarkHighContrast,
    surfaceContainerHigh = surfaceContainerHighDarkHighContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkHighContrast,
)

@Immutable
data class ColorFamily(
    val color: Color,
    val onColor: Color,
    val colorContainer: Color,
    val onColorContainer: Color
)

val unspecified_scheme = ColorFamily(
    Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable() () -> Unit
) {
  val colorScheme = when {
      darkTheme -> darkScheme
      else -> lightScheme
  }
      MaterialTheme(
    colorScheme = colorScheme,
    content = content
  )
}

