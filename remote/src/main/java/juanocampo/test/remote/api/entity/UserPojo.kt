package juanocampo.test.remote.api.entity

data class UserPojo(
    var id: String? = null,
    var name: String? = null,
    var username: String? = null,
    var email: String? = null,
    var address: Address? = null,
    var phone: String? = null,
    var website: String? = null,
    var company: Company? = null
)

data class Address(
    var street: String? = null,
    var suite: String? = null,
    var city: String? = null,
    var zipcode: String? = null
)

data class Company(
    var name: String? = null,
    var catchPhrase: String? = null,
    var bs: String? = null
)
