function TodoController( $scope, $routeParams, $http ) {

    $scope.title = "";
    $scope.todos = [];

    // load all todos, copying to the "todos" list on success
    $scope.loadTodos = function() {
        $http.get("todo/getTodos").success(
            function(data) {
                $scope.todos = data
            }
        )
    };

    // save a new todo, based on the "description" property
    $scope.addTodo = function() {
        $http.post("todo/save", {
                title : $scope.title
            }
        ).success( function(data) {
                $scope.todos = data;
                $scope.title = "";
            }
        )
    };

    // mark a todo complete, reloading the whole list on success
    $scope.complete = function( id ) {
        $http.post("todo/done?id=" + id).success( function() {
            $scope.loadTodos()
        })
    };

    // when we first stat up, load todos
    $scope.loadTodos()
}
