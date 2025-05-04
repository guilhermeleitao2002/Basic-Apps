package pt.ist.cmu.notesapp

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Detail : Screen("detail/{noteId}") {
        fun createRoute(noteId: Int) = "detail/$noteId"
    }
}