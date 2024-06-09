package org.elaastic.materials.answer

import org.elaastic.directory.Learner
import org.elaastic.materials.question.OpenQuestion

class SingleChoiceAnswer(
    learner: Learner,
    question: OpenQuestion,
    choiceIndex: Int,
    explanation: String
) : Answer(learner, question, explanation)