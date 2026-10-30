package com.example.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.*
import com.example.data.repository.ScienceRepository
import com.example.ui.tools.BioCalculators
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

import com.example.ui.responsive.ViewportMode

enum class AppDestination {
    HOME,
    DIVISIONS,
    DIVISION_DETAIL,
    DISEASES,
    DISEASE_DETAIL,
    TOOLS,
    RESEARCH_LIBRARY,
    AI_ASSISTANT,
    ABOUT,
    WEB_VIEW
}

data class AiMessage(
    val id: String,
    val sender: String, // "user" or "assistant"
    val text: String,
    val timestamp: String,
    val references: List<String> = emptyList()
)

data class SearchResultItem(
    val id: String,
    val title: String,
    val subtitle: String,
    val type: String, // "Division", "Topic", "Disease", "Paper", "Database"
    val targetDestination: AppDestination,
    val targetId: String
)

data class BioScienceUiState(
    val currentDestination: AppDestination = AppDestination.HOME,
    val destinationStack: List<AppDestination> = listOf(AppDestination.HOME),
    val selectedDivisionId: String? = null,
    val selectedTopicId: String? = null,
    val selectedDiseaseId: String? = null,
    val globalSearchQuery: String = "",
    val searchResults: List<SearchResultItem> = emptyList(),
    val isSearchActive: Boolean = false,
    
    // Disease filters
    val selectedDiseaseCategory: DiseaseCategory? = null,
    
    // Tools
    val activeToolIndex: Int = 0,
    val dnaInput: String = "ATGCGATCGATCGTAGCTAGCTAGCATCG",
    val dnaResult: BioCalculators.DnaAnalysisResult = BioCalculators.analyzeDnaSequence("ATGCGATCGATCGTAGCTAGCTAGCATCG"),
    val punnettP1: String = "Aa",
    val punnettP2: String = "Aa",
    val punnettResult: BioCalculators.PunnettResult = BioCalculators.calculateMonohybridCross("Aa", "Aa"),
    val unitConverterInputBp: String = "1000",
    val unitConverterResultNm: Double = 340.0,
    val glossarySearch: String = "",
    
    // Quiz
    val quizCurrentIndex: Int = 0,
    val quizSelectedOption: Int? = null,
    val quizIsAnswered: Boolean = false,
    val quizScore: Int = 0,
    
    // AI Assistant
    val aiMessages: List<AiMessage> = listOf(
        AiMessage(
            id = "welcome",
            sender = "assistant",
            text = "Welcome to BioScience Universe AI Assistant. I can assist you with molecular biology, genetics, biocomputing, human pathophysiology, and biotechnology workflows.\n\nNote: I provide educational and scientific research explanations only, not medical diagnosis or treatment.",
            timestamp = "System Active",
            references = listOf("Nature Structural Biology", "NCBI Entrez", "PubMed Central")
        )
    ),
    val aiIsLoading: Boolean = false,
    val aiInputText: String = "",
    val viewportMode: ViewportMode = ViewportMode.AUTO
)

class BioScienceViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(BioScienceUiState())
    val uiState: StateFlow<BioScienceUiState> = _uiState.asStateFlow()

    fun setViewportMode(mode: ViewportMode) {
        _uiState.value = _uiState.value.copy(viewportMode = mode)
    }

    fun navigateTo(destination: AppDestination, targetId: String? = null) {
        val currentStack = _uiState.value.destinationStack.toMutableList()
        if (currentStack.lastOrNull() != destination) {
            currentStack.add(destination)
        }
        _uiState.value = _uiState.value.copy(
            currentDestination = destination,
            destinationStack = currentStack,
            selectedDivisionId = if (destination == AppDestination.DIVISION_DETAIL) targetId ?: _uiState.value.selectedDivisionId else _uiState.value.selectedDivisionId,
            selectedDiseaseId = if (destination == AppDestination.DISEASE_DETAIL) targetId ?: _uiState.value.selectedDiseaseId else _uiState.value.selectedDiseaseId,
            isSearchActive = false
        )
    }

    fun navigateBack(): Boolean {
        val currentStack = _uiState.value.destinationStack.toMutableList()
        if (currentStack.size > 1) {
            currentStack.removeAt(currentStack.size - 1)
            val previousDestination = currentStack.last()
            _uiState.value = _uiState.value.copy(
                currentDestination = previousDestination,
                destinationStack = currentStack
            )
            return true
        }
        return false
    }

    fun selectDivision(divisionId: String) {
        val division = ScienceRepository.divisions.find { it.id == divisionId }
        val firstTopic = division?.topics?.firstOrNull()?.id
        _uiState.value = _uiState.value.copy(
            selectedDivisionId = divisionId,
            selectedTopicId = firstTopic
        )
        navigateTo(AppDestination.DIVISION_DETAIL, divisionId)
    }

    fun selectTopic(topicId: String) {
        _uiState.value = _uiState.value.copy(selectedTopicId = topicId)
    }

    fun selectDisease(diseaseId: String) {
        _uiState.value = _uiState.value.copy(selectedDiseaseId = diseaseId)
        navigateTo(AppDestination.DISEASE_DETAIL, diseaseId)
    }

    fun filterDiseaseCategory(category: DiseaseCategory?) {
        _uiState.value = _uiState.value.copy(selectedDiseaseCategory = category)
    }

    fun onSearchQueryChanged(query: String) {
        if (query.isBlank()) {
            _uiState.value = _uiState.value.copy(
                globalSearchQuery = query,
                searchResults = emptyList(),
                isSearchActive = false
            )
            return
        }

        val results = mutableListOf<SearchResultItem>()
        val q = query.lowercase().trim()

        // Search divisions
        ScienceRepository.divisions.forEach { div ->
            if (div.title.lowercase().contains(q) || div.overview.lowercase().contains(q) || div.keyConcepts.any { it.lowercase().contains(q) }) {
                results.add(
                    SearchResultItem(
                        id = "div_${div.id}",
                        title = div.title,
                        subtitle = "Division ${div.number} • ${div.tagline}",
                        type = "Division",
                        targetDestination = AppDestination.DIVISION_DETAIL,
                        targetId = div.id
                    )
                )
            }
            div.topics.forEach { topic ->
                if (topic.title.lowercase().contains(q) || topic.summary.lowercase().contains(q) || topic.definition.lowercase().contains(q)) {
                    results.add(
                        SearchResultItem(
                            id = "topic_${topic.id}",
                            title = topic.title,
                            subtitle = "${div.title} • Topic",
                            type = "Topic",
                            targetDestination = AppDestination.DIVISION_DETAIL,
                            targetId = div.id
                        )
                    )
                }
            }
        }

        // Search diseases
        ScienceRepository.diseases.forEach { dis ->
            if (dis.name.lowercase().contains(q) || dis.scientificName.lowercase().contains(q) || dis.biologicalMechanisms.lowercase().contains(q) || dis.commonSymptoms.any { it.lowercase().contains(q) }) {
                results.add(
                    SearchResultItem(
                        id = "dis_${dis.id}",
                        title = dis.name,
                        subtitle = "${dis.category.displayName} • ${dis.scientificName}",
                        type = "Disease",
                        targetDestination = AppDestination.DISEASE_DETAIL,
                        targetId = dis.id
                    )
                )
            }
        }

        // Search research papers
        ScienceRepository.researchPapers.forEach { paper ->
            if (paper.title.lowercase().contains(q) || paper.authors.lowercase().contains(q) || paper.abstractSummary.lowercase().contains(q)) {
                results.add(
                    SearchResultItem(
                        id = "paper_${paper.id}",
                        title = paper.title,
                        subtitle = "${paper.journal} • ${paper.category}",
                        type = "Paper",
                        targetDestination = AppDestination.RESEARCH_LIBRARY,
                        targetId = paper.id
                    )
                )
            }
        }

        // Search databases
        ScienceRepository.databases.forEach { db ->
            if (db.name.lowercase().contains(q) || db.abbreviation.lowercase().contains(q) || db.description.lowercase().contains(q)) {
                results.add(
                    SearchResultItem(
                        id = "db_${db.id}",
                        title = "${db.name} (${db.abbreviation})",
                        subtitle = db.primaryDataType,
                        type = "Database",
                        targetDestination = AppDestination.RESEARCH_LIBRARY,
                        targetId = db.id
                    )
                )
            }
        }

        _uiState.value = _uiState.value.copy(
            globalSearchQuery = query,
            searchResults = results.take(15),
            isSearchActive = true
        )
    }

    fun clearSearch() {
        _uiState.value = _uiState.value.copy(
            globalSearchQuery = "",
            searchResults = emptyList(),
            isSearchActive = false
        )
    }

    // Tools
    fun setActiveTool(index: Int) {
        _uiState.value = _uiState.value.copy(activeToolIndex = index)
    }

    fun onDnaInputChanged(sequence: String) {
        val result = BioCalculators.analyzeDnaSequence(sequence)
        _uiState.value = _uiState.value.copy(dnaInput = sequence, dnaResult = result)
    }

    fun onPunnettParentsChanged(p1: String, p2: String) {
        val result = BioCalculators.calculateMonohybridCross(p1, p2)
        _uiState.value = _uiState.value.copy(punnettP1 = p1, punnettP2 = p2, punnettResult = result)
    }

    fun onUnitBpChanged(bpStr: String) {
        val bp = bpStr.toLongOrNull() ?: 0L
        val nm = BioCalculators.basePairsToNanometers(bp)
        _uiState.value = _uiState.value.copy(unitConverterInputBp = bpStr, unitConverterResultNm = nm)
    }

    fun onGlossarySearchChanged(term: String) {
        _uiState.value = _uiState.value.copy(glossarySearch = term)
    }

    // Quiz
    fun selectQuizOption(index: Int) {
        if (!_uiState.value.quizIsAnswered) {
            _uiState.value = _uiState.value.copy(quizSelectedOption = index)
        }
    }

    fun submitQuizAnswer() {
        val currentQ = ScienceRepository.quizzes.getOrNull(_uiState.value.quizCurrentIndex) ?: return
        val isCorrect = _uiState.value.quizSelectedOption == currentQ.correctIndex
        val newScore = if (isCorrect) _uiState.value.quizScore + 1 else _uiState.value.quizScore
        _uiState.value = _uiState.value.copy(
            quizIsAnswered = true,
            quizScore = newScore
        )
    }

    fun nextQuizQuestion() {
        val nextIdx = (_uiState.value.quizCurrentIndex + 1) % ScienceRepository.quizzes.size
        _uiState.value = _uiState.value.copy(
            quizCurrentIndex = nextIdx,
            quizSelectedOption = null,
            quizIsAnswered = false
        )
    }

    fun resetQuiz() {
        _uiState.value = _uiState.value.copy(
            quizCurrentIndex = 0,
            quizSelectedOption = null,
            quizIsAnswered = false,
            quizScore = 0
        )
    }

    // AI Assistant
    fun onAiInputChanged(text: String) {
        _uiState.value = _uiState.value.copy(aiInputText = text)
    }

    fun sendAiPrompt(prompt: String) {
        val cleanPrompt = prompt.trim()
        if (cleanPrompt.isBlank()) return

        val userMsg = AiMessage(
            id = "u_${System.currentTimeMillis()}",
            sender = "user",
            text = cleanPrompt,
            timestamp = "Just now"
        )

        val updatedList = _uiState.value.aiMessages + userMsg
        _uiState.value = _uiState.value.copy(
            aiMessages = updatedList,
            aiInputText = "",
            aiIsLoading = true
        )

        viewModelScope.launch {
            delay(1200) // Realistic processing delay
            val aiResponse = generateScientificAnswer(cleanPrompt)
            val assistantMsg = AiMessage(
                id = "a_${System.currentTimeMillis()}",
                sender = "assistant",
                text = aiResponse.first,
                timestamp = "Just now",
                references = aiResponse.second
            )
            _uiState.value = _uiState.value.copy(
                aiMessages = _uiState.value.aiMessages + assistantMsg,
                aiIsLoading = false
            )
        }
    }

    private fun generateScientificAnswer(query: String): Pair<String, List<String>> {
        val lower = query.lowercase()
        return when {
            lower.contains("crispr") -> {
                Pair(
                    "**CRISPR-Cas9 Precision Genome Editing**\n\n" +
                    "• **Biological Mechanism:** The Cas9 endonuclease is directed by a synthetic single-guide RNA (sgRNA). It binds an adjacent Protospacer Adjacent Motif (PAM: 5'-NGG-3'), unwinds target duplex DNA, and induces double-strand breaks (DSBs) 3 bp upstream.\n\n" +
                    "• **Repair Pathways:** Cells repair DSBs via Non-Homologous End Joining (NHEJ) introducing indels for gene knockout, or Homology-Directed Repair (HDR) in the presence of a donor template for precise gene replacement.\n\n" +
                    "• **Clinical Applications:** Exa-cel (Casgevy) for sickle cell disease and beta-thalassemia, in vivo base editing for hypercholesterolemia (PCSK9), and therapeutic viral gene disruption.",
                    listOf("Doudna & Charpentier, Science 2012", "Nature Biotechnology 2024", "NIH Somatic Cell Gene Editing Consortium")
                )
            }
            lower.contains("alphafold") || lower.contains("protein fold") -> {
                Pair(
                    "**AlphaFold & AI-Driven Structural Biology**\n\n" +
                    "• **Core Architecture:** AlphaFold replaces hand-crafted energy potentials with a transformer network using Evoformer blocks to exchange spatial and co-evolutionary constraints from Multiple Sequence Alignments (MSAs).\n\n" +
                    "• **3D Invariant Point Attention (IPA):** Directs rotation- and translation-equivariant predictions of amino acid coordinate frames, predicting coordinates down to ~0.96 Å RMSD.\n\n" +
                    "• **Current Impact:** Over 200 million public structures in the AlphaFold Database (EMBL-EBI), revolutionizing orphan receptor drug discovery, cryo-EM map fitting, and generative de novo protein design (RFdiffusion).",
                    listOf("Jumper et al., Nature 2021", "EMBL-EBI AlphaFold DB", "Watson et al., Nature 2023")
                )
            }
            lower.contains("biocomputing") || lower.contains("dna storage") || lower.contains("dna comput") -> {
                Pair(
                    "**Biocomputing & DNA Information Processing**\n\n" +
                    "• **DNA Data Storage:** Digital binary bits are converted to quaternary nucleotides (A, C, G, T) with fountain erasure codes, achieving a theoretical limit of 215 PB per gram with millennia-scale chemical durability.\n\n" +
                    "• **Molecular Logic Gates:** Toehold-mediated strand displacement cascades execute Boolean AND, OR, and NOT operations enzymatically without electrical wiring.\n\n" +
                    "• **Living Circuits:** Synthetic biology engineers transcriptional repressor cascades into bacterial plasmids to create biological memory units (flip-flops) and edge-detection cellular cameras.",
                    listOf("Erlich & Zielinski, Science 2017", "Church et al., Science 2012", "Zhang & Seelig, Nat Chem 2011")
                )
            }
            lower.contains("heart") || lower.contains("cardio") || lower.contains("circulation") -> {
                Pair(
                    "**Cardiovascular Electrophysiology & Hemodynamics**\n\n" +
                    "• **Cardiac Action Potential:** Initiated in the Sinoatrial (SA) node pacemaker cells (Phase 4 funny current If), traversing the AV node (120ms physiological delay), bundle of His, and Purkinje fibers.\n\n" +
                    "• **Excitation-Contraction Coupling:** Calcium influx through L-type voltage-gated Ca2+ channels (Phase 2 plateau) triggers massive calcium-induced calcium release (CICR) from the sarcoplasmic reticulum via ryanodine receptors (RyR2), binding troponin C to execute systolic contraction.\n\n" +
                    "• **Educational Notice:** For symptoms such as chest pressure, diaphoresis, or palpitations, immediate consultation with emergency medical personnel is essential.",
                    listOf("Guyton and Hall Textbook of Medical Physiology", "Circulation Research 2024")
                )
            }
            lower.contains("cancer") || lower.contains("tumor") || lower.contains("oncology") -> {
                Pair(
                    "**Molecular Oncology & Hallmarks of Cancer**\n\n" +
                    "• **Biological Drivers:** Hanahan and Weinberg's hallmarks include sustaining proliferative signaling, evading growth suppressors (TP53, RB1), resisting cell death, enabling replicative immortality (telomerase reactivation), inducing angiogenesis, and activating invasion and metastasis.\n\n" +
                    "• **Modern Therapeutics:** Immune checkpoint blockade (anti-PD-1/PD-L1, anti-CTLA-4), targeted tyrosine kinase inhibitors (osimertinib, sotorasib), antibody-drug conjugates (ADCs), and autologous CAR-T cell cell therapies.\n\n" +
                    "• **Educational Notice:** Individual oncology diagnoses and therapies require dedicated multidisciplinary clinical evaluation by licensed oncology specialists.",
                    listOf("Hanahan D, Cancer Discovery 2022", "NCCN Clinical Practice Guidelines", "Lancet Oncology 2024")
                )
            }
            else -> {
                Pair(
                    "**Scientific Analysis: ${query.take(45)}**\n\n" +
                    "• **Overview:** In biological science, this query interfaces with fundamental mechanisms governing molecular interactions, cellular physiology, and bioenergetic equilibria.\n\n" +
                    "• **Molecular & Evolutionary Context:** Cellular machinery is conserved across phylogenetic domains, utilizing nucleic acid instructions, enzymatic biocatalysts, and compartmentalized lipid bilayer organelles to sustain life.\n\n" +
                    "• **Research Status:** Subject to ongoing peer-reviewed scientific investigation in academic repositories such as PubMed Central, NCBI Entrez, and RCSB PDB.\n\n" +
                    "• **Educational Notice:** This analysis is educational and should not be used as clinical or health advice.",
                    listOf("National Center for Biotechnology Information (NCBI)", "PubMed Central", "UniProt Knowledgebase")
                )
            }
        }
    }
}
