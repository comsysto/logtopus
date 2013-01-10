modules = {
    core {
        dependsOn 'jquery'
        defaultBundle 'ui'

        resource url:'/js/application.js', disposition: 'head'
        resource url:'/js/angular-1.0.3.js', disposition: 'head'

    }


}