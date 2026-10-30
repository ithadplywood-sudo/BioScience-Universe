package com.example.data.model

data class DivisionTopic(
    val id: String,
    val title: String,
    val subtitle: String,
    val summary: String,
    val definition: String,
    val structure: String,
    val functions: List<String>,
    val mechanisms: String,
    val relatedConditions: List<String>,
    val researchStatus: String, // Established, Current Research, Experimental, Emerging
    val references: List<String>
)

data class ScientificDivision(
    val id: String,
    val number: Int,
    val title: String,
    val tagline: String,
    val overview: String,
    val iconName: String,
    val accentHex: Long,
    val keyConcepts: List<String>,
    val topics: List<DivisionTopic>
)
