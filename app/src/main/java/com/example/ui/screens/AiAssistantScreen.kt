package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.MedicalDisclaimerBanner
import com.example.ui.responsive.ResponsiveConfig
import com.example.ui.theme.*
import com.example.viewmodel.AiMessage
import com.example.viewmodel.BioScienceUiState
import com.example.viewmodel.BioScienceViewModel

@Composable
fun AiAssistantScreen(
    responsiveConfig: ResponsiveConfig,
    uiState: BioScienceUiState,
    viewModel: BioScienceViewModel,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()

    LaunchedEffect(uiState.aiMessages.size) {
        if (uiState.aiMessages.isNotEmpty()) {
            listState.animateScrollToItem(uiState.aiMessages.size - 1)
        }
    }

    val sampleQueries = listOf(
        "Explain CRISPR-Cas9 mechanism",
        "How does AlphaFold predict 3D structures?",
        "DNA Data Storage vs Solid State Drives",
        "Explain Cardiac action potential phases",
        "Hallmarks of Cancer & modern therapies",
        "Biomolecular logic gates with toehold displacement"
    )

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        if (responsiveConfig.isWide) {
            // DESKTOP & TABLET: 2-Column Responsive Interface with Sidebar
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .widthIn(max = responsiveConfig.contentMaxWidth)
                    .padding(horizontal = if (responsiveConfig.isDesktop) 28.dp else 20.dp, vertical = 14.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                // Left Column: Research Prompts & Model Status Sidebar
                Column(
                    modifier = Modifier
                        .width(if (responsiveConfig.isDesktop) 320.dp else 260.dp)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .border(1.dp, CyanPrimary.copy(alpha = 0.5f), RoundedCornerShape(16.dp)),
                        colors = CardDefaults.cardColors(containerColor = SpaceSurfaceCard),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Column(modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Surface(
                                    color = CyanPrimary,
                                    shape = CircleShape,
                                    modifier = Modifier.size(38.dp)
                                ) {
                                    Icon(Icons.Default.SmartToy, contentDescription = null, tint = DeepSpaceDark, modifier = Modifier.padding(8.dp))
                                }
                                Column {
                                    Text("AI Research Engine", fontWeight = FontWeight.Bold, color = TextWhite, fontSize = 15.sp)
                                    Text("Gemini Multi-Modal Logic", color = BioMint, fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
                                }
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Trained on peer-reviewed biological literature, clinical oncology trials, and genomic databases.",
                                color = TextSlate,
                                fontSize = 12.sp,
                                lineHeight = 17.sp
                            )
                        }
                    }

                    Text("CURATED RESEARCH QUERIES", color = CyanPrimary, fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)

                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        items(sampleQueries) { query ->
                            Surface(
                                color = SpaceSurfaceCard,
                                shape = RoundedCornerShape(10.dp),
                                border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(SpaceBorder)),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { viewModel.sendAiPrompt(query) }
                            ) {
                                Row(
                                    modifier = Modifier.padding(12.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(text = query, color = TextWhite, fontSize = 12.sp, modifier = Modifier.weight(1f))
                                    Icon(Icons.Default.ArrowForward, contentDescription = null, tint = CyanPrimary, modifier = Modifier.size(14.dp))
                                }
                            }
                        }
                    }

                    MedicalDisclaimerBanner(compact = true)
                }

                // Right Column: Spacious Chat Stream & Input Area
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    LazyColumn(
                        state = listState,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(14.dp),
                        contentPadding = PaddingValues(vertical = 12.dp)
                    ) {
                        items(uiState.aiMessages) { msg ->
                            AiMessageBubble(message = msg, isWide = true)
                        }

                        if (uiState.aiIsLoading) {
                            item {
                                Surface(
                                    color = SpaceSurfaceCard,
                                    shape = RoundedCornerShape(14.dp),
                                    border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(SpaceBorder)),
                                    modifier = Modifier.width(360.dp)
                                ) {
                                    Row(
                                        modifier = Modifier.padding(16.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                                    ) {
                                        CircularProgressIndicator(modifier = Modifier.size(16.dp), color = CyanPrimary, strokeWidth = 2.dp)
                                        Text("Synthesizing molecular & literature data...", color = TextSlate, fontSize = 12.sp)
                                    }
                                }
                            }
                        }
                    }

                    // Input Bar
                    Surface(
                        color = SpaceSurfaceCard,
                        shape = RoundedCornerShape(16.dp),
                        border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(SpaceBorder)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            OutlinedTextField(
                                value = uiState.aiInputText,
                                onValueChange = viewModel::onAiInputChanged,
                                placeholder = { Text("Ask scientific or biological questions...", fontSize = 13.sp) },
                                singleLine = false,
                                maxLines = 4,
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedContainerColor = SpaceSurfaceVariant,
                                    unfocusedContainerColor = SpaceSurfaceVariant,
                                    focusedBorderColor = CyanPrimary,
                                    focusedTextColor = TextWhite,
                                    unfocusedTextColor = TextWhite
                                ),
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier.weight(1f).testTag("ai_prompt_input")
                            )

                            Button(
                                onClick = { viewModel.sendAiPrompt(uiState.aiInputText) },
                                enabled = uiState.aiInputText.isNotBlank() && !uiState.aiIsLoading,
                                colors = ButtonDefaults.buttonColors(containerColor = CyanPrimary, contentColor = DeepSpaceDark),
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier.height(48.dp).testTag("send_ai_prompt_btn")
                            ) {
                                Icon(Icons.Default.Send, contentDescription = "Send", modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Ask AI", fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        } else {
            // MOBILE: Full-Width Chat Interface
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .testTag("ai_assistant_screen")
            ) {
                Box(modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)) {
                    MedicalDisclaimerBanner(compact = true)
                }

                LazyColumn(
                    state = listState,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(vertical = 12.dp)
                ) {
                    items(uiState.aiMessages) { message ->
                        AiMessageBubble(message = message, isWide = false)
                    }

                    if (uiState.aiIsLoading) {
                        item {
                            Surface(
                                color = SpaceSurfaceCard,
                                shape = RoundedCornerShape(14.dp),
                                border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(SpaceBorder))
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    CircularProgressIndicator(modifier = Modifier.size(16.dp), color = CyanPrimary, strokeWidth = 2.dp)
                                    Text("Querying scientific models...", color = TextSlate, fontSize = 12.sp)
                                }
                            }
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    sampleQueries.forEach { prompt ->
                        AssistChip(
                            onClick = { viewModel.sendAiPrompt(prompt) },
                            label = { Text(prompt, fontSize = 11.sp) },
                            colors = AssistChipDefaults.assistChipColors(containerColor = SpaceSurfaceCard, labelColor = CyanPrimary)
                        )
                    }
                }

                Surface(
                    color = SpaceSurface,
                    modifier = Modifier
                        .fillMaxWidth()
                        .navigationBarsPadding()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedTextField(
                            value = uiState.aiInputText,
                            onValueChange = viewModel::onAiInputChanged,
                            placeholder = { Text("Ask scientific questions...", fontSize = 13.sp) },
                            singleLine = false,
                            maxLines = 3,
                            shape = RoundedCornerShape(20.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = SpaceSurfaceVariant,
                                unfocusedContainerColor = SpaceSurfaceVariant,
                                focusedBorderColor = CyanPrimary,
                                focusedTextColor = TextWhite,
                                unfocusedTextColor = TextWhite
                            ),
                            modifier = Modifier.weight(1f).testTag("ai_prompt_input")
                        )

                        IconButton(
                            onClick = { viewModel.sendAiPrompt(uiState.aiInputText) },
                            enabled = uiState.aiInputText.isNotBlank() && !uiState.aiIsLoading,
                            modifier = Modifier
                                .size(44.dp)
                                .clip(CircleShape)
                                .background(if (uiState.aiInputText.isNotBlank()) CyanPrimary else SpaceSurfaceVariant)
                                .testTag("send_ai_prompt_btn")
                        ) {
                            Icon(Icons.Default.Send, contentDescription = "Send", tint = if (uiState.aiInputText.isNotBlank()) DeepSpaceDark else TextDim, modifier = Modifier.size(18.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun AiMessageBubble(
    message: AiMessage,
    isWide: Boolean
) {
    val isUser = message.sender == "user"

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start
    ) {
        Column(
            modifier = Modifier.widthIn(max = if (isWide) 600.dp else 320.dp),
            horizontalAlignment = if (isUser) Alignment.End else Alignment.Start
        ) {
            Surface(
                color = if (isUser) CyanPrimary else SpaceSurfaceCard,
                shape = RoundedCornerShape(
                    topStart = 16.dp,
                    topEnd = 16.dp,
                    bottomStart = if (isUser) 16.dp else 4.dp,
                    bottomEnd = if (isUser) 4.dp else 16.dp
                ),
                border = if (!isUser) CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(SpaceBorder)) else null
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = message.text,
                        color = if (isUser) DeepSpaceDark else TextWhite,
                        fontSize = 13.sp,
                        lineHeight = 20.sp,
                        fontWeight = if (isUser) FontWeight.Medium else FontWeight.Normal
                    )

                    if (message.references.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Divider(color = SpaceBorder, thickness = 0.5.dp)
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "CITATIONS & SCIENTIFIC SOURCES:",
                            color = CyanPrimary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 9.sp,
                            letterSpacing = 0.5.sp
                        )
                        message.references.forEach { ref ->
                            Text(text = "• $ref", color = TextSlate, fontSize = 10.sp)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = message.timestamp, color = TextDim, fontSize = 10.sp, modifier = Modifier.padding(horizontal = 4.dp))
        }
    }
}
