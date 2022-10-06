/**
 * @author: 孙明君
 * @date: 2022-06-08
*/

const Mock = require("mockjs");

const data = Mock.mock({
    "data|100": [ //生成100条数据 数组
        {
            "empId|+1": 1,//生成商品id，自增1
            "empNum": /^[A-Z]{1}[0-9]{7}$/,
            "empName": "@cname",//生成商品名 ， 都是中国人的名字
            "birthDate": "@Date",
            "address":"@county(3)",
            "empPhone": /^1(5|3|7|8)[0-9]{9}$/,//生成随机电话号
            "empPossition":/(经理|收费员)$/,
            "empDepNum":/(销售部|营业部|行政部|保洁部|安保部)$/,
            "empPwd":/[A-Z]{2}[0-9]{4}[a-z]{3}$/,
            "gender":/(男|女)$/
        }
    ]
})

/**
 * @author: 孙明君
 * @date: 2022-06-08
*/
Mock.mock('http://mock/employee/employee', 'get', (options) => { //三个参数。第一个路径，第二个请求方式post/get，第三个回调，返回值

    let item=JSON.parse(options.body) 
    console.log(data.data)
    let res = [];
    let start = (parseInt(item.currentPage)- 1) * parseInt(item.pageSize);
    for(var i = 0; i< parseInt(item.pageSize) && start < data.data.length; i++){
        res.push(data.data[start++]);
    }

    return res
})

Mock.mock('http://mock/employee/tatal', 'get', () => { //三个参数。第一个路径，第二个请求方式post/get，第三个回调，返回值

    return data.data.length;
})


Mock.mock('http://mock/employee/serach','post',(options)=>{
    let item = JSON.parse(options.body);

    let res = [];
    if(item.empNum != null && item.empNum != ''){
        for(let i=0;i<data.data.length;i++){
            if(data.data[i].empNum == item.empNum){
                res.push(data.data[i])
            }
        }
    }else{
        res = data.data;
    }

    let res1 = [];
    if(item.empPhone != null && item.empPhone != ''){
        for(let i=0;i<res.length;i++){
            if(res[i].empPhone == item.empPhone){
                res1.push(res[i]);
            }
        }
    }else{
        res1 = res;
    }

    let res2 = [];
    if(item.empDepNum != null && item.empDepNum != ''){

        let unit = '';
        switch(item.empDepNum){
            case 1:unit = '行政部';break;
            case 2:unit = '营业部';break;
            case 3:unit = '销售部';break;
            case 4:unit = '保洁部';break;
            case 5:unit = '安保部';break;
        }

        for(let i=0;i<res1.length;i++){
            if(res1[i].empDepNum == unit){
                res2.push(res1[i])
            }
        }
    }else{
        res2 = res1;
    }

    return res2;
})