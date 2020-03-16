



var menuItem = Vue.extend({
    name: 'menu-item',
     props:{item:{}},
    template:
        [
            '<a v-if="item.type===0">',
        '<a    href="#" class="nav-link active">',
           '<i class="nav-icon fas fa-tachometer-alt"></i>',
             '<p>{{item.name}}' ,
        '<i class="right fas fa-angle-left"></i>',
     '</p>',
            '</a>',
             '<ul     class="nav nav-treeview">',
       //     '<ul>',
            '<menu-item :item="item"  v-for="item in item.list" ></menu-item>',
'</ul>',
            '</a>',
           // '	<a v-if="item.type === 0" href="\'#\'+item.url">',
           //
           //   '<menu-item :item="item"  v-for="item in item.list" ></menu-item>',
           //  '</a>',
            '	<a v-else="item.type === 1 && item.parentId === 2">',
       //   '<ul     class="nav nav-treeview">',

            '<li class="nav-item">',
            '<a  class="nav-link" :href="\'#\'+item.url">',
          '<i class="far fa-circle nav-icon"></i>',
            '<p>{{item.name}}</p>',
            '</a>',
            '</li>',

     //   '</ul>',
            '</a>',


        ].join('')
});

Vue.component('menuItem',menuItem);



var vm=new Vue({
    el:'#rrapp',
    data:{
        user:{},
        menuList:{},
        main:"main"
    },
    methods:{
        getMenuList: function (event) {
            $.ajax({
                url: "/permissions",
                headers: {'Authorization': getCookie("token")},
                type: "get",
                dataType: "json",
                // contentType: "application/json",
             //   data: {"id": 1},
                async: true,

                success: function(data) {
                    vm.menuList=data.list;
                },
                error:function (data) {
                    alert("系统错误");
                }

                });
        },
        getUser: function (event) {
            $.ajax({
                url: "/info",
                headers: {'Authorization': getCookie("token")},
                type: "post",
                dataType: "json",
                async: true,

                success: function(data) {
                    vm.user=data.user;
                },
                error:function (data) {
                    alert("系统错误");
                }

            });
        }


    },
    created:function () {
        this.getUser();
        this.getMenuList();
    },
    updated:function () {
        //路由
        var router =new Router();
        routerList(router, vm.menuList);
        router.start();
    }
});

function routerList(router, menuList){
    for(var key in menuList){
        var menu = menuList[key];
        if(menu.type == 0){
            routerList(router, menu.list);
        }else if(menu.type == 1){
            router.add('#'+menu.url, function() {
                var url = window.location.hash;
                //替换iframe的url
                vm.main = url.replace('#', '');


            });
        }
    }
}
