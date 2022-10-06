import { createAPI, createImgUpAPI } from '@/utils/request'

export const qrcode = data => createAPI('/system/faceLogin/qrcode', 'get', data)
export const codeCheck = data => createAPI(`/system/faceLogin/qrcode/${data.code}`, 'get', data)
//export const checkFace = data => createImgUpAPI(`/sys/faceLogin/checkFace`, 'post', data)
import axios from "axios"
export const checkFace = (data) => {
  let config = {}
  config.data = data
  config.headers = {
    'Cache-Control': 'no-cache',
    'Content-Type': 'multipart/form-data'
  }
  config.transformRequest = data
  return axios.request({
    baseURL: process.env.BASE_API,
    method: 'post',
    url: '/system/faceLogin/checkFace',
    ...config
  });
}