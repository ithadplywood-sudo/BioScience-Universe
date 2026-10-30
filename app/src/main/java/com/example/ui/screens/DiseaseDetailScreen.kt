package com.example.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.DiseaseEntry
import com.example.data.repository.ScienceRepository
import com.example.ui.components.MedicalDisclaimerBanner
import com.example.ui.responsive.ResponsiveConfig
import com.example.ui.theme.*

@Composable
fun DiseaseDetailScreen(
    responsiveConfig: ResponsiveConfig,
    diseaseId: String?,
    modifier: Modifier = Modifier
) {
    val disease = ScienceRepository.diseases.find { it.id == diseaseId }
        ?: ScienceRepository.diseases.first()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("disease_detail_screen"),
        contentPadding = PaddingValues(bottom = 32.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            MedicalDisclaimerBanner()
        }

        // Disease Title Card
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .border(1.dp, BioRose.copy(alpha = 0.5f), RoundedCornerShape(16.dp)),
                colors = CardDefaults.cardColors(containerColor = SpaceSurfaceCard),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Surface(
                        color = BioRose.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text(
                            text = disease.category.displayName.uppercase(),
                            color = BioRose,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = disease.name,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextWhite
                    )
                    Text(
                        text = disease.scientificName,
                        fontSize = 14.sp,
                        color = ElectricBlue,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = disease.scientificOverview,
                        fontSize = 13.sp,
                        color = TextSlate,
                        lineHeight = 19.sp
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Published: ${disease.publicationDate}", fontSize = 11.sp, color = TextDim)
                        Text("Reviewed: ${disease.reviewDate}", fontSize = 11.sp, color = CyanPrimary)
                    }
                }
            }
        }

        // Biological Mechanisms
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .border(1.dp, SpaceBorder, RoundedCornerShape(16.dp)),
                colors = CardDefaults.cardColors(containerColor = SpaceSurfaceCard),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "BIOLOGICAL & PATHOPHYSIOLOGICAL MECHANISMS",
                        color = CyanPrimary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                        letterSpacing = 0.8.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = disease.biologicalMechanisms,
                        color = TextWhite,
                        fontSize = 13.sp,
                        lineHeight = 20.sp
                    )
                }
            }
        }

        // Causes & Symptoms (Side-by-side on Wide Screens, Stacked on Mobile)
        item {
            if (responsiveConfig.isWide) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Box(modifier = Modifier.weight(1f)) { CausesCard(disease = disease) }
                    Box(modifier = Modifier.weight(1f)) { SymptomsCard(disease = disease) }
                }
            } else {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    CausesCard(disease = disease)
                    SymptomsCard(disease = disease)
                }
            }
        }

        // Diagnostics & Treatments (Side-by-side on Wide Screens, Stacked on Mobile)
        item {
            if (responsiveConfig.isWide) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Box(modifier = Modifier.weight(1f)) { DiagnosticsCard(disease = disease) }
                    Box(modifier = Modifier.weight(1f)) { TreatmentsCard(disease = disease) }
                }
            } else {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    DiagnosticsCard(disease = disease)
                    TreatmentsCard(disease = disease)
                }
            }
        }

        // Prevention Strategies
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .border(1.dp, SpaceBorder, RoundedCornerShape(16.dp)),
                colors = CardDefaults.cardColors(containerColor = SpaceSurfaceCard),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        text = "PREVENTION & RISK MITIGATION STRATEGIES",
                        color = ElectricBlue,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                        letterSpacing = 0.8.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    disease.preventionStrategies.forEach { prev ->
                        Row(modifier = Modifier.padding(vertical = 2.dp), verticalAlignment = Alignment.Top) {
                            Text("• ", color = ElectricBlue, fontWeight = FontWeight.Bold)
                            Text(text = prev, color = TextWhite, fontSize = 13.sp, lineHeight = 18.sp)
                        }
                    }
                }
            }
        }

        // References
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .border(1.dp, SpaceBorder, RoundedCornerShape(16.dp)),
                colors = CardDefaults.cardColors(containerColor = SpaceSurfaceCard),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        text = "PEER-REVIEWED SCIENTIFIC REFERENCES",
                        color = TextDim,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                        letterSpacing = 0.8.sp
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    disease.researchReferences.forEach { ref ->
                        Text(text = "• $ref", color = TextSlate, fontSize = 12.sp, modifier = Modifier.padding(vertical = 2.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun CausesCard(disease: DiseaseEntry) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .border(1.dp, SpaceBorder, RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = SpaceSurfaceCard),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Text("CAUSES & CLINICAL RISK FACTORS", color = BioAmber, fontWeight = FontWeight.Bold, fontSize = 11.sp, letterSpacing = 0.8.sp)
            Spacer(modifier = Modifier.height(8.dp))
            disease.causesAndRiskFactors.forEach { factor ->
                Row(modifier = Modifier.padding(vertical = 2.dp), verticalAlignment = Alignment.Top) {
                    Text("• ", color = BioAmber, fontWeight = FontWeight.Bold)
                    Text(factor, color = TextWhite, fontSize = 13.sp, lineHeight = 18.sp)
                }
            }
        }
    }
}

@Composable
private fun SymptomsCard(disease: DiseaseEntry) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .border(1.dp, SpaceBorder, RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = SpaceSurfaceCard),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Text("CHARACTERISTIC SYMPTOMS", color = BioRose, fontWeight = FontWeight.Bold, fontSize = 11.sp, letterSpacing = 0.8.sp)
            Spacer(modifier = Modifier.height(8.dp))
            disease.commonSymptoms.forEach { symptom ->
                Row(modifier = Modifier.padding(vertical = 2.dp), verticalAlignment = Alignment.Top) {
                    Text("• ", color = BioRose, fontWeight = FontWeight.Bold)
                    Text(symptom, color = TextWhite, fontSize = 13.sp, lineHeight = 18.sp)
                }
            }
        }
    }
}

@Composable
private fun DiagnosticsCard(disease: DiseaseEntry) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .border(1.dp, SpaceBorder, RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = SpaceSurfaceCard),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Text("EDUCATIONAL DIAGNOSTIC MODALITIES", color = BioViolet, fontWeight = FontWeight.Bold, fontSize = 11.sp, letterSpacing = 0.8.sp)
            Spacer(modifier = Modifier.height(8.dp))
            disease.educationalDiagnosticMethods.forEach { method ->
                Row(modifier = Modifier.padding(vertical = 2.dp), verticalAlignment = Alignment.Top) {
                    Text("• ", color = BioViolet, fontWeight = FontWeight.Bold)
                    Text(method, color = TextWhite, fontSize = 13.sp, lineHeight = 18.sp)
                }
            }
        }
    }
}

@Composable
private fun TreatmentsCard(disease: DiseaseEntry) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .border(1.dp, SpaceBorder, RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = SpaceSurfaceCard),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Text("EVIDENCE-BASED THERAPEUTIC REGIMENS", color = BioEmerald, fontWeight = FontWeight.Bold, fontSize = 11.sp, letterSpacing = 0.8.sp)
            Spacer(modifier = Modifier.height(8.dp))
            disease.evidenceBasedTreatments.forEach { tx ->
                Row(modifier = Modifier.padding(vertical = 2.dp), verticalAlignment = Alignment.Top) {
                    Text("• ", color = BioEmerald, fontWeight = FontWeight.Bold)
                    Text(tx, color = TextWhite, fontSize = 13.sp, lineHeight = 18.sp)
                }
            }
        }
    }
}
