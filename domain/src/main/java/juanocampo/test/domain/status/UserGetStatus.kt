package juanocampo.test.domain.status

import juanocampo.test.domain.entity.User

sealed class UserGetStatus
data class UserSuccess(val user: User): UserGetStatus()
object UserError: UserGetStatus()