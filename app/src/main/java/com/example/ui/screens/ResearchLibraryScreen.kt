package com.example.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.BioDatabaseResource
import com.example.data.model.ResearchPaper
import com.example.data.repository.ScienceRepository
import com.example.ui.responsive.ResponsiveConfig
import com.example.ui.theme.*

@Composable
fun ResearchLibraryScreen(
    responsiveConfig: ResponsiveConfig,
    modifier: Modifier = Modifier
) {
    var selectedTab by remember { mutableStateOf(0) }
    var filterQuery by remember { mutableStateOf("") }
    val context = LocalContext.current

    val filteredPapers = if (filterQuery.isBlank()) {
        ScienceRepository.researchPapers
    } else {
        ScienceRepository.researchPapers.filter {
            it.title.contains(filterQuery, ignoreCase = true) ||
            it.authors.contains(filterQuery, ignoreCase = true) ||
            it.category.contains(filterQuery, ignoreCase = true) ||
            it.abstractSummary.contains(filterQuery, ignoreCase = true)
        }
    }

    val filteredDatabases = if (filterQuery.isBlank()) {
        ScienceRepository.databases
    } else {
        ScienceRepository.databases.filter {
            it.name.contains(filterQuery, ignoreCase = true) ||
            it.abbreviation.contains(filterQuery, ignoreCase = true) ||
            it.primaryDataType.contains(filterQuery, ignoreCase = true) ||
            it.description.contains(filterQuery, ignoreCase = true)
        }
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = responsiveConfig.contentMaxWidth)
                .padding(horizontal = if (responsiveConfig.isDesktop) 28.dp else if (responsiveConfig.isTablet) 20.dp else 16.dp)
                .testTag("research_library_screen"),
            contentPadding = PaddingValues(top = 16.dp, bottom = 48.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = "Scientific Research Library",
                    fontSize = if (responsiveConfig.isDesktop) 28.sp else 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextWhite
                )
                Text(
                    text = "Peer-reviewed landmark scientific manuscripts and global biological database resources (NCBI, PubMed, UniProt, Ensembl, RCSB PDB).",
                    fontSize = 13.sp,
                    color = TextSlate
                )
            }

            // Search Bar & Tabs in a Row on Desktop
            item {
                if (responsiveConfig.isDesktop) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        OutlinedTextField(
                            value = filterQuery,
                            onValueChange = { filterQuery = it },
                            placeholder = { Text("Search papers, authors, DOIs, databases...") },
                            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = CyanPrimary) },
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = SpaceSurfaceVariant,
                                unfocusedContainerColor = SpaceSurfaceVariant,
                                focusedBorderColor = CyanPrimary,
                                unfocusedBorderColor = SpaceBorder,
                                focusedTextColor = TextWhite,
                                unfocusedTextColor = TextWhite
                            ),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.weight(1f)
                        )

                        TabRow(
                            selectedTabIndex = selectedTab,
                            containerColor = SpaceSurfaceCard,
                            contentColor = CyanPrimary,
                            divider = {},
                            modifier = Modifier.width(440.dp).clip(RoundedCornerShape(12.dp))
                        ) {
                            Tab(
                                selected = selectedTab == 0,
                                onClick = { selectedTab = 0 },
                                text = { Text("Landmark Papers (${filteredPapers.size})", fontWeight = if (selectedTab == 0) FontWeight.Bold else FontWeight.Normal) }
                            )
                            Tab(
                                selected = selectedTab == 1,
                                onClick = { selectedTab = 1 },
                                text = { Text("Databases Directory (${filteredDatabases.size})", fontWeight = if (selectedTab == 1) FontWeight.Bold else FontWeight.Normal) }
                            )
                        }
                    }
                } else {
                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        OutlinedTextField(
                            value = filterQuery,
                            onValueChange = { filterQuery = it },
                            placeholder = { Text("Filter papers or databases...") },
                            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = CyanPrimary) },
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = SpaceSurfaceVariant,
                                unfocusedContainerColor = SpaceSurfaceVariant,
                                focusedBorderColor = CyanPrimary,
                                unfocusedBorderColor = SpaceBorder,
                                focusedTextColor = TextWhite,
                                unfocusedTextColor = TextWhite
                            ),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth()
                        )

                        TabRow(
                            selectedTabIndex = selectedTab,
                            containerColor = SpaceSurfaceCard,
                            contentColor = CyanPrimary,
                            divider = { Divider(color = SpaceBorder, thickness = 0.5.dp) }
                        ) {
                            Tab(
                                selected = selectedTab == 0,
                                onClick = { selectedTab = 0 },
                                text = { Text("Landmark Papers (${filteredPapers.size})", fontWeight = if (selectedTab == 0) FontWeight.Bold else FontWeight.Normal) }
                            )
                            Tab(
                                selected = selectedTab == 1,
                                onClick = { selectedTab = 1 },
                                text = { Text("Databases Directory (${filteredDatabases.size})", fontWeight = if (selectedTab == 1) FontWeight.Bold else FontWeight.Normal) }
                            )
                        }
                    }
                }
            }

            if (selectedTab == 0) {
                if (responsiveConfig.isDesktop) {
                    val chunked = filteredPapers.chunked(2)
                    chunked.forEach { rowPapers ->
                        item {
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                                rowPapers.forEach { paper ->
                                    Box(modifier = Modifier.weight(1f)) {
                                        PaperCard(
                                            paper = paper,
                                            onOpenUrl = {
                                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(paper.externalUrl))
                                                context.startActivity(intent)
                                            }
                                        )
                                    }
                                }
                                if (rowPapers.size < 2) Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }
                } else {
                    items(filteredPapers) { paper ->
                        PaperCard(
                            paper = paper,
                            onOpenUrl = {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(paper.externalUrl))
                                context.startActivity(intent)
                            }
                        )
                    }
                }
            } else {
                if (responsiveConfig.isDesktop) {
                    val chunked = filteredDatabases.chunked(2)
                    chunked.forEach { rowDbs ->
                        item {
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                                rowDbs.forEach { db ->
                                    Box(modifier = Modifier.weight(1f)) {
                                        DatabaseCard(
                                            database = db,
                                            onOpenUrl = {
                                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(db.websiteUrl))
                                                context.startActivity(intent)
                                            }
                                        )
                                    }
                                }
                                if (rowDbs.size < 2) Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }
                } else {
                    items(filteredDatabases) { db ->
                        DatabaseCard(
                            database = db,
                            onOpenUrl = {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(db.websiteUrl))
                                context.startActivity(intent)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun PaperCard(
    paper: ResearchPaper,
    onOpenUrl: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .border(1.dp, SpaceBorder, RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = SpaceSurfaceCard),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    color = BioEmerald.copy(alpha = 0.15f),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(
                        text = paper.category.uppercase(),
                        color = BioEmerald,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }
                Text(
                    text = "${paper.citationCount} Citations",
                    color = CyanPrimary,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Text(
                text = paper.title,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = TextWhite,
                lineHeight = 22.sp
            )

            Text(
                text = "${paper.authors} • ${paper.journal} (${paper.publicationDate})",
                fontSize = 12.sp,
                color = ElectricBlue
            )

            Text(
                text = paper.abstractSummary,
                fontSize = 13.sp,
                color = TextSlate,
                lineHeight = 18.sp
            )

            Column {
                Text(
                    text = "Key Findings:",
                    color = TextWhite,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                paper.keyFindings.forEach { finding ->
                    Row(modifier = Modifier.padding(vertical = 2.dp), verticalAlignment = Alignment.Top) {
                        Text("• ", color = CyanPrimary, fontWeight = FontWeight.Bold)
                        Text(text = finding, color = TextSlate, fontSize = 12.sp, lineHeight = 16.sp)
                    }
                }
            }

            Divider(color = SpaceBorder, thickness = 0.5.dp)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "DOI: ${paper.doi}",
                    color = TextDim,
                    fontSize = 10.sp,
                    fontFamily = FontFamily.Monospace
                )
                Button(
                    onClick = onOpenUrl,
                    colors = ButtonDefaults.buttonColors(containerColor = SpaceSurfaceVariant, contentColor = CyanPrimary),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(imageVector = Icons.Default.OpenInNew, contentDescription = null, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("View Source", fontSize = 12.sp)
                }
            }
        }
    }
}

@Composable
private fun DatabaseCard(
    database: BioDatabaseResource,
    onOpenUrl: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .border(1.dp, SpaceBorder, RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = SpaceSurfaceCard),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    color = BioViolet.copy(alpha = 0.15f),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(
                        text = database.abbreviation,
                        color = BioViolet,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
                Text(
                    text = database.organization,
                    color = TextDim,
                    fontSize = 11.sp
                )
            }

            Text(
                text = database.name,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = TextWhite
            )

            Text(
                text = database.description,
                fontSize = 13.sp,
                color = TextSlate,
                lineHeight = 18.sp
            )

            Surface(
                color = SpaceSurfaceVariant,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(text = "Primary Data: ${database.primaryDataType}", color = CyanPrimary, fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "Example Query: ${database.queryExample}", color = TextWhite, fontSize = 11.sp, fontFamily = FontFamily.Monospace)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "Workflow: ${database.researchWorkflow}", color = BioMint, fontSize = 11.sp)
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = onOpenUrl,
                    colors = ButtonDefaults.buttonColors(containerColor = CyanPrimary, contentColor = DeepSpaceDark),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(imageVector = Icons.Default.Launch, contentDescription = null, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Access Portal", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
