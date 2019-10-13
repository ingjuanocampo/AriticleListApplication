package juanocampo.test.data.entity

class UserRepo (
    var id: String,
    var name: String,
    var username: String,
    var email: String,
    var address: AddressRepo,
    var phone: String,
    var website: String,
    var company: CompanyRepo)

class AddressRepo(
    var street: String,
    var suite: String,
    var city: String,
    var zipcode: String)

class CompanyRepo(
    var name: String,
    var catchPhrase: String,
    var bs: String
)
