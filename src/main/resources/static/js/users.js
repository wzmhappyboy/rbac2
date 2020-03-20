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

function checkInput() {
    if (vm.user_id==''){
        vm.getUserList();
    }

}

const delay = (function() {
    let timer = 0;
    return function(callback, ms) {
        clearTimeout(timer);
        timer = setTimeout(callback, ms);
    };
})();


var pageNum;





var vm=new Vue({
    el: '#rrapp',
    data:{
        users:'',
        page:'',
        showList:true,
        name:'',
        password:'',
        roles:[],
        selected:'',
        totalPages:'',
        user_id:'',
        update:false,
        user:''

    },
    watch: {
        //实时查询
        user_id() {

                this.getUserById();

        },

        // user_id() {
        //     delay(() => {
        //         this.checkInput();
        //     }, 300);
        // },

    },
    methods: {
        getUserList: function (p) {
            $.ajax({
                url: "/users",
                headers: {'Authorization': getCookie("token")},
                type: "get",
                dataType: "json",
                data: {
                    "page": p,
                    isFirstPage: true,
                    isLastPage: false
                },
                async: true,
                success: function (data) {
                    vm.users = data.userlist;
                    pageNum = data.pageNum;
                    vm.page = data.pageNum;
                    vm.isFirstPage = data.isFirstPage;
                    vm.isLastPage = data.isLastPage;
                    vm.totalPages = data.totalPages;
                    // vm.showList=true;
                },
                error: function (data) {
                    alert("系统错误");
                }

            });
        },
        upd: function (uid) {
            vm.update = true;
            $.ajax({
                url: "/users/" + uid,
                headers: {'Authorization': getCookie("token")},
                type: "get",
                dataType: "json",

                async: true,
                success: function (data) {

                    vm.user=data.u;
                    vm.update = true;
                    // vm.showList=true;
                },
                error: function (data) {
                    alert("系统错误");
                }

            });
        },
        updateUser: function () {
            var user_id=vm.user.id;
var role_id=vm.selected;
var name=vm.user.name;
var password=vm.user.password;
            $.ajax({
                url: "/users",
                headers: {'Authorization': getCookie("token")},
                type: "put",
                dataType: "json",
                data:{
                  id:user_id,
                  name:name,
                  id2:role_id,
                  password:password
                },
                async: true,
                success: function (data) {
                    if (data.s==1) {
                        vm.update = false;
                        this.getUserList();
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

        getUserById: function () {
            var uid = vm.user_id;
            if (uid != '') {
                $.ajax({
                    url: "/users/" + uid,
                    headers: {'Authorization': getCookie("token")},
                    type: "get",
                    dataType: "json",

                    async: true,
                    success: function (data) {
                        vm.users = data.user;
                        vm.isFirstPage = false;
                        vm.isLastPage = false;
                        // vm.showList=true;
                    },
                    error: function (data) {
                        alert("系统错误");
                    }

                });
            } else {
                vm.getUserList();
            }
        },
        getRoleList: function (p) {
            $.ajax({
                url: "/roles",
                headers: {'Authorization': getCookie("token")},
                type: "get",
                dataType: "json",
                data: {
                    "page": 1
                },
                async: true,
                success: function (data) {
                    vm.roles = data.roleslist;

                },
                error: function (data) {
                    alert("提示：系统获取角色列表失败");
                }

            });
        },
        deleteUser: function (userId) {

            $.ajax({
                url: "/users",
                headers: {'Authorization': getCookie("token")},
                type: "delete",
                data: {"id": userId},
                dataType: "json",
                // contentType: "application/json",

                async: true,
                success: function (data) {
                    var result = data.success;
                    if (result == 1) {
                        alert("删除成功");
                        window.location.reload();
                    } else {
                        alert("有外键约束等问题删不了");
                    }

                },
                error: function (data) {
                    alert("系统错误");
                }
            });

        },
        shift: function () {
            if (vm.showList === true) {
                vm.showList = false;
            } else {
                vm.showList = true;
            }
        },
        addUser: function () {
            var name = vm.name;
            var password = vm.password;
            var role_id = vm.selected;
            $.ajax({
                url: "/users",
                // headers: {'Authorization': getCookie("token")},
                type: "post",
                data: {
                    "name": name,
                    "password": password,
                    "role_id": role_id
                },
                dataType: "json",

                async: true,
                success: function (data) {
                    var result = data.success;
                    if (result === true) {
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
    },







    created:function () {
        this.getRoleList();
        this.getUserList();
    },
    // updated:function () {
    //     this.getUserList(pageNum);
    // }
});