package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.DiseaseCategory
import com.example.data.model.DiseaseEntry
import com.example.data.repository.ScienceRepository
import com.example.ui.components.MedicalDisclaimerBanner
import com.example.ui.responsive.ResponsiveConfig
import com.example.ui.theme.*

@Composable
fun DiseasesScreen(
    responsiveConfig: ResponsiveConfig,
    selectedCategory: DiseaseCategory?,
    selectedDiseaseId: String?,
    onSelectCategory: (DiseaseCategory?) -> Unit,
    onSelectDisease: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val filteredDiseases = if (selectedCategory == null) {
        ScienceRepository.diseases
    } else {
        ScienceRepository.diseases.filter { it.category == selectedCategory }
    }

    val activeDisease = ScienceRepository.diseases.find { it.id == selectedDiseaseId }
        ?: filteredDiseases.firstOrNull()
        ?: ScienceRepository.diseases.first()

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        if (responsiveConfig.isWide) {
            // DESKTOP & TABLET: Canonical 2-Column List-Detail Layout
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .widthIn(max = responsiveConfig.contentMaxWidth)
                    .padding(horizontal = if (responsiveConfig.isDesktop) 28.dp else 20.dp, vertical = 14.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = "Disease & Medical Science Encyclopedia",
                            fontSize = if (responsiveConfig.isDesktop) 26.sp else 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextWhite
                        )
                        Text(
                            text = "Comprehensive academic pathophysiological catalog covering 12 distinct medical specialties.",
                            fontSize = 13.sp,
                            color = TextSlate
                        )
                    }
                    MedicalDisclaimerBanner(compact = true, modifier = Modifier.width(360.dp))
                }

                // Category Filter Chips
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    FilterChip(
                        selected = selectedCategory == null,
                        onClick = { onSelectCategory(null) },
                        label = { Text("All 12 Specialties (${ScienceRepository.diseases.size})", fontSize = 12.sp) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = CyanPrimary,
                            selectedLabelColor = DeepSpaceDark,
                            containerColor = SpaceSurfaceCard,
                            labelColor = TextWhite
                        )
                    )

                    DiseaseCategory.values().forEach { cat ->
                        val isSelected = selectedCategory == cat
                        FilterChip(
                            selected = isSelected,
                            onClick = { onSelectCategory(cat) },
                            label = { Text(cat.displayName, fontSize = 12.sp) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = BioRose,
                                selectedLabelColor = DeepSpaceDark,
                                containerColor = SpaceSurfaceCard,
                                labelColor = TextWhite
                            )
                        )
                    }
                }

                // 2-Column Main Workspace
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    // Left Column: Disease Entries List
                    LazyColumn(
                        modifier = Modifier
                            .width(if (responsiveConfig.isDesktop) 380.dp else 300.dp)
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(filteredDiseases) { disease ->
                            val isSelected = disease.id == activeDisease.id
                            Surface(
                                color = if (isSelected) SpaceSurfaceVariant else SpaceSurfaceCard,
                                shape = RoundedCornerShape(12.dp),
                                border = CardDefaults.outlinedCardBorder().copy(
                                    brush = androidx.compose.ui.graphics.SolidColor(
                                        if (isSelected) BioRose else SpaceBorder
                                    )
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { onSelectDisease(disease.id) }
                            ) {
                                Column(modifier = Modifier.padding(14.dp)) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Surface(
                                            color = BioRose.copy(alpha = 0.15f),
                                            shape = RoundedCornerShape(4.dp)
                                        ) {
                                            Text(
                                                text = disease.category.displayName.uppercase(),
                                                color = BioRose,
                                                fontSize = 9.sp,
                                                fontWeight = FontWeight.Bold,
                                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                            )
                                        }
                                        if (isSelected) {
                                            Icon(Icons.Default.CheckCircle, contentDescription = null, tint = BioRose, modifier = Modifier.size(16.dp))
                                        }
                                    }
                                    Spacer(modifier = Modifier.height(6.dp))
                                    Text(
                                        text = disease.name,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp,
                                        color = TextWhite
                                    )
                                    Text(
                                        text = disease.scientificName,
                                        fontSize = 11.sp,
                                        color = ElectricBlue
                                    )
                                }
                            }
                        }
                    }

                    // Right Column: Full Disease Article
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                    ) {
                        DiseaseDetailScreen(
                            responsiveConfig = responsiveConfig,
                            diseaseId = activeDisease.id
                        )
                    }
                }
            }
        } else {
            // MOBILE: Single Column List
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .testTag("diseases_screen"),
                contentPadding = PaddingValues(bottom = 32.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                item {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Disease & Medical Encyclopedia",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextWhite
                    )
                    Text(
                        text = "Academic pathophysiological encyclopedia covering 12 distinct medical domains.",
                        fontSize = 13.sp,
                        color = TextSlate,
                        lineHeight = 18.sp
                    )
                }

                item {
                    MedicalDisclaimerBanner()
                }

                item {
                    Text(
                        text = "FILTER BY 12 CATEGORIES",
                        color = CyanPrimary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        FilterChip(
                            selected = selectedCategory == null,
                            onClick = { onSelectCategory(null) },
                            label = { Text("All (${ScienceRepository.diseases.size})", fontSize = 12.sp) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = CyanPrimary,
                                selectedLabelColor = DeepSpaceDark,
                                containerColor = SpaceSurfaceCard,
                                labelColor = TextWhite
                            )
                        )

                        DiseaseCategory.values().forEach { cat ->
                            val isSelected = selectedCategory == cat
                            FilterChip(
                                selected = isSelected,
                                onClick = { onSelectCategory(cat) },
                                label = { Text(cat.displayName, fontSize = 12.sp) },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = BioRose,
                                    selectedLabelColor = DeepSpaceDark,
                                    containerColor = SpaceSurfaceCard,
                                    labelColor = TextWhite
                                )
                            )
                        }
                    }
                }

                items(filteredDiseases) { disease ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .border(1.dp, SpaceBorder, RoundedCornerShape(16.dp))
                            .clickable { onSelectDisease(disease.id) }
                            .testTag("disease_card_${disease.id}"),
                        colors = CardDefaults.cardColors(containerColor = SpaceSurfaceCard),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Surface(
                                    color = BioRose.copy(alpha = 0.15f),
                                    shape = RoundedCornerShape(6.dp)
                                ) {
                                    Text(
                                        text = disease.category.displayName.uppercase(),
                                        color = BioRose,
                                        fontSize = 9.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                    )
                                }
                                Text("Reviewed ${disease.reviewDate}", color = TextDim, fontSize = 10.sp)
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(disease.name, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = TextWhite)
                            Text(disease.scientificName, fontSize = 12.sp, color = ElectricBlue)
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(disease.scientificOverview, fontSize = 13.sp, color = TextSlate, maxLines = 2)
                        }
                    }
                }
            }
        }
    }
}
