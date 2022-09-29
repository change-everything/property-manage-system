/**
 * @author: 孙明君
 * @date: 2022-06-08
*/

const Mock = require("mockjs");

const data = Mock.mock({
    "data|100": [ //生成100条数据 数组
        {
            "id|+1": 1,//生成商品id，自增1
            "ownerNum": /^[0-9]{7}$/,
            "houseNum":/^[A-Z]{1}[1-9]{1}$/, //房屋编号
            "currentStatus":/^(结清|欠费)$/,
            "totalCount|30-1000": 30, //随机生成商品价格 在30-1000之间
            "detail":[
                {
                    type:'物业费',
                    Number:'98.60',
                    count:'98.60'
                },
                {
                    type:'卫生费',
                    Number:'1',
                    count:'10'
                },
                {
                    type:'水费',
                    Number:'6',
                    count:'4.20'
                },
                {
                    type:'电费',
                    Number:'102',
                    count:'81.60'
                }
            ]
        }
    ]
})

Mock.mock('http://mock/fees','get',()=>{
    return data.data;
})

Mock.mock('http://mock/fees/search','get',(options)=>{
    let item = JSON.parse(options.body);

    let res = {};
    for(let i=0;i<data.data.length;i++){
        if(data.data[i].id == item.id){
            res = data.data[i];
        }
    }
    return res;
})