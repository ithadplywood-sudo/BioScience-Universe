package com.example.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import com.example.ui.responsive.ResponsiveConfig
import com.example.ui.theme.*
import com.example.viewmodel.AppDestination

import com.example.ui.responsive.ViewportMode

@Composable
fun BioTopBar(
    responsiveConfig: ResponsiveConfig,
    currentDestination: AppDestination,
    canNavigateBack: Boolean,
    onNavigateBack: () -> Unit,
    onNavigateDestination: (AppDestination) -> Unit,
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
    onClearSearch: () -> Unit,
    viewportMode: ViewportMode,
    onSelectViewportMode: (ViewportMode) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
    ) {
        ViewportControlBanner(
            viewportMode = viewportMode,
            onSelectViewportMode = onSelectViewportMode,
            onOpenWebView = { onNavigateDestination(AppDestination.WEB_VIEW) },
            currentDestination = currentDestination
        )

        if (responsiveConfig.isDesktop) {
            DesktopTopNavigation(
                currentDestination = currentDestination,
                onNavigateDestination = onNavigateDestination,
                searchQuery = searchQuery,
                onSearchQueryChanged = onSearchQueryChanged,
                onClearSearch = onClearSearch
            )
        } else if (responsiveConfig.isTablet) {
            TabletTopNavigation(
                currentDestination = currentDestination,
                canNavigateBack = canNavigateBack,
                onNavigateBack = onNavigateBack,
                onNavigateDestination = onNavigateDestination,
                searchQuery = searchQuery,
                onSearchQueryChanged = onSearchQueryChanged,
                onClearSearch = onClearSearch
            )
        } else {
            MobileTopNavigation(
                currentDestination = currentDestination,
                canNavigateBack = canNavigateBack,
                onNavigateBack = onNavigateBack,
                onNavigateDestination = onNavigateDestination,
                searchQuery = searchQuery,
                onSearchQueryChanged = onSearchQueryChanged,
                onClearSearch = onClearSearch
            )
        }
    }
}

@Composable
fun ViewportControlBanner(
    viewportMode: ViewportMode,
    onSelectViewportMode: (ViewportMode) -> Unit,
    onOpenWebView: () -> Unit,
    currentDestination: AppDestination
) {
    Surface(
        color = DeepSpaceDark.copy(alpha = 0.95f),
        modifier = Modifier
            .fillMaxWidth()
            .border(width = 0.5.dp, color = CyanPrimary.copy(alpha = 0.3f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 12.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "VIEWPORT:",
                color = ElectricBlue,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.8.sp
            )

            ViewportMode.entries.forEach { mode ->
                val isSelected = viewportMode == mode
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = if (isSelected) CyanPrimary else SpaceSurfaceVariant,
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .clickable { onSelectViewportMode(mode) }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = mode.iconText,
                            fontSize = 11.sp
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = mode.label,
                            color = if (isSelected) DeepSpaceDark else TextWhite,
                            fontSize = 10.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.width(6.dp))

            Surface(
                shape = RoundedCornerShape(12.dp),
                color = if (currentDestination == AppDestination.WEB_VIEW) ElectricBlue else SpaceSurfaceVariant,
                border = CardDefaults.outlinedCardBorder().copy(brush = Brush.horizontalGradient(listOf(CyanPrimary, ElectricBlue))),
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .clickable { onOpenWebView() }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 3.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Language,
                        contentDescription = null,
                        tint = if (currentDestination == AppDestination.WEB_VIEW) DeepSpaceDark else CyanPrimary,
                        modifier = Modifier.size(12.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "HTML5/CSS Web View",
                        color = if (currentDestination == AppDestination.WEB_VIEW) DeepSpaceDark else TextWhite,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

// -------------------------------------------------------------
// 1. DESKTOP / LAPTOP FULL-WIDTH HORIZONTAL WEBSITE NAVIGATION
// -------------------------------------------------------------
@Composable
private fun DesktopTopNavigation(
    currentDestination: AppDestination,
    onNavigateDestination: (AppDestination) -> Unit,
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
    onClearSearch: () -> Unit
) {
    Surface(
        color = SpaceSurface.copy(alpha = 0.98f),
        modifier = Modifier
            .fillMaxWidth()
            .border(width = 0.5.dp, color = SpaceBorder)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp, vertical = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(max = 1440.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // LEFT: Brand Logo & Title
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(14.dp),
                    modifier = Modifier.clickable { onNavigateDestination(AppDestination.HOME) }
                ) {
                    Box(
                        modifier = Modifier
                            .size(46.dp)
                            .clip(CircleShape)
                            .border(2.dp, CyanPrimary, CircleShape)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.app_icon_bioscience_1793378142747),
                            contentDescription = "BioScience Universe Logo",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "BIOSCIENCE",
                                fontWeight = FontWeight.Black,
                                fontSize = 20.sp,
                                color = TextWhite,
                                letterSpacing = 1.2.sp
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = "UNIVERSE",
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                color = CyanPrimary,
                                letterSpacing = 1.2.sp
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Surface(
                                color = CyanPrimary.copy(alpha = 0.15f),
                                shape = RoundedCornerShape(4.dp),
                                border = CardDefaults.outlinedCardBorder().copy(brush = Brush.horizontalGradient(listOf(CyanPrimary, ElectricBlue)))
                            ) {
                                Text(
                                    text = "GLOBAL RESEARCH PORTAL",
                                    color = CyanPrimary,
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 0.8.sp,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }
                        Text(
                            text = "EXPLORE THE SCIENCE OF LIFE • FOUNDATIONAL TO ADVANCED MEDICINE",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = ElectricBlue,
                            letterSpacing = 1.sp
                        )
                    }
                }

                // CENTER: Primary Horizontal Navigation Links
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    val navItems = listOf(
                        Triple("Home", Icons.Default.Home, AppDestination.HOME),
                        Triple("12 Divisions", Icons.Default.Hub, AppDestination.DIVISIONS),
                        Triple("Diseases", Icons.Default.MedicalServices, AppDestination.DISEASES),
                        Triple("Lab Tools", Icons.Default.Calculate, AppDestination.TOOLS),
                        Triple("Research Library", Icons.Default.MenuBook, AppDestination.RESEARCH_LIBRARY),
                        Triple("AI Assistant", Icons.Default.SmartToy, AppDestination.AI_ASSISTANT),
                        Triple("About", Icons.Default.Info, AppDestination.ABOUT),
                        Triple("Web View", Icons.Default.Language, AppDestination.WEB_VIEW)
                    )

                    navItems.forEach { (label, icon, destination) ->
                        val isSelected = when (destination) {
                            AppDestination.HOME -> currentDestination == AppDestination.HOME
                            AppDestination.DIVISIONS -> currentDestination == AppDestination.DIVISIONS || currentDestination == AppDestination.DIVISION_DETAIL
                            AppDestination.DISEASES -> currentDestination == AppDestination.DISEASES || currentDestination == AppDestination.DISEASE_DETAIL
                            AppDestination.TOOLS -> currentDestination == AppDestination.TOOLS
                            AppDestination.RESEARCH_LIBRARY -> currentDestination == AppDestination.RESEARCH_LIBRARY
                            AppDestination.AI_ASSISTANT -> currentDestination == AppDestination.AI_ASSISTANT
                            AppDestination.ABOUT -> currentDestination == AppDestination.ABOUT
                            AppDestination.WEB_VIEW -> currentDestination == AppDestination.WEB_VIEW
                            else -> false
                        }

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .clickable { onNavigateDestination(destination) }
                                .padding(horizontal = 12.dp, vertical = 8.dp)
                                .testTag("desktop_nav_${destination.name.lowercase()}")
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Icon(
                                    imageVector = icon,
                                    contentDescription = null,
                                    tint = if (isSelected) CyanPrimary else TextSlate,
                                    modifier = Modifier.size(16.dp)
                                )
                                Text(
                                    text = label,
                                    color = if (isSelected) TextWhite else TextSlate,
                                    fontSize = 13.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Box(
                                modifier = Modifier
                                    .width(28.dp)
                                    .height(2.dp)
                                    .background(if (isSelected) CyanPrimary else Color.Transparent, CircleShape)
                            )
                        }
                    }
                }

                // RIGHT: Global Search Box + AI Assistant Quick CTA
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = onSearchQueryChanged,
                        placeholder = {
                            Text(
                                text = "Search genes, pathology, CRISPR, PDB...",
                                color = TextDim,
                                fontSize = 12.sp
                            )
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search",
                                tint = CyanPrimary,
                                modifier = Modifier.size(16.dp)
                            )
                        },
                        trailingIcon = {
                            if (searchQuery.isNotEmpty()) {
                                IconButton(onClick = onClearSearch) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = "Clear",
                                        tint = TextSlate,
                                        modifier = Modifier.size(14.dp)
                                    )
                                }
                            }
                        },
                        singleLine = true,
                        shape = RoundedCornerShape(20.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = SpaceSurfaceVariant,
                            unfocusedContainerColor = SpaceSurfaceVariant.copy(alpha = 0.7f),
                            focusedBorderColor = CyanPrimary,
                            unfocusedBorderColor = SpaceBorder,
                            focusedTextColor = TextWhite,
                            unfocusedTextColor = TextWhite
                        ),
                        modifier = Modifier
                            .width(280.dp)
                            .height(44.dp)
                            .testTag("desktop_global_search_input")
                    )

                    Button(
                        onClick = { onNavigateDestination(AppDestination.AI_ASSISTANT) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = CyanPrimary,
                            contentColor = DeepSpaceDark
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .height(44.dp)
                            .testTag("desktop_ai_assistant_btn")
                    ) {
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "AI Research",
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
    }
}

// -------------------------------------------------------------
// 2. TABLET ADAPTIVE NAVIGATION
// -------------------------------------------------------------
@Composable
private fun TabletTopNavigation(
    currentDestination: AppDestination,
    canNavigateBack: Boolean,
    onNavigateBack: () -> Unit,
    onNavigateDestination: (AppDestination) -> Unit,
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
    onClearSearch: () -> Unit
) {
    Surface(
        color = SpaceSurface.copy(alpha = 0.98f),
        modifier = Modifier
            .fillMaxWidth()
            .border(width = 0.5.dp, color = SpaceBorder)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    if (canNavigateBack) {
                        IconButton(
                            onClick = onNavigateBack,
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(SpaceSurfaceVariant)
                        ) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = CyanPrimary)
                        }
                    } else {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .border(1.5.dp, CyanPrimary, CircleShape)
                                .clickable { onNavigateDestination(AppDestination.HOME) }
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.app_icon_bioscience_1793378142747),
                                contentDescription = "Logo",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }

                    Column(modifier = Modifier.clickable { onNavigateDestination(AppDestination.HOME) }) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("BIOSCIENCE", fontWeight = FontWeight.Black, fontSize = 18.sp, color = TextWhite)
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("UNIVERSE", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = CyanPrimary)
                        }
                        Text("EXPLORE THE SCIENCE OF LIFE", fontSize = 9.sp, fontWeight = FontWeight.SemiBold, color = ElectricBlue)
                    }
                }

                // Search Bar on Tablet
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = onSearchQueryChanged,
                    placeholder = { Text("Search topics, CRISPR, diseases...", fontSize = 12.sp, color = TextDim) },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = CyanPrimary, modifier = Modifier.size(16.dp)) },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            IconButton(onClick = onClearSearch) {
                                Icon(Icons.Default.Close, contentDescription = null, tint = TextSlate, modifier = Modifier.size(14.dp))
                            }
                        }
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(20.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = SpaceSurfaceVariant,
                        unfocusedContainerColor = SpaceSurfaceVariant,
                        focusedBorderColor = CyanPrimary,
                        unfocusedBorderColor = SpaceBorder,
                        focusedTextColor = TextWhite,
                        unfocusedTextColor = TextWhite
                    ),
                    modifier = Modifier
                        .width(260.dp)
                        .height(44.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Navigation Chips
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val navTabs = listOf(
                    Triple("Home", Icons.Default.Home, AppDestination.HOME),
                    Triple("12 Divisions", Icons.Default.Hub, AppDestination.DIVISIONS),
                    Triple("Diseases", Icons.Default.MedicalServices, AppDestination.DISEASES),
                    Triple("Lab Tools", Icons.Default.Calculate, AppDestination.TOOLS),
                    Triple("Research", Icons.Default.MenuBook, AppDestination.RESEARCH_LIBRARY),
                    Triple("AI Assistant", Icons.Default.SmartToy, AppDestination.AI_ASSISTANT),
                    Triple("About", Icons.Default.Info, AppDestination.ABOUT),
                    Triple("Web View", Icons.Default.Language, AppDestination.WEB_VIEW)
                )

                navTabs.forEach { (label, icon, dest) ->
                    val isSelected = currentDestination == dest
                    FilterChip(
                        selected = isSelected,
                        onClick = { onNavigateDestination(dest) },
                        label = { Text(label, fontSize = 12.sp, fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal) },
                        leadingIcon = { Icon(icon, contentDescription = null, modifier = Modifier.size(14.dp)) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = CyanPrimary,
                            selectedLabelColor = DeepSpaceDark,
                            selectedLeadingIconColor = DeepSpaceDark,
                            containerColor = SpaceSurfaceVariant,
                            labelColor = TextSlate
                        )
                    )
                }
            }
        }
    }
}

// -------------------------------------------------------------
// 3. MOBILE COMPACT NAVIGATION
// -------------------------------------------------------------
@Composable
private fun MobileTopNavigation(
    currentDestination: AppDestination,
    canNavigateBack: Boolean,
    onNavigateBack: () -> Unit,
    onNavigateDestination: (AppDestination) -> Unit,
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
    onClearSearch: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(SpaceSurface.copy(alpha = 0.98f))
            .border(width = 0.5.dp, color = SpaceBorder)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                if (canNavigateBack) {
                    IconButton(
                        onClick = onNavigateBack,
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(SpaceSurfaceVariant)
                            .testTag("nav_back_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = CyanPrimary,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .border(1.5.dp, CyanPrimary, CircleShape)
                            .clickable { onNavigateDestination(AppDestination.HOME) }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.app_icon_bioscience_1793378142747),
                            contentDescription = "Logo",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }

                Column(modifier = Modifier.clickable { onNavigateDestination(AppDestination.HOME) }) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("BIOSCIENCE", fontWeight = FontWeight.Black, fontSize = 17.sp, color = TextWhite, letterSpacing = 1.sp)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("UNIVERSE", fontWeight = FontWeight.Bold, fontSize = 17.sp, color = CyanPrimary, letterSpacing = 1.sp)
                    }
                    Text("EXPLORE THE SCIENCE OF LIFE", fontSize = 9.sp, fontWeight = FontWeight.SemiBold, color = ElectricBlue, letterSpacing = 1.sp)
                }
            }

            FilledTonalIconButton(
                onClick = { onNavigateDestination(AppDestination.AI_ASSISTANT) },
                colors = IconButtonDefaults.filledTonalIconButtonColors(
                    containerColor = SpaceSurfaceVariant,
                    contentColor = CyanPrimary
                ),
                modifier = Modifier.size(38.dp)
            ) {
                Icon(Icons.Default.AutoAwesome, contentDescription = "AI Assistant", modifier = Modifier.size(20.dp))
            }
        }

        OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchQueryChanged,
            placeholder = { Text("Search genes, anatomy, diseases, CRISPR...", color = TextDim, fontSize = 13.sp) },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search", tint = CyanPrimary, modifier = Modifier.size(18.dp)) },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = onClearSearch) {
                        Icon(Icons.Default.Close, contentDescription = "Clear", tint = TextSlate, modifier = Modifier.size(16.dp))
                    }
                }
            },
            singleLine = true,
            shape = RoundedCornerShape(24.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = SpaceSurfaceVariant,
                unfocusedContainerColor = SpaceSurfaceVariant.copy(alpha = 0.6f),
                focusedBorderColor = CyanPrimary,
                unfocusedBorderColor = SpaceBorder,
                focusedTextColor = TextWhite,
                unfocusedTextColor = TextWhite
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 4.dp)
                .height(48.dp)
                .testTag("global_search_input")
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 12.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val navTabs = listOf(
                Triple("Home", Icons.Default.Home, AppDestination.HOME),
                Triple("12 Divisions", Icons.Default.Hub, AppDestination.DIVISIONS),
                Triple("Diseases", Icons.Default.MedicalServices, AppDestination.DISEASES),
                Triple("Tools", Icons.Default.Calculate, AppDestination.TOOLS),
                Triple("Research", Icons.Default.MenuBook, AppDestination.RESEARCH_LIBRARY),
                Triple("AI Research", Icons.Default.SmartToy, AppDestination.AI_ASSISTANT),
                Triple("About", Icons.Default.Info, AppDestination.ABOUT),
                Triple("Web View", Icons.Default.Language, AppDestination.WEB_VIEW)
            )

            navTabs.forEach { (label, icon, dest) ->
                val isSelected = currentDestination == dest
                FilterChip(
                    selected = isSelected,
                    onClick = { onNavigateDestination(dest) },
                    label = { Text(label, fontSize = 12.sp, fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal) },
                    leadingIcon = { Icon(icon, contentDescription = null, modifier = Modifier.size(14.dp)) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = CyanPrimary,
                        selectedLabelColor = DeepSpaceDark,
                        selectedLeadingIconColor = DeepSpaceDark,
                        containerColor = SpaceSurfaceVariant,
                        labelColor = TextSlate
                    )
                )
            }
        }
    }
}
