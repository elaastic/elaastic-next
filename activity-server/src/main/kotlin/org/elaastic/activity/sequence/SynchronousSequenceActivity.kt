package org.elaastic.activity.sequence

import org.elaastic.activity.Activity

class SynchronousSequenceActivity(title: String) : Activity(title) {
    private val phases = listOf<Phase>(
        Phase.Response(),
        Phase.Evaluation(),
        Phase.Result(),
    )
    private var currentPhaseIndex: Int? = null
    private var closed = false

    fun getPhaseAt(phaseIndex: Int) = phases.getOrNull(phaseIndex) ?: throw IndexOutOfBoundsException()

    fun getPhase(phaseId: PhaseId) = phases.find { it.id == phaseId }

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
    }

    fun nextPhase() {
        assert(getCurrentPhase()?.isClosed() ?: false) { "Cannot change active phase ; the current phase is not closed" }
        assert(getCurrentPhase() != phases.last()) { "Cannot go to next phase ; the active phase is the last one" }

        currentPhaseIndex = currentPhaseIndex?.plus(1)
    }

    fun closeSequence() {
        closed = true
    }

    fun getActivePhases() = phases.filter { phase -> phase.isOpen() }

}