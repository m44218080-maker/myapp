package com.example

import org.junit.Assert.*
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
  @Test
  fun addition_isCorrect() {
    assertEquals(4, 2 + 2)
  }

  @Test
  fun phrasalVerbsData_hasExactly100VerbsWithFullDetails() {
    assertEquals(100, PhrasalVerbsData.verbs.size)
    val ids = mutableSetOf<Int>()
    for (verb in PhrasalVerbsData.verbs) {
      assertTrue("ID must be positive", verb.id > 0)
      assertTrue("ID should be unique: ${verb.id}", ids.add(verb.id))
      assertTrue("Verb text should not be blank", verb.verb.isNotBlank())
      assertTrue("Summary meaning should not be blank", verb.summaryMeaning.isNotBlank())
      assertTrue("Formal meaning should not be blank", verb.formalMeaning.isNotBlank())
      assertTrue("Formal example should not be blank", verb.formalExample.isNotBlank())
      assertTrue("Formal example translation should not be blank", verb.formalExampleTranslation.isNotBlank())
      assertTrue("Informal meaning should not be blank", verb.informalMeaning.isNotBlank())
      assertTrue("Informal example should not be blank", verb.informalExample.isNotBlank())
      assertTrue("Informal example translation should not be blank", verb.informalExampleTranslation.isNotBlank())
    }
  }

  @Test
  fun phrasalVerbsData_getByIdWorks() {
    val firstVerb = PhrasalVerbsData.getById(1)
    assertNotNull(firstVerb)
    assertEquals("Give up", firstVerb?.verb)

    val lastVerb = PhrasalVerbsData.getById(100)
    assertNotNull(lastVerb)
    assertEquals("Wrap up", lastVerb?.verb)
  }
}
