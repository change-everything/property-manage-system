/**
 * @author: 孙明君
 * @date: 2022-06-08
*/

const Mock = require("mockjs");

const data = Mock.mock({
    "data|100": [ //生成100条数据 数组
        {
            "ownerId|+1": 1,//生成商品id，自增1
            "ownerNum": /^[0-9]{7}$/,
            "ownerName": "@cname",//生成商品名 ， 都是中国人的名字
            "roomNum":/^[A-Z]{1}[1-9]{1}$/, //房屋编号
            "workUnit":"@ctitle(3)",
            "phoneNumber": /^1(5|3|7|8)[0-9]{9}$/,//生成随机电话号
            "floorArea|30-1000": 30, //随机生成商品价格 在30-1000之间
        }
    ]
})

Mock.mock('http://mock/owner/ownerAll', 'get', (options) => { //三个参数。第一个路径，第二个请求方式post/get，第三个回调，返回值

    let item=JSON.parse(options.body) 
    // console.log(data.data)
    let res = [];
    let start = (parseInt(item.currentPage)- 1) * parseInt(item.pageSize);
    for(var i = 0; i< parseInt(item.pageSize) && start < data.data.length; i++){
        res.push(data.data[start++]);
    }

    return res
})

Mock.mock('http://mock/owner/total', 'get', () => {
    // console.log(data)
  return data.data.length;  
})


Mock.mock('http://mock/owner/detail','get',(options)=>{

    let item = JSON.parse(options.body);
    let res = {}
    for(let i=0;i<data.data.length;i++){
        if(data.data[i].ownerId == item.id){
            res = data.data[i];
        }
    }

    return res;

})

Mock.mock('http://mock/owner/delete','get',(options)=>{
    let item = JSON.parse(options.body);

    for(let i=0;i<data.data.length;i++){
        if(data.data[i].ownerId == item.id){
            data.data.splice(i,1);
        }
    }

    let res={
        status: true
    }
    return res
})

Mock.mock('http://mmock/owner/update','post',(options) =>{
    let item = JSON.parse(options.body);

    for(let i=0;i<data.data.length;i++){
        if(data.data[i].ownerId == item.id){
            data.data[i].ownerNum = item.ownerNum;
            data.data[i].ownerName = item.ownerName;
            data.data[i].roomNum = item.roomNum;
            data.data[i].floorArea = item.floorArea;
            data.data[i].workUnit = item.workUnit;
            data.data[i].phoneNumber = item.phoneNumber;
        }
    }

    return {
        status:true
    }
})

Mock.mock('http://mock/owner/searchByownerNum','post',(options) =>{
    let item = JSON.parse(options.body);
    let res = [];
    
    if(item.roomNum != '' && item.roomNum != null){
        for(let i=0;i<data.data.length;i++){
            if(data.data[i].roomNum == item.roomNum){
                res.push(data.data[i]);
            }
        }
    }else{
        res = data.data;
    }

    let res1 =[];
    if(item.ownerName != '' && item.ownerName != null){
        for(let i=0;i<res.length;i++){
            if(res[i].ownerName == item.ownerName){
                res1.push(res[i]);
            }
        }
    }else{
        res1 = res;
    }

    let res2 = [];
    if(item.ownerNum != '' && item.ownerNum != null){
        for(let i=0;i<res1.length;i++){
            if(res1[i].ownerNum == item.ownerNum){
                res2.push(res1[i]);
            }
        }
    }else{
        res2 = res1;
    }
    
    return res2;
})


