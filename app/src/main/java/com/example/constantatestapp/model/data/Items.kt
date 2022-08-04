package com.example.constantatestapp.model.data

private const val BASE_NAME_ARRAY_SIZE = 3

data class Items(
    val title: String,
    private val directorName: String,
    val releaseYear: Int,
    private val actors: List<Actors>
) {

    fun removeDuplicateElements(): String {
        val stringBuilder = StringBuilder()
        actors.distinct().forEach {
            stringBuilder.append("\n${it.actorName}")
        }
        return stringBuilder.toString()
    }

    fun convertDirectorName(): String {
        val name = directorName.split(" ")
        val stringBuilder = StringBuilder()

        return if (name.size == BASE_NAME_ARRAY_SIZE) {
            stringBuilder
                .append(name[0])
                .append(" ")
                .append(name[1][0])
                .append(".")
                .append(name[2][0])
                .append(".")
            stringBuilder.toString()
        } else
            directorName
    }
}