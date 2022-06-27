import request from '@/utils/request'



// 新增用户信息
export function addObj(data) {
  return request({
    url: '/obj/sdobj',
    method: 'post',
    data: data
  })
}
