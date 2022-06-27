import request from '@/utils/request'

// 查询用户信息列表
export function listSduser(query) {
  return request({
    url: '/obj/sduser/list',
    method: 'get',
    params: query
  })
}

// 查询用户信息详细
export function getSduser(id) {
  return request({
    url: '/obj/sduser/' + id,
    method: 'get'
  })
}

// 新增用户信息
export function addSduser(data) {
  return request({
    url: '/obj/sduser',
    method: 'post',
    data: data
  })
}

// 修改用户信息
export function updateSduser(data) {
  return request({
    url: '/obj/sduser',
    method: 'put',
    data: data
  })
}

// 删除用户信息
export function delSduser(id) {
  return request({
    url: '/obj/sduser/' + id,
    method: 'delete'
  })
}
