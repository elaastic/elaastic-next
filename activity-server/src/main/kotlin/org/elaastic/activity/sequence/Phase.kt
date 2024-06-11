package org.elaastic.activity.sequence

sealed class Phase(val id: PhaseId) {
    abstract val state: PhaseState
    data class Response(override val state: PhaseState = PhaseState.UNDEFINED) : Phase(PhaseId.RESPONSE)
    data class Evaluation(override val state: PhaseState = PhaseState.UNDEFINED) : Phase(PhaseId.EVALUATION)
    data class Result(override val state: PhaseState = PhaseState.UNDEFINED) : Phase(PhaseId.RESULT)

    fun isOpen() = state == PhaseState.OPEN
}