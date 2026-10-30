package com.example.data.model

enum class DiseaseCategory(val displayName: String) {
    INFECTIOUS("Infectious Diseases"),
    GENETIC("Genetic Disorders"),
    CARDIOVASCULAR("Cardiovascular Diseases"),
    NEUROLOGICAL("Neurological Diseases"),
    RESPIRATORY("Respiratory Diseases"),
    DIGESTIVE("Digestive Diseases"),
    ENDOCRINE("Endocrine Disorders"),
    AUTOIMMUNE("Autoimmune Diseases"),
    HEMATOLOGY("Blood Disorders"),
    DERMATOLOGY("Skin Diseases"),
    ONCOLOGY("Cancer & Oncology"),
    METABOLIC("Metabolic Disorders")
}

data class DiseaseEntry(
    val id: String,
    val name: String,
    val scientificName: String,
    val category: DiseaseCategory,
    val scientificOverview: String,
    val causesAndRiskFactors: List<String>,
    val biologicalMechanisms: String,
    val commonSymptoms: List<String>,
    val educationalDiagnosticMethods: List<String>,
    val preventionStrategies: List<String>,
    val evidenceBasedTreatments: List<String>,
    val researchReferences: List<String>,
    val publicationDate: String,
    val reviewDate: String
)
