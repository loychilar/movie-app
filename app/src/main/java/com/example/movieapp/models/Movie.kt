package com.example.movieapp.models

class Movie {
    var name:String?=null
    var author:String?=null
    var about:String?=null
    var date:String?=null
    constructor()
    constructor(name: String?, author: String?, about: String?, date: String?) {
        this.name = name
        this.author = author
        this.about = about
        this.date = date
    }

}