function TodoController( $scope, $http ) {

    $scope.title = "";
    $scope.todos = [];

    $scope.loadTodos = function() {
        $http.get("todo/getTodos").success(
            function(data) {
                $scope.todos = data
            }
        )
    };

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

    $scope.complete = function( id ) {
        $http.post("todo/done?id=" + id).success( function() {
            $scope.loadTodos()
        })
    };

    $scope.loadTodos()
}
