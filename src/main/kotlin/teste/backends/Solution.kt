package teste.backends

import teste.backends.proposal.application.ProposalEventConsumer
import java.io.File

fun main(args: Array<String>) {
    val directory = args[0]

    File(directory)
        .walk()
        .filter { it.isFile }
        .sortedBy { it.name }
        .map { it.readText().split("\n") }
        .forEach { println(processMessages(it)) }
}

fun processMessages(messages: List<String>): String = ProposalEventConsumer.consume(messages)
