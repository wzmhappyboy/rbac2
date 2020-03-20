function getCookie(c_name) {
    if(document.cookie.length>0){
        c_start=document.cookie.indexOf(c_name+"=")
        if (c_start!=-1)
        {
            c_start=c_start+c_name.length+1
            c_end=document.cookie.indexOf(";",c_start)
            if(c_end==-1) c_end=document.cookie.length
            return unescape(document.cookie.substring(c_start,c_end) )
        }
    }
    return ""
}

var pageNum;

var vm=new Vue({
    el: '#rrapp',
    data:{
        page:'',
        showList:true,
        name:'',
        url:'',
        description:'',
        permissions:[],
        totalPages:'',
        update:false,
        p:'',
        p_id:''

    },

    watch: {
        //实时查询
        p_id() {

            this.getPById();

        },
    },
    methods: {


        getPermissionList: function (p) {
            $.ajax({
                url: "/permissionslist",
                headers: {'Authorization': getCookie("token")},
                type: "get",
                dataType: "json",
                data:{
                    "page":p
                },
                async: true,
                success: function(data) {
                    vm.permissions=data.permissionslist;
                    pageNum=data.pageNum;
                    vm.page=data.pageNum;
                    vm.isFirstPage=data.isFirstPage;
                    vm.isLastPage=data.isLastPage;
                    vm.totalPages=data.totalPages;
                },
                error:function (data) {
                    alert("提示：系统获取权限列表失败");
                }

            });
        },

        shift :function () {
            if (vm.showList===true)
            {
                vm.showList=false;
            }
            else {
                vm.showList=true;
            }
        },

        deletePermission: function (pId) {

            $.ajax({
                url: "/permissions",
                headers: {'Authorization': getCookie("token")},
                type: "delete",
                data:{"id":pId},
                dataType: "json",
                // contentType: "application/json",

                async: true,
                success: function (data) {
                    var result=data.s;
                    if (result==1)
                    {
                        alert("删除成功");
                        window.location.reload();
                    }
                    else {
                        alert("有外键约束等问题删不了");
                    }

                },
                error: function (data) {
                    alert("系统错误");
                }
            });

        },

        getPById: function () {
            var pid = vm.p_id;
            if (pid != '') {
                $.ajax({
                    url: "/permissions/" + pid,
                    headers: {'Authorization': getCookie("token")},
                    type: "get",
                    dataType: "json",

                    async: true,
                    success: function (data) {
                        vm.permissions = data.plist;
                        vm.isFirstPage = false;
                        vm.isLastPage = false;
                        // vm.showList=true;
                    },
                    error: function (data) {
                        alert("系统错误");
                    }

                });
            } else {
                vm.getPermissionList();
            }
        },


        addP: function () {
            var name = vm.name;
            var description = vm.description;
            var url = vm.url;
            $.ajax({
                    url: "/permissions",
                    headers: {'Authorization': getCookie("token")},
                    type: "post",
                    data: {
                        "name": name,
                        "description": description,
                        "url": url
                    },
                    dataType: "json",

                    async: true,
                    success: function (data) {
                        var result = data.s;
                        if (result === 1) {
                            alert("添加成功");
                            vm.showList = true;
                            // this.getUserList();
                        } else {
                            alert("添加失败");
                        }

                    },
                error: function (data) {
                    alert("系统错误");
                }
            });

        },
        upd: function (pid) {
            vm.update = true;
            $.ajax({
                url: "/permissions/" + pid,
                headers: {'Authorization': getCookie("token")},
                type: "get",
                dataType: "json",

                async: true,
                success: function (data) {

                    vm.p=data.p;
                    vm.update = true;
                    // vm.showList=true;
                },
                error: function (data) {
                    alert("系统错误");
                }

            });
        },

        updateP: function () {
            var id=vm.p.id;
            var type=vm.p.type;
            var name=vm.p.name;
            var description=vm.p.description;
            var url=vm.p.url;
            $.ajax({
                url: "/permissions",
                headers: {'Authorization': getCookie("token")},
                type: "put",
                dataType: "json",
                data:{
                    id:id,
                    name:name,
                    type:type,
        description:description,
                    url:url
                },
                async: true,
                success: function (data) {
                    if (data.s==1) {
                        vm.update = false;
                        this.getPermissionList();
                        alert("更新成功！")
                    }
                    else {
                        alert("更新失败！")
                    }
                },
                error: function (data) {
                    alert("系统错误");
                }

            });
        },


    },

    created:function () {
        this.getPermissionList();
    },

});