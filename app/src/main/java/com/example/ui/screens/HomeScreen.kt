package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.model.ScientificDivision
import com.example.data.repository.ScienceRepository
import com.example.ui.components.MedicalDisclaimerBanner
import com.example.ui.responsive.ResponsiveConfig
import com.example.ui.theme.*
import com.example.viewmodel.AppDestination

@Composable
fun HomeScreen(
    responsiveConfig: ResponsiveConfig,
    onNavigateDestination: (AppDestination) -> Unit,
    onSelectDivision: (String) -> Unit,
    onSelectDisease: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = responsiveConfig.contentMaxWidth)
                .padding(horizontal = if (responsiveConfig.isDesktop) 28.dp else if (responsiveConfig.isTablet) 20.dp else 16.dp)
                .testTag("home_screen_content"),
            contentPadding = PaddingValues(top = 16.dp, bottom = 48.dp),
            verticalArrangement = Arrangement.spacedBy(if (responsiveConfig.isDesktop) 36.dp else 24.dp)
        ) {
            // 1. HERO SECTION (Desktop Side-by-Side vs Mobile Stacked)
            item {
                ResponsiveHeroSection(
                    responsiveConfig = responsiveConfig,
                    onExploreClicked = { onNavigateDestination(AppDestination.DIVISIONS) },
                    onToolsClicked = { onNavigateDestination(AppDestination.TOOLS) },
                    onAiClicked = { onNavigateDestination(AppDestination.AI_ASSISTANT) }
                )
            }

            // 2. MEDICAL & SCIENTIFIC ETHICS NOTICE
            item {
                MedicalDisclaimerBanner(compact = !responsiveConfig.isDesktop)
            }

            // 3. THE 12 SCIENTIFIC DIVISIONS
            item {
                ResponsiveDivisionsSection(
                    responsiveConfig = responsiveConfig,
                    onSelectDivision = onSelectDivision,
                    onViewAllClicked = { onNavigateDestination(AppDestination.DIVISIONS) }
                )
            }

            // 4. SCIENTIFIC SHOWCASES DASHBOARD (Multi-Column on Desktop)
            item {
                ResponsiveShowcasesSection(
                    responsiveConfig = responsiveConfig,
                    onSelectDivision = onSelectDivision,
                    onNavigateDestination = onNavigateDestination
                )
            }

            // 5. INTERACTIVE SCIENTIFIC TOOLS SHOWCASE
            item {
                ResponsiveToolsShowcase(
                    responsiveConfig = responsiveConfig,
                    onOpenTools = { onNavigateDestination(AppDestination.TOOLS) }
                )
            }

            // 6. FOUNDER VISION & PRINCIPLES
            item {
                ResponsiveFounderSection(
                    responsiveConfig = responsiveConfig,
                    onReadMore = { onNavigateDestination(AppDestination.ABOUT) }
                )
            }

            // 7. MULTI-COLUMN SCIENTIFIC FOOTER
            item {
                ResponsiveFooter(
                    responsiveConfig = responsiveConfig,
                    onNavigateDestination = onNavigateDestination
                )
            }
        }
    }
}

// -----------------------------------------------------------------
// 1. RESPONSIVE HERO SECTION
// -----------------------------------------------------------------
@Composable
private fun ResponsiveHeroSection(
    responsiveConfig: ResponsiveConfig,
    onExploreClicked: () -> Unit,
    onToolsClicked: () -> Unit,
    onAiClicked: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .border(1.dp, SpaceBorderGlow, RoundedCornerShape(24.dp)),
        colors = CardDefaults.cardColors(containerColor = SpaceSurfaceCard),
        shape = RoundedCornerShape(24.dp)
    ) {
        if (responsiveConfig.isDesktop) {
            // DESKTOP: Side-by-side two column hero
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                // Left Column: Text, Metrics, CTAs
                Column(
                    modifier = Modifier.weight(1.1f),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Surface(
                        color = CyanPrimary.copy(alpha = 0.15f),
                        shape = RoundedCornerShape(6.dp),
                        border = CardDefaults.outlinedCardBorder().copy(brush = Brush.horizontalGradient(listOf(CyanPrimary, ElectricBlue)))
                    ) {
                        Text(
                            text = "THE SCIENCE OF LIFE • RESEARCH & DISCOVERY",
                            color = CyanPrimary,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.2.sp,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                        )
                    }

                    Text(
                        text = "Explore the Science of Life.",
                        fontSize = 38.sp,
                        fontWeight = FontWeight.Black,
                        color = TextWhite,
                        lineHeight = 44.sp,
                        letterSpacing = (-0.5).sp
                    )

                    Text(
                        text = "Discover biology, human pathophysiology, molecular genetics, biotechnology and the future of biological computing. An international academic platform built for learners, university researchers, and science enthusiasts.",
                        fontSize = 15.sp,
                        color = TextSlate,
                        lineHeight = 23.sp
                    )

                    // Academic Metrics Row
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp),
                        horizontalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        HeroMetric(number = "12", label = "Divisions")
                        HeroMetric(number = "50+", label = "Modules")
                        HeroMetric(number = "10", label = "Lab Tools")
                        HeroMetric(number = "Gemini", label = "AI Research")
                        HeroMetric(number = "100%", label = "Peer-Reviewed")
                    }

                    // CTAs Row
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            onClick = onExploreClicked,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = CyanPrimary,
                                contentColor = DeepSpaceDark
                            ),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.height(48.dp)
                        ) {
                            Icon(Icons.Default.Explore, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Explore 12 Divisions", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                        }

                        OutlinedButton(
                            onClick = onToolsClicked,
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = CyanPrimary),
                            border = ButtonDefaults.outlinedButtonBorder.copy(
                                brush = Brush.horizontalGradient(listOf(CyanPrimary, ElectricBlue))
                            ),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.height(48.dp)
                        ) {
                            Icon(Icons.Default.Calculate, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Launch Lab Tools", fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                        }

                        FilledTonalButton(
                            onClick = onAiClicked,
                            colors = ButtonDefaults.filledTonalButtonColors(
                                containerColor = SpaceSurfaceVariant,
                                contentColor = BioMint
                            ),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.height(48.dp)
                        ) {
                            Icon(Icons.Default.AutoAwesome, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("AI Assistant", fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                        }
                    }
                }

                // Right Column: Big Cinematic Hero Illustration
                Box(
                    modifier = Modifier
                        .weight(0.9f)
                        .height(340.dp)
                        .clip(RoundedCornerShape(18.dp))
                        .border(1.dp, SpaceBorder, RoundedCornerShape(18.dp))
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.hero_bioscience_1793378155353),
                        contentDescription = "Futuristic Scientific Universe",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    // Floating live badge
                    Surface(
                        color = DeepSpaceDark.copy(alpha = 0.85f),
                        shape = RoundedCornerShape(8.dp),
                        border = CardDefaults.outlinedCardBorder().copy(brush = Brush.horizontalGradient(listOf(CyanPrimary, BioEmerald))),
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(14.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(BioEmerald))
                            Text("Simulated Multi-Omics & Cellular Laboratory", color = TextWhite, fontSize = 11.sp, fontWeight = FontWeight.Medium)
                        }
                    }
                }
            }
        } else {
            // MOBILE & TABLET: Stacked Hero Layout
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(if (responsiveConfig.isTablet) 340.dp else 290.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.hero_bioscience_1793378155353),
                    contentDescription = "Hero",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, DeepSpaceDark.copy(alpha = 0.6f), DeepSpaceDark.copy(alpha = 0.95f))
                            )
                        )
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Surface(
                        color = CyanPrimary.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text(
                            text = "THE SCIENCE OF LIFE",
                            color = CyanPrimary,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Explore the Science of Life",
                        fontSize = if (responsiveConfig.isTablet) 28.sp else 23.sp,
                        fontWeight = FontWeight.Black,
                        color = TextWhite
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Discover biology, medicine, biotechnology and the future of biological computing.",
                        fontSize = 13.sp,
                        color = TextSlate,
                        lineHeight = 18.sp
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        Button(
                            onClick = onExploreClicked,
                            colors = ButtonDefaults.buttonColors(containerColor = CyanPrimary, contentColor = DeepSpaceDark),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Icon(Icons.Default.Explore, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Explore Science", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                        }
                        OutlinedButton(
                            onClick = onToolsClicked,
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = CyanPrimary),
                            border = ButtonDefaults.outlinedButtonBorder.copy(
                                brush = Brush.horizontalGradient(listOf(CyanPrimary, ElectricBlue))
                            ),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Icon(Icons.Default.Calculate, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Lab Tools", fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun HeroMetric(number: String, label: String) {
    Column {
        Text(text = number, fontSize = 18.sp, fontWeight = FontWeight.Black, color = CyanPrimary)
        Text(text = label, fontSize = 11.sp, color = TextSlate, fontWeight = FontWeight.Medium)
    }
}

// -----------------------------------------------------------------
// 2. RESPONSIVE DIVISIONS SECTION (Desktop Multi-Row Grid vs Mobile)
// -----------------------------------------------------------------
@Composable
private fun ResponsiveDivisionsSection(
    responsiveConfig: ResponsiveConfig,
    onSelectDivision: (String) -> Unit,
    onViewAllClicked: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "The 12 Scientific Divisions",
                    fontSize = if (responsiveConfig.isDesktop) 24.sp else 19.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextWhite
                )
                Text(
                    text = "Comprehensive academic curriculum from cellular biology to biocomputing",
                    fontSize = 13.sp,
                    color = TextSlate
                )
            }
            TextButton(onClick = onViewAllClicked) {
                Text("View All 12 Divisions", color = CyanPrimary, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                Icon(Icons.Default.ChevronRight, contentDescription = null, tint = CyanPrimary, modifier = Modifier.size(16.dp))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (responsiveConfig.isDesktop) {
            // DESKTOP: 4 Cards Per Row Grid
            val chunkedDivisions = ScienceRepository.divisions.chunked(4)
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                chunkedDivisions.forEach { rowDivisions ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        rowDivisions.forEach { division ->
                            Box(modifier = Modifier.weight(1f)) {
                                ResponsiveDivisionCard(
                                    division = division,
                                    onClick = { onSelectDivision(division.id) }
                                )
                            }
                        }
                        // Fill empty cells if last row has fewer than 4
                        for (i in rowDivisions.size until 4) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        } else if (responsiveConfig.isTablet) {
            // TABLET: 2 Cards Per Row Grid
            val chunkedDivisions = ScienceRepository.divisions.chunked(2)
            Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                chunkedDivisions.forEach { rowDivisions ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        rowDivisions.forEach { division ->
                            Box(modifier = Modifier.weight(1f)) {
                                ResponsiveDivisionCard(
                                    division = division,
                                    onClick = { onSelectDivision(division.id) }
                                )
                            }
                        }
                        if (rowDivisions.size < 2) Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        } else {
            // MOBILE: Horizontal scroll row of cards
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                items(ScienceRepository.divisions) { division ->
                    Box(modifier = Modifier.width(220.dp)) {
                        ResponsiveDivisionCard(
                            division = division,
                            onClick = { onSelectDivision(division.id) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ResponsiveDivisionCard(
    division: ScientificDivision,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(210.dp)
            .clip(RoundedCornerShape(16.dp))
            .border(1.dp, SpaceBorder, RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
            .testTag("division_card_${division.id}"),
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
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                        )
                    }
                    Icon(
                        imageVector = Icons.Default.ArrowOutward,
                        contentDescription = null,
                        tint = Color(division.accentHex),
                        modifier = Modifier.size(16.dp)
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = division.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = TextWhite,
                    maxLines = 2
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = division.tagline,
                    fontSize = 11.sp,
                    color = TextSlate,
                    maxLines = 2,
                    lineHeight = 15.sp
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${division.topics.size} Academic Modules",
                    fontSize = 11.sp,
                    color = CyanPrimary,
                    fontWeight = FontWeight.Medium
                )
                Surface(
                    color = SpaceSurfaceVariant,
                    shape = CircleShape
                ) {
                    Icon(
                        imageVector = Icons.Default.Science,
                        contentDescription = null,
                        tint = Color(division.accentHex),
                        modifier = Modifier.padding(6.dp).size(14.dp)
                    )
                }
            }
        }
    }
}

// -----------------------------------------------------------------
// 3. RESPONSIVE SHOWCASES (Desktop 2-Column Dashboard)
// -----------------------------------------------------------------
@Composable
private fun ResponsiveShowcasesSection(
    responsiveConfig: ResponsiveConfig,
    onSelectDivision: (String) -> Unit,
    onNavigateDestination: (AppDestination) -> Unit
) {
    if (responsiveConfig.isDesktop) {
        Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
            // Row 1: Featured Discovery + Anatomy Showcase
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    FeaturedDiscoveryCard(onReadMore = { onNavigateDestination(AppDestination.RESEARCH_LIBRARY) })
                }
                Box(modifier = Modifier.weight(1f)) {
                    AnatomyShowcaseCard(onExplore = { onSelectDivision("anatomy") })
                }
            }

            // Row 2: Biocomputing Spotlight + AI Assistant Preview
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    BiocomputingSpotlightCard(onExplore = { onSelectDivision("biocomputing") })
                }
                Box(modifier = Modifier.weight(1f)) {
                    AiAssistantPreviewCard(onOpenAssistant = { onNavigateDestination(AppDestination.AI_ASSISTANT) })
                }
            }
        }
    } else {
        // TABLET & MOBILE: Stacked Cards
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            FeaturedDiscoveryCard(onReadMore = { onNavigateDestination(AppDestination.RESEARCH_LIBRARY) })
            AnatomyShowcaseCard(onExplore = { onSelectDivision("anatomy") })
            BiocomputingSpotlightCard(onExplore = { onSelectDivision("biocomputing") })
            AiAssistantPreviewCard(onOpenAssistant = { onNavigateDestination(AppDestination.AI_ASSISTANT) })
        }
    }
}

@Composable
private fun FeaturedDiscoveryCard(onReadMore: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .border(1.dp, BioViolet.copy(alpha = 0.4f), RoundedCornerShape(18.dp))
            .clickable(onClick = onReadMore),
        colors = CardDefaults.cardColors(containerColor = SpaceSurfaceCard),
        shape = RoundedCornerShape(18.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Surface(
                    color = BioViolet.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(
                        text = "NOBEL PRIZE DISCOVERY",
                        color = BioViolet,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
                Text("Nature 596 • 22k Citations", color = CyanPrimary, fontSize = 11.sp)
            }
            Text("AlphaFold & In Silico Biology", color = TextWhite, fontWeight = FontWeight.Bold, fontSize = 17.sp)
            Text(
                text = "Resolution of the 50-year-old protein folding problem using deep invariant point attention networks. Over 200 million 3D atomic structures are now openly accessible to researchers worldwide.",
                color = TextSlate,
                fontSize = 13.sp,
                lineHeight = 19.sp
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text("Access Full Research Paper ->", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = ElectricBlue)
            }
        }
    }
}

@Composable
private fun AnatomyShowcaseCard(onExplore: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .border(1.dp, CyanPrimary.copy(alpha = 0.35f), RoundedCornerShape(18.dp))
            .clickable(onClick = onExplore),
        colors = CardDefaults.cardColors(containerColor = SpaceSurfaceCard),
        shape = RoundedCornerShape(18.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Surface(
                    color = CyanPrimary.copy(alpha = 0.15f),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(
                        text = "DIVISION 01 SHOWCASE",
                        color = CyanPrimary,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
                Icon(Icons.Default.AccessibilityNew, contentDescription = null, tint = CyanPrimary)
            }
            Text("Human Anatomy & Physiology", color = TextWhite, fontWeight = FontWeight.Bold, fontSize = 17.sp)
            Text(
                text = "Interactive deep-dives into hemodynamics, cardiac action potentials, alveolar gas exchange, and endocrine regulatory pathways across 12 organ systems.",
                color = TextSlate,
                fontSize = 13.sp,
                lineHeight = 19.sp
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text("Explore Anatomical Modules ->", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = CyanPrimary)
            }
        }
    }
}

@Composable
private fun BiocomputingSpotlightCard(onExplore: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .border(1.dp, BioEmerald.copy(alpha = 0.4f), RoundedCornerShape(18.dp))
            .clickable(onClick = onExplore),
        colors = CardDefaults.cardColors(containerColor = SpaceSurfaceCard),
        shape = RoundedCornerShape(18.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Surface(
                    color = BioEmerald.copy(alpha = 0.15f),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(
                        text = "FRONTIER FEATURE • DIV 06",
                        color = BioEmerald,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
                Icon(Icons.Default.Memory, contentDescription = null, tint = BioEmerald)
            }
            Text("Biocomputing & Biological Computers", color = TextWhite, fontWeight = FontWeight.Bold, fontSize = 17.sp)
            Text(
                text = "Discover how nucleic acids store digital archives at 215 PB/g density, how enzyme-free strand displacement implements Boolean logic, and how synthetic circuits compute inside living cells.",
                color = TextSlate,
                fontSize = 13.sp,
                lineHeight = 19.sp
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text("Explore Biocomputing Division ->", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = BioMint)
            }
        }
    }
}

@Composable
private fun AiAssistantPreviewCard(onOpenAssistant: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .border(1.dp, CyanPrimary.copy(alpha = 0.5f), RoundedCornerShape(18.dp))
            .clickable(onClick = onOpenAssistant),
        colors = CardDefaults.cardColors(containerColor = SpaceSurfaceCard),
        shape = RoundedCornerShape(18.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Surface(
                    color = CyanPrimary.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(
                        text = "GEMINI AI RESEARCH LOGIC",
                        color = CyanPrimary,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
                Icon(Icons.Default.SmartToy, contentDescription = null, tint = CyanPrimary)
            }
            Text("AI BioScience Assistant", color = TextWhite, fontWeight = FontWeight.Bold, fontSize = 17.sp)
            Text(
                text = "Ask questions on CRISPR mechanisms, structural biology, gene expression, or pathophysiological pathways. Provides structured evidence-oriented scientific answers with literature citations.",
                color = TextSlate,
                fontSize = 13.sp,
                lineHeight = 19.sp
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text("Open Research Assistant ->", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = CyanPrimary)
            }
        }
    }
}

// -----------------------------------------------------------------
// 4. RESPONSIVE TOOLS SHOWCASE (Desktop 4-Column Grid)
// -----------------------------------------------------------------
@Composable
private fun ResponsiveToolsShowcase(
    responsiveConfig: ResponsiveConfig,
    onOpenTools: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .border(1.dp, SpaceBorder, RoundedCornerShape(20.dp)),
        colors = CardDefaults.cardColors(containerColor = SpaceSurfaceCard),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("Interactive Scientific Tools", color = TextWhite, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Text("Functional laboratory calculators, genetic simulators, and physical converters", color = TextSlate, fontSize = 13.sp)
                }
                Button(
                    onClick = onOpenTools,
                    colors = ButtonDefaults.buttonColors(containerColor = CyanPrimary, contentColor = DeepSpaceDark),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("Launch Lab", fontWeight = FontWeight.Bold)
                }
            }

            val tools = listOf(
                Triple(Icons.Default.Science, "DNA & Codons", "Watson-Crick base-pairing, reverse complement, GC content, and codon reading frames"),
                Triple(Icons.Default.GridOn, "Punnett Simulator", "Monohybrid genetic inheritance crosses with exact statistical phenotype ratios"),
                Triple(Icons.Default.Scale, "Units & Physical MW", "Converts base pairs to nanometers (0.34 nm/bp pitch) and Daltons to grams"),
                Triple(Icons.Default.Quiz, "Biology Quiz Lab", "Adaptive multi-category scientific knowledge evaluation with instant review")
            )

            if (responsiveConfig.isDesktop) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    tools.forEach { (icon, title, desc) ->
                        Surface(
                            color = SpaceSurfaceVariant,
                            shape = RoundedCornerShape(12.dp),
                            border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(SpaceBorder)),
                            modifier = Modifier
                                .weight(1f)
                                .clickable(onClick = onOpenTools)
                        ) {
                            Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                Icon(icon, contentDescription = null, tint = CyanPrimary, modifier = Modifier.size(24.dp))
                                Text(title, fontWeight = FontWeight.Bold, color = TextWhite, fontSize = 14.sp)
                                Text(desc, color = TextSlate, fontSize = 11.sp, lineHeight = 16.sp)
                            }
                        }
                    }
                }
            } else {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    tools.forEach { (icon, title, desc) ->
                        Surface(
                            color = SpaceSurfaceVariant,
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(onClick = onOpenTools)
                        ) {
                            Row(
                                modifier = Modifier.padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                Icon(icon, contentDescription = null, tint = CyanPrimary, modifier = Modifier.size(20.dp))
                                Column {
                                    Text(title, fontWeight = FontWeight.Bold, color = TextWhite, fontSize = 13.sp)
                                    Text(desc, color = TextSlate, fontSize = 11.sp)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

// -----------------------------------------------------------------
// 5. RESPONSIVE FOUNDER SECTION
// -----------------------------------------------------------------
@Composable
private fun ResponsiveFounderSection(
    responsiveConfig: ResponsiveConfig,
    onReadMore: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .border(1.dp, SpaceBorder, RoundedCornerShape(20.dp))
            .clickable(onClick = onReadMore),
        colors = CardDefaults.cardColors(containerColor = SpaceSurfaceCard),
        shape = RoundedCornerShape(20.dp)
    ) {
        if (responsiveConfig.isDesktop) {
            Row(
                modifier = Modifier.padding(24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("ABOUT THE FOUNDER", color = CyanPrimary, fontWeight = FontWeight.Bold, fontSize = 11.sp, letterSpacing = 1.sp)
                    Text("The Vision Behind BioScience Universe", color = TextWhite, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Text(
                        text = "Founded by a passionate technology and biology enthusiast to connect biological sciences with modern computing technologies. We believe scientific knowledge should be accessible, verified by reliable research, and powered by intelligent computing.",
                        color = TextSlate,
                        fontSize = 13.sp,
                        lineHeight = 20.sp
                    )
                }
                Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("OUR CORE MISSION", color = BioEmerald, fontWeight = FontWeight.Bold, fontSize = 11.sp, letterSpacing = 1.sp)
                    Text("Accelerating Global Scientific Literacy", color = TextWhite, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text(
                        text = "Building a comprehensive, responsive international portal spanning foundational biology, medical pathophysiology, and biocomputing research.",
                        color = TextSlate,
                        fontSize = 13.sp,
                        lineHeight = 19.sp
                    )
                    Text("Read Complete Founder Vision ->", color = ElectricBlue, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                }
            }
        } else {
            Column(modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("ABOUT THE FOUNDER", color = CyanPrimary, fontWeight = FontWeight.Bold, fontSize = 11.sp, letterSpacing = 1.sp)
                Text("The Vision Behind BioScience Universe", color = TextWhite, fontWeight = FontWeight.Bold, fontSize = 17.sp)
                Text(
                    text = "\"BioScience Universe was founded to bridge biological sciences with cutting-edge computing. We believe scientific knowledge should be accessible to everyone, supported by reliable research, and elevated through technology.\"",
                    color = TextSlate,
                    fontSize = 13.sp,
                    lineHeight = 18.sp
                )
                Text("Read Founder Story & Mission ->", color = ElectricBlue, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

// -----------------------------------------------------------------
// 6. MULTI-COLUMN SCIENTIFIC INSTITUTE FOOTER
// -----------------------------------------------------------------
@Composable
private fun ResponsiveFooter(
    responsiveConfig: ResponsiveConfig,
    onNavigateDestination: (AppDestination) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Divider(color = SpaceBorder, thickness = 0.5.dp)

        if (responsiveConfig.isDesktop) {
            // DESKTOP: 5 Columns Footer
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Col 1: Brand & Mission
                Column(modifier = Modifier.weight(1.3f), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("BIOSCIENCE", fontWeight = FontWeight.Black, fontSize = 16.sp, color = TextWhite)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("UNIVERSE", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = CyanPrimary)
                    }
                    Text(
                        text = "Explore the Science of Life.\nA futuristic international scientific education and biotechnology research platform.",
                        color = TextSlate,
                        fontSize = 12.sp,
                        lineHeight = 17.sp
                    )
                    Text("© 2026 BioScience Universe. Educational Edition.", color = TextDim, fontSize = 11.sp)
                }

                // Col 2: Core Divisions
                Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text("SCIENTIFIC DIVISIONS", color = CyanPrimary, fontWeight = FontWeight.Bold, fontSize = 11.sp)
                    FooterLink("Human Anatomy") { onNavigateDestination(AppDestination.DIVISIONS) }
                    FooterLink("Molecular Genetics") { onNavigateDestination(AppDestination.DIVISIONS) }
                    FooterLink("Biotechnology") { onNavigateDestination(AppDestination.DIVISIONS) }
                    FooterLink("Biocomputing") { onNavigateDestination(AppDestination.DIVISIONS) }
                }

                // Col 3: Medical & Advanced
                Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text("MEDICAL & ADVANCED", color = BioEmerald, fontWeight = FontWeight.Bold, fontSize = 11.sp)
                    FooterLink("Disease Encyclopedia") { onNavigateDestination(AppDestination.DISEASES) }
                    FooterLink("Immunotherapy") { onNavigateDestination(AppDestination.DIVISIONS) }
                    FooterLink("Pharmacology (ADME)") { onNavigateDestination(AppDestination.DIVISIONS) }
                    FooterLink("AI & Future Biology") { onNavigateDestination(AppDestination.DIVISIONS) }
                }

                // Col 4: Tools & AI
                Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text("INTERACTIVE LAB", color = ElectricBlue, fontWeight = FontWeight.Bold, fontSize = 11.sp)
                    FooterLink("DNA Analyzer") { onNavigateDestination(AppDestination.TOOLS) }
                    FooterLink("Punnett Simulator") { onNavigateDestination(AppDestination.TOOLS) }
                    FooterLink("Unit & MW Converter") { onNavigateDestination(AppDestination.TOOLS) }
                    FooterLink("Gemini AI Assistant") { onNavigateDestination(AppDestination.AI_ASSISTANT) }
                }

                // Col 5: Data Standards
                Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text("DATA ARCHIVES", color = BioAmber, fontWeight = FontWeight.Bold, fontSize = 11.sp)
                    FooterLink("NCBI Entrez") { onNavigateDestination(AppDestination.RESEARCH_LIBRARY) }
                    FooterLink("PubMed Central") { onNavigateDestination(AppDestination.RESEARCH_LIBRARY) }
                    FooterLink("RCSB Protein Data Bank") { onNavigateDestination(AppDestination.RESEARCH_LIBRARY) }
                    FooterLink("UniProt Consortium") { onNavigateDestination(AppDestination.RESEARCH_LIBRARY) }
                }
            }
        } else {
            // MOBILE & TABLET: Clean Compact Footer
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text("BIOSCIENCE UNIVERSE", fontWeight = FontWeight.Bold, color = TextWhite, fontSize = 14.sp)
                Text("Explore the Science of Life. Discover the Future of Biology.", color = TextSlate, fontSize = 12.sp)
                Text("Aligned with NCBI • PubMed • UniProt • Ensembl • RCSB PDB Standards", color = TextDim, fontSize = 10.sp)
                Text("Educational & Academic Research Edition", color = TextDim, fontSize = 10.sp)
            }
        }
    }
}

@Composable
private fun FooterLink(text: String, onClick: () -> Unit) {
    Text(
        text = text,
        color = TextSlate,
        fontSize = 12.sp,
        modifier = Modifier.clickable(onClick = onClick)
    )
}
