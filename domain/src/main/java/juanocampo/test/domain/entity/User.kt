package juanocampo.test.domain.entity

data class User(
    var id: String,
    var name: String,
    var username: String,
    var email: String,
    var address: Address,
    var phone: String,
    var website: String,
    var company: Company
)

data class Address(
    var street: String,
    var suite: String,
    var city: String,
    var zipcode: String
)

data class Company(
    var name: String,
    var catchPhrase: String,
    var bs: String
)