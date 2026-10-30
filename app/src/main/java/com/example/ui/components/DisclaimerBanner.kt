package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.BioAmber
import com.example.ui.theme.CyanAccent
import com.example.ui.theme.SpaceSurfaceCard
import com.example.ui.theme.TextSlate
import com.example.ui.theme.TextWhite

@Composable
fun MedicalDisclaimerBanner(
    modifier: Modifier = Modifier,
    compact: Boolean = false
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(SpaceSurfaceCard.copy(alpha = 0.85f))
            .border(1.dp, BioAmber.copy(alpha = 0.45f), RoundedCornerShape(12.dp))
            .padding(if (compact) 10.dp else 14.dp)
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = "Medical Education Notice",
                tint = BioAmber,
                modifier = Modifier.size(20.dp)
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "EDUCATIONAL & SCIENTIFIC RESEARCH PLATFORM",
                    color = BioAmber,
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp,
                    letterSpacing = 0.8.sp
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "BioScience Universe is an academic scientific platform. Content is for educational, university learning, and reference purposes only. Never use this platform for personal medical diagnosis or self-treatment.",
                    color = TextSlate,
                    fontSize = 12.sp,
                    lineHeight = 16.sp
                )
            }
        }
    }
}

@Composable
fun ScienceEthicsBanner(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(SpaceSurfaceCard.copy(alpha = 0.85f))
            .border(1.dp, CyanAccent.copy(alpha = 0.4f), RoundedCornerShape(12.dp))
            .padding(12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "Scientific Ethics",
                tint = CyanAccent,
                modifier = Modifier.size(20.dp)
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "EVIDENCE-BASED SCIENTIFIC METHODOLOGY",
                    color = CyanAccent,
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp,
                    letterSpacing = 0.8.sp
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "All modules clearly demarcate established peer-reviewed knowledge from experimental and emerging hypotheses.",
                    color = TextWhite,
                    fontSize = 12.sp,
                    lineHeight = 16.sp
                )
            }
        }
    }
}
