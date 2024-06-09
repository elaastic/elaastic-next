package org.elaastic.materials.answer

import org.elaastic.directory.Learner
import org.elaastic.materials.question.OpenQuestion

class MultipleChoiceAnswer(
    learner: Learner,
    question: OpenQuestion,
    choicesIndex: List<Int>,
    explanation: String
) : Answer(learner, question, explanation)