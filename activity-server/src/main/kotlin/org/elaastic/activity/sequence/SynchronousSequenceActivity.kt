package org.elaastic.activity.sequence

import org.elaastic.activity.Activity
import org.elaastic.directory.Learner

class SynchronousSequenceActivity(title: String) : Activity(title) {
    private val phases = listOf<Phase>(
        Phase.Response(),
        Phase.Evaluation(),
        Phase.Result(),
    )
    private var currentPhaseIndex: Int? = null
    private var closed = false

    private val learnersProgression = phases.associate { it.id to mutableMapOf<Learner, LearnerPhaseState>() }

    private fun getPhaseAt(phaseIndex: Int) = phases.getOrNull(phaseIndex) ?: throw IndexOutOfBoundsException()

    private fun getPhase(phaseId: PhaseId) =
        phases.find { it.id == phaseId } ?: throw IllegalArgumentException("The sequence has no phase ${phaseId}")

    fun getCurrentPhase(): Phase? = currentPhaseIndex?.let { getPhaseAt(it) }

    fun initialize() {
        assert(currentPhaseIndex == null) { "This sequence is already initialized" }

        phases.map { phase -> phase.state == PhaseState.PENDING }
        currentPhaseIndex = 0
    }

    fun openPhase(phaseId: PhaseId) {
        assert(getCurrentPhase()?.isPending() ?: false) { "The current phase cannot be opened ; it is not pending" }
        getCurrentPhase()?.open()
    }

    fun closePhase() {
        assert(getCurrentPhase()?.isOpen() ?: false) { "The current phase cannot be closed ; it is not open" }
        getCurrentPhase()?.close()
        // TODO update learners progression
    }

    fun nextPhase() {
        assert(
            getCurrentPhase()?.isClosed() ?: false
        ) { "Cannot change active phase ; the current phase is not closed" }
        assert(getCurrentPhase() != phases.last()) { "Cannot go to next phase ; the active phase is the last one" }

        currentPhaseIndex = currentPhaseIndex?.plus(1)
    }

    fun closeSequence() {
        closed = true
    }

    fun getActivePhases() = phases.filter { phase -> phase.isOpen() }

    fun startPhaseForLearner(learner: Learner, phaseId: PhaseId) {
        val phase = getPhase(phaseId)
        assert(phase.isOpen()) { "The phase $phaseId is not open" }

        learnersProgression[phaseId]!![learner] = LearnerPhaseState.IN_PROGRESS
    }

    fun completePhaseForLearner(learner: Learner, phaseId: PhaseId) {

        // TODO It must be the current phase for the learner...
    }

}