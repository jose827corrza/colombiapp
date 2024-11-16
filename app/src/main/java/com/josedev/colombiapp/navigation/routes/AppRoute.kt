package com.josedev.colombiapp.navigation.routes

sealed class AppRoute(val route: String) {
    class Colombia : AppRoute("colombia")
    class Regions : AppRoute("regions")

    class DepartmentsByRegionIdStatic: AppRoute("departmentsByRegionId/{id}")
    class DepartmentsByRegionId(val id: Long) : AppRoute("departmentsByRegionId/$id")

    class StateDetailByIdStatic: AppRoute("stateDetailById/{id}")
    class StateDetailById(val id: Long) : AppRoute("stateDetailById/$id")

}