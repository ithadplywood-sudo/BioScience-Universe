package com.example

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.data.repository.ScienceRepository
import com.example.ui.responsive.ScreenType
import com.example.ui.responsive.calculateResponsiveConfig
import com.example.ui.tools.BioCalculators
import com.example.viewmodel.AppDestination
import com.example.viewmodel.BioScienceViewModel
import org.junit.Assert.*
import org.junit.Test

class ExampleUnitTest {

    @Test
    fun testAllRequestedScreenSizes() {
        val testSizes = listOf(
            Pair(320.dp, ScreenType.MOBILE),
            Pair(375.dp, ScreenType.MOBILE),
            Pair(390.dp, ScreenType.MOBILE),
            Pair(430.dp, ScreenType.MOBILE),
            Pair(768.dp, ScreenType.TABLET),
            Pair(1024.dp, ScreenType.LAPTOP),
            Pair(1280.dp, ScreenType.LAPTOP),
            Pair(1366.dp, ScreenType.LAPTOP),
            Pair(1440.dp, ScreenType.DESKTOP),
            Pair(1920.dp, ScreenType.DESKTOP)
        )

        testSizes.forEach { (width, expectedType) ->
            val config = calculateResponsiveConfig(width)
            assertEquals("Failed for width: $width", expectedType, config.screenType)
            when (expectedType) {
                ScreenType.MOBILE -> {
                    assertTrue(config.isMobile)
                    assertFalse(config.isDesktop)
                }
                ScreenType.TABLET -> {
                    assertTrue(config.isTablet)
                    assertTrue(config.isWide)
                }
                ScreenType.LAPTOP -> {
                    assertTrue(config.isLaptop)
                    assertTrue(config.isDesktop)
                    assertTrue(config.isWide)
                }
                ScreenType.DESKTOP -> {
                    assertTrue(config.isDesktop)
                    assertTrue(config.isWide)
                }
            }
        }
    }

    @Test
    fun testDivisionsAndDiseasesCount() {
        assertEquals(12, ScienceRepository.divisions.size)
        assertTrue(ScienceRepository.diseases.size >= 12)
        assertTrue(ScienceRepository.researchPapers.isNotEmpty())
        assertTrue(ScienceRepository.databases.isNotEmpty())
        assertTrue(ScienceRepository.glossary.isNotEmpty())
        assertTrue(ScienceRepository.bioElements.isNotEmpty())
    }

    @Test
    fun testDnaComplementAndCodonAnalysis() {
        val seq = "ATGCGATCG"
        val result = BioCalculators.analyzeDnaSequence(seq)

        assertEquals("ATGCGATCG", result.original)
        assertEquals("TACGCTAGC", result.complement)
        assertEquals("CGATCGCAT", result.reverseComplement)
        assertEquals("AUGCGAUCG", result.mrna)
        assertEquals(9, result.length)
        assertTrue(result.proteinTranslation.isNotEmpty())
        assertEquals("AUG -> Met (Start)", result.proteinTranslation[0])
    }

    @Test
    fun testPunnettSimulator() {
        val result = BioCalculators.calculateMonohybridCross("Aa", "Aa")
        assertEquals(2, result.grid.size)
        assertEquals(2, result.grid[0].size)
        assertEquals(75.0, result.phenotypePercentages["Dominant Phenotype"] ?: 0.0, 0.01)
        assertEquals(25.0, result.phenotypePercentages["Recessive Phenotype"] ?: 0.0, 0.01)
    }

    @Test
    fun testViewModelSearchAndNavigation() {
        val viewModel = BioScienceViewModel()
        viewModel.onSearchQueryChanged("CRISPR")
        val state = viewModel.uiState.value

        assertTrue(state.isSearchActive)
        assertTrue(state.searchResults.isNotEmpty())

        viewModel.navigateTo(AppDestination.DIVISIONS)
        assertEquals(AppDestination.DIVISIONS, viewModel.uiState.value.currentDestination)
    }
}
