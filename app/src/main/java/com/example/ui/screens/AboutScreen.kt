package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.components.MedicalDisclaimerBanner
import com.example.ui.components.ScienceEthicsBanner
import com.example.ui.responsive.ResponsiveConfig
import com.example.ui.theme.*

@Composable
fun AboutScreen(
    responsiveConfig: ResponsiveConfig,
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
                .testTag("about_screen"),
            contentPadding = PaddingValues(top = 16.dp, bottom = 48.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Emblem Banner Card
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .border(1.dp, CyanPrimary.copy(alpha = 0.5f), RoundedCornerShape(20.dp)),
                    colors = CardDefaults.cardColors(containerColor = SpaceSurfaceCard),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(if (responsiveConfig.isDesktop) 84.dp else 64.dp)
                                .clip(CircleShape)
                                .border(2.dp, CyanPrimary, CircleShape)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.app_icon_bioscience_1793378142747),
                                contentDescription = "BioScience Universe Emblem",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        Text(
                            text = "BIOSCIENCE UNIVERSE",
                            fontWeight = FontWeight.Black,
                            fontSize = if (responsiveConfig.isDesktop) 26.sp else 20.sp,
                            color = TextWhite,
                            letterSpacing = 1.2.sp
                        )

                        Text(
                            text = "Explore the Science of Life. Discover the Future of Biology.",
                            fontSize = 13.sp,
                            color = CyanPrimary,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            // Vision & Mission (Side-by-side on Wide Screens, Stacked on Mobile)
            item {
                if (responsiveConfig.isWide) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Box(modifier = Modifier.weight(1f)) { VisionCard() }
                        Box(modifier = Modifier.weight(1f)) { MissionCard() }
                    }
                } else {
                    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        VisionCard()
                        MissionCard()
                    }
                }
            }

            // Global Vision & Beliefs (Side-by-side on Wide Screens, Stacked on Mobile)
            item {
                if (responsiveConfig.isWide) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Box(modifier = Modifier.weight(1f)) { GlobalVisionCard() }
                        Box(modifier = Modifier.weight(1f)) { BeliefsCard() }
                    }
                } else {
                    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        GlobalVisionCard()
                        BeliefsCard()
                    }
                }
            }

            item {
                ScienceEthicsBanner()
            }

            item {
                MedicalDisclaimerBanner()
            }
        }
    }
}

@Composable
private fun VisionCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .border(1.dp, SpaceBorder, RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = SpaceSurfaceCard),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text("ABOUT THE FOUNDER", color = CyanPrimary, fontWeight = FontWeight.Bold, fontSize = 11.sp, letterSpacing = 1.sp)
            Text("The Vision Behind BioScience Universe", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = TextWhite)
            Text(
                text = "BioScience Universe was founded by a young technology and science enthusiast with a passion for biology, medicine, biotechnology, artificial intelligence, and the mysteries of life.\n\nThe founder believes that science and technology can work together to make complex scientific knowledge easier to explore, understand, and access.\n\nWith a strong interest in software engineering, artificial intelligence, astronomy, and advanced scientific research, the founder aims to create a platform that connects biological sciences with modern computing technologies.",
                color = TextSlate,
                fontSize = 13.sp,
                lineHeight = 20.sp
            )
        }
    }
}

@Composable
private fun MissionCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .border(1.dp, SpaceBorder, RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = SpaceSurfaceCard),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text("OUR MISSION", color = BioEmerald, fontWeight = FontWeight.Bold, fontSize = 11.sp, letterSpacing = 1.sp)
            Text("Accelerating Global Scientific Literacy", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = TextWhite)
            Text(
                text = "Our mission is to build an accessible, comprehensive, and innovative scientific knowledge platform covering biology, human anatomy, medical science, genetics, biotechnology, microbiology, bioinformatics, biocomputing, and emerging technologies.\n\nWe provide verified educational explanations bridging foundational science to university and medical-level concepts.",
                color = TextSlate,
                fontSize = 13.sp,
                lineHeight = 20.sp
            )
        }
    }
}

@Composable
private fun GlobalVisionCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .border(1.dp, SpaceBorder, RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = SpaceSurfaceCard),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text("GLOBAL REACH", color = ElectricBlue, fontWeight = FontWeight.Bold, fontSize = 11.sp, letterSpacing = 1.sp)
            Text("Our Vision for Learners Everywhere", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = TextWhite)
            Text(
                text = "To develop BioScience Universe into a global educational and scientific exploration platform where learners, students, researchers, and science enthusiasts can discover the science of life seamlessly.\n\nConnecting data with official databases like NCBI, PubMed, UniProt, and the RCSB Protein Data Bank.",
                color = TextSlate,
                fontSize = 13.sp,
                lineHeight = 20.sp
            )
        }
    }
}

@Composable
private fun BeliefsCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .border(1.dp, SpaceBorder, RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = SpaceSurfaceCard),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text("CORE PRINCIPLES", color = BioAmber, fontWeight = FontWeight.Bold, fontSize = 11.sp, letterSpacing = 1.sp)
            Text("What We Believe", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = TextWhite)
            listOf(
                "Scientific knowledge should be accessible to everyone.",
                "Education should be supported by reliable, peer-reviewed research.",
                "Artificial intelligence can significantly improve scientific learning.",
                "Biology and computing have enormous potential together.",
                "Curiosity and continuous learning drive human scientific progress."
            ).forEach { belief ->
                Row(modifier = Modifier.padding(vertical = 2.dp), verticalAlignment = Alignment.Top) {
                    Text("• ", color = CyanPrimary, fontWeight = FontWeight.Bold)
                    Text(text = belief, color = TextWhite, fontSize = 13.sp, lineHeight = 19.sp)
                }
            }
        }
    }
}
