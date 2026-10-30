package com.example.ui.tools

import java.util.Locale

object BioCalculators {

    // Codon table mapping
    private val codonTable = mapOf(
        "UUU" to "Phe", "UUC" to "Phe", "UUA" to "Leu", "UUG" to "Leu",
        "CUU" to "Leu", "CUC" to "Leu", "CUA" to "Leu", "CUG" to "Leu",
        "AUU" to "Ile", "AUC" to "Ile", "AUA" to "Ile", "AUG" to "Met (Start)",
        "GUU" to "Val", "GUC" to "Val", "GUA" to "Val", "GUG" to "Val",
        "UCU" to "Ser", "UCC" to "Ser", "UCA" to "Ser", "UCG" to "Ser",
        "CCU" to "Pro", "CCC" to "Pro", "CCA" to "Pro", "CCG" to "Pro",
        "ACU" to "Thr", "ACC" to "Thr", "ACA" to "Thr", "ACG" to "Thr",
        "GCU" to "Ala", "GCC" to "Ala", "GCA" to "Ala", "GCG" to "Ala",
        "UAU" to "Tyr", "UAC" to "Tyr", "UAA" to "STOP", "UAG" to "STOP",
        "CAU" to "His", "CAC" to "His", "CAA" to "Gln", "CAG" to "Gln",
        "AAU" to "Asn", "AAC" to "Asn", "AAA" to "Lys", "AAG" to "Lys",
        "GAU" to "Asp", "GAC" to "Asp", "GAA" to "Glu", "GAG" to "Glu",
        "UGU" to "Cys", "UGC" to "Cys", "UGA" to "STOP", "UGG" to "Trp",
        "CGU" to "Arg", "CGC" to "Arg", "CGA" to "Arg", "CGG" to "Arg",
        "AGU" to "Ser", "AGC" to "Ser", "AGA" to "Arg", "AGG" to "Arg",
        "GGU" to "Gly", "GGC" to "Gly", "GGA" to "Gly", "GGG" to "Gly"
    )

    data class DnaAnalysisResult(
        val original: String,
        val complement: String,
        val reverseComplement: String,
        val mrna: String,
        val length: Int,
        val gcContentPercent: Double,
        val molecularWeightDa: Double,
        val proteinTranslation: List<String>,
        val error: String? = null
    )

    fun analyzeDnaSequence(rawSequence: String): DnaAnalysisResult {
        val clean = rawSequence.uppercase(Locale.ROOT).replace(Regex("[^A-Z]"), "")
        if (clean.isEmpty()) {
            return DnaAnalysisResult(
                original = "",
                complement = "",
                reverseComplement = "",
                mrna = "",
                length = 0,
                gcContentPercent = 0.0,
                molecularWeightDa = 0.0,
                proteinTranslation = emptyList(),
                error = "Enter a valid nucleotide sequence (A, T, G, C)"
            )
        }

        val invalidChars = clean.filter { it !in "ATGCN" }
        if (invalidChars.isNotEmpty()) {
            return DnaAnalysisResult(
                original = clean,
                complement = "",
                reverseComplement = "",
                mrna = "",
                length = clean.length,
                gcContentPercent = 0.0,
                molecularWeightDa = 0.0,
                proteinTranslation = emptyList(),
                error = "Sequence contains invalid nucleotide characters: $invalidChars"
            )
        }

        val compBuilder = StringBuilder()
        var gcCount = 0

        for (c in clean) {
            when (c) {
                'A' -> compBuilder.append('T')
                'T' -> compBuilder.append('A')
                'G' -> { compBuilder.append('C'); gcCount++ }
                'C' -> { compBuilder.append('G'); gcCount++ }
                else -> compBuilder.append('N')
            }
        }

        val complement = compBuilder.toString()
        val reverseComplement = complement.reversed()
        val mrna = clean.replace('T', 'U')

        val gcPercent = if (clean.isNotEmpty()) (gcCount.toDouble() / clean.length) * 100.0 else 0.0

        // Approximate average single-stranded DNA nucleotide MW ~ 330 Da, minus water for phosphodiester bond
        val mwDa = if (clean.isNotEmpty()) (clean.length * 303.7) + 79.0 else 0.0

        // Translate mRNA into codons
        val peptides = mutableListOf<String>()
        val mrnaNoGaps = mrna.replace("N", "")
        for (i in 0 until (mrnaNoGaps.length - 2) step 3) {
            val codon = mrnaNoGaps.substring(i, i + 3)
            val aa = codonTable[codon] ?: "Unknown"
            peptides.add("$codon -> $aa")
            if (aa == "STOP") break
        }

        return DnaAnalysisResult(
            original = clean,
            complement = complement,
            reverseComplement = reverseComplement,
            mrna = mrna,
            length = clean.length,
            gcContentPercent = (gcPercent * 10.0).toInt() / 10.0,
            molecularWeightDa = (mwDa * 10.0).toInt() / 10.0,
            proteinTranslation = peptides
        )
    }

    // Punnett square calculation
    data class PunnettResult(
        val p1Alleles: Pair<Char, Char>,
        val p2Alleles: Pair<Char, Char>,
        val grid: List<List<String>>,
        val genotypePercentages: Map<String, Double>,
        val phenotypePercentages: Map<String, Double>
    )

    fun calculateMonohybridCross(p1: String, p2: String): PunnettResult {
        val a1 = p1.getOrNull(0) ?: 'A'
        val a2 = p1.getOrNull(1) ?: 'a'
        val b1 = p2.getOrNull(0) ?: 'A'
        val b2 = p2.getOrNull(1) ?: 'a'

        fun makeGenotype(g1: Char, g2: Char): String {
            return if (g1.isUpperCase() && g2.isLowerCase()) "$g1$g2"
            else if (g2.isUpperCase() && g1.isLowerCase()) "$g2$g1"
            else if (g1 <= g2) "$g1$g2" else "$g2$g1"
        }

        val r1c1 = makeGenotype(a1, b1)
        val r1c2 = makeGenotype(a1, b2)
        val r2c1 = makeGenotype(a2, b1)
        val r2c2 = makeGenotype(a2, b2)

        val grid = listOf(
            listOf(r1c1, r1c2),
            listOf(r2c1, r2c2)
        )

        val allOffspring = listOf(r1c1, r1c2, r2c1, r2c2)
        val genoCounts = allOffspring.groupingBy { it }.eachCount()
        val genoMap = genoCounts.mapValues { (it.value / 4.0) * 100.0 }

        // Phenotype: has at least one uppercase letter = Dominant
        val domCount = allOffspring.count { it.any { c -> c.isUpperCase() } }
        val recCount = 4 - domCount

        val phenoMap = mapOf(
            "Dominant Phenotype" to (domCount / 4.0) * 100.0,
            "Recessive Phenotype" to (recCount / 4.0) * 100.0
        )

        return PunnettResult(
            p1Alleles = Pair(a1, a2),
            p2Alleles = Pair(b1, b2),
            grid = grid,
            genotypePercentages = genoMap,
            phenotypePercentages = phenoMap
        )
    }

    // Physical length in nanometers from base pairs (B-form DNA has 0.34 nm pitch per bp)
    fun basePairsToNanometers(bp: Long): Double = bp * 0.34

    // Molecular weight in Da to physical mass per single molecule in picograms
    fun daltonsToGrams(da: Double): Double = da * 1.66053906660e-24
}
