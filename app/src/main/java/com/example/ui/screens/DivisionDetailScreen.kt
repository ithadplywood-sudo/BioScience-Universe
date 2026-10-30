package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.DivisionTopic
import com.example.data.model.ScientificDivision
import com.example.data.repository.ScienceRepository
import com.example.ui.components.MedicalDisclaimerBanner
import com.example.ui.responsive.ResponsiveConfig
import com.example.ui.theme.*

@Composable
fun DivisionDetailScreen(
    responsiveConfig: ResponsiveConfig,
    divisionId: String?,
    selectedTopicId: String?,
    onSelectTopic: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val division = ScienceRepository.divisions.find { it.id == divisionId }
        ?: ScienceRepository.divisions.first()

    val currentTopic = division.topics.find { it.id == selectedTopicId }
        ?: division.topics.firstOrNull()

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        if (responsiveConfig.isWide) {
            // DESKTOP & TABLET: Canonical 2-Column List-Detail Layout
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .widthIn(max = responsiveConfig.contentMaxWidth)
                    .padding(horizontal = if (responsiveConfig.isDesktop) 28.dp else 20.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                // Left Column: Division Overview & Academic Modules Sidebar
                Column(
                    modifier = Modifier
                        .width(if (responsiveConfig.isDesktop) 340.dp else 280.dp)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    DivisionSummaryCard(division = division)

                    Text(
                        text = "ACADEMIC MODULES (${division.topics.size})",
                        color = CyanPrimary,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )

                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        items(division.topics) { topic ->
                            val isSelected = currentTopic?.id == topic.id
                            Surface(
                                color = if (isSelected) SpaceSurfaceVariant else SpaceSurfaceCard,
                                shape = RoundedCornerShape(10.dp),
                                border = CardDefaults.outlinedCardBorder().copy(
                                    brush = androidx.compose.ui.graphics.SolidColor(
                                        if (isSelected) Color(division.accentHex) else SpaceBorder
                                    )
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { onSelectTopic(topic.id) }
                            ) {
                                Row(
                                    modifier = Modifier.padding(12.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = topic.title,
                                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                            fontSize = 13.sp,
                                            color = if (isSelected) TextWhite else TextSlate
                                        )
                                        Spacer(modifier = Modifier.height(2.dp))
                                        Text(
                                            text = topic.subtitle,
                                            fontSize = 11.sp,
                                            color = if (isSelected) ElectricBlue else TextDim,
                                            maxLines = 1
                                        )
                                    }
                                    if (isSelected) {
                                        Icon(
                                            imageVector = Icons.Default.CheckCircle,
                                            contentDescription = null,
                                            tint = Color(division.accentHex),
                                            modifier = Modifier.size(16.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                // Right Column: Topic Scientific Article
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(bottom = 32.dp)
                ) {
                    if (currentTopic != null) {
                        item {
                            TopicDetailCard(
                                topic = currentTopic,
                                accentHex = division.accentHex
                            )
                        }
                    }
                    if (division.id in listOf("anatomy", "diseases", "pharmacology", "immunology")) {
                        item {
                            MedicalDisclaimerBanner()
                        }
                    }
                }
            }
        } else {
            // MOBILE: Single Column Stacked Layout
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .testTag("division_detail_screen"),
                contentPadding = PaddingValues(bottom = 32.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    DivisionSummaryCard(division = division)
                }

                if (division.topics.size > 1) {
                    item {
                        Text(
                            text = "ACADEMIC MODULES",
                            color = CyanPrimary,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(rememberScrollState()),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            division.topics.forEach { topic ->
                                val isSelected = currentTopic?.id == topic.id
                                FilterChip(
                                    selected = isSelected,
                                    onClick = { onSelectTopic(topic.id) },
                                    label = { Text(topic.title, fontSize = 12.sp) },
                                    colors = FilterChipDefaults.filterChipColors(
                                        selectedContainerColor = Color(division.accentHex),
                                        selectedLabelColor = DeepSpaceDark,
                                        containerColor = SpaceSurfaceCard,
                                        labelColor = TextWhite
                                    )
                                )
                            }
                        }
                    }
                }

                if (currentTopic != null) {
                    item {
                        TopicDetailCard(
                            topic = currentTopic,
                            accentHex = division.accentHex
                        )
                    }
                }

                if (division.id in listOf("anatomy", "diseases", "pharmacology", "immunology")) {
                    item {
                        MedicalDisclaimerBanner()
                    }
                }
            }
        }
    }
}

@Composable
private fun DivisionSummaryCard(division: ScientificDivision) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .border(1.dp, Color(division.accentHex).copy(alpha = 0.5f), RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = SpaceSurfaceCard),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Surface(
                    color = Color(division.accentHex).copy(alpha = 0.2f),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(
                        text = "DIVISION ${division.number.toString().padStart(2, '0')}",
                        color = Color(division.accentHex),
                        fontWeight = FontWeight.Black,
                        fontSize = 11.sp,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = division.title,
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold,
                color = TextWhite
            )

            Text(
                text = division.tagline,
                fontSize = 12.sp,
                color = ElectricBlue,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = division.overview,
                fontSize = 12.sp,
                color = TextSlate,
                lineHeight = 17.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                division.keyConcepts.forEach { concept ->
                    Surface(
                        color = SpaceSurfaceVariant,
                        shape = RoundedCornerShape(6.dp),
                        border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(SpaceBorder))
                    ) {
                        Text(
                            text = concept,
                            color = TextWhite,
                            fontSize = 10.sp,
                            modifier = Modifier.padding(horizontal = 7.dp, vertical = 3.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun TopicDetailCard(
    topic: DivisionTopic,
    accentHex: Long
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .border(1.dp, SpaceBorder, RoundedCornerShape(18.dp)),
        colors = CardDefaults.cardColors(containerColor = SpaceSurfaceCard),
        shape = RoundedCornerShape(18.dp)
    ) {
        Column(modifier = Modifier.padding(22.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            // Header
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Surface(
                        color = Color(accentHex).copy(alpha = 0.15f),
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text(
                            text = topic.researchStatus.uppercase(),
                            color = Color(accentHex),
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = topic.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = TextWhite
                )
                Text(
                    text = topic.subtitle,
                    fontSize = 13.sp,
                    color = ElectricBlue
                )
            }

            Divider(color = SpaceBorder, thickness = 0.5.dp)

            ScientificSectionItem(
                label = "ACADEMIC DEFINITION",
                content = topic.definition,
                labelColor = CyanPrimary
            )

            ScientificSectionItem(
                label = "STRUCTURE & BIO-ORGANIZATION",
                content = topic.structure,
                labelColor = BioEmerald
            )

            Column {
                Text(
                    text = "PHYSIOLOGICAL & MOLECULAR FUNCTIONS",
                    color = BioAmber,
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp,
                    letterSpacing = 0.8.sp
                )
                Spacer(modifier = Modifier.height(6.dp))
                topic.functions.forEach { func ->
                    Row(
                        modifier = Modifier.padding(vertical = 2.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Text("• ", color = CyanPrimary, fontWeight = FontWeight.Bold)
                        Text(text = func, color = TextWhite, fontSize = 13.sp, lineHeight = 19.sp)
                    }
                }
            }

            ScientificSectionItem(
                label = "BIOLOGICAL MECHANISMS",
                content = topic.mechanisms,
                labelColor = ElectricBlue
            )

            if (topic.relatedConditions.isNotEmpty()) {
                Column {
                    Text(
                        text = "RELATED CONDITIONS & PATHOLOGIES",
                        color = BioRose,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                        letterSpacing = 0.8.sp
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        topic.relatedConditions.forEach { cond ->
                            Surface(
                                color = BioRose.copy(alpha = 0.12f),
                                shape = RoundedCornerShape(6.dp)
                            ) {
                                Text(
                                    text = cond,
                                    color = TextWhite,
                                    fontSize = 11.sp,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                )
                            }
                        }
                    }
                }
            }

            if (topic.references.isNotEmpty()) {
                Divider(color = SpaceBorder, thickness = 0.5.dp)
                Column {
                    Text(
                        text = "PEER-REVIEWED REFERENCES",
                        color = TextDim,
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp,
                        letterSpacing = 0.8.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    topic.references.forEach { ref ->
                        Text(
                            text = "• $ref",
                            color = TextSlate,
                            fontSize = 11.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ScientificSectionItem(
    label: String,
    content: String,
    labelColor: Color
) {
    Column {
        Text(
            text = label,
            color = labelColor,
            fontWeight = FontWeight.Bold,
            fontSize = 11.sp,
            letterSpacing = 0.8.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = content,
            color = TextWhite,
            fontSize = 13.sp,
            lineHeight = 19.sp
        )
    }
}
