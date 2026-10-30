package com.example.data.repository

import com.example.data.model.*

object ScienceRepository {

    val divisions: List<ScientificDivision> = listOf(
        ScientificDivision(
            id = "anatomy",
            number = 1,
            title = "Human Anatomy & Physiology",
            tagline = "The Architecture & Dynamic Systems of the Human Organism",
            overview = "Explore the hierarchical structural organization of the human body, from cellular bioenergetics and histopathology to coordinated organ systems maintaining homeostatic equilibrium.",
            iconName = "anatomy",
            accentHex = 0xFF00F2FE,
            keyConcepts = listOf("Homeostasis", "Organ Systems", "Neurotransmission", "Hemodynamics", "Cellular Respiration"),
            topics = listOf(
                DivisionTopic(
                    id = "nervous_system",
                    title = "Nervous System & Neural Networks",
                    subtitle = "Action Potentials, Synaptic Transmission & Central Processing",
                    summary = "The central and peripheral nervous system directs sensory integration, voluntary motor output, autonomic regulation, and cognitive processing.",
                    definition = "The complex electrochemical communication network composed of the brain, spinal cord, and peripheral nerves utilizing action potentials to transmit information.",
                    structure = "Composed of neurons (soma, dendrites, axon, axon terminal) surrounded by glial cells (astrocytes, oligodendrocytes, microglia, Schwann cells) forming white and gray matter structures.",
                    functions = listOf(
                        "Generation and saltatory propagation of action potentials along myelinated axons",
                        "Neurotransmitter exocytosis at chemical synapses via voltage-gated Ca2+ channels",
                        "Integration of afferent sensory signals in thalamic and cortical circuits",
                        "Autonomic maintenance of heart rate, respiratory drive, and visceral motor tone"
                    ),
                    mechanisms = "Resting membrane potential (-70 mV maintained by Na+/K+-ATPase) undergoes rapid depolarization upon threshold stimulation (-55 mV), triggering rapid influx of Na+ through voltage-gated sodium channels, followed by delayed K+ efflux for repolarization.",
                    relatedConditions = listOf("Multiple Sclerosis", "Parkinson's Disease", "Amyotrophic Lateral Sclerosis", "Alzheimer's Disease"),
                    researchStatus = "Established & Expanding with Neural Interface Technologies",
                    references = listOf(
                        "Kandel ER, et al. Principles of Neural Science, 6th Ed.",
                        "Purves D, et al. Neuroscience, 7th Ed."
                    )
                ),
                DivisionTopic(
                    id = "circulatory_system",
                    title = "Cardiovascular & Hemodynamic Dynamics",
                    subtitle = "Coronary Circulation, Microvascular Exchange & Myocardial Electrophysiology",
                    summary = "A closed vascular circuit powered by the four-chambered heart that transports oxygen, nutrients, hormones, and metabolites to cellular beds.",
                    definition = "The organ system containing the heart, systemic arteries, capillary exchange networks, and systemic venous return system.",
                    structure = "Muscular myocardium enveloped by epicardium and endocardium, subdivided into atria and ventricles separated by atrioventricular and semilunar valves.",
                    functions = listOf(
                        "Pulsatile distribution of oxygenated blood at systemic pressures (120/80 mmHg)",
                        "Capillary fluid and gas transudation governed by Starling forces",
                        "Thermoregulation via precapillary sphincter vasoconstriction and vasodilation",
                        "Humoral distribution of endocrine signals and immunological surveillance cells"
                    ),
                    mechanisms = "Cardiac action potentials originate in the sinoatrial node, propagate through the AV node (introducing an intrinsic 120ms delay), pass through the bundle of His and Purkinje fibers to activate ventricular syncytium contraction.",
                    relatedConditions = listOf("Coronary Artery Disease", "Myocardial Infarction", "Arrhythmia", "Hypertension"),
                    researchStatus = "Established Medical Science",
                    references = listOf(
                        "Guyton and Hall Textbook of Medical Physiology, 14th Ed.",
                        "Braunwald's Heart Disease: A Textbook of Cardiovascular Medicine"
                    )
                ),
                DivisionTopic(
                    id = "respiratory_system",
                    title = "Respiratory System & Gas Exchange",
                    subtitle = "Alveolar Mechanics, Hemoglobin Saturation & Acid-Base Balance",
                    summary = "Facilitates atmospheric oxygen uptake and metabolic carbon dioxide elimination across approximately 300 million micro-alveolar interfaces.",
                    definition = "The conductive airways and respiratory bronchioles culminating in alveolar sacs specialized for diffusion across the thin respiratory membrane.",
                    structure = "Upper airways (nasal cavity, pharynx, larynx), conducting zone (trachea, bronchi, bronchioles), and respiratory zone (alveolar ducts, alveoli lined with Type I and surfactant-secreting Type II pneumocytes).",
                    functions = listOf(
                        "External respiration across the 0.5 μm alveolar-capillary blood-gas barrier",
                        "Systemic pH maintenance via renal-respiratory bicarbonate buffering equilibrium",
                        "Mucociliary escalator clearance of inhaled particulate matter"
                    ),
                    mechanisms = "Passive diffusion governed by Fick's Law where partial pressure gradients drive O2 into pulmonary capillaries and CO2 into alveolar spaces for exhalation.",
                    relatedConditions = listOf("COPD", "Asthma", "Acute Respiratory Distress Syndrome (ARDS)", "Pulmonary Fibrosis"),
                    researchStatus = "Established Medical Science",
                    references = listOf("West JB. Respiratory Physiology: The Essentials, 11th Ed.")
                )
            )
        ),
        ScientificDivision(
            id = "diseases",
            number = 2,
            title = "Diseases & Medical Science",
            tagline = "Pathophysiology, Etiology, Diagnostics & Evidence-Based Therapeutics",
            overview = "A rigorous, educational encyclopedia analyzing disease mechanisms, molecular etiology, diagnostic modalities, and evidence-based clinical interventions.",
            iconName = "diseases",
            accentHex = 0xFFF43F5E,
            keyConcepts = listOf("Pathophysiology", "Molecular Etiology", "Biomarkers", "Histopathology", "Evidence-Based Medicine"),
            topics = listOf(
                DivisionTopic(
                    id = "infectious_pathogenesis",
                    title = "Viral & Bacterial Pathogenesis",
                    subtitle = "Cellular Tropism, Virulence Factors & Host Immune Evasion",
                    summary = "How exogenous microbes penetrate anatomical barriers, subvert innate immune signaling, and cause tissue damage.",
                    definition = "The molecular process by which biological pathogens establish colonization, replication, and tissue destruction in a host organism.",
                    structure = "Encompasses viral capsids/envelopes (e.g. Spike glycoproteins), bacterial cell walls (peptidoglycan, lipopolysaccharide), and microbial secretion systems (Type III secretion needles).",
                    functions = listOf(
                        "Receptor-mediated endocytosis and genomic uncoating",
                        "Endotoxin and exotoxin secretion disrupting eukaryotic cellular machinery",
                        "Antigenic variation avoiding antibody-mediated neutralization"
                    ),
                    mechanisms = "Pathogens attach to specific host membrane receptors (e.g., ACE2 for SARS-CoV-2, CD4 for HIV) using high-affinity ligands, triggering membrane fusion or endocytosis.",
                    relatedConditions = listOf("Sepsis", "Viral Pneumonia", "Tuberculosis", "Meningitis"),
                    researchStatus = "Active Frontier Research",
                    references = listOf(
                        "Mandell, Douglas, and Bennett's Principles and Practice of Infectious Diseases, 9th Ed.",
                        "WHO Global Health Observatory"
                    )
                )
            )
        ),
        ScientificDivision(
            id = "genetics",
            number = 3,
            title = "Molecular Biology & Genetics",
            tagline = "The Central Dogma, Chromosomal Architecture & Epigenetic Regulation",
            overview = "Explore how hereditary information encoded in deoxyribonucleic acid is replicated, transcribed into messenger RNA, translated into functional polypeptide proteins, and modulated by epigenetic mechanisms.",
            iconName = "genetics",
            accentHex = 0xFF38BDF8,
            keyConcepts = listOf("Central Dogma", "DNA Replication", "Transcription & Translation", "Epigenetics", "CRISPR-Cas9"),
            topics = listOf(
                DivisionTopic(
                    id = "central_dogma",
                    title = "Transcription & Polypeptide Translation",
                    subtitle = "RNA Polymerase Dynamics, Spliceosomes & Ribosomal Ribozymes",
                    summary = "The central flow of biological genetic information from genomic DNA to functional catalytic proteins.",
                    definition = "The universal biological paradigm: DNA is transcribed by RNA Polymerase II into pre-mRNA, processed by spliceosomes, and translated by tRNA-ribosome complexes into peptide chains.",
                    structure = "Consists of 64 triplet codons, transfer RNAs with anticodon loops charging specific amino acids via aminoacyl-tRNA synthetases, and 70S/80S ribosomes.",
                    functions = listOf(
                        "Precise template-directed phosphodiester polymerization during transcription",
                        "Intron excision and alternative exon splicing expanding proteomic diversity",
                        "Ribosomal peptidyl transferase catalysis forming covalent peptide bonds"
                    ),
                    mechanisms = "Promoter recognition by transcription factor complexes allows RNA Polymerase to unwind the double helix, synthesizing mRNA 5' to 3'. Ribosomes bind the 5' m7G cap and scan for AUG start codons.",
                    relatedConditions = listOf("Beta-Thalassemia", "Sickle Cell Disease", "Cystic Fibrosis", "Duchenne Muscular Dystrophy"),
                    researchStatus = "Established Foundation & Active Synthetic Expansion",
                    references = listOf("Alberts B, et al. Molecular Biology of the Cell, 7th Ed.")
                )
            )
        ),
        ScientificDivision(
            id = "biotech",
            number = 4,
            title = "Biotechnology & Synthetic Biology",
            tagline = "Recombinant DNA, Biomanufacturing & Cellular Reprogramming",
            overview = "Harnessing living organisms, enzymatic tools, and engineered biocatalysts to produce life-saving therapeutics, synthetic fuels, biomaterials, and regenerative tissues.",
            iconName = "biotech",
            accentHex = 0xFF10B981,
            keyConcepts = listOf("Recombinant DNA", "Synthetic Biology", "Biopharmaceuticals", "CRISPR Engineering", "Induced Pluripotent Stem Cells"),
            topics = listOf(
                DivisionTopic(
                    id = "crispr_cas9",
                    title = "Targeted CRISPR-Cas Genome Editing",
                    subtitle = "Guide RNA Design, PAM Recognition & Double-Strand Break Repair",
                    summary = "Adaptive bacterial immune systems repurposed into programmable molecular scissors for precise eukaryotic genetic modification.",
                    definition = "Clustered Regularly Interspaced Short Palindromic Repeats and Cas9 endonuclease system directing RNA-guided double-strand DNA cleavage.",
                    structure = "Cas9 enzyme paired with a single-guide RNA (sgRNA) containing a 20-nucleotide target spacer complementary to genomic DNA adjacent to an NGG Protospacer Adjacent Motif (PAM).",
                    functions = listOf(
                        "Specific Watson-Crick base-pairing genomic locus targeting",
                        "Blunt-ended double-strand DNA cleavage via HNH and RuvC nuclease domains",
                        "Facilitation of non-homologous end joining (NHEJ) or homology-directed repair (HDR)"
                    ),
                    mechanisms = "Cas9 interrogates genomic DNA for PAM motifs, unwinds the local duplex, pairs sgRNA with the target strand to form an R-loop, and triggers conformational changes executing dual-strand hydrolysis.",
                    relatedConditions = listOf("Sickle Cell Disease (Casgevy therapy)", "Transthyretin Amyloidosis", "Congenital Amaurosis"),
                    researchStatus = "Active Clinical Translation & Next-Gen Prime/Base Editing",
                    references = listOf(
                        "Doudna JA, Charpentier E. The new frontier of genome engineering with CRISPR-Cas9. Science. 2014.",
                        "Nature Biotechnology Clinical Updates"
                    )
                )
            )
        ),
        ScientificDivision(
            id = "bioinformatics",
            number = 5,
            title = "Bioinformatics & Computational Biology",
            tagline = "Sequence Alignment, Structural Biology & High-Throughput Omics",
            overview = "Developing algorithms, structural models, and computational workflows to analyze petabyte-scale genomic, transcriptomic, and proteomic datasets.",
            iconName = "bioinformatics",
            accentHex = 0xFF8B5CF6,
            keyConcepts = listOf("BLAST Algorithms", "UniProt & PDB", "Phylogenetics", "Molecular Dynamics", "Structural Prediction"),
            topics = listOf(
                DivisionTopic(
                    id = "sequence_alignment",
                    title = "Algorithms for Sequence Alignment & Homology",
                    subtitle = "Smith-Waterman, Needleman-Wunsch & Heuristic BLAST Searches",
                    summary = "Mathematical dynamic programming and heuristic search algorithms that identify conserved functional domains across species.",
                    definition = "The systematic computational comparison of primary nucleotide or amino acid sequences to identify evolutionary relatedness and functional orthologs.",
                    structure = "Scoring matrices (BLOSUM62, PAM250), affine gap penalties (gap open, gap extension), and position-specific scoring matrices.",
                    functions = listOf(
                        "Calculation of optimal local and global alignment tracebacks",
                        "E-value statistical threshold evaluation for non-random similarity",
                        "Annotation of uncharacterized open reading frames in novel genomes"
                    ),
                    mechanisms = "Dynamic programming constructs an (N+1)x(M+1) matrix scoring matches, mismatches, and insertions/deletions, tracing the highest scoring path backwards.",
                    relatedConditions = listOf("Pathogen Variant Tracking", "Rare Disease Gene Discovery"),
                    researchStatus = "Established Foundation & AI-Accelerated",
                    references = listOf("Altschul SF, et al. Basic local alignment search tool. J Mol Biol. 1990.")
                )
            )
        ),
        ScientificDivision(
            id = "biocomputing",
            number = 6,
            title = "Biocomputing & Biological Computers",
            tagline = "DNA Data Storage, Molecular Logic Gates & Living Circuits",
            overview = "A revolutionary frontier utilizing biological molecules, DNA strand displacement, and synthetic genetic circuits to perform computational logic and hyper-dense information storage.",
            iconName = "biocomputing",
            accentHex = 0xFF00F2FE,
            keyConcepts = listOf("DNA Data Storage", "Strand Displacement", "Biomolecular Logic Gates", "Synthetic Gene Circuits", "Cellular Computing"),
            topics = listOf(
                DivisionTopic(
                    id = "dna_data_storage",
                    title = "High-Density DNA Data Storage",
                    subtitle = "Encoding Digital Binary into Quaternary Nucleotide Sequences",
                    summary = "Translating bytes into adenine, cytosine, guanine, and thymine to store petabytes of data inside microscopic synthetic DNA droplets with millennia-scale stability.",
                    definition = "The technological process of synthesizing custom oligonucleotides to encode digital files (text, images, programs) into DNA sequences.",
                    structure = "Composed of single or double-stranded synthetic DNA fragments flanked by universal primer binding sites and embedded addressing barcodes.",
                    functions = listOf(
                        "Theoretical volumetric data storage density exceeding 215 petabytes per gram",
                        "Centuries of archival permanence at ambient temperatures without electrical power",
                        "Rapid random-access addressing using PCR amplification of index barcodes"
                    ),
                    mechanisms = "Binary bitstreams are mapped through Reed-Solomon or fountain codes into nucleotide strings (00->A, 01->C, 10->G, 11->T) avoiding homopolymer repeats, synthesized enzymatically or phosphoramidite-based, and read via Next-Gen or Nanopore Sequencing.",
                    relatedConditions = listOf("Global Data Center Energy Constraints", "Cold Archival Storage"),
                    researchStatus = "Frontier Experimental & Rapidly Scaling",
                    references = listOf(
                        "Erlich Y, Zielinski D. DNA Fountain enables a robust and efficient storage architecture. Science. 2017.",
                        "Church GM, et al. Next-generation digital information storage in DNA. Science. 2012."
                    )
                ),
                DivisionTopic(
                    id = "molecular_logic_gates",
                    title = "Biomolecular Logic Gates & Strand Displacement",
                    subtitle = "AND, OR, NOT Operations Implemented via Enzyme-Free DNA Hybridization",
                    summary = "Utilizing toehold-mediated strand displacement cascades to execute Boolean logic, thresholding, and neural computing inside cellular buffers.",
                    definition = "Synthetic chemical circuits wherein molecular inputs (specific oligonucleotide strands) bind and displace predetermined fluorescent or active outputs.",
                    structure = "Double-stranded DNA complexes with exposed single-stranded overhang toeholds that mediate branch migration.",
                    functions = listOf(
                        "Implementation of full Boolean logic without requiring enzymes or electronics",
                        "Autonomous smart disease biomarker classification in blood serum",
                        "Direct coupling to CRISPR or aptamer therapeutic triggers"
                    ),
                    mechanisms = "An input strand binds an exposed toehold through complementary Watson-Crick pairing, initiates branch migration across a duplex domain, and releases the incumbent strand through thermodynamic displacement.",
                    relatedConditions = listOf("In vitro Diagnostics", "Targeted Cancer Nanomachines"),
                    researchStatus = "Cutting-Edge Laboratory Innovation",
                    references = listOf("Zhang DY, Seelig G. Dynamic DNA nanotechnology using strand-displacement reactions. Nat Chem. 2011.")
                )
            )
        ),
        ScientificDivision(
            id = "microbiology",
            number = 7,
            title = "Microbiology & Infectious Agents",
            tagline = "Bacteriology, Virology, Mycology & Host-Microbiome Ecology",
            overview = "The microscopic world: investigating bacterial physiology, viral replication cycles, fungal genetics, and the 38-trillion-cell human commensal microbiome.",
            iconName = "microbiology",
            accentHex = 0xFFF59E0B,
            keyConcepts = listOf("Gram Staining", "Viral Capsids", "Antimicrobial Resistance", "Gut Microbiome", "Biofilms"),
            topics = listOf(
                DivisionTopic(
                    id = "antimicrobial_resistance",
                    title = "Mechanisms of Antimicrobial Resistance (AMR)",
                    subtitle = "Efflux Pumps, Beta-Lactamases, Target Alteration & Horizontal Gene Transfer",
                    summary = "How bacterial populations evolve biochemical countermeasures neutralizing clinical antibiotics.",
                    definition = "The acquired or intrinsic ability of microorganisms to survive inhibitory or bactericidal concentrations of antimicrobial pharmacotherapy.",
                    structure = "Encoded on plasmids, transposons, and integrons, expressed as membrane efflux pumps, metallo-beta-lactamase enzymes, or modified penicillin-binding proteins.",
                    functions = listOf(
                        "Enzymatic hydrolysis of antibiotic lactam rings",
                        "Active efflux of toxic fluorinated quinolones and macrolides",
                        "Ribosomal methylation preventing macrolide and aminoglycoside binding"
                    ),
                    mechanisms = "Mobile genetic elements transfer through conjugation, transformation, or transduction across bacterial genera, spreading multidrug resistance genes.",
                    relatedConditions = listOf("MRSA", "Carbapenem-Resistant Enterobacteriaceae", "MDR-Tuberculosis"),
                    researchStatus = "High-Priority Global Health Challenge",
                    references = listOf("WHO Global Action Plan on Antimicrobial Resistance")
                )
            )
        ),
        ScientificDivision(
            id = "medtech",
            number = 8,
            title = "Medical Technology & Surgery",
            tagline = "Diagnostic Imaging, Robotic Surgery & Neural Prosthetics",
            overview = "Advanced biomedical instrumentation from high-field superconducting Magnetic Resonance Imaging (MRI) to robotic multi-axis microsurgical consoles and bionic limbs.",
            iconName = "medtech",
            accentHex = 0xFF06B6D4,
            keyConcepts = listOf("MRI & CT Physics", "Robotic Teleoperation", "Bionic Prosthetics", "Endoscopy", "Biomaterials"),
            topics = listOf(
                DivisionTopic(
                    id = "mri_physics",
                    title = "Magnetic Resonance Imaging (MRI) Physics",
                    subtitle = "Nuclear Magnetic Resonance, Larmor Frequency & T1/T2 Relaxation",
                    summary = "Harnessing hydrogen proton nuclear spin precession in multi-Tesla magnetic fields to generate high-contrast anatomical tomograms.",
                    definition = "A non-invasive diagnostic imaging modality utilizing superconducting magnetic fields, radiofrequency excitation pulses, and spatial gradient coils.",
                    structure = "Consists of a 1.5T to 7.0T superconducting magnet, three orthogonal gradient coils (X, Y, Z), and radiofrequency transmit/receive surface coils.",
                    functions = listOf(
                        "Superior soft-tissue contrast differentiation in the neuroaxis and musculoskeletal joints",
                        "Diffusion-weighted imaging detecting acute cellular ischemic cytotoxic edema",
                        "Functional blood-oxygen-level-dependent (BOLD) neuroimaging"
                    ),
                    mechanisms = "Protons align along B0; a 90° RF pulse tips net magnetization into the transverse plane. As protons relax, T1 longitudinal recovery and T2 spin-spin dephasing induce RF signals detected by receiver coils.",
                    relatedConditions = listOf("Ischemic Stroke", "Glioblastoma", "Ligamentous Tears"),
                    researchStatus = "Established Diagnostic Gold Standard & 7T+ Research",
                    references = listOf("Bushberg JT. The Essential Physics of Medical Imaging, 4th Ed.")
                )
            )
        ),
        ScientificDivision(
            id = "immunology",
            number = 9,
            title = "Immunology & Immunotherapy",
            tagline = "Innate Defense, Somatic Recombination & Immune Checkpoint Blockade",
            overview = "The molecular defenses of self vs. non-self: V(D)J somatic hypermutation, Major Histocompatibility Complexes, cytokines, and chimeric antigen receptor T-cell therapies.",
            iconName = "immunology",
            accentHex = 0xFF34D399,
            keyConcepts = listOf("V(D)J Recombination", "MHC Class I & II", "CAR-T Cell Therapy", "Cytokine Signaling", "Immune Checkpoints"),
            topics = listOf(
                DivisionTopic(
                    id = "car_t_therapy",
                    title = "Chimeric Antigen Receptor (CAR) T-Cell Therapy",
                    subtitle = "Genetic Engineering of Autologous T-Cells for Targeted Oncology",
                    summary = "Reprogramming a patient's own T lymphocytes with synthetic receptors targeting malignant cellular surface antigens.",
                    definition = "An ex-vivo cellular immunotherapy where T-cells are transduced with a recombinant receptor fusing an antibody single-chain variable fragment (scFv) to intracellular T-cell signaling domains.",
                    structure = "Extracellular scFv antigen recognition domain, flexible hinge, transmembrane domain, and intracellular CD3ζ activation domain with 4-1BB or CD28 costimulatory modules.",
                    functions = listOf(
                        "MHC-independent recognition of cell surface tumor antigens (e.g., CD19, BCMA)",
                        "Target-triggered perforin and granzyme B mediated cytolytic degranulation",
                        "In vivo clonal expansion providing long-term immunologic surveillance"
                    ),
                    mechanisms = "Binding of the engineered scFv to cancer antigens triggers phosphorylation of CD3ζ ITAM motifs, initiating calcium-calcineurin-NFAT cascades and potent cytotoxicity.",
                    relatedConditions = listOf("B-Cell Acute Lymphoblastic Leukemia", "Diffuse Large B-Cell Lymphoma", "Multiple Myeloma"),
                    researchStatus = "Revolutionary Approved Therapy & Solid Tumor Expansion",
                    references = listOf(
                        "June CH, et al. CAR T cell immunotherapy for human cancer. Science. 2018.",
                        "Janeway's Immunobiology, 10th Ed."
                    )
                )
            )
        ),
        ScientificDivision(
            id = "pharmacology",
            number = 10,
            title = "Pharmacology & Drug Discovery",
            tagline = "Pharmacokinetics, Pharmacodynamics & Computational Drug Design",
            overview = "The molecular actions of chemical compounds: ADME kinetics, receptor-ligand thermodynamics, structure-based virtual screening, and phase I-IV clinical trials.",
            iconName = "pharmacology",
            accentHex = 0xFF38BDF8,
            keyConcepts = listOf("ADME Kinetics", "Receptor Agonism & Antagonism", "IC50 & Kd Binding", "Cytochrome P450", "Structure-Based Design"),
            topics = listOf(
                DivisionTopic(
                    id = "pk_pd_principles",
                    title = "Pharmacokinetics (ADME) & Target Dynamics",
                    subtitle = "Absorption, Bioavailability, Volume of Distribution & Renal Clearance",
                    summary = "Quantitative mathematical modeling of what the body does to a drug and how molecular targets respond.",
                    definition = "The branch of pharmacology measuring absorption, distribution, hepatic biotransformation, and renal/biliary elimination alongside dose-response curves.",
                    structure = "Two-compartment pharmacokinetic models, Michaelis-Menten elimination equations, and Hill equation receptor occupancy curves.",
                    functions = listOf(
                        "Calculation of therapeutic index (TD50 / ED50) for patient safety margins",
                        "Prediction of Cytochrome P450 3A4 competitive drug-drug interactions",
                        "Optimization of oral bioavailability via Lipinski's Rule of 5"
                    ),
                    mechanisms = "Small molecule ligands bind orthosteric or allosteric receptor pockets through hydrogen bonds, van der Waals interactions, and electrostatic fields, inducing conformational changes.",
                    relatedConditions = listOf("Adverse Drug Reactions", "Narrow Therapeutic Index Monitoring"),
                    researchStatus = "Established Foundation & AI-Driven Virtual Screening",
                    references = listOf("Goodman & Gilman's: The Pharmacological Basis of Therapeutics, 14th Ed.")
                )
            )
        ),
        ScientificDivision(
            id = "ecology",
            number = 11,
            title = "Ecology, Evolution & Biodiversity",
            tagline = "Natural Selection, Population Genomics & Biosphere Dynamics",
            overview = "Investigating speciation, trophic web energetics, biogeochemical cycles, and the evolutionary history of Earth's 8.7 million eukaryotic species.",
            iconName = "ecology",
            accentHex = 0xFF10B981,
            keyConcepts = listOf("Natural Selection", "Speciation", "Trophic Cascades", "Carbon & Nitrogen Cycles", "Phylogenetic Trees"),
            topics = listOf(
                DivisionTopic(
                    id = "trophic_cascades",
                    title = "Trophic Cascades & Apex Predator Dynamics",
                    subtitle = "Top-Down Ecological Regulation, Keystone Species & Ecosystem Stability",
                    summary = "How top predators regulate lower trophic levels, preserving riparian vegetation, geomorphology, and biodiversity.",
                    definition = "Powerful indirect ecological interactions that can control and alter entire ecosystems when a trophic level of a food web is suppressed or reintroduced.",
                    structure = "Hierarchical trophic pyramids: primary producers (photosynthetic autotrophs), primary consumers (herbivores), secondary consumers, and apex carnivores.",
                    functions = listOf(
                        "Limiting herbivore overgrazing on delicate riparian willow and aspen saplings",
                        "Stabilizing riverbank soil erosion through enhanced vegetative root networks",
                        "Buffering biodiversity against competitive exclusion by dominant mesopredators"
                    ),
                    mechanisms = "Keystone predators induce behavioral landscape-of-fear responses in prey, reducing residence times in vulnerable zones and triggering cascading trophic equilibria.",
                    relatedConditions = listOf("Defaunation", "Biodiversity Collapse", "Eutrophication"),
                    researchStatus = "Established Ecological Principle & Rewilding Science",
                    references = listOf(
                        "Estes JA, et al. Trophic downgrading of planet Earth. Science. 2011.",
                        "Begon M, Townsend CR. Ecology: From Individuals to Ecosystems"
                    )
                )
            )
        ),
        ScientificDivision(
            id = "ai_biology",
            number = 12,
            title = "AI & The Future of Biology",
            tagline = "AlphaFold, Generative Protein Design & In Silico Organisms",
            overview = "The convergence of deep neural networks, transformer architectures, and biological discovery: solving the 50-year protein folding problem and designing novel de novo enzymes.",
            iconName = "ai_biology",
            accentHex = 0xFF00F2FE,
            keyConcepts = listOf("AlphaFold & ESMFold", "Generative Protein Design", "In Silico Drug Discovery", "Digital Cell Models", "Quantum Biological Simulation"),
            topics = listOf(
                DivisionTopic(
                    id = "alphafold_folding",
                    title = "AlphaFold & Deep Learning in Structural Biology",
                    subtitle = "Evoformer Attention, Pair Representations & 3D Invariant Point Attention",
                    summary = "How neural networks accurately predict atomic-resolution 3D protein structures directly from 1D amino acid sequences.",
                    definition = "An artificial intelligence system utilizing invariant point attention and evolutionary representations to predict 3D protein coordinates within tenths of an angstrom.",
                    structure = "Consists of Multiple Sequence Alignment (MSA) representations, pair representations, Evoformer transformer blocks, and a 3D Structure Module with Invariant Point Attention (IPA).",
                    functions = listOf(
                        "Prediction of over 200 million 3D protein structures across the tree of life",
                        "Evaluation of per-residue confidence metric (pLDDT) and predicted aligned error (PAE)",
                        "Acceleration of target identification for previously intractable orphan receptors"
                    ),
                    mechanisms = "The network iteratively refines representations of amino acid spatial geometry through rotational and translational equivariance, optimizing stereochemical energy landscapes.",
                    relatedConditions = listOf("Novel Therapeutic Target Discovery", "Viral Spike Epitope Mapping"),
                    researchStatus = "Transformative Breakthrough (2024 Nobel Prize in Chemistry)",
                    references = listOf(
                        "Jumper J, et al. Highly accurate protein structure prediction with AlphaFold. Nature. 2021.",
                        "AlphaFold Protein Structure Database (EMBL-EBI)"
                    )
                ),
                DivisionTopic(
                    id = "denovo_protein_design",
                    title = "De Novo Generative Protein Design",
                    subtitle = "Diffusion Models & RFdiffusion Generating Non-Natural Therapeutic Enzymes",
                    summary = "Utilizing denoising diffusion probabilistic models to generate entirely novel functional proteins never before seen in nature.",
                    definition = "The algorithmic creation of artificial polypeptide chains engineered from first principles to bind specific pathogenic targets or catalyze synthetic reactions.",
                    structure = "Generative diffusion algorithms in SE(3) Cartesian and rotational space conditioned on target binding hotspots and catalytic motifs.",
                    functions = listOf(
                        "Generation of high-affinity binders for viral glycoproteins without animal immunization",
                        "Design of custom synthetic plastic-degrading enzymes and carbon-capture biocatalysts",
                        "Engineered molecular nanomachines and targeted vaccine immunogens"
                    ),
                    mechanisms = "Starting from random Gaussian distribution coordinates, a score-based diffusion model step-by-step denoises backbone atoms guided by thermodynamic and geometrical constraints.",
                    relatedConditions = listOf("Synthetic Enzymes", "Rapid Pandemic Vaccine Development"),
                    researchStatus = "Frontier Breakthrough Field",
                    references = listOf("Watson JL, et al. De novo design of protein structure and function with RFdiffusion. Nature. 2023.")
                )
            )
        )
    )

    val diseases: List<DiseaseEntry> = listOf(
        DiseaseEntry(
            id = "covid19",
            name = "COVID-19 (SARS-CoV-2 Infection)",
            scientificName = "Severe Acute Respiratory Syndrome Coronavirus 2",
            category = DiseaseCategory.INFECTIOUS,
            scientificOverview = "A novel enveloped positive-sense single-stranded RNA beta-coronavirus that emerged in late 2019, causing acute respiratory distress syndrome, microvascular endothelitis, and systemic hyperinflammatory cytokine release.",
            causesAndRiskFactors = listOf("Inhalation of viral aerosol droplets", "Advanced age (>65)", "Cardiovascular disease", "Diabetes mellitus", "Immunocompromised status"),
            biologicalMechanisms = "The viral trimeric Spike (S) glycoprotein binds host cell surface Angiotensin-Converting Enzyme 2 (ACE2), primed by host protease TMPRSS2. This mediates viral membrane fusion, uncoating, replication-transcription complex formation, and down-regulation of protective ACE2 signaling.",
            commonSymptoms = listOf("Fever & chills", "Cough & dyspnea", "Fatigue & myalgias", "Anosmia & dysgeusia", "Sore throat & congestion"),
            educationalDiagnosticMethods = listOf(
                "Reverse Transcription Quantitative PCR (RT-qPCR) targeting viral nucleocapsid (N) and ORF1ab genes",
                "Rapid Antigen Detection Tests (RADT) for viral nucleoprotein",
                "High-resolution chest CT imaging displaying bilateral peripheral ground-glass opacities"
            ),
            preventionStrategies = listOf(
                "Vaccination with mRNA lipid nanoparticle or viral vector platforms",
                "High-efficiency particulate air (HEPA) ventilation",
                "Targeted masking in healthcare environments"
            ),
            evidenceBasedTreatments = listOf(
                "Antiviral RNA-dependent RNA polymerase inhibition (e.g., Remdesivir)",
                "Viral 3CL protease inhibition (Nirmatrelvir/Ritonavir)",
                "Corticosteroid immunomodulation (Dexamethasone) for oxygen-dependent severe disease",
                "Monoclonal IL-6 receptor antagonists (Tocilizumab) in progressive hyperinflammation"
            ),
            researchReferences = listOf(
                "WHO Therapeutics and COVID-19 Living Guideline",
                "NIH COVID-19 Treatment Guidelines",
                "Hoffmann M, et al. SARS-CoV-2 Cell Entry Depends on ACE2 and TMPRSS2. Cell. 2020."
            ),
            publicationDate = "2020-03-11",
            reviewDate = "2026-05-15"
        ),
        DiseaseEntry(
            id = "huntington",
            name = "Huntington's Disease",
            scientificName = "Huntington Chorea (HTT Trinucleotide Repeat Expansion)",
            category = DiseaseCategory.GENETIC,
            scientificOverview = "An autosomal dominant, neurodegenerative disorder characterized by progressive choreiform involuntary movements, cognitive decline, and psychiatric disturbances caused by cytotoxic polyglutamine repeat expansions in the huntingtin protein.",
            causesAndRiskFactors = listOf("Autosomal dominant inheritance (50% offspring risk)", "Expansion of CAG trinucleotide repeat (>36 repeats) on chromosome 4p16.3", "Genetic anticipation in paternal transmission"),
            biologicalMechanisms = "Expanded CAG repeats encode an elongated polyglutamine tract in the huntingtin protein (mHTT), leading to misfolding, toxic aggregate accumulation, disruption of mitochondrial bioenergetics, and selective apoptosis of medium spiny GABAergic neurons in the striatum (caudate and putamen).",
            commonSymptoms = listOf("Chorea (involuntary writhing movements)", "Impaired voluntary saccadic eye movements", "Executive dysfunction", "Depression & irritability", "Dysphagia & dysarthria"),
            educationalDiagnosticMethods = listOf(
                "Polymerase Chain Reaction (PCR) molecular genetic testing determining exact CAG repeat length",
                "Volumetric brain MRI demonstrating caudate head atrophy and compensatory lateral ventriculomegaly"
            ),
            preventionStrategies = listOf(
                "Pre-implantation genetic diagnosis (PGD) with in vitro fertilization",
                "Comprehensive genetic counseling for at-risk individuals"
            ),
            evidenceBasedTreatments = listOf(
                "Vesicular Monoamine Transporter 2 (VMAT2) inhibitors (Tetrabenazine, Deutetrabenazine) for chorea management",
                "Dopamine receptor antagonists (atypical antipsychotics) for behavioral manifestations",
                "Experimental antisense oligonucleotides (ASOs) and RNA interference targeting HTT transcript degradation"
            ),
            researchReferences = listOf(
                "The Huntington's Disease Collaborative Research Group. Cell. 1993.",
                "Tabrizi SJ, et al. Targeting Huntingtin Expression in Patients with Huntington's Disease. N Engl J Med. 2019."
            ),
            publicationDate = "1993-03-26",
            reviewDate = "2026-02-10"
        ),
        DiseaseEntry(
            id = "atherosclerosis",
            name = "Atherosclerotic Cardiovascular Disease",
            scientificName = "Coronary Artery Atheroma & Arteriosclerosis",
            category = DiseaseCategory.CARDIOVASCULAR,
            scientificOverview = "A chronic inflammatory fibroproliferative disease of large and medium arterial walls, characterized by endothelial dysfunction, subendothelial apolipoprotein B deposition, macrophage foam cell formation, and plaque vulnerability leading to thrombosis.",
            causesAndRiskFactors = listOf("Elevated Low-Density Lipoprotein (LDL-C)", "Systemic arterial hypertension", "Cigarette smoking & endothelial shear stress", "Type 2 diabetes & metabolic syndrome", "Elevated Lipoprotein(a)"),
            biologicalMechanisms = "Endothelial injury allows LDL to penetrate the tunica intima, where it undergoes oxidative modification. Endothelial cells express adhesion molecules (VCAM-1) recruiting circulating monocytes that differentiate into macrophages, engulf oxidized LDL via scavenger receptors, become foam cells, and secrete pro-inflammatory cytokines creating a necrotic lipid core.",
            commonSymptoms = listOf("Stable angina pectoris upon exertion", "Shortness of breath on mild exertion", "Intermittent claudication in peripheral arteries", "Often asymptomatic until plaque rupture"),
            educationalDiagnosticMethods = listOf(
                "Coronary Artery Calcium (CAC) scoring via non-contrast cardiac CT",
                "Coronary CT Angiography (CCTA) assessing soft plaque stenosis",
                "Invasive fractional flow reserve (FFR) hemodynamic catheterization"
            ),
            preventionStrategies = listOf(
                "Aggressive LDL-C reduction (<55 mg/dL in very high risk)",
                "Mediterranean dietary pattern rich in monounsaturated lipids and dietary fiber",
                "Blood pressure optimization (<120/80 mmHg)"
            ),
            evidenceBasedTreatments = listOf(
                "HMG-CoA Reductase Inhibitors (Statins) stabilizing fibrous caps and lowering LDL",
                "PCSK9 monoclonal antibodies (Evolocumab) upregulating hepatic LDL receptor recycling",
                "Antiplatelet therapies (Aspirin, P2Y12 inhibitors) preventing thrombosis",
                "Percutaneous coronary intervention (PCI) with drug-eluting stents"
            ),
            researchReferences = listOf(
                "Libby P. Inflammation in atherosclerosis. Nature. 2002.",
                "2023 AHA/ACC Guidelines for the Management of Coronary Artery Disease"
            ),
            publicationDate = "2023-07-20",
            reviewDate = "2026-04-18"
        ),
        DiseaseEntry(
            id = "alzheimers",
            name = "Alzheimer's Disease",
            scientificName = "Dementia of the Alzheimer Type",
            category = DiseaseCategory.NEUROLOGICAL,
            scientificOverview = "The most prevalent neurodegenerative disease, characterized by insidious synaptic loss, extracellular Amyloid-Beta (Aβ) senile plaques, intraneuronal hyperphosphorylated tau neurofibrillary tangles, and progressive cortical atrophy.",
            causesAndRiskFactors = listOf("Apolipoprotein E (APOE ε4 allele)", "Advanced age (>65)", "Microvascular disease & hypertension", "Rare familial mutations in APP, PSEN1, PSEN2"),
            biologicalMechanisms = "Sequential cleavage of Amyloid Precursor Protein (APP) by β-secretase (BACE1) and γ-secretase produces neurotoxic Aβ42 oligomers that deposit in neuropil. Concurrently, hyperphosphorylated tau detaches from microtubules to form paired helical neurofibrillary tangles, disrupting axonal transport and inducing microglial neuroinflammation.",
            commonSymptoms = listOf("Short-term episodic memory impairment", "Visuospatial disorientation", "Anomia and word-finding difficulty", "Loss of executive planning", "Behavioral changes"),
            educationalDiagnosticMethods = listOf(
                "Amyloid and Tau Positron Emission Tomography (PET) radiotracer neuroimaging",
                "Cerebrospinal fluid (CSF) or plasma biomarker immunoassays (p-tau217, Aβ42/Aβ40 ratio)",
                "Volumetric MRI revealing hippocampal and entorhinal cortex atrophy"
            ),
            preventionStrategies = listOf(
                "Rigorous mid-life cardiovascular risk control",
                "Cognitive reserve enhancement through continuous intellectual engagement",
                "Hearing loss correction and regular aerobic exercise"
            ),
            evidenceBasedTreatments = listOf(
                "Cholinesterase inhibitors (Donepezil, Rivastigmine) elevating synaptic acetylcholine",
                "NMDA receptor antagonist (Memantine) mitigating glutamate excitotoxicity",
                "Humanized IgG1 monoclonal antibodies directed against aggregated amyloid-beta (Lecanemab, Donanemab)"
            ),
            researchReferences = listOf(
                "Jack CR Jr, et al. NIA-AA Research Framework: Toward a biological definition of Alzheimer's disease. Alzheimers Dement. 2018.",
                "van Dyck CH, et al. Lecanemab in Early Alzheimer's Disease. N Engl J Med. 2023."
            ),
            publicationDate = "2023-01-05",
            reviewDate = "2026-03-30"
        ),
        DiseaseEntry(
            id = "asthma",
            name = "Bronchial Asthma",
            scientificName = "Chronic Reactive Airway Disease",
            category = DiseaseCategory.RESPIRATORY,
            scientificOverview = "A heterogeneous chronic inflammatory disease of the lower airways characterized by episodic, reversible airflow obstruction, bronchial hyperresponsiveness, and variable expiratory dyspnea.",
            causesAndRiskFactors = listOf("Atopy and immunoglobulin E (IgE) predisposition", "Airborne aeroallergens (dust mites, pollen, mold)", "Respiratory viral infections (Rhinovirus, RSV)", "Environmental tobacco smoke & ozone"),
            biologicalMechanisms = "Type 2 helper T (Th2) and ILC2 lymphocytes release cytokines IL-4, IL-5, and IL-13 upon allergen presentation. This stimulates B-cell IgE class switching, eosinophil bone-marrow recruitment, mast cell degranulation, smooth muscle hypertrophy, and goblet cell mucin hypersecretion.",
            commonSymptoms = listOf("Expiratory wheezing", "Nocturnal cough", "Chest tightness", "Episodic dyspnea triggered by cold air or exertion"),
            educationalDiagnosticMethods = listOf(
                "Spirometry demonstrating FEV1/FVC < 0.70 with bronchodilator reversibility (FEV1 increase >12% and >200 mL)",
                "Fractional Exhaled Nitric Oxide (FeNO) quantifying eosinophilic airway inflammation",
                "Methacholine bronchoprovocation challenge testing"
            ),
            preventionStrategies = listOf(
                "Targeted allergen mitigation and avoidance",
                "Prompt treatment of allergic rhinitis",
                "Cessation of second-hand tobacco exposure"
            ),
            evidenceBasedTreatments = listOf(
                "Inhaled Corticosteroids (ICS) as first-line anti-inflammatory controller therapy",
                "Inhaled Long-Acting and Fast-Acting Beta-2 Agonists (Formoterol, Salbutamol)",
                "Leukotriene receptor antagonists (Montelukast)",
                "Biologic monoclonal antibodies targeting IgE (Omalizumab), IL-5 (Mepolizumab), or IL-4Rα (Dupilumab)"
            ),
            researchReferences = listOf(
                "Global Initiative for Asthma (GINA) Global Strategy for Asthma Management and Prevention",
                "Wenzel SE. Asthma phenotypes: the evolution from clinical to molecular approaches. Nat Med. 2012."
            ),
            publicationDate = "2024-05-01",
            reviewDate = "2026-06-12"
        ),
        DiseaseEntry(
            id = "celiac",
            name = "Celiac Disease",
            scientificName = "Gluten-Sensitive Enteropathy",
            category = DiseaseCategory.DIGESTIVE,
            scientificOverview = "An immune-mediated chronic systemic enteropathy triggered by dietary gluten ingestion in genetically susceptible individuals possessing HLA-DQ2 or HLA-DQ8 heterodimers.",
            causesAndRiskFactors = listOf("Genetic presence of HLA-DQ2.5 or HLA-DQ8", "Dietary consumption of prolamin storage proteins (gliadin in wheat, secalin in rye, hordein in barley)", "Family history in first-degree relatives"),
            biologicalMechanisms = "Gluten prolamins resist gastric digestion and are deamidated by mucosal tissue transglutaminase 2 (tTG-2), giving them a strong negative charge that tightly binds HLA-DQ2/8 on antigen-presenting cells. This induces CD4+ T-cell interferon-gamma release and intraepithelial cytotoxic CD8+ lymphocyte activation, causing enterocyte apoptosis, villous blunting, and crypt hyperplasia.",
            commonSymptoms = listOf("Chronic osmotic diarrhea", "Steatorrhea & abdominal distension", "Unexplained iron-deficiency anemia", "Dermatitis herpetiformis", "Failure to thrive in pediatric cohorts"),
            educationalDiagnosticMethods = listOf(
                "Serum IgA anti-Tissue Transglutaminase (anti-tTG) and anti-Endomysial (EMA) antibody serology",
                "Esophagogastroduodenoscopy (EGD) with multiple duodenal biopsies classified via Marsh criteria (Marsh 3 villous atrophy)",
                "HLA-DQ2/DQ8 genetic exclusion testing (high negative predictive value)"
            ),
            preventionStrategies = listOf(
                "No known primary prevention once genetic sensitization occurs",
                "Screening of at-risk relatives and patients with type 1 diabetes"
            ),
            evidenceBasedTreatments = listOf(
                "Strict, lifelong dietary elimination of gluten grains (<20 parts per million)",
                "Nutritional repletion of iron, folate, calcium, and fat-soluble vitamins (A, D, E, K)",
                "Experimental engineered gluten-degrading endopeptidases and tight-junction modulators under investigation"
            ),
            researchReferences = listOf(
                "Sollid LM, Jabri B. Triggers and drivers of autoimmunity: lessons from coeliac disease. Nat Rev Immunol. 2013.",
                "ACGS Clinical Guidelines for Celiac Disease"
            ),
            publicationDate = "2023-01-15",
            reviewDate = "2026-01-20"
        ),
        DiseaseEntry(
            id = "type1_diabetes",
            name = "Type 1 Diabetes Mellitus",
            scientificName = "Autoimmune Beta-Cell Destruction",
            category = DiseaseCategory.ENDOCRINE,
            scientificOverview = "An autoimmune endocrinopathy characterized by T-cell-mediated selective destruction of insulin-producing beta cells in the pancreatic islets of Langerhans, culminating in absolute insulin deficiency.",
            causesAndRiskFactors = listOf("Polymorphisms in HLA-DR3-DQ2 and HLA-DR4-DQ8 loci", "Putative enterovirus triggers (Coxsackie B)", "Positive family history"),
            biologicalMechanisms = "Autoreactive CD8+ and CD4+ T-cells breach central and peripheral self-tolerance, infiltrating pancreatic islets (insulitis). They recognize autoantigens including Insulin, GAD65, IA-2, and ZnT8, executing apoptotic destruction via perforin/granzyme and Fas-FasL pathways.",
            commonSymptoms = listOf("Polyuria (osmotic diuresis)", "Polydipsia (compensatory thirst)", "Polyphagia with paradoxical rapid weight loss", "Diabetic ketoacidosis (Kussmaul breathing, fruity acetone breath)"),
            educationalDiagnosticMethods = listOf(
                "Fasting plasma glucose ≥ 126 mg/dL or Glycated Hemoglobin (HbA1c) ≥ 6.5%",
                "Measurement of circulating islet autoantibodies (anti-GAD65, anti-IA2, anti-ZnT8)",
                "Serum C-peptide testing demonstrating negligible endogenous insulin production"
            ),
            preventionStrategies = listOf(
                "Teplizumab (anti-CD3 monoclonal antibody) approved to delay progression from Stage 2 to clinical Stage 3 disease in high-risk individuals",
                "Autoantibody surveillance in prospective pediatric cohorts"
            ),
            evidenceBasedTreatments = listOf(
                "Exogenous basal-bolus insulin replacement therapy (rapid-acting analogs: Lispro/Aspart; long-acting analogs: Degludec/Glargine)",
                "Automated Insulin Delivery (AID) / hybrid closed-loop artificial pancreas systems with continuous glucose monitoring (CGM)",
                "Islet cell transplantation under investigational immunosuppressive regimens"
            ),
            researchReferences = listOf(
                "American Diabetes Association Standards of Care in Diabetes",
                "Herold KC, et al. An Anti-CD3 Antibody, Teplizumab, in Relatives at Risk for Type 1 Diabetes. N Engl J Med. 2019."
            ),
            publicationDate = "2024-01-01",
            reviewDate = "2026-05-02"
        ),
        DiseaseEntry(
            id = "sle",
            name = "Systemic Lupus Erythematosus (SLE)",
            scientificName = "Multisystem Autoimmune Collagen Vascular Disease",
            category = DiseaseCategory.AUTOIMMUNE,
            scientificOverview = "A prototypic chronic multisystem autoimmune disease characterized by immune tolerance failure to nuclear antigens, pathogenic autoantibody generation, and immune complex deposition in kidneys, skin, and joints.",
            causesAndRiskFactors = listOf("Female sex (9:1 female-to-male ratio during reproductive years)", "HLA-DR2 and HLA-DR3 alleles", "Ultraviolet light (UV) irradiation inducing keratinocyte apoptosis", "Epstein-Barr Virus (EBV) molecular mimicry"),
            biologicalMechanisms = "Defective clearance of apoptotic debris exposes intracellular nuclear antigens (dsDNA, Sm, Ro/SSA). B-cells generate antinuclear antibodies (ANAs), forming circulating immune complexes that deposit in capillary basements (e.g., renal glomeruli), activating the classical complement cascade (C1q, C3, C4) and recruiting neutrophils.",
            commonSymptoms = listOf("Malar 'butterfly' facial erythema sparing nasolabial folds", "Non-erosive symmetric polyarthritis", "Photosensitivity", "Lupus nephritis with proteinuria and hematuria", "Serositis (pleuritis, pericarditis)"),
            educationalDiagnosticMethods = listOf(
                "EULAR/ACR 2019 classification criteria scoring system (entry requirement: positive ANA ≥ 1:80)",
                "Specific serology: anti-double-stranded DNA (anti-dsDNA) and anti-Smith (anti-Sm) antibodies",
                "Serum complement consumption testing showing depleted C3 and C4 levels",
                "Renal biopsy staging lupus nephritis (ISN/RPS Class I through VI)"
            ),
            preventionStrategies = listOf(
                "Broad-spectrum photoprotection against UVA and UVB radiation",
                "Smoking cessation to reduce disease flares",
                "Infection prophylaxis during immunosuppressive therapy"
            ),
            evidenceBasedTreatments = listOf(
                "Hydroxychloroquine (antimalarial) universally recommended to prevent disease flares and improve survival",
                "Systemic glucocorticoids for acute inflammatory flares with rapid tapering",
                "B-cell activating factor (BAFF) inhibitors (Belimumab)",
                "Type I interferon receptor antagonist (Anifrolumab)",
                "Mycophenolate Mofetil or Cyclophosphamide for severe lupus nephritis"
            ),
            researchReferences = listOf(
                "Aringer M, et al. 2019 European League Against Rheumatism/American College of Rheumatology Classification Criteria for Systemic Lupus Erythematosus. Ann Rheum Dis. 2019.",
                "Tsokos GC. Systemic lupus erythematosus. N Engl J Med. 2011."
            ),
            publicationDate = "2023-11-10",
            reviewDate = "2026-04-05"
        ),
        DiseaseEntry(
            id = "sickle_cell",
            name = "Sickle Cell Disease",
            scientificName = "Hemoglobin S Hemoglobinopathy (HbS)",
            category = DiseaseCategory.HEMATOLOGY,
            scientificOverview = "An inherited monogenic hemoglobinopathy caused by a missense point mutation in the beta-globin gene, resulting in hemoglobin polymerization, erythrocyte sickling, chronic hemolytic anemia, and recurrent vaso-occlusive crises.",
            causesAndRiskFactors = listOf("Autosomal recessive inheritance (two HbS alleles, homozygous HbSS)", "Heterozygous carrier state (HbAS, sickle cell trait) offers selective survival advantage against malaria (Plasmodium falciparum)", "Hypoxia, acidosis, dehydration, and hypothermia trigger crises"),
            biologicalMechanisms = "A single nucleotide substitution (GAG to GTG at codon 6 of HBB) replaces hydrophilic glutamic acid with hydrophobic valine. Under deoxygenated conditions, exposed valine patches on adjacent HbS tetramers cross-link into insoluble 14-strand helical polymers, distorting erythrocytes into rigid, sickle shapes that occlude microvessels.",
            commonSymptoms = listOf("Excruciating vaso-occlusive bone and joint pain crises", "Chronic fatigue and pallor from extravascular and intravascular hemolysis", "Dactylitis (hand-foot syndrome) in infants", "Functional asplenia predisposing to encapsulated bacterial sepsis", "Acute chest syndrome"),
            educationalDiagnosticMethods = listOf(
                "High-Performance Liquid Chromatography (HPLC) and hemoglobin electrophoresis quantifying HbS vs HbA",
                "Polymerase Chain Reaction (PCR) molecular genetic sequencing of the HBB gene",
                "Peripheral blood smear displaying classic drepanocytes (sickle cells) and Howell-Jolly bodies"
            ),
            preventionStrategies = listOf(
                "Universal newborn screening programs allowing early penicillin prophylaxis",
                "Pre-marital and pre-conception hemoglobinopathy carrier screening",
                "Comprehensive hydration and avoidance of high-altitude hypoxic exposure"
            ),
            evidenceBasedTreatments = listOf(
                "Hydroxyurea (increases fetal hemoglobin [HbF] expression, disrupting HbS polymerization)",
                "Exa-cel (Casgevy) autologous CRISPR-Cas9 edited stem cell gene therapy reactivating HbF",
                "Voxelotor (HbS oxygen-affinity modulator preventing polymerization)",
                "Crizanlizumab (P-selectin inhibitor preventing microvascular adhesion)",
                "Allogeneic hematopoietic stem cell transplantation (curative modality)"
            ),
            researchReferences = listOf(
                "Piel FB, Steinberg MH, Rees DC. Sickle Cell Disease. N Engl J Med. 2017.",
                "Frangoul H, et al. CRISPR-Cas9 Gene Editing for Sickle Cell Disease and β-Thalassemia. N Engl J Med. 2021."
            ),
            publicationDate = "2021-01-21",
            reviewDate = "2026-05-18"
        ),
        DiseaseEntry(
            id = "melanoma",
            name = "Cutaneous Melanoma",
            scientificName = "Malignant Melanocytic Neoplasm",
            category = DiseaseCategory.DERMATOLOGY,
            scientificOverview = "An aggressive malignancy originating from melanin-producing neural crest melanocytes, possessing high propensity for lymphatic and hematogenous metastasis driven by UV-induced DNA mutational signatures.",
            causesAndRiskFactors = listOf("Intermittent intense ultraviolet (UVB/UVA) radiation exposure", "History of blistering sunburns during youth", "High total melanocytic nevus count (>50)", "Fair skin phenotype (Fitzpatrick I-II, MC1R gene variants)", "Familial mutations in CDKN2A (p16INK4a)"),
            biologicalMechanisms = "UV radiation induces cyclobutane pyrimidine dimers and C-to-T transitions. Oncogenic driver mutations in the MAPK pathway (BRAF V600E in ~50%, NRAS in ~20%) cause constitutive serine/threonine kinase activation, evading senescence and driving uncontrolled melanocytic proliferation through cyclin D1 upregulation.",
            commonSymptoms = listOf("ABCDE signs: Asymmetry, Border irregularity, Color variegation, Diameter >6mm, Evolution/change over time", "Elevated nodular lesions with ulceration or bleeding", "Pruritus or local hyperpigmentation changes"),
            educationalDiagnosticMethods = listOf(
                "Dermoscopic epiluminescence microscopy assessing pigmented network atypicalities",
                "Full-thickness excisional biopsy with 1-2 mm margins (incisional biopsies discouraged)",
                "Histopathological Breslow depth measurement and mitotic rate indexing",
                "Sentinel lymph node biopsy (SLNB) for staging tumors with Breslow depth >0.8 mm"
            ),
            preventionStrategies = listOf(
                "Broad-spectrum SPF 30+ physical/chemical sunscreen application every 2 hours",
                "Avoidance of indoor UV tanning beds and peak midday solar radiation (10 AM to 4 PM)",
                "Regular total body cutaneous examination by dermatology specialists"
            ),
            evidenceBasedTreatments = listOf(
                "Wide local excision with margins based on Breslow thickness (1 to 2 cm)",
                "Immune Checkpoint Inhibitors targeting PD-1 (Pembrolizumab, Nivolumab) and CTLA-4 (Ipilimumab)",
                "Targeted kinase inhibition for BRAF V600-mutant disease (Dabrafenib plus Trametinib)",
                "Adjuvant and neoadjuvant immunotherapy protocols in resectable stage III/IV disease"
            ),
            researchReferences = listOf(
                "Shadid D, et al. Cutaneous melanoma. Lancet. 2020.",
                "National Comprehensive Cancer Network (NCCN) Guidelines: Cutaneous Melanoma"
            ),
            publicationDate = "2023-08-14",
            reviewDate = "2026-03-22"
        ),
        DiseaseEntry(
            id = "nsclc",
            name = "Non-Small Cell Lung Carcinoma (NSCLC)",
            scientificName = "Pulmonary Adenocarcinoma & Squamous Cell Carcinoma",
            category = DiseaseCategory.ONCOLOGY,
            scientificOverview = "The leading cause of global cancer mortality, encompassing distinct molecular subtypes driven by somatic alterations in oncogenes including EGFR, KRAS, ALK, and ROS1.",
            causesAndRiskFactors = listOf("Tobacco cigarette smoking (responsible for >80% of cases)", "Environmental radon gas decay products", "Secondhand tobacco smoke exposure", "Occupational exposure to asbestos, arsenic, and diesel exhaust"),
            biologicalMechanisms = "Carcinogens such as benzo[a]pyrene diol epoxide bind covalently to DNA, generating bulky guanine adducts and G-to-T transversions in TP53. In non-smokers, activating somatic driver kinase mutations (EGFR exon 19 deletions or L858R) or gene fusions (EML4-ALK) trigger hyperactive PI3K-AKT and RAS-MAPK cell survival signaling.",
            commonSymptoms = listOf("Persistent, worsening cough", "Hemoptysis (coughing up blood)", "Unexplained weight loss and cachexia", "Dull, pleuritic chest pain", "Hoarseness from recurrent laryngeal nerve invasion"),
            educationalDiagnosticMethods = listOf(
                "Low-Dose Computed Tomography (LDCT) lung cancer screening for high-risk smokers",
                "Bronchoscopy with Endobronchial Ultrasound (EBUS) guided transbronchial needle aspiration",
                "Next-Generation Sequencing (NGS) multi-gene panel testing for actionable biomarkers (EGFR, ALK, ROS1, BRAF, RET, MET exon 14, KRAS G12C)",
                "Immunohistochemistry quantifying Programmed Death-Ligand 1 (PD-L1) tumor proportion score"
            ),
            preventionStrategies = listOf(
                "Aggressive tobacco cessation programs and smoke-free public policies",
                "Residential indoor radon testing and soil mitigation",
                "Occupational particulate filter enforcement"
            ),
            evidenceBasedTreatments = listOf(
                "Surgical anatomic lobectomy with systematic mediastinal lymphadenectomy for localized stage I-II disease",
                "Stereotactic Body Radiation Therapy (SBRT) for medically inoperable early-stage lesions",
                "Third-generation EGFR Tyrosine Kinase Inhibitors (Osimertinib) crossing the blood-brain barrier",
                "ALK inhibitors (Alectinib, Brigatinib, Lorlatinib)",
                "Immune checkpoint immunotherapy (Pembrolizumab) alone or combined with platinum-doublet chemotherapy"
            ),
            researchReferences = listOf(
                "Herbst RS, Morgensztern D, Boshoff C. The biology and management of non-small cell lung cancer. Nature. 2018.",
                "Soria JC, et al. Osimertinib in Untreated EGFR-Mutated Advanced Non-Small-Cell Lung Cancer. N Engl J Med. 2018."
            ),
            publicationDate = "2023-04-10",
            reviewDate = "2026-06-01"
        ),
        DiseaseEntry(
            id = "metabolic_syndrome",
            name = "Metabolic Syndrome & MASH",
            scientificName = "Metabolic Dysfunction-Associated Steatohepatitis",
            category = DiseaseCategory.METABOLIC,
            scientificOverview = "A cluster of interconnected cardiometabolic abnormalities—visceral adiposity, atherogenic dyslipidemia, hypertension, and systemic insulin resistance—frequently progressing to hepatic steatosis, inflammation, and fibrosis.",
            causesAndRiskFactors = listOf("Excess dietary simple sugars (high-fructose corn syrup) and saturated lipids", "Sedentary lifestyle and visceral adipose expansion", "Genetic polymorphisms in PNPLA3 and TM6SF2"),
            biologicalMechanisms = "Visceral adipose hypertrophy causes adipocyte hypoxia, macrophage infiltration, and excess lipolysis. Elevated non-esterified fatty acid fluxes overwhelm mitochondrial beta-oxidation in hepatocytes, causing lipotoxicity, endoplasmic reticulum stress, reactive oxygen species generation, and hepatic stellate cell activation driving collagen deposition and fibrosis.",
            commonSymptoms = listOf("Elevated waist circumference (>102 cm men, >88 cm women)", "Acanthosis nigricans in skin folds", "Mild right upper quadrant abdominal fullness", "Frequently asymptomatic until advanced hepatic decompensation"),
            educationalDiagnosticMethods = listOf(
                "Diagnostic criteria: 3 of 5 features (Visceral obesity, Fasting Triglycerides ≥150 mg/dL, Low HDL-C, BP ≥130/85 mmHg, Fasting Glucose ≥100 mg/dL)",
                "Vibration-Controlled Transient Elastography (FibroScan) measuring liver stiffness in kilopascals (kPa)",
                "Serum non-invasive fibrosis scores (FIB-4 Index, NAFLD Fibrosis Score)"
            ),
            preventionStrategies = listOf(
                "Weight loss of 7-10% of total body weight via caloric restriction",
                "Minimization of ultra-processed food and sweetened beverage consumption",
                "Resistance and aerobic exercise improving peripheral insulin sensitivity"
            ),
            evidenceBasedTreatments = listOf(
                "Resmetirom (thyroid hormone receptor-beta selective agonist approved for MASH with moderate-to-advanced fibrosis)",
                "Glucagon-Like Peptide-1 (GLP-1) and dual GLP-1/GIP receptor agonists (Semaglutide, Tirzepatide) achieving substantial metabolic remodeling",
                "Sodium-Glucose Cotransporter 2 (SGLT2) inhibitors improving glycemic and cardiovascular outcomes"
            ),
            researchReferences = listOf(
                "Harrison SA, et al. A Randomized, Controlled Trial of Resmetirom in Non-Alcoholic Steatohepatitis. N Engl J Med. 2024.",
                "Rinella ME, et al. A multisociety Delphi consensus statement on new fatty liver disease nomenclature. Hepatology. 2023."
            ),
            publicationDate = "2024-02-08",
            reviewDate = "2026-05-10"
        )
    )

    val researchPapers: List<ResearchPaper> = listOf(
        ResearchPaper(
            id = "paper_alphafold",
            title = "Highly Accurate Protein Structure Prediction with AlphaFold",
            authors = "John Jumper, Richard Evans, Alexander Pritzel, Tim Green, Michael Figurnov, et al.",
            journal = "Nature 596, 583–589",
            publicationDate = "2021-07-15",
            category = "Computational Biology & AI",
            doi = "10.1038/s41586-021-03819-2",
            externalUrl = "https://www.nature.com/articles/s41586-021-03819-2",
            abstractSummary = "AlphaFold demonstrates atomic-level accuracy in predicting 3D macromolecular structures from primary amino acid sequences, resolving a 50-year grand challenge in structural biology.",
            keyFindings = listOf(
                "Achieved median backbone accuracy of 0.96 Å RMSD95 at CASP14",
                "Introduced Evoformer architecture to exchange spatial and co-evolutionary constraints",
                "Validated across 200 million structural predictions in public domain"
            ),
            methodology = "Dual-track neural network incorporating Multiple Sequence Alignment representations and pair matrices fed into Invariant Point Attention (IPA) 3D coordinate transformers.",
            citationCount = "22,000+",
            references = listOf("Nature 2021", "CASP14 Proceedings")
        ),
        ResearchPaper(
            id = "paper_crispr",
            title = "A Programmable Dual-RNA-Guided DNA Endonuclease in Adaptive Bacterial Immunity",
            authors = "Martin Jinek, Krzysztof Chylinski, Ines Fonfara, Michael Hauer, Jennifer A. Doudna, Emmanuelle Charpentier",
            journal = "Science 337 (6096), 816–821",
            publicationDate = "2012-08-17",
            category = "Biotechnology & Genome Editing",
            doi = "10.1126/science.1225829",
            externalUrl = "https://www.science.org/doi/10.1126/science.1225829",
            abstractSummary = "Engineered a single-guide RNA (sgRNA) chimera combining crRNA and tracrRNA to program Streptococcus pyogenes Cas9 for sequence-specific double-strand DNA cleavage.",
            keyFindings = listOf(
                "Demonstrated that Cas9 cleaves DNA 3 base pairs upstream of the PAM sequence",
                "Fused crRNA and tracrRNA into a single synthetic guide RNA (sgRNA)",
                "Established foundational biotechnology enabling targeted eukaryotic genome engineering"
            ),
            methodology = "In vitro nuclease cleavage assays, biochemical footprinting, and plasmid linearization electrophoretic mobility shift assays.",
            citationCount = "19,500+",
            references = listOf("Science 2012", "Nobel Prize in Chemistry 2020")
        ),
        ResearchPaper(
            id = "paper_dna_storage",
            title = "DNA Fountain Enables a Robust and Efficient Storage Architecture",
            authors = "Yaniv Erlich, Dina Zielinski",
            journal = "Science 355 (6328), 950–954",
            publicationDate = "2017-03-03",
            category = "Biocomputing & DNA Storage",
            doi = "10.1126/science.aaj2038",
            externalUrl = "https://www.science.org/doi/10.1126/science.aaj2038",
            abstractSummary = "Stored an operating system, a short movie, and a Pioneer plaque in DNA using fountain codes, achieving 215 petabytes per gram storage density without data loss across multiple copy generations.",
            keyFindings = listOf(
                "Pioneered Luby Transform fountain code packetization for error-resilient DNA encoding",
                "Attained 1.57 bits per nucleotide encoding efficiency",
                "Demonstrated error-free file retrieval via Illumina sequencing after deep PCR propagation"
            ),
            methodology = "Random packet drop synthesis, oligopool sequencing, and erasure code reconstruction algorithms.",
            citationCount = "1,800+",
            references = listOf("Science 2017", "Nature Materials 2020")
        ),
        ResearchPaper(
            id = "paper_dna_structure",
            title = "Molecular Structure of Nucleic Acids: A Structure for Deoxyribose Nucleic Acid",
            authors = "J. D. Watson, F. H. C. Crick",
            journal = "Nature 171, 737–738",
            publicationDate = "1953-04-25",
            category = "Molecular Biology & Genetics",
            doi = "10.1038/171737a0",
            externalUrl = "https://www.nature.com/articles/171737a0",
            abstractSummary = "Proposed the antiparallel double-helical structure of deoxyribonucleic acid with complementary purine-pyrimidine base-pairing, immediately suggesting a copying mechanism for genetic material.",
            keyFindings = listOf(
                "Antiparallel sugar-phosphate backbones on exterior with bases oriented inward",
                "Specific hydrogen bonding pairing Adenine with Thymine and Guanine with Cytosine",
                "Self-evident mechanism for faithful semiconservative replication"
            ),
            methodology = "Stereochemical molecular wire model building interpreted from Rosalind Franklin and Maurice Wilkins' X-ray diffraction Photo 51.",
            citationCount = "16,000+",
            references = listOf("Nature 1953", "Franklin & Gosling Nature 1953")
        )
    )

    val databases: List<BioDatabaseResource> = listOf(
        BioDatabaseResource(
            id = "ncbi",
            name = "National Center for Biotechnology Information",
            abbreviation = "NCBI",
            organization = "U.S. National Library of Medicine (NIH)",
            websiteUrl = "https://www.ncbi.nlm.nih.gov",
            description = "The premier global biomedical informatics repository hosting GenBank, BLAST, ClinVar, dbSNP, and taxonomy databases.",
            primaryDataType = "Genomic DNA, RNA transcripts, nucleotide records, variation data",
            queryExample = "Homo sapiens TP53 [Gene] OR SARS-CoV-2 isolate Wuhan-Hu-1 [Accession NC_045512]",
            researchWorkflow = "Search Entrez Gene -> Inspect chromosomal coordinates -> Download FASTA sequences -> Identify homologous orthologs via BLAST."
        ),
        BioDatabaseResource(
            id = "pubmed",
            name = "PubMed Biomedical Literature Archive",
            abbreviation = "PubMed",
            organization = "National Library of Medicine (NLM)",
            websiteUrl = "https://pubmed.ncbi.nlm.nih.gov",
            description = "Search engine comprising more than 37 million citations and abstracts for biomedical and life sciences literature.",
            primaryDataType = "Peer-reviewed scientific journal articles, clinical trials, systematic reviews",
            queryExample = "\"CRISPR-Cas9\"[Title] AND \"Clinical Trials\"[Filter] AND 2024:2026[dp]",
            researchWorkflow = "Formulate MeSH search syntax -> Filter by article type -> Export PMIDs and DOIs -> Access full-text via PubMed Central (PMC)."
        ),
        BioDatabaseResource(
            id = "uniprot",
            name = "Universal Protein Resource",
            abbreviation = "UniProt",
            organization = "UniProt Consortium (EBI, SIB, PIR)",
            websiteUrl = "https://www.uniprot.org",
            description = "The world's leading high-quality comprehensive resource for protein sequence and functional annotation data (UniProtKB/Swiss-Prot).",
            primaryDataType = "Curated protein amino acid sequences, catalytic residues, post-translational modifications",
            queryExample = "P04637 (Human Cellular Tumor Antigen p53) or P01308 (Human Insulin)",
            researchWorkflow = "Lookup accession code -> Inspect topology and domains -> Review known pathogenic missense variants -> Download PDB cross-references."
        ),
        BioDatabaseResource(
            id = "ensembl",
            name = "Ensembl Genome Browser",
            abbreviation = "Ensembl",
            organization = "EMBL-EBI & Wellcome Sanger Institute",
            websiteUrl = "https://www.ensembl.org",
            description = "Comprehensive genomic browser providing automatic annotation and comparative genomics for vertebrate and eukaryotic species.",
            primaryDataType = "Genome assemblies (GRCh38), gene models, splice isoforms, regulatory elements",
            queryExample = "ENSG00000141510 (Human TP53 Ensembl Gene ID)",
            researchWorkflow = "Navigate karyotype browser -> Inspect exon-intron structure -> Examine 100-vertebrate conservation tracks -> Analyze VEP consequences."
        ),
        BioDatabaseResource(
            id = "blast",
            name = "Basic Local Alignment Search Tool",
            abbreviation = "BLAST",
            organization = "NCBI (NIH)",
            websiteUrl = "https://blast.ncbi.nlm.nih.gov/Blast.cgi",
            description = "High-throughput heuristic sequence comparison algorithm that finds regions of local similarity between biological sequences.",
            primaryDataType = "Nucleotide-nucleotide (blastn), protein-protein (blastp), translated searches (blastx)",
            queryExample = "Paste 5'-ATGCGTACGTAGCTAG-3' to identify matching species or genomic loci",
            researchWorkflow = "Input unknown FASTA sequence -> Select Reference Genomes database -> Execute heuristic search -> Evaluate alignment bit scores and E-values."
        ),
        BioDatabaseResource(
            id = "rcsb_pdb",
            name = "RCSB Protein Data Bank",
            abbreviation = "RCSB PDB",
            organization = "Research Collaboratory for Structural Bioinformatics",
            websiteUrl = "https://www.rcsb.org",
            description = "The single global open-access archive of 3D macromolecular structures of proteins, nucleic acids, and complex molecular machines determined by X-ray, Cryo-EM, and NMR.",
            primaryDataType = "PDB / mmCIF 3D atomic coordinates, experimental electron density maps",
            queryExample = "PDB ID: 1BNA (B-DNA Dodecamer) or 4OO8 (CRISPR-Cas9-sgRNA-DNA complex)",
            researchWorkflow = "Query 4-character PDB identifier -> Inspect resolution and ligands -> Render 3D ribbon diagram -> Download .cif atomic coordinates."
        )
    )

    val glossary: List<CellGlossaryItem> = listOf(
        CellGlossaryItem(
            term = "Mitochondrion",
            phonetic = "/ˌmaɪ.təˈkɑːn.dri.ən/",
            category = "Organelles",
            definition = "Double-membrane eukaryotic organelle responsible for generating the majority of cellular adenosine triphosphate (ATP) through oxidative phosphorylation.",
            cellularRole = "Sites of the Krebs cycle, beta-oxidation of fatty acids, and electron transport chain proton gradient generation.",
            biologicalExample = "Cardiac myocytes contain thousands of mitochondria to support unceasing contractile pumping work."
        ),
        CellGlossaryItem(
            term = "Ribosome",
            phonetic = "/ˈraɪ.bəˌsoʊm/",
            category = "Molecular Machines",
            definition = "A ribonucleoprotein complex composed of ribosomal RNA (rRNA) and proteins that executes protein synthesis by translating messenger RNA.",
            cellularRole = "Catalyzes peptidyl transferase peptide bond formation between incoming aminoacyl-tRNAs in the 60S/40S subunits.",
            biologicalExample = "Membrane-bound ribosomes on the rough endoplasmic reticulum synthesize secretory and transmembrane proteins."
        ),
        CellGlossaryItem(
            term = "CRISPR",
            phonetic = "/ˈkrɪs.pər/",
            category = "Genetics & Biotechnology",
            definition = "Clustered Regularly Interspaced Short Palindromic Repeats: specialized bacterial DNA sequences functioning as an adaptive immunological defense against phages.",
            cellularRole = "Stores fragments of past viral invaders as spacer sequences that direct Cas nucleases to destroy matching foreign genetic material.",
            biologicalExample = "Repurposed in modern molecular biology as a programmable RNA-guided endonuclease system for genome engineering."
        ),
        CellGlossaryItem(
            term = "Telomere",
            phonetic = "/ˈtel.ə.mɪr/",
            category = "Chromosomal Structures",
            definition = "Repetitive non-coding nucleotide sequences (5'-TTAGGG-3' in vertebrates) capping the physical ends of linear eukaryotic chromosomes.",
            cellularRole = "Protects chromosomes from degradation, end-to-end fusion, and the end-replication problem; progressively shortens with cell division.",
            biologicalExample = "Telomerase reverse transcriptase is reactivated in approximately 85% to 90% of human malignant tumors to achieve replicative immortality."
        ),
        CellGlossaryItem(
            term = "Apoptosis",
            phonetic = "/ˌæp.əpˈtoʊ.sɪs/",
            category = "Cellular Processes",
            definition = "Programmed, non-inflammatory cellular death characterized by chromatin condensation, cellular shrinkage, membrane blebbing, and apoptotic body engulfment.",
            cellularRole = "Eliminates superfluous, damaged, or genetically compromised cells during embryogenesis, tissue remodeling, and cancer surveillance.",
            biologicalExample = "Interdigital cell death during human embryonic development separates fingers and toes."
        ),
        CellGlossaryItem(
            term = "Action Potential",
            phonetic = "/ˈæk.ʃən pəˈten.ʃəl/",
            category = "Physiology & Neurobiology",
            definition = "A transient, regenerative all-or-nothing reversal of electrical membrane potential across excitable neuron or muscle cell membranes.",
            cellularRole = "Enables rapid long-distance transmission of neural electrical information along axon fibers at speeds up to 120 m/s.",
            biologicalExample = "Depolarization opening voltage-gated Na+ channels in the axon hillock propagates signals to neuromuscular junctions."
        )
    )

    val bioElements: List<BioElement> = listOf(
        BioElement("O", "Oxygen", 8, 65.0, "Major Macroelement", "Essential electron acceptor in aerobic respiration; constituent of water and biological macromolecules.", "H2O, Carbohydrates, Lipids, Nucleic Acids"),
        BioElement("C", "Carbon", 6, 18.5, "Major Macroelement", "The fundamental structural backbone of all organic life; forms four stable covalent bonds.", "Proteins, DNA, RNA, Glucose, Fatty Acids"),
        BioElement("H", "Hydrogen", 1, 9.5, "Major Macroelement", "Component of water and organic molecules; establishes proton gradients powering ATP synthesis.", "H2O, Hydronium (H3O+), NADH, ATP synthase proton motive force"),
        BioElement("N", "Nitrogen", 7, 3.2, "Major Macroelement", "Crucial component of amino acid amine groups and nitrogenous purine/pyrimidine bases.", "Peptide bonds, Adenine, Thymine, Guanine, Cytosine, Uracil"),
        BioElement("Ca", "Calcium", 20, 1.5, "Mineral Macroelement", "Primary structural mineral in bones/teeth; universal intracellular second messenger and muscle activator.", "Hydroxyapatite Ca10(PO4)6(OH)2, Calmodulin, Troponin C"),
        BioElement("P", "Phosphorus", 15, 1.0, "Mineral Macroelement", "Forms high-energy phosphodiester backbones and cellular energy currency ATP/GTP.", "Adenosine Triphosphate, Phospholipid bilayer heads, DNA/RNA backbones"),
        BioElement("K", "Potassium", 19, 0.4, "Electrolyte", "Principal intracellular cation; establishes resting membrane potential and nerve impulse conduction.", "Na+/K+-ATPase, Voltage-gated K+ channels"),
        BioElement("S", "Sulfur", 16, 0.3, "Macroelement", "Forms structural disulfide bridges that stabilize tertiary and quaternary protein structures.", "Methionine, Cysteine, Glutathione (antioxidant)"),
        BioElement("Na", "Sodium", 11, 0.2, "Electrolyte", "Principal extracellular cation; regulates extracellular fluid osmotic volume and action potential upstroke.", "Blood pressure regulation, Nerve action potentials, Secondary active transport"),
        BioElement("Mg", "Magnesium", 12, 0.1, "Essential Cofactor", "Obligate cofactor for all biochemical reactions involving ATP; stabilizes nucleic acid structures.", "Hexokinase, DNA Polymerase catalytic triad, Chlorophyll in plants"),
        BioElement("Fe", "Iron", 26, 0.006, "Essential Trace Element", "Transition metal facilitating reversible oxygen binding in hemoglobin and electron transfer in cytochromes.", "Heme in Hemoglobin/Myoglobin, Iron-Sulfur clusters in Complex I"),
        BioElement("Zn", "Zinc", 30, 0.003, "Essential Trace Element", "Structural component of zinc-finger transcription factors and catalytic cofactor for carbonic anhydrase.", "Zinc-finger DNA binding motifs, Alcohol dehydrogenase, RNA polymerase")
    )

    val quizzes: List<QuizQuestion> = listOf(
        QuizQuestion(
            id = "q1",
            domain = "Genetics & Molecular Biology",
            question = "Which enzyme catalyzes the transcription of pre-messenger RNA from a genomic DNA template in eukaryotic cells?",
            options = listOf("DNA Polymerase III", "RNA Polymerase II", "Reverse Transcriptase", "DNA Ligase"),
            correctIndex = 1,
            explanation = "RNA Polymerase II is the primary eukaryotic enzyme responsible for synthesizing messenger RNA (mRNA) precursors and small nuclear RNAs.",
            difficulty = "Foundational"
        ),
        QuizQuestion(
            id = "q2",
            domain = "Biotechnology",
            question = "In the CRISPR-Cas9 genome editing mechanism, what short adjacent sequence is strictly required for Cas9 recognition and cleavage?",
            options = listOf("Poly-A tail", "TATA Box", "Protospacer Adjacent Motif (PAM)", "Kozak sequence"),
            correctIndex = 2,
            explanation = "Cas9 requires a 2-6 base pair Protospacer Adjacent Motif (PAM, typically 5'-NGG-3' for SpCas9) directly adjacent to the target genomic sequence to initiate unwinding and cleavage.",
            difficulty = "Intermediate"
        ),
        QuizQuestion(
            id = "q3",
            domain = "Human Physiology",
            question = "What primary cellular mechanism is responsible for restoring the resting membrane potential of a neuron following repolarization?",
            options = listOf("Sodium-Potassium ATPase pump", "Voltage-gated calcium channels", "Passive glucose transporter GLUT-4", "Aquaporin channels"),
            correctIndex = 0,
            explanation = "The electrogenic Na+/K+-ATPase pump actively expels 3 Na+ ions out of the cell for every 2 K+ ions pumped in, maintaining the negative resting potential (-70 mV).",
            difficulty = "Intermediate"
        ),
        QuizQuestion(
            id = "q4",
            domain = "Biocomputing",
            question = "What is the theoretical volumetric data storage density limit of synthetic DNA per gram of dry substance?",
            options = listOf("Approx. 1 Megabyte", "Approx. 5 Gigabytes", "Approx. 215 Petabytes", "Approx. 1 Terabyte"),
            correctIndex = 2,
            explanation = "Synthetic DNA has an astounding theoretical data storage density exceeding 215 petabytes (215 million gigabytes) per gram of DNA, utilizing quaternary nucleotide base encoding.",
            difficulty = "Advanced"
        ),
        QuizQuestion(
            id = "q5",
            domain = "Immunology",
            question = "Which class of cell surface glycoproteins presents endogenous viral or mutated peptide antigens to CD8+ cytotoxic T-lymphocytes?",
            options = listOf("MHC Class I", "MHC Class II", "CD4 coreceptor", "Toll-Like Receptor 4"),
            correctIndex = 0,
            explanation = "Major Histocompatibility Complex (MHC) Class I molecules are expressed on virtually all nucleated human cells and present internal peptides to CD8+ cytotoxic T-cells.",
            difficulty = "Foundational"
        ),
        QuizQuestion(
            id = "q6",
            domain = "AI in Biology",
            question = "AlphaFold utilizes which geometrical and mathematical property to process 3D spatial relations of amino acids during protein folding?",
            options = listOf("SE(3) Equivariance & Invariant Point Attention", "Simple 2D Convolution", "K-Means Clustering", "Naive Bayes Probability"),
            correctIndex = 0,
            explanation = "AlphaFold's Structure Module employs 3D Invariant Point Attention (IPA) and SE(3) equivariance to reason directly about 3D atomic coordinates without coordinate-frame bias.",
            difficulty = "Advanced"
        )
    )
}
