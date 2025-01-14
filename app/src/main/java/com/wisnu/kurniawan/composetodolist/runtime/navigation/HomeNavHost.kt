package com.wisnu.kurniawan.composetodolist.runtime.navigation

import androidx.compose.runtime.MutableState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.bottomSheet
import com.wisnu.kurniawan.composetodolist.features.dashboard.ui.DashboardScreen
import com.wisnu.kurniawan.composetodolist.features.dashboard.ui.DashboardTabletScreen
import com.wisnu.kurniawan.composetodolist.features.dashboard.ui.DashboardViewModel
import com.wisnu.kurniawan.composetodolist.features.todo.group.ui.CreateGroupScreen
import com.wisnu.kurniawan.composetodolist.features.todo.group.ui.CreateGroupViewModel
import com.wisnu.kurniawan.composetodolist.features.todo.group.ui.UpdateGroupScreen
import com.wisnu.kurniawan.composetodolist.features.todo.grouplist.ui.EditGroupListScreen
import com.wisnu.kurniawan.composetodolist.features.todo.grouplist.ui.UpdateGroupListScreen
import com.wisnu.kurniawan.composetodolist.features.todo.grouplist.ui.UpdateGroupListViewModel
import com.wisnu.kurniawan.composetodolist.features.todo.groupmenu.ui.GroupMenuScreen
import com.wisnu.kurniawan.composetodolist.features.todo.groupmenu.ui.GroupMenuViewModel
import com.wisnu.kurniawan.composetodolist.features.todo.main.ui.ToDoMainViewModel
import com.wisnu.kurniawan.composetodolist.features.todo.search.ui.SearchViewModel

@OptIn(ExperimentalMaterialNavigationApi::class)
fun NavGraphBuilder.HomeNavHost(
    navController: NavHostController,
    bottomSheetConfig: MutableState<MainBottomSheetConfig>
) {
    navigation(startDestination = HomeFlow.DashboardScreen.route, route = HomeFlow.Root.route) {
        composable(HomeFlow.DashboardScreen.route) {
            val viewModel = hiltViewModel<DashboardViewModel>()
            val toDoMainViewModel = hiltViewModel<ToDoMainViewModel>()
            val searchViewModel = hiltViewModel<SearchViewModel>()
            DashboardScreen(
                navController = navController,
                viewModel = viewModel,
                toDoMainViewModel = toDoMainViewModel,
                searchViewModel = searchViewModel
            )
        }
        HomeBottomSheetNavHost(
            bottomSheetConfig = bottomSheetConfig,
            navController = navController
        )
    }
}

@OptIn(ExperimentalMaterialNavigationApi::class)
fun NavGraphBuilder.HomeTabletNavHost(
    navController: NavHostController,
    navControllerLeft: NavHostController,
    navControllerRight: NavHostController,
    bottomSheetConfig: MutableState<MainBottomSheetConfig>
) {
    navigation(startDestination = HomeFlow.DashboardScreen.route, route = HomeFlow.Root.route) {
        composable(HomeFlow.DashboardScreen.route) {
            val viewModel = hiltViewModel<DashboardViewModel>()
            val toDoMainViewModel = hiltViewModel<ToDoMainViewModel>()
            DashboardTabletScreen(
                navController = navController,
                navControllerLeft = navControllerLeft,
                navControllerRight = navControllerRight,
                viewModel = viewModel,
                toDoMainViewModel = toDoMainViewModel
            )
        }

        HomeBottomSheetNavHost(
            bottomSheetConfig = bottomSheetConfig,
            navController = navControllerLeft
        )
    }
}

@OptIn(ExperimentalMaterialNavigationApi::class)
private fun NavGraphBuilder.HomeBottomSheetNavHost(
    bottomSheetConfig: MutableState<MainBottomSheetConfig>,
    navController: NavHostController
) {
    bottomSheet(
        route = HomeFlow.GroupMenu.route,
        arguments = HomeFlow.GroupMenu.arguments
    ) {
        val viewModel = hiltViewModel<GroupMenuViewModel>()
        bottomSheetConfig.value = DefaultMainBottomSheetConfig
        GroupMenuScreen(
            viewModel = viewModel,
            navController = navController
        )
    }
    bottomSheet(HomeFlow.CreateGroup.route) {
        val viewModel = hiltViewModel<CreateGroupViewModel>()
        bottomSheetConfig.value = DefaultMainBottomSheetConfig
        CreateGroupScreen(
            navController = navController,
            viewModel = viewModel
        )
    }
    bottomSheet(
        route = HomeFlow.UpdateGroup.route,
        arguments = HomeFlow.UpdateGroup.arguments
    ) {
        val viewModel = hiltViewModel<CreateGroupViewModel>()
        bottomSheetConfig.value = DefaultMainBottomSheetConfig
        UpdateGroupScreen(
            navController = navController,
            viewModel = viewModel
        )
    }
    bottomSheet(
        route = HomeFlow.UpdateGroupList.route,
        arguments = HomeFlow.UpdateGroupList.arguments
    ) {
        val viewModel = hiltViewModel<UpdateGroupListViewModel>()
        bottomSheetConfig.value = DefaultMainBottomSheetConfig
        UpdateGroupListScreen(
            navController = navController,
            viewModel = viewModel
        )
    }
    bottomSheet(
        route = HomeFlow.EditGroupList.route,
        arguments = HomeFlow.EditGroupList.arguments
    ) {
        val viewModel = hiltViewModel<UpdateGroupListViewModel>()
        bottomSheetConfig.value = DefaultMainBottomSheetConfig
        EditGroupListScreen(
            navController = navController,
            viewModel = viewModel
        )
    }
}
