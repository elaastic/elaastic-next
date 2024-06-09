package org.elaastic.materials.answer

import org.elaastic.directory.Learner
import org.elaastic.materials.question.OpenQuestion

class OpenAnswer(
    learner: Learner,
    question: OpenQuestion,
    explanation: String
) : Answer(learner, question, explanation) {
    fun getContent() = explanation
}