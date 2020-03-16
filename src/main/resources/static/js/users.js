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
        users:[],
        page:'',
        showList:true
    },
    methods: {
        getUserList: function (p) {
            $.ajax({
                url: "/users",
                headers: {'Authorization': getCookie("token")},
                type: "get",
                dataType: "json",
                data:{
                        "page":p,
                        isFirstPage:true,
                        isLastPage:false
                },
                async: true,
                success: function(data) {
                    vm.users=data.userlist;
                    pageNum=data.pageNum;
                    vm.page=data.pageNum;
                    vm.isFirstPage=data.isFirstPage;
                    vm.isLastPage=data.isLastPage;

                },
                error:function (data) {
                    alert("系统错误");
                }

            });
        },
      deleteUser: function (userId) {

        $.ajax({
            url: "/users",
            headers: {'Authorization': getCookie("token")},
            type: "delete",
            data:{"id":userId},
            dataType: "json",
            // contentType: "application/json",

            async: true,
            success: function (data) {
                var result=data.success;
                if (result==1)
                {
                    alert("删除成功");
                    this.getUserList(pageNum);
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
        shift :function () {
            if (vm.showList===true)
            {
                vm.showList=false;
            }
            else {
                vm.showList=true;
            }
        }

},

    created:function () {
        this.getUserList();
    },
    // updated:function () {
    //     this.getUserList(pageNum);
    // }
});