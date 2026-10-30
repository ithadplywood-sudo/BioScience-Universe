package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.repository.ScienceRepository
import com.example.ui.responsive.ResponsiveConfig
import com.example.ui.theme.*
import com.example.viewmodel.BioScienceUiState
import com.example.viewmodel.BioScienceViewModel

@Composable
fun ScientificToolsScreen(
    responsiveConfig: ResponsiveConfig,
    uiState: BioScienceUiState,
    viewModel: BioScienceViewModel,
    modifier: Modifier = Modifier
) {
    val toolTabs = listOf(
        Pair(Icons.Default.Biotech, "DNA & Codons"),
        Pair(Icons.Default.GridOn, "Punnett Genetics"),
        Pair(Icons.Default.Scale, "Units & MW"),
        Pair(Icons.Default.Book, "Cell Glossary"),
        Pair(Icons.Default.Science, "Bio Elements"),
        Pair(Icons.Default.Quiz, "Quiz Lab"),
        Pair(Icons.Default.Hub, "Protein Explorer")
    )

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = responsiveConfig.contentMaxWidth)
                .padding(horizontal = if (responsiveConfig.isDesktop) 28.dp else if (responsiveConfig.isTablet) 20.dp else 16.dp)
                .testTag("scientific_tools_screen"),
            contentPadding = PaddingValues(top = 16.dp, bottom = 48.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = "Scientific Tools Center",
                    fontSize = if (responsiveConfig.isDesktop) 28.sp else 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextWhite
                )
                Text(
                    text = "Interactive, fully functional laboratory calculators, genetic simulators, physical converters, and biological explorers.",
                    fontSize = 13.sp,
                    color = TextSlate
                )
            }

            // Horizontal Tools Switcher
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    toolTabs.forEachIndexed { index, (icon, name) ->
                        val isSelected = uiState.activeToolIndex == index
                        FilterChip(
                            selected = isSelected,
                            onClick = { viewModel.setActiveTool(index) },
                            leadingIcon = {
                                Icon(imageVector = icon, contentDescription = null, modifier = Modifier.size(16.dp))
                            },
                            label = { Text(name, fontSize = 12.sp, fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = CyanPrimary,
                                selectedLabelColor = DeepSpaceDark,
                                selectedLeadingIconColor = DeepSpaceDark,
                                containerColor = SpaceSurfaceCard,
                                labelColor = TextWhite
                            ),
                            modifier = Modifier.testTag("tool_chip_$index")
                        )
                    }
                }
            }

            // Active Tool Content
            item {
                when (uiState.activeToolIndex) {
                    0 -> ResponsiveDnaAnalyzerTool(responsiveConfig, uiState, viewModel::onDnaInputChanged)
                    1 -> ResponsivePunnettTool(responsiveConfig, uiState, viewModel::onPunnettParentsChanged)
                    2 -> ResponsiveUnitConverterTool(responsiveConfig, uiState, viewModel::onUnitBpChanged)
                    3 -> ResponsiveGlossaryTool(responsiveConfig, uiState, viewModel::onGlossarySearchChanged)
                    4 -> ResponsiveBioElementsTool(responsiveConfig)
                    5 -> ResponsiveQuizLabTool(responsiveConfig, uiState, viewModel)
                    else -> ResponsiveProteinExplorerTool(responsiveConfig)
                }
            }
        }
    }
}

// -----------------------------------------------------------------
// 1. RESPONSIVE DNA ANALYZER (2 Columns on Desktop)
// -----------------------------------------------------------------
@Composable
private fun ResponsiveDnaAnalyzerTool(
    responsiveConfig: ResponsiveConfig,
    uiState: BioScienceUiState,
    onSequenceChanged: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .border(1.dp, CyanPrimary.copy(alpha = 0.5f), RoundedCornerShape(20.dp)),
        colors = CardDefaults.cardColors(containerColor = SpaceSurfaceCard),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Text(
                text = "1. DNA COMPLEMENT, MRNA & CODON TRANSLATOR",
                color = CyanPrimary,
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
                letterSpacing = 1.sp
            )

            if (responsiveConfig.isDesktop) {
                // DESKTOP: 2 Columns (Inputs on Left, Outputs on Right)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    // Left: Input & Presets
                    Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text(
                            text = "Enter a 5' -> 3' nucleotide sequence to compute Watson-Crick complement, reverse complement, transcribed mRNA, and peptide reading frame:",
                            color = TextSlate,
                            fontSize = 13.sp
                        )

                        OutlinedTextField(
                            value = uiState.dnaInput,
                            onValueChange = onSequenceChanged,
                            label = { Text("DNA Nucleotide Sequence (A, T, G, C)") },
                            singleLine = false,
                            maxLines = 4,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = SpaceSurfaceVariant,
                                unfocusedContainerColor = SpaceSurfaceVariant,
                                focusedBorderColor = CyanPrimary,
                                unfocusedBorderColor = SpaceBorder,
                                focusedTextColor = TextWhite,
                                unfocusedTextColor = TextWhite
                            ),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("dna_input_field")
                        )

                        Text("Select Sample DNA Template:", color = TextDim, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            listOf(
                                Pair("Human Insulin Exon 1", "ATGGCCCTGTGGATGCGCCTC"),
                                Pair("CRISPR Genomic Target", "GGGGCCACTAGGGACAGGAT"),
                                Pair("TATA Box Promoter", "TATAAAAGGGCGCGCTAGCT")
                            ).forEach { (label, seq) ->
                                AssistChip(
                                    onClick = { onSequenceChanged(seq) },
                                    label = { Text(label, fontSize = 11.sp) },
                                    colors = AssistChipDefaults.assistChipColors(containerColor = SpaceSurfaceVariant, labelColor = CyanPrimary)
                                )
                            }
                        }
                    }

                    // Right: Live Analysis Results
                    Column(modifier = Modifier.weight(1.2f), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        if (uiState.dnaResult.error != null) {
                            Surface(color = BioRose.copy(alpha = 0.15f), shape = RoundedCornerShape(8.dp), modifier = Modifier.fillMaxWidth()) {
                                Text(uiState.dnaResult.error, color = BioRose, fontSize = 12.sp, modifier = Modifier.padding(12.dp))
                            }
                        } else {
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                MetricBox("Length", "${uiState.dnaResult.length} bp")
                                MetricBox("GC Content", "${uiState.dnaResult.gcContentPercent}%")
                                MetricBox("Molecular Weight", "${uiState.dnaResult.molecularWeightDa} Da")
                            }
                            Divider(color = SpaceBorder, thickness = 0.5.dp)
                            SequenceOutputBox("5' -> 3' Original Strand", uiState.dnaResult.original, TextWhite)
                            SequenceOutputBox("3' -> 5' Complement Strand", uiState.dnaResult.complement, ElectricBlue)
                            SequenceOutputBox("5' -> 3' Reverse Complement", uiState.dnaResult.reverseComplement, CyanPrimary)
                            SequenceOutputBox("5' -> 3' Transcribed mRNA", uiState.dnaResult.mrna, BioMint)

                            if (uiState.dnaResult.proteinTranslation.isNotEmpty()) {
                                Text("Translated Amino Acids (Frame 1):", color = BioAmber, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                Text(
                                    uiState.dnaResult.proteinTranslation.joinToString("  •  "),
                                    color = TextWhite,
                                    fontFamily = FontFamily.Monospace,
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }
                }
            } else {
                // MOBILE & TABLET: Stacked Layout
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        text = "Enter a 5' -> 3' nucleotide sequence to compute Watson-Crick complement, reverse complement, transcribed mRNA, and peptide reading frame:",
                        color = TextSlate,
                        fontSize = 12.sp
                    )

                    OutlinedTextField(
                        value = uiState.dnaInput,
                        onValueChange = onSequenceChanged,
                        label = { Text("DNA Nucleotide Sequence (A, T, G, C)") },
                        singleLine = false,
                        maxLines = 3,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = SpaceSurfaceVariant,
                            unfocusedContainerColor = SpaceSurfaceVariant,
                            focusedBorderColor = CyanPrimary,
                            unfocusedBorderColor = SpaceBorder,
                            focusedTextColor = TextWhite,
                            unfocusedTextColor = TextWhite
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth().testTag("dna_input_field")
                    )

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        listOf(
                            Pair("Insulin Exon", "ATGGCCCTGTGGATGCGCCTC"),
                            Pair("CRISPR", "GGGGCCACTAGGGACAGGAT")
                        ).forEach { (label, seq) ->
                            AssistChip(
                                onClick = { onSequenceChanged(seq) },
                                label = { Text(label, fontSize = 11.sp) },
                                colors = AssistChipDefaults.assistChipColors(containerColor = SpaceSurfaceVariant, labelColor = CyanPrimary)
                            )
                        }
                    }

                    if (uiState.dnaResult.error != null) {
                        Surface(color = BioRose.copy(alpha = 0.15f), shape = RoundedCornerShape(8.dp), modifier = Modifier.fillMaxWidth()) {
                            Text(uiState.dnaResult.error, color = BioRose, fontSize = 12.sp, modifier = Modifier.padding(10.dp))
                        }
                    } else {
                        Divider(color = SpaceBorder, thickness = 0.5.dp)
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            MetricBox("Length", "${uiState.dnaResult.length} bp")
                            MetricBox("GC Content", "${uiState.dnaResult.gcContentPercent}%")
                            MetricBox("Mol. Weight", "${uiState.dnaResult.molecularWeightDa} Da")
                        }
                        Divider(color = SpaceBorder, thickness = 0.5.dp)
                        SequenceOutputBox("5' -> 3' Original Strand", uiState.dnaResult.original, TextWhite)
                        SequenceOutputBox("3' -> 5' Complement Strand", uiState.dnaResult.complement, ElectricBlue)
                        SequenceOutputBox("5' -> 3' Reverse Complement", uiState.dnaResult.reverseComplement, CyanPrimary)
                        SequenceOutputBox("5' -> 3' Transcribed mRNA", uiState.dnaResult.mrna, BioMint)
                    }
                }
            }
        }
    }
}

// -----------------------------------------------------------------
// 2. RESPONSIVE PUNNETT GENETICS SIMULATOR
// -----------------------------------------------------------------
@Composable
private fun ResponsivePunnettTool(
    responsiveConfig: ResponsiveConfig,
    uiState: BioScienceUiState,
    onParentsChanged: (String, String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .border(1.dp, BioEmerald.copy(alpha = 0.5f), RoundedCornerShape(20.dp)),
        colors = CardDefaults.cardColors(containerColor = SpaceSurfaceCard),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Text("2. MENDELIAN GENETICS PUNNETT SIMULATOR", color = BioEmerald, fontWeight = FontWeight.Bold, fontSize = 13.sp, letterSpacing = 1.sp)

            if (responsiveConfig.isDesktop) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(24.dp)) {
                    // Left Column: Genotype Inputs & Presets
                    Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text(
                            "Model monohybrid genetic inheritance crosses to determine statistical genotype and phenotype distribution percentages:",
                            color = TextSlate,
                            fontSize = 13.sp
                        )

                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            OutlinedTextField(
                                value = uiState.punnettP1,
                                onValueChange = { onParentsChanged(it.take(2), uiState.punnettP2) },
                                label = { Text("Parent 1") },
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedContainerColor = SpaceSurfaceVariant,
                                    unfocusedContainerColor = SpaceSurfaceVariant,
                                    focusedBorderColor = BioEmerald,
                                    focusedTextColor = TextWhite,
                                    unfocusedTextColor = TextWhite
                                ),
                                modifier = Modifier.weight(1f)
                            )
                            OutlinedTextField(
                                value = uiState.punnettP2,
                                onValueChange = { onParentsChanged(uiState.punnettP1, it.take(2)) },
                                label = { Text("Parent 2") },
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedContainerColor = SpaceSurfaceVariant,
                                    unfocusedContainerColor = SpaceSurfaceVariant,
                                    focusedBorderColor = BioEmerald,
                                    focusedTextColor = TextWhite,
                                    unfocusedTextColor = TextWhite
                                ),
                                modifier = Modifier.weight(1f)
                            )
                        }

                        Text("Preset Crosses:", color = TextDim, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            listOf(
                                Pair("Heterozygous (Aa x Aa)", Pair("Aa", "Aa")),
                                Pair("Test Cross (Aa x aa)", Pair("Aa", "aa")),
                                Pair("Homozygous (AA x aa)", Pair("AA", "aa"))
                            ).forEach { (name, pair) ->
                                AssistChip(
                                    onClick = { onParentsChanged(pair.first, pair.second) },
                                    label = { Text(name, fontSize = 11.sp) },
                                    colors = AssistChipDefaults.assistChipColors(containerColor = SpaceSurfaceVariant, labelColor = BioMint)
                                )
                            }
                        }
                    }

                    // Right Column: Punnett 2x2 Grid & Statistical Ratios
                    Column(modifier = Modifier.weight(1.1f), verticalArrangement = Arrangement.spacedBy(14.dp)) {
                        Text("2x2 Gamete Segregation Grid:", color = TextWhite, fontWeight = FontWeight.Bold, fontSize = 13.sp)

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(SpaceSurfaceVariant)
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                                uiState.punnettResult.grid.forEach { row ->
                                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                                        row.forEach { cell ->
                                            Surface(
                                                color = SpaceSurfaceCard,
                                                shape = RoundedCornerShape(8.dp),
                                                border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(BioEmerald))
                                            ) {
                                                Text(
                                                    text = cell,
                                                    fontWeight = FontWeight.Bold,
                                                    fontSize = 18.sp,
                                                    color = TextWhite,
                                                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp)
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text("Genotype Frequencies:", color = CyanPrimary, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                uiState.punnettResult.genotypePercentages.forEach { (geno, pct) ->
                                    Text("• $geno: $pct%", color = TextWhite, fontSize = 12.sp)
                                }
                            }
                            Column(modifier = Modifier.weight(1f)) {
                                Text("Phenotype Probabilities:", color = BioAmber, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                uiState.punnettResult.phenotypePercentages.forEach { (pheno, pct) ->
                                    Text("• $pheno: $pct%", color = TextWhite, fontSize = 12.sp)
                                }
                            }
                        }
                    }
                }
            } else {
                // MOBILE / TABLET
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        OutlinedTextField(
                            value = uiState.punnettP1,
                            onValueChange = { onParentsChanged(it.take(2), uiState.punnettP2) },
                            label = { Text("Parent 1") },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = SpaceSurfaceVariant,
                                unfocusedContainerColor = SpaceSurfaceVariant,
                                focusedBorderColor = BioEmerald,
                                focusedTextColor = TextWhite,
                                unfocusedTextColor = TextWhite
                            ),
                            modifier = Modifier.weight(1f)
                        )
                        OutlinedTextField(
                            value = uiState.punnettP2,
                            onValueChange = { onParentsChanged(uiState.punnettP1, it.take(2)) },
                            label = { Text("Parent 2") },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = SpaceSurfaceVariant,
                                unfocusedContainerColor = SpaceSurfaceVariant,
                                focusedBorderColor = BioEmerald,
                                focusedTextColor = TextWhite,
                                unfocusedTextColor = TextWhite
                            ),
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(SpaceSurfaceVariant)
                            .padding(14.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                            uiState.punnettResult.grid.forEach { row ->
                                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                                    row.forEach { cell ->
                                        Surface(
                                            color = SpaceSurfaceCard,
                                            shape = RoundedCornerShape(8.dp),
                                            border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(BioEmerald))
                                        ) {
                                            Text(
                                                text = cell,
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 17.sp,
                                                color = TextWhite,
                                                modifier = Modifier.padding(horizontal = 18.dp, vertical = 10.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text("Genotypes:", color = CyanPrimary, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            uiState.punnettResult.genotypePercentages.forEach { (geno, pct) ->
                                Text("• $geno: $pct%", color = TextWhite, fontSize = 12.sp)
                            }
                        }
                        Column(modifier = Modifier.weight(1f)) {
                            Text("Phenotypes:", color = BioAmber, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            uiState.punnettResult.phenotypePercentages.forEach { (pheno, pct) ->
                                Text("• $pheno: $pct%", color = TextWhite, fontSize = 12.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}

// -----------------------------------------------------------------
// 3. RESPONSIVE UNIT CONVERTER
// -----------------------------------------------------------------
@Composable
private fun ResponsiveUnitConverterTool(
    responsiveConfig: ResponsiveConfig,
    uiState: BioScienceUiState,
    onBpChanged: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .border(1.dp, ElectricBlue.copy(alpha = 0.5f), RoundedCornerShape(20.dp)),
        colors = CardDefaults.cardColors(containerColor = SpaceSurfaceCard),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
            Text("3. SCIENTIFIC UNIT & DNA PHYSICAL LENGTH CONVERTER", color = ElectricBlue, fontWeight = FontWeight.Bold, fontSize = 13.sp, letterSpacing = 1.sp)

            OutlinedTextField(
                value = uiState.unitConverterInputBp,
                onValueChange = onBpChanged,
                label = { Text("Base Pairs (bp)") },
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = SpaceSurfaceVariant,
                    unfocusedContainerColor = SpaceSurfaceVariant,
                    focusedBorderColor = ElectricBlue,
                    focusedTextColor = TextWhite,
                    unfocusedTextColor = TextWhite
                ),
                modifier = Modifier.fillMaxWidth()
            )

            val bpVal = uiState.unitConverterInputBp.toLongOrNull() ?: 0L
            val nmVal = uiState.unitConverterResultNm
            val umVal = nmVal / 1000.0
            val mmVal = umVal / 1000.0

            Surface(color = SpaceSurfaceVariant, shape = RoundedCornerShape(12.dp), modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Computed Physical Dimensions:", color = CyanPrimary, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                    Text("• $nmVal Nanometers (nm)", color = TextWhite, fontSize = 13.sp)
                    Text("• $umVal Micrometers (μm)", color = TextWhite, fontSize = 13.sp)
                    Text("• $mmVal Millimeters (mm)", color = TextWhite, fontSize = 13.sp)
                    Text("• Helical turns (~10.5 bp/turn): ${(bpVal / 10.5 * 10).toInt() / 10.0} full 360° turns", color = BioMint, fontSize = 12.sp)
                }
            }
        }
    }
}

// -----------------------------------------------------------------
// 4. RESPONSIVE GLOSSARY (Multi-Column Grid on Desktop)
// -----------------------------------------------------------------
@Composable
private fun ResponsiveGlossaryTool(
    responsiveConfig: ResponsiveConfig,
    uiState: BioScienceUiState,
    onSearchChanged: (String) -> Unit
) {
    val filteredGlossary = if (uiState.glossarySearch.isBlank()) {
        ScienceRepository.glossary
    } else {
        ScienceRepository.glossary.filter {
            it.term.contains(uiState.glossarySearch, ignoreCase = true) ||
            it.definition.contains(uiState.glossarySearch, ignoreCase = true) ||
            it.category.contains(uiState.glossarySearch, ignoreCase = true)
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .border(1.dp, SpaceBorder, RoundedCornerShape(20.dp)),
        colors = CardDefaults.cardColors(containerColor = SpaceSurfaceCard),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
            Text("4. CELL BIOLOGY & MOLECULAR GLOSSARY", color = CyanPrimary, fontWeight = FontWeight.Bold, fontSize = 13.sp, letterSpacing = 1.sp)

            OutlinedTextField(
                value = uiState.glossarySearch,
                onValueChange = onSearchChanged,
                placeholder = { Text("Filter glossary terms...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = CyanPrimary) },
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = SpaceSurfaceVariant,
                    unfocusedContainerColor = SpaceSurfaceVariant,
                    focusedBorderColor = CyanPrimary,
                    focusedTextColor = TextWhite,
                    unfocusedTextColor = TextWhite
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            )

            val chunkedGlossary = filteredGlossary.chunked(if (responsiveConfig.isDesktop) 2 else 1)
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                chunkedGlossary.forEach { rowItems ->
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        rowItems.forEach { item ->
                            Surface(
                                color = SpaceSurfaceVariant,
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier.weight(1f)
                            ) {
                                Column(modifier = Modifier.padding(14.dp)) {
                                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                        Text(item.term, fontWeight = FontWeight.Bold, color = TextWhite, fontSize = 15.sp)
                                        Text(item.phonetic, color = CyanPrimary, fontSize = 11.sp, fontFamily = FontFamily.Monospace)
                                    }
                                    Text(item.category, color = ElectricBlue, fontSize = 11.sp)
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(item.definition, color = TextSlate, fontSize = 12.sp, lineHeight = 16.sp)
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text("Example: ${item.biologicalExample}", color = BioMint, fontSize = 11.sp)
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
// 5. RESPONSIVE BIO-ELEMENTS
// -----------------------------------------------------------------
@Composable
private fun ResponsiveBioElementsTool(responsiveConfig: ResponsiveConfig) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .border(1.dp, SpaceBorder, RoundedCornerShape(20.dp)),
        colors = CardDefaults.cardColors(containerColor = SpaceSurfaceCard),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
            Text("5. BIOLOGY PERIODIC BIO-ELEMENT REFERENCE", color = BioAmber, fontWeight = FontWeight.Bold, fontSize = 13.sp, letterSpacing = 1.sp)
            Text("The 12 essential biological elements comprising 99.9% of human body mass and cellular metabolism:", color = TextSlate, fontSize = 13.sp)

            val chunked = ScienceRepository.bioElements.chunked(if (responsiveConfig.isDesktop) 2 else 1)
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                chunked.forEach { rowItems ->
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        rowItems.forEach { elem ->
                            Surface(color = SpaceSurfaceVariant, shape = RoundedCornerShape(10.dp), modifier = Modifier.weight(1f)) {
                                Row(
                                    modifier = Modifier.padding(12.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                                ) {
                                    Surface(
                                        color = SpaceSurfaceCard,
                                        shape = RoundedCornerShape(8.dp),
                                        border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(BioAmber)),
                                        modifier = Modifier.size(46.dp)
                                    ) {
                                        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                                            Text(elem.atomicNumber.toString(), fontSize = 8.sp, color = TextDim)
                                            Text(elem.symbol, fontWeight = FontWeight.Black, fontSize = 15.sp, color = BioAmber)
                                        }
                                    }
                                    Column(modifier = Modifier.weight(1f)) {
                                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                            Text(elem.name, fontWeight = FontWeight.Bold, color = TextWhite, fontSize = 14.sp)
                                            Text("${elem.bodyMassPercent}% Body Mass", fontWeight = FontWeight.Bold, color = CyanPrimary, fontSize = 11.sp)
                                        }
                                        Text(elem.category, color = ElectricBlue, fontSize = 11.sp)
                                        Text(elem.physiologicalRole, color = TextSlate, fontSize = 11.sp, lineHeight = 15.sp)
                                    }
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
// 6. RESPONSIVE QUIZ LAB
// -----------------------------------------------------------------
@Composable
private fun ResponsiveQuizLabTool(
    responsiveConfig: ResponsiveConfig,
    uiState: BioScienceUiState,
    viewModel: BioScienceViewModel
) {
    val currentQ = ScienceRepository.quizzes.getOrNull(uiState.quizCurrentIndex)
        ?: ScienceRepository.quizzes.first()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .border(1.dp, BioViolet.copy(alpha = 0.5f), RoundedCornerShape(20.dp)),
        colors = CardDefaults.cardColors(containerColor = SpaceSurfaceCard),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(color = BioViolet.copy(alpha = 0.2f), shape = RoundedCornerShape(6.dp)) {
                    Text(
                        text = "QUESTION ${uiState.quizCurrentIndex + 1} OF ${ScienceRepository.quizzes.size}",
                        color = BioViolet,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
                Text("Score: ${uiState.quizScore}", color = CyanPrimary, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            }

            Text(currentQ.question, fontSize = 17.sp, fontWeight = FontWeight.Bold, color = TextWhite, lineHeight = 24.sp)

            currentQ.options.forEachIndexed { index, option ->
                val isSelected = uiState.quizSelectedOption == index
                val isCorrect = index == currentQ.correctIndex

                val borderColor = when {
                    uiState.quizIsAnswered && isCorrect -> BioEmerald
                    uiState.quizIsAnswered && isSelected && !isCorrect -> BioRose
                    isSelected -> CyanPrimary
                    else -> SpaceBorder
                }

                Surface(
                    color = if (isSelected) SpaceSurfaceVariant else SpaceSurfaceVariant.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(10.dp),
                    border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(borderColor)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(enabled = !uiState.quizIsAnswered) { viewModel.selectQuizOption(index) }
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Surface(
                            color = if (isSelected) CyanPrimary else SpaceSurfaceCard,
                            shape = CircleShape,
                            modifier = Modifier.size(24.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(('A' + index).toString(), fontWeight = FontWeight.Bold, fontSize = 12.sp, color = if (isSelected) DeepSpaceDark else TextWhite)
                            }
                        }
                        Text(option, color = TextWhite, fontSize = 13.sp, modifier = Modifier.weight(1f))
                    }
                }
            }

            if (uiState.quizIsAnswered) {
                Surface(
                    color = if (uiState.quizSelectedOption == currentQ.correctIndex) BioEmerald.copy(alpha = 0.15f) else BioRose.copy(alpha = 0.15f),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(
                            text = if (uiState.quizSelectedOption == currentQ.correctIndex) "Correct Analysis!" else "Scientific Review:",
                            fontWeight = FontWeight.Bold,
                            color = if (uiState.quizSelectedOption == currentQ.correctIndex) BioEmerald else BioRose,
                            fontSize = 13.sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(currentQ.explanation, color = TextWhite, fontSize = 12.sp, lineHeight = 17.sp)
                    }
                }
            }

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                if (!uiState.quizIsAnswered) {
                    Button(
                        onClick = { viewModel.submitQuizAnswer() },
                        enabled = uiState.quizSelectedOption != null,
                        colors = ButtonDefaults.buttonColors(containerColor = CyanPrimary, contentColor = DeepSpaceDark)
                    ) {
                        Text("Submit Answer", fontWeight = FontWeight.Bold)
                    }
                } else {
                    Button(
                        onClick = { viewModel.nextQuizQuestion() },
                        colors = ButtonDefaults.buttonColors(containerColor = CyanPrimary, contentColor = DeepSpaceDark)
                    ) {
                        Text("Next Question ->", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

// -----------------------------------------------------------------
// 7. RESPONSIVE PROTEIN EXPLORER
// -----------------------------------------------------------------
@Composable
private fun ResponsiveProteinExplorerTool(responsiveConfig: ResponsiveConfig) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .border(1.dp, SpaceBorder, RoundedCornerShape(20.dp)),
        colors = CardDefaults.cardColors(containerColor = SpaceSurfaceCard),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
            Text("7. PROTEIN STRUCTURE RESOURCE EXPLORER", color = CyanPrimary, fontWeight = FontWeight.Bold, fontSize = 13.sp, letterSpacing = 1.sp)
            Text("Direct lookup index for landmark biological macromolecules deposited in RCSB PDB and AlphaFold DB:", color = TextSlate, fontSize = 13.sp)

            val proteins = listOf(
                Triple("1BNA", "B-DNA Dodecamer", "First single-crystal X-ray structure of a full B-DNA turn confirming Watson-Crick pairing"),
                Triple("4OO8", "CRISPR-Cas9-sgRNA-DNA Complex", "Crystal structure illustrating RNA-guided target recognition and R-loop formation"),
                Triple("6VXX", "SARS-CoV-2 Spike Glycoprotein", "Prefusion stabilized trimer determined via cryogenic electron microscopy (Cryo-EM)"),
                Triple("1MBN", "Sperm Whale Myoglobin", "First globular protein structure resolved by John Kendrew (1958 Nobel Prize)")
            )

            val chunked = proteins.chunked(if (responsiveConfig.isDesktop) 2 else 1)
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                chunked.forEach { rowItems ->
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        rowItems.forEach { (pdbId, name, desc) ->
                            Surface(color = SpaceSurfaceVariant, shape = RoundedCornerShape(10.dp), modifier = Modifier.weight(1f)) {
                                Column(modifier = Modifier.padding(14.dp)) {
                                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                        Text(name, fontWeight = FontWeight.Bold, color = TextWhite, fontSize = 14.sp)
                                        Surface(color = SpaceSurfaceCard, shape = RoundedCornerShape(4.dp)) {
                                            Text("PDB ID: $pdbId", color = CyanPrimary, fontSize = 10.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Monospace, modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp))
                                        }
                                    }
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(desc, color = TextSlate, fontSize = 11.sp, lineHeight = 15.sp)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun MetricBox(title: String, value: String) {
    Column {
        Text(text = title, fontSize = 11.sp, color = TextDim)
        Text(text = value, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = TextWhite)
    }
}

@Composable
private fun SequenceOutputBox(label: String, sequence: String, color: Color) {
    Column {
        Text(text = label, fontSize = 11.sp, color = color, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(2.dp))
        Surface(
            color = SpaceSurfaceVariant,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = sequence,
                color = TextWhite,
                fontSize = 12.sp,
                fontFamily = FontFamily.Monospace,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp)
            )
        }
    }
}
