class UrlMappings {

    static excludes = ["/todo.html"]

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(view:"/index")
        "/about"(view:"/about")
		"500"(view:'/error')
	}
}
