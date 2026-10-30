package com.example.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import com.example.data.model.ScientificDivision
import com.example.data.repository.ScienceRepository
import com.example.ui.components.ScienceEthicsBanner
import com.example.ui.responsive.ResponsiveConfig
import com.example.ui.theme.*

@Composable
fun DivisionsScreen(
    responsiveConfig: ResponsiveConfig,
    onSelectDivision: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = responsiveConfig.contentMaxWidth)
                .padding(horizontal = if (responsiveConfig.isDesktop) 28.dp else if (responsiveConfig.isTablet) 20.dp else 16.dp)
                .testTag("divisions_screen")
        ) {
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = "The 12 Scientific Divisions",
                fontSize = if (responsiveConfig.isDesktop) 28.sp else 22.sp,
                fontWeight = FontWeight.Bold,
                color = TextWhite
            )
            Text(
                text = "Structured academic curricula spanning from foundational biology and human physiology to biocomputing and artificial intelligence.",
                fontSize = 13.sp,
                color = TextSlate,
                lineHeight = 19.sp
            )
            Spacer(modifier = Modifier.height(12.dp))
            ScienceEthicsBanner()
            Spacer(modifier = Modifier.height(16.dp))

            val columnsCount = when {
                responsiveConfig.isDesktop -> 4
                responsiveConfig.isTablet -> 2
                else -> 1
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(columnsCount),
                horizontalArrangement = Arrangement.spacedBy(14.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp),
                contentPadding = PaddingValues(bottom = 48.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(ScienceRepository.divisions) { division ->
                    DivisionGridCard(
                        division = division,
                        onClick = { onSelectDivision(division.id) }
                    )
                }
            }
        }
    }
}

@Composable
private fun DivisionGridCard(
    division: ScientificDivision,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(230.dp)
            .clip(RoundedCornerShape(16.dp))
            .border(1.dp, SpaceBorder, RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
            .testTag("grid_division_${division.id}"),
        colors = CardDefaults.cardColors(containerColor = SpaceSurfaceCard),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        color = Color(division.accentHex).copy(alpha = 0.15f),
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text(
                            text = "DIV ${division.number.toString().padStart(2, '0')}",
                            color = Color(division.accentHex),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Black,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                    Surface(
                        color = SpaceSurfaceVariant,
                        shape = CircleShape
                    ) {
                        Icon(
                            imageVector = when (division.id) {
                                "anatomy" -> Icons.Default.AccessibilityNew
                                "diseases" -> Icons.Default.MedicalServices
                                "genetics" -> Icons.Default.Science
                                "biotech" -> Icons.Default.Biotech
                                "bioinformatics" -> Icons.Default.Storage
                                "biocomputing" -> Icons.Default.Memory
                                "microbiology" -> Icons.Default.Coronavirus
                                "medtech" -> Icons.Default.PrecisionManufacturing
                                "immunology" -> Icons.Default.Shield
                                "pharmacology" -> Icons.Default.Medication
                                "ecology" -> Icons.Default.Forest
                                else -> Icons.Default.SmartToy
                            },
                            contentDescription = null,
                            tint = Color(division.accentHex),
                            modifier = Modifier.padding(6.dp).size(16.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = division.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = TextWhite,
                    maxLines = 2,
                    lineHeight = 19.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = division.tagline,
                    fontSize = 11.sp,
                    color = TextSlate,
                    maxLines = 3,
                    lineHeight = 15.sp
                )
            }

            Column {
                Divider(color = SpaceBorder, thickness = 0.5.dp)
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${division.topics.size} Academic Modules",
                        fontSize = 11.sp,
                        color = CyanPrimary,
                        fontWeight = FontWeight.SemiBold
                    )
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = null,
                        tint = Color(division.accentHex),
                        modifier = Modifier.size(14.dp)
                    )
                }
            }
        }
    }
}
