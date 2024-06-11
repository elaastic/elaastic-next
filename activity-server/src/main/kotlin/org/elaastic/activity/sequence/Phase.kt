package org.elaastic.activity.sequence

sealed class Phase(val id: PhaseId) {
    abstract var state: PhaseState
    data class Response(override var state: PhaseState = PhaseState.UNDEFINED) : Phase(PhaseId.RESPONSE)
    data class Evaluation(override var state: PhaseState = PhaseState.UNDEFINED) : Phase(PhaseId.EVALUATION)
    data class Result(override var state: PhaseState = PhaseState.UNDEFINED) : Phase(PhaseId.RESULT)

    fun isOpen() = state == PhaseState.OPEN
    fun isPending() = state == PhaseState.PENDING
    fun isClosed() = state == PhaseState.CLOSED

    fun open() {
        state = PhaseState.OPEN
    }

    fun close() {
        state = PhaseState.CLOSED
    }
}