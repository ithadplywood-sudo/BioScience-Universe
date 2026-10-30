package com.example.data.model

data class ResearchPaper(
    val id: String,
    val title: String,
    val authors: String,
    val journal: String,
    val publicationDate: String,
    val category: String,
    val doi: String,
    val externalUrl: String,
    val abstractSummary: String,
    val keyFindings: List<String>,
    val methodology: String,
    val citationCount: String,
    val references: List<String>
)

data class BioDatabaseResource(
    val id: String,
    val name: String,
    val abbreviation: String,
    val organization: String,
    val websiteUrl: String,
    val description: String,
    val primaryDataType: String,
    val queryExample: String,
    val researchWorkflow: String
)

data class QuizQuestion(
    val id: String,
    val domain: String,
    val question: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String,
    val difficulty: String // Foundational, Intermediate, Advanced
)

data class CellGlossaryItem(
    val term: String,
    val phonetic: String,
    val category: String,
    val definition: String,
    val cellularRole: String,
    val biologicalExample: String
)

data class BioElement(
    val symbol: String,
    val name: String,
    val atomicNumber: Int,
    val bodyMassPercent: Double,
    val category: String, // Major Macro, Trace Mineral, Ultra-trace
    val physiologicalRole: String,
    val molecularExamples: String
)
