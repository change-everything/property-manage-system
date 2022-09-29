/**
 * @author: 孙明君
 * @date: 2022-06-09
*/
/**
 * 校验工具
 */
 export default {
     /**
     * 检查电话号码是否合法
     * @param {电话号码} phone 
     */
     thePhoneIsTure(phone) {
         const reg_phone = /^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$/;
         return reg_phone.test(phone)
     },
 
     /**
      * 检查邮箱地址是否合法
      * @param {String} email 
      */
     theEmailIsTrue(email) {
         const reg_email = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
         return reg_email.test(email);
     },
 
     /**
      * 密码是否符合强度测试
      * @param {String} pwd 
      */
     thePwdIsSafe(pwd) {
         const reg_pwd = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$/;
         return reg_pwd.test(pwd);
     },
 
 
 }
 
