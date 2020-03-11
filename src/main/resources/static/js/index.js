



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
            '<a href="#" class="nav-link">',
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
        menuList:{}

    },
    methods:{
        getMenuList: function (event) {
alert("Cookie:"+getCookie("token"));
            $.ajax({
                url: "/uroles",
                headers: {'Authorization': getCookie("token")},
                type: "post",
                dataType: "json",
                // contentType: "application/json",
                data: {"id": 1},
                async: true,

                success: function(data) {
                    vm.menuList=data.rightlist;
                },
                error:function (data) {
                    alert("系统错误");
                }

                });
        }
    },
    created:function () {
        this.getMenuList();
    }
});