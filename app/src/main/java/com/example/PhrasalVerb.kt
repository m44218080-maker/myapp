package com.example

/**
 * Data model representing a Phrasal Verb with formal and informal usages.
 *
 * @param id Unique identifier.
 * @param verb The English phrasal verb.
 * @param summaryMeaning Concise Arabic translation for listing cards.
 * @param formalMeaning Formal/official definition and usage explanation in Arabic.
 * @param formalExample English sentence demonstrating formal/professional context.
 * @param formalExampleTranslation Arabic translation of the formal example.
 * @param informalMeaning Informal/slang/colloquial meaning and everyday usage in Arabic.
 * @param informalExample English sentence demonstrating conversational/slang context.
 * @param informalExampleTranslation Arabic translation of the informal example.
 */
data class PhrasalVerb(
    val id: Int,
    val verb: String,
    val summaryMeaning: String,
    val formalMeaning: String,
    val formalExample: String,
    val formalExampleTranslation: String,
    val informalMeaning: String,
    val informalExample: String,
    val informalExampleTranslation: String
)

