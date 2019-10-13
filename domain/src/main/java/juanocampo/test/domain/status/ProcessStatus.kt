package juanocampo.test.domain.status

sealed class ProcessStatus
object ProcessSuccess : ProcessStatus()
object ProcessError: ProcessStatus()