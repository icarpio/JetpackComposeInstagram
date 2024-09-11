package com.example.jetpackcomposeinstagram.model

data class DragonBall(
    val items: List<Item>,
    val links: Links,
    val meta: Meta
)
data class Item(
    val affiliation: String,
    val deletedAt: Any,
    val description: String,
    val gender: String,
    val id: Int,
    val image: String,
    val ki: String,
    val maxKi: String,
    val name: String,
    val race: String,
    val originPlanet:OriginPlanet,
    val transformations:List<Transformation>
)

data class OriginPlanet (
    val id:Int,
    val name:String,
    val isDestroyed:Boolean,
    val description: String,
    val image: String,
    val deletedAt: String?
)

data class Transformation(
    val id:Int,
    val name:String,
    val image: String,
    val ki:String,
    val deletedAt: String?
)

data class Meta(
    val currentPage: Int,
    val itemCount: Int,
    val itemsPerPage: Int,
    val totalItems: Int,
    val totalPages: Int
)

data class Links(
    val first: String,
    val last: String,
    val next: String,
    val previous: String
)