package org.elaastic.activity.sequence

import org.elaastic.activity.Activity

class SynchronousSequenceActivity(title: String) : Activity(title) {
    private val phases = listOf<Phase>(
        Phase.Response(),
        Phase.Evaluation(),
        Phase.Result(),
    )
    private var currentPhaseIndex: Int? = null

    fun getPhaseAt(phaseIndex: Int) = phases.getOrNull(phaseIndex) ?: throw IndexOutOfBoundsException()

    fun getPhase(phaseId: PhaseId) = phases.find { it.id == phaseId }

    fun getCurrentPhase(): Phase? = currentPhaseIndex?.let { getPhaseAt(it) }

    fun initialize() {
        phases.map { phase -> phase.state == PhaseState.PENDING }
        currentPhaseIndex = 0
    }

    fun startPhase(phaseId: PhaseId) {
        TODO()
    }

    fun stopPhase(phaseId: PhaseId) {
        TODO()
    }

    fun nextPhase(phaseId: PhaseId) {
        TODO()
    }

    fun close() {
        TODO()
    }

    fun getActivePhases() = phases.filter { phase -> phase.isOpen() }

}