/**
 * @author: 孙明君
 * @date: 2022-06-08
*/
const Mock = require("mockjs");

const units = [
    {
        unitId:1,
        unitName:'行政部',
        code:'001',
        user:'张三'
    },
    {
        unitId:2,
        unitName:'营业部',
        code:'002',
        user:'李四'
    },
    {
        unitId:3,
        unitName:'销售部',
        code:'003',
        user:'王五'
    },
    {
        unitId:4,
        unitName:'保洁部',
        code:'004',
        user:'赵六'
    },
    {
        unitId:5,
        unitName:'安保部',
        code:'005',
        user:'钱七'
    }
]

const zhiwu=[
    {
        zhiWuId:1,
        zhiWuName:'总经理',
        belongUnit:1
    },
    {
        zhiWuId:2,
        zhiWuName:'前台经理',
        belongUnit:2,
    },
    {
        zhiWuId:3,
        zhiWuName:'销售经理',
        belongUnit:3,
    },
    {
        zhiWuId:4,
        zhiWuName:'保洁经理',
        belongUnit:4,
    },
    {
        zhiWuId:5,
        zhiWuName:'安保经理',
        belongUnit:5
    },
    {
        zhiWuId:6,
        zhiWuName:'行政秘书',
        belongUnit:1
    },
    {
        zhiWuId:7,
        zhiWuName:'营业员',
        belongUnit:2
    },
    {
        zhiWuId:8,
        zhiWuName:'销售员',
        belongUnit:3
    },
    {
        zhiWuId:9,
        zhiWuName:'保洁',
        belongUnit:4
    },
    {
        zhiWuId:5,
        zhiWuName:'安保',
        belongUnit:5
    }
]

Mock.mock('http://mock/units/unit', 'get', () => { //三个参数。第一个路径，第二个请求方式post/get，第三个回调，返回值
    return units
})

Mock.mock('http://mock/units/zhiwu','get',(options)=>{
    let item = JSON.parse(options.body);
    let res= [];
    for(let i=0;i<zhiwu.length;i++){
        if(zhiwu[i].belongUnit == parseInt(item.unitId)){
            res.push(zhiwu[i])
        }
    }

    return res;
})

Mock.mock('http://mock/units/unitById','get',(options)=>{
    let item = JSON.parse(options.body);

    let res = {}
    for(let i=0;i<units.length;i++){
        if(units[i].unitId == item.id){
            res = units[i];
            break;
        }
    }

    return res;
})