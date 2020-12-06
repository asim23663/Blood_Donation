package com.example.asimyamin.blood_donation.activities.auth.model

/**
 * Created by Asim Yamin on 6/12/2020.
 */
class DonorInformation {
    var donorId: String? = null
    var name: String? = null
    var email: String? = null
    var gender: String? = null
    var profilePic: String? = null
    var phoneNumber: String? = null
    var bloodGroup: String? = null
    var lastDonationDate: String? = null
    var address: String? = null
    var city: String? = null
    var isVolunteer = false
    var isAvailable = false

    constructor() {}
    constructor(donorId: String?, name: String?, email: String?, gender: String?, profilePic: String?, phoneNumber: String?, bloodGroup: String?, lastDonationDate: String?, address: String?, city: String?, isVolunteer: Boolean, isAvailable: Boolean) {
        this.donorId = donorId
        this.name = name
        this.email = email
        this.gender = gender
        this.profilePic = profilePic
        this.phoneNumber = phoneNumber
        this.bloodGroup = bloodGroup
        this.lastDonationDate = lastDonationDate
        this.address = address
        this.city = city
        this.isVolunteer = isVolunteer
        this.isAvailable = isAvailable
    }
}