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
        description:'',
        roles:[],
        totalPages:'',
        role_id:''

    },
    watch: {
        //实时查询
        role_id() {

            this.getRoleById();

        },

        // user_id() {
        //     delay(() => {
        //         this.checkInput();
        //     }, 300);
        // },

    },
    methods: {


        getRoleList: function (p) {
            $.ajax({
                url: "/roles",
                headers: {'Authorization': getCookie("token")},
                type: "get",
                dataType: "json",
                data: {
                    "page": p
                },
                async: true,
                success: function (data) {
                    vm.roles = data.roleslist;
                    pageNum = data.pageNum;
                    vm.page = data.pageNum;
                    vm.isFirstPage = data.isFirstPage;
                    vm.isLastPage = data.isLastPage;
                    vm.totalPages = data.totalPages;
                },
                error: function (data) {
                    alert("提示：系统获取角色列表失败");
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

        getRoleById: function () {
            var rid = vm.role_id
            if (rid != '') {
                $.ajax({
                    url: "/roles/" + rid,
                    headers: {'Authorization': getCookie("token")},
                    type: "get",
                    dataType: "json",

                    async: true,
                    success: function (data) {
                        vm.roles = data.role;
                        vm.isFirstPage = false;
                        vm.isLastPage = false;
                        // vm.showList=true;
                    },
                    error: function (data) {
                        alert("系统错误");
                    }

                });
            } else {
                vm.getRoleList();
            }
        },


        addRole: function () {
            var name = vm.name;
            var description = vm.description;
            alert("执行方法成功");
            $.ajax({
                url: "/roles",
                headers: {'Authorization': getCookie("token")},
                type: "post",
                data: {
                    "name": name,
                    "description": description,
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

        deleteRole: function (roleId) {

            $.ajax({
                url: "/roles",
                headers: {'Authorization': getCookie("token")},
                type: "delete",
                data:{"id":roleId},
                dataType: "json",
                // contentType: "application/json",

                async: true,
                success: function (data) {
                    var result=data.suceesee;
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

    },

    created:function () {
        this.getRoleList();
    },

});