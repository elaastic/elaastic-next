package org.elaastic.materials.answer

import org.elaastic.directory.Learner
import org.elaastic.materials.question.Question

open class Answer(
    val learner: Learner,
    val question: Question,
    val explanation: String,
)