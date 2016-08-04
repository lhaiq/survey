var routers = {
    "surveyor_list": {
        "url": "/survey/user?page={page}&account={account}",
        "template": "/static/surveyor/template_list.html"
    },
    "surveyor_edit": {
        "url": "/survey/user/{id}",
        "template": "/static/surveyor/edit_surveyor.html"
    },
    "template_list": {
        "url": "/survey/template?page={page}",
        "template": "/static/template/template_list.html"
    }
}